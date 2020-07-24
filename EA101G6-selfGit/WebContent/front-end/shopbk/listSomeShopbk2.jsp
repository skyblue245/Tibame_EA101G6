<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.shopbk.model.*"%>
<%@ include file="/front-end/shopbk/front-end-nav.jsp"%>
<%
	ShopbkService shopbkSvc = new ShopbkService();
	String shopno = ((ShopVO)session.getAttribute("shopAcount")).getShopno();
	List<ShopbkVO> list = shopbkSvc.getShopbkByShop(shopno);
	pageContext.setAttribute("list", list);
	ShopbkVO shopbkVO = (ShopbkVO)request.getAttribute("shopvkVO");
%>
<%
		java.sql.Timestamp start = null;
		try {
			start = shopbkVO.getShoppds();
		} catch (Exception e) {
			start = new java.sql.Timestamp((System.currentTimeMillis()/1800000)*1800000+ (1000 * 60 * 60 * 96));
		}

		java.sql.Timestamp stop = null;
		try {
			stop = shopbkVO.getShoppde();
		} catch (Exception e) {
			stop = new java.sql.Timestamp((System.currentTimeMillis()/1800000)*1800000+ (1000 * 60 * 60 * 100));
		}
	%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">

<style>
table {
	margin-top: 10px;
}

td th tr {
	text-align: center;
}

.icon {
	width: 20px;
	height: 20px;
}

img {
	width: 50px;
	height: 50px;
}

.class {
	margin-left: 200px;
	margin-right: auto;
}

h4 {
	margin-left: 20px;
}

button {
	margin-left: auto;
	margin-right: auto;
}

.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>
</head>

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<body>
<jsp:useBean id="shopSvc" scope="page"
	class="com.shop.model.ShopService" />
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
						<li class="list-group-item list-group-item-action active"
							id="goShopbk">我的揪團</li>
						<FORM id="shopbk" METHOD="post"
							ACTION="<%=request.getContextPath()%>/front-end/shopbk/shopbk.do">
							<input type="hidden" name="shopno" value="${shopAcount.shopno}">
							<input type="hidden" name="action" value="getSome_For_Display2">
						</FORM>
						<li class="list-group-item list-group-item-action" id="goUpdate">更改店家資料</li>
						<FORM id="getOne_For_Update" METHOD="post"
							ACTION="<%=request.getContextPath()%>/front-end/shop/shop.do">
							<input type="hidden" name="action" value="getOne_For_Update">
						</FORM>
						<li class="list-group-item list-group-item-action" onclick="location.href='<%=request.getContextPath()%>/front-end/room/shop_roomList.jsp';">我的訂位</li>
					</ul>
				</div>
			</div>
			<div class="col-sm-8">
				<div>
				
					<div>
						<button id="btn" data-toggle="modal" data-target="#exampleModal"
							class="btn btn-primary btn-lg" <c:if test="${shopSvc.getOneShop(shopAcount.shopno).status!=1}">disabled="disabled"</c:if>>新增</button>
							<c:if test="${shopSvc.getOneShop(shopAcount.shopno).status!=1}">
							<span class="d-inline-block"  data-content="Disabled popover" id="pop">尚未審核，目前無法新增訂位</span>
							</c:if>
					</div>
				
				
					<table class="table table-sm">
						<tr>
							<th scope="col">提供人數</th>
							<th scope="col">遊玩時間</th>
							<th scope="col">以小時計算</th>
							<th scope="col">包日</th>
							<th></th>
						</tr>
						<c:forEach var="shopbkVO" items="${list}">
							<tr>
								<td>${shopbkVO.ofdtable}</td>
								<td><fmt:formatDate value="${shopbkVO.shoppds}" pattern="yyyy-MM-dd HH:mm" />-<fmt:formatDate value="${shopbkVO.shoppde}" pattern="HH:mm" /></td>
								<td>${shopbkVO.payinfohr}</td>
								<td>${shopbkVO.payinfoday}</td>
								<td><button data-toggle="modal" data-target="#updateModal${shopbkVO.shopbkno}"
							class="btn btn-primary btn-lg">修改</button></td>
							</tr>
							<!-- 	=======================================修改============================================ -->
