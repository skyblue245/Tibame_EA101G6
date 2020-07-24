<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.tfcord.model.*" %>

<%
	String tfno = (String) request.getParameter("tfno");
	TfcordService tfcordSvc = new TfcordService();
	TfcordVO tfcordVO = tfcordSvc.getOneTfcord(tfno);
	pageContext.setAttribute("tfcordVO",tfcordVO);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/> <!--要有這條 -->
<title>兌換紀錄詳情</title>

<style>
	
	.table{
		text-align:center;
	}
	
</style>

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

<jsp:include page="/back-end/back-end_nav.jsp"></jsp:include> 
<div class="container">
	<div class="row tm-content-row emptop">
		<div class="tm-col emp">
			<div class="bg-white tm-block">
				<div class="row">
					<div class="col"><!-- 點數轉換紀錄的標題 -->
						<h2 class="tm-block-title d-inline-block">點數轉換紀錄</h2>
					</div>
					<div class="col">
						<form method="post" action="<%=request.getContextPath()%>/back-end/tfcord/listAllTfcord.jsp">
							<input type="submit" id="addEmp" value="顯示全部" >
						</form>
					</div>
				</div>
				<div class="table-responsive">
					<table class="table">
						<thead>
							<tr class="tabletop">
								<th scope="col">兌換編號</th>
								<th scope="col">會員編號</th>
								<th scope="col">兌換種類</th>
								<th scope="col">兌換金額</th>
								<th scope="col">兌換時間</th>
								<th scope="col">審核狀態</th>
								<th scope="col">審核按鈕</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td  class="align-middle">${tfcordVO.tfno}</td>
								<td  class="align-middle"><a href="<%=request.getContextPath()%>/back-end/mbrpf/listOneMbrpf.jsp?mbrpfno=${tfcordVO.mbrno}">${tfcordVO.mbrno}</a></td>
								<td  class="align-middle">${tfcordVO.tftype == "M" ? "兌現" : "儲值"}</td>
								<td  class="align-middle">${tfcordVO.price}</td>
								<td  class="align-middle"><fmt:formatDate value="${tfcordVO.tftime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td  class="align-middle">${tfcordVO.tfstatus == 1 ? "已審核" : "未審核"}</td>
								<td  class="align-middle">
									<form method="post" action="<%=request.getContextPath()%>/tfcord/TfcordServlet">
										<input type="submit" value="確認審核" ${(tfcordVO.tfstatus == 1) ? "disabled" : "" }>
										<input type ="hidden" name="mbrno" value="${tfcordVO.mbrno}">
										<input type ="hidden" name="tfno" value="${tfcordVO.tfno}">
										<input type ="hidden" name="requestURL" value="<%=request.getServletPath()%>">					
										<input type ="hidden" name="action" value="changeStatus">
									</form> 
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>



<!-- <h3>顯示某筆點數交易紀錄的詳情</h3> -->

<!-- <table> -->
<!-- 		<tr> -->
<!-- 			<th>兌換編號</th> -->
<!-- 			<th>會員編號</th> -->
<!-- 			<th>兌換種類</th> -->
<!-- 			<th>兌換金額</th> -->
<!-- 			<th>兌換時間</th> -->
<!-- 			<th>兌換狀態</th> -->
<!-- 			<th>審核按鈕</th> -->
<!-- 		</tr> -->
<!-- 		<tr>      -->
<%-- 			<td>${tfcordVO.tfno}</td> --%>
<%-- 			<td>${tfcordVO.mbrno}</td> --%>
<%-- 			<td>${(tfcordVO.tftype == "M") ? "兌現" : "儲值"}</td> --%>
<%-- 			<td>${tfcordVO.price}</td> --%>
<%-- 			<td><fmt:formatDate value="${tfcordVO.tftime}" pattern="yyyy-MM-dd hh:mm:ss" /></td> --%>
<%-- 			<td>${(tfcordVO.tfstatus == 0) ? "未處理" : "已通過"}</td> --%>
<!-- 			<td> -->
<%-- 				<form method="post" action="<%=request.getContextPath()%>/tfcord/TfcordServlet"> --%>
<%-- 					<input type="submit" value="確認審核" ${(tfcordVO.tfstatus == 1) ? "disabled" : "" }> --%>
<%-- 					<input type ="hidden" name="mbrno" value="${tfcordVO.mbrno}"> --%>
<%-- 					<input type ="hidden" name="tfno" value="${tfcordVO.tfno}"> --%>
<%-- 					<input type ="hidden" name="requestURL" value="<%=request.getServletPath()%>">					 --%>
<!-- 					<input type ="hidden" name="action" value="changeStatue"> -->
<!-- 				</form>  -->
<!-- 			</td> -->
<!-- 		</tr> -->
<!-- 	</table> -->

</body>
</html>