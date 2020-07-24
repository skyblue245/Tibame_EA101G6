<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>功能列表相關</title>
</head>
<body>

<%-- 錯誤列表 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤：</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color:red">${message}</li>
			</c:forEach>
		</ul>
</c:if>

<a href="<%=request.getContextPath()%>/back-end/features/listAllFeatures.jsp">全部功能</a>

<form method="post" action="<%=request.getContextPath()%>/features/FeaturesServlet">
	<b>請輸入功能編號：</b><input type="text" name="ftno">
	<input type="hidden" name="action" value="getOne_For_Display">
	<input type="submit" value="送出">
</form>




</body>
</html>