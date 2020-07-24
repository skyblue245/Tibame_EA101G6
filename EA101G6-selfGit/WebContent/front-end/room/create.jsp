<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.*"%>
<%@ page import="com.rminfo.model.*"%>
<%@ page import="com.joinrm.model.*"%>
<%@ page import="java.sql.*"%>
<%
	RminfoService rminfoSvc = new RminfoService();
	List<RminfoVO> list = rminfoSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<%
	RminfoVO rminfoVO = (RminfoVO) request.getAttribute("rminfoVO");
%>

<jsp:useBean id="mbrpfSvc" scope="page" class="com.mbrpf.model.MbrpfService" />
<jsp:useBean id="shopSvc" scope="page" class="com.shop.model.ShopService" />
<jsp:useBean id="joinrmSvc" scope="page" class="com.joinrm.model.JoinrmService" />

<!DOCTYPE html>
<html>
<head>
<title>�ж��C��+�}��+�[�J</title>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
<script type="text/javascript"> var jQuery_1_12_2 = $.noConflict(true); </script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>

</head>

<body>
<c:if test="${not empty joinMsg}">
	<script>
	Swal.fire({
		  icon: 'error',
		  title: '${joinMsg}',
		})
	</script>
</c:if>
<c:if test="${not empty joinSuccessMsg}">
	<script>
		Swal.fire({
			  position: 'center',
			  icon: 'success',
			  title: '${joinSuccessMsg}',
			  showConfirmButton: false,
			  timer: 1500
			})
	</script>
</c:if>
<%@ include file="/front-end/front-end-nav.jsp"%>
<!-- �}�Ϊ�� -->
	<div id="dialog-form" title="�]�w���θ�T">
		<span style="color:red;">��*���������</span>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">�Эץ��H�U���~:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
		<form id="myMBRform" METHOD="post" ACTION="rminfo.do">
			<fieldset>
				<input readonly type="hidden" name="mbrno" id="mbrno" value="${mbrpfVO.mbrno}" class="text ui-widget-content ui-corner-all"> 
				<label for="naming">*�ЦW: </label>	
				<input type="text" name="naming" id="naming" value="<%= (rminfoVO==null)? "�Ѷ½г���!!": rminfoVO.getNaming()%>" class="text ui-widget-content ui-corner-all" maxlength="10">
				*�C���a�I:
				<select name="shopno">
					<c:forEach var="shopVO" items="${shopSvc.getAll()}">
<%-- 						<option value="${shopVO.shopno}" ${(param.shopno==shopVO.shopno)? 'selected':'' }>${shopVO.shopname} --%>
						<option value="${shopVO.shopno}" ${("DS00001"==shopVO.shopno)? 'selected':'' }>${shopVO.shopname}
					</c:forEach>
				</select><br><br>
				
				*�H�ƭ���: 
				<select name="lowlimit" id="lowlimit">
					<%for (int i = 2; i <= 20; i++) {%>
					<option value="<%=i%>"><%=i%></option>
					<%}%>
				</select> 
				<font>~</font> 
				<select name="uplimit" id="uplimit" value="4">
					<%
						for (int i = 2; i <= 20; i++) {
					%>
					<%
						if (i == 4) {
					%>
					<option value="<%=i%>" selected><%=i%></option>
					<%
						}
					%>
					<option value="<%=i%>"><%=i%></option>
					<%
						}
					%>
				</select> <font>�H</font><br> <br> <label for="starttime">*�C���}�l�ɶ�</label>
				<input type="text" name="starttime" id="f_date2"
					class="text ui-widget-content ui-corner-all"> <label
					for="endtime">*�C�������ɶ�</label> <input type="text" name="endtime"
					id="f_date3" class="text ui-widget-content ui-corner-all">
				*�w�p�����C��: <br>
				<textarea placeholder="��J�Q�����C�� EX:�d�d�|" required="required"
				     name="game" maxlength="30" 
				     style="resize: none; width: 280px; height: 60px;"><%--<c:if test="${not empty requestScope.rminfoVO}">${requestScope.rminfoVO.game}</c:if>--%>�T�H��
				</textarea>
				<br> <br>  *��������: <select name="restriction">
					<option value="0">�L</option>
					<%
						for (int i = 1; i <= 5; i++) {
					%>
					<option value="<%=i%>"><%=i%></option>
					<%
						}
					%>
				</select><br> <br> �Ƶ�: <br>
				<textarea name="remarks" maxlength="100"
					style="resize: none; width: 280px; height: 120px;"><%= (rminfoVO==null)? "": rminfoVO.getRemarks()%></textarea>

				<input type="hidden" name="action" value="insert">
				<!--  	  <input type="submit" value="�e�X�s�W"> -->
				<!-- Allow form submission with keyboard without duplicating the dialog button -->
				<!--       <input type="submit" tabindex="-1" style="position:absolute; top:-1000px"> -->
			</fieldset>
		</form>
	</div>


	<div id="users-contain" class="ui-widget">
		<h3>�ж��C��:</h3>
		<button id="create-user" type="button" class="btn btn-info btn-lg">�ڭn�}��</button>
	</div>

