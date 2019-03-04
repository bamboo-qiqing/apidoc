package com.bamboo.apidoc.extension.spring;

import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bamboo.apidoc.autoconfigure.ApidocProperties;
import com.bamboo.apidoc.code.model.Module;
import com.bamboo.apidoc.code.model.Project;
import com.bamboo.apidoc.code.toolkit.StringPool;
import java.nio.charset.StandardCharsets;
import java.util.*;
import lombok.Data;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.util.ResourceUtils;

/**
 * @Author: GuoQing
 * @Date: 2019/2/3 11:22
 * @description apidoc
 */
@Data
public class ApidocFactoryBean implements InitializingBean {

    @Value(StringPool.JSON_FILE_CLASS_PATH)
    private Resource areaRes;

    private static final Log LOGGER = LogFactory.getLog(ApidocFactoryBean.class);
    /**
     * 需要扫描的包集合
     */
    String packagePath;
    /**
     * 扫描指定注解得到的类
     */
    List<Class<?>> packagePathClass;

    private Project project;

//    void buildApidocFactory() {
//        if (hasLength(this.packagePath)) {
//            packagePathClass = new ArrayList<>();
//            if (packagePath.contains(StringPool.ASTERISK) && !packagePath.contains(
//                    StringPool.COMMA) && !packagePath.contains(
//                    StringPool.SEMICOLON)) {
//                Class<?>[] convertTypeAliasesPackages = PackageHelper.convertPackagePath(this.packagePath);
//                if (ArrayUtils.isEmpty(convertTypeAliasesPackages)) {
//                    LOGGER.warn("在'[" + packagePath + "]' 包下找不到类. 请检查你的配置.");
//                } else {
//                    packagePathClass.addAll(Arrays.asList(convertTypeAliasesPackages));
//                }
//            } else {
//                String[] typeAliasPackageArray = tokenizeToStringArray(this.packagePath,
//                        ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS);
//                for (String one : typeAliasPackageArray) {
//                    Class<?>[] convertTypeAliasesPackages = PackageHelper.convertPackagePath(one);
//                    if (ArrayUtils.isEmpty(convertTypeAliasesPackages)) {
//                        LOGGER.warn("在'[" + packagePath + "]' 包下找不到类. 请检查你的配置.");
//                    } else {
//                        packagePathClass.addAll(Arrays.asList(convertTypeAliasesPackages));
//                    }
//
//                }
//            }
//        } else {
//
//        }
//    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //TODO 待改善
//        this.buildApidocFactory();
        boolean exist = FileUtil.exist(ResourceUtils.getURL("classpath:").getPath() + "/apidoc/apidoc.json");
        if (!exist) {
            Project project = new Project();
            project.getModules().add(new Module(Module.UNALLOCATED, "当前模块为未曾分配的接口集合", null));
            project.getModules().add(new Module(Module.ABNORMAL, "当前模块为出现异常的接口集合", null));
            project.setLargeVersion(Project.defaultLargeVersion);
            project.setSmallVersion(Project.defaultSmallVersion);
            com.bamboo.apidoc.code.toolkit.FileUtil.createJson(JSONObject.toJSON(project), ApidocProperties.jsonFilePath);
        }
        this.project = JSON.parseObject(areaRes.getInputStream(), StandardCharsets.UTF_8, Project.class);
    }
}
