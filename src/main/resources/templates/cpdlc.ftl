<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <title>PBCS</title>

    <!-- 包含头部信息用于适应不同设备 -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0" />
    <!-- =========================== import CSS ================================ -->
    <!-- Bootstrap -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <!-- jQuery UI -->
    <!--<link href="plugins/jquery-ui/jquery-ui-1.10.2.custom.css" rel="stylesheet" type="text/css" />-->
    <!--[if lt IE 9]>
    <link rel="stylesheet" type="text/css" href="plugins/jquery-ui/jquery.ui.1.10.2.ie.css"/>
    <![endif]-->
    <!-- Theme -->
    <link href="assets/css/main.css" rel="stylesheet" type="text/css" />
    <link href="assets/css/plugins.css" rel="stylesheet" type="text/css" />
    <link href="assets/css/responsive.css" rel="stylesheet" type="text/css" />
    <link href="assets/css/icons.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="assets/css/fontawesome/font-awesome.min.css">
    <!--[if IE 7]>
    <link rel="stylesheet" href="assets/css/fontawesome/font-awesome-ie7.min.css">
    <![endif]-->
    <!--[if IE 8]>
    <link href="assets/css/ie8.css" rel="stylesheet" type="text/css" />
    <![endif]-->
    <!-- =========================== import JavaScript ================================ -->
    <script type="text/javascript" src="assets/js/libs/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="plugins/jquery-ui/jquery-ui-1.10.2.custom.min.js"></script>

    <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="assets/js/libs/lodash.compat.min.js"></script>

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="assets/js/libs/html5shiv.js"></script>
    <![endif]-->

    <!-- Smartphone Touch Events -->
    <script type="text/javascript" src="plugins/touchpunch/jquery.ui.touch-punch.min.js"></script>
    <script type="text/javascript" src="plugins/event.swipe/jquery.event.move.js"></script>
    <script type="text/javascript" src="plugins/event.swipe/jquery.event.swipe.js"></script>

    <!-- General -->
    <script type="text/javascript" src="assets/js/libs/breakpoints.js"></script>
    <script type="text/javascript" src="plugins/respond/respond.min.js"></script> <!-- Polyfill for min/max-width CSS3 Media Queries (only for IE8) -->
    <script type="text/javascript" src="plugins/cookie/jquery.cookie.min.js"></script>
    <script type="text/javascript" src="plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <script type="text/javascript" src="plugins/slimscroll/jquery.slimscroll.horizontal.min.js"></script>

    <!-- Page specific plugins -->
    <!-- Charts -->
    <!--[if lt IE 9]>
    <script type="text/javascript" src="plugins/flot/excanvas.min.js"></script>
    <![endif]-->

    <!-- Forms -->
    <script type="text/javascript" src="plugins/uniform/jquery.uniform.min.js"></script>
    <script type="text/javascript" src="plugins/select2/select2.min.js"></script>

    <!-- App -->
    <script type="text/javascript" src="assets/js/app.js"></script>
    <script type="text/javascript" src="assets/js/plugins.js"></script>
    <script type="text/javascript" src="assets/js/plugins.form-components.js"></script>
    <script type="text/javascript" src="assets/js/ui_general.js"></script>

    <!-- =========================== own JavaScript ================================ -->

    <script>
        $(document).ready(function(){
            "use strict";
            App.init();            // Init layout and core plugins
            Plugins.init();        // Init all plugins
            FormComponents.init(); // Init all form-specific plugins
        });
    </script>

</head>

