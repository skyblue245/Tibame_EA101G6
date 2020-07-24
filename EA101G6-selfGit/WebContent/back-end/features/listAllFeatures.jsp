<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="com.features.model.*" %>

<%
	FeaturesService feaSvc = new FeaturesService();
	List<FeaturesVO> list = feaSvc.getAll();
	pageContext.setAttribute("list", list);
%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>所有功能資料</title>
</head>
<body>
<h4><a href="<%=request.getContextPath()%>/back-end/features/select_page_Features.jsp">回首頁</a></h4>

<%-- 錯誤列表 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤：</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color:red">${message}</li>
			</c:forEach>
		</ul>
</c:if>

<table>
	<tr>
		<th>功能編號</th>
		<th>功能名稱</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
<%@ include file="page1.file" %> 
	<c:forEach var="feaVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr>
			<td>${feaVO.ftno}</td>
			<td>${feaVO.ftname}</td>
			<td>
				<form method="post" action="<%=request.getContextPath()%>/features/FeaturesServlet">
					<input type="submit" value="修改">
					<input type="hidden" name="ftno" value="${feaVO.ftno}">
					<input type="hidden" name="action" value="getOne_For_Update">
				</form>
			</td>	
			<td>
				<form method="post" action="<%=request.getContextPath()%>/features/FeaturesServlet">
					<input type="submit" value="刪除">
					<input type="hidden" name="ftno" value="${feaVO.ftno}">
					<input type="hidden" name="action" value="delete">
				</form>
			</td>	
		</tr>
	</c:forEach>
	
</table>


<%@ include file="page2.file" %>
</body>
</html>