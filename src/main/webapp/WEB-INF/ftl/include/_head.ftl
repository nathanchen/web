<#macro head title="" styles=[] jsvars=[]>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="format-detection" content="telephone=no, email=no"/>
    <title>
        <#if title?? && title!=''>
        ${title}-
        </#if>
        NathanCHEN
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta name="description" content="Nathan CHEN的个人网站">
    <meta name="keywords" content="个人网站,Nathan CHEN,java工程师,Nathan CHEN的个人网站,简历,qin chen, chen qin">
    <link rel="shortcut icon" type="image/x-icon" href="${__static_server__}/image/favicon.ico"/>
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="${__static_server__}/image/apple-touch-icon.png">
    <script type="text/javascript">
        var __STATIC_SERVER__ = "${__static_server__}";
        var __BASE_SERVER__ = "${__base_server__}";
    </script>
    <#include "/include/localstorage.ftl" />
    <#list jsvars as jsvar>
        var ${jsvar[0]} = "${jsvar[1]}";
    </#list>
    <#list styles as style>
        <link href="${__static_server__}/css/${style}?v=${__version__}" rel="stylesheet"/>
    </#list>
    <#nested>
</head>
<body>
</#macro>