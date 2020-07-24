<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mbrpf.model.*"%>

<%
  MbrpfVO mbrpfVO = (MbrpfVO) request.getAttribute("mbrpfVO"); //MbrpfServlet.java (Concroller) 存入req的mbrpfVO物件 (包括幫忙取出的mbrpfVO, 也包括輸入資料錯誤時的mbrpfVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>會員資料修改 - update_mbrpf_input.jsp</title>

<style>

.container #mainDiv{
	background-color: rgba(255,255,255,0.7);
} 
 
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
  
</style>

</head>
<body>
<%@ include file="/back-end/back-end_nav.jsp"%>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

	<div class="container">
		<div class="row">

			<div class="col-sm"></div>
			<div class="col-sm" id="mainDiv">
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/back-end/mbrpf/mbrpf.do" name="form1"
					enctype="multipart/form-data">
					<table>
						<tr>
							<td>會員頭像:</td>
		<tr><td><img alt=""src="<%= request.getContextPath()%>/mbrpf/mbrimg.do?mbrno=${mbrpfVO.mbrno}" width="200" height="175" id="demo1"></td></tr>		
							<tr>
							<tr style="display: none">
								<td>會員編號:<font color=red><b>*</b></font></td>
								<td><%=mbrpfVO.getMbrno()%></td>
							</tr>
							
						<td><label><input type="FILE" class="mainF" name="mbrimg" style="display:none"
								onchange="loadFile1(event)" size="30" /><a class="button">上傳圖片</a></label></td>
						</tr>
						</tr><tr>
							<td class="tdtitle">會員帳號</td>
						<tr>
							<td><input type="TEXT" class="mainF" name="mbract" size="45"
								value="<%=mbrpfVO.getMbract()%>" /></td>

						</tr>
						<tr>
							<td class="tdtitle">會員密碼</td>
						<tr>
							<td><input type="PASSWORD" class="mainF" name="mbrpw" size="45"
								value="<%=mbrpfVO.getMbrpw()%>" /></td>
						</tr>
						</tr>
						<tr>
							<td class="tdtitle">會員姓名</td>
						<tr>
							<td><input type="TEXT" class="mainF" name="mbrname" size="45"
								value="<%=mbrpfVO.getMbrname()%>" /></td>
						</tr>
						</tr>
						<tr>
							<td class="tdtitle">
								生日<a style="position: relative; right: -180px;"><font color="black">性別</font></a>
							</td>
						<tr>
							<td>
								<input name="birth" class="mainF" id="f_date1" type="date" value="<%=mbrpfVO.getBirth()%>">
								<a style="position: relative; right: -50px;">
									<input type="radio" name="sex" value="1" <%=mbrpfVO.getSex()==1?"checked":""%>><font color="black">男</font>
									<input type="radio" name="sex" value="2" <%=mbrpfVO.getSex()==2?"checked":""%>><font color="black">女</font>
								</a>
							</td>
						</tr>
						</tr>


						<tr>
							<td class="tdtitle">電子郵件</td>
						<tr>
							<td><input type="TEXT" class="mainF" name="mail" size="45"
								value="<%=mbrpfVO.getMail()%>" /></td>
						</tr>
						</tr>
						<tr>
							<td class="tdtitle">電話</td>
						<tr>
							<td><input type="TEXT" class="mainF" name="phone" size="45"
								value="<%=mbrpfVO.getPhone()%>" /></td>
						</tr>
						</tr>
						<tr>
							<td class="tdtitle">接收款項帳戶</td>
						<tr>
							<td><input type="TEXT" class="mainF" name="mbrac" size="45"
								value="<%=mbrpfVO.getMbrac()%>" /></td>
						</tr>
						</tr>
						<tr>
							<td class="tdtitle">暱稱<a style="position: relative; right: -180px;"><font color="black">狀態</font></a></td>
						<tr>
							<td><input type="TEXT" class="mainF" name="nickname" size="18"
								value="<%=mbrpfVO.getNickname()%>" />
								<a style="position: relative; right: -40px;">
									<input type="radio" name="status" value="1" <%=mbrpfVO.getStatus()==0?"checked":""%>><font color="black">未驗證</font>
									<input type="radio" name="status" value="1" <%=mbrpfVO.getStatus()==1?"checked":""%>><font color="black">正常</font>
									<input type="radio" name="status" value="2" <%=mbrpfVO.getStatus()==2?"checked":""%>><font color="black">停權</font>
								</a>	
							</td>
						</tr>
						</tr>
						<tr style="display: none">
							<td class="tdtitle">點數餘額:</td>
							<td><input type="TEXT" name="points" size="45"
								value="<%=mbrpfVO.getPoints()%>" /></td>
						</tr>
						<tr>
							<td class="tdtitle">會員狀態:</td>
						</tr>
						<tr>
							<td>
								
								</td>
						
						</tr>
						<tr style="display: none">
							<td class="tdtitle">被評價總人數:</td>
							<td><input type="TEXT" name="ratedtotal" size="45"
								value="<%=mbrpfVO.getRatedtotal()%>" /></td>
						</tr>
						<tr style="display: none">
							<td class="tdtitle">被評價總星數:</td>
							<td><input type="TEXT" name="startotal" size="45"
								value="<%=mbrpfVO.getStartotal()%>" /></td>
						</tr>
						<tr style="display: none">
							<td class="tdtitle">未出席次數:</td>
							<td><input type="TEXT" name="unattend" size="45"
								value="<%=mbrpfVO.getUnattend()%>" /></td>
						</tr>
						<tr style="display: none">
							<td class="tdtitle">總參團次數:</td>
							<td><input type="TEXT" name="ttattend" size="45"
								value="<%=mbrpfVO.getTtattend()%>" /></td>
						</tr>
						
<!-- 						<tr><td> -->
<!-- 						<input type="hidden" name="action" value="forUpdate"> -->
<%-- 						<input type="hidden" name="mbrno" value="<%=mbrpfVO.getMbrno()%>"> --%>
<!-- 						<input type="submit" value="送出修改" style="border:0px; outline: none;"> -->
<!-- 						</td></tr> -->
						

					</table>
					<br>
						<input type="hidden" name="action" value="forUpdate">
						<input type="hidden" name="mbrno" value="<%=mbrpfVO.getMbrno()%>">
						<input type="submit" value="送出修改" style="border:0px; outline: none;color: red;"></FORM>
				</FORM>
			</div>

			<div class="col-sm"></div>
		</div>
	</div>




</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>
<script type="text/javascript">
	var loadFile1 = function(event){
		var output = document.getElementById("demo1");
		output.src = URL.createObjectURL(event.target.files[0]);
	};
</script>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '<%=mbrpfVO.getBirth()%>', // value:   new Date(),
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