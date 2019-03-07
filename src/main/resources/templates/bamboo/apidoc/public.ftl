<#macro  html>
 <!DOCTYPE html>
<html lang="en" style="position:absolute;width: 100%;height: 100%">
<#nested >
</html>
</#macro>
<#macro head >
<head>
    <meta charset="UTF-8">
    <title>API文档</title>
    <link rel="stylesheet" href="/element-ui/lib/theme-chalk/index.css">
    <script src="/jquery/jquery-3.3.1.min.js"></script>
    <link rel="stylesheet" href="/bootstrap/bootstrap-3.3.7/css/bootstrap.min.css">
    <script src="/vue/vue.min.js"></script>
    <script src="/element-ui/lib/index.js"></script>
    <script src="/axios/axios.min.js"></script>
</head>
</#macro>
<#macro  body main >
 <style>
     .el-header, .el-footer {
         text-align: center;
         line-height: 30px;
     }
 </style>
<body style="width: 100%;height: 100%">
<div id="app" style="width: 100%;height: 100%">
    <nav class="navbar navbar-default" style="margin-bottom:0px">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">Bamboo</a>
            </div>
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li><a href="/bamboo/apidoc">API文档</a></li>
                    <li><a href="/bamboo/project">统计图</a></li>
                    <li><a href="/bamboo/model">模块管理</a></li>
                    <li><a href="/bamboo/user">成员管理</a></li>
                    <li><a href="/bamboo/project">项目信息</a></li>
                </ul>
                <form class="navbar-form navbar-right">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Search">
                    </div>
                    <button type="button" class="btn btn-default" v-model="edit" @click="modeSwitching" >{{edit.text}}</button>
                    <button type="button" v-if="window.location.href.split('/')[window.location.href.split('/').length-1]=='model'" class="btn btn-default" @click="addModel" >新增</button>
                </form>
            </div>
        </div>
    </nav>
    <el-container>
       <#if  main ==true >
           <#nested >
       <#else>
        <el-main style="overflow:none!important;">
            <#nested >
        </el-main>
       </#if>
    </el-container>
</div>
</body>
</#macro>