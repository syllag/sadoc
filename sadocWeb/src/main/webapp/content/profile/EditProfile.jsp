<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	session.setAttribute("currentMenu", "EditProfile");
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
		
		<c:if test="${ empty mail }">
			<c:import url="../layouts/Menu.jsp"/>
		</c:if>
		<c:if test="${ not empty mail }">
			<c:import url="../layouts/MenuNotConnected.jsp"/>
		</c:if>
		
		<div class="wrap_body">

		<div id="wrapper">
			
	
			<section id="content">
				<form method="post" action="ModifyProfile">
					<input type="password" name="password" id="password" placeholder="Nouveau mot de passe" /> 
					<input type="password" name="password2" id="password2" placeholder="Confirmation password" />
					<input type="text" name="adress" id="adress" placeholder="Mon adresse" value="${ adress }" required />
					<input type="text" name="zipCode" id="zipCode" placeholder="Code postal" value="${ zipCode }" required />   
					<input type="text" name="town" id="town" placeholder="Ville" value="${ town }" required /> 
					<input type="text" name="phone" id="phone" placeholder="Téléphone" value="${ phone }" required />
					<button type=submit class="button">Mettre à jour</button>
				</form>
			</section>
		</div>

		<div class="place_footer"></div>
		</div>
	
	
		</div>
		<c:import url="../layouts/Footer.jsp" />
	</body>
</html>

