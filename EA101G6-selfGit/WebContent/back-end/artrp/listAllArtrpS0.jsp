<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*" %>
<%@ page import="com.artrp.model.*" %>
<%@ page import="com.art.model.*" %>


<% 
	ArtrpService artrpSvc = new ArtrpService();
	List<ArtrpVO> list = artrpSvc.getAll();
	pageContext.setAttribute("list",list);
	
	ArtService artSvc = new ArtService();
	ArtVO artVO = new ArtVO();
	pageContext.setAttribute("artSvc", artSvc);
	pageContext.setAttribute("artVO", artVO);
	
%>


<html>
<head>
<title>上架中文章相關檢舉</title>

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
  	width:400px;
  	height:500px;	
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
  .logoutPIC{
   	margin-top: 18%;
   }
   .www{
   	text-align: center;
   }
</style>

</head>
<body bgcolor='white'>

	<%@ include file="/back-end/back-end_nav.jsp"%>

	<div class="container">
		<table class="col-12">
			<tr>
				<td>
					<h3>上架中文章相關檢舉</h3>

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

		<ul class="nav col-12 justify-content-center">
			<li class="nav-item"><a class="nav-link disabled"
				href="<%=request.getContextPath()%>/back-end/artrp/listAllArtrpS0.jsp">上架中</a>
			</li>
			<li class="nav-item"><a class="nav-link"
				href="<%=request.getContextPath()%>/back-end/artrp/listAllArtrpS1.jsp">已下架</a>
			</li>
		</ul>

		<table class=" table table-striped bg-white">
			<tr>
				<th class="align-middle www">文章檢舉編號</th>
				<th class="align-middle www">文章編號</th>
				<th class="align-middle www">檢舉內容</th>
				<th class="align-middle www">檢舉人編號</th>
				<th class="align-middle www">檢舉狀態</th>

				<th class="align-middle www">修改</th>

			</tr>

			<%-- 	<jsp:useBean id="mbrpfSvc" scope="page" class="com.mbrpf.model.MbrpfService"/> --%>


			<c:forEach var="artrpVO" items="${list}">

				<input type="hidden"
					value="${artVO = artSvc.getOneArt(artrpVO.artno)}">

				<c:if test="${artVO.status == 0}">
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/artrp/artrp.do"
						style="margin-bottom: 0px;">
						<tr>
							<td>${artrpVO.artrpno}</td>

							<td><a target="_blank"
								href="<%=request.getContextPath()%>/artrp/artrp.do?action=getOne_Display&artno=${artrpVO.artno}">${artrpVO.artno}</a></td>
							<td>${artrpVO.detail}</td>
							<td>${artrpVO.mbrno}</td>
							<td><select size="1" name="status">
									<option value="0" ${(artrpVO.status==0)? 'selected':''}>尚未審核

									
									<option value="1" ${(artrpVO.status==1)? 'selected':''}>通過審核

									
							</select></td>



							<td><input type="submit" id="action" value="修改"> <input
								type="hidden" name="artno" value="${artrpVO.artno}"> <input
								type="hidden" name="artrpno" value="${artrpVO.artrpno}">
								<input type="hidden" name="action" value="update0"></td>


						</tr>
					</FORM>
				</c:if>
			</c:forEach>
		</table>
	</div>
	
	




</body>
</html>