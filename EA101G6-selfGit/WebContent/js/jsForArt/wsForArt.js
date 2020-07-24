var mbrno = document.getElementById("mbrno").value;
var MyPoint = "/listAllArt/" + mbrno;
var host = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf('/',1));
var endPointURL = "ws://" + host +webCtx + MyPoint; //使用者地址

var webSocket;

//在後台listAllArt.jsp
var $artno = $("#wsArtno").val();
var $backAddArt = $("#backAddArt").val();

var $rep = $('#rep').val();


//在前台listOneArt.jsp
var $reEdit = $("#reEdit").val();

//在前台listAllArt.jsp
var $addArt = $("#addArt").val();



var jsondata;

if (mbrno !== '') {
	console.log(endPointURL);
	
	
	webSocket = new WebSocket(endPointURL); //建立連線到伺服器端 @onopen
	
	webSocket.onopen = function(event) { //成功連線後 伺服器端的@onopen 回應這邊的onopen
		console.log('success');
		
		//後台向伺服器端send資料 啟動伺服器端的 @onmessage
		if ($artno !== '' && $artno !== undefined) {
			//只送pk 從pk取值
			webSocket.send($artno);
		}
		
		//送出重新上架需要審核的文章
		if ($reEdit !== '' && $reEdit !== undefined) {
			sendReEdit();
		}
		
		//前台送出新上架的文章
		if ($addArt !== '' && $addArt !== undefined) {
			sendAddArt();
		}
		//後台送出新上架的文章
		if ($backAddArt !== '' && $backAddArt !== undefined) {
			sendBackAddArt();
		}
		if ($rep !== '' && $rep !== undefined) {
			sendRep();
		}
		
	};
	
	webSocket.onmessage = function(event) {
		console.log(event.data);
		swal.fire({
			icon: 'info',
			title: event.data,
			showConfirmButton: false,
			timer: 5000,
			footer: '快到討論區查看吧!'
		})
	}
	webSocket.onclose = function(event) {
		console.log('closed');
	}
	
	function sendReEdit() {
		webSocket.send($reEdit);
		if($reEdit !== ''){
			swal.fire({
				icon: 'success',
				title: '您已成功送出修改後的文章',
				text: '請等候審核',
				showConfirmButton: false,
				timer: 5000
			})
		}
	}
	function sendAddArt() {
		
		webSocket.send($addArt);
		if($addArt !== ''){
			swal.fire({
				icon: 'success',
				title: '您已成功新增文章',
				showConfirmButton: false,
				timer: 5000,
				footer: '快到個人文章查看吧!'
			})
		}
	}
	function sendBackAddArt() {
		
		webSocket.send($backAddArt);
		if($backAddArt !== ''){
			swal.fire({
				icon: 'success',
				title: '您已成功新增文章',
				showConfirmButton: false,
				timer: 5000,
			})
		}
	}
	function sendRep() {
		
		webSocket.send($rep);
		if($rep !== ''){
			swal.fire({
				icon: 'success',
				title: '您已下架該篇文章',
				showConfirmButton: false,
				timer: 5000,
			})
		}
	}
}