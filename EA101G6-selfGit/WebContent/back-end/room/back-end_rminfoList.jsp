<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.rminfo.model.*"%>

<%
	RminfoService rminfoSvc = new RminfoService();
	List<RminfoVO> list = rminfoSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<jsp:useBean id="mbrpfSvc" scope="page" class="com.mbrpf.model.MbrpfService" />
<jsp:useBean id="shopSvc" scope="page" class="com.shop.model.ShopService" />
<!DOCTYPE html>
<html>
<head>
<title>後臺用房間列表</title>

<%-- <script src="<%=request.getContextPath()%>/js/model/jquery-3.3.1.min.js"></script> --%>
<script src="<%=request.getContextPath()%>/js/model/popper.min.js"></script>
<script src="<%=request.getContextPath()%>/js/model/bootstrap.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/model/bootstrap.min.css">
</head>
<body>
<%@ include file="/back-end/back-end_nav.jsp"%>
<div id="listAll">
<table class="table table-striped">
	<tr>
		<th style="width:8%">房間編號</th>
		<th style="width:8%">遊玩店家</th>
		<th style="width:13%">建立時間</th>
		<th style="width:13%">房名</th>
		<th style="width:7%">房主</th>
		<th style="width:7%">遊玩時間</th>
		<th style="width:10%">遊玩遊戲</th>
		<th style="width:10%">備註</th>
		<th style="width:10%">房間狀態</th>
		<th></th>
	</tr>
	<c:forEach var="rminfoVO" items="${list}">
	<c:if test="${rminfoVO.status != 4}">
	<tr>
		<td>${rminfoVO.rmno}</td>
		<td>${shopSvc.getOneShop(rminfoVO.shopno).shopname}[${rminfoVO.shopno}]</td>
		<td><fmt:formatDate value="${rminfoVO.createtime}" pattern="yyyy-MM-dd HH:mm" /></td>
		<td>${rminfoVO.naming}</td>
		<td>${mbrpfSvc.getOneMbrpf(rminfoVO.mbrno).mbrname}[${rminfoVO.mbrno}]</td>
		<td>
			<fmt:formatDate value="${rminfoVO.starttime}" pattern="yyyy-MM-dd HH:mm" />~<fmt:formatDate value="${rminfoVO.endtime}" pattern="HH:mm" />
		</td>
		<td>${rminfoVO.game}</td>
		<td>${rminfoVO.remarks}</td>
		<td>
			<c:choose>
    			<c:when test="${rminfoVO.status eq 0}">
    				等待成員加入
   		 		</c:when>
			    <c:when test="${rminfoVO.status eq 1}">
			    	人數已達可訂位下限
			    </c:when>
			    <c:when test="${rminfoVO.status eq 2}">
			    	人數已滿
			    </c:when>
			    <c:when test="${rminfoVO.status eq 3}">
			    	已訂位
			    </c:when>
			    <c:when test="${rminfoVO.status eq 4}">
			    	取消揪團
			    </c:when>
			    <c:otherwise>
			    	已結束
			    </c:otherwise>
			</c:choose>
		</td>
		<td>
			
			<form METHOD="post" ACTION="rminfo.do">
				<input type="hidden" name="status" value="4">
				<input type="hidden" name="report" value="${rminfoVO.report}">
				<input type="hidden" name="rmno" value="${rminfoVO.rmno}">
				<input type="hidden" name="action" value="update">
				<input class="floatButton" type="submit" value="刪除" onclick="return(confirm('確認要刪除嗎？刪除後將無法恢復'))">
			</form>
					
		</td>
	</tr>
	</c:if>
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
.floatButton{
	float:left;
	margin:0px 5px;
}
</style>
</html>