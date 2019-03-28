<#include "public.ftl" >
<@html >
    <@head/>
<style>
    .read-only input {
        border: 0px;
    }
</style>
    <@body false>
       <el-row :gutter="20">
           <el-col :span="10" :offset="7">
               <div class="grid-content bg-purple">
                   <el-form ref="form" :model="project" label-width="80px">
                       <el-form-item label="项目名称">
                           <el-input v-model="project.name"></el-input>
                       </el-form-item>
                       <el-form-item label="开始时间">
                           <div class="block">
                               <el-date-picker
                                       v-model="project.startTime"
                                       type="date"
                                       placeholder="选择日期">
                               </el-date-picker>
                           </div>
                       </el-form-item>
                       <el-form-item label="项目描述">
                           <el-input type="textarea" v-model="project.description"></el-input>
                       </el-form-item>
                       <el-form-item label="检测次数">
                           <el-input type="text" readonly="true" class="read-only"
                                     v-model="project.number"></el-input>
                       </el-form-item>
                       <el-form-item label="当前版本">
                           <el-input type="text" readonly="true" class="read-only"
                                     v-model="project.version"></el-input>
                       </el-form-item>
                       <el-form-item label="JDK版本">
                           <el-input type="text" readonly="true" class="read-only"
                                     v-model="project.jdkVersion"></el-input>
                       </el-form-item>
                       <el-form-item label="文件地址">
                           <el-input type="text" readonly="true" class="read-only"
                                     v-model="project.jsonPath"></el-input>
                       </el-form-item>
                       <el-form-item>
                           <el-button type="primary" @click="onSubmit">保存</el-button>
                       </el-form-item>
                   </el-form>
               </div>
           </el-col>
       </el-row>
    </@body>
<script>
    new Vue({
        el: "#app",
        data() {
            return {
                project: {
                    name: "",
                    startTime: "",
                    description: "",
                    startFeatures: [],
                    jdkVersion: "",
                    jsonPath: "",
                    number: 0,
                    version: ""
                },
                edit: {
                    flag: false,
                    text: "编辑"
                }
            }
        },
        methods: {
            handleSelect(key, keyPath) {
                console.log(key, keyPath);
            }
            , modeSwitching() {
                let _this = this;
                if (_this.edit.flag) {
                    _this.edit.flag = false;
                    _this.edit.text = '编辑';
                } else {
                    _this.edit.flag = true;
                    _this.edit.text = '查看';
                }
            },
            onSubmit() {
                _this = this;
                this.updateJson(_this.project)
            }, getJson() {
                let _this = this;
                axios({
                    method: 'get',
                    url: '/bamboo/getJson',
                    headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                    responseType: 'json',
                    transformResponse: [function (data) {
                        if (data.status == 200) {
                            let json = data.result;
                            if (json.name != null) {
                                _this.project.name = json.name
                            }
                            if (json.startTime != null) {
                                _this.project.startTime = json.startTime
                            }
                            if (json.description != null) {
                                _this.project.description = json.description
                            }
                            if (json.startFeatures != null) {
                                _this.project.startFeatures = json.startFeatures
                            }
                            if (json.startFeatures != null) {
                                _this.project.startFeatures = json.startFeatures
                            }
                            if (json.jdkVersion != null) {
                                _this.project.jdkVersion = json.jdkVersion
                            }
                            if (json.jsonPath != null) {
                                _this.project.jsonPath = json.jsonPath
                            }
                            _this.project.number = (json.largeVersion * 100) + json.smallVersion
                            _this.project.version = json.largeVersion + "." + json.smallVersion

                        } else {
                            _this.$message({
                                message: '加载数据失败',
                                type: 'warning'
                            });
                        }
                        return data;
                    }]
                })
            }, updateJson(project) {
                console.log(project)
                let _this = this;

                axios({
                    method: 'POST',
                    url: '/bamboo/updateProject',
                    data: project,
                    responseType: 'json',
                    transformResponse: [function (data) {
                        if (data.status == 200) {
                            _this.$message({
                                message: '修改成功',
                                type: 'success'
                            });
                        } else {
                            _this.$message({
                                message: '修改失败',
                                type: 'warning'
                            });
                        }
                        return data;
                    }]
                })
            }
        }, mounted: function () {
            this.getJson();
        }
    })
</script>
</@html>
