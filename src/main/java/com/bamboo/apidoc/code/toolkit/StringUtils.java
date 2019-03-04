package com.bamboo.apidoc.code.toolkit;

import cn.hutool.core.util.StrUtil;
import java.util.Set;
/**
 * @Author: GuoQing
 * @Date: 2019/2/12 21:55
 * @description String 工具类
 */
public class StringUtils extends StrUtil {
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
     *
     * @param prefix 字符串
     * @return 处理过的String
     */
    static String startsWith(String prefix) {
        String pre = "";
        if (prefix == null) {
            return pre;
        }
        if (!prefix.startsWith(StringPool.SLASH)) {
            pre = StringPool.SLASH + prefix;
        } else {
            pre = prefix;
        }
        return pre;
    }


    /**
     * url使用,拼接
     *
     * @param unless url集合
     * @return 返回拼接的url
     */
    public static String urlSplice(Set<String> unless) {
        StringBuilder url = new StringBuilder();
        if (unless != null && unless.size() > 0) {
            for (String urls : unless) {
                url.append(urls);
                url.append(",");
            }
        }
        return url.toString();
    }



}
