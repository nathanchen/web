package info.chenqin.web.util.bigpipe.view;

import freemarker.template.TemplateException;
import info.chenqin.web.util.bigpipe.BigPipeModelMap;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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
public class BigPipeFreeMarkerFramePageletView extends AbstractBigPipeFreeMarkerPageletView
{
    @Override
    public void render(BigPipeModelMap bigPipeModelMap, HttpServletResponse response)
    {
        if (getContextType() != null)
        {
            response.setContentType(getContextType());
        }
        try
        {
            Map cond = new HashMap<>();
            cond.put("model", bigPipeModelMap);

            setCommonGlobalVariables(cond);

            PrintWriter out = response.getWriter();
            // 拿到ftl模块，然后将context里面的值附到ftl里，再通过out输出
            getTemplate().process(cond, out);
            out.flush();
        }
        catch (IOException | TemplateException e)
        {
            e.printStackTrace();
        }
    }
}
