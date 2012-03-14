<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
	session.setAttribute("currentMenu", "SignIn");
%>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="description"
	content="Système d'authentification de document" />
<title>Votre gestionnaire de compétences en ligne</title>
<link href="css/style.css" type="text/css" rel="stylesheet" />
<link rel="icon" type="image/png" href="img/favicon.png" />
<script>
	function check() {
		var p1 = document.getElementById('password');
		var p2 = document.getElementById('confirm_password');
		if (p1.value != p2.value) {
			p2.setCustomValidity("Passwords don't match");
		} else {
			p2.setCustomValidity('');
		}
		if (p1.value.length < 8) {
			p1.setCustomValidity("Put a password with at least 8 caracters");
		} else {
			p1.setCustomValidity("");
		}
	}
</script>
</head>

<body>
	<div class="wrap">

	<c:if test="${ empty mail }">
		<c:import url="../layouts/Menu.jsp"/>
	</c:if>
	<c:if test="${ not empty mail }">
		<c:import url="../layouts/MenuNotConnected.jsp"/>
	</c:if>

	<div class="wrap_body" id="wrapper">


		<div class="wrap_body">

			<section class="inscription">
				<form method="post" action="SignIn">
					<input type="text" name="form.firstname" id="prenom"
						placeholder="Type your Firstname" required /> <input type="text"
						name="form.name" id="nom" placeholder="Type your Lastname"
						required /> <input type="text" name="form.mail" id="email"
						placeholder="Type your email"
						pattern="([\w\-\.]+)@((\[([0-9]{1,3}\.){3}[0-9]{1,3}\])|(([\w\-]+\.)+)([a-zA-Z]{2,4}))"
						required /> <input type="password" name="form.password"
						id="password" placeholder="Type your password" required /> <input
						type="password" name="form.password2" id="confirm_password"
						placeholder="Confirm your password" required onblur="check();" />
					<s:if test="#session.error != 'Ok'">
						<div class="errorMessage">Problème temporaire... Essayez plus tard.</div>
					</s:if>
					<s:if test="#session.inexistante != 'Ok'">
						<div class="errorMessage">Vous n'avez pas de compte.</div>
					</s:if>
					<s:if test="#session.incorrectMail != 'Ok'">
						<div class="errorMessage">Mail déjà utilisé</div>
					</s:if>
					<button type=submit onclick="check();" class="button">SIGN
						IN</button>
				</form>
			</section>

		</div>
	
	<div class="place_footer"></div>
	</div>
	
	</div>
	<c:import url="../layouts/Footer.jsp" />
</body>
</html>
