<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ page import="com.gmType.model.*"%>
<%@ page import="com.gmTypeDt.model.*"%>
<%@ page import="com.mall.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
<div id="Modal" class="modal" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
      	<h2>�ק�ӫ~</h2>
       		 <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
     <div class="modal-body"> 
     
	<div class="commDiv" id="updateDiv" title="mallAdd">
		<form method="post" action="<%=request.getContextPath()%>/Mall/BackMallServlet" enctype="multipart/form-data">
		
				

				
				<%
				if(request.getAttribute("updateMallVo")==null){
					MallVO updateMallVo =((MallService)request.getAttribute("mallSvc")).findOneByNo(request.getParameter("commNo")) ;
					pageContext.setAttribute("updateMallVo", updateMallVo);
				}
				%>
				
				<input type="hidden" name="commNo" value="<%= request.getParameter("commNo") %>">
				<label for="name">�ӫ~�W��:</label> 
				<input name="commName" type="text" class="text" value="${updateMallVo.commName}"> 
				<label for="price">���:</label> 
				<input name="price" type="text" class="text" value="${updateMallVo.price}"> 
				<label for="password">�ƶq:</label> 
				<input name="quantity" type="text" class="text" value="${updateMallVo.quantity}"> 
				<label for="password">�ӫ~����:</label>
				<textarea name="intro" cols="32" rows="5" class="text" style="resize: none;">${updateMallVo.intro}</textarea>
				<label class="age">�A�X�~��: 
				<input name="age" type="text" value="${updateMallVo.age}">���H�W</label> 
				<label class="player">��ĳ�H��: 
				<input name="player" type="text" value="${updateMallVo.player}">�H �榡:1-6 or 1~6
				</label>
				<label class="d-block other">�C������:</label>
				
				<!-- ���X��vo�Ҧ��C������ �A�ΩҦ��C�������h���p�G�O���ܴN�ﭫ�AgmTypeSvc�b�����w�g�ФF-->
				<!-- ��foreach�̪����L�O�O�������p�G�@�˴N�Otrue�T���B��Otrue�N�襤 -->
				<!-- not empty����O�p�G�e�@�����Ŀ�|�s�J��updateTampTypeNolist�̡A�p�G�O�Ĥ@������updateTampTypeNolist
				�S���F��|����mallSvc.getType(updateMallVo.commNo)�AupdateTampTypeNolist�O�s�brequest
				�������ɷ|�ǥ�jquery�S�Ħ��_update�����A�o��updateTampTypeNolist�٬O�s�brequest�A���L�S���Y�A���@���ק��
				�L�|���s�ɨ즹�����|�������L���F�O�I�����remove�A�O�oel���W�l���n���ơAadd�̦��@��TampTypeNolist�����ƤF��򦺪��������D-->
								
				<c:forEach var="gmTypeVo" items="${gmTypeSvc.getAll()}">
					<c:set var="ischeck" value="false"/>
					<c:forEach var="mallVoType" items="${ not empty updateTampTypeNolist?updateTampTypeNolist:mallSvc.getType(updateMallVo.commNo)}">
						<c:if test="${gmTypeVo.typeNo==mallVoType.typeNo}">			
							<c:set var="ischeck" value="true"/>	
						</c:if>
					</c:forEach>
				<div class="box"><input type="checkbox" name="typeNo" value="${gmTypeVo.typeNo}" ${ischeck?"checked":""} >${ gmTypeVo.typeName }</div>
				</c:forEach>
				<!-- remove���̭������L�� -->
				<%pageContext.removeAttribute("ischeck"); %>
				
				<label class="other">�W�U�[���A: �U�[ 
				<input type="checkbox" class="check-switch check-switch-anim" name="status" value="1"
					${(updateMallVo.status=="1")?"checked":"" }> �W�[

				</label> <label class="other">�W�ǰӫ~�Ϥ�:
				<input type="file" name="img" class="upload" accept="image/*" value="${updateMallVo.img}"></label>(�p�L���ܧ�Ϥ����ΤW��)
				<div class="showimg">
					<img src="<%= request.getContextPath()%>/Mall/MallShowImg?commNo=${updateMallVo.commNo}" style="width:250px; height:250px;">
				</div>
				<!-- ��C�T�{�Ogetone�٬Ogetall -->
				<input type="hidden" name="isGetOne" value="${param.isGetOne}">
				<input type="hidden" name="action" value="update">
				<input  type="hidden" name="whichPage" value="${param.whichPage}">
				<input  type="hidden" name="call" value="updateModel">
				
				<div class="modal-footer">
					<input type="submit" value="�ק�" class="btn">
        			<button type="button" class="btn btn-secondary cancel" data-dismiss="modal">����</button>
      			</div>
		</form>
	</div>
        
        
        
      </div>
    </div>
  </div>
</div>
	
	<%request.removeAttribute("updateTampTypeNolist"); %>
	<%request.removeAttribute("updateMallVo"); %>
</body>
</html>