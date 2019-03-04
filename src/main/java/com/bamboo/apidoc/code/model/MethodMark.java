package com.bamboo.apidoc.code.model;

import lombok.Data;

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
     * 当前总版本号
     */
    private String totalVersion;
    /**
     * 改动后的接口
     */
    private Method method;

    static MethodMark buildMethodMark(Boolean ischange) {
        MethodMark methodMark = new MethodMark();
        methodMark.setChange(ischange == null ? Boolean.FALSE : Boolean.TRUE);
        return methodMark;
    }
}
