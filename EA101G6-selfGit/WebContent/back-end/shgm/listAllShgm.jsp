<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.shgm.model.*" %>
<%@ page import="java.util.*" %>
<%
	ShgmService shgmsvc = new ShgmService();
	Set<ShgmVO> set = shgmsvc.getAllShgm();
	pageContext.setAttribute("shgmset", set);
%>
<html>
<head>
<meta charset="UTF-8">
<title>市集商品管理</title>
<style>
	.logoutPIC{
		margin-top:18%;
	}
	table{
		border: 1px solid gray;
		text-align: center;
	}
	td {
    	border: 1px solid gray;
    	width: 200px;
  	}
  	#table td{
    	padding:0px;
    	vertical-align: middle;
	}
  	#shgmall-mainarea{
  		margin:0.5% auto;
  	}
  	@media (min-width: 576px){
	  	.modal-dialog {
    	margin: 1.75rem auto;
		}
  	}
  	#detailmodal{
  		max-width: 80%;
  	}
  	#intro{
	  	text-align:left;
  	}
  	#soldtlbtn{
  		margin-top: 10%;
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
  	.imgtd img{
  		width:200px;
  		height:150px;
  		objtec-fit:contain;
  	}
  	.modal-open {
    overflow: scroll;
}
</style>
</head>
<body>

<jsp:include page="/back-end/back-end_nav-boyuan.jsp"></jsp:include>
<div class="container" style="margin: 0 auto;">
	<jsp:include page="/back-end/shgm/shgm_select_page.jsp"></jsp:include>
</div>
<ul>
	<c:if test="${not empty errormsgs}">
		<c:forEach var="error" items="${errormsgs}">
			<li>${error}</li>
		</c:forEach>
	</c:if>
</ul>
<div id="shgmall-mainarea">
<div class="container" style="margin: 0 auto;">
		<%@ include file="/back-end/shgm/page1.file" %> 
	<table id="table" class="table bg-white">
		<tr style="background-color:#e6e6e6;">
			<td>市集商品編號</td>
			<td>賣家會員編號</td>
			<td>市集商品名稱</td>
			<td>市集商品價錢</td>
			<td>市集商品圖片</td>
			<td>上架時間</td>
			<td>售出時間</td>
			<td>市集商品詳情</td>
			<td>審核狀態</td>
		</tr>


		<c:forEach var="shgmvo" items="${(searchResult == null)? shgmset:searchResult}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr ${(shgmvo.shgmno == param.shgmno)? 'bgcolor=#e6e6e6':''} class="${shgmvo.shgmno}textRow">
			<td>${shgmvo.shgmno}</td>
			<td>${shgmvo.sellerno}</td>
			<td>${shgmvo.shgmname}</td>
			<td>${shgmvo.price}</td>
			<td class="imgtd"><img src="<%=request.getContextPath()%>/shgm/displayimg?shgmno=${shgmvo.shgmno}"/></td>
        	<c:choose>
        		<c:when test="${shgmvo.upcheck == 2}">
        			<td>已審核下架</td>
        		</c:when>
        		<c:when test="${shgmvo.uptime == null}">
        			<td>尚未上架</td>
        		</c:when>
        		<c:otherwise>
	        		<td><fmt:formatDate value="${shgmvo.uptime}" pattern="yyyy/MM/dd HH:mm:ss"/></td>
        		</c:otherwise>
        	</c:choose>
        	<c:choose>
        		<c:when test="${shgmvo.upcheck == 2}">
        			<td>已審核下架</td>
        		</c:when>
        		<c:when test="${shgmvo.soldtime == null}">
        			<td>尚未售出</td>
        		</c:when>
        		<c:otherwise>
	        		<td><fmt:formatDate value="${shgmvo.soldtime}" pattern="yyyy/MM/dd HH:mm:ss"/></td>
        		</c:otherwise>
        	</c:choose>
        	<td>
			<button id="introbtn" type="button" class="btn btn-primary" data-toggle="modal" onclick="showmodal()" data-target="#modal2${shgmvo.shgmno}">
			 簡介
			</button>
        	<div class="modal fade" id="modal2${shgmvo.shgmno}" tabindex="-1" role="dialog" aria-labelledby="${shgmvo.shgmno}ModalLabel2">
			  <div class="modal-dialog modal-dialog-scrollable">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h5 class="modal-title" id="${shgmvo.shgmno}ModalLabel2">市集商品簡介</h5>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
			      </div>
			      <div id="intro"class="modal-body">
			        	${shgmvo.intro}
			      </div>
			    </div>
			  </div>
			</div>
			<button id="soldtlbtn" type="button" class="btn btn-primary" data-toggle="modal" onclick="showmodal()" data-target="#modal1${shgmvo.shgmno}">
			  交易詳情
			</button>
        	<div class="modal fade" id="modal1${shgmvo.shgmno}" tabindex="-1" role="dialog" aria-labelledby="${shgmvo.shgmno}ModalLabel1">
			  <div id="detailmodal" class="modal-dialog modal-xl">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h5 class="modal-title" id="${shgmvo.shgmno}ModalLabel1">市集商品交易詳情</h5>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
			      </div>
			      <div class="modal-body">
			        <table style="width:100%">
			        	<tr>
			        		<td>買家會員編號</td>
			        		<td>取貨方式</td>
							<td>取貨人姓名</td>
							<td>取貨人電話</td>
							<td>取貨地址</td>
							<td>出貨狀態</td>
							<td>付款狀態</td>
							<td>訂單狀態</td>
			        	</tr>
			        	<tr>
			        		<td>${(shgmvo.buyerno == null)? "尚未有買家":shgmvo.buyerno}</td>
			        		<td>${(shgmvo.take == null)? "尚無資料":shgmvo.take}</td>
							<td>${(shgmvo.takernm == null)? "尚無資料":shgmvo.takernm}</td>
							<td>${(shgmvo.takerph == null)? "尚無資料":shgmvo.takerph}</td>
							<td>${(shgmvo.address == null)? "尚無資料":shgmvo.address}</td>
							<c:choose>
					            <c:when test="${shgmvo.boxstatus == 0}">
					                <td>未出貨</td>
					            </c:when>
					            <c:when test="${shgmvo.boxstatus == 1}">
					                <td>已出貨</td>
					            </c:when>
					            <c:otherwise>
					                 <td>送達</td>
					            </c:otherwise>
				        	</c:choose>
							<c:choose>
					            <c:when test="${shgmvo.paystatus == 0}">
					                <td>未付款</td>
					            </c:when>
					            <c:otherwise>
					                 <td>已付款</td>
					            </c:otherwise>
				        	</c:choose>
							<c:choose>
					            <c:when test="${shgmvo.status == 0}">
					                <td>未下訂</td>
					            </c:when>
					            <c:when test="${shgmvo.status == 1}">
					                <td>已下訂</td>
					            </c:when>
					            <c:when test="${shgmvo.status == 2}">
					                <td>已完成</td>
					            </c:when>
					            <c:otherwise>
					                 <td>取消</td>
					            </c:otherwise>
				        	</c:choose>
			        	</tr>
			        </table>
			      </div>
			    </div>
			  </div>
			</div>
        	</td>
			<td>
			<input class="shgmUpdate" type="hidden" value="${shgmvo.shgmno}">
				<select id="${shgmvo.shgmno}" name="upcheck">
					<c:forEach var="i" begin="0" end="2">
						<option value="${i}" ${(shgmvo.upcheck == i)? 'selected':'' }>${(i == 0)? "未審核": (i == 1)? "審核上架": "審核下架"}</option>
					</c:forEach>
				</select>
				<button value="${shgmvo.shgmno}" class="btn btn-primary upcheckUpdate" style="margin-top:27%;">確認修改</button>
			</td>
		</tr>
		</c:forEach>
	</table>
