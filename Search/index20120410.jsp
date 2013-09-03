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
    <script language="javascript">
    function testWrapper(){
    	var query = document.getElementById('query').value;
    	jsDoSearch.search(query,show);
    	//jsDoSearch.getContent(query,showContents);
    	
    }
    function show(data){
    	//document.getElementById('result').value=data;
    	document.getElementById('filename').innerHTML=data;
   	
    }
    function testContent(){
    	var query = document.getElementById('query').value;
    	jsDoSearch.getContent(query,showContents);
    }
    function showContents(contents){
    	document.getElementById('showcontent').innerHTML=contents;
    }
    </script>
  </head>
  
  <body>
	<table width="500" border="0">
		<tr>
			<td colspan="3" style="background-color:#99bbbb;">
				<h1>机械设计知识检索</h1>
			</td>
		</tr>
		<tr>
			<td>
				<input type="text" id="query" name="query">
			</td>
			<td>
				<input type="button" id="button" name="button" value="Search" onclick="javascript:testWrapper()"><br>
			</td>
			<td>
				<input type="button" id="content" name="content" value="show content" onclick="javascript:testContent()"><br>
			</td>
		</tr>
		<tr valign="top">
			<td id="filename" style="background-color:#ffff99;width:100px;text-align:top;">
				<b>相关文件</b><br/>
			</td>
			<td colspan="2" id="showcontent"style="background-color:#EEEEEE;height:200px;width:400px;text-align:top;">
				Content goes here.
			</td>
		</tr>
	</table>  
  
	<!--  
	<input type="text" id="query" name="query">
	<input type="button" id="button" name="button" value="Search" onclick="javascript:testWrapper()"><br>
    <textarea id="result" name="result" cols="40" rows="10"></textarea>
    <br>
    <input type="button" id="content" name="content" value="show content" onclick="javascript:testContent()"><br>
    <div id="contents">
    </div>
    -->
  </body>
</html>
