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
	<title>新工作单</title>
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
        <h3>新工作单</h3>
      </div>
      <!-- End .content-box-header -->
     <div class="content-box-content"> 
	 <form enctype="multipart/form-data" action="reportAction_addReport.action?currentPage=1&pageSize=100" method="post">
  	  	<p>
  	  		Required W/D: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	  		<input class="text-input small-input" type="text" name="report.requiredWD"/>
  	  	</p>
  	  	<p>
  	  		Date in：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	  		<input class="text-input small-input" type="text" name="report.dateIn"/>
  	  	</p>
  	  	<p>
  	  		BV Due date：
  	  		<input class="text-input small-input" type="text" name="report.bvDueDate"/>
  	  	</p>
  	  	<p>
  	  		Due date：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	  		<input class="text-input small-input" type="text" name="report.dueDate"/>
  	  	</p>
  	  	<p>
  	  		Date out：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	  		<input class="text-input small-input" type="text" name="report.dateOut"/>
  	  	</p>
  	  	<p>
  	  		Client：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	  		<input class="text-input small-input" type="text" name="report.client"/>
  	  	</p>
  	  	<p>
  	  		PO NO：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	  		<input class="text-input small-input" type="text" name="report.poNo"/>
  	  	</p>
  	  	<p>
  	  		Billing to：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	  		<input class="text-input small-input" type="text" name="report.billingTo"/>
  	  	</p>
  	  	<p>
  	  		INVOICE TYPE：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	  		<input class="text-input small-input" type="text" name="report.invoiceType"/>
  	  	</p>
  	  	<p>
  	  		MASTER SAMPLE NO：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	  		<input class="text-input small-input" type="text" name="report.masterSampleNo"/>
  	  	</p>
  	  	<p>
  	  		Report No.：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	  		<input class="text-input small-input" type="text" name="report.reportNo"/>
  	  	</p>
  	  	<p>
  	  		Special Item：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	  		<input class="text-input small-input" type="text" name="report.specialItem"/>
  	  	</p>
  	  	<p>
  	  		Vendor：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	  		<input class="text-input small-input" type="text" name="report.vendor"/>
  	  	</p>
  	  	<p>
  	  		Sample Description：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	  		<input class="text-input small-input" type="text" name="report.sampleDescription"/>
  	  	</p>
  	  	<p>
  	  		PRICE：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	  		<input class="text-input small-input" type="text" name="report.price"/>
  	  	</p>
  	  	<p>
  	  		Group.：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	  		<input class="text-input small-input" type="text" name="report.testGroup"/>
  	  	</p>
  	  	<p>
  	  		Status：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	  		<input class="text-input small-input" type="text" name="report.status"/>
  	  	</p>
  	  	<p>
  	  		Holding Reason：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	  		<input class="text-input small-input" type="text" name="report.holdReason"/>
  	  	</p>
  	  	<p>
  	  		Late Reason：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	  		<input class="text-input small-input" type="text" name="report.lateReason"/>
  	  	</p>
  	  	<p>
  	  		Engineer：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	  		<input class="text-input small-input" type="text" name="report.engineer"/>
  	  	</p>
  	  	<p>
  	  		Report Checker：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	  		<input class="text-input small-input" type="text" name="report.reportChecker"/>
  	  	</p>
  	  	<p>
  	  		Report Sender：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	  		<input class="text-input small-input" type="text" name="report.reportSender"/>
  	  	</p>
  	  	
<!--   	  	<p> -->
<!--                                           权限：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
<!--   	  		<select id="userType" name="user.userType" class="small-input"> -->
<!--                  <option value="1">管理员</option> -->
<!--                  <option value="2">普通用户</option> -->
<!--         	</select> -->
<!--   	  	</p> -->
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
