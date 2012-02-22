<!DOCTYPE html>
<html lang="fr">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="description" content="Système d'authentification de document." />
<title>Votre gestionnaire de compétence en ligne</title>
<link href="css/connect.css" type="text/css" rel="stylesheet" />
<link rel="icon" type="image/png" href="img/favicon.png" />
</head>

<body>
<header class="header">
	<a href="#"><img src='img/logo.png' /></a><h1>Système d'authentification de document</h1>
</header>

<section class="connection">
	<form method="POST" action="validateConnect">
		<input type="email" name="connect.email" id="email" placeholder="Your email" required  />
		<input type="password" name="connect.password" id="password" placeholder="Your password" required />
		<button type=submit class="button" >LOG IN</button>
	</form>
</section>

<footer>
</footer>
</body>
</html>
