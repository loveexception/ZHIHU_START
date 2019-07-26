package com.maodajun.zhihu.service;

import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.nutz.http.*;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.lang.Lang;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;


@IocBean
public class HttpTools {



//    public Object urlToJson(String url){
//
//        Response res =Http.get(url,2000);
//        if(res.isOK()){
//            return Json.fromJson(res.getReader());
//        }
//        return null;
//    }


    public Object urlToJson(String url){
        String str = chooseHttpClient(url);
        String regEx_html="<[^>]+>";
        str = str.replaceAll(regEx_html,"");
        if(str.indexOf("请输入验证码进行验证")>0){
            driver .get("http://zhihu.com/");

            //WebDriverWait wait=new WebDriverWait(driver,30);

            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.quit();;
            driver= null;
            return null;
        }
        if(str.indexOf("无法访问此网站")>0){
            driver.quit();
            driver=null;
            return null;
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

    public HttpHost proxy(){

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
//        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
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


        driver = new ChromeDriver();
        return driver;
    }

    private DesiredCapabilities proxyOptions() {
        System.setProperty("webdriver.chrome.driver", "/Users/maodajun/Documents/project/zhihu/start/chromedriver");


        Response response = Http.get("http://localhost:5010/get/");

        String temp =response.getContent();
        String usedProxy ="http://"+temp;

//        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//        capabilities.setCapability("chrome.switches", Arrays.asList("--proxy-server=http://"+temp));
        Proxy proxy = new Proxy();
        proxy.setHttpProxy(usedProxy).setFtpProxy(usedProxy).setSslProxy(usedProxy);
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(CapabilityType.PROXY, proxy);
        return cap;
    }

    public void quit(){
        if(driver!=null){
            driver.quit();
        }
    }

    WebDriver driver = null;

}
