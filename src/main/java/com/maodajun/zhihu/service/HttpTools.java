package com.maodajun.zhihu.service;

import com.maodajun.zhihu.common.HttpConnectionPoolUtil;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.nutz.http.*;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.lang.Lang;

import org.nutz.lang.Streams;
import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;


@IocBean
public class HttpTools {
    private static  int statis_i=0;


//    public static Object urlToJson4(String url){
//
//        Response res =Http.get(url,2000);
//        if(!res.isOK()) {
//            System.out.println(res.getStatus());
//            System.out.println(res.getContent());
//            return null;
//        }
//        Reader reader = res.getReader();
//        String str  = Lang.readAll(reader);
//        if(str.indexOf("无法访问此网站")>0){
//            System.out.print("*");
//            return null;
//        }
//        System.out.print("-");
//        if(statis_i++%100==0){
//            System.out.println( /*TODO: 时间*/ );
//        }
//        return Json.fromJson(str);
//
//    }
//
//    public   Object urlToJson3(String url) throws IOException {
//        System.out.println("^");
//       // CloseableHttpClient client =HttpConnectionPoolUtil.getHttpClient(url);
//        HttpHost proxy = proxy(); //添加代理，IP为本地IP 8888就是fillder的端口
//        HttpGet httpGet = new HttpGet(url);
//        System.out.println("获取的url为:"+url);
//        httpGet.setHeader("Connection", "keep-alive");
//
//        //代理
//        RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
//        httpGet.setConfig(config);
//
//        System.out.println("executing request:" + httpGet.getURI());
//        // 执行get请求
//        CloseableHttpResponse res = client.execute(httpGet);
//        // Response res =Http.get(url,2000);
//        if(res.getStatusLine().getStatusCode()!=200) {
//            proxy =null;
//            System.out.println("!");
//            return null;
//        }
//        BufferedReader in = new BufferedReader(new InputStreamReader(res.getEntity().getContent()));
//
//        String str = Streams.read(in).toString();
//
//
//        if(str.indexOf("无法访问此网站")>0){
//            proxy = null;
//            System.out.print("*");
//            return null;
//        }
//        System.out.print("-");
//        if(statis_i++%100==0){
//            System.out.println( /*TODO: 时间*/ );
//        }
//        return Json.fromJson(str);
//
//    }
    public Object urlToJson2(String url){
        String str = chooseHttpClient(url);
        String regEx_html="<[^>]+>";
        str = str.replaceAll(regEx_html,"");
        if(str.indexOf("请输入验证码进行验证")>0){
            driver .get("http://zhihu.com/");
            WebElement element = driver.findElement(By.tagName("img"));

            //WebDriverWait wait=new WebDriverWait(driver,30);

            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.quit();;
            driver= null;
            System.out.print("x");
            return null;
        }
        if(str.indexOf("无法访问此网站")>0){
            driver.quit();
            driver=null;
            System.out.print("*");
            return null;
        }
        System.out.print(".");
        if(statis_i++%100==0){
            System.out.println( /*TODO: 时间*/ );
        }
        return Json.fromJson(str);
    }

    private String chooseHttpClient(String url) {
        driver = init();
        driver.get(url);
        return driver.getPageSource();
    }

    private Object getObject(CloseableHttpClient httpclient, HttpGet httpGet) throws IOException {
        //请求返回
        CloseableHttpResponse httpResp = httpclient.execute(httpGet);

        try {

            int statusCode = httpResp.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                //System.out.println("成功");
                String str = EntityUtils.toString(httpResp.getEntity(), "UTF-8");

                return str;
            }

        } catch (Exception e) {


        } finally {
            httpResp.close();

        }
        return null;
    }
    public static HttpHost proxy;
    public static HttpHost proxy(){
        if(proxy!=null){
            return proxy;
        }

        Response response = Http.get("http://localhost:5010/get/");

        String temp =response.getContent();
        String[] array = temp.split(":");
        HttpHost proxy = new HttpHost(array[0], Lang.str2number(array[1]).intValue(), "HTTP");


        return proxy;
    }
    public WebDriver init(){
        if(driver!=null){
            return driver;
        }

        DesiredCapabilities capabilities = proxyOptions();


//        driver = new ChromeDriver(capabilities);
//        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
//
//        while(true){
//            driver.get("http://baidu.com");
//            String str =driver.getPageSource();
//            if(str.indexOf("百度首页")>0){
//                return driver;
//            }else {
//                driver.quit();
//                driver = null;
//                continue;
//
//            }
//        }
        ChromeOptions options = new ChromeOptions();
        options.addArguments("enable-automation");
        //options.addArguments("--headless");
        options.addArguments("--window-size=520,380");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-extensions");
        options.addArguments("--dns-prefetch-disable");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-browser-side-navigation");

        driver = new ChromeDriver(options);

        return driver;
    }

    private DesiredCapabilities proxyOptions() {
        System.setProperty("webdriver.chrome.driver", "/Users/maodajun/Documents/project/zhihu/start/chromedriver_76");


        Response response = Http.get("http://localhost:5010/get/");

        String temp =response.getContent();
        String usedProxy ="http://"+temp;

//        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//        capabilities.setCapability("chrome.switches", Arrays.asList("--proxy-server=http://"+temp));
        Proxy proxy = new Proxy();
        proxy.setHttpProxy(usedProxy).setFtpProxy(usedProxy).setSslProxy(usedProxy);
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(CapabilityType.PROXY, proxy);
//        options.addArguments("enable-automation");
//        options.addArguments("--headless");
//        options.addArguments("--window-size=520,380");
//        options.addArguments("--no-sandbox");
//        options.addArguments("--disable-extensions");
//        options.addArguments("--dns-prefetch-disable");
//        options.addArguments("--disable-gpu");
//        options.addArguments("--no-sandbox");
        return cap;
    }

    public void quit(){
        if(driver!=null){
            driver.quit();
        }
    }

    WebDriver driver = null;

}
