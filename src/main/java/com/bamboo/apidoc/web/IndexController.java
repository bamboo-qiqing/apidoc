package com.bamboo.apidoc.web;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.bamboo.apidoc.code.enums.Status;
import com.bamboo.apidoc.code.model.Project;
import com.bamboo.apidoc.code.model.ReturnMsg;
import com.bamboo.apidoc.code.toolkit.StringPool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;


/**
 * @Author: GuoQing
 * @Date: 2019/2/25 15:02
 * @description
 */
@Controller
@RequestMapping("bamboo")
public class IndexController {
    @Value(StringPool.JSON_FILE_CLASS_PATH)
    private Resource areaRes;

    /**
     * 跳转index界面
     *
     * @return 返回
     */
    @GetMapping("index")
    public String toIndex() {
        return "bamboo/apidoc/index";
    }

    /**
     * 跳转项目信息页面
     *
     * @return 跳转项目信息界面
     */
    @GetMapping("project")
    public String toProject() {
        return "bamboo/apidoc/project";
    }

    /**
     * 跳转文档界面
     *
     * @return 跳转文档界面
     */
    @GetMapping("apidoc")
    public String toApidoc() {
        return "bamboo/apidoc/apidoc";
    }
    /**
     * 跳转文档界面
     *
     * @return 跳转文档界面
     */
    @GetMapping("model")
    public String toModel() {
        return "bamboo/apidoc/model";
    }

    /**
     * 获取JSON数据
     *
     * @return 返回JSON文件读取到的信息
     */
    @GetMapping("getJson")
    @ResponseBody
    public ReturnMsg getJson() {
        return new ReturnMsg(Status.SUCCESS, Project.getProject());
    }

    /**
     * 修改项目信息接口
     *
     * @param project 项目信息
     * @return 接口结果
     */
    @PostMapping("updateProject")
    @ResponseBody
    public ReturnMsg updateJson(@RequestBody Project project) {
        Project projec = Project.getProject();
        projec.setStartTime(StrUtil.isBlank(project.getStartTime()) ? DateUtil.format(new Date(), StringPool.DATE_FORMAT_SIX) : project.getStartTime());
        projec.setDescription(project.getDescription());
        projec.setName(project.getName());
        projec.submitJson();
        return new ReturnMsg(Status.SUCCESS);
    }

    @GetMapping("updateProject")
    @ResponseBody
    public ReturnMsg updateJson(@RequestParam(required = false) int test) {
        return new ReturnMsg(Status.SUCCESS);
    }

    @GetMapping("getToString")
    @ResponseBody
    public ReturnMsg getToString(@RequestBody Object o) {
        return new ReturnMsg(Status.SUCCESS);
    }
    @GetMapping("getToString/{test}")
    @ResponseBody
    public ReturnMsg getToString(@PathVariable String test) {
        return new ReturnMsg(Status.SUCCESS);
    }

    @GetMapping("getToString11")
    @ResponseBody
    public ReturnMsg getToString11(@RequestHeader String tes1111t) {
        return new ReturnMsg(Status.SUCCESS);
    }
}
