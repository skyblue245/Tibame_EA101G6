<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.game.model.*"%>

<%
	GameService gameSvc = new GameService();
	List<GameVO> list = gameSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<!doctype html>
<html lang="en">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>遊戲列表</title>

<style>
table {
	margin-top: 10px;
}

tr th {

	text-align: center;
}

td {
	text-align: center;
}

.icon {
	width: 20px;
	height: 20px;
}

#preview img {
	width: 150px;
	height: 150px;
}

img {
	width: 50px;
	height: 50px;
}

h4 {
	margin-left: 20px;
}
</style>
</head>

<body>

	<jsp:include page="/front-end/front-end-nav.jsp" flush="true"></jsp:include>



	<jsp:include page="select_page.jsp" flush="true">
		<jsp:param name="" value="" />
	</jsp:include>



	<div class="container-fluid">
		<div class="row">
			<%@ include file="page1.file"%>
			<table class="table table-sm">
				<tr>
					<th>遊戲編號</th>
					<th>遊戲名稱</th>
					<th>遊戲圖片</th>
					<th>修改</th>
				</tr>
				<c:forEach var="gameVO" items="${list}" begin="<%=pageIndex%>"
					end="<%=pageIndex+rowsPerPage-1%>">
					<tr ${(gameVO.gmno==param.gmno) ? 'bgcolor=#CCCCFF':''}>
						<td>${gameVO.gmno}</td>
						<td>${gameVO.gmname}</td>
						<td><img
							src="<%=request.getContextPath()%>/GameShowImg?gmno=${gameVO.gmno}"></td>
						<td>
							<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/game/listAllGame.jsp?gmno=${gameVO.gmno}&whichPage=${param.whichPage}" style="margin-bottom: 0px;">
								<input type="submit" value="修改" data-toggle="modal" data-target="#exampleModal" class="btn btn-secondary"> 
								 <input type="hidden" name="gmno" value="${gameVO.gmno}"> 
								 <input type="hidden" name="call" value="updateModel">
<%-- 									 onclick="location.href='<%=request.getContextPath()%>/front-end/game/game.do?gmno=$gameVO.gmno&action=getOne_For_Update';" --%>
<!-- 									<input type="hidden" name="action" value="getOne_For_Update"> -->
							</FORM>
						</td>
					</tr>
				</c:forEach>
			</table>
			<div class="d-flex justify-content-center container"
				style="margin-left: auto; margin-right: auto;">
				<div class="row">
					<div class="col-sm-12">
						<%@ include file="page2.file"%>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:useBean id="gmSvc" scope="page"
	class="com.game.model.GameService" />


<c:if test="${param.call=='updateModel'}">	
	<div class="modal fade" id="exampleModal" tabindex="-1"
					role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content rp-2">				
			<div class="modal-header">
                <h3 class="modal-title" id="exampleModalLabel">修改遊戲</h3>
                <hr class="my-3">
            </div>
			
			<div class="modal-body">
				<div class="form-group">
<!-- =========================================以下為update的內容========================================== -->
<div class="d-flex justify-content-center container" style="margin-left: auto; margin-right: auto;">
	<FORM METHOD="post" id="ya" ACTION="game.do" name="form1"
		enctype="multipart/form-data">
		<div class="form-row">
					<div class="form-group col-md-8">
						<label for="name">遊戲名稱</label> <input class="form-control"
							type="TEXT" name="gmname" size=100% id="name"
							value="${gmSvc.getOneGame(param.gmno).gmname}"
							placeholder="name" />
					</div>
					</div>
				<div class="form-row">
				<div class="form-group col-md-8">
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
				</div>
				<div class="form-row">
					<div class="form-group col-md-6">
					<div type="file" id="preview">
						<img src="<%=request.getContextPath()%>/GameShowImg?gmno=${param.gmno}" />
					</div>
					</div>
				</div> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="gmno" value="${param.gmno}">
			<input type="hidden" name="whichPage"	value="<%=whichPage%>">
			<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
	</FORM>
</div>
<!-- =========================================以上為原addGame.jsp的內容========================================== -->
			</div></div>			
			<div class="modal-footer">
                <input type="submit" value="確認送出" id="go" class="btn btn-primary">
            </div>		
		</div>
	</div>
</div>

	<script>
	$(document).ready(function(){	
			<c:if test="${not empty successMsgs}">
			swal("Good", "${successMsgs}", "success");
			</c:if>
			
		 $("#go").click(function(){
				$("#ya").submit();
			})
		$("#exampleModal").modal({show: true})
		
		 $("#action").click(function(){
		 	if ($("#gmname").val() == ''){
		 		swal("", "請輸入遊戲名稱!", "error");
		 	}
		 })
	})
    		
    </script>
</c:if>	
<script>
	function init() {
		var myFile = document.getElementById("myFile");
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
						preview.innerHTML='';
						preview.append(img);
					});
					reader.readAsDataURL(file);
				}
			}
		});
	}
	window.onload = init;
</script>	
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

</body>
</html>