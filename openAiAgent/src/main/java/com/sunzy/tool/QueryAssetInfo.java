package com.sunzy.tool;

import java.util.List;
import java.util.Map;


import cn.hutool.json.JSONUtil;
import com.sunzy.ai.tool.AiTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sunzy.server.jdbc.JdbcServer;

@Component("queryAssetInfo")
@AiTool(description = "queryAssetInfo(hostIp) 查询资产信息，工具名:queryAssetInfo,参数: hostIp(资产ip,必填)")
public class QueryAssetInfo implements ToolHandle {
    @Autowired
    private JdbcServer jdbcServer;

    @Override
    public String invokeToolName(Map<String, Object> args) {
        if (args == null || !args.containsKey("hostIp")) {
            return "Invalid arguments: hostIp is required";
        }
        return  jdbcServer.queryAssetInfo(args.get("hostIp").toString());

    }
}
