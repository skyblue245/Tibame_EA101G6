<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.joinrm.model.*"%>

<%
	JoinrmService joinrmSvc2 = new JoinrmService();
	List<JoinrmVO> list2 = joinrmSvc2.findByPK(request.getParameter("rmno"),"");
	pageContext.setAttribute("list2",list2);
%>
<jsp:useBean id="mbrpfSvc" scope="page" class="com.mbrpf.model.MbrpfService" />
<!DOCTYPE html>
<html>
<head>

<title>┬丁ずΘ</title>
</head>
<body>
<div class="accordion" id="accordionExample">
  <c:forEach var="joinrmVO2" items="${list2}">
    <div class="card">
	  <div class="card-header" id="heading_${joinrmVO2.mbrno}_${joinrmVO2.rmno}">
        <h5 class="mb-0">
		  <button class="btn btn-light" type="button" data-toggle="collapse" data-target="#collapse_${joinrmVO2.mbrno}_${joinrmVO2.rmno}" aria-expanded="true" aria-controls="collapse_${joinrmVO2.mbrno}_${joinrmVO2.rmno}">
			${mbrpfSvc.getOneMbrpf(joinrmVO2.mbrno).mbrname}
		  </button>
	    </h5>
	  </div>

	  <div id="collapse_${joinrmVO2.mbrno}_${joinrmVO2.rmno}" class="collapse" aria-labelledby="heading_${joinrmVO2.mbrno}_${joinrmVO2.rmno}" data-parent="#accordionExample">
        <div class="card-body">
        	蝶基だ计:
        	<c:choose>
    			<c:when test="${mbrpfSvc.getOneMbrpf(joinrmVO2.mbrno).ratedtotal eq 0}">
                    	 ﹟礚蝶基
                </c:when>
	                <c:otherwise>
	                	<fmt:formatNumber type="number" value="${mbrpfSvc.getOneMbrpf(joinrmVO2.mbrno).startotal / mbrpfSvc.getOneMbrpf(joinrmVO2.mbrno).ratedtotal}" maxFractionDigits="1" />
	                </c:otherwise>
                </c:choose>
             		( <span style="color:blue;">${mbrpfSvc.getOneMbrpf(joinrmVO2.mbrno).ratedtotal}</span>蝶基 )
             		<a href="<%=request.getContextPath()%>/front-end/room/memberRate.jsp?ratedmbrno=${joinrmVO2.mbrno}" target="_blank"><span style="color:blue;">┮Τ蝶阶</span></a>
                   <br> 把Ω计: ${mbrpfSvc.getOneMbrpf(joinrmVO2.mbrno).ttattend} ( <span style="color:red;">${mbrpfSvc.getOneMbrpf(joinrmVO2.mbrno).unattend}</span>Ωゼ畊 )
        </div>
      </div>
    </div>
  </c:forEach>	
</div>
</body>
</html>