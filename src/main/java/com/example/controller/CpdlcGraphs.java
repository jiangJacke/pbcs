package com.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.entity.CpdlcEntity;
import com.example.service.CpdlcGraphsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: jiangm
 * @Description: CPDLC分析与报告生成功能
 * @CreateDate: 2018/10/9 16:15
 * @Version: 空管PBCS平台事后分析系统
 */
@Controller
public class CpdlcGraphs {
    @Autowired
    private CpdlcGraphsService cpdlcGraphsService;

    private final Logger logger = LoggerFactory.getLogger(Login.class);

    /**
     * 功能：根据传过来参数获取不同的参数,返回前台动态显示复选框
     */
    @RequestMapping("/getCpdlcParameter")
    @ResponseBody
    public List getCpdlcParameter(String type,CpdlcEntity cpdlcEntity){
        List list = cpdlcGraphsService.getParameter(type);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        return list;
    }

    /**
     * 功能：表单提交返回json,集成图表
     */
    @RequestMapping(value = "/cpdlcGraphs")
    @ResponseBody
    public void cpdlcGraphs(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String,Object> map = new HashMap<String,Object>();
        JSONObject data = new JSONObject();//封裝所有線條數據的json串
        JSONObject jsonObject1 = new JSONObject();//要發送的json串
        String[] num_msgdate = null;
        String operater = request.getParameter("operater");       //航空公司 OPERATER
        String actype = request.getParameter("actype");         //航空器类型 ACTYPE
        String media = request.getParameter("media");       //媒体类型 MEDIA
        String masrgs = request.getParameter("masrgs");     //地面站
        String standard = request.getParameter("standard"); //RCP标准
        String timeStandard = request.getParameter("timeStandard"); //时间标准
        String atsp = request.getParameter("atsp");         //管制区
        String beginDate = request.getParameter("beginDate");//起始时间
        String endDate = request.getParameter("endDate");    //结束时间
        System.out.println("where条件："+operater+" "+actype+" "+media+" "+masrgs+" "+standard+" "+atsp+" "+beginDate+" "+endDate);
        CpdlcEntity cpdlcEntity = new CpdlcEntity();
        //航空公司
        cpdlcEntity.setOPERATER(operater);
        //航空器类型
        cpdlcEntity.setActype(actype);
        //媒体类型
         cpdlcEntity.setMedia(media);
        //地面站
        cpdlcEntity.setMasrgs(masrgs);
        //管制区
        cpdlcEntity.setAtsp(atsp);
        //RCP标准
        cpdlcEntity.setStandard(standard);
        //时间标准
        cpdlcEntity.setTimeStandard(timeStandard);
        //起始时间
        cpdlcEntity.setBeginDate(beginDate);
        //结束时间
        cpdlcEntity.setEndDate(endDate);
        //分组条件
        //所有数据 筛选(没有分组条件)
        String check_msgdate = request.getParameter("check_msgdate");   //月份
        String check_atsp = request.getParameter("check_atsp");   //管制区
        String check_media = request.getParameter("check_media");   //媒体类型
        String check_opactype = request.getParameter("check_opactype");   //机型
        String check_actype = request.getParameter("check_actype");   //航空公司
        String check_masrgs = request.getParameter("check_masrgs");   //地面站
        System.out.println("分组条件："+check_msgdate+" "+check_atsp+" "+check_media+" "+check_opactype+" "+check_actype+" "+check_masrgs);
        JSONObject jsonObject = new JSONObject();//要发送的json串
        jsonObject.put("series", 1); //二维数组
        //所有数据 筛选（没有分组条件）
        if(check_msgdate != null){
            if(check_msgdate.length()>0){

                num_msgdate =check_msgdate.split(",");
                for (int i = 0; i < num_msgdate.length; i++) {
                    System.out.println(num_msgdate[i]);
                }

                String[] merchant1 = new String[num_msgdate.length];

                // 月份 筛选
                map = cpdlcGraphsService.getCpdlcMsgdate(cpdlcEntity,num_msgdate);
            }else if(check_atsp.length()>0){
                num_msgdate =check_atsp.split(",");
                for (int i = 0; i < num_msgdate.length; i++) {
                    System.out.println(num_msgdate[i]);
                }
                //管制区 筛选
                map = cpdlcGraphsService.getCpdlcAtsp(cpdlcEntity,num_msgdate);
            }else if(check_media.length()>0){
                num_msgdate =check_media.split(",");
                for (int i = 0; i < num_msgdate.length; i++) {
                    System.out.println(num_msgdate[i]);
                }
                //媒体类型 筛选
                map = cpdlcGraphsService.getCpdlcMedia(cpdlcEntity,num_msgdate);
            }else if(check_opactype.length()>0){
                num_msgdate =check_opactype.split(",");
                for (int i = 0; i < num_msgdate.length; i++) {
                    System.out.println(num_msgdate[i]);
                }
                //机型 筛选
                map = cpdlcGraphsService.getCpdlcOpactype(cpdlcEntity,num_msgdate);
            }else if(check_actype.length()>0){
                num_msgdate =check_actype.split(",");
                for (int i = 0; i < num_msgdate.length; i++) {
                    System.out.println(num_msgdate[i]);
                }
                //航空公司 筛选
                map = cpdlcGraphsService.getCpdlcActype(cpdlcEntity,num_msgdate);
            }else if(check_masrgs.length()>0){
                num_msgdate =check_masrgs.split(",");
                for (int i = 0; i < num_msgdate.length; i++) {
                    System.out.println(num_msgdate[i]);
                }
                //地面站 筛选
                map = cpdlcGraphsService.getCpdlcMasrgs(cpdlcEntity,num_msgdate);
            }
        }

        System.out.println("=============================");

        int len = 0; //各线段长度
        String[] cc=null;   //各线段
        String bb[] = (String[]) map.get("month");
        for (int i = 0; i < bb.length; i++) {           //多条线的时候循环
            int number = 0;
            cc = (String[])bb[i].split(",");
            System.out.println(cc.length);
            len = cc.length;
            float[][] series = new float[len/2][2];
            float[] array1 = new float[2];
            cc = (String[])bb[i].split(",");

            for (int k = 0; k < cc.length; k++) {
                if (k % 2 == 1) {
                    if(Float.parseFloat(cc[k]) != 0){
                        array1[0] = Float.parseFloat(cc[k-1]);
                        array1[1] = Float.parseFloat(cc[k]);
                    }
                    System.arraycopy(array1, 0, series[number], 0, 2);
                    number++;
                }
            }

            //x轴需要为升序或降序
            float tt;
            float ff;
            for (int k = 0; k < series.length; k++) {
                for (int j = 0; j < series.length-1; j++) {
                    if(series[k][0]<series[j][0]){
                        //key
                        tt = series[k][0];
                        series[k][0] = series[j][0];
                        series[j][0]=tt;
                        //value
                        ff = series[k][1];
                        series[k][1] = series[j][1];
                        series[j][1] = ff;
                    }
                }
            }

//            //封装数据
//            JSONObject lines = new JSONObject();
//            lines.put("name",num_msgdate[i]);
//            lines.put("linedata",series);
//            data.put("adaa",lines);

            //封装数据
            JSONObject lines = new JSONObject();
            lines.put("name",num_msgdate[i]);
            lines.put("linedata",series);
            data.put("adaa"+i,lines);
            jsonObject1.put("series", data);
        }

        System.out.println("=============================");
        //得到值，开始封装样式，写入流中
        JSONObject chartdata = new JSONObject();//图表的数据设置

        chartdata.put("title", "CPDLC");
        data.put("chartConfig", chartdata);

        //辅助线1
        float[][] subline1 = new float[4][2];
        float [] dot1 ={120,0};
        float [] dot2 ={120,90};
        float [] dot3 ={120,95};
        float [] dot4 ={1788,95};
        subline1[0]=dot1;subline1[1]=dot2;subline1[2]=dot3;subline1[3]=dot4;
        HashMap<String, Object> subline1_Style = new HashMap<String, Object>();
        subline1_Style.put("dashStyle", "ShortDash");//样式
        subline1_Style.put("data", subline1);//辅助线数据
        subline1_Style.put("step", "right");//弯折方向
        subline1_Style.put("name", "120s 95%");//图例名称
        subline1_Style.put("color", "red");//线条颜色
        //辅助线2
        float[][] subline2 = new float[4][2];
        float [] dots1 ={150,0};
        float [] dots2 ={150,90};
        float [] dots3 ={150,(float) 99.90};
        float [] dots4 ={1788, (float) 99.90};
        subline2[0]=dots1;subline2[1]=dots2;subline2[2]=dots3;subline2[3]=dots4;
        HashMap<String, Object> subline2_Style = new HashMap<String, Object>();
        subline2_Style.put("dashStyle", "ShortDash");//样式
        subline2_Style.put("data", subline2);//数据
        subline2_Style.put("step", "right");//弯折方向
        subline2_Style.put("name", "150s 99.9%");//图例名称
        subline2_Style.put("color", "black");//线条颜色


        JSONObject fuzhudata1 = new JSONObject();//紅色的輔助線（120 95%）
        fuzhudata1.put("linedata", subline1);
        fuzhudata1.put("style", subline1_Style);
        JSONObject fuzhudata2 = new JSONObject();//黑色的輔助線（150 99.9%）

        fuzhudata2.put("linedata", subline2);
        fuzhudata2.put("style", subline2_Style);

        data.put("data2", fuzhudata1);
        data.put("data3", fuzhudata2);

        jsonObject1.put("series", data);
        PrintWriter out=response.getWriter();
        out.println(jsonObject1);
        out.close();
    }

