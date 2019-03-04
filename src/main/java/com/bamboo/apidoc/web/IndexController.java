package com.bamboo.apidoc.web;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bamboo.apidoc.autoconfigure.ApidocProperties;
import com.bamboo.apidoc.code.enums.Status;
import com.bamboo.apidoc.code.model.Project;
import com.bamboo.apidoc.code.model.ReturnMsg;
import com.bamboo.apidoc.code.toolkit.StringPool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;


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
     * 获取JSON数据
     *
     * @return 返回JSON文件读取到的信息
     * @throws IOException 文件读取异常
     */
    @GetMapping("getJson")
    @ResponseBody
    public ReturnMsg getJson() throws IOException {
        Project project = JSON.parseObject(areaRes.getInputStream(), StandardCharsets.UTF_8, Project.class);
        return new ReturnMsg(Status.SUCCESS, project);
    }

    @PostMapping("updateProject")
    @ResponseBody
    public ReturnMsg updateJson(@RequestBody Project project) throws IOException {
        Project projec = JSON.parseObject(areaRes.getInputStream(), StandardCharsets.UTF_8, Project.class);
        projec.setStartTime(StrUtil.isBlank(project.getStartTime()) ? DateUtil.format(new Date(), StringPool.DATE_FORMAT_SIX) : project.getStartTime());
        projec.setDescription(project.getDescription());
        projec.setName(project.getName());
        projec.setStartFeatures(project.getStartFeatures());
        com.bamboo.apidoc.code.toolkit.FileUtil.createJson(JSONObject.toJSON(projec), ApidocProperties.jsonFilePath);
        return new ReturnMsg(Status.SUCCESS);
    }

    @GetMapping("updateProject")
    @ResponseBody
    public ReturnMsg updateJson(@RequestParam(required = false) int test) {
        return new ReturnMsg(Status.SUCCESS);
    }
}
