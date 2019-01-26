package MySpring.Utils.FrameWork;

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
            System.out.println("id为" + id + " 不存在");
        }
        switch (bean.getScopeType()){
            case singleton:
                obj = singleBeans.get(id);
                break;
            case prototype:
                obj = this.instance(bean);
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

        // 初始化bean对象
        beans.forEach(bean -> {
            if(null != bean && (null == bean.getScopeType() || ScopeType.singleton.equals(bean.getScopeType())) ){
                Object obj = this.instance(bean);
                singleBeans.put(bean.getId(), obj);
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
    private Object instance(BeanInstance bean){
        Object obj = null;
        if(null != bean.getFactoryAttribute()){
            obj = this.factoryBeanInstance(bean);
        }else{
            obj = this.defaultBeanInstance(bean);
        }

        // 初始化属性
        this.assignAttribute(obj, bean);
        return obj;
    }

    /**
     * @author gucheng.zheng
     * @description 默认初始化bean
     * @Param bean
     * @return java.lang.Object
     * @date 2019/1/26 19:01
     */
    private Object defaultBeanInstance(BeanInstance bean){
        return this.beanInstance(bean.getClassName(), null);
    }

    /**
     * @author gucheng.zheng
     * @description 使用工厂初始化bean
     * @Param bean
     * @return java.lang.Object
     * @date 2019/1/26 19:01
     */
    private Object factoryBeanInstance(BeanInstance bean){
        FactoryAttribute factoryAttribute = bean.getFactoryAttribute();
        Object obj = null;
        try {
            if (StringUtils.isNotEmpty(factoryAttribute.getFactoryBean())) {
                Object dyFactoryObj = this.getBean(factoryAttribute.getFactoryBean() );
                Class clazz = Class.forName(beans.get(factoryAttribute.getFactoryBean()).getClassName() );
                Method method = clazz.getMethod(factoryAttribute.getFactoryMethod());
                obj = method.invoke(dyFactoryObj);
            } else {
                obj = this.beanInstance(bean.getClassName(), factoryAttribute.getFactoryMethod());
            }
        }catch (Exception e){
            e.printStackTrace();
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
    private Object beanInstance(String className, String methodName){
        Object obj = null;
        try {
            Class clazz = Class.forName(className);
            obj = clazz.newInstance();
            if(StringUtils.isNotEmpty(methodName) ) {
                Method method = clazz.getDeclaredMethod(methodName);
                if(Modifier.isStatic(clazz.getModifiers()) ){
                    obj = method.invoke(null, null);
                }else{
                    obj = method.invoke(obj, null);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
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
    private void assignAttribute(Object object, BeanInstance bean){
        try {
            if(CollectionUtils.isEmpty(bean.getPropertyMap()) ){
                return;
            }
            Class clazz = Class.forName(object.getClass().getName());
            for (Map.Entry<String, String> entry : bean.getPropertyMap().entrySet()) {
                Field field = clazz.getDeclaredField(entry.getKey());
                field.setAccessible(true);
                field.set(object, entry.getValue());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
