<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mbrpf.model.*" %>

<!-- 此處濾器會先看是否有account在session，如果沒有代表沒有登入過，會先進到登入頁面 -->

<!-- 透過account(mbract)藉由checkLogin(mbract)方法，取得會員物件，下方才能取出會員物件的資訊 -->
<%
	String mbract = (String) session.getAttribute("account");
	MbrpfVO mbrpfVO = (MbrpfVO) session.getAttribute("mbrpfVO");
	request.setAttribute("mbrpfVO", mbrpfVO);

//本來以為這裡要set，include才拿的到，但因為listOnetf是include進來的，兩者之間有協同，
//所以include的頁面可以直接從request去getAttribute	
// 	try{
// 		String tfno = (String) request.getAttribute("tfno");
// 		request.setAttribute("tfno", tfno);
// 		System.out.println("最新tfno=" + tfno);
// 	}catch(Exception e){
// 		System.out.print("還沒兌換");
// 	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/> <!--要有這條 -->

<title>轉換現金</title>

<style>
	.orangeBtn {
		background-color: #FF8F00;
		box-shadow: 0px 5px 0px 0px #CD6509;
		outline:none;
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
		outline:none;
	}

	.pointBtn:active {
	  	transform: translate(0px, 5px);
	  	-webkit-transform: translate(0px, 5px);
	  	box-shadow: 0px 1px 0px 0px;
	}
	
	.modal-title{
		color:#ff8b00;
	}
	
	.jump{
		margin-right: 40% !important;
		margin-top: 10% !important;
		
	}

	.jumpBox{
		width: 1200px !important;
	}
</style>
</head>
<body>

<jsp:include page="/front-end/front-end-nav.jsp"></jsp:include> 

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
		<img src="<%=request.getContextPath()%>/img/ChangeMoney.png">
	</div>
	<div class="row float-right">
		<a href="<%=request.getContextPath()%>/front-end/tfcord/buyPoint.jsp" class="pointBtn orangeBtn">前往購買點數</a>
	</div>
	<div style="margin-top:7%; margin-bottom:10%; background-color:#FFE0B2; border-radius:10px;"> <!-- 橘色外框 -->
	<br>
		<form method="post" action="<%=request.getContextPath()%>/tfcord/TfcordServlet">
				<div class="row" style="clear:both; margin-top:1%;">
					<div class="col-sm-4 d-flex justify-content-end">
						<font style="font-size:18px; line-height:80px;">請選擇兌換多少現金</font>
					</div>
					<div class="col-sm-4 d-flex justify-content-start" style="clear:both">
						<select size="1" name="price" style="width:200px; line-height:80px; border-radius:10px; font-size:25px;" onmousedown="if(this.options.length>5){this.size=5;}"  onchange='this.size=0;' onblur="this.size=0;">
		<!-- 				onmousedown="if(this.options.length>5){this.size=5;}"  onchange='this.size=0;' onblur="this.size=0;" 可以將下拉式選單加上滾輪，但位置怪怪的 -->
							<c:forEach var="price" begin="100" end="${mbrPoint}" step="100">
								<option value="${price}">${price}元
							</c:forEach>	
						</select>
					</div>
		
					<input type="hidden" name="tftype" value="M">
					<input type="hidden" name="tfstatus" value="0">
					<input type="hidden" name="mbrno" value="${mbrpfVO.mbrno}">
					<input type ="hidden" name="requestURL" value="<%=request.getServletPath()%>">
					<input type="hidden" name="action" value="addTfMoney" >
					
					<div class="col-sm-4 d-flex justify-content-start">
						<input type="submit" class="pointBtn orangeBtn" value="提領現金" style="border: none; font-size:18px; height:40px;">
					</div>
					
				</div>
			<br>
		</form>
	</div>
	
<c:if test="${openModal!=null}">
	<% session.removeAttribute("openModal"); %> <!-- 此處將openModal remove掉，不然畫面回來會一直顯示listOnetf.jsp -->
	<div class="modal fade" id="basicModal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
		<div class="modal-dialog modal-lg jump">
			<div class="modal-content jumpBox">
				<div class="modal-header">
                	<h3 class="modal-title text-primary " id="myModalLabel"><b>GAMEING ON BOARD</b></h3>
                	<div style="width:130px">
					<a href="<%=request.getContextPath()%>/front-end/tfcord/listOneMbrtf.jsp" style="margin-right: 2%;margin-top: 1%;font-size: 20px; font-weight: bold;">前往帳戶管理</a>
					</div>
				</div>
			
				<div class="modal-body">
<!-- =========================================以下為原listOnetf.jsp的內容========================================== -->				
					<jsp:include page="/front-end/tfcord/listOnetf.jsp" />
<!-- =========================================以上為原listOnetf.jsp的內容========================================== -->				
				</div>
				
				<div class="modal-footer">
					<button type="button" class="pointBtn orangeBtn" style="border: none; font-size:18px; outline:none;" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	
	<script>
 		 $("#basicModal").modal({show: true});
    </script>
       
</c:if>	
	
<!-- 	顯示該會員全部轉換紀錄 -->
<%-- 	<% if (request.getAttribute("mbrpfVO") != null) {%> --%>
<%-- 		<jsp:include page="/front-end/tfcord/listOneMbrtf.jsp" /> --%>
<%-- 	<% }%> --%>

</div>

<%-- 	<form method="post" action="<%=request.getContextPath()%>/tfcord/TfcordServlet"> --%>
		
<!-- 		<select size="1" name="price" style="width:150px"> -->
<!-- <!-- 		onmousedown="if(this.options.length>5){this.size=5;}"  onchange='this.size=0;' onblur="this.size=0;" 可以將下拉式選單加上滾輪，但位置怪怪的--> -->
<%-- 							end這邊之後要換${mbr.point} --%>
<%-- 			<c:forEach var="price" begin="100" end="${mbrpfVO.points}" step="100"> --%>
<%-- 				<option value="${price}">${price}元 --%>
<%-- 			</c:forEach>	 --%>
<!-- 		</select> -->
		
<!-- 		<input type="hidden" name="tftype" value="M"> -->
<!-- 		<input type="hidden" name="tfstatus" value="0"> -->
<%-- 		<input type="hidden" name="mbrno" value="${mbrpfVO.mbrno}"> --%>
<!-- 		<input type="hidden" name="action" value="addTfMoney" > -->
<!-- 		<br><input type="submit" value="提領現金"> -->
<!-- 	</form> -->


</body>
</html>