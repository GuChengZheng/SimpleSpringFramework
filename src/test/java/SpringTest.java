import MySpring.Beans.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: gucheng.zheng
 * @create: 2018-12-23 20-01
 **/
public class SpringTest {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Config.xml");
        Student stu = (Student) context.getBean("student");
        System.out.println(stu);
        System.out.println(stu.getFirstName());
    }
}
