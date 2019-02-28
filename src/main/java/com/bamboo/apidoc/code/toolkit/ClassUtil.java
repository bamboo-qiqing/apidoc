package com.bamboo.apidoc.code.toolkit;


import com.bamboo.apidoc.annotation.Apidoc;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: GuoQing
 * @Date: 2019/2/20 15:30
 * @description 类工具类
 */
public class ClassUtil {

    /**
     * 判断类是否含有指定注解
     *
     * @param annotations 指定注解集合
     * @param clazz       类
     * @return 存在返回true 不存在返回false
     */
    public static boolean isAnnotation(List<Class<? extends Annotation>> annotations, Class<?> clazz) {
        if (clazz == null) {
            return Boolean.FALSE;
        }
        for (Class<? extends Annotation> annotation : annotations) {
            Annotation annotation1 = clazz.getAnnotation(annotation);
            if (annotation1 != null) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    /**
     * 判断类是否含有指定注解
     *
     * @param clazz 类
     * @return 存在返回true 不存在返回false
     */
    public static boolean isApidocClassAnnotation(Class<?> clazz) {
        //TODO 待改善此以下常量
        List<Class<? extends Annotation>> classes = new ArrayList<>();
        classes.add(Apidoc.class);
        classes.add(RestController.class);
        classes.add(Controller.class);
        return isAnnotation(classes, clazz);
    }



}
