package com.bamboo.apidoc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: GuoQing
 * @Date: 2019/2/3 10:36
 * @description  配置文件包扫描地址中如果当前有某类为控制层同时包含@Apidoc注解时,在apidoc中生成该控制层的所有接口等待填写接口信息
 */

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Apidoc {
  String title() default "";
}
