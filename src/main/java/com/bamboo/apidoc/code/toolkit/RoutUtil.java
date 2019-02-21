package com.bamboo.apidoc.code.toolkit;


import com.bamboo.apidoc.annotation.Apidoc;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;

/**
 * @Author: GuoQing
 * @Date: 2019/2/21 16:09
 * @description 路由工具类
 */
public class RoutUtil {
    /**
     * 根绝方法和类获取获取路由路径
     *
     * @param method           方法
     * @param packagePathClass 类
     * @return 路由路径
     */
    public static String getRout(java.lang.reflect.Method method, Class<?> packagePathClass) {
        String url = "";
        String classroutPath = getRoutPath(packagePathClass);
        String methodRoutPath = getRoutPath(method);
        if (classroutPath != null || classroutPath != "") {
            if (classroutPath.endsWith(StringPool.SLASH)) {
                url = classroutPath.replace(StringPool.SLASH, StringPool.EMPTY) + methodRoutPath;
            } else {
                url = classroutPath + methodRoutPath;
            }
        }
        return url;

    }

    /**
     * 获取当前方法的request类型/
     *
     * @param clazz 类
     * @return
     */
    public static String getRoutPath(Class<?> clazz) {

        if (clazz.getAnnotation(RestController.class) != null) {
            RestController annotation = clazz.getAnnotation(RestController.class);
            if ("".equals(annotation.value()) || annotation.value().equals(StringPool.SLASH)) {
                if (clazz.getAnnotation(RequestMapping.class) != null) {
                    RequestMapping annotation1 = clazz.getAnnotation(RequestMapping.class);
                    return StringUtils.startsWith(annotation1.value()[0], StringPool.SLASH);
                }
            }
            return StringUtils.startsWith(annotation.value(), StringPool.SLASH);
        }
        if (clazz.getAnnotation(Controller.class) != null) {
            Controller annotation = clazz.getAnnotation(Controller.class);
            if ("".equals(annotation.value()) || annotation.value().equals(StringPool.SLASH)) {
                if (clazz.getAnnotation(RequestMapping.class) != null) {
                    RequestMapping annotation1 = clazz.getAnnotation(RequestMapping.class);
                    return StringUtils.startsWith(annotation1.value()[0], StringPool.SLASH);
                }
            }
            return StringUtils.startsWith(annotation.value(), StringPool.SLASH);
        }
        if (clazz.getAnnotation(Apidoc.class) != null) {
            Apidoc annotation = clazz.getAnnotation(Apidoc.class);
            return StringUtils.startsWith(annotation.value(), StringPool.SLASH);
        }

        return "";
    }


    /**
     * 获取当前方法的request类型/
     *
     * @param method 方法
     * @return
     */
    public static String getRoutPath(Method method) {
        //TODO 有bug
        if (method.getAnnotation(GetMapping.class) != null) {
            GetMapping annotation = method.getAnnotation(GetMapping.class);
            return StringUtils.startsWith(annotation.value()[0], StringPool.SLASH);
        }
        if (method.getAnnotation(PostMapping.class) != null) {
            PostMapping annotation = method.getAnnotation(PostMapping.class);
            return StringUtils.startsWith(annotation.value()[0], StringPool.SLASH);
        }
        if (method.getAnnotation(RequestMapping.class) != null) {
            RequestMapping annotation = method.getAnnotation(RequestMapping.class);
            return StringUtils.startsWith(annotation.value()[0], StringPool.SLASH);
        }

        return "";
    }
}
