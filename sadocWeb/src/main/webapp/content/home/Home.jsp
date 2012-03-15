<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	session.setAttribute("currentMenu", "Home");
%>
<c:import url="../layouts/Header.jsp" />

		<section id="content">
			<table>
				<tr>
					<td>
						<a href="http://www.univ-artois.fr">
							<img src='img/univ_artois.png' alt="université d'artois" class="icon"/>
						</a>
					</td>
					<td><span class="quotation">SADoc - Système d'authentification de documents</span></td>
				</tr>
			</table>
			<p>
				<h3>Qu'est-ce ?</h3>
				SADoc est, comme son nom l'indique, un système d'authentification des documents
				qui permet de vérifier l'authenticité de ceux-ci. Grâce au plug-in intégré
				à un portfolio, on peut récupérer les fichiers hébergés dessus, les signer numériquement,
				enregistrer leurs signatures dans notre base de données, et pouvoir vérifier
				ultérieurement son authenticité grâce à un QRCode.<br />
				Développé par les deuxième année de Master Informatique Ingénierie Logicielle pour
				Internet, ce projet a principalement été développé en Java EE.
			</p>
		</section>

<c:import url="../layouts/Footer.jsp" />