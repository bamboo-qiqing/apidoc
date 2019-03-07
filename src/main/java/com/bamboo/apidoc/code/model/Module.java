package com.bamboo.apidoc.code.model;

import com.bamboo.apidoc.code.exceptions.ApiDocException;
import com.bamboo.apidoc.code.toolkit.StringUtils;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private List<Method> methods;

    /**
     * 获取与指定模块名相同的模块的下标
     *
     * @param modules    模块集合
     * @param moduleName 模块名
     * @return 存在返回模块的下标，不存在返回-1
     */
   public static int getOneModuleByName(List<Module> modules, String moduleName) {
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
                List<Method> methodInfos = modules.get(i).getMethods();
                if (methodInfos != null && methodInfos.size() > 0) {
                    for (int j = 0; j < methodInfos.size(); j++) {
                        MethodCache methodCache = MethodCache.buildMethod(modules.get(i).getName(), i, j, methodInfos.get(j));
                        MethodBasic methodBasic = methodInfos.get(j).getMethodInfo().getMethodBasic();
                        methodCacheMap.put(StringUtils.patternsSplice(methodBasic), methodCache);
                    }
                }
            }
        } else {
            throw new ApiDocException(Project.class.getName() + "-----------检测错误，未在Json文件中检测到任何模块----");
        }
        return methodCacheMap;
    }

    public Module initModule() {
        this.name = Module.UNALLOCATED;
        this.description = "当前模块为未曾分配的接口集合";
        return this;
    }
}
