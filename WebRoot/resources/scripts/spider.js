$(document).ready(function(){  
	
	window.setInterval(function(){ 
	getcount('all'); 
	}, 2000); 

}); 

function start(puid){
     var stamp2 = document.getElementById(puid+"start");
       stamp2.disabled="disabled";
       document.getElementById(puid+"start").style.backgroundColor="#ccc";


	window.setInterval(function(){ 
	getcount(puid); 
	}, 2000); 

	  $.ajax({  
	           
               url :"spiderAction_start.action?puid="+puid,  //后台处理程序  
               type:"post",    //数据发送方式  
               async:false,       
                          
            });  
}
function stop(puid){
 var stamp2 = document.getElementById(puid+"start");
       stamp2.disabled=false;
       document.getElementById(puid+"start").style.backgroundColor="#459300";
 $.ajax({  
               url :"spiderAction_stop.action?puid="+puid,  //后台处理程序  
               type:"post",    //数据发送方式  
               async:false,       
                          
            }); 
}
function getcount(puid) {
//   if(document.getElementById("jdstart").disabled == false ){
//		//通过开始按钮的可用不可用状态判断是否循环获取页面条数
//	return false;
//		
//	}
	$.ajax({ 
   		  
  		  url: "spiderAction_spidercount.action?puid="+puid, 
  		  type:"post",
  		  async:true,
  		  dataType:"json", 
    	  success: function(data){ 
    	
          var InHtmlContent=document.getElementById(data.puid+'ProductCount');
			 InHtmlContent.innerHTML=data.ProductCount+"条";
		  var InHtmlContent=document.getElementById(data.puid+'ReviewCount');
			 InHtmlContent.innerHTML=data.ReviewCount+"条";
		  var InHtmlContent=document.getElementById(data.puid+'TargetCount');
			 InHtmlContent.innerHTML=data.TargetCount+"页面";
			 
     }
    });
	
  
		

}