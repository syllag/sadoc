<header class="header">
	<a href="Home"><img src='img/logo.png' /></a>
	<h1>Système d'authentification de documents</h1>
</header>

<menu label="main_menu" class="main_menu">
	<li class="${ currentMenu == 'Home' ? 'menu_active' : ''}">
		<a href="Home">Accueil</a>
	</li>
	<li class="${currentMenu == 'SignIn' ? 'menu_active' : ''}">
		<a href="SignIn">Inscription</a>
	</li>
	<li class="${currentMenu == 'Connect' ? 'menu_active' : ''}">
		<a href="ValidateConnect">Connexion</a>
	</li>
</menu>
