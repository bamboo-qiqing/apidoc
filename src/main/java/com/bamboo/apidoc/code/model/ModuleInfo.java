package com.bamboo.apidoc.code.model;

import lombok.Data;

import java.util.List;

/**
 * @Author: GuoQing
 * @Date: 2019/2/13 17:10
 * @description 模块信息
 */
@Data
public class ModuleInfo {
    /**
     * 模块名
     */
    private String  name;
    /**
     * 模块描述
     */
    private String  description;
    /**
     * 路由文件集合
     */
    List<RoutClass> classList;
}
