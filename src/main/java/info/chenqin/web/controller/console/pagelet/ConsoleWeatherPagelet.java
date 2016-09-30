package info.chenqin.web.controller.console.pagelet;

import info.chenqin.apirequest.BaseAPIRequestModel;
import info.chenqin.web.service.crawler.WeatherCrawlerService;
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
@Controller(WebUrl.CONSOLE_WEATHER_PAGELET_URL)
public class ConsoleWeatherPagelet implements IPagelet
{
    @Autowired
    private WeatherCrawlerService weatherCrawlerService;

    @Override
    public PageletResult run(BigPipeModelMap bigPipeModelMap, BaseAPIRequestModel baseAPIRequestModel)
    {
        bigPipeModelMap.put("weatherList", weatherCrawlerService.getWeather(baseAPIRequestModel).getWeatherForecastDailyInfoModelList());
        return PageletResult.pageletResult("weather");
    }
}
