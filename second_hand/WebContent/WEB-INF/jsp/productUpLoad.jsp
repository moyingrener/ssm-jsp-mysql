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
		<title>商品上传</title>
		<link href="${pageContext.request.contextPath}/statics/css/bootstrap.css" rel="stylesheet">
		<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/jquery-3.3.1.min.js">
		</script>
		<script src="${pageContext.request.contextPath}/statics/js/bootstrap.js"></script>
		<!-- 价格校验 -->
		<script type="text/javascript">
		$(function() {
			$(".form-horizontal").validate({
				rules: {
					goods: {
						required: true,
						minlength: 1,
					},
					goodsdescription: {
						required: true,
						minlength: 10,
						maxlength: 50,
					},
					price: {
						required: true,
					},
					attach: {
						required: true,
					},
				},
				message: {
					
				}
			});
			$("#price").blur(function(){
				var theprice=$("#price").val();
		if(theprice!=null&&theprice!=""){
			if(/((^[1-9]\d*)|^0)(\.\d{0,2}){0,1}$/.test(theprice)){
			$("#pricespan").html("");
		}else{
			$("#pricespan").html("请输入合法的价格").css("color","red");
		}
	}
		});
		});
		function thesubmit(){
			var theprice=$("#price").val();
			if(theprice!=null&&theprice!=""){
				if(!/((^[1-9]\d*)|^0)(\.\d{0,2}){0,1}$/.test(theprice)){
				$("#pricespan").html("请输入合法的价格").css("color","red");
				return false;
			}else{
				$("#pricespan").html("");
				return true;
			}
		}else{
				return false;
			}
		}
	</script>
		<style type="text/css">	
			#bs-example-navbar-collapse-2 a{
				padding-left: 43px;
				padding-right: 43px;
			}
		</style>
	</head>

	<body>
	<%@ include file="/head.jsp" %>
	<!--商品上传表-->
  <div class="container">
  	  <div class="row">
   <h3>上架商品<span class="label label-info">商品信息</span></h3>
  </div>
  	  <div class="row">
  <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/file/fileUp" enctype="multipart/form-data" onsubmit="return thesubmit()">
  	  <div class="form-group">
    <label for="goods" class="col-sm-2 col-sm-offset-2 control-label">商品名</label>
    <div class="col-sm-4">
      <input type="text" class="form-control" autocomplete="off" id="goods" name="goods" placeholder="请输入商品名" value="${product.goods}">
    </div>
  </div>
    <div class="form-group">
    <label for="goodsdescription" class="col-sm-2 col-sm-offset-2 control-label">商品简介</label>
    <div class="col-sm-4">
      <input type="text" class="form-control" autocomplete="off" id="goodsdescription" name="goodsdescription" placeholder="请输入简介" value="${product.goodsdescription}">
    </div>
  </div>
	  <div class="form-group">
    <label for="price" class="col-sm-2 col-sm-offset-2 control-label">价格</label>
    <div class="col-sm-4">
      <input type="text" class="form-control" autocomplete="off" id="price" name="price" placeholder="请输入价格" value="${product.price}">
      <span id="pricespan"></span>
    </div>
  </div>
   <div class="form-group">
    <label for="priceunit" class="col-sm-2 col-sm-offset-2 control-label">价格单位</label>
		 <div class="col-sm-2">
	    	<select class="form-control" id="priceunit" name="priceunit">
				  <option value="人民币">人民币</option>
				  <option value="美元">美元</option>
			</select>
   		 </div>
  </div>
    <div class="form-group">
    <label for="acondition" class="col-sm-2 col-sm-offset-2 control-label">成色</label>
		 <div class="col-sm-2">
	    	<select class="form-control" id="acondition" name="acondition">
				  <option value="一成新">一成新</option>
				  <option value="二成新">二成新</option>
				  <option value="三成新">三成新</option>
				  <option value="四成新">四成新</option>
				  <option value="五成新">五成新</option>
				  <option value="六成新">六成新</option>
				  <option value="七成新">七成新</option>
				  <option value="八成新">八成新</option>
				  <option value="九成新">九成新</option>
				  <option value="全新">全新</option>
			</select>
   		 </div>
  </div>
   <div class="form-group">
    <label for="goodstype" class="col-sm-2 col-sm-offset-2 control-label">类型</label>
     <div class="col-sm-2">
	    	<select class="form-control" id="goodstype" name="goodstype">
				  <option value="食品">食品</option>
				  <option value="木制家具">木制家具</option>
				  <option value="电器">电器</option>
				  <option value="户外">户外</option>
				  <option value="自制">自制</option>
				  <option value="海外代购">海外代购</option>
				  <option value="数码">数码</option>
				  <option value="服装">服装</option>
			</select>
   		 </div>
  </div>
  <div class="form-group">
    <label for="location" class="col-sm-2 col-sm-offset-2 control-label">发货地</label>
     <div class="col-sm-2">
	    	<select class="form-control" id="location" name="location">
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
    <label for="attach" class="col-sm-2 col-sm-offset-2 control-label">图片</label>
    <div class="col-sm-4">
      <input type="file" class="form-control" id="attach" name="attach" placeholder="请输入商品类型">
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-4 col-sm-4">
      <button type="submit" class="btn btn-default">提交</button>
      <!-- 上交失败显示 -->
       <c:if test="${error!=null}">
      	 <span style="color: red">${error}</span>
      </c:if>
      <!-- 上交成功显示 -->
       <c:if test="${success!=null}">
      	<span style="color: green">${success}</span>
      </c:if>
      
    </div>
  </div>
</form>
  </div>
  </div>
	</body>

</html>