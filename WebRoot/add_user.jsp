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
	<title>Add User</title>
    <style type="text/css">
        .clear {
            clear: both;
        }
    </style>
    <jsp:include page="/public/work_bench_reference.jsp"></jsp:include>
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
    </script>
</head>
<body onload="">
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
    <div class="content-box" >
      <!-- Start Content Box -->
      <div class="content-box-header">
        <h3>Add User</h3>
      </div>
      <!-- End .content-box-header -->
     <div class="content-box-content"> 
	 <form enctype="multipart/form-data" action="userAction_addUser.action?currentPage=1&pageSize=100" method="post">
  	  	<p>
  	  		Login Name:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	  		<input class="text-input small-input" type="text" name="user.login"/>
  	  	</p>
  	  	<p>
  	  		Password:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	  		<input class="text-input small-input" type="text" name="user.password"/>
  	  	</p>
  	  	<p>
  	  		Re-type Password:
  	  		<input class="text-input small-input" type="text" name="rePassword"/>
  	  	</p>
  	  	<p>
  	  		User Name:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	  		<input class="text-input small-input" type="text" name="user.name"/>
  	  	</p>
  	  	<p>
  	  		E-Mail:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	  		<input class="text-input small-input" type="text" name="user.email"/>
  	  	</p>
  	  	<p>
  	  		Tel:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	  		<input class="text-input small-input" type="text" name="user.tel"/>
  	  	</p>
  	  	<p>
            Roles:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        	<input id="adminckbx" type="checkbox" name="privilegeArray" value="admin" onclick="chooseAdmin()" />admin&nbsp;
        	<input id="recorderckbx" type="checkbox" name="privilegeArray" value="recorder"/>recorder&nbsp;
        	<input id="entryPersonckbx" type="checkbox" name="privilegeArray" value="entryPerson"/>importer&nbsp;
        	<input id="generatorckbx" type="checkbox" name="privilegeArray" value="generator"/>generator&nbsp;
        	<input id="checkerckbx" type="checkbox" name="privilegeArray" value="checker"/>checker&nbsp;
        	<input id="senderckbx" type="checkbox" name="privilegeArray" value="sender"/>sender&nbsp;
        	<input id="addpriceckbx" type="checkbox" name="privilegeArray" value="addpricec"/>invoicer&nbsp;
  	  	</p>
  	  	<s:if test="isAddUserSucessed==2">
  	  		<p>
  	  			Name already exists
  	  		</p>
  	  	</s:if>
  	  	<s:elseif test="isAddUserSucessed==3">
  	  		<p>
  	  			The two password is different, please re-type the password 
  	  		</p>
  	  	</s:elseif>
		<p>
              <input class="button" type="submit" value="Save" />
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
