<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.features.model.*"%>

<%
	FeaturesVO feaVO = (FeaturesVO) request.getAttribute("feaVO");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="UTF-8">
<title>功能資料</title>
</head>
<body>
<h4><a href="<%=request.getContextPath()%>/back-end/features/select_page_Features.jsp">回首頁</a></h4>
<table>
	<tr>
		<th>功能編號</th>
		<th>功能名稱</th>
	</tr>
	<tr><%= feaVO.getFtno()%></tr>
	<tr><%= feaVO.getFtname()%></tr>
	
</table>


</body>
</html>