package com.bamboo.apidoc.code.model;

import com.bamboo.apidoc.ApidocApplication;
import lombok.Data;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @Author: GuoQing
 * @Date: 2019/2/13 16:47
 * @description 路由参数详情
 */
@Data
public class Param {

    /**
     * 参数名
     */
    private String name;
    /**
     * 参数类型
     */
    private String type;
    /**
     * 参数是否可以置空
     */
    private Boolean isNull;


    public static void main(String[] args) throws NoSuchMethodException {
        java.lang.reflect.Method aa=null;
        java.lang.reflect.Method[] methods = ApidocApplication.class.getMethods();
        for (java.lang.reflect.Method  method:methods) {
            if("deleteMenu".equals(method.getName())){
                aa=method;
            }
        }
        Annotation[][] parameterAnnotations = aa.getParameterAnnotations();
        for ( Annotation[] parameterAnnotation: parameterAnnotations) {
            for (Annotation  parameterAnnotatio:parameterAnnotation) {
                System.out.println(parameterAnnotatio.annotationType());
            }
        }
        Class<?>[] parameterTypes = aa.getParameterTypes();
        for (Class<?> parameterType:parameterTypes) {
            System.out.println(parameterType.getName());
        }
        int parameterCount = aa.getParameterCount();
        System.out.println(parameterCount);
        Class<?> returnType = aa.getReturnType();

    }
}
