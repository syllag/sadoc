<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	session.setAttribute("currentMenu", "Connect");
%>
<c:import url="../layouts/Header.jsp" />

	<section class="connection">
		<form method="POST" action="ValidateConnect">
			<input type="email" name="connect.email" id="email"
				placeholder="Your email" required /> <input type="password"
				name="connect.password" id="password" placeholder="Your password"
				required />
				<c:if test="${ incorrect == 'ok' }">
					<div class="errorMessage">Password ou mail incorrect</div>
				</c:if>
			<button type=submit class="buttonLogin">LOG IN</button>
			<a href="SignIn"><button class="buttonSignin">SIGN IN</button></a>
  <div class="spacer"> </div>
		</form>
	</section>

<c:import url="../layouts/Footer.jsp" />