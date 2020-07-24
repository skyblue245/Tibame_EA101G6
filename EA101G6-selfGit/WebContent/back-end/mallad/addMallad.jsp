<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mallad.model.*"%>

<%
  MalladVO malladVO = (MalladVO) request.getAttribute("malladVO");
%>  

<html>
<head>
<link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.9.1.js"></script>
  <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
  <link rel="stylesheet" href="http://jqueryui.com/resources/demos/style.css">

<title>商城廣告新增 - addMallad.jsp</title>

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
  .category-1{
   	margin-top:80px;
   	margin-bottom:20px;
   	
   }
   .add-1{
   	margin-top: 50px;
   	margin-bottom: 100px;
   }
   .img-1{
   	width: 100%;
   	margin-bottom: 25px;
   }
   .ta1{
   	margin-top: 40px;
   	margin-bottom: 40px;
   }
   #prePic{
   	width: 100%;
   	height: 200px;
   	margin-top: 25px;
   	
   }	   
   .logoutPIC{
   	margin-top: 18%;
   }
   .errorM{
   	margin-top: 40px;
   }
</style>
<link rel="stylesheet" href="./vendors/bootstrap/css/bootstrap.min.css">
</head>
<body bgcolor='white'>

<%@ include file="/back-end/back-end_nav.jsp"%>




	<div class="col-md-10 offset-md-1 errorM">
		<%-- 錯誤列表 --%>
		<c:if test="${not empty errorMsgs}">
			<font class="float-left" style="color: red">請修正以下錯誤:</font>
			<ul class="float-left">
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
	</div>


	<div class="bg-white tm-block col-md-10 offset-md-1 add-1">
		<div class="row">
			<div class="col-12">
				<h2 class="tm-block-title d-inline-block">Add Mallad</h2>
			</div>
		</div>
		
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/mallad/mallad.do" name="form1" enctype="multipart/form-data">
		<div class="row mt-4 tm-edit-product-row">
			<div class="col-xl-7 col-lg-7 col-md-12">			
					
					<div class="input-group mb-3">
						<label for="category" class="col-xl-4 col-lg-4 col-md-4 col-sm-5 col-form-label">商品名稱</label>
						<jsp:useBean id="mallSvc" scope="page" class="com.mall.model.MallService"/>
						<select size="1" name="commno" class="custom-select col-xl-9 col-lg-8 col-md-8 col-sm-7">
							<c:forEach var="mallVO" items="${mallSvc.all}">
								<option value="${mallVO.commNo}"
									${(mallVO.commNo==malladVO.commno)? 'selected':'' }>${mallVO.commName}
							</c:forEach>
						</select>
						
						

					</div>
					
					<div class="input-group mb-3">
						<label for="name"
							class="col-xl-4 col-lg-4 col-md-4 col-sm-5 col-form-label">商城廣告標題</label> 
							<input type="text"  name="gmadtt"  value="<%= (malladVO==null)? "" : malladVO.getGmadtt()%>" class="form-control validate col-xl-9 col-lg-8 col-md-8 col-sm-7"/>
					</div>

					<div class="input-group mb-3 category-1">
						<tabel>
						<tr>
							<td>開始日期: <input type="text" id="saday" name="startt"
								value="<%=(malladVO == null) ? "" : malladVO.getStartt()%>" /></td>
						</tr>
						<tr>
							<td>結束日期: <input type="text" id="spday" name="stopt"
								value="<%=(malladVO==null)? "" : malladVO.getStopt()%>"
								min="${malladVO.startt}" /></td>
						</tr>
						</tabel>
						<div class="ml-auto col-xl-2 col-lg-2 col-md-2 col-sm-1 pl-0 ">
							<button type="submit" class="btn btn-primary">Add</button>
							<input type="hidden" name="action" value="insert">
						</div>
					</div>





				</div>
				<div class="col-xl-4 col-lg-4 col-md-12 mx-auto mb-4">

					<input type="file" name="detail" id="upPic"
						class="btn btn-primary d-block mx-auto" /> <img id="prePic"
						src="<%=request.getContextPath()%>/mallad/malladpic.do?malladno=${malladVO.malladno}" />

				</div>
			</div>
		</FORM>
	</div>



		





<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<% 
  java.sql.Date startt = null;
  try {
	    startt = malladVO.getStartt();
   } catch (Exception e) {
	    startt = new java.sql.Date(System.currentTimeMillis());
   }
  
  java.sql.Date stopt = null;
  try {
	    stopt = malladVO.getStopt();
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
 $("#upPic").change(function(){
  readURL(this);
 });
 
 function readURL(input){
  if(input.files){
   var reader = new FileReader();
   reader.onload = function(e){
    $("#prePic").attr('src', e.target.result);
   }
   reader.readAsDataURL(input.files[0]);
  }
 }
 
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