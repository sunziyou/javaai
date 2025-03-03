package com.sunzy.ai.parser;

import com.sunzy.ai.ToolBean;

public interface ParserTool {
    ToolBean parseTool(String toolContent);

    String parseJson(String toolContent);
}
