package MySpring.Utils;

import org.springframework.context.annotation.Bean;

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
    private Map<String, BeanInstance> singleBeans = new HashMap<>();

    public void init(List<BeanInstance> beans){
        beans.stream().forEach(bean -> {singleBeans.put(bean.getId(), bean);});

    }

    public Object getBean(String id) throws Exception{
        BeanInstance bean = singleBeans.get(id);
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
