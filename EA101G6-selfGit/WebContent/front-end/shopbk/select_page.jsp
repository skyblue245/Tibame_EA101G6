<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.shopbk.model.*"%>
<!DOCTYPE html>
<html>
<head>
<title>�j�M</title>


<style>
body {
	background-position: center;
}

table {
	width: 80%;
	margin-top: 10px;
	margin-left: auto;
	margin-right: auto;
}

.icon {
	width: 20px;
	height: 20px;
}

h3 {
	margin-left: auto;
	margin-rghit: auto;
}

ul {
	margin-top: 2px;
	margin-left: auto;
	margin-right: auto;
}

li {
	margin-top: 15px;
	margin-left: auto;
	margin-right: auto;
}
</style>

</head>


<body>
<body bgcolor='white'>
	<jsp:useBean id="shopSvc" scope="page"
		class="com.shop.model.ShopService" />
	<%-- ���~��C --%>
	<%-- 	<c:if test="${not empty errorMsgs}"> --%>
	<!-- 		<font style="color: red">�Эץ��H�U���~:</font> -->
	<!-- 			<ul class="navbar-nav mr-auto"> -->
	<%-- 			<c:forEach var="message" items="${errorMsgs}"> --%>
	<%-- 				<li style="color: red">${message}</li> --%>
	<%-- 			</c:forEach> --%>
	<!-- 		</ul> -->
	<%-- 	</c:if> --%>
	<nav
		class="navbar navbar-expand-lg navbar-light bg-gradient-info shadow p-3 mb-5 rounded">

		<div class="collapse navbar-collapse" id="navbarSupportedContent">

			<ul class="navbar-nav mr-auto">
				<li class="nav-item"><a
					href='<%=request.getContextPath()%>/front-end/shopbk/listAllShopbk.jsp'>List</a>
					all shopbks.</li>
			</ul>
			<ul class="navbar-nav mr-auto">
				<li class="nav-item">
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/front-end/shopbk/shopbk.do">
						<b>��J�Q�n�C�����ɶ� :</b><input
							class="custom-select form-control col-xs-1" type="text"
							name="shoppds" id="f_date1"> <input type="hidden"
							name="action" value="getSome_For_Display"> <input
							class="btn btn-outline-secondary" type="submit" value="�e�X">
					</FORM>
				</li>
			</ul>
			<jsp:useBean id="shopbkSvc" scope="page"
				class="com.shopbk.model.ShopbkService" />
			<ul class="navbar-nav mr-auto">
				<li class="nav-item">
					<FORM METHOD="post" class="form-inline my-2 my-lg-0"
						ACTION="<%=request.getContextPath()%>/front-end/shopbk/shopbk.do">
						<select class="custom-select form-control mr-sm-1" name="shopno">
							<option value="">�Q�h���a����
								<c:forEach var="shopVO" items="${shopSvc.getAllowedShop()}">
									<option value="${shopVO.shopno}">${shopVO.shopname}
								</c:forEach>
						</select> <input type="hidden" name="action" value="getSome_For_Display">
						<button class="btn btn-outline-secondary my-2 my-sm-0"
							type="submit">�e�X</button>
					</FORM>
				</li>
			</ul>

		</div>
	</nav>
</body>
<link rel="stylesheet" type="text/css"
	href="../../datetimepicker/jquery.datetimepicker.css" />
<script src="../../datetimepicker/jquery.js"></script>
<script src="../../datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>
<script>
	$.datetimepicker.setLocale('zh'); // kr ko ja en
	$('#f_date1').datetimepicker({
		theme : '', //theme: 'dark',
		timepicker : true, //timepicker: false,
		step : 30, //step: 60 (�o�Otimepicker���w�]���j60����)
		format : 'Y-m-d H:i',
		value : new Date(),
	//disabledDates:    ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
	//startDate:	        '2017/07/10',  // �_�l��
	// 		minDate : '-1970-01-01', // �h������(���t)���e
	//maxDate:           '+1970-01-01'  // �h������(���t)����
	});
	// 	$('#f_date2').datetimepicker({
	// 		theme : '', //theme: 'dark',
	// 		timepicker : true, //timepicker: false,
	// 		step : 30, //step: 60 (�o�Otimepicker���w�]���j60����)
	// 		format : 'Y-m-d H:i:s',
	// 		value : new Date(),
	// 		//disabledDates:    ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
	// 		//startDate:	        '2017/07/10',  // �_�l��
	// 		minDate : '-1970-01-01', // �h������(���t)���e
	// 	//maxDate:           '+1970-01-01'  // �h������(���t)����
	// 	});
</script>

</html>