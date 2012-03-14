<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="description" content="SystÃ¨me d'authentification de document." />
<title>Votre gestionnaire de compétence en ligne</title>
<link href="css/documentInformation.css" type="text/css" rel="stylesheet" />
<link rel="icon" type="image/png" href="img/favicon.png" />
</head>

<body>
<div  class="wrap">

<div id="wrapper">
	<c:if test="${ empty mail }">
		<c:import url="../layouts/Menu.jsp"/>
	</c:if>
	<c:if test="${ not empty mail }">
		<c:import url="../layouts/MenuNotConnected.jsp"/>
	</c:if>

	<div class="wrap_body">
	
	
	<section id="content">
		<p>Error document information </p>
	</section>
</div>


	<div class="place_footer"></div>
	</div>
	
	
	</div>
	<c:import url="../layouts/Footer.jsp" />
</body>
</html>
