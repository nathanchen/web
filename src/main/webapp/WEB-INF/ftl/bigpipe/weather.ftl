<div class="bs-component">
    <table class="table table-striped table-hover" id="weather-table">
        <#if model.weatherList?? && model.weatherList?size gt 0>
            <#list model.weatherList as bean>
                <tr>
                    <td>${bean.date}</td>
                    <td>${bean.weather}</td>
                    <td>${bean.lowTemp} ~ ${bean.highTemp}</td>
                </tr>
            </#list>
        </#if>
    </table>
</div>