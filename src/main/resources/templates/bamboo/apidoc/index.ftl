<#include "public.ftl" >
<@html >
    <@head/>

    <@body false>

    </@body>
<script>
    var vm = new Vue({
        el: "#app",
        data() {
            return {
                activeIndex: '1',
                activeIndex2: '1',
                edit: {
                    flag: false,
                    text: "编辑"
                }
            };
        },
        methods: {
            handleSelect(key, keyPath) {
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
            }
        }, mounted: function () {
            Object.assign(obj1, Name, Age);
        }
    })
</script>
</@html>
