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
	<title>Edit Report</title>
    <style type="text/css">
        .clear {
            clear: both;
        }
    </style>
    <script type="text/javascript">
    	function setInvoiceType()
    	{
    		var invoiceTypeValue=$('#invoiceTypeValue').val();
    		if(invoiceTypeValue==1)
    		{
    			$('#invoiceType').val(1)
    		}
    		else if(invoiceTypeValue==2)
    		{
    			$('#invoiceType').val(2)
    		}
    		else if(invoiceTypeValue==3)
    		{
    			$('#invoiceType').val(3)
    		}
    	}
    	
    	function setStatus()
    	{
    		var statusValue=$('#statusValue').val();
    		if(statusValue==1)
    		{
    			$('#status').val(1)
    		}
    		else if(statusValue==2)
    		{
    			$('#status').val(2)
    		}
    		else if(statusValue==3)
    		{
    			$('#status').val(3)
    		}
    		else if(statusValue==4)
    		{
    			$('#status').val(4)
    		}
    	}
    	
    	function checkAndSave()
    	{
    	    var dateInError=false;
    	    var bvDueDateError=false;
    	    var dueDateError=false;
    	    var dateIn=$('#dateIn').val();
    	    var bvDueDate=$('#bvDueDate').val();
    	    var dueDate=$('#dueDate').val();
    	    var errorStr='';
    	    var rep = new RegExp("([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8])))");
    	    if(dateIn==''|| dateIn=='--' || !rep.test(dateIn))
    	    {
    	        dateInError=true;
    	        errorStr+="Date in:";
    	    }
    	    if(bvDueDate=='' || bvDueDate=='--' || !rep.test(bvDueDate))
    	    {
    	        bvDueDateError=true;
    	        errorStr+="BV Due date:";
    	    }
    	    if(dueDate==''|| dueDate=='--' || !rep.test(dueDate))
    	    {
    	        dueDateError=true;
    	        errorStr+="Due date:";
    	    }
    	    
    	    if(dateInError||bvDueDateError||dueDateError)
    	    {
    	        errorStr+=" format not correctly. should be yyyy-mm-dd";
    	        alert(errorStr);
    	    }
    	    else
    	    {
    	        $('#saveForm').submit();
    	    }
    	}
    </script>
    <jsp:include page="/public/work_bench_reference.jsp"></jsp:include>
</head>
<body onload="setInvoiceType();setStatus()">
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
        <h3>Edit Report</h3>
      </div>
      <!-- End .content-box-header -->
     <div class="content-box-content"> 
	 <form id="saveForm" enctype="multipart/form-data" action="reportAction_editReport4EntryPerson.action?currentPage=1&pageSize=100" method="post">
	 	<input type="hidden" id="report.puid" name="report.puid" value="<s:property value="report.puid"/>"  />
  	  	<p>
  	  		Required W/D: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	  		<input class="text-input small-input" type="text" name="report.requiredWD" value="<s:property value="report.requiredWD"/>"/>
  	  	</p>
  	  	<p>
  	  		Date in:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	  		<input id="dateIn" class="text-input small-input" type="text" name="report.dateIn" value="<s:property value="report.dateIn.substring(0,4)" />-<s:property value="report.dateIn.substring(4,6)" />-<s:property value="report.dateIn.substring(6,8)" />" />&nbsp;&nbsp;&nbsp;&nbsp;format yyyy-mm-dd
  	  	</p>
  	  	<p>
  	  		BV Due date:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	  		<input id="bvDueDate" class="text-input small-input" type="text" name="report.bvDueDate" value="<s:property value="report.bvDueDate.substring(0,4)" />-<s:property value="report.bvDueDate.substring(4,6)" />-<s:property value="report.bvDueDate.substring(6,8)" />" />&nbsp;&nbsp;&nbsp;&nbsp;format yyyy-mm-dd
  	  	</p>
  	  	<p>
  	  		Due date:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	  		<input id="dueDate" class="text-input small-input" type="text" name="report.dueDate" value="<s:property value="report.dueDate.substring(0,4)" />-<s:property value="report.dueDate.substring(4,6)" />-<s:property value="report.dueDate.substring(6,8)" />"/>&nbsp;&nbsp;&nbsp;&nbsp;format yyyy-mm-dd
  	  	</p>
  	  	<p>
  	  		Client:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	  		<input class="text-input small-input" type="text" name="report.client" value="<s:property value="report.client"/>"/>
  	  	</p>
  	  	<p>
  	  		PO NO:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	  		<input class="text-input small-input" type="text" name="report.poNo" value="<s:property value="report.poNo"/>"/>
  	  	</p>
  	  	<p>
  	  		Billing to:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	  		<input class="text-input small-input" type="text" name="report.billingTo" value="<s:property value="report.billingTo"/>"/>
  	  	</p>
  	  	<p>
  	  		<input type="hidden" id="invoiceTypeValue" name="invoiceTypeValue" value="<s:property value="report.invoiceType"/>"  />
  	  		INVOICE TYPE:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	  		<select id="invoiceType" name="report.invoiceType" class="small-input">
                 <option value="1">daily</option>
                 <option value="2">monthly</option>
                 <option value="3">Other</option>
        	</select>
  	  	</p>
  	  	<p>
  	  		Report No.:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	  		<input class="text-input small-input" type="text" name="report.reportNo" value="<s:property value="report.reportNo"/>"/>
  	  	</p>
  	  	<p>
  	  		Special Item:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	  		<input class="text-input small-input" type="text" name="report.specialItem" value="<s:property value="report.specialItem"/>"/>
  	  	</p>
  	  	<p>
  	  		Vendor:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	  		<input class="text-input small-input" type="text" name="report.vendor" value="<s:property value="report.vendor"/>"/>
  	  	</p>
  	  	<p>
  	  		Sample Description:
  	  		<input class="text-input small-input" type="text" name="report.sampleDescription" value="<s:property value="report.sampleDescription"/>"/>
  	  	</p>
  	  	<p>
  	  		Group.:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	  		<input class="text-input small-input" type="text" name="report.testGroup" value="<s:property value="report.testGroup"/>"/>
  	  	</p>
  	  	<p>
  	  		<input type="hidden" id="statusValue" name="statuseValue" value="<s:property value="report.status"/>"  />
  	  		Status:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<!--   	  		<input class="text-input small-input" type="text" name="report.status" /> -->
			<select id="status" name="report.status" class="small-input">
                 <option value="1">active</option>
                 <option value="2">on-hold</option>
                 <option value="3">cancel</option>
                 <option value="4">other</option>
        	</select>
  	  	</p>
  	  	<p>
  	  		Holding Reason:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	  		<input class="text-input small-input" type="text" name="report.holdingReason" value="<s:property value="report.holdingReason"/>"/>
  	  	</p>
  	  	<p>
  	  		Late Reason:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	  		<input class="text-input small-input" type="text" name="report.lateReason" value="<s:property value="report.lateReason"/>"/>
  	  	</p>
	  </form>
	  <p>
            <input class="button" type="submit" value="Save" onclick="checkAndSave()" />
        </p>
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
