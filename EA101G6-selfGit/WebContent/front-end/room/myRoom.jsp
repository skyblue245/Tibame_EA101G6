<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="com.joinrm.model.*" %>
<%@ page import="com.mbrpf.model.*" %>
<%@ page import="java.util.*"%>

<%
	JoinrmService joinrmSvc = new JoinrmService();
	MbrpfVO mbrpfVO =(MbrpfVO) session.getAttribute("mbrpfVO");
	String mbrno = mbrpfVO.getMbrno();
	List<JoinrmVO> list = joinrmSvc.findByPK("",mbrno);
	pageContext.setAttribute("list",list);
	
%>
<jsp:useBean id="mbrpfSvc" scope="page" class="com.mbrpf.model.MbrpfService" />
<jsp:useBean id="rminfoSvc" scope="page" class="com.rminfo.model.RminfoService" />
<jsp:useBean id="joinrmSvc2" scope="page" class="com.joinrm.model.JoinrmService" />
<jsp:useBean id="shopSvc" scope="page" class="com.shop.model.ShopService" />
<!DOCTYPE html>
<html>
<head>
<title>玩家的房間列表+狀態改變</title>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript"> var jQuery_1_12_4 = $.noConflict(true); </script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
</head>
<body>
<%@ include file="/front-end/front-end-nav.jsp"%>
<c:if test="${not empty successMsgs}">
	<script>
		Swal.fire({
			  position: 'center',
			  icon: 'success',
			  title: '${successMsgs}',
			  showConfirmButton: false,
			  timer: 1500
			})
	</script>
</c:if>
<c:if test="${not empty updateMsgs}">
	<script>
		Swal.fire({
			  position: 'center',
			  icon: 'success',
			  title: '${updateMsgs}',
			  showConfirmButton: false,
			  timer: 1500
			})
	</script>
