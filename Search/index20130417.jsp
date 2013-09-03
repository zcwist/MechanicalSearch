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
	<title>DWR Test</title>   
    <meta http-equiv=Content-Type content="text/html; charset=gb2312">
    <script type='text/javascript' src=${pageContext.request.contextPath}/dwr/engine.js></script>
    <!-- ${pageContext.request.contextPath}表示当前应用的路径 -->
    <script type='text/javascript' src=${pageContext.request.contextPath}/dwr/util.js></script>
    <script type="text/javascript" src=${pageContext.request.contextPath}/dwr/interface/jsDoSearch.js></script>
    <!-- 所有将在页面中使用的对象，都需要事先通过这种方式声明，而且其URL前缀都要包括到interface -->
    
    
    <link rel="stylesheet" type="text/css" href="ext/resources/css/ext-all.css">
    <script type="text/javascript" src="ext/ext-all.js"></script>
    <script type="text/javascript" src="ExtendWordList.js"></script>
    

   
    <!-- ExtJS相关 -->
    
    
    
    
    <script language="javascript">
    var result;
    function testWrapper(){
    	var query = document.getElementById('query').value;
    	var resultlist = document.getElementById("Result");
    	resultlist.innerHTML = "";
    	
    	jsDoSearch.getExtendWordsXML(query,showExtendWordGrid);
    	jsDoSearch.result(query,show);
    }
    function showExtendWordGrid(data){
    	grid(data);
    }
    function show(data){
    	document.getElementById("Result").innerHTML=data;
    }

    </script>
  </head>
  
  <body>
	<table width="1000" border="0">
		<tr>
			<td colspan="1" style="background-color:#99bbbb;">
				<h1>机械设计知识检索</h1>
			</td>
		</tr>
		<tr>
			<td>
				<input type="text" id="query" name="query">
				<input type="button" id="button" name="button" value="Search" onclick="javascript:testWrapper()"><br>
			</td>
		</tr>
		<tr id="Result" valign="top">
			<td colspan="1" style="background-color:#EEEEEE;height:200px;width:400px;text-align:top;">
				Content goes here.
			</td>
		</tr>
	</table>  
  
  </body>
</html>
