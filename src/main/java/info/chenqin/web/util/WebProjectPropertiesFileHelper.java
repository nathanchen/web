package info.chenqin.web.util;

import info.chenqin.util.PropertiesFileHelper;

import java.util.Properties;

/**
 * User: nathanchen
 * <p/>
 * Date: 21/09/2016
 * <p/>
 * Time: 1:54 AM
 * <p/>
 * Description:
 */
public class WebProjectPropertiesFileHelper extends PropertiesFileHelper
{
    private static Properties webProjectProperties;
    private static final String WEB_PROJECT_PROPERTIES_FILE_PATH = "conf/web-project-config.properties";

    static {
        webProjectProperties = initProperties(webProjectProperties, WEB_PROJECT_PROPERTIES_FILE_PATH);
    }

    public static boolean isRealEnvironment()
    {
        return webProjectProperties.getProperty("is_real").equals("Y");
    }

    public static String getAPIServerDomain()
    {
        return getWebAttributeValue("api.server.domain");
    }

    public static String getStaticServerDomain()
    {
        return getWebAttributeValue("static.server.domain");
    }

    public static String getBaseServerDomain()
    {
        return getWebAttributeValue("base.server.domain");
    }

    public static boolean isStaticFilesForceToRefresh()
    {
        return "Y".equals(getWebAttributeValue("static_file_force_to_refresh"));
    }

    private static String getWebAttributeValueByEnvironment(String attributeKey)
    {
        return isRealEnvironment() ? getWebAttributeValue(attributeKey) : getWebAttributeValue(attributeKey + ".test");
    }

    private static String getWebAttributeValue(String attributeKey)
    {
        return webProjectProperties.getProperty(attributeKey);
    }
}
