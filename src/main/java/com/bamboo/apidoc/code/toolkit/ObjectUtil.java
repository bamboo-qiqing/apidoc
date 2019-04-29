package com.bamboo.apidoc.code.toolkit;

import java.lang.annotation.Annotation;
import java.util.*;

/**
 * @Author: GuoQing
 * @Date: 2019/4/28 11:53
 * @description
 */
public class ObjectUtil {
    public static Map<Class<?>, String> map = new HashMap<>();
    public static Map<Class<?>, String> set = new HashMap<>();

    static {
        map.put(String.class, "String");
        map.put(Integer.class, "Integer");
        map.put(Boolean.class, "Integer");
        map.put(Float.class, "Float");
        map.put(Short.class, "Short");
        map.put(Date.class, "Date");
        map.put(Long.class, "Long");
        map.put(Byte.class, "Byte");
        map.put(Double.class, "Double");
        map.put(Enum.class, "Enum");
        map.put(Object.class, "Object");
        map.put(Annotation.class, "Annotation");


        set.put(List.class, "List");
        set.put(Map.class, "Map");
        set.put(Set.class, "Set");
        set.put(Vector.class, "Vector");
    }
}
