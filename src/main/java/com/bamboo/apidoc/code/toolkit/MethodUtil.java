package com.bamboo.apidoc.code.toolkit;

import com.bamboo.apidoc.annotation.Apidoc;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: GuoQing
 * @Date: 2019/2/13 20:39
 * @description
 */
public class MethodUtil {
    /**
     * 判断方法是否含有指定注解
     *
     * @param annotations 指定注解集合
     * @param method      方法
     * @return 存在返回true 不存在返回false
     */
    public static boolean isAnnotation(List<Class<? extends Annotation>> annotations, Method method) {
        if (annotations == null || annotations.size() < 1) {
            return Boolean.FALSE;
        }
        for (Class<? extends Annotation> annotation : annotations) {
            Annotation annotation1 = method.getAnnotation(annotation);
            if (annotation1 != null) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }


    /**
     * 判断方法是否含有指定注解
     *
     * @param method 方法
     * @return 存在返回true 不存在返回false
     */
    public static boolean isApidocMethodAnnotation(Method method) {
        //TODO 待改善此以下常量
        List<Class<? extends Annotation>> classes = new ArrayList<>();
        classes.add(Apidoc.class);
        classes.add(GetMapping.class);
        classes.add(PostMapping.class);
        classes.add(RequestMapping.class);
        return isAnnotation(classes, method);
    }


    /**
     * 获取当前方法的request类型9/
     *
     * @param method
     * @return
     */
    public static RequestMethod[] getRequestMethod(Method method) {
        //TODO 待改善
        GetMapping get = method.getAnnotation(GetMapping.class);
        if (get != null) {
            return new RequestMethod[]{RequestMethod.GET};
        }
        PostMapping post = method.getAnnotation(PostMapping.class);
        if (post != null) {
            return new RequestMethod[]{RequestMethod.POST};
        }
        RequestMapping request = method.getAnnotation(RequestMapping.class);
        if (request != null) {
            return request.method();
        }
        return null;
    }




}
