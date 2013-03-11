<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	session.setAttribute("currentMenu", "SignIn");
%>
<c:import url="../layouts/Header.jsp" />

<script>
	function check() {
		var p1 = document.getElementById('password');
		var p2 = document.getElementById('confirm_password');
		if (p1.value != p2.value) {
			p2.setCustomValidity("Les mots de passe ne correspondent pas.");
		} else {
			p2.setCustomValidity('');
		}
		if (p1.value.length < 8) {
			p1.setCustomValidity("Le mot de passe doit faire au minimum 8 caractères.");
		} else {
			p1.setCustomValidity("");
		}
	}
</script>

			<section class="inscription">
				<form method="post" action="SignIn">
					Prénom<br/>
					<input type="text" name="form.firstName" id="prenom" placeholder="Prénom" required /><br/>
					Nom<br/> 
					<input type="text" name="form.lastName" id="nom" placeholder="Nom" required /><br/>
					Email<br/> 
					<input type="text" name="form.mail" id="email" placeholder="Email" 
						pattern="([\w\-\.]+)@((\[([0-9]{1,3}\.){3}[0-9]{1,3}\])|(([\w\-]+\.)+)([a-zA-Z]{2,4}))"
						required /> <br/><br/>
					Mot de passe<br/>
					<input type="password" name="form.password"	id="password" placeholder="Mot de passe" required /><br/>
					Confirmation mot de passe<br/>
					<input type="password" name="form.password2" id="confirm_password" 
						placeholder="Confirmation mot de passe" required onblur="check();" /><br/>
					<div class="errorMessage">${ error }</div>
					<button type=submit onclick="check();" class="button">Inscription</button>
				</form>
			</section>


	<c:import url="../layouts/Footer.jsp" />