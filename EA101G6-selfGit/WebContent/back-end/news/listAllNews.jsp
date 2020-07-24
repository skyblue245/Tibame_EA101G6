<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*" %>
<%@ page import="com.news.model.*" %>


<% 
	NewsService newsSvc = new NewsService();
	List<NewsVO> list = newsSvc.getAll();
	pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有最新消息</title>

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
    
  }
  .www{
  	width: 400px;
  	
  }
  .yyy{
  	text-align: center;
  }
  .ttt{
  	margin-top: 20px;
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





<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>


<div class="container">
	<table class="table table-striped bg-white ttt">
	<tr>
		<th class="yyy">最新消息編號</th>
		<th class="www yyy">最新消息標題</th>
		<th class="www yyy">最新消息內容</th>
		<th class="yyy">發布日期</th>
		<th class="yyy">修改</th>
		<th class="yyy">刪除</th>
	</tr>
	

	
	
	<c:forEach var="newsVO" items="${list}">
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/news/news.do" style="margin-bottom: 0px;">
		<tr>
			<td class="yyy align-middle">${newsVO.newsno}</td>
			<td class="yyy www align-middle">${newsVO.newstt}</td>
			<td class="yyy www align-middle">${newsVO.detail}</td>
			<td class="yyy align-middle">${newsVO.pdate}</td>
			
			
			
			<td class="yyy align-middle">
				<input type="submit" value="修改" >
				<input type="hidden" name="newsno" value="${newsVO.newsno}">
				<input type="hidden" name="action" value="getOne_Update">
			</td></FORM>
			
			<td class="yyy align-middle">
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/news/news.do" style="margin-bottom: 0px;">
					<input type="submit" value="刪除" >
					<input type="hidden" name="newsno" value="${newsVO.newsno}">
					<input type="hidden" name="action" value="delete">
				</FORM>
			</td>
		</tr>
	</c:forEach>
</table>
</div>


<a target="_self" href="<%=request.getContextPath()%>/back-end/news/addNews.jsp"><img class="add7" src="<%=request.getContextPath()%>/images/news.png" title="Add News"></a>


</body>
</html>