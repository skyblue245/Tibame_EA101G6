<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.tfcord.model.*" %>
<%@ page import="com.shop.model.*" %>
<%@ page import="com.shopad.model.*" %>
<%@ page import="com.shoprpdt.model.*" %>
<%@ page import="com.mallOr.model.*" %>
<%@ page import="com.shgm.model.*" %>
<%@ page import="com.shgmrp.model.*" %>
<%@ page import="com.artrp.model.*" %>
<%@ page import="java.util.*" %>
<%
	TfcordService tfcordSvc = new TfcordService();
	List<TfcordVO> haveTfcord = tfcordSvc.getNotYetAll();
	int tfcordNum =  haveTfcord.size();
	pageContext.setAttribute("tfcordNum", tfcordNum);
	
	ShoprpdtService shoprpdtSvc = new ShoprpdtService();
	List<ShoprpdtVO> haveShoprpdt = shoprpdtSvc.getByStatus();
	int shoprpdtNum =  haveShoprpdt.size();
	pageContext.setAttribute("shoprpdtNum", shoprpdtNum);
	
	ShopService shopSvc = new ShopService();
	List<ShoprpdtVO> haveShop = shoprpdtSvc.getByStatus();
	int shopNum =  haveShoprpdt.size();
	pageContext.setAttribute("shopNum", shopNum);
	
	MallOrService mallOrSvc = new MallOrService();
	Set<MallOrVO> havemallOr = mallOrSvc.findByBoxStatus(0);
	int mallOrNum = havemallOr.size();
	pageContext.setAttribute("mallOrNum", mallOrNum);
	
	ShgmService shgmSvc = new ShgmService();
	Set<ShgmVO> haveShgm = shgmSvc.getAllShgmUncheck();
	int shgmNum = haveShgm.size();
	pageContext.setAttribute("shgmNum", shgmNum);
	
	ShgmrpService shgmrpSvc = new ShgmrpService();
	Set<ShgmrpVO> haveShgmrp = shgmrpSvc.getAllShgmrpUncheck();
	int shgmrpNum = haveShgmrp.size();
	pageContext.setAttribute("shgmrpNum", shgmrpNum);
	
	ArtrpService artrpSvc = new ArtrpService();
	List<ArtrpVO> haveArtrp = artrpSvc.getAllByStatus(0);
	int artrpNum = haveArtrp.size();
	pageContext.setAttribute("artrpNum", artrpNum);
	
	ShopadService shopadSvc = new ShopadService();
	List<ShopadVO> haveShopad = shopadSvc.getAllStatus(0);
	int shopadNum = haveShopad.size();
	pageContext.setAttribute("shopadNum", shopadNum);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/backCss/fontawesomeOld.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/backCss/bootstrapOld.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/backCss/tooplateOld.css">
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<link rel="stylesheet" href="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.css">
<script src="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.js"></script>

<style>

    *{
        box-sizing: border-box;
    }
    
    #tabs{
        margin: 50px auto;
    }
    
    .news{
        display: block;
        margin: 10px;
        border-bottom-style: dotted;
        border-bottom-color: rgb(158,158,158);
    }
    
/*     讓漢堡選單收起來的css，not選擇器，叫做 .collapse的元素，如果沒有.show的標籤，就隱藏起來*/
/*	   show這個js是boostrap寫好的，不是我們能去更動的*/
/*	       從外源引入的css最弱，寫在頁面內的css較強烈，寫在標籤上的最厲害*/
    .collapse:not(.show) {
	    display: none;
	}
</style>

<title>桌遊列國</title>

