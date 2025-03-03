package com.secisland;

import com.secisland.commrpc.thrift.client.RpcClientProxy;

public class AnalysisAlertTest {
    public static void main(String[] args) {
        String s = RpcClientProxy.sendRequest("secisland.collect.alertAnalysis", "源ip:192.168.2.154,WIN-QHEA3UJNE70$在2025-02-22 02:33:41通过192.168.2.154进行了绕行堡垒机操作，成功登录192.168.1.100的账号Administrator。请注意！", "127.0.0.1",1131);
        System.out.println(s);
    }
}
