<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="web.DoSearch"%>
<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	response.setContentType("text/plain");
	
	String text = request.getParameter("text");
	System.out.println(text);
	DoSearch doSearch=new DoSearch();
	String result = doSearch.getResultJson(text);
	response.getWriter().print(result);
 %>