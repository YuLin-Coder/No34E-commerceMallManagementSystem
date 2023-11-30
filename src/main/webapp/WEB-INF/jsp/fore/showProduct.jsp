<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/fore/top.jsp"%>

<title>简易商城 ${product.name}</title>

<div class="productPageDiv">
    <script>
        $(function(){
            var stock = ${product.stock};
            $(".productNumberSetting").keyup(function(){
                var num= $(".productNumberSetting").val();
                num = parseInt(num);
                if(isNaN(num))
                    num= 1;
                if(num<=0)
                    num = 1;
                if(num>stock)
                    num = stock;
                $(".productNumberSetting").val(num);
            });

            $(".increaseNumber").click(function(){
                var num= $(".productNumberSetting").val();
                num++;
                if(num>stock)
                    num = stock;
                $(".productNumberSetting").val(num);
            });
            $(".decreaseNumber").click(function(){
                var num= $(".productNumberSetting").val();
                --num;
                if(num<=0)
                    num=1;
                $(".productNumberSetting").val(num);
            });

            $(".addCartLink").click(function(){
                var page = "forecheckLogin";
                $.get(
                    page,
                    function(result){
                        if("success"==result){
                            var pid = ${product.id};
                            var num= $(".productNumberSetting").val();
                            var addCartpage = "foreaddCart";
                            $.get(
                                addCartpage,
                                {"pid":pid,"num":num},
                                function(result){
                                    if("success"==result){
                                        $(".addCartButton").html("已加入购物车");
                                        $(".addCartButton").attr("disabled","disabled");
                                        $(".addCartButton").css("background-color","lightgray")
                                        $(".addCartButton").css("border-color","lightgray")
                                        $(".addCartButton").css("color","black")

                                    }
                                }
                            );
                        }
                        else{
                            $("#loginModal").modal('show');
                        }
                    }
                );
                return false;
            });
            $(".buyLink").click(function(){
                var page = "forecheckLogin";
                $.get(
                    page,
                    function(result){
                        if("success"==result){
                            var num = $(".productNumberSetting").val();
                            location.href= $(".buyLink").attr("href")+"&num="+num;
                        }
                        else{
                            $("#loginModal").modal('show');
                        }
                    }
                );
                return false;
            });

            $("button.loginSubmitButton").click(function(){
                var name = $("#name").val();
                var password = $("#password").val();

                if(0==name.length||0==password.length){
                    $("span.errorMessage").html("请输入账号密码");
                    $("div.loginErrorMessageDiv").show();
                    return false;
                }

                var page = "foreloginAjax";
                $.post(
                    page,
                    {"name":name,"password":password},
                    function(result){
                        if("success"==result){
                            location.reload();
                        }
                        else{
                            $("span.errorMessage").html("账号密码错误");
                            $("div.loginErrorMessageDiv").show();
                        }
                    }
                );

                return true;
            });

            $("img.smallImage").mouseenter(function(){
                var bigImageURL = $(this).attr("bigImageURL");
                $("img.bigImg").attr("src",bigImageURL);
            });

            $("img.bigImg").load(
                function(){
                    $("img.smallImage").each(function(){
                        var bigImageURL = $(this).attr("bigImageURL");
                        img = new Image();
                        img.src = bigImageURL;

                        img.onload = function(){
                            console.log(bigImageURL);
                            $("div.img4load").append($(img));
                        };
                    });
                }
            );
        });

    </script>

    <div class="imgAndInfo">

        <div class="imgInimgAndInfo">
            <img src="/images/productSingle/${productImage.id}.jpg" class="bigImg">
            <div class="smallImageDiv">
                <c:forEach items="${product.productImages}" var="pi">
                    <img src="/images/productSingle_small/${pi.id}.jpg" bigImageURL="/images/productSingle/${pi.id}.jpg" class="smallImage">
                </c:forEach>
            </div>
            <div class="img4load hidden" ></div>
        </div>

        <div class="infoInimgAndInfo">

            <div class="productTitle">
                ${product.name}
            </div>

            <div class="productPrice">

                <div class="productPriceDiv">

                    <div class="originalDiv">
                        <span class="originalPriceDesc">价格</span>
                        <span class="originalPriceYuan">¥</span>
                        <span class="originalPrice">
						<fmt:formatNumber type="number" value="${product.original_price}" minFractionDigits="2"/>
					</span>
                    </div>
                    <div class="promotionDiv">
                        <span class="promotionPriceDesc">促销价 </span>
                        <span class="promotionPriceYuan">¥</span>
                        <span class="promotionPrice">
						<fmt:formatNumber type="number" value="${product.promote_price}" minFractionDigits="2"/>
					</span>
                    </div>
                </div>
            </div>
            <div class="productSaleAndReviewNumber">
                <div>销量 <span class="redColor boldWord"> ${product.saleCount}</span></div>
            </div>
            <div class="productNumber">
                <span>数量</span>
                <span>
				<span class="productNumberSettingSpan">
				<input class="productNumberSetting" type="text" value="1">
				</span>
				<span class="arrow">
					<a href="#nowhere" class="increaseNumber">
					<span class="updown">
							<img src="/images/site/increase.png">
					</span>
					</a>

					<span class="updownMiddle"> </span>
					<a href="#nowhere"  class="decreaseNumber">
					<span class="updown">
							<img src="/images/site/decrease.png">
					</span>
					</a>
				</span>件</span>
                <span>库存${product.stock}件</span>
            </div>

            <div class="buyDiv">
                <a class="buyLink" href="forebuyFirst?pid=${product.id}"><button class="buyButton">立即购买</button></a>
                <a href="#nowhere" class="addCartLink"><button class="addCartButton" id="addCartButton"><span class="glyphicon glyphicon-shopping-cart"></span>加入购物车</button></a>
            </div>
        </div>

        <div style="clear:both"></div>

    </div>

    <div class="productDetailDiv" >
        <div class="productDetailTopPart">
            <a href="#nowhere" class="productDetailTopPartSelectedLink selected">商品详情</a>
        </div>
        <div class="productParamterPart">
            <div class="productParamter">产品参数：</div>
            <div class="productParamterList">
                <c:forEach items="${propertyValues}" var="pv">
                    <span>${pv.property.name}:  ${fn:substring(pv.value, 0, 10)} </span>
                </c:forEach>
            </div>
            <div style="clear:both"></div>
        </div>
    </div>


    <div class="modal " id="loginModal" tabindex="-1" role="dialog" >
        <div class="modal-dialog loginDivInProductPageModalDiv">
            <div class="modal-content">
                <div class="loginDivInProductPage">
                    <div class="loginErrorMessageDiv">
                        <div class="alert alert-danger" >
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close"></button>
                            <span class="errorMessage"></span>
                        </div>
                    </div>

                    <div class="login_acount_text">账户登录</div>
                    <div class="loginInput " >
                                <span class="loginInputIcon ">
                                    <span class=" glyphicon glyphicon-user"></span>
                                </span>
                        <input id="name" name="name" placeholder="会员名" type="text">
                    </div>

                    <div class="loginInput " >
                                <span class="loginInputIcon ">
                                    <span class=" glyphicon glyphicon-lock"></span>
                                </span>
                        <input id="password" name="password"  type="password" placeholder="密码">
                    </div>
                    <span class="text-danger">模态窗口</span><br><br>
                    <div>
                        <a href="#nowhere">忘记登录密码</a>
                        <a href="/user/foreLogin" class="pull-right">免费注册</a>
                    </div>
                    <div style="margin-top:20px">
                        <button class="btn btn-block redButton loginSubmitButton" type="submit">登录</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>