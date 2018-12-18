package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: jiangm
 * @Description: ADS-C分析与报告生成功能
 * @CreateDate: 2018/10/9 16:15
 * @Version: 空管PBCS平台事后分析系统
 */
@Controller
public class AdsGraphs {

    private final Logger logger = LoggerFactory.getLogger(Login.class);

    /**
     * 获取月份
     */
    @RequestMapping("/getAdsMonths")
    public void getAdsMonths(){

    }

    /**
     * 获取管制区
     */
    @RequestMapping("/getAdsAtsp")
    public void getAdsAtsp(){

    }

    /**
     * 获取媒体类型
     */
    @RequestMapping("/getAdsMedia")
    public void getAdsMedia(){

    }

    /**
     * 获取机型
     */
    @RequestMapping("/getAdsOpactype")
    public void getAdsOpactype(){

    }

      /**
     * 获取航空公司
     */
    @RequestMapping("/getAdsActype")
    public void getAdsActype(){

    }

    /**
     * 获取地面站
     */
    @RequestMapping("/getAdsMasrgs")
    public void getAdsMasrgs(){

    }

    /**
     * 表单提交返回json,集成图表
     */
    @RequestMapping("/AdsGraphs")
    public void AdsGraphs(){

    }

}
