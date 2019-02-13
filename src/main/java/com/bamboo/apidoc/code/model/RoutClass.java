package com.bamboo.apidoc.code.model;

import lombok.Data;

import java.util.List;

/**
 * @Author: GuoQing
 * @Date: 2019/2/13 16:40
 * @description 路由文件详情
 */
@Data
public class RoutClass {
    /**
     * 包名
     */
    private  String packageName;
    /**
     * 文件名
     */
    private  String className;
    /**
     * 文件描述
     */
    private  String classDescription;

}
