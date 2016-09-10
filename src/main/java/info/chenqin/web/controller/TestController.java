package info.chenqin.web.controller;

import info.chenqin.web.util.url.FtlPath;
import org.springframework.stereotype.Controller;
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
public class TestController
{
    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
    public String test()
    {
        return FtlPath.TEST_FTL_PATH;
    }
}
