<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	session.setAttribute("currentMenu", "CV");
%>
	<s:debug/>
<c:import url="../layouts/Header.jsp" />
<section id="content">
	<h1>Création d'un curriculum vitae</h1>

	<form method="post" action="CreateResume">
		<p>Choisissez les compétences à ajouter:</p>
		<div class="competenceScroll">
			<div class="competence">
<!-- 				<ul> -->
<%-- 					<c:forEach var="referentiel" items="${listCompetences}"> --%>
<!-- 						<li><input type="checkbox" name="form.listCompetences" -->
<%-- 							class="mycheckbox" value="${referentiel.id}" checked /> --%>
<%-- 							${referentiel.description} --%>
<!-- 							<ul> -->
<%-- 								<c:forEach var="domaine" items="${referentiel.domaines}"> --%>
<!-- 									<li><input type="checkbox" name="form.listCompetences" -->
<%-- 										class="mycheckbox" value="${domaine.id}" checked /> --%>
<%-- 										${domaine.description}</li> --%>
<%-- 								</c:forEach> --%>
<!-- 							</ul></li> -->
<%-- 					</c:forEach> --%>
<!-- 				</ul> -->
<!-- 				<ul> -->
				
<!-- 				var="referentiel" -->


					<s:iterator var="referentiel" value="arbreCompetences">
						<li>-<s:property value="referentiel.key.name" />
							<ul>
								<s:iterator value="value" >
									<li><s:property value="key.codeDomaine" /></li>
								</s:iterator>
							</ul>
						</li>
					</s:iterator>
				</ul>

			</div>
		</div>
		<button type=submit class="button">Créer le CV</button>
	</form>
</section>



<c:import url="../layouts/Footer.jsp" />