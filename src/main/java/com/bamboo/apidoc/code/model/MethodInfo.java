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
public class MethodInfo {
    /**
     * 方法标记
     */
    private MethodMark methodMark;
    /**
     * 当前接口信息
     */
    private Method method;

    /**
     * 当前方法版本号
     */
    private  String methodVersion;
    /**
     * 已经改动版本
     */
    private Method[] changeMethods;

    static MethodInfo buildMethodInfo(RequestMappingInfo mappingInfo, HandlerMethod handler) {
        MethodInfo methodInfo = new MethodInfo();
        methodInfo.setMethodMark(MethodMark.buildMethodMark(Boolean.FALSE));
        methodInfo.setMethod(Method.buildMethod(mappingInfo,handler));
        return methodInfo;
    }
}
