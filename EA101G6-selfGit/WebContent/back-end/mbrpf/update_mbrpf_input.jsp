<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mbrpf.model.*"%>

<%
  MbrpfVO mbrpfVO = (MbrpfVO) request.getAttribute("mbrpfVO"); //MbrpfServlet.java (Concroller) �s�Jreq��mbrpfVO���� (�]�A�������X��mbrpfVO, �]�]�A��J��ƿ��~�ɪ�mbrpfVO����)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>�|����ƭק� - update_mbrpf_input.jsp</title>

<style>

.container #mainDiv{
	background-color: rgba(255,255,255,0.7);
} 
 
.mainF{
	border:1px;
	border-bottom-style: solid;
	border-top-style: none;
	border-left-style:none;
	border-right-style:none;
	background-color:transparent;
}
.mainF:focus{
	outline: none;
}
  
</style>

</head>
<body>
<%@ include file="/back-end/back-end_nav.jsp"%>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

	<div class="container">
		<div class="row">

			<div class="col-sm"></div>
			<div class="col-sm" id="mainDiv">
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/back-end/mbrpf/mbrpf.do" name="form1"
					enctype="multipart/form-data">
					<table>
						<tr>
							<td>�|���Y��:</td>
		<tr><td><img alt=""src="<%= request.getContextPath()%>/mbrpf/mbrimg.do?mbrno=${mbrpfVO.mbrno}" width="200" height="175" id="demo1"></td></tr>		
							<tr>
							<tr style="display: none">
								<td>�|���s��:<font color=red><b>*</b></font></td>
								<td><%=mbrpfVO.getMbrno()%></td>
							</tr>
							
						<td><label><input type="FILE" class="mainF" name="mbrimg" style="display:none"
								onchange="loadFile1(event)" size="30" /><a class="button">�W�ǹϤ�</a></label></td>
						</tr>
						</tr><tr>
							<td class="tdtitle">�|���b��</td>
						<tr>
							<td><input type="TEXT" class="mainF" name="mbract" size="45"
								value="<%=mbrpfVO.getMbract()%>" /></td>

						</tr>
						<tr>
							<td class="tdtitle">�|���K�X</td>
						<tr>
							<td><input type="PASSWORD" class="mainF" name="mbrpw" size="45"
								value="<%=mbrpfVO.getMbrpw()%>" /></td>
						</tr>
						</tr>
						<tr>
							<td class="tdtitle">�|���m�W</td>
						<tr>
							<td><input type="TEXT" class="mainF" name="mbrname" size="45"
								value="<%=mbrpfVO.getMbrname()%>" /></td>
						</tr>
						</tr>
						<tr>
							<td class="tdtitle">
								�ͤ�<a style="position: relative; right: -180px;"><font color="black">�ʧO</font></a>
							</td>
						<tr>
							<td>
								<input name="birth" class="mainF" id="f_date1" type="date" value="<%=mbrpfVO.getBirth()%>">
								<a style="position: relative; right: -50px;">
									<input type="radio" name="sex" value="1" <%=mbrpfVO.getSex()==1?"checked":""%>><font color="black">�k</font>
									<input type="radio" name="sex" value="2" <%=mbrpfVO.getSex()==2?"checked":""%>><font color="black">�k</font>
								</a>
							</td>
						</tr>
						</tr>


						<tr>
							<td class="tdtitle">�q�l�l��</td>
						<tr>
							<td><input type="TEXT" class="mainF" name="mail" size="45"
								value="<%=mbrpfVO.getMail()%>" /></td>
						</tr>
						</tr>
						<tr>
							<td class="tdtitle">�q��</td>
						<tr>
							<td><input type="TEXT" class="mainF" name="phone" size="45"
								value="<%=mbrpfVO.getPhone()%>" /></td>
						</tr>
						</tr>
						<tr>
							<td class="tdtitle">�����ڶ��b��</td>
						<tr>
							<td><input type="TEXT" class="mainF" name="mbrac" size="45"
								value="<%=mbrpfVO.getMbrac()%>" /></td>
						</tr>
						</tr>
						<tr>
							<td class="tdtitle">�ʺ�<a style="position: relative; right: -180px;"><font color="black">���A</font></a></td>
						<tr>
							<td><input type="TEXT" class="mainF" name="nickname" size="18"
								value="<%=mbrpfVO.getNickname()%>" />
								<a style="position: relative; right: -40px;">
									<input type="radio" name="status" value="1" <%=mbrpfVO.getStatus()==0?"checked":""%>><font color="black">������</font>
									<input type="radio" name="status" value="1" <%=mbrpfVO.getStatus()==1?"checked":""%>><font color="black">���`</font>
									<input type="radio" name="status" value="2" <%=mbrpfVO.getStatus()==2?"checked":""%>><font color="black">���v</font>
								</a>	
							</td>
						</tr>
						</tr>
						<tr style="display: none">
							<td class="tdtitle">�I�ƾl�B:</td>
							<td><input type="TEXT" name="points" size="45"
								value="<%=mbrpfVO.getPoints()%>" /></td>
						</tr>
						<tr>
							<td class="tdtitle">�|�����A:</td>
						</tr>
						<tr>
							<td>
								
								</td>
						
						</tr>
						<tr style="display: none">
							<td class="tdtitle">�Q�����`�H��:</td>
							<td><input type="TEXT" name="ratedtotal" size="45"
								value="<%=mbrpfVO.getRatedtotal()%>" /></td>
						</tr>
						<tr style="display: none">
							<td class="tdtitle">�Q�����`�P��:</td>
							<td><input type="TEXT" name="startotal" size="45"
								value="<%=mbrpfVO.getStartotal()%>" /></td>
						</tr>
						<tr style="display: none">
							<td class="tdtitle">���X�u����:</td>
							<td><input type="TEXT" name="unattend" size="45"
								value="<%=mbrpfVO.getUnattend()%>" /></td>
						</tr>
						<tr style="display: none">
							<td class="tdtitle">�`�ѹΦ���:</td>
							<td><input type="TEXT" name="ttattend" size="45"
								value="<%=mbrpfVO.getTtattend()%>" /></td>
						</tr>
						
