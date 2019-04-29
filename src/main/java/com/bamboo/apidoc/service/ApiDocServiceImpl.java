package com.bamboo.apidoc.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.bamboo.apidoc.code.enums.Status;
import com.bamboo.apidoc.code.model.Method;
import com.bamboo.apidoc.code.model.Module;
import com.bamboo.apidoc.code.model.Project;
import com.bamboo.apidoc.code.model.ReturnMsg;
import com.bamboo.apidoc.code.toolkit.StringPool;
import com.bamboo.apidoc.code.toolkit.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: GuoQing
 * @Date: 2019/3/7 16:53
 * @description apidoc 实现类
 */
@Service
public class ApiDocServiceImpl implements ApiDocService {
  @Override
  public ReturnMsg updateProject(Project project) {
    Project projec = Project.getProject();
    projec.setStartTime(StrUtil.isBlank(project.getStartTime()) ? DateUtil.format(new Date(), StringPool.DATE_FORMAT_SIX) : project.getStartTime());
    projec.setDescription(project.getDescription());
    projec.setName(project.getName());
    projec.submitJson();
    return new ReturnMsg(Status.SUCCESS, "成功");
  }

  @Override
  public ReturnMsg saveModel(String name, String description) {
    if (StrUtil.isNotBlank(name)) {
      Project projec = Project.getProject();
      String id = Module.getModuleIdByName(projec.getModules(), name);
      List<Module> modules = projec.getModules();
      if (StringUtils.isBlank(id)) {
        Module module = new Module();
        module.setName(name);
        module.setDescription(description);
        module.setId(StringUtils.getUUID());
        modules.add(module);
      } else {
        int iSubscript = Module.getModuleSubscriptByName(projec.getModules(), name);
        if (iSubscript > -1) {
          Module module = modules.get(iSubscript);
          module.setDescription(description);
        }
      }
      projec.submitJson();
    }
    return new ReturnMsg(Status.SUCCESS, "成功");
  }


  @Override
  public ReturnMsg saveApi(Method method) {
    Project projec = Project.getProject();
    for (Method metho : projec.getMethods()) {
      if (metho.getMethodInfo().getId().equals(method.getMethodInfo().getId())) {
        metho.setMethodInfo(method.getMethodInfo());
      } else {
        ReturnMsg.returnMsg(Status.ERROR, "该接口已不存在");
      }
    }
    projec.submitJson();
    return ReturnMsg.returnMsg(Status.SUCCESS, "成功");
  }
}
