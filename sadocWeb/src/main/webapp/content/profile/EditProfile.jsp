<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	session.setAttribute("currentMenu", "EditProfile");
%>
<c:import url="../layouts/Header.jsp" />
			
	
			<section id="content">
				<form method="post" action="ModifyProfile2">
					<input type="password" name="form.password" id="password" placeholder="Nouveau mot de passe" /> 
					<input type="password" name="form.password2" id="password2" placeholder="Confirmation password" />
					<input type="text" name="form.adress" id="adress" placeholder="Mon adresse" value="${ adress }"  />
					<input type="text" name="form.zipCode" id="zipCode" placeholder="Code postal" value="${ zipCode }"  />   
					<input type="text" name="form.town" id="town" placeholder="Ville" value="${ town }"  /> 
					<input type="text" name="form.phone" id="phone" placeholder="Téléphone" value="${ phone }"  />
					<button type=submit class="button">Mettre à jour</button>
				</form>
			</section>
			
<c:import url="../layouts/Footer.jsp" />