<!-- 						<tr><td> -->
<!-- 						<input type="hidden" name="action" value="forUpdate"> -->
<%-- 						<input type="hidden" name="mbrno" value="<%=mbrpfVO.getMbrno()%>"> --%>
<!-- 						<input type="submit" value="�e�X�ק�" style="border:0px; outline: none;"> -->
<!-- 						</td></tr> -->
						

					</table>
					<br>
						<input type="hidden" name="action" value="forUpdate">
						<input type="hidden" name="mbrno" value="<%=mbrpfVO.getMbrno()%>">
						<input type="submit" value="�e�X�ק�" style="border:0px; outline: none;color: red;"></FORM>
				</FORM>
			</div>

			<div class="col-sm"></div>
		</div>
	</div>




</body>



<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>
<script type="text/javascript">
	var loadFile1 = function(event){
		var output = document.getElementById("demo1");
		output.src = URL.createObjectURL(event.target.files[0]);
	};
</script>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '<%=mbrpfVO.getBirth()%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
           //startDate:	            '2017/07/10',  // �_�l��
           //minDate:               '-1970-01-01', // �h������(���t)���e
           //maxDate:               '+1970-01-01'  // �h������(���t)����
        });
        
        
   
        // ----------------------------------------------------------�H�U�ΨӱƩw�L�k��ܪ����-----------------------------------------------------------

        //      1.�H�U���Y�@�Ѥ��e������L�k���
        //      var somedate1 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});

        
        //      2.�H�U���Y�@�Ѥ��᪺����L�k���
        //      var somedate2 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});


        //      3.�H�U����Ӥ�����~������L�k��� (�]�i���ݭn������L���)
        //      var somedate1 = new Date('2017-06-15');
        //      var somedate2 = new Date('2017-06-25');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //		             ||
        //		            date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
        
</script>
</html>