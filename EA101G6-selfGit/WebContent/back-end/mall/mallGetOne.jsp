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
<title>Document</title>
<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/mallCss/mallcss.css">
</head>
<body style="font-size:18px;">

<%@ include file="/back-end/back-end-nav-susu.jsp" %>

	<div id="commaction"  >

		<button onclick="javascript:location.href='<%= request.getContextPath() %>/back-end/mall/mallGetAll.jsp'">商品頁面</button>
	</div>
	
	
			<table id="comm" class="table table-bordered " style="margin-bottom:50px;">
				<thead>
					<tr class="">
						<th></th>
						<th>圖片</th>
						<th>商品名稱</th>
						<th>售價</th>
						<th>數量</th>
						<th>商品詳情</th>
						<th>適合年齡</th>
						<th>建議人數</th>
						<th>遊戲類型</th>
						<th>上架狀態</th>

					</tr>
				</thead>
				<tbody>

					<jsp:useBean id="gmTypeSvc" class="com.gmType.model.GmTypeService" scope="request"/>
					<jsp:useBean id="mallSvc" class="com.mall.model.MallService" scope="request"/>					
					<c:forEach var="mallVo" items="${selNameMallVoSet}">
						<tr>
							<td>
							<form action= "<%= request.getContextPath()%>/back-end/mall/mallGetOne.jsp" method="post">
							<input class="upda" type="submit"value="修改">
							<input type="hidden" name="commNo" value="${mallVo.commNo}">
							<!-- 叫出修改介面 -->
							<input type="hidden" name="call" value="updateModel">
							<!-- 讓update確認是getone -->
							<input  type="hidden" name="isGetOne" value="isGetOne">
							</form></td>
							<td><img src="<%= request.getContextPath()%>/Mall/MallShowImg?commNo=${mallVo.commNo}"></td>
							<td><div>${mallVo.commName}</div></td>
							<td>${mallVo.price}</td>
							<td>${mallVo.quantity}</td>
							<td><div>${mallVo.intro}</div></td>
							<td>${mallVo.age}歲以上</td>
							<td>${mallVo.player}人</td>
							<td><div>
							
							<c:forEach var="typeVo" items="${mallSvc.getType(mallVo.commNo)}">
							${typeVo.typeName} 
							</c:forEach>
							</div></td>
							<td class="col-md-1">${(mallVo.status=="1")?"上架中":"下架中" }</td>

						</tr>
					</c:forEach>

				</tbody>
			</table>



<c:if test="${param.call=='updateModel'}">
<%@ include file="/back-end/mall/mallUpdate.jsp" %>
</c:if>



<script>var ctx ="<%=request.getContextPath()%>"</script>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script src="<%= request.getContextPath() %>/js/malljs.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>


<script>

<!-- 有成功訊息就啟動 -->
<c:if test="${not empty successMsg}"> 
Swal.fire({
  position: 'center',
  icon: 'success',
  title: '${successMsg}',
  showConfirmButton: false,
  timer: 1500
})
<%session.removeAttribute("successMsg");%>
</c:if>

<c:if test="${not empty param.call}">
	$("#Modal").modal({show: true});
</c:if>

<!-- 查詢時有錯誤啟動 -->

<c:if test="${not empty selErroMsg}">
	Swal.fire({
	  icon: 'error',
	  title: '錯誤訊息',
	  text:"${selErroMsg}"
	})
</c:if>		

<!-- //有錯誤就自動展開  -->
<c:if test="${not empty erroMsg}">
	let erroMsg='';
	<c:forEach var="msg" items="${erroMsg}">
		erroMsg+='<div style="color:red; text-align:left;padding:0px 35px;">${msg}<br></div>';
	</c:forEach>
	Swal.fire({
		  icon: 'error',
		  title: '錯誤訊息',
		  html:erroMsg
		})
</c:if>

<!--有錯誤就自動展開 -->
<c:if test="${not empty updateerroMsg}">
	let erroMsg='';
	<c:forEach var="msg" items="${updateerroMsg}">
		erroMsg+='<div style="color:red; text-align:left;padding:0px 35px;">${msg}<br></div>';
	</c:forEach>
	Swal.fire({
		  icon: 'error',
		  title: '錯誤訊息',
		  html:erroMsg
		})
</c:if>	

</script>					


</body>
</html>

