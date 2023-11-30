<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>后台管理</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

    <frameset rows="6%,*" frameborder="no" border="0" framespacing="0">
        <frame src="./top" scrolling="no">
        <frameset rows="90%" frameborder="no" border="0" framespacing="0">
            <frameset cols="18%,*">
                <frame src="./left" />
                <frame name="right" src="./content" />
            </frameset>
        </frameset>
    </frameset>
</head>
<body>
</body>
</html>
