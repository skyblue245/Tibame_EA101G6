<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>
<%@ page import="java.util.*"%>

<!-- EmpServlet.java (Controller) 存入req的empVO物件(req.getAttribute("empVO")) -->
<!-- 包含取出要更新的員工物件，以及輸入錯誤資料時，set進去(包含錯誤資訊)的員工物件empVO -->
<%
	EmpVO empVO = (EmpVO) request.getAttribute("empVO"); 
	
	//取得登入的員工所擁有的權限
	List<String> authList = (List<String>) session.getAttribute("authList");
	boolean auth;
	if(authList.contains("LF00001")){//如果今天List放的是權限物件，要用contains去比對的話，(括弧內也得放權限物件才能比對)，contains沒辦法用字串去單獨對物件內的某個屬性比對
		auth = true;//一定要一樣的東西才能比對
	}else{
		auth = false;
	}
	pageContext.setAttribute("auth", auth);
	
	//取得於getOne_For_Update和Update時set的Attribute，給下方使用
	List<String> empAuthority = (List<String>) request.getAttribute("empAuthority");
	request.setAttribute("empAuthority", empAuthority);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<link href='https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css' rel='stylesheet'></link>
<link rel="stylesheet" href="./vendors/bootstrap/css/bootstrap.min.css">
<title>員工修改</title>
</head>

<style>
	/*讓img沒有圖片時也不會有邊框 */
	img[src=""],img:not([src]){
    opacity:0;
	}
	
	.sexpng{
		height:50px;
		width:50px;
	}
	
	.empPic{
		height:180px;
		width:180px;
	}
	#upimg{
		border:1px solid #999999;
		background-color:#EEEEEE;
		color:black;
		cursor:pointer;
	}
	#output{
		height:180px;
		width:180px;
		border:none;
	}
	
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
	
	
</style>

<!-- 預覽圖片的三步驟 -->
<!-- 1.預覽圖片的js -->
<script>
  var loadFile = function(event) {
    var output = document.getElementById('output');
    output.src = URL.createObjectURL(event.target.files[0]);
    output.onload = function() {
      URL.revokeObjectURL(output.src) // free memory
    }
  };
</script>
<!-- 預覽圖片的js -->

<body>

<jsp:include page="/back-end/back-end_nav.jsp"></jsp:include> 

<!-- <h3>此為員工自己想修該資料 和 員工管理員想修改員工的狀態 的頁面</h3> -->

<%-- 錯誤表列 --%>
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
					<h2 class="tm-block-title emptitle">資料修改</h2>
				</div>
			</div>
			<form method="post" action="<%=request.getContextPath()%>/emp/EmpServlet" class="tm-signup-form" enctype="multipart/form-data">
				<div class="row">
					<div class="col-6">
						<div class="form-group">
							<label for="name">員工編號：</label>
							<input type="text" readonly="readonly" value="<%=empVO.getEmpno()%>" class="form-control validate">
						</div>
						<div class="form-group">
							<label for="name">員工姓名：</label>
							<input type="text" id="name" name="empname" value="<%=empVO.getEmpname()%>" class="form-control validate">
						</div>
						<div class="form-group">
							<label for="name">信箱：</label>
							<input type="email" id="email" name="mail" value="<%=empVO.getMail()%>" class="form-control validate">
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
								<c:set var="emphave" value="false"/> <!-- 先設一個變數設成false讓下面做判斷 -->
								<c:forEach var="empAuth" items="${empAuthority}"><!-- empAuthority為內含字串的陣列 -->
										<c:if test="${featuresVO.ftno == empAuth}"><!-- 如果陣列內的字串和權限的字串相同 -->
											<c:set var="emphave" value="true"/><!-- 將變數設成true讓下面做判斷 -->
										</c:if>
								</c:forEach>
								
<!-- 								原本用看是否為超級管理員決定核取方塊是否為disable，但如果是disable，就沒辦法傳遞核取方塊的value給後台!!! -->
<!--								所以用了以下的方法																	  -->

