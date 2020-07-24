<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mbrpf.model.*"%>

<%
	MbrpfVO erroMbrpfVO = (MbrpfVO) request.getAttribute("erroMbrpfVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>新會員註冊 - addMbrpf.jsp</title>

<style>

.mainF{
	border:1px;
	border-bottom-style: solid;
	border-top-style: none;
	border-left-style:none;
	border-right-style:none;
	background-color:transparent;
}
.mainF:focus{
	outline: none;
}

.button{
	display: inline-block;
	max-height:24px;
    text-align: center;
    vertical-align: middle;
    padding: 4px 6px;
    border: 0px solid #ab612e;
    border-radius: 50px;
    background: #ffba82;
    background: -webkit-gradient(linear, left top, left bottom, from(#ffba82), to(#f5c9ab));
    background: -moz-linear-gradient(top, #ffba82, #f5c9ab);
    background: linear-gradient(to bottom, #ffba82, #f5c9ab);
    font: normal normal bold 12px arial;
    color: #ffffff;
    text-decoration: none;
}
.button:hover,
.button:focus {
    border: 0px solid ##d6793a;
    background: #ffdf9c;
    background: -webkit-gradient(linear, left top, left bottom, from(#ffdf9c), to(#fff1cd));
    background: -moz-linear-gradient(top, #ffdf9c, #fff1cd);
    background: linear-gradient(to bottom, #ffdf9c, #fff1cd);
    color: #ffffff;
    text-decoration: none;
}
.button:active {
    background: #99704e;
    background: -webkit-gradient(linear, left top, left bottom, from(#99704e), to(#f5c9ab));
    background: -moz-linear-gradient(top, #99704e, #f5c9ab);
    background: linear-gradient(to bottom, #99704e, #f5c9ab);
}
body {
	background-size: cover;
	background-repeat: no-repeat;
	}

</style>


</head>


<body background="<%= request.getContextPath()%>/front-end/mbrpf/try.jpg">

	<%@ include file="/front-end/front-end-nav.jsp"%>

	<!-- <table  class="t111"> -->
	<!-- 	<tr><td> -->
	<!-- 		 <h3>新會員註冊 - addMbrpf.jsp</h3></td><td> -->
	<%-- 		 <h4><a href="select_page.jsp"><img src="<%=request.getContextPath()%>/images/tomcat.png" width="100" height="100" border="0">回首頁</a></h4> --%>
	<!-- 	</td></tr> -->
	<!-- </table> -->

	<!-- <h3>資料新增:</h3> -->


	<div class="container">
		<div class="row">

			<div class="col-4"></div>
			<div class="col-4">
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/mbrpf/mbrpf.do" name="form1"
					enctype="multipart/form-data">
					<table>
						<tr>
							<td style="text-align:center;">
							<img id="demo1" 
							src="<%=request.getContextPath()+"/front-end/mbrpf/09.jpg"%>">
							</td>
						<tr >
						<td class="tdtitle" style="text-align:center;" ><div>會員頭像</div>
						<label><input type="FILE" class="mainF" name="mbrimg" style="display:none"
								onchange="loadFile1(event)" size="30" /><a class="btn btn-secondary" style="color:#ffffff;padding:4px;">上傳圖片</a></label></td>
						</tr>
						<tr>
							<td class="tdtitle">一般會員帳號</td>
						<tr>
							<td><input type="TEXT" class="mainF" id="tmbract" name="mbract" size="45"
								value="<%=(erroMbrpfVO == null) ? "" : erroMbrpfVO.getMbract()%>" /></td>

						</tr>
						<tr>
							<td class="tdtitle">一般會員密碼</td>
						<tr>
							<td><input type="PASSWORD" class="mainF" id="tmbrpw" name="mbrpw" size="45"
								value="<%=(erroMbrpfVO == null) ? "" : erroMbrpfVO.getMbrpw()%>" /></td>
						</tr>
						<tr>
							<td class="tdtitle">會員姓名</td>
						<tr>
							<td><input type="TEXT" class="mainF" id="tmbrname" name="mbrname" size="45"
								value="<%=(erroMbrpfVO == null) ? "" : erroMbrpfVO.getMbrname()%>" /></td>
						</tr>
						<tr>
							<td class="tdtitle"><a>生日</a><a style="position: relative; right: -180px;">性別</a></td>
						<tr>
							<td><input name="birth" class="mainF" id="f_date1" type="date">
								<a style="position: relative; right: -50px;"><input type="radio" name="sex" value="1"
								 checked="true">男
								<input type="radio" name="sex" value="2">女</a></td>
						</tr>


						<tr>
							<td class="tdtitle">電子郵件</td>
						<tr>
							<td><input type="TEXT" class="mainF" id="temail" name="mail" size="45"
								value="<%=(erroMbrpfVO == null) ? "" : erroMbrpfVO.getMail()%>" /></td>
						</tr>
						<tr>
							<td class="tdtitle">電話</td>
						<tr>
							<td><input type="TEXT" class="mainF" id="tphone" name="phone" size="45"
								value="<%=(erroMbrpfVO == null) ? "" : erroMbrpfVO.getPhone()%>" /></td>
						</tr>
						<tr>
							<td class="tdtitle">接收款項帳戶</td>
						<tr>
							<td><input type="TEXT" class="mainF" id="tmbrac" name="mbrac" size="45"
								value="<%=(erroMbrpfVO == null) ? "" : erroMbrpfVO.getMbrac()%>" /></td>
						</tr>
						<tr>
							<td class="tdtitle">暱稱</td>
						<tr>
							<td><input type="TEXT" class="mainF" id="tnickname" name="nickname" size="45"
								value="<%=(erroMbrpfVO == null) ? "" : erroMbrpfVO.getNickname()%>" /></td>
						</tr>
						<tr style="display: none">
							<td class="tdtitle">點數餘額:</td>
							<td><input type="TEXT" name="points" size="45"
								value="<%=(erroMbrpfVO == null) ? "0" : erroMbrpfVO.getPoints()%>" /></td>
						</tr>
						<tr style="display: none">
							<td class="tdtitle">一般會員狀態:</td>
							<td><input type="TEXT" name="status" size="45"
								value="<%=(erroMbrpfVO == null) ? "1" : erroMbrpfVO.getStatus()%>" /></td>
						</tr>
						<tr style="display: none">
							<td class="tdtitle">被評價總人數:</td>
							<td><input type="TEXT" name="ratedtotal" size="45"
								value="<%=(erroMbrpfVO == null) ? "0" : erroMbrpfVO.getRatedtotal()%>" /></td>
						</tr>
						<tr style="display: none">
							<td class="tdtitle">被評價總星數:</td>
							<td><input type="TEXT" name="startotal" size="45"
								value="<%=(erroMbrpfVO == null) ? "0" : erroMbrpfVO.getStartotal()%>" /></td>
						</tr>
						<tr style="display: none">
							<td class="tdtitle">未出席次數:</td>
							<td><input type="TEXT" name="unattend" size="45"
								value="<%=(erroMbrpfVO == null) ? "0" : erroMbrpfVO.getUnattend()%>" /></td>
						</tr>
						<tr style="display: none">
							<td class="tdtitle">總參團次數:</td>
							<td><input type="TEXT" name="ttattend" size="45"
								value="<%=(erroMbrpfVO == null) ? "0" : erroMbrpfVO.getTtattend()%>" /></td>
						</tr>

					</table>
					<br> <input type="hidden" name="action" value="insert">
					<div style="text-align:center; margin-left:50px;"><input type="submit" value="送出新增"></div>
					<div style="text-align:center; padding-top:10px;">
					<button type="button" class="btn btn-secondary" style="margin-left:50px; color:#ffffff;padding:4px;" id="testman">我是阿明</button>
					</div>
					
				</FORM>
			</div>

			<div class="col-4"></div>
		</div>
	</div>


</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<%
	java.sql.Date birth = null;
	try {
		birth = erroMbrpfVO.getBirth();
	} catch (Exception e) {
		birth = new java.sql.Date(System.currentTimeMillis());
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
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<script type="text/javascript">
	var loadFile1 = function(event){
		var output = document.getElementById("demo1");
		output.src = URL.createObjectURL(event.target.files[0]);
	};
	
	<c:if test="${not empty errorMsgs}">
	let erroStr="";
	<c:forEach var="str" items="${errorMsgs}">
		erroStr+="${str}"+"\n";
	</c:forEach>
	Swal.fire(erroStr);
	</c:if>
	
</script>

<script>
// 給input  date設定預設值
var now = new Date();
//格式化日，如果小於9，前面補0
var day = ("0" + now.getDate()).slice(-2);
//格式化月，如果小於9，前面補0
var month = ("0" + (now.getMonth() + 1)).slice(-2);
//拼裝完整日期格式
var today = now.getFullYear()+"-"+(month)+"-"+(day) ;
//完成賦值
$('#f_date1').val(today);

</script>


<script>

$(document).ready(function(){
	   $("#testman").click(function(){
	     $("#tmbract").val("amin");
	     $("#tmbrpw").val("123456");
	     $("#tmbrname").val("王阿明");
	     $("#temail").val("k9798909@gmail.com");
	     $("#tphone").val("0987654321");
	     $("#tmbrac").val("1234-5678-8765-4321");
	     $("#tnickname").val("阿明");
	   });
	 });

</script>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=birth%>
	', // value:   new Date(),
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	//startDate:	            '2017/07/10',  // 起始日
	//minDate:               '-1970-01-01', // 去除今日(不含)之前
	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});

	// ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

	//      1.以下為某一天之前的日期無法選擇
	//      var somedate1 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      2.以下為某一天之後的日期無法選擇
	//      var somedate2 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
	//      var somedate1 = new Date('2017-06-15');
	//      var somedate2 = new Date('2017-06-25');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//		             ||
	//		            date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});
</script>
</html>