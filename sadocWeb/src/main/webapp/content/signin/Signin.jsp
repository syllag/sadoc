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
			
						<div class="errorMessage">${ error }</div>
					<button type=submit onclick="check();" class="button">SIGN
						IN</button>
				</form>
			</section>


	<c:import url="../layouts/Footer.jsp" />