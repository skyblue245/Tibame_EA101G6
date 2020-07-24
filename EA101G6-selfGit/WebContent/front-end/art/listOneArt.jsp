<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*" %>
<%@ page import="com.art.model.*" %>
<%@ page import="com.mbrpf.model.*" %>
<%@ page import="com.emp.model.*" %>

<%
	ArtVO artVO = (ArtVO) request.getAttribute("artVO");
	MbrpfVO mbrpfVO = (MbrpfVO)session.getAttribute("mbrpfVO");
	
	if(artVO!=null){
		EmpService empSvc = new EmpService();
		EmpVO empVO = empSvc.getOneEmp(artVO.getMbrno());
		pageContext.setAttribute("empVO", empVO);
		session.setAttribute("tampEmpVo",empVO);
		session.setAttribute("tampArtVo",artVO);
	}else{
		artVO=(ArtVO)session.getAttribute("tampArtVo");
		pageContext.setAttribute("artVO", artVO);
		EmpVO empVO=(EmpVO)session.getAttribute("tampEmpVo");
		pageContext.setAttribute("empVO", empVO);
	}
%>


<html>
<head>
<title>文章資料 - listOneEmp.jsp</title>

	<meta charset="utf-8">
	
	

	
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
  table {
	width: 600px;
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
  img {
  	width:400px;
  	height:500px;
  }
  .icon{
    width:20px;
    height:20px;
  }
  .icon-2{
    width:25px;
    height:25px;
  }
  .d1{
  	margin-top: 50px;
  }
  .t1{
  	margin-top: 50px;
  }
  .breadcrumb{
  	margin-top: 35px;
  	margin-left: 35px;
  	
  	background-color: white;
  }
  .ha1{
  	text-align: center;
  	margin-top: 50px;
  	margin-bottom: 50px;
  	
  }
  .breadcrunm-2{
  	margin-left: -35px;
  }
  body{
   	background-image: url('<%=request.getContextPath()%>/images/bg4.png');
  }
  .rp-area{
  	width: 450px;
  	height: 50px;
  }
  .rp-2{
  	margin-top: 350px;
  }
  #d2{
  	margin-top: 20px;
  	margin-bottom: 33px;
  }
  .tea1{
  	margin-left: 20px;
  	margin-bottom: 10px;
  	margin-top: 10px;
  }
  #mem1{
  	margin-top: -10px;
  	margin-left: 35px;
  	width: 50px;
  	height: 50px;
  }
  .artmsg{
  	margin-left: 30px;
  }
  #baba{
  	width: 35px;
  	height: 35px;
  }
 #h33{
 	margin-top: 10px;
 }
 .updown{
 	margin-top: 10px;
 	margin-bottom: 20px;
 }
 #bbttnn{
 	margin-left: 406px;
 }
 .bread1{
 	margin-bottom: 50px;
 }
 .rapper{
 	text-align: center;
 }
 .msgh{
 	height: 70px
 }
  
</style>

</head>



<body>

