<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="com.joinrm.model.*" %>
<%@ page import="java.util.*"%>
<%@ page import="com.rminfo.model.*"%>
<%@ page import="com.shop.model.*" %>

<%
	ShopVO shopVO = (ShopVO)session.getAttribute("shopAcount");
	RminfoService rminfoSvc = new RminfoService();
	List<RminfoVO> list = rminfoSvc.findByShopno(shopVO.getShopno());
	pageContext.setAttribute("list", list);
%>

<jsp:useBean id="joinrmSvc" scope="page" class="com.joinrm.model.JoinrmService" />
<jsp:useBean id="mbrpfSvc" scope="page" class="com.mbrpf.model.MbrpfService" />
<jsp:useBean id="shopSvc" scope="page" class="com.shop.model.ShopService" />
<!DOCTYPE html>
<html>
<head>

<title>���a�w���C��</title>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript"> var jQuery_1_12_4 = $.noConflict(true); </script>
</head>
<body>
<%@ include file="/front-end/front-end-nav.jsp"%>
<div class="container-fluid">
	<div class="row">
	<div class="col-sm-1"></div>
		<div class="col-sm-2">
			<div style="witdh: 20px;">
				<ul class="list-group list-group-item-action">
					<li class="list-group-item list-group-item-action" onclick="location.href='<%=request.getContextPath()%>/front-end/shop/shopArea.jsp';">�ڪ���T</li>	
					<li class="list-group-item list-group-item-action" id="goGmlist" onclick="location.href='<%=request.getContextPath()%>/front-end/gmlist/addGmlist.jsp';">�ڪ��C��</li>
					
					<li class="list-group-item list-group-item-action" id="goShopbk">�ڪ�����</li>
					
					<li class="list-group-item list-group-item-action" id="goUpdate">��况�a���</li>
					
					<li class="list-group-item list-group-item-action active" onclick="location.href='<%=request.getContextPath()%>/front-end/room/shop_roomList.jsp';">�ڪ��q��</li>
				</ul>
			</div>
		</div>	
<div id="listAll" class="col-sm-8">
	<table class="table table-striped">
	<tr>
		<th style="width:10%">�ЦW</th>
		<th style="width:6%">�ХD</th>
		<th style="width:10%">�C�����a</th>
		<th style="width:5%">�H��</th>
		<th style="width:10%">�C���ɶ�</th>
		<th style="width:15%">�w�p�C��</th>
		<th style="width:10%">�Ƶ�</th>
		<th style="width:10%">�ж����A</th>
		<th style="width:10%">�^�����A</th>
		<th></th>
	</tr>

	<c:forEach var="rminfoVO" items="${list}">
		<c:if test="${rminfoVO.status == 3 || rminfoVO.status == 5}">
	<tr>
		<td>${rminfoVO.naming}</td>
		<td>${mbrpfSvc.getOneMbrpf(rminfoVO.mbrno).mbrname}</td>
		<td>${shopSvc.getOneShop(rminfoVO.shopno).shopname}</td>
		<td style="text-align:center">${fn:length(joinrmSvc.findByPK(rminfoVO.rmno,''))}</td>
		<td><fmt:formatDate value="${rminfoVO.starttime}" pattern="yyyy-MM-dd HH:mm" />
			~<fmt:formatDate value="${rminfoVO.endtime}" pattern="HH:mm" /></td>
		<td>${rminfoVO.game}</td>
		<td>${rminfoVO.remarks}</td>	
		<td>
			<c:choose>
    			<c:when test="${rminfoVO.status eq 0}">
    				���ݦ����[�J
   		 		</c:when>
			    <c:when test="${rminfoVO.status eq 1}">
			    	�H�Ƥw�F�i�q��U��
			    </c:when>
			    <c:when test="${rminfoVO.status eq 2}">
			    	�H�Ƥw��
			    </c:when>
			    <c:when test="${rminfoVO.status eq 3}">
			    	�w�q��
			    </c:when>
			    <c:when test="${rminfoVO.status eq 4}">
			    	��������
			    </c:when>
			    <c:otherwise>
			    	�w����
			    </c:otherwise>
			</c:choose>
		</td>
		<td>
			<c:choose>
    			<c:when test="${rminfoVO.report eq 0}">
    				�|���^��
    			</c:when>
			    <c:otherwise>
			    	�w�^��
			    </c:otherwise>
			</c:choose>
		</td>
		<td>
			<div id="dialog_${rminfoVO.rmno}" title="����^��">
				<jsp:include page="/front-end/room/shop_report.jsp"><jsp:param name="rmno" value="${rminfoVO.rmno}" /></jsp:include>
			</div>
			<c:if test="${rminfoVO.report == 0 && rminfoVO.status == 5}">
				<button id="opener_${rminfoVO.rmno}">����^��</button>
			</c:if>
			<script>
			(function($){
			  $( function() {
			    $( "#dialog_${rminfoVO.rmno}" ).dialog({
			      autoOpen: false,
			      width:530,
			      show: {
			        effect: "blind",
			        duration: 1000
			      },
			      hide: {
			        effect: "explode",
			        duration: 1000
			      }
			    });
			 
			    $( "#opener_${rminfoVO.rmno}" ).on( "click", function() {
			      $( "#dialog_${rminfoVO.rmno}" ).dialog( "open" );
			    });
			  } );
			})(jQuery_1_12_4);  
			</script>
		</td>
	</tr>
	</c:if>
	</c:forEach>

</table>
</div>
	</div>
</div>
<FORM id="gmlist" METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/gmlist/gmlist.do">
	<input type="hidden" name="shopno" value="${shopVO.shopno}">
	<input type="hidden" name="action" value="getSome_For_Display">
</FORM>
<FORM id="shopbk" METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/shopbk/shopbk.do">
	<input type="hidden" name="shopno" value="${shopVO.shopno}">
	<input type="hidden" name="action" value="getSome_For_Display2">
</FORM>
<FORM id="getOne_For_Update" METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/shop/shop.do">
	<input type="hidden" name="action" value="getOne_For_Update">
</FORM>
		<script>
			$(document).ready(function() {
				$("#goUpdate").click(function() {
					$("#getOne_For_Update").submit();
				})
				$("#goShopbk").click(function() {
					$("#shopbk").submit();
				})
			})
		</script>
</body>

<style>
#listAll{ 
	margin:5px auto;
	width:100%;

}
.floatButton{
	float:left;
	margin:0px 5px;
}
</style>
</html>