<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.shoprpdt.model.*"%>
<%@ include file="/back-end/back-end-nav-susu.jsp" %>

<%
	ShoprpdtService shoprpdtSvc = new ShoprpdtService();
	String shoprpno = null;
	Integer status = null;
	if (request.getParameter("status") == null) {
		shoprpno = request.getParameter("shoprpno");
		status = 4;
	}

	if (request.getParameter("shoprpno") == null) {
		status = new Integer(request.getParameter("status"));
		shoprpno = "AA";
	}
	List<ShoprpdtVO> list = shoprpdtSvc.getSomeShoprpdt(shoprpno, status);
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
		<script>			
			$(document).ready(function(){
				$('#selectid').change(function(){
					$("#se").val($('#selectid option:selected').val());					
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
<jsp:include page="select_page.jsp" flush="true"></jsp:include>
	<div>
		<table class="table table-bordered">
			<tr>
				<th>檢舉店家編號</th>
				<th>房間編號</th>
				<th>會員編號</th>
				<th>店家編號</th>
				<th>檢舉內容</th>
				<th>處理狀態</th>
				<th>修改</th>
			</tr>
			<c:forEach var="shoprpdtVO" items="${list}">
				<tr>
					<td>${shoprpdtVO.shoprpno}</td>
					<td>${shoprpdtVO.rmno}</td>
					<td>${shoprpdtVO.mbrno}</td>
					<td>${shoprpdtVO.shopno}</td>
					<td>${shoprpdtVO.detail}</td>
					<td><select size="1" name="status" id='selectid'>
							<option value=0 ${(shoprpdtVO.status==0)? 'selected':''}>未處理							
							<option value=1 ${(shoprpdtVO.status==1)? 'selected':'' }>已處理				
					</select>
					<td>
						<FORM METHOD="post" ACTION="shoprpdt.do"
							style="margin-bottom: 0px;">
							<input type="submit" value="修改" class="btn btn-secondary"> 
							<input type="hidden" name="shoprpno" value="${shoprpdtVO.shoprpno}">
							<input type="hidden" name="rmno" value="${shoprpdtVO.rmno}"> 
							<input type="hidden" name="mbrno" value="${shoprpdtVO.mbrno}">
							<input type="hidden" name="shopno" value="${shoprpdtVO.shopno}">
							<input type="hidden" name="detail" value="${shoprpdtVO.detail}">
							<input type="hidden" name="status" id="se">
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