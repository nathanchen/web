package info.chenqin.web.util.interceptor;

import info.chenqin.util.network.WebCookiesUtil;
import info.chenqin.web.util.WebProjectPropertiesFileHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;

/**
 * User: nathanchen
 * <p>
 * Date: 23/9/16
 * <p>
 * Time: 12:39 PM
 * <p>
 * Description:
 */
@Slf4j
public class WebInterceptor extends HandlerInterceptorAdapter
{
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception
    {
        if (modelAndView != null)
        {
            request.setAttribute("__static_server__", WebProjectPropertiesFileHelper.getStaticServerDomain());
            request.setAttribute("__base_server__", WebProjectPropertiesFileHelper.getBaseServerDomain());

            // 随机数
            if (WebProjectPropertiesFileHelper.isStaticFilesForceToRefresh())
            {
                Random random = new Random();
                request.setAttribute("__version__", String.valueOf(random.nextInt(999999)));
            }
        }
        request.setAttribute("local_storage_version", WebCookiesUtil.getLocalStorageVersion());
    }
}
