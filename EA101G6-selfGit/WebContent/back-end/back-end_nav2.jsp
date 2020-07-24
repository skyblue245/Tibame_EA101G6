<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- contentType 是告訴"瀏覽器"我們專案的編碼方式為何 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />


<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>

<title>員工管理</title>

<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600">
<!-- https://fonts.google.com/specimen/Open+Sans -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/backCss/fontawesomeOld.min.css">
<!-- https://fontawesome.com/ -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/backCss/bootstrapOld.min.css">
<!-- https://getbootstrap.com/ -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/backCss/tooplateOld.css">

<!-- 這邊寫css!!!!!!!!! -->

<style>

</style>

</head>
<body class="bg03">
	<div class="container">
		<div class="row">
			<div class="col-12">
				<nav class="navbar navbar-expand-xl navbar-light bg-light">
					<a class="navbar-brand" href="<%=request.getContextPath()%>/back-end/index.jsp"> 
						<i class="fas fa-3x fa-tachometer-alt tm-site-icon"></i>
						<h1 class="tm-site-title mb-0">桌遊列國</h1>
					</a>
					
					<button class="navbar-toggler ml-auto mr-0" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
							aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">	
						<span class="navbar-toggler-icon"></span>
					</button>
					
					<div class="collapse navbar-collapse" id="navbarSupportedContent">
						<ul class="navbar-nav mx-auto backnav">
							<li class="nav-item">
								<a class="nav-link" href="<%=request.getContextPath()%>/back-end/index.jsp">首頁 
									<span class="sr-only">(current)</span>
								</a>
							</li>
							
							<li class="nav-item dropdown backnavdown">
								<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" 
								   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> 會員管理 
								</a>
								<div class="dropdown-menu" aria-labelledby="navbarDropdown">
									<a class="dropdown-item downlist" href="<%=request.getContextPath()%>/back-end/emp/listOneEmp.jsp">一般會員管理</a> 								
<!-- active測試，之後換頁面  -->			<a class="dropdown-item downlist" href="<%=request.getContextPath()%>/back-end/tfcord/listAllTfcord.jsp">店家管理</a>
								</div></li>
							<li class="nav-item"><a class="nav-link"
								href="#">商城管理</a>
							<!-- 目前還沒有這項 --></li>
							<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/back-end/emp/listAllEmp.jsp">員工管理</a>
							</li>
<!-- active測試，之後換頁面  -->	<li class="nav-item"><a class="nav-link"
								href="<%=request.getContextPath()%>/back-end/emp/addEmp.jsp">市集管理</a>
							<!-- 目前還沒有這項 --></li>
							<li class="nav-item dropdown backnavdown"><a
								class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
								role="button" data-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false"> 檢舉管理 </a>
								<div class="dropdown-menu" aria-labelledby="navbarDropdown">
									<a class="dropdown-item downlist" href="#">店家檢舉審核</a> 
									<a class="dropdown-item downlist" href="#">會員檢舉審核</a> 
									<a class="dropdown-item downlist" href="#">討論區檢舉審核</a> 
									<a class="dropdown-item downlist" href="#">市集商品檢舉審核</a>
								</div></li>
							<li class="nav-item"><a class="nav-link"
								href="<%=request.getContextPath()%>/back-end/art/listAllArt.jsp">討論區管理</a>
							</li>
							<li class="nav-item dropdown backnavdown"><a
								class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
								role="button" data-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false"> 揪團管理 </a>
								<div class="dropdown-menu" aria-labelledby="navbarDropdown">
									<a class="dropdown-item downlist" href="<%=request.getContextPath()%>/back-end/room/back-end_rminfoList.jsp">房間列表</a> 
									<a class="dropdown-item downlist" href="<%=request.getContextPath()%>/back-end/room/back-end_rateList.jsp">會員評價列表</a> 
									<a class="dropdown-item downlist" href="<%=request.getContextPath()%>/back-end/room/back-end_shoprpList.jsp">店家回報列表</a> 
								</div></li>
							<li class="nav-item"><a class="nav-link"
								href="<%=request.getContextPath()%>/back-end/emp/updatePwd.jsp">修改密碼</a>
							<!-- 目前還沒有這項 --></li>
						</ul>
						<ul class="navbar-nav">
							<li class="nav-item">
								<form method="post" action="<%=request.getContextPath()%>/emp/EmpServlet">
									<a class="nav-link d-flex" href="<%=request.getContextPath()%>/emp/EmpServlet?action=logout">
										<i class="far fa-user mr-2 tm-logout-icon"></i> <span>Logout</span>
										<input type="hidden" name="action" value="logout">
									</a>
								</form>
							</li>
						</ul>
					</div>
				</nav>
			</div>
		</div>
	</div>
	
	
	
	<script src="<%=request.getContextPath() %>/js/backJs/bootstrapOld.min.js"></script>
	
	<script>
	// DOM is ready
		$(function() {
			$(window).resize(function() {
				reloadPage();
			});
		})
	</script>
	
	<script>
		$(function () {
			$(".backnav").find("li").each(function () {
		 		var a = $(this).find("a:first")[0];
		 		if ($(a).attr("href") === location.pathname) {
		     		$(this).addClass("active");
		 		} else {
		    		 $(this).removeClass("active");
		 		}
			});
		});

		$(function () {
			$(".backnavdown").find("div").each(function () {
		 		var a = $(this).find("a")[0];
		 		if ($(a).attr("href") === location.pathname) {
		     		$(a).parent().parent().addClass("active");
		     		$(a).addClass("active");
		 		} else {
		    		 $(this).removeClass("active");
		 		}
			});
		});
		
		
</script>

</body>
</html>