</c:if>
<div id="listAll">
<table class="table table-striped">
	<tr>
		<th style="width:10%">房名</th>
		<th style="width:6%">房主</th>
		<th style="width:7%">遊玩店家</th>
		<th style="width:6%">人數限制[目前人數]</th>
		<th style="width:8%">遊玩時間</th>
		<th style="width:15%">玩的遊戲</th>
		<th style="width:10%">備註</th>
		<th style="width:15%">房間狀態</th>
		<th style="width:6%"></th>
		<th></th>
	</tr>
	<c:forEach var="joinrmVO" items="${list}">
	<tr>
		<td>${rminfoSvc.getOneRm(joinrmVO.rmno).naming}</td>
		<td>${mbrpfSvc.getOneMbrpf(rminfoSvc.getOneRm(joinrmVO.rmno).mbrno).mbrname}</td>
		<td>${shopSvc.getOneShop(rminfoSvc.getOneRm(joinrmVO.rmno).shopno).shopname}</td>
		<td>${rminfoSvc.getOneRm(joinrmVO.rmno).lowlimit}~${rminfoSvc.getOneRm(joinrmVO.rmno).uplimit} [${fn:length(joinrmSvc2.findByPK(joinrmVO.rmno,''))}]</td>
		<td><fmt:formatDate value="${rminfoSvc.getOneRm(joinrmVO.rmno).starttime}" pattern="yyyy-MM-dd HH:mm" />
			~<fmt:formatDate value="${rminfoSvc.getOneRm(joinrmVO.rmno).endtime}" pattern="HH:mm" /></td>
		<td>${rminfoSvc.getOneRm(joinrmVO.rmno).game}</td>
		<td>${rminfoSvc.getOneRm(joinrmVO.rmno).remarks}</td>
		<td>
			<c:choose>
    			<c:when test="${rminfoSvc.getOneRm(joinrmVO.rmno).status eq 0}">
    				<span style="color:blue;">等待成員加入</span>
   		 		</c:when>
			    <c:when test="${rminfoSvc.getOneRm(joinrmVO.rmno).status eq 1}">
			    	<span style="color:blue;">人數已達可訂位下限</span>
			    </c:when>
			    <c:when test="${rminfoSvc.getOneRm(joinrmVO.rmno).status eq 2}">
			    	<span style="color:blue;">人數已滿</span>
			    </c:when>
			    <c:when test="${rminfoSvc.getOneRm(joinrmVO.rmno).status eq 3}">
			    	<span style="color:red;">已訂位</span>
			    </c:when>
			    <c:when test="${rminfoSvc.getOneRm(joinrmVO.rmno).status eq 4}">
			    	取消揪團
			    </c:when>
			    <c:otherwise>
			    	已結束
			    </c:otherwise>
			</c:choose>
		</td>
		<td>
		<div id="dialog_${joinrmVO.rmno}" title="成員列表">
			<jsp:include page="/front-end/room/roomMember.jsp"><jsp:param name="rmno" value="${joinrmVO.rmno}" /></jsp:include>
		</div>
	
		<button class="btn btn-outline-info btn-sm" id="opener_${joinrmVO.rmno}">參加成員</button>
		</td>
	
		<td>
		
		<div id="dialog2_${joinrmVO.rmno}" title="遊玩評價">
			<jsp:include page="/front-end/room/rate.jsp"><jsp:param name="rmno" value="${joinrmVO.rmno}" /></jsp:include>
		</div>
		<c:if test="${rminfoSvc.getOneRm(joinrmVO.rmno).status == 5 && joinrmVO.ratereport == 0}">			
			<button class="btn btn-warning btn-sm" id="opener2_${joinrmVO.rmno}">團員遊玩評價</button>
		</c:if>
		<c:if test="${rminfoSvc.getOneRm(joinrmVO.rmno).status == 1 || rminfoSvc.getOneRm(joinrmVO.rmno).status == 2 && rminfoSvc.getOneRm(joinrmVO.rmno).mbrno == mbrpfVO.mbrno}">
			<c:if test="${rminfoSvc.getOneRm(joinrmVO.rmno).mbrno == mbrpfVO.mbrno}">
			<form METHOD="post" ACTION="rminfo.do">
				<input type="hidden" name="status" value="3">
				<input type="hidden" name="report" value="${rminfoSvc.getOneRm(joinrmVO.rmno).report}">
				<input type="hidden" name="rmno" value="${joinrmVO.rmno}">
				<input type="hidden" name="action" value="update">
				<input class="btn btn-success btn-sm" type="submit" value="訂位" onclick="return(confirm('確定要訂位嗎？確定後將無法取消'))">
			</form>
			</c:if>
		</c:if>
		<c:if test="${rminfoSvc.getOneRm(joinrmVO.rmno).status <= 2 && rminfoSvc.getOneRm(joinrmVO.rmno).mbrno == mbrpfVO.mbrno}">
			<form METHOD="post" ACTION="rminfo.do">
				<input type="hidden" name="status" value="4">
				<input type="hidden" name="report" value="${rminfoSvc.getOneRm(joinrmVO.rmno).report}">
				<input type="hidden" name="rmno" value="${joinrmVO.rmno}">
				<input type="hidden" name="action" value="update">
				<input class="btn btn-danger btn-sm" type="submit" value="取消揪團" onclick="return(confirm('確認要取消嗎？確認後將無法恢復'))">
			</form>
		</c:if>
		<div id="dialog3_${joinrmVO.rmno}" title="檢舉店家">
			<jsp:include page="/front-end/shoprpdt/addShoprpdt.jsp"><jsp:param name="rmno" value="${joinrmVO.rmno}"/>
			<jsp:param name="ratereport" value="${joinrmVO.ratereport}" /></jsp:include>
		</div>
		<c:if test="${rminfoSvc.getOneRm(joinrmVO.rmno).status == 5 && joinrmVO.shopreport == 0}">
			<button class="btn btn-secondary btn-sm" id="opener3_${joinrmVO.rmno}">檢舉店家</button>
		</c:if>	
		</td>
	
<script>
(function($){
  $( function() {
    $( "#dialog_${joinrmVO.rmno}" ).dialog({
      autoOpen: false,
      show: {
        effect: "blind",
        duration: 1000
      },
      hide: {
        effect: "explode",
        duration: 1000
      },
      width: 400,
    });
 
    $( "#opener_${joinrmVO.rmno}" ).on( "click", function() {
      $( "#dialog_${joinrmVO.rmno}" ).dialog( "open" );
    });
  } );
  
  $( function() {
	    $( "#dialog2_${joinrmVO.rmno}" ).dialog({
	      autoOpen: false,
	      show: {
	        effect: "blind",
	        duration: 1000
	      },
	      hide: {
	        effect: "explode",
	        duration: 1000
	      },
	      width: 600,
	    });
	 
	    $( "#opener2_${joinrmVO.rmno}" ).on( "click", function() {
	      $( "#dialog2_${joinrmVO.rmno}" ).dialog( "open" );
	    });
	  } );
  
  $( function() {
	    $( "#dialog3_${joinrmVO.rmno}" ).dialog({
	      autoOpen: false,
	      show: {
	        effect: "blind",
	        duration: 1000
	      },
	      hide: {
	        effect: "explode",
	        duration: 1000
	      },
	      width: 600,
	    });
	 
	    $( "#opener3_${joinrmVO.rmno}" ).on( "click", function() {
	      $( "#dialog3_${joinrmVO.rmno}" ).dialog( "open" );
	    });
	  } );
})(jQuery_1_12_4);  
 </script>
	</tr>
	</c:forEach>

</table>
</div>	
</body>
<style>
#listAll{
	margin:20px auto;
	width:95%;

}
.btn{
	float:left;
	margin:0px 5px;
}
</style>
</html>