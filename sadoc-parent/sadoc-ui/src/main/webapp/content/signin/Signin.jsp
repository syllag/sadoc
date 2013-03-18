<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	session.setAttribute("currentMenu", "SignIn");
%>
<c:import url="../layouts/Header.jsp" />


			<section class="inscription">
						<s:form action="SignIn" validate="true">
							<s:actionerror />
							<s:textfield label="Prénom" labelposition="top" key="form.firstName" placeholder="Prénom" />
							<s:textfield label="Nom" labelposition="top" key="form.lastName" placeholder="Nom" />
							<s:textfield label="Email" labelposition="top" key="form.mail" placeholder="Email" />
							<s:password label="Mot de passe" labelposition="top" key="form.password" placeholder="Mot de passe" />
							<s:password label="Confirmation mot de passe" labelposition="top" key="form.password2" placeholder="Confirmation mot de passe" />
							<s:submit key="Inscription" cssClass="button" />
					</s:form>
</section>

	<c:import url="../layouts/Footer.jsp" />