<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<<!DOCTYPE html>
<html>
<head>
	<meta charset=utf-8 />
	<meta name="description"
	content="Systeme d'authentification de document." />
	<title>Votre gestionnaire de compétence en ligne</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="css/style.css" type="text/css" rel="stylesheet" />
	<link rel="icon" type="image/png" href="img/favicon.png" />
</head>
<body>
	<header class="header">
		<a href="#"><img src='img/logo.png' /> </a>
		<h1>Système d'authentification de document</h1>
	</header>

	<c:import url="../layouts/Menu.jsp" />

	<div id="wrapper">
		<section class="createResume" id="createResume">
		 	<h1>Création d'un curriculum vitae</h1>
			<form method="post" action="CreateResume">
				<p>Choisissez les compétences à ajouter:</p>
				<ul>
				<c:forEach var="competence" items="${listCompetences}">
					<li><input type="checkbox" name="form.listCompetences" id="listCompetences" value="${competence.id}" /> ${competence.name}</li>
				</c:forEach>
				</ul>
				<button type=submit class="button">Créer le CV</button>
			</form>
		</section>
	</div>
		<div class="place_footer"></div>

	
	<c:import url="../layouts/Footer.jsp" />
	
</body>
</html>