<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.game.model.*"%>

<%
	GameService gameSvc = new GameService();
	String gmname = request.getParameter("gmname");
	List<GameVO> list = gameSvc.getSomeGames(gmname);
	pageContext.setAttribute("list", list);
%>

<!doctype html>
<html lang="en">
<head>

<meta charset="utf-8">

<title>遊戲列表</title>

<style>
table {
	margin-top: 10px;
}

tr th {

	text-align: center;
}

td {
	text-align: center;
}

.icon {
	width: 20px;
	height: 20px;
}

#preview img {
	width: 150px;
	height: 150px;
}

img {
	width: 50px;
	height: 50px;
}

h4 {
	margin-left: 20px;
}
</style>
</head>

<body>

	<jsp:include page="/front-end/front-end-nav.jsp" flush="true"></jsp:include>


	<jsp:include page="select_page.jsp" flush="true"></jsp:include>

	<div class="container-fluid">
		<div class="row">
			<%@ include file="page1.file"%>
			<table class="table table-sm">
				<tr>
					<th>遊戲編號</th>
					<th>遊戲名稱</th>
					<th>遊戲圖片</th>
					<th>修改</th>
				</tr>
				<c:forEach var="gameVO" items="${list}" begin="<%=pageIndex%>"
					end="<%=pageIndex+rowsPerPage-1%>">
					<tr>
						<td>${gameVO.gmno}</td>
						<td>${gameVO.gmname}</td>
						<td><img
							src="<%=request.getContextPath()%>/GameShowImg?gmno=${gameVO.gmno}"></td>
						<td>
							<FORM METHOD="post" ACTION="game.do" style="margin-bottom: 0px;">
								<input type="submit" value="修改"
									class="btn btn-secondary my-2 my-sm-0"> <input
									type="hidden" name="gmno" value="${gameVO.gmno}"> <input
									type="hidden" name="action" value="getOne_For_Update">
							</FORM>
						</td>
					</tr>
				</c:forEach>
			</table>
			<div class="d-flex justify-content-center container"
				style="margin-left: auto; margin-right: auto;">
				<div class="row">
					<div class="col-sm-12">
						<%@ include file="page2.file"%>
					</div>
				</div>
			</div>
		</div>
	</div>

<script>
	$(document).ready(function(){	
			<c:if test="${not empty successMsgs}">
			swal("Good", "${successMsgs}", "success");
			</c:if>
	})
</script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</body>
</html>