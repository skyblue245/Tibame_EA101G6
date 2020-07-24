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
      	<h2>新增商品</h2>
       		 <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
     <div class="modal-body"> 
		<div class="commDiv" id="addDiv" title="mallAdd">
			<form method="post" action="<%= request.getContextPath()%>/Mall/BackMallServlet" enctype="multipart/form-data">
				
				<div><label for="name">商品名稱:</label> 
					<input id="commName" name="commName" type="text" class="text" value="${tempmallVo.commName}"></div>
				<div><label for="price">售價:</label> 
					<input id="price" name="price" type="text" class="text" value="${tempmallVo.price}"></div>
				<div><label >數量:</label>  
					<input id="quantity" name="quantity" type="text" class="text" value="${tempmallVo.quantity}"></div>
				<div><label>商品介紹:</label></div>
					<textarea id="intro" name="intro" cols="32" rows="5" class="text" style="resize: none;">${tempmallVo.intro}</textarea>
				<div><label class="age">適合年齡: 
					<input id="age" name="age" type="text" value="${tempmallVo.age}">歲以上</label>
				<label class="player" >建議人數: 
					<input id="player" name="player" type="text" value="${tempmallVo.player}">人 格式:1-6 or 1~6</label>
				
				<!-- gmTypeSvc在首頁已經創了 ，tampTypeNolist是在新增時有錯誤會回傳之前勾選的type -->
				<!-- 在用foreach如果tampTypeNolist的type=typeNo就checked -->
				 <label class="d-block other">遊戲類型:</label>
					<c:forEach var="gmTypeVo" items="${gmTypeSvc.getAll()}">
						<div class="box">
							<input class="magicChick"  type="checkbox" name="typeNo" value="${gmTypeVo.typeNo}" 
								<c:forEach var="typeNo" items="${tampTypeNolist}">
								${typeNo==gmTypeVo.typeNo?"checked":"" }
								</c:forEach> >${ gmTypeVo.typeName }
						</div>
					</c:forEach>
				
				<label class="other">上下架狀態: 下架
				<input type="checkbox"class="check-switch check-switch-anim" name="status" value="1" >
						上架
				</label>
			
				 <label class="other">上傳商品圖片:<input type="file" name="img" accept="image/*"
					class="upload"></label>
				<div class="showimg"><img></div>
				<input type="hidden" name="action" value="mallAdd">
				<!-- 確定頁面 -->
				<input  type="hidden" name="whichPage" value="${param.whichPage}">
				<input  type="hidden" name="call" value="addModel">
				<div class="modal-footer">
					<button type="button" class="magicBtn btn btn-secondary">神奇海螺</button>
					<input type="submit" value="新增" class="btn">
        			<button type="button" class="btn btn-secondary cancel" data-dismiss="modal">取消</button>
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
		$("#commName").val("炸彈競技場2");
		$("#price").val("120");
		$("#quantity").val("30");
		$("#intro").val("這些礦工矮人們整天在坑道中挖掘寶石"+
				"，雖然累積的財富無數，但生活習慣相當糟糕，"
				+"衛生兩字他們從沒聽過。白雪公主來了以後，豐盛的晚餐上桌前，必定要求他們將手洗淨，但是，"
				+"頑固的矮人們由於長相穿著都很相似，經常魚目混珠、瞞騙公主。聰明的公主在他們的帽子繡上編號，一個個點名檢查，"
				+"一旦排序錯誤，公主就會大發雷霆，矮人們就甭想吃晚餐了！");
		$("#age").val("8");
		$("#player").val("2-4");
		$(".magicChick").prop("checked",true);
	})
});

</script>

</body>
</html>