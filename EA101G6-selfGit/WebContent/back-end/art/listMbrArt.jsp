<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*" %>
<%@ page import="com.art.model.*" %>

<%
	
	List<ArtVO> list = (List<ArtVO>) request.getAttribute("artVO");
	pageContext.setAttribute("list", list);
%>


<html>
<head>
<title>文章資料</title>

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
</style>

<style>
  table {
	width: 600px;
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
  img {
  	width:400px;
  	height:500px;	
  }
  .www{
  	text-align: center;
  }
  .logoutPIC{
   	margin-top: 18%;
   }
</style>

</head>

<body bgcolor='white'>

<%@ include file="/back-end/back-end_nav.jsp"%>


	<div class="container">
		<table class="col-12">
			<tr>
				<td>
					<h3>文章資料</h3>
					<h4>
						<a href="listAllArt.jsp">回首頁</a>
					</h4>
				</td>
			</tr>
		</table>

		<table class=" table table-striped bg-white">
			<tr>
				<th class="www">文章編號</th>
				<th class="www">作者編號</th>
				<th class="www">文章標題</th>
				<th class="www">發文日期</th>
				<th class="www">文章狀態</th>
				<th class="www">文章種類</th>

				<th class="www">修改</th>
				<th class="www">刪除</th>



				<c:forEach var="artVO" items="${list}">
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/back-end/art/art.do"
						style="margin-bottom: 0px";">
						<tr>
							<td>${artVO.artno}</td>
							<td>${artVO.mbrno}</td>
							<td>${artVO.arttt}</td>
							<td>${artVO.pdate}</td>
							<td><select size="1" name="artStatus">
									<option value="0" ${(artVO.status==0)? 'selected':''}>正常顯示</option>
									<option value="1" ${(artVO.status==1)? 'selected':''}>隱藏文章</option>
							</select></td>
							<c:if test="${artVO.atno == 'AT01'}">
								<td>遊戲心得</td>
							</c:if>

							<c:if test="${artVO.atno == 'AT02'}">
								<td>遊戲百科</td>
							</c:if>
							<c:if test="${artVO.atno == 'AT03'}">
								<td>討論區公告</td>
							</c:if>




							<td><input type="hidden" name="artno" value="${artVO.artno}">
								<input type="hidden" name="action" value="update_Art_Status">
								<input type="submit" value="修改"></td>
							<td>
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/art/art.do"
									style="margin-bottom: 0px;">
									<input type="submit" value="刪除"> <input type="hidden"
										name="artno" value="${artVO.artno}"> <input
										type="hidden" name="action" value="delete">
								</FORM>
							</td>

						</tr>
					</FORM>
				</c:forEach>
		</table>
	</div>





</body>
</html>