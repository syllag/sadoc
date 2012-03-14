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
		<a href="CV">CV</a>
	</li>
	<li class="${ currentMenu == 'EditProfile' ? 'menu_active' : ''}"> 
		<a href="ModifyProfile">Modifier le profil</a>
	</li>
</menu>
