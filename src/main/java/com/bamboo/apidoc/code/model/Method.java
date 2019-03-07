package com.bamboo.apidoc.code.model;

import lombok.Data;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;

/**
 * @Author: GuoQing
 * @Date: 2019/3/1 17:28
 * @description 方法详细信息
 */
@Data
public class Method {
    /**
     * 默认总版本初始值
     */
    public static final Integer defaultTotalVersion = 1;
    /**
     * 方法标记
     */
    private MethodMark methodMark;
    /**
     * 当前接口信息
     */
    private MethodInfo methodInfo;
    /**
     * 当前检测版本号
     */
    private String checkVersion;
    /**
     * 此方法总版本记录
     */
    private Integer totalVersion;
    /**
     * 已经改动版本
     */
    private MethodInfo[] methodHistories;

    /**
     * 初始化方法实体
     *
     * @param mappingInfo RequestMappingInfo
     * @param handler     HandlerMethod
     * @return method对象
     */
    static Method buildMethod(RequestMappingInfo mappingInfo, HandlerMethod handler) {
        Method method = new Method();
        method.setMethodMark(new MethodBasic().getMethodMark(Boolean.FALSE));
        MethodBasic methodBasic = MethodBasic.buildMethodBasic(mappingInfo, handler);
        method.setMethodInfo(methodBasic.getMethodInfo(MethodInfo.defaultVersion));
        method.setTotalVersion(defaultTotalVersion);
        return method;
    }


    boolean isChange(MethodBasic newMethodInfo) {
        if (newMethodInfo != null) {
            return !newMethodInfo.equals(this.getMethodInfo().getMethodBasic());
        }
        return false;
    }

    boolean isChange(Method method) {
        if (method != null && method.getMethodInfo() != null) {
            return isChange(method.getMethodInfo().getMethodBasic());
        }
        return false;
    }
}
