
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人信息查看</title>
</head>
<body>

    <div style="text-align: center">
        <h1>我的信息</h1>

        <form>
             <tr> 用户名:   ${user.name} </tr>     <br>
             <tr> 性  别:     ${user.sex}  </tr>       <br>
             <tr> 手机号:   ${user.telephone} </tr>         <br>
             <tr>地  址:     ${user.address}</tr>      <br>
             <tr>邮  箱:     ${user.email}</tr>        <br>
             <tr>积分等级: ${user.credit.name}</tr>      <br>
             <tr>积分数量: ${user.credit.number}</tr>    <br>

        </form>
    </div>

</body>
</html>
