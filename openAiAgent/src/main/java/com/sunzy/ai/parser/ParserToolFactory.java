package com.sunzy.ai.parser;

import com.sunzy.ai.ToolBean;

public class ParserToolFactory {
    public static ParserTool createParserTool(String moduleName)
    {
        if(moduleName.toLowerCase().contains("deepseek"))
        {
            return new DeepseekParserTool();
        }
        return new DeepseekParserTool();
    }

}
