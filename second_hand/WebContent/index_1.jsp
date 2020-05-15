<%@ page language="java" contentType="text/html;charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="fm"%>
<jsp:useBean id="product"  class="com.myee.pojo.Product" scope="request" ></jsp:useBean>
<!DOCTYPE html>
<html lang="zh-CN">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>首页</title>
		<link href="${pageContext.request.contextPath}/statics/css/bootstrap.css" rel="stylesheet">
		<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/jquery-3.3.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/jquery.validate.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/messages_zh.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/bootstrap.js"></script>
		<script type="text/javascript">
		$(function(){
			$(".the_main_nav li").click(function(){/* 商品分类导航条辅助黑色下划线 */
				$(".the_main_nav li").css("border-bottom-color","#f8f8f8");
				$(this).css("border-bottom-color","black");
			});
			
			var a=<%=request.getAttribute("productlist")%>/* 访问首页时默认查询内容的标识 */
			var b=<%=request.getAttribute("productlist_1")%>/* 访问首页时默认查询内容的标识 */
			var c=<%=request.getAttribute("productlist_null")%>/* 未查询到商品的标识 */
			if(c==1){/* 未查询到商品 */
				
			}
			else if(a==null&&b==null){/* 访问首页时默认查询内容 */
			window.location.href="${pageContext.request.contextPath}/product/defaultMessage?selectmessage=海外代购&from=1";	
			}
			
		});
		
		function selectMessageSubmit() {/* 搜索框表单校验 */
			var a=$("#selectmessage").val();
			if(a!=null&&a!=''){
				return true;
			}else{
				return false;
			}
		}
	</script>
		<style type="text/css">	
			.the_top h1{
			margin: 0px;
			}
			#bs-example-navbar-collapse-2 a{
				padding-left: 43px;
				padding-right: 43px;
			}
			.the_main_nav li{
				border-bottom:2px solid #f8f8f8;
			}
				.toproduct{
			float:left;
			padding-bottom: 0px;
			}
			.pricespan{
			color: red;float: right;
			}
		</style>
	</head>

	<body >
	<%@ include file="/head.jsp" %>
		<div class="container-fluid">
			<!--平台品牌条-->
			<div class="row">
				<div class="navbar-brand nav-justified the_top" style="background-color:#ffd84d;width:100%;height: 250px;margin-bottom: 20px;">
					<h1 style="margin-top: 0px;padding-top: 85px;">二手交易平台</h1>
				</div>
			</div>
			<!--商品展示区-->
			<div class="row">
				<div class="col-md-10">
					<!--买卖选项-->
					<div class="row">
						<div class="col-xs-12 col-md-3 col-md-offset-3">
							<div class="thumbnail" style="padding: 0px;">
								<div class="caption">
									<h3>想买就买<small>9成新任你挑,价格优惠</small></h3>
									<p>
										<a href="${pageContext.request.contextPath}/product/defaultMessage?selectmessage=海外代购&from=1" class="btn btn-primary" role="button">了解更多</a>
									</p>
								</div>
							</div>
						</div>
						<div class="col-xs-12 col-md-3">
							<div class="thumbnail" style="padding: 0px;">
								<div class="caption">
									<h3>想卖就卖<small>专业评估,给你满意价格</small></h3>
									<p>
										<a href="${pageContext.request.contextPath}/product/productUpLoad" class="btn btn-primary" role="button">了解更多</a>
									</p>
								</div>
							</div>
						</div>
					</div>
					<!--商品分类导航条和搜索框-->
					<div class="row">
						<!--搜索框-->
						<div class="row">
									<c:if test="${nogoods!=null}">
							<div class="row">
								<div class="col-md-2 col-md-push-1">
										<span style="color: red;padding-left: 30px;">${nogoods}</span>
								</div>
							</div>
									</c:if>
							<div class="col-md-10 col-md-push-1">
								<form class="navbar-form navbar-left select_product" method="post" action="${pageContext.request.contextPath}/product/inputtextselectgoods" onsubmit="return selectMessageSubmit()">
									<div class="form-group">
									<!-- 发送一个默认from,防止空指针异常 -->
									<label for="defaultfrom" class="sr-only">defaultfrom</label>
										<input type="hidden" class="form-control" id="defaultfrom" name="from" value="1">
									<label for="selectmessage" class="sr-only">select</label>
										<input type="text" class="form-control" id="selectmessage" autocomplete="off" name="selectmessage" required="required" style="width: 930px;" placeholder="${selectmessage}">
									</div>
									<input type="submit" class="btn btn-default" value="搜索" />
								</form>
							</div>
						</div>
						<!--商品导航栏-->
						<div class="row">
							<nav class="navbar navbar-default main-body col-md-10 col-md-push-1">
								<div class="container-fluid">
									<div class="navbar-header">
										<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-2" aria-expanded="false">
								        <span class="sr-only">Toggle navigation</span>
								        <span class="icon-bar"></span>
								        <span class="icon-bar"></span>
								        <span class="icon-bar"></span>
				      				</button>
									</div>

									<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-2">
										<ul class="nav navbar-nav the_main_nav">
											<li>
												<a href="${pageContext.request.contextPath}/product/defaultMessage?selectmessage=食品&from=1">食品</a>
											</li>
											<li>
												<a href="${pageContext.request.contextPath}/product/defaultMessage?selectmessage=木制家具&from=1">木制家具</a>
											</li>

											<li>
												<a href="${pageContext.request.contextPath}/product/defaultMessage?selectmessage=电器&from=1">电器</a>
											</li>
											<li>
												<a href="${pageContext.request.contextPath}/product/defaultMessage?selectmessage=户外&from=1">户外</a>
											</li>
											<li>
												<a href="${pageContext.request.contextPath}/product/defaultMessage?selectmessage=自制&from=1">自制</a>
											</li>
											<li>
												<a href="${pageContext.request.contextPath}/product/defaultMessage?selectmessage=海外代购&from=1">海外代购</a>
											</li>
											<li>
												<a href="${pageContext.request.contextPath}/product/defaultMessage?selectmessage=数码&from=1">数码</a>
											</li>
											<li>
												<a href="${pageContext.request.contextPath}/product/defaultMessage?selectmessage=服装&from=1">服装</a>
											</li>
										</ul>
									</div>

								</div>
							</nav>
						</div>

					</div>
					<!--商品展示区-->
					<div class="row ">
						<!-- 导航栏类 -->
					<c:if test="${productlist_1!=null}">
							<c:forEach items="${productlist_1}" var="product">
							 <div class="col-sm-6 col-md-3">
								    <div class="thumbnail" style="padding-bottom: 10px;" >
								       <img src="/picture/${product.goodspic}" alt="图片">
								      <div class="caption">
								        <h3 style="margin: 5px;">${product.goods}</h3>
								        <p><small>${product.goodsdescription}</small></p>
								        <p style="float:right;margin-bottom: 0px;">${product.goodstype}</p>
								      <p>
								         <span>
								         <form class="form-inline" method="post" action="${pageContext.request.contextPath}/product/theproductmessage">
											  <div class="form-group">
											    <label for="exampleInput3" class="sr-only">text</label>
											    <input type="hidden" class="form-control" id="exampleInput3" name="ID" value="${product.ID}"/>
											  </div>
				 							 	 <input type="submit" class="btn btn-primary toproduct" value="了解更多"/>
										</form>
										<c:if test="${product.priceunit=='人民币'}">
											 <span class="pricespan">￥ ${product.price}</span>
										</c:if>
										<c:if test="${product.priceunit=='美元'}">
											 <span class="pricespan">$ ${product.price}</span>
										</c:if>
										</span>
								     </p>
								      </div>
								    </div>
 							 </div>
						</c:forEach>
					</c:if>
					
					<!-- 搜索框类 -->
					<c:if test="${productlist!=null}">
						<c:forEach items="${productlist}" var="product">
							 <div class="col-sm-6 col-md-3">
								    <div class="thumbnail" style="padding-bottom: 10px;" >
								       <img src="/picture/${product.goodspic}" alt="图片">
								      <div class="caption">
								        <h3 style="margin: 5px;">${product.goods}</h3>
								        <p><small>${product.goodsdescription}</small></p>
								        <p style="float:right;margin-bottom: 0px;">${product.goodstype}</p>
								      <p>
								         <span>
								           <form class="form-inline" method="post" action="${pageContext.request.contextPath}/product/theproductmessage">
											  <div class="form-group">
											    <label for="exampleInput3" class="sr-only">text</label>
											    <input type="hidden" class="form-control" id="exampleInput3" name="ID" value="${product.ID}"/>
											  </div>
				 							 	 <input type="submit" class="btn btn-primary toproduct" value="了解更多"/>
										</form>
										<c:if test="${product.priceunit=='人民币'}">
											 <span class="pricespan">￥ ${product.price}</span>
										</c:if>
										<c:if test="${product.priceunit=='美元'}">
											 <span class="pricespan">$ ${product.price}</span>
										</c:if>
										</span>
								     </p>
								      </div>
								    </div>
 							 </div>
						</c:forEach>
					</c:if>
						 	
					</div>
					<!--分页-->
					<!-- 导航栏类 -->
					<c:if test="${pagecount_1!=null}">
					<div class="row">
						<div class="row" >
							<div class="col-md-12">
								 <nav aria-label="Page navigation">
								  <ul class="pager">
								  	<li><a onclick="this.href='${pageContext.request.contextPath}/product/defaultMessage?from=1&selectmessage=${selectmessage}'">首页</a></li>
								  	
								    <li>
								      <a onclick="this.href='${pageContext.request.contextPath}/product/defaultMessage?from=${currentpage-1}&selectmessage=${selectmessage}'" aria-label="Previous">
								        <span aria-hidden="true">上一页</span>
								      </a>
								    </li>
								    
								    <!-- 显示页数通过pageArray标识遍历 -->
								    <c:if test="${pageArray!=null}">
									    <c:forEach items="${pageArray}" var="array" varStatus="statue">
									    <!-- 每个分页按钮都发送from -->
									  	  <li><a onclick="this.href='${pageContext.request.contextPath}/product/defaultMessage?from=${statue.count}&selectmessage=${selectmessage}'">${statue.count}</a></li>
									    </c:forEach>
								    </c:if>
								    
								    <li>
								      <a onclick="this.href='${pageContext.request.contextPath}/product/defaultMessage?from=${currentpage+1}&selectmessage=${selectmessage}'" aria-label="Next">
								        <span aria-hidden="true">下一页</span>
								      </a>
								    </li>
								    
								     <li><a onclick="this.href='${pageContext.request.contextPath}/product/defaultMessage?from=${pagecount_1}&selectmessage=${selectmessage}'">尾页</a></li>
								     
								     <!-- 总共记录数用pagecount标识 -->
								      <li>总共<mark>${pagecount_1}</mark>条商品信息</li>
								  </ul>
								</nav>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
							  	<form class="form-inline" method="post" onsubmit="return skipfrom()" action="${pageContext.request.contextPath}/product/defaultMessage" style="text-align:center;">
											  <div class="form-group">
											  <!-- 发送之前搜索的信息,用于分页查询 -->
											  <label for="selectmessage_2" class="sr-only">defaultfrom</label>
										<input type="hidden" class="form-control" id="selectmessage_2" name="selectmessage" value="${selectmessage}">
											    <label for="from">跳转到第</label>
											    <input type="text" autocomplete="off" class="form-control" id="from" name="from" value="${currentpage}" required="required"/>页
											  </div>
											  <input type="submit" class="btn btn-primary" value="跳转" />
								</form>							
							</div>
						</div>
					</div><hr><hr>
					</c:if>
					
					<!-- 搜索框类 -->
					<c:if test="${pagecount!=null}">
					<div class="row">
						<div class="row" >
							<div class="col-md-12">
								 <nav aria-label="Page navigation">
								  <ul class="pager">
								  	<li><a onclick="this.href='${pageContext.request.contextPath}/product/inputtextselectgoods?from=1&selectmessage=${selectmessage}'">首页</a></li>
								  	
								    <li>
								      <a onclick="this.href='${pageContext.request.contextPath}/product/inputtextselectgoods?from=${currentpage-1}&selectmessage=${selectmessage}'" aria-label="Previous">
								        <span aria-hidden="true">上一页</span>
								      </a>
								    </li>
								    
								    <!-- 显示页数通过pageArray标识遍历 -->
								    <c:if test="${pageArray!=null}">
									    <c:forEach items="${pageArray}" var="array" varStatus="statue">
									    <!-- 每个分页按钮都发送from -->
									  	  <li><a onclick="this.href='${pageContext.request.contextPath}/product/inputtextselectgoods?from=${statue.count}&selectmessage=${selectmessage}'">${statue.count}</a></li>
									    </c:forEach>
								    </c:if>
								    
								    <li>
								      <a onclick="this.href='${pageContext.request.contextPath}/product/inputtextselectgoods?from=${currentpage+1}&selectmessage=${selectmessage}'" aria-label="Next">
								        <span aria-hidden="true">下一页</span>
								      </a>
								    </li>
								    
								     <li><a onclick="this.href='${pageContext.request.contextPath}/product/inputtextselectgoods?from=${pagecount}&selectmessage=${selectmessage}'">尾页</a></li>
								     
								     <!-- 总共记录数用pagecount标识 -->
								      <li>总共<mark>${pagecount}</mark>条商品信息</li>
								  </ul>
								</nav>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
							  	<form class="form-inline" method="post" onsubmit="return skipfrom()" action="${pageContext.request.contextPath}/product/inputtextselectgoods" style="text-align:center;">
											  <div class="form-group">
											  <!-- 发送之前搜索的信息,用于分页查询 -->
											  <label for="selectmessage_2" class="sr-only">defaultfrom</label>
										<input type="hidden" class="form-control" id="selectmessage_2" name="selectmessage" value="${selectmessage}">
											    <label for="from">跳转到第</label>
											    <input type="text" autocomplete="off" class="form-control" id="from" name="from" value="${currentpage}" required="required"/>页
											  </div>
											  <input type="submit" class="btn btn-primary" value="跳转" />
								</form>							
							</div>
						</div>
					</div><hr><hr>
					</c:if>
					
				</div>
				<div class="col-md-2" role="complementary">
				<nav class="bs-docs-sidebar hidden-print hidden-xs hidden-sm affix">
					<ul class="nav bs-docs-sidenav">
						<li>
					<a href="#the_top" class="btn btn-default" style=""><small>回到顶部</small></a>
						</li>
					</ul>
				</nav>
				</div>
			</div>
			</div>
	</body>

</html>