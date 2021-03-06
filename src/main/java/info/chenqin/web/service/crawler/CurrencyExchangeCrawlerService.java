package info.chenqin.web.service.crawler;

import info.chenqin.apirequest.BaseAPIRequestModel;
import info.chenqin.apiresponse.crawler.BloombergFinancialDataInfoApiResponse;
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
    public BloombergFinancialDataInfoApiResponse getCurrencyExchangeRate(BaseAPIRequestModel baseAPIRequestModel)
    {
        return (BloombergFinancialDataInfoApiResponse)getForObject(baseAPIRequestModel, APIURL.API_CURRENCY_EXCHANGE_RATE_URL, BloombergFinancialDataInfoApiResponse.class);
    }
}
