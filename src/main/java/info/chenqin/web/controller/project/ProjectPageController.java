package info.chenqin.web.controller.project;

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
        return "project/2016";
    }
}
