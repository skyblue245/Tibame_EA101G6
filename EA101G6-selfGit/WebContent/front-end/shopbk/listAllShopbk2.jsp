<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.shopbk.model.*"%>

<%
// 	ShopbkService shopbkSvc = new ShopbkService();
//  	List<ShopbkVO> list = shopbkSvc.getAllAfterNow();
// 	pageContext.setAttribute("list", list);
%>

<!doctype html>
<html lang="en">
<head>
<!-- 	<script type="text/javascript" src="https://code.jquery.com/jquery-1.9.1.min.js"></script> -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<title>遊戲列表</title>
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

h4 {
	margin-left: 20px;
}
button{
	margin-left: auto;
	margin-right: auto;
}
</style>
  <!-- Moment.js v2.20.0 -->
  <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.20.0/moment.min.js"></script>
<!-- FullCalendar v3.8.1 -->
<link href="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.8.1/fullcalendar.min.css" rel="stylesheet"  />
<link href="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.8.1/fullcalendar.print.css" rel="stylesheet" media="print"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.8.1/fullcalendar.min.js"></script>
</head>

<body>

<%@ include file="/front-end/shopbk/front-end-nav.jsp" %>


<jsp:include page="select_page.jsp" flush="true"/>

<%-- 	<%-- 錯誤表列 --%>
<%-- 	<c:if test="${not empty errorMsgs}"> --%>
<!-- 		<font style="color: red">請修正以下錯誤:</font> -->
<!-- 		<ul> -->
<%-- 			<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 				<li style="color: red">${message}</li> --%>
<%-- 			</c:forEach> --%>
<!-- 		</ul> -->
<%-- 	</c:if> --%>
<div id="example"></div>

 <script>
 $(document).ready(function() {
  	$( "#example" ).fullCalendar({
  		// 參數設定[註1]
  		header: { // 頂部排版
  			left: "prev,next today", // 左邊放置上一頁、下一頁和今天
  			center: "title", // 中間放置標題
  			right: "month,basicWeek,basicDay" // 右邊放置月、周、天
  		},
  		defaultDate:<fmt:formatDate value="<%=new Date()%>" pattern="yyyy-MM-dd"/>, // 起始日期
  		weekends: false, // 顯示星期六跟星期日
  		editable: true,  // 啟動拖曳調整日期
  		events: [ // 事件
  			
  			{ // 事件(包含開始時間)
  				title: "中餐",
  				start: "2020-07-13T12:00:00"
  			},
  			{ // 事件(包含跨日開始時間、結束時間)
  				title: "音樂節",
  				start: "2018-02-07",
  				end: "2018-02-10"
  			},
  			{ // 事件(包含開始時間、結束時間)
  				title: "會議",
  				start: "2018-02-12T10:30:00",
  				end: "2018-02-12T12:30:00"
  			},
  			{ // 事件(設定連結)
  				title: "Click for Google",
  				url: "http://google.com/",
  				start: "2018-02-28"
  			}
  		]
  	});
 });
  </script>



</body>
</html>