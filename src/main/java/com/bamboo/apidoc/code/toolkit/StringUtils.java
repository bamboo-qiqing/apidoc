package com.bamboo.apidoc.code.toolkit;

import cn.hutool.core.util.StrUtil;
import com.bamboo.apidoc.autoconfigure.ApidocAutoConfiguration;
import com.bamboo.apidoc.code.exceptions.ApiDocException;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.util.Set;
/**
 * @Author: GuoQing
 * @Date: 2019/2/12 21:55
 * @description String 工具类
 */
public class StringUtils extends StrUtil {

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
    public static String patternsSplice(Set<String> unless) {
        StringBuilder url = new StringBuilder();
        if (unless != null && unless.size() > 0) {
            for (String urls : unless) {
                url.append(urls);
                url.append(",");
            }
        }
        return url.toString();
    }

    /**
     * 获取JSON文件地址
     * @return JSON文件地址
     */
    public  static  String  getJsonPath(){
        String jsonpath = "";
        try {
            jsonpath = ResourceUtils.getURL("classpath:").getPath() + StringPool.JSON_PATH;
        } catch (FileNotFoundException e) {
            throw new ApiDocException("获取Json文件地址失败 错误信息为" + e);
        }
        return   jsonpath;
    }
}