<div class="modal fade" id="updateModal${shopbkVO.shopbkno}" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content rp-2">
				<div class="modal-header"  style="text-align:center;">
				</div>

				<div class="modal-body">
					<div class="form-group">
						<!-- =========================================以下為原listOneEmp.jsp的內容========================================== -->
						<div class="container">
							<FORM id="${shopbkVO.shopbkno}" METHOD="post"
								ACTION="<%=request.getContextPath()%>/front-end/shopbk/shopbk.do">

								<input type="hidden" name="shopno" value="<%=shopno%>" />
								<div class="form-row">
									<div class="form-group col-md-4">
										<label for="4${shopbkVO.shopbkno}">提供人數</label> <input type="TEXT"
											name="ofdtable" class="form-control" id="4${shopbkVO.shopbkno}" value="${shopbkVO.ofdtable}" placeholder="單位:人頭"/>
									</div>
									<div class="form-group col-md-4">
										<label for="5${shopbkVO.shopbkno}">每小時</label> <input type="TEXT"
											name="payinfohr" class="form-control" id="5${shopbkVO.shopbkno}" value="${shopbkVO.payinfohr}" placeholder="每小時/元"/>
									</div>
									<div class="form-group col-md-4">
										<label for="6${shopbkVO.shopbkno}">包日:</label> <input type="TEXT"
											name="payinfoday" class="form-control" id="6${shopbkVO.shopbkno}"  value="${shopbkVO.payinfoday}" placeholder="整天/元"/>
									</div>
								</div>
								<div class="form-row">
									<div class="form-group col-md-12">
										<label for="f_date1">起:</label> <input type="datetime"
											name="shoppds"  id="date1${shopbkVO.shopbkno}"  class="form-control" value="<fmt:formatDate value="${shopbkVO.shoppds}" pattern="yyyy-MM-dd HH:mm" />"/>
									</div>
								</div>
								<div class="form-row">
									<div class="form-group col-md-12">
										<label for="f_date2">迄:</label> <input type="datetime"
											name="shoppde"  id="date2${shopbkVO.shopbkno}" class="form-control" value="<fmt:formatDate value="${shopbkVO.shoppde}" pattern="yyyy-MM-dd HH:mm" />"/>
									</div>
									</div>
								 <input type="hidden" name="shopbkno" value="${shopbkVO.shopbkno}">
								 <input type="hidden" name="action" value="update">
							</FORM>
						</div>
						<!-- =========================================以上為原listOneEmp.jsp的內容========================================== -->
					</div>
				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" id="go${shopbkVO.shopbkno}" class="btn btn-primary">確認修改</button>
				</div>

			</div>
		</div>
	</div>
	<script>
	$('#date1${shopbkVO.shopbkno}').datetimepicker({
		theme : '', //theme: 'dark',
		timepicker : true, //timepicker: false,
		step : 30, //step: 60 (這是timepicker的預設間隔60分鐘)
		format : 'Y-m-d H:i',
<%-- 		value : '<%=start%>', --%>
		//disabledDates:    ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
		//startDate:	        '2017/07/10',  // 起始日
		minDate : '-1969-12-28 00:00:00', // 去除今日(不含)之前
	//maxDate:  '+1970-01-01'  // 去除今日(不含)之後
	});
	$('#date2${shopbkVO.shopbkno}').datetimepicker({
		theme : '', //theme: 'dark',
		timepicker : true, //timepicker: false,
		step : 30, //step: 60 (這是timepicker的預設間隔60分鐘)
		format : 'Y-m-d H:i',
<%-- 		value : '<%=start%>', --%>
		//disabledDates:    ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
		//startDate:	        '2017/07/10',  // 起始日
		minDate : '-1969-12-28 00:00:00', // 去除今日(不含)之前
	//maxDate:  '+1970-01-01'  // 去除今日(不含)之後
	});</script>
	<script>
	$(document).ready(function(){
		$("#go${shopbkVO.shopbkno}").click(function(){
			console.log(1)
			$("#${shopbkVO.shopbkno}").submit();
		})
	})
	
	</script>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</div>
