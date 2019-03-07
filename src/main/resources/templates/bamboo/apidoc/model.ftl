<#include "public.ftl" >
<@html >
    <@head/>
<style>
    .read-only input {
        border: 0px;
    }

    .el-menu-item, .el-submenu__title {
        height: 100%;
    }
</style>
    <@body false>
    <el-row :gutter="24">
        <el-col :span="2">
            <div class="grid-content bg-purple">
                <el-aside style="overflow:none!important;">
                    <el-menu style="border:none !important;overflow-y:none"
                             default-active="2"
                             class="el-menu-vertical-demo">
                        <el-menu-item :index="index" v-for="(model, index) in models" @click="currentModelInfo(model)">
                            <i class="el-icon-menu"></i>
                            <span slot="title">{{model.name}}</span>
                        </el-menu-item>
                    </el-menu>
                </el-aside>
            </div>
        </el-col>
        <el-col :span="18" :offset="2" v-if="currentModel!=null">
            <el-main>
                <div class="grid-content bg-purple">
                    <el-form ref="currentModel" :model="currentModel" label-width="80px">
                        <el-row :gutter="24">
                            <el-col :span="8">
                                <el-form-item label="模块名称:">
                                    <el-input v-if="edit.flag"
                                              v-model="currentModel.name">
                                    </el-input>
                                    <el-input v-else readonly="true" class="read-only"
                                              v-model="currentModel.name">
                                    </el-input>
                                </el-form-item>
                                <el-form-item v-if="!edit.flag" label="接口数量:">
                                    <el-input readonly="true" class="read-only"
                                              v-model="currentModel.methods.length">
                                    </el-input>
                                </el-form-item>
                                <el-form-item label="模块说明:">
                                    <el-input v-if="edit.flag"
                                              type="textarea"
                                              rows="2"
                                              v-model="currentModel.description">
                                    </el-input>
                                    <el-input v-else readonly="true" class="read-only"
                                              type="text"
                                              rows="2"
                                              v-model="currentModel.description">
                                    </el-input>
                                </el-form-item>
                                <el-form-item v-if="edit.flag">
                                    <el-button type="primary" @click="saveModel">保存</el-button>
                                </el-form-item>
                            </el-col>
                        </el-row>
                    </el-form>
                </div>
            </el-main>
        </el-col>
    </el-row>
    </@body>

<script>
    new Vue({
        el: "#app",
        data() {
            return {
                models: [],
                currentModel: null,
                edit: {
                    flag: false,
                    text: "编辑"
                }
            }
        },
        methods: {
            addModel() {
                let _this = this;
                _this.currentModel = {name: "", description: "", methods: []};
                _this.edit.flag = true;
                _this.edit.text = "查看";
            },
            modeSwitching() {
                let _this = this;
                if (_this.edit.flag) {
                    _this.edit.flag = false;
                    _this.edit.text = '编辑';
                } else {
                    _this.edit.flag = true;
                    _this.edit.text = '查看';
                }
            }, currentModelInfo(model) {
                let _this = this;
                _this.currentModel = model;
                console.log(_this.currentModel)
            }, getJson() {
                let _this = this;
                $.ajax({
                    type: 'get',
                    url: '/bamboo/getJson',
                    dataType: "json",
                    data: {},
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success: function (data) {
                        _this.models = data.result.modules;
                    },
                    error: function (data) {
                    }
                });
            }, saveModel() {
                let _this = this;
                $.ajax({
                    type: "post",
                    url: '/bamboo/saveModel',
                    dataType: "json",
                    data: {name: _this.currentModel.name, description: _this.currentModel.description},
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success: function (data) {
                        _this.$message({
                            message: '成功',
                            type: 'success'
                        });
                        this.getJson();
                    },
                    error: function (data) {
                        _this.$message({
                            message: '保存失败',
                            type: 'warning'
                        });
                        this.getJson();
                    }
                });
            }
        }, mounted:
                function () {
                    this.getJson();
                }
    })
    ;
</script>
</@html>
