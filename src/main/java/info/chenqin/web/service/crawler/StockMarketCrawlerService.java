package info.chenqin.web.service.crawler;

import info.chenqin.apirequest.BaseAPIRequestModel;
import info.chenqin.apiresponse.crawler.BloombergFinancialDataInfoApiResponse;
import info.chenqin.url.APIURL;
import info.chenqin.web.service.BaseService;
import org.springframework.stereotype.Service;

/**
 * User: nathanchen
 * <p>
 * Date: 28/9/16
 * <p>
 * Time: 8:38 PM
 * <p>
 * Description:
 */
@Service
public class StockMarketCrawlerService extends BaseService
{
    public BloombergFinancialDataInfoApiResponse getStockMarkerData(BaseAPIRequestModel baseAPIRequestModel)
    {
        return (BloombergFinancialDataInfoApiResponse)getForObject(baseAPIRequestModel, APIURL.API_STOCK_PRICE_URL, BloombergFinancialDataInfoApiResponse.class);
    }
}
