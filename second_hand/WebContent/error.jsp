<%@ page language="java" contentType="text/html;charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="fm"%>
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
	</head>
	<body>
<%@ include file="/head.jsp" %>
  		<div class="container">
			<div class="jumbotron">
			  <h1>要登录才能访问哦!</h1>
			  <p>点击此处<a class="btn btn-lg" href="${pageContext.request.contextPath}/login.jsp">登陆</a></p>
			</div>
		</div>
	</body>

</html>