<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags" %>	

	
<c:import url="../layouts/Header.jsp" />

	<div class="curriculum">
		<h3>Curriculum vitae</h3> <span class="genererCv">
		<a href="<s:url value="ShowCreateResume" />">+ generer CV</a> </span>
		<table>
			<c:forEach var="resume" items="${resumes}">
				<tr>
					<td>CV_${resume.id}</td>
					<td><span class="genererCv">
					<a href="<s:url value="downloadResume?cv=${resume.id}" />" title="Télécharger le CV"><img
							src="img/download.png" /> </a></span></td>
				</tr>
			</c:forEach>
		</table>
	</div>

<c:import url="../layouts/Footer.jsp" />