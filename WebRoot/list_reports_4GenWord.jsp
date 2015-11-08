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
<title>Generate Report</title>
<jsp:include page="/public/work_bench_reference.jsp"></jsp:include>
	<script type="text/javascript">
		function resultLogin(rid)
		{
			var url ='reportAction_resultLogin.action';
			var param ={reportId:rid};
			$.post(url,param,callback1,"json"); 
		}
		
		function callback1(data)
		{
			var reportId=data.id;
			$("#tdid"+reportId).html("Result login");
		}
		
		function resultLoginPending(rid)
		{
			var url ='reportAction_resultLoginPending.action';
			var param ={reportId:rid};
			$.post(url,param,callback2,"json"); 
		}
		
		function callback2(data)
		{
			var reportId=data.id;
			var time=data.time;
			$("#tdid"+reportId).html("Pending");
		}
		
		function showPendingTime(rid)
		{
			var timeStr=$("#ptm"+rid).val();
			var array=timeStr.split(":");
			var display="Historical pending time: \n"+array[0].substr(0,4)+"-"+array[0].substr(4,2)+"-"+array[0].substr(6,2)+"\n";
			for(var i=1; i<array.length; i++)
			{
				display=display+array[i].substr(0,4)+"-"+array[i].substr(4,2)+"-"+array[i].substr(6,2)+"\n";
			}
			alert(display);
		}
		
		function setDone(rid)
		{
			var url ='reportAction_setDone.action';
			var param ={reportId:rid};
			$.post(url,param,callback1,"json"); 
		}
		
		function callback1(data)
		{
			var reportId=data.id;
			$("#tdid"+reportId).html("Done");
		}
		
		function setUnDone(rid)
		{
			var url ='reportAction_setUnDone.action';
			var param ={reportId:rid};
			$.post(url,param,callback2,"json"); 
		}
		
		function callback2(data)
		{
			var reportId=data.id;
			var time=data.time;
			$("#tdid"+reportId).html("Not Done");
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
    <s:else>
    	<a class="button" href="reportAction_redirect2AddReportJSP.action">添加用户</a>
    </s:else>
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
          <table>
            <thead>
              <tr>
                <th>
                  <input class="check-all" type="checkbox" />
                </th>
                <th>MASTER SAMPLE NO</th>
                <th>Status</th>
                <th>Start Time</th>
                <th>End Time</th>
                <th>Operator</th>
                <th>Operation</th>
                <th>Change status</th>
              </tr>
            </thead>
            <tfoot>
              <tr>
                <td colspan="6">
                  <div class="bulk-actions align-left">
				  </div> 
                  <div class="pagination"> 
                  	<a href="reportAction_listReportsByPage4GenWord.action?currentPage=1&pageSize=<s:property value="pageBean.pageSize"/>" title="First Page">&laquo; First</a>
                  	<s:if test="%{pageBean.currentPage==1}">
                  		<a href="#" title="Previous Page">&laquo; Pre</a>
                  	</s:if>
                  	<s:else>
                  		<a href="reportAction_listReportsByPage4GenWord.action?currentPage=<s:property value="%{pageBean.currentPage-1}"/>&pageSize=<s:property value="pageBean.pageSize"/>" title="Previous Page">&laquo; Pre</a>
                  	</s:else>
                  	<s:if test="%{pageBean.totalPage<5}">
                  	    <s:bean name= "org.apache.struts2.util.Counter"  id= "counter1" >
                  	        <s:param name="first"  value="1"  />    
                            <s:param name="last"  value="pageBean.totalPage"  />
                        	<s:iterator>
                        		<s:if test="%{pageBean.currentPage==current-1}">
                        		    <a href="reportAction_listReportsByPage4GenWord.action?currentPage=<s:property value="current-1"/>&pageSize=<s:property value="pageBean.pageSize"/>" ><font color="red">[<s:property value="current-1"/>]</font></a>
                        		</s:if>
                        		<s:else>
                        		    <a href="reportAction_listReportsByPage4GenWord.action?currentPage=<s:property value="current-1"/>&pageSize=<s:property value="pageBean.pageSize"/>" >[<s:property value="current-1"/>]</a>
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
                        		        <a href="reportAction_listReportsByPage4GenWord.action?currentPage=<s:property value="current-1"/>&pageSize=<s:property value="pageBean.pageSize"/>" ><font color="red">[<s:property value="current-1"/>]</font></a>
                        		    </s:if>
                        		    <s:else>
                        		        <a href="reportAction_listReportsByPage4GenWord.action?currentPage=<s:property value="current-1"/>&pageSize=<s:property value="pageBean.pageSize"/>" >[<s:property value="current-1"/>]</a>
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
                        		        <a href="reportAction_listReportsByPage4GenWord.action?currentPage=<s:property value="current-1"/>&pageSize=<s:property value="pageBean.pageSize"/>" ><font color="red">[<s:property value="current-1"/>]</font></a>
                        		    </s:if>
                        		    <s:else>
                        		        <a href="reportAction_listReportsByPage4GenWord.action?currentPage=<s:property value="current-1"/>&pageSize=<s:property value="pageBean.pageSize"/>" >[<s:property value="current-1"/>]</a>
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
                        		        <a href="reportAction_listReportsByPage4GenWord.action?currentPage=<s:property value="current-1"/>&pageSize=<s:property value="pageBean.pageSize"/>" ><font color="red">[<s:property value="current-1"/>]</font></a>
                        		    </s:if>
                        		    <s:else>
                        		        <a href="reportAction_listReportsByPage4GenWord.action?currentPage=<s:property value="current-1"/>&pageSize=<s:property value="pageBean.pageSize"/>" >[<s:property value="current-1"/>]</a>
                        		    </s:else>
                                </s:iterator>
                            </s:bean>
                  	    </s:else>
                  	</s:else> 
                  	<s:if test="%{pageBean.currentPage==pageBean.totalPage}">
           	    			<a href="#" title="Next Page">Next</a>
            	  	</s:if>
            	  	<s:else>
            	  		<a href="reportAction_listReportsByPage4GenWord.action?currentPage=<s:property value="%{pageBean.currentPage+1}"/>&pageSize=<s:property value="pageBean.pageSize"/>" title="Next Page">Next&raquo;</a>
            	  	</s:else>
                  	<a href="reportAction_listReportsByPage4GenWord.action?currentPage=<s:property value="pageBean.totalPage"/>&pageSize=<s:property value="pageBean.pageSize"/>" title="Last Page">Last&raquo;</a> </div>
                  <!-- End .pagination -->
                  <div class="clear"></div>
                </td>
              </tr>
            </tfoot>
            <tbody>
              <s:iterator value="pageBean.list" id="report">
              <tr>
                <td>
                  <input type="checkbox" />
                </td>
                <td><s:property value="#report.masterSampleNo" /></td>
                <td id="tdid<s:property value="#report.puid"/>">
                	<s:if test="%{#report.generateStatus==1}">
                		Done
                	</s:if>
                	<s:elseif test="%{#report.generateStatus==2}">
                		Not Done
                	</s:elseif>
                </td>
                <td>
                	<s:property value="#report.generateStartTime.substring(0,4)" />-<s:property value="#report.generateStartTime.substring(4,6)" />-<s:property value="#report.generateStartTime.substring(6,8)" />-<s:property value="#report.generateStartTime.substring(8,10)" />:<s:property value="#report.generateStartTime.substring(10,12)" />
                </td>
                <td>
                	<s:property value="#report.generateEndTime.substring(0,4)" />-<s:property value="#report.generateEndTime.substring(4,6)" />-<s:property value="#report.generateEndTime.substring(6,8)" />-<s:property value="#report.generateEndTime.substring(8,10)" />:<s:property value="#report.generateEndTime.substring(10,12)" />
                <td>
                	<s:property value="#report.engineer.name" />
                </td> 
                <td>
                	<a class="button" href="reportAction_redirect2GenReportJSP.action?report.puid=<s:property value="#report.puid" />">Fill data</a>
                </td> 
                <td>
                	<a class="button" href="javascript:setDone('${report.puid}')" >Done</a>&nbsp;&nbsp;&nbsp;<a class="button" href="javascript:setUnDone('${report.puid}')" >UnDone</a>
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