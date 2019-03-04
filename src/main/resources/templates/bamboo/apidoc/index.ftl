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
            };
        },
        methods: {
            handleSelect(key, keyPath) {
                console.log(key, keyPath);
            }
        }, mounted: function () {
        <@axios />

        }
    })
</script>
</@html>
