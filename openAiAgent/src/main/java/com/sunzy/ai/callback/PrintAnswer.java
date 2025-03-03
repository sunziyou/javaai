package com.sunzy.ai.callback;

public class PrintAnswer implements AiAnswerCallback {
    private volatile boolean isFinish = false;
    @Override
    public void onAiAnswer(String answer) {
        System.out.println(answer);
    }

    @Override
    public void finish(String answer) {
        isFinish=true;
        System.out.println("分析结果:"+answer);
    }

    @Override
    public boolean isFinish() {
        return false;
    }
}
