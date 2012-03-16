<div class="wrap_header">
<header class="header">
	<a href="Home"><img src='img/logo.png' /></a>
	<h1>Système d'authentification de documents</h1>
</header>


<menu label="main_menu" class="main_menu">
	<li class="${ currentMenu == 'Home' ? 'menu_active' : ''}">
		<a href="Home">Accueil</a>
	</li>
	<li class="${ currentMenu == 'Profile' ? 'menu_active' : ''}">
		<a href="Profile">Profil</a>
	</li>
	<li class="${ currentMenu == 'Competence' ? 'menu_active' : ''}"> 
		<a href="Competence">Competence</a>
	</li>
	<li class="${ currentMenu == 'CV' ? 'menu_active' : ''}"> 
		<a href="manageResume">CV</a>
	</li>
	<li class="${ currentMenu == 'EditProfile' ? 'menu_active' : ''}"> 
		<a href="ModifyProfile">Modifier le profil</a>
	</li>
	<li> 
		<a href="Deconnection">Deconnexion</a>
	</li>
</menu>
</div>
