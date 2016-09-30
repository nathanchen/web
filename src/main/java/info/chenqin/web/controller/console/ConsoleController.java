package info.chenqin.web.controller.console;

import info.chenqin.web.util.bigpipe.BigPipeTask;
import info.chenqin.web.util.url.WebUrl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * User: nathanchen
 * <p/>
 * Date: 10/09/2016
 * <p/>
 * Time: 8:45 PM
 * <p/>
 * Description:
 */
@Controller
public class ConsoleController
{
    @RequestMapping(value = WebUrl.CONSOLE_PAGE_URL, method = RequestMethod.GET)
    public Object consolePage(@ModelAttribute("model") ModelMap model)
    {
        return new BigPipeTask(model, WebUrl.CONSOLE_FRAME_PAGELET_URL, WebUrl.CONSOLE_OSCHINA_PAGELET_URL,
                WebUrl.CONSOLE_WEATHER_PAGELET_URL, WebUrl.CONSOLE_CURRENCY_PAGELET_URL, WebUrl.CONSOLE_STOCK_PAGELET_URL);
    }
}
