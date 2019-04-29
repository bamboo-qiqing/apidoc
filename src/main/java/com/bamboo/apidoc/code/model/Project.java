package com.bamboo.apidoc.code.model;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileWriter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bamboo.apidoc.code.exceptions.ApiDocException;
import com.bamboo.apidoc.code.toolkit.StringUtils;
import lombok.Data;
import org.springframework.http.MediaType;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.ProducesRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.xml.ws.Response;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @Author: GuoQing
 * @Date: 2019/2/13 17:08
 * @description 项目信息
 */
@Data
public class Project {
  /**
   * 大版本默认值
   */
  public static Integer defaultLargeVersion = 0;
  /**
   * 小版本默认值
   */
  public static Integer defaultSmallVersion = 1;
  /**
   * 项目名
   */
  private String name;
  /**
   * 开始时间
   */
  private String startTime;
  /**
   * 项目描述
   */
  private String description;
  /**
   * 小版本
   */
  private Integer smallVersion;
  /**
   * 大版本
   */
  private Integer largeVersion;
  /**
   * jdk版本
   */
  private String jdkVersion = System.getProperty("java.version");
  /**
   * 模块集合
   */
  private List<Module> modules;
  /**
   * 方法集合
   */
  private List<Method> methods;
  /**
   * Json地址
   */
  private String jsonPath = StringUtils.getJsonPath();

  private void initProject() {
    ArrayList<Module> modules = new ArrayList<>();
    modules.add(new Module().initModule());
    methods = new ArrayList<>();
    this.modules = modules;
    this.largeVersion = Project.defaultLargeVersion;
    this.smallVersion = Project.defaultSmallVersion;
  }

  public Project buildProject(RequestMappingHandlerMapping requestMappingHandlerMapping) {
    //获取已存在Json文件中的数据
    Project project = getProject(jsonPath);
    //初始化新的版本号
    project.initNewVersion();
    //根据检测重新生成Project对象
    project.regenerate(requestMappingHandlerMapping.getHandlerMethods());
    //把当前对象编译为Json文件，文件地址为当前对象的jsonPath
    project.compileJson();
    return project;
  }

  /**
   * 读取JSON文件转化为Project对象
   *
   * @param path JSON文件地址
   * @return 返回已读取的文件对象
   */
  private static Project getProject(String path) {
    Project project = null;
    try {
      if (FileUtil.exist(path)) {
        project = JSON.parseObject(new FileInputStream(FileUtil.file(path)), StandardCharsets.UTF_8, Project.class);
      } else {
        project = new Project();
        project.initProject();
        File json = project.compileJson(path);
        project = JSON.parseObject(new FileInputStream(json), StandardCharsets.UTF_8, Project.class);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    return project;
  }

  /**
   * 读取默认JSON文件地址把JSON文件转化为Project对象
   *
   * @return 返回已读取的文件对象
   */
  public static Project getProject() {
    return getProject(StringUtils.getJsonPath());
  }

  /**
   * 获取所有方法
   *
   * @return 所有模块
   */
  private Map<String, MethodCache> getAllMethods() {
    return Module.getAllMethods(this.methods);
  }

  /**
   * 根据JSON文件地址，创建JSON文
   *
   * @param jsonPath JSON文件地址，包括文件名称
   * @return 返回创建的File文件对象
   */
  private File compileJson(String jsonPath) {
    File file = FileUtil.touch(jsonPath);

    if (!file.canWrite()) {
      file.setWritable(true);
    }
    FileWriter fileWriter = FileWriter.create(file);
    return fileWriter.write(JSONObject.toJSON(this).toString());
  }

  /**
   * 根据JSON文件地址，创建JSON文件
   *
   * @return 返回创建的File文件对象
   */
  private void compileJson() {
    File file = FileUtil.touch(this.jsonPath);
    FileWriter fileWriter = FileWriter.create(file);
    fileWriter.write(JSONObject.toJSON(this).toString());
  }


  /**
   * 根据默认JSON文件地址，创建JSON文
   *
   * @return 返回创建的File文件对象
   */
  public void submitJson() {
    this.compileJson(StringUtils.getJsonPath());
  }

  /**
   * 初始化新的版本号
   */
  private void initNewVersion() {
    if (this.smallVersion != null) {
      this.smallVersion = this.smallVersion + 1;
      if (this.smallVersion > 100) {
        this.largeVersion = this.largeVersion + 1;
        this.smallVersion = Project.defaultSmallVersion;
      }
    }
  }

  /**
   * 重新生成Project对象
   *
   * @param handlerMethods Map<RequestMappingInfo, HandlerMethod>
   */
  private Project regenerate(Map<RequestMappingInfo, HandlerMethod> handlerMethods) {
    //获取历史方法集合
    Map<String, MethodCache> allMethods = this.getAllMethods();
    //遍历所有Spring处理器获取到的方法
    for (Map.Entry<RequestMappingInfo, HandlerMethod> handlerMethod : handlerMethods.entrySet()) {
      if (handlerMethod.getKey().getPatternsCondition().getPatterns().contains("/error")) {
        break;
      }
      //根据拼接的Url检测已存在方法中是否存在该接口
      if (allMethods != null) {

        MethodCache methodCache = allMethods.get(StringUtils.patternsSplice(handlerMethod));
        Method newmethod = Method.buildMethod(handlerMethod.getKey(), handlerMethod.getValue());
        //不存在则生成，存入未分配模块
        if (methodCache == null) {
          //获取未分配模块Id
          String moduleId = Module.getModuleIdByName(this.getModules(), Module.UNALLOCATED);
          if (StringUtils.isNotBlank(moduleId)) {
            newmethod.setCheckVersion(this.largeVersion + "." + this.smallVersion);
            newmethod.getMethodInfo().setModelId(moduleId);
            this.methods.add(newmethod);
          }
        } else {
          methodCache.getMethodInfo().setCheckVersion(this.largeVersion + "." + this.smallVersion);
          if (newmethod != null) {
            if (methodCache.getMethodInfo().isChange(newmethod)) {
              this.methods.set(methodCache.getMethodSubscript(), methodCache.getMethodInfo());
            }
          }
        }
      } else {
        throw new ApiDocException(Project.class.getName() + "-----------检测错误，未在Json文件中检测到任何接口----");
      }
    }
    return this;
  }
}
