package com.bamboo.apidoc.code.model;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: GuoQing
 * @Date: 2019/3/1 11:45
 * @description 此为Method校验时缓存信息
 */
@Getter
@Setter
public class MethodCache {
    /**
     * 模块名
     */
    private String moduleName;

    /**
     * 数组下标
     */
    private Integer moduleSubscript;

    /**
     * 方法下标
     */
    private Integer methodSubscript;

    /**
     * 当前缓存方法
     */
    private MethodInfo methodInfo;

    private MethodCache(String moduleName, Integer moduleSubscript, Integer methodSubscript, MethodInfo methodInfo) {
        this.moduleName = moduleName;
        this.moduleSubscript = moduleSubscript;
        this.methodSubscript = methodSubscript;
        this.methodInfo = methodInfo;
    }

    public static MethodCache buildMethod(String moduleName, Integer moduleSubscript, Integer methodSubscript, MethodInfo methodInfo) {
        return new MethodCache(moduleName, moduleSubscript, methodSubscript, methodInfo);
    }

}
