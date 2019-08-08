package com.maodajun.zhihu.common;


import lombok.Data;

@Data
public class Config {
    static final int  HttpMonitorInterval=2;// 设置连接建立的超时时间为10s
    static final int  HttpSocketTimeout=2;
    static final int  HttpMaxPoolSize=3;
    static final int  HttpIdelTimeout=2;
    static final int  HttpConnectTimeout=2;


//    private static final int CONNECT_TIMEOUT = Config.getHttpConnectTimeout();// 设置连接建立的超时时间为10s
//    private static final int SOCKET_TIMEOUT = Config.getHttpSocketTimeout();
//    private static final int MAX_CONN = Config.getHttpMaxPoolSize(); // 最大连接数
//    private static final int Max_PRE_ROUTE = Config.getHttpMaxPoolSize();
//    private static final int MAX_ROUTE = Config.getHttpMaxPoolSize();


}
