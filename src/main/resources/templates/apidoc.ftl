<#include "public.ftl" >
<@html >
    <@head/>
<style>
    .el-menu {
        margin: 1px;
    }
</style>
    <@body true>
     <el-row :gutter="24">
         <el-col :span="2">
             <div class="grid-content bg-purple">
                 <el-aside>
                     <el-menu
                             default-active="2"
                             class="el-menu-vertical-demo"
                             @open="handleOpen"
                             @close="handleClose">
                         <el-submenu  v-for=" apidoc in apidocs" index="1">
                             <template slot="title">
                                 <span>{{apidoc.name}}</span>
                             </template>
                             <el-menu-item-group v-for="method in apidoc.methods"  >
                                 <el-menu-item index="1-3">{{method.name}}</el-menu-item>
                             </el-menu-item-group>
                         </el-submenu>

                     </el-menu>
                 </el-aside>
             </div>
         </el-col>
         <el-col :span="18">
             <div class="grid-content bg-purple"></div>
         </el-col>

     </el-row>
    </@body>
<script>
    var vm = new Vue({
        el: "#app",
        data() {
           return{
               apidocs:[]
           }
        },
        methods: {
            handleSelect(key, keyPath) {
                console.log(key, keyPath);
            },
            handleOpen(key, keyPath) {
                console.log(key, keyPath);
            },
            handleClose(key, keyPath) {
                console.log(key, keyPath);
            }
        }, mounted: function () {
            let _this=this;
            axios({
                method: 'get',
                url: '/bamboo/getJson',
                headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                responseType: 'json',
                transformResponse: [function (data) {
                    _this.apidocs=data.result.modules;
                    return data;
                }]
            })
        }
    })
</script>
</@html>
