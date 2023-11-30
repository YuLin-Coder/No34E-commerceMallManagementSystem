
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>

<div class="workingArea">

    <ol class="breadcrumb">
        <li><a href="/category/category_list">所有分类</a></li>
        <li class="active">商品列表</li>
    </ol>

    <div class="listDataTableDiv" style="width: 1000px">
        <table
                class="table table-bordered table-hover  table-condensed">
            <thead>
            <tr class="success">
                <th>ID</th>
                <th>图片</th>
                <th>商品名称</th>
                <th>原价</th>
                <th>优惠价</th>
                <th>库存</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${ps}" var="p">
                <tr>
                    <td>${p.id}</td>
                    <td>
                        <c:if test="${!empty p.showPicture}">
                            <img width="40px" src="/images/productSingle/${p.showPicture.id}.jpg">
                        </c:if>
                    </td>
                    <td>${p.name}</td>
                    <td>${p.original_price}</td>
                    <td>${p.promote_price}</td>
                    <td>${p.stock}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="pageDiv">
        <%@include file="../include/admin/adminPage.jsp"%>
    </div>

</div>
