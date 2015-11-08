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
      <h1 id="sidebar-title"><a href="#">青岛市电子商务产品质量信息服务平台</a></h1>
 
      <!-- Sidebar Profile links -->
      <div id="profile-links">
      
      <!--  Hello, <a href="#" title="Edit your profile">865171</a>, you have <a href="#messages" rel="modal" title="3 Messages">3 Messages</a><br />
         -->
         <br />
        欢迎回来， <s:property value="#session.loginName"/>&nbsp;| <a href="<%=basePath%>adminAction_logOut.action" title="Sign Out">退出登录</a> </div>
      <ul id="main-nav">
        <!-- Accordion Menu -->
        <!--<li> <a href="#/" class="nav-top-item no-submenu">
           Add the class "no-submenu" to menu items with no sub menu 
          	控制面板 </a> </li>-->
        <s:iterator value="#session.menusAuthority" id="menu">
         <s:if test="#menu == 1">
         <li> <a id="1001" href="#" class="nav-top-item">质量信息采集管理</a>
          <ul>
            <li><a id="dianshangguanli" onClick="setMenuCookie('1001dianshangguanli')"
                   href="spiderAction_spiderList.action">电商采集管理</a></li>
           <li><a id="yuqingguanli" onClick="setMenuCookie('1001yuqingguanli')" href="admin_qdqts_centling/yuqing/highchart.jsp" target="_blank">舆情管理</a></li> 
            <li><a id="zhijianxinxicaiji" onClick="setMenuCookie('1001zhijianxinxicaiji')" href="spiderAction_spiderreportList.action">质检信息采集</a></li>
            <li><a id="zhijianxinxiguanli" onClick="setMenuCookie('1001zhijianxinxiguanli')" href="spiderAction_spiderreportManager.action">质检信息管理</a></li>
            <li><a id="neirongfenxiguanli" onClick="setMenuCookie('1001neirongfenxiguanli')" href="analysisAction_analysisList.action">内容分析设置</a></li>
          </ul>
        </li>
        </s:if>
        
        <s:if test="#menu == 2">
        <li> <a id="1002" href="#" class="nav-top-item">监管信息管理 </a>
          <ul>
            <li><a id="wenzhang4" 		onClick="setMenuCookie('1002wenzhang4')" href="<%=basePath%>articleAction_findArticlesByPage.action?menuId=4&currentPage=1&pageSize=10">文章管理</a></li>
            <!-- li><a id="wenzhangfenlei4" onClick="setMenuCookie('1002wenzhangfenlei4')" href="<%=basePath%>articleTypeAction_manage.action?menuId=4">文章分类管理</a></li -->
          </ul>
        </li>
        </s:if>
        <s:if test="#menu == 3">
        <li> <a id="1003" href="#" class="nav-top-item">WTO信息管理</a>
          <ul>
            <li><a id="wtowenzhang1" 		onClick="setMenuCookie('1003wtowenzhang1')" href="<%=basePath%>articleAction_findWTOArticlesByType.action?menuId=6&typeId=wto01&currentPage=1&pageSize=10">新闻</a></li>
            <li><a id="wtowenzhang2" 		onClick="setMenuCookie('1003wtowenzhang2')" href="<%=basePath%>articleAction_findWTOArticlesByType.action?menuId=6&typeId=wto02&currentPage=1&pageSize=10">通报批评</a></li>
            <li><a id="wtowenzhang3" 		onClick="setMenuCookie('1003wtowenzhang3')" href="<%=basePath%>articleAction_findWTOArticlesByType.action?menuId=6&typeId=wto03&currentPage=1&pageSize=10">重点产品</a></li>
            <li><a id="wtowenzhang4" 		onClick="setMenuCookie('1003wtowenzhang4')" href="<%=basePath%>articleAction_findWTOArticlesByType.action?menuId=6&typeId=wto04&currentPage=1&pageSize=10">目标市场</a></li>
            <li><a id="wtowenzhang5" 		onClick="setMenuCookie('1003wtowenzhang5')" href="<%=basePath%>articleAction_findWTOArticlesByType.action?menuId=6&typeId=wto05&currentPage=1&pageSize=10">热点话题</a></li>
          </ul>
        </li>
        </s:if>
        <s:if test="#menu == 4">
        <li> <a id="1004" href="#" class="nav-top-item">产品信息管理 </a>
          <ul>
            <li><a id="chanpin" 		onClick="setMenuCookie('1004chanpin')" href="<%=basePath%>productInfo_productInfoMagerInit.action">产品管理</a></li>
            <li><a id="chanpinfenlei" 	onClick="setMenuCookie('1004chanpinfenlei')" href="<%=basePath%>productInfotype_productInfoTypeManagerInit.action">分类管理</a></li>
         	<li><a id="chanpinfenle" 	onClick="setMenuCookie('1004chanpinfenle')" href="<%=basePath%>provideProductInfoId_provideProductInfoInit.action">提供产品编码</a></li>
          </ul>
        </li>
        </s:if>
        <s:if test="#menu == 5">
        <li> <a id="1005" href="#" class="nav-top-item">订单信息管理 </a>
          <ul>
            <li><a id="dingdan" 		onClick="setMenuCookie('1005dingdan')"	href="<%=basePath%>standardOrderManager_standardAdminOrderUserInit.action">订单管理</a></li>
          </ul>
        </li>
        </s:if>
        <s:if test="#menu == 6">
        <li> <a id="1006" href="#" class="nav-top-item">标准信息管理 </a>
          <ul>
            <li><a id="biaozhun6" 		onClick="setMenuCookie('1006biaozhun6')" href="<%=basePath%>standardInfoManager_standardInfoManagerInit.action">标准管理</a></li>
            <li><a id="biaozhunfeilei6" 	onClick="setMenuCookie('1006biaozhunfeilei6')" href="<%=basePath%>standardClass_standardClassInitH.action">分类管理</a></li>
            <li><a id="biaoapply" 	onClick="setMenuCookie('1006biaoapply')" href="<%=basePath%>standardOrderManager_searchStandardApply.action">申请文档信息</a></li>
             <li><a id="adddate" 	onClick="setMenuCookie('1006adddate')" href="<%=basePath%>standardInfoManager_getIncrementDataList.action">导入增量数据</a></li>
          </ul>
        </li>
        </s:if>
        <s:if test="#menu == 7">
        <li> <a id="1007" href="#" class="nav-top-item">企业信息管理 </a>
          <ul>
            <li><a id="biaozhun7" 		onClick="setMenuCookie('1007biaozhun7')" href="<%=basePath%>enterpriseInfoManager_enterpriseInfoManagerInit.action">企业管理</a></li>
            <li><a id="biaozhunfeilei7"  onClick="setMenuCookie('1007biaozhunfeilei7')" href="<%=basePath%>enterpriseClassManager_enterpriseClassAddInit.action">企业类别</a></li>
            <li><a id="enterpriseBrand" 	onClick="setMenuCookie('1007enterpriseBrand')" href="<%=basePath%>productInfoBrand_productInfoBrandManagerList.action">企业品牌管理</a></li>
          </ul>
        </li>
        </s:if>
        <s:if test="#menu == 8">
        <li> <a id="1008" href="#" class="nav-top-item">条码企业管理 </a>
          <ul>
            <li><a id="enterpriseMana" 		onClick="setMenuCookie('1008enterpriseMana')" href="<%=basePath%>barCodeEnterprise_barCodeList.action">条码企业管理</a></li>
            <li><a id="enterpriseType"  onClick="setMenuCookie('1008enterpriseType')" href="<%=basePath%>barCodeEnterprise_barCodeTypeManager.action">条码企业类别</a></li>
          </ul>
        </li>
        </s:if>
        <s:if test="#menu == 9">
        <li> <a id="1009" href="#" class="nav-top-item">溯源信息管理</a>
          <ul>
            <li><a id="suyuan" 	onClick="setMenuCookie('1009suyuan')" href="<%=basePath%>productSource_productSourceListSearchAdminInit.action">溯源管理</a></li>
          </ul>
        </li>
        </s:if>
        <s:if test="#menu == 10">
        <li> <a id="1010" href="#" class="nav-top-item"> 标准服务咨询 </a>
          <ul>
            <li><a id="fuwguangli"  onClick="setMenuCookie('1010fuwguangli')"  href="<%=basePath%>standardServiceInfoManager_standardServiceAdminInit.action">服务咨询管理</a></li>
            <li><a id="fuwufenlei"    onClick="setMenuCookie('1010fuwufenlei')"  href="<%=basePath%>standardServiceManager_findStandardServiceType.action">服务咨询分类</a></li>
          </ul>
        </li>
        </s:if>
        <s:if test="#menu == 11">
         <li> <a id="1011" href="#" class="nav-top-item">个人中心</a>
          <ul>
            <li><a id="yonghuguanli" onClick="setMenuCookie('1011yonghuguanli')"  href="<%=basePath%>adminAction_beforeChangePassWord.action">修改密码</a></li>
            <li><a id="userrank" onClick="setMenuCookie('1011userrank')"  href="<%=basePath%>userrank_searchUserRankAdminInit.action">用户等级管理</a></li>
          </ul>
        </li>
        </s:if>
        <s:if test="#menu == 12">
        <li> <a id="1012" href="#" class="nav-top-item">质检新闻管理 </a>
          <ul>
            <li><a id="wenzhang2" 		onClick="setMenuCookie('1012wenzhang2')" href="<%=basePath%>articleAction_findArticlesByPage.action?menuId=2&currentPage=1&pageSize=10">文章管理</a></li>
            <li><a id="wenzhangfenlei2" onClick="setMenuCookie('1012wenzhangfenlei2')" href="<%=basePath%>articleTypeAction_manage.action?menuId=2">文章分类管理</a></li>
          </ul>
        </li>
        </s:if>
       
        <s:if test="#menu == 13">
         <li> <a id="1013" href="#" class="nav-top-item">主页管理</a>
          <ul>
            <li><a id="lunbotupian" onClick="setMenuCookie('1013lunbotupian')" href="indexFlashInfoction_findAll.action">主页幻灯片管理</a></li>
            <li><a id="standardPDF" onClick="setMenuCookie('1013standardPDF')" href="standardIndexFlashInfoAction_standardFlashManager.action">标准主页幻灯片管理</a></li>
          	<li><a id="friendshipLink" onClick="setMenuCookie('1013friendshipLink')" href="adminAction_getFriendshipLinks.action">友情链接管理</a></li>
          	<li><a id="friendshipLink" onClick="setMenuCookie('1013aboutus')" href="aboutUsAction_findAll.action">关于我们管理</a></li>
          </ul>
        </li>
        </s:if>
        <s:if test="#menu == 14">
        <li> <a id="1014" href="#" class="nav-top-item"> 用户管理 </a>
          <ul>
            <li><a id="putongzhanghao"  onClick="setMenuCookie('1014putongzhanghao')"  href="<%=basePath%>commonUserAuditAction_queryForPage.action">普通账号</a></li>
            <li><a id="qiyezhanghao"    onClick="setMenuCookie('1014qiyezhanghao')"  href="<%=basePath%>enterpriseUserAuditAction_queryForPage.action">企业账号</a></li>
            <li><a id="zhengfuzhanghao" onClick="setMenuCookie('1014zhengfuzhanghao')" href="<%=basePath%>governmentUserAuditAction_queryForPage.action">政府账号</a></li>
          	<li><a id="adminmanage"     onClick="setMenuCookie('1014adminmanage')" href="<%=basePath%>adminAction_listAdmin.action">管理员管理</a></li>
          	<li><a id="liveMessage"     onClick="setMenuCookie('1014liveMessage')" href="<%=basePath%>messageAction_liveMessageList.action">客户留言列表</a></li>
          </ul>
        </li>
        </s:if>
        </s:iterator>
      </ul>
      <!-- End #main-nav -->
    </div>
  </div>
