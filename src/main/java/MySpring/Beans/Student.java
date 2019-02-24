package MySpring.Beans;

/**
 * @author: gucheng.zheng
 * @create: 2018-12-23 20-00
 **/
public class Student {
    private String firstName;

    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        System.out.println("setFirstName...");
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        System.out.println("setLastName...");
        this.lastName = lastName;
    }

    public void init(){
        System.out.println("init....");
    }

    public void destory(){
        System.out.println("destory...");
    }

    public Student() {
        System.out.println("new Student()...");
    }
}
