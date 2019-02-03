package com.bamboo.apidoc.extension.toolkit;


import com.fasterxml.jackson.databind.util.ClassUtil;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;

/**
 * <p>
 *
 * </p>
 * @Author: GuoQing
 * @Date: 2019/2/3 10:56
 * @description 包工具类
 */
public class PackageHelper {

  public  static  String[] convertPackagePath(String packagePath){

    /**
     * PathMatchingResourcePatternResolver是ResourcePatternResolver的实现，
     * 它能够将指定的资源位置路径解析为一个或多个匹配的资源
     */
    ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
    MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resolver);
    String pkg = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
        + ClassUtils.convertClassNameToResourcePath(packagePath) + "/*.class";
    return  null;
  }

}
