package com.bamboo.apidoc.extension.toolkit;

import com.bamboo.apidoc.annotation.Apidoc;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @Author: GuoQing
 * @Date: 2019/2/13 15:15
 * @description 注解过滤工具类
 */
public class AnnotaionFilter {

    //TODO 待改善
    public final static Map<String, Class<?>> needFilters = new HashMap<>();

    static {
        needFilters.put("com.bamboo.apidoc.annotation.Apidoc", Apidoc.class);
        needFilters.put("org.springframework.web.bind.annotation.RestController", RestController.class);
        needFilters.put("org.springframework.stereotype.Controller", Controller.class);
    }

    public static Boolean filter(Collection<String> annotaionNames) {

        for (String annotaionName : annotaionNames) {
            Class<?> aClass = needFilters.get(annotaionName);
            if (aClass != null) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;

    }


}
