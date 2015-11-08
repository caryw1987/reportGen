<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Edit User</title>
    <style type="text/css">
        .clear {
            clear: both;
        }
    </style>
    <script type="text/javascript">
    	function chooseAdmin()
    	{
    		var test = $('#adminckbx').attr("checked");
    		if($('#adminckbx').attr("checked")){
    			$('#recorderckbx').removeAttr("checked");
    			$('#entryPersonckbx').removeAttr("checked");
    			$('#generatorckbx').removeAttr("checked");
    			$('#checkerckbx').removeAttr("checked");
    			$('#senderckbx').removeAttr("checked");
    			$('#addpriceckbx').removeAttr("checked");
    			$('#recorderckbx').attr('disabled', 'disabled');
    			$('#entryPersonckbx').attr('disabled', 'disabled');
    			$('#generatorckbx').attr('disabled', 'disabled');
    			$('#checkerckbx').attr('disabled', 'disabled');
    			$('#senderckbx').attr('disabled', 'disabled');
    			$('#addpriceckbx').attr('disabled', 'disabled');
    		}
    		else{
    			$('#recorderckbx').removeAttr("disabled");
    			$('#entryPersonckbx').removeAttr("disabled");
    			$('#generatorckbx').removeAttr("disabled");
    			$('#checkerckbx').removeAttr("disabled");
    			$('#senderckbx').removeAttr("disabled");
    			$('#addpriceckbx').removeAttr("disabled");
    		}
    	}
    	
    	function getPrivilege()
    	{
    		 var privilegeStr=$('#privilegeArray1').val();
    		 var privilegeArray= privilegeStr.split(",");
    		 for(var i=0; i<privilegeArray.length; i++)
    		 {
    		 	if(privilegeArray[i]=="admin")
    		 	{
    		 		$('#adminckbx').attr("checked", true);
    		 		$('#recorderckbx').removeAttr("checked");
    				$('#entryPersonckbx').removeAttr("checked");
    				$('#generatorckbx').removeAttr("checked");
    				$('#checkerckbx').removeAttr("checked");
    				$('#senderckbx').removeAttr("checked");
    				$('#recorderckbx').attr('disabled', 'disabled');
    				$('#entryPersonckbx').attr('disabled', 'disabled');
    				$('#generatorckbx').attr('disabled', 'disabled');
    				$('#checkerckbx').attr('disabled', 'disabled');
    				$('#senderckbx').attr('disabled', 'disabled');
    				$('#addpriceckbx').attr('disabled', 'disabled');
    				break;
    		 	}
    		 	else
    		 	{
    		 		if(privilegeArray[i]=="recorder" || privilegeArray[i]==" recorder")
    		 		{
    		 			$('#recorderckbx').attr("checked", true);
    		 		}
    		 		else if(privilegeArray[i]=="entryPerson" || privilegeArray[i]==" entryPerson")
    		 		{
    		 			$('#entryPersonckbx').attr("checked", true);
    		 		}
    		 		else if(privilegeArray[i]=="generator" || privilegeArray[i]==" generator")
    		 		{
    		 			$('#generatorckbx').attr("checked", true);
    		 		}
    		 		else if(privilegeArray[i]=="sender" || privilegeArray[i]==" sender")
    		 		{
    		 			$('#senderckbx').attr("checked", true);
    		 		}
    		 		else if(privilegeArray[i]=="checker" || privilegeArray[i]==" checker")
    		 		{
    		 			$('#checkerckbx').attr("checked", true);
    		 		}
    		 		else if(privilegeArray[i]=="addpricec" || privilegeArray[i]==" addpricec")
    		 		{
    		 			$('#addpriceckbx').attr("checked", true);
    		 		}
    		 	}
    		 }
    	}
    </script>
    <jsp:include page="/public/work_bench_reference.jsp"></jsp:include>
