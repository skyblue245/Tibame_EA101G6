<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>搜尋</title>
</head>
<body>
	<style>
body {
	background-position: center;
}

table {
	width: 80%;
	margin-top: 10px;
	margin-left: auto;
	margin-right: auto;
}

td th tr {
	text-align: center;
}

.icon {
	width: 20px;
	height: 20px;
}

h3 {
	margin-left: auto;
	margin-rghit: auto;
}

ul {
	margin-top: 2px;
	margin-left: auto;
	margin-right: auto;
}

li {
	margin-top: 15px;
}

div {
	margin-left: auto;
	margin-right: auto;
}
</style>

	<nav
		class="navbar navbar-expand-lg navbar-light bg-gradient-info shadow p-3 mb-5 rounded">

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item"><a href='listAllGame.jsp'>List </a>all
					Games.</li>
			</ul>
			<jsp:useBean id="gameSvc" scope="page"
				class="com.game.model.GameService" />

			<ul class="navbar-nav mr-auto">
				<li class="nav-item">
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/front-end/game/game.do"
						class="form-inline my-2 my-lg-0">
						<input type="text" name="gmname"  placeholder="遊戲名稱關鍵字" class="form-control" id="gmname">
						<input type="hidden" name="action" value="getSome_For_Display">
						<input type="submit" value="送出" class="btn btn-outline-secondary my-2 my-sm-0" id="action">
					</FORM>
				</li>
			</ul>

			<ul class="navbar-nav mr-auto">
				<li class="nav-item">
					<FORM METHOD="post" class="form-inline my-2 my-lg-0"
						ACTION="<%=request.getContextPath()%>/front-end/game/game.do">
						<select class="custom-select form-control mr-sm-1" name="gmno">
							<option value="">選擇遊戲名稱
								<c:forEach var="gameVO" items="${gameSvc.getAll()}">
									<option value="${gameVO.gmno}">${gameVO.gmname}
								</c:forEach>
						</select> <input type="hidden" name="action" value="getOne_For_Display">
						<button class="btn btn-outline-secondary my-2 my-sm-0"
							type="submit">送出</button>
					</FORM>
				</li>
			</ul>
		</div>
	</nav>
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