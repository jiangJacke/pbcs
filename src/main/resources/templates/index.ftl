<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>PBCS</title>

    <!-- 包含头部信息用于适应不同设备
    -->
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

    <!-- DataTables -->
    <script type="text/javascript" src="plugins/datatables/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="plugins/datatables/DT_bootstrap.js"></script>
    <script type="text/javascript" src="plugins/datatables/responsive/datatables.responsive.js"></script> <!-- optional -->

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
    function submitBtn() {
        var submitBtn = document.getElementById("submit");
        // submitBtn.onclick=function (ev) {
        var startTime = $("input[name='startTime']").val();
        var endTime = $("input[name='endTime']").val();
        if(startTime > endTime){
            alert("起始时间不能大于结束时间"); return false;
        }
        if (startTime == "" || endTime == "") {
            alert("时间不能为空，请输入时间"); return false;
        }
        // }
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
        <a class="navbar-brand" href=".html">
            <img src="../assets/img/logo1.png" alt="logo" />
            <strong>PBCS</strong>
        </a>
        <!-- /logo -->

        <!-- Sidebar Toggler -->
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
                    <a href="">
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
                            <a href=".ftl">
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
                        Import  CSV
                    </a>
                </li>

                <li>
                    <a href="javascript:void(0);">
                        Clean imported  Data
                    </a>
                </li>

                <li>
                    <a href="">
                         Graphs
                    </a>
                </li>

                <li>
                    <a href="javascript:void(0);">
                        Delete ADS-C Data
                    </a>
                </li>

                <li>
                    <a href="javascript:void(0);">
                        Celete  Data
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
                <h3>Oracle import to mongodb</h3>
            </div>
            <form class="form-horizontal row-border" action="/Controller" method="post">

                <div class="form-group" >
                    <label class="col-md-3 control-label" style="text-align: right; margin-top:5px">Start time:</label>
                    <div class="col-md-2">
                        <input type="text" name="startTime" class="form-control datepicker">
                    </div>

                    <label class="col-md-3 control-label" style="text-align: right; margin-top:5px">End time:</label>
                    <div class="col-md-2">
                        <input type="text" name="endTime" class="form-control datepicker">
                    </div>

                    <div class="col-md-2">
                        <button class="btn btn-primary" type="submit" id="submit" onclick="submitBtn">Import</button>
                    </div>
                </div>

            </form>

            <!--=== Normal ===-->
            <div class="row">
                <div class="col-md-12">
                    <div class="widget-content">
                    <#--<table id="adsgold"
                           data-url=""
                           data-striped="true"
                           data-sort-name="idNo"
                           data-side-pagination="server"
                           data-query-params-type="not-limit"
                           data-click-to-select="true"
                           data-single-select="true"
                           data-pagination="true"
                           data-smart-display="true"
                           data-show-header="ressponseHeader"
                           data-page-list="[10,20,50]"
                           data-toolbar="#customer-toolbar"
                           target="pbcsTable"
                           tableWidth="800">
                        <thead>
                        <tr>
                            <th data-field="atsp" data-align="left" data-width="120px" data-lang="atsp">atsp</th>></th>
                            <th data-field="an" data-align="left" data-width="120px" data-lang="an">an</th>></th>
                            <th data-field="actype" data-align="left" data-width="120px" data-lang="actype">actype</th>></th>
                            <th data-field="operater" data-align="left" data-width="120px" data-lang="operater">operater</th>></th>
                            <th data-field="operater" data-align="left" data-width="120px" data-lang="operater">operater</th>></th>
                            <th data-field="msgdate" data-align="left" data-width="120px" data-lang="msgdate">msgdate</th>></th>
                            <th data-field="rgs" data-align="left" data-width="120px" data-lang="rgs">rgs</th>></th>
                            <th data-field="msgtype" data-align="left" data-width="120px" data-lang="msgtype">msgtype</th>></th>
                            <th data-field="lat" data-align="left" data-width="120px" data-lang="lat">lat</th>></th>
                            <th data-field="lon" data-align="left" data-width="120px" data-lang="lon">lon</th>></th>
                            <th data-field="sendtime" data-align="left" data-width="120px" data-lang="sendtime">sendtime</th>></th>
                            <th data-field="dwnmsgtime" data-align="left" data-width="120px" data-lang="dwnmsgtime">dwnmsgtime</th>></th>
                            <th data-field="transtime" data-align="left" data-width="120px" data-lang="transtime">transtime</th>></th>
                            <th data-field="media" data-align="left" data-width="120px" data-lang="media">media</th>></th>
                        </tr>
                        </thead>
                    </table>-->
                        <table class="table table-striped table-bordered table-hover table-checkable datatable">
                            <thead>
                            <tr>
                                <th>atsp</th>
                                <th>an</th>
                                <th>actype</th>
                                <th>operater</th>
                                <th>msgdate</th>
                                <th>rgs</th>
                                <th>msgtype</th>
                                <th>lat</th>
                                <th>lon</th>
                                <th>sendtime</th>
                                <th>dwnmsgtime</th>
                                <th>transtime</th>
                                <th>media</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                            <#--<td class="checkbox-column">-->
                            <#--<input type="checkbox" class="uniform">-->
                            <#--</td>-->
                                <td>Joey</td>
                                <td>Greyson</td>
                                <td class="hidden-xs">joey123</td>
                                <td><span class="label label-success">Approved</span></td>
                                <td><span class="label label-success">Approved</span></td>
                                <td><span class="label label-success">Approved</span></td>
                                <td><span class="label label-success">Approved</span></td>
                                <td><span class="label label-success">Approved</span></td>
                                <td><span class="label label-success">Approved</span></td>
                                <td><span class="label label-success">Approved</span></td>
                                <td><span class="label label-success">Approved</span></td>
                                <td><span class="label label-success">Approved</span></td>
                                <td><span class="label label-success">Approved</span></td>
                            </tr>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <!-- /Normal -->


    </div>
    <!-- /Page Content -->
</div>

</body>
</html>