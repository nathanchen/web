package info.chenqin.web.controller.console.pagelet;

import info.chenqin.apirequest.BaseAPIRequestModel;
import info.chenqin.web.util.bigpipe.IPagelet;
import info.chenqin.web.util.bigpipe.BigPipeModelMap;
import info.chenqin.web.util.bigpipe.PageletResult;
import info.chenqin.web.util.url.WebUrl;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User: nathanchen
 * <p>
 * Date: 29/9/16
 * <p>
 * Time: 10:37 AM
 * <p>
 * Description:
 */
@Controller(WebUrl.CONSOLE_FRAME_PAGELET_URL)
public class ConsoleFramePagelet implements IPagelet
{
    @Override
    public PageletResult run(BigPipeModelMap bigPipeModelMap, BaseAPIRequestModel baseAPIRequestModel)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd E");
        bigPipeModelMap.put("date", simpleDateFormat.format(new Date()));

        return PageletResult.frameResult("console");
    }
}
