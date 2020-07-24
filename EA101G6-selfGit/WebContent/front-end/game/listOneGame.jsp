<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.game.model.*"%>

<%
	GameVO gameVO = (GameVO) request.getAttribute("gameVO"); //shopServlet.java (Concroller) 存入req的shopVO物件 (包括幫忙取出的shopVO, 也包括輸入資料錯誤時的shopVO物件)	
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
			<table class="table table-sm">
				<tr>
					<th>遊戲編號</th>
					<th>遊戲名稱</th>
					<th>遊戲圖片</th>
					<th>修改</th>
				</tr>
					<tr>
						<td>${gameVO.gmno}</td>
						<td>${gameVO.gmname}</td>
						<td><img
							src="<%=request.getContextPath()%>/GameShowImg?gmno=${gameVO.gmno}"></td>
						<td>
							<FORM METHOD="post" ACTION="game.do" style="margin-bottom: 0px;">
								<input type="submit" value="修改" class="btn btn-secondary"> <input type="hidden"
									name="gmno"  value="${gameVO.gmno}"> <input
									type="hidden" name="action" value="getOne_For_Update">
							</FORM>
						</td>
					</tr>
			</table>
		</div>
	</div>

</body>
</html>