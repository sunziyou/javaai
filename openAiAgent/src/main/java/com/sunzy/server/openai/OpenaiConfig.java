package com.sunzy.server.openai;

import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenaiConfig {
    @Value("${apiKey}")
    private String apiKey;
    @Value("${baseUrl}")
    private String baseUrl;
    @Bean
    public OpenAiApi createOpenAiApi() {
       OpenAiApi openAiApi = OpenAiApi.builder().apiKey(apiKey).baseUrl(baseUrl).build();
        return openAiApi;
    }
}
