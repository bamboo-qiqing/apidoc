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

    /**
     * 为字符串指定特定的字符开头
     * @param prefix 字符串
     * @param starts  指定开头
     * @return 处理过的String
     */
    public static String startsWith(String prefix, String starts) {
        String pre = "";
        if (prefix == null || prefix == "") {
            return pre;
        }
        if (!prefix.startsWith(starts)) {
            pre = starts + prefix;
        }else{
            pre =prefix;
        }
        return  pre;
    }
}
