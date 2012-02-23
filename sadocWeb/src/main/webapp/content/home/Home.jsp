<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	session.setAttribute("currentMenu", "Home");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/style.css" type="text/css" rel="stylesheet" />
<title>SADoc</title>
</head>
<body>
	<header class="header">
		<a href="Home"><img src='img/logo.png' /></a>
		<h1>Système d'authentification de documents</h1>
	</header>
	<c:import url="../layouts/MenuNotConnected.jsp" />

	<div id="wrapper">
		<section id="content">
			<table>
				<tr>
					<td><img src='img/logo Artois.png' alt="université d'artois"
						class="icon" /></td>
					<td><span class="quotation"> Gestion de certification
							de documents</span></td>
				</tr>
			</table>
		</section>
	</div>

</body>
</html>