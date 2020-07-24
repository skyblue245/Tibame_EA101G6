<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.gmlist.model.*"%>
<%@ page import="com.shop.model.*"%>
<%@ page import="com.game.model.*"%>
<%@ include file="/front-end/front-end-nav.jsp"%>
<%
	GmlistService gmlistSvc = new GmlistService();
	ShopVO shopVO = (ShopVO)session.getAttribute("shopAcount");
	List<GmlistVO> list = gmlistSvc.getSomeGmlistByShop(shopVO.getShopno());
	pageContext.setAttribute("list", list);
	GameVO gameVO = (GameVO)request.getAttribute("gameVO");
%>
<jsp:useBean id="gameSvc" scope="page"
	class="com.game.model.GameService" />
<jsp:useBean id="shopSvc" scope="page"
	class="com.shop.model.ShopService" />
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">

	<title>新增提供的遊戲</title>

	<style>
table {
	margin-top: 10px;
}

tr th {
	border: 2px solid black;
	text-align: center;
}

td {
	text-align: center;
}

.icon {
	width: 20px;
	height: 20px;
}


img {
	width: 50px;
	height: 50px;
}
#preview img{
	width: 200px;
	height: 200px;
	margin-left:auto;
	margin-top:auto;
}

h4 {
	margin-left: 20px;
}
</style>
</head>

<body>




<div class="container-fluid">
<div class="row">
<div class="col-sm-1"></div>
			<div class="col-sm-2">
			<div style="witdh: 20px;">
				<ul class="list-group list-group-item-action">
					<li class="list-group-item list-group-item-action" onclick="location.href='<%=request.getContextPath()%>/front-end/shop/shopArea.jsp';">我的資訊</li>	
					<li class="list-group-item list-group-item-action active" id="goGmlist" onclick="location.href='<%=request.getContextPath()%>/front-end/gmlist/addGmlist.jsp';">我的遊戲</li>
					<FORM id="gmlist" METHOD="post"
						ACTION="<%=request.getContextPath()%>/front-end/gmlist/gmlist.do">
						<input type="hidden" name="shopno" value="${shopVO.shopno}">
						<input type="hidden" name="action" value="getSome_For_Display">
					</FORM>
					<li class="list-group-item list-group-item-action" id="goShopbk">我的揪團</li>
					<FORM id="shopbk" METHOD="post"
						ACTION="<%=request.getContextPath()%>/front-end/shopbk/shopbk.do">
						<input type="hidden" name="action" value="getSome_For_Display2">
					</FORM>
					<li class="list-group-item list-group-item-action" id="goUpdate">更改店家資料</li>
					<FORM id="getOne_For_Update" METHOD="post"
						ACTION="<%=request.getContextPath()%>/front-end/shop/shop.do">
						<input type="hidden" name="action" value="getOne_For_Update">
					</FORM>
					<li class="list-group-item list-group-item-action" onclick="location.href='<%=request.getContextPath()%>/front-end/room/shop_roomList.jsp';">我的訂位</li>
				</ul>
			</div>
		</div>
<div class="col-sm-4">
	<table class="table table-sm">
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/gmlist/gmlist.do">
		<tr>
		<h4 class="text-dark">我的桌遊</h4>
		<input type="hidden" name="action" value="delete">
		<input type="submit" value="刪除" class="btn btn-secondary">
		</tr>
		
		<c:forEach var="gmlistVO" items="${list}">				
			<tr>
			<td>
			<div class="form-check">
  			<input class="form-check-input" name="gmno" value="${gmlistVO.gmno}" type="checkbox" id="${gmlistVO.gmno}">
  			<label class="form-check-label" for="${gmlistVO.gmno}"></label>
  			</div>
  			</td>			
			<td>
			<label class="form-check-label" for="${gmlistVO.gmno}">${gameSvc.getOneGame(gmlistVO.gmno).gmname}</label></td>				
			<td>
			<label class="form-check-label" for="${gmlistVO.gmno}"><img
					src="<%=request.getContextPath()%>/GameShowImg?gmno=${gmlistVO.gmno}"></label></td>							
			</tr>
		</c:forEach>
		</FORM>
	</table>
