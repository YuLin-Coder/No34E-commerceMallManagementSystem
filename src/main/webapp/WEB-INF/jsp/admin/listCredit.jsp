
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>

<div class="workingArea" style="padding-right: 50px; padding-left: 50px">
    <h1 class="label label-info" >积分排行榜</h1>
    <br>
    <br>

    <div class="listDataTableDiv" >
        <table class="table table-bordered table-hover  table-condensed">
            <thead>
            <tr class="success">
                <th>用户ID</th>
                <th>积分数量</th>
                <th>积分等级</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${users}" var="u">
                <tr>
                    <td>${u.name}</td>
                    <td>${u.credit.number}</td>
                    <td>${u.credit.name}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="pageDiv">
        <%@include file="../include/admin/adminPage.jsp" %>
    </div>
</div>
