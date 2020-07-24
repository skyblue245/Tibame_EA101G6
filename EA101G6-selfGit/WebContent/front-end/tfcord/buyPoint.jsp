<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.util.Date" %>
<%@page import="com.mbrpf.model.*" %>

<%
	java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy");
	//利用SimpleDateFormat定義格式
	
	java.util.Date date = new java.util.Date(System.currentTimeMillis());
	//透過util.Date的System.currentTimeMillis()取得系統當前的時間
	// 	將當前系統時間(ms)傳給sql.Date()
	// 	System.out.println(date); //2020-06-28
	
	String year = df.format(date);
	//並利用DateFormat的format()，將傳進的util.Date轉型成指定格式的字串
	//System.out.println(year); //2020
	
	request.setAttribute("year", year);
%>

<!-- 此處濾器會先看是否有account在session，如果沒有代表沒有登入過，會先進到登入頁面 -->


<!-- 透過account(mbract)藉由checkLogin(mbract)方法，取得會員物件，下方才能取出會員物件的資訊 -->
<%
	String mbract = (String) session.getAttribute("account");
	MbrpfVO mbrpfVO = (MbrpfVO) session.getAttribute("mbrpfVO");
	request.setAttribute("mbrpfVO", mbrpfVO);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/> <!--要有這條 -->
<title>購買點數</title>

<style>
	.pointpic{
		width:100%;
		height:240px;
	}
	
	.orangeBtn {
		background-color: #FF8F00;
		box-shadow: 0px 5px 0px 0px #CD6509;
	}

	.orangeBtn:hover {
	  	background-color: #FF983C;
	}

	.pointBtn {
		border-radius: 5px;
		padding: 5px 5px;
		font-size: 18px;
		text-decoration: none;
		margin: 20px;
		color: black;
		position: relative;
		display: inline-block;
	}

	.pointBtn:active {
	  	transform: translate(0px, 5px);
	  	-webkit-transform: translate(0px, 5px);
	  	box-shadow: 0px 1px 0px 0px;
	}
	
	
	
</style>

</head>

<body>
<jsp:include page="/front-end/front-end-nav.jsp"></jsp:include> 
<%-- <%@ include file="/front-end/front-end-nav.jsp"  %> --%>
<!-- 錯誤列表  -->
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤：</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>


<div class="container">
	<div class="row">
		<img src="<%=request.getContextPath()%>/img/PointBanner.png">
	</div>
	<div class="row float-right">
		<a href="<%=request.getContextPath()%>/front-end/tfcord/tfMoney.jsp" class="pointBtn orangeBtn">前往兌換現金</a>
	</div>
	<br>
	<div class="row style='clear:both'">
		${mbrpfVO.mbrname}，您目前擁有<font style="color:red"><b>${mbrPoint}</b></font>點
	</div>
	<br>
	<form method="post" action="<%=request.getContextPath()%>/tfcord/TfcordServlet">
		<div class="row" style="clear:both">
			<div class="col-sm">
				<label for="p100"><input type="radio" name="price" value="100" id="p100"> 儲值100點 / NT$ 100<br><img class="pointpic" src="<%=request.getContextPath()%>/img/Point/100.png"></label>
			</div>
			<div class="col-sm">
				<label for="p300"><input type="radio" name="price" value="300" id="p300"> 儲值300點 / NT$ 300<br><img class="pointpic" src="<%=request.getContextPath()%>/img/Point/300.png"></label>
			</div>
			<div class="col-sm">
				<label for="p500"><input type="radio" name="price" value="500" id="p500"> 儲值500點 / NT$ 500<br><img class="pointpic" src="<%=request.getContextPath()%>/img/Point/500.png"></label>
			</div>
		</div>
		<div class="row">
			<div class="col-sm">
				<label for="p700"><input type="radio" name="price" value="700" id="p700"> 儲值700點 / NT$ 700<br><img class="pointpic" src="<%=request.getContextPath()%>/img/Point/700.png"></label>
			</div>
			<div class="col-sm">
				<label for="p900"><input type="radio" name="price" value="900" id="p900"> 儲值900點 / NT$ 900<br><img class="pointpic" src="<%=request.getContextPath()%>/img/Point/900.png"></label>
			</div>
			<div class="col-sm">
				<label for="p1000"><input type="radio" name="price" value="1000" id="p1000"> 儲值1000點 / NT$ 1000<br><img class="pointpic" src="<%=request.getContextPath()%>/img/Point/1000.png"></label>
			</div>
		</div>
		<div class="row">
			<div class="col-sm">
