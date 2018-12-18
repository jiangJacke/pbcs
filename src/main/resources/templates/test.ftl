<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
<#--<script type="text/javascript" src="./jquery-3.2.1/jquery.js"/>-->
<head>
    <title>Hello World!</title>
</head>
<body>
<h1>前后台传值Test</h1><br/>
${name}<br/>
${age}<br/>
${time?date}
${time?time}

</body>
</html>