<%@ page language="java" contentType="text/html;charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="fm"%>
<jsp:useBean id="user"  class="com.myee.pojo.User" scope="request" ></jsp:useBean>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>注册</title>
		<link href="${pageContext.request.contextPath}/statics/css/bootstrap.css" rel="stylesheet">
		<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/jquery-3.3.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/jquery.validate.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/messages_zh.js"></script>
		<script src="${pageContext.request.contextPath}/statics/js/bootstrap.js"></script>
		<script type="text/javascript">
			$(function() {
				$(".form-horizontal").validate({
					rules: {
						username: {
							required: true,
							minlength: 3,
						},
						password: {
							required: true,
							minlength: 6
						},
						repassword: {
							required: true,
							equalTo:"[name='password']"
						},
						email: {
							required: true,
							email: true
						},phone: {
							required: true,
							minlength: 11,
							maxlength: 11,
							digits: true,
						},
					},
					message: {
						
					}
				});
			});
		</script>
		<style type="text/css">
		
		</style>
	</head>
	<body>
<%@ include file="/head.jsp" %>
  <div class="container">
  	  <div class="row">
   <h3>用户注册<span class="label label-info">信息填写</span></h3>
  </div>
  	  <div class="row">
  <form class="form-horizontal"method="post" action="${pageContext.request.contextPath}/user/register">
  	  <div class="form-group">
    <label for="username" class="col-sm-2 col-sm-offset-2 control-label">用户名</label>
    <div class="col-sm-4">
      <input type="text" class="form-control" id="username" name="username" autocomplete="off" placeholder="请输入用户名" value="${user.username}">
   <!-- 用户名已存在时显示 -->
   <c:if test="${usernameerror!=null}">
    <span style="color: red">${usernameerror}</span>
   </c:if>
    </div>
  </div>
  <div class="form-group">
    <label for="password" class="col-sm-2 col-sm-offset-2 control-label">密码</label>
    <div class="col-sm-4">
      <input type="password" class="form-control" id="password" name="password" placeholder="请输入密码">
    </div>
  </div>
  <div class="form-group">
    <label for="repassword" class="col-sm-2 col-sm-offset-2 control-label">确认密码</label>
    <div class="col-sm-4">
      <input type="password" class="form-control" id="repassword" name="repassword" placeholder="请输入密码">
    </div>
  </div>
  <div class="form-group">
    <label for="email" class="col-sm-2 col-sm-offset-2 control-label">邮箱</label>
    <div class="col-sm-4">
      <input type="email" class="form-control" id="email" name="email" autocomplete="off" placeholder="请输入邮箱" value="${user.email}">
    </div>
  </div>
   <div class="form-group">
    <label for="phonenumber" class="col-sm-2 col-sm-offset-2 control-label">手机号</label>
    <div class="col-sm-4">
      <input type="text" class="form-control" id="phone" name="phone" autocomplete="off" placeholder="请输入手机号" value="${user.phone}">
    </div>
  </div>

  <div class="form-group">
    <div class="col-sm-offset-4 col-sm-4">
      <button type="submit" class="btn btn-default">注册</button>
       <button type="reset" class="btn btn-default">重置</button>
      <!-- 注册失败显示 -->
       <c:if test="${registererror!=null}">
      	 <span style="color: red">${registererror}</span>
      </c:if>
      <!-- 注册成功显示 -->
       <c:if test="${registersuccess!=null}">
      	<span style="color: green">${registersuccess}</span>
      	点击此处<a href="${pageContext.request.contextPath}/user/Cancellation">登陆</a>
      </c:if>
    </div>
  </div>
</form>
  </div>
  </div>
	</body>

</html>