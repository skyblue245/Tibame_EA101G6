<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ page import="com.mall.model.*"%>
<%@ page import="com.mallOr.model.*"%>
<%@ page import="com.mallOrDt.model.*"%>
<%@ page import="com.mbrpf.model.*"%>		
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>結帳訂單</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/model/bootstrap.min.css">
	
	

<style>
		
input.mbrName{
	height:24px;
	margin-right:10px;
	width:100px;
}

 div.paydiv{ 
 	text-align:center; 
 } 

 #msform input.paybtn{ 
 	display:inline-block;
	width:20%;
	height:40px;
	vertical-align:middle;
	padding:0px;
	margin-right:30px;
	background-color:#007bff;
	color:#ffffff;
	border:none;
	border-radius: 4px;
 }
 
#msform input.paybtn:hover{ 
	opacity:0.8;
 } 
  
#msform input.cancel{ 
 	display:inline-block;
 	width:20%;
 	height:40px;
 	vertical-align:middle;
 	padding:0px;
 	margin-left:30px;
 	background-color:#007bff;
	color:#ffffff;
	border:none;
	border-radius: 4px;
 	}
 #msform input.cancel:hover{ 
	opacity:0.8;
 	}  

#msform div{
	margin:10px 0px;
}

#msform input.addr{
	width:400px;
	height:25px;
}

.table img{
	width: 40px;
	height:40px;
	border: solid 1px;
	display: inline-block;
	margin:0px 10px; 
}
		
div.orTitle{
	text-align:center;
	margin:10px 0px;
	font-size:18px;
}
	
div.checkdiv{
	text-align:right;
}
	
div.checkdiv p{
	display: inline-block;
	margin:0px 30px;
	font-size: 26px;
}
		

/*form styles*/
#msform {
	width: 630px;
	margin: 20px auto;
	position: relative;
}
#msform fieldset {
	background: white;
	border: 0 none;
	border-radius: 3px;
	box-shadow: 0 0 15px 1px rgba(0, 0, 0, 0.4);
	padding: 20px 30px;
	box-sizing: border-box;
	width: 80%;
	margin: 0 10%;
	
	/*stacking fieldsets above each other*/
	position: relative;
}


</style>


</head>
<body>

<jsp:include page="/front-end/front-end-nav.jsp"/>

<main>
	
	
	<div class="container">
	<div class="orTitle"><h4>訂單明細</h4></div>
		<table class="table table-striped table-bordered">
			<thead class="thead-light">
				<tr>
					<th scope="col">#</th>
					<th scope="col">商品名稱</th>
					<th scope="col">數量</th>
					<th scope="col">價錢</th>
					<th scope="col">小計</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="buyCarMall" items="${buyCarList}" varStatus="count" >
				<tr>
					<th scope="row">${count.count}</th>
					<td class="name"><img src="<%= request.getContextPath()%>/Mall/MallShowImg?commNo=${buyCarMall.commNo}">${buyCarMall.commName}</td>
					<td>${buyCarMall.quantity}</td>
					<td>${buyCarMall.price}</td>
					<td>${buyCarMall.quantity*buyCarMall.price}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<div class="checkdiv">
			<p id="total">總金額:${totalPrice}元</p>
		</div>	
	</div>
	
	
		<%	
			String mbract = (String)session.getAttribute("account");
			MbrpfService mbrpfSvc = new MbrpfService();
			MbrpfVO mbrpfVo=mbrpfSvc.checkLogin(mbract);
			pageContext.setAttribute("mbrpfVo",mbrpfVo);
		%>
		
		
<form id="msform" action="<%= request.getContextPath()%>/MallOr/MallOrServlet" method="post">
  <fieldset>
    <h4 class="fs-title">訂單資訊</h4>
    	<div>
			<label>會員姓名：<input class="mbrName" type="text" value="${mbrpfVo.mbrname}" readonly>
<%-- 			<INPUT NAME="TAKE" TYPE="RADIO" VALUE="超商取貨" ${"超商取貨"==TAKE?"CHECKED":""}>超商取貨 --%>
			取貨方式:<input style="margin-left:3px;" name="take" type="radio" value="住家取貨" checked>住家取貨</label>	
			<label>取貨地點：
			<select name="city" id="縣市2"></select>
			<select name="area" id="鄉鎮市區2"></select>
			</label>
			<input id="addr" type="text" name="addr" class="addr" value="${not empty addr?addr:''}" placeholder="請輸入地址">
			<div><p id="total">總金額：${totalPrice}元</p></div>
		</div>
				<input  type="hidden" name="price" value="${totalPrice}">
				<input type="hidden" name="action" value="checkOut">
    <div class="paydiv"><input type="submit" class="paybtn" value="確定付款"><a href="<%= request.getContextPath()%>/front-end/mall/mallGetAllUp.jsp"><input type="button" class="cancel" value="取消"></a></div>
    	<button id="magicBtn" style="height:20px;padding:0px; width:80px;font-size:12px;" class="btn btn-light" type="button">神奇海螺</button>	
  </fieldset>
</form>
		
	
</main>	
	<script
		src="<%=request.getContextPath()%>/js/model/jquery-3.3.1.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/js/model/popper.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/js/model/bootstrap.min.js"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/js/address.js"></script>	
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
	
	
	<script>
		
		<c:if test="${not empty erroList}">	
		 	let erromsg="";
		 	let pointUrl="<%=request.getContextPath()%>/front-end/tfcord/buyPoint.jsp";
			<c:forEach var="erromsg" items="${erroList}">
					erromsg+="<p>${erromsg}</p>"
			</c:forEach>
				Swal.fire({
					icon: 'error',
					html:erromsg,
					${pointErro?"footer:'<a href='+pointUrl+'>移至點數儲值網頁</a>'":""}
				})
		</c:if>
		
		   window.onload = function () {
		       //當頁面載完之後，用AddressSeleclList.Initialize()，
		       //傳入要綁定的縣市下拉選單ID及鄉鎮市區下拉選單ID
		       //AddressSeleclList.Initialize('縣市1', '鄉鎮市區1');
		       //後面四個參數分別是兩個下拉選單的預設文字跟值
		      AddressSeleclList.Initialize('縣市2', '鄉鎮市區2', '請選擇縣市', '0', '請選擇地區', '0');
		      <c:if test="${not empty erroList}">
		      	AddressSeleclList.erroAdd("${city}","${area}")
		      </c:if> 
		  }

		  $(document).ready(function(){
			  $("#magicBtn").click(function(){
				  $("#addr").val("中大路300號");
				  AddressSeleclList.erroAdd("桃園市","320中壢區");
			
			  })
		  })
		
	</script>

</body>
</html>