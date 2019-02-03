package com.bamboo.apidoc.extension.factory;

import lombok.Data;

/**
 * @Author: GuoQing
 * @Date: 2019/2/3 11:22
 * @description
 */
@Data
public class ApidocFactoryBean {

  /**
   * 需要扫描的包集合
   */
  String[] packagePaths;
}
