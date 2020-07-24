<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tfcord.model.*" %>
<%@ page import="com.mbrpf.model.*" %>

<% 
	String tfno = null;
	try{
		tfno = (String) session.getAttribute("tfno");//原本是從request中取得，後來因為用重導的方式，所以將Attribute存在session，這邊才會從session取得
	}catch(Exception e){
		System.out.print("還沒兌換");
	}	
	
	TfcordService tfcordSvc = new TfcordService();
	TfcordVO tfcordVO = tfcordSvc.getOneTfcord(tfno);
	request.setAttribute("tfcordVO", tfcordVO);
	
// 	System.out.println("listOnetf tfno=" + tfno);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/> <!--要有這條 -->
<link href="https://fonts.googleapis.com/css?family=Rubik:300,400,700|Oswald:400,700" rel="stylesheet">
<link rel="stylesheet" href="<%=request.getContextPath()%>/fonts/icomoon/style.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/model/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/model/jquery.fancybox.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/model/owl.carousel.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/model/owl.theme.default.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/model/aos.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/model/style.css">
<title>顯示單筆兌換紀錄</title>
</head>

<style>
	.orangeBtn {
		background-color: #FF8F00;
		box-shadow: 0px 5px 0px 0px #CD6509;
		outline:none;
	}

	.orangeBtn:hover {
	  	background-color: #FF983C;
	}

	.pointBtn {
		border-radius: 5px;
		padding: 5px 5px;
		font-size: 18px;
		text-decoration: none;
		margin: 20px;
		color: black;
		position: relative;
		display: inline-block;
		outline:none;
	}

	.pointBtn:active {
	  	transform: translate(0px, 5px);
	  	-webkit-transform: translate(0px, 5px);
	  	box-shadow: 0px 1px 0px 0px;
	}
	.ftTable{
		border:2px solid orange;
		text-align: center;
		width:100%;
	}
</style>

<body>
<!-- 此為被include的畫面，所以不用加nav -->
<div class="container">
	<div class="row">
		<table class="ftTable" style="background-color:#FFF3E0"><!-- listAll.jsp -->
			<thead style="border:2px solid orange">
				<tr class="tabletop" style="border:2px solid orange">
					<th scope="col">兌換編號</th>
	                <th scope="col">兌換種類</th>
	                <th scope="col">兌換金額</th>
	                <th scope="col">兌換時間</th>
	                <th scope="col">兌換狀態</th>
	                <th scope="col">取消兌換</th>
	           	</tr>
			</thead>
			<tbody style="border:2px solid orange">
				<tr>
					<td>${tfcordVO.tfno}</td>
					<td>${tfcordVO.tftype == "M" ? "兌現" : "儲值"}</td>
					<td>${tfcordVO.price}</td>
					<td><fmt:formatDate value="${tfcordVO.tftime}" pattern="yyyy-MM-dd hh:mm:ss" /></td>
					<td>${(tfcordVO.tfstatus == 0) ? "待審核" : "已通過"}</td>
					<td>
						<input type ="hidden" name="mbrno" value="${tfcordVO.mbrno}">
						<input type ="hidden" name="tfno" value="${tfcordVO.tfno}">
						<input type ="hidden" name="price" value="${tfcordVO.price}">
						<input type ="hidden" name="requestURL" value="<%=request.getServletPath()%>">									
						<input type ="hidden" name="action" value="deletetf">
						<form method="post" action="<%=request.getContextPath()%>/tfcord/TfcordServlet">
							<input type ="hidden" name="mbrno" value="${tfcordVO.mbrno}">
							<input type ="hidden" name="tfno" value="${tfcordVO.tfno}">
							<input type ="hidden" name="price" value="${tfcordVO.price}">
							<input type ="hidden" name="requestURL" value="<%=request.getServletPath()%>">									
							<input type ="hidden" name="action" value="deletetf">
							<input type="submit" value="取消兌換" ${(tfcordVO.tfstatus == 1) ? "disabled" : "" } class="pointBtn orangeBtn" style="border: none; font-size:18px; ">
						</form> 
					</td>
				</tr>
			</tbody>
		</table>
	</div>








