package com.sunzy.server.openai;

import com.sunzy.ai.ToolBean;
import com.sunzy.ai.tool.AiTool;
import com.sunzy.tool.ToolHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ToolContains implements ApplicationContextAware, InitializingBean {
    private Logger logger = LoggerFactory.getLogger(ToolContains.class);

    private Map<String, ToolHandle> handles = new ConcurrentHashMap<>();
    private final List<String> tools = new ArrayList<>();
    private ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext=applicationContext;
    }
    public String invokeToolName(ToolBean toolBean) {
        try {
            if (!handles.containsKey(toolBean.getToolName())) {
                return "无法获取信息";
            }
            return handles.get(toolBean.getToolName()).invokeToolName(toolBean.getArg());
        } catch (Exception e) {
            logger.warn("调用工具失败", e);
            return "无法获取信息";
        }

    }
    @Override
    public void afterPropertiesSet() {

        String[] beanNames = applicationContext.getBeanNamesForType(ToolHandle.class);
        for (String beanName : beanNames) {
            ToolHandle toolHandle = applicationContext.getBean(beanName, ToolHandle.class);
            if(toolHandle.getClass().getAnnotation(AiTool.class)!=null){
               tools.add(toolHandle.getClass().getAnnotation(AiTool.class).description());
            }
            handles.put(beanName, toolHandle);
        }

    }

    public List<String> getTools() {
        return tools;
    }
}
