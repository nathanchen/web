package info.chenqin.web.util.bigpipe.handler;

import info.chenqin.apirequest.BaseAPIRequestModel;
import info.chenqin.common.ApiVersionEnum;
import info.chenqin.web.util.bigpipe.*;
import info.chenqin.web.util.bigpipe.view.BigPipeFreeMarkerPageletViewResolver;
import info.chenqin.web.util.bigpipe.view.IPageletView;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncUtils;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * User: nathanchen
 * <p>
 * Date: 29/9/16
 * <p>
 * Time: 10:18 AM
 * <p>
 * Description:
 */
public class BigpipeTaskReturnValueHandler implements HandlerMethodReturnValueHandler, ApplicationContextAware, InitializingBean
{
    private ApplicationContext applicationContext;

    private Executor executor;

    private DefaultPageletFinder defaultPageletFinder;

    private BigPipeFreeMarkerPageletViewResolver bigPipeFreeMarkerPageletViewResolver;

    public void setExecutor(final Executor executor)
    {
        this.executor = executor;
    }

    public void setPageletFinder(final DefaultPageletFinder pageletFinder)
    {
        this.defaultPageletFinder = pageletFinder;
    }

    public void setPageletResolver(final BigPipeFreeMarkerPageletViewResolver pageletViewResolver)
    {
        this.bigPipeFreeMarkerPageletViewResolver = pageletViewResolver;
    }

    @Override
    public void afterPropertiesSet() throws Exception
    {
        if (executor == null)
        {
            executor = Executors.newScheduledThreadPool(10);
        }
        if (defaultPageletFinder == null)
        {
            defaultPageletFinder = applicationContext.getBean(DefaultPageletFinder.class);
        }
        if (bigPipeFreeMarkerPageletViewResolver == null)
        {
            bigPipeFreeMarkerPageletViewResolver = applicationContext.getBean(BigPipeFreeMarkerPageletViewResolver.class);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
    {
        this.applicationContext = applicationContext;
    }

    @Override
    public boolean supportsReturnType(MethodParameter returnType)
    {
        return BigPipeTask.class.isAssignableFrom(returnType.getParameterType());
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception
    {
        final BigPipeTask bigPipeTask = (BigPipeTask) returnValue;

        final HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        final HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);

        final DeferredResult<Void> deferredResult = new DeferredResult<Void>();
        mavContainer.setRequestHandled(true);
        WebAsyncUtils.getAsyncManager(request).startDeferredResultProcessing(deferredResult, mavContainer);

        // 整个页面（主模块+子模块）公有的modelMap
        final BigPipeModelMap pageGeneralModelMap = new BigPipeModelMap(request.getContextPath(), bigPipeTask.getModel());

        // 获取操控主模块pagelet的Java方法名称
        final String framePageletName = bigPipeTask.getFramePageletName();

        // 获取、加载操控主模块pagelet的Java方法
        final IPagelet framePagelet = defaultPageletFinder.find(framePageletName);

        BigPipeModelMap frameModelMap = pageGeneralModelMap.copy();

        final BaseAPIRequestModel baseAPIRequestModel = new BaseAPIRequestModel();
        baseAPIRequestModel.setApiVersion(ApiVersionEnum.V1.getCode());
        baseAPIRequestModel.setTimestamp(System.currentTimeMillis());

        // 将操控主模块pagelet的Java方法和对应的主模块ftl进行绑定
        // 看看主模块的modelMap有没有什么特别要加的model，这就是为什么之前要copy一下。这个是在公用的modelMap上新增一些只有主模块用到的，其他模块没有用到的model
        final PageletResult framePageletResult = framePagelet.run(frameModelMap, baseAPIRequestModel);

        // 将主模块对应的ftl实体文件的相对路径找到，赋给framePageletView
        final IPageletView framePageletView = bigPipeFreeMarkerPageletViewResolver.resolve(framePageletResult);

        // 将渲染过的主模块输出，但是输出流不关闭
        framePageletView.render(frameModelMap, response);

        final AtomicInteger counter = new AtomicInteger(bigPipeTask.getPageletNames().size());
        for (String otherPageletName : bigPipeTask.getPageletNames())
        {
            final IPagelet pagelet = defaultPageletFinder.find(otherPageletName);
            executor.execute(new Runnable() {
                @Override
                public void run()
                {
                    try
                    {
                        final BigPipeModelMap pageletModelMap = pageGeneralModelMap.copy();
                        final PageletResult pageletResult = pagelet.run(pageletModelMap, baseAPIRequestModel);
                        final IPageletView pageletView = bigPipeFreeMarkerPageletViewResolver.resolve(pageletResult);
                        pageletView.render(pageletModelMap, response);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    if (counter.decrementAndGet() <= 0)
                    {
                        deferredResult.setResult(null);
                    }
                }
            });
        }
    }
}