<!-- ===================================新增============================================= -->
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content rp-2">
				<div class="modal-header"  style="text-align:center;">
				</div>

				<div class="modal-body">
					<div class="form-group">
						<!-- =========================================以下為原listOneEmp.jsp的內容========================================== -->
						<div class="container">
							<FORM id="create" METHOD="post"
								ACTION="<%=request.getContextPath()%>/front-end/shopbk/shopbk.do">

								<input type="hidden" name="shopno" value="<%=shopno%>" />
								<%-- 					value="<%=(shopbkVO == null) ? "" : shopbkVO.getShopno()%>"  --%>
								<div class="form-row">
									<div class="form-group col-md-4">
										<label for="1">提供人數</label> <input type="TEXT"
											name="ofdtable" class="form-control" id="1" value="<%=(shopbkVO == null) ? "" : shopbkVO.getOfdtable()%>" placeholder="單位:人頭"/>
									</div>
									<div class="form-group col-md-4">
										<label for="2">每小時</label> <input type="TEXT"
											name="payinfohr" class="form-control" id="2" value="<%=(shopbkVO == null) ? "" : shopbkVO.getPayinfohr()%>" placeholder="每小時/元"/>
									</div>
									<div class="form-group col-md-4">
										<label for="3">包日:</label> <input type="TEXT"
											name="payinfoday" class="form-control" id="3"  value="<%=(shopbkVO == null) ? "" : shopbkVO.getPayinfoday()%>" placeholder="整天/元"/>
									</div>
								</div>
								<div class="form-row">
									<div class="form-group col-md-12">
										<label for="f_date1">起:</label> <input type="datetime"
											name="shoppds" id="f_date1" class="form-control"  value="<%=(shopbkVO == null) ? "" : shopbkVO.getShoppds()%>"/>
									</div>
								</div>
								<div class="form-row">
									<div class="form-group col-md-12">
										<label for="f_date2">迄:</label> <input type="datetime"
											name="shoppde" id="f_date2" class="form-control" value="<%=(shopbkVO == null) ? "" : shopbkVO.getShoppde()%>"/>
									</div>
									</div>
								 <input type="hidden" name="action" value="insert">
							</FORM>
						</div>
						<!-- =========================================以上為原listOneEmp.jsp的內容========================================== -->
					</div>
				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button id="go" type="button" class="btn btn-primary">送出新增</button>
				<img src="images/meow.png" style="width: 50px;
					height: 50px;bottom:0;right:0;" id="magicBtn">		
				</div>

			</div>
		</div>
	</div>
	
	

	<!-- 	=================================datetimepicker============================ -->
	
	
	<script>
		$.datetimepicker.setLocale('en'); // kr ko ja en
		$('#f_date1').datetimepicker({
			theme : '', //theme: 'dark',
			timepicker : true, //timepicker: false,
			step : 30, //step: 60 (這是timepicker的預設間隔60分鐘)
			format : 'Y-m-d H:i',
			value : '<%=start%>',
			//disabledDates:    ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
			//startDate:	        '2017/07/10',  // 起始日
			minDate : '-1969-12-28 00:00:00', // 去除今日(不含)之前
		//maxDate:  '+1970-01-01'  // 去除今日(不含)之後
		});
		$('#f_date2').datetimepicker({
			theme : '', //theme: 'dark',
			timepicker : true, //timepicker: false,
			step : 30, //step: 60 (這是timepicker的預設間隔60分鐘)
			format : 'Y-m-d H:i',
			value : '<%=stop%>',
			//disabledDates:    ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
			//startDate:	        '2017/07/10',  // 起始日
			minDate : '-1969-12-28 00:00:00', // 去除今日(不含)之前
// 			maxDate:           '+1970-01-01'  // 去除今日(不含)之後
		});
	</script>
	
	<script>
	<c:if test="${not empty errorMsgs}">	
 	var erromsg="";
	<c:forEach var="erromsg" items="${errorMsgs}">
			erromsg+="${erromsg}\n"
	</c:forEach>
	swal("",erromsg,"error");
	</c:if>
	</script>
	

	<script>
		$(document).ready(function() {
			<c:if test="${not empty successMsgs}">
			swal("", "${successMsgs}", "success");
			</c:if>
			
			$("#go").click(function() {
				$("#create").submit();
			})
			$("#goUpdate").click(function() {
				$("#getOne_For_Update").submit();
			})
			$("#goShopbk").click(function() {
				$("#shopbk").submit();
			})
	 		 $("#magicBtn").click(function(){
		 	 $("#1").val("16");
		 	 $("#2").val("30");
		     $("#3").val("200");
	   	     $("#f_date1").val('2020-08-09 12:00');
			 $("#f_date2").val('2020-08-09 22:00');
	 		 })
		})
	</script>
</body>
</html>