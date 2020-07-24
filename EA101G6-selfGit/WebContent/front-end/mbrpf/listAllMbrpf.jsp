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

  .t111{
  	margin-top: 35px;
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
</style>

<meta charset="utf-8">

</head>
<body>

<%@ include file="/front-end/front-end-nav.jsp"%>

<table id="table-1" class="t111">
	<tr><td>
		 <h3>�Ҧ���� - listAllMbrpf.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>�|���Ӥ�</th>
		<th>�|���s��</th>
		<th>�@��|���b��</th>
		<th>�@��|���K�X</th>
		<th>�|���m�W</th>
<!-- 		<th>�|���Ӥ�</th> -->
		<th>�X�ͦ~���</th>
		<th>�ʧO</th>
		<th>�q�l�l��</th>
		<th>�q��</th>
		<th>�����ڶ��b��</th>
		<th>�ʺ�</th>
		<th>�I�ƾl�B</th>
		<th>�@��|�����A</th>
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
			<td>${mbrpfVO.mbrpw}</td>
			<td>${mbrpfVO.mbrname}</td>
<%-- 			<td><img alt="" width="100" height="100" src="<%= request.getContextPath()%>/mbrpf/mbrimg.do?mbrno=${mbrpfVO.mbrno}"></td> --%>
			<td>${mbrpfVO.birth}</td>
			<td>${mbrpfVO.sex}</td>
			<td>${mbrpfVO.mail}</td>
			<td>${mbrpfVO.phone}</td>
			<td>${mbrpfVO.mbrac}</td>
			<td>${mbrpfVO.nickname}</td>
			<td>${mbrpfVO.points}</td>
			<td>${mbrpfVO.status}</td>
			<td>${mbrpfVO.ratedtotal}</td>
			<td>${mbrpfVO.startotal}</td>
			<td>${mbrpfVO.unattend}</td>
			<td>${mbrpfVO.ttattend}</td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/mbrpf/mbrpf.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="mbrno"  value="${mbrpfVO.mbrno}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/mbrpf/mbrpf.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="mbrno"  value="${mbrpfVO.mbrno}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>