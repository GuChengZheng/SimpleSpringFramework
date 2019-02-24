import MySpring.Beans.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FrameworkTest {
    public static void main(String[] args) {
        // 作用域验证
        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Config.xml");

        Student stu = (Student) context.getBean("student");
        System.out.println(stu);
        /*Student stu1 = (Student) context.getBean("student1");
        System.out.println(stu1);*/

        // 动态和静态工厂
        /*Student stuDyFactory = (Student) context.getBean("studentByDyFactory");
        System.out.println(stuDyFactory);
        Student stuStaticFactory = (Student) context.getBean("studentByStaticFactory");
        System.out.println(stuStaticFactory);*/

    }
}
