
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>桌面</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <link href="css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet" type="text/css">
    <style type="text/css">
        .table th, .table td {
            text-align: center;
            vertical-align: middle!important;
        }
    </style>
</head>
<%
    String osName = System.getProperty("os.name");
    String user = System.getProperty("user.name");
    String version = System.getProperty("java.version");
%>
<body>
<div class="container-fluid">
    <h3>我的桌面</h3>
    <div class="col-md-10">
        <table class="table table-bordered table-hover">
            <caption><strong>系统信息</strong></caption>
            <tbody>
            <tr>
                <td class="col-md-4">操作系统</td>
                <td><%=osName %></td>
            </tr>
            <tr>
                <td class="col-md-4">服务器名称</td>
                <td><%=request.getServerName() %></td>
            </tr>
            <tr>
                <td class="col-md-4">登录用户</td>
                <td><%=user %></td>
            </tr>
            </tbody>
        </table>
    </div>
    <div class="col-md-10">
        <table class="table table-bordered table-hover">
            <caption><strong>开发环境</strong></caption>
            <tbody>
            <tr>
                <td class="col-md-4">JDK</td>
                <td>JDK<%=version %></td>
            </tr>
            <tr>
                <td class="col-md-4">Tomcat</td>
                <td>Tomcat 9.0</td>
            </tr>
            <tr>
                <td class="col-md-4">数据库</td>
                <td>MySql 5.7</td>
            </tr>
            <tr>
                <td class="col-md-4">用户名</td>
                <td>root</td>
            </tr>
            <tr>
                <td class="col-md-4">密码</td>
                <td>admin</td>
            </tr>
            <tr>
                <td class="col-md-4">SpringMVC</td>
                <td>SpringMVC</td>
            </tr>
            <tr>
                <td class="col-md-4">Mybatis</td>
                <td>Mybatis 3.1.1</td>
            </tr>
            <tr>
                <td class="col-md-4">Spring</td>
                <td>Spring</td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
