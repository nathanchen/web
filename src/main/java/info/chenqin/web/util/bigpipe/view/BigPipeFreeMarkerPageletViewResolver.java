package info.chenqin.web.util.bigpipe.view;

import info.chenqin.web.util.bigpipe.PageletResult;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * User: nathanchen
 * <p>
 * Date: 29/9/16
 * <p>
 * Time: 10:57 AM
 * <p>
 * Description:
 */
@Data
public class BigPipeFreeMarkerPageletViewResolver implements ApplicationContextAware
{
    private ApplicationContext applicationContext;
    private String prefix = "";
    private String suffix = "";
    private String order = "";
    private String encoding;
    private String contentType;
    private String templateLoaderPath;
    private Class<?> pageletViewClass;
    private Class<?> framePageletViewClass;

    public BigPipeFreeMarkerPageletViewResolver()
    {
        this.pageletViewClass = defaultPageletViewClass();
        this.framePageletViewClass = defaultFramePageletViewClass();
    }

    private Class<?> defaultFramePageletViewClass()
    {
        return BigPipeFreeMarkerFramePageletView.class;
    }

    private Class<?> defaultPageletViewClass()
    {
        return BigPipeFreeMarkerPageletView.class;
    }

    public IPageletView resolve(final PageletResult pageletResult)
    {
        AbstractBigPipeFreeMarkerPageletView pageletView = null;
        if (pageletResult.isFrameResult())
        {
            pageletView = (AbstractBigPipeFreeMarkerPageletView) BeanUtils.instantiate(framePageletViewClass);
        }
        else
        {
            pageletView = (AbstractBigPipeFreeMarkerPageletView) BeanUtils.instantiate(pageletViewClass);
        }
        pageletView.setContextType(getContentType());
        pageletView.setEncoding(getEncoding());
        pageletView.setUrl(getPrefix() + pageletResult.getViewName() + getSuffix());
        pageletView.setPageletResult(pageletResult);
        pageletView.setApplicationContext(applicationContext);

        return pageletView;
    }
}
