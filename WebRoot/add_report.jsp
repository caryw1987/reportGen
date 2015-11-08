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
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add New Report</title>
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
					<div>Javascript is disabled or is not supported by your
						browser。</div>
				</div>
			</noscript>
			<!-- Page Head -->
			<!-- End .shortcut-buttons-set -->
			<div class="clear"></div>
			<!-- End .clear -->
			<div class="content-box" >
				<!-- Start Content Box -->
				<div class="content-box-header">
					<h3>Add New Report</h3>
				</div>
				<!-- End .content-box-header -->
				<div class="content-box-content">
					<form enctype="multipart/form-data"
						action="reportAction_addReport.action?currentPage=1&pageSize=100"
						method="post">
						<p>
							MASTER SAMPLE NO: <input class="text-input small-input"
								type="text" name="report.masterSampleNo" />
						</p>
						<p>
							Department:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<!-- 							<select id="logStatus" name="report.department" -->
<!-- 								class="small-input"> -->
<!-- 								<option value="1">AN</option> -->
<!-- 								<option value="2">PHY</option> -->
<!-- 								<option value="3">Other</option> -->
<!-- 							</select> -->
							<input id="anckbx" type="checkbox" name="departmentArray" value="AN"/>AN&nbsp;
        					<input id="phyckbx" type="checkbox" name="departmentArray" value="PHY"/>PHY&nbsp;
        					<input id="otherckbx" type="checkbox" name="departmentArray" value="Other"/>Other&nbsp;
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
