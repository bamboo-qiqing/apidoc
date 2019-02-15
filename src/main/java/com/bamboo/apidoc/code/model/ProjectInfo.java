package com.bamboo.apidoc.code.model;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.bamboo.apidoc.code.toolkit.ArrayUtils;
import com.bamboo.apidoc.code.toolkit.MethodUtil;
import com.bamboo.apidoc.extension.spring.ApidocFactoryBean;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: GuoQing
 * @Date: 2019/2/13 17:08
 * @description 项目信息
 */
@Data
public class ProjectInfo implements InitializingBean {
    @Value("classpath:/apidoc/apidoc.json")
    private Resource areaRes;
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
    private List<ModuleInfo> modules = new ArrayList<>();
    public void buildProjectInfoFactory(ApidocFactoryBean apidocFactory) {
        List<Class<?>> packagePathClass = apidocFactory.getPackagePathClass();
        if (ArrayUtils.isNotEmpty(packagePathClass)) {
            for (Class<?> packagePathClas : packagePathClass) {
                Method[] methods = packagePathClas.getMethods();
                ModuleInfo moduleInfo = new ModuleInfo();
                moduleInfo.setName(ModuleInfo.UNALLOCATED);
                for (Method method : methods) {
                    if (MethodUtil.isApidocMethodAnnotation(method)) {
                        if (moduleInfo.getMethods() == null) {
                            List<RoutMethod> routMethods = new ArrayList<>();
                            routMethods.add(RoutMethod.buildRoutMethod(method, packagePathClas));
                            moduleInfo.setMethods(routMethods);
                        } else {
                            moduleInfo.getMethods().add(RoutMethod.buildRoutMethod(method, packagePathClas));
                        }
                    }
                }
                this.modules.add(moduleInfo);
            }
        }
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        ProjectInfo projectInfo = JSON.parseObject(areaRes.getInputStream(), StandardCharsets.UTF_8, ProjectInfo.class);
        if(StrUtil.isBlank(name)&&StrUtil.isNotBlank(projectInfo.getName())){
          this.name=projectInfo.getName();
        }
        if(StrUtil.isBlank(description)&&StrUtil.isNotBlank(projectInfo.getDescription())){
            this.description=projectInfo.getDescription();
        }
        if(StrUtil.isBlank(startTime)&&StrUtil.isNotBlank(projectInfo.getStartTime())){
            this.startTime=projectInfo.getStartTime();
        }
    }
}