<!-- 								 line:189----用choose判斷是否為超級管理員 															  -->
<!--								 line:190----如果是超級管理員，顯示可以選擇的核取方塊，後台也接的到值 -->
<!-- 								 line:191----給超級管理員看可以按的核取方塊 -->
<!-- 								 line:193----如果是一般管理員 -->
<!--								 line:194----將該員工所擁有的權限用forEach跑出來，並用hidden的方式傳給後台接 -->
<!--								 line:197----給一般管理員看可以不能按的核取方塊，注意：如果是disable，此處的value沒辦法傳給後台，所以才用上方的hidden傳送 -->
						
								<c:choose>
									<c:when test="${auth}">
										<label for="${featuresVO.ftno}"><input type="checkbox" name="features" id="${featuresVO.ftno}" value="${featuresVO.ftno}" ${ emphave ? "checked" : ""} ${ auth ? "" : "disabled"}>${featuresVO.ftname}</label>
									</c:when>
									<c:otherwise>
										<c:forEach var="smauth" items="${authList}">
											<input type="hidden" name="features" value="${smauth}">
										</c:forEach>
										<input type="checkbox" value="${featuresVO.ftno}" ${ emphave ? "checked" : ""} ${ auth ? "" : "disabled"}>${featuresVO.ftname}
									</c:otherwise>
								</c:choose>
							</c:forEach>

						</div>
					</div>
					<div class="col-6 bg-white tm-block rightfrom">
						<label for="pic">員工照片：</label><br>
						<img class="empPic" src="<%=request.getContextPath()%>/emp/EmpImgServlet?empno=${empVO.empno}">
						<br><br><br>
						<label id="upimg" class="btn btn-info">
							<input id="upload_img" style="display:none;" type="file" name="pic" onchange="loadFile(event)">
							<i class="fa fa-photo"></i> 修改圖片									<!-- 2.將type="file"後面註冊一個事件 -->
						</label>
						<br><br><br>
						<!-- 3.預覽圖片的圖片放這 -->
						<td><img id="output" style="border:none;" /></td>
						<!-- 預覽圖片的圖片放這 -->
					</div>
					
					
					<div class="col-6 bg-white tm-block rightfrom" style="display:none;">
						<img class="ePic" id="output"><br><br>
						<input id="uppicbtn" type="file" name="pic" onchange="loadFile(event)"/>
					</div>
				</div>
				<input type="hidden" name="requestURL"	value="${param.requestURL}">
			    <input type="hidden" name="whichPage"	value="${param.whichPage}"> 
				<input type="hidden" name="empno" value="<%=empVO.getEmpno()%>">
				<input type="hidden" name="action" value="update">
				<div class="row empsend">
					<div class="col-12 col-sm-4">
						<button type="submit" class="btn btn-primary">送出修改</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>



<%-- 	<form method="post" action="<%=request.getContextPath()%>/emp/EmpServlet" enctype="multipart/form-data"> --%>
<!-- 		<table> -->
		
<!-- 			<tr> -->
<!-- 				<td>員工照片：</td> -->
<%-- 				<td><img class="empPic" src="<%=request.getContextPath()%>/emp/EmpImgServlet?empno=${empVO.empno}"></td> --%>
<!-- 				<td> -->
<!-- 				<label id="upimg" class="btn btn-info"> -->
<!-- 				<input id="upload_img" style="display:none;" type="file" name="pic" onchange="loadFile(event)"> -->
<!-- 				<i class="fa fa-photo"></i> 修改圖片									2.將type="file"後面註冊一個事件 -->
<!-- 				</label> -->
<!-- 				</td> -->
<!-- 				3.預覽圖片的圖片放這 -->
<!-- 				<td><img id="output"/></td> -->
<!-- 				預覽圖片的圖片放這 -->
<!-- 			</tr> -->
			
<!-- 			<tr> -->
<!-- 				<td>員工編號：</td> -->
<%-- 				<td><%=empVO.getEmpno()%></td> --%>
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td>員工姓名：</td> -->
<%-- 				<td><input type="text" name="empname" value="<%=empVO.getEmpname()%>"></td> --%>
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td>信箱：</td> -->
<%-- 				<td><input type="email" name="mail" value="<%=empVO.getMail()%>"></td> --%>
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td>性別：</td> -->
<%-- 				<td><input type="radio" name="sex" value="MALE" ${(empVO.sex == "MALE") ? "checked" : "" }>男 --%>
<%-- 				<input type="radio" name="sex" value="FEMALE" ${(empVO.sex == "FEMALE") ? "checked" : "" }>女</td> --%>
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td>員工狀態：</td> -->
<!-- 				<td> -->
<!-- 				<select name="empstatus"> -->
<%-- 					<option value="0" ${(empVO.empstatus == 0) ? "selected" : "" }>離職</option> --%>
<%-- 					<option value="1" ${(empVO.empstatus == 1) ? "selected" : "" }>在職</option> --%>
<%-- 					<option value="2" ${(empVO.empstatus == 2) ? "selected" : "" }>未到職</option> --%>
<!-- 				</select> -->
<!-- 				</td> -->
<!-- 			</tr> -->
						<!-- !!!!!!!!!!!!!!!!!!之後會加上如果員工擁有授權管理的權限，才能修改這項的東西!!!!!!!!!!!!! -->

<!-- 		</table> -->
<!-- 		<br> -->
<!-- 	<input type="hidden" name="action" value="update"> -->
<%-- 	<input type="hidden" name="empno" value="<%=empVO.getEmpno()%>"> --%>
<!-- 	<input type="submit" value="送出修改"> -->
<!-- 	</form> -->
	
	 
	<script src="<%=request.getContextPath()%>/js/jquery-3.3.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
	
	<script src="./vendors/jquery/jquery-3.4.1.min.js"></script>
    <script src="./vendors/popper/popper.min.js"></script>
    <script src="./vendors/bootstrap/js/bootstrap.min.js"></script>	
</body>
</html>