package com.bamboo.apidoc.code.model;

import com.alibaba.fastjson.JSONObject;
import com.bamboo.apidoc.code.toolkit.ParamUtil;
import com.bamboo.apidoc.code.toolkit.StringPool;
import lombok.Data;
import org.springframework.core.MethodParameter;
import org.springframework.util.Assert;
import org.springframework.web.method.HandlerMethod;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

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
        return  ParamUtil.getParams(handler.getMethod());
    }


}
