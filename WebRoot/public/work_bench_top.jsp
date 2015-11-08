<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
if(session.getAttribute("loginName")==null){
%>

<jsp:forward page="/login.jsp" /> 

<%
}
%>
<div style="padding:5px 10px 5px 10px;background:#fff;">
	<img src="<%=basePath %>images/admin_logo.jpg" height="63" />
</div>