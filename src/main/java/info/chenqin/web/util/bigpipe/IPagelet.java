package info.chenqin.web.util.bigpipe;

import info.chenqin.apirequest.BaseAPIRequestModel;

/**
 * User: nathanchen
 * <p>
 * Date: 29/9/16
 * <p>
 * Time: 10:33 AM
 * <p>
 * Description:
 */
public interface IPagelet
{
    // 各个模块传参modelMap
    PageletResult run(final BigPipeModelMap bigPipeModelMap, BaseAPIRequestModel baseAPIRequestModel);
}
