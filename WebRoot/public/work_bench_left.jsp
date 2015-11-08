<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<div id="sidebar">
    <div id="sidebar-wrapper">
      <!-- Sidebar with logo and menu -->
      <h1 id="sidebar-title"><a href="#">Test Report Manage System</a></h1>
 
      <!-- Sidebar Profile links -->
      <div id="profile-links">
      
      	<br />
        welcom back， <s:property value="#session.loginName"/>&nbsp;| <a href="<%=basePath%>login.jsp" title="Sign Out">Logout</a> 
      </div>
      <ul id="main-nav">
         <li> <a id="1001" href="#" class="nav-top-item">User Management</a>
          <ul>
          	<s:if test="#session.isAdmin==true">
          		<li><a id="adduser" onClick="setMenuCookie('1001adduser')" href="userAction_redirect2AddUserJSP.action">Add User</a></li>
          		<li><a id="listuser" onClick="setMenuCookie('1001listuser')" href="userAction_listUsersByPage.action?currentPage=1&pageSize=100" >List Users</a></li>
          	</s:if>
            <s:else>
            	<li><a id="adduser" onClick="setMenuCookie('1001adduser')" href="#">Add User</a></li>
            	<li><a id="listuser" onClick="setMenuCookie('1001listuser')" href="userAction_listUsersByPage.action?currentPage=1&pageSize=100" >List Users</a></li>
            </s:else>
            
          </ul>
        </li>
        <li> <a id="1002" href="#" class="nav-top-item">Report Management</a>
          <ul>
			<s:if test="#session.isAdmin==true || #session.isRecorder==true" >
<!--             	<li><a id="addreport" onClick="setMenuCookie('1002addreport')" href="reportAction_redirect2AddReportJSP.action">Add New Report</a></li> -->
            	<li><a id="addmultreport" onClick="setMenuCookie('1002addmultreport')" href="reportAction_redirect2AddMultiReportJSP.action">Add Multiple Report</a></li>
           		<li><a id="listreport4recorde" onClick="setMenuCookie('1002listreport4recorde')" href="reportAction_listReportsByPage4Recorder.action?currentPage=1&pageSize=100" >My Reports</a></li>
            </s:if>
            <s:if test="#session.isAdmin==true || #session.isEntryPerson==true" >
<!--             	<li><a id="importreportfromexcel" onClick="setMenuCookie('1002importreportfromexcel')" href="reportAction_redirect2ImportReportJSP.action">Import Reports from excel</a></li> -->
            	<li><a id="listreport4entryperson" onClick="setMenuCookie('1002listreport4entryperson')" href="reportAction_listReportsByPage4EntryPerson.action?currentPage=1&pageSize=100" >Report Data Import</a></li>
            </s:if>
            <s:if test="#session.isAdmin==true || #session.isRecorder==true" >
            	<li><a id="listreport4resultlogin" onClick="setMenuCookie('1002listreport4resultlogin')" href="reportAction_listReportsByPage4ResultLogin.action?currentPage=1&pageSize=100" >Report Result Login</a></li>
            </s:if>
            <s:if test="#session.isAdmin==true || #session.isGenerator==true" >
            	<li><a id="listreport4genword" onClick="setMenuCookie('1002listreport4genword')" href="reportAction_listReportsByPage4GenWord.action?currentPage=1&pageSize=100" >Generate Report</a></li>
            </s:if>
            <s:if test="#session.isAdmin==true || #session.isChecker==true" >
            <!--	<li><a id="listreport4check" onClick="setMenuCookie('1002listreport4check')" href="reportAction_listReportsByPage4Check.action?currentPage=1&pageSize=100" >Check Report</a></li> -->
            </s:if>
            <s:if test="#session.isAdmin==true || #session.isSender==true" >
            <!--    <li><a id="listreport4send" onClick="setMenuCookie('1002listreport4send')" href="reportAction_listReportsByPage4Send.action?currentPage=1&pageSize=100" >Send Report</a></li>-->
            </s:if>
            <s:if test="#session.isAdmin==true || #session.addPricec==true">
            <!--	<li><a id="listreport4addprice" onClick="setMenuCookie('1002listreport4addprice')" href="reportAction_listReportsByPage4AddPrice.action?currentPage=1&pageSize=100" >Add Price</a></li>-->
            </s:if>	
            <s:if test="#session.isAdmin==true">
            <!--	<li><a id="listreport4statistics" onClick="setMenuCookie('1002listreport4statistics')" href="reportAction_listReportsByPage4Statistics.action?currentPage=1&pageSize=100" >Report statistics</a></li>-->
            </s:if>
            <!--<li><a id="listreport4progress" onClick="setMenuCookie('1002listreport4progress')" href="reportAction_listReportsByPage4Progress.action?currentPage=1&pageSize=100" >Report progress</a></li>-->	               
          </ul>
        </li>
        <li> <a id="1003" href="#" class="nav-top-item">Report Generate tool</a>
        	<ul>
        		<li><a id="listreport4genwordonly" onClick="setMenuCookie('1003listreport4genwordonly')" href="gen_word_only.jsp" >Generate Report</a></li>
        	</ul>
        </li>
      </ul>
      <!-- End #main-nav -->
    </div>
  </div>
