import MySpring.Beans.Student;
import MySpring.Utils.BeanInstance;
import MySpring.Utils.Factory;
import MySpring.Utils.ScopeType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: gucheng.zheng
 * @create: 2018-12-23 20-01
 **/
public class MySpringTest {

    public static void main(String[] args) throws Exception {
        BeanInstance singletonBean = new BeanInstance();
        singletonBean.setClassName("MySpring.Beans.Student");
        singletonBean.setId("student");
        Map<String, String> propertyMap = new HashMap<>();
        propertyMap.put("firstName", "Wang");
        singletonBean.setPropertyMap(propertyMap);

        BeanInstance prototypeBean = new BeanInstance();
        prototypeBean.setClassName("MySpring.Beans.Student");
        prototypeBean.setId("student1");
        prototypeBean.setScopeType(ScopeType.prototype);
        propertyMap = new HashMap<>();
        propertyMap.put("firstName", "Li");
        prototypeBean.setPropertyMap(propertyMap);


        List<BeanInstance> beans = new ArrayList<>();
        beans.add(prototypeBean);
        beans.add(singletonBean);
        Factory.init(beans);

        Student stu1 = (Student) Factory.getBean("student");
        Student stu2 = (Student) Factory.getBean("student");
        System.out.println("singleton:");
        System.out.println(stu1 == stu2);
        System.out.println(stu1.getFirstName() );

        Student stu3 = (Student) Factory.getBean("student1");
        Student stu4 = (Student) Factory.getBean("student1");
        System.out.println("prototype:");
        System.out.println(stu3 == stu4);
        System.out.println(stu3.getFirstName());
    }
}
