<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.shgm.model.*"%>
<%
	ShgmVO shgmvo = (ShgmVO) request.getAttribute("shgmvo");
%>

<html>
<head>
<meta charset="UTF-8">
<title>檢視市集商品</title>

<style>
table {
	border: 3px solid black;
	text-align: center;
	margin-top: 1%;
}
th, td {
	border: 1px solid black;
	width: 200px;
}
img {
	width: 200px;
	height: 150px;
}
#intro {
	text-align: left;
}
#introbtn {
	margin-top: 10%;
}
#table td {
	padding: 0px;
	vertical-align: middle;
}
#shgmall-mainarea {
	margin: 0 auto;
}
@media ( min-width : 576px) {
	.modal-dialog {
		margin: 1.75rem auto;
	}
}
#detailmodal {
	max-width: 80%;
}
</style>
</head>
<body>
<jsp:include page="/back-end/back-end_nav.jsp"></jsp:include>
	<table id="table" class="table table-striped bg-white">
		<tr>
			<td>市集商品編號</td>
			<td>賣家會員編號</td>
			<td>市集商品名稱</td>
			<td>市集商品價錢</td>
			<td>市集商品圖片</td>
			<td>上架審核狀態</td>
			<td>上架時間</td>
			<td>市集商品詳情</td>
			<td>售出時間</td>
		</tr>
		<tr>
			<td>${shgmvo.shgmno}</td>
			<td>${shgmvo.sellerno}</td>
			<td>${shgmvo.shgmname}</td>
			<td>${shgmvo.price}</td>
			<td class="imgtd"><img
				src="<%=request.getContextPath()%>/shgm/displayimg?shgmno=${shgmvo.shgmno}" /></td>
			<c:choose>
				<c:when test="${shgmvo.upcheck == 0}">
					<td>未審核</td>
				</c:when>
				<c:when test="${shgmvo.upcheck == 1}">
					<td>審核上架</td>
				</c:when>
				<c:otherwise>
					<td>審核下架</td>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${shgmvo.upcheck == 2}">
					<td>本商品已審核下架</td>
				</c:when>
				<c:when test="${shgmvo.uptime == null}">
					<td>本商品尚未上架</td>
				</c:when>
				<c:otherwise>
					<td><fmt:formatDate value="${shgmvo.uptime}"
							pattern="yyyy/MM/dd HH:mm:ss" /></td>
				</c:otherwise>
			</c:choose>
			<td>
				<button id="seldtlbtn" type="button" class="btn btn-primary"
					data-toggle="modal" onclick="showmodal()"
					data-target="#modal1${shgmvo.shgmno}">交易詳情</button>
				<div class="modal fade" id="modal1${shgmvo.shgmno}" tabindex="-1"
					role="dialog" aria-labelledby="${shgmvo.shgmno}ModalLabel1">
					<div id="detailmodal" class="modal-dialog modal-xl">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="${shgmvo.shgmno}ModalLabel1">市集商品交易詳情</h5>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<table style="width: 100%">
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
				<button id="introbtn" type="button" class="btn btn-primary"
					data-toggle="modal" onclick="showmodal()"
					data-target="#modal2${shgmvo.shgmno}">簡介</button>
				<div class="modal fade" id="modal2${shgmvo.shgmno}" tabindex="-1"
					role="dialog" aria-labelledby="${shgmvo.shgmno}ModalLabel2">
					<div class="modal-dialog modal-dialog-scrollable">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="${shgmvo.shgmno}ModalLabel2">市集商品簡介</h5>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div id="intro" class="modal-body">${shgmvo.intro}</div>
						</div>
					</div>
				</div>
			</td>
			<c:choose>
				<c:when test="${shgmvo.upcheck == 2}">
					<td>本商品已審核下架</td>
				</c:when>
				<c:when test="${shgmvo.soldtime == null}">
					<td>本商品尚未售出</td>
				</c:when>
				<c:otherwise>
					<td><fmt:formatDate value="${shgmvo.soldtime}"
							pattern="yyyy/MM/dd HH:mm:ss" /></td>
				</c:otherwise>
			</c:choose>
</body>
</html> 