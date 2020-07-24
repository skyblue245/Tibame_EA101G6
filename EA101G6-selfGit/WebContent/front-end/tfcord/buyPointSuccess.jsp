<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	Integer point = (Integer) request.getAttribute("price");
	String tftype = (String) request.getAttribute("tftype");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/> <!--要有這條 -->
<title>購買成功頁面(沒用到)</title>
</head>
<body>
	
	<a href="<%=request.getContextPath()%>/front-end/tfcord/select_page_Tfcord_front.jsp">回前台點數轉換首頁</a>
	
	這裡到時會跳轉到會員的點數畫面，顯示的點數為原有的加上剛剛儲值的總共點數
	
	總共加值：<%= point %> 點
</body>
</html>