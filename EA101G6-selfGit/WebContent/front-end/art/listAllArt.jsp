<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*" %>
<%@ page import="com.art.model.*" %>
<%@ page import="com.mbrpf.model.*" %>


<% 
	
	MbrpfVO mbrpfVO = (MbrpfVO)session.getAttribute("mbrpfVO");
	ArtService artSvc = new ArtService();
	List<ArtVO> list = artSvc.getArtsByStatus(0);
	pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>討論區</title>

	<meta charset="utf-8">

<style>
 
  h4 {
    color: blue;
    display: inline;
  }
  
  img {
  	width:100%;
  	height: 348px;
  }
  .img-1{
  	width:500px;
  	height: 600px;
  }
  	
  table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
  .icon{
    width:20px;
    height:20px;
  }
  p{
  	overflow:hidden;
  	text-overflow:ellipsis;
  	display:-webkit-box; 
	-webkit-box-orient:vertical;
	-webkit-line-clamp:2; 
  } 
  nav{
  	margin-top: 25px;
  }
  
  .breadcrumb{
  	margin-top: -35px;
  	margin-left: 35px;
  	margin-bottom: -35px;
  	background-color: white;
  	height: 50px;
  }
  .errorMsgs{
  	margin-top: 25px;
  	margin-left: 25px;
  }
  .button-1{
  	background-color: white;
  	border: 5px solid white;
  }
  body{
   	background-image: url('<%=request.getContextPath()%>/images/bg5.png');
  }
  
  img.h1-png{
  	position: fixed;
  	bottom: 30px;
  	right: 15px;
  	width: 200px;
  	height: 155px;
  }
  
  .fffuuu{
  	margin-bottom: 50px;
  }
  .tdtitle{
  min-width:105px;
  }
 
 

</style>

</head>



<body>

<%@ include file="/front-end/front-end-nav.jsp"%>   
 
    <main role="main">
	  <!-- 導覽列表 -->
      <nav class="navbar navbar-light bg-gradient-info shadow p-3 mb-5 rounded">
      	<a class="navbar-brand">Talking on Board</a>
      	
      	
		
      	<form class="form-inline my-2 my-lg-0" METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/art/art.do">
      		<input class="form-control mr-sm-2" type="search" placeholder="Search" name="keyWord" aria-label="Search">
      		<button class="btn btn-outline-success my-2 my-sm-0" type="submit" name="action" value="getOne_Key_Display">Search</button>
      	</form>
      </nav>
      
      <!-- 麵包屑 -->
      <nav aria-label="breadcrumb" class="d-inline-flex">
  			<ol class="breadcrumb ">
    			<li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/front-end/index.jsp">Home</a></li>
    			<li class="breadcrumb-item active" aria-current="page">Article</li>
 			 </ol>
	  </nav>
	  
	  <!-- 錯誤訊息 -->
	  <div class="errorMsgs">
	  	<c:if test="${not empty errorMsgs}">
			<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color:red">${message}</li>
			</c:forEach>
			</ul>
	  	</c:if>
	  </div>
		
	  <!-- card -->
      <div class="album py-5 bg-transparent">
        <div class="container">

          <div class="row">
          <%@ include file="page1.file" %>
          <c:forEach var="artVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
          
          <FORM class="col-md-4" METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/art/art.do" style="margin-bottom: 0px;">
            
              <div class="card mb-4 box-shadow shadow p-3 mb-5 bg-white rounded">
              	
                <a target="_self" href="<%=request.getContextPath()%>/front-end/art/art.do?action=get_One_Detail&artno=${artVO.artno}"><img class="card-img-top" src="<%=request.getContextPath()%>/art/artpic.do?artno=${artVO.artno}" alt="Card image cap"></a>
               
                <div class="card-body ">
                 <h5 class="card-title">${artVO.arttt}</h5>
                 <p class="card-text">${artVO.detail}</p>
                  <div class="d-flex justify-content-between align-items-center">
                    <div class="btn-group">
                      <button type="submit" name="action" value="get_One_Detail" class="btn btn-sm btn-outline-secondary">繼續閱讀...</button>
                      <input type="hidden" name="artno" value="${artVO.artno}"/>
                      
                    </div>
                    <small class="text-muted">${artVO.pdate}</small>
                  </div>
                </div>
              </div>
              
             </FORM>  </c:forEach>
       		<c:if test="${mbrpfVO != null}">
       		<a href="<%=request.getContextPath()%>/front-end/art/addArt.jsp"><img class="h1-png col-md-2" src="<%=request.getContextPath()%>/images/h1.png" title="Add Article"></a>
       		</c:if>
          </div>
        </div>
      </div>

    </main>
    <footer class="fffuuu">
    	<div class="d-flex justify-content-center">
    		<%@ include file="page2.file" %>
    	</div>
    
    </footer>
    
    
    
	<input type="hidden" id="addArt" value='${addArt}'>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jsForArt/wsForArt.js"></script>
	
	


	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

</body>
</html>