<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.emp.model.*" %>

<%
	String empno = (String) session.getAttribute("accountBack");
	EmpService empSvc = new EmpService();
	EmpVO empVO = empSvc.getOneEmp(empno);
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">

<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/backCss/fontawesomeOld.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/backCss/bootstrapOld.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/backCss/tooplateOld.css">

<title>修改密碼</title>
</head>

<style>

	#getnewPwd{
		margin-left: 190px;
		margin-right: 20%;
	}
	
</style>

<body>
<!-- 	<h3>員工更新密碼：</h3> -->
	
	<%-- 錯誤列表 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	
	<div class="container">
		<div class="row tm-mt-big">
			<div class="col-12 mx-auto tm-login-col">
				<div class="bg-white tm-block">
					<div class="row">
						<div class="col-12 text-center">
							<i class="fas fa-3x fa-tachometer-alt tm-site-icon text-center"></i>
							<h2 class="tm-block-title mt-3">Update Password</h2>
						</div>
					</div>
					<div class="row mt-2">
						<div class="col-12">
							<form method="post" action="<%=request.getContextPath()%>/emp/EmpServlet" class="tm-login-form">
								<div class="input-group">
									<label for="empno" class="col-xl-4 col-lg-4 col-md-4 col-sm-5 col-form-label">員工編號</label>
									<input type="text" value="<%=empVO.getEmpno()%>" readonly="readonly" maxLength="10" name="empno" class="form-control validate col-xl-9 col-lg-8 col-md-8 col-sm-7" id="empno">
								</div>
								<div class="input-group">
									<label for="emppwd" class="col-xl-4 col-lg-4 col-md-4 col-sm-5 col-form-label">請輸入新密碼</label>
									<input type="password" maxLength="10" name="emppwd" class="form-control validate col-xl-9 col-lg-8 col-md-8 col-sm-7" id="emppwd" required>
								</div>
								<div class="input-group">
									<label for="chkpwd" class="col-xl-4 col-lg-4 col-md-4 col-sm-5 col-form-label">確認密碼</label>
									<input type="password" maxLength="10" name="chkpwd" class="form-control validate col-xl-9 col-lg-8 col-md-8 col-sm-7" id="chkpwd" required>
								</div>
								
								<input type="hidden" name="empno" value="${empVO.empno}">
								
								<div class="input-group mt-3">
									<input type="hidden" name="action" value="updatePwd" >
									<button type="submit" id="getnewPwd" class="btn btn-primary d-inline-block ">送出修改</button>
									<a href="<%=request.getContextPath()%>/back-end/index.jsp" style="margin-top: 3%; font-size: 19px;">回首頁</a>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	
<%-- 	<form method="post" action="<%= request.getContextPath()%>/emp/EmpServlet"> --%>
<!-- 		<table> -->
<!-- 			<tr> -->
<!-- 				<td>員工編號：</td> -->
<%-- 				<td><%=empVO.getEmpno()%></td> --%>
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td>新密碼：</td> -->
<!-- 				<td><input type="password" name="emppwd" maxLength="10"></td> -->
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td>確認密碼：</td> -->
<!-- 				<td><input type="password" name="chkpwd" maxLength="10"></td> -->
<!-- 			</tr> -->
<!-- 		</table> -->
<!-- 		<input type="hidden" name="action" value="updatePwd"> -->
<%-- 		<input type="hidden" name="empno" value="${empVO.empno}"> --%>
<!-- 		<input type="submit" value="送出修改"> -->
<!-- 	</form> -->
	
	

 
 
	

</body>
</html>