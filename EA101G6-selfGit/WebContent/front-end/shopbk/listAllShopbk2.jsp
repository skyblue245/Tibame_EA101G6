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
<title>�C���C��</title>
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

<%-- 	<%-- ���~��C --%>
<%-- 	<c:if test="${not empty errorMsgs}"> --%>
<!-- 		<font style="color: red">�Эץ��H�U���~:</font> -->
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
  		// �ѼƳ]�w[��1]
  		header: { // �����ƪ�
  			left: "prev,next today", // �����m�W�@���B�U�@���M����
  			center: "title", // ������m���D
  			right: "month,basicWeek,basicDay" // �k���m��B�P�B��
  		},
  		defaultDate:<fmt:formatDate value="<%=new Date()%>" pattern="yyyy-MM-dd"/>, // �_�l���
  		weekends: false, // ��ܬP������P����
  		editable: true,  // �Ұʩ즲�վ���
  		events: [ // �ƥ�
  			
  			{ // �ƥ�(�]�t�}�l�ɶ�)
  				title: "���\",
  				start: "2020-07-13T12:00:00"
  			},
  			{ // �ƥ�(�]�t���}�l�ɶ��B�����ɶ�)
  				title: "���ָ`",
  				start: "2018-02-07",
  				end: "2018-02-10"
  			},
  			{ // �ƥ�(�]�t�}�l�ɶ��B�����ɶ�)
  				title: "�|ĳ",
  				start: "2018-02-12T10:30:00",
  				end: "2018-02-12T12:30:00"
  			},
  			{ // �ƥ�(�]�w�s��)
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