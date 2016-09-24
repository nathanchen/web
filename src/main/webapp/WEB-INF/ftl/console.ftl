<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Nathan CHEN</title>
    <link href="${__static_server__}/css/thirdparty/bootstrap.css" rel="stylesheet"/>
    <link href="${__static_server__}/css/index.css" rel="stylesheet"/>
</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Console</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Console</a></li>
            </ul>
            <ul class="navbar-text navbar-right" id="nav-ul">
                <span>${model.date}</span>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>
<div class="container">
    <div class="bs-docs-section">
        <div class="row">
            <div class="col-md-5">
                <div class="page-header">
                    <h3>开源中国</h3>
                </div>
                <div class="bs-component">
                    <div class="list-group">
                        <#list model.newsList as news>
                            <a class="list-group-item" href="${news.url}" title="${news.title}">
                                ${news.title}
                            </a>
                        </#list>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="page-header">
                    <h3>天气</h3>
                </div>
                <div class="bs-component">
                    <table class="table table-striped table-hover" id="weather-table">
                        <#list model.weatherList as bean>
                            <tr>
                                <td>${bean.date}</td>
                                <td>${bean.weather}</td>
                                <td>${bean.lowTemp} ~ ${bean.highTemp}</td>
                            </tr>
                        </#list>
                    </table>
                </div>
            </div>
            <div class="col-md-3">
                <div class="page-header">
                    <h3>汇率</h3>
                </div>
                <div class="bs-component">
                    <div class="panel <#if model.currencyX.changeValue lt 0>panel-success<#else>panel-danger</#if>">
                        <div class="panel-heading">
                            <h3 class="panel-title">${model.currencyX.currencyExchangeRate}</h3>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="${__static_server__}/js/thirdparty/jquery-1.11.2.min.js"></script>
<script src="${__static_server__}/js/thirdparty/bootstrap.js"></script>
</body>
</html>