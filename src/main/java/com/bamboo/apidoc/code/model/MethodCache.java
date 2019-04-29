package com.bamboo.apidoc.code.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: GuoQing
 * @Date: 2019/3/1 11:45
 * @description 此为Method校验时缓存信息
 */
@Getter
@Setter
public class MethodCache {
  /**
   * 方法下标
   */
  private Integer methodSubscript;

  /**
   * 当前缓存方法
   */
  private Method methodInfo;

  private MethodCache(Integer methodSubscript, Method methodInfo) {
    this.methodSubscript = methodSubscript;
    this.methodInfo = methodInfo;
  }

  public static MethodCache buildMethod(Integer methodSubscript, Method methodInfo) {
    return new MethodCache(methodSubscript, methodInfo);
  }

}
