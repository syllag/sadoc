<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
	session.setAttribute("currentMenu", "Profil");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/style.css" type="text/css" rel="stylesheet" />
<title>page de profil</title>
</head>
<body>
	<c:import url="../layouts/Menu.jsp" />
	
		<div class="profile">
				<p><s:property value="#session.firstName" /></p>
				
		</div>
	<c:import url="../layouts/Footer.jsp" />
</body>
</html>