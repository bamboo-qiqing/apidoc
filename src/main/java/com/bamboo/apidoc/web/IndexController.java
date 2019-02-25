package com.bamboo.apidoc.web;

import com.alibaba.fastjson.JSON;
import com.bamboo.apidoc.code.model.Project;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @Author: GuoQing
 * @Date: 2019/2/25 15:02
 * @description
 */
@Controller
@RequestMapping("bamboo")
public class IndexController {
    @Value("classpath:/apidoc/apidoc.json")
    private Resource areaRes;

    @GetMapping("index")
    public String toIndex(){
        return "index";
    }
    @GetMapping("project")
    public String toProject() {
        return "project";
    }
    @GetMapping("getJson")
    @ResponseBody
    public Object getJson() throws IOException {
        return JSON.parseObject(areaRes.getInputStream(), StandardCharsets.UTF_8, Project.class);
    }

}
