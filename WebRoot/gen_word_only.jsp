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
<script type="text/javascript">
	var fileId = 2;

	function addNew() {

		var count = $("#addCount").val();
		if (count == 0) {
			count = 1;
		}
		for (i = 0; i < count; i++) {
			var removeBtn = document.createElement("input")
			removeBtn.type = "button";
			removeBtn.className = "button"
			removeBtn.value = "remove";
			removeBtn.id = "removeBtn" + fileId;
			var removeBtnId = "removeBtn" + fileId;
			removeBtn.onclick = function() {
				removeP(this);
			};

			var tsTdTd = document.getElementById("tsTd");
			var tsP = document.createElement("p");
			tsP.id = "p" + fileId;
			var fileInput = document.createElement("input");
			fileInput.type = "text";
			fileInput.id = fileId;
			fileInput.className = "text-input small-input";
			fileInput.name = "wordAttrComponent";

			var fileInput1 = document.createElement("input");
			fileInput1.type = "text";
			fileInput1.id = fileId;
			fileInput1.className = "text-input little-input";
			fileInput1.name = "wordAttrLead";

			var fileInput2 = document.createElement("input");
			fileInput2.type = "text";
			fileInput2.id = fileId;
			fileInput2.className = "text-input little-input";
			fileInput2.name = "wordAttrCadmium";

			tsP.innerHTML = "Component:";
			tsP.appendChild(fileInput);
			tsP.appendChild(document.createTextNode(" Lead:"));
			tsP.appendChild(fileInput1);
			tsP.appendChild(document.createTextNode(" Cadmium:"));
			tsP.appendChild(fileInput2);
			tsP.appendChild(removeBtn);
			tsTd.appendChild(tsP);
			fileId = fileId + 1;
		}

	}

	function removeP(removeBtn) {
		var id = removeBtn.id.substr(9);
		$("#p" + id).remove();
	}

	function findImage() {
		var masterSampleNo = $("#masterSampleNo").val();
		var url = 'reportAction_findImage.action';
		var param = {
			wordAttrMasterSampleNo : masterSampleNo
		};
		if ($("#showImage").length > 0) {
			$("#showImage").remove();
		}
		$.post(url, param, callback, "json");
	}

	function callback(data) {
		var imagePath = data.path;
		var text = "<p> <img id='showImage' src='"+ imagePath +"' height='413px' width='536px' /> </p>";
		if (imagePath != "") {
			$("#imageP").after(text);
		}
	}
	
	function editable(select1){
    if(select1.value == ""){
       var newvalue = prompt("请输入","");
    if(newvalue){
       addSelected(select1,newvalue,newvalue);
    }
    }
 }
 
 function addSelected(fld1,value1,text1){
  if (document.all) {
       var Opt = fld1.document.createElement("OPTION");
    Opt.text = text1;
    Opt.value = value1;
    fld1.options.add(Opt);
    Opt.selected = true;
  }else{
       var Opt = new Option(text1,value1,false,false);
    Opt.selected = true;
    fld1.options[fld1.options.length] = Opt;
  }
 }
</script>
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
			<div class="content-box" style="width:1060px;">
				<!-- Start Content Box -->
				<div class="content-box-header">
					<h3>新工作单</h3>
				</div>
				<!-- End .content-box-header -->
				<div class="content-box-content">
					<form enctype="multipart/form-data"
						action="reportAction_generateWordOnly.action"
						method="post">
						<p>
							MASTER SAMPLE No.:
							<input id="masterSampleNo" class="text-input small-input" type="text"
								name="wordAttrMasterSampleNo" />
						</p>
<!-- 						<p> -->
<!-- 							Technical Report: -->
<!-- 							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
<!-- 							<input class="text-input small-input" type="text" -->
<!-- 								name="wordAttrTechnicalReport" /> -->
<!-- 						</p> -->
						<p>
							Date In:
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input class="text-input small-input" type="text"
								name="wordAttrDateIn" /> &nbsp;&nbsp;&nbsp;format yyyy-mm-dd
						</p>
						<p>
							Date Modify:
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input class="text-input small-input" type="text"
								name="wordAttrDateModify" /> &nbsp;&nbsp;&nbsp;format yyyy-mm-dd
						</p>
						<p>
							Date Out:
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input class="text-input small-input" type="text"
								name="wordAttrDateOut" /> &nbsp;&nbsp;&nbsp;format yyyy-mm-dd
						</p>
						<p>
							Style No.:
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input class="text-input small-input" type="text"
								name="wordAttrStyleNo" />
						</p>
						<p>
							PO No.:
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input class="text-input small-input" type="text"
								name="wordAttrPoNo" />
						</p>
						<p>
							Vendor:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input class="text-input small-input" type="text"
								name="wordAttrVendor" />
						</p>
						<p>
							Factory/Manufacturer: 
							<input class="text-input small-input"
								type="text" name="wordAttrFactory" />
						</p>
						<p>
							Sample Description: &nbsp;&nbsp;&nbsp;
							<input class="text-input small-input"
								type="text" name="wordAttrSampleDescription" />
						</p>
						<p>
							Retest:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<select id="wordAttrRetest" name="wordAttrRetest"
								class="small-input">
								<option value="No">No</option>
								<option value="Yes">Yes</option>
							</select>
						</p>
						<p>
							Country of
							Origin:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<select id="wordAttrCountryOfOrigin" name="wordAttrCountryOfOrigin"
								class="small-input" onChange="editable(this);">
								<option value="China">China</option>
								<option value="Vietnam">Vietnam</option>
								<option value="">请输入</option>
							</select>
						</p>
						<p>
							Country of Destination:
							<select id="wordAttrCountryOfDestination" name="wordAttrCountryOfDestination"
								class="small-input" onChange="editable(this);">
								<option value="E.U.&USA">E.U.&USA</option>
								<option value="Germany">Germany</option>
								<option value="USA">USA</option>
								<option value="">请输入</option>
							</select>
						</p>
						<p>
							Color:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input class="text-input small-input" type="text"
								name="wordAttrColor" />
						</p>
						<p>
							Brand
							Name:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input class="text-input small-input" type="text"
								name="wordAttrBrandName" />
						</p>
						<p>
							Actual OO Date:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input class="text-input small-input" type="text"
								name="wordAttrActualOoDate" />
						</p>
						<p>
							Previous Report
							No.:&nbsp;&nbsp;&nbsp;
							<input class="text-input small-input" type="text"
								name="wordAttrPreviousReportNo" />
						</p>
						<p id="imageP">
							Image:
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input class="file-input small-input" type="file" name="image" />
							<input id="findImageId" class="button"
												type="button" onClick="findImage()" value="FindImage" />
						</p>
						<p>
						<table border="0">
							<tr>
								<td>TestResult：</td>
								<td id="tsTd">
									<div>
										<p id="p1">
											Component:<input id="1" class="text-input small-input" name="wordAttrComponent" type="text" />
											Lead:<input id="1" class="text-input little-input" type="text" name="wordAttrLead" />
											Cadmium:<input id="1" class="text-input little-input" type="text" name="wordAttrCadmium" /> 
<!-- 											<input id="removeBtn1" class="button" type="button" onClick="removeP(this)" value="remove" /> -->
											<input id="addBtn" class="button" type="button" onClick="addNew()" value="add" />
											<input id="addCount" class="text-input little-input" type="text" />
										</p>
									</div></td>
							</tr>
						</table>
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
