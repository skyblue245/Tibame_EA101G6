<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String mail = (String) request.getAttribute("mail");
	String mbract = (String) request.getAttribute("mbract");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>UnearthLogin</title>

  <link rel="stylesheet" href="<%= request.getContextPath()%>/css/style_login.css">


</head>

<body>

<%@ include file="/front-end/front-end-nav.jsp"%>


<main>
<form class="login-form " action="<%=request.getContextPath()%>/mbrpf/mbrpf.do" method="post">
<div class="login-form ">
     <h1><img src="<%=request.getContextPath()%>/images/rocket.gif" width="250" height="200" border="0"></h1>
     <div class="form-group ">
       <input type="email" class="form-control" name="mail" placeholder="�п�J�q�l�H�c " id="UserName">
       <i class="fa fa-user"></i>
     </div>
     <div class="form-group log-status">
       <input type="text" class="form-control" name="mbract" placeholder="�п�J�b��" id="Passwod">
       <i class="fa fa-lock"></i>
     </div> 
     <input type="hidden" name="action" value="forget">
     <div style="text-align:center;"><input type="submit" value="�e�X"></div>
   </div>
   </form>
  </main>
  
 <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>  
   
<!-- partial -->
  <script  src="<%= request.getContextPath()%>/js/script_login.js"></script>
	<script>
	<c:if test="${not empty errorMsgs}">
		let erroStr="";
		<c:forEach var="str" items="${errorMsgs}">
			erroStr+="${str}"+"\n";
		</c:forEach>
		swal({text:erroStr });
	</c:if>
	</script>
  


</body>
</html>