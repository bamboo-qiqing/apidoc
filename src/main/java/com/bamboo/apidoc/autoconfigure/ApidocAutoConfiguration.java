package com.bamboo.apidoc.autoconfigure;


import com.bamboo.apidoc.code.model.ProjectInfo;
import com.bamboo.apidoc.code.toolkit.ArrayUtils;
import com.bamboo.apidoc.code.toolkit.MethodUtil;
import com.bamboo.apidoc.extension.spring.ApidocFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ObjectUtils;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @Author: GuoQing
 * @Date: 2019/2/2 11:07
 * @description
 */
@EnableConfigurationProperties(ApidocProperties.class)
@Configuration
public class ApidocAutoConfiguration {

    /**
     * apidoc 配置文件
     */
    private ApidocProperties properties;

    ApidocAutoConfiguration(ApidocProperties properties) {
        this.properties = properties;
    }

    /**
     * apidoc工厂bean配置
     */
    @Bean(name = "apidocFactory")
    @ConditionalOnMissingBean
    public ApidocFactoryBean apiDocFactory() {
        ApidocFactoryBean factory = new ApidocFactoryBean();
        if (!ObjectUtils.isEmpty(this.properties.getPackagePath())) {
            factory.setPackagePath(this.properties.getPackagePath());
        }
        return factory;
    }

    @Bean(name = "projectInfo")
    @ConditionalOnBean(ApidocFactoryBean.class)
    public ProjectInfo getProjectInfo(@Autowired ApidocFactoryBean apidocFactory) {
        ProjectInfo projectInfo = new ProjectInfo();
        if (!ObjectUtils.isEmpty(this.properties.getTitle())) {
            projectInfo.setName(this.properties.getTitle());
        }
        if (!ObjectUtils.isEmpty(this.properties.getDescription())) {
            projectInfo.setDescription(this.properties.getDescription());
        }
        List<Class<?>> packagePathClass = apidocFactory.getPackagePathClass();
        if (ArrayUtils.isNotEmpty(packagePathClass)) {
            for (Class<?> packagePathClas : packagePathClass) {
                Method[] methods = packagePathClas.getMethods();
                for (Method method : methods) {
                    if(MethodUtil.isApidocMethodAnnotation(method)){

                    }
                }
            }
        }
        return projectInfo;
    }
}
