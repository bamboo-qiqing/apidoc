package com.bamboo.apidoc.code.model;

import lombok.Data;

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
     * 方法类型
     */
    private String type;
    /**
     * 方法描述
     */
    private String description;
    /**
     * 方法参数
     */
    private List<RoutParam> params;
    /**
     * 文件信息
     */
    private RoutClass classInfo;


}
