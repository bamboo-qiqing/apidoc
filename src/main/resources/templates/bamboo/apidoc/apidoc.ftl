<#include "public.ftl" >
<@html >
    <@head/>
<style>
    .el-menu {
        margin: 1px;
    }


</style>
    <@body false>
     <el-row :gutter="24">
         <el-col :span="2">
             <div class="grid-content bg-purple">
                 <el-aside>
                     <el-menu
                             default-active="2"
                             class="el-menu-vertical-demo"
                             @open="handleOpen"
                             @close="handleClose">
                         <el-submenu v-for="(apidoc, index) in apidocs" :index="index">
                             <template slot="title">
                                 <span>{{apidoc.name}}</span>
                             </template>
                             <el-menu-item-group v-for="(methodInfo, index) in apidoc.methodInfos">
                                 <el-menu-item :index="methodInfo.method.routPaths" @click="currentApiInfo(methodInfo)">
                                     <template v-for="url in methodInfo.method.routPaths">
                                         {{url}}
                                     </template>
                                 </el-menu-item>
                             </el-menu-item-group>
                         </el-submenu>
                     </el-menu>
                 </el-aside>
             </div>
         </el-col>
         <el-col :span="18" :offset="2">
             <el-row :gutter="20">

                 <el-col :span="14" :offset="6">
                 <#--<el-header>-->
                 <#--<el-input-->
                 <#--placeholder="请输入接口名称"-->
                 <#--v-model="currentApi.method.chineseName">-->
                 <#--</el-input>-->
                 <#--</el-header>-->
                     <el-main>
                         <div class="grid-content bg-purple">
                             <el-form ref="currentApi" :model="currentApi" label-width="80px" v-if="currentApi!=null">
                                 <el-form-item label="接口名称:">
                                     <el-input
                                             placeholder="请输入接口名称"
                                             v-model="currentApi.method.chineseName">
                                     </el-input>
                                 </el-form-item>
                                 <el-form-item label="接口地址:">
                                     <template v-for=" url in currentApi.method.routPaths">
                                         <el-tag type="success">{{url}}</el-tag>
                                     </template>
                                 </el-form-item>
                                 <el-form-item label="请求类型:">
                                     <template v-for=" type in currentApi.method.methodTypes">
                                         <el-tag>{{type}}</el-tag>
                                     </template>
                                 </el-form-item>
                                 <el-form-item label="接口说明:">
                                     <el-input
                                             type="textarea"
                                             :rows="2"
                                             placeholder="接口描述"
                                             v-model="currentApi.method.description">
                                     </el-input>
                                 </el-form-item>
                                 <el-form-item label="接口说明:">
                                     <div class="table-responsive">
                                         <table class="table table-hover table-bordered">
                                             <tr>
                                                 <th>参数名</th>
                                                 <th>参数类型</th>
                                                 <th>必选</th>
                                             </tr>
                                             <tr v-for="param  in currentApi.method.params">
                                                 <td>{{param.name}}</td>
                                                 <td>{{param.type}}</td>
                                                 <td v-if="param.isNull">否</td>
                                                 <td v-else>是</td>
                                             </tr>
                                         </table>
                                     </div>

                                 </el-form-item>
                             </el-form>


                         </div>
                     </el-main>
                 </el-col>

             </el-row>
         </el-col>
     </el-row>
    </@body>

<script>
    new Vue({
        el: "#app",
        data() {
            return {
                apidocs: [],
                currentApi: null
            }
        },
        methods: {
            handleOpen(key, keyPath) {
                console.log(key, keyPath);
            },
            handleClose(key, keyPath) {
                console.log(key, keyPath);
            }, currentApiInfo(apiInfo) {
                console.log(apiInfo);
                this.currentApi = apiInfo;

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
