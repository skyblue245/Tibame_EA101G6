<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>後台市集商品檢舉首頁</title>
</head>
<style>
	table#select-area{
		margin-top: 2%;
		border: gray 1px solid;
		background-color:white;
	}
	table#select-area td{
		padding:1% 0 0 0;
	}
	.half{
		width:50%;
	}
</style>
<body>
	<jsp:useBean id="shgmrpsvc" class="com.shgmrp.model.ShgmrpService"/>
	<jsp:useBean id="shgmsvc" class="com.shgm.model.ShgmService"/>

	<table id="select-area" class="table table-striped">
	<tr>
		<td>
			<form method="post" action="<%=request.getContextPath()%>/shgmrp/shgmrp.do">
				查詢市集檢舉，編號：
				<select size="1" name="shgmrpno">
					<c:forEach var="shgmrpvo" items="${shgmrpsvc.allShgmrp}">
						<option value="${shgmrpvo.shgmrpno}">${shgmrpvo.shgmrpno}
					</c:forEach>
				</select>
				<input type="hidden" name="action" value="get_one" >
				<input type="submit" value="送出" class="btn btn-primary">
			</form>
		</td>
		<td class="half">
			<form method="post" action="<%=request.getContextPath()%>/shgmrp/shgmrp.do">
			審核市集檢舉，搜尋名稱：
				<input id="word" type="text" name="word" value="${(searchRpResult == null)? '':param.word}"/>
				<input type="hidden" name="action" value="search"/>
				<input id="findshgmrp"  class="btn btn-primary" type="submit" value="送出"/>
			</form>
		</td>
	</tr>
	<tr>
		<td>
			<form method="post" action="<%=request.getContextPath()%>/shgmrp/shgmrp.do">
			查詢市集檢舉，名稱：
				<select size="1" name="shgmrpno">
					<c:forEach var="shgmrpvo" items="${shgmrpsvc.allShgmrp}">
						<option value="${shgmrpvo.shgmrpno}">${shgmsvc.getOneShgm(shgmrpvo.shgmno).shgmname}
					</c:forEach>
				</select>
				<input type="hidden" name="action" value="get_one" >
				<input type="submit" value="送出" class="btn btn-primary">
			</form>
		</td>
		<td class="half">
			<form method="post" action="<%=request.getContextPath()%>/shgmrp/shgmrp.do">
			審核市集檢舉：
			<select size="1" name="word">
					<c:forEach var="shgmrpvo" items="${shgmrpsvc.allShgmrp}">
						<option value="${shgmsvc.getOneShgm(shgmrpvo.shgmno).shgmname}"  ${param.word == shgmsvc.getOneShgm(shgmrpvo.shgmno).shgmname? 'selected':''}>${shgmsvc.getOneShgm(shgmrpvo.shgmno).shgmname}
					</c:forEach>
				</select>
				<input type="hidden" name="action" value="search"/>
				<input id="findshgmrp"  class="btn btn-primary" type="submit" value="送出"/>
			</form>
		</td>
	</tr>
	</table>
	
	<ul>
		<c:if test="${not empty errormsgs}">
			<c:forEach var="errors" items="${errormsgs}">
				<li>${errors}</li>
			</c:forEach>
		</c:if>
	</ul>
</body>
</html>