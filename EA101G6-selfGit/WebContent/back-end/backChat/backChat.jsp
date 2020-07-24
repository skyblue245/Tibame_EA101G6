<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="connectionpool.*"%>
<%@ page import="redis.clients.*"%>
<%@ page import="com.websocket.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>		
<!DOCTYPE html>
<html>
<head>
<title>Chat Room</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

</head>

<style>
	#messagesArea{
		width:100%;
		height:400px;
		padding:20px;
		color:#ffffff;
		box-sizing:border-box;
		overflow-x:hidden;
		overflow-y:scroll;
	}
/* 	#massageZone{ */
/* 		display:none; */
/* 	} */
	
	#messagesArea div.textDiv{
		margin:10px 5px;
		height:auto;
		font-size:20px;
		border-radius:10px;
		background-color:blue;
		width:auto;
		display:block;
		padding:5px;
		word-break: break-all;
		
	}
	div.inDiv{
		width:200px;
	}
	

	div.customer{
		width:110px;
		text-align:center;
	 	transform:rotate(180deg);
	 	position:fixed;
		right:50px;
		bottom:120px;
		display:none;
		background-color:#ffffff;
		border:1px solid;
	}

	
	button#customerBtn{
		position:fixed;
		right:60px;
		bottom:50px;
		border:0px;
		border-width: 0px;
		
	}
	
	img.msgicon{
		width:60px;
		height:60px;
		border-radius:40px;
		
	}
	
	input#message{
		width:320px;
	}
	
	div.modal-footer button.btn{
		width:60px;
		height:30px;
		padding:0px;
		margin:0px 10px;
	}
	
	div.modal-footer button.btn:hover{
		background-color:#007bff;
		color:#ffffff;
	}
	
	div.customer button.btn{
		transform:rotate(180deg);
		background-color:transparent;
		color:#000000;
		height:30px;
		padding:0px;
		border:0px;
		display:block;
		margin:0px auto;
		width:100%;
	}
	
	div.customer button.btn:hover{
		background-color:#007bff;
		color:#ffffff;
		width:100%;
		height:30px;
		padding:0px;
		border:0px;
		
	}
	
	
	button.msgBtn{
		border:none;
		background-color:transparent;
		outline:none;
	}
	
	button.msgBtn:hover{
		border:0px;
	}
	
	button#customerBtn div.redPoint{
		border-radius:50%;
		background-color:red;
		width:15px;
		height:15px;
		position:relative;
		right:-40px;
		bottom:-15px;
		margin:0px;
		display:none;
	}
	
</style>

<body onload="connect();" onunload="disconnect();">

<div id="basicModal" class="modal" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title"></h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
   		<div id="massageZone">
			<div id="messagesArea">
				<div id="msg_end" style="height:0px; overflow:hidden"></div>
			</div>
		</div>
      </div>
      
      <div class="modal-footer">
      	<input id="message" type="text">
		<button id="sendMessage" class="btn btn-primary">傳送</button> 
        <button type="button" onclick="done();" class="btn btn-primary" data-dismiss="modal">完成</button>
      </div>
    </div>
  </div>
