package com.sunzy.server.openai;

import cn.hutool.json.JSONUtil;
import com.sunzy.ai.ToolBean;
import com.sunzy.ai.callback.AiAnswerCallback;
import com.sunzy.ai.parser.ParserTool;
import com.sunzy.ai.parser.ParserToolFactory;
import com.sunzy.utils.MessageUtils;
import com.sunzy.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class AnalysisAlert implements ApplicationRunner {
    private Logger logger = LoggerFactory.getLogger(AnalysisAlert.class);
    @Value("${module}")
    private String module;
    @Autowired
    private OpenAiApi openAiApi;
    @Autowired
    private ToolContains toolContains;
    private int limitCount =8;

    public void analysis(String alertInfo, AiAnswerCallback aiAnswerCallback, String moduleName) {
        String prompt = StringUtils.readByResource("SystemPrompt.txt");
        List<String> shortTermMemory = new ArrayList<>();
        int count = 0;
        if (moduleName == null) {
            moduleName = module;
        }
        aiAnswerCallback.onAiAnswer("用户问题:" + alertInfo);
        ParserTool parserTool = ParserToolFactory.createParserTool(moduleName);
        boolean needFinish = false;
        while (true) {
            List<OpenAiApi.ChatCompletionMessage> messages = createAlertMessage(prompt, alertInfo, shortTermMemory);
            if (needFinish) {
                messages.add(MessageUtils.createUserMessage("直接根据目前已知信息，调用finishTool工具给出目前最合理的结论"));
            }
            String content = "";
            try {
                OpenAiApi.ChatCompletionRequest request = new OpenAiApi.ChatCompletionRequest(messages, moduleName, null, null);
                ResponseEntity<OpenAiApi.ChatCompletion> chatCompletionResponseEntity = openAiApi.chatCompletionEntity(request);
                content = chatCompletionResponseEntity.getBody().choices().get(0).message().content();
                String s = parserTool.parseJson(content);
                aiAnswerCallback.onAiAnswer(JSONUtil.parseObj(s).get("thoughts").toString());
                shortTermMemory.add(content);
            } catch (Exception e) {
                aiAnswerCallback.finish("调用大模型错误:" + e.getMessage());
                break;
            }
            ToolBean toolBean = parserTool.parseTool(content);
            if (toolBean == null) {
                aiAnswerCallback.onAiAnswer("获取结果:" + content);
                shortTermMemory.add("没有获取工具请继续分析");
                continue;
            }
            if (Objects.equals(toolBean.getToolName(), "finishTool")) {
                aiAnswerCallback.finish(String.valueOf(toolBean.getArg().get("result")));
                break;
            }
            aiAnswerCallback.onAiAnswer("执行命令:" + toolBean);
            String toolMessage = toolContains.invokeToolName(toolBean).trim();
            aiAnswerCallback.onAiAnswer("命令结果:" + toolMessage);
            shortTermMemory.add(toolBean.getToolName()+"结果:" + toolMessage);
            count++;
            if (count > limitCount) {
                needFinish = true;
            }
            if (count > limitCount + 2) {
                aiAnswerCallback.finish("由于工具能力有限暂时无法获取值");
                break;
            }
        }
        if(!aiAnswerCallback.isFinish()){
            aiAnswerCallback.finish("由于能力有限，此问题暂时无法分析出结果");
        }

    }

    private List<OpenAiApi.ChatCompletionMessage> createAlertMessage(String prompt, String alertInfo, List<String> shortTermMemory) {
        Map<String, String> varValue = new HashMap<>();
        varValue.put("tools", String.valueOf(toolContains.getTools()));
        varValue.put("shortTermMemory", String.valueOf(shortTermMemory));
        String actualValue = MessageUtils.createMessage(varValue, prompt);
        List<OpenAiApi.ChatCompletionMessage> messages = new ArrayList<>();
        messages.add(MessageUtils.createSystemMessage(actualValue));
        messages.add(MessageUtils.createUserMessage(alertInfo));
        return messages;
    }

    @Override
    public void run(ApplicationArguments args) {
       // analysis("告警时间:2025-03-01 00:01:05,告警内容:近一小时内192.168.2.161 资产日志量为0，请检查该主机是否正常！", new PrintAnswer(), null);
    }
}
