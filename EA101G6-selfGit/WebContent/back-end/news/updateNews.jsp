<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.news.model.*"%>

<%
  NewsVO newsVO = (NewsVO) request.getAttribute("newsVO");
%>  

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ckeditor/ckeditor.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<title>最新消息修改 - updateNews.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
    margin-top: 50px;
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
  textarea {
  	width:33%;
  	height:21%;
  }

  table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
  .add-1{
  	margin-top: 60px;
  }
  .logoutPIC{
   	margin-top: 18%;
   }
</style>

</head>
<body bgcolor='white'>

<%@ include file="/back-end/back-end_nav.jsp"%>


<%-- 錯誤列表 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

	<div class="container">
		<div class="bg-white tm-block  add-1">
			<div class="row">
				<div class="col-12">
					<h2 class="tm-block-title d-inline-block">Update News</h2>
				</div>
			</div>



			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/news/news.do" name="form1">




				<div class="form-group">
					<label for="exampleFormControlInput1">最新消息標題:</label> <input
						type="TEXT" class="form-control" id="exampleFormControlInput1"
						placeholder="最新消息標題" name="newstt"
						value="<%=(newsVO == null) ? "" : newsVO.getNewstt()%>">
				</div>






				<textarea name="detail" id="detail"><%=(newsVO == null) ? "" : newsVO.getDetail()%></textarea>

				<script type="text/javascript">
					window.onload = function() {
						CKEDITOR.replace('detail');
					};
				</script>




				<br> <input type="hidden" name="newsno"
					value="${newsVO.newsno}"> <input type="hidden"
					name="action" value="update"> <input type="submit"
					value="送出修改">

			</FORM>

		</div>
	</div>




	<script src="./vendors/jquery/jquery-3.4.1.min.js"></script>
<script src="./vendors/popper/popper.min.js"></script>
<script src="./vendors/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>