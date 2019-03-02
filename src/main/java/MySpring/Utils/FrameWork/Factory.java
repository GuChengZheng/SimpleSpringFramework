package MySpring.Utils.FrameWork;

import MySpring.Utils.FrameWork.Enums.BeanPostType;
import MySpring.Utils.FrameWork.Enums.ScopeType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 构建Bean的工厂，相当于spring框架中的ApplicationContext
 * @author: gucheng.zheng
 * @create: 2018-12-23 23-07
 **/
public class Factory {

    /**
     * 存储作用域为singleton的bean实例（默认情况下为singleton）
     */
    private static Map<String, Object> singleBeans = new HashMap<>();

    /**
     * 存储bean信息
     */
    private static Map<String, BeanInstance> beans = new HashMap<>();

    /**
     * 存储beanpost处理器
     */
    private static Map<String, Object> beanPosts = new HashMap<>();

    /**
     * @author gucheng.zheng
     * @description 根据Bean id获取实例
     * @Param id
     * @return java.lang.Object
     * @date 2019/1/26 18:53
     */
    public Object getBean(String id){
        Object obj = null;
        BeanInstance bean = beans.get(id);
        if(null == bean){
            System.out.println("id为 " + id + " 不存在");
            return null;
        }
        switch (bean.getScopeType()){
            case singleton:
                obj = singleBeans.get(id);
                break;
            case prototype:
                obj = this.beanInstance(bean);
                break;
        }
        return obj;
    }

    /**
     * @author gucheng.zheng
     * @description 添加beans集合并初始化
     * @Param beans
     * @return void
     * @date 2019/1/26 18:55
     */
    public void init(List<BeanInstance> beans){
        // 存储bean
        beans.forEach(bean ->{
            this.beans.put(bean.getId(), bean);
        });

        // beanPost 初始化
        beans.forEach(bean -> {
            this.beanPostInit(bean);
        });

        // 初始化bean对象
        beans.forEach(bean -> {
            if (!singleBeans.containsKey(bean.getId())) {
                this.beanInstance(bean);
            }
        });
    }

    /**
     * @author gucheng.zheng
     * @description 初始化bean
     * @Param bean
     * @return java.lang.Object
     * @date 2019/1/26 18:57
     */
    private Object beanInstance(BeanInstance bean){
        try {
            Object obj = singleBeans.get(bean.getId());
            if (null != obj) {
                return obj;
            }

            // 创建bean对象
            if (null != bean.getFactoryAttribute()) {
                obj = this.factoryBeanInstance(bean);
            } else {
                obj = this.defaultBeanInstance(bean);
            }

            // 初始化属性
            this.assignAttribute(obj, bean);

            // beanPost处理器，只有不是beanPost接口的类才能调用beanPost方法
            if (!BeanPost.class.isAssignableFrom(obj.getClass())) {
                // beanPost before
                this.beanPost(obj, bean.getId(), BeanPostType.BEFORE);

                // beanPost after
                this.beanPost(obj, bean.getId(), BeanPostType.AFTER);
            }

            // 存储bean
            if (null != bean && (null == bean.getScopeType() || ScopeType.singleton.equals(bean.getScopeType()))) {
                singleBeans.put(bean.getId(), obj);
            }
            return obj;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @author gucheng.zheng
     * @description 默认初始化bean
     * @Param bean
     * @return java.lang.Object
     * @date 2019/1/26 19:01
     */
    private Object defaultBeanInstance(BeanInstance bean) throws Exception{
        return this.beanInstance(bean.getClassName(), null);
    }

    /**
     * @author gucheng.zheng
     * @description 使用工厂初始化bean
     * @Param bean
     * @return java.lang.Object
     * @date 2019/1/26 19:01
     */
    private Object factoryBeanInstance(BeanInstance bean) throws Exception{
        FactoryAttribute factoryAttribute = bean.getFactoryAttribute();
        Object obj = null;

        if (StringUtils.isNotEmpty(factoryAttribute.getFactoryBean())) {
            // 如果是动态工厂，则需要先初始化
            BeanInstance factoryBean = beans.get(bean.getFactoryAttribute().getFactoryBean());
            Object dyFactoryObj = this.beanInstance(factoryBean);
            Class clazz = Class.forName(factoryBean.getClassName() );
            Method method = clazz.getMethod(factoryAttribute.getFactoryMethod());
            obj = method.invoke(dyFactoryObj);
            //obj = this.beanInstance(beans.get(factoryAttribute.getFactoryBean()).getClassName(), factoryAttribute.getFactoryMethod());
        } else {
            obj = this.beanInstance(bean.getClassName(), factoryAttribute.getFactoryMethod());
        }
        return obj;
    }

    /**
     * @author gucheng.zheng
     * @description 创建Bean
     * @Param className
     * @Param methodName
     * @return java.lang.Object
     * @date 2019/1/26 19:07
     */
    private Object beanInstance(String className, String methodName) throws Exception{
        Object obj = null;
        Class clazz = Class.forName(className);
        obj = clazz.newInstance();
        if(StringUtils.isNotEmpty(methodName) ) {
            Method method = clazz.getDeclaredMethod(methodName);
            // 判断是否为静态方法
            if(Modifier.isStatic(clazz.getModifiers()) ){
                obj = method.invoke(null, null);
            }else{
                obj = method.invoke(obj, null);
            }
        }
        return obj;
    }

    /**
     * @author gucheng.zheng
     * @description 属性赋值
     * @Param object
     * @Param bean 
     * @return void
     * @date 2019/1/26 19:10
     */
    private void assignAttribute(Object object, BeanInstance bean) throws Exception{
        if(CollectionUtils.isEmpty(bean.getPropertyMap()) ){
            return;
        }
        Class clazz = Class.forName(object.getClass().getName());
        for (Map.Entry<String, String> entry : bean.getPropertyMap().entrySet()) {
            Field field = clazz.getDeclaredField(entry.getKey());
            field.setAccessible(true);
            field.set(object, entry.getValue());
        }
    }


    /**
     * @author gucheng.zheng
     * @description beanPost 初始化
     * @Param bean
     * @return void
     * @date 2019/3/2 17:13
     */
    private void beanPostInit(BeanInstance bean){
        try {
            Class clazz = Class.forName(bean.getClassName());
            // beanPost 初始化
            if (BeanPost.class.isAssignableFrom(clazz)) {
                Object obj = this.defaultBeanInstance(bean);
                beanPosts.put(bean.getId(), obj);
                singleBeans.put(bean.getId(), obj);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * @author gucheng.zheng
     * @description BeanPost处理器
     * @Param targetObj
     * @Param id
     * @Param beanPostType
     * @return void
     * @date 2019/3/2 17:22
     */
    private void beanPost(Object targetObj, String id, BeanPostType beanPostType){
        switch (beanPostType) {
            case BEFORE:
                beanPosts.values().forEach(obj -> {
                    Object tmp = ((BeanPost)obj).postProcessBeforeInstantiation(targetObj, id);
                    if(singleBeans.containsKey(id)){
                        singleBeans.put(id, tmp);
                    }
                });
                break;
            case AFTER:
                beanPosts.values().forEach(obj -> {
                    Object tmp = ((BeanPost)obj).postProcessAfterInstantiation(targetObj, id);
                    if(singleBeans.containsKey(id)){
                        singleBeans.put(id, tmp);
                    }
                });
                break;
        }
    }

}
