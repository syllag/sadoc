<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset=utf-8 />
<meta name="description"
	content="Systeme d'authentification de document." />
<title>Votre gestionnaire de comp�tence en ligne</title>
<link href="css/style.css" type="text/css" rel="stylesheet" />
<link rel="icon" type="image/png" href="img/favicon.png" />
</head>

<body>
	<header class="header">
		<a href="#"><img src='img/logo.png' /> </a>
		<h1>Syst�me d'authentification de document</h1>
	</header>

	<c:import url="../layouts/Menu.jsp" />

	<div id="wrapper">


		<section id="content2">
			<div class="user">
				<h3>${owner.lastName} ${owner.firstName}</h3>
				<p>${owner.address}</p>
				<p>${owner.town}, ${owner.zipCode}</p>
				<p>${owner.phone}</p>

			</div>
			<hr />
			<p class="auth">Le document a �t� certifi� par SADOC , le
				${document.creationDate}
			<p>
			<hr />

			<div class="infoCompetence">
				<c:forEach var="competence" items="${listCompetences}">
					<p>comp�tence : ${competence.name}
					<p>
					<p>description comp�tence : ${competence.description}</p>
				</c:forEach>
			</div>

			<div class="infoDocument">
				<p>Nom du document : ${document.name}</p>
				<p>url document : ${document.url}</p>
			</div>

			<div class="infoP7S">
				<p>signature du fichier (fichier *.p7s) : ${document.pk7}</p>
				<p>
					L'utilisation du fichier *.p7s requiert le logiciel <a
						href="http://www.adesium.com/index.php?option=com_quickfaq&view=items&cid=1%3Amysign&id=14">mySIGN</a>
				</p>
			</div>
		</section>
	</div>



	<footer> </footer>
</body>
</html>


