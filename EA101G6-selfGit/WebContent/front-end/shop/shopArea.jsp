<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.shop.model.*"%>
<%@ page import="com.gmlist.model.*"%>
<%@ page import="com.game.model.*"%>
<%@ include file="/front-end/front-end-nav.jsp"%>

<head>

<meta charset="utf-8">

<title>店家</title>
<style>
table {
	margin-top: 10px;
}

tr th {
	border: 2px solid black;
	text-align: center;
}

td {
	text-align: center;
}

.icon {
	width: 20px;
	height: 20px;
}

tr:nth-child(odd) {
	background-color: #FFED97;
}

img {
	width: 300px;
	height: 200px;
}

h4 {
	margin-left: 20px;
}
</style>
</head>

<body>
<div class="container-fluid">
	<div class="row">
		<div class="col-sm-1"></div>
			<div class="col-sm-2">
			<div style="witdh: 20px;">
				<ul class="list-group list-group-item-action">
					<li class="list-group-item list-group-item-action active" onclick="location.href='<%=request.getContextPath()%>/front-end/shop/shopArea.jsp';">我的資訊</li>	
					<li class="list-group-item list-group-item-action" id="goGmlist" onclick="location.href='<%=request.getContextPath()%>/front-end/gmlist/addGmlist.jsp';">我的遊戲</li>
					<FORM id="gmlist" METHOD="post"
						ACTION="<%=request.getContextPath()%>/front-end/gmlist/gmlist.do">
						<input type="hidden" name="shopno" value="${shopAcount.shopno}">
						<input type="hidden" name="action" value="getSome_For_Display">
					</FORM>
					<li class="list-group-item list-group-item-action" id="goShopbk">我的揪團</li>
					<FORM id="shopbk" METHOD="post"
						ACTION="<%=request.getContextPath()%>/front-end/shopbk/shopbk.do">
						<input type="hidden" name="shopno" value="${shopAcount.shopno}">
						<input type="hidden" name="action" value="getSome_For_Display2">
					</FORM>
					<li class="list-group-item list-group-item-action" id="goUpdate">更改店家資料</li>
					<FORM id="getOne_For_Update" METHOD="post"
						ACTION="<%=request.getContextPath()%>/front-end/shop/shop.do">
						<input type="hidden" name="action" value="getOne_For_Update">
					</FORM>
					<li class="list-group-item list-group-item-action" onclick="location.href='<%=request.getContextPath()%>/front-end/room/shop_roomList.jsp';">我的訂位</li>
				</ul>
			</div>
		</div>
		<div class="col-sm-1"></div>
		<div class="col-sm-5">
			<h1 class="display-4" style="margin-left: auto;">${shopAcount.shopname}</h1>
			<p class="lead">
				<img
					src="<%=request.getContextPath()%>/ShopShowImg?shopno=${shopAcount.shopno}" />
			</p>
			<hr class="my-3">
			<p>提供座位:${shopAcount.shopcy}</p>
			<p>電話:0${shopAcount.shopphone}</p>
			<p>位置:${shopAcount.shoploc}</p>
		</div>
	</div>
	</div>
		<script>
			$(document).ready(function() {
				<c:if test="${not empty successMsgs}">
				swal("", "${successMsgs}", "success");
				</c:if>
				
				$("#goUpdate").click(function() {
					$("#getOne_For_Update").submit();
				})
				$("#goShopbk").click(function() {
					$("#shopbk").submit();
				})
			
			})
		</script>
		<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</body>
</html>