<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.shop.model.*"%>



<!doctype html>
<html lang="en">
<head>
<title>Unearth &mdash; Website Template by Colorlib</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link
	href="https://fonts.googleapis.com/css?family=Rubik:300,400,700|Oswald:400,700"
	rel="stylesheet">
<!-- 登入圖示 -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/fonts/icomoon/style.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/model/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/model/jquery.fancybox.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/model/owl.carousel.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/model/owl.theme.default.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/model/aos.css">
<!-- MAIN CSS -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/model/style.css">

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/mallCss/mallGetAllUpFornt.css">

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

.imgdiv img {
	width: 100%;
	height: 100%;
	margin: 0px auto;
	border: solid #FF5809;
}

div.imgdiv {
	margin: 5px auto;
	width: 90%;
	height: 60%;
}

#accordionExample {
	margin-top: 50px;
}

div.comm {
	height: 250px;
}

.card:hover {
	cursor: pointer;
	box-shadow: 0 0 11px rgba(33, 33, 33, .2);
}
</style>

</head>
<body data-spy="scroll" data-target=".site-navbar-target"
	data-offset="300">

<%
	HttpServletRequest req = (HttpServletRequest)request;
	HttpServletResponse res = (HttpServletResponse)response;
	
	session.setAttribute("location",req.getRequestURI());	
