package com.bamboo.apidoc.code.toolkit;

import com.bamboo.apidoc.annotation.Apidoc;
import com.bamboo.apidoc.code.exceptions.ApiDocException;
import com.bamboo.apidoc.code.model.MethodCache;
import com.bamboo.apidoc.code.model.MethodInfo;
import com.bamboo.apidoc.code.model.Module;
import com.bamboo.apidoc.code.model.Project;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Annotation;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    static boolean isAnnotation(List<Class<? extends Annotation>> annotations, Method method) {
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
        classes.add(DeleteMapping.class);
        classes.add(RequestMapping.class);
        return isAnnotation(classes, method);
    }


    /**
     * 获取当前方法的request类型9/
     *
     * @param method 方法
     * @return 返回当前请求类型
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

    /**
     * 根据模块获取所有的方法集合
     *
     * @param modules 模块集合
     * @return 返回方法集合
     */
    public static Map<String, MethodCache> getAllMethods(List<Module> modules) {
        Map<String, MethodCache> methodCacheMap = new HashMap<>();
        if (!modules.isEmpty()) {
            for (int i = 0; i < modules.size(); i++) {
                List<MethodInfo> methodInfos = modules.get(i).getMethodInfos();
                if (methodInfos != null && methodInfos.size() > 0) {
                    for (int j = 0; j < methodInfos.size(); j++) {
                        MethodCache methodCache = MethodCache.buildMethod(modules.get(i).getName(), i, j, methodInfos.get(j));
                        methodCacheMap.put(StringUtils.urlSplice(methodInfos.get(j).getMethod().getRoutPaths()), methodCache);
                    }
                }
            }
        } else {
            throw  new ApiDocException(Project.class.getName()+"-----------检测错误，未在Json文件中检测到任何模块----");
        }
        return methodCacheMap;
    }
}
