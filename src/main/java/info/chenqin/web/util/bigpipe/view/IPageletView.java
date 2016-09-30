package info.chenqin.web.util.bigpipe.view;

import info.chenqin.web.util.bigpipe.BigPipeModelMap;

import javax.servlet.http.HttpServletResponse;

/**
 * User: nathanchen
 * <p>
 * Date: 29/9/16
 * <p>
 * Time: 11:01 AM
 * <p>
 * Description:
 */
public interface IPageletView
{
    void render(final BigPipeModelMap bigPipeModelMap, final HttpServletResponse response);
}
