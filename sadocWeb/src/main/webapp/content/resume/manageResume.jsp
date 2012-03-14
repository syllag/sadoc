<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>	

	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%-- 
		A REFAIRE !!!!!!!!
		PAS DE BALISES STRUTS DANS LES PAGES JSP !!!!!
		REGARDER LA PAGE layouts/DefaultLayout.jsp
	 --%>
	
	<s:url id="fileDownload" namespace="/" action="downloadResume?cv=1" ></s:url>
	
	<p>CV 1 : <s:a href="%{fileDownload}"><button>cv1.pdf</button></s:a></p>
	
</body>
</html>