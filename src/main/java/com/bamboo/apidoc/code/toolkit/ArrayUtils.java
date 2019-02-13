package com.bamboo.apidoc.code.toolkit;

/**
 * @Author: GuoQing
 * @Date: 2019/2/12 21:42
 * @description ArrayUtils工具类
 */
public class ArrayUtils {
  /**
   * 判断数据是否为空
   *
   * @param array 长度
   * @return 数组对象为null或者长度为 0 时，返回 false
   */
  public static boolean isEmpty(Object[] array) {
    return array == null || array.length == 0;
  }

  /**
   * 判断数组是否不为空
   *
   * @param array 数组
   * @return 数组对象内含有任意对象时返回 true
   */
  public static boolean isNotEmpty(Object[] array) {
    return !isEmpty(array);
  }
}
