<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>點數轉換相關</title>
</head>
<body>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<a href="<%=request.getContextPath()%>/back-end/tfcord/listAllTfcord.jsp">所有點數交易紀錄</a>

<form method="post" action="<%=request.getContextPath()%>/tfcord/TfcordServlet">
	<b>請輸入會員編號(如：BM00001)</b>
	<input type="text" name="mbrno">
	<input type="hidden" name="action" value="getMbr_Tfcord">
    <input type="submit" value="送出">
</form>

<form method="post" action="<%=request.getContextPath()%>/tfcord/TfcordServlet">
	<b>請輸入兌換編號(如：20200630-0000049)</b>
	<input type="text" name="tfno">
	<input type="hidden" name="action" value="getOne_Tfcord">
    <input type="submit" value="送出">
</form>

<a href="<%=request.getContextPath()%>/back-end/tfcord/notYetTfcord.jsp">查看所有未審核之點數交易紀錄</a>

</body>
</html>