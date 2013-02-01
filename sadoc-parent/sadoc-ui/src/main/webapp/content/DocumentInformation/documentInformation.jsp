<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="../layouts/Header.jsp" />


<section id="content2">
	<div class="user">
		<h3>${owner.lastName} ${owner.firstName}</h3>
		<p>${owner.address}</p>
		<p>${owner.town}, ${owner.zipCode}</p>
		<p>${owner.phone}</p>

	</div>
	<hr />
	<p class="auth">Le document a été certifié par SADOC , le
		${document.creationDate}
	<p>
	<hr />

	<div class="infoCompetence">
		<c:forEach var="competence" items="${listCompetences}">
			<p>compétence : ${competence.name}
			<p>
			<p>description compétence : ${competence.description}</p>
		</c:forEach>
	</div>

	<div class="infoDocument">
		<p>Nom du document : ${document.name}</p>
		<c:if test="${null ne document.url}">
			<p>
				<a href="${document.url}">Télécharger le document</a>
			</p>
		</c:if>
	</div>
	<form method="post" action="VerifyDocument"
		enctype="multipart/form-data">
		<input type="file" name="file" id="file" /> <input type="hidden"
			name="id" value="${document.id}" /> <br/>
		<button type=submit class="button">Vérifier le document</button>
		<!-- <input type="submit" value="Vérifier le document"/> -->
	</form>
	<c:if test="${(null ne mail) && (mail eq owner.mail)}">
		<form method="post" action="ModifyUrl">
			<input type="text" name="form.url" id="url"
				placeholder="URL du document" value="${document.url}" /> <input
				type="hidden" name="form.documentId" value="${document.id}" />
			<button type="submit" class="button">Modifier</button>
		</form>
	</c:if>
	<div class="infoP7S">
		<p>
			signature du fichier (fichier *.p7s) : <a href="downloadP7S?sa=${sa}"><button>Télécharger</button></a>
		</p>
		<p>
			L'utilisation du fichier *.p7s requiert le logiciel <a
				href="http://www.adesium.com/index.php?option=com_quickfaq&view=items&cid=1%3Amysign&id=14">mySIGN</a>
		</p>
	</div>
</section>

<c:import url="../layouts/Footer.jsp" />