<#import "public.ftl" as pg />
<@pg.head />
<style>
    .el-footer {
        background-color: #B3C0D1;
        color: #333;
        text-align: center;
        line-height: 60px;
    }

    .el-aside {
        background-color: #D3DCE6;
        color: #333;
        text-align: center;
        line-height: 200px;
    }

    .el-main {
        background-color: #E9EEF3;
        color: #333;
        text-align: center;
        line-height: 160px;
    }

    body > .el-container {
        margin-bottom: 40px;
    }

    .el-container:nth-child(5) .el-aside,
    .el-container:nth-child(6) .el-aside {
        line-height: 260px;
    }

    .el-container:nth-child(7) .el-aside {
        line-height: 320px;
    }
</style>
<body>
<div id="app">
    <el-container>
  <@pg.navigation/>
        <el-header>
            <el-menu class="el-menu-demo" mode="horizontal">
                <el-menu-item index="1">处理中心</el-menu-item>
                <el-submenu index="2">
                    <template slot="title">我的工作台</template>
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
        <el-main>

        </el-main>
    </el-container>

</div>

</body>

<script>
    var vm = new Vue({
        el: "#app",
        data() {
            return {
                activeIndex: '1',
                activeIndex2: '1',
            };
        },
        methods: {
            handleSelect(key, keyPath) {
                console.log(key, keyPath);
            }
        }, mounted: function () {
            axios({
                method: 'get',
                url: '/bamboo/getJson',
                headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                responseType: 'json',
                transformResponse: [function (data) {
                    console.log(data)

                    return data;
                }]
            })
        }
    })
</script>
<@pg.footer/>
