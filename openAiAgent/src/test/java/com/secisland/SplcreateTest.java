package com.secisland;

import cn.hutool.json.JSONUtil;
import com.secisland.commrpc.thrift.client.RpcClientProxy;

public class SplcreateTest {
    public static void main(String[] args) {
        String s = RpcClientProxy.sendRequest("secisland.collect.splCreate", "获取今天targetIp为127.0.0.1的日志信息", "192.168.1.152",1131);
        System.out.println(JSONUtil.parseObj(s).getStr("resultDesc"));
    }
}
