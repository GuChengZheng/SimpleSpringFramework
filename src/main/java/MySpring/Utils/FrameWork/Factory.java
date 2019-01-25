package MySpring.Utils.FrameWork;

import MySpring.Utils.FrameWork.Enums.ScopeType;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Method;
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

    private void loadBean(List<BeanInstance> beans){
        beans.forEach(bean -> {
            if(null == Factory.beans.get(bean.getId()) ) {
                if (null == bean.getScopeType())
                    bean.setScopeType(ScopeType.singleton);
                Factory.beans.put(bean.getId(), bean);
            }
        });
    }

    public void init(String beanId){
        BeanInstance bean = beans.get(beanId);
        if(null != bean) {
            if (StringUtils.isNotEmpty(bean.getParentId())) {
                this.init(bean.getParentId());
            } else {
                this.instance(bean);
            }
        }
    }

    private void instance(BeanInstance bean){
        if(null != bean.getFactoryAttribute()){
            this.factoryBeanInstance();
        }else{
            this.defaultBeanInstance();
        }
    }

    private void defaultBeanInstance(){

    }

    private void factoryBeanInstance(BeanInstance bean){
        FactoryAttribute factoryAttribute = bean.getFactoryAttribute();
        if(StringUtils.isNotEmpty(factoryAttribute.getFactoryBean()) ){
            Object dyFactoryObj = this.getBean(factoryAttribute.getFactoryBean());
            Class clazz = Class.forName(beans.get());
            Method method = clazz.getMethod(factoryAttribute.getFactoryMethod());
            Object obj = (Object)method.invoke(dyFactoryObj);

        }else{
            this.beanInstance();
        }
    }

    private Object beanInstance(String className, String methodName){
        Object obj = null;
        try {
            Class clazz = Class.forName(className);
            if(StringUtils.isNotEmpty() ) {
                obj = clazz.newInstance();
            }else{
                Method method = clazz.getDeclaredMethod(methodName);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return obj;
    }

    private void assignAttribute(){

    }

    public Object getBean(String id){
       /* Object obj = null;
        BeanInstance bean = beans.get(id);
        switch (bean.getScopeType()){
            case singleton:
                synchronized (Factory.class) {
                    obj = singleBeans.get(bean.getId());
                    if (null == obj) {
                        obj = instance(bean);
                        singleBeans.put(bean.getId(), obj);
                    }
                }
                break;
            case prototype:
                obj = instance(bean);
                break;
        }
        return obj;*/
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
