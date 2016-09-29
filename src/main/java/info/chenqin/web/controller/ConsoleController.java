package info.chenqin.web.controller;

import info.chenqin.apirequest.BaseAPIRequestModel;
import info.chenqin.apiresponse.crawler.BloombergFinancialDataInfoApiResponse;
import info.chenqin.apiresponse.crawler.OSChinaIndexPageCrawlerApiResponse;
import info.chenqin.apiresponse.crawler.YahooWeatherAPIResponse;
import info.chenqin.common.ApiVersionEnum;
import info.chenqin.web.service.crawler.CurrencyExchangeCrawlerService;
import info.chenqin.web.service.crawler.OSChinaIndexPageCrawlerService;
import info.chenqin.web.service.crawler.StockMarketCrawlerService;
import info.chenqin.web.service.crawler.WeatherCrawlerService;
import info.chenqin.web.util.url.FtlPath;
import info.chenqin.web.util.url.WebUrl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * User: nathanchen
 * <p/>
 * Date: 10/09/2016
 * <p/>
 * Time: 8:45 PM
 * <p/>
 * Description:
 */
@Slf4j
@Controller
public class ConsoleController extends BaseController
{
    @Autowired
    OSChinaIndexPageCrawlerService osChinaIndexPageCrawlerService;

    @Autowired
    WeatherCrawlerService weatherCrawlerService;

    @Autowired
    CurrencyExchangeCrawlerService currencyExchangeCrawlerService;

    @Autowired
    StockMarketCrawlerService stockMarketCrawlerService;

    @RequestMapping(value = WebUrl.CONSOLE_PAGE_URL, method = RequestMethod.GET)
    public String consolePage(@ModelAttribute("model") ModelMap model)
    {
        BaseAPIRequestModel baseAPIRequestModel = new BaseAPIRequestModel();
        baseAPIRequestModel.setApiVersion(ApiVersionEnum.V1.getCode());
        baseAPIRequestModel.setTimestamp(System.currentTimeMillis());

        Future<OSChinaIndexPageCrawlerApiResponse> osChinaIndexPageCrawlerApiResponseFuture = getOSChinaIndexPageNews(baseAPIRequestModel);
        Future<YahooWeatherAPIResponse> yahooWeatherAPIResponseFuture = getWeather(baseAPIRequestModel);
        Future<BloombergFinancialDataInfoApiResponse> currencyExchangeRateCrawlerApiResponseFuture = getCurrency(baseAPIRequestModel);
        Future<BloombergFinancialDataInfoApiResponse> stockMarketCrawlerApiResponseFuture = getStockMarket(baseAPIRequestModel);

        try
        {
            OSChinaIndexPageCrawlerApiResponse osChinaIndexPageCrawlerApiResponse = osChinaIndexPageCrawlerApiResponseFuture.get();
            YahooWeatherAPIResponse yahooWeatherAPIResponse = yahooWeatherAPIResponseFuture.get();
            BloombergFinancialDataInfoApiResponse currencyExchangeRateCrawlerApiResponse = currencyExchangeRateCrawlerApiResponseFuture.get();
            BloombergFinancialDataInfoApiResponse stockMarketCrawlerApiResponse = stockMarketCrawlerApiResponseFuture.get();

            if (osChinaIndexPageCrawlerApiResponse.getCode() == 0)
            {
                model.put("newsList", osChinaIndexPageCrawlerApiResponse.getOsChinaIndexPageNewsModels());
            }
            if (yahooWeatherAPIResponse.getCode() == 0)
            {
                model.put("weatherList", yahooWeatherAPIResponse.getWeatherForecastDailyInfoModelList());
            }
            if (currencyExchangeRateCrawlerApiResponse.getCode() == 0)
            {
                model.put("currencyX", currencyExchangeRateCrawlerApiResponse.getBloombergFinancialDataInfoModelList());
            }
            if (stockMarketCrawlerApiResponse.getCode() == 0)
            {
                model.put("stocks", stockMarketCrawlerApiResponse.getBloombergFinancialDataInfoModelList());
            }
        }
        catch (InterruptedException | ExecutionException e)
        {
            e.printStackTrace();
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd E");
        model.put("date", simpleDateFormat.format(new Date()));

        return FtlPath.CONSOLE_FTL_NAME;
    }

    private Future<YahooWeatherAPIResponse> getWeather(final BaseAPIRequestModel baseAPIRequestModel)
    {
        return threadPoolExecutor.submit(new Callable<YahooWeatherAPIResponse>() {
            public YahooWeatherAPIResponse call() throws Exception
            {
                return weatherCrawlerService.getWeather(baseAPIRequestModel);
            }
        });
    }

    private Future<BloombergFinancialDataInfoApiResponse> getCurrency(final BaseAPIRequestModel baseAPIRequestModel)
    {
        return threadPoolExecutor.submit(new Callable<BloombergFinancialDataInfoApiResponse>() {
            public BloombergFinancialDataInfoApiResponse call() throws Exception
            {
                return currencyExchangeCrawlerService.getCurrencyExchangeRate(baseAPIRequestModel);
            }
        });
    }

    private Future<BloombergFinancialDataInfoApiResponse> getStockMarket(final BaseAPIRequestModel baseAPIRequestModel)
    {
        return threadPoolExecutor.submit(new Callable<BloombergFinancialDataInfoApiResponse>() {
            public BloombergFinancialDataInfoApiResponse call() throws Exception
            {
                return stockMarketCrawlerService.getStockMarkerData(baseAPIRequestModel);
            }
        });
    }

    private Future<OSChinaIndexPageCrawlerApiResponse> getOSChinaIndexPageNews(final BaseAPIRequestModel baseAPIRequestModel)
    {
        return threadPoolExecutor.submit(new Callable<OSChinaIndexPageCrawlerApiResponse>() {
            public OSChinaIndexPageCrawlerApiResponse call() throws Exception
            {
                return osChinaIndexPageCrawlerService.getOSChinaIndexPage(baseAPIRequestModel);
            }
        });
    }
}
