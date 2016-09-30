package info.chenqin.web.util.bigpipe.view;

import freemarker.template.Configuration;
import freemarker.template.Template;
import info.chenqin.web.util.WebProjectPropertiesFileHelper;
import info.chenqin.web.util.bigpipe.PageletResult;
import org.springframework.context.ApplicationContext;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Random;

/**
 * User: nathanchen
 * <p>
 * Date: 29/9/16
 * <p>
 * Time: 11:02 AM
 * <p>
 * Description:
 */
public abstract class AbstractBigPipeFreeMarkerPageletView implements IPageletView
{
    private ApplicationContext applicationContext;
    private String contextType;
    private String url;
    private String encoding;
    private Template template;
    private PageletResult pageletResult;
    private Configuration cfg;
    private String classPath;

    private static final String STATIC_SERVER = WebProjectPropertiesFileHelper.getStaticServerDomain();
    private static final String BASE_SERVER = WebProjectPropertiesFileHelper.getBaseServerDomain();
    private static String rnd = "2016";

    public ApplicationContext getApplicationContext()
    {
        return applicationContext;
    }

    public void setApplicationContext(ApplicationContext applicationContext)
    {
        this.applicationContext = applicationContext;
    }

    public String getContextType()
    {
        return contextType;
    }

    public void setContextType(String contextType)
    {
        this.contextType = contextType;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getEncoding()
    {
        return encoding;
    }

    public void setEncoding(String encoding)
    {
        this.encoding = encoding;
    }

    public void setTemplate(Template template)
    {
        this.template = template;
    }

    public Configuration getCfg()
    {
        return cfg;
    }

    public void setCfg(Configuration cfg)
    {
        this.cfg = cfg;
    }

    public String getClassPath()
    {
        return classPath;
    }

    public void setClassPath(String classPath)
    {
        this.classPath = this.getClass().getResource("").getPath();
    }

    public PageletResult getPageletResult()
    {
        return pageletResult;
    }

    public void setPageletResult(final PageletResult pageletResult)
    {
        this.pageletResult = pageletResult;
    }

    protected Template getTemplate() throws IOException
    {
        if (template != null)
        {
            return template;
        }
        return getTemplate(getUrl());
    }

    // 获取ftl模板
    protected Template getTemplate(String url) throws IOException
    {
        cfg = new Configuration();
        String baseUrl = System.getProperty("info.web");
        cfg.setDirectoryForTemplateLoading(new File(baseUrl.substring(0, baseUrl.length() - 1) + "/WEB-INF/ftl"));
        cfg.setDefaultEncoding("UTF-8");
        if (getEncoding() != null)
        {
            return cfg.getTemplate(url);
        }
        return cfg.getTemplate(url, getEncoding());
    }

    protected Map setCommonGlobalVariables(Map cond)
    {
        // 随机数
        if (WebProjectPropertiesFileHelper.isStaticFilesForceToRefresh())
        {
            Random random = new Random();
            rnd = String.valueOf(random.nextInt(999999));
        }
        cond.put("__version__", rnd);
        cond.put("__static_server__", STATIC_SERVER);
        cond.put("__base_server__", BASE_SERVER);
        return cond;
    }
}
