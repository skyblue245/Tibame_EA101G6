<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mallOr.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/model/bootstrap.min.css">

<style>
div.orMain table.table{
	table-layout:fixed;
	word-break:break-all;
	text-align:center;
}
	
div.orMain table.table td{
	height:10px;
	box-sizing:border-box;
	padding:5px;
	font-size:18px;
}
	
input.dtbtn {
	margin-top:2px;
    border-radius: 2px; 
    border: none;
    padding: 0px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 18px;
    background-color:#007bff;
	color:#ffffff;
	border:solid 1px #000000;
}
	
input.dtbtn:hover {
	opacity:0.8;
}
	
.bg-gray{
	background-color:#E0E0E0;
}


div.orMain table.table tbody tr:nth-child(even) {
	background: #ffffff;
}

div.orMain table.table tbody tr:nth-child(odd) {
	background:#F0F0F0;
}



</style>

 
  
</head>
<body>



<main>

	<div class="container orMain">
		<div class="row">
		<div class="col-12">
			<table class="table table-bordered">
				<thead class="bg-white">
				<tr>
				<th >訂單編號</th>
				<th >付款狀態</th>
				<th >出貨狀態</th>
				<th >訂單狀態</th>
				<th >詳細資訊</th>
				</tr>	
				
				<tbody class="bg-white">
				<% 
					MallOrService mallOrSvc=new MallOrService();
					Set<MallOrVO> set=mallOrSvc.getAll();
					request.setAttribute("mallOrSvc",mallOrSvc);
				%>
				<%@ include file="/back-end/mallOr/page1.file" %>
				<c:forEach var="mallOr" items="${mallOrSvc.all}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
				<tr>
				<td>${mallOr.mallOrNo}</td>
				<td>${mallOr.payStatus=="1"?"已付款":"未付款"}</td>
				<td>${mallOr.boxStatus=="1"?"已出貨":mallOr.boxStatus=="2"?"已送達":"未出貨"}</td>
				<td>${mallOr.status=="1"?"已完成":mallOr.status=="2"?"已取消":"未完成"}</td>

				<td><form action="<%= request.getContextPath()%>/MallOr/MallOrServlet" method="post">
					<input type="hidden" name="mallOrNo" value="${mallOr.mallOrNo}">
					<input type="hidden" name="mbrNo" value="${mallOr.mbrNo}">
					<input type="hidden" name="action" value="selectone">
					<input type="hidden" name="active" value="${param.active}">
					<input type="hidden" name="whichPage" value="${param.whichPage}">
					<input class="dtbtn" type="submit" value="觀看">
				</form></td>
				</tr>
				</c:forEach>
				
				</tbody>
				
			</table>
			<div style="text-align:center"><%@ include file="/back-end/mallOr/page2.file" %></div>
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
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

	
	

</body>
</html>