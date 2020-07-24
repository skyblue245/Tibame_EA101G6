<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	String accountBack = (String) request.getAttribute("accountBack");
	String password = (String) request.getAttribute("password");
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>登入</title>

<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/backCss/fontawesomeOld.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/backCss/bootstrapOld.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/backCss/tooplateOld.css">
<link rel="stylesheet" href="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.css">
<script src="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.js"></script>
</head>


<style>
	#loginback{
		margin-left: 160px;
		margin-right: 140px;
	}
	
	#forget{
		padding-top: 15px;
	}
	
	
</style>


<body>
${pwdok}
<!-- 錯誤表列 -->
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<!-- 成功表列 -->
<%-- <c:if test="${not empty successMsgs}"> --%>
	
<%-- 		<c:forEach var="message" items="${successMsgs}"> --%>
<%-- 			<h2><font style="color:blue">${message}</font></h2> --%>
<%-- 		</c:forEach> --%>
	
<%-- </c:if> --%>

<div class="container">
	<div class="row tm-mt-big">
		<div class="col-12 mx-auto tm-login-col">
			<div class="bg-white tm-block">
				<div class="row">
					<div class="col-12 text-center">
						<i class="fas fa-3x fa-tachometer-alt tm-site-icon text-center"></i>
						<h2 class="tm-block-title mt-3">Login</h2>
					</div>
				</div>
				<div class="row mt-2">
					<div class="col-12">
						<form method="post" action="<%=request.getContextPath()%>/emp/EmpServlet" class="tm-login-form">
							<div class="input-group">
								<label for="accountBack" class="col-xl-4 col-lg-4 col-md-4 col-sm-5 col-form-label">帳號</label>
								<input type="text" name="accountBack" value="<%=(accountBack == null)? "" : accountBack%>" class="form-control validate col-xl-9 col-lg-8 col-md-8 col-sm-7" id="accountBack" required>
							</div>
							<div class="input-group mt-3">
								<label for="password" class="col-xl-4 col-lg-4 col-md-4 col-sm-5 col-form-label">密碼</label>
								<input name="password" type="password" value="<%=(password == null)? "" : password%>" class="form-control validate" id="password" required>
							</div>
							<div class="input-group mt-3">
								<input type="hidden" name="action" value="tryLogin">
								<button type="submit" id="loginback" class="btn btn-primary d-inline-block ">Login</button>
								<div id="forget">
									<a href="<%=request.getContextPath()%>/forgetPwd.jsp">忘記密碼</a>
								</div>
							</div>
						</form>
						
					</div>
				</div>
			</div>
		</div>
	</div>
</div>




<%-- 	<form method="post" action="<%=request.getContextPath()%>/emp/EmpServlet"> --%>
<!-- 		<table> -->
<!-- 			<tr> -->
<!-- 				<td>員工編號：</td> -->
<%-- 				<td><input type="text" name="accountBack" value="<%=(accountBack == null)? "" : accountBack%>"></td> --%>
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td>密碼：</td> -->
<%-- 				<td><input type="password" name="password" value="<%=(password == null)? "" : password%>"></td> --%>
<!-- 			</tr> -->
<!-- 		</table> -->
<!-- 	<input type="hidden" name="action" value="tryLogin"> -->
<!-- 	<input type="submit" value="登入">  -->
<!-- 	</form> -->
	
<!-- 	<br> -->
<%-- 	<a href="<%=request.getContextPath()%>/forgetPwd.jsp">忘記密碼</a> --%>
	
	
	<script>
	if(${ok}){
		 swal({ 
			  title : "修改密碼成功" ,
			  type : "success" ,
			  buttons : false ,
			  confirmButtonColor : "#9CCC65"
			});
	 }

</script>
	
</body>
</html>