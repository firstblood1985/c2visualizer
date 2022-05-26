package io.github.firstblood1985.c2visualizer.service.wx;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * usage of this class: WXLoginService
 * created by limin @ 2022/4/30
 */
@Service
@Slf4j
public class WXLoginService {
    private final String loginPrefix = "https://api.weixin.qq.com/sns/jscode2session";

    @Value("${wx.appId}")
    private String appId;

    @Value("${wx.appSecret}")
    private String appSecret;

    public String login(String code, String grantType){
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpUriRequest request =  RequestBuilder.get(loginPrefix)
                .addParameter("appid",appId)
                .addParameter("secret",appSecret)
                .addParameter("js_code",code)
                .addParameter("grant_type",grantType)
                .build();
        try {
            HttpResponse httpresponse = httpclient.execute(request);
            String results = EntityUtils.toString(httpresponse.getEntity());

            return (String) JSONUtil.parseObj(results).get("openid");


        } catch (IOException e) {
            log.error("failed to login wx with code: {}, granType: {}",code, grantType);
            return null;
        }
    }

}
