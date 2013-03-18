<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	session.setAttribute("currentMenu", "Connect");
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<c:import url="../layouts/Header.jsp" />

<section class="connection">
	<s:form method="POST" action="ValidateConnect" theme="simple">
		<s:actionerror />
		<s:textfield key="connect.email" placeholder="Email" />
		<s:password key="connect.password" placeholder="Mot de passe" />

		<div>
			<s:submit cssClass="buttonLogin" value="Connexion" />
			<s:url var="signin" action="SignIn" />
			<s:submit cssClass="buttonSignin" value="Inscription"
				onclick="window.location.href='/SignIn'" />
		</div>
	</s:form>

	<div class="spacer"></div>

</section>

<c:import url="../layouts/Footer.jsp" />