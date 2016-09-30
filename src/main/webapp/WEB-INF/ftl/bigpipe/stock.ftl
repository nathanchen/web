<div class="bs-component">
<#if model.stocks?? && model.stocks?size gt 0>
    <#list model.stocks as bean>
        <div class="panel <#if bean.changeValue?number lt 0>panel-success<#else>panel-danger</#if> financial-panel">
            <div class="panel-heading">
                <h3 class="panel-title">
                                    <span class="financial-key">
                                    ${bean.name}
                                    </span>
                    <span class="financial-value">
                    ${bean.currentValue}
                                    </span>
                </h3>
            </div>
        </div>
    </#list>
</#if>
</div>