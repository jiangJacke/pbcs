package com.example.controller;

import com.example.service.ShujukuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 *@Author: jiangm
 *@Description: 测试知识库Oracle数据库是否能连通
 *@CreateDateDate: 2018/10/17_8:59
 *@Version: 知识库系统
 */
@Controller
public class ShujukuController {
    @Autowired
    private ShujukuService shujukuService;

    private final Logger logger = LoggerFactory.getLogger(ShujukuController.class);

//    @RequestMapping("/ShujuController")
    public void zhishiku(){

        List<Object> list = shujukuService.shujukuTest();
        //将筛选出的数据导入到mongodb
        logger.info("知识库测试 "+ list.size() +" 条");
        for (int i = 0; i <list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

}
