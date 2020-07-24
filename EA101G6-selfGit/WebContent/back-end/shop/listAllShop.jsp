<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.shop.model.*"%>
<%@ include file="/back-end/back-end-nav-susu.jsp" %>
<%
	ShopService shopSvc = new ShopService();
	List<ShopVO> list = shopSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<script>			
			$(document).ready(function(){
				$('.sta').change(function(){
					$(".status").val($(this).val());					
				});	
			});
</script>
</head>
<body>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color: red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color: red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<style>
table {
	width:100%;
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
	background-color: white;
}
tr:nth-child(even) {
	background-color: gray;
}

img {
	width: 300px;
	height: 200px;
}

h4 {
	margin-left: 20px;
}
</style>
<jsp:include page="select_page.jsp" flush="true"/>
<div>
	<table class="table table-bordered">
		<tr>
			<th>店家編號</th>
			<th>店家名稱</th>
			<th>位置</th>
			<th>場地</th>
			<th>電話</th>
			<th>店家圖片</th>
			<th>店家狀態</th>
			<th>修改</th>
		</tr>
		<c:forEach var="shopVO" items="${list}">
			<tr>
				<td>${shopVO.shopno}</td>
				<td>${shopVO.shopname}</td>
				<td>${shopVO.shoploc}</td>
				<td>${shopVO.shopcy}</td>
				<td>0${shopVO.shopphone}</td>
				<td><img src="<%=request.getContextPath()%>/ShopShowImg?shopno=${shopVO.shopno}"></td>
				<td><select size="1" name="status" class="sta">					 
							<option value=0 ${(shopVO.status == 0)? 'selected':'' }>未審核
							<option value=1 ${(shopVO.status == 1)? 'selected':'' }>審核通過
							<option value=2 ${(shopVO.status == 2)? 'selected':'' }>停權
					</select></td>
				<td>
					<FORM METHOD="post" ACTION="<%= request.getContextPath()%>/back-end/shop/shop.do" enctype="multipart/form-data">
						<input type="submit" value="修改" class="btn btn-secondary"> 
						<input type="hidden" name="shopno" value="${shopVO.shopno}"> 
						<input type="hidden" name="status" value="${shopVO.status}" class="status">	
						<input type="hidden" name="URL" value="<%=request.getServletPath()%>">
						<input type="hidden" name="action" value="update_back">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>