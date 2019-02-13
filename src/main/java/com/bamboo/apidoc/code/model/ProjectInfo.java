package com.bamboo.apidoc.code.model;

import lombok.Data;
import java.util.Date;
import java.util.List;

/**
 * @Author: GuoQing
 * @Date: 2019/2/13 17:08
 * @description 项目信息
 */
@Data
public class ProjectInfo {
    /**
     * 项目名
     */
    private String name;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 项目描述
     */
    private String description;
    /**
     * 模块集合
     */
    private List<ModuleInfo> modules;

}
