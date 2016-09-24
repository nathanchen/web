package info.chenqin.web.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.gson.Gson;
import info.chenqin.apirequest.BaseAPIRequestModel;
import info.chenqin.apiresponse.BaseAPIResponse;
import info.chenqin.util.JsonHelper;
import info.chenqin.util.LogHelper;
import info.chenqin.util.MethodHelper;
import info.chenqin.web.util.WebProjectPropertiesFileHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.HashMap;

/**
 * User: nathanchen
 * <p/>
 * Date: 21/09/2016
 * <p/>
 * Time: 1:51 AM
 * <p/>
 * Description:
 */
@Slf4j
public class BaseService
{
    protected BaseAPIResponse getForObject(BaseAPIRequestModel baseAPIRequest, String url, Class<?> clazz)
    {
        // 按照接口文档对该加密的加密
        MethodHelper.generateSign(baseAPIRequest);
        ObjectMapper m = new ObjectMapper();
        HashMap<String, String> props = m.convertValue(baseAPIRequest, HashMap.class);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
        String responseContent;
        try
        {
            responseContent = restTemplate.getForObject(WebProjectPropertiesFileHelper.getAPIServerDomain() + url + "?" + Joiner.on("&").withKeyValueSeparator("=")
                    .join(props), String.class);
        }
        catch (Exception e)
        {
            String errMsg = StringEscapeUtils.escapeJava(e.getMessage());
            responseContent = "{\"rtnCode\":\"-1\",\"rtnMsg\":\"" + errMsg + "\"}";
        }

        log.info(LogHelper.apiRequestResponseLoggerFormat(url, JsonHelper.chgBean2Json(baseAPIRequest), responseContent));

        if (Strings.isNullOrEmpty(responseContent))
        {
            responseContent = "{\"rtnCode\":\"-1\",\"rtnMsg\":\"未知错误\"}";
        }

        Gson gson = new Gson();

        BaseAPIResponse response = (BaseAPIResponse) gson.fromJson(responseContent, clazz);
        if (response.getCode() != 0)
        {
            log.error(LogHelper.apiRequestResponseLoggerFormat(url, JsonHelper.chgBean2Json(baseAPIRequest), responseContent));
        }
        return response;
    }

    public BaseAPIResponse postForObject(BaseAPIRequestModel baseAPIRequest, String url, Class<?> clazz)
    {
        // 按照接口文档对该加密的加密
        MethodHelper.generateSign(baseAPIRequest);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
        String responseContent;
        try
        {
            responseContent = restTemplate.postForObject(WebProjectPropertiesFileHelper.getAPIServerDomain() + url, baseAPIRequest, String.class);
        }
        catch (Exception e)
        {
            String errMsg = StringEscapeUtils.escapeJava(e.getMessage());
            responseContent = "{\"rtnCode\":\"-1\",\"rtnMsg\":\"" + errMsg + "\"}";
        }

        log.info(LogHelper.apiRequestResponseLoggerFormat(url, JsonHelper.chgBean2Json(baseAPIRequest), responseContent));

        if (Strings.isNullOrEmpty(responseContent))
        {
            responseContent = "{\"rtnCode\":\"-1\",\"rtnMsg\":\"未知错误\"}";
        }

        Gson gson = new Gson();

        BaseAPIResponse response = (BaseAPIResponse) gson.fromJson(responseContent, clazz);
        if (response.getCode() != 0)
        {
            log.error(LogHelper.apiRequestResponseLoggerFormat(url, JsonHelper.chgBean2Json(baseAPIRequest), responseContent));
        }
        return response;
    }
}
