package com.example.service;

import com.example.controller.Login;
import com.example.entity.CpdlcEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: jiangm
 * @Description:
 * @CreateDate: 2018/10/10 16:29
 * @Version: 空管PBCS平台事后分析系统
 */
@RestController
public class CpdlcGraphsService {
    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    protected JdbcTemplate secondaryDataSource;

    private final Logger logger = LoggerFactory.getLogger(Login.class);

    public List getParameter(String type){
        List list = new ArrayList();
        if ("1".equals(type)){
            String sql1 = "SELECT distinct(substr(MSGDATE,1,6))  as MSGDATE FROM cpdlcgold order by MSGDATE desc";
            list = secondaryDataSource.queryForList(sql1);
            logger.info("获取月份：共 "+list.size()+" 条数据");
        }else if ("2".equals(type)){
            String sql2 = "SELECT DISTINCT ATSP FROM cpdlcgold order by ATSP";
            list = secondaryDataSource.queryForList(sql2);
            logger.info("获取管制区:共 "+list.size()+" 条数据");
        }else if("3".equals(type)){
            String sql3 = "SELECT  distinct MEDIA  FROM cpdlcgold order by MEDIA";
            list = secondaryDataSource.queryForList(sql3);
            logger.info("获取媒体类型:共 "+list.size()+" 条数据");
        }else if("4".equals(type)){
            String sql4 = "SELECT distinct ACTYPE FROM cpdlcgold order by ACTYPE";
            list = secondaryDataSource.queryForList(sql4);
            logger.info("获取机型：共 "+list.size()+" 条数据");
        }else if("5".equals(type)){
            String sql5 = "SELECT distinct OPERATER FROM cpdlcgold order by OPERATER";
            list = secondaryDataSource.queryForList(sql5);
            logger.info("获取航空公司：共 "+list.size()+" 条数据");
        }else if ("6".equals(type)){
            String sql6 = "SELECT distinct MASRGS FROM cpdlcgold order by MASRGS";
            list = secondaryDataSource.queryForList(sql6);
            logger.info("获取地面站：共 "+list.size()+" 条数据");
        }
        return list;
    }

    public Map getCpdlcMsgdate (CpdlcEntity cpdlcEntity,String[] num){
        //航空公司
        String operater = cpdlcEntity.getOPERATER();
        //航空器类型
        String actype = cpdlcEntity.getActype();
        //媒体类型
        String media = cpdlcEntity.getMedia();
        //地面站
        String masrgs = cpdlcEntity.getMasrgs();
        //管制区
        String atsp = cpdlcEntity.getAtsp();
        //RCP标准
        String standard = cpdlcEntity.getStandard();
        //时间标准
        String timeStandard = cpdlcEntity.getTimeStandard();
        //起始时间
        String beginDate = cpdlcEntity.getBeginDate();
        //结束时间
        String endinDate = cpdlcEntity.getEndDate();

        Map map = new HashMap();
        Integer AllSize=0;  //未过滤后的（过滤条件为：少于19列且等于19列，MEDIA不能为空）
        Integer AllSize1=0;  //过滤后的
        Integer x = null;

        double Percentage_x1=0;
        double Percentage_x2=0;
        String [] All = new String[num.length+10];
        String [] month = new String[num.length];
        String[][] cont = new String[num.length][2];

        for (int i = 0; i < num.length; i++) {  //选择几个月份就有几个线段
            String SqlAll = "SELECT ATSP,AN,ACTYPE,OPERATER,MSGDATE,MASRGS,OPSRGS,UPMSGTIME,MASTIME,MASTRIP,FMSTIME,DWNMSGTIME,ACPS,DOWNTIME,UPID,DWNID,ACTP,ACP,PORT,MEDIA FROM CPDLCGOLD WHERE  MEDIA is not null";
            String sql = "SELECT ACTP,COUNT(1) AS COUNT FROM CPDLCGOLD WHERE  1 = 1 ";
//            String sql = "SELECT ACTP,COUNT(1) AS COUNT FROM " +
//                    "(SELECT * FROM CPDLCGOLD WHERE 1=1 AND ATSP IS NOT NULL AND AN IS NOT NULL AND ACTYPE IS NOT NULL AND OPERATER IS NOT NULL AND MSGDATE IS NOT NULL AND MASRGS IS NOT NULL AND OPSRGS IS NOT NULL " +
//                    " AND UPMSGTIME IS NOT NULL AND MASTRIP IS NOT NULL AND FMSTIME IS NOT NULL AND DWNMSGTIME IS NOT NULL AND ACPS IS NOT NULL AND DOWNTIME IS NOT NULL AND UPID IS NOT NULL AND DWNID IS NOT NULL AND ACTP IS NOT NULL " +
//                    " AND ACP IS NOT NULL AND PORT IS NOT NULL AND MEDIA IS NOT NULL AND FI IS NOT NULL AND UFI IS NOT NULL)" +
//                    " WHERE  1 = 1 ";
            if (operater != "" && operater != null){    //航空公司
                SqlAll += " AND OPERATER LIKE '"+operater;
                sql += " AND OPERATER LIKE '"+operater;
            }
            if (actype != "" && actype != null){    //航空器类型
                SqlAll += " AND ACTYPE LIKE '"+actype;
                sql += " AND ACTYPE LIKE '"+actype;
            }
            if (media != "" && media != null){    //媒体类型
                SqlAll += " AND MEDIA LIKE '"+media;
                sql += " AND MEDIA LIKE '"+media;
            }
            if (masrgs != "" && masrgs != null){    //地面站
                SqlAll += " AND MEDIA LIKE '"+masrgs;
                sql += " AND MEDIA LIKE '"+masrgs;
            }
            if (atsp != "" && atsp != null){    //管制区
                SqlAll += " AND MEDIA LIKE '"+atsp;
                sql += " AND MEDIA LIKE '"+atsp;
            }
            if (beginDate != "" && beginDate != null){    //起始时间
                SqlAll += " AND FMSTIME LIKE '"+beginDate;
                sql += " AND  FMSTIME '"+beginDate;
            }
            if (endinDate != "" && endinDate != null){    //结束时间
                SqlAll += " AND DWNMSGTIME LIKE '"+endinDate;
                sql += " AND DWNMSGTIME LIKE '"+endinDate;
            }
            if(num[i] != "" && num[i]!=null){
                SqlAll+=" AND MSGDATE LIKE '"+num[i]+"%'";
                sql += " AND MSGDATE like '"+num[i]+"%' group by ACTP ";
            }
            logger.info(sql);
            logger.info(SqlAll);
            List<Map<String, Object>> query1 = secondaryDataSource.queryForList(SqlAll);
            List<Map<String, Object>> lessList = secondaryDataSource.queryForList(sql);
            for (int j = 0; j < query1.size(); j++) {  //统计过滤后的总数与未过滤后的总数
                int actpSize = 0;
                AllSize++;  //未过滤的总数
                if (query1.get(j).get("ATSP") != "" && query1.get(j).get("ATSP") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("AN") != "" && query1.get(j).get("AN") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("ACTYPE") != "" && query1.get(j).get("ACTYPE") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("OPERATER") != "" && query1.get(j).get("OPERATER") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("MSGDATE") != "" && query1.get(j).get("MSGDATE") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("MASRGS") != "" && query1.get(j).get("MASRGS") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("OPSRGS") != "" && query1.get(j).get("OPSRGS") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("UPMSGTIME") != "" && query1.get(j).get("UPMSGTIME") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("MASTIME") != "" && query1.get(j).get("MASTIME") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("MASTRIP") != "" && query1.get(j).get("MASTRIP") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("FMSTIME") != "" && query1.get(j).get("FMSTIME") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("DWNMSGTIME") != "" && query1.get(j).get("DWNMSGTIME") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("ACPS") != "" && query1.get(j).get("ACPS") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("DOWNTIME") != "" && query1.get(j).get("DOWNTIME") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("UPID") != "" && query1.get(j).get("UPID") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("DWNID") != "" && query1.get(j).get("DWNID") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("ACTP") != "" && query1.get(j).get("ACTP") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("ACP") != "" && query1.get(j).get("ACP") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("PORT") != "" && query1.get(j).get("PORT") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("MEDIA") != "" && query1.get(j).get("MEDIA") != null) {
                    actpSize++;
                }
                if ((actpSize >= 19) && (query1.get(j).get("MEDIA") != null)) {
                    AllSize1 += 1; //过滤后的总数
                }
            }
            for (int k = 0; k < query1.size(); k++) {
                if (timeStandard.equals("ACP")){

                }else if(timeStandard.equals("ACTP")){  //统计240标准与400标准 分子
                    if (standard.equals("240")){          //240标准
                        // 时间标准ACTP
                        if (Integer.parseInt(String.valueOf(query1.get(k).get("ACTP")))<150){
                            Percentage_x1++;    //统计99.9%
                        }
                        if (Integer.parseInt(String.valueOf(query1.get(k).get("ACTP")))<120){
                            Percentage_x2++;    //统计95%
                        }
                    }else if (standard.equals("400")){    //400标准
                        // 时间标准ACTP
                        if (Integer.parseInt(String.valueOf(query1.get(k).get("ACTP")))<310){
                            Percentage_x1++;    //统计99.9%
                        }
                        if (Integer.parseInt(String.valueOf(query1.get(k).get("ACTP")))<260){
                            Percentage_x2++;    //统计99.5%
                        }
                    }
                }
            }

                for (int j1 = 0; j1 < lessList.size(); j1++) {  //统计Json数组
                    //线段
                    Integer actp = Integer.parseInt(String.valueOf(lessList.get(j1).get("actp")));
                    DecimalFormat df = new DecimalFormat("0.00000");
                    x = Integer.parseInt(String.valueOf(lessList.get(j1).get("actp")));
                    double sum = 0.0;//统计小于等于ACP的个数
                    for (int l = 0; l < lessList.size(); l++) { //统计小于等于actp的个数
                        Integer actp1 = Integer.parseInt(String.valueOf(lessList.get(l).get("actp")));
                        if (actp1 <= actp){
                            sum += Integer.parseInt(String.valueOf(lessList.get(l).get("count")));
                        }
                    }
                    String y = df.format((float)sum/AllSize*100);
                    if(j1 == lessList.size()-1){
                        if(month[i] == null){
                            month[i]=x+","+y;
                        }else if(month[i] != null){
                            month[i] = month[i]+x+","+y;
                        }
                    }else {
                        if(month[i] != null){
                            month[i] = month[i]+x+","+y+",";
                        }else {
                            month[i] = x+","+y+",";
                        }

                    }
                }
                    //百分比值
                    DecimalFormat df1 = new DecimalFormat("0.0000");
                    cont[i][0] = df1.format((double)Percentage_x1/AllSize1 * 100);
                    cont[i][1] = df1.format((double)Percentage_x2/AllSize1 * 100);
            }

            map.put("month",month);
            map.put("cont1",cont[0][0]);
            map.put("cont2",cont[0][1]);
            return map;
    }

