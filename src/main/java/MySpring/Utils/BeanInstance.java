package MySpring.Utils;

import java.util.Map;

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

    private Map<String, String> propertyMap;

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

    public Map<String, String> getPropertyMap() {
        return propertyMap;
    }

    public void setPropertyMap(Map<String, String> propertyMap) {
        this.propertyMap = propertyMap;
    }
}
