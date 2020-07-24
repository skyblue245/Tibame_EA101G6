<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.shop.model.*"%>

<%
	ShopVO shopVO = (ShopVO) request.getAttribute("shopvo"); //shopServlet.java (Concroller) �s�Jreq��shopVO���� (�]�A�������X��shopVO, �]�]�A��J��ƿ��~�ɪ�shopVO����)
%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<style>
table {
	margin-top: 10px;
}

tr th {
	border: 2px solid black;
	text-align: center;
}

td {
	border: 2px solid black;
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
	width: 300px;
	height: 200px;
}
</style>
	<FORM METHOD="post" ACTION="shop.do" name="form1"
		enctype="multipart/form-data">
		<table>
			<tr>
				<td>���a�s��:</td>
				<td><input type="hidden" id="myFile" name="shopno" value="${shopVO.shopno}"><%=shopVO.getShopno()%></td>
			</tr>
			
			<input type="hidden" name="shopact" size="45"
					value="<%=shopVO.getShopact()%>" />
					
			<input type="hidden" name="shoppw" size="45"
					value="<%=shopVO.getShoppw()%>" />

			<tr>
				<td>���a�W��:</td>
				<td><input type="hidden" id="myFile" name="shopname" value="${shopVO.shopname}"><%=shopVO.getShopname()%></td>
			</tr>
			<tr>
				<td>��m:</td>
				<td><input type="hidden" id="myFile" name="shoploc" value="${shopVO.shoploc}"><%=shopVO.getShoploc()%></td>
			</tr>
			<tr>
				<td>���a:</td>
				<td><input type="hidden" id="myFile" name="shopcy" value="${shopVO.shopcy}"><%=shopVO.getShopcy()%></td>
			</tr>
			<tr>
				<td>�q��:</td>
				<td><input type="hidden" id="myFile" name="shopphone" value="${shopVO.shopphone}"><%=shopVO.getShopphone()%></td>
			</tr>
			<tr>
				<td>���a�Ϥ�:</td>
				<td><input type="hidden" id="myFile" name="shopimg">
					<div type="hidden" name="shopimg" value="${shopVO.shopimg}">
						<img src="<%=request.getContextPath()%>/ShopShowImg?shopno=${shopVO.shopno}" />
					</div>
				</td>
			</tr>
			<jsp:useBean id="shopSvc" scope="page" class="com.shop.model.ShopService" />
			<tr>
				<td>���a���A:</td>
				<td>
					<select size="1" name="status">					 
							<option value=0 ${(shopVO.status == 0)? 'selected':'' }>���f��
							<option value=1 ${(shopVO.status == 1)? 'selected':'' }>�f�ֳq�L
							<option value=2 ${(shopVO.status == 2)? 'selected':'' }>���v
					</select>
				</td>
			</tr>


		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="shopno" value="<%=shopVO.getShopno()%>">
		<input type="submit" value="�e�X�ק�">
	</FORM>
</body>
</html>