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
      	<h2>修改商品</h2>
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
				<label for="name">商品名稱:</label> 
				<input name="commName" type="text" class="text" value="${updateMallVo.commName}"> 
				<label for="price">售價:</label> 
				<input name="price" type="text" class="text" value="${updateMallVo.price}"> 
				<label for="password">數量:</label> 
				<input name="quantity" type="text" class="text" value="${updateMallVo.quantity}"> 
				<label for="password">商品介紹:</label>
				<textarea name="intro" cols="32" rows="5" class="text" style="resize: none;">${updateMallVo.intro}</textarea>
				<label class="age">適合年齡: 
				<input name="age" type="text" value="${updateMallVo.age}">歲以上</label> 
				<label class="player">建議人數: 
				<input name="player" type="text" value="${updateMallVo.player}">人 格式:1-6 or 1~6
				</label>
				<label class="d-block other">遊戲類型:</label>
				
				<!-- 拿出此vo所有遊戲類型 再用所有遊戲類型去比對如果是的話就選重，gmTypeSvc在首頁已經創了-->
				<!-- 雙foreach裡的布林是記錄類型如果一樣就是true三元運算是true就選中 -->
				<!-- not empty那行是如果前一次有勾選會存入到updateTampTypeNolist裡，如果是第一次執行updateTampTypeNolist
				沒有東西會執行mallSvc.getType(updateMallVo.commNo)，updateTampTypeNolist是存在request
				按取消時會藉由jquery特效收起update介面，這時updateTampTypeNolist還是存在request，不過沒關係再按一次修改時
				他會重新導到此網頁會消失不過為了保險有手動remove，記得el的名子不要重複，add裡有一個TampTypeNolist取重複了怎麼死的都不知道-->
								
				<c:forEach var="gmTypeVo" items="${gmTypeSvc.getAll()}">
					<c:set var="ischeck" value="false"/>
					<c:forEach var="mallVoType" items="${ not empty updateTampTypeNolist?updateTampTypeNolist:mallSvc.getType(updateMallVo.commNo)}">
						<c:if test="${gmTypeVo.typeNo==mallVoType.typeNo}">			
							<c:set var="ischeck" value="true"/>	
						</c:if>
					</c:forEach>
				<div class="box"><input type="checkbox" name="typeNo" value="${gmTypeVo.typeNo}" ${ischeck?"checked":""} >${ gmTypeVo.typeName }</div>
				</c:forEach>
				<!-- remove掉裡面的布林直 -->
				<%pageContext.removeAttribute("ischeck"); %>
				
				<label class="other">上下架狀態: 下架 
				<input type="checkbox" class="check-switch check-switch-anim" name="status" value="1"
					${(updateMallVo.status=="1")?"checked":"" }> 上架

				</label> <label class="other">上傳商品圖片:
				<input type="file" name="img" class="upload" accept="image/*" value="${updateMallVo.img}"></label>(如無須變更圖片不用上傳)
				<div class="showimg">
					<img src="<%= request.getContextPath()%>/Mall/MallShowImg?commNo=${updateMallVo.commNo}" style="width:250px; height:250px;">
				</div>
				<!-- 讓C確認是getone還是getall -->
				<input type="hidden" name="isGetOne" value="${param.isGetOne}">
				<input type="hidden" name="action" value="update">
				<input  type="hidden" name="whichPage" value="${param.whichPage}">
				<input  type="hidden" name="call" value="updateModel">
				
				<div class="modal-footer">
					<input type="submit" value="修改" class="btn">
        			<button type="button" class="btn btn-secondary cancel" data-dismiss="modal">取消</button>
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