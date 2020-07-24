<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.shop.model.*"%>
<%@ page import="com.gmlist.model.*"%>
<%@ page import="com.game.model.*"%>
<%@ include file="/front-end/front-end-nav.jsp"%>
<%
	String shopno = request.getParameter("shopno");
	ShopService shopSvc = new ShopService();
	ShopVO shopVO = shopSvc.getOneShop(shopno);
	pageContext.setAttribute("shopVO", shopVO);
%>

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



	<jsp:include page="select_page.jsp" />
	<div class="row">
		<div class="col-sm-4"></div>
		<div class="col-sm-5">
			<h1 class="display-4" style="margin-left: auto;">${shopVO.shopname}</h1>
			<p class="lead">
				<img
					src="<%=request.getContextPath()%>/ShopShowImg?shopno=${shopVO.shopno}" />
			</p>
			<hr class="my-3">
			<p>提供座位:${shopVO.shopcy}</p>
			<p>電話:0${shopVO.shopphone}</p>
			<p>位置:${shopVO.shoploc}</p>
			<p class="lead">
			<FORM id="gmlist" METHOD="post"
				ACTION="<%=request.getContextPath()%>/front-end/gmlist/gmlist.do">
				<input type="hidden" name="shopno" value="${shopVO.shopno}">
				<input type="hidden" name="action" value="getSome_For_Display">
			</FORM>
			<button type="submit" class="btn btn-primary btn-lg" id="goGmlist">店家遊戲</button>
			<button type="submit" class="btn btn-primary btn-lg" id="goShopbk">查看揪團</button>
			</p>
		</div>
	</div>
	<FORM id="shopbk" METHOD="post"
		ACTION="<%=request.getContextPath()%>/front-end/shopbk/shopbk.do">
		<input type="hidden" name="shopno" value="${shopVO.shopno}"> <input
			type="hidden" name="action" value="getSome_For_Display">
	</FORM>




	<script>
		$(document).ready(function() {
			$("#goGmlist").click(function() {
				$("#gmlist").submit();
			})

			$("#goShopbk").click(function() {
				$("#shopbk").submit();
			})
		})
	</script>

</body>
</html>