<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.game.model.*"%>

<%
	GameVO gameVO = (GameVO) request.getAttribute("gameVO"); //gameServlet.java (Concroller) 存入req的gameVO物件 (包括幫忙取出的gameVO, 也包括輸入資料錯誤時的shopVO物件)
%>

<!doctype html>
<html lang="en">
<head>

<meta charset="utf-8">

	<title>遊戲列表</title>

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
	width: 300px;
	height: 300px;
}

h4 {
	margin-left: 20px;
}
</style>

<body>
<jsp:include page="/front-end/front-end-nav.jsp" flush="true"></jsp:include>

</head>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
<div class="container-fluid">
	<FORM METHOD="post" ACTION="game.do" name="form1"
		enctype="multipart/form-data">
		<div class="form-row"><div class="form-group col-md-3"></div>
					<div class="form-group col-md-5">
						<label for="name">遊戲名稱</label> <input class="form-control"
							type="TEXT" name="gmname" size=100% id="name"
							value="<%=(gameVO == null) ? "" : gameVO.getGmname()%>"
							placeholder="name" />
					</div>
					</div>
				<div class="form-row">
				<div class="form-group col-md-3"></div>
				<div class="form-group col-md-5">
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
				<div class="form-group col-md-4"></div>
					<div class="form-group col-md-4">
					<div type="file" id="preview">
						<img src="<%=request.getContextPath()%>/GameShowImg?gmno=${gameVO.gmno}" />
					</div>
					</div>
				</div> <div class="form-row"><div class="form-group col-md-4"></div><input type="hidden" name="action" value="update"> <input
			type="hidden" name="gmno" value="<%=gameVO.getGmno()%>"><input type="submit" value="修改" class="btn-lg btn-primary"></div> 	
				<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
	</FORM>
</div>
</body>



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
					// 					filename.value = file.name;
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
</html>