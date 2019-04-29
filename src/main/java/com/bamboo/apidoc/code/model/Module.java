package com.bamboo.apidoc.code.model;

import com.bamboo.apidoc.code.exceptions.ApiDocException;
import com.bamboo.apidoc.code.toolkit.StringUtils;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Author: GuoQing
 * @Date: 2019/2/13 17:10
 * @description 模块信息
 */
@Data
public class Module {

  /**
   * 所有接口在未分配指定模块时，默认在未分配模块
   * 此为未分配模块名称
   */
  public final static String UNALLOCATED = "未分配";
  /**
   * 所有接口出现异常时将会被分配在异常模
   */
  public final static String ABNORMAL = "异常";
  /**
   * 模块id
   */
  private String id;
  /**
   * 模块名
   */
  private String name;
  /**
   * 模块描述
   */
  private String description;

  /**
   * 获取与指定模块名相同的模块的下标
   *
   * @param modules    模块集合
   * @param moduleName 模块名
   * @return 存在返回模块的下标，不存在返回-1
   */
  public static String getModuleIdByName(List<Module> modules, String moduleName) {
    if (modules == null || modules.size() < 1 || moduleName == null || "".equals(moduleName)) {
      return null;
    }
    for (int i = 0; i < modules.size(); i++) {
      if (moduleName.equals(modules.get(i).getName())) {
        return modules.get(i).getId();
      }
    }
    return null;
  }

  /**
   * 获取与指定模块名相同的模块的下标
   *
   * @param modules    模块集合
   * @param moduleName 模块名
   * @return 存在返回模块的下标，不存在返回-1
   */
  public static int getModuleSubscriptByName(List<Module> modules, String moduleName) {
    if (modules == null || modules.size() < 1 || moduleName == null || "".equals(moduleName)) {
      return -1;
    }
    for (int i = 0; i < modules.size(); i++) {
      if (moduleName.equals(modules.get(i).getName())) {
        return i;
      }
    }
    return -1;
  }

  /**
   * 根据模块获取所有的方法集合
   *
   * @param methods 方法集合
   * @return 返回方法集合
   */
  public static Map<String, MethodCache> getAllMethods(List<Method> methods) {
    Map<String, MethodCache> methodCacheMap = new HashMap<>();
    if (!methods.isEmpty()) {
      for (int i = 0; i < methods.size(); i++) {
        MethodBasic methodBasic = methods.get(i).getMethodInfo().getMethodBasic();

        methodCacheMap.put(StringUtils.patternsSplice(methodBasic), MethodCache.buildMethod(i, methods.get(i)));
      }

    }
    return methodCacheMap;
  }

  /**
   * 初始化model
   *
   * @return
   */
  public Module initModule() {
    this.name = Module.UNALLOCATED;
    this.description = "当前模块为未曾分配的接口集合";
    this.id = StringUtils.getUUID();
    return this;
  }
}
