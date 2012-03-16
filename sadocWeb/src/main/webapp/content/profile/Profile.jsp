<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	session.setAttribute("currentMenu", "Profile");
%>
<c:import url="../layouts/Header.jsp" />

<section id="content">
	<div class="user">
		<p>${firstname} ${name}</p>
		<p>${ adress }</p>
		<p>
			${ zipCode }
			<c:if test="not empty ${ zipCode }  && not empty ${ town } ">
				,
				</c:if>
			${ town }
		</p>
		<p>${ phone }</p>
		<p>${ mail }</p>
	</div>
	<hr />
	<div class="competence">
		<h3>Compétences</h3>

		<c:forEach var="entry" items="${mapCompetence}">
			<fieldset>
				<legend>${entry.key.name }</legend>

				<p>Niveau : ${entry.key.acronym }</p>
				<table>
					<c:forEach var="document" items="${entry.value }">
						<tr>
							<td>${document.name }</td>
							<td><a href="#" title="Télécharger le document"><img
									src="img/download.png" /> </a>
							</td>
						</tr>
					</c:forEach>
				</table>
			</fieldset>
		</c:forEach>
	</div>
	<hr />
	<div class="curriculum">
		<h3>Curriculum vitae</h3>
		<span class="genererCv"><a href="manageResume" title="Gérer vos CV">Gérer vos CV</a></span>
	</div>
</section>

<c:import url="../layouts/Footer.jsp" />