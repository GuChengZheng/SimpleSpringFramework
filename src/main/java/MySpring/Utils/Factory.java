package MySpring.Utils;

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
     * 存储作用域为singleton的bean集合（默认情况下为singleton）
     */
    private static Map<String, BeanInstance> singletonBeans = new HashMap<>();

    private static  Map<String, BeanInstance> prototypeBeans = new HashMap<>();

    public void init(List<BeanInstance> beans){
        beans.stream().forEach(bean -> {
            switch (bean.getScopeType()){
                case singleton:
                    singletonBeans.put(bean.getId(), bean);
                    break;
                case prototype:
                    prototypeBeans.put(bean.getId(), bean);
                    break;
            }
        });
    }

    public Object getBean(String id) throws Exception{
        BeanInstance bean = singletonBeans.get(id);
        Class clazz = Class.forName(bean.getClassName());
        Object object = clazz.newInstance();
        for(Map.Entry<String, String> entry : bean.getPropertyMap().entrySet()) {
            Field field = clazz.getDeclaredField(entry.getKey());
            field.setAccessible(true);
            field.set(object, entry.getValue());
        }
        return object;
    }

}
