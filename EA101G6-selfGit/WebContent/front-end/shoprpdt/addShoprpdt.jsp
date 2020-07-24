<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.rminfo.model.*"%>

<%
	RminfoService rmSvc = new RminfoService();
	String rmno = request.getParameter("rmno");
	RminfoVO rmVO = rmSvc.getOneRm(rmno);
	pageContext.setAttribute("rmVO", rmVO);
%>
<jsp:useBean id="shopSvc" scope="page" class="com.shop.model.ShopService" />
<jsp:useBean id="jSvc" scope="page" class="com.joinrm.model.JoinrmService" />
<!DOCTYPE html>
<html>
<head>
<title>檢舉店家include</title>
</head>
<body>
<form METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/shoprpdt/shoprpdt.do">
<table class="table table-striped">
<tr><th style="width:130px">店家名稱</th>
<th style="width:130px">房間名稱</th>
<th >檢舉內容</th></tr>
			<tr>		  
		      <td>${shopSvc.getOneShop(rmVO.shopno).shopname}
		      </td>
		      <td> 
				${rmVO.naming}
			  </td>
		      <td><input type="text" name="detail" maxlength="15" placeholder="有何不滿?" name="detail"></td>     
		    </tr>    
	</table>
	<div style="text-align:right">
		<input type="hidden" name="ratereport" value="${param.ratereport}">
		<input type="hidden" name="status" value="0">
		<input type="hidden" name="rmno" value="${param.rmno}">
		<input type="hidden" name="mbrno" value="${mbrpfVO.mbrno}">
		<input type="hidden" name="shopno" value="${shopSvc.getOneShop(rmVO.shopno).shopno}">
		<input type="hidden" name="action" value="insert">
		<input type="submit" value="送出" onclick="return(confirm('送出後將無法修改'))">
	</div>
</form>

</body>
</html>