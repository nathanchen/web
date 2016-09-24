package info.chenqin.web.service.crawler;

import info.chenqin.apirequest.BaseAPIRequestModel;
import info.chenqin.apiresponse.crawler.YahooWeatherAPIResponse;
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
public class WeatherCrawlerService extends BaseService
{
    public YahooWeatherAPIResponse getWeather(BaseAPIRequestModel baseAPIRequestModel)
    {
        return (YahooWeatherAPIResponse)getForObject(baseAPIRequestModel, APIURL.API_CRAWL_YAHOO_WEATHER_URL, YahooWeatherAPIResponse.class);
    }
}
