package com.bamboo.apidoc.autoconfigure;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;

/**
 * @Author: GuoQing
 * @Date: 2019/2/2 10:55
 * @description apidoc 自动配置类
 *
 */
@Data
@ConfigurationProperties(value = "bamboo.apidoc")
public class ApidocProperties implements InitializingBean {

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
    /**
     * json文件路径
     */
  public static String jsonFilePath;

  @Override
  public void afterPropertiesSet() throws Exception {
      try {
        jsonFilePath= ResourceUtils.getURL("classpath:").getPath()+"/apidoc/apidoc.json";
        System.out.println(ResourceUtils.getURL("classpath:").getPath());
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }

  }
}
