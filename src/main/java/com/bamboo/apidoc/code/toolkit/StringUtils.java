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
import java.util.UUID;

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


  public static String patternsSplice(Map.Entry<RequestMappingInfo, HandlerMethod> rem) {
    MethodBasic methodBasic = MethodBasic.buildMethodBasic(rem.getKey(), rem.getValue());
    return patternsSplice(methodBasic);
  }

  public static String patternsSplice(MethodBasic methodBasic) {
    Set<RequestMethod> methodTypes = methodBasic.getMethodTypes();
    Set<String> routPaths = methodBasic.getRoutPaths();
    return patternsSplice(routPaths, methodTypes);
  }

  public static String patternsSplice(Set<String> routPaths, Set<RequestMethod> methodTypes) {
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
    return url.toString();
  }


  /**
   * 获取JSON文件地址
   *
   * @return JSON文件地址
   */
  public static String getJsonPath() {
    return (System.getProperty("user.dir") + "/src/main/resources/" + StringPool.JSON_PATH).replace("\\", "/");
  }

  /**
   * 获取UUID
   *
   * @return 返回生成的UUID
   */
  public static String getUUID() {
    return UUID.randomUUID().toString();
  }
}
