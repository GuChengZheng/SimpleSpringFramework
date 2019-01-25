package MySpring.Utils.FrameWork;

/**
 * 工厂属性
 * @author: gucheng.zheng
 * @create: 2019-01-24 21-59
 **/
public class FactoryAttribute {

    /**
     * 动态工厂对应Bean的id
     */
    private String factoryBean;

    /**
     * 要初始化的方法
     */
    private String factoryMethod;

    public String getFactoryBean() {
        return factoryBean;
    }

    public void setFactoryBean(String factoryBean) {
        this.factoryBean = factoryBean;
    }

    public String getFactoryMethod() {
        return factoryMethod;
    }

    public void setFactoryMethod(String factoryMethod) {
        this.factoryMethod = factoryMethod;
    }
}
