/**
 * @author: gucheng.zheng
 * @create: 2018-12-23 20-01
 **/
public class MySpringTest {

    public static void main(String[] args) throws Exception {

        /*Factory factory = new Factory();

        BeanInstance prototypeBean = new BeanInstance();
        prototypeBean.setClassName("MySpring.Beans.Student");
        prototypeBean.setId("student1");
        prototypeBean.setScopeType(ScopeType.prototype);
        Map<String, String> propertyMap = new HashMap<>();
        propertyMap.put("firstName", "Li");
        prototypeBean.setPropertyMap(propertyMap);

        List<BeanInstance> beans = new ArrayList<>();
        beans.add(prototypeBean);

        factory.init(beans);
        Student student = (Student) factory.getBean("student1");
        System.out.println(student.getFirstName());*/

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
