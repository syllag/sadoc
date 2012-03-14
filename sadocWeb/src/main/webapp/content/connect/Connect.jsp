<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	session.setAttribute("currentMenu", "Connect");
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
</head>

<body>
	<div  class="wrap">

	<c:import url="../layouts/MenuNotConnected.jsp"/>
	
	<div class="wrap_body">

	<section class="connection">
		<form method="POST" action="ValidateConnect">
			<input type="email" name="connect.email" id="email"
				placeholder="Your email" required /> <input type="password"
				name="connect.password" id="password" placeholder="Your password"
				required />
				<s:if test="#session.incorrect == 'ok'">
					<div class="errorMessage">Password ou mail incorrect</div>
				</s:if>
			<button type=submit class="buttonLogin">LOG IN</button>
			<a href="SignIn"><button class="buttonSignin">SIGN IN</button></a>
  <div class="spacer"> </div>
		</form>
	</section>

	<div class="place_footer"></div>
	</div>
	
	
	</div>
	<c:import url="../layouts/Footer.jsp" />
</body>
</html>
