<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>检测报告生成系统</title>
    <jsp:include page="/public/work_bench_reference.jsp"></jsp:include>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    <jsp:include page="/public/work_bench_top.jsp"></jsp:include>

	<jsp:include page="/public/work_bench_left.jsp"></jsp:include> 
	<jsp:include page="/public/work_bench_bottom.jsp"></jsp:include>
  </body>
</html>
