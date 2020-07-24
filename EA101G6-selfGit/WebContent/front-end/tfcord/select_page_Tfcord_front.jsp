<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.tfcord.model.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/> <!--要有這條 -->
<title>前台點數轉換功能</title>
</head>
<body>

<a href="<%=request.getContextPath()%>/front-end/tfcord/listOneMbrtf.jsp">個人點數交易紀錄</a><br>

<a href="<%=request.getContextPath()%>/front-end/tfcord/buyPoint.jsp">點數儲值</a><br>

<a href="<%=request.getContextPath()%>/front-end/tfcord/tfMoney.jsp">兌換現金</a><br>


</body>
</html>