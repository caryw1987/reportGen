<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
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
<jsp:include page="/public/work_bench_reference.jsp"></jsp:include>
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
      <div> Javascript is disabled or is not supported by your browser。
        </div>
    </div>
    </noscript>
    <b>
    </b>
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
          <s:if test="%{repeatedList.size() != 0}">
            <s:iterator value="repeatedList" id="reportId">
                <s:property value="#reportId" />: 
            </s:iterator>
            <font color="red">has been already imported before.</font>
          </s:if>
      	  <form enctype="multipart/form-data"
						action="reportAction_importReports.action?currentPage=1&pageSize=100"
						method="post">
						<p>
							&nbsp;&nbsp;&nbsp;(Import report data from excel)
						</p>
						<p>
							&nbsp;&nbsp;&nbsp;Excel file: &nbsp;&nbsp;<input class="text-input small-input"
								type="file" name="excel" />
								<input class="button" type="submit" value="Satrt Import" />
						</p>
		  </form>
          <table>
            <thead>
              <tr>
                <th>
                  <input class="check-all" type="checkbox" />
                </th>
                <th>MASTER SAMPLE NO</th>
                <th>Department</th>
                <th>Status</th>
                <th>Invoice</th>
                <th>Operation</th>
              </tr>
            </thead>
            <tfoot>
              <tr>
                <td colspan="6">
                  <div class="bulk-actions align-left">
				  </div> 
                  <div class="pagination"> 
                  	<a href="reportAction_listReportsByPage4EntryPerson.action?currentPage=1&pageSize=<s:property value="pageBean.pageSize"/>" title="First Page">&laquo; First</a>
                  	<s:if test="%{pageBean.currentPage==1}">
                  		<a href="#" title="Previous Page">&laquo; Pre</a>
                  	</s:if>
                  	<s:else>
                  		<a href="reportAction_listReportsByPage4EntryPerson.action?currentPage=<s:property value="%{pageBean.currentPage-1}"/>&pageSize=<s:property value="pageBean.pageSize"/>" title="Previous Page">&laquo; Pre</a>
                  	</s:else>
                  	<s:if test="%{pageBean.totalPage<5}">
                  	    <s:bean name= "org.apache.struts2.util.Counter"  id= "counter1" >
                  	        <s:param name="first"  value="1"  />    
                            <s:param name="last"  value="pageBean.totalPage"  />
                        	<s:iterator>
                        		<s:if test="%{pageBean.currentPage==current-1}">
                        		    <a href="reportAction_listReportsByPage4EntryPerson.action?currentPage=<s:property value="current-1"/>&pageSize=<s:property value="pageBean.pageSize"/>" ><font color="red">[<s:property value="current-1"/>]</font></a>
                        		</s:if>
                        		<s:else>
                        		    <a href="reportAction_listReportsByPage4EntryPerson.action?currentPage=<s:property value="current-1"/>&pageSize=<s:property value="pageBean.pageSize"/>" >[<s:property value="current-1"/>]</a>
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
                        		        <a href="reportAction_listReportsByPage4EntryPerson.action?currentPage=<s:property value="current-1"/>&pageSize=<s:property value="pageBean.pageSize"/>" ><font color="red">[<s:property value="current-1"/>]</font></a>
                        		    </s:if>
                        		    <s:else>
                        		        <a href="reportAction_listReportsByPage4EntryPerson.action?currentPage=<s:property value="current-1"/>&pageSize=<s:property value="pageBean.pageSize"/>" >[<s:property value="current-1"/>]</a>
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
                        		        <a href="reportAction_listReportsByPage4EntryPerson.action?currentPage=<s:property value="current-1"/>&pageSize=<s:property value="pageBean.pageSize"/>" ><font color="red">[<s:property value="current-1"/>]</font></a>
                        		    </s:if>
                        		    <s:else>
                        		        <a href="reportAction_listReportsByPage4EntryPerson.action?currentPage=<s:property value="current-1"/>&pageSize=<s:property value="pageBean.pageSize"/>" >[<s:property value="current-1"/>]</a>
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
                        		        <a href="reportAction_listReportsByPage4EntryPerson.action?currentPage=<s:property value="current-1"/>&pageSize=<s:property value="pageBean.pageSize"/>" ><font color="red">[<s:property value="current-1"/>]</font></a>
                        		    </s:if>
                        		    <s:else>
                        		        <a href="reportAction_listReportsByPage4EntryPerson.action?currentPage=<s:property value="current-1"/>&pageSize=<s:property value="pageBean.pageSize"/>" >[<s:property value="current-1"/>]</a>
                        		    </s:else>
                                </s:iterator>
                            </s:bean>
                  	    </s:else>
                  	</s:else> 
                  	<s:if test="%{pageBean.currentPage==pageBean.totalPage}">
           	    			<a href="#" title="Next Page">Next</a>
            	  	</s:if>
            	  	<s:else>
            	  		<a href="reportAction_listReportsByPage4EntryPerson.action?currentPage=<s:property value="%{pageBean.currentPage+1}"/>&pageSize=<s:property value="pageBean.pageSize"/>" title="Next Page">Next&raquo;</a>
            	  	</s:else>
                  	<a href="reportAction_listReportsByPage4EntryPerson.action?currentPage=<s:property value="pageBean.totalPage"/>&pageSize=<s:property value="pageBean.pageSize"/>" title="Last Page">Last&raquo;</a> </div>
                  <!-- End .pagination -->
                  <div class="clear"></div>
                </td>
              </tr>
            </tfoot>
            <tbody>
<%--               <s:iterator value="articleInfoList" id="articleInfo"> --%>
              <s:iterator value="pageBean.list" id="report">
              <tr>
                <td>
                  <input type="checkbox" />
                </td>
                <td><s:property value="#report.masterSampleNo" /></td>
                <td>
                	<s:property value="#report.department" />
                </td>
                <td>
                	<s:if test="%{#report.status==1}">
                		active
                	</s:if>
                	<s:elseif test="%{#report.status==2}">
                		on-hold
                	</s:elseif>
                	<s:elseif test="%{#report.status==3}">
                		cancel
                	</s:elseif>
                	<s:elseif test="%{#report.status==4}">
                		other
                	</s:elseif>
                </td>
                <td>
                	<s:if test="%{#report.invoiceType==1}">
                		daily
                	</s:if>
                	<s:elseif test="%{#report.invoiceType==2}">
                		monthly
                	</s:elseif>
                	<s:elseif test="%{#report.invoiceType==3}">
                		Other
                	</s:elseif>
                </td>
                <td>
                    <a href="reportAction_redirect2EditReportJSP4EntryPerson.action?report.puid=<s:property value="#report.puid" />" title="Edit">Edit</a>
<!--                     <a href="reportAction_deleteReport4Recorder.action?report.puid=<s:property value="#report.puid" />" title="Delete">删除</a> -->
                </td> 
              </tr>
              </s:iterator>
            </tbody>
          </table>
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