<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>找不到目标网页</title>



<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<LINK href="style/style.css" type=text/css rel=stylesheet>


</head>

<body>
	<DIV id=maincolumn>
		<DIV></DIV>
		<DIV></DIV>
	</DIV>
	<DIV id=container>
		<h2>date format error……</h2>
		<h4>date format error: date format should be yyyy-yy-dd</h4>
		
	</DIV>
	<DIV id=maincolumn>
		<DIV></DIV>
		<DIV></DIV>
	</DIV>
</body>
</html>
