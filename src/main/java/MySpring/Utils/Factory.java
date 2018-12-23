package MySpring.Utils;

import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 构建Bean的工厂，相当于spring框架中的ApplicationContext
 * @author: gucheng.zheng
 * @create: 2018-12-23 23-07
 **/
public class Factory {
    private Map<String, BeanInstance> singleBeans = new HashMap<>();

    public void init(BeanInstance bean){
        singleBeans.put(bean.getId(), bean);
    }

    public void getBean(String id){
        BeanInstance bean = singleBeans.get(id);

    }

}
