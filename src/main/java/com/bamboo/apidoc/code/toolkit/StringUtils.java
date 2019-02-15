package com.bamboo.apidoc.code.toolkit;


/**
 * @Author: GuoQing
 * @Date: 2019/2/12 21:55
 * @description String 工具类
 */
public class StringUtils {
    /**
     * 安全的进行字符串 format
     *
     * @param target 目标字符串
     * @param params format 参数
     * @return format 后的
     */
    public static String format(String target, Object... params) {
        if (target.contains("%s") && ArrayUtils.isNotEmpty(params)) {
            return String.format(target, params);
        }
        return target;
    }
}