<!-- �ж��C�� -->
<div id="allCard">
	<c:forEach var="rminfoVO" items="${list}">
	<c:if test="${rminfoVO.status < 4}">
		<div class='cards' id="${rminfoVO.rmno}_info">
			<div>
				<form METHOD="post" ACTION="joinrm.do">
					<input type="hidden" name="rmno" value="${rminfoVO.rmno}">
					<input type="hidden" name="mbrno" value="${mbrpfVO.mbrno}"> 
					<input type="hidden" name="action" value="insert"> 
					<c:choose>
		    			<c:when test="${fn:length(joinrmSvc.findByPK(rminfoVO.rmno,'')) eq rminfoVO.uplimit}">
		    				<input class="btn btn-warning btn-sm" type="submit" value="�H�Ƥw��" disabled>
		   		 		</c:when>					
						<c:otherwise>
			    			<input class="btn btn-warning btn-sm" type="submit" value="�[�J" onclick="return(confirm('�T�w�n�[�J�ܡH�T�{��N�L�k�h�X'))">
			    		</c:otherwise>
					</c:choose>
				</form>				
			</div>
			<div id="dialog3_${rminfoVO.rmno}" title="�����C��">
				<jsp:include page="/front-end/room/roomMember.jsp"><jsp:param name="rmno" value="${rminfoVO.rmno}" /></jsp:include>
			</div>
