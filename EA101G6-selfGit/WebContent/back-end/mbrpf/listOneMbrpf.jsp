<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.mbrpf.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
	MbrpfVO mbrpfVO = (MbrpfVO) request.getAttribute("mbrpfVO"); //mbrpfServlet.java(Concroller), �s�Jreq��mbrpfVO����
	
	if(request.getParameter("mbrpfno") != null){
		String mbrpfno = request.getParameter("mbrpfno");
		MbrpfService mbrpfSvc = new MbrpfService();
		mbrpfVO = mbrpfSvc.getOneMbrpf(mbrpfno);
		request.setAttribute("mbrpfVO", mbrpfVO);
	}
	
	
%>

<html>
<head>
<title>�|����� - listOneMbrpf.jsp</title>



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

</head>


<body>

<%@ include file="/back-end/back-end_nav.jsp"%>

<div class="container">
	<div class="row">
		<div class="col">
			<table>
		<tr>
			<th>�|���Ӥ�</th>
			<th>�|���s��</th>
			<th>�|���b��</th>
<!-- 			<th>�|���K�X</th> -->
			<th>�|���m�W</th>
<!-- 			<th>�|���Ӥ�</th> -->
			<th>�ͤ�</th>
			<th>�ʧO</th>
			<th>�q�l�l��</th>
			<th>�q��</th>
			<th>���ڱb��</th>
			<th>�ʺ�</th>
<!-- 			<th>�I�ƾl�B</th> -->
			<th>�|�����A</th>
			<th>�Q�����`�H��</th>
			<th>�Q�����`�P��</th>
			<th>���X�u����</th>
			<th>�`�ѹΦ���</th>
		</tr>
		<tr>
			<td><img width="100" height="100" src="<%= request.getContextPath()%>/mbrpf/mbrimg.do?mbrno=${mbrpfVO.mbrno}"></td>
			<td>${mbrpfVO.mbrno}</td>
			<td>${mbrpfVO.mbract}</td>
<%-- 			<td>${mbrpfVO.mbrpw}</td> --%>
			<td>${mbrpfVO.mbrname}</td>
<%-- 			<td><img width="100" height="100" src="<%= request.getContextPath()%>/mbrpf/mbrimg.do?mbrno=${mbrpfVO.mbrno}"></td> --%>
			<td>${mbrpfVO.birth}</td>
			<td><%--�����ʧO�P�_--%>
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
<%-- 			<td>${mbrpfVO.points}</td> --%>
			<td><%--�������A�P�_--%>
				<c:if test="${mbrpfVO.status ==1}">
				<c:out value="���`"/>
				</c:if>
				<c:if test="${mbrpfVO.status ==2}">
				<c:out value="���v"/>
				</c:if>	</td>
			<td>${mbrpfVO.ratedtotal}</td>
			<td>${mbrpfVO.startotal}</td>
			<td>${mbrpfVO.unattend}</td>
			<td>${mbrpfVO.ttattend}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/mbrpf/mbrpf.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="mbrno"  value="${mbrpfVO.mbrno}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			<br>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/mbrpf/mbrpf.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="mbrno"  value="${mbrpfVO.mbrno}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</table>		
		<a href="<%=request.getContextPath()%>/back-end/mbrpf/listAllMbrpf.jsp" style="background-color:rgba(255,255,255,0.7);"><font color="red">�^��@��|���޲z</font></a>
		</div>
	</div>
</div>



</body>
</html>