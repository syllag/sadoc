<%@ taglib prefix="s" uri="/struts-tags"%>
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
			<input type="text" name="form.firstname" id="prenom"
				placeholder="Type your Firstname" required /> <input type="text"
				name="form.name" id="nom" placeholder="Type your Lastname" required />
			<input type="text" name="form.email" id="email"
				placeholder="Type your email"
				pattern="([\w\-\.]+)@((\[([0-9]{1,3}\.){3}[0-9]{1,3}\])|(([\w\-]+\.)+)([a-zA-Z]{2,4}))"
				required /> <input type="password" name="form.password"
				id="password" placeholder="Type your password" required /> <input
				type="password" name="form.password2" id="confirm_password"
				placeholder="Confirm your password" required
				onblur="checkPassword();" />
			<div id="msg_erreur">
				<s:actionerror />
			</div>

			<button type=submit onfocus="checkPassword();" class="button">SIGN
				IN</button>
		</form>
	</section>

	<footer> </footer>


	<script>
		function checkPassword() {
			var p1 = document.getElementById('password');
			var p2 = document.getElementById('confirm_password');
			if (p1.value != p2.value) {
				p2.setCustomValidity("Passwords don't match");
			} else {
				p2.setCustomValidity('');
			}
		}
	</script>
</body>
</html>
