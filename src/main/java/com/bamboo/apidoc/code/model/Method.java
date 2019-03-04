package com.bamboo.apidoc.code.model;

import lombok.Data;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import java.util.Set;


/**
 * @Author: GuoQing
 * @Date: 2019/2/13 16:43
 * @description 路由方法详情
 */
@Data
public class Method {
    /**
     * 方法名
     */
    private String name;
    /**
     * 包名
     */
    private String packageName;
    /**
     * 类名称
     */
    private String className;
    /**
     * 中文名
     */
    private String chineseName;
    /**
     * 接口地址
     */
    private Set<String> routPaths;
    /**
     * 方法类型
     */
    private Set<RequestMethod> methodTypes;
    /**
     * 方法描述
     */
    private String description;
    /**
     * 方法参数
     */
    private Param[] params;

    /**
     * 返回类型
     */
    private Object returnType;

    /**
     * 根据 RequestMappingInfo 和 handler 构建Method对象
     *
     * @param mappingInfo mappingInfo
     * @param handler     handler
     * @return 返回构建好的Method对象
     */
    static Method buildMethod(RequestMappingInfo mappingInfo, HandlerMethod handler) {
        Assert.notNull(mappingInfo, "RequestMappingInfo must not be null");
        Assert.notNull(handler, "HandlerMethod must not be null");
        Method method = new Method();
        method.setMethodTypes(mappingInfo.getMethodsCondition().getMethods());
        method.setRoutPaths(mappingInfo.getPatternsCondition().getPatterns());
        method.setName(handler.getMethod().getName());
        method.setPackageName(handler.getMethod().getDeclaringClass().getPackage().getName());
        method.setClassName(handler.getMethod().getDeclaringClass().getName());
        method.setParams(Param.buildParams(handler));
        return method;
    }

    boolean isChange(Method newMethod) {
        if (newMethod != null) {
            return !newMethod.equals(this);
        }
        return false;
    }


}
