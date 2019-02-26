package com.bamboo.apidoc.code.model;

import lombok.Data;
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
    /**
     * 返回类型
     */
    private Object returnType;

}