<script type="text/javascript">
    // var submitBtn = document.getElementById("submit");
    // var startTime = $("input[name='startTime']").val();
    // var endTime = $("input[name='endTime']").val();
    // if(startTime > endTime){
    //     $("input[name='startTime']").val = "";
    //     $("input[name='endTime']").val = "";
    //     alert("起始时间不能大于结束时间"); return false;
    // }
    // if (startTime == "" || endTime == "") {
    //     alert("时间不能为空，请输入时间"); return false;
    // }

    //提交
    function submitBtn() {
        var operater =$("input[name='operater']").val();
        var actype =$("input[name='actype']").val();
        var media =$("input[name='media']").val();
        var masrgs =$("input[name='masrgs']").val();
        var text_standard = document.getElementById("standard");    //RCP标准
        for (var i = 0; i < text_standard.length; i++) {
            if(text_standard[i].selected == true){
                var text=text_standard[i].value;
            }
        }
        if(text == "1"){
            standard = "240";
        }else if(text == "2"){
            standard = "400";
        }
        var text_timeStandard = document.getElementById("timeStandard");    //时间标准
        for (var i = 0; i < text_timeStandard.length; i++) {
            if(text_timeStandard[i].selected == true){
                var text=text_timeStandard[i].value;
            }
        }
        if(text == "1"){
            timeStandard = "ACTP";
        }else if(text == "2"){
            timeStandard = "ACP";
        }
        var atsp =$("input[name='atsp']").val();
        var beginDate =$("input[name='beginDate']").val();
        var endDate =$("input[name='endDate']").val();
        var msgdate = document.getElementsByName("msgdate");
        var check_msgdate = [];
        for (var k = 0;k < msgdate.length;k++  ) {
            if(msgdate[k].checked){
                check_msgdate.push(msgdate[k].parentNode.innerText);
            }
        }

        console.log("check_msgdate "+check_msgdate);

        var atsp1 = document.getElementsByName("atsp");
        var check_atsp = [];
        for (k in atsp1) {
            if(atsp1[k].checked){
                check_atsp.push(atsp1[k].parentNode.innerText);
            }
        }

        console.log("check_atsp "+check_atsp);

        var media1 = document.getElementsByName("media");
        var check_media = [];
        for (k in media1) {
            if(media1[k].checked){
                check_media.push(media1[k].parentNode.innerText);
            }
        }

        console.log("check_media "+check_media);

        var opactype = document.getElementsByName("opactype");
        var check_opactype = [];
        for (k in opactype) {
            if(opactype[k].checked){
                check_opactype.push(opactype[k].parentNode.innerText);
            }
        }

        console.log("check_opactype "+check_opactype);

        var actypes = document.getElementsByName("actypes");
        var check_actypes = [];
        for (k in actypes) {
            if(actypes[k].checked){
                check_actypes.push(actypes[k].parentNode.innerText);
            }
        }

        console.log("check_actypes "+check_actypes);

        var masrgs1 = document.getElementsByName("masrgs");
        var check_masrgs = [];
        for (var k = 0;k < masrgs1.length;k++) {
            if(masrgs1[k].checked){
                check_masrgs.push(masrgs1[k].parentNode.innerText);
            }
        }

        console.log("check_masrgs "+check_masrgs);

        console.log("operater "+operater+" actype "+actype+" media "+media+" masrgs "+masrgs+"standard "+standard+"beginDate "+beginDate+
                "endDate "+endDate+"check_msgdate "+check_msgdate+"check_atsp "+check_atsp+"check_media "+check_media+"check_opactype "+check_opactype+"check_actypes "+check_actypes+"check_masrgs "+check_masrgs);

        window.open("/jumpCpdlcChart?" +
                "operater="+operater+"&actype="+actype+"&media="+media+"&masrgs="+masrgs+"&standard="+standard+
                "&timeStandard="+timeStandard+"&atsp="+atsp+"&beginDate="+beginDate+"&endDate="+endDate+
                "&check_msgdate="+check_msgdate+"&check_atsp="+check_atsp+"&check_media="+check_media+
                "&check_opactype="+check_opactype+"&check_actype="+check_actypes+"&check_masrgs="+check_masrgs,
                "_bank","height=500,whidth=400");//弹窗
    }

    //动态显示各分组复选框
    function getCpdlcParameter(number){
        var type = number;  //用于区分不同选项
        var t = 0;
        //防止重复生成、只能选其中一组
        if (type == "1") {
            // $("#tab_3_1").html("");
            var msgdate = document.getElementsByName("msgdate");
            if(msgdate.length>1){
                return false;
            }
        };
        if (type == "2") {
            // $("#tab_3_2").html("");
            var atsp = document.getElementsByName("atsp");
            if(atsp.length>1){
                return false;
            }
        };
        if (type == "3") {
            //防止重复生成、只能选其中一组
            // $("#tab_3_3").html("");
            var media = document.getElementsByName("media");
            if(media.length>1){
                return;
            }
        };
        if (type == "4") {
            //防止重复生成、只能选其中一组
            // $("#tab_3_4").html("");
            var opactype = document.getElementsByName("opactype");
            if(opactype.length>1){
                return;
            }
        };
        if (type == "5") {
            //防止重复生成、只能选其中一组
            // $("#tab_3_5").html("");
            var actypes = document.getElementsByName("actypes");
            if(actypes.length>1){
                return;
            }
        };
        if (type == "6") {
            //防止重复生成、只能选其中一组
            // $("#tab_3_6").html("");
            var masrgs = document.getElementsByName("masrgs");
            if(masrgs.length>1){
                return;
            }
        };

        $.ajax({
            type :"post",
            async:true,
            dataType:"json",
            data:{type:number},
            url:"/getCpdlcParameter",
            success:function(data) {
                switch (type) {
                    case '1':
                        //一个没有就生成复选框
                            //如果有一个或多个，则不用动态生成复选框

                        for (var i = 0; i < data.length; i++){
                                if (type == "1" && data[i].MSGDATE != null && data[i].MSGDATE != "") {
                                    $("#tab_3_1").append(
                                            "<div  class='col-md-1' id='msgdate"+i+"'></div>"
                                    );
                                    $("#msgdate"+i).append(
                                            "<label class='checkbox'>"+"<input type='checkbox' name='msgdate" +"'/>"+"<span>"+data[i].MSGDATE+"</spen>"+"</label>"
                                    );
                                }
                        }

                        break;
                    case '2':
                        for (var i = 0; i < data.length; i++){
                            if (type == "2" && data[i].ATSP != null && data[i].ATSP != "") {
                                $("#tab_3_2").append(
                                        "<div  class='col-md-1' id='atsp"+i+"'></div>"
                                );
                                $("#atsp"+i).append(
                                        "<label class='checkbox'>"+"<input type='checkbox' name='atsp" +"'>"+data[i].ATSP+"</label>"
                                );
                            }
                        }
                        break;
                    case '3':
                        for (var i = 0; i < data.length; i++){
                            if(data[i].MEDIA != null && data[i].MEDIA !="") {
                                $("#tab_3_3").append(
                                        "<div  class='col-md-1' id='media" + i + "'></div>"
                                );
                                $("#media"+i).append(
                                        "<label class='checkbox'>"+"<input type='checkbox' name='media" +"'>"+data[i].MEDIA+"</label>"
                                );
                            }
                        }
                        break;
                    case '4':
                        for (var i = 0; i < data.length; i++){
                            if(data[i].ACTYPE != null && data[i].ACTYPE !=""){
                                $("#tab_3_4").append(
                                        "<div  class='col-md-1' id='opactype"+i+"'></div>"
                                );
                                $("#opactype"+i).append(

                                      "<label class='checkbox'>"+"<input type='checkbox' name='opactype"+"'>"+data[i].ACTYPE+"</label>"
                                );
                            }
                        }
                        break;
                    case '5':
                        for (var i = 0; i < data.length; i++){
                            if(data[i].OPERATER != null && data[i].OPERATER !="") {
                                $("#tab_3_5").append(
                                        "<div  class='col-md-1' id='actypes" + i + "'></div>"
                                );
                                $("#actypes" + i).append(
                                        "<label class='checkbox'>" + "<input type='checkbox' name='actypes" + "'>" + data[i].OPERATER + "</label>"
                                );
                            }
                        }
                        break;
                    case '6':
                        for (var i = 0; i < data.length; i++){
                            if(data[i].MASRGS != null && data[i].MASRGS !="") {
                                $("#tab_3_6").append(
                                        "<div  class='col-md-1' id='masrgs" + i + "'></div>"
                                );
                                $("#masrgs" + i).append(
                                        "<label class='checkbox'>" + "<input type='checkbox' name='masrgs" + "'>" + data[i].MASRGS + "</label>"
                                );
                            }
                        }
                        break;
                }
            }
        });
    }

