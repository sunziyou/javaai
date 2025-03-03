package com.sunzy.utils;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.resource.ResourceUtil;
public class StringUtils {
    public static boolean isNotEmpty(CharSequence cs) {
        return !isEmpty(cs);
    }

    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }
    public static String readByResource(String fileName) {
        try (var inputStream = ResourceUtil.getStream(fileName)) {
            return IoUtil.readUtf8(inputStream);
        } catch (Exception e) {
            throw new RuntimeException("读取文件错误",e);
        }
    }

    public static void main(String[] args) {
        System.out.println(readByResource("systemPrompt.txt"));
    }

}