    //管制区
    public Map getCpdlcAtsp(CpdlcEntity cpdlcEntity, String[] num) {
        //航空公司
        String operater = cpdlcEntity.getOPERATER();
        //航空器类型
        String actype = cpdlcEntity.getActype();
        //媒体类型
        String media = cpdlcEntity.getMedia();
        //地面站
        String masrgs = cpdlcEntity.getMasrgs();
        //管制区
        String atsp = cpdlcEntity.getAtsp();
        //RCP标准
        String standard = cpdlcEntity.getStandard();
        //时间标准
        String timeStandard = cpdlcEntity.getTimeStandard();
        //起始时间
        String beginDate = cpdlcEntity.getBeginDate();
        //结束时间
        String endinDate = cpdlcEntity.getEndDate();

        Map map = new HashMap();
        Integer AllSize=0;  //未过滤后的（过滤条件为：少于19列且等于19列，MEDIA不能为空）
        Integer AllSize1=0;  //过滤后的
        Integer x = null;

        double Percentage_x1=0;
        double Percentage_x2=0;
        String [] All = new String[num.length+10];
        String [] month = new String[num.length];
        String[][] cont = new String[num.length][2];

        for (int i = 0; i < num.length; i++) {  //选择几个月份就有几个线段
            String SqlAll = "SELECT ATSP,AN,ACTYPE,OPERATER,MSGDATE,MASRGS,OPSRGS,UPMSGTIME,MASTIME,MASTRIP,FMSTIME,DWNMSGTIME,ACPS,DOWNTIME,UPID,DWNID,ACTP,ACP,PORT,MEDIA FROM CPDLCGOLD WHERE  MEDIA is not null";
            String sql = "SELECT ACTP,COUNT(1) AS COUNT FROM CPDLCGOLD WHERE  1 = 1 ";
//            String sql = "SELECT ACTP,COUNT(1) AS COUNT FROM " +
//                    "(SELECT * FROM CPDLCGOLD WHERE 1=1 AND ATSP IS NOT NULL AND AN IS NOT NULL AND ACTYPE IS NOT NULL AND OPERATER IS NOT NULL AND MSGDATE IS NOT NULL AND MASRGS IS NOT NULL AND OPSRGS IS NOT NULL " +
//                    " AND UPMSGTIME IS NOT NULL AND MASTRIP IS NOT NULL AND FMSTIME IS NOT NULL AND DWNMSGTIME IS NOT NULL AND ACPS IS NOT NULL AND DOWNTIME IS NOT NULL AND UPID IS NOT NULL AND DWNID IS NOT NULL AND ACTP IS NOT NULL " +
//                    " AND ACP IS NOT NULL AND PORT IS NOT NULL AND MEDIA IS NOT NULL AND FI IS NOT NULL AND UFI IS NOT NULL)" +
//                    " WHERE  1 = 1 ";
            if (operater != "" && operater != null){    //航空公司
                SqlAll += " AND OPERATER LIKE '"+operater;
                sql += " AND OPERATER LIKE '"+operater;
            }
            if (actype != "" && actype != null){    //航空器类型
                SqlAll += " AND ACTYPE LIKE '"+actype;
                sql += " AND ACTYPE LIKE '"+actype;
            }
            if (media != "" && media != null){    //媒体类型
                SqlAll += " AND MEDIA LIKE '"+media;
                sql += " AND MEDIA LIKE '"+media;
            }
            if (masrgs != "" && masrgs != null){    //地面站
                SqlAll += " AND MEDIA LIKE '"+masrgs;
                sql += " AND MEDIA LIKE '"+masrgs;
            }
            if (atsp != "" && atsp != null){    //管制区
                SqlAll += " AND MEDIA LIKE '"+atsp;
                sql += " AND MEDIA LIKE '"+atsp;
            }
            if (beginDate != "" && beginDate != null){    //起始时间
                SqlAll += " AND FMSTIME LIKE '"+beginDate;
                sql += " AND  FMSTIME '"+beginDate;
            }
            if (endinDate != "" && endinDate != null){    //结束时间
                SqlAll += " AND DWNMSGTIME LIKE '"+endinDate;
                sql += " AND DWNMSGTIME LIKE '"+endinDate;
            }
            if(num[i] != "" && num[i]!=null){
                SqlAll+=" AND ATSP LIKE '"+num[i]+"%'";
                sql += " AND ATSP like '"+num[i]+"%' group by ACTP ";
            }
            logger.info(sql);
            logger.info(SqlAll);
            List<Map<String, Object>> query1 = secondaryDataSource.queryForList(SqlAll);
            List<Map<String, Object>> lessList = secondaryDataSource.queryForList(sql);
            for (int j = 0; j < query1.size(); j++) {  //统计过滤后的总数与未过滤后的总数
                int actpSize = 0;
                AllSize++;  //未过滤的总数
                if (query1.get(j).get("ATSP") != "" && query1.get(j).get("ATSP") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("AN") != "" && query1.get(j).get("AN") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("ACTYPE") != "" && query1.get(j).get("ACTYPE") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("OPERATER") != "" && query1.get(j).get("OPERATER") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("MSGDATE") != "" && query1.get(j).get("MSGDATE") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("MASRGS") != "" && query1.get(j).get("MASRGS") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("OPSRGS") != "" && query1.get(j).get("OPSRGS") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("UPMSGTIME") != "" && query1.get(j).get("UPMSGTIME") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("MASTIME") != "" && query1.get(j).get("MASTIME") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("MASTRIP") != "" && query1.get(j).get("MASTRIP") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("FMSTIME") != "" && query1.get(j).get("FMSTIME") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("DWNMSGTIME") != "" && query1.get(j).get("DWNMSGTIME") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("ACPS") != "" && query1.get(j).get("ACPS") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("DOWNTIME") != "" && query1.get(j).get("DOWNTIME") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("UPID") != "" && query1.get(j).get("UPID") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("DWNID") != "" && query1.get(j).get("DWNID") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("ACTP") != "" && query1.get(j).get("ACTP") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("ACP") != "" && query1.get(j).get("ACP") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("PORT") != "" && query1.get(j).get("PORT") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("MEDIA") != "" && query1.get(j).get("MEDIA") != null) {
                    actpSize++;
                }
                if ((actpSize >= 19) && (query1.get(j).get("MEDIA") != null)) {
                    AllSize1 += 1; //过滤后的总数
                }
            }
            for (int k = 0; k < query1.size(); k++) {
                if (timeStandard.equals("ACP")){

                }else if(timeStandard.equals("ACTP")){  //统计240标准与400标准 分子
                    if (standard.equals("240")){          //240标准
                        // 时间标准ACTP
                        if (Integer.parseInt(String.valueOf(query1.get(k).get("ACTP")))<150){
                            Percentage_x1++;    //统计99.9%
                        }
                        if (Integer.parseInt(String.valueOf(query1.get(k).get("ACTP")))<120){
                            Percentage_x2++;    //统计95%
                        }
                    }else if (standard.equals("400")){    //400标准
                        // 时间标准ACTP
                        if (Integer.parseInt(String.valueOf(query1.get(k).get("ACTP")))<310){
                            Percentage_x1++;    //统计99.9%
                        }
                        if (Integer.parseInt(String.valueOf(query1.get(k).get("ACTP")))<260){
                            Percentage_x2++;    //统计99.5%
                        }
                    }
                }
            }

            for (int j1 = 0; j1 < lessList.size(); j1++) {  //统计Json数组
                //线段
                Integer actp = Integer.parseInt(String.valueOf(lessList.get(j1).get("actp")));
                DecimalFormat df = new DecimalFormat("0.00000");
                x = Integer.parseInt(String.valueOf(lessList.get(j1).get("actp")));
                double sum = 0.0;//统计小于等于ACP的个数
                for (int l = 0; l < lessList.size(); l++) { //统计小于等于actp的个数
                    Integer actp1 = Integer.parseInt(String.valueOf(lessList.get(l).get("actp")));
                    if (actp1 <= actp){
                        sum += Integer.parseInt(String.valueOf(lessList.get(l).get("count")));
                    }
                }
                String y = df.format((float)sum/AllSize*100);
                if(j1 == lessList.size()-1){
                    if(month[i] == null){
                        month[i]=x+","+y;
                    }else if(month[i] != null){
                        month[i] = month[i]+x+","+y;
                    }
                }else {
                    if(month[i] != null){
                        month[i] = month[i]+x+","+y+",";
                    }else {
                        month[i] = x+","+y+",";
                    }

                }
            }
            //百分比值
            DecimalFormat df1 = new DecimalFormat("0.0000");
            cont[i][0] = df1.format((double)Percentage_x1/AllSize1 * 100);
            cont[i][1] = df1.format((double)Percentage_x2/AllSize1 * 100);
        }

        map.put("month",month);
        map.put("cont1",cont[0][0]);
        map.put("cont2",cont[0][1]);
        return map;

    }

