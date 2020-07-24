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
<script src="<%=request.getContextPath()%>/js/address2.js"></script>
<title>���U���a</title>

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

label {
	text-align: left;
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
</style>
</head>
<body bgcolor='white'>
<div>
	<img src="images/meow.png" style="width: 150px;
	height: 150px;position:fixed;bottom:0;right:0;" id="magicBtn">
</div>

		<div class="container"
			style="text-align: center; width: 500px; height: 50px;">
			<form METHOD="post"
				ACTION="<%=request.getContextPath()%>/front-end/shop/shop.do"
				enctype="multipart/form-data">
				<div class="form-row">
					<div class="col-md-2"></div>
					<div id="preview" style="border-width:3px;border-style:dashed;border-color:#FFAC55;padding:5px;"><img alt="" src="<%= request.getContextPath()%>/NoData/none2.jpg"></div>
				</div>
				<div class="form-row">
					<div class="col-md-5">
						<label for="name">���a�W��</label> <input class="form-control"
							type="TEXT" name="shopname" size=100% id="name"
							value="<%=(shopVO == null) ? "" : shopVO.getShopname()%>"
							placeholder="name" />
					</div>
					<div class="col-md-7">
						<label for="act">�b��</label> <input class="form-control" type="TEXT"
							name="shopact" size=100% id="act"
							value="<%=(shopVO == null) ? "" : shopVO.getShopact()%>"
							placeholder="�b��" />
					</div>
				</div>
				<div class="form-row">
					<div class="col-md-12">
						<label for="inputEmail">Email</label> <input type="email" name="email"
							class="form-control" id="inputEmail" placeholder="Email">
					</div>
				</div>

				<div class="form-row">
					<div class="form-group col-md-6">
						<label for="����1">City</label> <select name="city" id="����1"
							class="form-control">
							<option value="${param.city}">${param.city}</option>
						</select>
					</div>
					<div class="form-group col-md-6">
						<label for="�m����1">Area</label> <select name="area" id="�m����1"
							class="form-control"></select>
					</div>
				</div>
				<div class="form-row">
					<div class="form-group col-md-12">
						<label for="ads">Address</label> <input type="text"
							id="ads" name="addr" class="address form-control"
							value="<%=(hashmap == null) ? "" : hashmap.get("addr")%>"><input
							id="address" name="address" type="hidden"
							value="<%=(shopVO == null) ? "" : shopVO.getShoploc()%>" />
					</div>
				</div>
				<div class="form-row">
					<div class="form-group col-md-4">
						<label for="inputCy">���a</label><input id="inputCy" name="shopcy"
							class="form-control" type="text"
							value="<%=(shopVO == null) ? "" : shopVO.getShopcy()%>"
							placeholder="���H��*10" />
					</div>
					<div class="form-group col-md-8">
						<label for="inputPhone">�q��</label><input id="inputPhone"
							name="shopphone" type="tel" class="form-control"
							value="<%=(shopVO == null) ? "" : shopVO.getShopphone()%>"
							placeholder="0912345678" />
					</div>
				</div>
				<div class="form-group">
					<div class="input-group">
						<div class="input-group-prepend">
							<span class="input-group-text" id="inputGroupFileAddon01">Upload</span>
						</div>
						<div class="custom-file">
							<input type="file" class="custom-file-input" id="myFile"
								aria-describedby="myFile" name="shopimg"> <label
								class="custom-file-label" for="inputGroupFile01">Choose
								file</label>
						</div>
					</div>
				</div>
				<input type="hidden" name="status" value="0">
				<input type="hidden" name="action" value="insert">	
				<button type="submit" class="btn btn-primary">���U</button>		
			</form>				
		</div>
</body>
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
	swal("",erromsg,"error");
	</c:if>


   	window.onload = function () {
   		init();
       //������������A��AddressSeleclList.Initialize()�A
       //�ǤJ�n�j�w�������U�Կ��ID�ζm���ϤU�Կ��ID
   	 AddressSeleclList.Initialize('����1', '�m����1'<%=(hashmap == null) ? "" : ",'" + hashmap.get("city") + "'"%><%=(hashmap == null) ? "" : ",'" + hashmap.get("area") + "'"%>);
     var addressClass = document.getElementsByClassName("address");
     var address = document.getElementById("address");
     
     for (i = 0; i < addressClass.length; i++) {
  	   addressClass[i].addEventListener("change", addressValues);
  	}
     function addressValues(){
	       var city = document.getElementById("����1").value;
	       console.log(city);
	       var area = document.getElementById("�m����1").value;
	       console.log(area);
	       var location = document.getElementById("ads").value;
	       console.log(location);
	       address.value = city + area + location;
	       console.log(address.value);
     };
  }
</script>
<script>
$(document).ready(function(){
	  $("#magicBtn").click(function(){
		  $("#ads").val("���j��300��");
// 		  AddressSeleclList.erroAdd("��饫","320���c��");
		  $("#name").val("���j��C");
		  $("#act").val("SUSU");
		  $("#inputEmail").val("k9798909@gmail.com");
		  $("#inputCy").val("�K�H��*20");
		  $("#inputPhone").val("0912345678");
	  })
})
</script>

<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

</html>