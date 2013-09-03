<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="myPackage.FindStuff"%>
<%
	System.out.println("receive a request");
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	String id = request. getParameter("id");
	System.out.println(id);
	FindStuff findStuff = new FindStuff();
	String result = findStuff.getStuffById(id).getContents();
	response.getWriter().print(result);
	
%>