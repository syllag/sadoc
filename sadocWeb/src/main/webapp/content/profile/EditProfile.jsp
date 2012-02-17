<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="description" content="Système d'authentification de document." />
		<title>Votre gestionnaire de compétence en ligne</title>
		<link href="css/modifier_details.css" type="text/css" rel="stylesheet" />
		<link rel="icon" type="image/png" href="img/favicon.png" />
	</head>

	<body>
		<header class="header">
			<a href="#"><img src='img/logo.png' /></a>
			<h1>Système d'authentification de document</h1>
		</header>

		<div id="wrapper">
			<menu label="main_menu" class="main_menu">
				<li class="menu_active"><a href="#">Profil</a></li>
				<li><a href="#">Compétences</a></li>
				<li><a href="#">CV</a></li>
			</menu>
	
			<section id="content">
				<form method="post" action="ModifyProfile">
					<input type="text" name="firstName" id="firstName" placeholder="Prenom" value="toto" required /> 
					<input type="text" name="lastName" id="lastName" placeholder="Nom" required /> 
					<input type="text" name="mail" id="mail" placeholder="mon.email@trololo.ufo" required /> 
					<input type="password" name="password" id="password" placeholder="Nouveau mot de passe" required /> 
					<input type="password" name="password2" id="password2" placeholder="Confirmation password" required />
					<input type="text" name="adress" id="adress" placeholder="Mon adresse" />
					<input type="text" name="zipCode" id="zipCode" placeholder="Code postal" />   
					<input type="text" name="town" id="town" placeholder="Ville" /> 
					<input type="text" name="phone" id="phone" placeholder="03/03/03/03/03" />
					<button type=submit class="button">UPDATE</button>
				</form>
			</section>
		</div>

		<footer> </footer>
	</body>
</html>

