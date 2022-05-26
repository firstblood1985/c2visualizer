package io.github.firstblood1985.c2visualizer.c2authenticate;

import io.github.firstblood1985.c2visualizer.domain.user.dto.C2UserParam;
import io.github.firstblood1985.c2visualizer.service.user.c2authenticate.C2AuthenticateService;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

/**
 * usage of this class: C2AuthenticateServiceTest
 * created by limin @ 2022/4/21
 */
@RunWith(SpringRunner.class)
public class C2AuthenticateServiceTest {
    private final String C2LOGIN_URL = "https://log.concept2.com/login";
    private C2AuthenticateService c2AuthenticateService = new C2AuthenticateService();

    @Test
    public void C2SiteLoginTest() throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse response = client.execute(new HttpGet(C2LOGIN_URL));
        int statusCode = response.getStatusLine().getStatusCode();
        Assert.assertEquals(HttpStatus.SC_OK, statusCode);
    }

    @Test
    public void C2SiteLoginGetBody() throws IOException {
        CloseableHttpClient instance = HttpClientBuilder.create().build();

        CloseableHttpResponse response = instance.execute(new HttpGet(C2LOGIN_URL));
        String bodyAsString = EntityUtils.toString(response.getEntity());

        Document d = Jsoup.parse(bodyAsString, "UTF-8");

        Assert.assertNotNull(d);
    }

    private File getFileFromResource(String fileName) throws URISyntaxException {

        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {

            return new File(resource.toURI());
        }

    }

    @Test
    public void parseC2LoginSite() throws URISyntaxException, IOException {
        File htmlFile = getFileFromResource("c2login.html");
        Document d = Jsoup.parse(htmlFile, "UTF-8");

        Elements e = d.getElementsByClass("signin");
        Elements input = e.first().getElementsByTag("input");
        Element value = input.first();
        Assert.assertEquals("ofERlOIIZp1XnUvTiaW2lFE0WiCiSYDs6QeRIcUR", value.val());
    }

    @Test
    public void parseC2LogPage() throws URISyntaxException, IOException {
        File htmlFile = getFileFromResource("c2log.html");
        Document d = Jsoup.parse(htmlFile, "UTF-8");

        Elements e = d.getElementsByClass("dropdown-menu");
        Elements profile = e.first().getElementsByTag("li");
        Elements profileLink = profile.first().getElementsByTag("a");
        Attributes href = profileLink.first().attributes();
        String logbookId = href.get("href").substring(9);
        Assert.assertEquals("1043029",logbookId);
    }

    @Test
    public void parseC2ProfilePage() throws URISyntaxException, IOException {
        File htmlFile = getFileFromResource("c2profile.html");
        Document d = Jsoup.parse(htmlFile, "UTF-8");

        Elements content = d.getElementsByClass("content");
        String fullname = content.first().getElementsByTag("h2").first().text();
        Assert.assertEquals("Min Li",fullname);

        Elements c2UserProfile = content.first().getElementsByTag("p").get(1).getElementsByTag("strong");

        Element p = content.first().getElementsByTag("p").get(1);
        String pText = p.text();

        Map<String,String> profile = new HashMap<>();

        Object[] o = c2UserProfile.toArray();
        IntStream.range(0,o.length-1).forEach( i ->{
            Element e = (Element) o[i];
            String k = e.text();
            String pattern= k+"( .* )";
            if(i<o.length-1)
                pattern = pattern + ((Element)o[i+1]).text();
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(pText);
            if(m.find()) {
                System.out.println(m.group(1));
            }else{
                System.out.println("not found for " + pattern);
            }
        });


    }

    @Test
    public void testC2Login() throws IOException, InterruptedException {
        CloseableHttpClient instance = HttpClientBuilder.create().build();

        CloseableHttpResponse response = instance.execute(new HttpGet(C2LOGIN_URL));
        String bodyAsString = EntityUtils.toString(response.getEntity());

        Document d = Jsoup.parse(bodyAsString, "UTF-8");
        Elements e = d.getElementsByClass("signin");
        Elements input = e.first().getElementsByTag("input");
        Element value = input.first();
        String token = value.val();

        Thread.sleep(3000);

        CloseableHttpClient httpclient =HttpClientBuilder.create().setRedirectStrategy(new LaxRedirectStrategy()).build();

        //Creating the RequestBuilder object
        RequestBuilder reqbuilder = RequestBuilder.post();

        //Setting URI and parameters
        RequestBuilder reqbuilder1 = reqbuilder.setUri(C2LOGIN_URL);
        RequestBuilder reqbuilder2 = reqbuilder1.addParameter("username",
                "firstblood1985").addParameter("password", "12qwaszx").addParameter("_token", token);

        //Building the HttpUriRequest object
        HttpUriRequest httppost = reqbuilder2.build();

        //Executing the request
        HttpResponse httpresponse = httpclient.execute(httppost);
        Header[] headers = httpresponse.getAllHeaders();
        System.out.println(headers);
//        String results = EntityUtils.toString(httpresponse.getEntity());
//        System.out.println(results);

        HttpGet httpGet = new HttpGet("https://log.concept2.com/profile/1043029");
//        httpGet.setHeaders(headers);
        httpresponse = httpclient.execute(httpGet);
        String results = EntityUtils.toString(httpresponse.getEntity());
        System.out.println(results);

        Assert.assertTrue(results.contains("1043029"));
    }

    @Test
    public void C2LoginFailed() throws IOException {
        CloseableHttpClient instance = HttpClientBuilder.create().build();

        CloseableHttpResponse response = instance.execute(new HttpGet(C2LOGIN_URL));
        String bodyAsString = EntityUtils.toString(response.getEntity());

        Document d = Jsoup.parse(bodyAsString, "UTF-8");
        Elements e = d.getElementsByClass("signin");
        Elements input = e.first().getElementsByTag("input");
        Element value = input.first();
        String token = value.val();


        CloseableHttpClient httpclient = HttpClients.createDefault();

        //Creating the RequestBuilder object
        RequestBuilder reqbuilder = RequestBuilder.post();

        //Setting URI and parameters
        RequestBuilder reqbuilder1 = reqbuilder.setUri(C2LOGIN_URL);
        RequestBuilder reqbuilder2 = reqbuilder1.addParameter("username",
                "firstblood1985").addParameter("password", "123").addParameter("_token", token);

        //Building the HttpUriRequest object
        HttpUriRequest httppost = reqbuilder2.build();

        //Executing the request
        HttpResponse httpresponse = httpclient.execute(httppost);

        //Printing the status and the contents of the response
        String results = EntityUtils.toString(httpresponse.getEntity());
        System.out.println(results);
        Assert.assertTrue(results.contains("Sorry, it looks like something has gone wrong!"));
    }
    @Test
    public void testC2AuthenticateService(){
        C2UserParam c2UserParam = C2UserParam.builder().username("firstblood1985").password("12qwaszx").build();
        Assert.assertTrue(c2AuthenticateService.authenticate(c2UserParam));

        System.out.println(c2UserParam);

        Assert.assertEquals("1043029",c2UserParam.getLogbookId());
        Assert.assertEquals("firstblood1985",c2UserParam.getUsername());

    }

    @Test
    public void testC2AuthenticateServiceFailed(){
        C2UserParam c2UserParam = C2UserParam.builder().username("firstblood1985").password("123456").build();
        Assert.assertFalse(c2AuthenticateService.authenticate(c2UserParam));
    }


}
