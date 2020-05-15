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
		<script type="text/javascript">
			$(function() {
				$(".form-horizontal").validate({
					rules: {
						address: {
							required: true,
						},
						paypassword: {
							required: true,
							minlength: 6,
							maxlength: 6,
							digits: true,
						},
					},
					message: {
						
					}
				});
				
					$(".form-horizontal").submit(function(){
					var a=$("#address").val();
					if(a=="no"){
						alert("请选择地址!");
						return false;
					}else{
						var b=confirm("确定要提交订单吗?");
						if(b){
							return true;
						}else{
							return false;
						}
					}
				});
			});
			
		</script>
		<style type="text/css">
			label.error {
			  padding-left: 16px;
			  color: #EA5200;
			}
		</style>
	</head>
	<body>
<%@ include file="/head.jsp" %>
  	<div class="container">
			<div class="row">
				<h3>支付<span class="label label-info">订单填写</span></h3>
			</div>
			<div class="row">
				<form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/pay/createorder">
					<div class="form-group">
						<label for="address" class="col-sm-2 col-sm-offset-2 control-label">收货地址</label>
						<div class="col-sm-2">
						      <!-- 订单提交失败显示 -->
						       <c:if test="${fail!=null}">
						      	 <span style="color: red">${fail}</span>
						      </c:if>
						       <!-- 支付密码错误时显示 -->
						       <c:if test="${paypassworderror!=null}">
						      	 <span style="color: red">${paypassworderror}</span>
						      </c:if>
							<select class="form-control" id="address" name="address">
								<option value="no" selected="selected">---请选择---</option>
								<option value="北京市">北京市</option>
								<option value="天津市">天津市</option>
								<option value="上海市">上海市</option>
								<option value="重庆市">重庆市</option>
								<option value="山西省">山西省</option>
								<option value="辽宁省">辽宁省</option>
								<option value="吉林省">吉林省</option>
								<option value="黑龙江省">黑龙江省</option>
								<option value="江苏省">江苏省</option>
								<option value="浙江省">浙江省</option>
								<option value="安徽省">安徽省</option>
								<option value="福建省">福建省</option>
								<option value="江西省">江西省</option>
								<option value="山东省">山东省</option>
								<option value="河南省">河南省</option>
								<option value="河北省">河北省</option>
								<option value="湖南省">湖南省</option>
								<option value="湖北省">湖北省</option>
								<option value="广东省">广东省</option>
								<option value="海南省">海南省</option>
								<option value="四川省">四川省</option>
								<option value="贵州省">贵州省</option>
								<option value="云南省">云南省</option>
								<option value="陕西省">陕西省</option>
								<option value="甘肃省">甘肃省</option>
								<option value="青海省">青海省</option>
								<option value="台湾省">台湾省</option>
								<option value="内蒙古自治区">内蒙古自治区</option>
								<option value="广西壮族自治区">广西壮族自治区</option>
								<option value="西藏自治区">西藏自治区</option>
								<option value="宁夏回族自治区">宁夏回族自治区</option>
								<option value="新疆维吾尔自治区">新疆维吾尔自治区</option>
								<option value="香港特别行政区">香港特别行政区</option>
								<option value="澳门特别行政区">澳门特别行政区</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="paypassword" class="col-sm-2 col-sm-offset-2 control-label">支付密码</label>
						<div class="col-sm-4">
							<!-- 发送一个商品id -->
							<label for="productid" class="sr-only">productid</label>
							<input type="hidden" class="form-control" id="productid" name="productid" value="${productid}">
							<input type="password" class="form-control" id="paypassword" name="paypassword" placeholder="请输入支付密码">
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-offset-4 col-sm-4">
							<input type="submit" class="btn btn-default" value="提交订单"></input>
							<input type="reset" class="btn btn-default" value="重置"></input>
							<p>还未设置支付密码？点击这里<a href="#">设置</a></p>
						</div>
					</div>
				</form>
			</div>
		</div>
	</body>

</html>