<%@ page language="java" contentType="text/html;charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="fm"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>商品信息</title>
	<link href="${pageContext.request.contextPath}/statics/css/bootstrap.css" rel="stylesheet">
		<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/jquery-3.3.1.min.js"></script>
		<script src="${pageContext.request.contextPath}/statics/js/bootstrap.js"></script>
<style type="text/css">
			.thumbnail{
				width: 500px;
			}
			.thumbnail>img{
				height: 400px;
			}
			#price {
				color: red;
				float: right;
			}
			.thumbnail a{
				width: 100%;
			}
		</style>
		<script type="text/javascript">
			$(function() {
				var a=<%=request.getAttribute("alreadysell")%>/* 作为商品已卖完的标识 */
				if(a==1){
					alert("该商品卖完了哦!");
				}
			});
		</script>
		<style type="text/css">
		
		</style>
</head>
<body>
	<%@ include file="/head.jsp" %>
	<div class="container">
	<!-- 单个商品展示页面 -->
		<c:if test="${theproductmessage!=null}">
			<div class="row">
				<div class="col-sm-6 col-md-4 col-md-offset-3">
					<div class="thumbnail">
						<img src="/picture/${theproductmessage.goodspic}" alt="商品图片">
						<div class="caption">
							<h3>${theproductmessage.goods}<small>(${theproductmessage.goodstype})</</small></h3>
							<p>${theproductmessage.goodsdescription}</p>
								<c:if test="${theproductmessage.priceunit=='人民币'}">
									<p id="price">￥ ${theproductmessage.price}</p>
								</c:if>
								<c:if test="${theproductmessage.priceunit=='美元'}">
									<p id="price">$ ${theproductmessage.price}</p>
								</c:if>
							<p>成色:&nbsp;&nbsp;${theproductmessage.acondition}</p>
							<p>发货地:&nbsp;&nbsp;${theproductmessage.location}</p>
							<p><!-- 传商品ID -->
								<a href="${pageContext.request.contextPath}/pay/payforproduct?ID=${theproductmessage.ID}" class="btn btn-danger" role="button">立即购买</a>
							</p>
						</div>
					</div>
				</div>
			</div>
		</c:if>
		</div>
</body>
</html>