package io.github.firstblood1985.c2visualizer.service.user.c2authenticate;

import io.github.firstblood1985.c2visualizer.domain.user.C2User;
import io.github.firstblood1985.c2visualizer.domain.user.dto.C2UserParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.CaseUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

/**
 * usage of this class: C2AuthenticateService
 * created by limin @ 2022/4/21
 */
@Service
@Slf4j
public class C2AuthenticateService {
    private final String C2LOGIN_URL = "https://log.concept2.com/login";

    public Boolean authenticate(C2UserParam c2UserParam) {
        String username = c2UserParam.getUsername();
        String password = c2UserParam.getPassword();
        try {
            String token = getToken();
            Thread.sleep(3000);
            return c2LoginAndGetProfile(username, password, token, c2UserParam);
        } catch (IOException | InterruptedException e) {
            log.warn("Failed to log in with exception: {}, ", e.getMessage());
            return false;
        }
    }

    private Boolean checkLoginFailed(String results) {
        return results.contains("Sorry, it looks like something has gone wrong!");
    }

    private boolean c2LoginAndGetProfile(String username, String password, String token, C2UserParam c2UserParam) throws IOException {
        CloseableHttpClient httpclient = HttpClientBuilder.create().setRedirectStrategy(new LaxRedirectStrategy()).build();
        HttpResponse httpresponse = c2Login(username, password, token, httpclient);

        String results = EntityUtils.toString(httpresponse.getEntity());
        if (checkLoginFailed(results)) {
            log.warn("failed to login with username: {}, password: {}", username, password);
            return false;
        }

        String logbookId = parseC2LogPageGetLogbookId(results);

        HttpGet httpGet = new HttpGet("https://log.concept2.com/profile/"+logbookId);
        httpresponse = httpclient.execute(httpGet);
        String profilePageHtml = EntityUtils.toString(httpresponse.getEntity());
        Map<String,String> mapProfile = parseProfilePageGetProfile(profilePageHtml);
        c2UserParam.initFromMapProfile(mapProfile);
        return true;
    }

    private Map<String,String> parseProfilePageGetProfile(String profilePageHtml) {
        Map<String,String> profile = new HashMap<>();
        Document d = Jsoup.parse(profilePageHtml, "UTF-8");

        Elements content = d.getElementsByClass("content");
        String fullname = content.first().getElementsByTag("h2").first().text();
        profile.put("fullName",fullname);
        Elements c2UserProfile = content.first().getElementsByTag("p").get(1).getElementsByTag("strong");
        Element p = content.first().getElementsByTag("p").get(1);
        String pText = p.text();
        Object[] o = c2UserProfile.toArray();
        IntStream.range(0,o.length).forEach(i ->{
            Element e = (Element) o[i];
            String k = e.text();
            String pattern= k+"( .* )";
            if(i<o.length-1)
                pattern = pattern + ((Element)o[i+1]).text();
            else
                pattern = k+ "( .*)";
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(pText);
            if(m.find()) {
                profile.put(CaseUtils.toCamelCase(k.substring(0,k.length()-1),false,' '),m.group(1).trim());
            }else{
                log.warn("no matcher found for pattern: {}",pattern);
            }
        });
        log.info("Get C2User profile: {}",profile);
        return profile;
    }

    private String parseC2LogPageGetLogbookId(String c2LogPageHtml) {
        Document d = Jsoup.parse(c2LogPageHtml, "UTF-8");

        Elements e = d.getElementsByClass("dropdown-menu");
        Elements profile = e.first().getElementsByTag("li");
        Elements profileLink = profile.first().getElementsByTag("a");
        Attributes href = profileLink.first().attributes();
        return href.get("href").substring(9);
    }

    private HttpResponse c2Login(String username, String password, String token, CloseableHttpClient httpclient) throws IOException {

        RequestBuilder reqbuilder = RequestBuilder.post().setUri(C2LOGIN_URL).addParameter("username",
                username).addParameter("password", password).addParameter("_token", token);
        HttpUriRequest httppost = reqbuilder.build();

        HttpResponse httpresponse = httpclient.execute(httppost);

        return httpresponse;
    }

    private String getToken() throws IOException {
        CloseableHttpClient instance = HttpClientBuilder.create().build();

        CloseableHttpResponse response = instance.execute(new HttpGet(C2LOGIN_URL));
        String bodyAsString = EntityUtils.toString(response.getEntity());

        Document d = Jsoup.parse(bodyAsString, "UTF-8");
        Elements e = d.getElementsByClass("signin");
        Elements input = e.first().getElementsByTag("input");
        Element value = input.first();
        String token = value.val();
        return token;
    }

    public Boolean isAuthenticated(C2User c2User) {
        return true;
    }
}