%>


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
						<span id="mbrname" class="d-md-inline-block text-white">歡迎你！${mbrpfVO.mbrname}</span>
						</c:when>
						<c:otherwise>
						<a href="<%= request.getContextPath()%>/front-end/mbrpf/addMbrpf.jsp" class="text-white"><span class="d-md-inline-block">
								<img class="icon reg1"
								src="<%=request.getContextPath()%>/images/reg3.png">註冊
						</span></a>
						</c:otherwise>
						</c:choose>
						</c:if>
						<c:if test="${not empty shopAcount}">
						<span id="mbrname" class="d-md-inline-block text-white">歡迎你！${shopAcount.shopname}</span>
						<a href="<%=request.getContextPath()%>/front-end/shop/shopArea.jsp" class="text-white"><img
									class="icon"
									src="<%=request.getContextPath()%>/images/shop.png">店家專區</a>
						</c:if>
					</div>

					<div class="float-right">
						
						<c:choose>
						<c:when test="${mbrpfVO.mbrname != null || shopAcount.shopname != null}">
						<a <c:if test="${mbrpfVO.mbrname != null}"> href="<%= request.getContextPath()%>/mbrpf/mbrpf.do?action=logout"</c:if>
						   <c:if test="${shopAcount.shopname != null}"> href="<%= request.getContextPath()%>/front-end/shop/shop.do?action=logout&requestURL=<%=request.getServletPath()%>"</c:if>
							 id="logout" class="d-md-inline-block text-white"><img class="icon"
								src="<%=request.getContextPath()%>/images/logout.png">登出</a>
						</c:when>
						<c:otherwise>
						<c:if test="${empty shopAcount}">
							<a href="<%= request.getContextPath()%>/front-end/login.jsp" class="text-white"><span class="d-md-inline-block">
								<img class="icon"
								src="<%=request.getContextPath()%>/images/ghost.png">會員登入
							</span></a>
							<a href="<%=request.getContextPath()%>/front-end/shop/login.jsp"
								class="text-white"> <span class="d-md-inline-block"><img
									class="icon"
									src="<%=request.getContextPath()%>/images/shop.png">店家登入</span></a>
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
					<a href="<%=request.getContextPath()%>/front-end/index.jsp"
						class="text-black"> <span class="text-primary">Gaming
							on Board</span>
					</a>
				</div>

				<div class="col-12">
					<nav class="site-navigation text-right ml-auto " role="navigation">

						<ul
							class="site-menu main-menu js-clone-nav ml-auto d-none d-lg-block">
							<li><a
								href="<%=request.getContextPath()%>/front-end/index.jsp"
								class="nav-link">首頁</a></li>
							<li class="has-children"><a href="#about-section"
								class="nav-link">會員專區</a>
								<ul class="dropdown arrow-top">
									<li><a
										href="<%=request.getContextPath()%>/front-end/mallOr/mbrMallOr.jsp"
										class="nav-link">查詢訂單</a></li>
									<li><a href="<%= request.getContextPath()%>/front-end/tfcord/listOneMbrtf.jsp" class="nav-link">帳戶管理</a></li>
									<li><a href="<%=request.getContextPath()%>/front-end/tfcord/buyPoint.jsp" class="nav-link">購買點數</a></li>
									<li><a href="<%=request.getContextPath()%>/front-end/tfcord/tfMoney.jsp" class="nav-link">兌換現金</a></li>
									<c:choose>
										<c:when test="${mbrpfVO.mbrname != null}">
											<li><a href="<%=request.getContextPath()%>/front-end/mbrpf/listMyMbrpf.jsp" class="nav-link">個人資訊</a></li>																	
										</c:when>
									</c:choose>
								</ul></li>

							<li><a
								href="<%=request.getContextPath()%>/front-end/mall/mallGetAllUp.jsp"
								class="nav-link">商城</a></li>
							<li><a
								href="<%=request.getContextPath()%>/front-end/shgm/mainPage.jsp"
								class="nav-link">市集</a></li>
							<li class="has-children"><a href="#" class="nav-link">揪團區</a>
								<ul class="dropdown arrow-top">
									<li><a
										href="<%=request.getContextPath()%>/front-end/room/create.jsp"
										class="nav-link">房間列表</a></li>
									<li><a
										href="<%=request.getContextPath()%>/front-end/room/myRoom.jsp"
										class="nav-link">我的房間</a></li>
								</ul></li>
							<li><a
								href="<%=request.getContextPath()%>/front-end/shop/listAllShop.jsp"
								class="nav-link">店家列表</a></li>
							<li><a
								href="<%=request.getContextPath()%>/front-end/art/listAllArt.jsp"
								class="nav-link">討論區</a></li>
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

	<div class="owl-carousel slide-one-item">





		<div class="site-section-cover  img-bg-section"
			style="background-image: url('<%=request.getContextPath() %>/images/logo.gif');">
			<div class="container">
				<div
					class="row align-items-center justify-content-center text-center">
					<div class="col-md-12 col-lg-7">
						<h1 data-aos="fade-up" data-aos-delay="">Welcome to Gaming
							on board</h1>
						<p class="mb-5" data-aos="fade-up" data-aos-delay="100">資策會最大桌遊平台上線啦!!</p>
						<p data-aos="fade-up" data-aos-delay="200">
							<a href="#" class="btn btn-outline-white border-w-2 btn-md">Get
								in touch</a>
						</p>
					</div>
				</div>
			</div>

		</div>



		<div class="site-section-cover  img-bg-section"
			style="background-image: url('<%=request.getContextPath() %>/images/game.png');">
			<div class="container">
				<div
					class="row align-items-center justify-content-center text-center">
					<div class="col-md-12 col-lg-8"></div>
				</div>
			</div>

		</div>

		<div class="site-section-cover  img-bg-section"
			style="background-image: url('<%=request.getContextPath() %>/images/game2.png');">
			<div class="container">
				<div
					class="row align-items-center justify-content-center text-center">
					<div class="col-md-12 col-lg-8"></div>
				</div>
			</div>

		</div>


		<div class="site-section-cover overlay img-bg-section"
			style="background-image: url('<%=request.getContextPath() %>/images/game2.png');">
			<div class="container">
				<div
					class="row align-items-center justify-content-center text-center">
					<div class="col-md-12 col-lg-8">
						<h1 data-aos="fade-up" data-aos-delay="">New Generation of
							Mining</h1>
						<p class="mb-5" data-aos="fade-up" data-aos-delay="100">Lorem
							ipsum dolor sit amet, consectetur adipisicing elit. Est odit
							dolorum voluptates!</p>
						<p data-aos="fade-up" data-aos-delay="200">
							<a href="#" class="btn btn-outline-white border-w-2 btn-md">Get
								in touch</a>
						</p>
					</div>
				</div>
			</div>
		</div>


	</div>

	<!-- 最新消息 -->

	<jsp:useBean id="newsSvc" scope="page"
		class="com.news.model.NewsService" />

	<div class="accordion col-md-10 offset-md-1" id="accordionExample">
		<h1>
			<span class="badge badge-light">最新消息</span><span
				class="badge badge-secondary">News</span>
		</h1>
		<c:forEach var="newsVO" items="${newsSvc.all}">
			<div class="card col-md-10 offset-md-1">
				<div class="card-header">
					<h5 class="mb-0">
						<button class="btn btn-link" type="button" data-toggle="collapse"
							data-target="#${newsVO.newsno}" aria-expanded="false"
							aria-controls="${newsVO.newsno}">${newsVO.newstt}</button>
						<span class="d-flex justify-content-end" id="pd">${newsVO.pdate}</span>
					</h5>
				</div>

				<div id="${newsVO.newsno}" class="collapse"
					data-parent="#accordionExample">
					<div class="card-body">${newsVO.detail}</div>
				</div>
			</div>
		</c:forEach>
	</div>


	<!-- 最新商品 -->

	<jsp:useBean id="gmTypeSvc" class="com.gmType.model.GmTypeService"
		scope="request" />
	<jsp:useBean id="mallSvc" class="com.mall.model.MallService"
		scope="request" />
	<div class="accordion col-md-10 offset-md-1">
		<h1>
			<span class="badge badge-light">最新商品</span>
		</h1>
	</div>
	<div id="services-section">
		<div class="container commMain col-10">
			<div class="row">
				<c:forEach var="mallVo" items="${mallSvc.getNew()}">
					<div class="col-lg-2 col-4 comm">
						<a
							href="<%=request.getContextPath()%>/front-end/mall/mallGetOne.jsp?commNo=${mallVo.commNo}">
							<div class="imgdiv">
								<img
									src="<%= request.getContextPath()%>/Mall/MallShowImg?commNo=${mallVo.commNo}">
							</div>
							<p>${mallVo.commName}</p>

							<div class="dt">
								<p>
									<b>$${mallVo.price}</b>
								</p>
							</div>
						</a>
					</div>
				</c:forEach>

			</div>
		</div>

	</div>

