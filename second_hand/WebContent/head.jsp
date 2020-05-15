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
		<title>Hello World</title>
		<link href="${pageContext.request.contextPath}/statics/css/bootstrap.css" rel="stylesheet">
		<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/jquery-3.3.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/jquery.validate.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/messages_zh.js"></script>
		<script src="${pageContext.request.contextPath}/statics/js/bootstrap.js"></script>
		<style type="text/css">
			#bs-example-navbar-collapse-1 a{
				padding-left: 40px;
				padding-right: 40px;
			}
			#userpic{
			width: 68px;
			height: 50px;
			}
				label.error {
			  padding-left: 16px;
			  color: #EA5200;
			}
		</style>
	</head>
	<body style="background-color: rgb(240,240,240);">
 <nav class="navbar navbar-default"  id="the_top" style="margin-bottom: 0px;">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#"><span class="glyphicon glyphicon-bishop" aria-hidden="true">Brand</span></a>
    </div>

    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
      <c:if test="${userid==null}">
      	 <li><a href="${pageContext.request.contextPath}/login.jsp">登陆</a></li>
      </c:if>
   <c:if test="${userid!=null}">
    <li><a href="${pageContext.request.contextPath}/user/Cancellation">注销</a></li>
	</c:if>
        <li><a href="${pageContext.request.contextPath}/register.jsp">注册</a></li>
          <c:if test="${username!=null}">
	      <li><a href="${pageContext.request.contextPath}/personal/personalcenter" style="padding: 0px;"><img id="userpic" src="${pageContext.request.contextPath}/statics/img/用户图片.png" alt="用户图片" class="img-circle">
	         <span>${username}</span></a>
	         </li>
		</c:if>
        
      </ul>
      <ul class="nav navbar-nav navbar-right">
       <li><a href="${pageContext.request.contextPath}/index_1.jsp">首页</a></li>
       <li><a href="${pageContext.request.contextPath}/product/productUpLoad">上架商品</a></li>
        <li><a href="${pageContext.request.contextPath}/personal/personalcenter">个人中心</a></li>
      </ul>
    </div>
  </div>
</nav>
	</body>

</html>