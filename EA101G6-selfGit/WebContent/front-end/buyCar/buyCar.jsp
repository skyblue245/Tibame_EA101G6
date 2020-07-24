<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ page import="com.mall.model.*"%>
<%@ page import="com.mall.model.*"%>
<%@ page import="com.mallOr.model.*"%>
<%@ page import="com.mallOrDt.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>購物車</title>
<!-- 個人CSS -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/buyCarCss/buyCar.css">

</head>
<body>

<%@ include file="/front-end/front-end-nav.jsp" %>


	<main>
		<div class="container">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<th scope="col"></th>
						<th scope="col">商品名稱</th>
						<th scope="col">數量</th>
						<th scope="col">價錢</th>
						<th scope="col">小計</th>
						<th scope="col"></th>
					</tr>
				</thead>
				<tbody>
					
					<c:forEach var="buyCarMall" items="${buyCarList}" varStatus="count">
						<tr id="row${count.count}">
							<th scope="row"></th>
							<td class="name"><img src="<%= request.getContextPath()%>/Mall/MallShowImg?commNo=${buyCarMall.commNo}">${buyCarMall.commName}</td>
							<td><div class="quantitydiv">
									<select>
										
										<%
											//最大值是此商品的庫存數量
											MallService mallSvc = new MallService();
											pageContext.setAttribute("mallSvc", mallSvc);
										%>
										
										<c:forEach var="i" begin="1"
											end="${mallSvc.findOneByNo(buyCarMall.commNo).quantity}">
											<option value="${i}" ${i==buyCarMall.quantity?"selected":""}>${i}</option>
										</c:forEach>
									</select>
								</div></td>
							<td>${buyCarMall.price}</td>
							<!-- 小計和總金額可能會因ajax浮動所以用jquery運算 -->
							<td class="buyPricePlus"></td>

							<th scope="row">
								<button class="cancel">取消</button>
							</th>
						</tr>


						<input type="hidden" class="commName" name="commName"
							value="${buyCarMall.commName}">
						<input type="hidden" class="buyPrice" name="price"
							value="${buyCarMall.price}">
						<input type="hidden" class="buyQuantity" name="quantity"
							value="${buyCarMall.quantity}">

					</c:forEach>
				</tbody>
			</table>





			<div class="checkdiv">
				<p id="total">總金額:</p>
				<form method="post" action="<%= request.getContextPath()%>/MallOr/MallOrServlet">
				<input type="hidden" name="action" value="showCheckOut">
				<a href="<%=request.getContextPath()%>/front-end/mall/mallGetAllUp.jsp" ><button type="button" class="back">繼續購物</button></a>
				<input type="submit" class="checkbtn" value="結帳">
				</form>
				
			</div>
			
			
		</div>



	</main>
	
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<script>
	$(document).ready(function() {
		
		<c:forEach var="buyCarMall" items="${buyCarList}" varStatus="count">
			
			$("#row${count.count} .buyPricePlus").text(${buyCarMall.price}*$("#row${count.count} select").val());			
			$("#row${count.count} select").change(function(){
				let buyPrice=${buyCarMall.price};
				$("#row${count.count} .buyPricePlus").text(buyPrice*$(this).val());
				
				$.post('<%=request.getContextPath()%>/BuyCar/BuyCarServlet',{
					action:"update",
					commNo:"${buyCarMall.commNo}",
					commName:"${buyCarMall.commName}",
					buyQuantity:$("#row${count.count} select").val(),
					buyPrice:"${buyCarMall.price}"
					
					},function(data,status){
						if(status="success")
							swal({text:data });
					})
				
				getTotal();
			})
		
			$("#row${count.count} .cancel").click(function(){
				$("#row${count.count}").remove();
				reSetNo();
				getTotal();
				$.post('<%=request.getContextPath()%>/BuyCar/BuyCarServlet',{
					action:"delete",
					commNo:"${buyCarMall.commNo}",
					commName:"${buyCarMall.commName}",
					buyQuantity:"${buyCarMall.quantity}",
					buyPrice:"${buyCarMall.price}"
					
					},function(data,status){
						if(status="success")
							swal({text:data });
					})
				
				
			})
		</c:forEach>
		
		
		//標頭隨tr數量改變	
		reSetNo();
		function reSetNo(){
			for(let i=1;i<=$("table.table tbody tr").length;i++){
				$("#row"+i+" th:first-of-type").text(i);
			}
		}
		//取得總金額
		getTotal();
		function getTotal(){
			let total=0;
			let buyPricePlus =document.getElementsByClassName("buyPricePlus");
			for(let i=0;i<$(".buyPricePlus").length;i++){
				total+=parseInt(buyPricePlus[i].innerText.trim());
			}
			$("#total").text("總金額:"+total);
		}
		
	})
	
	
	<c:if test="${not empty noMallAlert}">
		swal({text:"${noMallAlert}" });
	</c:if>
	</script>

</body>
</html>