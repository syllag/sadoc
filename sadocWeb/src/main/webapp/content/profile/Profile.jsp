<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	session.setAttribute("currentMenu", "Profile");
%>
<c:import url="../layouts/Header.jsp" />
				
			<section id="content">
				<form method="post" action="ModifyProfile">
					<input type="text" name="firstName" id="firstName" placeholder="Prénom" value="${ firstname }" required /> 
					<input type="text" name="lastName" id="lastName" placeholder="Nom" value="${ name }" required /> 
					<input type="text" name="mail" id="mail" placeholder="email" value="${ mail }" required /> 
					<input type="text" name="adress" id="adress" placeholder="Adresse" value="${ adress }" />
					<input type="text" name="zipCode" id="zipCode" placeholder="Code postal" value="${ zipCode }" />   
					<input type="text" name="town" id="town" placeholder="Ville" value="${ town }" /> 
					<input type="text" name="phone" id="phone" placeholder="03/03/03/03/03" value="${ phone }" />
				</form>
			</section>

<c:import url="../layouts/Footer.jsp" />