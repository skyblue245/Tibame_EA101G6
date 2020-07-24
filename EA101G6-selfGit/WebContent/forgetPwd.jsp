<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	String mail = (String) request.getAttribute("mail");
	String empno = (String) request.getAttribute("empno");
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

<title>忘記密碼</title>
</head>

<style>

	#getnewPwd{
		margin-left: 190px;
	}
	
</style>

<body>
<!-- 錯誤表列 -->
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
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
						<h2 class="tm-block-title mt-3">Forget</h2>
					</div>
				</div>
				<div class="row mt-2">
					<div class="col-12">
						<form method="post" action="<%=request.getContextPath()%>/emp/EmpServlet" class="tm-login-form">
							<div class="input-group">
								<label for="empno" class="col-xl-4 col-lg-4 col-md-4 col-sm-5 col-form-label">請輸入員工編號</label>
								<input type="text" name="empno" value="<%= (empno == null)? "" : empno %>" class="form-control validate col-xl-9 col-lg-8 col-md-8 col-sm-7" id="empno" required>
							</div>
							<div class="input-group mt-3">
								<label for="mail" class="col-xl-4 col-lg-4 col-md-4 col-sm-5 col-form-label">請輸入信箱</label>
								<input type="text" name="mail" value="<%= (mail == null)? "" : mail %>" class="form-control validate" id="mail" required>
							</div>
							<div class="input-group mt-3">
								<input type="hidden" name="action" value="forget" >
								<button type="submit" id="getnewPwd" class="btn btn-primary d-inline-block ">送出</button>
								<a href="<%=request.getContextPath()%>/loginBack.jsp" style="margin-top: 3%; font-size: 19px; margin-left: 28%;">回登入</a>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>




<%-- <form method="post" action="<%=request.getContextPath()%>/emp/EmpServlet"> --%>
<!-- 	<table> -->
<!-- 		<tr> -->
<!-- 			<td>請輸入員工編號：</td> -->
<%-- 			<td><input type="text" name="empno" value="<%= (empno == null)? "" : empno %>"></td> --%>
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td>請輸入信箱：</td> -->
<%-- 			<td><input type="text" name="mail" value="<%= (mail == null)? "" : mail %>"></td> --%>
<!-- 		</tr> -->
<!-- 	</table> -->
<!-- 	<input type="hidden" name="action" value="forget" /> -->
<!-- 	<input type="submit" value="送出"> -->
<!-- </form> -->


</body>
</html>