    @RequestMapping(value = "/jumpCpdlcChart")
    public String jumpCpdlcChart(HttpServletRequest request,HttpServletResponse response){
        String operater = request.getParameter("operater");
        String actype = request.getParameter("actype");
        String media = request.getParameter("media");
        String masrgs = request.getParameter("masrgs");
        String standard = request.getParameter("standard");
        String timeStandard = request.getParameter("timeStandard");
        String atsp = request.getParameter("atsp");
        String beginDate = request.getParameter("beginDate");
        String endDate = request.getParameter("endDate");
        String check_msgdate = request.getParameter("check_msgdate");
        String check_atsp = request.getParameter("check_atsp");
        String check_media = request.getParameter("check_media");
        String check_opactype = request.getParameter("check_opactype");
        String check_actype = request.getParameter("check_actype");
        String check_masrgs = request.getParameter("check_masrgs");

        logger.info(operater+"  "+actype+"  "+media+"  "+masrgs+"  "+standard+"  "+timeStandard+"  "+atsp+"  "+beginDate+"  "+endDate+"  "+media
        +"  "+check_msgdate+"  "+check_atsp+"  "+check_media+"  "+check_opactype+"  "+check_actype+"  "+check_masrgs);

        request.setAttribute("operater",operater);
        request.setAttribute("actype",actype);
        request.setAttribute("media",media);
        request.setAttribute("masrgs",masrgs);
        request.setAttribute("standard",standard);
        request.setAttribute("timeStandard",timeStandard);
        request.setAttribute("atsp",atsp);
        request.setAttribute("beginDate",beginDate);
        request.setAttribute("endDate",endDate);

        request.setAttribute("check_msgdate",check_msgdate);
        request.setAttribute("check_atsp",check_atsp);
        request.setAttribute("check_media",check_media);
        request.setAttribute("check_opactype",check_opactype);
        request.setAttribute("check_actype",check_actype);
        request.setAttribute("check_masrgs",check_masrgs);
        return "cpdlcChart";
    }

}
