package com.bamboo.apidoc.code.model;


import com.bamboo.apidoc.code.toolkit.ObjectUtil;
import com.bamboo.apidoc.code.toolkit.StringPool;
import lombok.*;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.method.HandlerMethod;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: GuoQing
 * @Date: 2019/2/13 16:47
 * @description 路由参数详情
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Param {

  /**
   * 参数名
   */
  private String name;
  /**
   * 参数类型
   */
  private String type;
  /**
   * 参数是否可以置空
   */
  private Boolean isNull;
  /**
   * 参数长度
   */
  private String length;
  /**
   * 参数描述
   */
  private String description;


  public static ArrayList<Param> buildParams(HandlerMethod handler) {
    Assert.notNull(handler, "HandlerMethod must not be null");
    return getParams(handler.getMethod());
  }

  /**
   * 根据方法获取当前方法的所有参数信息
   *
   * @param method 方法
   * @return 返回参数对象数组
   */
  public static ArrayList<Param> getParams(Method method) {
    //java8新特性，可以获取到参数的名称
    LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
    //获取方法中参数的数量
    int parameterCount = method.getParameterCount();
    //创建参数对象数组，并初始化数组的长度
    ArrayList<Param> params = new ArrayList<>();
    //获取参数类型
    Class<?>[] parameterTypes = method.getParameterTypes();
    //根据方法获取参数名称
    String[] parameterNames = u.getParameterNames(method);
    //获取方法中参数所有的注解
    Annotation[][] parameterAnnotations = method.getParameterAnnotations();
    for (int i = 0; i < parameterCount; i++) {
      Param param = new Param();
      if (parameterTypes[i].isPrimitive()) {
        param.setName(parameterNames[i]);
        param.setType(parameterTypes[i].getName());
        param.setIsNull(isNull(parameterAnnotations[i]));
        params.add(param);
      } else {
        try {
          getParam(parameterTypes[i], params);
        } catch (ClassNotFoundException e) {
          e.printStackTrace();
        }
      }

    }
    return params;
  }

  /**
   * 根据参数注解判断参数可否为空
   *
   * @param annotations 参数注解
   * @return 如果可以为空  返回true，否则返回false
   */
  public static Boolean isNull(Annotation[] annotations) {
    Boolean isNull = true;
    if (annotations != null && annotations.length > 1) {
      for (Annotation annotation : annotations) {
        if (annotation instanceof RequestParam) {
          isNull = ((RequestParam) annotation).required();
          break;
        }
        if (annotation instanceof PathVariable) {
          isNull = ((PathVariable) annotation).required();
          break;
        }
        if (annotation instanceof RequestBody) {
          isNull = ((RequestBody) annotation).required();
          break;
        }
      }
    }
    return isNull;
  }


  private static void getParam(Class<?> clazz, ArrayList<Param> params) throws ClassNotFoundException {
    assert params != null;

    Field[] declaredFields = clazz.getDeclaredFields();
    for (Field field : declaredFields) {
      if (field.getType().isPrimitive()) {
        params.add(new Param(field.getName(), field.getType().getTypeName(), null, "", ""));
      } else {
        if (ObjectUtil.map.get(field.getType()) != null) {
          params.add(new Param(field.getName(), ObjectUtil.map.get(field.getType()), null, "", ""));
        } else {
          if (ObjectUtil.set.get(field.getType()) != null) {
            ParameterizedType listGenericType = (ParameterizedType) field.getGenericType();
            Type[] listActualTypeArguments = listGenericType.getActualTypeArguments();
            Class<?> classType = Class.forName(listActualTypeArguments[listActualTypeArguments.length - 1].getTypeName());
            if (classType.isInterface()) {
              params.add(new Param(field.getName(), "接口类型", null, "", ""));
            } else if (classType.isEnum()) {
              params.add(new Param(field.getName(), ObjectUtil.map.get(Enum.class), null, "", ""));
            } else {
              getParam(classType, params);
            }
          } else {
            getParam(field.getType(), params);
          }

        }
      }

    }

  }
}
