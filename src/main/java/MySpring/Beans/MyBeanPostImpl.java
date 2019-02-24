package MySpring.Beans;

import MySpring.Utils.FrameWork.BeanPost;

/**
 * @author: gucheng.zheng
 * @create: 2019-02-24 21-13
 **/
public class MyBeanPostImpl implements BeanPost {
    @Override
    public Object postProcessBeforeInstantiation(Object obj, String beanName) {
        System.out.println("bean post before...");
        return obj;
    }

    @Override
    public Object postProcessAfterInstantiation(Object obj, String beanName) {
        System.out.println("bean post after...");
        return obj;
    }
}
