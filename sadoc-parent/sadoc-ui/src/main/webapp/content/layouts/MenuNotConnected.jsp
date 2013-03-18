<%@taglib uri="/struts-tags" prefix="s"%> 
<div class="wrap_header">
<header class="header">
	<a href="<s:url action="Home"/>"><img src="<s:url value="/img/logo.png"/>" class="img_header"/></a>
	<h1>Système d'authentification de documents</h1>
</header>

<menu label="main_menu" class="main_menu">
	<li class="${ currentMenu == 'Home' ? 'menu_active' : ''}">
		<a href="<s:url action="Home"/>">Accueil</a>
	</li>
	<li class="${currentMenu == 'SignIn' ? 'menu_active' : ''}">
		<a href="<s:url action="SignIn"/>">Inscription</a>
	</li>
	<li class="${currentMenu == 'Connect' ? 'menu_active' : ''}">
		<a href="<s:url action="ValidateConnect"/>">Connexion</a>
	</li>
</menu>
</div>