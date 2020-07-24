<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ page import="com.mall.model.*"%>
<%@ page import="com.mallOr.model.*"%>
<%@ page import="com.mallOrDt.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/model/bootstrap.min.css">

<style>
.margin-center {
  margin:0px auto;
}
div.dtMain{
 margin:0px;
}

div.dtMain table.table th{
 font-size:14px;
}
div.dtMain table.table td{
 font-size:14px;
}

main{
	margin-top:20px;
}

img.rock{
	width:110px;
	height:120px;
	position:absolute;
	bottom:10px;
	left:30px;
	transform:rotate(-20deg);
}


</style>



</head>
<body>
<img class="rock" src="<%= request.getContextPath()%>/image/rocklogo.png">
<main>
	<div class="container">
		<div class="row">
			<table class="table col-6 text-center margin-center ">
				<thead>
					<tr >
						<th colspan="2" >�q��s��:${mallOrVo.mallOrNo}</th>
					</tr>
				</thead>

				<tbody>
					<tr>
						<td >�q�ʤH:</td>
						<td >������</td>
					</tr>
					<tr> 
						<td >�q�ʤ��:</td>
						<td><fmt:formatDate value="${mallOrVo.orDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					</tr>
					<tr>
						<td >�q����B:</td>
						<td>${mallOrVo.price}</td>
					</tr>
					<tr>
						<td >�B�z���A:</td>
						<td >${mallOrVo.boxStatus==1?"�w�X�f":"���X�f"}</td>
					</tr>
					<tr>
						<td >�I�ڪ��A</td>
						<td>${mallOrVo.payStatus==1?"�w�I��":"���I��"}</td>
					</tr>
					<tr>
						<td>�q�檬�p</td>
						<td>${mallOr.status=="1"?"�w����":mallOr.status=="2"?"�w����":"������"}</td>
					</tr>
					<tr>
						<td >���f�覡</td>
						<td >${mallOrVo.take}</td>
					</tr>
					<tr>
						<td >���f�a�I</td>
						<td>${mallOrVo.address}</td>
					</tr>

				</tbody>
	
			</table>
			</div>
		</div>
		
	
	<div class="container dtMain">
		<div class="row">
		<div class="col-8 margin-center">
			<table class="table text-center">
				<thead>
				<tr> <th>�ӫ~�W��</th><th>�ƶq</th><th>����</th><th>�p�p</th></tr>
				</thead>
				
				<jsp:useBean id="mallSvc" class="com.mall.model.MallService" />
				
				<tbody>
				<c:forEach var="mallOrDt" items="${mallOrDtSet}" varStatus="count">
				<tr> <td>${mallSvc.findOneByNo(mallOrDt.commNo).commName}</td><td>${mallOrDt.quantity}</td><td>${mallOrDt.price}</td><td>${mallOrDt.price*mallOrDt.quantity}</td></tr>
				</c:forEach>
				</tbody>

			</table>
			</div>
			</div>
		</div>
</main>		



		<script
			src="<%=request.getContextPath()%>/js/model/jquery-3.3.1.min.js"></script>
		<script
			src="<%=request.getContextPath()%>/js/model/popper.min.js"></script>
		<script
			src="<%=request.getContextPath()%>/js/model/bootstrap.min.js"></script>
		
			
</body>
</html>