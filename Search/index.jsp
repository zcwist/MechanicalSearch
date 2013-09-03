<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<style>
	.highlight {
	 background: yellow;
	}
	</style>
  <head>
	<title>My Search</title>   
    <meta http-equiv=Content-Type content="text/html; charset=gb2312">
    <script type='text/javascript' src=${pageContext.request.contextPath}/dwr/engine.js></script>
    <!-- ${pageContext.request.contextPath}表示当前应用的路径 -->
    <script type='text/javascript' src=${pageContext.request.contextPath}/dwr/util.js></script>
    <script type="text/javascript" src=${pageContext.request.contextPath}/dwr/interface/jsDoSearch.js></script>
    <!-- 所有将在页面中使用的对象，都需要事先通过这种方式声明，而且其URL前缀都要包括到interface -->
    
    
    <link rel="stylesheet" type="text/css" href="ext/resources/css/ext-all.css">
    <script type="text/javascript" src="ext/ext-all.js"></script>
    <script type="text/javascript" src="layout.js"></script>
  
  </head> 
  <body>
  </body>
</html>
