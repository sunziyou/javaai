package com.sunzy.ai.callback;

public interface AiAnswerCallback {
     void onAiAnswer(String answer);
     void finish(String answer);

     boolean isFinish();
}
