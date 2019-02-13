package com.bamboo.apidoc.extension.toolkit;




import com.bamboo.apidoc.code.toolkit.ExceptionUtils;
import java.util.HashSet;
import java.util.Set;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
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
    System.out.println("PackageHelper.convertPackagePath"+pkg);
    /*
     * 将加载多个绝对匹配的所有Resource
     * 将首先通过ClassLoader.getResource("META-INF")加载非模式路径部分，然后进行遍历模式匹配，排除重复包路径
     */
    try {
      Set<String> set = new HashSet<>();
      Resource[] resources = resolver.getResources(pkg);
      if (resources != null && resources.length > 0) {
        MetadataReader metadataReader;
        for (Resource resource : resources) {
          if (resource.isReadable()) {
            metadataReader = metadataReaderFactory.getMetadataReader(resource);
            set.add(Class.forName(metadataReader.getClassMetadata().getClassName()).getPackage().getName());
          }
        }
      }
      if (!set.isEmpty()) {
        return set.toArray(new String[]{});
      }
      return new String[0];
    } catch (Exception e) {
      throw ExceptionUtils
          .mpe("not find typeAliasesPackage: %s", e, pkg);
    }

  }


  /**
   * <p>
   * 扫描获取指定包路径所有类
   * </p>
   *
   * @param typePackage 扫描类包路径
   * @return
   */
  public static Set<Class> scanTypePackage(String typePackage) {
    ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
    MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resolver);
    String pkg = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
        + ClassUtils.convertClassNameToResourcePath(typePackage) + "/*.class";
    /*
     * 将加载多个绝对匹配的所有Resource
     * 将首先通过ClassLoader.getResource("META-INF")加载非模式路径部分，然后进行遍历模式匹配，排除重复包路径
     */
    try {
      Set<Class> set = new HashSet<>();
      Resource[] resources = resolver.getResources(pkg);
      if (resources != null && resources.length > 0) {
        MetadataReader metadataReader;
        for (Resource resource : resources) {
          if (resource.isReadable()) {
            metadataReader = metadataReaderFactory.getMetadataReader(resource);
            set.add(Class.forName(metadataReader.getClassMetadata().getClassName()));
          }
        }
      }
      return set;
    } catch (Exception e) {
      throw ExceptionUtils.mpe("not find scanTypePackage: %s", e, pkg);
    }
  }

}
