
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*"%>
<%@include file="../include/admin/adminHeader.jsp"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>用户登录</title>
    <link rel="stylesheet" href="/css/fore/normalize.css">
    <link rel="stylesheet" href="/css/fore/login.css">
    <link rel="stylesheet" href="/css/fore/sign-up-login.css">
    <link rel="stylesheet" type="text/css" href="http://cdn.bootcss.com/font-awesome/4.6.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="/css/fore/inputEffect.css" />
    <link rel="stylesheet" href="/css/fore/tooltips.css" />
    <link rel="stylesheet" href="/css/fore/spop.min.css" />
    <link rel="stylesheet" href="/css/bootstrap/3.3.6/bootstrap.css" />

    <script src="/js/jquery/2.0.0/jquery.min.js"></script>
    <script src="/js/snow.js"></script>
    <script src="/js/jquery.pure.tooltips.js"></script>
    <script src="/js/spop.min.js"></script>
    <script>
        $(function() {
            $('#login #login-password').focus(function() {
                $('.login-owl').addClass('password');
            }).blur(function() {
                $('.login-owl').removeClass('password');
            });
            $('#login #register-password').focus(function() {
                $('.register-owl').addClass('password');
            }).blur(function() {
                $('.register-owl').removeClass('password');
            });
            $('#login #register-repassword').focus(function() {
                $('.register-owl').addClass('password');
            }).blur(function() {
                $('.register-owl').removeClass('password');
            });
        });

        function goto_register(){
            $("#register-username").val("");
            $("#register-password").val("");
            $("#register-repassword").val("");
            $("#tab-2").prop("checked",true);
        }

        function goto_login(){
            $("#login-username").val("");
            $("#login-password").val("");
            $("#tab-1").prop("checked",true);
        }

        function login(){//登录
            var value = $("#login-username").val();
            if (value.length==0){
                alert("用户名不能为空!")
                $("#login-username")[0].focus();
                return;
            }
            var value1 = $("#login-password").val();
            if (value1.length==0){
                alert('密码不能为空')
                $("#login-password")[0].focus();
                return;
            }
            <c:if test="${!empty message}">
                alert('${message}')
            </c:if>
        }

        //注册
        $(function(){
            $("#registerBtn").click(function(){
                var name = $("#register-username").val().length;
                var password = $("#register-password").val().length;
                if (name == 0){
                    alert("用户名不能为空!");
                    return;
                }
                if (password == 0){
                    alert("密码不能为空!")
                    return;
                }
                $(this).prop("type","submit");
                <c:if test="${!empty message}">
                    alert('${message}')
                </c:if>
            });
        });

    </script>
    <style type="text/css">
        html{width: 100%; height: 100%;}
        body{
            background-repeat: no-repeat;
            background-position: center center #2D0F0F;
            background-color: #00BDDC;
            background-image: url(/images/snow.jpg);
            background-size: 100% 100%;
        }
        .snow-container { position: fixed; top: 0; left: 0; width: 100%; height: 100%; pointer-events: none; z-index: 100001; }
    </style>
