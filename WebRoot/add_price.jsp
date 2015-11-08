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
	<title>Add Price</title>
    <style type="text/css">
        .clear {
            clear: both;
        }
    </style>
    <jsp:include page="/public/work_bench_reference.jsp"></jsp:include>
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
    <div class="content-box" style="width:1060px;">
      <!-- Start Content Box -->
      <div class="content-box-header">
        <h3>Add Price</h3>
      </div>
      <!-- End .content-box-header -->
     <div class="content-box-content"> 
	 <form enctype="multipart/form-data" action="reportAction_addPrice.action?currentPage=1&pageSize=100" method="post">
	 		<input type="hidden" name="report.puid" value="<s:property value="report.puid" />" />
	 	<p>
  	  		Price：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	  		<input class="text-input small-input" type="text" name="report.price"/>
  	  	</p>
  	  	<p>
          	  Type：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	  		<select id="logStatus" name="report.moneyType" class="small-input">
                 <option value="1">$</option>
                 <option value="2">￥</option>
                 <option value="3">Other</option>
        	</select>
  	  	</p>
  	  	<p>
  	  		Other：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	  		<input class="text-input small-input" type="text" name="report.otherMoneyType"/>
  	  	</p>
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
