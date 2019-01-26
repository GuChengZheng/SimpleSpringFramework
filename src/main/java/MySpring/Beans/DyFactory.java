package MySpring.Beans;

/**
 * 动态工厂
 * @author: gucheng.zheng
 * @create: 2019-01-26 18-05
 **/
public class DyFactory {
    public Student init(){
        System.out.println("DyFactory init...");
        return new Student();
    }
}