<script>
(function($){
  $( function() {
    $( "#dialog3_${rminfoVO.rmno}" ).dialog({
      autoOpen: false,
      show: {
        effect: "blind",
        duration: 1000
      },
      hide: {
        effect: "explode",
        duration: 1000
      },
      width: 400,
    });
 
    $( "#opener3_${rminfoVO.rmno}" ).on( "click", function() {
      $( "#dialog3_${rminfoVO.rmno}" ).dialog( "open" );
    });
  } );
  
})(jQuery_1_12_2);  
 </script>
			<div class='roomtitle'>
				<span class='titleType'><b>${rminfoVO.naming}</b></span>
			</div>
			<div style="height:32px">�ХD: ${mbrpfSvc.getOneMbrpf(rminfoVO.mbrno).mbrname}<button class="btn btn-outline-info btn-sm" id="opener3_${rminfoVO.rmno}">�ѥ[����</button></div>
			<div style="height:32px">�C�����a: ${shopSvc.getOneShop(rminfoVO.shopno).shopname}
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/shop/shop.do" target="_blank" id="shopInfo">
					<input type="hidden" name="shopno" value="${rminfoVO.shopno}">
					<input type="hidden" name="action" value="getOne_For_Display">
					<input type="submit" value="���a��T" class="btn btn-outline-primary btn-sm">
				</FORM>
			</div>
			<div>
				�H�ƭ���:
				${rminfoVO.lowlimit}~${rminfoVO.uplimit}�H&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
				${fn:length(joinrmSvc.findByPK(rminfoVO.rmno,''))}/${rminfoVO.uplimit}
			</div>
			<div>
				�C���ɶ�:
				<fmt:formatDate value="${rminfoVO.starttime}"
					pattern="yyyy-MM-dd HH:mm" />
				~
				<fmt:formatDate value="${rminfoVO.endtime}" pattern="HH:mm" />
			</div>
			<div class="game">�w�p�����C��: ${rminfoVO.game}</div>
			<div>
				���W�I��ɶ�:
				<fmt:formatDate value="${rminfoVO.cutoff}"
					pattern="yyyy-MM-dd HH:mm:ss" />
			</div>
			<div>��������: ${(rminfoVO.restriction == '0')?'�L':rminfoVO.restriction}</div>
			<div class="remark"><span style="color:red;">${rminfoVO.remarks}</span></div>
		</div>
	</c:if>
	</c:forEach>
</div>
</body>
<%
	java.sql.Timestamp starttime = null;

	if(request.getParameter("shoppds") != null){
		String starttimeStr = request.getParameter("shoppds");
		starttime = Timestamp.valueOf(starttimeStr);
	}else{
		try {
			starttime = rminfoVO.getStarttime();
		} catch (Exception e) {
			starttime = new java.sql.Timestamp(
					(System.currentTimeMillis() / 1800000) * 1800000 + (1000 * 60 * 60 * 72));
		}
	}	
%>
<%
	java.sql.Timestamp endtime = null;
	
	if(request.getParameter("shoppde") != null){
		String endtimeStr = request.getParameter("shoppde");
		endtime = Timestamp.valueOf(endtimeStr);
	}else{
		try {
			endtime = rminfoVO.getEndtime();
		} catch (Exception e) {
			endtime = new java.sql.Timestamp(
					(System.currentTimeMillis() / 1800000) * 1800000 + (1000 * 60 * 60 * 75));
		}
	}
	
%>
<script>
(function($){
        $.datetimepicker.setLocale('zh');
        $('#f_date2').datetimepicker({
	       theme: '',              //theme: 'dark',
// 	       timepicker:false,       
	       timepicker:true,
	       step: 30,                //step: 60 (�o�Otimepicker���w�]���j60����)
// 	       format:'Y-m-d',         
			format:'Y-m-d H:i:s',
<%-- 		   value: '<%=starttime%>', --%>
		   value: '2020-08-02 12:00:00',
		   // value:   new Timestamp(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
           //startDate:	            '2017/07/10',  // �_�l��
           minDate:               '-1970-01-01', // �h������(���t)���e
           //maxDate:               '+1970-01-01'  // �h������(���t)����
        });        

        $.datetimepicker.setLocale('zh');
        $('#f_date3').datetimepicker({
	       theme: '',              //theme: 'dark',
// 	       timepicker:false,       
	       timepicker:true,
	       step: 30,                //step: 60 (�o�Otimepicker���w�]���j60����)
// 	       format:'Y-m-d',         
			format:'Y-m-d H:i:s',
<%-- 		   value: '<%=endtime%>', --%>
		   value: '2020-08-02 16:00:00',
		// value:   new Timestamp(),
		//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
		//startDate:	            '2017/07/10',  // �_�l��
		minDate : '-1970-01-01', // �h������(���t)���e
	//maxDate:               '+1970-01-01'  // �h������(���t)����
	});
        
       
        	  $( function() {
        	    var dialog, form,
        	 
        	      mbrno = $( "#mbrno" ),
        	      naming = $( "#naming" ),
        	      shopno = $( "#shopno" ),
        	      lowlimit = $( "#lowlimit" ),
        	      uplimit = $( "#uplimit" ),
        	      starttime = $( "#starttime" ),
        	      endtime = $( "#endtime" ),
        	      game = $( "#game" ),
        	      cutoff = $( "#cutoff" ),
        	      remarks = $( "#remarks" ),     
        	      restriction = $( "#restriction" ),
        	      confirmed = $( "#confirmed" ),
        	      
        	      allFields = $( [] ).add( mbrno ).add( naming ).add( shopno ).add( lowlimit ).add( uplimit ).add( starttime )
        	      .add( endtime ).add( game ).add( cutoff ).add( remarks ).add( restriction ).add( confirmed ),
        	      tips = $( ".validateTips" );
        	 
        	    function updateTips( t ) {
        	      tips
        	        .text( t )
        	        .addClass( "ui-state-highlight" );
        	      setTimeout(function() {
        	        tips.removeClass( "ui-state-highlight", 1500 );
        	      }, 500 );
        	    }
        	 	
        	    function addUserXX() {
        	 		$('#myMBRform').submit();
        	 	}
        	 	
        	    dialog = $( "#dialog-form" ).dialog({
        	      autoOpen: false,
        	      height: 500,
        	      width: 350,
        	      modal: true,
        	      buttons: {
        	        "�إߩж�": addUserXX,
        	        "����": function() {
        	          dialog.dialog( "close" );
        	        }
        	      },
        	      close: function() {
//        	     	$('#myMBRform')[0].reset();
        	        allFields.removeClass( "ui-state-error" );
        	      }
        	    });
        	 
        	    $( "#create-user" ).button().on( "click", function() {
        	    		
        	    		
	        	    <c:choose>
		    			<c:when test="${not empty account}">
		    				dialog.dialog( "open" );
		   		 		</c:when>					
						<c:otherwise>
						Swal.fire({
							  icon: 'error',
							  title: '�Х��n�J',
							})
			    		</c:otherwise>
					</c:choose>
        	    });
        	  } );


	dialog2 = $("#dialog-form")
	
	$(window).load(function() {
		<c:if test="${not empty errorMsgs}">
			dialog2.dialog("open");
		</c:if>
	});
	$(window).load(function() {
		<c:if test="${not empty param.shopno}">
			dialog2.dialog("open");
		</c:if>
	});
	
})(jQuery_1_12_2);  	
</script>
<script>
	var cards = document.getElementsByClassName('cards');
	for(var i = 0; i < cards.length; i++){
		var r = Math.floor((Math.random()*4)+1);
		cards[i].style.backgroundImage="url(<%=request.getContextPath()%>/image/poker" + r +".jpg)";
	}
</script>
<style>
  
#allCard{
	margin:50px 5% 50px 15%;
	width:80%;
}
#create-user{
	float:right;
	display: inline;
	margin:15px 70px;
}
.btn-warning{
	float:right;
	margin:10px 5px;
}
.btn-outline-info{
	float:right;
	margin:0px 5px;
}
.btn-outline-primary{
	float:right;
	margin:0px 5px;
}
#shopInfo{
	display:inline;
	margin:0px;
}
#form {
	display: none;
}

