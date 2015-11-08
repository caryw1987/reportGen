
	 function validateData(obj){
	  	  if (obj.value == ""){
	  		$("#cm_"+obj.id).html('<img src="resources/images/icons/cross_circle.png" height="16px" width="16px"/>'+" 不能为空!");
	  		
	  		  return false;
	  	  }
	  	  if(obj.id == "productBarCode"){
	  		if(!(/^[0-9]*$/.test($("#productBarCode").val()))){
		  		 $("#cm_"+obj.id).html('<img src="resources/images/icons/cross_circle.png" height="16px" width="16px"/>'+" 并且必须为数字!");
		  		 $("#productBarCode").val("");
		  		 return false;
		  	} 
	  	} 
	  	if(obj.id == "registeredCapital"){
	  		if(!(/^((\d{1,3}(,\d{3})+?|\d+)(\.\d{2})?|(\.\d{2}))$/.test($("#registeredCapital").val()))){
		  		 $("#cm_"+obj.id).html('<img src="resources/images/icons/cross_circle.png" height="16px" width="16px"/>'+" 只能为数字并且小数点后只能有两位数!");
		  		 $("#registeredCapital").val("");
		  		 return false;
		  	} 
	  	} 
	  	 $("#cm_"+obj.id).html('<img src="resources/images/icons/tick_circle.png" height="16px" width="16px"/>');
	}
	 
	 function validateImg(){
		 if($("#epuid").val()=="" && $("#image").val()==""){
			 $("#images").html('<img src="resources/images/icons/cross_circle.png" height="16px" width="16px"/>'+" 不能为空!");
			 return false;
		 }
		 $("#images").html('<img src="resources/images/icons/tick_circle.png" height="16px" width="16px"/>');
	 }
	 
	 function validates(obj){
		 if($("#stdName").val()=="" && $("#stdEname").val()==""){
			 $("#cc_stdName").html('<img src="resources/images/icons/cross_circle.png" height="16px" width="16px"/>'+" 标准名称或标准英文名称不能为空!");
			 $("#cc_stdEname").html('<img src="resources/images/icons/cross_circle.png" height="16px" width="16px"/>'+" 标准名称或标准英文名称不能为空!");
			 return false;
		 }
		 $("#cc_stdName").html('<img src="resources/images/icons/tick_circle.png" height="16px" width="16px"/>');
		 $("#cc_stdEname").html('<img src="resources/images/icons/tick_circle.png" height="16px" width="16px"/>');
		 
	 }
	 function validatea(obj){
		 if($("#file").val()=="" && $("#image").val()==""){
			 $("#cc_file").html('<img src="resources/images/icons/cross_circle.png" height="16px" width="16px"/>'+" 文件或图片上传必须有一个!");
			 $("#cc_images").html('<img src="resources/images/icons/cross_circle.png" height="16px" width="16px"/>'+" 文件或图片上传必须有一个!");
			 return false;
		 }
		 $("#cc_file").html('<img src="resources/images/icons/tick_circle.png" height="16px" width="16px"/>');
		 $("#cc_images").html('<img src="resources/images/icons/tick_circle.png" height="16px" width="16px"/>');
		 	
	 }
	 
	 function validate(obj){
		 if(obj.value=="-1"){
			 $("#cd_"+obj.id).html('<img src="resources/images/icons/cross_circle.png" height="16px" width="16px"/>'+" 不能为空!");
			 return false;
		 }
		 $("#cd_"+obj.id).html('<img src="resources/images/icons/tick_circle.png" height="16px" width="16px"/>');
	}