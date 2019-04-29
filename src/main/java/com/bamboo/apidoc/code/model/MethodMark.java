package com.bamboo.apidoc.code.model;

import com.bamboo.apidoc.code.enums.ChangeType;
import lombok.Data;

import java.util.List;

/**
 * @Author: GuoQing
 * @Date: 2019/3/1 17:21
 * @description method 改动标记类
 */
@Data
public class MethodMark {

  /**
   * 是否改变
   */
  private boolean isChange;
  /**
   * 改动后的接口
   */
  private MethodBasic methodBasic;
  /**
   * 改变类型
   */
  private List<ChangeType> changeType;


}
