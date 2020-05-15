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
		<title>个人中心</title>
		<link href="${pageContext.request.contextPath}/statics/css/bootstrap.css" rel="stylesheet">
		<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/jquery-3.3.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/jquery.validate.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/messages_zh.js"></script>
		<script src="${pageContext.request.contextPath}/statics/js/bootstrap.js"></script>
		<script type="text/javascript">
			$(function() {
				$(".form-horizontal").validate({
					rules: {
						email: {
							required: true,
							email: true
						},
						phone: {
							required: true,
							minlength: 11,
							maxlength: 11,
							digits: true
						},
						oldpassword: {
							required: true,
							minlength: 6
						},
						newpassword: {
							required: true,
							minlength: 6
						},
						repassword: {
							required: true,
							equalTo:"[name='newpassword']"
						},
							address: {
							required: true,
							minlength: 3
						},
						oldpaypassword: {
							required: true,
							minlength: 6,
							maxlength: 6,
						},
						newpaypassword: {
							required: true,
							minlength: 6,
							maxlength: 6,
						},
						repaypassword: {
							required: true,
							equalTo:"[name='newpaypassword']"
						},
						
					},
					message: {
						
					}
				});
				
				var a=<%=request.getAttribute("deletemessage")%>/* 后台返回删除是否成功的响应信息 */
				if(a==0){
					alert("商品下架失败!");
				}else if(a==1){
					alert("商品下架成功!");
				}else if(a==2){
					alert("无法下架已出售的商品!");
				}
			});
			 function lowershelf() {
				 var b=confirm("确定要下架该商品吗?");
				 if(b){
						return true;
					}else{
						return false;
					}
			}
		</script>
		<style type="text/css">
			ul li a small{
			color: #000000;
			}
			.table > tbody > tr > td{
			vertical-align:middle;
			}
			.table > tbody > tr > td>img{
			width:200px;
			height: 150px;
			}
		</style>
	</head>
	<body>
	<%@ include file="/head.jsp" %>
	<div class="container">
			<div class="row">
				<div class="col-md-10">
				
					<!-- 我的信息 -->
					<c:if test="${user!=null}">
						<div class="row">
							<h1>我的信息</h1><br />
							<h4>会员名:<span>&nbsp;&nbsp;${user.username}</span></h4><br />
							<h4>邮箱:<span>&nbsp;&nbsp;${user.email}</span></h4><br />
							<h4>手机号:<span>&nbsp;&nbsp;${user.phone}</span></h4><br />
							<h4>注册时间:<span>&nbsp;&nbsp;${user.registertime}</span></h4><br />
							<h4>信用:<span style="color: green;">&nbsp;&nbsp;${user.credit}</span></h4><br />
							<h4>成交量:<span>&nbsp;&nbsp;${user.turnover}单</span></h4><br />
							<h4>投诉量:<span>&nbsp;&nbsp;${user.complaint}单</span></h4><br />
						</div>
 					</c:if>
 					
 					<!--修改信息-->
 					<!--修改除密码外的信息-->
 					<c:if test="${modifyuser_partial!=null}">
 						 <div class="row">
				  <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/personal/modifypartialmessage">
				   <div class="form-group">
				    <label for="email" class="col-sm-2 col-sm-offset-2 control-label">邮箱</label>
				    <div class="col-sm-4">
				      <input type="email" class="form-control" autocomplete="off" id="email" name="email" value="${modifyuser_partial.email}">
				    </div>
				  </div>
				   <div class="form-group">
				    <label for="phone" class="col-sm-2 col-sm-offset-2 control-label">手机号</label>
				    <div class="col-sm-4">
				      <input type="text" class="form-control" autocomplete="off" id="phone" name="phone" value="${modifyuser_partial.phone}">
				    </div>
				  </div>
				   <div class="form-group">
				    <label for="address" class="col-sm-2 col-sm-offset-2 control-label">默认收货地址</label>
				    <div class="col-sm-4">
				      <input type="text" class="form-control" autocomplete="off" id="address" name="address" value="${modifyuser_partial.address}">
				    </div>
				  </div>
				
				  <div class="form-group">
				    <div class="col-sm-offset-4 col-sm-4">
				      <input type="submit" class="btn btn-default" value="确认修改" />
				      <a href="${pageContext.request.contextPath}/personal/modifypasswordpage" class="btn btn-default">修改密码</a>
				      <c:if test="${success!=null}">
				      	<span style="color: green">${success}</span>
				      </c:if>
				       <c:if test="${error_2!=null}">
				      	<span style="color: red">${error_2}</span>
				      </c:if>
				    </div>
				  </div>
				</form>
  		</div>	
 					</c:if>
 						 
 					<!--修改密码-->
 					<c:if test="${modifyuser_password!=null}">
 						 <div class="row">
						  <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/personal/modifypassword">
						    <div class="form-group">
						    <label for="oldpassword" class="col-sm-2 col-sm-offset-2 control-label">原密码</label>
						    <div class="col-sm-4">
						      <input type="password" class="form-control" id="oldpassword" name="oldpassword" placeholder="请输入原密码">
						      <c:if test="${error_1!=null}">
				      	<span style="color: red">${error_1}</span>
				      </c:if>
						    </div>
						  </div>
						  <div class="form-group">
						    <label for="newpassword" class="col-sm-2 col-sm-offset-2 control-label">新密码</label>
						    <div class="col-sm-4">
						      <input type="password" class="form-control" id="newpassword" name="newpassword" placeholder="请输入新密码">
						    </div>
						  </div>
						  <div class="form-group">
						    <label for="repassword" class="col-sm-2 col-sm-offset-2 control-label">确认密码</label>
						    <div class="col-sm-4">
						      <input type="password" class="form-control" id="repassword" name="repassword" placeholder="请再次输入新密码">
						    </div>
						  </div>
						  <div class="form-group">
						    <div class="col-sm-offset-4 col-sm-4">
						      <input type="submit" class="btn btn-default" value="确认修改" />
						      <a href="${pageContext.request.contextPath}/personal/modifypaypasswordpage" class="btn btn-default">修改支付密码</a>
						         <c:if test="${error_2!=null}"><!-- 修改失败是显示 -->
				      	<span style="color: red">${error_2}</span>
				      </c:if>
				           <c:if test="${success!=null}"><!-- 修改成功时显示 -->
				      	<span style="color: green">${success}</span>
				      </c:if>
						    </div>
						  </div>
						</form>
						  </div>
 					</c:if>
 					
 					<!--修改支付密码-->
 					<c:if test="${modifyuser_paypassword!=null}">
 							<div class="row">
				  <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/personal/modifypaypassword">
				    <c:if test="${noexists==null}"><!-- 判断是否已经设置过支付密码 -->
				    	<div class="form-group">
				    <label for="oldpaypassword" class="col-sm-2 col-sm-offset-2 control-label">原支付密码</label>
				    <div class="col-sm-4">
				      <input type="password" class="form-control" id="oldpaypassword" name="oldpaypassword" placeholder="请输入原支付密码" />
				   	<c:if test="${error_1!=null}"><!-- 原支付密码错误时显示 -->
				      	<span style="color: red">${error_1}</span>
				      </c:if>
				    </div>
				  </div>
				    </c:if>
				  <div class="form-group">
				    <label for="newpaypassword" class="col-sm-2 col-sm-offset-2 control-label">新支付密码</label>
				    <div class="col-sm-4">
				      <input type="password" class="form-control" id="newpaypassword" name="newpaypassword" placeholder="请输入新支付密码" />
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="repaypassword" class="col-sm-2 col-sm-offset-2 control-label">确认新支付密码</label>
				    <div class="col-sm-4">
				      <input type="password" class="form-control" id="repaypassword" name="repaypassword" placeholder="请再次输入新支付密码" />
				    </div>
				  </div>
				  <div class="form-group">
				    <div class="col-sm-offset-4 col-sm-4">
				      <input type="submit" class="btn btn-default" value="确认修改" />
				        <c:if test="${error_2!=null}"><!-- 修改失败是显示 -->
				      	<span style="color: red">${error_2}</span>
				      </c:if>
				           <c:if test="${success!=null}"><!-- 修改成功时显示 -->
				      	<span style="color: green">${success}</span>
				      </c:if>
				    </div>
				  </div>
				</form>
				  </div>
 					</c:if>
 					
 					<!--我上架的商品-->
					<c:if test="${myupproductlist!=null}">
					<div class="row">
						 <table class="table table-striped table-bordered">
							      <thead>
							        <tr>
							        <th>商品图片</th>
							          <th>商品名</th>
							          <th>商品简介</th>
							          <th>价格</th>
							          <th>成色</th>
							          <th>发货地</th>
							          <th>类型</th>
							          <th>上架时间</th>
							          <th>出售情况</th>
							        </tr>
							      </thead>
							      <tbody>
							      <c:forEach items="${myupproductlist}" var="product">
							        <tr>
							          <td><img src="/picture/${product.goodspic}" alt="图片"></td>
							          <td>${product.goods}</td>
							          <td>${product.goodsdescription}</td>
							          <td>${product.price}${product.priceunit}</td>
							          <td>${product.acondition}</td>
							          <td>${product.location}</td>
							          <td>${product.goodstype}</td>
							          <td>${product.shelftime}</td>
							          <td>${product.state}
							          <span><form class="form-inline" id="lowershelf" method="post" action="${pageContext.request.contextPath}/file/lowershelf" onsubmit="return lowershelf()">
											  <div class="form-group">
											    <label for="exampleInput3" class="sr-only">productid</label>
											    <input type="hidden" class="form-control" id="exampleInput3" name="productid" value="${product.ID}">
											  </div>
				 							 <input type="submit" class="btn btn-primary" value="下架商品" />
										</form></span></td>
							        </tr>
							        </c:forEach>
							      </tbody>
							    </table>
					</div>
							
					</c:if>
					
					<!--我的订单-->
					<c:if test="${myorderlist!=null}">
					<div class="row">
					<div class="bs-example" data-example-id="striped-table">
					    <table class="table table-striped table-bordered">
					      <thead>
					        <tr>
					          <th>商品图片</th>
					          <th>商品名</th>
					          <th>成色</th>
					          <th>发货地</th>
					          <th>下单时间</th>
					        </tr>
					      </thead>
					      <tbody>
					      <!-- 用OrderCustom当结果集 -->
					       <c:forEach items="${myorderlist}" var="order">
					       	 <tr>
					          <td style="width: 155px;"><img src="/picture/${order.goodspic}" alt="图片"></td>
					          <td>${order.goods}</td>
					          <td>${order.acondition}</td>
					          <td>${order.location}</td>
					          <td>${order.createtime}<br/><a href="${pageContext.request.contextPath}/product/theproductmessage?ID=${order.productid}" class="btn btn-primary">了解更多</a></td>
					        </tr>
					       </c:forEach>
					      </tbody>
					    </table>
					  </div>
					</div>
				</c:if>
				
						<!-- 空空如也~ -->
					<c:if test="${user==null&&modifyuser_partial==null&&modifyuser_password==null&&modifyuser_paypassword==null&&myupproductlist==null&&myorderlist==null}">
						<c:if test="${myupproductlist_1!=null&&myorderlist_1==null}">
							<div class="row">
						<div class="jumbotron" style="background-color: rgb(240,240,240);">
					  <h1 style="color:#777777;">空空如也~</h1>
					  <p><a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/product/productUpLoad">去上架商品</a></p>
					</div>
					</div>
						</c:if>
						<c:if test="${myupproductlist_1==null&&myorderlist_1!=null}">
							<div class="row">
						<div class="jumbotron" style="background-color: rgb(240,240,240);">
					  <h1 style="color:#777777;">空空如也~</h1>
					  <p><a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/index_1.jsp">去购买</a></p>
					</div>
					</div>
						</c:if>
					</c:if>
				</div>
				<div class="col-md-2" role="complementary">
						<nav class="bs-docs-sidebar hidden-print hidden-xs hidden-sm affix">
							<ul class="nav bs-docs-sidenav">
								<li>
							<a href="${pageContext.request.contextPath}/personal/personalcenter"><small>我的信息</small></a>
								</li>
									<li>
										<a href="${pageContext.request.contextPath}/personal/modifypartialmessagepage"><small>修改信息</small></a>
								</li>
									<li>
										<a href="${pageContext.request.contextPath}/product/myupproduct"><small>我上架的商品</small></a>
								</li>
									<li>
										<a href="${pageContext.request.contextPath}/product/myorderlist"><small>我的订单</small></a>
								</li>
								<li>
										<a href="#the_top"><small>回到顶部</small></a>
								</li>
							</ul>
						</nav>
					</div>
				</div>
		</div>
 	
	</body>

</html>