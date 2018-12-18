package com.example.service;

import com.example.controller.Login;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 *@Author: jiangm
 *@Description: 仅测试数据库系统的192.168.204.36数据库是否能连通
 *@CreateDateDate: 2018/10/17_8:57
 *@Version: 数据库系统测试
 */
@RestController
public class ShujukuService {
    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    protected JdbcTemplate secondaryDataSource;

    private final Logger logger = LoggerFactory.getLogger(Login.class);

    public List shujukuTest(){
        List list = new ArrayList();
        String sql1 = "SELECT * FROM student";
        list = secondaryDataSource.queryForList(sql1);
        logger.info("共 "+list.size()+" 条数据");
        return list;
    }

}
