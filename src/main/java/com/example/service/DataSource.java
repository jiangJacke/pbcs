package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author: jiangm
 * @Description:
 * @CreateDate: 2018/8/21 16:28
 * @Version: 空管PBCS平台事后分析系统
 */
@RestController
public class DataSource {
    @Autowired
    @Qualifier("primaryJdbcTemplate")
    protected JdbcTemplate primaryDataSource;

    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    protected JdbcTemplate secondaryDataSource;

    public void getOracleTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");
        Date data = new Date();
        System.out.println("----  test   begin!"+simpleDateFormat.format(data)+"-----");
        String sql="SELECT COUNT(1) FROM ADSGOLD";
        int count = secondaryDataSource.queryForObject(sql,Integer.class);
        System.out.println(count);


        System.out.println("----  test   begin!"+simpleDateFormat.format(data)+"-----");
        String contSql= "SELECT COUNT(*) FROM ADSGOLD";
    //    int count = secondaryDataSource.queryForObject(contSql,Integer.class);
        System.out.println("ADSGOLD共有 "+count+ " 条数据");

        System.out.println("查询起始时间为 "+simpleDateFormat.format(data));
    //    String sql="SELECT * FROM ADSGOLD";
        List adsgold = secondaryDataSource.queryForList(sql);
        System.out.println("查询结束时间为 "+simpleDateFormat.format(data));

        System.out.println("遍历结果为 ");
        for (int i = 0; i <adsgold.size(); i++) {
            System.out.println(adsgold.get(i));
        }
        System.out.println("-----  test  end "+simpleDateFormat.format(data)+" -----");
    }

    public void getCxzdb(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");
        Date date = new Date();
        System.out.println("----  test   begin!"+simpleDateFormat.format(date)+"-----");
        String sql="SELECT COUNT(1) FROM ADSGOLD";
        int count = secondaryDataSource.queryForObject(sql,Integer.class);
        System.out.println("ADSGOLD共有 "+count+ " 条数据");
        System.out.println("查询起始时间为 "+simpleDateFormat.format(date));
        String querySql="SELECT * FROM ADSGOLD WHERE ROWNUM < 650000";
        List adsgold = secondaryDataSource.queryForList(querySql);
        Date data1 = new Date();
        System.out.println("查询结束时间为 "+simpleDateFormat.format(data1));
        System.out.println(adsgold.size());

        Date data2 = new Date();
        System.out.println("遍历结果开始时间为 "+simpleDateFormat.format(data2));
        System.out.println("遍历结果为 ");
        for (int i = 0; i <adsgold.size(); i++) {
            System.out.println(adsgold.get(i));
        }
        Date data3 = new Date();
        System.out.println("遍历结果结束时间为 "+simpleDateFormat.format(data3));
        /*System.out.println("----LHD primaryDataSource  test"+simpleDateFormat.format(data)+"-----");
        String sql1="SELECT COUNT(*) FROM TEST1";
        int count1 = primaryDataSource.queryForObject(sql1,Integer.class);
        System.out.println(count1);*/
        Date data4 = new Date();
        System.out.println("-----  test  end "+simpleDateFormat.format(data4)+" -----");
    }
}
