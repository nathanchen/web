package info.chenqin.web.service.crawler;

import info.chenqin.apirequest.BaseAPIRequestModel;
import info.chenqin.apiresponse.crawler.OSChinaIndexPageCrawlerApiResponse;
import info.chenqin.url.APIURL;
import info.chenqin.web.service.BaseService;
import org.springframework.stereotype.Service;

/**
 * User: nathanchen
 * <p/>
 * Date: 20/09/2016
 * <p/>
 * Time: 9:35 PM
 * <p/>
 * Description:
 */
@Service
public class OSChinaIndexPageCrawlerService extends BaseService
{
    public OSChinaIndexPageCrawlerApiResponse getOSChinaIndexPage(BaseAPIRequestModel baseAPIRequestModel)
    {
        return (OSChinaIndexPageCrawlerApiResponse)getForObject(baseAPIRequestModel, APIURL.API_CRAWL_OSCHINA_INDEX_PAGE_URL, OSChinaIndexPageCrawlerApiResponse.class);
    }
}
