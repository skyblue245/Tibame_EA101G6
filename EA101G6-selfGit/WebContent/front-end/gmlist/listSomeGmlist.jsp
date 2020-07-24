<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.game.model.*"%>
<%@ page import="com.gmlist.model.*"%>
<%@ page import="com.shop.model.*"%>
<%@ include file="/front-end/front-end-nav.jsp" %>
<%
	GmlistService gmlistSvc = new GmlistService();
	String gmno = request.getParameter("gmno");
	String shopno = request.getParameter("shopno");
	List<GmlistVO> list = null;
	if(shopno != null)
		list = gmlistSvc.getSomeGmlistByShop(shopno);
	if(gmno != null)
		list = gmlistSvc.getSomeGmlistByGame(gmno);
	pageContext.setAttribute("list", list);
%>

<!doctype html>
<html lang="en">
<head>
<title>Unearth &mdash; Website Template by Colorlib</title>
<meta charset="utf-8">

	<title>�C���C��</title>

	<style>
table {
	margin-top: 10px;
}

tr th {
	text-align: center;
}

td {
	text-align: center;
}

.icon {
	width: 20px;
	height: 20px;
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




<jsp:include page="select_page.jsp" flush="true">
	<jsp:param name="" value="" />
</jsp:include>

<table>
	<tr style="background-color: #FFFFFF; border: 0px; font:;">
		<td style="background-color: #FFFFFF; border: 0px;">
			<h3>�C���C��</h3>
		</td>
	</tr>
</table>
<%-- ���~��C --%>
<%-- <c:if test="${not empty errorMsgs}"> --%>
<!-- 	<font style="color: red">�Эץ��H�U���~:</font> -->
<!-- 	<ul> -->
<%-- 		<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 			<li style="color: red">${message}</li> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
<%-- </c:if> --%>
<div class="container-fluid">
		<div class="row">
			<%@ include file="page1.file"%>
	<table class="table table-sm">
		<tr>
			<th>���a�W��</th>
			<th>���a�Ӥ�</th>
			<th>���a��m</th>
			<th>�C���W��</th>
			<th>�C���Ϥ�</th>
		</tr>
		<jsp:useBean id="gameSvc" scope="page"
	class="com.game.model.GameService" />
<jsp:useBean id="shopSvc" scope="page"
	class="com.shop.model.ShopService" />
	
		<c:forEach var="gmlistVO" items="${list}" begin="<%=pageIndex%>"
					end="<%=pageIndex+rowsPerPage-1%>">
					<tr>
						<td>${shopSvc.getOneShop(gmlistVO.shopno).shopname}</td>
						<td><img src="<%=request.getContextPath()%>/ShopShowImg?shopno=${gmlistVO.shopno}"></td>			
						<td>${shopSvc.getOneShop(gmlistVO.shopno).shoploc}</td>
						<td>${gameSvc.getOneGame(gmlistVO.gmno).gmname}</td>
						<td><img style="width: 50px; height: 50px;" src="<%=request.getContextPath()%>/GameShowImg?gmno=${gmlistVO.gmno}"></td>
					</tr>
				</c:forEach>
	</table>
	<div class="d-flex justify-content-center container"
		style="margin-left: auto; margin-right: auto;">
		<div class="row">
			<div class="col-sm-12">
				<%@ include file="page2.file"%>
			</div>
</div>
</div>

<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<!-- �d�߮ɦ����~�Ұ� -->
	<c:if test="${not empty errorMsgs}">
		<script>
			swal({
				text : "${errorMsgs}"
			});
		</script>
		<%
			request.removeAttribute("errorMsgs");
		%>
	</c:if>


</body>
</html>