package com.bamboo.apidoc.service;

import com.bamboo.apidoc.code.model.Project;
import com.bamboo.apidoc.code.model.ReturnMsg;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: GuoQing
 * @Date: 2019/3/7 16:53
 * @description  apidoc Service 接口类
 */
public interface ApiDocService {

    /**
     * 更新项目信息
     * @param project  参数
     * @return 修改结果
     */
    ReturnMsg updateProject(Project project);

    /**
     * 保存模块
     * @param name 模块名
     * @param description  模块描述
     * @return 返回保存结果
     */
    ReturnMsg saveModel(String name, String description);
}
