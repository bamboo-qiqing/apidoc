package com.bamboo.apidoc.code.model;

import com.bamboo.apidoc.code.enums.ChangeType;
import com.bamboo.apidoc.code.toolkit.StringUtils;
import lombok.Data;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


/**
 * @Author: GuoQing
 * @Date: 2019/2/13 16:43
 * @description 路由方法详情
 */
@Data
public class MethodBasic {
  /**
   * 方法名
   */
  private String name;
  /**
   * 包名
   */
  private String packageName;
  /**
   * 类名称
   */
  private String className;
  /**
   * 中文名
   */
  private String chineseName;
  /**
   * 接口地址
   */
  private Set<String> routPaths;
  /**
   * 方法类型
   */
  private Set<RequestMethod> methodTypes;
  /**
   * 方法描述
   */
  private String description;
  /**
   * 方法参数
   */
  private ArrayList<Param> params;


  /**
   * 根据 RequestMappingInfo 和 handler 构建Method对象
   *
   * @param mappingInfo mappingInfo
   * @param handler     handler
   * @return 返回构建好的Method对象
   */
  public static MethodBasic buildMethodBasic(RequestMappingInfo mappingInfo, HandlerMethod handler) {
    Assert.notNull(mappingInfo, "RequestMappingInfo must not be null");
    Assert.notNull(handler, "HandlerMethod must not be null");
    MethodBasic methodInfo = new MethodBasic();
    methodInfo.setMethodTypes(mappingInfo.getMethodsCondition().getMethods());
    methodInfo.setRoutPaths(mappingInfo.getPatternsCondition().getPatterns());
    methodInfo.setName(handler.getMethod().getName());
    methodInfo.setPackageName(handler.getMethod().getDeclaringClass().getPackage().getName());
    methodInfo.setClassName(handler.getMethod().getDeclaringClass().getName());
    methodInfo.setParams(Param.buildParams(handler));

    return methodInfo;
  }


  /**
   * 根据方法基础类获取方法详细信息对象
   *
   * @param version 方法版本
   * @return 返回
   */
  MethodInfo getMethodInfo(Integer version) {
    MethodInfo methodInfo = new MethodInfo();
    methodInfo.setMethodBasic(this);
    methodInfo.setVersion(version);
    methodInfo.setId(StringUtils.getUUID());
    return methodInfo;
  }

  /**
   * 根据方法基础类获取方法详标记类对象
   *
   * @param ischange 是否改动
   * @return 返回
   */
  MethodMark getMethodMark(Boolean ischange) {
    MethodMark methodMark = new MethodMark();
    methodMark.setChange(ischange == null ? Boolean.FALSE : ischange);
    methodMark.setMethodBasic(this);
    return methodMark;
  }

  /**
   * 和当前对象比较，判断是否相同
   *
   * @return 返回不同之处的改变类型
   */
  public List<ChangeType> equals(MethodBasic methodBasic) {
    List<ChangeType> changeTypes = new ArrayList<>();
    if (!(this.name != null && this.name.equals(methodBasic.getName()))) {
      changeTypes.add(ChangeType.name);
    }
    if (!(this.packageName != null && this.packageName.equals(methodBasic.getPackageName()))) {
      changeTypes.add(ChangeType.packageName);
    }
    if (!(this.className != null && this.className.equals(methodBasic.getClassName()))) {
      changeTypes.add(ChangeType.className);
    }
    if (!(this.routPaths != null && this.routPaths.equals(methodBasic.getRoutPaths()))) {
      changeTypes.add(ChangeType.routPaths);
    }
    return changeTypes;
  }
}
