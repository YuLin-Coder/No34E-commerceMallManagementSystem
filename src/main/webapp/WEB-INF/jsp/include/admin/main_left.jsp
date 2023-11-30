
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>菜单</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <link rel="stylesheet" type="text/css" href="css/back/nav.css">
    <link rel="stylesheet" type="text/css" href="font/iconfont.css">
    <script type="text/javascript" src="js/jquery/2.0.0/jquery.min.js"></script>
    <script type="text/javascript" src="js/nav.js"></script>
</head>
<body>
<div class="nav">
    <div style="text-align:center; ">
        <img src="images/admin/face.jpg" style="height: 100px;margin-top: 5px; border-radius:200px; ">
    </div>
    <div style="text-align:center; ">
        <p><font style="font-family:微软雅黑;color: white;font-size: 16px;">北冥有鱼</font></p>
    </div>
    <ul>
        <li class="nav-item">
            <a href="/fore/foreHome" target="_blank"><strong>网站首页</strong></a>
        </li>
        <li class="nav-item">
            <a href="javascript:;">分类管理</a>
            <ul>
                <li><a href="category/category_list" target="right"><span>分类列表</span></a></li>
                <li><a href="category/addCategoryPage" target="right"><span>添加分类</span></a></li>
            </ul>
        </li>
        <li class="nav-item">
            <a href="javascript:;">商品管理</a>
            <ul>
                <li><a href="product/getItemList" target="right"><span>商品列表</span></a></li>
            </ul>
        </li>
        <li class="nav-item">
            <a href="javascript:;">会员管理</a>
            <ul>
                <li><a href="user/getUserList" target="right"><span>会员列表</span></a></li>
            </ul>
        </li>
        <li class="nav-item">
            <a href="javascript:;">订单管理</a>
            <ul>
                <li><a href="order/getOrderList" target="right"><span>订单列表</span></a></li>
            </ul>
        </li>
        <li class="nav-item">
            <a href="javascript:;">积分管理</a>
            <ul>
                <li><a href="credit/getCreditList" target="right"><span>积分列表</span></a></li>
                <li><a href="credit/creditRule" target="right"><span>积分规则</span></a></li>
            </ul>
        </li>
        <li class="nav-item">
            <a href="javascript:;">系统管理</a>
            <ul>
                <li><a href="admin/getAdminList" target="right"><span>用户列表</span></a></li>
                <li><a href="admin/addAdminPage" target="right"><span>添加用户</span></a></li>
            </ul>
        </li>
    </ul>
</div>
</body>
</html>
