package MySpring.Beans;

/**
 * 静态工厂
 * @author: gucheng.zheng
 * @create: 2019-01-26 18-05
 **/
public class StaticFactory {
    public static Student init(){
        System.out.println("StaticFactory init...");
        return new Student();
    }
}
