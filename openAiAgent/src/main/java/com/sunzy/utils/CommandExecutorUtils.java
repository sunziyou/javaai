package com.sunzy.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class CommandExecutorUtils {
    public static String executeCommand(String command) {
        Charset charset = System.getProperty("os.name").toLowerCase().contains("win") ? Charset.forName("GBK") : Charset.defaultCharset();
        List<String> commands = new ArrayList<>();
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            commands.add("cmd");
            commands.add("/c");
        } else {
            commands.add("/bin/sh");
            commands.add("-c");
        }
        commands.add(command);

        ProcessBuilder processBuilder = new ProcessBuilder(commands);
        Process process = null;
        try {
            process = processBuilder.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return readStream(process.getInputStream(), charset);
    }
    private static String readStream(InputStream inputStream, Charset charset) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, charset))) {
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
            String result = output.toString().trim();
            if(result.length()>2000){
                result=result.substring(0,2000);
            }
            return result;
        } catch (IOException e) {
            return "无法获取信息";
        }
    }

}

