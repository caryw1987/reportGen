<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
  <div id="footer"> <small>
      <!-- Remove this notice or replace it with whatever you want -->
      &#169; Copyright 2014 Centling.com | Powered by <a href="#"> Centling.com</a> | <a href="#">Top</a> </small> </div>
    <!-- End #footer -->
    
<script type="text/javascript">
	$(function () {
		var currentMenu=""+$.cookie('menuid');
	 	$("#"+currentMenu.substring(0,4)).addClass("current");
	 	$("#"+currentMenu.substring(4)).addClass("current");
	 	$("#"+currentMenu.substring(0,4)).click();
	});
	
	function setMenuCookie(menuid)
	{
		$.cookie('menuid', ""+menuid+"", {expires:1, path:'/'});
	}
	
</script>