<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.shgm.model.*"%>
<%@ page import="com.mbrpf.model.*"%>
<%
	java.util.HashMap<String, String> hashmap = (java.util.HashMap<String, String>) request.getAttribute("cityarea");
	MbrpfVO mbrpfVO = (MbrpfVO) session.getAttribute("mbrpfVO");
	ShgmVO shgmvo = (ShgmVO) session.getAttribute("infoshgm");
%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>${infoshgm.shgmname}</title>
</head>
<style>
body {
	background-color: #EEEEEE;
	background-size: repeat;
	height:980px;
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
	max-height: 750px;
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

.rpdiv {
	color: #FF4500;
	margin-right: 5%;
}


.awrapper {
	display: block;
	text-align: right;
	width: 79%;
}
@media (max-width: 1672px) {
	.awrapper {
		width:70%;
	}
}
@media (max-width: 1150px) {
	.awrapper {
		width:50%;
	}
}
div.top-info {
	margin: 0 auto;
	border: green 1px solid;
}

.breadcrumb button {
	margin: 0;
}

.shgm-info-left img {
	height: 320px;
	width: 480px;
	object-fit: contain;
}

.shgm-info-left {
	width: 45%;
	display: table-cell;
	vertical-align: left;
	margin: 3% 0;
	padding-top: 1.6%;
	text-align: left;
}

.shgm-info-right {
	width: 45%;
	display: table-cell;
	vertical-align: middle;
	margin: 3% 0;
	padding-top: 2%;
	text-align: left;
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

.button-wrapper {
	text-align: center;
}
.alert{
	color: #FF4500;
}
.modal-footer{
	height:70px;
}
</style>


<body data-offset="300" background="<%=request.getContextPath() %>/images/bgimage3.jpg">

<jsp:include page="/front-end/front-end-nav.jsp"></jsp:include>

	<div class="main-area container col-10 align-self-center">
		<div class="top-info-wrapper">
			<nav aria-label="breadcrumb" class="breadcrumb-nav">
				<ol class="breadcrumb d-flex">
					<li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/front-end/index.jsp">首頁</a></li>
					<li class="breadcrumb-item"><a
						href="<%=request.getContextPath()%>/front-end/shgm/mainPage.jsp">市集</a></li>
					<li class="breadcrumb-item"><a
						href="<%=request.getContextPath()%>/front-end/shgm/infoPage.jsp?shgmno=${infoshgm.shgmno}">商品頁面</a></li>
					<li class="breadcrumb-item active" aria-current="page">購買頁面</li>
					<li class="awrapper"><span class="rpdiv">${errormap.get("rp")}</span><button id="rp" type="button"
							class="btn btn-primary" data-toggle="modal"
							data-target="#exampleModal" data-whatever="@mdo">檢舉</button></li>
				</ol>
			</nav>
		</div>
		<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">檢舉此商品</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<form method="post" action="<%=request.getContextPath()%>/front-end/shgm/shgmrp.do">
						<div class="modal-body">
							<div class="form-group">
								<label for="message-text" class="col-form-label">檢舉內容:</label>
								<textarea name="detail" class="form-control" id="message-text"></textarea>
							</div>

						</div>
						<div class="modal-footer">
							<button id="sendRp" type="submit" class="btn btn-primary">確定</button>
							<button id="cancel" type="button" class="btn btn-primary"
								data-dismiss="modal">取消</button>
						</div>
						<input type="hidden" name="shgmno" value="${infoshgm.shgmno}">
						<input type="hidden" name="suiterno" value="${mbrpfVO.mbrno}">
						<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
						<input type="hidden" name="action" value="insertrp">
					</form>
				</div>
			</div>
		</div>
		<div class="shgm-info-allarea">
			<div class="shgm-info-toparea container">
				<div id="imgzoom" class="shgm-info-left ">
					<div
						class="d-flex align-items-left flex-column bd-highlight mb-3">
						<img
							src="<%=request.getContextPath() %>/shgm/displayimg?shgmno=${infoshgm.shgmno}"
							alt="..." class="img-thumbnail rounded float-left"> <br>
						<div class="p-2 bd-highlight">
							名稱
							<h1>${infoshgm.shgmname}</h1>
						</div>
						<div class="p-2 bd-highlight">
							售價
							<h1>${infoshgm.price}</h1>
						</div>
					</div>	
				</div>
				<div class="shgm-info-right justify-content-center">
					<div
						class="shgm-info-right-inner  align-items-center flex-column bd-highlight mb-3">
						<form method="post"
							action="<%=request.getContextPath()%>/front-end/shgm/shgm.do">
							<div class="form-group" style="margin:0;">
								<label for="take">取貨方式</label><span class="alert">${errormap.get("take")}</span><br>
								<label for="1"><input id="1" type="radio" name="take" value="宅配到府" <%=(shgmvo.getTake() == null)? "":shgmvo.getTake().equals("宅配到府")? "checked":""%>>宅配到府</label>
								<label for="2"><input id="2" type="radio" name="take" value="超商取貨" <%=(shgmvo.getTake() == null)? "":shgmvo.getTake().equals("超商取貨")? "checked":""%>>超商取貨</label>
							</div>
							<br>
							<div class="form-group">
								<label for="takernm">取貨人姓名</label><span class="alert">${errormap.get("takernm")}</span>
								<input type="text" class="form-control" id="takernm"
									name="takernm" value="<%=(shgmvo.getTakernm() == null)? "":shgmvo.getTakernm()%>">
							</div>
							<br>
							<div class="form-group">
								<label for="takerph">取貨人電話</label><span class="alert">${errormap.get("takerph")}</span>
								<input type="text" class="form-control" id="takerph"
									name="takerph" value="<%=(shgmvo.getTakerph() == null)? "":shgmvo.getTakerph()%>">
							</div>
							<br>
							<div class="form-group">
								<label for="ads">取貨地址</label><span class="alert">${errormap.get("ads")}</span><br>
								<select id="縣市1" name="city" class="address"></select>
								<select id="鄉鎮市區1" name="area" class="address"></select>
								<input id="ads" name="ads" type="text" class="form-control address" value="<%= (hashmap == null)? "":hashmap.get("ads") %>"  style="margin-bottom:15%;"/>
								<input id="address" name="address" type="hidden" value="<%= (shgmvo.getAddress() == null)? "":shgmvo.getAddress() %>"/>
							</div>
							<div class="button-wrapper">
								<button id="buythis" type="submit" class="btn btn-primary">確定購買</button>
								<a id="backToMain" href="<%=request.getContextPath()%>/front-end/shgm/mainPage.jsp" class="btn btn-primary">取消購買</a>
								<input type="button" class="btn btn-primary resetBtnCss" value="神奇小按鈕" onclick="magicalBtn()">
							</div>
							<input type="hidden" name="shgmno" value="${infoshgm.shgmno}">
							<input type="hidden" name="buyerno" value="${mbrpfVO.mbrno}">
							<input type="hidden" name="action" value="dealingshgm">
						</form>
						<b><span class="alert">${errormap.get("error")}</span></b>
					</div>
				</div>
			</div>
		</div>
	</div>
	<input type="hidden" id="rpsuccess" value="${rpsuccess}">
	
<jsp:include page="/front-end/shgm/alert-area.jsp"></jsp:include>

	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jsForShgm/taiwan_address_auto_change.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jsForShgm/ajaxForMbrmsgs.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jsForShgm/wsForShgm.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jsForShgm/jsForAlert-area.js"></script>
	
	<script>
	
	function magicalBtn(){
		$("input[name='take']")[0].checked = true;
		$("#takernm").val("王阿明");
		$("#takerph").val("0955661314");
		$("#ads").val("中大路300號");
	}
	
	window.onload = function () {
		if($("#rpsuccess").val() == "success"){
			Swal.fire({
				  icon: 'success',
				  title: '您的檢舉已成功送出！',
				  text: '請等待審核',
				  showConfirmButton: false,
				  timer: 1500
				})
		}
	       AddressSeleclList.Initialize('縣市1', '鄉鎮市區1'<%= (hashmap == null)? "":",'"+hashmap.get("city")+"'"%><%= (hashmap == null)? "": ",'"+hashmap.get("area")+"'"%>);
	       var addressClass = document.getElementsByClassName("address");
	       var address = document.getElementById("address");
	       
	       for (i = 0; i < addressClass.length; i++) {
	    	   addressClass[i].addEventListener("change", addressValues);
	    	}
	       function addressValues(){
		       var city = document.getElementById("縣市1").value;
		       var area = document.getElementById("鄉鎮市區1").value;
		       var location = document.getElementById("ads").value;
		       address.value = city + area + location;
		       console.log(address.value);
	       };
	  }
	</script>
</body>
</html>