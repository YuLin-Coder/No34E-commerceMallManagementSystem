<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/fore/top.jsp"%>

<div id="category">
    <div class="categoryPageDiv">

        <c:if test="${empty param.categorycount}">
            <c:set var="categorycount" scope="page" value="100"/>
        </c:if>

        <c:if test="${!empty param.categorycount}">
            <c:set var="categorycount" scope="page" value="${param.categorycount}"/>
        </c:if>

        <div class="categoryProducts">
            <c:forEach items="${category.products}" var="p" varStatus="stc">
                <c:if test="${stc.count<=categorycount}">
                    <div class="productUnit" price="${p.promote_price}">
                        <div class="productUnitFrame">
                            <a href="foreproduct?pid=${p.id}">
                                <img class="productImage" src="/images/productSingle_middle/${p.showPicture.id}.jpg">
                            </a>
                            <span class="productPrice">¥<fmt:formatNumber type="number" value="${p.promote_price}" minFractionDigits="2"/></span>
                            <a class="productLink" href="foreproduct?pid=${p.id}">
                                    ${fn:substring(p.name, 0, 50)}
                            </a>
                            <a  class="tmallLink" href="foreproduct?pid=${p.id}">商城专卖</a>

                            <div class="show1 productInfo">
                                <span class="monthDeal ">月成交 <span class="productDealNumber">${p.saleCount}笔</span></span>
                            </div>
                        </div>
                    </div>
                </c:if>
            </c:forEach>
            <div style="clear:both"></div>
        </div>

    </div>
</div>
