package com.bamboo.apidoc.code.enums;


/**
 * @Author: 郭晴
 * @Date: 2018/12/15 22:07
 * @Description: 全局状态码
 */
@SuppressWarnings("all")
public enum Status {


  SUCCESS(200, "成功"),
  ERROR(5000, "失败");
  private long code;
  private String name;

  Status(long code, String name) {
    this.name = name;
    this.code = code;
  }

  public static String getName(int index) {
    for (Status c : Status.values()) {
      if (c.getCode() == index) {
        return c.name;
      }
    }
    return null;
  }

  public long getCode() {
    return this.code;
  }


  public String getMsg() {
    return this.name;
  }
}
