package info.chenqin.web.controller;

import info.chenqin.web.util.url.FtlPath;
import info.chenqin.web.util.url.WebUrl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * User: nathanchen
 * <p>
 * Date: 28/9/16
 * <p>
 * Time: 9:51 PM
 * <p>
 * Description:
 */
@Controller
public class SystemController
{
    @RequestMapping(value = WebUrl.STATUS_PAGE_URL, method = RequestMethod.GET)
    public String getStatusPage()
    {
        return FtlPath.STATUS_FTL_NAME;
    }
}
