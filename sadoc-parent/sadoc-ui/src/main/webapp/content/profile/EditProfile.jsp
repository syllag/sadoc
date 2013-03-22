<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	session.setAttribute("currentMenu", "EditProfile");
%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<c:import url="../layouts/Header.jsp" />
			
	
			<section id="content">
				<s:form action="ModifyProfile2" validate="true">
					<s:actionerror />
					
					
					<s:password label="Mot de passe" labelposition="top" key="form.password" placeholder="Nouveau mot de passe" />
					
					<s:password label="Confirmation du mot de passe" labelposition="top" key="form.password2" placeholder="Confirmation mot de passe" />
					
					<s:textfield label="Adresse" labelposition="top" key="form.address" placeholder="Adresse" value="%{session.owner.address}" />
					
					<s:textfield label="Code postal" labelposition="top" key="form.zipCode" placeholder="Code postal" value="%{session.owner.zipCode}" />
					  
					<s:textfield label="Ville" labelposition="top" key="form.town" placeholder="Ville" value="%{session.owner.town}" />
					
					<s:textfield label="Téléphone" labelposition="top" key="form.phone" placeholder="Téléphone" value="%{session.owner.phone}" />
					<s:submit key="Mettre à jour" cssClass="button" />
				</s:form>
				
				
			</section>
			
<c:import url="../layouts/Footer.jsp" />