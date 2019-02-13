package com.bamboo.apidoc.extension.factory;

import static org.springframework.util.StringUtils.hasLength;
import static org.springframework.util.StringUtils.tokenizeToStringArray;

import com.bamboo.apidoc.code.toolkit.StringPool;
import com.bamboo.apidoc.code.toolkit.ArrayUtils;
import com.bamboo.apidoc.extension.toolkit.PackageHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.Data;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Author: GuoQing
 * @Date: 2019/2/3 11:22
 * @description
 */
@Data
public class ApidocFactoryBean implements InitializingBean {

  private static final Log LOGGER = LogFactory.getLog(ApidocFactoryBean.class);
  /**
   * InitializingBean 是Spring提供bean初始化的一种方式，
   * 凡是实现InitializingBean的类，在初始化bean时，会调用afterPropertiesSet方法
   */

  /**
   * 需要扫描的包集合
   */
  String packagePath;

  public void buildApidocFactory() {
    if (hasLength(this.packagePath)) {
      // TODO 支持自定义通配符
      List<String> typeAliasPackageList = new ArrayList<>();
      if (packagePath.contains(StringPool.ASTERISK) && !packagePath.contains(
          StringPool.COMMA) && !packagePath.contains(
          StringPool.SEMICOLON)) {
        String[] convertTypeAliasesPackages = PackageHelper.convertPackagePath(this.packagePath);
        if (ArrayUtils.isEmpty(convertTypeAliasesPackages)) {
          LOGGER.warn("在'[" + packagePath + "]' 包下找不到类. 请检查你的配置.");
        } else {
          typeAliasPackageList.addAll(Arrays.asList(convertTypeAliasesPackages));
        }
      } else {
        String[] typeAliasPackageArray = tokenizeToStringArray(this.packagePath,
            ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS);
        for (String one : typeAliasPackageArray) {
          if (one.contains(StringPool.ASTERISK)) {
            String[] convertTypeAliasesPackages = PackageHelper.convertPackagePath(one);
            if (ArrayUtils.isEmpty(convertTypeAliasesPackages)) {
              LOGGER.warn("在'[" + packagePath + "]' 包下找不到类. 请检查你的配置.");
            } else {
              typeAliasPackageList.addAll(Arrays.asList(convertTypeAliasesPackages));
            }
          } else {
            typeAliasPackageList.add(one);
          }
        }
      }

      for (String typeAliasPackageLists:
          typeAliasPackageList) {
        System.out.println("----测试"+typeAliasPackageLists);
      }
    }
  }

  @Override
  public void afterPropertiesSet() {
    this.buildApidocFactory();
  }
}