</div>
<div class="col-sm-1"></div>
<div class="col-sm-3">
	<table class="table table-sm">
		
		<tr>
			<h4 class="text-dark">其他桌遊</h4>
			
			<button id="create" class="btn btn-secondary">增加</button>
			<button data-toggle="modal" data-target="#exampleModal" class="btn btn-secondary" style="margin-left:10px;">我有新桌遊</button>
			<button class="btn btn-secondary" style="margin-left:10px;" onclick="location.href='<%=request.getContextPath()%>/front-end/game/listAllGame.jsp';">更改桌遊</button>
		</tr>
		<FORM id="goCreate" METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/gmlist/gmlist.do">		
		<input type="hidden" name="action" value="insert">
		<c:forEach var="gameVO" items="${gameSvc.all}">	
		<c:set var="tampbollean" value="true"/>
		<c:forEach var="gmlistVO" items="${list}">
					<c:if test="${gameVO.gmno==gmlistVO.gmno}">
						<c:set var="tampbollean" value="false"/>
					</c:if>
				</c:forEach>
				<c:if test="${tampbollean}">
				<tr>
			<td>
			<div class="form-check">
  			<input class="form-check-input" name="gmno2" value="${gameVO.gmno}" type="checkbox" id="${gameVO.gmno}">
  			<label class="form-check-label" for="${gameVO.gmno}"></label>
  			</div>
  			</td>			
			<td>
			<label class="form-check-label" for="${gameVO.gmno}">${gameSvc.getOneGame(gameVO.gmno).gmname}</label></td>				
			<td>
			<label class="form-check-label" for="${gameVO.gmno}"><img
					src="<%=request.getContextPath()%>/GameShowImg?gmno=${gameVO.gmno}"></label></td>							
			</tr>
			</c:if>			
		</c:forEach>

		</FORM>
	</table>
</div>
</div>
</div>

<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

	
		<script>
			$(document).ready(function() {
				<c:if test="${not empty errorMsgs}">
				swal("Error", "${errorMsgs[0]}\n${errorMsgs[1]}", "error");
				</c:if>
				
				
				$("#goUpdate").click(function() {
					$("#getOne_For_Update").submit();
				})
				$("#goShopbk").click(function() {
					$("#shopbk").submit();
				})
			})
		</script>


<div class="modal fade" id="exampleModal" tabindex="-1"
					role="dialog" aria-labelledby="exampleModalLabel"
					aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content rp-2">				
			<div class="modal-header">
                <h3 class="modal-title" id="exampleModalLabel">新增遊戲</h3>
                <hr class="my-3">
            </div>
			
			<div class="modal-body">
				<div class="form-group">
<!-- =========================================以下為原addGame.jsp的內容========================================== -->
		<div class="container">
			<form METHOD="post" id="ya"
				ACTION="<%=request.getContextPath()%>/front-end/game/game.do"
				enctype="multipart/form-data">
				<div class="form-row">
					<div class="col-md-5">
						<label for="name">遊戲名稱</label> <input class="form-control"
							type="TEXT" name="gmname" size=100% id="name"
							value="<%=(gameVO == null) ? "" : gameVO.getGmname()%>"
							placeholder="name" />
					</div>
					</div>
				<div class="form-group">
					<div class="input-group">
						<div class="input-group-prepend">
							<span class="input-group-text" id="inputGroupFileAddon01">pic</span>
						</div>
						<div class="custom-file">
							<input type="file" class="custom-file-input" id="myFile"
								aria-describedby="myFile" name="gmimg"> <label
								class="custom-file-label" for="inputGroupFile01">Choose
								file</label>
						</div>
					</div>
				</div>
				<div class="form-row">
				<div class="col-md-3"></div>
					<div class="col-md-6">
					<div id="preview">
					</div>
					</div>
				</div>
		<input type="hidden" name="action" value="insert">
		</FORM>
		</div>
<!-- =========================================以上為原addGame.jsp的內容========================================== -->
			</div></div>			
			<div class="modal-footer"><img src="images/meow.png" style="width: 50px;
					height: 50px;bottom:0;left:0;" id="magicBtn">
                <input type="submit" value="確認送出" id="go" class="btn btn-primary">
            </div>
		</div>
	</div>
</div>
<script>
	function init() {
		var myFile = document.getElementById("myFile");
		var filename = document.getElementById("filename");
		var preview = document.getElementById('preview');
		myFile.addEventListener('change', function(e) {
			var files = myFile.files;
			if (files !== null && files.length > 0) {
				var file = files[0];
				if (file.type.indexOf('image') > -1) {
					var reader = new FileReader();
					reader.addEventListener('load', function(e) {
						var result = e.target.result;
						console.log(result);
						var img = document.createElement('img');
						img.src = result;
						preview.innerHTML="";
						preview.append(img);
					});
					reader.readAsDataURL(file);
				}
			}
		});
	}
	window.onload = init;
</script>
<script>
	$(document).ready(function(){	
		<c:if test="${not empty successMsgs}">
		swal("Good", "${successMsgs}", "success");
		</c:if>
		
		$("#go").click(function(){
			$("#ya").submit();
		})
		
		$("#create").click(function(){
			$("#goCreate").submit();
		})		
		 $("#magicBtn").click(function(){
		 	 $("#name").val("麻將");
	 		 })
	})
</script>
</body>
</html>