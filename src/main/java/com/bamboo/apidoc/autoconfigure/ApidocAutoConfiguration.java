package com.bamboo.apidoc.autoconfigure;

import com.bamboo.apidoc.code.model.Project;
import com.bamboo.apidoc.web.IndexController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * @Author: GuoQing
 * @Date: 2019/3/4 22:45
 * @description
 */
@Configuration
@ComponentScan(basePackageClasses = {IndexController.class,})
@EnableConfigurationProperties(ApiDocProperties.class)
public class ApidocAutoConfiguration {

    private ApiDocProperties properties;

    ApidocAutoConfiguration(ApiDocProperties properties) {
        this.properties = properties;
    }

    @Bean(name = "project")
    @ConditionalOnBean({RequestMappingHandlerMapping.class})
    public Project project(@Autowired RequestMappingHandlerMapping requestMappingHandlerMapping) {
        return new Project().buildProject(requestMappingHandlerMapping);
    }
}
