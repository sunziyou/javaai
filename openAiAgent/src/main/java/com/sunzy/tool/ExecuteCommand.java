package com.sunzy.tool;

import java.util.Map;

import com.sunzy.ai.tool.AiTool;
import org.springframework.stereotype.Component;

import com.sunzy.utils.CommandExecutorUtils;
@Component("executeCommand")
@AiTool(description = "executeCommand(hostIp,command)执行命令，工具名:executeCommand,参数: hostIp(资产ip,必填),command(执行的命令，必填),此工具现在无法使用请不要使用这个工具")
public class ExecuteCommand implements ToolHandle {

    @Override
    public String invokeToolName(Map<String, Object> args) {
        if (args == null || !args.containsKey("hostIp") || !args.containsKey("command")) {
            return "Invalid arguments";
        }
        return CommandExecutorUtils.executeCommand(args.get("command").toString());
    }
    
}
