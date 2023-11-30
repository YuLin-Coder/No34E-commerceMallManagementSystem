<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/fore/top.jsp"%>

<div id="searchResult">

    <div class="searchResultDiv">
        <div class="searchProducts">

            <c:forEach items="${products}" var="p">
                <div class="productUnit" price="${p.promote_price}">
                    <a href="foreproduct?pid=${p.id}">
                        <img class="productImage" src="/images/productSingle/${p.showPicture.id}.jpg">
                    </a>
                    <span class="productPrice">¥<fmt:formatNumber type="number" value="${p.promote_price}" minFractionDigits="2"/></span>
                    <a class="productLink" href="foreproduct?pid=${p.id}">
                            ${fn:substring(p.name, 0, 50)}
                    </a>

                    <a class="tmallLink" href="foreproduct?pid=${p.id}">商城专卖</a>

                    <div class="productInfo">
                        <span class="monthDeal ">月成交 <span class="productDealNumber">${p.saleCount}笔</span></span>

                    </div>

                </div>
            </c:forEach>

            <c:if test="${empty products}">
              <div class="noMatch">没有满足条件的产品<div>
             </c:if>

                <div style="clear:both"></div>
            </div>
            </div>

</div>
