package com.sunzy.ai.parser;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.sunzy.ai.ToolBean;

public class DeepseekParserTool implements ParserTool{

    @Override
    public ToolBean parseTool(String toolContent) {
        toolContent=toolContent.trim();
        if(toolContent.contains("{")&&toolContent.contains("}")){
            toolContent=toolContent.substring(toolContent.indexOf("{"),toolContent.lastIndexOf("}")+1);
            JSONObject jsonObject = JSONUtil.parseObj(toolContent);
            if(jsonObject.containsKey("tool")){
               return JSONUtil.toBean(jsonObject.getJSONObject("tool"),ToolBean.class);
            }
            return JSONUtil.toBean(toolContent,ToolBean.class);
        }
       return null;
    }

    @Override
    public String parseJson(String toolContent) {
        toolContent=toolContent.trim();

        if(toolContent.contains("{")&&toolContent.contains("}")){
            toolContent=toolContent.substring(toolContent.indexOf("{"),toolContent.lastIndexOf("}")+1);
            return toolContent;
        }
        return null;
    }
    public static void main(String[] args) {
        String ss="```json{\"toolName\":\"deepseek\",\"arg\":{\"command\":\"whoami\"}}```";
        DeepseekParserTool deepseekParserTool = new DeepseekParserTool();
        ToolBean toolBean = deepseekParserTool.parseTool(ss);
        System.out.println(toolBean);
    }
}
