import MySpring.Beans.Student;
import MySpring.Utils.FrameWork.BeanInstance;
import MySpring.Utils.FrameWork.Factory;

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

        Factory factory = new Factory();
        List<BeanInstance> beans = new ArrayList<>();

        BeanInstance bean1 = new BeanInstance();
        bean1.setClassName("MySpring.Beans.Student");
        bean1.setId("student1");
        Map<String, String> propertyMap1 = new HashMap<>();
        propertyMap1.put("firstName", "Li");
        propertyMap1.put("123", "Li");
        bean1.setPropertyMap(propertyMap1);
        beans.add(bean1);

        BeanInstance bean2 = new BeanInstance();
        bean2.setClassName("MySpring.Beans.MyBeanPostImpl");
        bean2.setId("MyBeanPost");
        beans.add(bean2);

        BeanInstance bean3 = new BeanInstance();
        bean3.setClassName("MySpring.Beans.MyBeanPostImpl2");
        bean3.setId("MyBeanPost2");
        beans.add(bean3);

        factory.init(beans);

        Student stu = (Student) factory.getBean("student1");
        System.out.println(stu);
        System.out.println(stu.getFirstName());

        /*
        // DyFactory
        BeanInstance dyFactoryBean = new BeanInstance();
        dyFactoryBean.setId("factory");
        dyFactoryBean.setClassName("MySpring.Beans.DyFactory");
        beans.add(dyFactoryBean);

        // 1
        BeanInstance bean1 = new BeanInstance();
        bean1.setClassName("MySpring.Beans.Student");
        bean1.setId("student1");

        Map<String, String> propertyMap1 = new HashMap<>();
        propertyMap1.put("firstName", "Li");
        bean1.setPropertyMap(propertyMap1);

        FactoryAttribute attribute1 = new FactoryAttribute();
        attribute1.setFactoryBean("factory");
        attribute1.setFactoryMethod("init");
        bean1.setFactoryAttribute(attribute1);

        beans.add(bean1);

        // 2
        BeanInstance bean2 = new BeanInstance();
        bean2.setClassName("MySpring.Beans.StaticFactory");
        bean2.setId("student2");

        Map<String, String> propertyMap2 = new HashMap<>();
        propertyMap2.put("firstName", "Wang");
        bean2.setPropertyMap(propertyMap2);

        FactoryAttribute attribute2 = new FactoryAttribute();
        attribute2.setFactoryMethod("init");
        bean2.setFactoryAttribute(attribute2);

        beans.add(bean2);


        factory.init(beans);

        // test
        Student stu1 = (Student) factory.getBean("student1");
        System.out.println(stu1 + "\t" + stu1.getFirstName());

        Student stu2 = (Student) factory.getBean("student2");
        System.out.println(stu2 + "\t" + stu2.getFirstName());*/


       /* List<BeanInstance> beans1 = new ArrayList<>();
        beans1.add(create());
        Factory.init(beans1);

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 10; i++){
                    try {
                        Student student = (Student) Factory.getBean("student");
                        System.out.println("thread1： " + student);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 10; i++){
                    try {
                        Student student = (Student) Factory.getBean("student");
                        System.out.println("thread2： " + student);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        thread1.run();
        thread2.run();*/
    }

    /*private static BeanInstance create(){
        BeanInstance singletonBean = new BeanInstance();
        singletonBean.setClassName("MySpring.Beans.Student");
        singletonBean.setId("student");
        Map<String, String> propertyMap = new HashMap<>();
        propertyMap.put("firstName", "Wang");
        singletonBean.setPropertyMap(propertyMap);
        return singletonBean;
    }*/
}
