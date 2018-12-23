package MySpring.Utils;

/**
 * bean的实体类
 * @author: gucheng.zheng
 * @create: 2018-12-23 23-07
 **/
public class BeanInstance {

    /**
     * bean 唯一标识id
     */
    private String id;

    /**
     * 类完整路径
     */
    private String className;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
