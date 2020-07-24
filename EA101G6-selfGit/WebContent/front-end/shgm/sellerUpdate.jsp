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
<title>更新市集商品</title>
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
	text-align: center;
	margin-top: 3%;
}

.breadcrumb-nav {
	background-color: #EEEEEE;
}

div.top-info {
	margin: 0 auto;
	border: green 1px solid;
}

.breadcrumb button {
	margin: 0;
}

#imgzoom{
	width: 540px;
	height: 400px;
	display: flex;
    flex-flow: row wrap;
    align-content: flex-end;
    text-align:center;
}

.shgm-info-left img {
	margin: 0;
	height: 320px;
	width: 480px;
	object-fit: contain;
}

.alert {
	color: #FF4500;
}

.shgm-info-right {
	display: table-cell;
	vertical-align: left;
	margin: 3% 0;
	padding-top: 5%;
	text-align: left;
}

.inputtext {
	width:290px;
	margin: 0 auto;
}

.btn {
	margin: 0 1%;
	background-color: white;
}

.button-wrapper{
	width: 125px;
}

.btn:hover {
	background-color: white;
	color: #FF8C00; /*ffa216*/
	box-shadow: 0 0 11px rgba(33, 33, 33, .2);
}

.button-wrapper {
	margin: 0 auto;
	text-align: center;
}

.shgm-info-middle {
    margin: 2%;
}
</style>
<body data-offset="300" background="<%=request.getContextPath() %>/images/bgimage3.jpg">

<jsp:include page="/front-end/front-end-nav.jsp"></jsp:include>

	<div class="main-area container col-10 align-self-center">
		<div class="top-info-wrapper">
			<nav aria-label="breadcrumb" class="breadcrumb-nav">
				<ol class="breadcrumb d-flex">
					<li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/front-end/index.jsp">首頁</a></li>
					<li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/front-end/shgm/mainPage.jsp">市集</a></li>
					<li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/front-end/shgm/sellerPage.jsp">賣家專區</a></li>
					<li class="breadcrumb-item active" aria-current="page">市集商品更新</li>
				</ol>
			</nav>
		</div>
		<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">New message</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<form>
							<div class="form-group">
								<label for="recipient-name" class="col-form-label">Recipient:</label>
								<input type="text" class="form-control" id="recipient-name">
							</div>
							<div class="form-group">
								<label for="message-text" class="col-form-label">Message:</label>
								<textarea class="form-control" id="message-text"></textarea>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Close</button>
						<button type="button" class="btn btn-primary">Send
							message</button>
					</div>
				</div>
			</div>
		</div>
		<div class="shgm-info-allarea">
			<div class="shgm-info-toparea container">
				<form method="post" action="<%=request.getContextPath()%>/front-end/shgm/shgm.do" enctype="multipart/form-data">
					<div id="imgzoom" class="shgm-info-left col-6 rounded float-left">
						<label for="imgfile">
							<img name="imgtag" id="blah" alt="Click here to upload!" class="img-thumbnail rounded float-left" src="<%=request.getContextPath() %>/shgm/displayimg?shgmno=${shgmvo.shgmno}"/>
						</label>
						<div class="uploadwrapper">
							<input type="file" name="img" id="imgfile" onchange="document.getElementById('blah').src = window.URL.createObjectURL(this.files[0])" accept=".png, .jpg, .jpeg .gif"/>
						</div>
						<br>
					</div>
					<div class="shgm-info-right col-6 d-flex justify-content-center">
						<div
							class="shgm-info-right-inner d-flex align-items-center flex-column bd-highlight mb-3">
							<div class="form-group p-2 bd-highlight">
								<label for="shgmname">輸入桌遊名稱</label> <span class="alert">${errormap.get("shgmname")}</span><input name="shgmname"
									class="form-control inputtext" id="shgmname" rows="3" value=${(shgmvo != null)? shgmvo.shgmname:""}>
							</div>
							<div class="form-group p-2 bd-highlight">
								<label for="price">輸入您欲販售之價格</label> <span class="alert">${errormap.get("price")}</span><input name="price"
									class="form-control inputtext" id="price" rows="3" value=${(shgmvo != null)? shgmvo.price:""}>
							</div>
							<div class="button-wrapper">
								<button type="submit" class="btn btn-primary resetBtnCss">修改</button>
								<a href="<%=request.getContextPath()%>/front-end/shgm/sellerPage.jsp" class="btn btn-primary resetBtnCss">取消</a>
							</div>
						</div>
					</div>
					<br> <br> <br>
					<div class="shgm-info-middle">
						輸入此桌遊的詳情<span class="alert">${errormap.get("intro")}</span>
						<div class="card">
							<textarea name="intro">${(shgmvo != null)? shgmvo.intro:""}</textarea>
						</div>
						${errormap.get("error")}
					</div>
					<input type="hidden" name="sellerno" value="${mbrpfVO.mbrno}">
					<input type="hidden" name="shgmno" value="${shgmvo.shgmno}">
					<input type="hidden" name="action" value="sellerUpdate">
				</form>
				<br>
			</div>
		</div>
	</div>
	
<jsp:include page="/front-end/shgm/alert-area.jsp"></jsp:include>

	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jsForShgm/ajaxForMbrmsgs.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jsForShgm/wsForShgm.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jsForShgm/jsForAlert-area.js"></script>

</body>
</html>