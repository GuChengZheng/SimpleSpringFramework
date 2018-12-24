import MySpring.Beans.Student;
import MySpring.Utils.BeanInstance;
import MySpring.Utils.Factory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: gucheng.zheng
 * @create: 2018-12-23 20-01
 **/
public class SpringTest {

    public static void main(String[] args) throws Exception {
        BeanInstance bean = new BeanInstance();
        bean.setClassName("MySpring.Beans.Student");
        bean.setId("student");
        Map<String, String> propertyMap = new HashMap<>();
        propertyMap.put("firstName", "Wang");
        bean.setPropertyMap(propertyMap);

        List<BeanInstance> beans = new ArrayList<>();
        beans.add(bean);

        Factory factory = new Factory();
        factory.init(beans);
        Student student = (Student) factory.getBean("student");
        System.out.println(student);
        System.out.println(student.getFirstName());


    }
}