<%@ include file="/front-end/front-end-nav.jsp"%>  
    
	<!-- 麵包屑 -->
    <nav aria-label="breadcrumb" class="d-inline-flex bread1">
  			<ol class="breadcrumb ">
    			<li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/index.html">Home</a></li>
    			<li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/front-end/art/listAllArt.jsp">Article</a></li>
    			<li class="breadcrumb-item active" aria-current="page">${artVO.arttt}</li>
 			</ol>
	</nav>
    
	
	<jsp:useBean id="mbrSvc" class="com.mbrpf.model.MbrpfService"/>
	<%
		MbrpfVO mbrVO = mbrSvc.getOneMbrpf(artVO.getMbrno());
		pageContext.setAttribute("mbrVO", mbrVO);
	%>

    <div class="card col-md-8 offset-md-2" id="d1">
    	<header class="ha1">
    		<h1 class="card-title col-md-12">${artVO.arttt}</h1>
    		
    		<FORM METHOD="POST"
					ACTION="<%=request.getContextPath()%>/front-end/art/art.do">
    		<nav aria-label="breadcrumb" class="d-inline-flex breadcrunm-2 ">
  			<ol class="breadcrumb bg-white">
  			
  				<c:if test="${empVO == null}">
  					<li class="breadcrumb-item"><a target="_self"  href="<%=request.getContextPath()%>/mbrpf/mbrpf.do?action=getOne_To_Display&mbrno=${artVO.mbrno}" class="text-black"><span class="d-md-inline-block"><img class="icon-2" src="<%=request.getContextPath()%>/images/User-icon.png">${mbrVO.mbrname}</span></a></li>
  				</c:if>
  				<c:if test="${empVO != null}">
  					<li class="breadcrumb-item"><img class="icon-2" src="<%=request.getContextPath()%>/images/User-icon.png">[管理員] ${empVO.empname}</span></li>
  				</c:if>
    			
    			
    			<li class="breadcrumb-item active" aria-current="page"><img class="icon-2" src="<%=request.getContextPath()%>/images/cal-icon.png">${artVO.pdate}</li>
 			</ol>
			</nav>


			<c:if test="${mbrpfVO.mbrno != artVO.mbrno && mbrpfVO != null}">
				<!-- Button trigger modal -->
				<button type="button" class="btn btn-primary" data-toggle="modal"
					data-target="#exampleModal">Report</button>


				<!-- Modal -->
				<div class="modal fade" id="exampleModal" tabindex="-1"
					role="dialog" aria-labelledby="exampleModalLabel"
					aria-hidden="true">
					<div class="modal-dialog" role="document">
						<div class="modal-content rp-2">
							<div class="modal-header">
								<h5 class="modal-title" id="exampleModalLabel">檢舉內容</h5>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<input type="text" class="rp-area" id="rp_detail"
									name="rp_detail" placeholder="輸入檢舉原因">
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">關閉</button>
								<button type="submit" name="action" id="action" value="insert"
									id="demo1" class="btn btn-primary" data-dismiss="modal">送出檢舉</button>
								<input type="hidden" name="artno" id="artno"
									value="${artVO.artno}"> <input type="hidden"
									name="mbrno" id="mbrno" value="${artVO.mbrno}"> <input
									type="hidden" name="status" id="status" value="0">

							</div>
						</div>
					</div>
				</div>
			</c:if>
			
			
			<c:if test="${mbrpfVO.mbrno == artVO.mbrno && mbrpfVO != null}">
				<!-- 修改按鈕 -->
				
					<button type="submit" class="btn btn-primary">修改</button>
					<input type="hidden" name="artno" value="${artVO.artno}"> <input
						type="hidden" name="action" value="getOne_For_Update">
				
			</c:if>
			</FORM>



		</header>
    	
  		<img class="card-img-top col-md-8 offset-md-2" src="<%=request.getContextPath()%>/art/artpic.do?artno=${artVO.artno}" alt="Card image cap">
  		<div class="card-body col-md-8 offset-md-2">
    		<p class="card-text">${artVO.detail}</p>	
  		</div>
	</div>
	
	
	<jsp:useBean id="artSvc" class="com.art.model.ArtService" />
	
	<c:forEach var="artVO2" items="${artSvc.all}" varStatus="s">
		<script>
			function presses${s.count}(){
				document.open("<%=request.getContextPath()%>/front-end/art/art.do?action=get_One_Detail&artno=${artVO2.artno}", "_self" ,"");
			}
		</script>
		
		<c:if test="${artVO2.artno == artVO.artno}">
			<input type="hidden" value="${ sc = s.count }"/>
		</c:if>
		
		<c:if test="${s.first == true}">
			<input type="hidden" value="${ fc = s.count }"/>
		</c:if>
		<c:if test="${s.last == true}">
			<input type="hidden" value="${ lc = s.count }"/>
		</c:if>
		
	</c:forEach>
	
	
	<!-- 上下頁按鈕 -->
	


	<nav aria-label="breadcrumb"
		class="breadcrumb-nav col-md-8 offset-md-2 bg-transparent updown">
		<ol class="breadcrumb d-flex justify-content-center bg-transparent">
			<li class="rapper"><c:if test="${fc != sc }">
					<a href="javascript:presses${sc-1}()"><button type="button"
							class="btn btn-primary">上一篇</button></a>
				</c:if> <c:if test="${lc != sc }">
					<a href="javascript:presses${sc+1}()"><button type="button"
							class="btn btn-primary">下一篇</button></a>
				</c:if>
		</ol>
	</nav>










	<!-- 留言區 -->
	<jsp:useBean id="msgSvc" scope="page" class="com.msg.model.MsgService"/>

	<c:if test="${mbrpfVO != null}">

		

		<footer>

			<div class="card col-md-8 offset-md-2" id="d2">
				<h3 id="h33" class="col-md-12">
					<img id="baba"
						src="<%=request.getContextPath()%>/front-end/images/post.jpg">發布迴響
				</h3>

				<FORM METHOD="POST"
					ACTION="<%=request.getContextPath()%>/front-end/msg/msg.do">
					<p>
						<input class="col-md-11  tea1" type="text" name="detail"
							placeholder="說點什麼吧...">
						<button class="btn btn-secondary" type="submit" name="action"
							value="insert">發表</button>
						<input type="hidden" name="mbrno" value="${mbrpfVO.mbrno}">
						<input type="hidden" name="status" value="0"> <input
							type="hidden" name="artno" value="${artVO.artno}">
					</p>
				</FORM>


				

			</div>
		</footer>
	</c:if>

	<c:forEach var="msgVO"
		items="<%=msgSvc.getAllByArtno(artVO.getArtno())%>">


		<input type="hidden"
			value="${mbrname = mbrSvc.getOneMbrpf(msgVO.mbrno).mbrname}" />

		<div class="card col-md-8 offset-md-2 float-left msgh">
			<div class="card-body">
				<a><img class="float-left" id="mem1"
					src="<%=request.getContextPath()%>/mbrpf/mbrimg.do?mbrno=${msgVO.mbrno}"><span class="float-left">${mbrname}
						:</span></a>
				<p class="artmsg float-left ">
					<span>${msgVO.detail}</span>
				</p>	
			</div>
		</div>
		
	
		
	</c:forEach>
	
	<input type="hidden" id="reEdit" value='${reEdit}'>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jsForArt/wsForArt.js"></script>

	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
	
	<script>
    $(document).ready(function(){
    	$("#action").click(function(){
        	if ($("#rp_detail").val() == ''){
        		swal.fire("失敗!", "請輸入檢舉內容!", "error");
        	} else{
        		$.ajax({
            		url: "<%=request.getContextPath()%>/artrp/artrp.do",
            		type: "POST",
            		data: { action: $("#action").val(), artno: $("#artno").val(), rp_detail: $("#rp_detail").val(), mbrno: $("#mbrno").val(), status: $("#status").val()},
            		success: function(){
            			swal.fire("Good job!", "檢舉成功!", "success");
            		}
            	})
        	}
        	
        })
    	
    })
    
  
    
//     $.post("ArtrpServlet.java",
//     		{ action: $("#action").val(), artno: $("#artno").val(), rp_detail: $("#rp_dateil").val(), mbrno: $("#mbrno").val(), status: $("#status").val()},
//     		function(data, status){
//     			if(status == "success"){
//     				swal("Good job!", "You clicked the button!", "success");
//     			}
//     		});
    
    

	
	
	
	</script>




</body>
</html>