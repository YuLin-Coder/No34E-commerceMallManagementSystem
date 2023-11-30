<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="/js/jquery/2.0.0/jquery-1.8.3.js"></script>
<html>
<head>
    <title>查询展示页面</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>Bootstrap HelloWorld</title>

    <!-- Bootstrap -->
    <link href="/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">

    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="/js/jquery/2.0.0/jquery.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="/js/bootstrap/3.3.6/bootstrap.min.js"></script>

    <script>
        $(function() {
            $("#addForm").submit(function() {
                if (!checkEmpty("name", "产品名称"))
                    return false;
                if (!checkNumber("original_price", "原价格"))
                    return false;
                if (!checkNumber("promote_price", "优惠价格"))
                    return false;
                if (!checkInt("stock", "库存"))
                    return false;
                return true;
            });
        });
    </script>
</head>
<body>

<div class="panel panel-warning addDiv">
    <div class="panel-heading">新增产品</div>
    <div class="panel-body">
        <form method="post" id="addForm" action="/product/product_add">
            <table class="addTable">
                <tr>
                    <td>产品名称</td>
                    <td><input id="name" name="name" type="text"
                               class="form-control"></td>
                </tr>
                <tr>
                    <td>原价格</td>
                    <td><input value="99.98" name="original_price" type="text"
                               class="form-control"></td>
                </tr>
                <tr>
                    <td>优惠价格</td>
                    <td><input id="promote_price"  value="19.98" name="promote_price" type="text"
                               class="form-control"></td>
                </tr>
                <tr>
                    <td>库存</td>
                    <td><input id="stock"  value="99" name="stock" type="text"
                               class="form-control"></td>
                </tr>
                <tr class="submitTR">
                    <td colspan="2" align="center">
                        <input type="hidden" name="cid" value="${c.id}">
                        <button type="submit" class="btn btn-success">提 交</button>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>

</body>
</html>
