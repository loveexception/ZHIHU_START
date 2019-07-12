package com.maodajun.zhihu.service;

import com.alibaba.druid.util.HttpClientUtils;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.nutz.http.*;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;
import org.nutz.lang.random.R;
import org.nutz.log.Logs;
import sun.net.www.http.HttpClient;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.regex.Pattern;

@IocBean
public class HttpTools {



    public Object urlToJson(String url){

        Response res =Http.get(url,2000);
        if(res.isOK()){
            return Json.fromJson(res.getReader());
        }
        return null;
    }
//    public Object urlToJson(String url){
//        try {
//
//            Http.setProxySwitcher(new ProxySwitcher() {
//                public Proxy getProxy(URL url) {
//                    //String[] array = proxy().split(":");
//                    return null; //new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(array[0], Lang.str2number(array[1]).intValue()));
//                }
//
//                public Proxy getProxy(Request req) {
//                    if (req.getUrl().getHost().indexOf("zhihu") >= 0) {
//                        temp =proxy();
//                        Logs.getLog(HttpTools.class).error(temp);
//
//                        String[] array = temp.split(":");
//                        return new Proxy(Proxy.Type.HTTP, new InetSocketAddress(array[0], Lang.str2number(array[1]).intValue()));
//                    }
//                    return null;//
//                }
//            });
//            System.out.println(url);
//            Response res = Http.get(url, 2000);
//            System.out.println(res.getContent());
//            if (res.isOK()) {
//                return Json.fromJson(res.getReader());
//            }
//        }catch(HttpException exception){
//            System.out.println(exception.fillInStackTrace());
//            String message =exception.getCause().getCause().getLocalizedMessage();
//            if(message.indexOf("timed out")>=0){
//                String delete ="http://localhost:5010/delete/?proxy="+temp;
//                System.out.println(
//                        delete
//                );
//                Http.get(delete,1000);
//                System.out.println("delete over ");
//            }
//
//        }
//        return null;
//    }

//    public Object urlToJson(String url)  {
//        String temp =proxy();
//        String[] array = temp.split(":");
//        HttpHost proxy = new HttpHost(array[0], Lang.str2number(array[1]).intValue(), "HTTP");
//        //设置代理IP、端口、协议（请分别替换）
//
//        //把代理设置到请求配置
//        RequestConfig defaultRequestConfig = RequestConfig.custom()
//                .setProxy(proxy)
//                .build();
//
//        //实例化CloseableHttpClient对象
//        CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).build();
//
//        //访问目标地址
//        HttpGet httpGet = new HttpGet(url);
//        try {
//            Object statusCode = getObject(httpclient, httpGet);
//            return statusCode;
//
//        }catch (Exception e){
//            e.printStackTrace();
//
//        }
//
//        return null;
//    }

    private Object getObject(CloseableHttpClient httpclient, HttpGet httpGet) throws IOException {
        //请求返回
        CloseableHttpResponse httpResp = httpclient.execute(httpGet);

        try {

            int statusCode = httpResp.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                System.out.println("成功");
                String str = EntityUtils.toString(httpResp.getEntity(), "UTF-8");

                return str;
            }

        } catch (Exception e) {


        } finally {
            httpResp.close();

        }
        return null;
    }

    public String proxy(){

        Response response = Http.get("http://localhost:5010/get/");


        return response.getContent();
    }


}
