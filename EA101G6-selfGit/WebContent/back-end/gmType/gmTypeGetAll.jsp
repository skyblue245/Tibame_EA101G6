<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ page import="com.mall.model.*"%>
<%@ page import="com.gmTypeDt.model.*"%>
<%@ page import="com.gmType.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>

<style>


div.gmtypezone{
	width:300px;
	background-color:aliceblue;
	text-algin:center;
	border:solid;
	border-radius:5px;
	position:absolute;	
	left:50%;
	margin-left:-150px;
	z-index:998;
	top:100px;
	display:none;
}

div.gmtypezone div{
text-align:center;
margin:10px 0px;
height:40px;
}

div.gmtypezone button{
	margin-left:10px;
	border-radius: 4px;
	background-color: #e7e7e7;
	color: #000000;
	font-size: 16px;
}

div.gmtypezone p{
 display:inline-block;
 mragin:0px;
}



div#delalert{
	height:100px;
	width:200px;
	background-color:#ffffff;
	text-algin:center;
	border:solid;
	border-radius:5px;
	position:absolute;	
	left:50%;
	margin-left:-100px;
	z-index:999;
	top:200px;
	display:none;
	padding:10px 0px;

}
div#delalert div{
	text-align:center;
	margin:10px 0px;
}



</style>

</head>
<body>


	
		<div class="gmtypezone" title="�C������">
		<c:forEach var="gmType" items="${gmTypeSvc.getAll()}">
  			<div class="${gmType.typeNo}"><p>${gmType.typeName}</p><button class="deltypebtn btn btn-secondary" value="${gmType.typeNo}">�R��</button></div>
  		</c:forEach>

  			<div id="tampAddDiv">�п�J�n�s�W���C������:<input id="typeNameInput" name="typeName" type="text" required></div>
  			<br>
  			<div><button type="submit" class="confirm addtypebtn btn btn-secondary" >�s�W�C������</button> <button type="button" class="typecancel btn btn-secondary">����</button></div>
  			
  			
  		
		</div>
	

	<div id="delalert">
	<div>�T�w�n�R��������?</div>
	<div><button class="confirm">�T�w</button><button class="cnacel" style="margin-left:20px;">����</button></div>
	</div>


</body>
</html>