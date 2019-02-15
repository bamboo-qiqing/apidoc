package com.bamboo.apidoc.code.model;

import com.bamboo.apidoc.code.toolkit.MethodUtil;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.reflect.Method;
import java.util.List;


/**
 * @Author: GuoQing
 * @Date: 2019/2/13 16:43
 * @description 路由方法详情
 */
@Data
public class RoutMethod {
    /**
     * 方法名
     */
    private String name;
    /**
     * 包名
     */
    private String packageName;
    /**
     * 类名称
     */
    private String className;
    /**
     * 中文名
     */
    private String chineseName;
    /**
     * 方法类型
     */
    private RequestMethod[] methodType;
    /**
     * 方法描述
     */
    private String description;
    /**
     * 方法参数
     */
    private List<RoutParam> params;


   public static RoutMethod buildRoutMethod(Method method, Class<?> packagePathClass) {
        RoutMethod routMethod = new RoutMethod();
        routMethod.setName(method.getName());
        routMethod.setPackageName(packagePathClass.getPackage().getName());
        routMethod.setClassName( packagePathClass.getName());
        routMethod.setMethodType(MethodUtil.getRequestMethod(method));
        return routMethod;
    }
}
