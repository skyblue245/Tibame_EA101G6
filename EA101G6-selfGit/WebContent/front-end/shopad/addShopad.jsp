<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.shopad.model.*"%>

<%
  ShopadVO shopadVO = (ShopadVO) request.getAttribute("shopadVO");
%>  
<%= shopadVO==null %> -- ${shopadVO.shopadno}-- <!--關聯到第100行 -->
<html>
<head>
<link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.9.1.js"></script>
  <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
  <link rel="stylesheet" href="http://jqueryui.com/resources/demos/style.css">

<title>店家廣告新增 - addShopad.jsp</title>

<style>
  table#table-1 {
 background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
  textarea {
   width:33%;
   height:21%;
  }

  table {
 width: 450px;
 background-color: white;
 margin-top: 1px;
 margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>
<link rel="stylesheet" href="./vendors/bootstrap/css/bootstrap.min.css">
</head>
<body bgcolor='white'>

<table id="table-1">
 <tr><td>
  <h3>店家廣告新增 - addShopad.jsp</h3>
  <h4><a href="select_page.jsp">回首頁</a></h4>
 </td></tr>
</table>

<h3>資料薪資:</h3>

<%-- 錯誤列表 --%>
<c:if test="${not empty errorMsgs}">
 <font style="color:red">請修正以下錯誤:</font>
 <ul>
  <c:forEach var="message" items="${errorMsgs}">
   <li style="color:red">${message}</li>
  </c:forEach>
 </ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/shopad/shopad.do" name="form1" >
 
 <tabel>
  <tr>
   <td>店家編號: <input type="TEXT" name="shopno" value="<%=(shopadVO==null)? "" : shopadVO.getShopno()%>"/></td>
  </tr>
 </tabel>
 
 
 <div class="form-group">
     <label for="exampleFormControlInput1">店家廣告標題:</label>
     <input type="TEXT" class="form-control" id="exampleFormControlInput1" placeholder="店家標題" name="shopadtt"  value="<%=(shopadVO==null)? "" : shopadVO.getShopadtt()%>">
   </div>
 
 
 <tabel>
  <tr>
   <td>開始日期: <input type="text" id="saday" name="startt" value="<%=(shopadVO==null)? "" : shopadVO.getStartt()%>" /></td>
  </tr>
  <tr>
   <td>結束日期: <input type="text" id="spday" name="stopt" value="<%=(shopadVO==null)? "" : shopadVO.getStopt()%>" "/></td>
  </tr>
 </tabel>
 
 <input type="hidden" name="status" value="0">

 
 
 
   
   
   
   

<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>


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


<script>
 
 
 $.datetimepicker.setLocale('zh');
 $('#saday').datetimepicker({
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
 $('#spday').datetimepicker({
    theme: '',              //theme: 'dark',
    timepicker:false,       //timepicker:true,
    step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
    format:'Y-m-d',         //format:'Y-m-d H:i:s',
	value: '<%=stopt%>', // value:   new Date(),
    //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
    //startDate:	            '2017/07/10',  // 起始日
    minDate:               '-1969-11-21', // 去除今日(不含)之前
    //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
 });
 
 


</script>


<script src="./vendors/jquery/jquery-3.4.1.min.js"></script>
<script src="./vendors/popper/popper.min.js"></script>
<script src="./vendors/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>