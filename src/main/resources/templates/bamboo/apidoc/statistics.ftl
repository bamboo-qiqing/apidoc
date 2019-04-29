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

    </@body>
 <script type="text/javascript" src='/echarts/echarts.min.js'></script>
<script>
    new Vue({
        el: "#app",
        data() {
            return {
                edit: {
                    flag: false,
                    text: "编辑"
                }
            }
        }, mounted: function () {
            var myChart = echarts.init(document.getElementById('chart'));
            myChart.showLoading();
            $.get('https://echarts.baidu.com/data/asset/data/flare.json', function (data) {
                myChart.hideLoading();

                echarts.util.each(data.children, function (datum, index) {
                    index % 2 === 0 && (datum.collapsed = true);
                });

                myChart.setOption(option = {
                    tooltip: {
                        trigger: 'item',
                        triggerOn: 'mousemove'
                    },
                    series: [
                        {
                            type: 'tree',

                            data: [data],

                            top: '1%',
                            left: '7%',
                            bottom: '1%',
                            right: '20%',

                            symbolSize: 7,

                            label: {
                                normal: {
                                    position: 'left',
                                    verticalAlign: 'middle',
                                    align: 'right',
                                    fontSize: 9
                                }
                            },

                            leaves: {
                                label: {
                                    normal: {
                                        position: 'right',
                                        verticalAlign: 'middle',
                                        align: 'left'
                                    }
                                }
                            },

                            expandAndCollapse: true,
                            animationDuration: 550,
                            animationDurationUpdate: 750
                        }
                    ]
                });
            });
        }

    })
    ;
</script>
</@html>
