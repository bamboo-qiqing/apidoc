package com.bamboo.apidoc.code.toolkit;


import com.bamboo.apidoc.code.model.Param;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @Author: GuoQing
 * @Date: 2019/2/22 14:33
 * @description
 */
public class ParamUtil {

    /**
     * 根据方法获取当前方法的所有参数信息
     *
     * @param method 方法
     * @return 返回参数对象数组
     */
    public static Param[] getParams(Method method) {
        //java8新特性，可以获取到参数的名称
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        //获取方法中参数的数量
        int parameterCount = method.getParameterCount();
        //创建参数对象数组，并初始化数组的长度
        Param[] params = new Param[parameterCount];
        //获取参数类型
        Class<?>[] parameterTypes = method.getParameterTypes();
        //根据方法获取参数名称
        String[] parameterNames = u.getParameterNames(method);
        //获取方法中参数所有的注解
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        Class<?> returnType = method.getReturnType();
        for (int i = 0; i < parameterCount; i++) {
            Param param = new Param();
            param.setName(parameterNames[i]);
            param.setType(parameterTypes[i].toString());
            param.setIsNull(isNull(parameterAnnotations[i]));
            param.setReturnType(returnType);
            params[i] = param;
        }
        return params;
    }

    /**
     * 根据参数注解判断参数可否为空
     *
     * @param annotations 参数注解
     * @return 如果可以为空  返回true，否则返回false
     */
    public static Boolean isNull(Annotation[] annotations) {
        Boolean isNull = true;
        if (annotations != null && annotations.length > 1) {
            for (Annotation annotation : annotations) {
                if (annotation instanceof RequestParam) {
                    isNull = ((RequestParam) annotation).required();
                    break;
                }
                if (annotation instanceof RequestBody) {
                    isNull = ((RequestParam) annotation).required();
                    break;
                }
            }
        }

        return isNull;
    }
}
