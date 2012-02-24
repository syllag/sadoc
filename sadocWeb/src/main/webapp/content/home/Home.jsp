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
	<div  class="wrap">

	<c:import url="../layouts/MenuNotConnected.jsp"/>
	
	<div class="wrap_body">

	<div id="wrapper">
		<section id="content">
			<table>
				<tr>
					<td>
						<a href="http://www.univ-artois.fr">
							<img src='img/univ_artois.png' alt="université d'artois" class="icon"/>
						</a>
					</td>
					<td><span class="quotation">SADoc - Système d'authentification de documents</span></td>
				</tr>
			</table>
			<p>
				<h3>Qu'est-ce ?</h3>
				SADoc est, comme son nom l'indique, un système d'authentification des documents
				qui permet de vérifier l'authenticité de ceux-ci. Grâce au plug-in intégré
				à un portfolio, on peut récupérer les fichiers hébergés dessus, les signer numériquement,
				enregistrer leurs signatures dans notre base de données, et pouvoir vérifier
				ultérieurement son authenticité grâce à un QRCode.<br />
				Développé par les deuxième année de Master Informatique Ingénierie Logicielle pour
				Internet, ce projet a principalement été développé en Java EE.
			</p>
		</section>
	</div>
	
	<div class="place_footer"></div>
	</div>
	
	
	</div>
	<c:import url="../layouts/Footer.jsp" />

</body>
</html>