</div>
	<jsp:include page="/back-end/shgm/alert-area-backend.jsp"></jsp:include>
	
	<input type="hidden" id="mbrno" value="shgmBackEnd">
	<input type="hidden" id="wsShgmno" value="${param.shgmno}">
</div>
	<c:if test="${searchResult == null}">
		<%@ include file="/back-end/shgm/page2.file" %>
	</c:if>
	<c:if test="${setsize == 0}">
		<div class="container" style="width:100%; padding:250px; text-align:center; background-color:white;">沒有符合的搜尋結果 或是 此商品已被訂購，無法進行審核</div>
	</c:if>
		
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jsForShgm/ajaxForMbrmsgs-backend.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jsForShgm/wsForShgm.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jsForShgm/jsForAlert-area.js"></script>
	<script>
	$(document).ready(function(){
		function showmodal(){
			$(this).modal("show");
		}
		
		$("#findshgm").click(function(){
			console.log($("#word").val());
			if($("#word").val().trim() === ''){
				event.preventDefault();
			}
		});
		
		$("#shgmall-mainarea").on("click",".upcheckUpdate",function(){
			var $shgmno = $(this).val();
			var $value = $(this).siblings()[1].value;
			console.log($shgmno);
			console.log($value);
			
			$.ajax({
				type: "POST",
				url: "<%=request.getContextPath()%>/front-end/shgm/shgm.do?action=statusUpdate",
				data: {"shgmno":$shgmno,"upcheck":$value,"backend":"backendUpdate"},
				dataType: "json",
				cache: false,
				success: function(response){
					//根據dataType辨識出response的資料是json，並把它轉換成Javascript物件
					webSocket.send(response.shgmno);
					
					$("."+response.shgmno+"textRow td:eq(5)").text(response.uptime);
					$("."+response.shgmno+"textRow td:eq(6)").text(response.soldtime);
					
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