package com.bamboo.apidoc.code.exceptions;

/**
 * @Author: GuoQing
 * @Date: 2019/2/12 21:49
 * @description api异常类
 */
public class ApiDocException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public ApiDocException(String message) {
    super(message);
  }

  public ApiDocException(Throwable throwable) {
    super(throwable);
  }

  public ApiDocException(String message, Throwable throwable) {
    super(message, throwable);
  }
}
