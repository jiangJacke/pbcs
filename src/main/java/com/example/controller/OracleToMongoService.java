package com.example.controller;

//import com.example.controller.OracleToMongoController;

import com.example.entity.Adsgold;
import com.example.entity.ImportTime;
import com.example.service.OracleToMongoController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author: jiangm
 * @Description:
 * @CreateDate: 2018/8/30 15:06
 * @Version: 空管PBCS平台事后分析系统
 */
@Controller
public class OracleToMongoService {
    @Autowired
    private OracleToMongoController oracleToMongoController;

    @Autowired
    private MongoTemplate mongoTemplate;

    private final Logger logger = LoggerFactory.getLogger(OracleToMongoService.class);

    @RequestMapping("/indexController")
    public String indexContrller(ImportTime importTime){
        //后台较验
        String startTime = importTime.getStartTime();
        String endTime = importTime.getEndTime();
        //...

        //根据时间段、筛选出Oracle数据
        logger.info("导入数据的起始时间为 "+startTime+"结束时间为 "+endTime);
        List<Object> list = oracleToMongoController.OracleImportMongodb(importTime);
        //将筛选出的数据导入到mongodb
        mongoTemplate.insert(list,Adsgold.class);
        logger.info("Oracle 导入mongodb 结束，此次共计导入 "+ list.size() +" 条");
        return "success";
    }

}