<!-- 	<div class="row"> -->
<!-- 		兌現明細 -->
<!-- 	</div> -->
<!-- 	<div class="row"> -->
<!-- 		<div class="col-sm align-middle d-flex justify-content-center">兌換編號</div> -->
<!-- 		<div class="col-sm align-middle d-flex justify-content-center">兌換種類</div> -->
<!-- 		<div class="col-sm align-middle d-flex justify-content-center">兌換金額</div> -->
<!-- 		<div class="col-sm align-middle d-flex justify-content-center">兌換時間</div> -->
<!-- 		<div class="col-sm align-middle d-flex justify-content-center">兌換狀態</div> -->
<!-- 		<div class="col-sm align-middle d-flex justify-content-center">取消兌換</div> -->
<!-- 	</div> -->
<!-- 	<div class="row"> -->
<%-- 		<div class="col-sm align-middle d-flex justify-content-center">${tfcordVO.tfno}</div> --%>
<%-- 		<div class="col-sm align-middle d-flex justify-content-center">${tfcordVO.tftype == "M" ? "兌現" : "儲值"}</div> --%>
<%-- 		<div class="col-sm align-middle d-flex justify-content-center">${tfcordVO.price}</div> --%>
<%-- 		<div class="col-sm align-middle d-flex justify-content-center"><fmt:formatDate value="${tfcordVO.tftime}" pattern="yyyy-MM-dd hh:mm:ss" /></div> --%>
<%-- 		<div class="col-sm align-middle d-flex justify-content-center">${(tfcordVO.tfstatus == 0) ? "待審核" : "已通過"}</div> --%>
<!-- 		<div class="col-sm align-middle d-flex justify-content-center"> -->
<%-- 			<form method="post" action="<%=request.getContextPath()%>/tfcord/TfcordServlet"> --%>
<%-- 				<input type ="hidden" name="mbrno" value="${tfcordVO.mbrno}"> --%>
<%-- 				<input type ="hidden" name="tfno" value="${tfcordVO.tfno}"> --%>
<%-- 				<input type ="hidden" name="price" value="${tfcordVO.price}"> --%>
<%-- 				<input type ="hidden" name="requestURL" value="<%=request.getServletPath()%>">									 --%>
<!-- 				<input type ="hidden" name="action" value="deletetf"> -->
<%-- 				<input type="submit" value="取消兌換" ${(tfcordVO.tfstatus == 1) ? "disabled" : "" } class="pointBtn orangeBtn" style="border: none; font-size:18px; "> --%>
<!-- 			</form>  -->
<!-- 		</div> -->
<!-- 	</div> -->
	
</div>




<!-- <h3>兌現明細</h3> -->

<!-- <table> -->
<!-- 	<tr> -->
<!-- 		<th>兌換編號</th> -->
<!-- 		<th>兌換種類</th> -->
<!-- 		<th>兌換金額</th> -->
<!-- 		<th>兌換時間</th> -->
<!-- 		<th>兌換狀態</th> -->
<!-- 		<th>取消兌換</th> -->
<!-- 	</tr> -->
<!-- 	<tr> -->
<%-- 		<td>${tfcordVO.tfno}</td> --%>
<%-- 		<td>${tfcordVO.tftype == "M" ? "兌現" : "儲值"}</td> --%>
<%-- 		<td>${tfcordVO.price}</td> --%>
<%-- 		<td><fmt:formatDate value="${tfcordVO.tftime}" pattern="yyyy-MM-dd hh:mm:ss" /></td> --%>
<%-- 		<td>${(tfcordVO.tfstatus == 0) ? "待審核" : "已通過"}</td> --%>
<!-- 		<td> -->
<%-- 			<form method="post" action="<%=request.getContextPath()%>/tfcord/TfcordServlet"> --%>
<%-- 				<input type ="hidden" name="mbrno" value="${tfcordVO.mbrno}"> --%>
<%-- 				<input type ="hidden" name="tfno" value="${tfcordVO.tfno}"> --%>
<%-- 				<input type ="hidden" name="price" value="${tfcordVO.price}"> --%>
<%-- 				<input type ="hidden" name="requestURL" value="<%=request.getServletPath()%>">									 --%>
<!-- 				<input type ="hidden" name="action" value="deletetf"> -->
<%-- 				<input type="submit" value="取消兌換" ${(tfcordVO.tfstatus == 1) ? "disabled" : "" } class="pointBtn orangeBtn" style="border: none; font-size:18px; "> --%>
<!-- 			</form>  -->
<!-- 		</td> -->
<!-- 	</tr> -->
<!-- </table> -->

</body>
</html>