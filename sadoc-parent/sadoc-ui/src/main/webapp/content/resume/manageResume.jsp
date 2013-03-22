<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
	session.setAttribute("currentMenu", "CV");
%>

<c:import url="../layouts/Header.jsp" />

<div class="curriculum">
	<h3>Curriculum vitae</h3>
	<span class="genererCv"> <a
		href="<s:url action="CreateResume" />">+ generer CV</a>
	</span>
	<table>
		<c:forEach var="resume" items="${resumes}">
			<tr>
				<td>CV_${resume.id}</td>
				<td><span class="genererCv"> 
				<s:url var="genCV" action="DownloadResume">
					<s:param name="cv">${resume.id}</s:param>
				</s:url>
				<a	href="${genCV}"	title="Télécharger le CV"> 
					<img src="<s:url value="/img/download.png"/>"/>
				</a>
				<s:url var="remCV" action="RemoveResume">
					<s:param name="cv">${resume.id}</s:param>
				</s:url>
				<a href="${remCV}" title="Supprimer le CV">
					<img src="<s:url value="/img/delete.png"/>"/>
				</a>	
					</span></td>
			</tr>
		</c:forEach>
	</table>
</div>

<c:import url="../layouts/Footer.jsp" />