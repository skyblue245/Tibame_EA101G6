<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.joinrm.model.*"%>

<%
	JoinrmService joinrmSvc2 = new JoinrmService();
	List<JoinrmVO> list2 = joinrmSvc2.findByPK(request.getParameter("rmno"),"");
	pageContext.setAttribute("list2",list2);
%>
<jsp:useBean id="mbrpfSvc" scope="page" class="com.mbrpf.model.MbrpfService" />
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
<form METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/room/shoprp.do">
	<table class="table table-striped">
	<tr>
		<th style="width:100px">會員名稱</th><th style="width:100px;text-align:center">是否到場</th><th>備註</th>
	</tr>
	<c:forEach var="joinrmVO2" items="${list2}">
		<tr>
		    <td><input type="hidden" name="rmno" value="<%=request.getParameter("rmno")%>">
			${mbrpfSvc.getOneMbrpf(joinrmVO2.mbrno).mbrname}
			<input type="hidden" name="mbrno" value="${joinrmVO2.mbrno}">
			</td>
			<td style="text-align:center">
				<select name="attend">
					<option value="1">是</option>
					<option value="0">否</option>
				</select>
			</td>
			<td><input type="text" name="detail" maxlength="15"></td>
		</tr>	             		 	  
	</c:forEach>
<!-- 	<tr> -->
<!-- 		<td colspan="3" align="right"> -->
<!-- 			<input type="hidden" name="action" value="insert"> -->
<!-- 			<input type="submit" value="送出"> -->
<!-- 		</td> -->
<!-- 	</tr> -->
	</table>
	<div style="text-align:right">
		<input type="hidden" name="action" value="insert">
		<input type="submit" value="送出">
	</div>
</form>

</body>
</html>