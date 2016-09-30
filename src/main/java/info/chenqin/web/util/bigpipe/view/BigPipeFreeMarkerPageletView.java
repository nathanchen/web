package info.chenqin.web.util.bigpipe.view;

import freemarker.template.TemplateException;
import info.chenqin.web.util.bigpipe.BigPipeModelMap;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * User: nathanchen
 * <p>
 * Date: 29/9/16
 * <p>
 * Time: 10:59 AM
 * <p>
 * Description:
 */
public class BigPipeFreeMarkerPageletView extends AbstractBigPipeFreeMarkerPageletView
{
    @Override
    public void render(BigPipeModelMap bigPipeModelMap, HttpServletResponse response)
    {
        Map cond = new HashMap();
        cond.put("model", bigPipeModelMap);

        setCommonGlobalVariables(cond);

        StringWriter sw = new StringWriter();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        try
        {
            getTemplate().process(cond, sw);
        }
        catch (TemplateException | IOException e1)
        {
            e1.printStackTrace();
        }
        StringBuilder buffer = new StringBuilder();

        buffer.append("<script>pl.write(");
        buffer.append("{");
        buffer.append("container:\"");
        buffer.append(getPageletResult().getContainer());
        buffer.append("\",");
        buffer.append("html:\"");
        buffer.append(sw.toString().replaceAll("\"", "\\\\\"").replaceAll("[\r\n]", ""));
        buffer.append("\",");
        buffer.append("css:");
        appendArray(buffer, bigPipeModelMap.getContextPath(), getPageletResult().getCssUrls());
        buffer.append(",");
        buffer.append("js:");
        appendArray(buffer, bigPipeModelMap.getContextPath(), getPageletResult().getJsUrls());
        buffer.append("}");
        buffer.append(");</script>");
        try
        {
            response.getWriter().write(buffer.toString());
            response.getWriter().flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void appendArray(StringBuilder buffer, String contextPath, String[] urls)
    {
        buffer.append("[");
        if (urls != null)
        {
            int index = 0;
            for (String url : urls)
            {
                if (index > 0)
                {
                    buffer.append(",");
                }
                buffer.append("\"");

                if (!url.startsWith("http:") && !url.startsWith("https:") && !url.startsWith(contextPath))
                {
                    url = contextPath + url;
                }
                buffer.append(url);
                buffer.append("\"");
                index++;
            }
        }
        buffer.append("]");
    }
}
