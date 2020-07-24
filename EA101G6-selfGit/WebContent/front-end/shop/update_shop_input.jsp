<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.shop.model.*"%>
<%@ include file="/front-end/shop/front-end-nav.jsp"%>
<%
	java.util.HashMap<String, String> hashmap = (java.util.HashMap<String, String>) request
			.getAttribute("cityarea");
	ShopVO shopVO = (ShopVO) request.getAttribute("shopVO");
%>
<html>
<head>
<meta charset="utf-8">

<title>店家修改資料</title>

<style>
table {
	margin-top: 10px;
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

tr:nth-child(odd) {
	background-color: #FFED97;
}

img {
	width: 100%;
	height: 100%;
}
</style>
</head>
<body bgcolor='white'>





	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-1"></div>
			<div class="col-sm-2">
				<div style="witdh: 20px;">
					<ul class="list-group list-group-item-action">
						<li class="list-group-item list-group-item-action"
							onclick="location.href='<%=request.getContextPath()%>/front-end/shop/shopArea.jsp';">我的資訊</li>
						<li class="list-group-item list-group-item-action" id="goGmlist"
							onclick="location.href='<%=request.getContextPath()%>/front-end/gmlist/addGmlist.jsp';">我的遊戲</li>
						<FORM id="gmlist" METHOD="post"
							ACTION="<%=request.getContextPath()%>/front-end/gmlist/gmlist.do">
							<input type="hidden" name="shopno" value="${shopAcount.shopno}">
							<input type="hidden" name="action" value="getSome_For_Display">
						</FORM>
						<li class="list-group-item list-group-item-action" id="goShopbk">我的揪團</li>
						<FORM id="shopbk" METHOD="post"
							ACTION="<%=request.getContextPath()%>/front-end/shopbk/shopbk.do">
							<input type="hidden" name="shopno" value="${shopAcount.shopno}">
							<input type="hidden" name="action" value="getSome_For_Display2">
						</FORM>
						<li class="list-group-item list-group-item-action active"
							id="goUpdate">更改店家資料</li>
						<FORM id="getOne_For_Update" METHOD="post"
							ACTION="<%=request.getContextPath()%>/front-end/shop/shop.do">
							<input type="hidden" name="action" value="getOne_For_Update">
						</FORM>
						<li class="list-group-item list-group-item-action" onclick="location.href='<%=request.getContextPath()%>/front-end/room/shop_roomList.jsp';">我的訂位</li>
					</ul>
				</div>
			</div>
			<div class="col-sm-1"></div>
			<div class="col-sm-5"
				style="text-align: center; width: 500px; height: 50px;">
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/front-end/shop/shop.do"
					name="form1" enctype="multipart/form-data">
					<div class="form-row">
						<div class="col-md-2"></div>
						<div class="form-row">
							<div class="col-md-5">
								<label for="name">店家名稱</label> <input class="form-control"
									type="TEXT" name="shopname" size=100% id="name"
									value="${shopAcount.shopname}" placeholder="name" />
							</div>
							<div class="col-md-7">
								<label for="act">帳號</label> <input class="form-control"
									type="TEXT" name="shopact" size=100% id="act"
									value="${shopAcount.shopact}" placeholder="帳號" />
							</div>
						</div>
						<div class="form-row">
							<div class="col-md-12">
								<label for="inputPw">Password</label> <input type="password"
									size=100% class="form-control" id="inputPw" name="shoppw"
									value="${shopVO==null ? shopAcount.shoppw:shopVO.shoppw}">
							</div>
						</div>

						<div class="form-row">
							<div class="form-group col-md-6">
								<label for="縣市1">City</label> <select name="city" id="縣市1"
									class="form-control">
									<option value="${city.city}">${city.city}</option>
								</select>
							</div>
							<div class="form-group col-md-6">
								<label for="鄉鎮市區1">Area</label> <select name="area" id="鄉鎮市區1"
									class="form-control"></select>
							</div>
						</div>
						<div class="form-row">
							<div class="form-group col-md-12">
								<label for="ads">Address</label> <input type="text" id="ads"
									name="addr" class="address form-control" size=100%
									value="${hashmap == null ? shopAcount.shoploc.substring(9):hashmap.get("addr")}"><input
									id="address" name="address" type="hidden"
									value="<%=(shopVO == null) ? "${shopAcount.shoploc}" : shopVO.getShoploc()%>" />
<%-- 									<%=(hashmap == null) ? "" : hashmap.get("addr")%> --%>
							</div>
						</div>
						<div class="form-row">
							<div class="form-group col-md-4">
								<label for="inputCy">場地</label><input id="inputCy" name="shopcy"
									class="form-control" type="text" size=100%
									value="${shopAcount.shopcy}" placeholder="六人桌*10" />
							</div>
							<div class="form-group col-md-8">
								<label for="inputPhone">電話</label><input id="inputPhone"
									name="shopphone" type="tel" class="form-control" size=100%
									value="0${shopAcount.shopphone}" placeholder="0912345678" />
							</div>
						</div>
						<div class="form-row">
							<div class="form-group col-md-12">
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text" id="inputGroupFileAddon01">Upload</span>
									</div>
									<div class="custom-file">
										<input type="file" class="custom-file-input" id="myFile"
											aria-describedby="myFile" name="shopimg"> <label
											class="custom-file-label" for="myFile" style="width:563px;">Choose
											file</label>
									</div>
								</div>
							</div>
						</div>
						<div class="form-row">
							<div class="form-group col-md-12" id="preview">
<!-- 								<div > -->
									<img
										src="<%=request.getContextPath()%>/ShopShowImg?shopno=${shopAcount.shopno}" />
<!-- 								</div> -->
							</div>
						</div>
					</div>
					<input type="hidden" name="status" value="${shopAcount.status}" /> <br>
					<input type="hidden" name="action" value="update"> <input
						type="hidden" name="shopno" value="${shopAcount.shopno}"> <input
						type="submit" class="btn btn-secondary" value="送出修改"
						style="margin-bottom: 20px;">
				</FORM>
			</div>
		</div>
	</div>
</body>

<!-- 				<td>店家圖片:</td> -->
<!-- 				<td><input type="file" id="myFile" name="shopimg"> -->
<!-- 					<div type="file" id="preview"> -->
<%-- 						<img src="<%=request.getContextPath()%>/ShopShowImg?shopno=${shopVO.shopno}" /> --%>
<!-- 					</div></td> -->

<!-- 				<td><select name="city" id="縣市1"> -->
<%-- 				<option value"${city.city}">${city.city}</option></select> --%>
<!-- 				<select name="area" id="鄉鎮市區1"></select> -->
<%-- 				<input type="text" id="ads" name="addr" class="address" value="<%= (hashmap == null)? "":hashmap.get("addr") %>"></td> --%>
<%-- 				<input id="address" name="address" type="hidden" value="<%= (shopVO == null)? "":shopVO.getShoploc() %>"/> --%>

<script>
	function init() {
		var myFile = document.getElementById("myFile");
		var filename = document.getElementById("filename");
		var preview = document.getElementById('preview');
		myFile.addEventListener('change', function(e) {
			var files = myFile.files;
			if (files !== null && files.length > 0) {
				var file = files[0];
				if (file.type.indexOf('image') > -1) {
					var reader = new FileReader();
					reader.addEventListener('load', function(e) {
						var result = e.target.result;
						console.log(result);
						var img = document.createElement('img');
						img.src = result;
						preview.innerHTML="";
						preview.append(img);
					});
					reader.readAsDataURL(file);
				}
			}
		});
	}
	window.onload = init;
	
	<c:if test="${not empty errorMsgs}">	
 	var erromsg="";
	<c:forEach var="erromsg" items="${errorMsgs}">
			erromsg+="${erromsg}\n"
	</c:forEach>
	swal({text:erromsg });
	</c:if>


   	window.onload = function () {
   		init();
       //當頁面載完之後，用AddressSeleclList.Initialize()，
       //傳入要綁定的縣市下拉選單ID及鄉鎮市區下拉選單ID
   	 AddressSeleclList.Initialize('縣市1', '鄉鎮市區1'<%=(hashmap == null) ? "" : ",'" + hashmap.get("city") + "'"%><%=(hashmap == null) ? "" : ",'" + hashmap.get("area") + "'"%>);
     var addressClass = document.getElementsByClassName("address");
     var address = document.getElementById("address");
     
     for (i = 0; i < addressClass.length; i++) {
  	   addressClass[i].addEventListener("change", addressValues);
  	}
     function addressValues(){
	       var city = document.getElementById("縣市1").value;
	       console.log(city);
	       var area = document.getElementById("鄉鎮市區1").value;
	       console.log(area);
	       var location = document.getElementById("ads").value;
	       console.log(location);
	       address.value = city + area + location;
	       console.log(address.value);
     };
  }
   	 	
</script>
<script>
			$(document).ready(function() {
				$("#goUpdate").click(function() {
					$("#getOne_For_Update").submit();
				})
				$("#goShopbk").click(function() {
					$("#shopbk").submit();
				})
			})
		</script>
<script src="<%=request.getContextPath()%>/js/address2.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</html>