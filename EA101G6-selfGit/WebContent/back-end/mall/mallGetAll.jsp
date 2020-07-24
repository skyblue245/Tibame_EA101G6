<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ page import="com.mall.model.*"%>
<%@ page import="com.gmTypeDt.model.*"%>
<%@ page import="com.gmType.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>�ӫ~�޲z</title>
<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/mallCss/mallcss.css">

<style>
div.backNav .dropdown-item.active, div.backNav .dropdown-item:active {
    color: #000000;
    text-decoration: none;
    background-color: #ffffff;
}

</style>

</head>



<body>

<%@ include file="/back-end/back-end-nav-susu.jsp" %>

	<div id="commaction">
		<button id="callGmType" style="margin-right:10px;">�C������</button>
		<a href="<%= request.getContextPath()%>/back-end/mall/mallGetAll.jsp?call=addModel&whichPage=${param.whichPage}"><button id="addMall">�s�W�ӫ~</button></a>
		<div style="display:inline"><b>���M�ӫ~:</b><form method="post" action="<%= request.getContextPath()%>/Mall/BackMallServlet" style="display:inline"><input type="text" name="selName">
		<input  type="hidden" name="action" value="selectone">
		<input type="submit" value="�j�M">
		</form>
		
		</div>
	</div>
	
	
		<div >
			<table id="comm" class="table table-bordered">
				<thead>
					<tr>
						<th></th>
						<th>�Ϥ�</th>
						<th>�ӫ~�W��</th>
						<th>���</th>
						<th>�ƶq</th>
						<th>�ӫ~�Ա�</th>
						<th>�A�X�~��</th>
						<th>��ĳ�H��</th>
						<th>�C������</th>
						<th>�W�[���A</th>

					</tr>
				</thead>
				<tbody>
				
				<!-- �srequest����P���ள�� -->
				<jsp:useBean id="gmTypeSvc" class="com.gmType.model.GmTypeService" scope="request"/>
				<jsp:useBean id="mallSvc" class="com.mall.model.MallService" scope="request"/>
					
					<%	
					Set<MallVO> mallVoSet = mallSvc.getAll();
					pageContext.setAttribute("mallVoSet", mallVoSet);
					%>
					<%@ include file="/back-end/mall/page1.file" %>
					<c:forEach var="mallVo" items="${mallVoSet}"  begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" >
						<tr>
							<td class="">
								<form action= "<%= request.getContextPath()%>/back-end/mall/mallGetAll.jsp" method="post">
									<input class="upda" type="submit"value="�ק�">
									<input type="hidden" name="commNo" value="${mallVo.commNo}">
									<!-- �s�X�ק虜�� -->
									<input type="hidden" name="call" value="updateModel">
									<!-- �T�w���� -->
									<input  type="hidden" name="whichPage" value="${param.whichPage}">
								</form>
							</td>
							<td><img src="<%= request.getContextPath()%>/Mall/MallShowImg?commNo=${mallVo.commNo}"></td>
							<td><div>${mallVo.commName}</div></td>
							<td>${mallVo.price}</td>
							<td>${mallVo.quantity}</td>
							<td><div>${mallVo.intro}</div></td>
							<td>${mallVo.age}���H�W</td>
							<td>${mallVo.player}�H</td>
							<td>
								<div>
									<c:forEach var="typeVo" items="${mallSvc.getType(mallVo.commNo)}">
										${typeVo.typeName} 
									</c:forEach>
								</div>
							</td>
							
							<td>${(mallVo.status=="1")?"�W�[��":"�U�[��" }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<br>
			<div style="text-align:center"><%@ include file="/back-end/mall/page2.file" %></div>

		</div>


<%@ include file="/back-end/gmType/gmTypeGetAll.jsp" %>

<c:if test="${param.call=='addModel'}">
<%@ include file="/back-end/mall/mallAdd.jsp" %>
</c:if>
<c:if test="${param.call=='updateModel'}">
<%@ include file="/back-end/mall/mallUpdate.jsp" %>
</c:if>

<script>var ctx ="<%=request.getContextPath()%>"</script>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<script src="<%= request.getContextPath() %>/js/malljs.js"></script>
<script>

<%session.removeAttribute("selNameMallVoSet"); //�������j�M�ӫ~�ɷ|�d��session%>

<!-- �����\�T���N�Ұ� -->
<c:if test="${not empty successMsg}"> 
	Swal.fire({
	  position: 'center',
	  icon: 'success',
	  title: '${successMsg}',
	  showConfirmButton: false,
	  timer: 1500
	})
	<%session.removeAttribute("successMsg");%>
</c:if>

<c:if test="${not empty param.call}">
	$("#Modal").modal({show: true});
</c:if>

<c:if test="${not empty selErroMsg}">
	Swal.fire({
  		icon: 'error',
  		title: '���~�T��',
 	 	text:"${selErroMsg}"
	})
</c:if>	

<!-- //�����~�N�۰ʮi�}  -->
<c:if test="${not empty erroMsg}">
	let erroMsg='';
	<c:forEach var="msg" items="${erroMsg}">
		erroMsg+='<div style="color:red; text-align:left;padding:0px 35px;">${msg}<br></div>';
	</c:forEach>
	Swal.fire({
		  icon: 'error',
		  title: '���~�T��',
		  html:erroMsg
		})
</c:if>

<!--�����~�N�۰ʮi�} -->
<c:if test="${not empty updateerroMsg}">
	let erroMsg='';
	<c:forEach var="msg" items="${updateerroMsg}">
		erroMsg+='<div style="color:red; text-align:left;padding:0px 35px;">${msg}<br></div>';
	</c:forEach>
	Swal.fire({
		  icon: 'error',
		  title: '���~�T��',
		  html:erroMsg
		})
</c:if>	
	

</script>

</body>
</html>

