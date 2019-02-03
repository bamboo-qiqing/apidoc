package com.bamboo.apidoc.autoconfigure;


import com.bamboo.apidoc.annotation.Apidoc;
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


  private  ApidocProperties properties;

  ApidocAutoConfiguration(ApidocProperties properties){
    this.properties=properties;
  }


  @Bean
  @ConditionalOnMissingBean
  public ApidocFactoryBean   apiDocFactory(){
    ApidocFactoryBean   factory= new ApidocFactoryBean();
    if (!ObjectUtils.isEmpty(this.properties.getPackagePath())){
      factory.setPackagePaths(this.properties.getPackagePath());
    }
   return  factory;
  }
}