</head>
<body id="reportsPage">
	<div class="" id="home">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<nav class="navbar navbar-expand-xl navbar-light bg-light">
						<a class="navbar-brand"
							href="<%=request.getContextPath()%>/back-end/index.jsp"> <i
							class="fas fa-3x fa-tachometer-alt tm-site-icon"></i>
							<h1 class="tm-site-title mb-0">桌遊列國</h1>
						</a>
						<button class="navbar-toggler ml-auto mr-0" type="button"
							data-toggle="collapse" data-target="#navbarSupportedContent"
							aria-controls="navbarSupportedContent" aria-expanded="false"
							aria-label="Toggle navigation">
							<span class="navbar-toggler-icon"></span>
						</button>
						<div class="collapse navbar-collapse" id="navbarSupportedContent">
							<ul class="navbar-nav mx-auto backnav">
								<li class="nav-item"><a class="nav-link"
									href="<%=request.getContextPath()%>/back-end/index.jsp">首頁 <span
										class="sr-only">(current)</span>
								</a></li>
								<li class="nav-item dropdown backnavdown"><a
									class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
									role="button" data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false"> 會員管理 </a>
									<div class="dropdown-menu" aria-labelledby="navbarDropdown">
										<a class="dropdown-item downlist" href="<%=request.getContextPath()%>/back-end/mbrpf/listAllMbrpf.jsp">一般會員管理</a> <!-- 還沒放 -->
	<!-- active測試，之後換頁面  -->			<a class="dropdown-item downlist" href="<%=request.getContextPath()%>/back-end/shop/listSomeShop.jsp?status=0"">店家管理</a>
									</div></li>
								
								<li class="nav-item dropdown backnavdown">
								<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" 
								   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> 商城管理 
								</a>
								<div class="dropdown-menu" aria-labelledby="navbarDropdown">
									<a class="dropdown-item downlist" href="<%=request.getContextPath()%>/back-end/mall/mallGetAll.jsp">商城商品管理</a> 								
									<a class="dropdown-item downlist" href="<%=request.getContextPath()%>/back-end/mallad/listAllMallad.jsp">商品廣告管理</a>
								</div></li>
								
								
								
								<li class="nav-item dropdown backnavdown">
								<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" 
								   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> 員工管理 
								</a>
								<div class="dropdown-menu" aria-labelledby="navbarDropdown">
									<a class="dropdown-item downlist" href="<%=request.getContextPath()%>/back-end/emp/listAllEmp.jsp">員工相關</a> 								
									<a class="dropdown-item downlist" href="<%=request.getContextPath()%>/back-end/emp/updatePwd.jsp">修改密碼</a>
								</div></li>
								<li class="nav-item"><a class="nav-link"
									href="<%=request.getContextPath()%>/back-end/shgm/listAllShgm.jsp">市集管理</a>
								</li>
								
								<li class="nav-item dropdown backnavdown"><a
									class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
									role="button" data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false"> 檢舉管理 </a>
									<div class="dropdown-menu" aria-labelledby="navbarDropdown">
										<a class="dropdown-item downlist" href="<%=request.getContextPath()%>/back-end/shoprpdt/listAllShoprpdt.jsp">店家檢舉審核</a> 
										<a class="dropdown-item downlist" href="<%=request.getContextPath()%>/back-end/artrp/listAllArtrp.jsp">討論區文章檢舉審核</a> 
										<a class="dropdown-item downlist" href="<%=request.getContextPath()%>/back-end/shgmrp/listAllShgmrp.jsp">市集商品檢舉審核</a>
									</div></li>
								<li class="nav-item"><a class="nav-link"
									href="<%=request.getContextPath()%>/back-end/art/listAllArt.jsp">討論區管理</a>
								</li>
								<li class="nav-item dropdown backnavdown"><a
									class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
									role="button" data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false"> 揪團管理 </a>
									<div class="dropdown-menu" aria-labelledby="navbarDropdown">
										<a class="dropdown-item downlist" href="<%=request.getContextPath()%>/back-end/room/back-end_rminfoList.jsp">房間列表</a> 
										<a class="dropdown-item downlist" href="<%=request.getContextPath()%>/back-end/room/back-end_rateList.jsp">會員評價列表</a> 
										<a class="dropdown-item downlist" href="<%=request.getContextPath()%>/back-end/room/back-end_shoprpList.jsp">店家回報列表</a> 
									</div></li>
								<li class="nav-item dropdown backnavdown"><a
									class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
									role="button" data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false"> 更多 </a>
									<div class="dropdown-menu" aria-labelledby="navbarDropdown">
										<a class="dropdown-item downlist" href="<%=request.getContextPath()%>/back-end/tfcord/listAllTfcord.jsp">點數管理</a> 
										<a class="dropdown-item downlist" href="<%=request.getContextPath()%>/back-end/news/listAllNews.jsp">最新消息管理</a> 
										 
									</div></li>
								
							</ul>
							<ul class="navbar-nav">
								<li class="nav-item logoutPIC">
									<form method="post" action="<%=request.getContextPath()%>/emp/EmpServlet">
										<a class="nav-link d-flex" id="empout">
											<i class="far fa-user mr-2 tm-logout-icon"></i> <span>Logout</span>
											<input type="hidden" name="action" value="logout">
										</a> 
									</form>
								</li>
							</ul>
						</div>
					</nav>
				</div>
			</div>
			<div id="tabs">
				<ul>
		            <li><a href="#tabs-1">檢舉通知</a></li>
		            <li><a href="#tabs-2">審核通知</a></li>
		            <li><a href="#tabs-3">未出貨通知</a></li>
		        </ul>
		        <div id="tabs-1"><!--以下每一條都是未處理的通知-->
		        	<span class="news">
		        		<a href="<%=request.getContextPath()%>/back-end/shgmrp/listAllShgmrp.jsp">目前有<font style="color:red">${shgmrpNum}</font>筆_市集商品檢舉_未處理</a>
		        	</span>
		        	<span class="news">
		        		<a href="<%=request.getContextPath()%>/back-end/artrp/listAllArtrpS0.jsp">目前有<font style="color:red">${artrpNum}</font>筆_文章檢舉_未處理</a>
		        	</span>
		        	<span class="news">
		        		<a href="<%=request.getContextPath()%>/back-end/shoprpdt/listSomeShoprpdt.jsp?status=0">目前有<font style="color:red">${shoprpdtNum}</font>筆_店家檢舉_未處理</a>
		        	</span>
		        </div>
		        <div id="tabs-2">
		        	<span class="news">
		        		<a href="<%=request.getContextPath()%>/back-end/shop/listSomeShop.jsp?status=0"">目前有<font style="color:red">${shopNum}</font>筆_店家申請_尚未處理</a>
		        	</span>
		        	<span class="news">
		        		<a href="<%=request.getContextPath()%>/back-end/shopad/listAllShopadS0.jsp">目前有<font style="color:red">${shopadNum}</font>筆_店家廣告申請_尚未處理</a>
		        	</span>
		        	<span class="news">
		        		<a href="<%=request.getContextPath()%>/back-end/shgm/listAllShgm.jsp">目前有<font style="color:red">${shgmNum}</font>筆_市集商品審核_尚未處理</a>
		        	</span>
		        	<span class="news">
		        		<a href="<%=request.getContextPath()%>/back-end/tfcord/notYetTfcord.jsp">目前有<font style="color:red">${tfcordNum}</font>筆_點數轉換_尚未處理</a>
		        	</span>
		        </div>
		        <div id="tabs-3">
		        	<span class="news">
		        		<a href="<%=request.getContextPath()%>/back-end/mallOr/mallOrGet.jsp?active=getByBox">目前有<font style="color:red">${mallOrNum}</font>筆訂單尚未出貨</a>
		        	</span>
		        </div>
			</div>
		</div>
	</div>

	<script src="<%=request.getContextPath()%>/js/backJs/bootstrapOld.min.js"></script>
	
	<script>
	    $(function() {
	        $("#tabs").tabs();
	    });
	</script>
	
	<script>
	// DOM is ready
		$(function() {
			$(window).resize(function() {
				reloadPage();
			});
		})
	</script>
	
	<script>
		$(function () {
			$(".backnav").find("li").each(function () {
		 		var a = $(this).find("a:first")[0];
		 		if ($(a).attr("href") === location.pathname) {
		     		$(this).addClass("active");
		 		} else {
		    		 $(this).removeClass("active");
		 		}
			});
		});

		$(function () {
			$(".backnavdown").find("div").each(function () {
		 		var a = $(this).find("a")[0];
		 		if ($(a).attr("href") === location.pathname) {
		     		$(a).parent().parent().addClass("active");
		     		$(a).addClass("active");
		 		} else {
		    		 $(this).removeClass("active");
		 		}
			});
		});
		
		$('#empout').click(function(e){
					
					e.preventDefault();
					
					swal({ 
						  title : "已登出" ,
						  type : "success" ,
						  buttons : false ,
						  confirmButtonColor : "#9CCC65"
						});
					setTimeout(function(){
						$('#empout').parent('form').submit();
					},1500);
				});
</script>


<jsp:include page="/back-end/backChat/backChat.jsp"/>
	
</body>
</html>