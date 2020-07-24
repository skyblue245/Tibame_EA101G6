<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.shopbk.model.*"%>

<%
	ShopbkVO shopbkVO = (ShopbkVO) request.getAttribute("shopbkVO"); //shopServlet.java (Concroller) �s�Jreq��shopVO���� (�]�A�������X��shopVO, �]�]�A��J��ƿ��~�ɪ�shopVO����)	
%>

<!doctype html>
<html lang="en">
<head>

<meta charset="utf-8">

	<title>�C���C��</title>

	<style>
table {
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
	background-color: #FFED97;
}

img {
	width: 50px;
	height: 50px;
}

h4 {
	margin-left: 20px;
}
</style>
</head>

<body>

<%@ include file="/front-end/front-end-nav.jsp" %>

<h4>
	<a href="../shop/index.jsp"><img src="images/add-icon.png" class="icon">�^����</a>
</h4>

<jsp:include page="select_page.jsp" flush="true">
	<jsp:param name="" value="" />
</jsp:include>

<table>
	<tr>
			<th>���a�s��</th>
			<th>���ѤH��</th>
			<th>�}�l�ɶ�</th>
			<th>�����ɶ�</th>
			<th>�H�p�ɭp��</th>
			<th>�]��</th>
		</tr>
	<tr>
		<td>${shopbkVO.shopno}</td>
		<td>${shopbkVO.ofdtable}</td>
		<td>${shopbkVO.shoppds}</td>
		<td>${shopbkVO.shoppde}</td>
		<td>${shopbkVO.payinfohr}</td>
		<td>${shopbkVO.payinfoday}</td>
	</tr>
</table>

</body>
</html>