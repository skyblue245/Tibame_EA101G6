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
<title>��O�Ωж��C��</title>

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
		<th style="width:8%">�ж��s��</th>
		<th style="width:8%">�C�����a</th>
		<th style="width:13%">�إ߮ɶ�</th>
		<th style="width:13%">�ЦW</th>
		<th style="width:7%">�ХD</th>
		<th style="width:7%">�C���ɶ�</th>
		<th style="width:10%">�C���C��</th>
		<th style="width:10%">�Ƶ�</th>
		<th style="width:10%">�ж����A</th>
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
    				���ݦ����[�J
   		 		</c:when>
			    <c:when test="${rminfoVO.status eq 1}">
			    	�H�Ƥw�F�i�q��U��
			    </c:when>
			    <c:when test="${rminfoVO.status eq 2}">
			    	�H�Ƥw��
			    </c:when>
			    <c:when test="${rminfoVO.status eq 3}">
			    	�w�q��
			    </c:when>
			    <c:when test="${rminfoVO.status eq 4}">
			    	��������
			    </c:when>
			    <c:otherwise>
			    	�w����
			    </c:otherwise>
			</c:choose>
		</td>
		<td>
			
			<form METHOD="post" ACTION="rminfo.do">
				<input type="hidden" name="status" value="4">
				<input type="hidden" name="report" value="${rminfoVO.report}">
				<input type="hidden" name="rmno" value="${rminfoVO.rmno}">
				<input type="hidden" name="action" value="update">
				<input class="floatButton" type="submit" value="�R��" onclick="return(confirm('�T�{�n�R���ܡH�R����N�L�k��_'))">
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