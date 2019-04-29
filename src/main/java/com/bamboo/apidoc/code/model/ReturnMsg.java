package com.bamboo.apidoc.code.model;

import com.bamboo.apidoc.code.enums.Status;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * @Author: GuoQing
 * @Date: 2019/2/26 10:42
 * @description 接口统一返回对象
 */
@Data
public class ReturnMsg implements Serializable {

  private long status;
  private String msg;
  private Object result;

  public ReturnMsg(long status, String msg, Object result) {
    this.status = status;
    this.msg = msg;
    this.result = result;
  }

  public ReturnMsg(Status status, Object result) {
    this.status = status.getCode();
    this.msg = status.getMsg();
    this.result = result;
  }

  public ReturnMsg(HttpStatus status, Object result) {
    this.status = status.value();
    this.msg = status.getReasonPhrase();
    this.result = result;
  }

  public ReturnMsg(Status status) {
    this.status = status.getCode();
    this.msg = status.getMsg();
  }

  public static ReturnMsg returnMsg(long status, String msg, Object result) {
    return new ReturnMsg(status, msg, result);
  }

  public static ReturnMsg returnMsg(HttpStatus status, Object result) {
    return new ReturnMsg(status, result);
  }

  public static ReturnMsg returnMsg(Status status, Object result) {
    return new ReturnMsg(status, result);
  }

}
