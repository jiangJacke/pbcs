package com.example.service;

import com.example.controller.OracleToMongoService;
import com.example.entity.Adsgold;
import com.example.entity.ImportTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: jiangm
 * @Description: Oracle 数据导入到 mongodb
 * @CreateDate: 2018/8/30 15:15
 * @Version: 空管PBCS平台事后分析系统
 */
@RestController
public class OracleToMongoController {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    protected JdbcTemplate secondaryDataSource;

    private final Logger logger = LoggerFactory.getLogger(OracleToMongoService.class);

    public List OracleImportMongodb(ImportTime importTime){
        List list = new ArrayList();
        String startTime = importTime.getStartTime();
        String endTime = importTime.getEndTime();
        //在mongo里按时间的查询，有则返回直接把mongo里这段数据删除，没有返回在Oracle里查询出来的数据
        Query query = new Query();
        Criteria criteria = Criteria.where("MSGDATE").gte(startTime).lt(endTime);
        query.addCriteria(criteria);
        long count = mongoTemplate.count(query, Adsgold.class, "adsgold");
        logger.info("有 "+count+" 条数据重复");
        if(count > 0){
            //有则返回直接把mongo里这段数据删除
            mongoTemplate.remove(query,"adsgold");
        }
        //在mongo里没有重复的数据则：返回在Oracle里查询出来的数据
        String sql="SELECT A.Atsp,A.An,A.Actype,A.Operater,A.Msgdate,A.Rgs,A.Msgtype,A.Lat,A.Lon,A.Sendtime,To_date(sendtime,'hh24-mi-ss'),To_date(dwnmsgtime,'hh24-mi-ss'),A.Transtime,A.Media FROM ADSGOLD A WHERE A.MSGDATE >="+ startTime +" AND A.MSGDATE <= "+ endTime;
        logger.info(sql);
        list = secondaryDataSource.queryForList(sql);
        logger.info("Oracle数据操作结束,在起始时间："+startTime+" 结束时间："+endTime+" 最新查询出："+list.size()+" 条数据");
        return list;
    }

}
