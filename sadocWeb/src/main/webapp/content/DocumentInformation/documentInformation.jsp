<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<header class="header">
	<a href="#"><img src='img/logo.png' /></a><h1>Système d'authentification de document</h1>
</header>

<c:import url="../layouts/Menu.jsp"/>

<div id="wrapper">
	
	
	<section id="content2">
		<div class="user">
			<h3> ${owner.lastName} ${owner.firstName} </h3>
			<p> ${owner.town}, ${owner.zipCode} </p>
			<p>${owner.address} </p>
			<p> ${owner.phone} </p>

		</div>
		<hr />
		<p class="auth"> Le document a �t� certifi� par SADOC , le ${document.creationDate} <p>
		<hr />

		<div class="infoCompetence">
			<c:forEach var="competence" items="${listCompetences}">
			<p> comp�tence : ${competence.name}<p>
			<p> description comp�tence : ${competence.description}</p>
			</c:forEach>	
		</div>
	
		<div class="infoDocument">
			<p> Nom du document : ${document.name}</p>
			<p> url document : ${document.url}</p>
		</div>

		<div class="infoP7S">
			<p>signature du fichier (fichier *.p7s) : ${document.pk7}</p>
		</div>
	</section>
</div>



<footer>
</footer>
</body>
</html>


