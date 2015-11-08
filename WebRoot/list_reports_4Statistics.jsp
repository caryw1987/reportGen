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
<title>Report Statistics</title>
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
      	  <form enctype="multipart/form-data" action="reportAction_searchReport4Statistics.action?currentPage=1&pageSize=<s:property value="pageBean.pageSize"/>" method="post">
                <p>	
                						Generator:
                						<input class="text-input smaller-input" type="text" name="loginKeyWords" value="<s:property value="loginKeyWords"/>" />
                						&nbsp;&nbsp;&nbsp;
                						From Date:
                						<input class="text-input smaller-input" type="text" name="fromCheckDateKeyWords" value="<s:property value="fromCheckDateKeyWords"/>" />
                						&nbsp;&nbsp;&nbsp;
                						End Date:
                						<input class="text-input smaller-input" type="text" name="endCheckDateKeyWords" value="<s:property value="endCheckDateKeyWords"/>" />
                						&nbsp;&nbsp;&nbsp;
                						<input class="button" type="submit" value="Search" onclick="search()"/> 
                </p>
                <p>
                		<span style="color:red"> Find <s:property value="pageBean.allRow"/> result </span>
                </p>	         					
		  </form>
          <table>
            <thead>
              <tr>
                <th>
                  <input class="check-all" type="checkbox" />
                </th>
				<th>Report NO</th>
				<th>Vendor</th>
				<th>Generator</th>
				<th>Check Date</th>
				<th>Price</th>
				<th>Operation</th>
              </tr>
            </thead>
            <tfoot>
              <tr>
                <td colspan="6">
                  <div class="bulk-actions align-left">
				  </div> 
                  <div class="pagination">
                  	<s:if test="%{listType==0}"> 
                  		<a href="reportAction_listReportsByPage4Statistics.action?currentPage=1&pageSize=<s:property value="pageBean.pageSize"/>" title="First Page">&laquo; First</a>
                  		<s:if test="%{pageBean.currentPage==1}">
                  			<a href="#" title="Previous Page">&laquo; Pre</a>
                  		</s:if>
                  		<s:else>
                  			<a href="reportAction_listReportsByPage4Statistics.action?currentPage=<s:property value="%{pageBean.currentPage-1}"/>&pageSize=<s:property value="pageBean.pageSize"/>" title="Previous Page">&laquo; Pre</a>
                  		</s:else>
                  		<s:if test="%{pageBean.totalPage<5}">
                  	    <s:bean name= "org.apache.struts2.util.Counter"  id= "counter1" >
                  	        <s:param name="first"  value="1"  />    
                            <s:param name="last"  value="pageBean.totalPage"  />
                        	<s:iterator>
                        		<s:if test="%{pageBean.currentPage==current-1}">
                        		    <a href="reportAction_listReportsByPage4Statistics.action?currentPage=<s:property value="current-1"/>&pageSize=<s:property value="pageBean.pageSize"/>" ><font color="red">[<s:property value="current-1"/>]</font></a>
                        		</s:if>
                        		<s:else>
                        		    <a href="reportAction_listReportsByPage4Statistics.action?currentPage=<s:property value="current-1"/>&pageSize=<s:property value="pageBean.pageSize"/>" >[<s:property value="current-1"/>]</a>
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
                        		            <a href="reportAction_listReportsByPage4Statistics.action?currentPage=<s:property value="current-1"/>&pageSize=<s:property value="pageBean.pageSize"/>" ><font color="red">[<s:property value="current-1"/>]</font></a>
                        		        </s:if>
                        		        <s:else>
                        		            <a href="reportAction_listReportsByPage4Statistics.action?currentPage=<s:property value="current-1"/>&pageSize=<s:property value="pageBean.pageSize"/>" >[<s:property value="current-1"/>]</a>
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
                        		            <a href="reportAction_listReportsByPage4Statistics.action?currentPage=<s:property value="current-1"/>&pageSize=<s:property value="pageBean.pageSize"/>" ><font color="red">[<s:property value="current-1"/>]</font></a>
                        		        </s:if>
                        		        <s:else>
                        		        <a href="reportAction_listReportsByPage4Statistics.action?currentPage=<s:property value="current-1"/>&pageSize=<s:property value="pageBean.pageSize"/>" >[<s:property value="current-1"/>]</a>
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
                        		            <a href="reportAction_listReportsByPage4Statistics.action?currentPage=<s:property value="current-1"/>&pageSize=<s:property value="pageBean.pageSize"/>" ><font color="red">[<s:property value="current-1"/>]</font></a>
                        		        </s:if>
                        		        <s:else>
                        		            <a href="reportAction_listReportsByPage4Statistics.action?currentPage=<s:property value="current-1"/>&pageSize=<s:property value="pageBean.pageSize"/>" >[<s:property value="current-1"/>]</a>
                        		        </s:else>
                                    </s:iterator>
                                </s:bean>
                  	        </s:else>
                  	    </s:else> 
                  		<s:if test="%{pageBean.currentPage==pageBean.totalPage}">
           	    			<a href="#" title="Next Page">Next</a>
            	  		</s:if>
            	  		<s:else>
            	  			<a href="reportAction_listReportsByPage4Statistics4Statistics.action?currentPage=<s:property value="%{pageBean.currentPage+1}"/>&pageSize=<s:property value="pageBean.pageSize"/>" title="Next Page">Next&raquo;</a>
            	  		</s:else>
                  		<a href="reportAction_listReportsByPage4Statistics4Statistics4Statistics.action?currentPage=<s:property value="pageBean.totalPage"/>&pageSize=<s:property value="pageBean.pageSize"/>" title="Last Page">Last&raquo;</a> </div>
                 	</s:if>
                  	<s:if test="%{listType==1}"> 
                  		<a href="reportAction_searchReport4Statistics.action?currentPage=1&pageSize=<s:property value="pageBean.pageSize"/>&loginKeyWords=<s:property value="loginKeyWords"/>&fromCheckDateKeyWords=<s:property value="fromCheckDateKeyWords"/>&endCheckDateKeyWords=<s:property value="endCheckDateKeyWords"/>" title="First Page">&laquo; First</a>
                  		<s:if test="%{pageBean.currentPage==1}">
                  			<a href="#" title="Previous Page">&laquo; Pre</a>
                  		</s:if>
                  		<s:else>
                  			<a href="reportAction_searchReport4Statistics.action?currentPage=<s:property value="%{pageBean.currentPage-1}"/>&pageSize=<s:property value="pageBean.pageSize"/>&loginKeyWords=<s:property value="loginKeyWords"/>&fromCheckDateKeyWords=<s:property value="fromCheckDateKeyWords"/>&endCheckDateKeyWords=<s:property value="endCheckDateKeyWords"/>" title="Previous Page">&laquo; Pre</a>
                  		</s:else>
                  		<a href="reportAction_searchReport4Statistics.action?currentPage=1&pageSize=<s:property value="pageBean.pageSize"/>" title="First Page">&laquo; First</a>
                  		<s:if test="%{pageBean.totalPage<5}">
                  	        <s:bean name= "org.apache.struts2.util.Counter"  id= "counter1" >
                  	            <s:param name="first"  value="1"  />    
                                <s:param name="last"  value="pageBean.totalPage"  />
                        	    <s:iterator>
                        		    <s:if test="%{pageBean.currentPage==current-1}">
                        		        <a href="reportAction_searchReport4Statistics.action?currentPage=<s:property value="current-1"/>&pageSize=<s:property value="pageBean.pageSize"/>" ><font color="red">[<s:property value="current-1"/>]</font></a>
                        		    </s:if>
                        		    <s:else>
                        		        <a href="reportAction_searchReport4Statistics.action?currentPage=<s:property value="current-1"/>&pageSize=<s:property value="pageBean.pageSize"/>" >[<s:property value="current-1"/>]</a>
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
                        		            <a href="reportAction_searchReport4Statistics.action?currentPage=<s:property value="current-1"/>&pageSize=<s:property value="pageBean.pageSize"/>" ><font color="red">[<s:property value="current-1"/>]</font></a>
                        		        </s:if>
                        		        <s:else>
                        		            <a href="reportAction_searchReport4Statistics.action?currentPage=<s:property value="current-1"/>&pageSize=<s:property value="pageBean.pageSize"/>" >[<s:property value="current-1"/>]</a>
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
                        		            <a href="reportAction_searchReport4Statistics.action?currentPage=<s:property value="current-1"/>&pageSize=<s:property value="pageBean.pageSize"/>" ><font color="red">[<s:property value="current-1"/>]</font></a>
                        		        </s:if>
                        		        <s:else>
                        		        <a href="reportAction_searchReport4Statistics.action?currentPage=<s:property value="current-1"/>&pageSize=<s:property value="pageBean.pageSize"/>" >[<s:property value="current-1"/>]</a>
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
                        		            <a href="reportAction_searchReport4Statistics.action?currentPage=<s:property value="current-1"/>&pageSize=<s:property value="pageBean.pageSize"/>" ><font color="red">[<s:property value="current-1"/>]</font></a>
                        		        </s:if>
                        		        <s:else>
                        		            <a href="reportAction_searchReport4Statistics.action?currentPage=<s:property value="current-1"/>&pageSize=<s:property value="pageBean.pageSize"/>" >[<s:property value="current-1"/>]</a>
                        		        </s:else>
                                    </s:iterator>
                                </s:bean>
                  	        </s:else>
                  	    </s:else>
                  		<s:if test="%{pageBean.currentPage==pageBean.totalPage}">
           	    			<a href="#" title="Next Page">Next</a>
            	  		</s:if>
            	  		<s:else>
            	  			<a href="reportAction_searchReport4Statistics.action?currentPage=<s:property value="%{pageBean.currentPage+1}"/>&pageSize=<s:property value="pageBean.pageSize"/>&loginKeyWords=<s:property value="loginKeyWords"/>&fromCheckDateKeyWords=<s:property value="fromCheckDateKeyWords"/>&endCheckDateKeyWords=<s:property value="endCheckDateKeyWords"/>" title="Next Page">Next&raquo;</a>
            	  		</s:else>
                  		<a href="reportAction_searchReport4Statistics.action?currentPage=<s:property value="pageBean.totalPage"/>&pageSize=<s:property value="pageBean.pageSize"/>&loginKeyWords=<s:property value="loginKeyWords"/>&fromCheckDateKeyWords=<s:property value="fromCheckDateKeyWords"/>&endCheckDateKeyWords=<s:property value="endCheckDateKeyWords"/>" title="Last Page">Last&raquo;</a> 
                  	</s:if>
                  </div>
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
				<td>
					<s:property value="#report.masterSampleNo" />
				</td>
				<td>
					<s:property value="#report.vendor" />
				</td>
				<td>
					<s:property value="#report.engineer.name" />
				</td>
				<td>
					<s:property value="#report.dateOut.substring(0,4)" />-<s:property
											value="#report.dateOut.substring(4,6)" />-<s:property
											value="#report.dateOut.substring(6,8)" />
				</td>
				<td>
					<s:property value="#report.price" />
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
                <td>
                    <a href="reportAction_deleteReport4Statistics.action?report.puid=<s:property value="#report.puid" />&currentPage=<s:property value="%{pageBean.currentPage+1}"/>&pageSize=<s:property value="pageBean.pageSize"/>&loginKeyWords=<s:property value="loginKeyWords"/>&fromCheckDateKeyWords=<s:property value="fromCheckDateKeyWords"/>&endCheckDateKeyWords=<s:property value="endCheckDateKeyWords"/>" title="Delete">Delete</a>
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