<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.authority.model.*"%>

<%
	EmpVO empVO = (EmpVO) request.getAttribute("empVO");
	List<String> authList = (List<String>) session.getAttribute("authList");
	boolean auth;
	if(authList.contains("LF00001")){//如果今天List放的是權限物件，要用contains去比對的話，(括弧內也得放權限物件才能比對)，contains沒辦法用字串去單獨對物件內的某個屬性比對
		auth = true;				 //一定要一樣的東西才能比對
	}else{
		auth = false;
	}
	pageContext.setAttribute("auth", auth);
	
// 	System.out.println("有沒有這個權限" + auth);
// 	System.out.println("List長度" + authList.size());
// 	for(String authority : authList){
// 		System.out.println(authority);
// 	}
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>新增員工</title>
</head>

<style>
	.ePic {
		height:200px;
		width:200px;
	}
	.sexpng{
		height:50px;
		width:50px;
	}
	
	#empdata{
		margin-top:5%;			
	}
	
	.rightfrom{
		position:absolute; 
		top:0px;
		text-align: center;
	}
	
	#empall{
		width:100%;
	}
	
	.emptitle{
		text-align: center;
	}
	
	#uppicbtn{
		padding-left:200px;
		
	}
	
	.empsend{
		padding-left:680px;
	}
	
	.magicBtn{
		padding-left:655px;
	}
	
	
</style>

<script>
  var loadFile = function(event) {
    var output = document.getElementById('output');
    output.src = URL.createObjectURL(event.target.files[0]);
    output.onload = function() {
      URL.revokeObjectURL(output.src) // free memory
    }
  };
</script>

<body>

<%-- <%@ include file="/back-end/back-end_nav.jsp" %> --%>
<!-- 用jsp去include比較不容易跟其他的程式碼發生衝突 -->
<jsp:include page="/back-end/back-end_nav.jsp"></jsp:include> 


<!-- 錯誤列表  -->
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤：</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

<div class="container">
	<div class="tm-col tm-col-big" id="empall">
		<div class="bg-white tm-block" id="empdata">
			<div class="row">
				<div class="col-12 ">
					<h2 class="tm-block-title emptitle">新增員工</h2>
				</div>
			</div>
			<form method="post" action="<%=request.getContextPath()%>/emp/EmpServlet" class="tm-signup-form" enctype="multipart/form-data">
				<div class="row">
					<div class="col-6">
						<div class="form-group">
							<label for="name">員工姓名：</label>
							<input placeholder="請輸入員工姓名：" id="name" name="empname" type="text" class="form-control validate" value="<%= (empVO == null)? "" : empVO.getEmpname()%>" />
						</div>
						<div class="form-group">
							<label for="email">Email：</label>
							<input placeholder="email@gmail.com" id="email" name="mail" type="email" class="form-control validate" value="<%= (empVO == null)? "" : empVO.getMail()%>" />
						</div>
						<div class="form-group">
							<label for="sex">性別：</label><br>
							<label for="male"><input type="radio" id="male" name="sex" value="MALE" ${(empVO.sex == "MALE") ? "checked" : "" }> <img class="sexpng" src="<%=request.getContextPath()%>/img/SEX/male.png"></label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<label for="female"><input type="radio" id="female" name="sex" value="FEMALE" ${(empVO.sex == "FEMALE") ? "checked" : "" }><img class="sexpng" src="<%=request.getContextPath()%>/img/SEX/female.png"></label>
						</div>
						<div class="form-group">
							<label for="status">員工狀態：</label>
							<select name="empstatus">
								<option value="2" ${(empVO.empstatus == 2) ? "selected" : "" }>未到職</option>
								<option value="1" ${(empVO.empstatus == 1) ? "selected" : "" }>在職</option>
								<option value="0" ${(empVO.empstatus == 0) ? "selected" : "" }>離職</option>
							</select>
						</div>
						
						<jsp:useBean id="featuresSvc" scope="page" class="com.features.model.FeaturesService" />
						
						<div class="form-group">
							<label>員工權限：</label>
							<br>	
							<c:forEach var="featuresVO" items="${featuresSvc.all}">
								<c:set var="emphave" value="false" />
								<c:forEach var="empft" items="${empftno}">
									<c:if test="${empft == featuresVO.ftno}">
										<c:set var="emphave" value="true" />
									</c:if>
								</c:forEach>
								<label for="${featuresVO.ftno}"><input type="checkbox" name="features" id="${featuresVO.ftno}" value="${featuresVO.ftno}" ${emphave ? "checked" : ""}>${featuresVO.ftname}</label>
							</c:forEach>
						</div>
					</div>
					<div class="col-6 bg-white tm-block rightfrom">
						<img class="ePic" id="output"><br><br>
						<input id="uppicbtn" type="file" name="pic" onchange="loadFile(event)"/>
					</div>
				</div>
				
				<input type="hidden" name="action" value="insert">
				<div class="row empsend">
					<div class="col-12 col-sm-4">
						<button type="submit" class="btn btn-primary">Add</button>
					</div>
				</div>
				<br>
				<div class="magicBtn">
					<button type="button" id="magic" class="btn btn-outline-warning">神奇小按鈕</button>
				</div>
			</form>
		</div>
	</div>
</div>

	

<script src="<%=request.getContextPath()%>/js/jquery-3.3.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>

<script>

$(document).ready(function(){
   $("#magic").click(function(){
     $("#name").val("小智智");
     $("#email").val("lct840116@gmail.com");
   });
 });
 
</script>

</body>
</html>









