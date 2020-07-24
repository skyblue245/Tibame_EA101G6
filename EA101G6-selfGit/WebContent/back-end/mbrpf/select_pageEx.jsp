<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Mbrpf: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>
<meta charset="utf-8">
</head>


<body>

<%@ include file="/back-end/back-end_nav.jsp"%>
<%-- <%@ include file="/front-end/front-end-nav.jsp"%> --%>

<table id="table-1">
   <tr><td><img src="<%= request.getContextPath()%>/images/tomcat.png" width="100" height="100" border="0"><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Mbrpf: Home</p>

<h3>��Ƭd��:</h3>
	
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllMbrpf.jsp'>List</a> all Mbrpfs.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="mbrpf.do" >
        <b>��J�|���s�� :</b>
        <input type="text" name="mbrno">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="mbrpfSvc" scope="page" class="com.mbrpf.model.MbrpfService" />
   
  <li>
     <FORM METHOD="post" ACTION="mbrpf.do" >
       <b>��ܷ|���s��:</b>
       <select size="1" name="mbrno">
           <c:forEach var="mbrpfVO" items="${mbrpfSvc.all}" > 
          <option value="${mbrpfVO.mbrno}">${mbrpfVO.mbrno}
         </c:forEach> 
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="mbrpf.do" >
       <b>��ܷ|���s��:</b>
       <select size="1" name="mbrno">
         <c:forEach var="mbrpfVO" items="${mbrpfSvc.all}" > 
          <option value="${mbrpfVO.mbrno}">${mbrpfVO.mbrno}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
     </FORM>
  </li>
</ul>


<h3>�|���޲z</h3>

<ul>
  <li><a href='addMbrpf.jsp'>Add</a> a new Mbrpf.</li>
</ul>

<h3>�n�X</h3>
	<form method="post" action="mbrpf.do">
	<input type="hidden" name="action" value="logout">
	<input type="submit" value="�n�X">
	</form>


</body>
</html>