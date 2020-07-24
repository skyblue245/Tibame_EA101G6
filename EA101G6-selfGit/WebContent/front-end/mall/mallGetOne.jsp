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
<meta charset="UTF-8">
<title>商品</title>

<!-- 個人CSS -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/mallCss/mallGetOne.css">


</head>
<body>

<jsp:include page="/front-end/front-end-nav.jsp"/>

	<%	
	
		if(request.getParameter("commNo")!=null){
			String commNo = request.getParameter("commNo");
			MallService mallSvc = new MallService();
			request.setAttribute("mallSvc", mallSvc);
			MallVO mallVo = mallSvc.findOneByNo(commNo);
			pageContext.setAttribute("mallVo", mallVo);
			session.setAttribute("tempCommNo",commNo);
		}else{
			String commNo = (String)session.getAttribute("tempCommNo");
			MallService mallSvc = new MallService();
			request.setAttribute("mallSvc", mallSvc);
			MallVO mallVo = mallSvc.findOneByNo(commNo);
			pageContext.setAttribute("mallVo", mallVo);
		}
	%>

	<main>
		<div class="container commmain">
			<div class="row justify-content-around">
				<div class="col-12 col-lg-5 commimg">
					<img
						src="<%= request.getContextPath()%>/Mall/MallShowImg?commNo=${mallVo.commNo}">
				</div>

				<div class="col-12 col-lg-6  comm">
					<h1>${mallVo.commName}</h1>
					<div class="type">
						遊戲類型:
						<p class="d-inline-block">
							<c:forEach var="typename"
								items="${mallSvc.getType(mallVo.commNo)}">
						${typename.typeName}
					</c:forEach>
						</p>
					</div>
					<div class="d-inline-block price">
						<p>
							<b> $${mallVo.price} </b>
						</p>
					</div>

					<div class="player">遊玩人數: ${mallVo.player} 人</div>
					<div class="age">建議年齡: ${mallVo.age} 歲以上</div>

					<div class="intro">
						遊戲介紹:
						<div>${mallVo.intro}</div>
					</div>

					<div class="left">剩餘數量: ${mallVo.quantity}</div>

						<div class="quantity">
							數量:
							<button id="minus" type="button">-</button>
							<input type="text" class="buyQuantity" name="buyQuantity" value="1" Readonly>
							<button id="plus" type="button">+</button>
						</div>
						<input type="hidden" name="action" value="add">
						<button type="button" class="carbtn">加入購物車</button>
					
					<form method="post" action="<%=request.getContextPath()%>/BuyCar/BuyCarServlet">
						<input type="hidden" id="commNo"  name="commNo" value="${mallVo.commNo}">
						<input type="hidden" id="commName"  name="commName" value="${mallVo.commName}">
						<input type="hidden" id="buyPrice"  name="buyPrice" value="${mallVo.price}">  
						<input type="hidden" id="buyQuantity" class="buyQuantity" name="buyQuantity" value="1">
						<input type="hidden" name="action" value="buyOne"> 
						<input type="submit" value="購買" class="bybtn">
					</form>
				</div>
			</div>
		</div>


	</main>
	<a href="<%=request.getContextPath()%>/front-end/buyCar/buyCar.jsp" class="shopcar"><img src="<%= request.getContextPath()%>/image/buyCar.png"></a>
		
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

	<!-- 查詢時有錯誤啟動 -->
	<c:if test="${not empty selErroMsg}">
		<script>
			swal({
				text : "${selErroMsg}"
			});
		</script>
		<%
			request.removeAttribute("selErroMsg");
		%>
	</c:if>

	<script>
		$(document).ready(function() {
			var quantity = 1;
			var maxquantity = parseInt("${mallVo.quantity}");
			$("#minus").click(function() {
				if (quantity > 1)
					$(".buyQuantity").attr("value", --quantity);
			})

			$("#plus").click(function() {
				if (maxquantity > quantity)
					$(".buyQuantity").attr("value", ++quantity);
			})

			$(".buyQuantity").change(function() {
				quantity = $(".buyQuantity").val();
				if ($(this).val() > maxquantity) {
					quantity = 1;
					swal({
						text : "數量不夠"
					});
				} else {
					$(".buyQuantity").attr("value", quantity);
				}
				$(".buyQuantity").attr("value", quantity);

			})
			
			$("button.carbtn").click(function(){
				$.post('<%=request.getContextPath()%>/BuyCar/BuyCarServlet',{
					action:"add",
					commNo:$("#commNo").val(),
					commName:$("#commName").val(),
					buyQuantity:$("#buyQuantity").val(),
					buyPrice:$("#buyPrice").val()
					
					},function(data,status){
						if(status="success")
							swal({text:data });
					})
			
			})

		})
	</script>



</body>
</html>