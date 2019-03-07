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
                             class="el-menu-vertical-demo"
                             @open="handleOpen"
                             @close="handleClose">
                        <el-submenu v-for="(apidoc, index) in apidocs" :index="index">
                            <template slot="title">
                                <span>{{apidoc.name}}</span>
                            </template>
                            <el-menu-item-group v-for="(method, index) in apidoc.methods">
                                <el-menu-item :index="method.methodInfo.methodBasic.routPaths"
                                              @click="currentApiInfo(method,apidoc.name)">
                                    <template v-for="url in method.methodInfo.methodBasic.routPaths">
                                        {{url}}
                                    </template>
                                </el-menu-item>
                            </el-menu-item-group>
                        </el-submenu>
                    </el-menu>
                </el-aside>
            </div>
        </el-col>
        <el-col :span="18" :offset="2" v-if="currentApi!=null">
            <el-main>
                <div class="grid-content bg-purple">
                    <el-form ref="currentApi" :model="currentApi" label-width="80px">
                        <el-row :gutter="24">
                            <el-col :span="8">
                                <el-form-item label="接口名称:">
                                    <el-input v-if="edit.flag"
                                              placeholder="请输入接口名称"
                                              v-model="currentApi.methodInfo.methodBasic.chineseName">
                                    </el-input>
                                    <el-input v-else readonly="true" class="read-only"
                                              placeholder="请输入接口名称"
                                              v-model="currentApi.methodInfo.methodBasic.chineseName">
                                    </el-input>
                                </el-form-item>
                            </el-col>
                        </el-row>

                        <el-form-item label="接口地址:">
                            <template v-for=" url in currentApi.methodInfo.methodBasic.routPaths">
                                <el-tag type="success">{{url}}</el-tag>
                            </template>
                        </el-form-item>
                        <el-form-item label="请求类型:">
                            <template v-for=" type in currentApi.methodInfo.methodBasic.methodTypes">
                                <el-tag>{{type}}</el-tag>
                            </template>
                        </el-form-item>
                        <el-form-item label="所在模块:">
                            <el-select v-if="edit.flag" v-model="currentModel" placeholder="请选择">
                                <el-option
                                        v-for="item in apidocs"
                                        :key="item.name"
                                        :label="item.name"
                                        :value="item.name">
                                </el-option>
                            </el-select>
                            <el-input v-else readonly="true" class="read-only"
                                      placeholder="请输入接口名称"
                                      v-model="currentModel">
                            </el-input>
                        </el-form-item>

                        <el-form-item label="接口说明:">
                            <div class="table-responsive">
                                <table class="table table-hover table-bordered">
                                    <tr>
                                        <th>参数名</th>
                                        <th>参数类型</th>
                                        <th>必选</th>
                                        <th>说明</th>
                                    </tr>
                                    <tr v-for="param  in currentApi.methodInfo.methodBasic.params">
                                        <td>{{param.name}}</td>
                                        <td>{{param.type}}</td>
                                        <td v-if="param.isNull">否</td>
                                        <td v-else>是</td>
                                        <td v-if="edit.flag">
                                            <el-input v-model="param.description">
                                            </el-input>
                                        </td>
                                        <td v-else>{{param.description}}</td>
                                    </tr>
                                </table>
                            </div>
                        </el-form-item>
                        <el-form-item label="接口说明:">
                            <el-input v-if="edit.flag"
                                      type="textarea"
                                      rows="2"
                                      v-model="currentApi.methodInfo.methodBasic.description">
                            </el-input>
                            <el-input v-else readonly="true" class="read-only"
                                      type="text"
                                      rows="2"
                                      v-model="currentApi.methodInfo.methodBasic.description">
                            </el-input>
                        </el-form-item>
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
                apidocs: [],
                currentApi: null,
                currentModel: '',
                edit: {
                    flag: false,
                    text: "编辑"
                },
                editCloseInput: 'editCloseInput'
            }
        },
        methods: {
            handleOpen(key, keyPath) {
                console.log(key, keyPath);
            }, modeSwitching() {
                let _this = this;
                if (_this.edit.flag) {
                    _this.edit.flag = false;
                    _this.edit.text = '编辑';
                } else {
                    _this.edit.flag = true;
                    _this.edit.text = '查看';
                }
            },
            handleClose(key, keyPath) {
                console.log(key, keyPath);
            }, currentApiInfo(apiInfo, modelName) {
                let _this = this;
                _this.currentApi = apiInfo;
                console.log(modelName)
                _this.currentModel = modelName;
            }, getJson() {
                let _this = this;
                axios({
                    method: 'get',
                    url: '/bamboo/getJson',
                    headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                    responseType: 'json',
                    transformResponse: [function (data) {
                        _this.apidocs = data.result.modules;
                        console.log(data);
                        return data;
                    }]
                })
            }
        }, mounted: function () {
            this.getJson();
        }
    });
</script>
</@html>
