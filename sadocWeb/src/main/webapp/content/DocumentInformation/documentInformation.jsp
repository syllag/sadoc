<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset=utf-8 />
<meta name="description"
	content="Systeme d'authentification de document." />
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


		<section id="content2">
			<div class="user">
				<h3>${owner.lastName} ${owner.firstName}</h3>
				<p>${owner.address}</p>
				<p>${owner.town}, ${owner.zipCode}</p>
				<p>${owner.phone}</p>

			</div>
			<hr />
			<p class="auth">Le document a été certifié par SADOC , le
				${document.creationDate}
			<p>
			<hr />

			<div class="infoCompetence">
				<c:forEach var="competence" items="${listCompetences}">
					<p>compétence : ${competence.name}
					<p>
					<p>description compétence : ${competence.description}</p>
				</c:forEach>
			</div>

			<div class="infoDocument">
				<p>Nom du document : ${document.name}</p>
				<p>url document : ${document.url}</p>
			</div>

			<div class="infoP7S">
				<p>
					signature du fichier (fichier *.p7s) : <a
						href="downloadP7S?sa=${sa}"><button>Télécharger</button></a>
				</p>
				<p>
					L'utilisation du fichier *.p7s requiert le logiciel <a
						href="http://www.adesium.com/index.php?option=com_quickfaq&view=items&cid=1%3Amysign&id=14">mySIGN</a>
				</p>
			</div>
		</section>
	</div>



	<div class="place_footer"></div>
	</div>
	
	
	</div>
	<c:import url="../layouts/Footer.jsp" />
</body>
</html>


