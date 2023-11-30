

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<script type="text/javascript" src="/js/jquery/2.0.0/jquery-1.8.3.js"></script>


<script>
    function finds(){
        var product =$("#product").val();
        if(product==""){
            alert("亲，不可以输入空的查询条件呢！");
            return false;
        }
        else
        {
            $.ajax({
                type:'post',
                url:'finds',
                dataType:"json",//注意使用的是dataType，而不是Content-Type
                async: true,
                data:{product:product},
                success:function(data){
                    if(data==null){
                        alert("没有这个产品！");
                    }
                    else{
                        window.location.href ="test";
                    }
                }
            });
        }
    }
</script>

<div class="workingArea">

    <ol class="breadcrumb">
        <li><a href="/category/category_list">所有分类</a></li>
        <li><a href="/product/product_list?cid=${c.id}">${c.name}</a></li>
        <li class="active">产品管理</li>
    </ol>

    <%--<td style="text-align:right;">--%>
    <div>
        <td>
            <input type="text" name="product" id="product">
            <input type="button" value="搜索" onclick="finds()"/>
        </td>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input align="right" type="button" value="添加商品" onclick="window.location.href='wqAdd'">
    </div>


    <div class="listDataTableDiv">
        <table
                class="table table-bordered table-hover  table-condensed">
            <thead>
            <tr class="success">
                <th>ID</th>
                <th>图片</th>
                <th>名称</th>
                <th>原价</th>
                <th>优惠价</th>
                <th>库存</th>
                <th>图片</th>
                <th>属性</th>
                <th>编辑</th>
                <th>删除</th>
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
                    <td><a href="/productImage/productImage_list?pid=${p.id}"><span>图片</span></a></td>
                    <td><a href="/propertyValue/propertyValue_edit?pid=${p.id}"><span>属性</span></a></td>
                    <td><a href="product_edit?id=${p.id}"><span>编辑</span></a></td>
                    <td><a deleteLink="true" href="product_delete?id=${p.id}"><span>删除</span></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="pageDiv">
        <%@include file="../include/admin/adminPage.jsp"%>
    </div>



</div>