</div>
	
	
	<div class="customer">
	</div>
	<button id="customerBtn" class="msgBtn"><div class="redPoint"></div><img class="msgicon" src="<%=request.getContextPath()%>/image/message-icon.png"></button>
	
		
	<% 
	request.setAttribute("account","LE00001");
	request.setAttribute("seName","客服1號");
	%>
	
	<script>
	var changeReceiver,changeSeName;
	var messagesArea=document.getElementById("messagesArea");
	var statusOutput = document.getElementById("statusOutput");
	var webSocket;
	var set=new Set();
	
	$("#customerBtn").click(function(){
		$(".customer").slideToggle();
		if($("div.customer").val()==""){
			$("div.customer").css("border","0px");
		}else{
			$("div.customer").css("border","1px");
		}	
	})
	
	
	
	function connect() {
		let MyPoint = "/MyWebSocket/${account}";
		let host = window.location.host;
		let path = window.location.pathname;
		let webCtx = path.substring(0, path.indexOf('/', 1));
		let endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
		webSocket = new WebSocket(endPointURL);

		webSocket.onopen = function(event) {
			
		};

		webSocket.onmessage = function(event) {
			let msgObj = JSON.parse(event.data);
			//因為歷史訊息是一陣列
			if(Array.isArray(msgObj)){
				if(JSON.parse(msgObj[0]).type=="unDone"){
					for (let i = 0; i < msgObj.length; i++) {
						let unDone=JSON.parse(msgObj[i]);
						unDoneShow(unDone);
						set.add(unDone.sender);
					}
					return;
				}
				//歷史訊息
				//讓聊天室窗初始化
				let msg_end=document.getElementById("msg_end");
				let tampmsg_end = $(msg_end).clone();
				$(messagesArea).text("");
				$(messagesArea).append(tampmsg_end);
				for (let i = 0; i < msgObj.length; i++) {
					let messageObj = JSON.parse(msgObj[i]);
					showMsg(messageObj);
				}
				
			}else if(msgObj!=null){
				
				if(msgObj.type=="unDone"){
					unDoneShow(msgObj);

					return;
				}
				//取出set裡的值做比較
				showMsg(msgObj);
			    for (let str of set) {
			        if(str == msgObj.sender) {
			            return;
			        }
			    }
				alert("有新訊息");
			    set.add(msgObj.sender);
			    unDoneShow(msgObj);
				
			}
			
		};

		webSocket.onclose = function(event) {
	
		};
	}
	//傳送訊息changeReceiver是變動的隨點及變動
	$("#sendMessage").click(function() {
		let message = $("#message").val().trim();		
		
		if(message.length==0)
			return;
			
		let msgObj = {
			"sender" : "${account}",
			"seName":"${seName}",
			"message" : message,
			"receiver" : changeReceiver
		};
		
		let msg=JSON.stringify(msgObj);
		webSocket.send(msg);
		showMsg(msgObj);
		$("#message").val("");
		$("#message").focus();
		
	})

	function disconnect() {
		webSocket.close();
	}
	
	function getHistory(receiver,seName) {
		let msgObj = {
				"sender" : "${account}",
				"seName":"${seName}",
				"receiver" : receiver,
				"type":"history"
			};
		let msg=JSON.stringify(msgObj);
		webSocket.send(msg);
		$("#basicModal").modal({show: true});
		$("h5.modal-title").text(seName+"訊息");
	}
	
	function showMsg(msg) {
		let msg_end=document.getElementById("msg_end");
		let message = msg.seName + ":" + msg.message + "\r\n";
		let outDiv=document.createElement("div");
		let inDiv=document.createElement("div");
		let textDiv=document.createElement("div");
		$(inDiv).addClass("inDiv");
		$(outDiv).addClass("outDiv");
		$(textDiv).addClass("textDiv");
		if(msg.sender==="${account}"){
		$(outDiv).css({"text-align":"left",
						"position":"relative",
						"right":"-220px"});
		}
		$(textDiv).text(message);
		$(outDiv).append(inDiv);
		$(inDiv).append(textDiv);
		$(msg_end).before(outDiv);
		msg_end.scrollIntoView(); 
	}
	//show出未完成的按鈕
	function unDoneShow(unDone){
		let button=document.createElement("button");
		$(button).text(unDone.seName);
		$(button).addClass("btn btn-primary");
		$(button).click(function(){
					getHistory(unDone.sender,unDone.seName)
					changeReceiver=unDone.sender;
					changeSeName=unDone.seName;
					});
		$(button).addClass(unDone.sender);
		$("div.customer").append(button);
		$("button#customerBtn div.redPoint").show();
	}
	
	function done(){
		let msgObj = {
				"type":"unDone",
				"sender" : changeReceiver,
				"seName":changeSeName
			};
		let msg=JSON.stringify(msgObj);
		webSocket.send(msg);
		$("div.customer button."+changeReceiver).remove();
		set.delete(changeReceiver);
		if($("div.customer").val()==""){
			$("button#customerBtn div.redPoint").hide();
		}	
	}
	
	function prompt(){
		
	}

</script>
	
</body>


</html>