<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0" />
    <title>PBCS</title>
    <!--=== CSS ===-->
    <!-- Bootstrap -->
    <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <!-- Theme -->
    <link href="../assets/css/main.css" rel="stylesheet" type="text/css" />
    <link href="../assets/css/plugins.css" rel="stylesheet" type="text/css" />
    <link href="../assets/css/responsive.css" rel="stylesheet" type="text/css" />
    <link href="../assets/css/icons.css" rel="stylesheet" type="text/css" />
    <!-- Login -->
    <link href="../assets/css/login.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="../assets/css/fontawesome/font-awesome.min.css">
    <!--[if IE 7]>
    <link rel="stylesheet" href="../assets/css/fontawesome/font-awesome-ie7.min.css">
    <![endif]-->
    <!--[if IE 8]>
    <link href="../assets/css/ie8.css" rel="stylesheet" type="text/css" />
    <![endif]-->
    <!--=== JavaScript ===-->
    <script type="text/javascript" src="../assets/js/libs/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="../bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../assets/js/libs/lodash.compat.min.js"></script>
    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="../assets/js/libs/html5shiv.js"></script>
    <![endif]-->
    <!-- Beautiful Checkboxes -->
    <script type="text/javascript" src="../plugins/uniform/jquery.uniform.min.js"></script>
    <!-- Form Validation -->
    <script type="text/javascript" src="../plugins/validation/jquery.validate.min.js"></script>
    <!-- Slim Progress Bars -->
    <script type="text/javascript" src="../plugins/nprogress/nprogress.js"></script>
    <!-- App -->
    <script type="text/javascript" src="../assets/js/login.js"></script>

    <script>
        //登录基本较验
        $(document).ready(function(){
            Login.init(); // Init login JavaScript
        });
        //重置
        function Reset() {
            document.form1.username.value="";
            document.form1.password.value="";
        }
    </script>
</head>

<body class="login">
<!-- Logo -->
<div class="logo">
    <img src="../assets/img/logo2.png" alt="logo" />
    <strong>PBCS</strong>
</div>
<!-- /Logo -->

<!-- Login Box -->
<div class="box">
    <div class="content">
        <!-- Login Formular -->
        <form  action="/userLogin" name="form1" class="form-vertical login-form" method="post">
            <!-- Title -->
            <h3 class="form-title">Sign In to your Account</h3>

            <!-- Error Message -->
            <div class="alert fade in alert-danger" style="display: none;">
                <i class="icon-remove close" data-dismiss="alert"></i>
                Enter any username and password.
            </div>

            <!-- Input Fields -->
            <div class="form-group">
                <!--<label for="username">Username:</label>-->
                <div class="input-icon">
                    <i class="icon-user"></i>
                    <input type="text" name="username" class="form-control" placeholder="Username" autofocus="autofocus" data-rule-required="true" data-msg-required="Please enter your username." />
                </div>
            </div>
            <div class="form-group">
                <!--<label for="password">Password:</label>-->
                <div class="input-icon">
                    <i class="icon-lock"></i>
                    <input type="password" name="password" class="form-control" maxlength="8" placeholder="Password" data-rule-required="true" data-msg-required="Please enter your password." />
                </div>
            </div>
            <!-- /Input Fields -->

            <!-- Form Actions -->
            <div class="form-actions ">
                <button type="button" class="submit btn btn-primary pull-left" onclick="Reset()">
                    Reset
                </button>
                <#--<label class="checkbox pull-left"><input type="checkbox" class="uniform" name="remember"> Remember me</label>-->
                <button type="submit" class="submit btn btn-primary pull-right">
                    Sign In <i class="icon-angle-right"></i>
                </button>
            </div>
        </form>
        <!-- /Login Formular -->
    </div> <!-- /.content -->

</div>
<!-- /Login Box -->

</body>
</html>