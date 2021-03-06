<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.gmlist.model.*"%>
<%@ page import="com.shop.model.*"%>

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

tr th {
	border: 2px solid black;
	text-align: center;
}

td {
	border: 2px solid black;
	text-align: center;
}

.icon {
	width: 20px;
	height: 20px;
}
nav {
	background-color: white;
}

h3 {
	margin-left: auto;
	margin-rghit: auto;
}
ul {
	margin-top: 2px;
	margin-left: center;
	margin-right: center;
}
li {
	margin-top: 15px;
}
</style>
</head>
<body bgcolor='white'>
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
<nav 
		class="navbar navbar-expand-lg navbar-light bg-gradient-info shadow p-3 mb-5 rounded">

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
	<ul class="navbar-nav mr-auto">
		<li class="nav-item"><a href='listAllShop.jsp' >List</a> all Shop. <br></li>
<jsp:useBean id="shopSvc" scope="page" class="com.shop.model.ShopService" />
	</ul>


	<ul class="navbar-nav mr-auto">
		<li class="nav-item">
			<FORM METHOD="post" ACTION="<%= request.getContextPath()%>/back-end/shop/shop.do">
				<b>選擇處理狀態:</b> <select size="1" name="status">
						<option value=0>未審核	
						<option value=1>審核通過
						<option value=2>停權		
				</select> <input type="hidden" name="action" value="getSome_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>
	</ul>
</div>
	</nav>

</body>
</html>