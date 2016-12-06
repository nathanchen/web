<#--公用 - 作为HTML的footer，提供公用footer引用及JS插入-->
<#macro footer scripts=[]>

    <#list scripts as script>
    <script src="${__static_server__}/js/${script}?v=${__version__}"></script>
    </#list>
</body>
</html>
</#macro><!--底部start-->