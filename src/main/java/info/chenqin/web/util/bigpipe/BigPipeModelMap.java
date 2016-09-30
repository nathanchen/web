package info.chenqin.web.util.bigpipe;

import java.util.HashMap;
import java.util.Map;

/**
 * User: nathanchen
 * <p>
 * Date: 29/9/16
 * <p>
 * Time: 10:23 AM
 * <p>
 * Description:
 */
public class BigPipeModelMap extends HashMap
{
    private final String contextPath;

    public BigPipeModelMap(final String contextPath, final Map model)
    {
        this.contextPath = contextPath;
        put("contextPath", contextPath);

        if (model != null)
        {
            this.putAll(model);
        }
    }

    public String getContextPath()
    {
        return contextPath;
    }

    public BigPipeModelMap copy()
    {
        BigPipeModelMap bigPipeModelMap = new BigPipeModelMap(this.contextPath, null);
        bigPipeModelMap.putAll(this);
        return bigPipeModelMap;
    }
}
