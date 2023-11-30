<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*"%>

<%@include file="../include/admin/adminHeader.jsp"%>

<script>
    $(function() {
        $("#addForm").submit(function() {
            if (!checkEmpty("name", "管理员姓名"))
                return false;
            if (!checkEmpty("type", "角色"))
                return false;
            if (!checkEmpty("password", "密码"))
                return false;
            return true;
        });
    });
    $(function(){
        <c:if test="${!empty message}">
            $(".errorMessage").html("${message}");
        </c:if>
    });
</script>

<div class="panel panel-warning addDiv" style="margin-top: 100px; margin-left: 50px; margin-right: 50px">
    <div class="panel-heading">新增用户</div>

    <div class="addErrorMessageDiv">
        <div class="alert alert-info">
            <span class="errorMessage"></span>
        </div>
    </div>

    <div class="panel-body">
        <form method="post" id="addForm" action="/admin/admin_add">
            <table class="addTable">
                <tr>
                    <td>姓名</td>
                    <td><input id="name" name="name" type="text"
                               class="form-control"></td>
                </tr>
                <tr>
                    <td>角色</td>
                    <td><input id="type" name="type" type="text" placeholder="普通管理员/超级管理员"
                               class="form-control"></td>
                </tr>
                <tr>
                    <td>密码</td>
                    <td><input id="password" name="password" type="text"
                               class="form-control"></td>
                </tr>
                <tr class="submitTR">
                    <td colspan="2" align="center">
                        <button type="submit" class="btn btn-success">提 交</button>
                        <button type="reset" class="btn btn-primary">重置</button>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>