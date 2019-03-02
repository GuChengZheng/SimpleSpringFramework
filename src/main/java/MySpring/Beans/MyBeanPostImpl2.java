package MySpring.Beans;

import MySpring.Utils.FrameWork.BeanPost;

/**
 * @author: gucheng.zheng
 * @create: 2019-02-24 21-13
 **/
public class MyBeanPostImpl2 implements BeanPost {
    @Override
    public Object postProcessBeforeInstantiation(Object obj, String beanName) {
        System.out.println("bean post2 before...");
        return obj;
    }

    @Override
    public Object postProcessAfterInstantiation(Object obj, String beanName) {
        System.out.println("bean post2 after...");
        return obj;
    }
}
