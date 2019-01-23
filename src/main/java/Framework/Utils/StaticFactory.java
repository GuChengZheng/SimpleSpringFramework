package Framework.Utils;

import MySpring.Beans.Student;

/**
 * @author: gucheng.zheng
 * @Type StaticFactory
 * @Description
 * @create: 2019-01-23 17-27
 * @Version 1.0
 **/
public class StaticFactory {

    /**
     * @Author gucheng.zheng
     * @Description 静态工厂初始化Student
     * @Param []
     * @return MySpring.Beans.Student
     * @Date 2019/1/23 17:23
     */
    public static Student staticInitStudent(){
        System.out.println("静态工厂初始化Student");
        return new Student();
    }
}