    //媒体类型
    public Map getCpdlcMedia(CpdlcEntity cpdlcEntity, String[] num) {
        //航空公司
        String operater = cpdlcEntity.getOPERATER();
        //航空器类型
        String actype = cpdlcEntity.getActype();
        //媒体类型
        String media = cpdlcEntity.getMedia();
        //地面站
        String masrgs = cpdlcEntity.getMasrgs();
        //管制区
        String atsp = cpdlcEntity.getAtsp();
        //RCP标准
        String standard = cpdlcEntity.getStandard();
        //时间标准
        String timeStandard = cpdlcEntity.getTimeStandard();
        //起始时间
        String beginDate = cpdlcEntity.getBeginDate();
        //结束时间
        String endinDate = cpdlcEntity.getEndDate();

        Map map = new HashMap();
        List list = new ArrayList();
        Integer AllSize=0;  //未过滤后的（过滤条件为：少于19列且等于19列，MEDIA不能为空）
        Integer AllSize1=0;  //过滤后的
        Integer x = null;

        double Percentage_x1=0;
        double Percentage_x2=0;
        String [] All = new String[num.length+10];
        String [] month = new String[num.length];
        String[][] cont = new String[num.length][2];

        for (int i = 0; i < num.length; i++) {  //选择几个月份就有几个线段
            String SqlAll = "SELECT ATSP,AN,ACTYPE,OPERATER,MSGDATE,MASRGS,OPSRGS,UPMSGTIME,MASTIME,MASTRIP,FMSTIME,DWNMSGTIME,ACPS,DOWNTIME,UPID,DWNID,ACTP,ACP,PORT,MEDIA FROM CPDLCGOLD WHERE  MEDIA is not null";
            String sql = "SELECT ACTP,COUNT(1) AS COUNT FROM CPDLCGOLD WHERE  1 = 1 ";
//            String sql = "SELECT ACTP,COUNT(1) AS COUNT FROM " +
//                    "(SELECT * FROM CPDLCGOLD WHERE 1=1 AND ATSP IS NOT NULL AND AN IS NOT NULL AND ACTYPE IS NOT NULL AND OPERATER IS NOT NULL AND MSGDATE IS NOT NULL AND MASRGS IS NOT NULL AND OPSRGS IS NOT NULL " +
//                    " AND UPMSGTIME IS NOT NULL AND MASTRIP IS NOT NULL AND FMSTIME IS NOT NULL AND DWNMSGTIME IS NOT NULL AND ACPS IS NOT NULL AND DOWNTIME IS NOT NULL AND UPID IS NOT NULL AND DWNID IS NOT NULL AND ACTP IS NOT NULL " +
//                    " AND ACP IS NOT NULL AND PORT IS NOT NULL AND MEDIA IS NOT NULL AND FI IS NOT NULL AND UFI IS NOT NULL)" +
//                    " WHERE  1 = 1 ";
            if (operater != "" && operater != null){    //航空公司
                SqlAll += " AND OPERATER LIKE '"+operater;
                sql += " AND OPERATER LIKE '"+operater;
            }
            if (actype != "" && actype != null){    //航空器类型
                SqlAll += " AND ACTYPE LIKE '"+actype;
                sql += " AND ACTYPE LIKE '"+actype;
            }
            if (media != "" && media != null){    //媒体类型
                SqlAll += " AND MEDIA LIKE '"+media;
                sql += " AND MEDIA LIKE '"+media;
            }
            if (masrgs != "" && masrgs != null){    //地面站
                SqlAll += " AND MEDIA LIKE '"+masrgs;
                sql += " AND MEDIA LIKE '"+masrgs;
            }
            if (atsp != "" && atsp != null){    //管制区
                SqlAll += " AND MEDIA LIKE '"+atsp;
                sql += " AND MEDIA LIKE '"+atsp;
            }
            if (beginDate != "" && beginDate != null){    //起始时间
                SqlAll += " AND FMSTIME LIKE '"+beginDate;
                sql += " AND  FMSTIME '"+beginDate;
            }
            if (endinDate != "" && endinDate != null){    //结束时间
                SqlAll += " AND DWNMSGTIME LIKE '"+endinDate;
                sql += " AND DWNMSGTIME LIKE '"+endinDate;
            }
            if(num[i] != "" && num[i]!=null){
                SqlAll+=" AND MEDIA LIKE '"+num[i]+"%'";
                sql += " AND MEDIA like '"+num[i]+"%' group by ACTP ";
            }
            logger.info(sql);
            logger.info(SqlAll);
            List<Map<String, Object>> query1 = secondaryDataSource.queryForList(SqlAll);
            List<Map<String, Object>> lessList = secondaryDataSource.queryForList(sql);
            for (int j = 0; j < query1.size(); j++) {  //统计过滤后的总数与未过滤后的总数
                int actpSize = 0;
                AllSize++;  //未过滤的总数
                if (query1.get(j).get("ATSP") != "" && query1.get(j).get("ATSP") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("AN") != "" && query1.get(j).get("AN") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("ACTYPE") != "" && query1.get(j).get("ACTYPE") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("OPERATER") != "" && query1.get(j).get("OPERATER") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("MSGDATE") != "" && query1.get(j).get("MSGDATE") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("MASRGS") != "" && query1.get(j).get("MASRGS") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("OPSRGS") != "" && query1.get(j).get("OPSRGS") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("UPMSGTIME") != "" && query1.get(j).get("UPMSGTIME") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("MASTIME") != "" && query1.get(j).get("MASTIME") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("MASTRIP") != "" && query1.get(j).get("MASTRIP") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("FMSTIME") != "" && query1.get(j).get("FMSTIME") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("DWNMSGTIME") != "" && query1.get(j).get("DWNMSGTIME") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("ACPS") != "" && query1.get(j).get("ACPS") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("DOWNTIME") != "" && query1.get(j).get("DOWNTIME") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("UPID") != "" && query1.get(j).get("UPID") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("DWNID") != "" && query1.get(j).get("DWNID") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("ACTP") != "" && query1.get(j).get("ACTP") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("ACP") != "" && query1.get(j).get("ACP") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("PORT") != "" && query1.get(j).get("PORT") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("MEDIA") != "" && query1.get(j).get("MEDIA") != null) {
                    actpSize++;
                }
                if ((actpSize >= 19) && (query1.get(j).get("MEDIA") != null)) {
                    AllSize1 += 1; //过滤后的总数
                }
            }
            for (int k = 0; k < query1.size(); k++) {
                if (timeStandard.equals("ACP")){

                }else if(timeStandard.equals("ACTP")){  //统计240标准与400标准 分子
                    if (standard.equals("240")){          //240标准
                        // 时间标准ACTP
                        if (Integer.parseInt(String.valueOf(query1.get(k).get("ACTP")))<150){
                            Percentage_x1++;    //统计99.9%
                        }
                        if (Integer.parseInt(String.valueOf(query1.get(k).get("ACTP")))<120){
                            Percentage_x2++;    //统计95%
                        }
                    }else if (standard.equals("400")){    //400标准
                        // 时间标准ACTP
                        if (Integer.parseInt(String.valueOf(query1.get(k).get("ACTP")))<310){
                            Percentage_x1++;    //统计99.9%
                        }
                        if (Integer.parseInt(String.valueOf(query1.get(k).get("ACTP")))<260){
                            Percentage_x2++;    //统计99.5%
                        }
                    }
                }
            }

            for (int j1 = 0; j1 < lessList.size(); j1++) {  //统计Json数组
                //线段
                Integer actp = Integer.parseInt(String.valueOf(lessList.get(j1).get("actp")));
                DecimalFormat df = new DecimalFormat("0.00000");
                x = Integer.parseInt(String.valueOf(lessList.get(j1).get("actp")));
                double sum = 0.0;//统计小于等于ACP的个数
                for (int l = 0; l < lessList.size(); l++) { //统计小于等于actp的个数
                    Integer actp1 = Integer.parseInt(String.valueOf(lessList.get(l).get("actp")));
                    if (actp1 <= actp){
                        sum += Integer.parseInt(String.valueOf(lessList.get(l).get("count")));
                    }
                }
                String y = df.format((float)sum/AllSize*100);
                if(j1 == lessList.size()-1){
                    if(month[i] == null){
                        month[i]=x+","+y;
                    }else if(month[i] != null){
                        month[i] = month[i]+x+","+y;
                    }
                }else {
                    if(month[i] != null){
                        month[i] = month[i]+x+","+y+",";
                    }else {
                        month[i] = x+","+y+",";
                    }

                }
            }
            //百分比值
            DecimalFormat df1 = new DecimalFormat("0.0000");
            cont[i][0] = df1.format((double)Percentage_x1/AllSize1 * 100);
            cont[i][1] = df1.format((double)Percentage_x2/AllSize1 * 100);
        }

        map.put("month",month);
        map.put("cont1",cont[0][0]);
        map.put("cont2",cont[0][1]);
        return map;
    }

