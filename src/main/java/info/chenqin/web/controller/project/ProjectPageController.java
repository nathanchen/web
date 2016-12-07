package info.chenqin.web.controller.project;

import info.chenqin.web.util.url.FtlPath;
import info.chenqin.web.util.url.WebUrl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * User: nathanchen
 * <p>
 * Date: 6/12/16
 * <p>
 * Time: 8:44 AM
 * <p>
 * Description:
 */
@Controller
public class ProjectPageController
{
    @RequestMapping(value = {WebUrl.INDEX_PAGE_URL, WebUrl.DEFAULT_PAGE_URL,
            WebUrl.PROJECT_HOME_PAGE_URL, WebUrl.PROJECT2016_PAGE_URL}, method = RequestMethod.GET)
    public String getProjectHomePage(@ModelAttribute("model") ModelMap modelMap)
    {
        modelMap.put("title", "2016项目");
        return FtlPath.PROJECT2016_FTL_NAME;
    }

    @RequestMapping(value = {WebUrl.PROJECT2015_PAGE_URL}, method = RequestMethod.GET)
    public String getProject2015Page(@ModelAttribute("model") ModelMap modelMap)
    {
        modelMap.put("title", "2015项目");
        return FtlPath.PROJECT2015_FTL_NAME;
    }

    @RequestMapping(value = {WebUrl.PROJECT2014_PAGE_URL}, method = RequestMethod.GET)
    public String getProject2014Page(@ModelAttribute("model") ModelMap modelMap)
    {
        modelMap.put("title", "2014项目");
        return FtlPath.PROJECT2014_FTL_NAME;
    }

    @RequestMapping(value = {WebUrl.PROJECT2013_PAGE_URL}, method = RequestMethod.GET)
    public String getProject2013Page(@ModelAttribute("model") ModelMap modelMap)
    {
        modelMap.put("title", "2013项目");
        return FtlPath.PROJECT2013_FTL_NAME;
    }
}
