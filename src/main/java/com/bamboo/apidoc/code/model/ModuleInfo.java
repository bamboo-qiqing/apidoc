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


    public final static String UNALLOCATED = "未分配";
    /**
     * 模块名
     */
    private String name;
    /**
     * 模块描述
     */
    private String description;

    /**
     * 接口集合
     */
    private List<RoutMethod> methods;
    ModuleInfo(){
    }
    public ModuleInfo(String name, String description, List<RoutMethod> methods) {
        this.name = name;
        this.description = description;
        this.methods = methods;
    }
}
