<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ page import="com.gmType.model.*"%>
<%@ page import="com.gmTypeDt.model.*"%>
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
      	<h2>�s�W�ӫ~</h2>
       		 <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
     <div class="modal-body"> 
		<div class="commDiv" id="addDiv" title="mallAdd">
			<form method="post" action="<%= request.getContextPath()%>/Mall/BackMallServlet" enctype="multipart/form-data">
				
				<div><label for="name">�ӫ~�W��:</label> 
					<input id="commName" name="commName" type="text" class="text" value="${tempmallVo.commName}"></div>
				<div><label for="price">���:</label> 
					<input id="price" name="price" type="text" class="text" value="${tempmallVo.price}"></div>
				<div><label >�ƶq:</label>  
					<input id="quantity" name="quantity" type="text" class="text" value="${tempmallVo.quantity}"></div>
				<div><label>�ӫ~����:</label></div>
					<textarea id="intro" name="intro" cols="32" rows="5" class="text" style="resize: none;">${tempmallVo.intro}</textarea>
				<div><label class="age">�A�X�~��: 
					<input id="age" name="age" type="text" value="${tempmallVo.age}">���H�W</label>
				<label class="player" >��ĳ�H��: 
					<input id="player" name="player" type="text" value="${tempmallVo.player}">�H �榡:1-6 or 1~6</label>
				
				<!-- gmTypeSvc�b�����w�g�ФF �AtampTypeNolist�O�b�s�W�ɦ����~�|�^�Ǥ��e�Ŀ諸type -->
				<!-- �b��foreach�p�GtampTypeNolist��type=typeNo�Nchecked -->
				 <label class="d-block other">�C������:</label>
					<c:forEach var="gmTypeVo" items="${gmTypeSvc.getAll()}">
						<div class="box">
							<input class="magicChick"  type="checkbox" name="typeNo" value="${gmTypeVo.typeNo}" 
								<c:forEach var="typeNo" items="${tampTypeNolist}">
								${typeNo==gmTypeVo.typeNo?"checked":"" }
								</c:forEach> >${ gmTypeVo.typeName }
						</div>
					</c:forEach>
				
				<label class="other">�W�U�[���A: �U�[
				<input type="checkbox"class="check-switch check-switch-anim" name="status" value="1" >
						�W�[
				</label>
			
				 <label class="other">�W�ǰӫ~�Ϥ�:<input type="file" name="img" accept="image/*"
					class="upload"></label>
				<div class="showimg"><img></div>
				<input type="hidden" name="action" value="mallAdd">
				<!-- �T�w���� -->
				<input  type="hidden" name="whichPage" value="${param.whichPage}">
				<input  type="hidden" name="call" value="addModel">
				<div class="modal-footer">
					<button type="button" class="magicBtn btn btn-secondary">���_����</button>
					<input type="submit" value="�s�W" class="btn">
        			<button type="button" class="btn btn-secondary cancel" data-dismiss="modal">����</button>
      			</div>
      			
		</form>
	</div>
        
        
        
      </div>
    </div>
  </div>
</div>

<script>
$(document).ready(function() {
	$("button.magicBtn").click(function(){
		$("#commName").val("���u�v�޳�2");
		$("#price").val("120");
		$("#quantity").val("30");
		$("#intro").val("�o���q�u�G�H�̾�Ѧb�|�D�������_��"+
				"�A���M�ֿn���]�I�L�ơA���ͬ��ߺD�۷��V�|�A"
				+"�åͨ�r�L�̱q�Sť�L�C�ճ����D�ӤF�H��A�ײ������\�W��e�A���w�n�D�L�̱N��~�b�A���O�A"
				+"�x�T���G�H�̥ѩ���۬�۳��ܬۦ��A�g�`���زV�]�B�f�F���D�C�o�������D�b�L�̪��U�l¸�W�s���A�@�ӭ��I�W�ˬd�A"
				+"�@���Ƨǿ��~�A���D�N�|�j�o�p�^�A�G�H�̴N�ǷQ�Y���\�F�I");
		$("#age").val("8");
		$("#player").val("2-4");
		$(".magicChick").prop("checked",true);
	})
});

</script>

</body>
</html>