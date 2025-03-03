package com.sunzy.tool;
import java.util.Map;
import com.sunzy.ai.tool.AiTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
@Component("querySyslogByIp")
@AiTool(description = "querySyslogByIp(String hostIp,String startTime,String endTime,queryString)查询主机的syslog日志,工具名:querySyslogByIp,参数:startTime(开始时间,格式:yyy-MM-dd HH:mm:ss),endTime(结束时间,格式:yyy-MM-dd HH:mm:ss),hostIp(查询的资产ip,必填),queryString(自然语言描述，必须包含足够完全准确的详细信息,源目标明确,必填),注意此工具只能包括操作系统的系统日志并没有其他信息")
public class QuerySyslogByIp implements ToolHandle {
    private static final Logger logger = LoggerFactory.getLogger(QuerySyslogByIp.class);


    //todo 根据自己业务实现，也可以自己写prompt 实现sql
    @Override
    public String invokeToolName(Map<String, Object> args) {
        if (args == null || !args.containsKey("hostIp")) {
            return "Invalid arguments: hostIp is required";
        }
       return "[]";
    }
}
