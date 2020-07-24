//讀檔的全域變數
var reader = new FileReader();

$(document).ready(function() {
	
	
	$("#addDiv .cancel").click(function() {
		// 清空屬性
		$(".text").val("");
		$(".other input").val("");
		$(".other select").val("0");
		$("#addDiv .showimg img").remove();
	})

	// 預覽圖片function
	$("#addDiv .upload").change(function() {
		let myfile = this.files;
		// 獨到檔案啟動
		$(reader).on("load", function(e) {
			// img屬性
			$("#addDiv .showimg img").css({
				"width" : "250",
				"height" : "250px"
			});
			// 設img src
			$("#addDiv .showimg img").attr("src", e.target.result);
		});
		// 讀檔
		reader.readAsDataURL(myfile[0]);
	});

	hov();
	// 預覽事件
	function hov() {
		$("#comm td div").click(function() {
			$("#comm").css({
				"opacity" : "0.3"
			});
			let intro = document.createElement("div");
			let button = document.createElement("button");
			$(button).text("關閉");
			$(intro).attr({
				"id" : "introshow",
			});
			$(intro).text($(this).text());
			$(intro).addClass("intro");
			$(button).addClass("btn btn-secondary");
			$(button).css({"display":"block",
							"margin-left":"43%",
							"margin-top":"10px"});
			$(intro).append(button);
			$("body").append(intro);
			
			$(button).click(function() {
				$("#comm").css({
					"opacity" : "1"
				});
				$(this).css({
					"color" : "#000000"
				});
				$("#introshow").remove();

			})
			
		});
		

	}


	// 預覽圖片function
	$("#updateDiv .upload").change(function() {
		let myfile = this.files;
		// 獨到檔案啟動
		$(reader).on("load", function(e) {
			// img屬性
			$("#updateDiv .showimg img").css({
				"width" : "250",
				"height" : "250px"
			});
			// 設img src
			$("#updateDiv .showimg img").attr("src", e.target.result);
		});
		// 讀檔
		reader.readAsDataURL(myfile[0]);
	});

	
	//叫出遊戲類型
	$("button#callGmType").click(function(){
		  $("div.gmtypezone").slideToggle();
		  $("#comm td div").off();
	  })
	  //隱藏遊戲類型
	 $("button.typecancel").click(function(){
		  $("div.gmtypezone").slideToggle();
		  $("div.gmtypezone button.confirm").attr('disabled', false);
		  $("div#delalert").hide();
		  setTimeout(hov, 500);
		  
	  })
	  var typeNo="";
	  //刪除按鈕
	  $("button.deltypebtn").click(function(){
		  typeNo=$(this).val();
		  $("div#delalert").toggle();
		  $("div.gmtypezone button.confirm").attr('disabled', true);
		  		  
	  });
	//遊戲類型警告確定
	  $("div#delalert button.confirm").click(function(){
		  $("div#delalert").toggle();
		  $("div.gmtypezone input.confirm").attr('disabled', false);
		  $("div.gmtypezone button.confirm").attr('disabled', false);
		  $.post(ctx+"/GmType/GmTypeServlet",
					{
			  			action:"delete",
			  			typeNo:typeNo
					},
					function(data,status){
						if(status=="success"){
							$("div."+typeNo+"").remove();
							Swal.fire({
								  position: 'center',
								  icon: 'success',
								  title: data,
								  showConfirmButton: false,
								  timer: 1500
								})
						}	
					}
			)
		}
	  
	  );
  
	   //遊戲類型警告取消
	  $("div#delalert button.cnacel").click(function(){
			  $("div#delalert").toggle()
			  $("div.gmtypezone button.confirm").attr('disabled', false);
		});
	//遊戲類型確定
	  $("div.gmtypezone button.confirm").click(function(){
		  if($("#typeNameInput").val().length==0||$("#typeNameInput").val().length>10){
				Swal.fire({
					  icon: 'error',
					  title: '錯誤訊息',
					  text:"請確認長度"
					})
					return;
		  }
		  
		  
		  $.ajax({
			  dataType: "json",
			  url: ctx+"/GmType/GmTypeServlet",
			  type: "POST",
			  data: {
		  			action:"add",
		  			typeName:$("#typeNameInput").val()
				},
			  success:function(data){
						Swal.fire({
						  position: 'center',
						  icon: 'success',
						  title: data.msg,
						  showConfirmButton: false,
						  timer: 1500
						})
						let tampDiv=document.createElement("div");
						let tampP=document.createElement("p");
						let tampButton=document.createElement("button");
						$(tampDiv).addClass(data.typeNo);
						$(tampButton).val(data.typeNo);
						$(tampButton).addClass("deltypebtn btn btn-secondary");
						$(tampButton).text("刪除");
						$(tampP).text(data.typeName);
						$("#tampAddDiv").before(tampDiv);
						$(tampDiv).prepend(tampButton);
						$(tampDiv).prepend(tampP);
						$("input#typeNameInput").val("");
						 $("button.deltypebtn").click(function(){
							  typeNo=$(this).val();
							  $("div#delalert").toggle();
							  $("div.gmtypezone button.confirm").attr('disabled', true);
							  		  
						  });
				} 
			});
		    
	  })
	  

});
