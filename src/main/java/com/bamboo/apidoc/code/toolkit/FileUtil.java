package com.bamboo.apidoc.code.toolkit;

import cn.hutool.core.io.file.FileWriter;

import java.io.BufferedOutputStream;
import java.io.File;
import java.util.List;

/**
 * @Author: GuoQing
 * @Date: 2019/2/20 14:48
 * @description 文件工具类
 */
public class FileUtil extends cn.hutool.core.io.FileUtil {
    public static void createJson(Object json, String path) {
        File file = cn.hutool.core.io.FileUtil.touch(path);
        FileWriter fileWriter = FileWriter.create(file);
        File write = fileWriter.write(json.toString());

    }


}
