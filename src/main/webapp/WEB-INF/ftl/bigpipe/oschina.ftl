<div class="bs-component">
    <div class="list-group">
        <#if model.newsList?? && model.newsList?size gt 0>
            <#list model.newsList as news>
                <a class="list-group-item" href="${news.url}" title="${news.title}" target="_blank">
                ${news.title}
                </a>
            </#list>
        </#if>
    </div>
</div>