<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	session.setAttribute("currentMenu", "Connect");
%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<c:import url="../layouts/Header.jsp" />

	<section class="connection">
		<form method="POST" action="ValidateConnect">
			<input type="email" name="connect.email" id="email" placeholder="Email" required /> 
			<input type="password" name="connect.password" id="password" placeholder="Mot de passe" required />
				
					<div class="errorMessage">${ incorrect }</div>
				
			<button type=submit class="buttonLogin">Connexion</button>
			
			<a href="<s:url action="SignIn" />"><button class="buttonSignin">Inscription</button></a>
  <div class="spacer"> </div>
		</form>
	</section>

<c:import url="../layouts/Footer.jsp" />