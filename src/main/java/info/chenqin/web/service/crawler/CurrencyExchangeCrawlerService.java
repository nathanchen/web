package info.chenqin.web.service.crawler;

import info.chenqin.apirequest.BaseAPIRequestModel;
import info.chenqin.apiresponse.crawler.CurrencyExchangeRateCrawlerApiResponse;
import info.chenqin.url.APIURL;
import info.chenqin.web.service.BaseService;
import org.springframework.stereotype.Service;

/**
 * User: nathanchen
 * <p>
 * Date: 23/9/16
 * <p>
 * Time: 11:44 AM
 * <p>
 * Description:
 */
@Service
public class CurrencyExchangeCrawlerService extends BaseService
{
    public CurrencyExchangeRateCrawlerApiResponse getCurrencyExchangeRate(BaseAPIRequestModel baseAPIRequestModel)
    {
        return (CurrencyExchangeRateCrawlerApiResponse)getForObject(baseAPIRequestModel, APIURL.API_CURRENCY_EXCHANGE_RATE_URL, CurrencyExchangeRateCrawlerApiResponse.class);
    }
}