<jsp:useBean id="shgmsvc" class="com.shgm.model.ShgmService" scope="request" />

	<div class="site-section" id="press-section">
		<div class="accordion col-md-10 offset-md-1">
		<h1>
			<span class="badge badge-light">市集商品</span>
		</h1>
	</div>
	<div id="services-section">
		<div class="container commMain col-10">
			<div class="row">
				<c:forEach var="ShgmVO" items="${shgmsvc.getAllShuffled()}" begin="0" end="5">
						<div class="mb-4" style="margin:0 auto;">
							<a target="_self" href="<%=request.getContextPath()%>/front-end/shgm/shgm.do?action=getOneForMoreInfo&shgmno=${ShgmVO.shgmno}">
								<div class="card" style="width: 198px;float: left;margin: 3% 5%;">
									<img
										src="<%=request.getContextPath()%>/shgm/displayimg?shgmno=${ShgmVO.shgmno}"
										class="card-img-top" alt="Sorry! there's no image..."style="width: 195px;height: 200px;object-fit: contain;">
									<div class="card-body d-flex flex-column" style="height:128px; padding:3% 15px">
										<h5 class="card-title">${ShgmVO.shgmname}</h5>
										<p class="card-text mt-auto">$${ShgmVO.price}</p>
									</div>
								</div>
							</a>
						</div>
				</c:forEach>
			</div>
		</div>
	</div>
	</div>

	<jsp:useBean id="shopSvc" class="com.shop.model.ShopService"
		scope="request" />
	<div class="accordion col-md-10 offset-md-1">
		<h1>
			<span class="badge badge-light">熱門店家</span>
		</h1>
	</div>
	<div id="services-section" style="margin-left:50px;">
		<div class="container commMain">
			<div class="card-deck">
			<div class="row">
				<c:forEach var="shopVO" items="${shopSvc.getAllowedShop()}" step="2">
					<div class="card col-md-10" style="margin-bottom: 10px;margin-left: 10px;">
						<a
							href="<%=request.getContextPath()%>/front-end/shop/listOneShop.jsp?shopno=${shopVO.shopno}">
<!-- 							<div class="card" style="width: 13rem"> -->
							<div class="card-body">
							<label>
							<img style="width: 150px;height:150px;"
							src="<%=request.getContextPath()%>/ShopShowImg?shopno=${shopVO.shopno}"
							class="card-img-top" alt="Responsive image">
								<h5 class="card-title">${shopVO.shopname}</h5>
								<p class="card-text">地址:${shopVO.shoploc}</p>
							</label>
							</div>					
<!-- 						</div> -->
						</a>
					</div>
				</c:forEach>
				</div>
			</div>
		</div>

	</div>
	
	
	<jsp:include page="/front-end/footer.jsp"/>

	<script
		src="<%=request.getContextPath()%>/js/model/jquery-3.3.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/model/popper.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/model/bootstrap.min.js"></script>
	<!-- 重要廣告界面 -->
	<script
		src="<%=request.getContextPath()%>/js/model/owl.carousel.min.js"></script>
	<!--...-->
	<script src="<%=request.getContextPath()%>/js/model/jquery.sticky.js"></script>
	<script
		src="<%=request.getContextPath()%>/js/model/jquery.waypoints.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/js/model/jquery.animateNumber.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/js/model/jquery.fancybox.min.js"></script>
	<!-- 上介面連結動畫 -->
	<script
		src="<%=request.getContextPath()%>/js/model/jquery.easing.1.3.js"></script>
	<!-- 重要廣告界面 -->
	<script src="<%=request.getContextPath()%>/js/model/aos.js"></script>
	<script src="<%=request.getContextPath()%>/js/model/main.js"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<!-- 	聊天 -->
	<c:if test="${mbrpfVO != null}">
		<jsp:include page="/front-end/frontChat/frontChat.jsp"/>
	</c:if>
	


	<c:if test="${not empty successMsgs}">
	<script>swal("註冊成功！","","success")</script>
	</c:if>



	
	
	<!-- 	店家登出js -->
	<script>
		$(document).ready(function() {
			$("#goLogout").click(function() {
				$("#logout").submit();
			})
		})
	</script>
</body>
</html>