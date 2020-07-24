<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- contentType 是告訴"瀏覽器"我們專案的編碼方式為何 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>員工管理</title>

<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600">
<!-- https://fonts.google.com/specimen/Open+Sans -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/fontawesome.min.css">
<!-- https://fontawesome.com/ -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<!-- https://getbootstrap.com/ -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/tooplate.css">

<style type="text/css">
    #upimg : hover{
        cursor:pointer;
    }
</style>

<!-- 這邊寫css!!!!!!!!! -->

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
					<button class="navbar-toggler ml-auto mr-0" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    	<ul class="navbar-nav mx-auto">
                    		<li class="nav-item">
                    			<a class="nav-link" href="<%=request.getContextPath()%>/back-end/index.jsp">首頁
                                <span class="sr-only">(current)</span>
                            	</a>
                    		</li>
                    		<li class="nav-item dropdown">
                    			<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    			會員管理
                        		</a>
                        		<div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        			<a class="dropdown-item" href="#">一般會員管理</a>
                        			<a class="dropdown-item" href="#">店家管理</a>
                        		</div>
                    		</li>
                    		<li class="nav-item">
                    			<a class="nav-link" href="<%=request.getContextPath()%>/back-end/products.html">商城管理</a><!-- 目前還沒有這項 -->
                    		</li>
                    		 <li class="nav-item active">
                    		 	<a class="nav-link" href="#">員工管理</a>
                    		 </li>
                    		  <li class="nav-item">
                    		  	<a class="nav-link" href="<%=request.getContextPath()%>/back-end/bazaars.html">市集管理</a><!-- 目前還沒有這項 -->
                    		  </li>
                    		  <li class="nav-item dropdown">
                    		  	<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    		  	檢舉管理
                    		  	</a>
                    		  	<div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    		  		<a class="dropdown-item" href="#">店家檢舉審核</a>
                    		  		<a class="dropdown-item" href="#">會員檢舉審核</a>
                    		  		<a class="dropdown-item" href="#">討論區檢舉審核</a>
                    		  		<a class="dropdown-item" href="#">市集商品檢舉審核</a>
                    		  	</div>
                    		  </li>
                    	</ul>
                    	<ul class="navbar-nav">
                    		<li class="nav-item">
                    			<a class="nav-link d-flex" href="login.html">
                    				<i class="far fa-user mr-2 tm-logout-icon"></i>
                    				<span>Logout</span>
                    			</a>
                    		</li>
                    	</ul>
                    </div>
				</nav>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row tm-content-row emptop">
			<div class="tm-col emp">
				<div class="bg-white tm-block">
					<div class="row test">
						<div class="col test1"><!-- 員工清單的標題 -->
							<h2 class="tm-block-title d-inline-block">員工清單</h2>
						</div>
						<div class="col test2"><!-- 新增員工 -->
							<button id="addEmp">新增員工</button>
						</div>
					</div>
					<div class="table-responsive">
						<table class="table"><!-- listAll.jsp -->
							<thead>
								<tr>
									<th scope="col">照片</th>
				                    <th scope="col">員工編號</th>
				                    <th scope="col">員工姓名</th>
				                    <th scope="col">Email</th>
				                    <th scope="col">員工權限</th>
				                    <th scope="col">員工狀態</th>
				                    <th scope="col">相關修改</th>
                				</tr>
							</thead>
							
							<tbody>
								<tr>
									<td>
										<div class="emp_pic">
											<img src="img/G6_PIC/EMP/1.jpg">
										</div>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	
	
	
	
	
	
	
	
	
	
	<h1>員工管理畫面</h1>
	<h3>查詢資料</h3>

	<!-- --------------------錯誤訊息----------------------- -->
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<!-- --------------------錯誤訊息----------------------- -->

	<!-- 顯示全部員工 -->

	<li><a href="<%=request.getContextPath()%>/back-end/emp/listAllEmp.jsp">顯示全部員工</a></li>
	<br>

	<!-- (透過員工編號)查單一員工 -->
	<form method="post" action="<%=request.getContextPath()%>/emp/EmpServlet" enctype="multipart/form-data">
																				<!-- ↑如果有檔案的傳送，一定要加這條 -->
		<b>請輸入員工編號(ex:LE00001)：</b> <input type="text" name="empno"> 
		<input type="hidden" name="action" value="getOne_For_Display">  <!-- name是給後端傳資料用的，controller用getParameter接 -->
		<input type="submit" value="送出">
	
	</form>
	
	<!-- (透過員工名字)查單一員工 -->
	<form method="post" action="<%=request.getContextPath()%>/emp/EmpServlet" enctype="multipart/form-data">
																				<!-- ↑如果有檔案的傳送，一定要加這條 -->
		<b>請輸入員工姓名：</b> <input type="text" name="empname"> 
		<input type="hidden" name="action" value="getOneName_For_Display">  <!-- name是給後端傳資料用的，controller用getParameter接 -->
		<input type="submit" value="送出">
	
	</form>

<%-- 	<jsp:useBean id="empSvc" scope="page" class="com.emp.model.EmpService" /> --%>

	<!-- 新增員工 -->
	<form method="post" action="<%=request.getContextPath()%>/emp/EmpServlet">

		<ul>
			<li><a href="<%=request.getContextPath()%>/back-end/emp/addEmp.jsp">新增員工</a></li>
		</ul>
		
	</form>
	
	<!-- 員工修改密碼 -->
	<form method="post" action="<%=request.getContextPath()%>/emp/EmpServlet">
		<ul>
			<li>員工修改密碼</li>
		</ul>
		<input type="hidden" name="action" value="changePwd">
		<input type="submit" value="修改密碼">	
	</form>

	<!-- 登出 -->
	<form method="post" action="<%=request.getContextPath()%>/emp/EmpServlet">
		<input type="hidden" name="action" value="logout">
		<input type="submit" value="登出">
	</form>
	
</body>
</html>