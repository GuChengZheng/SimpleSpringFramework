package MySpring.Utils.FrameWork;

import MySpring.Utils.FrameWork.Enums.ScopeType;

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
     *
     * 初始化属性值
     */
    private Map<String, String> propertyMap;

    /**
     * 作用域
     */
    private ScopeType scopeType = ScopeType.singleton;

    /**
     * 工厂属性
     */
    private FactoryAttribute factoryAttribute;

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

    public FactoryAttribute getFactoryAttribute() {
        return factoryAttribute;
    }

    public void setFactoryAttribute(FactoryAttribute factoryAttribute) {
        this.factoryAttribute = factoryAttribute;
    }
}
