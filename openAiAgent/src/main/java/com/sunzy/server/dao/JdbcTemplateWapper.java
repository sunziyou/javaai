package com.sunzy.server.dao;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * @Author sunzy
 * @DATE Create in  2019/12/9 10:16
 */
@Component("jdbcTemplateWapper")
public class JdbcTemplateWapper {
    @Autowired
    Environment env;

    @Bean
    public JdbcTemplate initJdbc() {
		HikariConfig hikariConfig = new HikariConfig();
		//        hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/mydata");//mysql
		hikariConfig.setJdbcUrl(getConfig("h2.url"));//oracle
		hikariConfig.setDriverClassName(getConfig("h2.drive"));
		hikariConfig.setUsername(getConfig("h2.username"));
		hikariConfig.setPassword(getConfig("h2.password"));
		hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
		hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
		hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
		HikariDataSource ds = new HikariDataSource(hikariConfig);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
        return jdbcTemplate;
    }

    private String getConfig(String key) {

        return env.getProperty(key);
    }

    @Bean("datasource")
    public DataSource getDataSource(){
    	return new HikariDataSource();
	}

}
