<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.rate.model.*"%>

<%
	RateService rateSvc = new RateService();
	List<RateVO> list = rateSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<jsp:useBean id="mbrpfSvc" scope="page" class="com.mbrpf.model.MbrpfService" />
<!DOCTYPE html>
<html>
<head>
<title>後台用評價列表</title>

<%-- <script src="<%=request.getContextPath()%>/js/model/jquery-3.3.1.min.js"></script> --%>
<script src="<%=request.getContextPath()%>/js/model/popper.min.js"></script>
<script src="<%=request.getContextPath()%>/js/model/bootstrap.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/model/bootstrap.min.css">
</head>
<body>
<%@ include file="/back-end/back-end_nav.jsp" %>
<div id="listAll">
<table class="table table-striped">
	<tr>
		<th style="width:10%">評價編號</th>
		<th style="width:8%">房間編號</th>
		<th style="width:10%">評價人</th>
		<th style="width:10%">被評價人</th>
		<th style="width:15%">備註</th>
		<th style="width:6%">分數</th>
		<th style="width:15%">評價時間</th>
		<th></th>
	</tr>
	<c:forEach var="rateVO" items="${list}">
	<tr>
		<td>${rateVO.rateno}</td>
		<td>${rateVO.rmno}</td>
		<td>${mbrpfSvc.getOneMbrpf(rateVO.ratingmbrno).mbrname}[${rateVO.ratingmbrno}]</td>
		<td>${mbrpfSvc.getOneMbrpf(rateVO.ratedmbrno).mbrname}[${rateVO.ratedmbrno}]</td>
		<td>${rateVO.detail}</td>
		<td>${rateVO.score}</td>
		<td><fmt:formatDate value="${rateVO.ratetime}" pattern="yyyy-MM-dd HH:mm" /></td>
		<td>
			<form METHOD="post" ACTION="rate.do">			
				<input type="hidden" name="rateno" value="${rateVO.rateno}">
				<input type="hidden" name="action" value="delete">
				<input type="submit" value="刪除" onclick="return(confirm('確認要刪除嗎？刪除後將無法恢復'))">
			</form
		></td>
	</tr>
	</c:forEach>

</table>
</div>
</body>
<style>
#listAll{
	margin:20px auto;
	width:89%;
	background-color:white;
}
</style>
</html>