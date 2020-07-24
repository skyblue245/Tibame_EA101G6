<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.shop.model.*"%>
<%@ page import="com.shopad.model.*"%>

<%
	ShopService shopSvc = new ShopService();
	List<ShopVO> list = shopSvc.getAllowedShop();
	pageContext.setAttribute("list", list);
	pageContext.setAttribute("shopSvc", shopSvc);

	ShopadService shopadSvc = new ShopadService();
	List<ShopadVO> list2 = shopadSvc.getAll();
	pageContext.setAttribute("list2", list2);

	ShopadVO shopadVO = (ShopadVO) request.getAttribute("shopadVO");

	ShopVO ssVO = (ShopVO) session.getAttribute("shopAcount");
	pageContext.setAttribute("ssVO", ssVO);
%>


<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">


<title>店家列表</title>

<style>
.icon {
	width: 20px;
	height: 20px;
}

img {
	width: 300px;
	height: 200px;
}

h4 {
	margin-left: 20px;
}

.class {
	margin-left: 200px;
	margin-right: auto;
}

div {
	margin-left: auto;
	margin-right: auto;
}

.hb1-png {
	position: fixed;
	bottom: 30px;
	right: 30px;
	width: 100px;
	height: 100px;
}

footer {
	margin-bottom: 50px;
	position: relative;
}

.absolute {
  position: absolute;
  bottom: 10px;
  left: 10px;
}

</style>
</head>

<body>

	<%@ include file="/front-end/front-end-nav.jsp"%>









	<jsp:include page="select_page.jsp" flush="true" />





	<table>
		<tr style="background-color: #FFFFFF; border: 0px; font:;">
			<td style="background-color: #FFFFFF; border: 0px;">
				<h3>店家列表</h3>
			</td>
		</tr>
	</table>


	<marquee onMouseOver="this.stop()" onMouseOut="this.start()"
		bgcolor=ffcc00 class="col-md-8 offset-md-2">
		<c:forEach var="shopadVO" items="${list2}">
			<input type="hidden"
				value="${shopVO = shopSvc.getOneShop(shopadVO.shopno)}" />
			<c:if test="${shopadVO.status == 1}">
				<a target="_self" style="text-decoration: none;"
					href="<%=request.getContextPath()%>/front-end/shop/shop.do?action=getOne_For_Display&shopno=${shopadVO.shopno}">
					${shopVO.shopname} </a> : ${shopadVO.shopadtt} &nbsp;&nbsp;&nbsp;
			</c:if>
		</c:forEach>
	</marquee>




	<div class="container">
		<div class="card-deck">
			<div class="row">
				<%@ include file="page1.file"%>
				<c:forEach var="shopVO" items="${list}" begin="<%=pageIndex%>"
					end="<%=pageIndex+rowsPerPage-1%>">

					<div class="col-sm-3"
						style="margin-bottom: 20px; margin-left: 20px;">

						<div class="card" style="width: 20rem;height: 25rem;">
							<div class="card-body">
								<label> <img
									src="<%=request.getContextPath()%>/ShopShowImg?shopno=${shopVO.shopno}"
									class="card-img-top" alt="Responsive image">
									<h5 class="card-title">${shopVO.shopname}</h5>
									<p class="card-text">地址:${shopVO.shoploc}</p>
									<FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/front-end/shop/shop.do">
										<input type="hidden" name="shopno" value="${shopVO.shopno}">
										<input type="hidden" name="action" value="getOne_For_Display"><input
											type="submit" value="詳細資訊" class="btn btn-primary absolute">
									</FORM>
								</label>
							</div>
						</div>
					</div>

				</c:forEach>
			</div>
		</div>
	</div>

	<div class="d-flex justify-content-center container"
		style="margin-left: auto; margin-right: auto;">
		<div class="row">
			<div class="col-sm-12">
				<%@ include file="page2.file"%>
			</div>
		</div>
	</div>



	<table>
		<tr style="background-color: #FFFFFF; border: 0px; font:;">
			<td style="background-color: #FFFFFF; border: 0px;"></td>
		</tr>
	</table>








	<!-- 新增廣告按鈕 -->


	<c:if test="${ssVO != null}">
		<!-- Button trigger modal -->
		<img data-toggle="modal" data-target="#exampleModal" class="hb1-png"
			title="Add AD" src="images/hb1.png">


		<!-- Modal -->
		<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content rp-2">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">廣告申請</h5>

					</div>
					<div class="modal-body">
						<div class="form-group">
							<label for="exampleFormControlInput1">店家廣告標題:</label> <input
								type="TEXT" class="form-control" id="shopadtt"
								placeholder="店家標題" name="shopadtt"
								value="<%=(shopadVO == null) ? "" : shopadVO.getShopadtt()%>">
						</div>


						<tabel>
						<tr>
							<td>開始日期: <input type="text" id="startt" name="startt"
								value="<%=(shopadVO == null) ? "" : shopadVO.getStartt()%>" /></td>
						</tr>
						<br>
						<tr>
							<td>結束日期: <input type="text" id="stopt" name="stopt"
								value="<%=(shopadVO == null) ? "" : shopadVO.getStopt()%>" "/></td>
						</tr>
						</tabel>
						<input type="hidden" name="shopno" id="shopno" value="DS00001">
						<input type="hidden" name="status" id="status" value="0">
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">關閉</button>
						<button type="submit" name="action" id="action" value="insert"
							id="demo1" class="btn btn-primary" data-dismiss="modal">送出申請</button>

					</div>
				</div>
			</div>
		</div>
	</c:if>

	<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

	<%
		java.sql.Date startt = null;
		try {
			startt = shopadVO.getStartt();
		} catch (Exception e) {
			startt = new java.sql.Date(System.currentTimeMillis());
		}

		java.sql.Date stopt = null;
		try {
			stopt = shopadVO.getStopt();
		} catch (Exception e) {
			stopt = new java.sql.Date(System.currentTimeMillis());
		}
	%>
	<link rel="stylesheet" type="text/css"
		href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
	<script
		src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

	<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>


	<script>
