package com.bamboo.apidoc.code.model;


import com.bamboo.apidoc.code.toolkit.MethodUtil;
import com.bamboo.apidoc.code.toolkit.ParamUtil;
import com.bamboo.apidoc.code.toolkit.RoutUtil;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * @Author: GuoQing
 * @Date: 2019/2/13 16:43
 * @description 路由方法详情
 */
@Data
public class Method {
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
     * 接口地址
     */
    private String routPath;
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
    private Param[] params;


    public static Method buildRoutMethod(java.lang.reflect.Method method, Class<?> packagePathClass) {
        Method routMethod = new Method();
        routMethod.setName(method.getName());
        routMethod.setPackageName(packagePathClass.getPackage().getName());
        routMethod.setClassName(packagePathClass.getName());
        routMethod.setMethodType(MethodUtil.getRequestMethod(method));
        routMethod.setRoutPath(RoutUtil.getRout(method,packagePathClass));
        routMethod.setParams(ParamUtil.getParams(method));
        return routMethod;
    }


}
