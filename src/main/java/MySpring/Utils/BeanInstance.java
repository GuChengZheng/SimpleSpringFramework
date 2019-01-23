package MySpring.Utils;

import MySpring.Utils.Enums.ScopeType;

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

    /**
     * 初始化属性值
     */
    private Map<String, String> propertyMap;

    /**
     * 添加作用域
     */
    private ScopeType scopeType;

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

    public ScopeType getScopeType() {
        return scopeType;
    }

    public void setScopeType(ScopeType scopeType) {
        this.scopeType = scopeType;
    }
}
