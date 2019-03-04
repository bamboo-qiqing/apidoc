package com.bamboo.apidoc.code.model;

import lombok.Data;

import java.util.List;

/**
 * @Author: GuoQing
 * @Date: 2019/2/13 17:10
 * @description 模块信息
 */
@Data
public class Module {

    /**
     * 所有接口在未分配指定模块时，默认在未分配模块
     * 此为未分配模块名称
     */
    public final static String UNALLOCATED = "未分配";
    /**
     * 所有接口出现异常时将会被分配在异常模
     */
    public final static String ABNORMAL = "异常";
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
    private List<MethodInfo> methodInfos ;


    public Module(String name, String description, List<MethodInfo> methodInfos) {
        this.name = name;
        this.description = description;
        this.methodInfos = methodInfos;
    }

    /**
     * 根据当前模块名是否和指定模块名相同
     *
     * @param module     模块
     * @param moduleName 指定模块名
     * @return 如果是，返回当前模块，如果不是，返回null
     */
    public static Module isModuleByName(Module module, String moduleName) {
        if (module == null || moduleName == null || "".equals(moduleName)) {
            return null;
        }
        return moduleName.equals(module.getName()) ? module : null;
    }

    /**
     * 获取与指定模块名相同的模块的下标
     *
     * @param modules    模块集合
     * @param moduleName 模块名
     * @return 存在返回模块的下标，不存在返回-1
     */
    static int getOneModuleByName(List<Module> modules, String moduleName) {
        if (modules == null || modules.size() < 1 || moduleName == null || "".equals(moduleName)) {
            return -1;
        }
        for (int i = 0; i < modules.size(); i++) {
            if (moduleName.equals(modules.get(i).getName())) {
                return i;
            }
        }
        return -1;
    }

}
