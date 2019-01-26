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


    public void init(List<BeanInstance> beans){

        beans.forEach(bean ->{
            this.beans.put(bean.getId(), bean);
        });

        beans.forEach(bean -> {
            if(null != bean && (null == bean.getScopeType() || ScopeType.singleton.equals(bean.getScopeType())) ){
                Object obj = this.instance(bean);
                singleBeans.put(bean.getId(), obj);
            }
        });
    }

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

    private Object defaultBeanInstance(BeanInstance bean){
        return this.beanInstance(bean.getClassName(), null);
    }

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

   /* private Object instance(BeanInstance bean) throws Exception{
        Object object = null;
        if(null == bean.getFactoryAttribute()) {
            object = initObj(bean);
        }else{
            // 如果FactoryBean有值，说明使用的是动态工厂
            if(StringUtils.isNotEmpty(bean.getFactoryAttribute().getFactoryBean()) ) {

            }else{ // 否则使用静态工厂

            }
        }

        assignAttribute(object, bean);
        return object;
    }

    private Object initObj(BeanInstance bean) throws Exception{
        Class clazz = Class.forName(bean.getClassName());
        Object object = clazz.newInstance();
        return object;
    }

    private void assignAttribute(Object object, BeanInstance bean) throws Exception{
        Class clazz = Class.forName(bean.getClassName());
        for(Map.Entry<String, String> entry : bean.getPropertyMap().entrySet()) {
            Field field = clazz.getDeclaredField(entry.getKey());
            field.setAccessible(true);
            field.set(object, entry.getValue());
        }
    }*/

}
