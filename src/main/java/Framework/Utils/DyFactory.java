package Framework.Utils;

import MySpring.Beans.Student;

/**
 * @author: gucheng.zheng
 * @Type DyFactory
 * @Description 动态工厂
 * @create: 2019-01-23 17-18
 * @Version 1.0
 **/
public class DyFactory {
    /**
     * @Author gucheng.zheng
     * @Description 动态工厂初始化Student
     * @Param []
     * @return MySpring.Beans.Student
     * @Date 2019/1/23 17:20
     */
    public Student dyInitStudent(){
        System.out.println("动态工厂初始化Student");
        return new Student();
    }
}
