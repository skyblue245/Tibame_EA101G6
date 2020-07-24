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

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color: red">�Эץ��H�U���~:</font>
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
			<th>���a�s��</th>
			<th>���a�W��</th>
			<th>��m</th>
			<th>���a</th>
			<th>�q��</th>
			<th>���a�Ϥ�</th>
			<th>���a���A</th>
			<th>�ק�</th>
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
							<option value=0 ${(shopVO.status == 0)? 'selected':'' }>���f��
							<option value=1 ${(shopVO.status == 1)? 'selected':'' }>�f�ֳq�L
							<option value=2 ${(shopVO.status == 2)? 'selected':'' }>���v
					</select></td>
				<td>
					<FORM METHOD="post" ACTION="<%= request.getContextPath()%>/back-end/shop/shop.do" enctype="multipart/form-data">
						<input type="submit" value="�ק�" class="btn btn-secondary"> 
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