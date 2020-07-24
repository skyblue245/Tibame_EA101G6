<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tfcord.model.*" %>
<%@ page import="com.mbrpf.model.*" %>


<%
	MbrpfVO mbrpfVO = (MbrpfVO) session.getAttribute("mbrpfVO");
	TfcordService tfcordSvc = new TfcordService();
	List<TfcordVO> list = tfcordSvc.getWhoAll(mbrpfVO.getMbrno());
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/> <!--要有這條 -->
<title>會員轉換紀錄</title>

<style>
	.mbrimf{
		border:1px solid orange;
	}
	
	.orangeBtn {
		background-color: #FF8F00;
		box-shadow: 0px 5px 0px 0px #CD6509;
	}

	.orangeBtn:hover {
	  	background-color: #FF983C;
	}

	.pointBtn {
		border-radius: 5px;
		padding: 5px 5px;
		font-size: 18px;
		text-decoration: none;
		color: black;
		position: relative;
		display: inline-block;
	}

	.pointBtn:active {
	  	transform: translate(0px, 5px);
	  	-webkit-transform: translate(0px, 5px);
	  	box-shadow: 0px 1px 0px 0px;
	}
	
	.PdeloranBtn {
		background-color: #FF8F00;
		box-shadow: 0px 5px 0px 0px #CD6509;
		outline:none;
		border:none;
	}

	.PdeloranBtn:hover {
	  	background-color: #FF983C;
	}
	
	.PdeloranBtn:disabled {
	  	display:none;
	}

	.PdelBtn {
		border-radius: 5px;
		padding: 5px 5px;
		font-size: 16px;
		text-decoration: none;
		color: black;
		position: relative;
		display: inline-block;
		outline:none;
		border:none;
	}

	.PdelBtn:active {
	  	transform: translate(0px, 5px);
	  	-webkit-transform: translate(0px, 5px);
	  	box-shadow: 0px 1px 0px 0px;
	}
	
	.PdelBtn:disabled {
	  	display:none;
	}
	
</style>

</head>

<body>

<jsp:include page="/front-end/front-end-nav.jsp"></jsp:include> 

<!-- <h3>此為某會員的所有點數交易紀錄(前台帳戶管理)</h3> -->
<div class="container">
	<div class="row">
		<h4><b>會員帳戶管理</b></h4>
	</div>
	<br>
	<div class="row">
		<div class="col-sm-2 d-flex justify-content-center mbrimf" style="background-color:#FFCC80; padding-top: 1%; padding-bottom: 1%;">
			會員帳號
		</div>
		<div class="col-sm-3 d-flex justify-content-start mbrimf align-items-center">
			${mbrpfVO.mbract}
		</div>
	</div>
	<div class="row">
		<div class="col-sm-2 d-flex justify-content-center mbrimf" style="background-color:#FFCC80; padding-top: 1%; padding-bottom: 1%;">
			會員可用餘額
		</div>
		<div class="col-sm-3 d-flex justify-content-start mbrimf align-items-center">
			<c:choose>
				<c:when test="${not empty mbrPoint}">
					${mbrPoint}
				</c:when>
				<c:otherwise>
					${mbrpfVO.points}
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<div class="row float-right">
		<a href="<%=request.getContextPath()%>/front-end/tfcord/buyPoint.jsp" class="pointBtn orangeBtn">前往點數購買</a>&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="<%=request.getContextPath()%>/front-end/tfcord/tfMoney.jsp" class="pointBtn orangeBtn">前往兌換現金</a>
	</div>
	<div class="row" style="clear:both">
	<div class="table-responsive table-striped">
		<table class="table" style="text-align:center">
			<thead>
				<tr class="tabletop" style="background-color:#FFF3E0;">
					<th>兌換編號</th>
					<th>兌換種類</th>
					<th>兌換金額</th>
					<th>兌換時間</th>
					<th>兌換狀態</th>
					<th>取消兌換</th>
				</tr>
			</thead>
			<br>
			<%@ include file="page1.file" %>
			<tbody>
				<c:forEach var="tfcordVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
					<tr>
						<td class="align-middle">${tfcordVO.tfno}</td>
						<td class="align-middle">${tfcordVO.tftype == "M" ? "兌現" : "儲值"}</td>
						<td class="align-middle">${tfcordVO.price}</td>
						<td class="align-middle">
							<fmt:formatDate value="${tfcordVO.tftime}" pattern="yyyy-MM-dd hh:mm:ss" />
						</td>
						<td class="align-middle">${(tfcordVO.tfstatus == 0) ? "待審核" : "已通過"}</td>
						<td class="align-middle">
							<form method="post" action="<%=request.getContextPath()%>/tfcord/TfcordServlet">
								<input type="submit" value="取消兌換" ${(tfcordVO.tfstatus == 1) ? "disabled" : "" } class="PdelBtn PdeloranBtn">
								<input type ="hidden" name="mbrno" value="${tfcordVO.mbrno}">
								<input type ="hidden" name="tfno" value="${tfcordVO.tfno}">
								<input type ="hidden" name="price" value="${tfcordVO.price}">
								<input type ="hidden" name="requestURL" value="<%=request.getServletPath()%>">					
								<input type ="hidden" name="whichPage" value="<%=whichPage%>">					
								<input type ="hidden" name="action" value="deletetf">
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<%@ include file="page2.file" %>
	</div>
	</div>
