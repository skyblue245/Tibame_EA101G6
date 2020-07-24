<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.shop.model.*"%>
<!DOCTYPE html>
<html>
<meta charset="utf-8">
<head>
<title>���a�n�J</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/loginStyle.css">
<style>
table {
	margin-top: 10px;
}

tr th {
	border: 2px solid black;
	text-align: center;
}

td {
	text-align: center;
}

.icon {
	width: 20px;
	height: 20px;
}

tr:nth-child(odd) {
	background-color: #FFED97;
}

img {
	width: 300px;
	height: 200px;
}

h4 {
	margin-left: 20px;
}
</style>
</head>

<body>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gameing on Board</title>

<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link
	href="https://fonts.googleapis.com/css?family=Rubik:300,400,700|Oswald:400,700"
	rel="stylesheet">
<!-- �n�J�ϥ� -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/fonts/icomoon/style.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/model/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/model/jquery.fancybox.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/model/owl.carousel.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/model/owl.theme.default.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/model/aos.css">
<!-- MAIN CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/model/style.css">

<style>
.icon {
	width: 30px;
	height: 30px;
	margin-right: 3px;
}
.reg1{
	width: 30px;
	height: 30px;
	margin-right: 10px;
}
</style>






</head>
<body>

	<div class="site-wrap" id="home-section">
		<div class="site-mobile-menu site-navbar-target">
			<div class="site-mobile-menu-header">
				<div class="site-mobile-menu-close mt-3">
					<span class="icon-close2 js-menu-toggle"></span>
				</div>
			</div>
			<div class="site-mobile-menu-body"></div>
		</div>
	</div>

	<div class="top-bar">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<div class="float-left">
						<c:if test="${empty shopAcount}">
						<c:choose>
						<c:when test="${mbrpfVO.mbrname != null}">
						<span id="mbrname" class="d-md-inline-block text-white">�w��A�I${mbrpfVO.mbrname}</span>
						</c:when>
						<c:otherwise>
						<a href="<%= request.getContextPath()%>/front-end/mbrpf/addMbrpf.jsp" class="text-white"><span class="d-md-inline-block">
								<img class="icon reg1"
								src="<%=request.getContextPath()%>/images/reg3.png">���U
						</span></a>
						</c:otherwise>
						</c:choose>
						</c:if>
						<c:if test="${not empty shopAcount}">
						<span id="mbrname" class="d-md-inline-block text-white">�w��A�I${shopAcount.shopname}</span>
						<a href="<%=request.getContextPath()%>/front-end/shop/shopArea.jsp" class="text-white"><img
									class="icon"
									src="<%=request.getContextPath()%>/images/shop.png">���a�M��</a>
						</c:if>
					</div>

					<div class="float-right">
						
						<c:choose>
						<c:when test="${mbrpfVO.mbrname != null || shopAcount.shopname != null}">
						<a <c:if test="${mbrpfVO.mbrname != null}"> href="<%= request.getContextPath()%>/mbrpf/mbrpf.do?action=logout"</c:if>
						   <c:if test="${shopAcount.shopname != null}"> href="<%= request.getContextPath()%>/front-end/shop/shop.do?action=logout&requestURL=<%=request.getServletPath()%>"</c:if>
							 id="logout" class="d-md-inline-block text-white"><img class="icon"
								src="<%=request.getContextPath()%>/images/logout.png">�n�X</a>
						</c:when>
						<c:otherwise>
						<c:if test="${empty shopAcount}">
							<a href="<%= request.getContextPath()%>/front-end/login.jsp" class="text-white"><span class="d-md-inline-block">
								<img class="icon"
								src="<%=request.getContextPath()%>/images/ghost.png">�|���n�J
							</span></a>
							<a href="<%=request.getContextPath()%>/front-end/shop/login.jsp"
								class="text-white"> <span class="d-md-inline-block"><img
									class="icon"
									src="<%=request.getContextPath()%>/images/shop.png">���a�n�J</span></a>
						</c:if>					
						<c:if test="${not empty shopAcount}">
							<span class="mx-md-2 d-inline-block"></span>
							<a href="<%=request.getContextPath()%>/front-end/shop/shopArea.jsp" class="text-white"> <span
								class="mr-2 text-white icon-instagram"></span> <span
								class="d-none d-md-inline-block">${shopAcount.shopname}</span></a>
						</c:if>
						
						</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
		</div>
	</div>

	<header class="site-navbar js-sticky-header site-navbar-target"
		role="banner">

		<div class="container">
			<div class="row align-items-center position-relative">


				<div class="site-logo">
					<a href="<%= request.getContextPath()%>/front-end/index.jsp" class="text-black"><span
						class="text-primary">Gameing on Board</span></a>
				</div>

				<div class="col-12">
					<nav class="site-navigation text-right ml-auto " role="navigation">

						<ul
							class="site-menu main-menu js-clone-nav ml-auto d-none d-lg-block">
							<li><a href="<%=request.getContextPath()%>/front-end/index.jsp" class="nav-link">����</a></li>

							<li class="has-children"><a href="" class="nav-link">�|���M��</a>
								<ul class="dropdown arrow-top">
									<li><a href="<%= request.getContextPath()%>/front-end/mallOr/mbrMallOr.jsp" class="nav-link">�d�߭q��</a></li>
									<li><a href="#pricing-section" class="nav-link">Pricing</a></li>
									<li><a href="#faq-section" class="nav-link">FAQ</a></li>
									<li class="has-children"><a href="#">More Links</a>
										<ul class="dropdown">
											<li><a href="#">Menu One</a></li>
											<li><a href="#">Menu Two</a></li>
											<li><a href="#">Menu Three</a></li>
										</ul></li>
								</ul></li>

							<li><a href="<%=request.getContextPath()%>/front-end/mall/mallGetAllUp.jsp" class="nav-link">�ӫ�</a></li>
							<li><a href="<%=request.getContextPath()%>/front-end/shgm/mainPage.jsp" class="nav-link">����</a></li>
							<li class="has-children"><a href="#" class="nav-link">���ΰ�</a>
								<ul class="dropdown arrow-top">
									<li><a href="<%=request.getContextPath()%>/front-end/room/create.jsp" class="nav-link">�ж��C��</a></li>
									<li><a href="<%=request.getContextPath()%>/front-end/room/myRoom.jsp" class="nav-link">�ڪ��ж�</a></li>
								</ul>
							</li>
							<li class="has-children"><a href="<%=request.getContextPath()%>/front-end/shop/listAllShop.jsp" class="nav-link">���a�C��</a>
							<ul class="dropdown arrow-top">
							<li><a href="<%= request.getContextPath()%>/front-end/gmlist/listAllGmlist.jsp" class="nav-link">���a�C��</a></li>
							<li><a href="<%= request.getContextPath()%>/front-end/shopbk/listAllShopbk.jsp" class="nav-link">���a�q��</a></li>
							</ul></li>
							<li><a href="<%=request.getContextPath()%>/front-end/art/listAllArt.jsp" class="nav-link">�Q�װ�</a></li>
						</ul>
					</nav>

				</div>

				<div class="toggle-button d-inline-block d-lg-none">
					<a href="#" class="site-menu-toggle py-5 js-menu-toggle text-black"><span
						class="icon-menu h3"></span></a>
				</div>
			</div>
		</div>
	</header>

	<script
		src="<%=request.getContextPath()%>/js/model/jquery-3.3.1.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/js/model/popper.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/js/model/bootstrap.min.js"></script>
	<!-- ���n�s�i�ɭ� -->
	<script
		src="<%=request.getContextPath()%>/js/model/owl.carousel.min.js"></script>
	<!--...-->
	<script
		src="<%=request.getContextPath()%>/js/model/jquery.sticky.js"></script>
	<script
		src="<%=request.getContextPath()%>/js/model/jquery.waypoints.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/js/model/jquery.animateNumber.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/js/model/jquery.fancybox.min.js"></script>
	<!-- �W�����s���ʵe -->
	<script
		src="<%=request.getContextPath()%>/js/model/jquery.easing.1.3.js"></script>
	<!-- ���n�s�i�ɭ� -->
	<script src="<%=request.getContextPath()%>/js/model/aos.js"></script>
	<script src="<%=request.getContextPath()%>/js/model/main.js"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

		<!-- 	���a�n�Xjs -->
	<script>
		$(document).ready(function() {
			<c:if test="${not empty successMsgs}">
			swal("", "���U���\!�K�X�ЦܫH�c���H", "success");
			</c:if>
			$("#goLogout").click(function() {
				$("#logout").submit();
			})
			
			<c:if test="${not empty errorMsgs}">	
		 	var erromsg="";
			<c:forEach var="erromsg" items="${errorMsgs}">
					erromsg+="${erromsg}\n"
			</c:forEach>
			swal("",erromsg,"error");
			</c:if>
		})
	</script>
</body>
</html>


	<form method="post" action="<%=request.getContextPath()%>/front-end/shop/shop.do">
		<div class="login-form" style="margin-top: 40px;">
			<h1>���a�n�J</h1>
			<div class="form-group ">
				<input type="text" class="form-control" placeholder="�b�� "
					id="UserName" name="account"> <i class="fa fa-user"></i>
			</div>
			<div class="form-group log-status">
				<input type="password" class="form-control" placeholder="�K�X"
					id="Passwod" name="password"> <i class="fa fa-lock"></i>
			</div>
			<span style="font-size: 12px; color: #f00; float: left;"> 
			</span> <input type="submit"
				class="log-btn" value="���a�n�J"> <input type="hidden"
				name="action" value="login">
			
				<button type="button" style="margin-top:10px; background-color:orange;" class="log-btn" onclick="location.href='<%=request.getContextPath()%>/front-end/shop/addShop.jsp'" >���a���U</button>
		</div>
	</form>

	<script
		src='https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
</body>
</html>