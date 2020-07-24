<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*" %>
<%@ page import="com.mallad.model.*" %>


<% 
	MalladService malladSvc = new MalladService();
	List<MalladVO> list = malladSvc.getAll();
	pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有商城廣告</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
  
  img {
  	width:40%;
  }
  	
  table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
  .www{
  	width: 200px;
  	text-align: center;
  }
  .logoutPIC{
   	margin-top: 18%;
   }
   .add7{
  	position: fixed;
  	bottom: 30px;
  	right: 30px;
  	width: 70px;
  	height: 70px;
  }
</style>

</head>
<body bgcolor='white'>

<%@ include file="/back-end/back-end_nav.jsp"%>


	<div class="container">
		<table class="col-12">
			<tr>
				<td>
					<h3>所有商城廣告</h3>

				</td>
			</tr>
		</table>


		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>



		<table class="table table-striped bg-white">
			<tr>
				<th class="www align-middle">商城廣告編號</th>
				<th class="www align-middle">商品編號</th>
				<th class="www align-middle">商城廣告標題</th>

				<th class="www align-middle">開始日期</th>
				<th class="www align-middle">結束日期</th>
				<th class="www align-middle">廣告圖示</th>
				<th class="yyy align-middle">刪除</th>
			</tr>



			<c:forEach var="malladVO" items="${list}">
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/mallad/mallad.do"
					style="margin-bottom: 0px;">
					<tr>
						<td class="align-middle">${malladVO.malladno}</td>
						<td class="align-middle">${malladVO.commno}</td>
						<td class="align-middle">${malladVO.gmadtt}</td>
						<td class="align-middle">${malladVO.startt}</td>
						<td class="align-middle">${malladVO.stopt}</td>
						<td class="align-middle"><img
							src="<%=request.getContextPath()%>/mallad/malladpic.do?malladno=${malladVO.malladno}" /></td>
				</FORM>

				<td class="align-middle">
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/mallad/mallad.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> <input type="hidden"
							name="malladno" value="${malladVO.malladno}"> <input
							type="hidden" name="action" value="delete">
					</FORM>
				</td>
				</tr>
			</c:forEach>
		</table>
	</div>


	<a target="_self" href="<%=request.getContextPath()%>/back-end/mallad/addMallad.jsp"><img class="add7" src="<%=request.getContextPath()%>/back-end/mallad/images/addMad.png" title="Add Mallad"></a>


</body>
</html>