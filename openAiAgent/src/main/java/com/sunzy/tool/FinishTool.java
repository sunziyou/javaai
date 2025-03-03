package com.sunzy.tool;

import com.sunzy.ai.tool.AiTool;
import com.sunzy.utils.CommandExecutorUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("finishTool")
@AiTool(description = "finishTool(result) 问题得出结论最后调用函数，工具名:finishTool,参数: result(目标结论，必填)")
public class FinishTool implements ToolHandle {

    @Override
    public String invokeToolName(Map<String, Object> args) {
        return args.get("result").toString();
    }
    
}
