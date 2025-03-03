package com.sunzy.ai;

import java.util.HashMap;
import java.util.Map;

public class ToolBean {
    private String toolName;
    private Map<String, Object> arg=new HashMap<>();

    public String getToolName() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
    }

    public Map<String, Object> getArg() {
        return arg;
    }

    public void setArg(Map<String, Object> arg) {
        this.arg = arg;
    }

    @Override
    public String toString() {
        return "ToolBean{" +
                "toolName='" + toolName + '\'' +
                ", arg=" + arg +
                '}';
    }
}
