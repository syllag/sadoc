<!DOCTYPE html>
<html lang="fr">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="description"
	content="Système d'authentification de document." />
<title>Votre gestionnaire de compétence en ligne</title>
<link href="css/signin.css" type="text/css" rel="stylesheet" />
<link rel="icon" type="image/png" href="img/favicon.png" />
</head>

<body>
	<header class="header">
		<a href="#"><img src='img/logo.png' /></a>
		<h1>Système d'authentification de document</h1>
	</header>

	<section class="inscription">
		<form method="post" action="signIn">
			<input type="text" name="prenom" id="prenom"
				placeholder="Type your Firstname" required />
			<input type="text"
				name="nom" id="nom" placeholder="Type your Lastname" required /> 
			<input
				type="text" name="email" id="email" placeholder="Type your email"
				required /> 
			<input type="password" name="password" id="password"
				placeholder="Type your password" required /> 
			<input type="password"
				name="confirm_password" id="confirm_password"
				placeholder="Confirm your password" required />
			<button type=submit class="button">SIGN IN</button>
		</form>
	</section>

	<footer> </footer>
</body>
</html>
