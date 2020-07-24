<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.shgm.model.*"%>
<%@ page import="com.mbrpf.model.*"%>
<%
	java.util.HashMap<String, String> hashmap = (java.util.HashMap<String, String>) request.getAttribute("cityarea");
	MbrpfVO mbrpfVO = (MbrpfVO) session.getAttribute("mbrpfVO");
	ShgmVO shgmvo = (ShgmVO) request.getAttribute("shgmvo");
%>
<!doctype html>
<html lang="en">
<head>
<title>更改取貨資訊</title>
<meta charset="utf-8">
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
	width:75%;
}
@media (max-width: 1666px) {
	.awrapper {
		width:60%;
	}
}
@media (max-width: 1080px) {
	.awrapper {
		width:40%;
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
						href="<%=request.getContextPath()%>/front-end/shgm/myShgm.jsp">我的市集商品</a></li>
					<li class="breadcrumb-item active" aria-current="page">修改取貨資訊</li>
				</ol>
			</nav>
		</div>
		<div class="shgm-info-allarea">
			<div class="shgm-info-toparea container">
				<div id="imgzoom" class="shgm-info-left">
					<div
						class="d-flex align-items-left flex-column bd-highlight mb-3">
						<img
							src="<%=request.getContextPath() %>/shgm/displayimg?shgmno=${shgmvo.shgmno}"
							alt="..." class="img-thumbnail rounded float-left"> <br>
						<div class="p-2 bd-highlight">
							名稱
							<h1>${shgmvo.shgmname}</h1>
						</div>
						<div class="p-2 bd-highlight">
							售價
							<h1>${shgmvo.price}</h1>
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
								<input id="ads" name="ads" type="text" class="form-control address" value="<%= (hashmap == null)? "":hashmap.get("ads") %>" style="margin-bottom:15%;"/>
								<input id="address" name="address" type="hidden" value="<%= (shgmvo.getAddress() == null)? "":shgmvo.getAddress() %>"/>
							</div>
							<div class="button-wrapper">
								<button type="submit" class="btn btn-primary resetBtnCss">確定修改</button>
								<a href="<%=request.getContextPath()%>/front-end/shgm/myShgm.jsp" class="btn btn-primary resetBtnCss">取消修改</a>
							</div>
							<input type="hidden" name="shgmno" value="${shgmvo.shgmno}">
							<input type="hidden" name="buyerno" value="${mbrpfVO.mbrno}">
							<input type="hidden" name="action" value="buyerUpdate">
						</form>
						<b><span class="alert">${errormap.get("error")}</span></b>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="random-area"></div>

<jsp:include page="/front-end/shgm/alert-area.jsp"></jsp:include>
	
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jsForShgm/taiwan_address_auto_change.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jsForShgm/ajaxForMbrmsgs.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jsForShgm/wsForShgm.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jsForShgm/jsForAlert-area.js"></script>
	
	<script type="text/javascript">
	window.onload = function () {
	       //當頁面載完之後，用AddressSeleclList.Initialize()，
	       //傳入要綁定的縣市下拉選單ID及鄉鎮市區下拉選單ID
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