label, input {
	display: block;
}

input.text {
	margin-bottom: 12px;
	width: 95%;
	padding: .4em;
}

fieldset {
	padding: 0;
	border: 0;
	margin-top: 25px;
}

h3 {
	margin: .6em 50px;
	display: inline-block;
	color:red;
}

div#users-contain {
	width: 90%;
	margin: 20px 10px;
}

div#users-contain table {
	margin: 1em 0;
	border-collapse: collapse;
	width: 100%;
}

div#users-contain table th {
	border: 1px solid #eee;
	padding: .6em 10px;
	text-align: left;
}

div#users-contain table td {
	border: 1px solid #eee;
	padding: .6em 10px;
	text-align: center;
}

.ui-dialog .ui-state-error {
	padding: .3em;
}

.validateTips {
	border: 1px solid transparent;
	padding: 0.3em;
}

.roomtitle {
	background:;
	font-size: 24px;
	width: 312px;
	height: 34px;
	margin: 60px 0px 30px 25px;
	padding: 10px 16px;
	text-align: center;
	color:#42454C;
}
.cards {
	width: 344px;
	height: 440px;
	display: inline-block;
	margin: 25px;
	background-size: 395px 490px;
	background-position: -25px -30px; 
	border: none;
}

.cards div {
	padding:0px 30px;
}
.remark{
	width:100%;
	height:20%;
	overflow: auto;
}
.game{
	width:100%;
	height:7%;
	overflow: auto;
}
</style>
</html>