package com.bamboo.apidoc.autoconfigure;


import com.bamboo.apidoc.extension.factory.ApidocFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ObjectUtils;

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
  @Bean
  @ConditionalOnMissingBean
  public ApidocFactoryBean apiDocFactory() {
    ApidocFactoryBean factory = new ApidocFactoryBean();
    if (!ObjectUtils.isEmpty(this.properties.getPackagePath())) {
      factory.setPackagePath(this.properties.getPackagePath());
    }
    return factory;
  }
}
