<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Gaming on Board</title>

	<meta charset="utf-8">
  

<style>
  
  h4 {
    color: blue;
    display: inline;
  }
  .icon{
    width:20px;
    height:20px;
   }
</style>

</head>



<body>

<%@ include file="/front-end/front-end-nav.jsp"%>

<h3>資料查詢:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
	<li><a href='listAllArt.jsp'>List</a> all Arts. <br><br></li>
	
	
	
	<jsp:useBean id="artSvc" scope="page" class="com.art.model.ArtService"/>
	
	<li>
		<FORM METHOD="post" ACTION="art.do">
			<b>搜尋文章 (請輸入關鍵字) : </b>
			<input type="text" name="keyWord">
			<input type="hidden" name="action" value="getOne_Key_Display">
			<input type="submit" value="送出">
		</FORM>
	</li>
</ul>





<h3>新增文章</h3>

<ul>
	<li><a href='addArt.jsp'>Add</a> a new Article.</li>
</ul>


</body>
</html>