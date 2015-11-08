<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Price</title>
<jsp:include page="/public/work_bench_reference.jsp"></jsp:include>
<script type="text/javascript">
		function addPrice(rid)
		{
			var url ='reportAction_setPrice.action';
			var prc= $("#prc"+rid).val();
			var param ={reportId:rid,price:prc};			
			$.post(url,param,callback1,"json"); 
		}
		
		function callback1(data)
		{
			var reportId=data.id;
			var price=data.prc;
			$("#prc"+reportId).val(price);
			$("#prc"+reportId).attr("readonly","true");
		}
		
		function setEditable(reportId)
		{
			$("#prc"+reportId).removeAttr("readonly");
		}
	</script>
</head>
<body>
	<jsp:include page="/public/work_bench_top.jsp"></jsp:include>
	<jsp:include page="/public/work_bench_left.jsp"></jsp:include>
	<div id="body-wrapper">
		<!-- Wrapper for the radial gradient background -->
		<!-- End #sidebar -->
		<div id="main-content">
			<!-- Main Content Section with everything -->
			<noscript>
				<!-- Show a notification if the report has disabled javascript -->
				<div class="notification error png_bg">
					<div>Javascript is disabled or is not supported by your
						browser。</div>
				</div>
			</noscript>
			<!-- Page Head -->
			<b> </b>
			<div class="clear"></div>
			<!-- End .clear -->
			<div class="content-box">
				<!-- Start Content Box -->
				<div class="content-box-header">
					<h3>Report List</h3>
					<div class="clear"></div>
				</div>
				<!-- End .content-box-header -->
				<div class="content-box-content">
					<form enctype="multipart/form-data" action="reportAction_searchReport4Price.action?currentPage=1&pageSize=<s:property value="pageBean.pageSize"/>" method="post">										
                						Check date:&nbsp;&nbsp;
                						<s:if test="%{checkDateKeyWords==null}">
                							<input class="text-input small-input" type="text" name="checkDateKeyWords" value="yyyy-mm-dd" /> 
                						</s:if>
                						<s:else>
                							<input class="text-input small-input" type="text" name="checkDateKeyWords" value="<s:property value="checkDateKeyWords"/>" />
                						</s:else>
                						 
                						<input class="button" type="submit" value="Search" onclick="search()"/>             					
					</form>
					<p></p>
					<form enctype="multipart/form-data" action="reportAction_changeMoneyType4SelectedReports.action?currentPage=1&pageSize=<s:property value="pageBean.pageSize"/>" method="post">
										Money Type:
                						<select id="logStatus" name="changedMoneyType" class="smaller-input">
											<option value="1">$</option>
											<option value="2">￥</option>
											<option value="3">Other</option>
										</select> 
										&nbsp;
										other:&nbsp;<input class="text-input little-input" type="text" name="changedOtherMoneyType"/>
                						&nbsp;
                						<input class="button" type="submit" value="Change"/>
					<table>
						<thead>
							<tr>					
								<th><input class="check-all" type="checkbox" /></th>
								<th>Report NO</th>
								<th>Vendor</th>
								<th>Check Date</th>
								<th>Price</th>
								<th>Add Price</th>
								<th>Report download</th>								
							</tr>
						</thead>
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="bulk-actions align-left"></div>
									<div class="pagination">
										<s:if test="%{listType==0}"> 
											<a href="reportAction_listReportsByPage4AddPrice.action?currentPage=1&pageSize=<s:property value="pageBean.pageSize"/>" title="First Page">&laquo; First</a>
                  							<s:if test="%{pageBean.currentPage==1}">
                  								<a href="#" title="Previous Page">&laquo; Pre</a>
                  							</s:if>
                  							<s:else>
                  								<a href="reportAction_listReportsByPage4AddPrice.action?currentPage=<s:property value="%{pageBean.currentPage-1}"/>&pageSize=<s:property value="pageBean.pageSize"/>" title="Previous Page">&laquo; Pre</a>
                  							</s:else>
                  							<s:if test="%{pageBean.totalPage<5}">
                  	                            <s:bean name= "org.apache.struts2.util.Counter"  id= "counter1" >
                  	                                <s:param name="first"  value="1"  />    
                                                    <s:param name="last"  value="pageBean.totalPage"  />
                        	                        <s:iterator>
                        		                        <s:if test="%{pageBean.currentPage==current-1}">
                        		                            <a href="reportAction_listReportsByPage4AddPrice.action?currentPage=<s:property value="current-1"/>&pageSize=<s:property value="pageBean.pageSize"/>" ><font color="red">[<s:property value="current-1"/>]</font></a>
                        		                        </s:if>
                        		                        <s:else>
                        		                             <a href="reportAction_listReportsByPage4AddPrice.action?currentPage=<s:property value="current-1"/>&pageSize=<s:property value="pageBean.pageSize"/>" >[<s:property value="current-1"/>]</a>
                        		                        </s:else>
                                                    </s:iterator>
                                                </s:bean>
                  	                         </s:if>
                  	                         <s:else>
                  	                             <s:if test="%{pageBean.currentPage<=2}">
                  	                                 <s:bean name= "org.apache.struts2.util.Counter"  id= "counter2" >
                  	                                     <s:param name="first"  value="1"  />    
                                                         <s:param name="last"  value="5"  />
                        	                             <s:iterator>
                        	                                 <s:if test="%{pageBean.currentPage==current-1}">
                        		                                 <a href="reportAction_listReportsByPage4AddPrice.action?currentPage=<s:property value="current-1"/>&pageSize=<s:property value="pageBean.pageSize"/>" ><font color="red">[<s:property value="current-1"/>]</font></a>
                        		                             </s:if>
                        		                             <s:else>
                        		                                 <a href="reportAction_listReportsByPage4AddPrice.action?currentPage=<s:property value="current-1"/>&pageSize=<s:property value="pageBean.pageSize"/>" >[<s:property value="current-1"/>]</a>
                        		                             </s:else>
                                                         </s:iterator>
                                                     </s:bean>
                  	                             </s:if>
                  	                             <s:elseif test="%{pageBean.currentPage>2&&pageBean.totalPage-pageBean.currentPage>=2}">
                  	                                 <s:bean name= "org.apache.struts2.util.Counter"  id= "counter3" >
                  	                                     <s:param name="first"  value="pageBean.currentPage-2"  />    
                                                         <s:param name="last"  value="pageBean.currentPage+2"  />
                        	                             <s:iterator>
                        		                             <s:if test="%{pageBean.currentPage==current-1}">
                        		                                 <a href="reportAction_listReportsByPage4AddPrice.action?currentPage=<s:property value="current-1"/>&pageSize=<s:property value="pageBean.pageSize"/>" ><font color="red">[<s:property value="current-1"/>]</font></a>
                        		                             </s:if>
                        		                             <s:else>
                        		                                 <a href="reportAction_listReportsByPage4AddPrice.action?currentPage=<s:property value="current-1"/>&pageSize=<s:property value="pageBean.pageSize"/>" >[<s:property value="current-1"/>]</a>
                        		                             </s:else>
                                                         </s:iterator>
                                                     </s:bean>
                  	                             </s:elseif>
                  	                             <s:else>
                  	                                 <s:bean name= "org.apache.struts2.util.Counter"  id= "counter4" >
                  	                                     <s:param name="first"  value="pageBean.totalPage-4"  />    
                                                         <s:param name="last"  value="pageBean.totalPage"  />
                        	                             <s:iterator>
                        		                             <s:if test="%{pageBean.currentPage==current-1}">
                        		                                 <a href="reportAction_listReportsByPage4AddPrice.action?currentPage=<s:property value="current-1"/>&pageSize=<s:property value="pageBean.pageSize"/>" ><font color="red">[<s:property value="current-1"/>]</font></a>
                        		                             </s:if>
                        		                             <s:else>
                        		                                 <a href="reportAction_listReportsByPage4AddPrice.action?currentPage=<s:property value="current-1"/>&pageSize=<s:property value="pageBean.pageSize"/>" >[<s:property value="current-1"/>]</a>
                        		                             </s:else>
                                                         </s:iterator>
                                                     </s:bean>
                  	                             </s:else>
                  	                        </s:else> 
                  							<s:if test="%{pageBean.currentPage==pageBean.totalPage}">
           	    								<a href="#" title="Next Page">Next</a>
            	  							</s:if>
            	  							<s:else>
            	  								<a href="reportAction_listReportsByPage4AddPrice.action?currentPage=<s:property value="%{pageBean.currentPage+1}"/>&pageSize=<s:property value="pageBean.pageSize"/>" title="Next Page">Next&raquo;</a>
            	  							</s:else>
                  							<a href="reportAction_listReportsByPage4AddPrice.action?currentPage=<s:property value="pageBean.totalPage"/>&pageSize=<s:property value="pageBean.pageSize"/>" title="Last Page">Last&raquo;</a> 
                  						</s:if>
                  						<s:if test="%{listType==1}"> 
											<a href="reportAction_searchReport4Price.action?currentPage=1&pageSize=<s:property value="pageBean.pageSize"/>&checkDateKeyWords=<s:property value="checkDateKeyWords"/>" title="First Page">&laquo; First</a>
                  							<s:if test="%{pageBean.currentPage==1}">
                  								<a href="#" title="Previous Page">&laquo; Pre</a>
                  							</s:if>
                  							<s:else>
                  								<a href="reportAction_searchReport4Price.action?currentPage=<s:property value="%{pageBean.currentPage-1}"/>&pageSize=<s:property value="pageBean.pageSize"/>&checkDateKeyWords=<s:property value="checkDateKeyWords"/>" title="Previous Page">&laquo; Pre</a>
                  							</s:else>
                  							<s:if test="%{pageBean.totalPage<5}">
                  	                            <s:bean name= "org.apache.struts2.util.Counter"  id= "counter1" >
                  	                                <s:param name="first"  value="1"  />    
                                                    <s:param name="last"  value="pageBean.totalPage"  />
                        	                        <s:iterator>
                        		                        <s:if test="%{pageBean.currentPage==current-1}">
                        		                            <a href="reportAction_searchReport4Price.action?currentPage=<s:property value="current-1"/>&pageSize=<s:property value="pageBean.pageSize"/>" ><font color="red">[<s:property value="current-1"/>]</font></a>
                        		                        </s:if>
                        		                        <s:else>
                        		                             <a href="reportAction_searchReport4Price.action?currentPage=<s:property value="current-1"/>&pageSize=<s:property value="pageBean.pageSize"/>" >[<s:property value="current-1"/>]</a>
                        		                        </s:else>
                                                    </s:iterator>
                                                </s:bean>
                  	                         </s:if>
                  	                         <s:else>
                  	                             <s:if test="%{pageBean.currentPage<=2}">
                  	                                 <s:bean name= "org.apache.struts2.util.Counter"  id= "counter2" >
                  	                                     <s:param name="first"  value="1"  />    
                                                         <s:param name="last"  value="5"  />
                        	                             <s:iterator>
                        	                                 <s:if test="%{pageBean.currentPage==current-1}">
                        		                                 <a href="reportAction_searchReport4Price.action?currentPage=<s:property value="current-1"/>&pageSize=<s:property value="pageBean.pageSize"/>" ><font color="red">[<s:property value="current-1"/>]</font></a>
                        		                             </s:if>
                        		                             <s:else>
                        		                                 <a href="reportAction_searchReport4Price.action?currentPage=<s:property value="current-1"/>&pageSize=<s:property value="pageBean.pageSize"/>" >[<s:property value="current-1"/>]</a>
                        		                             </s:else>
                                                         </s:iterator>
                                                     </s:bean>
                  	                             </s:if>
                  	                             <s:elseif test="%{pageBean.currentPage>2&&pageBean.totalPage-pageBean.currentPage>=2}">
                  	                                 <s:bean name= "org.apache.struts2.util.Counter"  id= "counter3" >
                  	                                     <s:param name="first"  value="pageBean.currentPage-2"  />    
                                                         <s:param name="last"  value="pageBean.currentPage+2"  />
                        	                             <s:iterator>
                        		                             <s:if test="%{pageBean.currentPage==current-1}">
                        		                                 <a href="reportAction_searchReport4Price.action?currentPage=<s:property value="current-1"/>&pageSize=<s:property value="pageBean.pageSize"/>" ><font color="red">[<s:property value="current-1"/>]</font></a>
                        		                             </s:if>
                        		                             <s:else>
                        		                                 <a href="reportAction_searchReport4Price.action?currentPage=<s:property value="current-1"/>&pageSize=<s:property value="pageBean.pageSize"/>" >[<s:property value="current-1"/>]</a>
                        		                             </s:else>
                                                         </s:iterator>
                                                     </s:bean>
                  	                             </s:elseif>
                  	                             <s:else>
                  	                                 <s:bean name= "org.apache.struts2.util.Counter"  id= "counter4" >
                  	                                     <s:param name="first"  value="pageBean.totalPage-4"  />    
                                                         <s:param name="last"  value="pageBean.totalPage"  />
                        	                             <s:iterator>
                        		                             <s:if test="%{pageBean.currentPage==current-1}">
                        		                                 <a href="reportAction_searchReport4Price.action?currentPage=<s:property value="current-1"/>&pageSize=<s:property value="pageBean.pageSize"/>" ><font color="red">[<s:property value="current-1"/>]</font></a>
                        		                             </s:if>
                        		                             <s:else>
                        		                                 <a href="reportAction_searchReport4Price.action?currentPage=<s:property value="current-1"/>&pageSize=<s:property value="pageBean.pageSize"/>" >[<s:property value="current-1"/>]</a>
                        		                             </s:else>
                                                         </s:iterator>
                                                     </s:bean>
                  	                             </s:else>
                  	                        </s:else> 
                  							<s:if test="%{pageBean.currentPage==pageBean.totalPage}">
           	    								<a href="#" title="Next Page">Next</a>
            	  							</s:if>
            	  							<s:else>
            	  								<a href="reportAction_searchReport4Price.action?currentPage=<s:property value="%{pageBean.currentPage+1}"/>&pageSize=<s:property value="pageBean.pageSize"/>&checkDateKeyWords=<s:property value="checkDateKeyWords"/>" title="Next Page">Next&raquo;</a>
            	  							</s:else>
                  							<a href="reportAction_searchReport4Price.action?currentPage=<s:property value="pageBean.totalPage"/>&pageSize=<s:property value="pageBean.pageSize"/>&checkDateKeyWords=<s:property value="checkDateKeyWords"/>" title="Last Page">Last&raquo;</a> 
                  						</s:if>
                  					</div> 
                  					<!-- End .pagination -->
									<div class="clear"></div></td>
							</tr>
						</tfoot>
						<tbody>
							<s:iterator value="pageBean.list" id="report">
								<tr>
									<td><input type="checkbox" name="sendReportArray" value="<s:property value="#report.puid" />"/></td>
									<td><s:property value="#report.masterSampleNo" />
									</td>
									<td><s:property value="#report.vendor" />
									</td>
									<td><s:property value="#report.dateOut.substring(0,4)" />-<s:property
											value="#report.dateOut.substring(4,6)" />-<s:property
											value="#report.dateOut.substring(6,8)" />
									</td>
									<td>
										<input id="prc<s:property value="#report.puid" />" class="text-input small-input" type="text" readonly="true" name="priceArray" 
										ondblclick="setEditable('${report.puid}')" value="<s:property value="#report.price" />" />
										<s:if test="%{#report.moneyType==1}">
                							$
                						</s:if> 
                						<s:elseif test="%{#report.moneyType==2}">
                							￥
                						</s:elseif>
                						<s:elseif test="%{#report.moneyType==3}">
                							<s:property value="#report.otherMoneyType" />
                						</s:elseif>
                					</td>
                					<td><a class="button" href="javascript:addPrice('${report.puid}')">Add Price</a></td>
                					<td> <a class="button" href="<s:property value="#report.reportRelativePath" />" title="Edit">Download</a></td>	
								</tr>
							</s:iterator>
						</tbody>
					</table>
					</form>
					<!-- End #tab1 -->
				</div>
				<!-- End .content-box-content -->
			</div>
			<!-- End .content-box -->
			<div class="clear"></div>
			<jsp:include page="/public/work_bench_bottom.jsp"></jsp:include>
		</div>
		<!-- End #main-content -->
	</div>
</body>
</html>