    //机型
    public Map getCpdlcOpactype(CpdlcEntity cpdlcEntity, String[] num) {
        //航空公司
        String operater = cpdlcEntity.getOPERATER();
        //航空器类型
        String actype = cpdlcEntity.getActype();
        //媒体类型
        String media = cpdlcEntity.getMedia();
        //地面站
        String masrgs = cpdlcEntity.getMasrgs();
        //管制区
        String atsp = cpdlcEntity.getAtsp();
        //RCP标准
        String standard = cpdlcEntity.getStandard();
        //时间标准
        String timeStandard = cpdlcEntity.getTimeStandard();
        //起始时间
        String beginDate = cpdlcEntity.getBeginDate();
        //结束时间
        String endinDate = cpdlcEntity.getEndDate();

        Map map = new HashMap();
        List list = new ArrayList();
        Integer AllSize=0;  //未过滤后的（过滤条件为：少于19列且等于19列，MEDIA不能为空）
        Integer AllSize1=0;  //过滤后的
        Integer x = null;

        double Percentage_x1=0;
        double Percentage_x2=0;
        String [] All = new String[num.length+10];
        String [] month = new String[num.length];
        String[][] cont = new String[num.length][2];

        for (int i = 0; i < num.length; i++) {  //选择几个月份就有几个线段
            String SqlAll = "SELECT ATSP,AN,ACTYPE,OPERATER,MSGDATE,MASRGS,OPSRGS,UPMSGTIME,MASTIME,MASTRIP,FMSTIME,DWNMSGTIME,ACPS,DOWNTIME,UPID,DWNID,ACTP,ACP,PORT,MEDIA FROM CPDLCGOLD WHERE  MEDIA is not null";
            String sql = "SELECT ACTP,COUNT(1) AS COUNT FROM CPDLCGOLD WHERE  1 = 1 ";
//            String sql = "SELECT ACTP,COUNT(1) AS COUNT FROM " +
//                    "(SELECT * FROM CPDLCGOLD WHERE 1=1 AND ATSP IS NOT NULL AND AN IS NOT NULL AND ACTYPE IS NOT NULL AND OPERATER IS NOT NULL AND MSGDATE IS NOT NULL AND MASRGS IS NOT NULL AND OPSRGS IS NOT NULL " +
//                    " AND UPMSGTIME IS NOT NULL AND MASTRIP IS NOT NULL AND FMSTIME IS NOT NULL AND DWNMSGTIME IS NOT NULL AND ACPS IS NOT NULL AND DOWNTIME IS NOT NULL AND UPID IS NOT NULL AND DWNID IS NOT NULL AND ACTP IS NOT NULL " +
//                    " AND ACP IS NOT NULL AND PORT IS NOT NULL AND MEDIA IS NOT NULL AND FI IS NOT NULL AND UFI IS NOT NULL)" +
//                    " WHERE  1 = 1 ";
            if (operater != "" && operater != null){    //航空公司
                SqlAll += " AND OPERATER LIKE '"+operater;
                sql += " AND OPERATER LIKE '"+operater;
            }
            if (actype != "" && actype != null){    //航空器类型
                SqlAll += " AND ACTYPE LIKE '"+actype;
                sql += " AND ACTYPE LIKE '"+actype;
            }
            if (media != "" && media != null){    //媒体类型
                SqlAll += " AND MEDIA LIKE '"+media;
                sql += " AND MEDIA LIKE '"+media;
            }
            if (masrgs != "" && masrgs != null){    //地面站
                SqlAll += " AND MEDIA LIKE '"+masrgs;
                sql += " AND MEDIA LIKE '"+masrgs;
            }
            if (atsp != "" && atsp != null){    //管制区
                SqlAll += " AND MEDIA LIKE '"+atsp;
                sql += " AND MEDIA LIKE '"+atsp;
            }
            if (beginDate != "" && beginDate != null){    //起始时间
                SqlAll += " AND FMSTIME LIKE '"+beginDate;
                sql += " AND  FMSTIME '"+beginDate;
            }
            if (endinDate != "" && endinDate != null){    //结束时间
                SqlAll += " AND DWNMSGTIME LIKE '"+endinDate;
                sql += " AND DWNMSGTIME LIKE '"+endinDate;
            }
            if(num[i] != "" && num[i]!=null){
                SqlAll+=" AND ACTYPE LIKE '"+num[i]+"%'";
                sql += " AND ACTYPE like '"+num[i]+"%' group by ACTP ";
            }
            logger.info(sql);
            logger.info(SqlAll);
            List<Map<String, Object>> query1 = secondaryDataSource.queryForList(SqlAll);
            List<Map<String, Object>> lessList = secondaryDataSource.queryForList(sql);
            for (int j = 0; j < query1.size(); j++) {  //统计过滤后的总数与未过滤后的总数
                int actpSize = 0;
                AllSize++;  //未过滤的总数
                if (query1.get(j).get("ATSP") != "" && query1.get(j).get("ATSP") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("AN") != "" && query1.get(j).get("AN") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("ACTYPE") != "" && query1.get(j).get("ACTYPE") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("OPERATER") != "" && query1.get(j).get("OPERATER") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("MSGDATE") != "" && query1.get(j).get("MSGDATE") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("MASRGS") != "" && query1.get(j).get("MASRGS") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("OPSRGS") != "" && query1.get(j).get("OPSRGS") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("UPMSGTIME") != "" && query1.get(j).get("UPMSGTIME") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("MASTIME") != "" && query1.get(j).get("MASTIME") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("MASTRIP") != "" && query1.get(j).get("MASTRIP") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("FMSTIME") != "" && query1.get(j).get("FMSTIME") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("DWNMSGTIME") != "" && query1.get(j).get("DWNMSGTIME") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("ACPS") != "" && query1.get(j).get("ACPS") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("DOWNTIME") != "" && query1.get(j).get("DOWNTIME") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("UPID") != "" && query1.get(j).get("UPID") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("DWNID") != "" && query1.get(j).get("DWNID") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("ACTP") != "" && query1.get(j).get("ACTP") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("ACP") != "" && query1.get(j).get("ACP") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("PORT") != "" && query1.get(j).get("PORT") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("MEDIA") != "" && query1.get(j).get("MEDIA") != null) {
                    actpSize++;
                }
                if ((actpSize >= 19) && (query1.get(j).get("MEDIA") != null)) {
                    AllSize1 += 1; //过滤后的总数
                }
            }
            for (int k = 0; k < query1.size(); k++) {
                if (timeStandard.equals("ACP")){

                }else if(timeStandard.equals("ACTP")){  //统计240标准与400标准 分子
                    if (standard.equals("240")){          //240标准
                        // 时间标准ACTP
                        if (Integer.parseInt(String.valueOf(query1.get(k).get("ACTP")))<150){
                            Percentage_x1++;    //统计99.9%
                        }
                        if (Integer.parseInt(String.valueOf(query1.get(k).get("ACTP")))<120){
                            Percentage_x2++;    //统计95%
                        }
                    }else if (standard.equals("400")){    //400标准
                        // 时间标准ACTP
                        if (Integer.parseInt(String.valueOf(query1.get(k).get("ACTP")))<310){
                            Percentage_x1++;    //统计99.9%
                        }
                        if (Integer.parseInt(String.valueOf(query1.get(k).get("ACTP")))<260){
                            Percentage_x2++;    //统计99.5%
                        }
                    }
                }
            }

            for (int j1 = 0; j1 < lessList.size(); j1++) {  //统计Json数组
                //线段
                Integer actp = Integer.parseInt(String.valueOf(lessList.get(j1).get("actp")));
                DecimalFormat df = new DecimalFormat("0.00000");
                x = Integer.parseInt(String.valueOf(lessList.get(j1).get("actp")));
                double sum = 0.0;//统计小于等于ACP的个数
                for (int l = 0; l < lessList.size(); l++) { //统计小于等于actp的个数
                    Integer actp1 = Integer.parseInt(String.valueOf(lessList.get(l).get("actp")));
                    if (actp1 <= actp){
                        sum += Integer.parseInt(String.valueOf(lessList.get(l).get("count")));
                    }
                }
                String y = df.format((float)sum/AllSize*100);
                if(j1 == lessList.size()-1){
                    if(month[i] == null){
                        month[i]=x+","+y;
                    }else if(month[i] != null){
                        month[i] = month[i]+x+","+y;
                    }
                }else {
                    if(month[i] != null){
                        month[i] = month[i]+x+","+y+",";
                    }else {
                        month[i] = x+","+y+",";
                    }

                }
            }
            //百分比值
            DecimalFormat df1 = new DecimalFormat("0.0000");
            cont[i][0] = df1.format((double)Percentage_x1/AllSize1 * 100);
            cont[i][1] = df1.format((double)Percentage_x2/AllSize1 * 100);
        }

        map.put("month",month);
        map.put("cont1",cont[0][0]);
        map.put("cont2",cont[0][1]);
        return map;
    }

