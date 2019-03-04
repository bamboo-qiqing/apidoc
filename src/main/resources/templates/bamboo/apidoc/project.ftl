<#include "public.ftl" >
<@html >
    <@head/>
    <@body false>
       <el-row :gutter="20">
           <el-col :span="10" :offset="7">
               <div class="grid-content bg-purple">
                   <el-form ref="form" :model="project" label-width="80px">
                       <el-form-item label="项目名称">
                           <el-input v-model="project.name"></el-input>
                       </el-form-item>
                       <el-form-item label="功能开启">
                           <el-checkbox-group v-model="project.startFeatures">
                               <el-checkbox label="API文档"></el-checkbox>
                               <el-checkbox label="统计图"></el-checkbox>
                           </el-checkbox-group>
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
                       <el-form-item>
                           <el-button type="primary" @click="onSubmit">保存</el-button>
                           <el-button>取消</el-button>
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
                    startFeatures: []
                }
            }
        },
        methods: {
            handleSelect(key, keyPath) {
                console.log(key, keyPath);
            }
            ,
            onSubmit() {
                _this = this;
                console.log(_this.project)
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
                            let json = data.result
                            if (json.name != null) {
                                _this.project.startFeatures = json.name
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
