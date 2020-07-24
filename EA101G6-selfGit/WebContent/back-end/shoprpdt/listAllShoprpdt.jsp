<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.shoprpdt.model.*"%>
<%@ include file="/back-end/back-end-nav-susu.jsp" %>
<%
	ShoprpdtService shoprpdtSvc = new ShoprpdtService();
	List<ShoprpdtVO> list = shoprpdtSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
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
<jsp:include page="select_page.jsp" flush="true">
	<jsp:param name="" value="" />
</jsp:include>
<div>
	<table class="table table-bordered">
		<tr>
			<th>���|���a�s��</th>
			<th>�ж��s��</th>
			<th>�|���s��</th>
			<th>���a�s��</th>
			<th>���|���e</th>
			<th>�B�z���A</th>
			<th>�ק�</th>
		</tr>
		<c:forEach var="shoprpdtVO" items="${list}">
			<tr>
				<td>${shoprpdtVO.shoprpno}</td>
				<td>${shoprpdtVO.rmno}</td>
				<td>${shoprpdtVO.mbrno}</td>
				<td>${shoprpdtVO.shopno}</td>
				<td>${shoprpdtVO.detail}</td>
				<td><select size="1" class="sta">					 
							<option value=0 ${(shoprpdtVO.status==0)? 'selected':''}>���B�z
							<option value=1 ${(shoprpdtVO.status==1)? 'selected':'' }>�w�B�z
					</select>
				<td>
					<FORM METHOD="post" ACTION="shoprpdt.do"
							style="margin-bottom: 0px;">
							<input type="submit" value="�ק�" class="btn btn-secondary"> 
							<input type="hidden" name="shoprpno" value="${shoprpdtVO.shoprpno}">
							<input type="hidden" name="rmno" value="${shoprpdtVO.rmno}"> 
							<input type="hidden" name="mbrno" value="${shoprpdtVO.mbrno}">
							<input type="hidden" name="shopno" value="${shoprpdtVO.shopno}">
							<input type="hidden" name="detail" value="${shoprpdtVO.detail}">
							<input type="hidden" name="status" class="status" value="${shoprpdtVO.status}">
							<input type="hidden" name="URL" value="<%=request.getServletPath()%>">
							<input type="hidden" name="action" value="update">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>