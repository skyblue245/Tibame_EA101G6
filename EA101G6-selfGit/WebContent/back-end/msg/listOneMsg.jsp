<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.msg.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  MsgVO msgVO = (MsgVO) request.getAttribute("msgVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>留言資料</title>

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
</style>

</head>
<body bgcolor='white'>

<%@ include file="/back-end/back-end_nav.jsp"%>

<table id="table-1"  class="col-md-10 offset-md-1">
	<tr><td>
		 <h3>留言資料</h3>
		 <h4><a href="listAllMsg.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table  class="col-md-10 offset-md-1 table table-striped bg-white">
	<tr>
		<th>留言編號</th>
		<th>會員編號</th>
		<th>留言內容</th>
		<th>文章編號</th>
		<th>顯示狀態</th>
	</tr>
	<tr>
		<td>${msgVO.msgno}</td>
			<td>${msgVO.mbrno}</td>
			<td>${msgVO.detail}</td>
			<td>${msgVO.artno}</td>
			<td>${msgVO.status}</td>
	</tr>
</table>

</body>
</html>