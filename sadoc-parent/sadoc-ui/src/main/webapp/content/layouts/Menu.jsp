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
	<li class="${ currentMenu == 'Profile' ? 'menu_active' : ''}">
		<a href="<s:url action="Profile"/>">Profil</a>
	</li>
	<li class="${ currentMenu == 'Competence' ? 'menu_active' : ''}"> 
		<a href="<s:url action="Competence"/>">Compétences</a>
	</li>
	<li class="${ currentMenu == 'CV' ? 'menu_active' : ''}"> 
		<a href="<s:url action="ManageResume"/>">CV</a>
	</li>
	<li class="${ currentMenu == 'EditProfile' ? 'menu_active' : ''}"> 
		<a href="<s:url action="ModifyProfile"/>">Modifier le profil</a>
	</li>
	<li> 
		<a href="<s:url action="Deconnection"/>">Déconnexion</a>
	</li>
</menu>
</div>
