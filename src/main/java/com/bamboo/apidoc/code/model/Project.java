package com.bamboo.apidoc.code.model;

import com.alibaba.fastjson.JSONObject;
import com.bamboo.apidoc.code.exceptions.ApiDocException;
import com.bamboo.apidoc.code.toolkit.ArrayUtils;
import com.bamboo.apidoc.code.toolkit.ClassUtil;
import com.bamboo.apidoc.code.toolkit.MethodUtil;
import com.bamboo.apidoc.extension.spring.ApidocFactoryBean;
import lombok.Data;
import org.springframework.util.ResourceUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: GuoQing
 * @Date: 2019/2/13 17:08
 * @description 项目信息
 */
@Data
public class Project {


    /**
     * 项目名
     */
    private String name;
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 项目描述
     */
    private String description;
    /**
     * 模块集合
     */
    private List<Module> modules = new ArrayList<>();

    public void buildProjectInfoFactory(ApidocFactoryBean apidocFactory) throws IOException {
        Project project = apidocFactory.getProject();
        List<Class<?>> packagePathClass = apidocFactory.getPackagePathClass();
        if (ArrayUtils.isNotEmpty(packagePathClass)) {
            for (Class<?> packagePathClas : packagePathClass) {
                if (ClassUtil.isApidocClassAnnotation(packagePathClas)) {
                    java.lang.reflect.Method[] methods = packagePathClas.getMethods();
                    int oneModuleByName = Module.getOneModuleByName(project.getModules(), Module.UNALLOCATED);
                    if (oneModuleByName < 0) {
                        throw new ApiDocException(".......未分配模块不存在,请删除JSON文件重新生成");
                    }
                    for (java.lang.reflect.Method method : methods) {
                        if (MethodUtil.isApidocMethodAnnotation(method)) {
                            if (project.getModules().get(oneModuleByName).getMethods() == null) {
                                List<Method> routMethods = new ArrayList<>();
                                routMethods.add(Method.buildRoutMethod(method, packagePathClas));
                                project.getModules().get(oneModuleByName).setMethods(routMethods);
                            } else {
                                project.getModules().get(oneModuleByName).getMethods().add(Method.buildRoutMethod(method, packagePathClas));
                            }
                        }
                    }
                }
            }
        }
        com.bamboo.apidoc.code.toolkit.FileUtil.createJson(JSONObject.toJSON(project),ResourceUtils.getURL("classpath:").getPath()+"/apidoc/apidoc.json");

    }
}