<%-- 				<label for="p10000"><input type="radio" name="price" value="10000" id="p10000"> 儲值10000點 / NT$ 10000<br><img class="pointpic" src="<%=request.getContextPath()%>/img/Point/10000.png"></label> --%>
			</div>
			<div class="col-sm">
					<label for="p10000"><input type="radio" name="price" value="10000" id="p10000"> 儲值10000點 / NT$ 10000<br><img class="pointpic" src="<%=request.getContextPath()%>/img/Point/10000.png"></label>
			</div>
			<div class="col-sm">
<%-- 				<label for="p1000"><input type="radio" name="price" value="1000" id="p1000"> 儲值1000點 / NT$ 1000<br><img class="pointpic" src="<%=request.getContextPath()%>/img/Point/1000.png"></label> --%>
			</div>
		</div>
			
		<input type="hidden" name="mbrno" value="${mbrpfVO.mbrno}">
		<input type="hidden" name="tftype" value="P">
		
		<div class="row d-flex justify-content-center" style="background-color:#FF8F00; margin-top:20px; border-top-left-radius:10px; border-top-right-radius:10px;">付款資訊</div>
		
		<div style="background-color:#FFE0B2; margin-right:-15px; margin-left:-15px; border-bottom-right-radius:10px; border-bottom-left-radius:10px;">
			<br>
			<div class="row d-flex justify-content-center">信用卡卡號：</div>
			<div class="row d-flex justify-content-center" style="margin-top:20px;margin-bottom:20px;">
				<input type="text" name="card1" id="card1" size="4" maxlength="4" onKeyUp="next(this, 'card2')">&nbsp;&nbsp;-&nbsp;&nbsp; 
				<input type="text" name="card2" id="card2" size="4" maxlength="4" onKeyUp="next(this, 'card3')">&nbsp;&nbsp;-&nbsp;&nbsp; 
				<input type="text" name="card3" id="card3" size="4" maxlength="4" onKeyUp="next(this, 'card4')">&nbsp;&nbsp;-&nbsp;&nbsp; 
				<input type="text" name="card4" id="card4" size="4" maxlength="4" onKeyUp="next(this)">
			</div>
			<div class="row" style="margin-right:-72px">
				<div class="col-sm d-flex justify-content-end" style="padding-right:40px">有效日期：</div>
				<div class="col-sm d-flex justify-content-start">CCV：</div>
			</div>
			<div class="row d-flex justify-content-center" style="margin-bottom:20px;">
				<div class="col-sm text-right">
					<select size="1">
						<c:forEach var="month" begin="1" end="12" step="1">
							<option value="${month}">${month}月
						</c:forEach>
					</select>
					<select size="1">
						<c:forEach var="year" begin="${year}" end="${year+15}" step="1">
							<option value="${year}">${year}年
						</c:forEach>
					</select>
				</div>
				<div class="col-sm text-left" style="margin-right:-72px">
					<input type="text" name="ccv" id="ccv" size="3" maxlength="3">
				</div>
			</div>
			<br>
		</div>
		<input type="hidden" name="tfstatus" value="1">
		<input type="hidden" name="action" value="addTfcord">
		
		<div class="row d-flex justify-content-center" style="margin-top:5%;margin-bottom:5%">
			<input type="submit" class="pointBtn orangeBtn" value="確認購買" style="border: none">
			<button type="button" id="magic" class="btn btn-outline-warning" style="height: 40px;margin-top: 2%;">神奇小按鈕</button>
		</div>
	</form>
</div>


<!-- 讓信用卡的text 自動往下一個text移動的js -->
<script>  
	function next(obj,next) {  
	    if (obj.value.length == obj.maxLength)  //注意此處maxLength的大小寫  
	        obj.form.elements[next].focus();      
	}  
	
	$(document).ready(function(){
		   	 $("#magic").click(function(){
		     $("#card1").val("4477");
		     $("#card2").val("5487");
		     $("#card3").val("0487");
		     $("#card4").val("9487");
		     $("#ccv").val("987");		     
		   });
		 });
</script>

</body>
</html>