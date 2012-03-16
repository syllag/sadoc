<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="../layouts/Header.jsp" />

<section id="content">
	<h1>Création d'un curriculum vitae</h1>
	<form method="post" action="CreateResume">
		<p>Choisissez les compétences à ajouter:</p>
		<div class="competenceScroll">
			<div class="competence">
				<ul>
					<li><input type="checkbox" name="form.listCompetences" id="listCompetence" value="all"/>Toutes</li>
					<c:forEach var="competence" items="${listCompetences}">
						<li><input type="checkbox" name="form.listCompetences" id="listCompetence" value="${competence.id}" /> ${competence.name}</li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<button type=submit class="button">Créer le CV</button>
	</form>
</section>
		
		
	
<c:import url="../layouts/Footer.jsp" />