$(document).ready(function(){
	$("#action").click(function(){
    	if ($("#shopadtt").val() == ''){
    		swal("失敗!", "請輸入廣告標題!", "error");
    	} else{
    		$.ajax({
        		url: "<%=request.getContextPath()%>/shopad/shopad.do",
        		type: "POST",
        		data: { action: $("#action").val(), shopno: $("#shopno").val(), shopadtt: $("#shopadtt").val(), startt: $("#startt").val(), stopt: $("#stopt").val(), status: $("#status").val()},
        		success: function(){
        			swal("Good job!", "申請成功!", "success");
        		}
        	})
    	}
    	
    })
})



 
 
 $.datetimepicker.setLocale('zh');
 $('#startt').datetimepicker({
    theme: '',              //theme: 'dark',
    timepicker:false,       //timepicker:true,
    step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
    format:'Y-m-d',         //format:'Y-m-d H:i:s',
	value: '<%=startt%>', // value:   new Date(),
    //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
    //startDate:	            '2017/07/10',  // 起始日
    minDate:               '-1970-01-01', // 去除今日(不含)之前
    maxDate:               '+1970-01-11'  // 去除今日(不含)之後
 });
 
 $.datetimepicker.setLocale('zh');
 $('#stopt').datetimepicker({
    theme: '',              //theme: 'dark',
    timepicker:false,       //timepicker:true,
    step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
    format:'Y-m-d',         //format:'Y-m-d H:i:s',
	value: '<%=stopt%>', // value:   new Date(),
			//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
			//startDate:	            '2017/07/10',  // 起始日
			minDate : '-1969-11-21', // 去除今日(不含)之前
		//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
		});
	</script>



</body>
</html>