<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.gmlist.model.*"%>
<%@ page import="com.shop.model.*"%>
<%@ page import="com.game.model.*"%>

<%
	GmlistVO gmlistVO = (GmlistVO) request.getAttribute("gmlistVO"); //gmlistServlet.java (Concroller) 存入req的gmlistVO物件 (包括幫忙取出的gmlistVO, 也包括輸入資料錯誤時的gmlistVO物件)
%>

<!doctype html>
<html lang="en">
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
	width: 150px;
	height: 100px;
}

h4 {
	margin-left: 20px;
}
</style>
</head>
<body>

<%@ include file="/front-end/front-end-nav.jsp" %>

<h4 style="margin-left: 20px;">
	<a href="index.jsp"><img src="images/add-icon.png" class="icon">回首頁</a>
</h4>
<%-- <jsp:include page="select_page.jsp" flush="true"> --%>
<%-- 	<jsp:param name="" value="" /> --%>
<%-- </jsp:include> --%>
<table>
		<tr>
			<th>店家名稱</th>
			<th>店家照片</th>
			<th>店家位置</th>
			<th>遊戲名稱</th>
			<th>遊戲圖片</th>
		</tr>
<jsp:useBean id="gameSvc" scope="page" class="com.game.model.GameService" />
<jsp:useBean id="shopSvc" scope="page" class="com.shop.model.ShopService" />
		<tr>
			<td>${ shopSvc.getOneShop(gmlistVO.shopno).shopname }</td>
			<td><img src="<%=request.getContextPath()%>/ShopShowImg?shopno=${gmlistVO.shopno}"></td>
			<td>${ shopSvc.getOneShop(gmlistVO.shopno).shoploc }</td>
			<td>${ gameSvc.getOneGame(gmlistVO.gmno).gmname }</td>
			<td><img style="width: 50px; height: 50px;" src="<%=request.getContextPath()%>/GameShowImg?gmno=${gmlistVO.gmno}"></td>
		</tr>
	</table>

</body>
</html>