package com.sunzy.config;
import com.sunzy.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 配置文件配置
 *
 * @Author sunzy
 * @DATE Create in  2019/10/11 14:55
 * 注入到spring env 里不需要引用
 */
@Component("configProperties")
@PropertySource({"classpath:properties/db.properties","classpath:properties/system.properties"})
@SuppressWarnings("unused")
public class ConfigProperties {
    private static final Logger logger = LoggerFactory.getLogger(ConfigProperties.class);
    @Autowired
    private Environment env;

    public String getOrDefault(String key,String defaultValue){
        if(env.containsProperty(key)&&!StringUtils.isEmpty(env.getProperty(key))){
            return  env.getProperty(key);
        }
        return defaultValue;
    }



}
