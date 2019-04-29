package com.bamboo.apidoc.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: GuoQing
 * @Date: 2019/3/7 11:05
 * @description
 */
@ConfigurationProperties("bamboo.apidoc")
@Data
public class ApiDocProperties {

  /**
   * 管理员账号
   */
  private String username;
  /**
   * 管理员密码
   */
  private String password;
  /**
   * 文件地址
   */
  private String jsonPath;
}
