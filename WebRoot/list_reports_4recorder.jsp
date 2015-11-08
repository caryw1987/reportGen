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
    <script type="text/javascript">
        function deleteSelected(pageSize)
        {
            $("#recordFrom").attr("action", "reportAction_deleteSelectedReports.action?currentPage=1&pageSize="+pageSize);
            $("#recordFrom").submit();
        }
        
        function changeDeparment4SelectedReports(pageSize)
        {
            $("#recordFrom").attr("action", "reportAction_changeDeparment4SelectedReports.action?currentPage=1&pageSize="+pageSize);
            $("#recordFrom").submit();
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
      <div> Javascript is disabled or is not supported by your browser。
        </div>
    </div>
    </noscript>
    <!-- Page Head -->
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
            <font color="red">has been already existed in system.</font>
        </s:if>
      	<form id="recordFrom" enctype="multipart/form-data" method="post">										
                						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                						Department:
                						<input id="anckbx" type="checkbox" name="departmentArray" value="AN"/>AN&nbsp;
        								<input id="phyckbx" type="checkbox" name="departmentArray" value="PHY"/>PHY&nbsp;
        								<input id="otherckbx" type="checkbox" name="departmentArray" value="Other"/>Other&nbsp;
        								<a class="button" href="javascript:changeDeparment4SelectedReports('${pageBean.pageSize}')" title="Change Department">Change Department</a> 
<!--                 						<input class="button" type="submit" value="Change Department"/>           							   -->
          <table>
            <thead>
              <tr>
                <th>
                  <input class="check-all" type="checkbox" />
                </th>
                <th>MASTER SAMPLE NO</th>
                <th>Log Out Time</th>
                <th>Recorder</th>
                <th>Department</th>
                <th>Operation: <a class="button" href="javascript:deleteSelected('${pageBean.pageSize}')" title="Edit">DEL Selected</a></th>
              </tr>
            </thead>
            <tfoot>
              <tr>
                <td colspan="6">
                  <div class="bulk-actions align-left">
				  </div> 
                  <div class="pagination"> 
                  	<a href="reportAction_listReportsByPage4Recorder.action?currentPage=1&pageSize=<s:property value="pageBean.pageSize"/>" title="First Page">&laquo; First</a>
                  	<s:if test="%{pageBean.currentPage==1}">
                  		<a href="#" title="Previous Page">&laquo; Pre</a>
                  	</s:if>
                  	<s:else>
                  		<a href="reportAction_listReportsByPage4Recorder.action?currentPage=<s:property value="%{pageBean.currentPage-1}"/>&pageSize=<s:property value="pageBean.pageSize"/>" title="Previous Page">&laquo; Pre</a>
                  	</s:else>
                  	<s:if test="%{pageBean.totalPage<5}">
                  	    <s:bean name= "org.apache.struts2.util.Counter"  id= "counter1" >
                  	        <s:param name="first"  value="1"  />    
                            <s:param name="last"  value="pageBean.totalPage"  />
                        	<s:iterator>
                        		<s:if test="%{pageBean.currentPage==current-1}">
                        		        <a href="reportAction_listReportsByPage4Recorder.action?currentPage=<s:property value="current-1"/>&pageSize=<s:property value="pageBean.pageSize"/>" ><font color="red">[<s:property value="current-1"/>]</font></a>
                        		</s:if>
                        		<s:else>
                        		    <a href="reportAction_listReportsByPage4Recorder.action?currentPage=<s:property value="current-1"/>&pageSize=<s:property value="pageBean.pageSize"/>" >[<s:property value="current-1"/>]</a>
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
                        		        <a href="reportAction_listReportsByPage4Recorder.action?currentPage=<s:property value="current-1"/>&pageSize=<s:property value="pageBean.pageSize"/>" ><font color="red">[<s:property value="current-1"/>]</font></a>
                        		    </s:if>
                        		    <s:else>
                        		        <a href="reportAction_listReportsByPage4Recorder.action?currentPage=<s:property value="current-1"/>&pageSize=<s:property value="pageBean.pageSize"/>" >[<s:property value="current-1"/>]</a>
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
                        		        <a href="reportAction_listReportsByPage4Recorder.action?currentPage=<s:property value="current-1"/>&pageSize=<s:property value="pageBean.pageSize"/>" ><font color="red">[<s:property value="current-1"/>]</font></a>
                        		    </s:if>
                        		    <s:else>
                        		        <a href="reportAction_listReportsByPage4Recorder.action?currentPage=<s:property value="current-1"/>&pageSize=<s:property value="pageBean.pageSize"/>" >[<s:property value="current-1"/>]</a>
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
                        		        <a href="reportAction_listReportsByPage4Recorder.action?currentPage=<s:property value="current-1"/>&pageSize=<s:property value="pageBean.pageSize"/>" ><font color="red">[<s:property value="current-1"/>]</font></a>
                        		    </s:if>
                        		    <s:else>
                        		        <a href="reportAction_listReportsByPage4Recorder.action?currentPage=<s:property value="current-1"/>&pageSize=<s:property value="pageBean.pageSize"/>" >[<s:property value="current-1"/>]</a>
                        		    </s:else>
                                </s:iterator>
                            </s:bean>
                  	    </s:else>
                  	</s:else>
                  	<s:if test="%{pageBean.currentPage==pageBean.totalPage}">
           	    			<a href="#" title="Next Page">Next</a>
            	  	</s:if>
            	  	<s:else>
            	  		<a href="reportAction_listReportsByPage4Recorder.action?currentPage=<s:property value="%{pageBean.currentPage+1}"/>&pageSize=<s:property value="pageBean.pageSize"/>" title="Next Page">Next&raquo;</a>
            	  	</s:else>
                  	<a href="reportAction_listReportsByPage4Recorder.action?currentPage=<s:property value="pageBean.totalPage"/>&pageSize=<s:property value="pageBean.pageSize"/>" title="Last Page">Last&raquo;</a> </div>
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
                  <input type="checkbox" name="sendReportArray" value="<s:property value="#report.puid" />"/>
                </td>
                <td><s:property value="#report.masterSampleNo" /></td>
                <td><s:property value="#report.opLogoutTime.substring(0,4)" />-<s:property value="#report.opLogoutTime.substring(4,6)" />-<s:property value="#report.opLogoutTime.substring(6,8)" /></td>
                <td><s:property value="#report.recorder.name" /></td>
                <td>
                	<s:property value="#report.department" />
<!--                 	<s:if test="%{#report.department==1}"> -->
<!--                 		AN -->
<!--                 	</s:if> -->
<!--                 	<s:elseif test="%{#report.department==2}"> -->
<!--                 		PHY -->
<!--                 	</s:elseif> -->
<!--                 	<s:elseif test="%{#report.department==3}"> -->
<!--                 		Other -->
<!--                 	</s:elseif> -->
                </td>
                <td>
                    <a href="reportAction_redirect2EditReportJSP4Recorder.action?report.puid=<s:property value="#report.puid" />" title="Edit">Edit</a>
                    &nbsp;&nbsp;&nbsp;
                    <a href="reportAction_deleteReport4Recorder.action?report.puid=<s:property value="#report.puid" />&currentPage=<s:property value="%{pageBean.currentPage}" />" title="Delete">Delete</a>
                </td> 
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