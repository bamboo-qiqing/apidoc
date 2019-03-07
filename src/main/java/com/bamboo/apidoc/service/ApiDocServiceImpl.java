package com.bamboo.apidoc.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.bamboo.apidoc.code.enums.Status;
import com.bamboo.apidoc.code.model.Module;
import com.bamboo.apidoc.code.model.Project;
import com.bamboo.apidoc.code.model.ReturnMsg;
import com.bamboo.apidoc.code.toolkit.StringPool;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: GuoQing
 * @Date: 2019/3/7 16:53
 * @description
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
        return new ReturnMsg(Status.SUCCESS,"成功");
    }

    @Override
    public ReturnMsg saveModel(String name, String description) {
        if (StrUtil.isNotBlank(name)) {
            Project projec = Project.getProject();
            int oneModuleByName = Module.getOneModuleByName(projec.getModules(), name);
            List<Module> modules = projec.getModules();
            if (oneModuleByName < 0) {
                Module module = new Module();
                module.setName(name);
                module.setDescription(description);
                module.setMethods(new ArrayList<>());
                modules.add(module);
            } else {
                Module module = modules.get(oneModuleByName);
                module.setDescription(description);
            }
            projec.submitJson();
        }
        return new ReturnMsg(Status.SUCCESS, "成功");
    }
}
