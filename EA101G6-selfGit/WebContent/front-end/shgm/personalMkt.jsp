<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.shgm.model.*"%>
<%@ page import="com.mbrpf.model.*"%>
<%@ page import="java.util.*"%>
<%
	MbrpfVO mbrpfVO = (MbrpfVO) session.getAttribute("mbrpfVO");
%>
<!doctype html>
<html lang="en">
<head>
<title>${sellerinfo.nickname}的個人市集</title>
<meta charset="utf-8">
</head>
<style>
body {
	background-color: #EEEEEE;
	background-size: repeat;
}

.icon {
	width: 20px;
	height: 20px;
}

div.main-area {
	position: relative;
	display: block;
	border: black 1px solid;
	background-color: white;
	max-height: 100%;
	margin: 2% auto;
}

.top-info-wrapper {
	position: relative;
	text-align: center;
	margin-top: 3%;
}

.breadcrumb-nav {
	background-color: #EEEEEE;
}

.awrapper {
	display: block;
	text-align: right;
	width: 80%;
}
@media (max-width: 1496px) {
	.awrapper {
		width:60%;
	}
}
@media (max-width: 936px) {
	.awrapper {
		width:40%;
	}
}

div.top-info {
	margin: 0 auto;
	border: green 1px solid;
}

.btn {
	margin: 0 1%;
	background-color: white;
}

.btn:hover {
	background-color: white;
	color: #FF8C00; /*ffa216*/
	box-shadow: 0 0 11px rgba(33, 33, 33, .2);
}

.shgm-area-wrapper {
	text-align: center;
}

div.shgm-area {
	margin: auto;
}

.card-deck {
	margin: 0 auto;
}

div.card-body{
	height:127px;
	padding:3% 15px;
}

.card {
	width: 228px;
	float: left;
	margin: 3% 5%;
}

.card:hover {
	cursor: pointer;
	box-shadow: 0 0 11px rgba(33, 33, 33, .2);
}

.card img {
	width: 225px;
	height: 240px;
	object-fit: contain;
}

.pageselect-area-wrapper {
	text-align: center;
}

div.pageselect-area {
	display: block;
	position: relative;
	width: 100%;
	margin: 0 auto;
}

.pagination {
	margin-top: -5%;
	display: flex;
	justify-content: center;
}
</style>
<body data-offset="300" background="<%=request.getContextPath() %>/images/bgimage3.jpg">

<jsp:include page="/front-end/front-end-nav.jsp"></jsp:include>

	<div class="main-area container col-10 align-self-center">
		<div class="top-info-wrapper">
			<nav aria-label="breadcrumb" class="breadcrumb-nav">
				<ol class="breadcrumb d-flex">
					<li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/front-end/index.jsp">首頁</a></li>
					<li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/front-end/shgm/mainPage.jsp">市集</a>
					<li class="breadcrumb-item active" aria-current="page">${sellerinfo.nickname}的個人市集</li>
					<li class="awrapper">
					<a id="myshgm" class="btn btn-primary" href="<%=request.getContextPath()%>/front-end/shgm/myShgm.jsp" role="button">我的市集商品</a>
					<a id="seller" class="btn btn-primary" href="<%=request.getContextPath()%>/front-end/shgm/sellerPage.jsp" role="button">賣家專區</a></li>
				</ol>
			</nav>
		</div>
		<div class="shgm-area-wrapper">
			<div class="shgm-area ">
				<div class="card-deck">
					<c:forEach var="shgmvo" items="${(searchResult == null)? pslset:searchResult}">
						<div class="mb-4">
							<a target="_self" href="<%=request.getContextPath()%>/front-end/shgm/shgm.do?action=getOneForMoreInfo&shgmno=${shgmvo.shgmno}">
								<div class="card">
									<img
										src="<%=request.getContextPath()%>/shgm/displayimg?shgmno=${shgmvo.shgmno}"
										class="card-img-top" alt="Sorry! there's no image...">
									<div class="card-body d-flex flex-column">
										<h5 class="card-title">${shgmvo.shgmname}</h5>
										<p class="card-text mt-auto">$${shgmvo.price}</p>
									</div>
								</div>
							</a>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
		<c:if test="${listsize == 0}">
		<div style="margin:20% 0; width:100%; text-align:center;">很抱歉！並沒有符合的搜尋結果</div>
		</c:if>
	</div>
	<input type="hidden" id="mbrpfVO" value="${mbrpfVO.mbrname}">
	
<jsp:include page="/front-end/shgm/alert-area.jsp"></jsp:include>
	
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jsForShgm/ajaxForMbrmsgs.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jsForShgm/wsForShgm.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jsForShgm/jsForAlert-area.js"></script>

	<script>
	$(document).ready(function(){
		$("#upshgm,#myshgm,#seller").click(function(){
			if($('#mbrpfVO').val() === ''){
				Swal.fire({
					  title: '您尚未登入',
					  icon: 'info',
					  html:'請先登入再執行動作',
					  showCancelButton: true,
					  focusConfirm: false,
					  confirmButtonText:
					    '<a style="color:white;" href="<%=request.getContextPath()%>/front-end/login.jsp" class="fa">前往登入</a>',
					  cancelButtonText:
					    '<span class="fa fa-thumbs-down">繼續逛逛</span>',
					})
				event.preventDefault();
			}
		});
	});
	</script>
</body>
</html>