</head>
<body onload="getPrivilege()">
    <jsp:include page="/public/work_bench_top.jsp"></jsp:include>
	<jsp:include page="/public/work_bench_left.jsp"></jsp:include>
	<div id="body-wrapper">
  <!-- Wrapper for the radial gradient background -->
  <!-- End #sidebar -->
  <div id="main-content">
    <!-- Main Content Section with everything -->
    <noscript>
    <!-- Show a notification if the user has disabled javascript -->
    <div class="notification error png_bg">
      <div> Javascript is disabled or is not supported by your browser。
      </div>
    </div>
    </noscript>
    <!-- Page Head -->
    <!-- End .shortcut-buttons-set -->
    <div class="clear"></div>
    <!-- End .clear -->
    <div class="content-box" style="width:1060px;">
      <!-- Start Content Box -->
      <div class="content-box-header">
        <h3>添加用户</h3>
      </div>
      <!-- End .content-box-header -->
     <div class="content-box-content"> 
	 <form enctype="multipart/form-data" action="userAction_editUser.action?currentPage=1&pageSize=100" method="post">
  	  	<p>
  	  		登录名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	  		<input type="hidden" name="user.puid" value="<s:property value="user.puid"/>"  />
  	  		<input class="text-input small-input" type="text" name="user.login" value="<s:property value="user.login" />" readonly="readonly" />
  	  	</p>
  	  	<p>
  	  		密码：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	  		<input class="text-input small-input" type="text" name="user.password" value="<s:property value="user.password" />" />
  	  	</p>
  	  	<p>
  	  		密码（再次输入）：
  	  		<input class="text-input small-input" type="text" name="rePassword" value="<s:property value="user.password" />" />
  	  	</p>
  	  	<p>
  	  		用户名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	  		<input class="text-input small-input" type="text" name="user.name" value="<s:property value="user.name" />" />
  	  	</p>
  	  	<p>
  	  		邮箱：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	  		<input class="text-input small-input" type="text" name="user.email" value="<s:property value="user.email" />" />
  	  	</p>
  	  	<p>
  	  		电话：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	  		<input class="text-input small-input" type="text" name="user.tel" value="<s:property value="user.tel" />" />
  	  	</p>
  	  	<p>
  	  		
  	  		<input type="hidden" id="privilegeArray1" name="privilegeArray1" value="<s:property value="privilegeArray"/>"  />
  	  		<s:if test="#session.isAdmin==true">
                             权限：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	  		<input id="adminckbx" type="checkbox" name="privilegeArray" value="admin" onclick="chooseAdmin()" />admin&nbsp;
        	<input id="recorderckbx" type="checkbox" name="privilegeArray" value="recorder"/>recorder&nbsp;
        	<input id="entryPersonckbx" type="checkbox" name="privilegeArray" value="entryPerson"/>importer&nbsp;
        	<input id="generatorckbx" type="checkbox" name="privilegeArray" value="generator"/>generator&nbsp;
        	<input id="checkerckbx" type="checkbox" name="privilegeArray" value="checker"/>checker&nbsp;
        	<input id="senderckbx" type="checkbox" name="privilegeArray" value="sender"/>sender&nbsp;
        	<input id="addpriceckbx" type="checkbox" name="privilegeArray" value="addpricec"/>invoicer&nbsp;
        	</s:if>
        	<s:else>
        	权限：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	  		<input id="adminckbx" type="checkbox" name="privilegeArray" disabled="true" value="admin" onclick="chooseAdmin()" />admin&nbsp;
        	<input id="recorderckbx" type="checkbox" name="privilegeArray" disabled="true" value="recorder"/>recorder&nbsp;
        	<input id="entryPersonckbx" type="checkbox" name="privilegeArray" disabled="true" value="entryPerson"/>importer&nbsp;
        	<input id="generatorckbx" type="checkbox" name="privilegeArray" disabled="true" value="generator"/>generator&nbsp;
        	<input id="checkerckbx" type="checkbox" name="privilegeArray" disabled="true" value="checker"/>checker&nbsp;
        	<input id="senderckbx" type="checkbox" name="privilegeArray" disabled="true" value="sender"/>sender&nbsp;
        	<input id="addpriceckbx" type="checkbox" name="privilegeArray" disabled="true" value="addpricec"/>invoicer&nbsp;
        	</s:else>
  	  	</p>
  	  	<s:if test="isAddUserSucessed==3">
  	  		<p>
  	  			两次输入密码不一致，请重新输入
  	  		</p>
  	  	</s:if>
		<p>
              <input class="button" type="submit" value="编辑用户" />
        </p>
	  </form>
      </div>
      <!-- End .content-box-content -->
    </div>
    <div class="clear"></div>
	<jsp:include page="/public/work_bench_bottom.jsp"></jsp:include>
  </div>
  <!-- End #main-content -->
</div>
</body>
</html>
