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
 	margin:20px auto;
}

div.dtMain table.table th{
 	font-size:16px;
}
div.dtMain table.table td{
 	font-size:14px;
}

main{
	margin-top:20px;
}

.for{
	margin:50px;
	text-align:center;
}

main div.thank{
	text-align:center;
	margin:30px 0px;
}


</style>



</head>
<body>

<%@ include file="/front-end/front-end-nav.jsp"%>

<main>

<div class="thank"><h1></h1></div>
	<div class="container">
		<div class="row">
			<table class="table col-6 text-center margin-center">
				<thead>
					<tr>
						<th colspan="2">�q��s��:${mallOrVo.mallOrNo}</th>
					</tr>
				</thead>

				<tbody>
					<tr>
						<td>�q�ʤH:</td>
						<td>${mbrName}</td>
					</tr>
					<tr>
						<td>�q�ʤ��:</td>
						<td><fmt:formatDate value="${mallOrVo.orDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					</tr>
					<tr>
						<td>�q����B:</td>
						<td>${mallOrVo.price}</td>
					</tr>
					<tr>
						<td>�B�z���A:</td>
						<td>${mallOr.boxStatus=="1"?"�w�X�f":mallOr.boxStatus=="2"?"�w�e�F":"���X�f"}</td>
					</tr>
					<tr>
						<td>�I�ڪ��A</td>
						<td>${mallOrVo.payStatus==1?"�w�I��":"���I��"}</td>
					</tr>
					<tr>
						<td>�q�檬�p</td>
						<td>${mallOr.status=="1"?"�w����":mallOr.status=="2"?"�w����":"������"}</td>
					</tr>
					<tr>
						<td>���f�覡</td>
						<td>${mallOrVo.take}</td>
					</tr>
					<tr>
						<td>���f�a�I</td>
						<td>${mallOrVo.address}</td>
					</tr>
					

					
				</tbody>
	
			</table>
			</div>
		</div>
		
		<div class="for"><a href="<%= request.getContextPath()%>/front-end/mallOr/mbrMallOr.jsp"><button class="btn btn-secondary">�e���q�歶��</button></a></div>
</main>		



		<script
			src="<%=request.getContextPath()%>/js/model/jquery-3.3.1.min.js"></script>
		<script
			src="<%=request.getContextPath()%>/js/model/popper.min.js"></script>
		<script
			src="<%=request.getContextPath()%>/js/model/bootstrap.min.js"></script>
		<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
		
		<script>
		Swal.fire({
	  		icon: 'success',
	  		title: '���±z���ʶR�I',
	  		showConfirmButton: false,
	  		timer: 1500
		})
		</script>
			
</body>
</html>