</script>
<body>
<!-- =========================== Header  ================================ -->
<header class="header navbar navbar-fixed-top" role="banner">
    <!-- Top Navigation Bar -->
    <div class="container">

        <!-- Only visible on smartphones, menu toggle -->
        <ul class="nav navbar-nav">
            <li class="nav-toggle"><a href="javascript:void(0);" title=""><i class="icon-reorder"></i></a></li>
        </ul>

        <!-- Logo -->
        <a class="navbar-brand" href="index.html">
            <img src="../assets/img/logo1.png" alt="logo" />
            <strong>PBCS</strong>
        </a>

        <!-- /logo -->

        <!-- Si  bar Toggler -->
        <a href="#" class="toggle-sidebar bs-tooltip" data-placement="bottom" data-original-title="Toggle navigation">
            <i class="icon-reorder"></i>
        </a>
        <!-- /Sidebar Toggler -->

        </ul>
        <!-- /Top Right Menu -->
    </div>
    <!-- /top navigation bar -->

    <!--=== Project Switcher ===-->
    <div id="project-switcher" class="container project-switcher">
        <div id="scrollbar">
            <div class="handle"></div>
        </div>

    </div> <!-- /#project-switcher -->
</header> <!-- /.header -->


<div id="container">
    <!-- =========================== Sidebar  ================================ -->
    <div id="sidebar" class="sidebar-fixed">
        <div id="sidebar-content">
            <!--=== Navigation ===-->
            <ul id="nav">
                <li>
                    <a href="index">
                        Overview
                    </a>
                </li>
                <li>
                    <a href="javascript:void(0);">
                        Import ADS-C CSV
                    </a>

                    <ul class="sub-menu">
                        <li>
                            <a href="test1">
                                <i class="icon-angle-right"></i>
                                test1
                            </a>
                        </li>
                        <li>
                            <a href="test">
                                <i class="icon-angle-right"></i>
                                test2
                            </a>
                        </li>
                        <li>
                            <a href="index.ftl">
                                <i class="icon-angle-right"></i>
                                test3
                            </a>
                        </li>
                    </ul>

                </li>
                <li>
                    <a href="javascript:void(0);">
                        Clean Imported ADS-C Data
                    </a>
                </li>
                <li>
                    <a href="javascript:void(0);">
                        ADS-C Graphs
                    </a>
                </li>

                <li>
                    <a href="javascript:void(0);">
                        ADS-C Tabular
                    </a>
                </li>

                <li>
                    <a href="javascript:void(0);">
                        Import CPDLC CSV
                    </a>
                </li>

                <li>
                    <a href="javascript:void(0);">
                        Clean imported CPDLC Data
                    </a>
                </li>

                <li>
                    <a href="cpdlc">
                        CPDLC Graphs
                    </a>
                </li>

                <li>
                    <a href="javascript:void(0);">
                        Delete ADS-C Data
                    </a>
                </li>

                <li>
                    <a href="javascript:void(0);">
                        Celete CPDLC Data
                    </a>
                </li>

                <li>
                    <a href="javascript:void(0);">
                        Admin Intertace
                    </a>
                </li>
            </ul>
        </div>
    </div>
    <!-- /Sidebar -->


    <!-- =========================== Content  ================================ -->
    <div id="content">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3>CPDLC 分析与报告生成</h3>
            </div>
            <form class="form-horizontal row-border"  method="post">
                <div class="row next-row">
                <#--<div class="row">-->
                    <label class="col-md-3 control-label" style="text-align: right; margin-top:5px">航空公司</label>
                    <div class="col-md-2">
                        <input type="text" name="operater" class="form-control required">
                    </div>
                    <label class="col-md-3 control-label" style="text-align: right; margin-top:5px">航空器类型</label>
                    <div class="col-md-2">
                        <input type="text" name="actype" class="form-control required">
                    </div>
                </div>
                <div class="row next-row">
                    <label class="col-md-3 control-label" style="text-align: right; margin-top:5px">媒体类型</label>
                    <div class="col-md-2">
                        <input type="text" name="media" class="form-control required">
                    </div>
                    <label class="col-md-3 control-label" style="text-align: right; margin-top:5px">地面站</label>
                    <div class="col-md-2">
                        <input type="text" name="masrgs" class="form-control required">
                    </div>
                </div>

                <div class="row next-row">
                    <label class="col-md-3 control-label" style="text-align: right; margin-top:5px">RCP标准 &nbsp;<span style="color:red">*</span></label>
                    <div class="col-md-2">
                        <select id="standard" name="standard" class="form-control required">
                            <option value="1">RCP240</option>
                            <option value="2">RCP400</option>
                        </select>
                    </div>


                    <label class="col-md-3 control-label" style="text-align: right; margin-top:5px">时间标准 &nbsp;<span style="color:red">*</span></label>
                    <div class="col-md-2">
                        <select id="timeStandard" name="timeStandard" class="form-control required">
                            <option value="1">ACTP</option>
                            <option value="2">ACP</option>
                        </select>
                    </div>
                </div>

                <div class="row next-row">
                    <label class="col-md-3 control-label" style="text-align: right; margin-top:5px">管制区</label>
                    <div class="col-md-2">
                        <input type="text" name="atsp" class="form-control required">
                    </div>

                    <label class="col-md-3 control-label" style="text-align: right; margin-top:5px">起始时间</label>
                    <div class="col-md-2">
                        <input type="text" name="beginDate" class="form-control datepicker">
                    </div>
                <#--</div>-->

                <#--<div class="row next-row">-->
                    <label class="col-md-3 control-label" style="text-align: right; margin-top:5px">结束时间</label>
                    <div class="col-md-2">
                        <input type="text" name="endDate" class="form-control datepicker">
                    </div>
                </div>

                    <div class="form-group">
                        <div class="row">
                            <div class="col-md-10" style="text-align: center">
                                <div class="form-group">
                                    <label class="col-md-2 "><h3>&nbsp;&nbsp;选择要显示的系列</h3></label>
                                </div>
                            </div>

                            <div class="col-md-11" style="text-align: center">
                                <#--<div>-->

                                <#--</div>-->
                                <!-- Tabs-->
                                <div class="tabbable tabbable-custom tabs-left">
                                    <!-- Only required for left/right tabs -->
                                    <ul class="nav nav-tabs tabs-left">
                                        <li class="active"><a href="#tab_3_0" data-toggle="tab">所有数据</a></li>
                                        <li ><a href="#tab_3_1" data-toggle="tab" onclick="getCpdlcParameter('1')">月份</a></li>
                                        <li><a href="#tab_3_2" data-toggle="tab" onclick="getCpdlcParameter('2')">管制区</a></li>
                                        <li><a href="#tab_3_3" data-toggle="tab" onclick="getCpdlcParameter('3')">媒体类型</a></li>
                                        <li><a href="#tab_3_4" data-toggle="tab" onclick="getCpdlcParameter('4')">机型</a></li>
                                        <li><a href="#tab_3_5" data-toggle="tab" onclick="getCpdlcParameter('5')">航空公司</a></li>
                                        <li><a href="#tab_3_6" data-toggle="tab" onclick="getCpdlcParameter('6')">地面站</a></li>
                                        <#--<li ><a href="#tab_3_1" data-toggle="tab">月份</a></li>-->
                                        <#--<li><a href="#tab_3_2" data-toggle="tab">管制区</a></li>-->
                                        <#--<li><a href="#tab_3_3" data-toggle="tab">媒体类型</a></li>-->
                                        <#--<li><a href="#tab_3_4" data-toggle="tab">机型</a></li>-->
                                        <#--<li><a href="#tab_3_5" data-toggle="tab">航空公司</a></li>-->
                                        <#--<li><a href="#tab_3_6" data-toggle="tab">地面站</a></li>-->
                                    </ul>
                                    <div class="tab-content">
                                        <!--所有数据-->
                                        <div class="tab-pane active" id="tab_3_0">
                                            <div class="col-md-1">
                                                <label class="checkbox"><input type="checkbox" name="sport" class="required"> all</label>
                                                <#--<label for="sport" class="has-error help-block" generated="true" style="..."></label>-->
                                            </div>
                                        </div>
                                        <!--月份-->
                                        <div class="tab-pane " id="tab_3_1">

                                        </div>
                                        <!--管制区-->
                                        <div class="tab-pane" id="tab_3_2">

                                        </div>
                                        <!--媒体类型-->
                                        <div class="tab-pane" id="tab_3_3">

                                        </div>
                                        <!--机型-->
                                        <div class="tab-pane" id="tab_3_4">

                                        </div>
                                        <!--航空公司-->
                                        <div class="tab-pane"  id="tab_3_5">

                                        </div>
                                        <!--地面站-->
                                        <div class="tab-pane" id="tab_3_6">

                                        </div>
                                        <div class="tab-pane" id="tab_3_7">

                                        </div>
                                    </div>
                                </div>
                                <!--END TABS-->
                                <button class="btn btn-primary" id="submit" onclick="submitBtn()" >提交</button>
                            </div>
                        </div>
                    </div>

            </form>
            <div class="panel-heading">
            </div>
            </div>
        </div>
    <!-- /Page Content -->
    </div>

</body>
</html>