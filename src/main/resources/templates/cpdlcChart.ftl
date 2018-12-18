<#--noinspection ALL-->
<!DOCTYPE html>
<html>
<head>
    <title>cpdlc</title>

    <meta name="keywords" content="keyword1,keyword2,keyword3">
    <meta name="description" content="this is my page">
    <meta name="content-type" content="text/html; charset=UTF-8">

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
    <script src="js/highcharts.js"></script>
    <script src="https://img.hcharts.cn/highcharts/modules/exporting.js"></script>

    <script type="text/javascript">
        var chart = null;
        var state = 0;
        var temp = null;
        var chartConf = null;
        var OldInterval = 30;



        $(function() {
            var operater = "${operater}";
            var actype = "${actype}";
            var media = "${media}";
            var masrgs = "${masrgs}";
            var standard = "${standard}";
            var timeStandard = "${timeStandard}";
            var atsp = "${atsp}";
            var beginDate = "${beginDate}";
            var endDate = "${endDate}";

            var check_msgdate = "${check_msgdate}";
            var check_atsp = "${check_atsp}";
            var check_media = "${check_media}";
            var check_opactype = "${check_opactype}";
            var check_actype = "${check_actype}";
            var check_masrgs = "${check_masrgs}";

            var msgdataLean = check_msgdate.split(",");
            var count = msgdataLean.length;
            console.log(count);
            var totalAll = new Array();
            var sum = new Array();
            var sumAll;


            console.log("======================"+totalAll);
            console.log(operater+"  "+actype+"  "+media+"  "+masrgs+"  "+standard+"  "
                    +timeStandard+"  "+atsp+"  "+beginDate+"  "+endDate+"  "+check_msgdate+"  "+check_atsp
                    +"  "+check_media+"  "+check_media+"  "+check_opactype+"  "+check_actype+"  "+check_masrgs);
            console.log("======================");
            $.ajax({
                dataType : "json",
                contentType : "application/x-www-form-urlencoded; charset=utf-8",
                data:{
                    "operater":operater,
                    "actype":actype,
                    "media":media,
                    "masrgs":masrgs,
                    "standard":standard,
                    "timeStandard":timeStandard,
                    "atsp":atsp,
                    "beginDate":beginDate,
                    "endDate":endDate,
                    "check_msgdate":check_msgdate,
                    "check_atsp":check_atsp,
                    "check_media":check_media,
                    "check_opactype":check_opactype,
                    "check_actype":check_actype,
                    "check_masrgs":check_masrgs
                },
                url : "cpdlcGraphs",
                type : "POST",
                success : function(data) {
                    console.log(data.series);
                    temp = eval(data.series);
                    console.log(temp);
                    chartConf = eval(temp.chartConfig);
                    //设置初始值
                    Highcharts.setOptions({
                        lang : {
                            resetZoom : "返回",
                            resetZoomTitle : "回到初始状态"
                        }
                    });

                    $('#container').highcharts({
                        chart : {
                            type : 'line',
                            zoomType : 'xy',
                            events : {
                                //监听图表区域选择事件
                                selection : function() {
                                    //动态修改
                                    DynamicChangeTickInterval(30);
                                    InitialTickInterval(10);
                                }

                            }
                        },
                        title : {
                            text : chartConf.title
                        },
                        xAxis : {
                            title : {
                                text : 'Time (seconds)'
                            },
                            max : 240,
                            tickInterval : OldInterval
                            //默认间隔值为30
                        },
                        yAxis : {
                            title : {
                                text : 'Cumulative Percentage (%)'
                            },
                            //tickPositions:[90,91,92,93,94,95,96,97,98,99,100],
                            min : 0,
                            max : 100,
                            tickInterval : 1
                        },
                        exporting : {
                            enabled : true, // 关闭导出按钮
                            url : 'https://export.highcharts.com.cn'
                        },
                        plotOptions : {
                            line : {
                                connectNulls : true
                            },
                            series : {
                                marker : {
                                    enabled : false,
                                    states : {
                                        hover : {
                                            enabled : true
                                        }
                                    }
                                }
                            }
                        },
                        series : [

                        ],
                        navigation : {
                            buttonOptions : { // 关闭导出按钮，效果同上
                                enabled : true,
                            }
                        }
                    }, function(chartObj) {
                        console.log("★★★★★★★获得图表对象chart  之前值 "+chart);
                        console.log("★★★★★★★获得图表对象 "+chartObj);
                        //获得图表对象
                        chart = chartObj;
                    });
                    var num = 0;
                    for ( var i in temp) {
                        if (temp[i].linedata != null) {
                            if (temp[i].style != null) {
                                var style = temp[i].style;
                                chart.addSeries({
                                    dashStyle : style.dashStyle,
                                    data : temp[i].linedata,
                                    step : style.step,//left,right,center,true,默认false
                                    name : style.name,
                                    color : style.color,
                                    marker : {
                                        radius : 0,
                                        states : {
                                            hover : {
                                                enabled : true
                                            }
                                        }
                                    }
                                });

                            } else {
                                chart.addSeries({
                                    name : temp[i].name,
                                });
                               // console.log("★★★★★★★★★★ "+temp[i].name);
                                chart.series[num].setData(temp[i].linedata);    //填充数据
                               // console.log("★★★★★★★★★★ "+temp[i].linedata+"========");


                                var cc = null;
                                var all = 0;
                                var sum1= 0;
                                var sum2= 0;
                                cc = ""+temp[i].linedata;
                                var yesult = cc.split(",");
                                console.log("☆☆☆☆☆☆☆☆☆☆☆肢解后为："+yesult);
                                for (var j = 0; j < yesult.length; j++) {
                              //      console.log("**  "+yesult[j]+"   **");
                                    if(j % 2 == 0){
                                        if(yesult[j]< 120){sum1++;}   //总 小于120的
                                        // if(yesult[j]< 120){sum1+=parseFloat(yesult[j]);}   //总 小于120的
                                        if(yesult[j]< 150){sum2++;}   //总 小于150的
                                        // if(yesult[j]< 150){sum2+=parseFloat(yesult[j]);}   //总 小于150的
                                        // all+=parseFloat(yesult[j]);     //总数
                                        all++;     //总数
                                        console.log("☆☆☆☆☆☆☆☆☆☆☆总数为："+yesult[j]);
                                    }
                                }
                                var a;
                                var b;
                                a =sum1/all*100;
                                b =sum2/all*100;

                                console.log("单个个数1为："+sum1+"单个个数2为："+sum2+" 总个数为："+all);
                                // console.log("计算之前："+sum1/all*100+"      "+sum2/all*100);
                                // console.log("总的个数 "+all+" 小于 120个数 "+sum1+" 小于150个数 "+sum2);
                                // console.log("计算之后："+a.toFixed(2)+"%      "+b.toFixed(2)+"%");
                                console.log("num="+num+" temp[i].name="+temp[i].name+"  all="+all+" a.toFixed(2)="+a.toFixed(2)+" b.toFixed(2)="+b.toFixed(2));
                                totalAll[num]=[temp[i].name,all,a.toFixed(2),b.toFixed(2)];b
                                sumAll = cc.split(",");
                                sum[num] = sumAll;
                            }
                        }
                        num++;
                    }
                    //扩展或者重写Highcharts图表组件的方法
                    ExtendHighcharts();



                    var Allsum1 = 0;
                    var Allsum2 = 0;
                    var Allcount = 0;

                    //★★★★★★★★★★

                    //计算总的个数   总的小于120比例    总的小于150比例
                    for (var i = 0; i < sum.length; i++) {
                        for (var j = 0; j < sum[i].length; j++) {
                            if(j % 2 == 0){
                                // if(sum[i][j]< 120){Allsum1+=parseFloat(sum[i][j]);}   //总 小于120的
                                if(sum[i][j]< 120){Allsum1++;}   //总 小于120的
                                // if(sum[i][j]< 150){Allsum2+=parseFloat(sum[i][j]);}   //总 小于150的
                                if(sum[i][j]< 150){Allsum2++;}   //总 小于150的
                                // Allcount+=parseFloat(sum[i][j]);     //所有总数
                                Allcount++;     //所有总数
                            }
                            // console.log(sum[i][j]);
                        }
                        // console.log("=====================");
                    }
                    console.log("所有小于120的"+Allsum1+"   所有小于150的"+Allsum2+"     总的"+Allcount);
                    var Alla;
                    var Allb;
                    Alla = Allsum1/Allcount*100;
                    Allb = Allsum2/Allcount*100;
                    console.log("所有小于120："+Alla+"     所有小于150："+Allb);

                    //★★★★★★★★★★
                    console.log("总的"+Allcount+"    所有小于120："+Alla.toFixed(2)+"     所有小于150："+Allb.toFixed(2));

                    $("#tab_3_3").append(
                            "<tr> <td>"+
                            'All'
                            +"</td> <td>"+
                            Allcount
                            +"</td> <td>"+
                            Alla.toFixed(2)+"%"
                            +"</td> <td>"+
                            Allb.toFixed(2)+"%"
                            +"</td> </tr>"
                    );

                    //各个值
                    for (var i = 0; i < totalAll.length; i++) {
                        console.log(totalAll[i][0]+"    "+totalAll[i][1]+"     "+totalAll[i][2]+"     "+totalAll[i][3]);
                        $("#tab_3_3").append(
                                "<tr id='"+totalAll[i][0]+"'> <td>"+
                                totalAll[i][0]
                                +"</td> <td>"+
                                totalAll[i][1]
                                +"</td> <td>"+
                                totalAll[i][2]+"%"
                                +"</td> <td>"+
                                totalAll[i][3]+"%"
                                +"</td> </tr>"
                        );
                    }

                }
            });
        });

        //动态修改xAxis的刻度间隔值
        function DynamicChangeTickInterval(interval) {
            chart.xAxis[0].update({
                max : 1800,
                tickInterval : interval
            });

        }
        //动态修改yAxis的刻度间隔值
        function InitialTickInterval(interval) {
            chart.yAxis[0].update({
                min : 0,
                max : 100,
                tickInterval : interval
            });
        }

        //扩展或者重写Highcharts图表组件的方法
        function ExtendHighcharts() {
            Highcharts.extend(Highcharts.Chart.prototype, {
                zoomOut : function() {
                    //还原图表X轴y轴的间隔
                    DynamicChangeTickInterval(OldInterval);
                    InitialTickInterval(10);
                    this.zoom();
                }
            });
        }
        //style="with:1000px;height: 400px"
    </script>

</head>

<body>

<div id="container" style="with: 1000px;height: 400px"></div>
Performance Measure:&nbsp;CPDLC </br>
&nbsp;</br>
Performance Criteria:&nbsp;RSP180 </br>
Filters: </br>
Type of Analysis:&nbsp;Month </br>
<div>

<table style="float: left;text-align: right;width: 500px;" id="tab_3_3">
    <tr>
        <td>
            Month
        </td>
        <td>
            Count of Msgs
        </td>
        <td>
            120sec%
        </td>
        <td>
            150sec%
        </td>
    </tr>
</table>
</div>

</br>
<div class="tab-pane" id="tab_3_2">

</div>

</body>
</html>
