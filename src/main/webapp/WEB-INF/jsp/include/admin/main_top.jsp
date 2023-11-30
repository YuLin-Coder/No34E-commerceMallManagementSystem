
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    Date date = new Date();
    SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
    String today = df.format(date);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>导航页面</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <link href="css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet" type="text/css">
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation" style="background: #263238">
    <div class="navbar-header">
        <a class="navbar-brand">SSM实现简易商城</a>
    </div>
    <div class="col-md-4 col-md-offset-6">
        <p class="navbar-text">
            当前用户:&nbsp;&nbsp;
            <font color="red"><%= session.getAttribute("admin")%></font>&nbsp;&nbsp;
            登录时间:&nbsp;&nbsp;<font color="red"><%=today %></font>
        <img class="img-circle" src="images/admin/face.jpg" style="height: 42px">

    </div>
</nav>
<script src="js/jquery/2.0.0/jquery.min.js"></script>
<script src="js/bootstrap/3.3.6/bootstrap.min.js"></script>
</body>
</html>

