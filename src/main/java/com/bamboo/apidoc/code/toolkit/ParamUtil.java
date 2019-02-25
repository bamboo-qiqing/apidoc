package com.bamboo.apidoc.code.toolkit;

import com.bamboo.apidoc.ApidocApplication;
import com.bamboo.apidoc.code.model.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @Author: GuoQing
 * @Date: 2019/2/22 14:33
 * @description
 */
public class ParamUtil {


    public static Param[] getParams(Method method){
        int parameterCount = method.getParameterCount();
        Param[]  params=  new Param[parameterCount];
        Class<?>[] parameterTypes = method.getParameterTypes();
        for (int i=0;i<parameterCount;i++){
            Param param = new Param();
            params[i]=param;
        }
        return params;
    }



    public static void main(String[] args)  {
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
                if("interface  org.springframework.web.bind.annotation.RequestParam".equals(parameterAnnotatio.annotationType())){
                    RequestParam dd= (RequestParam) parameterAnnotatio;
                }
                System.out.println();
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
