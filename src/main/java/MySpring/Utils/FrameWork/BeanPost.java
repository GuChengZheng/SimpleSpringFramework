package MySpring.Utils.FrameWork;

/**
 * BeanPost接口
 * @author: gucheng.zheng
 * @create: 2019-02-23 12-15
 **/
public interface BeanPost {
    
    /**
     * @author gucheng.zheng
     * @description 在实例化和属性赋值之后，在定制初始化方法（如init-method）之前调用。
     * @Param obj
     * @Param beanName
     * @return java.lang.Object
     * @date 2019/2/24 21:06
     */
    default Object postProcessBeforeInstantiation(Object obj, String beanName){
        return obj;
    }

    /**
     * @author gucheng.zheng
     * @description 在定制初始化方法（init-method）之后调用。
     * @Param obj
     * @Param beanName 
     * @return java.lang.Object
     * @date 2019/2/24 21:07
     */
    default Object postProcessAfterInstantiation(Object obj, String beanName){
        return obj;
    }
}
