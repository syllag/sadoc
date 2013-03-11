<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	session.setAttribute("currentMenu", "Home");
%>
<c:import url="../layouts/Header.jsp" />

<section id="content">
	<table>
		<tr>
			<td><a href="<s:url value="http://www.univ-artois.fr" />"> <img
					src='img/univ_artois.png' alt="université d'artois" class="icon" />
			</a></td>
			<td><span class="quotation">SADoc - Système
					d'authentification de documents</span></td>
		</tr>
	</table>
	<p>
		<img width="300px" src='img/logo_big.png' />
	</p>
</section>

<c:import url="../layouts/Footer.jsp" />