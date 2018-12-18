package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *@Author: jiangm
 *@Description:
 *@CreateDateDate: 2018/8/30_8:29
 *@Version: 空管PBCS平台事后分析系统
 */
@Controller
public class Freemarker {
    @Autowired
    private DataSource dataSource;

    public void getOracleCollection(){

        dataSource.getOracleTime();
    }

    @RequestMapping("/")
    public String Freemarker(){
        System.out.println("--------- 进入主页面 ------------");
        //用户名  密码

//        return "test1";
        return "";
    }

    @RequestMapping("/login")
    public String login(){
        System.out.println("--------- 登录操作 ------------");

        return "login";
    }

    @RequestMapping("/cpdlc")
    public String cpdlc(){
        System.out.println("--------- cpdlc ------------");

        return "cpdlc";
    }
}
