<script type="text/javascript" src="${__static_server__}/js/localstorage.js?v=${__version__}"></script>
<#if local_storage_version != '' && local_storage_version != 'Nolsv'>
<script>
    var lsv = "";
    var name = "lsv=";
    var ca = document.cookie.split(";");
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == " ") {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            lsv = c.substring(name.length, c.length);
        }
    }
    if (lsv != whir.res.pageVersion || (window.localStorage && lsv != localStorage.getItem("version"))) {
        var d = new Date();
        d.setTime(d.getTime() + (365 * 24 * 60 * 60 * 1000));
        var expires = "expires=" + d.toUTCString();
        document.cookie = "lsv=;path=/;" + expires;
        location.reload();
    }
</script>
<#else>
<link href="${__static_server__}/css/thirdparty/bootstrap.min.css?v=2016" rel="stylesheet"/>
<script type="text/javascript" src="${__static_server__}/js/thirdparty/jquery-1.11.2.min.js?v=2016"></script>
<script type="text/javascript" src="${__static_server__}/js/thirdparty/bootstrap.min.js?v=2016"></script>
<script type="text/javascript" src="${__static_server__}/js/thirdparty/tether.min.js?v=2016"></script>
</#if>
<script>
    whir.res.loadCss("bootstrapcss", __STATIC_SERVER__ + "/css/thirdparty/bootstrap.min.css?v=2016");
    whir.res.loadJs("jquery", __STATIC_SERVER__ + "/js/thirdparty/jquery-1.11.2.min.js?v=2016");
    whir.res.loadJs("bootstrapjs", __STATIC_SERVER__ + "/js/thirdparty/bootstrap.min.js?v=2016");
    whir.res.loadJs("bootstrapjs", __STATIC_SERVER__ + "/js/thirdparty/tether.min.js?v=2016");
</script>