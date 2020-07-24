<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tfcord.model.*" %>

<%--將現在的位址，以pointLocation的名字存進session --%>
<%-- <% session.setAttribute("pointLocation", request.getRequestURI()); %>  --%>

<%
	TfcordService tfcordSvc = new TfcordService();
	List<TfcordVO> list = tfcordSvc.getNotYetAll();
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/> <!--要有這條 -->
<link rel="stylesheet" href="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.css">
<script src="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.js"></script>
<title>尚未處理的點數轉換紀錄</title>

<style type="text/css">
	.table{
		text-align:center;
	}
	.tftext{
		text-align: center;
	}

</style>

</head>
<body>

<jsp:include page="/back-end/back-end_nav.jsp"></jsp:include> 

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<!-- <h3>此為所有還沒審核的點數交易紀錄(後台點數轉換管理)</h3> -->

<!-- 如果別人要從其他頁面進行購買點數的動作，會回到來源網頁 -->
<%-- <a href="<%=request.getContextPath()%>/front-end/tfcord/buyPoint.jsp">點數儲值</a><br> --%>


<div class="container">
	<div class="row tm-content-row emptop">
		<div class="tm-col emp">
			<div class="row">
				<div class="col tftext">
					<form method="post" action="<%=request.getContextPath()%>/tfcord/TfcordServlet">
						<b>請輸入會員編號(如：BM00001)</b>
						<input type="text" name="mbrno">
						<input type="hidden" name="action" value="getMbr_Tfcord">
					    <input type="submit" value="送出">
					</form>
				</div>
				<div class="col tftext">
					<form method="post" action="<%=request.getContextPath()%>/tfcord/TfcordServlet">
						<b>請輸入兌換編號(如：20200630-0000049)</b>
						<input type="text" name="tfno">
						<input type="hidden" name="action" value="getOne_Tfcord">
					    <input type="submit" value="送出">
					</form>
				</div>
			</div>
			<br>
			<div class="bg-white tm-block">
				<!-- 轉換紀錄清單的區塊 -->
				<div class="row">
					<div class="col"><!-- 轉換紀錄清單的標題 -->
						<h2 class="tm-block-title d-inline-block">未審核點數轉換紀錄</h2>
					</div>
					<div class="col">
						<form method="post" action="<%=request.getContextPath()%>/back-end/tfcord/listAllTfcord.jsp">
							<input type="submit" id="addEmp" value="顯示全部" >
						</form>
					</div>
				</div>
				<div class="table-responsive"><!--table-striped：將畫面一行灰色，一行白色-->
					<table class="table"><!-- listAllTfcord.jsp -->
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
						<%@ include file="page1.file"%><!-- 引入換頁的程式碼 -->
						<tbody>
							<c:forEach var="tfcordVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex + rowsPerPage-1 %>">
								<tr ${(tfcordVO.tfno == param.tfno) ?  'bgcolor=#CCCCFF' : ''} >
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
											<input type ="hidden" name="price" value="${tfcordVO.price}">
											<input type ="hidden" name="requestURL" value="<%=request.getServletPath()%>">					
											<input type ="hidden" name="whichPage" value="<%=whichPage%>">					
											<input type ="hidden" name="action" value="changeStatus">
										</form>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<%@ include file="page2.file"%>
				</div>
			</div>
		</div>
	</div>
</div>





<!-- <table> -->
<!-- 	<tr> -->
<!-- 		<th>兌換編號</th> -->
<!-- 		<th>會員編號</th> -->
<!-- 		<th>兌換種類</th> -->
<!-- 		<th>兌換金額</th> -->
<!-- 		<th>兌換時間</th> -->
<!-- 		<th>審核狀態</th> -->
<!-- 		<th>審核按鈕</th> -->
<!-- 	</tr> -->
	
<%-- 	<%@ include file="page1.file"%> --%>
<%-- 	<c:forEach var="tfcordVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>"> --%>
<%-- 		<tr ${(tfcordVO.tfno == param.tfno) ?  'bgcolor=#CCCCFF' : ''} > --%>
<%-- 			<td>${tfcordVO.tfno}</td> --%>
<%-- 			<td>${tfcordVO.mbrno}</td> --%>
<%-- 			<td>${tfcordVO.tftype == "M" ? "兌現" : "儲值"}</td> --%>
<%-- 			<td>${tfcordVO.price}</td> --%>
<%-- 			<td><fmt:formatDate value="${tfcordVO.tftime}" pattern="yyyy-MM-dd HH:mm:ss"/></td> --%>
<%-- 			<td>${tfcordVO.tfstatus == 1 ? "已審核" : "未審核"}</td> --%>
<!-- 			<td> -->
<%-- 				<form method="post" action="<%=request.getContextPath()%>/tfcord/TfcordServlet"> --%>
<%-- 					<input type="submit" value="確認審核" ${(tfcordVO.tfstatus == 1) ? "disabled" : "" }> --%>
<%-- 					<input type ="hidden" name="mbrno" value="${tfcordVO.mbrno}"> --%>
<%-- 					<input type ="hidden" name="tfno" value="${tfcordVO.tfno}"> --%>
<%-- 					<input type ="hidden" name="requestURL" value="<%=request.getServletPath()%>">					 --%>
<%-- 					<input type ="hidden" name="whichPage" value="<%=whichPage%>">					 --%>
<!-- 					<input type ="hidden" name="action" value="changeStatue"> -->
<!-- 				</form>  -->
<!-- 			</td> -->
<!-- 		</tr> -->
<%-- 	</c:forEach> --%>
	
<!-- </table> -->
<%-- <%@ include file="page2.file"%> --%>

<script>
if(${ok}){
	 swal({ 
		  title : "審核完成" ,
		  type : "success" ,
		  buttons : false ,
		  confirmButtonColor : "#9CCC65"
		});
}
</script>


</body>
</html>