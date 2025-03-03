package com.secisland;

import com.secisland.commrpc.thrift.client.RpcClientProxy;

public class QuerySyslogTest {
    public static void main(String[] args) {
        String s = RpcClientProxy.sendRequest("secisland.collect.querySysLog", "{\"startTime\":\"2025-02-21 14:00:00\",\"endTime\":\"2025-02-21 14:05:00\",\"hostIp\":\"192.168.1.100\",\"queryString\":\"查询源ip192.168.2.154绕过堡垒机登录的详细日志\"}", "127.0.0.1",1131);
        System.out.println(s);
    }
}
