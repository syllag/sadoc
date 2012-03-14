<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	session.setAttribute("currentMenu", "Profile");
%>
<!DOCTYPE html>
<html lang="fr">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="description" content="Système d'authentification de document." />
		<title>Votre gestionnaire de compétence en ligne</title>
		<link href="css/style.css" type="text/css" rel="stylesheet" />
		<link rel="icon" type="image/png" href="img/favicon.png" />
	</head>

	<body>
		<div  class="wrap">
		
		<c:if test="${  empty mail }">
			<c:import url="../layouts/Menu.jsp"/>
		</c:if>
		<c:if test="${ not empty mail }">
			<c:import url="../layouts/MenuNotConnected.jsp"/>
		</c:if>
		
		<div class="wrap_body">

		<div id="wrapper">
				
			<section id="content">
				<form method="post" action="ModifyProfile">
					<input type="text" name="firstName" id="firstName" placeholder="Prénom" value="${ firstname }" required /> 
					<input type="text" name="lastName" id="lastName" placeholder="Nom" value="${ name }" required /> 
					<input type="text" name="mail" id="mail" placeholder="email" value="${ mail }" required /> 
					<input type="text" name="adress" id="adress" placeholder="Adresse" value="${ adress }" />
					<input type="text" name="zipCode" id="zipCode" placeholder="Code postal" value="${ zipCode }" />   
					<input type="text" name="town" id="town" placeholder="Ville" value="${ town }" /> 
					<input type="text" name="phone" id="phone" placeholder="03/03/03/03/03" value="${ phone }" />
				</form>
			</section>
		</div>

		<div class="place_footer"></div>
		</div>
	
	
		</div>
		<c:import url="../layouts/Footer.jsp" />
	</body>
</html>
