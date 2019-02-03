package com.bamboo.apidoc.annotation;

/**
 * @Author: GuoQing
 * @Date: 2019/2/3 10:36
 * @description  配置文件包扫描地址中如果当前有某类为控制层同时包含@Apidoc注解时,在apidoc中生成该控制层的所有接口等待填写接口信息
 */
public @interface Apidoc {
  String title() default "";
}
