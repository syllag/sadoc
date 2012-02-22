<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="description" content="Système d'authentification de document" />
<title>Votre gestionnaire de compétences en ligne</title>
<link href="css/style.css" type="text/css" rel="stylesheet" />
<link rel="icon" type="image/png" href="img/favicon.png" />
</head>

<body>
<header class="header">
	<a href="Home"><img src='img/logo.png' /></a>
	<h1>Système d'authentification de documents</h1>
</header>

<c:import url="../layouts/MenuNotConnected.jsp"/>

<section class="connection">
	<form method="POST" action="ValidateConnect">
		<input type="email" name="connect.email" id="email" placeholder="Your email" required  />
		<input type="password" name="connect.password" id="password" placeholder="Your password" required />
		<button type=submit class="buttonLogin" >LOG IN</button>
	</form>
	<a href="SignIn"><button class="buttonSignin">SIGN IN</button></a>
</section>

<footer>
</footer>
</body>
</html>