    //航空公司
    public Map getCpdlcActype(CpdlcEntity cpdlcEntity, String[] num) {
        //航空公司
        String operater = cpdlcEntity.getOPERATER();
        //航空器类型
        String actype = cpdlcEntity.getActype();
        //媒体类型
        String media = cpdlcEntity.getMedia();
        //地面站
        String masrgs = cpdlcEntity.getMasrgs();
        //管制区
        String atsp = cpdlcEntity.getAtsp();
        //RCP标准
        String standard = cpdlcEntity.getStandard();
        //时间标准
        String timeStandard = cpdlcEntity.getTimeStandard();
        //起始时间
        String beginDate = cpdlcEntity.getBeginDate();
        //结束时间
        String endinDate = cpdlcEntity.getEndDate();

        Map map = new HashMap();
        List list = new ArrayList();
        Integer AllSize=0;  //未过滤后的（过滤条件为：少于19列且等于19列，MEDIA不能为空）
        Integer AllSize1=0;  //过滤后的
        Integer x = null;

        double Percentage_x1=0;
        double Percentage_x2=0;
        String [] All = new String[num.length+10];
        String [] month = new String[num.length];
        String[][] cont = new String[num.length][2];

        for (int i = 0; i < num.length; i++) {  //选择几个月份就有几个线段
            String SqlAll = "SELECT ATSP,AN,ACTYPE,OPERATER,MSGDATE,MASRGS,OPSRGS,UPMSGTIME,MASTIME,MASTRIP,FMSTIME,DWNMSGTIME,ACPS,DOWNTIME,UPID,DWNID,ACTP,ACP,PORT,MEDIA FROM CPDLCGOLD WHERE  MEDIA is not null";
            String sql = "SELECT ACTP,COUNT(1) AS COUNT FROM CPDLCGOLD WHERE  1 = 1 ";
//            String sql = "SELECT ACTP,COUNT(1) AS COUNT FROM " +
//                    "(SELECT * FROM CPDLCGOLD WHERE 1=1 AND ATSP IS NOT NULL AND AN IS NOT NULL AND ACTYPE IS NOT NULL AND OPERATER IS NOT NULL AND MSGDATE IS NOT NULL AND MASRGS IS NOT NULL AND OPSRGS IS NOT NULL " +
//                    " AND UPMSGTIME IS NOT NULL AND MASTRIP IS NOT NULL AND FMSTIME IS NOT NULL AND DWNMSGTIME IS NOT NULL AND ACPS IS NOT NULL AND DOWNTIME IS NOT NULL AND UPID IS NOT NULL AND DWNID IS NOT NULL AND ACTP IS NOT NULL " +
//                    " AND ACP IS NOT NULL AND PORT IS NOT NULL AND MEDIA IS NOT NULL AND FI IS NOT NULL AND UFI IS NOT NULL)" +
//                    " WHERE  1 = 1 ";
            if (operater != "" && operater != null){    //航空公司
                SqlAll += " AND OPERATER LIKE '"+operater;
                sql += " AND OPERATER LIKE '"+operater;
            }
            if (actype != "" && actype != null){    //航空器类型
                SqlAll += " AND ACTYPE LIKE '"+actype;
                sql += " AND ACTYPE LIKE '"+actype;
            }
            if (media != "" && media != null){    //媒体类型
                SqlAll += " AND MEDIA LIKE '"+media;
                sql += " AND MEDIA LIKE '"+media;
            }
            if (masrgs != "" && masrgs != null){    //地面站
                SqlAll += " AND MEDIA LIKE '"+masrgs;
                sql += " AND MEDIA LIKE '"+masrgs;
            }
            if (atsp != "" && atsp != null){    //管制区
                SqlAll += " AND MEDIA LIKE '"+atsp;
                sql += " AND MEDIA LIKE '"+atsp;
            }
            if (beginDate != "" && beginDate != null){    //起始时间
                SqlAll += " AND FMSTIME LIKE '"+beginDate;
                sql += " AND  FMSTIME '"+beginDate;
            }
            if (endinDate != "" && endinDate != null){    //结束时间
                SqlAll += " AND DWNMSGTIME LIKE '"+endinDate;
                sql += " AND DWNMSGTIME LIKE '"+endinDate;
            }
            if(num[i] != "" && num[i]!=null){
                SqlAll+=" AND OPERATER LIKE '"+num[i]+"%'";
                sql += " AND OPERATER like '"+num[i]+"%' group by ACTP ";
            }
            logger.info(sql);
            logger.info(SqlAll);
            List<Map<String, Object>> query1 = secondaryDataSource.queryForList(SqlAll);
            List<Map<String, Object>> lessList = secondaryDataSource.queryForList(sql);
            for (int j = 0; j < query1.size(); j++) {  //统计过滤后的总数与未过滤后的总数
                int actpSize = 0;
                AllSize++;  //未过滤的总数
                if (query1.get(j).get("ATSP") != "" && query1.get(j).get("ATSP") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("AN") != "" && query1.get(j).get("AN") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("ACTYPE") != "" && query1.get(j).get("ACTYPE") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("OPERATER") != "" && query1.get(j).get("OPERATER") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("MSGDATE") != "" && query1.get(j).get("MSGDATE") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("MASRGS") != "" && query1.get(j).get("MASRGS") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("OPSRGS") != "" && query1.get(j).get("OPSRGS") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("UPMSGTIME") != "" && query1.get(j).get("UPMSGTIME") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("MASTIME") != "" && query1.get(j).get("MASTIME") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("MASTRIP") != "" && query1.get(j).get("MASTRIP") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("FMSTIME") != "" && query1.get(j).get("FMSTIME") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("DWNMSGTIME") != "" && query1.get(j).get("DWNMSGTIME") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("ACPS") != "" && query1.get(j).get("ACPS") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("DOWNTIME") != "" && query1.get(j).get("DOWNTIME") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("UPID") != "" && query1.get(j).get("UPID") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("DWNID") != "" && query1.get(j).get("DWNID") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("ACTP") != "" && query1.get(j).get("ACTP") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("ACP") != "" && query1.get(j).get("ACP") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("PORT") != "" && query1.get(j).get("PORT") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("MEDIA") != "" && query1.get(j).get("MEDIA") != null) {
                    actpSize++;
                }
                if ((actpSize >= 19) && (query1.get(j).get("MEDIA") != null)) {
                    AllSize1 += 1; //过滤后的总数
                }
            }
            for (int k = 0; k < query1.size(); k++) {
                if (timeStandard.equals("ACP")){

                }else if(timeStandard.equals("ACTP")){  //统计240标准与400标准 分子
                    if (standard.equals("240")){          //240标准
                        // 时间标准ACTP
                        if (Integer.parseInt(String.valueOf(query1.get(k).get("ACTP")))<150){
                            Percentage_x1++;    //统计99.9%
                        }
                        if (Integer.parseInt(String.valueOf(query1.get(k).get("ACTP")))<120){
                            Percentage_x2++;    //统计95%
                        }
                    }else if (standard.equals("400")){    //400标准
                        // 时间标准ACTP
                        if (Integer.parseInt(String.valueOf(query1.get(k).get("ACTP")))<310){
                            Percentage_x1++;    //统计99.9%
                        }
                        if (Integer.parseInt(String.valueOf(query1.get(k).get("ACTP")))<260){
                            Percentage_x2++;    //统计99.5%
                        }
                    }
                }
            }

            for (int j1 = 0; j1 < lessList.size(); j1++) {  //统计Json数组
                //线段
                Integer actp = Integer.parseInt(String.valueOf(lessList.get(j1).get("actp")));
                DecimalFormat df = new DecimalFormat("0.00000");
                x = Integer.parseInt(String.valueOf(lessList.get(j1).get("actp")));
                double sum = 0.0;//统计小于等于ACP的个数
                for (int l = 0; l < lessList.size(); l++) { //统计小于等于actp的个数
                    Integer actp1 = Integer.parseInt(String.valueOf(lessList.get(l).get("actp")));
                    if (actp1 <= actp){
                        sum += Integer.parseInt(String.valueOf(lessList.get(l).get("count")));
                    }
                }
                String y = df.format((float)sum/AllSize*100);
                if(j1 == lessList.size()-1){
                    if(month[i] == null){
                        month[i]=x+","+y;
                    }else if(month[i] != null){
                        month[i] = month[i]+x+","+y;
                    }
                }else {
                    if(month[i] != null){
                        month[i] = month[i]+x+","+y+",";
                    }else {
                        month[i] = x+","+y+",";
                    }

                }
            }
            //百分比值
            DecimalFormat df1 = new DecimalFormat("0.0000");
            cont[i][0] = df1.format((double)Percentage_x1/AllSize1 * 100);
            cont[i][1] = df1.format((double)Percentage_x2/AllSize1 * 100);
        }

        map.put("month",month);
        map.put("cont1",cont[0][0]);
        map.put("cont2",cont[0][1]);
        return map;
    }

