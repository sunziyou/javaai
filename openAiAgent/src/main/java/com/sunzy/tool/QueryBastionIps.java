package com.sunzy.tool;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import com.sunzy.ai.tool.AiTool;
import org.springframework.stereotype.Component;

@Component("queryBastionIps")
@AiTool(description = "queryBastionIps() 查询堡垒机Ip，工具名:queryBastionIps,参数:无")
public class QueryBastionIps implements ToolHandle {

    //todo 根据自行需要添加函数实现
    @Override
    public String invokeToolName(Map<String, Object> args) {
        List<String> bastionIps = new ArrayList<>();
        // TODO: Implement actual bastion host query logic
        bastionIps.add("192.168.1.154");
        bastionIps.add("127.0.0.1");
        bastionIps.add("223.112.17.162");
        return bastionIps.toString();
    }
}
