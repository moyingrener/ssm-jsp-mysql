<%@ page language="java" contentType="text/html;charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="fm"%>
<jsp:useBean id="user"  class="com.myee.pojo.User" scope="request" ></jsp:useBean>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登陆</title>
	<link href="${pageContext.request.contextPath}/statics/css/bootstrap.css" rel="stylesheet">
		<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/jquery-3.3.1.min.js"></script>
		<script src="${pageContext.request.contextPath}/statics/js/bootstrap.js"></script>
<style type="text/css">
	#theerror{
	color: red;
	}
</style>
<script type="text/javascript">
			$(function() {
				$(".form-horizontal").validate({
					rules: {
						username: {
							required: true,
						},
						password: {
							required: true,
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
   <h3>用户登陆<span class="label label-info">登陆入口</span></h3>
  </div>
  	  <div class="row">
  <fm:form cssClass="form-horizontal" modelAttribute="user" method="post" action="${pageContext.request.contextPath}/user/login">
  	  <div class="form-group">
    <label for="username" class="col-sm-2 col-sm-offset-2 control-label">用户名</label>
    <div class="col-sm-4">
    <c:if test="${theerror!=null}">
    	<span id="theerror">${theerror}</span>
    </c:if>
      <fm:input  cssClass="form-control" path="username" autocomplete="off" placeholder="请输入用户名" value="${user.username}"/>
      <fm:errors path="username"></fm:errors>
    </div>
  </div>
  <div class="form-group">
    <label for="password" class="col-sm-2 col-sm-offset-2 control-label">密码</label>
    <div class="col-sm-4">
     <fm:password  cssClass="form-control" path="password"  placeholder="请输入密码"/>
      <fm:errors path="password"></fm:errors>
    </div>
  </div>

  <div class="form-group">
    <div class="col-sm-offset-4 col-sm-4">
      <button type="submit" class="btn btn-default">登陆</button>
      	<a href="${pageContext.request.contextPath}/register.jsp">免费注册</a>
      
    </div>
  </div>
</fm:form></div></div>
	



</body>
</html>