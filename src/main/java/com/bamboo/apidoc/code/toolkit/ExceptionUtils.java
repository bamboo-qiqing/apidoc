package com.bamboo.apidoc.code.toolkit;

import com.bamboo.apidoc.code.exceptions.ApiDocException;

/**
 * @Author: GuoQing
 * @Date: 2019/2/12 21:48
 * @description
 */
public class ExceptionUtils {


  /**
   * 返回一个新的异常，统一构建，方便统一处理
   *
   * @param msg 消息
   * @param t   异常信息
   * @return 返回异常
   */
  public static ApiDocException ape(String msg, Throwable t, Object... params) {
    return new ApiDocException(StringUtils.format(msg, params), t);
  }

  /**
   * 重载的方法
   *
   * @param msg 消息
   * @return 返回异常
   */
  public static ApiDocException ape(String msg, Object... params) {
    return new ApiDocException(StringUtils.format(msg, params));
  }

  /**
   * 重载的方法
   *
   * @param t 异常
   * @return 返回异常
   */
  public static ApiDocException ape(Throwable t) {
    return new ApiDocException(t);
  }

}
