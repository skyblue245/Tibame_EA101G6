<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.emp.model.*"%>
<%@ page import="java.util.*"%>

<!-- 下方可以getAttribute，是因為EmpServlet有存放empVO 【req.setAttribute("empVO", empVO);】 EmpServlet的69行 -->
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
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>員工資料</title>
</head>

<!-- 這邊放css!!!!!!!!!!!!!! -->
<style>
	
	.table{
		text-align:center;
	}
	
	.empPic{
		height:80px;
		width:80px;
	}
</style>
<body>

<jsp:include page="/back-end/back-end_nav.jsp"></jsp:include> 
	<div class="container">
		<div class="row tm-content-row emptop">
			<div class="tm-col emp">
				<div class="bg-white tm-block">
					<div class="row">
						<div class="col"><!-- 員工資料的標題 -->
							<h2 class="tm-block-title d-inline-block">員工資料</h2>
						</div>
					</div>
					<div class="table-responsive">
						<table class="table">
							<thead>
								<tr class="tabletop">
									<th scope="col">照片</th>
				                    <th scope="col">員工編號</th>
				                    <th scope="col">員工姓名</th>
				                    <th scope="col">Email</th>
				                    <th scope="col">性別</th>
				                    <th scope="col">員工狀態</th>
				                    <th scope="col">員工權限</th>
				                    <th scope="col">相關修改</th>
                				</tr>
							</thead>
							<tbody>
								<tr>
									<td>
										<div class="emp_pic">
											<img src="<%=request.getContextPath()%>/emp/EmpImgServlet?empno=${empVO.empno}">
										</div>
									</td>
									<td class="align-middle">${empVO.empno}</td>
					                <td class="align-middle">${empVO.empname}</td>
					                <td class="align-middle">${empVO.mail}</td>
					                <td class="align-middle">${empVO.sex}</td>
					                <td class="align-middle">${(empVO.empstatus== 0)? "離職" : (empVO.getEmpstatus() == 1)? "在職" : "未到職"}</td>
					               
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
					                <td class="align-middle">
					                	<form method="post" action="<%=request.getContextPath()%>/emp/EmpServlet">
					                		<input type="submit" value="修改" ${auth ? "" : "disabled"}> 
											<input type="hidden" name="empno" value="${empVO.empno}"> 
											<input type="hidden" name="action" value="getOne_For_Update">
										</form>
					                </td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	
	
<!-- 	<h3>員工資料</h3> -->
<!-- 	<table> -->
<!-- 		<tr> -->
<!-- 			<th>員工照片</th> -->
<!-- 			<th>員工編號</th> -->
<!-- 			<th>員工姓名</th> -->
<!-- 			<th>信箱</th> -->
<!-- 			<th>性別</th> -->
<!-- 			<th>員工狀態</th> -->
<!-- 			<th>修改</th> -->
<!-- 		</tr> -->
<!-- 		<tr> -->
<%-- 			<th><img class="empPic" src="<%=request.getContextPath()%>/emp/EmpImgServlet?empno=${empVO.empno}"></th>         --%>
<%-- 			<th><%=empVO.getEmpno() %></th> --%>
<%-- 			<th><%=empVO.getEmpname() %></th> --%>
<%-- 			<th><%=empVO.getMail() %></th> --%>
<%-- 			<th><%=empVO.getSex() %></th> --%>
<%-- 			<th><%=(empVO.getEmpstatus() == 0)? "離職" : (empVO.getEmpstatus() == 1)? "在職" : "未到職"%></th> --%>
<!-- 			<th> -->
<%-- 				<form method="post" action="<%=request.getContextPath()%>/emp/EmpServlet"> --%>
<!-- 					<input type="submit" value="修改">  -->
<%-- 					<input type="hidden" name="empno" value="${empVO.empno}">  --%>
<!-- 					<input type="hidden" name="action" value="getOne_For_Update"> -->
<!-- 				</form> -->
<!-- 			</th> -->
<!-- 		</tr> -->
<!-- 	</table> -->
</body>
</html>