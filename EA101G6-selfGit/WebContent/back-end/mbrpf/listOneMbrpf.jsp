<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.mbrpf.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	MbrpfVO mbrpfVO = (MbrpfVO) request.getAttribute("mbrpfVO"); //mbrpfServlet.java(Concroller), 存入req的mbrpfVO物件
	
	if(request.getParameter("mbrpfno") != null){
		String mbrpfno = request.getParameter("mbrpfno");
		MbrpfService mbrpfSvc = new MbrpfService();
		mbrpfVO = mbrpfSvc.getOneMbrpf(mbrpfno);
		request.setAttribute("mbrpfVO", mbrpfVO);
	}
	
	
%>

<html>
<head>
<title>會員資料 - listOneMbrpf.jsp</title>



<style>
  table {
	width: 800px;
	background-color: rgba(255,255,255,0.7);
	margin-top: 5px;
	margin-bottom: 5px;
	text-align: center;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
    position:relative;
    min-width:80px;
  }
</style>

</head>


<body>

<%@ include file="/back-end/back-end_nav.jsp"%>

<div class="container">
	<div class="row">
		<div class="col">
			<table>
		<tr>
			<th>會員照片</th>
			<th>會員編號</th>
			<th>會員帳號</th>
<!-- 			<th>會員密碼</th> -->
			<th>會員姓名</th>
<!-- 			<th>會員照片</th> -->
			<th>生日</th>
			<th>性別</th>
			<th>電子郵件</th>
			<th>電話</th>
			<th>收款帳戶</th>
			<th>暱稱</th>
<!-- 			<th>點數餘額</th> -->
			<th>會員狀態</th>
			<th>被評價總人數</th>
			<th>被評價總星數</th>
			<th>未出席次數</th>
			<th>總參團次數</th>
		</tr>
		<tr>
			<td><img width="100" height="100" src="<%= request.getContextPath()%>/mbrpf/mbrimg.do?mbrno=${mbrpfVO.mbrno}"></td>
			<td>${mbrpfVO.mbrno}</td>
			<td>${mbrpfVO.mbract}</td>
<%-- 			<td>${mbrpfVO.mbrpw}</td> --%>
			<td>${mbrpfVO.mbrname}</td>
<%-- 			<td><img width="100" height="100" src="<%= request.getContextPath()%>/mbrpf/mbrimg.do?mbrno=${mbrpfVO.mbrno}"></td> --%>
			<td>${mbrpfVO.birth}</td>
			<td><%--此為性別判斷--%>
				<c:if test="${mbrpfVO.sex ==1}">
				<c:out value="男"/>
				</c:if>
				<c:if test="${mbrpfVO.sex ==2}">
				<c:out value="女"/>
				</c:if>		
			</td>
			<td>${mbrpfVO.mail}</td>
			<td>${mbrpfVO.phone}</td>
			<td>${mbrpfVO.mbrac}</td>
			<td>${mbrpfVO.nickname}</td>
<%-- 			<td>${mbrpfVO.points}</td> --%>
			<td><%--此為狀態判斷--%>
				<c:if test="${mbrpfVO.status ==1}">
				<c:out value="正常"/>
				</c:if>
				<c:if test="${mbrpfVO.status ==2}">
				<c:out value="停權"/>
				</c:if>	</td>
			<td>${mbrpfVO.ratedtotal}</td>
			<td>${mbrpfVO.startotal}</td>
			<td>${mbrpfVO.unattend}</td>
			<td>${mbrpfVO.ttattend}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/mbrpf/mbrpf.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="mbrno"  value="${mbrpfVO.mbrno}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			<br>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/mbrpf/mbrpf.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="mbrno"  value="${mbrpfVO.mbrno}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</table>		
		<a href="<%=request.getContextPath()%>/back-end/mbrpf/listAllMbrpf.jsp" style="background-color:rgba(255,255,255,0.7);"><font color="red">回到一般會員管理</font></a>
		</div>
	</div>
</div>



</body>
</html>