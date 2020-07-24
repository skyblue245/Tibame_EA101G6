<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Msgrp: Home</title>

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

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>IBM Msgrp: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Msgrp: Home</p>

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
  <li><a href='listAllMsgrp.jsp'>List</a> all Msgrps.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="msgrp.do" >
        <b>��J�d���s�� (�pBMS0001):</b>
        <input type="text" name="msgrpno">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="msgrpSvc" scope="page" class="com.msgrp.model.MsgrpService" />
   
  <li>
     <FORM METHOD="post" ACTION="msgrp.do" >
       <b>��ܯd���s��:</b>
       <select size="1" name="msgrpno">
         <c:forEach var="msgrpVO" items="${msgrpSvc.all}" > 
          <option value="${msgrpVO.msgrpno}">${msgrpVO.msgrpno}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="msgrp.do" >
       <b>��ܷ|���s��:</b>
       <select size="1" name="msgrpno">
         <c:forEach var="msgrpVO" items="${msgrpSvc.all}" > 
          <option value="${msgrpVO.msgrpno}">${msgrpVO.msgno}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
     </FORM>
  </li>
</ul>


<h3>�d���޲z</h3>

<ul>
  <li><a href='addMsgrp.jsp'>Add</a> a new Msgrp.</li>
</ul>

</body>
</html>