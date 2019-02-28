package com.bamboo.apidoc.extension.toolkit;


import com.bamboo.apidoc.annotation.Apidoc;
import com.bamboo.apidoc.code.toolkit.ExceptionUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.bamboo.apidoc.code.toolkit.FileUtil;
import org.springframework.core.io.*;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;

/**
 * <p>
 *
 * </p>
 *
 * @Author: GuoQing
 * @Date: 2019/2/3 10:56
 * @description 包工具类
 */
public class PackageHelper {

    public static Class<?>[] convertPackagePath(String packagePath) {
        /**
         * PathMatchingResourcePatternResolver是ResourcePatternResolver的实现，
         * 它能够将指定的资源位置路径解析为一个或多个匹配的资源
         */
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resolver);
        String pkg = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
                + ClassUtils.convertClassNameToResourcePath(packagePath) + "/*.class";
        /*
         * 将加载多个绝对匹配的所有Resource
         * 将首先通过ClassLoader.getResource("META-INF")加载非模式路径部分，然后进行遍历模式匹配，排除重复包路径
         */
        try {
            Set<Class<?>> set = new HashSet<>();
            Resource[] resources = resolver.getResources(pkg);
            if (resources != null && resources.length > 0) {
                MetadataReader metadataReader;
                for (Resource resource : resources) {
                    if (resource.isReadable()) {
                        metadataReader = metadataReaderFactory.getMetadataReader(resource);
                        Set<String> annotationTypes = metadataReader.getAnnotationMetadata().getAnnotationTypes();
                        Boolean filter = AnnotaionFilter.filter(annotationTypes);
                        if (filter) {
                            set.add(Class.forName(metadataReader.getClassMetadata().getClassName()));
                        }
                    }
                }
            }
            if (!set.isEmpty()) {
                return set.toArray(new Class[]{});
            }
            return new Class[0];
        } catch (Exception e) {
            throw ExceptionUtils.ape("找不到package-path: %s", e, pkg);
        }

    }


    public static void main(String[] args) throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resolver);
        TypeFilter annotationTypeFilter = new AnnotationTypeFilter(Apidoc.class);
        String path = ResourceUtils.getURL("classpath:").getPath();
        List<File> files = FileUtil.loopFiles(path);
        for (File file : files) {
            if ("class".equals(FileUtil.extName(file))) {
                String[] classes = FileUtil.getAbsolutePath(file.getParent()).split("classes");
                if(classes!=null&&classes.length>1){
                    for (String one : classes){
                        Class<?>[] classes1 = convertPackagePath(one);
                        for ( Class classes11: classes1){
                            System.out.println(classes11.getName());
                        }
                    }
                }
            }



        }



    }


    /**
     * 扫描获取指定包路径所有类
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
            throw ExceptionUtils.ape("未扫描到该包路径: %s", e, pkg);
        }
    }

}