</div>

<%-- <a href="<%=request.getContextPath()%>/front-end/tfcord/select_page_Tfcord_front.jsp">回前台點數轉換首頁</a> --%>

<!-- <table> -->
<!-- 	<tr> -->
<!-- 		<th>兌換編號</th> -->
<!-- 		<th>兌換種類</th> -->
<!-- 		<th>兌換金額</th> -->
<!-- 		<th>兌換時間</th> -->
<!-- 		<th>兌換狀態</th> -->
<!-- 		<th>取消兌換</th> -->
<!-- 	</tr> -->
<%-- <%@ include file="page1.file" %> --%>
<%-- 	<c:forEach var="tfcordVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>"> --%>
<!-- 		<tr> -->
<%-- 			<td>${tfcordVO.tfno}</td> --%>
<%-- 			<td>${tfcordVO.tftype == "M" ? "兌現" : "儲值"}</td> --%>
<%-- 			<td>${tfcordVO.price}</td> --%>
		<%--<td>${tfcordVO.tftime}</td> --%>
<%-- 			<td><fmt:formatDate value="${tfcordVO.tftime}" pattern="yyyy-MM-dd hh:mm:ss" /></td> --%>
<%-- 			<td>${(tfcordVO.tfstatus == 0) ? "待審核" : "已通過"}</td> --%>
<!-- 			<td> -->
<%-- 				<form method="post" action="<%=request.getContextPath()%>/tfcord/TfcordServlet"> --%>
<%-- 					<input type="submit" value="取消兌換" ${(tfcordVO.tfstatus == 1) ? "disabled" : "" }> --%>
<%-- 					<input type ="hidden" name="mbrno" value="${tfcordVO.mbrno}"> --%>
<%-- 					<input type ="hidden" name="tfno" value="${tfcordVO.tfno}"> --%>
<%-- 					<input type ="hidden" name="price" value="${tfcordVO.price}"> --%>
<%-- 					<input type ="hidden" name="requestURL" value="<%=request.getServletPath()%>">					 --%>
<%-- 					<input type ="hidden" name="whichPage" value="<%=whichPage%>">					 --%>
<!-- 					<input type ="hidden" name="action" value="deletetf"> -->
<!-- 				</form>  -->
<!-- 			</td> -->
<!-- 		</tr> -->
<%-- 	</c:forEach> --%>
<!-- </table>	 -->
<%-- <%@ include file="page2.file" %>	 --%>

<%-- request.getServletPath() = <%=request.getServletPath()%> --%>

</body>
</html>