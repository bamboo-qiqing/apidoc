package com.bamboo.apidoc.code.model;


import com.bamboo.apidoc.code.toolkit.StringPool;
import lombok.Data;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.method.HandlerMethod;

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


    public static Param[] buildParams(HandlerMethod handler) {
        Assert.notNull(handler, "HandlerMethod must not be null");
        return getParams(handler.getMethod());
    }

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
        for (int i = 0; i < parameterCount; i++) {
            Param param = new Param();
            if (parameterTypes[i].isPrimitive()) {
                param.setName(parameterNames[i]);
                param.setType(parameterTypes[i].getName());
            } else {
                String[] names = parameterTypes[i].getName().split(StringPool.DOT_TRA);
                param.setName(parameterNames[i]);
                param.setType(names[names.length - 1]);
            }
            param.setIsNull(isNull(parameterAnnotations[i]));
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
                if (annotation instanceof PathVariable) {
                    isNull = ((PathVariable) annotation).required();
                    break;
                }
                if (annotation instanceof RequestBody) {
                    isNull = ((RequestBody) annotation).required();
                    break;
                }
            }
        }
        return isNull;
    }

}
