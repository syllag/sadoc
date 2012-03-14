<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	session.setAttribute("currentMenu", "EditProfile");
%>
<c:import url="../layouts/Header.jsp" />
			
	
			<section id="content">
				<form method="post" action="ModifyProfile">
					<input type="password" name="password" id="password" placeholder="Nouveau mot de passe" /> 
					<input type="password" name="password2" id="password2" placeholder="Confirmation password" />
					<input type="text" name="adress" id="adress" placeholder="Mon adresse" value="${ adress }" required />
					<input type="text" name="zipCode" id="zipCode" placeholder="Code postal" value="${ zipCode }" required />   
					<input type="text" name="town" id="town" placeholder="Ville" value="${ town }" required /> 
					<input type="text" name="phone" id="phone" placeholder="Téléphone" value="${ phone }" required />
					<button type=submit class="button">Mettre à jour</button>
				</form>
			</section>
			
<c:import url="../layouts/Footer.jsp" />