<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mbrpf.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
    MbrpfService mbrpfSvc = new MbrpfService();
    List<MbrpfVO> list = mbrpfSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>�Ҧ���� - listAllMbrpf.jsp</title>

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
<!-- 		 <h3>�Ҧ���� - listAllMbrpf.jsp</h3> -->
<!-- 		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4> -->
<!-- 	</td></tr> -->
<!-- </table> -->

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
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
		<th style="text-align:center">�|���Ӥ�</th>
		<th>�|���s��</th>
		<th>�|���b��</th>
<!-- 		<th>�@��|���K�X</th> -->
		<th>�|���m�W</th>
<!-- 		<th>�|���Ӥ�</th> -->
		<th>�ͤ�</th>
		<th>�ʧO</th>
		<th>�q�l�l��</th>
		<th>�q��</th>
		<th>�����ڶ��b��</th>
		<th>�ʺ�</th>
		<th>�I�ƾl�B</th>
<!-- 		<th>�|�����A</th> -->
		<th>�Q�����`�H��</th>
		<th>�Q�����`�P��</th>
		<th>���X�u����</th>
		<th>�`�ѹΦ���</th>
		
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
			<td><%--�����ʧO�P�_--%>>
				<c:if test="${mbrpfVO.sex ==1}">
				<c:out value="�k"/>
				</c:if>
				<c:if test="${mbrpfVO.sex ==2}">
				<c:out value="�k"/>
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
			     <input type="submit" value="�Բ�">
			     <input type="hidden" name="mbrno"  value="${mbrpfVO.mbrno}">
			     <input type="hidden" name="action"	value="getOne_For_Display"></FORM>
			<br>
			
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/mbrpf/mbrpf.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
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