<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.mbrpf.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>



<html>
<head>
<title>會員資料 - listOneMbrpf.jsp</title>

<% 
MbrpfVO vo;
	if(request.getAttribute("mbrVO")!=null){
		 vo=(MbrpfVO)request.getAttribute("mbrVO");
		pageContext.setAttribute("mbrVO",vo);
	}else if(session.getAttribute("mbrpfVO")!=null){
		 vo=(MbrpfVO)session.getAttribute("mbrpfVO");
		pageContext.setAttribute("mbrVO",vo);
	}
%>

<style>
	table{
		width:100%;
	}
	
	td{
		text-align:center;
	}

.cho{
	display:none;
}

</style>

</head>


<body>

<%@ include file="/front-end/front-end-nav.jsp"%>

	
	<div class="container">
		<div class="row">
			<div class="col">
				
			</div>
			<div class="col">
				<table class="memInfo">
					<tr>
						<td colspan="2" style="text-align:center;" ><img width="200" height="175" src="<%= request.getContextPath()%>/mbrpf/mbrimg.do?mbrno=${mbrVO.mbrno}"></td>
					</tr>
						<tr><td>&nbsp;</td></tr>
						<tr><td>會員帳戶</td><td>${mbrVO.mbract}</td></tr>
						<tr><td>&nbsp;</td></tr>
						<tr><td>會員姓名</td><td>${mbrVO.mbrname}</td></tr>
						<tr><td>&nbsp;</td></tr>
						<tr><td>生日</td><td>${mbrVO.birth}</td></tr>
						<tr><td>&nbsp;</td></tr>
						<tr><td>性別</td><td>
							<c:if test="${mbrVO.sex ==1}">
							<c:out value="男"/>
							</c:if>
							<c:if test="${mbrVO.sex ==2}">
							<c:out value="女"/>
							</c:if>
						</td></tr>
						<tr><td>&nbsp;</td></tr>
						<tr><td>電子郵件</td><td>${mbrVO.mail}</td></tr>
						<tr><td>&nbsp;</td></tr>
						<tr><td>電話</td><td>${mbrVO.phone}</td></tr>
						<tr><td>&nbsp;</td></tr>
<%-- 						<tr><td>接收款項帳戶</td><td>${mbrVO.mbrac}</td></tr> --%>
<!-- 						<tr><td>&nbsp;</td></tr> -->
						<tr><td>暱稱</td><td>${mbrVO.nickname}</td></tr>
<!-- 						<tr><td>&nbsp;</td></tr> -->
<%-- 						<tr><td>點數餘額</td><td>${mbrpfVO.points}</td></tr> --%>
<!-- 						<tr><td>&nbsp;</td></tr> -->
<%-- 						<tr><td>被評價總人數</td><td>${mbrpfVO.ratedtotal}</td></tr> --%>
<!-- 						<tr><td>&nbsp;</td></tr> -->
<%-- 						<tr><td>被評價總星數</td><td>${mbrpfVO.startotal}</td></tr> --%>
<!-- 						<tr><td>&nbsp;</td></tr> -->
<%-- 						<tr><td>未出席次數</td><td>${mbrpfVO.unattend}</td></tr> --%>
<!-- 						<tr><td>&nbsp;</td></tr> -->
<%-- 						<tr><td>總參團次數</td><td>${mbrpfVO.ttattend}</td></tr> --%>
						<td>
						  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/mbrpf/mbrpf.do" style="margin-bottom: 0px;">
						     <input type="submit" value="修改" class="cho">
						     <input type="hidden" name="mbrno"  value="${mbrVO.mbrno}">
						     <input type="hidden" name="action"	value="getOne_To_Update"></FORM>
						</td>
						
				</table>
			</div>
			<div class="col"></div>
		</div>
	</div>
		
	
	
<!-- 	<table> -->
<!-- 		<tr> -->
<!-- 			<th>會員照片</th> -->
<!-- 			<th>會員編號</th> -->
<!-- 			<th>一般會員帳號</th> -->
<!-- 			<th>一般會員密碼</th> -->
<!-- 			<th>會員姓名</th> -->
<!-- 			<th>會員照片</th> -->
<!-- 			<th>出生年月日</th> -->
<!-- 			<th>性別</th> -->
<!-- 			<th>電子郵件</th> -->
<!-- 			<th>電話</th> -->
<!-- 			<th>接收款項帳戶</th> -->
<!-- 			<th>暱稱</th> -->
<!-- 			<th>點數餘額</th> -->
<!-- 			<th>一般會員狀態</th> -->
<!-- 			<th>被評價總人數</th> -->
<!-- 			<th>被評價總星數</th> -->
<!-- 			<th>未出席次數</th> -->
<!-- 			<th>總參團次數</th> -->
<!-- 			</tr> -->
<!-- 			<tr> -->
<%-- 			<td><img width="100" height="100" src="<%= request.getContextPath()%>/mbrpf/mbrimg.do?mbrno=${mbrpfVO.mbrno}"></td> --%>
<%-- 			<td>${mbrpfVO.mbrno}</td> --%>
<%-- 			<td>${mbrpfVO.mbract}</td> --%>
<%-- 			<td>${mbrpfVO.mbrpw}</td> --%>
<%-- 			<td>${mbrpfVO.mbrname}</td> --%>
<%-- 			<td><img width="100" height="100" src="<%= request.getContextPath()%>/mbrpf/mbrimg.do?mbrno=${mbrpfVO.mbrno}"></td> --%>
<%-- 			<td>${mbrpfVO.birth}</td> --%>
<%-- 			<td>${mbrpfVO.sex}</td> --%>
<%-- 			<td>${mbrpfVO.mail}</td> --%>
<%-- 			<td>${mbrpfVO.phone}</td> --%>
<%-- 			<td>${mbrpfVO.mbrac}</td> --%>
<%-- 			<td>${mbrpfVO.nickname}</td> --%>
<%-- 			<td>${mbrpfVO.points}</td> --%>
<%-- 			<td>${mbrpfVO.status}</td> --%>
<%-- 			<td>${mbrpfVO.ratedtotal}</td> --%>
<%-- 			<td>${mbrpfVO.startotal}</td> --%>
<%-- 			<td>${mbrpfVO.unattend}</td> --%>
<%-- 			<td>${mbrpfVO.ttattend}</td> --%>
<!-- 		</tr> -->
<!-- 	</table> -->

</body>
</html>