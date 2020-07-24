<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.emp.model.*"%>
<%
	EmpService empSvc = new EmpService();
	List<EmpVO> list = empSvc.getAllEmp();
	pageContext.setAttribute("list", list);
	
	List<String> authList = (List<String>) session.getAttribute("authList");
	boolean auth;
	if(authList.contains("LF00001")){//如果今天List放的是權限物件，要用contains去比對的話，(括弧內也得放權限物件才能比對)，contains沒辦法用字串去單獨對物件內的某個屬性比對
		auth = true;				 //一定要一樣的東西才能比對
	}else{
		auth = false;
	}
	pageContext.setAttribute("auth", auth);

%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>全部員工</title>

<!-- 此處放css!!!!!! -->

<style type="text/css">
	.table{
		text-align:center;
	}
	.emptext{
		text-align: center;
	}

/* 沒有可以新增員工的管理員的按鈕 */
	#noAddEmp{
		opacity:0.8;
	}
	.ppic:hover{
		transform:scale(3,3);
	}
</style>


</head>
<body>

<jsp:include page="/back-end/back-end_nav.jsp"></jsp:include> 

<%-- 錯誤列表 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>


	
	<div class="container">
		<div class="row tm-content-row emptop">
			<div class="tm-col emp">
				<div class="row">
					<div class="col emptext">
						<form method="post" action="<%=request.getContextPath()%>/emp/EmpServlet" enctype="multipart/form-data">
																									<!-- ↑如果有檔案的傳送，一定要加這條 -->
							<b>請輸入員工編號(ex:LE00001)：</b> <input type="text" name="empno">
							<input type="hidden" name="action" value="getOne_For_Display">  <!-- name是給後端傳資料用的，controller用getParameter接 -->
							<input type="submit" value="送出">
						</form>
					</div>
					<div class="col emptext">
						<form method="post" action="<%=request.getContextPath()%>/emp/EmpServlet" enctype="multipart/form-data">
																									<!-- ↑如果有檔案的傳送，一定要加這條 -->
							<b>請輸入員工姓名：</b> <input type="text" name="empname"> 
							<input type="hidden" name="action" value="getOneName_For_Display">  <!-- name是給後端傳資料用的，controller用getParameter接 -->
							<input type="submit" value="送出">
						</form>																		
					</div>
				</div>
				<br>
				<div class="bg-white tm-block">
					<!-- 員工清單的區塊 -->
					<div class="row">
						<div class="col"><!-- 員工清單的標題 -->
							<h2 class="tm-block-title d-inline-block">員工清單</h2>
						</div>
						<div class="col"><!-- 新增員工 -->
							<form method="post" action="<%=request.getContextPath()%>/back-end/emp/addEmp.jsp">
								<c:if test="${auth == false}">
									<input type="submit" id="noAddEmp" value="新增員工" style="display:none" disabled>
								</c:if>
								<c:if test="${auth == true}">
									<input type="submit" id="addEmp" value="新增員工" >
								</c:if>
								 
							</form>
						</div>
					</div>
					<div class="table-responsive"> <!--table-striped：將畫面一行灰色，一行白色-->
						<table class="table"><!-- listAll.jsp -->
							<thead>
								<tr class="tabletop">
									<th scope="col">照片</th>
				                    <th scope="col">員工編號</th>
				                    <th scope="col">員工姓名</th>
				                    <th scope="col">Email</th>
				                    <th scope="col">員工性別</th>
				                    <th scope="col">員工狀態</th>
				                    <th scope="col">員工權限</th>
				                    <th scope="col">相關修改</th>
                				</tr>
							</thead>
								<%@ include file="page1.file"%><!-- 引入換頁的程式碼 -->
							<tbody>
								<c:forEach var="empVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex + rowsPerPage-1 %>">
									<tr ${(empVO.empno == param.empno) ?  'bgcolor=#e6e6e6' : ''}>
										<td class="align-middle">
											<div class="emp_pic align-middle">
												<img class="ppic" src="<%=request.getContextPath()%>/emp/EmpImgServlet?empno=${empVO.empno}">
											</div>
										</td>
										<td  class="align-middle">${empVO.empno}</td>
						                <td  class="align-middle">${empVO.empname}</td>
						                <td  class="align-middle">${empVO.mail}</td>
						                <td  class="align-middle">${empVO.sex}</td>
						                <td  class="align-middle">${(empVO.empstatus== 0)? "離職" : (empVO.getEmpstatus() == 1)? "在職" : "未到職"}</td>
						                
						                <jsp:useBean id="authoritySvc" scope="page" class="com.authority.model.AuthorityService" />
						                <jsp:useBean id="featuresSvc" scope="page" class="com.features.model.FeaturesService" />
						                
						                <td class="align-middle">
						                	<c:forEach var="authorityVO" items="${authoritySvc.all}"><!--取出全部的權限VO，給下面比對每個權限的內容 -->
						                		<c:if test="${authorityVO.empno == empVO.empno}"><!--如果權限的員工編號和員工的員工編號一樣 -->
						                			<c:forEach var="featuresVO" items="${featuresSvc.all}"><!--取出全部的功能VO，給下面比對每個功能的內容 -->
						                				<c:if test="${authorityVO.ftno == featuresVO.ftno}"><!--如果權限的編號和功能的編號一樣 -->
						                					${featuresVO.ftname} <br><!--就印出功能的中文名 -->
						                				</c:if>
						                			</c:forEach>
						                		</c:if>
						                	</c:forEach>
						                </td>
						                <td  class="align-middle">
						                	<form method="post" action="<%=request.getContextPath()%>/emp/EmpServlet">
						                		<c:if test="${auth == true}">
													<input type="submit" value="修改" class="btn-outline-secondary" style="border:1px solid;color:black;" >
												</c:if>
												<c:if test="${auth == false}">
													<input type="submit" value="修改" class="btn-outline-secondary" ${empVO.empno == accountBack ? "style='border:1px solid;color:black;'" : "style='display:none'"}>
												</c:if>
<!-- 						                		<input type="submit" value="修改" class="btn-outline-secondary" style="border:1px solid;color:black;" >  -->
												<input type="hidden" name="empno" value="${empVO.empno}"> 
												<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"> 
												<input type="hidden" name="whichPage" value="<%=whichPage%>"> 
												<input type="hidden" name="action" value="getOne_For_Update">
											</form>
						                </td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<%@include file="page2.file"%>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>