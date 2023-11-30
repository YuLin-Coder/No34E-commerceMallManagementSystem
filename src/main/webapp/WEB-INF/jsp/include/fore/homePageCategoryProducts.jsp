<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

    <c:if test="${empty param.categorycount}">
        <c:set var="categorycount" scope="page" value="10"/>
    </c:if>

    <c:if test="${!empty param.categorycount}">
        <c:set var="categorycount" scope="page" value="${param.categorycount}"/>
    </c:if>

<div class="homepageCategoryProducts">
    <%--varstatus表示当前遍历到了第几个元素,category存储的对象在ForeController这个控制器里面--%>
    <c:forEach items="${categories}" var="c" varStatus="stc">
        <c:if test="${stc.count<=categorycount}">
            <div class="eachHomepageCategoryProducts">
                <span class="categoryTitle">${c.name}</span>
                <br>
                <c:forEach items="${c.products}" var="p" varStatus="st">
                    <c:if test="${st.count<=5}">
                        <div class="productItem" >
                            <%--<a href="foreproduct?pid=${p.id}"><img width="100px" src="/images/productSingle_middle/${p.showPicture.id}.jpg"></a>--%>
                            <a href="foreproduct?pid=${p.id}"><img width="100px" src="/images/productSingle_middle/${p.showPicture.id}.jpg"></a>
                            <a class="productItemDescLink" href="foreproduct?pid=${p.id}">
								<span class="productItemDesc">[热销]
								    ${fn:substring(p.name, 0, 20)}
								</span>
                            </a>
                            <span class="productPrice">
                                原价: <fmt:formatNumber type="number" value="${p.original_price}" minFractionDigits="2"/><br/>
								现价: <fmt:formatNumber type="number" value="${p.promote_price}" minFractionDigits="2"/>
							</span>
                        </div>
                    </c:if>
                </c:forEach>
                <div style="clear:both"></div>
            </div>
        </c:if>
    </c:forEach>
</div>
