package com.bamboo.apidoc.code.model;

import lombok.Data;

/**
 * @Author: GuoQing
 * @Date: 2019/3/1 17:21
 * @description   method 改动标记类
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



}
