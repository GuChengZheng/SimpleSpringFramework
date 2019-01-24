package MySpring.Utils;

import MySpring.Utils.Enums.ScopeType;

import java.lang.reflect.Field;
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

    public void init(List<BeanInstance> beans){
       beans.forEach(bean -> {
           if(null == Factory.beans.get(bean.getId()) ) {
               if (null == bean.getScopeType())
                   bean.setScopeType(ScopeType.singleton);
               Factory.beans.put(bean.getId(), bean);
           }
       });
    }

    public Object getBean(String id){
        Object obj = null;
        BeanInstance bean = beans.get(id);
        switch (bean.getScopeType()){
            case singleton:
                synchronized (Factory.class) {
                    obj = singleBeans.get(bean.getId());
                    if (null == obj) {
                        obj = createInstance(bean);
                        singleBeans.put(bean.getId(), obj);
                    }
                }
                break;
            case prototype:
                obj = createInstance(bean);
                break;
        }
        return obj;
    }

    /**
     * 初始化实例对象
     * @param bean
     * @return
     */
    private Object createInstance(BeanInstance bean){
        Object object = null;
        try {
            object = initObj(bean);
            assignAttribute(object, bean);
        }catch (Exception e){
            e.printStackTrace();
        }
        return object;
    }

    private Object initObj(BeanInstance bean){
        Object object = null;
        try {
            Class clazz = Class.forName(bean.getClassName());
            object = clazz.newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }
        return object;
    }

    private void assignAttribute(Object object, BeanInstance bean){
        try {
            Class clazz = Class.forName(bean.getClassName());
            for(Map.Entry<String, String> entry : bean.getPropertyMap().entrySet()) {
                Field field = clazz.getDeclaredField(entry.getKey());
                field.setAccessible(true);
                field.set(object, entry.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
