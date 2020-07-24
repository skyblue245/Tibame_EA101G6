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
<title>�ʪ���</title>
<!-- �ӤHCSS -->
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
						<th scope="col">�ӫ~�W��</th>
						<th scope="col">�ƶq</th>
						<th scope="col">����</th>
						<th scope="col">�p�p</th>
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
											//�̤j�ȬO���ӫ~���w�s�ƶq
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
							<!-- �p�p�M�`���B�i��|�]ajax�B�ʩҥH��jquery�B�� -->
							<td class="buyPricePlus"></td>

							<th scope="row">
								<button class="cancel">����</button>
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
				<p id="total">�`���B:</p>
				<form method="post" action="<%= request.getContextPath()%>/MallOr/MallOrServlet">
				<input type="hidden" name="action" value="showCheckOut">
				<a href="<%=request.getContextPath()%>/front-end/mall/mallGetAllUp.jsp" ><button type="button" class="back">�~���ʪ�</button></a>
				<input type="submit" class="checkbtn" value="���b">
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
		
		
		//���Y�Htr�ƶq����	
		reSetNo();
		function reSetNo(){
			for(let i=1;i<=$("table.table tbody tr").length;i++){
				$("#row"+i+" th:first-of-type").text(i);
			}
		}
		//���o�`���B
		getTotal();
		function getTotal(){
			let total=0;
			let buyPricePlus =document.getElementsByClassName("buyPricePlus");
			for(let i=0;i<$(".buyPricePlus").length;i++){
				total+=parseInt(buyPricePlus[i].innerText.trim());
			}
			$("#total").text("�`���B:"+total);
		}
		
	})
	
	
	<c:if test="${not empty noMallAlert}">
		swal({text:"${noMallAlert}" });
	</c:if>
	</script>

</body>
</html>