 <%@ page language="java" contentType="text/html; charset=UTF-8"
                    pageEncoding="UTF-8" import="java.util.*"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@include file="../include/admin/adminHeader.jsp"%>
 <script type="text/javascript" src="/js/jquery/2.0.0/jquery-1.8.3.js"></script>
 <script>
     function finds(){
         var uname =$("#uname").val();
         if(uname==""){
             alert("伟大的管理员同志，您不可以输入空的哦！");
             return false;
         }
         else
         {
             $.ajax({
                 type:'post',
                 url:'finds',
                 dataType:"json",//注意使用的是dataType，而不是Content-Type
                 async: true,
                 data:{uname:uname},
                 success:function(data){
                     if(data==null){
                         alert("没有这个用户！");
                     }
                     else{
                         window.location.href ="test";
                     }
                 }
             });
         }
     }
 </script>
 <div class="container">
     <div class="row clearfix">
         <div class="col-md-12 column">
             <div class="page-header">
             </div>
         </div>
     </div>
     <div class="row">
         <div class="col-md-4 column">
             <a class="btn btn-primary" href="${pageContext.request.contextPath}/">添加用户</a>
         </div>
         <div class="col-md-8 column">
             <td >
                 用户名：<input type="text" name="uname" id="uname">
                 <input type="button" value="搜索" onclick="finds()"/><br> </td>
         </div>
     </div>
     <div class="workingArea" style="padding-right: 50px; padding-left: 50px">
         <h1 class="label label-info" >管理员管理</h1>
         <br>
         <br>

         <div class="listDataTableDiv" >
             <table class="table table-bordered table-hover  table-condensed">
                 <thead>
                 <tr class="success">
                     <th>ID</th>
                     <th>管理员名称</th>
                     <th>角色</th>
                     <th>删除</th>
                 </tr>
                 </thead>
                 <tbody>
                 <c:forEach items="${admins}" var="a">
                     <tr>
                         <td>${a.id}</td>
                         <td>${a.name}</td>
                         <td>${a.type}</td>
                         <td><a deleteLink="true" href="/admin/admin_delete?id=${a.id}"><span
                                 class=" 	glyphicon glyphicon-trash"></span></a></td>
                     </tr>
                 </c:forEach>
                 </tbody>
             </table>
         </div>

         <div class="pageDiv">
             <%@include file="../include/admin/adminPage.jsp" %>
         </div>
     </div>
 </div>