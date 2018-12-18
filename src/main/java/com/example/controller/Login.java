package com.example.controller;

import com.example.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author: jiangm
 * @Description:
 * @CreateDate: 2018/9/20 14:21
 * @Version: 空管PBCS平台事后分析系统
 */
@Controller
public class Login {
    @Autowired
    MongoTemplate mongoTemplate;

    private final Logger logger = LoggerFactory.getLogger(Login.class);

    @RequestMapping(value = "/userLogin")
    public String userLogin(UserEntity userEntity){
        String username = userEntity.getUsername();
        String password = userEntity.getPassword();
        logger.info("登录用户名 "+username+"  密码 "+password);
        if(userEntity.getUsername() == "" || userEntity.getPassword() == ""){
            logger.info("用户名或密码为空！");
            return "login";
        }
        //在mongo里根据用户名、密码查询是否存在这个用户
        Criteria c1 = Criteria.where("username").is(username).and("password").is(password);
        List<UserEntity> user = mongoTemplate.find(new Query(c1), UserEntity.class, "user");
        if (user.size() > 0){
            return "index";
        }else{
            return "login";
        }
    }

}
