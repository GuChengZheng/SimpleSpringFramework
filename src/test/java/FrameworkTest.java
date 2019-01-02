import MySpring.Beans.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FrameworkTest {
    public static void main(String[] args) {
        // 作用域验证
        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Config.xml");
        Student stu = (Student) context.getBean("student");
        Student stu1 = (Student) context.getBean("student");
        System.out.println(stu == stu1);
        Student stu2 = (Student) context.getBean("student1");
        Student stu3 = (Student) context.getBean("student1");
        System.out.println(stu2 == stu3);

    }
}