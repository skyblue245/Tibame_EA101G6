<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.rate.model.*"%>
<%@ page import="com.joinrm.model.*"%>

<%
  RateVO rateVO = (RateVO) request.getAttribute("rateVO");
%>
<%
	JoinrmService joinrmSvc2 = new JoinrmService();
	List<JoinrmVO> list2 = joinrmSvc2.findByPK(request.getParameter("rmno"),"");
	pageContext.setAttribute("list2",list2);
%>
<jsp:useBean id="mbrpfSvc" scope="page" class="com.mbrpf.model.MbrpfService" />
<!DOCTYPE html>
<html>
<head>
<title>�C���᪱�a���۵���-include��</title>
</head>
<body>
<form METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/room/rate.do">
<table class="table table-striped">
<tr><th style="width:130px">�����W��</th><th style="width:100px">��������</th><th >�������e</th></tr>
	<c:forEach var="joinrmVO2" items="${list2}">
		<c:if test="${joinrmVO2.mbrno ne mbrpfVO.mbrno}">
			<tr>		  
		      <td>
			      ${mbrpfSvc.getOneMbrpf(joinrmVO2.mbrno).mbrname}
			      <input type="hidden" name="ratedmbrno" value="${joinrmVO2.mbrno}">
			      <input type="hidden" name="rmno" value="<%=request.getParameter("rmno")%>">
			      <input type="hidden" name="ratingmbrno" value="${mbrpfVO.mbrno}">
			      <input type="hidden" name="shopreport" value="${joinrmVO2.shopreport}">
		      </td>
		      <td align="center"> 
		      	<select name="score">
					<%for (int i = 5; i >= 1; i--) {%>
					<option value="<%=i%>"><%=i%></option>
					<%}%>
				</select>
			  </td>
		      <td><input type="text" name="detail" value="��{�͵�" maxlength="15"></td>     
		    </tr>    		 	  
		</c:if>
	</c:forEach>
<!-- 	<tr><td colspan="3" align="right"><input type="hidden" name="action" value="insert"> -->
<!-- 	<input type="submit" value="�e�X"></td></tr> -->
	</table>
	<div style="text-align:right">
		<input type="hidden" name="action" value="insert">
		<input type="submit" value="�e�X" onclick="return(confirm('�e�X��N�L�k�ק�'))">
	</div>
</form>

</body>
</html>