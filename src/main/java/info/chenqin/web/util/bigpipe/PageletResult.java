package info.chenqin.web.util.bigpipe;

import lombok.Data;

/**
 * User: nathanchen
 * <p>
 * Date: 29/9/16
 * <p>
 * Time: 10:34 AM
 * <p>
 * Description:
 */
@Data
public class PageletResult
{
    private boolean isFrameResult = false;
    private String viewName;

    private String container;
    private String[] cssUrls;
    private String[] jsUrls;

    public static PageletResult frameResult(String viewName)
    {
        PageletResult framePageletResult = new PageletResult();
        framePageletResult.viewName = viewName;
        framePageletResult.setContainer(viewName);
        framePageletResult.isFrameResult = true;
        return framePageletResult;
    }

    public static PageletResult frameResult(String viewName, String containerName)
    {
        PageletResult framePageletResult = new PageletResult();
        framePageletResult.viewName = viewName;
        framePageletResult.setContainer(containerName);
        framePageletResult.isFrameResult = true;
        return framePageletResult;
    }

    public static PageletResult pageletResult(String viewName)
    {
        PageletResult pageletResult = new PageletResult();
        pageletResult.viewName = viewName;
        pageletResult.setContainer(viewName);
        pageletResult.isFrameResult = false;
        return pageletResult;
    }

    public static PageletResult pageletResult(String viewName, String containerName)
    {
        PageletResult pageletResult = new PageletResult();
        pageletResult.viewName = viewName;
        pageletResult.setContainer(containerName);
        pageletResult.isFrameResult = false;
        return pageletResult;
    }
}
