package com.sunzy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.autoconfigure.openai.OpenAiAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;


@SpringBootApplication(exclude = {OpenAiAutoConfiguration.class, JdbcTemplateAutoConfiguration.class})
public class Bootstrap {
    private static final Logger logger = LoggerFactory.getLogger(Bootstrap.class);
    private static final Object obj = new Object();
    public static void main(String[] args) throws Exception {
       SpringApplication.run(Bootstrap.class, args);
        //只是作为阻塞线程使用，防止线程结束
        synchronized (obj){
            obj.wait();
        }

    }




}
