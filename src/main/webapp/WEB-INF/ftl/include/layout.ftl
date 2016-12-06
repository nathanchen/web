<#import "/include/_head.ftl" as head />
<#import "/include/_footer.ftl" as footer />

<#macro my_project title scripts=[] styles=[]>
    <@head.head title=title styles=styles + ["project.css"]></@head.head>
    <div class="container">
        <div id="project-content" class="row">
            <#include "/include/_nav.ftl" />
        <#include "/include/_sidebar.ftl" />
        <#nested>
        </div>
    </div>
    <@footer.footer scripts=scripts></@footer.footer>
</#macro>