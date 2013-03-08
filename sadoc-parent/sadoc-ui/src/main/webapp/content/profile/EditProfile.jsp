<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	session.setAttribute("currentMenu", "EditProfile");
%>
<c:import url="../layouts/Header.jsp" />
			
	
			<section id="content">
				<form method="post" action="ModifyProfile2">
					Mot de passe<br/>
					<input type="password" name="form.password" id="password" placeholder="Nouveau mot de passe" /><br/>
					Confirmation du mot de passe<br/> 
					<input type="password" name="form.password2" id="password2" placeholder="Confirmation mot de passe" /><br/><br/>
					Adresse<br/>
					<input type="text" name="form.adress" id="adress" placeholder="Adresse" value="${ owner.adress }"  /><br/>
					Code postal<br/>
					<input type="text" name="form.zipCode" id="zipCode" placeholder="Code postal" value="${ owner.zipCode }"  /><br/>
					Ville<br/>   
					<input type="text" name="form.town" id="town" placeholder="Ville" value="${ owner.town }"  /><br/>
					Téléphone<br/> 
					<input type="text" name="form.phone" id="phone" placeholder="Téléphone" value="${ owner.phone }"  /><br/>
					<button type=submit class="button">Mettre à jour</button>
				</form>
			</section>
			
<c:import url="../layouts/Footer.jsp" />