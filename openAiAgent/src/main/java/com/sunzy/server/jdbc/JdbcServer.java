package com.sunzy.server.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class JdbcServer {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    //todo 此处可以直接根据自己业务返回对应数据
    public String queryAssetInfo(String ip){
        return "{\"hostIp\":\"\",\"assetName\":\"\",\"userName\":\"\",\"groupName\":\"\",\"osVersion\":\"\",\"safeAssetType\":\"\"}";
    }
    public List<String> queryBastionIp(){
        ArrayList<String> lists = new ArrayList<>();
        lists.add("192.168.1.154");
        lists.add("223.112.17.162");
        lists.add("127.0.0.1");
        return lists;
    }
}
