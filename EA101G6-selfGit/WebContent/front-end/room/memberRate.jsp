<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.rate.model.*"%>
<%@ page import="java.util.*"%>
<%
	RateService rateSvc = new RateService();
	String ratedmbrno = request.getParameter("ratedmbrno");
	List<RateVO> list = rateSvc.findByRatedmbrno(ratedmbrno);
	pageContext.setAttribute("list", list);
%>
<jsp:useBean id="mbrpfSvc" scope="page" class="com.mbrpf.model.MbrpfService" />
<!DOCTYPE html>
<html>
<head>

<title>評價列表</title>
<style>
	.ratings {
	    position: relative;
	    vertical-align: middle;
	    display: inline-block;
	    color: #b1b1b1;
	    overflow: hidden;
	}
	.full-stars {
	    position: absolute;
	    left: 0;
	    top: 0;
	    white-space: nowrap;
	    overflow: hidden;
	    color: #fde16d;
	}
	.empty-stars:before, .full-stars:before {
	    content:"\2605\2605\2605\2605\2605";
	    font-size: 14pt;
	}
	.empty-stars:before {
	    -webkit-text-stroke: 1px #848484;
	}
	.full-stars:before {
	    -webkit-text-stroke: 1px orange;
	}
	.comment {
		margin:10px 15px;
		width:300px;
	}
	.allComment{
		margin:0px auto;
		max-width:60%;
	}
	h3{
		color:red;
		margin:auto 50px;
	}
</style>
</head>
<body>
<%@ include file="/front-end/front-end-nav.jsp"%>

<h3>${mbrpfSvc.getOneMbrpf(param.ratedmbrno).mbrname}的所有評論:</h3>
<div class="allComment">
	<c:forEach var="rateVO" items="${list}">
	  <div class="comment">	
		<div>
			 <font style="color: blue">${mbrpfSvc.getOneMbrpf(rateVO.ratingmbrno).mbrname}</font>
		</div>
		<div class="ratings">
		    <div class="empty-stars"></div>
		    <div class="full-stars" style="width:${rateVO.score * 20}%"></div>
		</div>
		<font style="color: grey"><fmt:formatDate value="${rateVO.ratetime}" pattern="yyyy-MM-dd" /></font>
		<div>
			${rateVO.detail}<hr>
		</div>
	  </div>	
	</c:forEach>
</div>
</body>
</html>