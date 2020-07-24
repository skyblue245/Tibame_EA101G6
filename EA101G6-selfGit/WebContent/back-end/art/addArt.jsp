<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.art.model.*"%>
<%@ page import="com.emp.model.*" %>

<%
	ArtVO artVO = (ArtVO) request.getAttribute("artVO");
	EmpVO eVO = (EmpVO) session.getAttribute("eVO");
	pageContext.setAttribute("eVO", eVO);
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ckeditor/ckeditor.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<title>員工資料新增</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
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
  textarea {
  	width:33%;
  	height:21%;
  }

  table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
  .category-1{
   	margin-top:20px;
   	margin-bottom:20px;
   	
   }
   .add-1{
   	margin-top: 100px;
   	margin-bottom: 100px;
   }
   .img-1{
   	width: 100%;
   	margin-bottom: 25px;
   }
   .ta1{
   	margin-top: 40px;
   	margin-bottom: 40px;
   }
   .logoutPIC{
   	margin-top: 18%;
   }
</style>
<link rel="stylesheet" href="./vendors/bootstrap/css/bootstrap.min.css">
</head>

<body bgcolor='white'>

<%@ include file="/back-end/back-end_nav.jsp"%>





<%-- 錯誤列表 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<div class="bg-white tm-block col-md-10 offset-md-1 add-1">
		<div class="row">
			<div class="col-12">
				<h2 class="tm-block-title d-inline-block">Add Article</h2>
			</div>
		</div>
		
		<FORM METHOD="post" class="tm-edit-product-form" ACTION="<%=request.getContextPath()%>/back-end/art/art.do" name="form1" enctype="multipart/form-data">
		<div class="row mt-4 tm-edit-product-row">
			<div class="col-xl-7 col-lg-7 col-md-12">			
					
					
					
					<input type="hidden" name="mbrno" value="${eVO.empno}">
					
					<div class="input-group mb-3">
						<label for="name"
							class="col-xl-4 col-lg-4 col-md-4 col-sm-5 col-form-label">文章標題</label> 
							<input type="text" name="arttt" value="<%=(artVO == null) ? "" : artVO.getArttt()%>" class="form-control validate col-xl-9 col-lg-8 col-md-8 col-sm-7"/>
					</div>
					<div class="ta1">
						<textarea  name="detail" id="detail"><%=(artVO == null) ? "" : artVO.getDetail()%></textarea>

						<script type="text/javascript">
							window.onload = function() {
								CKEDITOR.replace('detail');
							};
						</script>
					</div>	
					<div class="input-group mb-3 category-1">
						<label for="category" class="col-xl-4 col-lg-4 col-md-4 col-sm-5 col-form-label">文章分類</label>
						<jsp:useBean id="atypeSvc" scope="page" class="com.atype.model.AtypeService"/>
						<select size="1" name="atno" class="custom-select col-xl-5 col-lg-4 col-md-4 col-sm-3">
							<c:forEach var="atypeVO" items="${atypeSvc.all}">
								<option value="${atypeVO.atno}"
									${(artVO.atno==atypeVO.atno)? 'selected':'' }>${atypeVO.atname}
							</c:forEach>
						</select>
						<div class="ml-auto col-xl-2 col-lg-2 col-md-2 col-sm-1 pl-0 ">
							<button type="submit" class="btn btn-primary">Add</button>
							<input type="hidden" name="action" value="insert">
						</div>
					</div>
					
					<input type="hidden" name="status" value="0" ${(artVO.status==0)? 'checked' : '' } checked>
					
					
				
			</div>
			<div class="col-xl-4 col-lg-4 col-md-12 mx-auto mb-4">
				
  				<img class="img-1" id="prePic" src="<%=request.getContextPath()%>/art/artpic.do?artno=${artVO.artno}"/>
				<input type = "file" name = "apic" id="upPic" class="btn btn-primary d-block mx-auto"/> 
				
			</div>
		</div>
		</FORM>
	</div>


<script>
	$("#upPic").change(function(){
		readURL(this);
	});
	
	function readURL(input){
		if(input.files){
			var reader = new FileReader();
			reader.onload = function(e){
				$("#prePic").attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
</script>


<script src="./vendors/jquery/jquery-3.4.1.min.js"></script>
<script src="./vendors/popper/popper.min.js"></script>
<script src="./vendors/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>