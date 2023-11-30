<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<link rel="stylesheet" type="text/css" href="css/back/style.css">

<script>
    $(function(){
        $("#addForm").submit(function(){
            if(!checkEmpty("name","分类名称"))
                return false;
            if(!checkEmpty("categoryPic","分类图片"))
                return false;
            return true;
        });
    });
</script>

<div class="panel panel-warning addDiv" style="margin-top: 100px; margin-left: 50px; margin-right: 50px">
        <div class="panel-heading">新增分类</div>
        <div class="panel-body">
            <form id="addForm" action="/category/category_add" method="post" enctype="multipart/form-data">
                <table class="addTable">
                    <tr>
                        <td>分类名称:</td>
                        <td><input  id="name" name="name" type="text" class="form-control"></td>
                    </tr>
                    <tr>
                        <td>分类图片</td>
                        <td>
                            <input id="categoryPic" accept="image/*" type="file" name="multipartFile" />
                        </td>
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
