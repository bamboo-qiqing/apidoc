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
    <el-header style="text-align: center">
        <h2>API文档管理</h2>
    </el-header>
    <el-header style="text-align: center">
        <el-menu class="el-menu-demo" mode="horizontal">
            <el-menu-item index="1"><a href="/bamboo/apidoc">API文档</a></el-menu-item>
            <el-submenu index="2">
                <template slot="title">统计图</template>
                <el-menu-item index="2-1">选项1</el-menu-item>
                <el-menu-item index="2-2">选项2</el-menu-item>
                <el-menu-item index="2-3">选项3</el-menu-item>
                <el-submenu index="2-4">
                    <template slot="title">选项4</template>
                    <el-menu-item index="2-4-1">选项1</el-menu-item>
                    <el-menu-item index="2-4-2">选项2</el-menu-item>
                    <el-menu-item index="2-4-3">选项3</el-menu-item>
                </el-submenu>
            </el-submenu>
            <el-menu-item index="4"><a href="/bamboo/project">项目信息</a></el-menu-item>
        </el-menu>
    </el-header>
    <el-container>
       <#if  main ==true >
           <#nested >
       <#else>
        <el-main>
            <#nested >
        </el-main>
       </#if>
    </el-container>

</div>
</body>
</#macro>