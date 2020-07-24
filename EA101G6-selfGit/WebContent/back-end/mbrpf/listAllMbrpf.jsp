<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mbrpf.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    MbrpfService mbrpfSvc = new MbrpfService();
    List<MbrpfVO> list = mbrpfSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有資料 - listAllMbrpf.jsp</title>

<style>
 
  table {
	width: 800px;
	background-color: rgba(255,255,255,0.7);
	margin-top: 5px;
	margin-bottom: 5px;
	text-align: center;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
    position:relative;
    min-width:80px;
  }
  
</style>

<meta charset="utf-8">

</head>
<body>
<%@ include file="/back-end/back-end_nav.jsp"%>
<%-- <%@ include file="/front-end/front-end-nav.jsp"%> --%>

<!-- <table id="table-1" class="t111"> -->
<!-- 	<tr><td> -->
<!-- 		 <h3>所有資料 - listAllMbrpf.jsp</h3> -->
<!-- 		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4> -->
<!-- 	</td></tr> -->
<!-- </table> -->

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
	<div class="row">
		<div class="col">
		
		</div>
		<div class="col">
		      <table style="">
	<tr>
		<th style="text-align:center">會員照片</th>
		<th>會員編號</th>
		<th>會員帳號</th>
<!-- 		<th>一般會員密碼</th> -->
		<th>會員姓名</th>
<!-- 		<th>會員照片</th> -->
		<th>生日</th>
		<th>性別</th>
		<th>電子郵件</th>
		<th>電話</th>
		<th>接收款項帳戶</th>
		<th>暱稱</th>
		<th>點數餘額</th>
<!-- 		<th>會員狀態</th> -->
		<th>被評價總人數</th>
		<th>被評價總星數</th>
		<th>未出席次數</th>
		<th>總參團次數</th>
		
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="mbrpfVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td><img alt="" width="100" height="100" src="<%= request.getContextPath()%>/mbrpf/mbrimg.do?mbrno=${mbrpfVO.mbrno}"></td>
			<td>${mbrpfVO.mbrno}</td>
			<td>${mbrpfVO.mbract}</td>
<%-- 			<td>${mbrpfVO.mbrpw}</td> --%>
			<td>${mbrpfVO.mbrname}</td>
<%-- 			<td><img alt="" width="100" height="100" src="<%= request.getContextPath()%>/mbrpf/mbrimg.do?mbrno=${mbrpfVO.mbrno}"></td> --%>
			<td>${mbrpfVO.birth}</td>
			<td><%--此為性別判斷--%>>
				<c:if test="${mbrpfVO.sex ==1}">
				<c:out value="男"/>
				</c:if>
				<c:if test="${mbrpfVO.sex ==2}">
				<c:out value="女"/>
				</c:if>						
			</td>
			<td>${mbrpfVO.mail}</td>
			<td>${mbrpfVO.phone}</td>
			<td>${mbrpfVO.mbrac}</td>
			<td>${mbrpfVO.nickname}</td>
			<td>${mbrpfVO.points}</td>
<%-- 			<td>${mbrpfVO.status}</td> --%>
			<td>${mbrpfVO.ratedtotal}</td>
			<td>${mbrpfVO.startotal}</td>
			<td>${mbrpfVO.unattend}</td>
			<td>${mbrpfVO.ttattend}</td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/mbrpf/mbrpf.do" style="margin-bottom: 0px;">
			     <input type="submit" value="詳細">
			     <input type="hidden" name="mbrno"  value="${mbrpfVO.mbrno}">
			     <input type="hidden" name="action"	value="getOne_For_Display"></FORM>
			<br>
			
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/mbrpf/mbrpf.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="mbrno"  value="${mbrpfVO.mbrno}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>
		</div>
		<div class="col">
		
		</div>
	</div>
</div>












</body>
</html>