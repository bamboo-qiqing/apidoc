<#import "public.ftl" as pg />
<@pg.head />
<body>
<el-container id="app">
<@pg.navigation/>
    <el-main>
        <el-row :gutter="20">
            <el-col :span="10" :offset="7">
                <div class="grid-content bg-purple">
                    <el-form ref="form" :model="project" label-width="80px">
                        <el-form-item label="项目名称">
                            <el-input v-model="project.name"></el-input>
                        </el-form-item>
                        <el-form-item label="API文档">
                            <el-switch v-model="project.apiFlag"></el-switch>
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
                            <el-input type="textarea" v-model="project.desc"></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="onSubmit">保存</el-button>
                            <el-button>取消</el-button>
                        </el-form-item>
                    </el-form>
                </div>
            </el-col>
        </el-row>

    </el-main>
</el-container>
</body>
<script>
    new Vue({
        el: "#app",
        data() {
            return {
                project:{
                    name:"",
                    startTime:"",
                    apiFlag:false,
                    desc:"",
                }
            }
        },
        methods: {
            handleSelect(key, keyPath) {
                console.log(key, keyPath);
            }
            ,

            onSubmit() {
                console.log('submit!');
            }

        }, mounted: function () {
            let _this=this;
            axios({
                method: 'get',
                url: '/bamboo/getJson',
                headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                responseType: 'json',
                transformResponse: [function (data) {
                    _this.project.name=data.name
                    _this.project.startTime=data.startTime
                    _this.project.desc=data.description
                    return data;
                }]
            })
        }
    })
</script>
<@pg.footer/>
