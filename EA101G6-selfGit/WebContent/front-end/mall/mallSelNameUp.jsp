<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ page import="com.mall.model.*"%>
<%@ page import="com.gmTypeDt.model.*"%>
<%@ page import="com.gmType.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商城</title>

<!-- 個人CSS -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/mallCss/mallGetAllUpFornt.css">

</head>
<body>

<%@ include file="/front-end/front-end-nav.jsp" %>

	<main class="mall">
	
		<div class="seldiv">
			<a href="<%=request.getContextPath()%>/front-end/mall/mallGetAllUp.jsp"><button style="display:inline-block">回首頁</button></a>
			<form style="display:inline-block" action="<%=request.getContextPath()%>/Mall/FrontMallServlet" method="post">
				<input placeholder="請輸入商品名稱" type="text" name="selName" class="selinput">
				<input type="hidden" name="action" value="selName">
				<input type="submit" value="搜尋商品">
			</form>
		</div>



		<%
			//分別是GmTypeService  MallService
			GmTypeService gmTypeSvc = new GmTypeService();
			pageContext.setAttribute("gmTypeSvc", gmTypeSvc);
			MallService mallSvc = new MallService();
			pageContext.setAttribute("mallSvc", mallSvc);
		%>

		
		<div class="container commMain">
			<div class="row">
				

				
				<c:forEach var="mallVo" items="${selMallVoSet}">
					<div class="col-lg-3 col-6 comm">
						<a href="<%=request.getContextPath()%>/front-end/mall/mallGetOne.jsp?commNo=${mallVo.commNo}">
							<div class="imgdiv"><img src="<%= request.getContextPath()%>/Mall/MallShowImg?commNo=${mallVo.commNo}"></div>
							<p> ${mallVo.commName} </p>
							<div class="dt"><p><b>$${mallVo.price}</b></p></div>
						</a>
					</div>
				</c:forEach>
				
			</div>
		</div>

		<a href="<%=request.getContextPath()%>/front-end/buyCar/buyCar.jsp" class="shopcar"><img src="<%= request.getContextPath()%>/image/buyCar.png"></a>

	</main>
	
	
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<!-- 查詢時有錯誤啟動 -->
<c:if test="${not empty selErroMsg}">
	<script>swal({text:"${selErroMsg}" });</script>
</c:if>	


</body>
</html>