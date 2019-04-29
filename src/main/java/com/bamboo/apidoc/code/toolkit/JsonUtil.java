package com.bamboo.apidoc.code.toolkit;


import com.bamboo.apidoc.code.model.Param;
import com.bamboo.apidoc.code.model.Project;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * @Author: GuoQing
 * @Date: 2019/4/28 11:17
 * @description
 */
public class JsonUtil {
  public static Param[] getParam(Class<?> clazz) throws ClassNotFoundException {
    assert clazz != null;
    ArrayList<Param> param = getParam(clazz, null);
    Param[] params = param.toArray(new Param[param.size()]);
    return params;
  }

  private static ArrayList<Param> getParam(Class<?> clazz, ArrayList<Param> params) throws ClassNotFoundException {
    if (params == null) {
      params = new ArrayList<>();
    }

    Field[] declaredFields = clazz.getDeclaredFields();
    for (Field field : declaredFields) {
      if (field.getType().isPrimitive()) {
        params.add(new Param(field.getName(), field.getType().getTypeName(), null, "", ""));
      } else {
        if (ObjectUtil.map.get(field.getType()) != null) {
          if (field.getType().isInterface()) {

          } else {
            params.add(new Param(field.getName(), ObjectUtil.map.get(field.getType()), null, "", ""));
          }

        } else {
          if (ObjectUtil.set.get(field.getType()) != null) {
            ParameterizedType listGenericType = (ParameterizedType) field.getGenericType();
            Type[] listActualTypeArguments = listGenericType.getActualTypeArguments();
            Class<?> classType = Class.forName(listActualTypeArguments[listActualTypeArguments.length - 1].getTypeName());
            if (classType.isInterface()) {

            } else {
              params = getParam(classType, params);
            }
          } else {
            params = getParam(field.getType(), params);
          }

        }
      }

    }
    return params;
  }

  public static void main(String[] args) throws ClassNotFoundException {
    Param[] param = getParam(Project.class);
    System.out.println(param.length);
    for (Param para : param) {
      System.out.println(para.toString());
    }

  }
}
