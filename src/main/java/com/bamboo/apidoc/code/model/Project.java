package com.bamboo.apidoc.code.model;


import com.alibaba.fastjson.JSONObject;
import com.bamboo.apidoc.autoconfigure.ApidocProperties;
import com.bamboo.apidoc.code.exceptions.ApiDocException;
import com.bamboo.apidoc.code.toolkit.MethodUtil;
import com.bamboo.apidoc.code.toolkit.StringUtils;
import com.bamboo.apidoc.extension.spring.ApidocFactoryBean;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.*;

/**
 * @Author: GuoQing
 * @Date: 2019/2/13 17:08
 * @description 项目信息
 */
@Data
public class Project implements InitializingBean {
    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Autowired
    private ApidocFactoryBean apidocFactory;
    /**
     * 大版本默认值
     */
    public static Integer defaultLargeVersion = 0;
    /**
     * 小版本默认值
     */
    public static Integer defaultSmallVersion = 1;
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
     * 开启功能
     */
    private String[] startFeatures;
    /**
     * 小版本
     */
    private Integer smallVersion;
    /**
     * 大版本
     */
    private Integer largeVersion;
    /**
     * jdk版本
     */
    private String jdkVersion=System.getProperty("java.version");
    /**
     * 模块集合
     */
    private List<Module> modules = new ArrayList<>();


//    public void buildProjectInfoFactory(ApidocFactoryBean apidocFactory) throws IOException {
//        Project project = apidocFactory.getProject();
//        List<Class<?>> packagePathClass = apidocFactory.getPackagePathClass();
//        if (ArrayUtils.isNotEmpty(packagePathClass)) {
//            for (Class<?> packagePathClas : packagePathClass) {
//                if (ClassUtil.isApidocClassAnnotation(packagePathClas)) {
//                    java.lang.reflect.Method[] methods = packagePathClas.getMethods();
//                    int oneModuleByName = Module.getOneModuleByName(project.getModules(), Module.UNALLOCATED);
//                    if (oneModuleByName < 0) {
//                        throw new ApiDocException(".......未分配模块不存在,请删除JSON文件重新生成");
//                    }
//                    for (java.lang.reflect.Method method : methods) {
//                        if (MethodUtil.isApidocMethodAnnotation(method)) {
//                            if (project.getModules().get(oneModuleByName).getMethods() == null) {
//                                List<Method> routMethods = new ArrayList<>();
//                                routMethods.add(Method.buildRoutMethod(method, packagePathClas));
//                                project.getModules().get(oneModuleByName).setMethods(routMethods);
//                            } else {
//                                project.getModules().get(oneModuleByName).getMethods().add(Method.buildRoutMethod(method, packagePathClas));
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        com.bamboo.apidoc.code.toolkit.FileUtil.createJson(JSONObject.toJSON(project), ApidocProperties.jsonFilePath);
//    }


    @Override
    public void afterPropertiesSet() throws Exception {
        //获取已存在Json文件中的数据
        Project project = apidocFactory.getProject();
        Integer smallversion = project.getSmallVersion();
        if(smallversion!=null){
            Integer currentVersion = ++smallversion;
            if(currentVersion<100){
                project.setSmallVersion(currentVersion);
            }else{
                project.setLargeVersion(project.getLargeVersion()+1);
                project.setSmallVersion(Project.defaultSmallVersion);
            }
        }
        //根绝spring处理器获取所有接口方法
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
        //获取所有已存在Json文件中的方法集合
        Map<String, MethodCache> allMethods = MethodUtil.getAllMethods(project.getModules());
        //遍历所有Spring处理器获取到的方法
        for (Map.Entry<RequestMappingInfo, HandlerMethod> handlerMethod : handlerMethods.entrySet()) {
            //获取方法中的Url
            Set<String> patterns = handlerMethod.getKey().getPatternsCondition().getPatterns();
            //根据拼接的Url检测已存在方法中是否存在该接口
            if (allMethods != null) {
                MethodCache methodCache = allMethods.get(StringUtils.urlSplice(patterns));
                MethodInfo method = MethodInfo.buildMethodInfo(handlerMethod.getKey(), handlerMethod.getValue());
                //不存在则生成，存入未分配模块
                if (methodCache == null) {
                    //获取未分配模块下标
                    int oneModuleByName = Module.getOneModuleByName(project.getModules(), Module.UNALLOCATED);
                    if (oneModuleByName > -1) {
                        //写入对象
                        List<MethodInfo> methods = project.getModules().get(oneModuleByName).getMethodInfos();
                        if (methods != null && methods.size() > 0) {
                            project.getModules().get(oneModuleByName).getMethodInfos().add(method);
                        } else {
                            List<MethodInfo> methodLists = new ArrayList<>();
                            methodLists.add(method);
                            project.getModules().get(oneModuleByName).setMethodInfos(methodLists);
                        }
                    }
                } else {
                    MethodInfo methodInfo = methodCache.getMethodInfo();
                    if (methodInfo!=null){
                        Method method1 = methodInfo.getMethod();
                        if (method1.isChange(method.getMethod())){
                            System.out.println("221");
                       }
                   }
                }
            } else {
                throw new ApiDocException(Project.class.getName() + "-----------检测错误，未在Json文件中检测到任何接口----");
            }
        }
        com.bamboo.apidoc.code.toolkit.FileUtil.createJson(JSONObject.toJSON(project), ApidocProperties.jsonFilePath);
    }
}
