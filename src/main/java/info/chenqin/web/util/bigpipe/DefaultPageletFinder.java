package info.chenqin.web.util.bigpipe;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * User: nathanchen
 * <p>
 * Date: 29/9/16
 * <p>
 * Time: 10:51 AM
 * <p>
 * Description:
 */
public class DefaultPageletFinder implements ApplicationContextAware
{
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException
    {
        this.applicationContext = applicationContext;
    }

    public IPagelet find(final String pageletName)
    {
        return this.applicationContext.getBean(pageletName, IPagelet.class);
    }
}
