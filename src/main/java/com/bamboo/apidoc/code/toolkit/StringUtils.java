package com.bamboo.apidoc.code.toolkit;

import cn.hutool.core.util.StrUtil;
import com.bamboo.apidoc.autoconfigure.ApidocAutoConfiguration;
import com.bamboo.apidoc.code.exceptions.ApiDocException;
import com.bamboo.apidoc.code.model.MethodBasic;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;

import java.io.FileNotFoundException;
import java.util.Map;
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


    public static String patternsSplice(Map.Entry<RequestMappingInfo, HandlerMethod> requestMappingInfoHandlerMethodEntry) {
        MethodBasic methodBasic = MethodBasic.buildMethodBasic(requestMappingInfoHandlerMethodEntry.getKey(), requestMappingInfoHandlerMethodEntry.getValue());
        return patternsSplice(methodBasic);
    }

    public static String patternsSplice(MethodBasic methodBasic) {
        Set<RequestMethod> methodTypes = methodBasic.getMethodTypes();
        String name = methodBasic.getName();
        String className = methodBasic.getClassName();
        Set<String> routPaths = methodBasic.getRoutPaths();
        return patternsSplice(routPaths, methodTypes, className, name);
    }

    public static String patternsSplice(Set<String> routPaths, Set<RequestMethod> methodTypes, String className, String name) {
        StringBuilder url = new StringBuilder();
        if (routPaths != null && routPaths.size() > 0) {
            for (String urls : routPaths) {
                url.append(urls);
                url.append(",");
            }
        }
        if (methodTypes != null && methodTypes.size() > 0) {
            for (RequestMethod requestMethod : methodTypes) {
                url.append(requestMethod.toString());
                url.append(",");
            }
        }
        if (StrUtil.isNotBlank(className)) {
            url.append(className);
        }
        if (StrUtil.isNotBlank(name)) {
            url.append(name);
        }
        return url.toString();
    }


    /**
     * 获取JSON文件地址
     *
     * @return JSON文件地址
     */
    public static String getJsonPath() {
        String jsonpath = "";
        try {
            jsonpath = ResourceUtils.getURL("classpath:").getPath() + StringPool.JSON_PATH;
        } catch (FileNotFoundException e) {
            throw new ApiDocException("获取Json文件地址失败 错误信息为" + e);
        }
        return jsonpath;
    }
}
