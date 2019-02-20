package com.bamboo.apidoc.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: GuoQing
 * @Date: 2019/2/2 10:55
 * @description apidoc 自动配置类
 *
 */
@Data
@ConfigurationProperties(value = "bamboo.apidoc")
public class ApidocProperties {

  /**
   * 需要扫描的包路径
   */
  private  String packagePath;
  /**
   * 项目名
   */
  private  String title;
  /**
   * 项目描述
   */
  private  String description;

}
