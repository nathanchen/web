package info.chenqin.web.controller.console.pagelet;

import info.chenqin.apirequest.BaseAPIRequestModel;
import info.chenqin.web.service.crawler.StockMarketCrawlerService;
import info.chenqin.web.util.bigpipe.IPagelet;
import info.chenqin.web.util.bigpipe.BigPipeModelMap;
import info.chenqin.web.util.bigpipe.PageletResult;
import info.chenqin.web.util.url.WebUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * User: nathanchen
 * <p>
 * Date: 29/9/16
 * <p>
 * Time: 10:41 AM
 * <p>
 * Description:
 */
@Controller(WebUrl.CONSOLE_STOCK_PAGELET_URL)
public class ConsoleStockPagelet implements IPagelet
{
    @Autowired
    private StockMarketCrawlerService stockMarketCrawlerService;

    @Override
    public PageletResult run(BigPipeModelMap bigPipeModelMap, BaseAPIRequestModel baseAPIRequestModel)
    {
        bigPipeModelMap.put("stocks", stockMarketCrawlerService.getStockMarkerData(baseAPIRequestModel).getBloombergFinancialDataInfoModelList());
        return PageletResult.pageletResult("stock");
    }
}
