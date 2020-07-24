<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.msg.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
    MsgService msgSvc = new MsgService();
    List<MsgVO> list = msgSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>�Ҧ����|���</title>

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
  .ser-0{
  	margin-top: 15px;
  }
  .ser-1{
  	margin-left: 25px;
  	margin-right: 800px;
  }
  
  
  .add7{
  	position: fixed;
  	bottom: 30px;
  	right: 30px;
  	width: 125px;
  	height: 125px;
  }
  .www{
  	text-align: center;
  }
   
  .logoutPIC{
  	margin-top:18%;
  }
</style>

</head>
<body bgcolor='white'>

<%@ include file="/back-end/back-end_nav.jsp"%>





	<ul class="col-md-10 offset-md-1 ser-0">

		<li class="float-left ser-1">
			<FORM METHOD="post" ACTION="msg.do">
				<b>��J�d���s�� (�pBMS0001):</b> <input type="text" name="msgno"> <input
					type="hidden" name="action" value="getOne_For_Display"> <input
					type="submit" value="�e�X">
			</FORM>
		</li>
		
		<jsp:useBean id="msSvc" scope="page" class="com.msg.model.MsgService" />

		

		

		
		<%-- ���~��C --%>
		<c:if test="${not empty errorMsgs}">


			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>

		</c:if>


	</ul>




<table  class="col-md-10 offset-md-1 table table-striped bg-white">
	<tr>
		<th>�d���s��</th>
		<th>�|���s��</th>
		<th>�d�����e</th>
		<th>�峹�s��</th>
		<th>��ܪ��A</th>
		<th>�ק�</th>
		<th>�R��</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="msgVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${msgVO.msgno}</td>
			<td>${msgVO.mbrno}</td>
			<td>${msgVO.detail}</td>
			<td>${msgVO.artno}</td>
			<td>${msgVO.status}</td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/msg/msg.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="msgno"  value="${msgVO.msgno}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/msg/msg.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="msgno"  value="${msgVO.msgno}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
	<footer class="fffuuu">
		<div class="d-flex justify-content-center">
			<%@ include file="page2.file"%>
		</div>

	</footer>


</body>
</html>