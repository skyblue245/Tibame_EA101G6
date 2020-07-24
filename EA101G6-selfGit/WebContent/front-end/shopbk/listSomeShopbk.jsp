<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.shopbk.model.*"%>
<%@ include file="/front-end/front-end-nav.jsp" %>
<%
	ShopbkService shopbkSvc = new ShopbkService();
	String shopno = null;
	java.sql.Timestamp shoppds = null;
	List<ShopbkVO> list = null;
	if(request.getParameter("shopps")==null){
		shopno = request.getParameter("shopno");
		list = shopbkSvc.getShopbkByShop(shopno);
	}
	if(request.getParameter("shopno")==null) {
		String s = req.getParameter("shoppds").trim()+":00";
		shoppds = java.sql.Timestamp.valueOf(s);
		list = shopbkSvc.getShopbkByTime(shoppds);
	}
	pageContext.setAttribute("list", list);
%>

<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">

	<title>遊戲列表</title>

	<style>
table {
	margin-top: 10px;
}

td{
	text-align: center;
}
th{
	text-align: center;
}


.icon {
	width: 20px;
	height: 20px;
}


img {
	width: 50px;
	height: 50px;
}

h4 {
	margin-left: 20px;
}
</style>
</head>

<body>



<jsp:include page="select_page.jsp" flush="true"/>

<%-- <%-- 錯誤表列 --%>
<%-- <c:if test="${not empty errorMsgs}"> --%>
<!-- 	<font style="color: red">請修正以下錯誤:</font> -->
<!-- 	<ul> -->
<%-- 		<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 			<li style="color: red">${message}</li> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
<%-- </c:if> --%>
<div class="container-fluid">
	<table class="table table-sm">
		<tr>
			<th>店家名稱</th>
			<th>提供人數</th>
			<th>遊玩時間</th>
			<th>以小時計算</th>
			<th>包日</th>
			<th></th>
		</tr>
		<jsp:useBean id="shopSvc" scope="page" class="com.shop.model.ShopService" />
		<c:forEach var="shopbkVO" items="${list}">
			<tr>
				<td><A style="color:black;" href="<%=request.getContextPath()%>/front-end/shop/shop.do?shopno=${shopbkVO.shopno}&action=getOne_For_Display3&requestURL=<%=request.getServletPath()%>"><b>${shopSvc.getOneShop(shopbkVO.shopno).shopname}</b></a></td>
				<td>${shopbkVO.ofdtable}</td>
				<td><fmt:formatDate value="${shopbkVO.shoppds}" pattern="yyyy-MM-dd HH:mm" />-<fmt:formatDate value="${shopbkVO.shoppde}" pattern="HH:mm" /></td>
				<td>${shopbkVO.payinfohr}</td>
				<td>${shopbkVO.payinfoday}</td>
				<td><a href="#" id="${shopbkVO.shopbkno}"><button class="btn btn-primary">來去開團</button></a></td>
<%-- 					onclick="location.href='<%=request.getContextPath()%>/front-end/room/create.jsp?shopno=${shopbkVO.shopno}&shoppds=${shopbkVO.shoppds}&shoppde=${shopbkVO.shoppde}';" --%>
				</tr>
				<script>
		    		 $("#${shopbkVO.shopbkno}").click(function() {
		    			 <c:choose>		    		
		    			 	<c:when test="${empty account}">
					    			Swal.fire({
										  icon: 'error',
										  title: '請先登入',
									});
					    	</c:when>					
							<c:otherwise>
								location.href="<%=request.getContextPath()%>/front-end/room/create.jsp?shopno=${shopbkVO.shopno}&shoppds=${shopbkVO.shoppds}&shoppde=${shopbkVO.shoppde}";
							</c:otherwise>
						</c:choose>		
		     	     });
				</script>
		</c:forEach>
	</table>
</div>
<div class="d-flex justify-content-center container"
		style="margin-left: auto; margin-right: auto;">
		<div class="row">
			<div class="col-sm-12">
			</div>
<c:if test="${openModal!=null}">

<div class="modal fade element-center" id="basicModal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
<!-- =========================================以下為原listOneEmp.jsp的內容========================================== -->
               <jsp:include page="/front-end/shop/listOneShop2.jsp" />
<!-- =========================================以上為原listOneEmp.jsp的內容========================================== -->			
<!-- 			<div class="modal-footer" style="margin-left:auto;margin-top:auto;"> -->
<!--                 <button type="button" class="btn btn-default" data-dismiss="modal">Close</button> -->
<!--             </div> -->
		</div>
		</div>
		</div>
	</div>
</div>
        <script>
    		 $("#basicModal").modal({show: true});
        </script>
 </c:if>
 
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<!-- 查詢時有錯誤啟動 -->
	<c:if test="${not empty errorMsgs}">
		<script>
			swal({
				text : "${errorMsgs}"
			});
		</script>
		<%
			request.removeAttribute("errorMsgs");
		%>
	</c:if>

</body>
</html>