</head>
<body>
<!-- 雪花背景 -->
<div class="snow-container"></div>
<!-- 登录控件 -->
<div id="login">
    <input id="tab-1" type="radio" name="tab" class="sign-in hidden" checked />
    <input id="tab-2" type="radio" name="tab" class="sign-up hidden" />
    <input id="tab-3" type="radio" name="tab" class="sign-out hidden" />
    <div class="wrapper">
        <!-- 登录页面 -->
        <div class="login sign-in-htm">
            <form action="/fore/fore_login" method="post" class="container offset1 loginform">
                <!-- 猫头鹰控件 -->
                <div id="owl-login" class="login-owl">
                    <div class="hand"></div>
                    <div class="hand hand-r"></div>
                    <div class="arms">
                        <div class="arm"></div>
                        <div class="arm arm-r"></div>
                    </div>
                </div>

                <div class="pad input-container">
                    <section class="content">
							<span class="input input--hideo">
								<input class="input__field input__field--hideo" type="text" id="login-username"
                                       autocomplete="off" placeholder="请输入用户名" tabindex="1" name="name" />
								<label class="input__label input__label--hideo" for="login-username">
									<i class="fa fa-fw fa-user icon icon--hideo"></i>
									<span class="input__label-content input__label-content--hideo"></span>
								</label>
							</span>
                        <span class="input input--hideo">
								<input class="input__field input__field--hideo" type="password" id="login-password" name="password" placeholder="请输入密码" tabindex="2" maxlength="15"/>
								<label class="input__label input__label--hideo" for="login-password">
									<i class="fa fa-fw fa-lock icon icon--hideo"></i>
									<span class="input__label-content input__label-content--hideo"></span>
								</label>
							</span>
                    </section>
                </div>
                <div class="form-actions">
                    <a tabindex="5" class="btn btn-link text-muted" onClick="goto_register()">注册</a>
                    <input class="btn btn-primary" type="submit" tabindex="3" onClick="login()" value="登录"
                           style="color:white;"/>
                </div>
            </form>
        </div>

        <!-- 注册页面 -->
        <div class="login sign-up-htm">
            <form action="register" class="container offset1 loginform" id="registerForm">
                <!-- 猫头鹰控件 -->
                <div id="owl-login" class="register-owl">
                    <div class="hand"></div>
                    <div class="hand hand-r"></div>
                    <div class="arms">
                        <div class="arm"></div>
                        <div class="arm arm-r"></div>
                    </div>
                </div>
                    <div class="pad input-container">
                        <section class="content">
							<span class="input input--hideo">
								<input class="input__field input__field--hideo" type="text" id="register-username" name="name"
                                       autocomplete="off" placeholder="请输入用户名" maxlength="15"/>
								<label class="input__label input__label--hideo" for="register-username">
									<i class="fa fa-fw fa-user icon icon--hideo"></i>
									<span class="input__label-content input__label-content--hideo"></span>
								</label>
							</span>
                            <span class="input input--hideo">
								<input class="input__field input__field--hideo" type="password" id="register-password" name="password" placeholder="请输入密码" maxlength="15"/>
								<label class="input__label input__label--hideo" for="register-password">
									<i class="fa fa-fw fa-lock icon icon--hideo"></i>
									<span class="input__label-content input__label-content--hideo"></span>
								</label>
							</span>
                            <span class="input input--hideo">
								<input class="input__field input__field--hideo" type="password" id="register-repassword" placeholder="请确认密码" maxlength="15"/>
								<label class="input__label input__label--hideo" for="register-repassword">
									<i class="fa fa-fw fa-lock icon icon--hideo"></i>
									<span class="input__label-content input__label-content--hideo"></span>
								</label>
							</span>
                            <span class="input input--hideo">
                            <input class="input__field input__field--hideo" type="text" placeholder="请输入性别" name="sex" maxlength="15"/>
                            <label class="input__label input__label--hideo">
									<i class="fa fa-fw fa-lock icon icon--hideo"></i>
							</label>
                        </span>
                            <span class="input input--hideo">
                            <input class="input__field input__field--hideo" type="text"  name="telephone" placeholder="请输入电话" maxlength="15"/>
                            <label class="input__label input__label--hideo">
									<i class="fa fa-fw fa-lock icon icon--hideo"></i>
							</label>
                        </span>
                            <span class="input input--hideo">
                            <input class="input__field input__field--hideo" type="text" name="address" placeholder="请输入地址" maxlength="15"/>
                            <label class="input__label input__label--hideo">
									<i class="fa fa-fw fa-lock icon icon--hideo"></i>
							</label>
                        </span>
                            <span class="input input--hideo">
                            <input class="input__field input__field--hideo" type="text"  name="email" placeholder="请输入邮箱" maxlength="25"/>
                            <label class="input__label input__label--hideo">
									<i class="fa fa-fw fa-lock icon icon--hideo"></i>
							</label>
                        </span>
                        </section>
                    </div>
                    <div class="form-actions">
                        <a class="btn pull-left btn-link text-muted" onClick="goto_login()">返回登录</a>
                        <input class="btn btn-primary" type="button" id="registerBtn" value="注册"
                               style="color:white;"/>
                    </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>