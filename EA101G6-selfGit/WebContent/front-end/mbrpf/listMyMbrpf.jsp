<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.mbrpf.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>



<html>
<head>
<title>�|����� - listOneMbrpf.jsp</title>

<style>

	table{
		width:100%;
	}

</style>

</head>


<body>

<%@ include file="/front-end/front-end-nav.jsp"%>

	
	<div class="container">
		<div class="row">
			<div class="col">
				
			</div>
			<div class="col">
				<table class="memInfo">
					<tr>
						<td colspan="2" style="text-align:center;"><img width="200" height="175" src="<%= request.getContextPath()%>/mbrpf/mbrimg.do?mbrno=${mbrpfVO.mbrno}"></td>
					</tr>
						<tr><td>&nbsp;</td></tr>
						<tr><td>�|���b��:</td><td>${mbrpfVO.mbract}</td></tr>
						<tr><td>&nbsp;</td></tr>
						<tr><td>�|���m�W:</td><td>${mbrpfVO.mbrname}</td></tr>
						<tr><td>&nbsp;</td></tr>
						<tr><td>�ͤ�:</td><td>${mbrpfVO.birth}</td></tr>
						<tr><td>&nbsp;</td></tr>
						<tr><td>�ʧO:</td><td>
							<c:if test="${mbrpfVO.sex ==1}">
							<c:out value="�k"/>
							</c:if>
							<c:if test="${mbrpfVO.sex ==2}">
							<c:out value="�k"/>
							</c:if>
						</td></tr>
						<tr><td>&nbsp;</td></tr>
						<tr><td>�q�l�l��:</td><td>${mbrpfVO.mail}</td></tr>
						<tr><td>&nbsp;</td></tr>
						<tr><td>�q��:</td><td>${mbrpfVO.phone}</td></tr>
						<tr><td>&nbsp;</td></tr>
						<tr><td>�����ڶ��b��:</td><td>${mbrpfVO.mbrac}</td></tr>
						<tr><td>&nbsp;</td></tr>
						<tr><td>�ʺ�:</td><td>${mbrpfVO.nickname}</td></tr>
<!-- 						<tr><td>&nbsp;</td></tr> -->
<%-- 						<tr><td>�I�ƾl�B</td><td>${mbrpfVO.points}</td></tr> --%>
<!-- 						<tr><td>&nbsp;</td></tr> -->
<%-- 						<tr><td>�Q�����`�H��</td><td>${mbrpfVO.ratedtotal}</td></tr> --%>
<!-- 						<tr><td>&nbsp;</td></tr> -->
<%-- 						<tr><td>�Q�����`�P��</td><td>${mbrpfVO.startotal}</td></tr> --%>
<!-- 						<tr><td>&nbsp;</td></tr> -->
<%-- 						<tr><td>���X�u����</td><td>${mbrpfVO.unattend}</td></tr> --%>
<!-- 						<tr><td>&nbsp;</td></tr> -->
<%-- 						<tr><td>�`�ѹΦ���</td><td>${mbrpfVO.ttattend}</td></tr> --%>
						<tr><td>&nbsp;</td></tr>
						<td colspan="2">
						  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/mbrpf/mbrpf.do" style="margin-bottom: 0px;">
						     <div style="text-align:center;"><input type="submit" value="�ק�"></div>
						     <input type="hidden" name="mbrno"  value="${mbrpfVO.mbrno}">
						     <input type="hidden" name="action"	value="getOne_To_Update"></FORM>
						</td>
						
				</table>
			</div>
			<div class="col"></div>
		</div>
	</div>
		
	
	
<!-- 	<table> -->
<!-- 		<tr> -->
<!-- 			<th>�|���Ӥ�</th> -->
<!-- 			<th>�|���s��</th> -->
<!-- 			<th>�@��|���b��</th> -->
<!-- 			<th>�@��|���K�X</th> -->
<!-- 			<th>�|���m�W</th> -->
<!-- 			<th>�|���Ӥ�</th> -->
<!-- 			<th>�X�ͦ~���</th> -->
<!-- 			<th>�ʧO</th> -->
<!-- 			<th>�q�l�l��</th> -->
<!-- 			<th>�q��</th> -->
<!-- 			<th>�����ڶ��b��</th> -->
<!-- 			<th>�ʺ�</th> -->
<!-- 			<th>�I�ƾl�B</th> -->
<!-- 			<th>�@��|�����A</th> -->
<!-- 			<th>�Q�����`�H��</th> -->
<!-- 			<th>�Q�����`�P��</th> -->
<!-- 			<th>���X�u����</th> -->
<!-- 			<th>�`�ѹΦ���</th> -->
<!-- 			</tr> -->
<!-- 			<tr> -->
<%-- 			<td><img width="100" height="100" src="<%= request.getContextPath()%>/mbrpf/mbrimg.do?mbrno=${mbrpfVO.mbrno}"></td> --%>
<%-- 			<td>${mbrpfVO.mbrno}</td> --%>
<%-- 			<td>${mbrpfVO.mbract}</td> --%>
<%-- 			<td>${mbrpfVO.mbrpw}</td> --%>
<%-- 			<td>${mbrpfVO.mbrname}</td> --%>
<%-- 			<td><img width="100" height="100" src="<%= request.getContextPath()%>/mbrpf/mbrimg.do?mbrno=${mbrpfVO.mbrno}"></td> --%>
<%-- 			<td>${mbrpfVO.birth}</td> --%>
<%-- 			<td>${mbrpfVO.sex}</td> --%>
<%-- 			<td>${mbrpfVO.mail}</td> --%>
<%-- 			<td>${mbrpfVO.phone}</td> --%>
<%-- 			<td>${mbrpfVO.mbrac}</td> --%>
<%-- 			<td>${mbrpfVO.nickname}</td> --%>
<%-- 			<td>${mbrpfVO.points}</td> --%>
<%-- 			<td>${mbrpfVO.status}</td> --%>
<%-- 			<td>${mbrpfVO.ratedtotal}</td> --%>
<%-- 			<td>${mbrpfVO.startotal}</td> --%>
<%-- 			<td>${mbrpfVO.unattend}</td> --%>
<%-- 			<td>${mbrpfVO.ttattend}</td> --%>
<!-- 		</tr> -->
<!-- 	</table> -->

</body>
</html>