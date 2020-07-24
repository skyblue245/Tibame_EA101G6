<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.shgmrp.model.*" %>
<%@ page import="com.shgm.model.*" %>
<%@ page import="java.util.*" %>
<%
	ShgmrpService shgmrpsvc = new ShgmrpService();
	Set<ShgmrpVO> set = shgmrpsvc.getAllShgmrp();
	pageContext.setAttribute("shgmrpset", set);
	ShgmService shgmsvc = new ShgmService();
	pageContext.setAttribute("shgmsvc", shgmsvc);
%>

<html>
<head>
<meta charset="UTF-8">
<title>市集商品檢舉審核</title>

<style>
	.logoutPIC{
		margin-top:18%;
	}
	table{
		border: 1px solid gray;
		text-align: center;
	}
	#intro{
		text-align:left;
	}
	td {
    	border: 1px solid gray;
    	width: 200px;
  	}
  	#table td{
    	padding:0px;
    	vertical-align: middle;
	}
  	img{
  		height: 150px;
  		width: 200px;
  		objtec-fit:contain;
  	}
  	div.pageselect-area {
		display: flex;
		justify-content: center;
	}
	.lefta{
		float: right;
		margin-left:1%;
	}
	#whichpage{
		width: 50px;
		margin:0 1%;
	}
	.right-area{
		width:48%;
		float:left;
	}
	.right-area a,.left-area  a{
		background-color: #9999
	}
	.left-area{
		width:48%;
	}
</style>
</head>
<body>

<jsp:include page="/back-end/back-end_nav-boyuan.jsp"></jsp:include>
<div class="container" style="margin: 0 auto;">
	<jsp:include page="/back-end/shgmrp/shgmrp_select_page.jsp"></jsp:include>
</div>
<ul>
	<c:if test="${not empty errormsgs}">
		<c:forEach var="error" items="${errormsgs}">
			<li>${error}</li>
		</c:forEach>
	</c:if>
</ul>
<div class="container" style="margin: 0 auto;">
<%@ include file="/back-end/shgmrp/page1.file" %> 
	<table id="table" class="table bg-white">
		<tr style="background-color:#e6e6e6;" class="${shgmvrpo.shgmrpno}textRow">
			<td>市集商品檢舉編號</td>
			<td>檢舉人會員編號</td>
			<td>賣家會員編號</td>
			<td>市集商品名稱</td>
			<td>市集商品價錢</td>
			<td>市集商品簡介</td>
			<td>市集商品圖片</td>
			<td>檢舉內容</td>
			<td>審核狀態</td>
		</tr>
		
		<c:forEach var="shgmrpvo" items="${(searchRpResult == null)? shgmrpset:searchRpResult}"  begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr ${(shgmrpvo.shgmrpno == param.shgmrpno)? 'bgcolor=#e6e6e6':''}>
			<td>${shgmrpvo.shgmrpno}</td>
			<td>${shgmrpvo.suiterno}</td>
			<td>${shgmsvc.getOneForInfo(shgmrpvo.shgmno).sellerno}</td>
			<td>${shgmsvc.getOneForInfo(shgmrpvo.shgmno).shgmname}</td>
			<td>${shgmsvc.getOneForInfo(shgmrpvo.shgmno).price}</td>
			<td id="intro"style="width:300px">${shgmsvc.getOneForInfo(shgmrpvo.shgmno).intro}</td>
			<td><img src="<%=request.getContextPath() %>/shgm/displayimg?shgmno=${shgmrpvo.shgmno}"></td>
			<td>${shgmrpvo.detail}</td>
			<td>
			<input class="shgmrpUpdate" type="hidden" value="${shgmvo.shgmno}">
				<select id="${shgmrpvo.shgmrpno}" name="status">
					<c:forEach var="i" begin="0" end="2">
						<option value="${i}" ${(shgmrpvo.status == i)? 'selected':''}>${(i == 0)? "未審查": (i == 1)? "確認檢舉": "取消檢舉"}</option>
					</c:forEach>
				</select>
			<button value="${shgmrpvo.shgmrpno}" class="btn btn-primary upcheckUpdate" style="margin-top:27%;">確認修改</button>
			</td>
		</tr>
		</c:forEach>
	</table>
</div>
	<jsp:include page="/back-end/shgm/alert-area-backend.jsp"></jsp:include>

	<input type="hidden" id="mbrno" value="shgmBackEnd">
	<input type="hidden" id="wsShgmno" value="${param.shgmno}">
	
	<c:if test="${searchRpResult == null}">
		<%@ include file="/back-end/shgmrp/page2.file" %>
	</c:if>
	<c:if test="${setRpsize == 0}">
		<div class="container" style="width:100%; padding:250px; text-align:center; background-color:white;">沒有符合的搜尋結果</div>
	</c:if>

	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jsForShgm/ajaxForMbrmsgs-backend.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jsForShgm/wsForShgm.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jsForShgm/jsForAlert-area.js"></script>
	<script>
	$(document).ready(function(){
		
		$("#findshgmrp").click(function(){
			if($("#word").val().trim() === ''){
				event.preventDefault();
			}
		});
		
		$(".container").on("click",".upcheckUpdate",function(){
			var $shgmrpno = $(this).val();
			var $value = $(this).siblings()[1].value;
			console.log($shgmrpno);
			console.log($value);
			
			$.ajax({
				type: "POST",
				url: "<%=request.getContextPath()%>/front-end/shgm/shgm.do?action=statusUpdate",
				data: {"shgmrpno":$shgmrpno,"shgmrpStatus":$value,"backend":"backendUpdate"},
				dataType: "json",
				cache: false,
				success: function(response){
					webSocket.send(response.shgmno);
					
					Swal.fire({
						  icon: 'success',
						  title: response.alertStr,
						  showConfirmButton: false,
						  timer: 1500
						})
				},
				error:function(result){
					alert("目前不允許此操作");
				}
			});
		});
		
	});
	</script>
</body>
</html>