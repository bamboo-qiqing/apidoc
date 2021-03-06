package com.bamboo.apidoc.code.model;

import lombok.Data;


/**
 * @Author: GuoQing
 * @Date: 2019/3/6 15:14
 * @description
 */
@Data
public class MethodInfo {
  /**
   * 版本默认初始值
   */
  public final static Integer defaultVersion = 1;
  /**
   * 方法id
   */
  private String id;
  /**
   * 当前方法版本
   */
  private Integer version;
  /**
   * 模块id
   */
  private String modelId;
  /**
   * 方法基本信息
   */
  private MethodBasic methodBasic;
}