    //地面站
    public Map getCpdlcMasrgs(CpdlcEntity cpdlcEntity, String[] num) {
        //航空公司
        String operater = cpdlcEntity.getOPERATER();
        //航空器类型
        String actype = cpdlcEntity.getActype();
        //媒体类型
        String media = cpdlcEntity.getMedia();
        //地面站
        String masrgs = cpdlcEntity.getMasrgs();
        //管制区
        String atsp = cpdlcEntity.getAtsp();
        //RCP标准
        String standard = cpdlcEntity.getStandard();
        //时间标准
        String timeStandard = cpdlcEntity.getTimeStandard();
        //起始时间
        String beginDate = cpdlcEntity.getBeginDate();
        //结束时间
        String endinDate = cpdlcEntity.getEndDate();

        Map map = new HashMap();
        List list = new ArrayList();
        Integer AllSize=0;  //未过滤后的（过滤条件为：少于19列且等于19列，MEDIA不能为空）
        Integer AllSize1=0;  //过滤后的
        Integer x = null;

        double Percentage_x1=0;
        double Percentage_x2=0;
        String [] All = new String[num.length+10];
        String [] month = new String[num.length];
        String[][] cont = new String[num.length][2];

        for (int i = 0; i < num.length; i++) {  //选择几个月份就有几个线段
            String SqlAll = "SELECT ATSP,AN,ACTYPE,OPERATER,MSGDATE,MASRGS,OPSRGS,UPMSGTIME,MASTIME,MASTRIP,FMSTIME,DWNMSGTIME,ACPS,DOWNTIME,UPID,DWNID,ACTP,ACP,PORT,MEDIA FROM CPDLCGOLD WHERE  MEDIA is not null";
            String sql = "SELECT ACTP,COUNT(1) AS COUNT FROM CPDLCGOLD WHERE  1 = 1 ";
            if (operater != "" && operater != null){    //航空公司
                SqlAll += " AND OPERATER LIKE '"+operater;
                sql += " AND OPERATER LIKE '"+operater;
            }
            if (actype != "" && actype != null){    //航空器类型
                SqlAll += " AND ACTYPE LIKE '"+actype;
                sql += " AND ACTYPE LIKE '"+actype;
            }
            if (media != "" && media != null){    //媒体类型
                SqlAll += " AND MEDIA LIKE '"+media;
                sql += " AND MEDIA LIKE '"+media;
            }
            if (masrgs != "" && masrgs != null){    //地面站
                SqlAll += " AND MEDIA LIKE '"+masrgs;
                sql += " AND MEDIA LIKE '"+masrgs;
            }
            if (atsp != "" && atsp != null){    //管制区
                SqlAll += " AND MEDIA LIKE '"+atsp;
                sql += " AND MEDIA LIKE '"+atsp;
            }
            if (beginDate != "" && beginDate != null){    //起始时间
                SqlAll += " AND FMSTIME LIKE '"+beginDate;
                sql += " AND  FMSTIME '"+beginDate;
            }
            if (endinDate != "" && endinDate != null){    //结束时间
                SqlAll += " AND DWNMSGTIME LIKE '"+endinDate;
                sql += " AND DWNMSGTIME LIKE '"+endinDate;
            }
            if(num[i] != "" && num[i]!=null){
                SqlAll+=" AND MASRGS LIKE '"+num[i]+"%'";
                sql += " AND MASRGS like '"+num[i]+"%' group by ACTP ";
            }
            logger.info(sql);
            logger.info(SqlAll);
            List<Map<String, Object>> query1 = secondaryDataSource.queryForList(SqlAll);
            List<Map<String, Object>> lessList = secondaryDataSource.queryForList(sql);
            for (int j = 0; j < query1.size(); j++) {  //统计过滤后的总数与未过滤后的总数
                int actpSize = 0;
                AllSize++;  //未过滤的总数
                if (query1.get(j).get("ATSP") != "" && query1.get(j).get("ATSP") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("AN") != "" && query1.get(j).get("AN") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("ACTYPE") != "" && query1.get(j).get("ACTYPE") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("OPERATER") != "" && query1.get(j).get("OPERATER") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("MSGDATE") != "" && query1.get(j).get("MSGDATE") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("MASRGS") != "" && query1.get(j).get("MASRGS") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("OPSRGS") != "" && query1.get(j).get("OPSRGS") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("UPMSGTIME") != "" && query1.get(j).get("UPMSGTIME") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("MASTIME") != "" && query1.get(j).get("MASTIME") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("MASTRIP") != "" && query1.get(j).get("MASTRIP") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("FMSTIME") != "" && query1.get(j).get("FMSTIME") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("DWNMSGTIME") != "" && query1.get(j).get("DWNMSGTIME") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("ACPS") != "" && query1.get(j).get("ACPS") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("DOWNTIME") != "" && query1.get(j).get("DOWNTIME") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("UPID") != "" && query1.get(j).get("UPID") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("DWNID") != "" && query1.get(j).get("DWNID") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("ACTP") != "" && query1.get(j).get("ACTP") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("ACP") != "" && query1.get(j).get("ACP") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("PORT") != "" && query1.get(j).get("PORT") != null) {
                    actpSize++;
                }
                if (query1.get(j).get("MEDIA") != "" && query1.get(j).get("MEDIA") != null) {
                    actpSize++;
                }
                if ((actpSize >= 19) && (query1.get(j).get("MEDIA") != null)) {
                    AllSize1 += 1; //过滤后的总数
                }
            }
            for (int k = 0; k < query1.size(); k++) {
                if (timeStandard.equals("ACP")){
                    if (standard.equals("240")){          //240标准
                        // 时间标准ACP
                        if (Integer.parseInt(String.valueOf(query1.get(k).get("ACTP")))<240){
                            Percentage_x1++;    //统计99.9%
                        }
                        if (Integer.parseInt(String.valueOf(query1.get(k).get("ACTP")))<210){
                            Percentage_x2++;    //统计95%
                        }
                    }else if (standard.equals("400")){    //400标准
                        // 时间标准ACTP
                        if (Integer.parseInt(String.valueOf(query1.get(k).get("ACTP")))<400){
                            Percentage_x1++;    //统计99.9%
                        }
                        if (Integer.parseInt(String.valueOf(query1.get(k).get("ACTP")))<350){
                            Percentage_x2++;    //统计99.5%
                        }
                    }
                }else if(timeStandard.equals("ACTP")){  //统计240标准与400标准 分子
                    if (standard.equals("240")){          //240标准
                        // 时间标准ACTP
                        if (Integer.parseInt(String.valueOf(query1.get(k).get("ACTP")))<150){
                            Percentage_x1++;    //统计99.9%
                        }
                        if (Integer.parseInt(String.valueOf(query1.get(k).get("ACTP")))<120){
                            Percentage_x2++;    //统计95%
                        }
                    }else if (standard.equals("400")){    //400标准
                        // 时间标准ACTP
                        if (Integer.parseInt(String.valueOf(query1.get(k).get("ACTP")))<310){
                            Percentage_x1++;    //统计99.9%
                        }
                        if (Integer.parseInt(String.valueOf(query1.get(k).get("ACTP")))<260){
                            Percentage_x2++;    //统计99.5%
                        }
                    }
                }
            }

            for (int j1 = 0; j1 < lessList.size(); j1++) {  //统计Json数组
                //线段
                Integer actp = Integer.parseInt(String.valueOf(lessList.get(j1).get("actp")));
                DecimalFormat df = new DecimalFormat("0.00000");
                x = Integer.parseInt(String.valueOf(lessList.get(j1).get("actp")));
                double sum = 0.0;//统计小于等于ACP的个数
                for (int l = 0; l < lessList.size(); l++) { //统计小于等于actp的个数
                    Integer actp1 = Integer.parseInt(String.valueOf(lessList.get(l).get("actp")));
                    if (actp1 <= actp){
                        sum += Integer.parseInt(String.valueOf(lessList.get(l).get("count")));
                    }
                }
                String y = df.format((float)sum/AllSize*100);
                if(j1 == lessList.size()-1){    //null68,0.01381
                    if(month[i] == null){
                        month[i]=x+","+y;
                    }else if(month[i] != null){
                        month[i] = month[i]+x+","+y;
                    }
                }else {
                    if(month[i] != null){
                        month[i] = month[i]+x+","+y+",";
                    }else {
                        month[i] = x+","+y+",";
                    }

                }
            }
            //百分比值
            DecimalFormat df1 = new DecimalFormat("0.0000");
            cont[i][0] = df1.format((double)Percentage_x1/AllSize1 * 100);
            cont[i][1] = df1.format((double)Percentage_x2/AllSize1 * 100);
        }

        map.put("month",month);
        map.put("cont1",cont[0][0]);
        map.put("cont2",cont[0][1]);
        return map;
    }

}
