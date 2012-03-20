<?php  // $Id:  lib.php,v 1.0 2008/04/29 00:00:00 jfruitet Exp $
///////////////////////////////////////////////////////////////////////////
//                                                                       //
// NOTICE OF COPYRIGHT                                                   //
//                                                                       //
// Moodle - Modular Object-Oriented Dynamic Learning Environment         //
//          http://moodle.org                                            //
//                                                                       //
// Copyright (C) 2005 Martin Dougiamas  http://dougiamas.com             //
//                                                                       //
// This program is free software; you can redistribute it and/or modify  //
// it under the terms of the GNU General Public License as published by  //
// the Free Software Foundation; either version 2 of the License, or     //
// (at your option) any later version.                                   //
//                                                                       //
// This program is distributed in the hope that it will be useful,       //
// but WITHOUT ANY WARRANTY; without even the implied warranty of        //
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the         //
// GNU General Public License for more details:                          //
//                                                                       //
//          http://www.gnu.org/copyleft/gpl.html                         //
//                                                                       //
///////////////////////////////////////////////////////////////////////////

/**
 * Library of functions and constants for module referentiel
 * 
 * @author jfruitet
 * @version $Id: lib.php,v 5.4.11 2011/02/07 00:00:00 jfruitet Exp $
 * @package referentiel v 6.0.00 2011/04/01 00:00:00
 **/
 
// 2010/10/18 : configuration
require_once ("lib_config.php");
require_once ("lib_users.php");
require_once ("lib_cron.php");
require_once ("lib_accompagnement.php");

// les constantes suivantes permettent de tuner le fonctionnement du module
// a ne modifier qu'avec précaution

define('TIME_LIMIT', 360);// temps  maximal d'exécution d'un script si PHP ne fonctionne pas en safe_mode

define('EDITOR_ON', 1);// editeur de referentiels simplifié wysiwyg actif (necessite le dossier mod/referentiel/editor sur le serveur)
// define('EDITOR_ON', 0);   // editeur inactif

define('MAXBOITESSELECTION', 6);  // à réduire si le nombre de boites de selection des etudiants
// ne tient pas dans la page sans ascenceur horizontal

define('NOTIFICATION_TACHES', 1); // placer à 0 pour désactiver la notification
define('NOTIFICATION_ACTIVITES', 1); // placer à 0 pour désactiver la notification
define('NOTIFICATION_CERTIFICATS', 1); // placer à 0 pour désactiver la notification

define('NOTIFICATION_TACHES_AUX_REFERENTS', 0); // placer à 1 pour activer la notification de la tâches aux enseignants du cours ; en general inutile
define('NOTIFICATION_JURY', 0); // placer à 1 pour activer la notification la certification ; notification en général inutile
define('NOTIFICATION_AUTEUR_ACTIVITE', 0); // placer à 0 pour désactiver la notification de l'auteur de la declaration d'activite ; notification en général inutile

define('NOTIFICATION_DELAI', 0); // placer à la valeur à 1 pour activer la temporisation de la notification entre le moment ou l'activité
// est validée et celui où elle est notifiée
define('NOTIFICATION_INTERVALLE_JOUR', 2); // 2 jours d'intervalle de temps d'action du cron
// augmenter la valeur pour prendre en compte des évaluatios anciennes
// cela aura pour effet de réactiver des prise en compte d'évaluation par objectifs
// et de relancer des notifications
// surtout utile pour deboguer si le cron ne s'est pas exercé depuis un temps certain

// CONSTANTES  NE PAS MODIFIER
define('TYPE_ACTIVITE', 0);    // Ne pas modifier
define('TYPE_TACHE', 1);       // Ne pas modifier
define('TYPE_CERTIFICAT', 2);  // Ne pas modifier

define('MAXLIGNEGRAPH', 15);  // Nombre de lignes par graphique

// DEBUG ?
// si à 1 le cron devient très bavard :))
define ('REFERENTIEL_DEBUG', 0);    // DEBUG ACTIF
// define ('REFERENTIEL_DEBUG', 1);       // DEBUG INACTIF

// traitement des activites evaluées par objectifs
define ('REFERENTIEL_OUTCOMES', 1);   // placer à 0 pour désactiver le traitement
 
/// Liste des rubriques (non exhaustive)
/// CRON
/// CONFIGURATION
/// ACTIVITES
/// TACHES
/// CERTIFICATS
/// URL

/// FONCTIONS A ECRIRE /////////////////////////////////////////////////////////////////////////


/**
 * Given a course and a time, this module should find recent activity 
 * that has occurred in referentiel activities and print it out. 
 * Return true if there was output, or false is there was none. 
 *
 * @uses $CFG
 * @return boolean
 * @todo Finish documenting this function
 **/
function referentiel_print_recent_activity($course, $isteacher, $timestart) {
    global $CFG;

    return false;  //  True if anything was printed, otherwise false 
}
// ################################### EDITOR

function  referentiel_editor_is_ok(){
// editeur wisiwyg  appele depuis mod.html
// non implanté car en cours de developpement :))
    return EDITOR_ON;
}

// pour suppprimer le probleme des noms de fichiers incorrects
function referentiel_purge_caracteres_incorrects($str){
    $replace_pairs = array("\\" => "","'" => "_", " " => "_");
    return strtr ( $str , $replace_pairs );
}


/**
 * Must return an array of grades for a given instance of this module, 
 * indexed by user.  It also returns a maximum allowed grade.
 * 
 * Example:
 *    $return->grades = array of grades;
 *    $return->maxgrade = maximum allowed grade;
 *
 *    return $return;
 *
 * @param int $referentielid ID of an instance of this module
 * @return mixed Null or object with an array of grades and with the maximum grade
 **/
function referentiel_grades($referentielid) {
// A FAIRE
// renvoie le carnet de notes de l'instance Ã  Moodle, afin que la plate-forme l'intÃ¨gre dans son carnet de notes global 
// pour l'Ã©tudiant. Cette fonction DOIT retourner un combinÃ© de deux tableau associatif : 
// { 'grades' => { userId => array of double }, 'maxgrades' => { userId = > array of double }}.
// Le premier tableau renvoie les notes obtenues, le deuxiÃ¨me renvoie les notes maximales 
// (ex : je renvoie pour l'utilisateur 23 les notes 7/20 et 13/15 :
// { 'grades' => { 23 => (7, 13)}, 'maxgrades' => { 23 => (20, 15)}}
// Un module peut donc renvoyer une sÃ©rie de notes pour chaque Ã©tudiant. 

   return false;
}


/**
 * This function returns if a scale is being used by one referentiel
 * it it has support for grading and scales. Commented code should be
 * modified if necessary. See forum, glossary or journal modules
 * as reference.
 *
 * @param int $referentielid ID of an instance of this module
 * @return mixed
 * @todo Finish documenting this function
 **/
function referentiel_scale_used ($referentielid, $scaleid) {
    $return = false;
    // thows dml exception
    //$rec = $DB->get_record("referentiel", array("id"=>"$referentielid","scale"=>"-$scaleid");
    //
    //if (!empty($scaleid)) {
    //    $return = true;
    //}

    return $return;
}


/**
 * Checks if scale is being used by any instance of refrentiel
 *
 * This is used to find out if scale used anywhere
 * @param $scaleid int
 * @return boolean True if the scale is used by any referentiel
 */
function referentiel_scale_used_anywhere($scaleid) {
global $DB;
/*
    $params = array('grade' => -$scaleid);
    if ($scaleid and $DB->record_exists('referentiel', $params)) {
        return true;
    } else {
        return false;
    }
*/
    return false;
}


/**
 * Must return an array of user records (all referentiel) who are participants
 * for a given instance of referentiel. Must include every user involved
 * in the instance, independient of his role (student, teacher, admin...)
 * See other modules as example.
 *
 * @param int $referentielid ID of an instance of this module
 * @return mixed boolean/array of students
 **/
function referentiel_get_participants($referentielid) {
    return false;
}



/// FONCTIONS UTILITAIRES /////////////////////////////////////////////////////////////////////////


/**
* @param string Y:2008m:09d:26
* @return timestamp
*/
function referentiel_date_special_date($date_special){
	// Y:2008m:09d:26 -> 2008/09/26
	$ladate="";
	$matches=array();
	preg_match("/Y:(\d+)m:(\d+)d:(\d+)/",$date_special,$matches);
	// print_r($matches);
	if (isset($matches[1]) && $matches[1]){
		$ladate=$matches[1];
	    if (isset($matches[2]) && $matches[2]){
			$ladate.='/'.$matches[2];
		    if (isset($matches[3]) && $matches[3]){
				$ladate.='/'.$matches[3];
			}
		}
	}
	return $ladate; 
}

/**
* @param int timestamp
* @return string Y:2008m:09d:26
*/
function referentiel_timestamp_date_special($timestamp){
	// 1222380000 -> Y:2008m:09d:26
	$ladate="Y:".date("Y",$timestamp)."m:".date("m",$timestamp)."d:".date("d",$timestamp);
	return $ladate; 
}

/**
* @param string Y:2008m:09d:26
* @return string Y/m/d
*/
function referentiel_date_special_timestamp($date_special){
	// Y:2008m:09d:26 -> 1222380000
	$ladate="";
	$matches=array();
	preg_match("/Y:(\d+)m:(\d+)d:(\d+)/",$date_special,$matches);
	// print_r($matches);
	if (isset($matches[1]) && $matches[1]){
		$ladate=$matches[1];
	    if (isset($matches[2]) && $matches[2]){
			$ladate.='/'.$matches[2];
		    if (isset($matches[3]) && $matches[3]){
				$ladate.='/'.$matches[3];
			}
		}
	}
	return strtotime($ladate); 
}

/**
 * Given an object containing all the necessary referentiel, 
 * (defined by the form in mod.html) this function 
 * will create a new instance and return the id number 
 * of the new instance.
 *
 * @param object $instance An object from the form in mod.html
 * @return int The id of the newly inserted referentiel record
 **/
function referentiel_add_instance($form) {
global $DB;
// La premiere creation sans saisie d'un domaine, d'une compÃ©tence et d'un item 
	$referentiel_id=0;
    // temp added for debugging
    // echo "DEBUG : ADD INSTANCE CALLED";
    // DEBUG
	// print_object($form);
    // echo "<br />";
	// exit;
			// saisie de l'instance
	if (isset($form) && !empty($form)){
			$referentiel = new object();
			$referentiel->name=$form->name;
			$referentiel->description_instance=$form->description_instance;
			$referentiel->label_domaine=$form->label_domaine;
			$referentiel->label_competence=$form->label_competence;
			$referentiel->label_item=$form->label_item;
		    $referentiel->date_instance = time();
			$referentiel->course=$form->course;
			// configuration
			$referentiel->config=referentiel_initialise_configuration($form, 'config');
			$referentiel->config_impression=referentiel_initialise_configuration($form, 'config_impression');
			
			$referentiel->ref_referentiel=$referentiel_id;
		    // DEBUG
			// print_object($referentiel);
		    // echo "<br />";
			$referentiel_id= $DB->insert_record("referentiel", $referentiel);
	}
	return $referentiel_id;
}

/**
 * Given an object containing all the necessary referentiel, 
 * (defined by the form in mod.html) this function 
 * will update an existing instance with new referentiel.
 *
 * @param object $instance An object from the form in mod.html
 * @return boolean Success/Fail
 **/
function referentiel_update_instance($form) {
global $DB;
$ok=false;	
// DEBUG
// print_object($form);
// echo "<br />";
	if (isset($form->instance) && ($form->instance>0)){
		// echo "<br /> REFERENTIEL : $form->instance\n";
		$referentiel = new object();
		$referentiel->id=$form->instance;		
		$referentiel->name=($form->name);
		$referentiel->description_instance=($form->description_instance);
		$referentiel->label_domaine=($form->label_domaine);
		$referentiel->label_competence=($form->label_competence);
		$referentiel->label_item=($form->label_item);
		$referentiel->date_instance = time();
		$referentiel->course=$form->course;
		$referentiel->config=referentiel_initialise_configuration($form,'config');
		$referentiel->config_impression=referentiel_initialise_configuration($form,'config_impression');

		// $referentiel->ref_referentiel=$form->ref_referentiel;
		// DEBUG
		// print_object($referentiel);
		// echo "<br />";
		$ok=$DB->update_record("referentiel", $referentiel);
	}
	return $ok;
}

/**
 * Given an ID of an instance of this module, 
 * this function will permanently delete the instance 
 * and any activity and certificate and task ank accompagnement
 * that depends on it.
 *
 * @param int $id Id of the module instance
 * @return boolean Success/Failure
 **/
function referentiel_delete_instance($id) {
// La suppression de l'instance ne supprime pas le referentiel associe
// par contre les cativiy=tes, tâches accompagnement et certificats associés sont supprimés

	$ok=true;
	// verifier existence
    if (! $referentiel = $DB->get_record("referentiel", array("id"=>"$id"))) {
        return false;
    }
	// suppression des activites associees
	$activites=referentiel_get_activites_instance($id);
    if ($activites){
		foreach ($activites as $activite){
			referentiel_delete_activity_record($activite->id);
		}
	}
	// suppression des taches associees
	$taches=referentiel_get_tasks_instance($id);
	if ($taches){
		foreach ($taches as $tache){
			referentiel_delete_task_record($tache->id);
		}
	}
	// suppression des certificats associes
	$certificats=referentiel_get_certificats($referentiel->ref_referentiel);
	if ($certificats){
		foreach ($certificats as $certificat){
			referentiel_delete_certificat_record($certificat->id);
		}
	}
	
	// suppression des accompagnements
	$accompagnements=referentiel_get_accompagnements($id);
	if ($accompagnements){
		foreach ($accompagnements as $accompagnement){
			referentiel_delete_accompagnement_record($accompagnement->id);
		}
	}

	
	// suppression du referentiel
    if (! $DB->delete_records("referentiel", array("id" => "$id"))) {
        $ok = $ok && false;
    }
    
	return ($ok);
}


/**
 * Given an object containing all the necessary referentiel, 
 * (defined by the form in mod.html) this function 
 * will update instance and return true or false
 *
 * @param object $form An object from the form in edit.html
 * @return true false
 **/
function referentiel_associe_referentiel_instance($form){
global $DB;
// importation ou selection ou creation
	if (isset($form->instance) && ($form->instance)
		&& isset($form->new_referentiel_id) && ($form->new_referentiel_id)){
		// id referentiel doit Ãªtre numerique
		$referentiel_id = intval(trim($form->instance));
		$referentiel_referentiel_id = intval(trim($form->new_referentiel_id));
		$referentiel = referentiel_get_referentiel($referentiel_id);
		$referentiel->name=($referentiel->name);
		$referentiel->description_instance=($referentiel->description_instance);
		$referentiel->label_domaine=($referentiel->label_domaine);
		$referentiel->label_competence=($referentiel->label_competence);
		$referentiel->label_item=($referentiel->label_item);
		$referentiel->ref_referentiel=$referentiel_referentiel_id;
		
		// DEBUG
		// echo "<br />DEBUG :: lib.php :: 152 :: referentiel_associe_referentiel_instance()<br />\n";
		// print_object($referentiel);
		// echo "<br />";
		$ok=$DB->update_record("referentiel", $referentiel);
		return $ok;
	}
	return 0;
}

/**
 * Given an object containing referentiel id, 
 * will set referentiel_id to 0
 *
 * @param id 
 * @return 0
 **/
function referentiel_de_associe_referentiel_instance($id){
global $DB;
// suppression de la reference vers un referentiel_referentiel
	if (isset($id) && ($id)){
		// id referentiel doit Ãªtre numerique
		$id = intval(trim($id));
		$referentiel = referentiel_get_referentiel($id);
		$referentiel->name=($referentiel->name);
		$referentiel->description_instance=($referentiel->description_instance);
		$referentiel->label_domaine=($referentiel->label_domaine);
		$referentiel->label_competence=($referentiel->label_competence);
		$referentiel->label_item=($referentiel->label_item);		
		$referentiel->ref_referentiel=0;
		// DEBUG
		// print_object($referentiel);
		// echo "<br />";
		return ($DB->update_record("referentiel", $referentiel));
	}
	return 0;
}

/***
TABLES referentiel_referentiel
*/


/**
 * Given an object containing all the necessary referentiel, 
 * (defined by the form in pass.html) this function
 * checks the md5 pass
 * 
 * @return int The boolean
 **/
function referentiel_check_pass($referentiel_referentiel, $pass){
//	
	if (!empty($pass)){
		$pass=md5($pass);
		if (isset($referentiel_referentiel->pass_referentiel) && ($referentiel_referentiel->pass_referentiel!='')){
			return ($referentiel_referentiel->pass_referentiel==$pass);
		}
		else{
			return 1;
		}
	}
    return 0;
}

/**
 * Given an object containing all the necessary referentiel,
 * (defined by the form in pass.html) this function
 * set the md5 pass
 *
 * @return int The boolean
 **/
 function referentiel_set_pass($referentiel_referentiel_id, $pass){
 global $DB;
// met à jour le mot de passe
	if (!empty($pass)){
		// MD5
		$pass_referentiel=md5($pass);
        //  sauvegarde
    	$ok=false;
        if (!empty($referentiel_referentiel_id) && !empty($pass_referentiel)){
            $ok=$DB->set_field('referentiel_referentiel','pass_referentiel',"$pass_referentiel", array("id" => "$referentiel_referentiel_id"));
	    }
        if ($ok) return 1;
	}
	return 0;
}

/**
 * Given an object containing all the necessary referentiel, 
 * (defined by the form in add.html) this function 
 * will create a new instance and return the id number 
 * of the new instance.
 *
 * @param object $instance An object from the form in add.html
 * @return int The id of the newly inserted referentiel record
 **/
function referentiel_add_referentiel_domaines($form) {
global $DB;
global $USER;
// La premiere creation permet aussi la saisie d'un domaine, d'une compÃ©tence et d'un item 
	$referentiel_referentiel_id=0;
    // temp added for debugging
    // echo "<br />DEBUG :: lib.php :: 196 :: ADD INSTANCE CALLED";
    // DEBUG
	// print_object($form);
    // echo "<br />";
	// exit;
	// saisie d'un referentiel
	if (isset($form->name) && ($form->name!="") 
		&& isset($form->code_referentiel) && ($form->code_referentiel!="")){
		// creer
		$referentiel_referentiel = new object();
		$referentiel_referentiel->name=($form->name);
		$referentiel_referentiel->code_referentiel=($form->code_referentiel);
		$referentiel_referentiel->description_referentiel=($form->description_referentiel);
		$referentiel_referentiel->url_referentiel=($form->url_referentiel);
		$referentiel_referentiel->seuil_certificat=$form->seuil_certificat;
		$referentiel_referentiel->nb_domaines=$form->nb_domaines;	
		$referentiel_referentiel->liste_codes_competence=($form->liste_codes_competence);
		$referentiel_referentiel->liste_empreintes_competence=$form->liste_empreintes_competence;
		// Modif JF 2009/10/16
		if (isset($form->liste_poids_competence)){
			$referentiel_referentiel->liste_poids_competence=$form->liste_poids_competence;
		}
		else{
			$referentiel_referentiel->liste_poids_competence='';
		}
		
		$referentiel_referentiel->timemodified = time();
		if (isset($form->local) && ($form->local!=0) && isset($form->course) && ($form->course!=0)){
			$referentiel_referentiel->local=$form->course;
		}
		else{
			$referentiel_referentiel->local=0;
		}
		$referentiel_referentiel->logo_referentiel = $form->logo_referentiel;


		// traitements speciaux
		if (isset($form->mail_auteur_referentiel) && ($form->mail_auteur_referentiel!='')){
			$referentiel_referentiel->mail_auteur_referentiel=$form->mail_auteur_referentiel;
		}
		else{
			// Modif JF 2009/10/16
			if (isset($USER->email) && ($USER->email!='')){
				$referentiel_referentiel->mail_auteur_referentiel=$USER->email;
			}
			else{
				$referentiel_referentiel->mail_auteur_referentiel='';
			}
		}
		if (isset($form->cle_referentiel) && (trim($form->cle_referentiel)!='')){
			$referentiel_referentiel->cle_referentiel=$form->cle_referentiel;	
		}
		else{
			// Modif JF 2009/10/16
			if (isset($USER->email) && ($USER->email!='')){
				// MD5
				$referentiel_referentiel->cle_referentiel=md5($USER->email.$referentiel_referentiel->code_referentiel);
			}
			else{
				$referentiel_referentiel->cle_referentiel='';
			}
		}
		// Modif JF 2009/10/16
		if (isset($form->old_pass_referentiel)){ // mot de passe stocke au format Crypte MD5()
			$referentiel_referentiel->old_pass_referentiel=$form->old_pass_referentiel;	
		}
		else{
			$referentiel_referentiel->old_pass_referentiel='';	
		}
		if (isset($form->pass_referentiel) && (trim($form->pass_referentiel)!='')){ // mot de passe changÃ©
			// MD5
			$referentiel_referentiel->pass_referentiel=md5($form->pass_referentiel);
		}
		
		
	    // DEBUG
	    // echo "<br />DEBUG :: lib.php :: 221";		
		// print_object($referentiel_referentiel);
	    // echo "<br />";
		
		$referentiel_referentiel_id = $DB->insert_record("referentiel_referentiel", $referentiel_referentiel);
    	// echo "REFERENTIEL ID : $referentiel_referentiel_id<br />";
		
		if ($referentiel_referentiel_id>0){
			// saisie de l'instance
			$referentiel = new object();
			$referentiel->name=($form->name_instance);
			$referentiel->description_instance=($form->description_instance);
			$referentiel->label_domaine=($form->label_domaine);
			$referentiel->label_competence=($form->label_competence);
			$referentiel->label_item=($form->label_item);
		    $referentiel->date_instance = time();
			$referentiel->course=$form->course;
			$referentiel->ref_referentiel=$referentiel_referentiel_id;
		    // DEBUG
			// echo "<br />DEBUG :: lib.php :: 240";
			// print_object($referentiel);
		    // echo "<br />";
			$referentiel_id= $DB->insert_record("referentiel", $referentiel);
				
			// saisie du domaine
			$domaine = new object();
			$domaine->ref_referentiel=$referentiel_referentiel_id;
			$domaine->code_domaine=$form->code_domaine;
			$domaine->description_domaine=$form->description_domaine;
			$domaine->num_domaine=$form->num_domaine;
			$domaine->nb_competences=$form->nb_competences;
		    // DEBUG
			// echo "<br />DEBUG :: lib.php :: 253";
			// print_object($domaine);
			// echo "<br />";
			
			$domaine_id = $DB->insert_record("referentiel_domaine", $domaine);
    		// echo "DOMAINE ID / $domaine_id<br />";
			if ($domaine_id>0){
				$competence = new object();
				$competence->ref_domaine=$domaine_id;
				$competence->code_competence=($form->code_competence);
				$competence->description_competence=($form->description_competence);
				$competence->num_competence=$form->num_competence;
				$competence->nb_item_competences=$form->nb_item_competences;
				
    			// DEBUG
				// echo "<br />DEBUG :: lib.php :: 268";
				// print_object($competence);
    			// echo "<br />";
				
				$competence_id = $DB->insert_record("referentiel_competence", $competence);
		    	// echo "COMPETENCE ID / $competence_id<br />";
				if ($competence_id>0){
					$item = new object();
					$item->ref_referentiel=$referentiel_referentiel_id;
					$item->ref_competence=$competence_id;
					$item->code_item=($form->code_item);
					$item->description_item=($form->description_item);
					$item->type_item=($form->type_item);
					$item->poids_item=$form->poids_item;
					$item->empreinte_item=$form->empreinte_item;
					$item->num_item=$form->num_item;
    				// DEBUG
					// echo "<br />DEBUG :: lib.php :: 283";
					// print_object($item);
    				// echo "<br />";
					
					$item_id=$DB->insert_record("referentiel_item_competence", $item);
				    // echo "ITEM ID / $item_id<br />";	
				}
			}
		}
		if ($referentiel_referentiel_id>0){
			// MODIF JF 2009/10/16
			$liste_codes_competence=referentiel_new_liste_codes_competence($referentiel_referentiel_id);
			referentiel_set_liste_codes_competence($referentiel_referentiel_id, $liste_codes_competence);
			$liste_empreintes_competence=referentiel_new_liste_empreintes_competence($referentiel_referentiel_id);
			referentiel_set_liste_empreintes_competence($referentiel_referentiel_id, $liste_empreintes_competence);		
			$liste_poids_competence=referentiel_new_liste_poids_competence($referentiel_referentiel_id);
			referentiel_set_liste_poids_competence($referentiel_referentiel_id, $liste_poids_competence);
		}
    	# May have to add extra stuff in here #
	}
	else{
		return get_string('erreur_creation','referentiel');
		// "Name and code mandatory";
	}
	return $referentiel_referentiel_id;
}


/**
 * Given an object containing all the necessary referentiel, 
 * (defined by the form in edit.html) this function 
 * will update an existing instance .
 *
 * @param object $instance An object from the form in edit.html
 * @return boolean Success/Fail
 **/
function referentiel_update_referentiel_domaines($form) {
global $USER;
global $DB;
	$ok=true;	
	// DEBUG
	// echo "<br />DEBUG :: lib.php :: 446 <br />";
	// print_object($form);
	// echo "<br />";
	if (isset($form->referentiel_id) && ($form->referentiel_id>0)){
		if (isset($form->action) && ($form->action=="modifierreferentiel")){
			// referentiel
			$referentiel_referentiel = new object();
			$referentiel_referentiel->name=($form->name);
			$referentiel_referentiel->code_referentiel=($form->code_referentiel);
			$referentiel_referentiel->description_referentiel=($form->description_referentiel);
			$referentiel_referentiel->url_referentiel=($form->url_referentiel);
			$referentiel_referentiel->seuil_certificat=($form->seuil_certificat);
    		$referentiel_referentiel->timemodified = time();
			$referentiel_referentiel->nb_domaines=$form->nb_domaines;
			$referentiel_referentiel->liste_codes_competence=$form->liste_codes_competence;
			$referentiel_referentiel->liste_empreintes_competence=$form->liste_empreintes_competence;
			// Modif JF 2009/10/16
			if (isset($form->liste_poids_competence)){
				$referentiel_referentiel->liste_poids_competence=$form->liste_poids_competence;
			}
			else{
				$referentiel_referentiel->liste_poids_competence='';
			}
			$referentiel_referentiel->mail_auteur_referentiel=$form->mail_auteur_referentiel;
			$referentiel_referentiel->cle_referentiel=$form->cle_referentiel;	
			$referentiel_referentiel->pass_referentiel=$form->old_pass_referentiel;	// sera modifie par traitement special

			// traitements speciaux
			if (isset($form->mail_auteur_referentiel) && ($form->mail_auteur_referentiel!='')){
				$referentiel_referentiel->mail_auteur_referentiel=$form->mail_auteur_referentiel;
			}
			else{
				// Modif JF 2009/10/16
				if (isset($USER->email) && ($USER->email!='')){
					$referentiel_referentiel->mail_auteur_referentiel=$USER->email;
				}
				else{
					$referentiel_referentiel->mail_auteur_referentiel='';
				}
			}
			
			if (isset($form->cle_referentiel) && (trim($form->cle_referentiel)!='')){
				$referentiel_referentiel->cle_referentiel=$form->cle_referentiel;	
			}
			else{
				// Modif JF 2009/10/16
				if (isset($USER->email) && ($USER->email!='')){
					// MD5
					$referentiel_referentiel->cle_referentiel=md5($USER->email.$referentiel_referentiel->code_referentiel);
				}
				else{
					$referentiel_referentiel->cle_referentiel='';
				}
			}
			// Modif JF 2009/10/16
			$referentiel_referentiel->pass_referentiel=$form->old_pass_referentiel;	// sera modifie par traitement special
			if ($form->pass_referentiel!=''){ // le pass a Ã©tÃ© ressaisi
				// MD5
				$referentiel_referentiel->pass_referentiel=md5($form->pass_referentiel);
			}
			
			
			// local ou global
			if (isset($form->local) && ($form->local!=0) && isset($form->course) && ($form->course!=0))
				$referentiel_referentiel->local=$form->course;
			else
				$referentiel_referentiel->local=0;
			
			$referentiel_referentiel->timemodified = time();
    		$referentiel_referentiel->id = $form->referentiel_id;
			$referentiel_referentiel->logo_referentiel = $form->logo_referentiel;
			
	    	// DEBUG
		    // echo "<br />";		
			// print_object($referentiel_referentiel);
	    	// echo "<br />";
			// exit;
			$ok=$DB->update_record("referentiel_referentiel", $referentiel_referentiel);
		}
		else if (isset($form->action) && ($form->action=="completerreferentiel")){
			if (isset($form->domaine_id) && is_array($form->domaine_id)){
				for ($i=0; $i<count($form->domaine_id); $i++){
					$domaine = new object();
					$domaine->id=$form->domaine_id[$i];
					$domaine->ref_referentiel=$form->referentiel_id;
					$domaine->code_domaine=($form->code_domaine[$i]);
					$domaine->description_domaine=($form->description_domaine[$i]);
					$domaine->num_domaine=$form->num_domaine[$i];
					$domaine->nb_competences=$form->nb_competences[$i];
					
					if (!$DB->update_record("referentiel_domaine", $domaine)){
						// DEBUG
						// print_object($domaine);
						// echo "<br />ERREUR DE MISE A JOUR...";
						$ok=$ok && false;
						// exit;
					}
					else{
						// DEBUG
						// print_object($domaine);
						// echo "<br />MISE A JOUR DOMAINE...";
						$ok=$ok && true;
					}
				}
			}
			// NOUVEAU DOMAINE
			if (isset($form->new_code_domaine) && is_array($form->new_code_domaine)){
				for ($i=0; $i<count($form->new_code_domaine); $i++){
					$domaine = new object();
					$domaine->ref_referentiel=$form->referentiel_id;
					$domaine->code_domaine=($form->new_code_domaine[$i]);
					$domaine->description_domaine=($form->new_description_domaine[$i]);
					$domaine->num_domaine=$form->new_num_domaine[$i];
					$domaine->nb_competences=$form->new_nb_competences[$i];
					// DEBUG
					// print_object($domaine);
					// echo "<br />";
					$new_domaine_id = $DB->insert_record("referentiel_domaine", $domaine);
					$ok=$ok && ($new_domaine_id>0); 
    				// echo "DOMAINE ID / $new_domaine_id<br />";
				}
			}
			// COMPETENCES
			if (isset($form->competence_id) && is_array($form->competence_id)){
				for ($i=0; $i<count($form->competence_id); $i++){
					$competence = new object();
					$competence->id=$form->competence_id[$i];
					$competence->code_competence=($form->code_competence[$i]);
					$competence->description_competence=($form->description_competence[$i]);
					$competence->ref_domaine=$form->ref_domaine[$i];
					$competence->num_competence=$form->num_competence[$i];
					$competence->nb_item_competences=$form->nb_item_competences[$i];
					// DEBUG
					// print_object($competence);
					if (!$DB->update_record("referentiel_competence", $competence)){
						// echo "<br />ERREUR DE MISE A JOUR...";
						$ok=$ok && false;
						// exit;
					}
					else{
						// echo "<br />MISE A JOUR COMPETENCES...";
						$ok=$ok && true;
					}
				}
			}
			// NOUVElle competence
			if (isset($form->new_code_competence) && is_array($form->new_code_competence)){
				for ($i=0; $i<count($form->new_code_competence); $i++){
					$competence = new object();
					$competence->code_competence=($form->new_code_competence[$i]);
					$competence->description_competence=($form->new_description_competence[$i]);
					$competence->ref_domaine=$form->new_ref_domaine[$i];
					$competence->num_competence=$form->new_num_competence[$i];
					$competence->nb_item_competences=$form->new_nb_item_competences[$i];
					// DEBUG
					// print_object($competence);
					// echo "<br />";
					$new_competence_id = $DB->insert_record("referentiel_competence", $competence);
					$ok=$ok && ($new_competence_id>0); 
   					// echo "competence ID / $new_competence_id<br />";
				}
			}
			// ITEM COMPETENCES
			if (isset($form->item_id) && is_array($form->item_id)){
				for ($i=0; $i<count($form->item_id); $i++){
					$item = new object();
					$item->id=$form->item_id[$i];
					$item->ref_referentiel=$form->referentiel_id;
					$item->ref_competence=$form->ref_competence[$i];
					$item->code_item=($form->code_item[$i]);
					$item->description_item=($form->description_item[$i]);
					$item->num_item=$form->num_item[$i];
					$item->type_item=$form->type_item[$i];
					$item->poids_item=$form->poids_item[$i];
					$item->empreinte_item=$form->empreinte_item[$i];
					
					// DEBUG
					// print_object($item);
					// echo "<br />";
					if (!$DB->update_record("referentiel_item_competence", $item)){
						// echo "<br />ERREUR DE MISE A JOUR ITEM COMPETENCE...";
						$ok=$ok && false;
						// exit;
					}
					else {
						// echo "<br />MISE A JOUR ITEM COMPETENCES...";
						$ok=$ok && true;
					}
				}
			}
			// NOUVEL item
			if (isset($form->new_code_item) && is_array($form->new_code_item)){
				for ($i=0; $i<count($form->new_code_item); $i++){
					$item = new object();
					$item->ref_referentiel=$form->referentiel_id;
					$item->ref_competence=$form->new_ref_competence[$i];
					$item->code_item=($form->new_code_item[$i]);
					$item->description_item=($form->new_description_item[$i]);
					$item->num_item=$form->new_num_item[$i];
					$item->type_item=($form->new_type_item[$i]);
					$item->poids_item=$form->new_poids_item[$i];
					$item->empreinte_item=$form->new_empreinte_item[$i];
					
					// DEBUG
					// print_object($item);
					// echo "<br />";
					$new_item_id = $DB->insert_record("referentiel_item_competence", $item);
					$ok=$ok && ($new_item_id>0); 
   					// echo "item ID / $new_item_id<br />";
				}
			}
			
			// Mise Ã  jour de la liste de competences
			$liste_codes_competence=referentiel_new_liste_codes_competence($form->referentiel_id);
			// echo "<br />LISTE_CODES_COMPETENCE : $liste_codes_competence\n";
			referentiel_set_liste_codes_competence($form->referentiel_id, $liste_codes_competence);
			$liste_empreintes_competence=referentiel_new_liste_empreintes_competence($form->referentiel_id);
			// echo "<br />LISTE_empreintes_COMPETENCE : $liste_empreintes_competence\n";
			referentiel_set_liste_empreintes_competence($form->referentiel_id, $liste_empreintes_competence);
			// Modif JF 2009/10/16
			$liste_poids_competence=referentiel_new_liste_poids_competence($form->referentiel_id);
			referentiel_set_liste_poids_competence($form->referentiel_id, $liste_poids_competence);
		}
	}
	return $ok;
}



/**
 * Given an object containing all the necessary referentiel, 
 * (defined by the form) this function 
 * will create a new instance and return the id number 
 * of the new instance.
 *
 * @param object $form An object
 * @return int The id of the newly inserted referentiel record
 **/
function referentiel_add_referentiel($form) {
global $USER;
global $DB;
// Creer un referentiel sans domaine ni competence ni item
    // temp added for debugging
    // echo "<br />DEBUG : ADD REFERENTIEL CALLED :: lib.php Ligne 633";
    // DEBUG
	// print_object($form);
    // echo "<br />";
	
	// referentiel
	$referentiel = new object();
	$referentiel->name=($form->name);
	$referentiel->code_referentiel=($form->code_referentiel);
	$referentiel->description_referentiel=($form->description_referentiel);
	$referentiel->url_referentiel=($form->url_referentiel);
	$referentiel->seuil_certificat=$form->seuil_certificat;
	$referentiel->nb_domaines=$form->nb_domaines;	
	$referentiel->liste_codes_competence=($form->liste_codes_competence);
    $referentiel->timemodified = time();
	$referentiel->liste_empreintes_competence=$form->liste_empreintes_competence;
	// Modif JF 2009/10/16
	if (isset($form->liste_poids_competence)){
		$referentiel->liste_poids_competence=$form->liste_poids_competence;
	}
	else{
		$referentiel->liste_poids_competence='';
	}		
	$referentiel->logo_referentiel=$form->logo_referentiel;		
	// local ou global
	if (isset($form->local) && ($form->local!=0) && isset($form->course) && ($form->course!=0))
		$referentiel->local=$form->course;
	else
		$referentiel->local=0;

	// traitements speciaux
	if (!isset($form->mail_auteur_referentiel)){
		$form->mail_auteur_referentiel='';
	}

	if (!isset($form->cle_referentiel)){
		$form->cle_referentiel='';
	}
	
	if (!isset($form->old_pass_referentiel)){
		$form->old_pass_referentiel='';
	}
	if (!isset($form->pass_referentiel)){
		$form->pass_referentiel='';
	}
	
	if ($form->mail_auteur_referentiel==''){
		if (isset($USER->id)  && ($USER->id>0)){
			// mail auteur
			$referentiel_referentiel->mail_auteur_referentiel=referentiel_get_user_mail($USER->id);
		}
		else{
			$referentiel_referentiel->mail_auteur_referentiel='';
		}	
	}

	if (($form->cle_referentiel=='') && ($form->mail_auteur_referentiel!='')){
		// MD5
		$referentiel_referentiel->cle_referentiel=md5($referentiel_referentiel->mail_auteur_referentiel.$form->code_referentiel);
	}
	else{
		$referentiel_referentiel->cle_referentiel='';
	}
	if ($form->pass_referentiel!=''){
		// MD5
		$referentiel_referentiel->pass_referentiel=md5($form->pass_referentiel);
	}
	else{
		$referentiel_referentiel->pass_referentiel=$form->old_pass_referentiel; // archive md5()
	}


    // DEBUG
    // echo "<br />DEBUG :: lib.php Ligne 658";	
	// print_object($referentiel);
    // echo "<br />";
	
	$new_referentiel_id= $DB->insert_record("referentiel_referentiel", $referentiel);
    // echo "REFERENTIEL ID / $referentiel_id<br />";
	
	return $new_referentiel_id;
}

/**
 * Given an object containing all the necessary referentiel, 
 * (defined by the form in mod.html) this function 
 * will update an instance and return true
 *
 * @param object $form An object from the form in mod.html
 * @return boolean 
 **/
function referentiel_update_referentiel($form) {
global $DB;
// $form : formulaire
	// DEBUG
	// echo "<br />DEBUG lib.php Ligne 676";
	// print_object($form);
	// echo "<br />";
	$ok=false;
	if (isset($form->referentiel_id) && ($form->referentiel_id>0)){
		// referentiel
		$referentiel = new object();
		$referentiel->name=($form->name);
		$referentiel->code_referentiel=($form->code_referentiel);
		$referentiel->description_referentiel=($form->description_referentiel);
		$referentiel->url_referentiel=($form->url_referentiel);
		$referentiel->seuil_certificat=$form->seuil_certificat;
    	$referentiel->timemodified = time();
		$referentiel->nb_domaines=$form->nb_domaines;
		$referentiel->liste_codes_competence=$form->liste_codes_competence;
		$referentiel->liste_empreintes_competence=$form->liste_empreintes_competence;

			// Modif JF 2009/10/16
	if (isset($form->liste_poids_competence)){
		$referentiel->liste_poids_competence=$form->liste_poids_competence;
	}
	else{
		$referentiel->liste_poids_competence='';
	}		
	if (isset($form->logo_referentiel)){
		$referentiel->logo_referentiel=$form->logo_referentiel;		
	}
	else{
		$referentiel->logo_referentiel='';
	}
	
	// local ou global
	if (isset($form->local) && ($form->local!=0) && isset($form->course) && ($form->course!=0))
		$referentiel->local=$form->course;
	else
		$referentiel->local=0;

	// traitements speciaux
	if (!isset($form->mail_auteur_referentiel)){
		$form->mail_auteur_referentiel='';
	}

	if (!isset($form->cle_referentiel)){
		$form->cle_referentiel='';
	}
	
	if (!isset($form->old_pass_referentiel)){
		$form->old_pass_referentiel='';
	}
	if (!isset($form->pass_referentiel)){
		$form->pass_referentiel='';
	}
	
	if ($form->mail_auteur_referentiel==''){
		if (isset($USER->id)  && ($USER->id>0)){
			// mail auteur
			$referentiel_referentiel->mail_auteur_referentiel=referentiel_get_user_mail($USER->id);
		}
		else{
			$referentiel_referentiel->mail_auteur_referentiel='';
		}	
	}

	if (($form->cle_referentiel=='') && ($form->mail_auteur_referentiel!='')){
		// MD5
		$referentiel_referentiel->cle_referentiel=md5($referentiel_referentiel->mail_auteur_referentiel.$form->code_referentiel);
	}
	else{
		$referentiel_referentiel->cle_referentiel='';
	}
	if ($form->pass_referentiel!=''){
		// MD5
		$referentiel_referentiel->pass_referentiel=md5($form->pass_referentiel);
	}
	else{
		if (isset($form->old_pass_referentiel)){
			$referentiel_referentiel->pass_referentiel=$form->old_pass_referentiel; // archive md5()
		}
		else{
			$referentiel_referentiel->pass_referentiel='';
		}
	}


		$referentiel->timemodified = time();
    	$referentiel->id = $form->referentiel_id;
	    // DEBUG
	    // echo "<br />";		
		// print_object($referentiel);
	    // echo "<br />";
		if (!$DB->update_record("referentiel_referentiel", $referentiel)){
			// echo "<br />ERREUR DE MISE A JOUR...";
			$ok=false;
		}
		else {
			$ok=true;
		}
    }
	// DEBUG
	// exit;
    return $ok;
}

/**
 * Given an object containing all the necessary referentiel, 
 * (defined by the form in mod.html) this function 
 * will update an existing instance with new referentiel.
 *
 * @param object $instance An object from the form in mod.html
 * @return boolean Success/Fail
 **/
function referentiel_update_domaine($form) {
global $DB;
	$ok=false;	
	// DEBUG
	// echo "<br />DEBUG :: lib.php :: 652 <br />\n";
	// print_object($form);
	// echo "<br />";

	if (isset($form->domaine_id) && ($form->domaine_id>0)){
			$domaine = new object();
			$domaine->id=$form->domaine_id;
			$domaine->ref_referentiel=$form->instance;
			$domaine->code_domaine=($form->code_domaine);
			$domaine->description_domaine=($form->description_domaine);
			$domaine->num_domaine=$form->num_domaine;
			$domaine->nb_competences=$form->nb_competences;
			if (!$DB->update_record("referentiel_domaine", $domaine)){
				// DEBUG
				// print_object($domaine);
				// echo "<br />ERREUR DE MISE A JOUR...";
				$ok=false;
				// exit;
			}
			else{
				// DEBUG
				// print_object($domaine);
				// echo "<br />MISE A JOUR DOMAINE...";
				$ok=true;
			}
	}

	return $ok;
}

/**
 * Given an object containing all the necessary referentiel, 
 * (defined by the form in mod.html) this function 
 * will add an existing instance with new domaine.
 *
 * @param object $instance An object from the form in mod.html
 * @return new_domaine_id
 **/
function referentiel_add_domaine($form) {
global $DB;
	$new_domaine_id=0;	
    // temp added for debugging
    // echo "<br />DEBUG : ADD DOMAINE CALLED";
    // DEBUG
	// print_object($form);
    // echo "<br />";

		// NOUVEAU DOMAINE
		if (isset($form->new_code_domaine) && ($form->new_code_domaine!="")){
			$domaine = new object();
			$domaine->ref_referentiel=$form->instance;
			$domaine->code_domaine=$form->new_code_domaine;
			$domaine->description_domaine=($form->new_description_domaine);
			$domaine->num_domaine=$form->new_num_domaine;
			$domaine->nb_competences=$form->new_nb_competences;
			// DEBUG
			// print_object($domaine);
			// echo "<br />";
			$new_domaine_id = $DB->insert_record("referentiel_domaine", $domaine); 
    		// echo "DOMAINE ID / $new_domaine_id<br />";
		}

	return $new_domaine_id; 
}

/**
 * Given a domain id, 
 * this function will delete this domain.
 *
 * @param int id
 * @return boolean 
 **/
function referentiel_delete_domaine($domaine_id){
// suppression
$ok_domaine=true;
$ok_competence=true;
$ok_item=true;
    # Delete any dependent records here #
	// Competences
	if ($competences = $DB->get_records("referentiel_competence", array("ref_domaine" => "$domaine_id"))) {
		// DEBUG
		// print_object($competences);
		// echo "<br />";
		// Item
		foreach ($competences as $competence){
			if ($items = $DB->get_records("referentiel_item_competence", array("ref_competence" => "$competence->id"))) {
				// DEBUG
				// print_object($items);
				// echo "<br />";
				foreach ($items as $item){
					// suppression
					$ok_item=$ok_item && $DB->delete_records("referentiel_item_competence", array("id" => "$item->id"));
				}
			}	
			$ok_competence=$ok_competence && $DB->delete_records("referentiel_competence", array("id" => "$competence->id"));
		}
	}
	// suppression
	$ok_domaine=$ok_domaine && $DB->delete_records("referentiel_domaine", array("id" => "$domaine_id"));
	// Mise Ã  jour de la liste de competences dans le referentiel_referentiel associe
    return ($ok_domaine && $ok_competence && $ok_item);
}


/**
 * Given a domain id, 
 * this function will delete this domain.
 *
 * @param int id
 * @return boolean 
 **/
function referentiel_supprime_domaine($domaine_id){
// suppression avec mise a jour du nombre de domaines dans le referentiel
global $DB;
	if (!$domaine_id){
		return false;
	}
	$ok=false;
	// suppression du domaine avec mise a jour dans le referentiel associe
    $params = array("id" => "$domaine_id");
    $sql="SELECT ref_referentiel FROM {referentiel_domaine} WHERE id=:id";
	$ref_referentiel = $DB->get_record_sql($sql, $params);
	$ok=referentiel_delete_domaine($domaine_id);
	
	// mise a jour du referentiel
	if ($ok && $ref_referentiel){
	    $params2=array("refid" => "$ref_referentiel->ref_referentiel");
        $sql2="SELECT * FROM {referentiel_referentiel} WHERE id=:refid";
		$r_record=$DB->get_record_sql($sql2, $params2);
		if ($r_record){
			$referentiel = new object();
			$referentiel->id=$r_record->id;
			$referentiel->name=($r_record->name);
			$referentiel->code_referentiel=($r_record->code_referentiel);
			$referentiel->description_referentiel=($r_record->description_referentiel);
			$referentiel->url_referentiel=($r_record->url_referentiel);
			$referentiel->seuil_certificat=$r_record->seuil_certificat;
    		$referentiel->timemodified = time();
			$referentiel->nb_domaines=$r_record->nb_domaines-1;
			$referentiel->logo_referentiel=$r_record->logo_referentiel;		
			$referentiel->cle_referentiel=$r_record->cle_referentiel;
			$referentiel->local=$r_record->local;
			$referentiel->liste_codes_competence=referentiel_new_liste_codes_competence($r_record->id);
			$referentiel->liste_empreintes_competence=referentiel_new_liste_empreintes_competence($r_record->id);
			// Modif JF 2009/10/16
			$referentiel->liste_poids_competence=referentiel_new_liste_poids_competence($r_record->id);
			
			$DB->update_record("referentiel_referentiel", $referentiel);
		}
	}
	
	return $ok;
}


/**
 * Given an object containing all the necessary referentiel, 
 * (defined by the form in mod.html) this function 
 * will add an existing instance with new domaine.
 *
 * @param object $instance An object from the form in mod.html
 * @return new_competence_id
 **/
function referentiel_add_competence($form) {
global $DB;
	$new_competence_id=0;	
    // temp added for debugging
    // echo "<br />DEBUG : ADD COMPETENCE CALLED";
    // DEBUG
	// print_object($form);
    // echo "<br />";

		// NOUVElle competence
		if (isset($form->new_code_competence) && ($form->new_code_competence!="")){
			$competence = new object();
			$competence->code_competence=($form->new_code_competence);
			$competence->description_competence=($form->new_description_competence);
			$competence->ref_domaine=$form->new_ref_domaine;
			$competence->num_competence=$form->new_num_competence;
			$competence->nb_item_competences=$form->new_nb_item_competences;
			// DEBUG
			// print_object($competence);
			// echo "<br />";
			$new_competence_id = $DB->insert_record("referentiel_competence", $competence);
			// echo "competence ID / $new_competence_id<br />";
		}

	return $new_competence_id; 
}

/**
 * Given an object containing all the necessary referentiel, 
 * (defined by the form in mod.html) this function 
 * will update an existing instance with new referentiel.
 *
 * @param object $instance An object from the form in mod.html
 * @return boolean Success/Fail
 **/
function referentiel_update_competence($form) {
global $DB;
	$ok=false;	
	// DEBUG
	// print_object($form);
	// echo "<br />";

		if (isset($form->competence_id) && ($form->competence_id>0)){
			$competence = new object();
			$competence->id=$form->competence_id;
			$competence->code_competence=($form->code_competence);
			$competence->description_competence=($form->description_competence);
			$competence->ref_domaine=$form->ref_domaine;
			$competence->num_competence=$form->num_competence;
			$competence->nb_item_competences=$form->nb_item_competences;
			// DEBUG
			// print_object($competence);
			if (!$DB->update_record("referentiel_competence", $competence)){
				// echo "<br />ERREUR DE MISE A JOUR...";
				$ok=false;
				// exit;
			}
			else{
				// echo "<br />MISE A JOUR COMPETENCES...";
				$ok=true;
			}
		}

	return $ok;
}

/**
 * Given a competence id, 
 * this function will delete of this competence.
 *
 * @param int id
 * @return boolean 
 **/
function referentiel_delete_competence($competence_id){
// suppression
global $DB;
    $ok_competence=true;
    $ok_item=true;
    # Delete any dependent records here #
	// items
	if ($items = $DB->get_records("referentiel_item_competence", array("ref_competence" => "$competence_id"))) {
		// DEBUG
		// print_object($items);
		// echo "<br />";
		foreach ($items as $item){
			// suppression
			$ok_item=$ok_item && $DB->delete_records("referentiel_item_competence", array("id" => "$item->id"));
		}
	}	
	// suppression
	$ok_competence=$ok_competence && $DB->delete_records("referentiel_competence", array("id" => "$competence_id"));
	
    return ($ok_competence && $ok_item);
}

/**
 * Given an cometence id, 
 * this function will delete this competence and update competence number in domain linked.
 *
 * @param int id
 * @return boolean 
 **/
function referentiel_supprime_competence($competence_id){
// suppression avec mise a jour du nombre de competences dans le domaine associe
global $CFG;
global $DB;
	if (!$competence_id){
		return false;
	}
	$ok_competence=true;
	$ok_item=true;
    # Delete any dependent records here #
	// items
	if ($items = $DB->get_records("referentiel_item_competence", array("ref_competence" => "$competence_id"))) {
		// DEBUG
		// print_object($items);
		// echo "<br />";
		foreach ($items as $item){
			// suppression
			$ok_item=$ok_item && $DB->delete_records("referentiel_item_competence", array("id" => "$item->id"));
		}
	}	
	// suppression de la competence avec mise a jour dans le domaine associe
    $params = array("id" => "$competence_id");
    $sql = "SELECT ref_domaine FROM {referentiel_competence} WHERE id=:id";
    $ref_domaine = $DB->get_record_sql($sql, $params);
	if ($ref_domaine){
	    $params2 = array("id" => "$ref_domaine->ref_domaine");
        $sql2="SELECT * FROM {referentiel_domaine} WHERE id=:id";
		$d_record=$DB->get_record_sql($sql2, $params2);
		if ($d_record){
			$domaine = new object();
			$domaine->id=$d_record->id;
			$domaine->ref_referentiel=$d_record->ref_referentiel;
			$domaine->code_domaine=($d_record->code_domaine);
			$domaine->description_domaine=($d_record->description_domaine);
			$domaine->num_domaine=$d_record->num_domaine;
			$domaine->nb_competences=$d_record->nb_competences-1;
			$ok=$DB->update_record("referentiel_domaine", $domaine);
			
			// Mise Ã  jour de la liste de competences dans le referentiel_referentiel associe
			if ($ok && $d_record->ref_referentiel){
				$liste_codes_competence=referentiel_new_liste_codes_competence($d_record->ref_referentiel);
				// echo "<br />LISTE_CODES_COMPETENCE : $liste_codes_competence\n";
				referentiel_set_liste_codes_competence($d_record->ref_referentiel, $liste_codes_competence);
				$liste_empreintes_competence=referentiel_new_liste_empreintes_competence($d_record->ref_referentiel);
				// echo "<br />LISTE_empreintes_COMPETENCE : $liste_empreintes_competence\n";
				referentiel_set_liste_empreintes_competence($d_record->ref_referentiel, $liste_empreintes_competence);
				// Modif JF 2009/10/16
				$liste_poids_competence=referentiel_new_liste_poids_competence($d_record->ref_referentiel);
				// echo "<br />LISTE_poids_COMPETENCE : $liste_poids_competence\n";
				referentiel_set_liste_poids_competence($d_record->ref_referentiel, $liste_poids_competence);
			}
		}
	}
	$ok_competence=$ok_competence &&  $DB->delete_records("referentiel_competence", array("id" => "$competence_id"));
    return ($ok_competence && $ok_item);
}


/**
 * Given an object containing all the necessary referentiel, 
 * (defined by the form in mod.html) this function 
 * will update an existing instance with new referentiel.
 *
 * @param object $instance An object from the form in mod.html
 * @return boolean Success/Fail
 **/
function referentiel_update_item($form) {
global $DB;
	$ok=false;	
	// DEBUG
	// print_object($form);
	// echo "<br />";
		// ITEM COMPETENCES
		if (isset($form->item_id) && ($form->item_id>0)){
			$item = new object();
			$item->id=$form->item_id;
			$item->ref_referentiel=$form->instance;
			$item->ref_competence=$form->ref_competence;
			$item->code_item=($form->code_item);
			$item->description_item=($form->description_item);
			$item->num_item=$form->num_item;
			$item->type_item=($form->type_item);
			$item->poids_item=$form->poids_item;
			$item->empreinte_item=$form->empreinte_item;
			// DEBUG
			// print_object($item);
			// echo "<br />";
			if (!$DB->update_record("referentiel_item_competence", $item)){
				// echo "<br />ERREUR DE MISE A JOUR ITEM COMPETENCE...";
				$ok=false;
			}
			else {
				// echo "<br />MISE A JOUR ITEM COMPETENCES...";
				$ok=true;
				// Mise Ã  jour de la liste de competences
				$liste_codes_competence=referentiel_new_liste_codes_competence($form->referentiel_id);
				// echo "<br />LISTE_CODES_COMPETENCE : $liste_codes_competence\n";
				referentiel_set_liste_codes_competence($form->referentiel_id, $liste_codes_competence);
				$liste_empreintes_competence=referentiel_new_liste_empreintes_competence($form->referentiel_id);
				// echo "<br />LISTE_empreintes_COMPETENCE : $liste_empreintes_competence\n";
				referentiel_set_liste_empreintes_competence($form->referentiel_id, $liste_empreintes_competence);
				// Modif JF 2009/10/16
				$liste_poids_competence=referentiel_new_liste_poids_competence($form->referentiel_id);
				// echo "<br />LISTE_poids_COMPETENCE : $liste_poids_competence\n";
				referentiel_set_liste_poids_competence($form->referentiel_id, $liste_poids_competence);
			}
		}
	return $ok;
}

/**
 * Given an object containing all the necessary referentiel, 
 * (defined by the form in mod.html) this function 
 * will add an existing instance with new item.
 *
 * @param object $instance An object from the form
 * @return new_item_id
 **/

function referentiel_add_item($form) {
global $DB;
// NOUVEL item
	$new_item_id=0;	
	if (isset($form->new_code_item) && ($form->new_code_item!="")){
		$item = new object();
		$item->ref_referentiel=$form->instance;
		$item->ref_competence=$form->new_ref_competence;
		$item->code_item=($form->new_code_item);
		$item->description_item=($form->new_description_item);
		$item->num_item=$form->new_num_item;
		$item->type_item=($form->new_type_item);
		$item->poids_item=$form->new_poids_item;
		$item->empreinte_item=$form->new_empreinte_item;
		
		// DEBUG
		// echo "<br />DEBUG :: lib.php :: 921<br />\n";
		// print_object($item);
		// echo "<br />";
		$new_item_id = $DB->insert_record("referentiel_item_competence", $item);
   		// echo "item ID / $new_item_id<br />";
		if ($new_item_id > 0){
			// Mise Ã  jour de la liste de competences
			$liste_codes_competence=referentiel_new_liste_codes_competence($form->instance);
			// echo "<br />LISTE_CODES_COMPETENCE : $liste_codes_competence\n";
			referentiel_set_liste_codes_competence($form->instance, $liste_codes_competence);
			$liste_empreintes_competence=referentiel_new_liste_empreintes_competence($form->instance);
			// echo "<br />LISTE_empreintes_COMPETENCE : $liste_empreintes_competence\n";
			referentiel_set_liste_empreintes_competence($form->instance, $liste_empreintes_competence);
			// Modif JF 2009/10/16
			$liste_poids_competence=referentiel_new_liste_poids_competence($form->instance);
			// echo "<br />LISTE_poids_COMPETENCE : $liste_poids_competence\n";
			referentiel_set_liste_poids_competence($form->instance, $liste_poids_competence);
		}
	}
	return $new_item_id;
}

/**
 * Given an item id, 
 * this function will delete of this item.
 *
 * @param int id
 * @return boolean 
 **/
function referentiel_delete_item($item_id){
// suppression
	if ($item_id){
		return  $DB->delete_records("referentiel_item_competence", array("id" => "$item_id"));
	}
}

/**
 * Given an item id, 
 * this function will delete of this item.
 *
 * @param int id
 * @return boolean 
 **/
function referentiel_supprime_item($item_id){
// suppression avec mise a jour de la liste des item dans la competence associee
global $CFG;
global $DB;
$ok=false;
	if ($item_id){
        $params = array("id" => "$item_id");
        $sql = "SELECT ref_competence, ref_referentiel FROM {referentiel_item_competence} WHERE id=:id";
		$reference = $DB->get_record_sql($sql, $params);
		if ($reference){
            $params2 = array("id" => "$reference->ref_competence");
            $sql2 = "SELECT * FROM {referentiel_competence} WHERE id=:id";
			$c_record=$DB->get_record_sql($sql2, $params2);
			if ($c_record){
				$competence = new object();
				$competence->id=$c_record->id;
				$competence->code_competence=($c_record->code_competence);
				$competence->description_competence=($c_record->description_competence);
				$competence->ref_domaine=$c_record->ref_domaine;
				$competence->num_competence=$c_record->num_competence;
				$competence->nb_item_competences=$c_record->nb_item_competences-1;
				// DEBUG
				// print_object($competence);
				$DB->update_record("referentiel_competence", $competence);
			}
		}
		$ok=$DB->delete_records("referentiel_item_competence", array("id" =>"$item_id"));
		// Mise Ã  jour de la liste de competences
		if ($ok && $reference && $reference->ref_referentiel){
			$liste_codes_competence=referentiel_new_liste_codes_competence($reference->ref_referentiel);
			// echo "<br />LISTE_CODES_COMPETENCE : $liste_codes_competence\n";
			referentiel_set_liste_codes_competence($reference->ref_referentiel, $liste_codes_competence);
			$liste_empreintes_competence=referentiel_new_liste_empreintes_competence($reference->ref_referentiel);
			// echo "<br />LISTE_empreintes_COMPETENCE : $liste_empreintes_competence\n";
			referentiel_set_liste_empreintes_competence($reference->ref_referentiel, $liste_empreintes_competence);
			// Modif JF 2009/10/16 
			$liste_poids_competence=referentiel_new_liste_poids_competence($reference->ref_referentiel);
			// echo "<br />LISTE_poids_COMPETENCE : $liste_poids_competence\n";
			referentiel_set_liste_poids_competence($reference->ref_referentiel, $liste_poids_competence);
		}
	}
	return $ok;
}


/**
 * Given referentiel_referentiel id
 * this function 
 * will return a list of referentiel instance.
 *
 * @param  referentiel_referentiel id
 * @return a array of instance id
 **/
function referentiel_referentiel_list_of_instance($id){
// liste ds instances associees a ce referentiel
global $DB;
	if (!empty($id)){
		// id referentiel doit Ãªtre numerique
		$id = intval(trim($id));
		$params= array("id" => "$id");
		$sql="SELECT id FROM {referentiel} WHERE ref_referentiel=:id";
		$records_instance=$DB->get_records_sql($sql, $params);
		if ($records_instance){
			// DEBUG
			// echo "<br />DEBUG :: lib.php :: 1309 <br />";
			// print_object($records_instance);
			// echo "<br />";
			// exit;
			return ($records_instance);
		}
	}
	return NULL;
}


/**
 * Given an id of  referentiel_referentiel, 
 * this function 
 * will delete all object associated to this referentiel_referentiel.
 *
 * @param id
 * @return boolean Success/Fail
 **/
function referentiel_delete_referentiel_domaines($id) {
global $DB;
$ok_domaine=true;
$ok_competence=true;
$ok_item=true;
$ok=true;
	// verifier existence
    if (!$id) return false;
	if (!$referentiel_referentiel = $DB->get_record("referentiel_referentiel", array("id" => "$id"))) {
        return false;
    }
	
    # Delete any dependent records here #
    if ($domaines = $DB->get_records("referentiel_domaine", array("ref_referentiel" => "$id"))) {
		// DEBUG
		// print_object($domaines);
		// echo "<br />";
		foreach ($domaines as $domaine){
			// Competences
			if ($competences = $DB->get_records("referentiel_competence", array("ref_domaine" => "$domaine->id"))) {
				// DEBUG
				// print_object($competences);
				// echo "<br />";
				// Item
				foreach ($competences as $competence){
					if ($items = $DB->get_records("referentiel_item_competence", array("ref_competence" => "$competence->id"))) {
						// DEBUG
						// print_object($items);
						// echo "<br />";
						foreach ($items as $item){
							// suppression
							$ok_item=$ok_item && $DB->delete_records("referentiel_item_competence", array("id" => "$item->id"));
						}
					}	
					$ok_competence=$ok_competence && $DB->delete_records("referentiel_competence", array("id" => "$competence->id"));
				}
			}
			// suppression
			$ok_domaine=$ok_domaine && $DB->delete_records("referentiel_domaine", array("id" =>"$domaine->id"));
		}
    }
    if (! $DB->delete_records("referentiel_referentiel", array("id" =>"$id"))) {
        $ok = $ok && false;
    }
	
    return ($ok && $ok_domaine && $ok_competence && $ok_item);
}




/**
 * Given a document id, 
 * this function will permanently delete the document instance 
 *
 * @param object $id
 * @return boolean Success/Failure
 **/

function referentiel_delete_document_record($id) {
// suppression document
global $DB;
$ok_document=false;
	if (isset($id) && ($id>0)){
		if ($document = $DB->get_record("referentiel_document", array("id" => "$id"))) {
			//  CODE A AJOUTER SI GESTION DE FICHIERS DEPOSES SUR LE SERVEUR
            $ok_document= $DB->delete_records("referentiel_document", array("id" => "$id"));
		}
	}
	return $ok_document;
}


/**
 * Given an activity id, 
 * this function will permanently delete the activite instance 
 * and any document that depends on it. 
 *
 * @param object $id
 * @return boolean Success/Failure
 **/

function referentiel_delete_activity_record($id) {
// suppression activite + documents associes
global $DB;
$ok_activite=false;	
	if (isset($id) && ($id>0)){
		if ($activite = $DB->get_record("referentiel_activite", array("id" => "$id"))) {
	   		// Delete any dependent records here 
			
			// Si c'est une activite - tache il faut aussi supprimer les liens vers cette tache
			if (isset($activite->ref_task) && ($activite->ref_task>0) && isset($activite->userid) && ($activite->userid>0)){
                $params=array("taskid" => "$activite->ref_task" , "userid" => "$activite->userid");
                $sql="SELECT * FROM {referentiel_a_user_task} WHERE ref_task=:taskid AND ref_user=:userid";
                $a_t_records = $DB->get_records_sql($sql, $params);
				if ($a_t_records){
					foreach ($a_t_records as $a_t_record){
						// suppression
						referentiel_delete_a_user_task_record($a_t_record->id);
					}
				}
			}
			
			$ok_document=true;
			if ($documents = $DB->get_records("referentiel_document", array("ref_activite" => "$id"))) {
				// DEBUG
				// print_object($documents);
				// echo "<br />";
				// suppression des documents associes dans la table referentiel_document
				foreach ($documents as $document){
					// suppression
					$ok_document=$ok_document && referentiel_delete_document_record($document->id);
				}
			}
			// suppression activite
			if ($ok_document){
                $ok_activite = $DB->delete_records("referentiel_activite", array("id" => "$id"));
				if 	($ok_activite
					&& isset($activite->userid) && ($activite->userid>0) 
					&& isset($activite->competences_activite) && ($activite->competences_activite!='')){
					// mise a jour du certificat 
					referentiel_mise_a_jour_competences_certificat_user($activite->competences_activite, '', $activite->userid, $activite->ref_referentiel, $activite->approved, true, true);
				}
			}
		}
	}
    return $ok_activite;
}


/**
 * Given a form, 
 * this function will permanently delete the activite instance 
 * and any document that depends on it. 
 *
 * @param object $form
 * @return boolean Success/Failure
 **/

function referentiel_delete_activity($form) {
// suppression activite + document
$ok_activite_detruite=false;
$ok_document=false;
    // DEBUG
	// echo "<br />";
	// print_object($form);
    // echo "<br />";
	if (isset($form->action) && ($form->action=="modifier_activite")){
		// suppression d'une activite et des documents associes
		if (isset($form->activite_id) && ($form->activite_id>0)){
			$activite=referentiel_get_activite($form->activite_id);
			$ok_activite_detruite=referentiel_delete_activity_record($form->activite_id);
			
			// MODIF JF 2009/09/21
			// mise a zero du certificat associe a cette personne pour ce referentiel 
			// referentiel_certificat_user_invalider($form->userid, $form->ref_referentiel);
			// referentiel_regenere_certificat_user($form->userid, $form->ref_referentiel);
			if 	($ok_activite_detruite  
				&& $activite->userid>0 
				&& ($activite->competences_activite!='')){
				// mise a jour du certificat 
				referentiel_mise_a_jour_competences_certificat_user($activite->competences_activite, '', $activite->userid, $activite->ref_referentiel, $activite->approved, true, true);
			}
		}
	}
	else if (isset($form->action) && ($form->action=="modifier_document")){
		// suppression d'un document
		if (isset($form->document_id) && ($form->document_id>0)){
			$ok_document=referentiel_delete_document_record($form->document_id);
		}
	}
	
    return $ok_activite_detruite or $ok_document;
}


/**
 * Given an object containing all the necessary referentiel, 
 * (defined by the form in mod.html) this function 
 * will create a new instance and return the id number 
 * of the new instance.
 *
 * @param object $instance An object from the form in activite.html
 * @return int The id of the newly inserted referentiel record
 **/
function referentiel_add_activity($form) {
// creation activite + document
global $USER;
global $DB;
    // DEBUG
    // echo "DEBUG : ADD ACTIVITY CALLED : lib.php : ligne 1033";
	// print_object($form);
    // echo "<br />";
	// referentiel
	$activite = new object();
	$activite->type_activite=($form->type_activite);
	if (!empty($form->code_item)){
		$activite->competences_activite=reference_conversion_code_2_liste_competence('/', $form->code_item);
	}
	else{
		$activite->competences_activite='';
	}
	$activite->description_activite=($form->description_activite);
	$activite->commentaire_activite=($form->commentaire_activite);
	$activite->ref_instance=$form->instance;
	$activite->ref_referentiel=$form->ref_referentiel;
	$activite->ref_course=$form->course;
	$activite->date_creation=time();
	$activite->date_modif_student=time();
	$activite->date_modif=0;
	$activite->approved=0;
	$activite->userid=$USER->id;
	$activite->teacherid=0;
	$activite->ref_task=0;

	$activite->mailed=1;  // MODIF JF 2010/10/05  pour empêcher une notification intempesttive
    if (isset($form->mailnow)){
        $activite->mailnow=$form->mailnow;
        if ($form->mailnow=='1'){ // renvoyer
            $activite->mailed=0;   // forcer l'envoi
        }
    }
    else{ 
      $activite->mailnow=0;
    }	
    
    // DEBUG
    // echo "<br />DEBUG :: lib.php : 1163 : APRES CREATION\n";	
	// print_object($activite);
    // echo "<br /> EXIT lib.php Ligne 1734 <br />";
	// exit;
	$activite_id= $DB->insert_record("referentiel_activite", $activite);
	// DEBUG
	// echo "ACTIVITE ID / $activite_id<br />";
	
	// MODIF JF 2009/09/21
	// mise a zero du certificat associe a cette personne pour ce referentiel 
	// referentiel_certificat_user_invalider($activite->userid, $activite->ref_referentiel);
	// referentiel_regenere_certificat_user($activite->userid, $activite->ref_referentiel);
	if 	(($activite_id>0) && ($activite->competences_activite!='')){
		// mise a jour du certificat 
		referentiel_mise_a_jour_competences_certificat_user('', $activite->competences_activite, $activite->userid, $activite->ref_referentiel, $activite->approved, true, false);
	}
	
    
	if 	(isset($activite_id) && ($activite_id>0)
			&& 
			(	(isset($form->url_document) && !empty($form->url_document))
				|| 
				(isset($form->description_document) && !empty($form->description_document))
			)
	){
		$document = new object();
		$document->url_document=($form->url_document);
		$document->type_document=($form->type_document);
		$document->description_document=($form->description_document);
		$document->ref_activite=$activite_id;
		if (isset($form->cible_document)){
			$document->cible_document=$form->cible_document;
   		}
		else{
			$document->cible_document=1;
		}
		if (isset($form->etiquette_document)){
			$document->etiquette_document=$form->etiquette_document;
   		}
		else{
			$document->etiquette_document='';
		}

	   	// DEBUG
		// print_object($document);
    	// echo "<br />";
		
		$document_id = $DB->insert_record("referentiel_document", $document);
    	// echo "DOCUMENT ID / $document_id<br />";
	}
    return $activite_id;
}

function referentiel_update_activity($form) {
// MAJ activite + document;
global $USER;
global $DB;
$ok=true;
    // DEBUG
	// echo "<br />UPDATE ACTIVITY<br />\n";
	// print_object($form);
    // echo "<br />";
	
	if (isset($form->action) && ($form->action=="modifier_activite")){
		
		// recuperer l'ancien enregistrement pour les mises Ã  jour du certificat
		$old_liste_competences='';
		if ($form->activite_id){
			$record_activite=referentiel_get_activite($form->activite_id);
			if ($record_activite){
				$old_liste_competences=$record_activite->competences_activite;
			}
		}
		if (($old_liste_competences=='') && isset($form->old_liste_competences)){
			$old_liste_competences=$form->old_liste_competences;
		}

		// activite
		$activite = new object();
		$activite->id=$form->activite_id;	
		$activite->type_activite=($form->type_activite);
		// $activite->competences_activite=$form->competences_activite;
		if (isset($form->code_item) && is_array($form->code_item)){
			$activite->competences_activite=reference_conversion_code_2_liste_competence('/', $form->code_item);
		}
		else if (isset($form->competences_activite)){
			$activite->competences_activite=$form->competences_activite;
		}
		else{
			$activite->competences_activite='';
		}
		$activite->description_activite=($form->description_activite);
		$activite->commentaire_activite=($form->commentaire_activite);
		$activite->ref_instance=$form->instance;
		$activite->ref_referentiel=$form->ref_referentiel;
		$activite->ref_course=$form->course;
		$activite->date_creation=$form->date_creation;
		$activite->approved=$form->approved;
		$activite->userid=$form->userid;	
		$activite->teacherid=$form->teacherid;
		
		
		// MODIF JF 2009/10/27
		if ($USER->id==$activite->userid){
			$activite->date_modif_student=time();
			$activite->date_modif=$form->date_modif;
			$activite->teacherid=$form->teacherid;
		}
		else{
			$activite->date_modif=time();
			$activite->date_modif_student=$form->date_modif_student;
            $activite->teacherid=$USER->id;
		}
		
		// MODIF JF 2010/02/11
        if (isset($form->mailnow)){
            $activite->mailnow=$form->mailnow;
            if ($form->mailnow=='1'){ // renvoyer
                $activite->mailed=0;   // annuler envoi precedent
            }
        }
        else{
            $activite->mailnow=0;
        }
    
		// DEBUG
		// print_object($activite);
	    // echo "<br />";
		$ok = $ok && $DB->update_record("referentiel_activite", $activite);
		
	    // echo "DEBUG :: lib.php :: 1803 :: ACTIVITE ID / $activite->id<br />";
		// exit;
		
		// MODIF JF 2009/09/21
		// mise a zero du certificat associe a cette personne pour ce referentiel 
		// referentiel_certificat_user_invalider($activite->userid, $activite->ref_referentiel);
		// referentiel_regenere_certificat_user($activite->userid, $activite->ref_referentiel);
		if 	($ok && ($activite->userid>0)){
			// mise a jour du certificat 
			referentiel_mise_a_jour_competences_certificat_user($old_liste_competences, $activite->competences_activite, $activite->userid, $activite->ref_referentiel, $activite->approved, true, $activite->approved);
		}
	}
	else if (isset($form->action) && ($form->action=="modifier_document")){
		$document = new object();
		$document->id=$form->document_id;
		$document->url_document=($form->url_document);
		$document->type_document=($form->type_document);
		$document->description_document=($form->description_document);
		$document->ref_activite=$form->ref_activite;
		if (isset($form->cible_document)){
			$document->cible_document=$form->cible_document;
   		}
		else{
			$document->cible_document=1;
		}
		if (isset($form->etiquette_document)){
			$document->etiquette_document=$form->etiquette_document;
   		}
		else{
			$document->etiquette_document='';
		}
		
   		// DEBUG
		// print_object($document);
    	// echo "<br />";
		$ok= $ok && $DB->update_record("referentiel_document", $document);
		// exit;
	}
	else if (isset($form->action) && ($form->action=="creer_document")){
		$document = new object();
		$document->url_document=($form->url_document);
		$document->type_document=($form->type_document);
		$document->description_document=($form->description_document);
		$document->ref_activite=$form->ref_activite;
		if (isset($form->cible_document)){
			$document->cible_document=$form->cible_document;
   		}
		else{
			$document->cible_document=1;
		}
		if (isset($form->etiquette_document)){
			$document->etiquette_document=$form->etiquette_document;
   		}
		else{
			$document->etiquette_document='';
		}
		
   		// DEBUG
		// print_object($document);
    	// echo "<br />";
		$ok = $DB->insert_record("referentiel_document", $document);
    	// echo "DOCUMENT ID / $ok<br />";
		// exit;
	}
    return $ok;
}

function referentiel_update_document($form) {
global $DB;
// MAJ document;
    // DEBUG
	// echo "<br />UPDATE ACTIVITY<br />\n";
	// print_object($form);
    // echo "<br />";
	if (isset($form->document_id) && $form->document_id
		&&
		isset($form->ref_activite) && $form->ref_activite){
		$document = new object();
		$document->id=$form->document_id;
		$document->url_document=($form->url_document);
		$document->type_document=($form->type_document);
		$document->description_document=($form->description_document);
		$document->ref_activite=$form->ref_activite;
		if (isset($form->cible_document)){
			$document->cible_document=($form->cible_document);
   		}
		else{
			$document->cible_document=1;
		}
		if (isset($form->etiquette_document)){
			$document->etiquette_document=$form->etiquette_document;
   		}
		else{
			$document->etiquette_document='';
		}

   		// DEBUG
		// print_object($document);
    	// echo "<br />";
		return $DB->update_record("referentiel_document", $document);
	}
	return false;
}

function referentiel_add_document($form) {
// MAJ document;
	$id_document=0;
	if (isset($form->ref_activite) && $form->ref_activite){
		$document = new object();
		$document->url_document=($form->url_document);
		$document->type_document=($form->type_document);
		$document->description_document=($form->description_document);
		$document->ref_activite=$form->ref_activite;
		if (isset($form->cible_document)){
			$document->cible_document=$form->cible_document;
   		}
		else{
			$document->cible_document=1;
		}
		if (isset($form->etiquette_document)){
			$document->etiquette_document=$form->etiquette_document;
   		}
		else{
			$document->etiquette_document='';
		}
		// DEBUG
		// print_object($document);
    	// echo "<br />";
		$id_document = $DB->insert_record("referentiel_document", $document);
    	// echo "DOCUMENT ID / $ok<br />";
		// exit;
	}
    return $id_document;
}



/**
 * Return a small object with summary information about what a 
 * user has done with a given particular instance of this module
 * Used for user activity reports.
 * $return->time = the time they did it
 * $return->info = a short text description
 *
 * @return null
 * @todo Finish documenting this function
 **/
function referentiel_user_outline($course, $user, $mod, $referentiel) {
    $return= new Object;

	$return->time = $referentiel->date_instance;
    $return->instance = $referentiel->id;
	$return->info = get_string('name_instance','referentiel').' : <i>'.$referentiel->name.'</i>';
	$return->info .= ", ".get_string('description_instance','referentiel').' : <i>'.$referentiel->description_instance.'</i>';
	
	if (isset($referentiel->ref_referentiel) && ($referentiel->ref_referentiel>0)){
		$referentiel_referentiel=referentiel_get_referentiel_referentiel($referentiel->ref_referentiel);
		if ($referentiel_referentiel){
			$return->info .= ", ".get_string('name','referentiel').' : <i>'.$referentiel_referentiel->name.'</i>';		
			$return->info .= ", ".get_string('code_referentiel','referentiel').' : <i>'.$referentiel_referentiel->code_referentiel.'</i>';
			if (isset($referentiel_referentiel->local) && ($referentiel_referentiel->local!=0)){
				$return->info .= ", ".get_string('referentiel_global','referentiel').' : <i>' . get_string('no').'</i>';
			}
			else{
				$return->info .= ", ".get_string('referentiel_global','referentiel').' : <i>' . get_string('yes').'</i>';	
			}
		}
	}
    return $return;
}

/**
 * Print a detailed representation of what a user has done with 
 * a given particular instance of this module, for user activity reports.
 *
 * 
 * @todo Finish documenting this function
 **/
function referentiel_user_complete($course, $user, $mod, $referentiel) {
    $return= new Object;
	$return->time = $referentiel->date_instance;
    $return->instance = $referentiel->id;
	$return->info = "<li>".get_string('name_instance','referentiel').' : <i>'.$referentiel->name.'</i>';
	$return->info .="</li><li>".get_string('description_instance','referentiel').' : <i>'.$referentiel->description_instance.'</i>';
	$return->info .="</li><li>".get_string('label_domaine','referentiel').' : <i>'.$referentiel->label_domaine.'</i>';	
	$return->info .="</li><li>".get_string('label_competence','referentiel').' : <i>'.$referentiel->label_competence.'</i>';	
	$return->info .="</li><li>".get_string('label_item','referentiel').' : <i>'.$referentiel->label_item.'</i>';	

	if (isset($referentiel->ref_referentiel) && ($referentiel->ref_referentiel>0)){
		$referentiel_referentiel=referentiel_get_referentiel_referentiel($referentiel->ref_referentiel);
		if ($referentiel_referentiel){
			$return->info .="</li><li>".get_string('name','referentiel').' : <i>'.$referentiel_referentiel->name.'</i>';
			$return->info .="</li><li>".get_string('code_referentiel','referentiel').' : <i>'.$referentiel_referentiel->code_referentiel.'</i>';
			$return->info .="</li><li>".get_string('description_referentiel','referentiel').' : <i>'.$referentiel_referentiel->description_referentiel.'</i>';
			$return->info .="</li><li>".get_string('url_referentiel','referentiel').' : <i>'.$referentiel_referentiel->url_referentiel.'</i>';
			$return->info .="</li><li>".get_string('seuil_certificat','referentiel').' : <i>'.$referentiel_referentiel->seuil_certificat.'</i>';
			$return->info .="</li><li>".get_string('modification','referentiel').' : <i>'.date("Y/m/d",$referentiel_referentiel->timemodified).'</i>';	
			
			if (isset($referentiel_referentiel->local) && ($referentiel_referentiel->local!=0)){
				$return->info .="</li><li>".get_string('referentiel_global','referentiel').' : <i>' . get_string('no')."</i></li>";
			}
			else{
				$return->info .="</li><li>".get_string('referentiel_global','referentiel').' : <i>' . get_string('yes')."</i></li>";	
			}
		}
		$referentiel_certificat=referentiel_get_certificat_user($user->id, $referentiel->ref_referentiel);
		if ($referentiel_certificat){
		/*
 id bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  commentaire_certificat text NOT NULL,
  synthese_certificat text
  competences_certificat text NOT NULL,
  decision_jury varchar(80) NOT NULL DEFAULT '',
  date_decision bigint(10) unsigned NOT NULL DEFAULT '0',
  ref_referentiel bigint(10) unsigned NOT NULL DEFAULT '0',
  userid bigint(10) unsigned NOT NULL,
  teacherid bigint(10) unsigned NOT NULL,
  verrou tinyint(1) unsigned NOT NULL,
  valide tinyint(1) unsigned NOT NULL,
  evalua*/	
			$return->info .="</li>\n<li><b>".get_string('certification','referentiel')."</b><ul>\n";
			if ($referentiel_certificat->decision_jury){
				$return->info .="<li>".get_string('certificat_etat','referentiel').' : <i>'.$referentiel_certificat->decision_jury.' ('.date("Y/m/d",$referentiel_certificat->date_decision).")</i></li>";
			}
			
			if ($referentiel_certificat->verrou!=0){
				$bgcolor=' color="#ffaaaa"';
			}
			else{
				$bgcolor=' color="#aaffaa"';
			}
			
			// Pas possible car la fonction ne retourne plus rien
			// $return->info .="<li>".get_string('competences_certificat','referentiel').' :<br />'.referentiel_affiche_certificat_consolide('/',':',$referentiel_certificat->competences_certificat, $referentiel->ref_referentiel, $bgcolor)."</li>";
			// ca c'est ok
            $return->info .="<li>".get_string('competences_certificat','referentiel').' :<br />'.$referentiel_certificat->competences_certificat."</li>";
            $return->info .="<li>".get_string('evaluation','referentiel').' : <i>'.$referentiel_certificat->evaluation."</i></li>";
			$return->info .="</ul></li>";
		}
	}
	
    echo $return->info;
}


//////////////////////////////////////////////////////////////////////////////////////
/// Any other referentiel functions go here.  Each of them must have a name that 
/// starts with referentiel_

/**
 * This function returns max id from table passed
 *
 * @param table name
 * @return id
 * @todo Finish documenting this function
 **/
function referentiel_get_max_id($table){
global $DB;
	if (isset($table) && ($table!="")){
        $sql="SELECT MAX(id) as id FROM {$table}";
        $r=$DB->get_record_sql($sql, NULL);
		if ($r){
			return $r->id;
		}
	}
	else 
		return 0; 
}

/**
 * This function returns min id from table passed
 *
 * @param table name
 * @return id
 * @todo Finish documenting this function
 **/
function referentiel_get_min_id($table){
global $DB;
	if (isset($table) && ($table!="")){
		$r=$DB->get_record_sql("SELECT MIN(id) as id FROM {$table}", NULL);
		if ($r){
			return $r->id;
		}
	}
	else 
		return 0; 
}


function referentiel_get_table($id, $table) {
global $DB;
// retourn un objet  
    // DEBUG
    // temp added for debugging
    // echo "DEBUG : GET INSTANCE CALLED";
    // echo "<br />";
	
	// referentiel
	$objet = $DB->get_record($table, array("id" => "$id"));
    // DEBUG
	// print_object($objet);
    // echo "<br />";
	return $objet;
}

/**
 * This function returns nomber of domains from table referentiel
 *
 * @param id
 * @return int
 * @todo Finish documenting this function
 **/
function referentiel_get_nb_domaines($id){
global $DB;
	if (isset($id) && ($id>0)){
        $params = array("id" => "$id");
        $sql="SELECT nb_domaines FROM {referentiel_referentiel} WHERE id=:id";
		return $DB->get_record_sql($sql, $params);
	}
	else 
		return 0; 
}

/**
 * This function returns records from table referentiel_domaine
 *
 * @param ref
 * @return record
 * @todo Finish documenting this function
 **/
function referentiel_get_domaines($ref_referentiel){
global $DB;
	if (!empty($ref_referentiel)){
        $params = array("refid" => "$ref_referentiel");
        $sql="SELECT * FROM {referentiel_domaine} WHERE ref_referentiel=:refid ORDER BY num_domaine ASC";
		return $DB->get_records_sql($sql, $params);
	}
	else 
		return 0; 
}


/**
 * This function returns nomber of competences from table referentiel_domaine
 *
 * @param id
 * @return int
 * @todo Finish documenting this function
 **/
function referentiel_get_nb_competences($id){
global $DB;
	if (isset($id) && ($id>0)){
        $params = array("id" => "$id");
        $sql="SELECT nb_competences FROM {referentiel_domaine} WHERE id=:id";
        return $DB->get_record_sql($sql, $params);
	}
	else 
		return 0; 
}

/**
 * This function returns records from table referentiel_item_competence
 *
 * @param ref
 * @return id
 * @todo Finish documenting this function
 **/
function referentiel_get_competences($ref_domaine){
global $DB;
	if (!empty($ref_domaine)){
        $params = array("refdomaine" => "$ref_domaine");
        $sql="SELECT * FROM {referentiel_competence} WHERE ref_domaine=:refdomaine ORDER BY num_competence ASC";
        return $DB->get_records_sql($sql, $params);
	}
	else 
		return 0; 
}

/**
 * This function returns nomber of items from table referentiel_competence
 *
 * @param id
 * @return int
 * @todo Finish documenting this function
 **/
function referentiel_get_nb_item_competences($id){
global $DB;
	if (!empty($id)){
        $params = array("id" => "$id");
        $sql="SELECT nb_item_competences FROM {referentiel_competence} WHERE id=:id";
		return $DB->get_record_sql($sql, $params);
	}
	else 
		return 0; 
}

/**
 * This function returns records from table referentiel_item_competence
 *
 * @param id
 * @return int
 * @todo Finish documenting this function
 **/
function referentiel_get_item_competences($ref_competence){
global $DB;
	if (!empty($ref_competence)){
	    $params = array("refcompetence" => "$ref_competence");
        $sql="SELECT * FROM {referentiel_item_competence} WHERE ref_competence=:refcompetence ORDER BY num_item ASC ";
        return $DB->get_records_sql($sql, $params);
	}
	else 
		return 0; 
}

/**
 * This function returns an int from table referentiel_item_competence
 *
 * @param id
 * @return int of poids
 * @todo Finish documenting this function
 **/
function referentiel_get_poids_item($code, $referentiel_id){
global $DB;
	if (!empty($code)){
	    $params=array("code" => "$code", "refid" => "$referentiel_id");
        $sql="SELECT poids_item FROM {referentiel_item_competence} WHERE code_item=:code AND ref_referentiel=:refid ";
		$record=$DB->get_record_sql($sql, $params);
		if ($record){
			return $record->poids_item;
		}
	}
	return 0;
}


/**
 * This function returns an int from table referentiel_item_competence
 *
 * @param referentiel id
 * @return int of empreinte
 * @todo Finish documenting this function
 **/
function referentiel_get_empreinte_item($code, $referentiel_id){
global $DB;
	if (!empty($code)){
	    $params=array("code" => "$code", "refid" => "$referentiel_id");
        $sql="SELECT empreinte_item FROM {referentiel_item_competence} WHERE code_item=:code AND ref_referentiel=:refid";
		$record=$DB->get_record_sql($sql, $params);
		if ($record){
			return $record->empreinte_item;
		}
	}
	return 0;
}


/**
 * This function returns a string from table referentiel_item_competence
 *
 * @param referentiel id
 * @return string of poids
 * @todo Finish documenting this function
 **/
function referentiel_get_liste_poids($referentiel_id){
global $DB;
$liste="";
    $params=array("refid" => "$referentiel_id");
    $sql="SELECT id, description_item, poids_item FROM {referentiel_item_competence} WHERE ref_referentiel=:refid";
	$records=$DB->get_records_sql($sql, $params);
	if ($records){
		 foreach ($records as $record) {
		 	$liste.= $record->description_item.'#'.$record->poids_item.'|';
		 }
	}
	return $liste;
}



/**
 * This function returns a string from table referentiel_item_competence
 *
 * @param code, referentiel id
 * @return string
 * @todo Finish documenting this function
 **/

function referentiel_get_description_item($code, $referentiel_id=0){
global $DB;
	if (!empty($code)){
		if ($referentiel_id){
            $params=array("code" => "$code", "refid" => "$referentiel_id");
            $sql="SELECT description_item FROM {referentiel_item_competence} WHERE code_item=:code AND ref_referentiel=:refid";
            $record=$DB->get_record_sql($sql, $params);
			if ($record){
				return $record->description_item;
			}
		}
		else{
            $params=array("code" => "$code");
            $sql="SELECT description_item FROM {referentiel_item_competence} WHERE code_item=:code";
			$records=$DB->get_records_sql($sql, $params);
			if ($records){
				$s="";
				foreach ($records as $record){
					$s.=$record->description_item." ";
				}
				return $s;
			}
		}
	}
	return "";
}

/**
 * This function returns records from table referentiel
 *
 * @param $id : int id refrentiel to filter
 * $params filtering clause
 * @return int
 * @todo Finish documenting this function
 **/
function referentiel_filtrer($id, $data){
global $DB;
	if (!empty($id)){
        $params=array("id" => "$id", "zero" => "0");
		$where = "WHERE id=:id ";
        if (isset($data)){
			if (isset($data->filtrerinstance) && ($data->filtrerinstance!=0)){
				if (isset($data->localinstance) && ($data->localinstance==0)){
					$where .= " AND local=:zero ";
				}
				else {
					$where .= " AND local!=:zero ";
				}
			}
			// $data->referentiel_pass
			if (isset($data->referentiel_pass) && ($data->referentiel_pass!='')){
                $params=array("id" => "$id", "zero" => "0", "refpass" => "$data->referentiel_pass");
				$where .= " AND pass_referentiel=:refpass ";
			}
		}
		$sql="SELECT * FROM {referentiel_referentiel} $where ";
		$record = $DB->get_record_sql($sql, $params);
		if ($record){
			return $record->id;
		}
		else {
			return 0;
		}
	}
	else{
		return 0;
	}
}

/**
 * This function returns records from table referentiel_referentiel
 *
 * @param id
 * @return int
 * @todo Finish documenting this function
 **/
function referentiel_get_referentiel_referentiel($id){
global $DB;
 if (!empty($id)){
        $params = array("id" => "$id");
        $sql="SELECT * FROM {referentiel_referentiel} WHERE id=:id ";
		return $DB->get_record_sql($sql, $params);
	}
	else 
		return 0; 
}

/**
 * This function returns string from table referentiel_referentiel
 *
 * @param id
 * @return int
 * @todo Finish documenting this function
 **/
function referentiel_get_nom_referentiel($id){
global $DB;
$s="";
 if (!empty($id)){
        $params = array("id" => "$id");
        $sql="SELECT * FROM {referentiel_referentiel} WHERE id=:id ";
        $record=$DB->get_record_sql($sql, $params);
		if ($record){
			$s=$record->name;
		}
	}
	return $s; 
}


/**
 * This function returns records from table referentiel
 *
 * @param $params filtering clause
 * @return records
 * @todo Finish documenting this function
 **/
function referentiel_get_referentiel_referentiels($data){
global $DB;
	$where = "";
	if (isset($data)){
		if (isset($data->filtrerinstance) && ($data->filtrerinstance!=0)){
			if (isset($data->localinstance) && ($data->localinstance==0)){
				$where = " WHERE local=0 ";
			}
			else {
				$where = " WHERE local!=0 ";
			}
		}
	}
	$sql="SELECT * FROM {referentiel_referentiel} $where ORDER BY id ASC ";
	return $DB->get_records_sql($sql, NULL);
}

/**
 * This function returns records from table referentiel
 *
 * @param $params filtering clause
 * @return records
 * @todo Finish documenting this function
 **/
function referentiel_get_infos_from_code_ref($code){
global $DB;
	$trefref = array();
	if (!empty($code)){
        $params=array("code" => "$code");
        $sql="SELECT * FROM {referentiel_referentiel} WHERE code_referentiel=:code ORDER BY id ASC ";
        $recs=$DB->get_records_sql($sql, $params);
        if ($recs){
            foreach($recs as $rec){
                $trefref[]=$rec->id.':'.stripslashes($rec->code_referentiel).':'.stripslashes($rec->name);
            }
       }
    }
    return $trefref;
}

/**
 * This function returns records from table referentiel
 *
 * @param id
 * @return int
 * @todo Finish documenting this function
 **/
function referentiel_get_referentiel($id){
global $DB;
	if (!empty($id)){
	    $params = array("id" => "$id");
        $sql="SELECT * FROM {referentiel} WHERE id=:id ";
		return $DB->get_record_sql($sql, $params);
	}
	else 
		return 0; 
}


// ACTIVITES

/**
 * This function returns record from table referentiel_activite
 *
 * @param id
 * @return object
 * @todo Finish documenting this function
 **/
function referentiel_get_activite($id){
global $DB;
	if (isset($id) && ($id>0)){
	    $params = array("id" => "$id");
        $sql="SELECT * FROM {referentiel_activite} WHERE id=:id ";
		return $DB->get_record_sql($sql, $params);
	}
	else 
		return 0; 
}

/**
 * This function returns records from table referentiel_activite
 *
 * @param referentiel_id referentiel_activite->ref_referentiel : referentiel_referentiel id
 * @param user_id  referentiel_activite->userid : user id
 * @return array of objects
 * @todo Finish documenting this function
 **/
function referentiel_user_activites($referentiel_id, $user_id){
global $DB;
	if (($user_id>0) && ($referentiel_id>0)){
        $params = array("refid" => "$referentiel_id", "userid" => "$user_id");
        $sql="SELECT id FROM {referentiel_activite} WHERE ref_referentiel=:refid AND userid=:userid";
		$records=$DB->get_records_sql($sql, $params);
		return $records;
	}
	else 
		return 0; 
}

/**
 * This function returns records owned by user_id from table referentiel_activite 
 *
 * @param id reference id , user id
 * @return objects
 * @todo Finish documenting this function
 **/
function referentiel_get_all_activites_user_course($referentiel_id, $user_id, $course_id, $sql_filtre_where='', $sql_filtre_order=''){
global $DB;
	if (isset($referentiel_id) && ($referentiel_id>0) && isset($course_id) && ($course_id>0)){
        $params = array("refid" => "$referentiel_id", "courseid" => "$course_id", "userid" => "$user_id");
        $sql="SELECT * FROM {referentiel_activite} WHERE ref_referentiel=:refid AND ref_course=:courseid AND userid=:userid  $sql_filtre_where ORDER BY date_creation DESC $sql_filtre_order";
		return $DB->get_records_sql($sql, $params);
    }
	else 
		return 0; 
}



/**
 * This function returns records owned by user_id from table referentiel_activite for $referentiel_id
 *
 * @param id reference id , user id
 * @return objects
 * @todo Finish documenting this function
 **/
function referentiel_get_all_activites_user($referentiel_id, $user_id, $sql_filtre_where='', $sql_filtre_order=''){
global $DB;
// DEBUG
	if (!empty($referentiel_id)){
		if ($sql_filtre_order==''){
			$sql_filtre_order='  date_creation DESC ';
		}
        $params = array("refid" => "$referentiel_id", "userid" => "$user_id");
		$sql = "SELECT * FROM {referentiel_activite} WHERE ref_referentiel=:refid AND userid=;userid  $sql_filtre_where ORDER BY $sql_filtre_order";
		// DEBUG
		// echo "<br>DEBUG :: lib.sql :: Ligne 2459 :: SQL&gt; $sql\n";
		return $DB->get_records_sql($sql, $params);
	}
	else 
		return 0; 
}

/**
 * This function returns records from table referentiel_activite for referentiel_instance_id and user_id
 *
 * @param id reference activite
 * @param select clause : ' AND champ=valeur,  ... '
 * @param order clause : ' champ ASC|DESC, ... '
 * @return objects
 * @todo Finish documenting this function
 **/
function referentiel_instance_get_activites_user($referentiel_instance_id, $user_id, $sql_filtre_where='', $sql_filtre_order=''){
global $DB;
	if (!empty($referentiel_instance_id)){
		if ($sql_filtre_order==''){
			$sql_filtre_order='  date_creation DESC ';
		}
        $params = array("refid" => "$referentiel_instance_id", "userid" => "$user_id");
        $sql="SELECT * FROM {referentiel_activite} WHERE ref_instance=:refid AND userid=:userid  $sql_filtre_where ORDER BY $sql_filtre_order";
		return $DB->get_records_sql($sql, $params);
	}
	else 
		return NULL; 
}


/**
 * This function returns records from table referentiel_activite
 *
 * @param id reference activite
 * @param select clause : ' AND champ=valeur,  ... '
 * @param order clause : ' champ ASC|DESC, ... ' 
 * @return objects
 * @todo Finish documenting this function
 **/
function referentiel_get_users_activites($referentiel_id, $sql_filtre_where='', $sql_filtre_order=''){
global $DB;
    if (!empty($referentiel_id)){
		if ($sql_filtre_order==''){
			$sql_filtre_order=' userid ASC ';
		}
		else{
			$sql_filtre_order=' userid ASC, '.$sql_filtre_order;
		}
		$params = array("refid" => "$referentiel_id");
        $sql="SELECT DISTINCT userid FROM {referentiel_activite} WHERE ref_referentiel=:refid  $sql_filtre_where ORDER BY $sql_filtre_order ";
		return $DB->get_records_sql($sql, $params);
	}
	else 
		return 0; 
}



/**
 * This function returns records from table referentiel_activite
 *
 * @param id reference activite
 * @return objects
 * @todo Finish documenting this function
 **/
function referentiel_get_teachers_activites($referentiel_id){
global $DB;
	if (!empty($referentiel_id)){
	    $params = array("refid" => "$referentiel_id");
        $sql="SELECT DISTINCT teacherid FROM {referentiel_activite} WHERE ref_referentiel=:refid ORDER BY teacherid ASC ";
		return $DB->get_records_sql($sql, $params);
	}
	else 
		return 0; 
}



/**
 * This function returns records from table referentiel_activite
 *
 * @param id reference activite
 * @param select clause : ' AND champ=valeur,  ... '
 * @param order clause : ' champ ASC|DESC, ... '
 * @return objects
 * @todo Finish documenting this function
 **/
function referentiel_get_activites_instance($referentiel_instance_id, $select="", $order=""){
global $DB;
	if (isset($referentiel_instance_id) && ($referentiel_instance_id>0)){
		if (empty($order)){
			$order= 'userid ASC, date_creation DESC ';
		}
		$params = array("refid" => "$referentiel_instance_id");
        $sql="SELECT * FROM {referentiel_activite} WHERE ref_instance=:refid  $select ORDER BY $order ";
        return $DB->get_records_sql($sql, $params);
    }

	else 
		return NULL; 
}



/**
 * This function returns records from table referentiel_activite
 *
 * @param id reference activite
 * @param select clause : ' AND champ=valeur,  ... '
 * @param order clause : ' champ ASC|DESC, ... '
 * @return objects
 * @todo Finish documenting this function
 **/
function referentiel_get_users_activites_instance($referentiel_instance_id, $user_id=0, $select='', $order=''){
global $DB;
	$where='';
	if (!empty($referentiel_instance_id)){
        $params = array("refid" => "$referentiel_instance_id", "userid" => "$user_id");
		if ($user_id!=0){
			$where= ' AND userid=:userid';
		}
		if (empty($order)){
			$order= 'userid ASC, date_creation DESC ';
		}
        $sql="SELECT DISTINCT userid FROM {referentiel_activite} WHERE ref_instance=:refid  $where  $select ORDER BY $order ";
		return $DB->get_records_sql($sql, $params);
	}
	else 
		return NULL; 
}



/**
 * This function returns records from table referentiel_activite
 *
 * @param id reference activite
 * @param select clause : ' AND champ=valeur,  ... '
 * @param order clause : ' champ ASC|DESC, ... '
 * @return objects
 * @todo Finish documenting this function
 **/
function referentiel_get_activites($referentiel_id, $select="", $order=""){
global $DB;
	if (!empty($referentiel_id)){
	    $params = array("refid" => "$referentiel_id");

		if (empty($order)){
			$order= 'userid ASC, date_creation DESC ';
		}
		$sql="SELECT * FROM {referentiel_activite} WHERE ref_referentiel=:refid  $select ORDER BY $order ";
		return $DB->get_records_sql($sql, $params);
	}
	else 
		return 0; 
}

/**
 * This function returns record document from table referentiel_document
 *
 * @param id ref_activite
 * @return objects
 * @todo Finish documenting this function
 **/
function referentiel_get_documents($activite_id){
global $DB;
	if (!empty($activite_id)){
	    $params = array("id" => "$activite_id");
        $sql="SELECT * FROM {referentiel_document} WHERE ref_activite=:id ORDER BY id ASC ";
    	return $DB->get_records_sql($sql, $params);
    }
	else 
		return NULL;
}

/**
 * This function returns number of document from table referentiel_document
 *
 * @param id ref_activite
 * @return objects
 * @todo Finish documenting this function
 **/
function referentiel_nombre_documents($activite_id){
global $DB;
	if (!empty($activite_id)){
	    $params = array("id" => "$activite_id");
        $sql="SELECT * FROM {referentiel_document} WHERE ref_activite=:id ";
		$r=$DB->get_records_sql($sql, $params);
        // print_r($r) ;
        return (count($r));
	}
	else
		return 0;
}

//function referentiel_user_can_addactivity($referentiel, $currentgroup, $groupmode) {
function referentiel_user_can_addactivity($referentiel) {
    global $USER;

    if (!$cm = get_coursemodule_from_instance('referentiel', $referentiel->id, $referentiel->course)) {
        print_error('Course Module ID was incorrect');
    }
    $context = get_context_instance(CONTEXT_MODULE, $cm->id);

    if (!has_capability('mod/referentiel:write', $context)) {
        return false;
    }
/*
    if (!$groupmode or has_capability('moodle/site:accessallgroups', $context)) {
        return true;
    }

    if ($currentgroup) {
        return ismember($currentgroup);
    } else {
        //else it might be group 0 in visible mode
        if ($groupmode == VISIBLEGROUPS){
            return true;
        } else {
            return false;
        }
    }
*/
	return true;
}


function referentiel_activite_isowner($id){
global $USER;
global $DB;
	if (isset($id) && ($id>0)){
		$record=$DB->get_record("referentiel_activite", array("id" => "$id"));
		// DEBUG
		// echo "<br >USERID : $USER->id ; OWNER : $record->userid\n";
		return ($USER->id == $record->userid);
	}
	else 
		return false; 
} 



/**
 * This function return course link
 *
 * @param courseid reference course id
 * @return string
 * @todo Finish documenting this function
 **/
function referentiel_get_course_link($courseid, $complet=false){
global $CFG;
global $DB;
	if ($courseid){
		$that_course=$DB->get_record("course", array("id" => "$courseid"));
		if ($that_course){
            if ($complet){
    			return '<a href="'.$CFG->wwwroot.'/course/view.php?id='.$that_course->id.'" target="_blank">'.$that_course->shortname.'</a> ';
            }
			else{
                return '<a href="'.$CFG->wwwroot.'/course/view.php?id='.$that_course->id.'">'.$that_course->shortname.'</a> ';
            }
		}
	}
	return '';
}


// COMPETENCES

// Liste des codes de competences du referentiel
function referentiel_get_liste_codes_competence($id){
// retourne la liste des codes de competences pour la table referentiel
global $DB;
	if (!empty($id)){
        $params = array("id" => "$id");
        $sql="SELECT * FROM {referentiel_referentiel} WHERE id=:id";
		$record_r=$DB->get_record_sql($sql, $params);
		if ($record_r){
    		// afficher
			// DEBUG
			// echo "<br/>DEBUG ::<br />\n";
			// print_r($record_r);
			return ($record_r->liste_codes_competence);
		}
	}
	return 0;
}


// Liste des codes de competences du referentiel
function referentiel_new_liste_codes_competence($id){
// regenere la liste des codes de competences pour la table referentiel
global $DB;
$liste_codes_competence="";
	if (!empty($id)){
        $params = array("id" => "$id");
        $sql="SELECT * FROM {referentiel_referentiel} WHERE id=:id";
		$record_r=$DB->get_record_sql($sql, $params);
		if ($record_r){
    		// afficher
			// DEBUG
			// echo "<br/>DEBUG ::<br />\n";
			// print_r($record_r);
			$old_liste_codes_competence=$record_r->liste_codes_competence;
			$liste_codes_competence="";
			// charger les domaines associes au referentiel courant
			$referentiel_id=$id; // plus pratique
			// LISTE DES DOMAINES
            $sql2="SELECT * FROM {referentiel_domaine} WHERE ref_referentiel=:id  ORDER BY num_domaine ASC ";
			$records_domaine = $DB->get_records_sql($sql2, $params);
			if ($records_domaine){
    			// afficher
				// DEBUG
				// echo "<br/>DEBUG ::<br />\n";
				// print_r($records_domaine);
				foreach ($records_domaine as $record_d){
                    $params3 = array("id" => "$record_d->id");
                    $sql3="SELECT * FROM {referentiel_competence} WHERE ref_domaine=:id ORDER BY num_competence ASC ";
					$records_competences = $DB->get_records_sql($sql3, $params3);
			   		if ($records_competences){
						// DEBUG
						// echo "<br/>DEBUG :: COMPETENCES <br />\n";
						// print_r($records_competences);
						foreach ($records_competences as $record_c){
							// ITEM
							$compteur_item=0;
                            $params4 = array("id" => "$record_c->id");
                            $sql4="SELECT * FROM {referentiel_item_competence} WHERE ref_competence=:id ORDER BY num_item ASC ";
							$records_items = $DB->get_records_sql($sql4, $params4);
					    	if ($records_items){
								// DEBUG
								// echo "<br/>DEBUG :: ITEMS <br />\n";
								// print_r($records_items);
								foreach ($records_items as $record_i){
									$liste_codes_competence.=$record_i->code_item."/";
								}
							}
						}
					}
				}
			}
		}
	}
	return $liste_codes_competence;
}

// --------------------
function referentiel_set_liste_codes_competence($id, $liste_codes_competence){
global $DB;
	if (isset($id) && ($id>0)){
        $liste_codes_competence=($liste_codes_competence);
        $ok=$DB->set_field("referentiel_referentiel", "liste_codes_competence", "$liste_codes_competence", array("id" => "$id"));
        if ($ok) return 1;
	}
	return 0;
}



// Liste des poids de competences du referentiel
function referentiel_get_liste_poids_competence($id){
// retourne la liste des poids de competences pour la table referentiel
// MODIF JF 2009/10/16
global $DB;
	if (!empty($id)){
	    $params = array("id" => "$id");
        $sql= "SELECT * FROM {referentiel_referentiel} WHERE id=:id";
		$record_r=$DB->get_record_sql($sql, $params);
		if ($record_r){
    		// afficher
			// DEBUG
			// echo "<br/>DEBUG ::<br />\n";
			// print_r($record_r);
			if ($record_r->liste_poids_competence==''){
				$record_r->liste_poids_competence=referentiel_new_liste_poids_competence($id);
				referentiel_set_liste_poids_competence($id, $record_r->liste_poids_competence);
			}
			return ($record_r->liste_poids_competence);
		}
	}
	return 0;
}


// Liste des poids de competences du referentiel
function referentiel_new_liste_poids_competence($id){
// regenere la liste des poids de competences pour la table referentiel
global $DB;
$liste_poids_competence="";
	if (!empty($id)){
	    $params = array("id" => "$id");
        $sql="SELECT * FROM {referentiel_referentiel} WHERE id=:id";
		$record_r=$DB->get_record_sql($sql, $params);
		if ($record_r){
    		// afficher
			// DEBUG
			// echo "<br/>DEBUG ::<br />\n";
			// print_r($record_r);
			$old_liste_poids_competence=$record_r->liste_poids_competence;
			$liste_poids_competence="";
			// charger les domaines associes au referentiel courant
			// LISTE DES DOMAINES
			$sql2="SELECT * FROM {referentiel_domaine} WHERE ref_referentiel=:id ORDER BY num_domaine ASC";
			$records_domaine = $DB->get_records_sql($sql2, $params);
			if ($records_domaine){
    			// afficher
				// DEBUG
				// echo "<br/>DEBUG ::<br />\n";
				// print_r($records_domaine);
				foreach ($records_domaine as $record_d){
        			$params3 = array("id" => "$record_d->id");
                    $sql3="SELECT * FROM {referentiel_competence} WHERE ref_domaine=:id ORDER BY num_competence ASC";
					$records_competences = $DB->get_records_sql($sql3, $params3);
			   		if ($records_competences){
						// DEBUG
						// echo "<br/>DEBUG :: COMPETENCES <br />\n";
						// print_r($records_competences);
						foreach ($records_competences as $record_c){
							$params4 = array("id" => "$record_c->id");
                            $sql4="SELECT * FROM {referentiel_item_competence} WHERE ref_competence=:id ORDER BY num_item ASC ";
							// ITEM
							$compteur_item=0;
							$records_items = $DB->get_records_sql($sql4, $param4);
					    	if ($records_items){
								// DEBUG
								// echo "<br/>DEBUG :: ITEMS <br />\n";
								// print_r($records_items);
								foreach ($records_items as $record_i){
									$liste_poids_competence.=$record_i->poids_item."/";
								}
							}
						}
					}
				}
			}
		}
	}
	return $liste_poids_competence;
}

/**
 * Given an id referentiel_referentiel and a list of poids
 * this function will update liste_poids_competence.
 *
 * @param id, list
 * @return boolean Success/Fail
 **/
 
function referentiel_set_liste_poids_competence($id, $liste_poids_competence){
global $DB;
	if (isset($id) && ($id>0)){
        $ok=$DB->set_field("referentiel_referentiel", "liste_poids_competence", "$liste_poids_competence", array("id" => "$id"));
        if ($ok) return 1;
	}
	return 0;
}

/**
 * Given an array , 
 * return a new liste_codes_competence.
 *
 * @param array $instance An object from the form in mod_activite.html
 * @return string
 **/
function reference_conversion_code_2_liste_competence($separateur, $tab_code_item){
$lc="";
// print_r($tab_code_item);
// echo "<br />DEBUG\n";

	if (count($tab_code_item)>0){
		for ($i=0; $i<count($tab_code_item); $i++){
			$lc.=$tab_code_item[$i].$separateur;
		}
	}
	return $lc;
}


// Liste des empreintes de competences du rÃ©fÃ©rentiel
function referentiel_get_liste_empreintes_competence($id){
// retourne la liste des empreintes de competences pour la table referentiel
global $DB;
	if (!empty($id)){
	    $params = array("id" => "$id");
	    $sql="SELECT * FROM {referentiel_referentiel} WHERE id=:id";
		$record_r=$DB->get_record_sql($sql, $params);
		if ($record_r){
    		// afficher
			// DEBUG
			// echo "<br/>DEBUG ::<br />\n";
			// print_r($record_r);
			return ($record_r->liste_empreintes_competence);
		}
	}
	return 0;
}


// Liste des empreintes de competences du referentiel
function referentiel_new_liste_empreintes_competence($id){
// regenere la liste des empreintes de competences pour la table referentiel
global $DB;
$liste_empreintes_competence="";
	if (!empty($id)){
	    $params = array("id" => "$id");
	    $sql="SELECT * FROM {referentiel_referentiel} WHERE id=:id";
		$record_r=$DB->get_record_sql($sql, $params);
		if ($record_r){
    		// afficher
			// DEBUG
			// echo "<br/>DEBUG ::<br />\n";
			// print_r($record_r);
			$old_liste_empreintes_competence=$record_r->liste_empreintes_competence;
			$liste_empreintes_competence="";
			// charger les domaines associes au referentiel courant

			// LISTE DES DOMAINES
			$params2= array("refid" => "$id");
            $sql2="SELECT * FROM {referentiel_domaine} WHERE ref_referentiel=:refid ORDER BY num_domaine ASC";
			$records_domaine = $DB->get_records_sql($sql2, $params2);
			if ($records_domaine){
    			// afficher
				// DEBUG
				// echo "<br/>DEBUG ::<br />\n";
				// print_r($records_domaine);
				foreach ($records_domaine as $record_d){
        			$params3 = array("id" => "$record_d->id");
                    $sql3="SELECT * FROM {referentiel_competence} WHERE ref_domaine=:id ORDER BY num_competence ASC";
					$records_competences = $DB->get_records_sql($sql3, $params3);
			   		if ($records_competences){
						// DEBUG
						// echo "<br/>DEBUG :: COMPETENCES <br />\n";
						// print_r($records_competences);
						foreach ($records_competences as $record_c){
                  			$params4 = array("id" => "$record_c->id");
                            $sql4="SELECT * FROM {referentiel_item_competence} WHERE ref_competence=:id ORDER BY num_item ASC";
							// ITEM
							$compteur_item=0;
							$records_items = $DB->get_records_sql($sql4, $params4);
					    	if ($records_items){
								// DEBUG
								// echo "<br/>DEBUG :: ITEMS <br />\n";
								// print_r($records_items);
								foreach ($records_items as $record_i){
									$liste_empreintes_competence.=$record_i->empreinte_item."/";
								}
							}
						}
					}
				}
			}
		}
	}
	return $liste_empreintes_competence;
}

/**
 * Given an id referentiel_referentiel and a list of empreintes
 * this function will update liste_empreintes_competence.
 *
 * @param id, list
 * @return boolean Success/Fail
 **/
function referentiel_set_liste_empreintes_competence($id, $liste_empreintes_competence){
global $DB;
	if (isset($id) && ($id>0)){
        $ok=$DB->set_field('referentiel_referentiel','liste_empreintes_competence', "$liste_empreintes_competence", array("id" => "$id"));
        if ($ok) return 1;
	}
	return 0;
}


// TACHES 
// -----------------------
function referentiel_user_can_use_task($referentiel) {
    global $USER;

    if (!$cm = get_coursemodule_from_instance('referentiel', $referentiel->id, $referentiel->course)) {
        print_error('Course Module ID was incorrect');
    }
    $context = get_context_instance(CONTEXT_MODULE, $cm->id);

    if (has_capability('mod/referentiel:addtask', $context) || has_capability('mod/referentiel:viewtask', $context)) {
        return true;
    }
	else{
		return false;
	}
}



// URL

    /**
     * display an url accorging to moodle file mangement 
     * @return string active link
	 * @ input $url : an uri
	 * @ input $etiquette : a label 
     */
    function referentiel_affiche_url($url, $etiquette="", $cible="") {
	global $CFG; global $DB;
		if ($etiquette==""){
			$l=strlen($url);
			$posr=strrpos($url,'/');
			if ($posr===false){ // pas de separateur
				$etiquette=$url;
			}
			else if ($posr==$l-1){ // separateur en fin
				$etiquette=get_string("etiquette_inconnue", "referentiel");
			}
			else if ($posr==0){ // separateur en tete et en fin !
				$etiquette=get_string("etiquette_inconnue", "referentiel");
			}
			else {
				$etiquette=substr($url,$posr+1);
			}
		}
		$importfile = "{$CFG->dataroot}/{$url}";
		if (file_exists($importfile)) {
	        if ($CFG->slasharguments) {
    	    	$efile = "{$CFG->wwwroot}/file.php/$url";
        	}
		    else {
				$efile = "{$CFG->wwwroot}/file.php?file=/$url";
        	}
		}
		else{
			$efile = "$url";
		}
		
		return "<a href=\"$efile\" target=\"".$cible."\">$etiquette</a>";
    }
    

    /**
     * display an url accorging to moodle file mangement
     * @return string active link
	 * @ input $url : an uri
	 * @ input $etiquette : a label
     */
    function referentiel_get_url($url, $etiquette="", $cible="") {
	global $CFG; global $DB;
		if ($etiquette==""){
			$l=strlen($url);
			$posr=strrpos($url,'/');
			if ($posr===false){ // pas de separateur
				$etiquette=$url;
			}
			else if ($posr==$l-1){ // separateur en fin
				$etiquette=get_string("etiquette_inconnue", "referentiel");
			}
			else if ($posr==0){ // separateur en tete et en fin !
				$etiquette=get_string("etiquette_inconnue", "referentiel");
			}
			else {
				$etiquette=substr($url,$posr+1);
			}
		}
		$importfile = "{$CFG->dataroot}/{$url}";
		if (file_exists($importfile)) {
	        if ($CFG->slasharguments) {
    	    	$efile = "{$CFG->wwwroot}/file.php/$url";
        	}
		    else {
				$efile = "{$CFG->wwwroot}/file.php?file=/$url";
        	}
		}
		else{
			$efile = "$url";
		}
        if ($cible){
    		return '<a href=\"'.$efile.'\" target=\"'.$cible.'\">'.$etiquette.'</a>';
        }
        else{
    		return '<a href='.$efile.'>'.$etiquette.'</a>';
        }
    }

	
// TRAITEMENT DES LISTES code, poids, empreintes

/**
 purge 
*/
function referentiel_purge_dernier_separateur($s, $sep){
	if ($s){
		$s=trim($s);
		if ($sep){
			$pos = strrpos($s, $sep);
			if ($pos === false) { // note : trois signes Ã©gal  
				// pas trouvÃ©
			}
			else{
				// supprimer le dernier "/"
				if ($pos==strlen($s)-1){
					return substr($s,0, $pos);
				}
			}
		}
	}
	return $s;
}



// ----------------
function referentiel_purge_caracteres_indesirables($texte){
	$cherche = array(",", "\"", "'","\r\n", "\n", "\r");
	$remplace= array(" ", " ", " " , " ", " ", " ");
	return str_replace($cherche, $remplace, $texte);
}


function referentiel_initialise_descriptions_items_referentiel($referentiel_referentiel_id){
// calcule la table des descriptions des items de competences
// necessaire Ã  l'affichage des overlib
global $t_item_code; // codes
global $t_item_description_competence; // descriptifs
	$t_item_code=array();
	$t_item_description_competence=array(); // table des descriptions d'item
	$compteur_domaine=0;
	$compteur_competence=0;
	$compteur_item=0;
	
	// ITEMS
	if (isset($referentiel_referentiel_id) && ($referentiel_referentiel_id>0)){
		$record_a = referentiel_get_referentiel_referentiel($referentiel_referentiel_id);
		$code_referentiel=$record_a->code_referentiel;
		$nb_domaines = $record_a->nb_domaines;
		$liste_codes_competence=$record_a->liste_codes_competence;
		// charger les domaines associes au referentiel courant
		// DOMAINES
		$records_domaine = referentiel_get_domaines($referentiel_referentiel_id);
	   	if ($records_domaine){
			foreach ($records_domaine as $record){
				$domaine_id=$record->id;
				$nb_competences = $record->nb_competences;
				// LISTE DES COMPETENCES DE CE DOMAINE
				$records_competences = referentiel_get_competences($domaine_id);
		    	if ($records_competences){
					foreach ($records_competences as $record_c){
						$competence_id=$record_c->id;
						$nb_item_competences=$record_c->nb_item_competences;
						// ITEM
						$records_items = referentiel_get_item_competences($competence_id);
					    if ($records_items){
							foreach ($records_items as $record_i){
								$t_item_code[$compteur_item]=stripslashes($record_i->code_item);
								$t_item_description_competence[$t_item_code[$compteur_item]]=referentiel_purge_caracteres_indesirables(stripslashes($record_i->description_item));
								$compteur_item++;
							}
						}
						$compteur_competence++;
					}
				}
				$compteur_domaine++;
			}
		}
	}
	return ($compteur_item>0);
}




/**
 * This function sets referentiel_referentiel contents in arrays
 *
 * @param id
 * @return int
 * @todo Finish documenting this function
 **/
function referentiel_initialise_data_referentiel($referentiel_referentiel_id, $mode_calcul=0){
	if ($mode_calcul==0){
		return (referentiel_initialise_data_referentiel_new($referentiel_referentiel_id));
	}
	else{
		return (referentiel_initialise_data_referentiel_old($referentiel_referentiel_id));
	}
}
/**
 * This function sets referentiel_referentiel contents in arrays
 *
 * @param id
 * @return int
 * @todo Finish documenting this function
 **/

function referentiel_initialise_data_referentiel_new($referentiel_referentiel_id){
/*
Je comprends mieux maintenant ton approche.
Finalement ce que j'appelais POIDS est pour toi quelque chose  comme 
EMPREINT * POIDS
et cela donne pour ta formule
SOMME(V / E * P * E) / SOMME(P * E)  =  SOMME(V *  P) / SOMME(P * E)
qui comme tu le disais est Ã©quivalent Ã 
SOMME(V / E * P ) / SOMME(P ) si E identique partout...
*/
// calcule la table des coefficients poids/empreintes pour les item, competences, domaines
// necessaire Ã  l'affichage de la liste des 'notes' dans un certificat (pourcentages de competences validees) 
// return true or false
global $t_domaine;
global $t_domaine_coeff;
global $t_competence;
global $t_competence_coeff;
global $t_item_code;
global $t_item_description_competence; // descriptifs
global $t_item_coeff; // coefficients
global $t_item_domaine; // index du domaine associÃ© Ã  un item 
global $t_item_competence; // index de la competence associÃ©e Ã  un item 
global $t_item_poids;
global $t_item_empreinte;
global $t_nb_item_domaine;
global $t_nb_item_competence;

	$cherche=array();
	$remplace=array(); 
	$compteur_domaine=0;
	$compteur_competence=0;
	$compteur_item=0;
	
	// DOMAINES
	$t_domaine=array();
	$t_domaine_coeff=array();
	
	// COMPETENCES
	$t_competence=array();
	$t_competence_coeff=array();

	// ITEMS
	$t_item_domaine=array(); // table des domaines d' item
	$t_item_competence=array(); // table des competences d' item
	$t_item_description_competence=array(); // table des descriptions d'item
	$t_item_code=array();
	$t_item_poids=array();
	$t_item_empreinte=array();
	$t_item_coeff=array(); // poids / empreinte
	$t_nb_item_domaine=array(); // nb item dans le domaine
	$t_nb_item_competence=array(); // nb items dans la competence

	if (isset($referentiel_referentiel_id) && ($referentiel_referentiel_id>0)){
		$record_a = referentiel_get_referentiel_referentiel($referentiel_referentiel_id);
		$code_referentiel=$record_a->code_referentiel;
		$seuil_certificat = $record_a->seuil_certificat;
		$nb_domaines = $record_a->nb_domaines;
		$liste_codes_competence=$record_a->liste_codes_competence;
		$liste_empreintes_competence=$record_a->liste_empreintes_competence;
		/*
		echo "<br>DEBUG :: lib.php :: Ligne 1959 :: ".$code_referentiel." ".$seuil_certificat."\n";
		echo "<br>CODES : ".$liste_codes_competence." EMPREINTES : ".$liste_empreintes_competence."\n";
		echo "<br><br>".referentiel_affiche_liste_codes_empreintes_competence('/', $liste_codes_competence, $liste_empreintes_competence); 
		*/
		// charger les domaines associes au referentiel courant
		// DOMAINES

		$records_domaine = referentiel_get_domaines($referentiel_referentiel_id);
	   	if ($records_domaine){
    		// afficher
			// DEBUG
			// echo "<br/>DEBUG ::<br />\n";
			// print_r($records_domaine);
			
			foreach ($records_domaine as $record){
				$domaine_id=$record->id;
				$nb_competences = $record->nb_competences;
				$t_domaine[$compteur_domaine]=stripslashes($record->code_domaine);
				$t_nb_item_domaine[$compteur_domaine]=0;
				
				// LISTE DES COMPETENCES DE CE DOMAINE
				$records_competences = referentiel_get_competences($domaine_id);
		    	if ($records_competences){
					// DEBUG
					// echo "<br/>DEBUG :: COMPETENCES <br />\n";
					// print_r($records_competences);
					foreach ($records_competences as $record_c){
       					$competence_id=$record_c->id;
						$nb_item_competences=$record_c->nb_item_competences;
						$t_competence[$compteur_competence]=stripslashes($record_c->code_competence);
						$t_nb_item_competence[$compteur_competence]=0;
						
						// ITEM
						$records_items = referentiel_get_item_competences($competence_id);
					    if ($records_items){
							foreach ($records_items as $record_i){
								$t_item_code[$compteur_item]=stripslashes($record_i->code_item);
								$t_item_description_competence[$t_item_code[$compteur_item]]=referentiel_purge_caracteres_indesirables(stripslashes($record_i->description_item));
								$t_item_poids[$compteur_item]=$record_i->poids_item;	
								$t_item_empreinte[$compteur_item]=$record_i->empreinte_item;
								$t_item_domaine[$compteur_item]=$compteur_domaine;
								$t_item_competence[$compteur_item]=$compteur_competence;
								$t_nb_item_domaine[$compteur_domaine]++;
								$t_nb_item_competence[$compteur_competence]++;
								$compteur_item++;
							}
						}
						$compteur_competence++;
					}
				}
				$compteur_domaine++;
			}
		}
		
		// consolidation
		// somme des poids pour les domaines
		for ($i=0; $i<count($t_domaine); $i++){
			$t_domaine_coeff[$i]=0.0;
		}
		for ($i=0; $i<count($t_item_poids); $i++){
			if (($t_item_poids[$i]) && ($t_item_empreinte[$i])){
				// $t_domaine_coeff[$t_item_domaine[$i]]+= ((float)$t_item_poids[$i] / (float)$t_item_empreinte[$i]);
				$t_domaine_coeff[$t_item_domaine[$i]]+= (float)$t_item_poids[$i] * (float)$t_item_empreinte[$i];
			}
		}
		
		// somme des poids pour les competences
		for ($i=0; $i<count($t_competence); $i++){
			$t_competence_coeff[$i]=0.0;
		}
		for ($i=0; $i<count($t_item_poids); $i++){
			if (($t_item_poids[$i]>0.0) && ($t_item_empreinte[$i]>0)){
				// $t_competence_coeff[$t_item_competence[$i]]+= ((float)$t_item_poids[$i] / (float)$t_item_empreinte[$i]);
				$t_competence_coeff[$t_item_competence[$i]]+= (float)$t_item_poids[$i] * (float)$t_item_empreinte[$i];
			}
		}
		
		// coefficient poids / empreinte pour les items
		for ($i=0; $i<count($t_competence); $i++){
			$t_item_coeff[$i]=0.0;
		}
		for ($i=0; $i<count($t_item_poids); $i++){
			if (($t_item_poids[$i]) && ($t_item_empreinte[$i])){
				$t_item_coeff[$i] = (float)$t_item_poids[$i];
			}
		}
	}
	return ($compteur_item>0);
}



/**
 * This function sets referentiel_referentiel contents in arrays
 *
 * @param id
 * @return int
 * @todo Finish documenting this function
 **/

function referentiel_initialise_data_referentiel_old($referentiel_referentiel_id){
// calcule la table des coefficients poids/empreintes pour les item, competences, domaines
// necessaire Ã  l'affichage de la liste des 'notes' dans un certificat (pourcentages de competences validees) 
// return true or false
/*
ALGO

SOMME(V / E * P ) / SOMME(P) 

*/
global $t_domaine;
global $t_domaine_coeff;
global $t_competence;
global $t_competence_coeff;
global $t_item_code;
global $t_item_description_competence; // descriptifs
global $t_item_coeff; // coefficients
global $t_item_domaine; // index du domaine associÃ© Ã  un item 
global $t_item_competence; // index de la competence associÃ©e Ã  un item 
global $t_item_poids;
global $t_item_empreinte;
global $t_nb_item_domaine;
global $t_nb_item_competence;

	$cherche=array();
	$remplace=array(); 
	$compteur_domaine=0;
	$compteur_competence=0;
	$compteur_item=0;
	
	// DOMAINES
	$t_domaine=array();
	$t_domaine_coeff=array();
	
	// COMPETENCES
	$t_competence=array();
	$t_competence_coeff=array();

	// ITEMS
	$t_item_domaine=array(); // table des domaines d' item
	$t_item_competence=array(); // table des competences d' item
	$t_item_description_competence=array(); // table des descriptions d'item
	$t_item_code=array();
	$t_item_poids=array();
	$t_item_empreinte=array();
	$t_item_coeff=array(); // poids / empreinte
	$t_nb_item_domaine=array(); // nb item dans le domaine
	$t_nb_item_competence=array(); // nb items dans la competence

	if (isset($referentiel_referentiel_id) && ($referentiel_referentiel_id>0)){
		$record_a = referentiel_get_referentiel_referentiel($referentiel_referentiel_id);
		$code_referentiel=$record_a->code_referentiel;
		$seuil_certificat = $record_a->seuil_certificat;
		$nb_domaines = $record_a->nb_domaines;
		$liste_codes_competence=$record_a->liste_codes_competence;
		$liste_empreintes_competence=$record_a->liste_empreintes_competence;
		/*
		echo "<br>DEBUG :: lib.php :: Ligne 1959 :: ".$code_referentiel." ".$seuil_certificat."\n";
		echo "<br>CODES : ".$liste_codes_competence." EMPREINTES : ".$liste_empreintes_competence."\n";
		echo "<br><br>".referentiel_affiche_liste_codes_empreintes_competence('/', $liste_codes_competence, $liste_empreintes_competence); 
		*/
		// charger les domaines associes au referentiel courant
		// DOMAINES

		$records_domaine = referentiel_get_domaines($referentiel_referentiel_id);
	   	if ($records_domaine){
    		// afficher
			// DEBUG
			// echo "<br/>DEBUG ::<br />\n";
			// print_r($records_domaine);
			
			foreach ($records_domaine as $record){
				$domaine_id=$record->id;
				$nb_competences = $record->nb_competences;
				$t_domaine[$compteur_domaine]=stripslashes($record->code_domaine);
				$t_nb_item_domaine[$compteur_domaine]=0;
				
				// LISTE DES COMPETENCES DE CE DOMAINE
				$records_competences = referentiel_get_competences($domaine_id);
		    	if ($records_competences){
					// DEBUG
					// echo "<br/>DEBUG :: COMPETENCES <br />\n";
					// print_r($records_competences);
					foreach ($records_competences as $record_c){
       					$competence_id=$record_c->id;
						$nb_item_competences=$record_c->nb_item_competences;
						$t_competence[$compteur_competence]=stripslashes($record_c->code_competence);
						$t_nb_item_competence[$compteur_competence]=0;
						
						// ITEM
						$records_items = referentiel_get_item_competences($competence_id);
					    if ($records_items){
							foreach ($records_items as $record_i){
								$t_item_code[$compteur_item]=stripslashes($record_i->code_item);
								$t_item_description_competence[$t_item_code[$compteur_item]]=referentiel_purge_caracteres_indesirables(stripslashes($record_i->description_item));
								$t_item_poids[$compteur_item]=$record_i->poids_item;	
								$t_item_empreinte[$compteur_item]=$record_i->empreinte_item;
								$t_item_domaine[$compteur_item]=$compteur_domaine;
								$t_item_competence[$compteur_item]=$compteur_competence;
								$t_nb_item_domaine[$compteur_domaine]++;
								$t_nb_item_competence[$compteur_competence]++;
								$compteur_item++;
							}
						}
						$compteur_competence++;
					}
				}
				$compteur_domaine++;
			}
		}
		
		// consolidation
		// somme des poids pour les domaines
		for ($i=0; $i<count($t_domaine); $i++){
			$t_domaine_coeff[$i]=0.0;
		}
		for ($i=0; $i<count($t_item_poids); $i++){
			if (($t_item_poids[$i]) && ($t_item_empreinte[$i])){
				// $t_domaine_coeff[$t_item_domaine[$i]]+= ((float)$t_item_poids[$i] / (float)$t_item_empreinte[$i]);
				$t_domaine_coeff[$t_item_domaine[$i]]+= (float)$t_item_poids[$i];
			}
		}
		
		// somme des poids pour les competences
		for ($i=0; $i<count($t_competence); $i++){
			$t_competence_coeff[$i]=0.0;
		}
		for ($i=0; $i<count($t_item_poids); $i++){
			if (($t_item_poids[$i]>0.0) && ($t_item_empreinte[$i]>0)){
				// $t_competence_coeff[$t_item_competence[$i]]+= ((float)$t_item_poids[$i] / (float)$t_item_empreinte[$i]);
				$t_competence_coeff[$t_item_competence[$i]]+= (float)$t_item_poids[$i];
			}
		}
		
		// coefficient poids / empreinte pour les items
		for ($i=0; $i<count($t_competence); $i++){
			$t_item_coeff[$i]=0.0;
		}
		for ($i=0; $i<count($t_item_poids); $i++){
			if (($t_item_poids[$i]) && ($t_item_empreinte[$i])){
				$t_item_coeff[$i] = ((float)$t_item_poids[$i] / (float)$t_item_empreinte[$i]);
			}
		}
	}
	return ($compteur_item>0);
}


// ----------------
function referentiel_affiche_tableau_1d_old($tab_1d){
// DEBUG
	if ($tab_1d){
		echo '<table border="1"><tr>'."\n";
		for ($i=0;$i<count($tab_1d); $i++){
			echo '<td>'.$tab_1d[$i].'</td>'."\n";
		}
		echo '</tr></table>'."\n";
	}
}

// ----------------
function referentiel_affiche_tableau_1d($tab_1d){
// DEBUG
	if ($tab_1d){
		echo '<table border="1"><tr>'."\n";
		foreach ($tab_1d as $val){
			echo '<td>'.$val.'</td>'."\n";
		}
		echo '</tr></table>'."\n";
	}
}

// ----------------
function referentiel_affiche_tableau($tab_1d){
// DEBUG
	if ($tab_1d){
		echo '<table border="1"><tr>'."\n";
		foreach ($tab_1d as $val){
			echo '<td>'.$val.'</td>'."\n";
		}
		echo '</tr></table>'."\n";
	}
}

// ------------------------------
function referentiel_affiche_data_referentiel($referentiel_referentiel_id, $params=NULL){
// 
global $OK_REFERENTIEL_DATA;
global $t_domaine;
global $t_domaine_coeff;
		
// COMPETENCES
global $t_competence;
global $t_competence_coeff;
		
// ITEMS
global $t_item_code;
global $t_item_description_competence;
global $t_item_coeff; // poids / empreinte
global $t_item_domaine; // index du domaine associÃ© Ã  un item 
global $t_item_competence; // index de la competence associÃ©e Ã  un item 
	if (!isset($OK_REFERENTIEL_DATA) || ($OK_REFERENTIEL_DATA==false)){
		$OK_REFERENTIEL_DATA=referentiel_initialise_data_referentiel($referentiel_referentiel_id);
	}
	if (isset($OK_REFERENTIEL_DATA) && ($OK_REFERENTIEL_DATA==true)){
		$label_d="";
		$label_c="";
		$label_i="";
		if (isset($params) && !empty($params)){
			if (isset($params->label_domaine)){
				$label_d=$params->label_domaine;
			}
			if (isset($params->label_competence)){
				$label_c=$params->label_competence;
			}
			if (isset($params->label_item)){
				$label_i=$params->label_item;
			}
		}
		
		// affichage
		// DOMAINES
		echo "<br />DOMAINES<br />\n";
		if (!empty($label_d)){
			p($label_d);
		}
		else {
			p(get_string('domaine','referentiel'));
		}
		
		echo '<br />'."\n";
		referentiel_affiche_tableau_1d($t_domaine);
		echo "<br />DOMAINES COEFF\n";
		referentiel_affiche_tableau_1d($t_domaine_coeff);
		
		echo "<br />COMPETENCES\n";
		if (!empty($label_c)){
			p($label_c);
		}
		else {
			p(get_string('competence','referentiel')) ;
		}
		echo '<br />'."\n";
		referentiel_affiche_tableau_1d($t_competence);
		echo "<br />COMPETENCES COEFF\n";
		referentiel_affiche_tableau_1d($t_competence_coeff);
		
		// ITEMS
		echo "<br />ITEMS\n";
		if (!empty($label_i)){
			p($label_i);
		}
		else {
			p(get_string('item_competence','referentiel')) ;
		}
		
		echo '<br />'."\n";
		echo "<br />CODES ITEM\n";
		referentiel_affiche_tableau_1d($t_item_code);
		echo "<br />DESCRIPTION ITEM\n";
		referentiel_affiche_tableau($t_item_description_competence);
		echo "<br />COMPETENCES COEFF\n";
		referentiel_affiche_tableau_1d($t_item_coeff);
		
		echo "<br />POIDS ITEM\n";
		referentiel_affiche_tableau_1d($t_item_poids);
		echo "<br />EMPREINTES ITEM\n";
		referentiel_affiche_tableau_1d($t_item_empreinte);
	}
}


// CERTIFICATS

function referentiel_genere_competences_declarees_vide($liste_competences){
//
// retourne une liste de la forme 
// input :: A.1.1:0/A.1.2:0/A.1.3:0/A.1.4:0/A.1.5:0/A.2.1:0 ...
// output A.1.1:0/A.1.2:0/A.1.3:0/A.1.4:0/A.1.5:0/A.2.1:0 ...
	// collecter les competences
	$jauge_competences_declarees='';
	$tcomp=explode("/", $liste_competences);
	while (list($key, $val) = each($tcomp)) {
		// echo "$key => $val\n";
		if ($val!=""){
			$jauge_competences_declarees.=$val.":0/";
		}
	}
	return $jauge_competences_declarees;
}

/**
 * This function get all competencies declared in activities and return a competencies list
 *
 * @param userid reference user id
 * @param $ref_referentiel reference a referentiel id (not an instance of it !)
 * @return bolean
 * @todo Finish documenting this function
 * algorithme : cumule pour chaque competences le nombre d'activitÃ©s oÃ¹ celle ci est validee
 **/
function referentiel_genere_certificat_liste_competences_declarees($userid, $ref_referentiel){
	$t_liste_competences_declarees=array();
	$t_competences_declarees=array();
	$t_competences_referentiel=array(); // les competences du referentiel
	
	$liste_competences_declarees=""; // la liste sous forme de string
	$jauge_competences_declarees=""; // la juge sous forme CODE_COMP_0:n0/CODE_COMP_1:n1/...
	// avec 0 si competence declaree 0 fois, n>0 sinon
	
	if (isset($userid) && ($userid>0) && isset($ref_referentiel) && ($ref_referentiel>0)){
		// liste des competences definies dans le referentiel
		$liste_competences_referentiel=referentiel_purge_dernier_separateur(referentiel_get_liste_codes_competence($ref_referentiel), "/");
		// DEBUG
		// echo "<br />DEBUG :: Ligne 2706 :: USERID : $userid :: REFERENTIEL : $ref_referentiel\n";
		
		$t_competences_referentiel=explode("/", $liste_competences_referentiel);
		// creer un tableau dont les indices sont les codes de competence
		while (list($key, $val) = each($t_competences_referentiel)) {    
			$t_competences_declarees[$val]=0;
		}
		// collecter les activites validees
		$select=" AND userid=".$userid." ";
		$order= ' id ASC ';
		$records_activite = referentiel_get_activites($ref_referentiel, $select, $order);
		if (!$records_activite){
			return referentiel_genere_competences_declarees_vide($liste_competences_referentiel);
		}
		// DEBUG
		// echo "<br />Debug :: lib.php :: Ligne 2721 \n";
		// print_r($records_activite);
		
		// collecter les competences
		foreach ($records_activite  as $activite){
			$t_liste_competences_declarees[]=referentiel_purge_dernier_separateur($activite->competences_activite, "/");
		}
 		for ($i=0; $i<count($t_liste_competences_declarees); $i++){
			$tcomp=explode("/", $t_liste_competences_declarees[$i]);
			while (list($key, $val) = each($tcomp)) {    
				// echo "$key => $val\n";
				if (isset($t_competences_declarees[$val])) $t_competences_declarees[$val]++;
			}
		}
		$i=0;
		while (list($key, $val) = each($t_competences_declarees)) {    
			// echo "$key => $val\n";
			if ((!is_numeric($key) && ($key!=""))  && ($val!="") && ($val>0)){
				$liste_competences_declarees.=$key."/";
			}
			$jauge_competences_declarees.=$key.":".trim($val)."/";
		}
	}
	// DEBUG
	// echo "<br />DEBUG :: Ligne lib.php :: 4055 :: $jauge_competences_declarees\n";

	return $jauge_competences_declarees; 
}



/**
 * This function get all valid competencies in activite and return a competencies list
 *
 * @param userid reference user id
 * @param $ref_referentiel reference a referentiel id (not an instance of it !)
 * @return bolean
 * @todo Finish documenting this function
 * algorithme : cumule pour chaque competences le nombre d'activitÃ©s oÃ¹ celle ci est validee
 **/
function referentiel_genere_certificat_liste_competences($userid, $ref_referentiel){
	$t_liste_competences_valides=array();
	$t_competences_valides=array();
	$t_competences_referentiel=array(); // les competences du referentiel
	
	$liste_competences_valides=""; // la liste sous forme de string
	$jauge_competences=""; // la juge sous forme CODE_COMP_0:n0/CODE_COMP_1:n1/...
	// avec 0 si competence valide 0 fois, n>0 sinon
	
	if (isset($userid) && ($userid>0) && isset($ref_referentiel) && ($ref_referentiel>0)){
		// liste des competences definies dans le referentiel
		$liste_competences_referentiel=referentiel_purge_dernier_separateur(referentiel_get_liste_codes_competence($ref_referentiel), "/");
		// DEBUG
		// echo "<br />DEBUG :: lib.php :: Ligne 7275 ::<br />USERID : $userid :: REFERENTIEL : $ref_referentiel<br>$liste_competences_referentiel\n";
		
		$t_competences_referentiel=explode("/", $liste_competences_referentiel);
		// creer un tableau dont les indices sont les codes de competence
		while (list($key, $val) = each($t_competences_referentiel)) {    
			$t_competences_valides[$val]=0;
		}
		// collecter les activites validees
		$select=" AND approved!=0 AND userid=".$userid." ";
		$order= ' id ASC ';
		$records_activite = referentiel_get_activites($ref_referentiel, $select, $order);
		if ($records_activite){
			// DEBUG
			// echo "<br />Debug :: lib.php :: Ligne 7288<br />COMPETENCES REFERENTIEL VALIDES AVANT :<br />\n";
			// print_r($t_competences_valides);
			
      // echo "<br />Debug :: lib.php :: Ligne 7291 :<br />ACTIVIE<br />\n";
			// print_r($records_activite);
			
			// collecter les competences
			foreach ($records_activite  as $activite){
				$t_liste_competences_valides[]=referentiel_purge_dernier_separateur($activite->competences_activite, "/");
  			// DEBUG
	   		// echo "<br />Debug :: lib.php :: Ligne 7298<br />COMPETENCES ACTIVITE :<br />".$activite->competences_activite."\n";
			}

			// print_r($t_liste_competences_valides);
			// exit;
 			
      for ($i=0; $i<count($t_liste_competences_valides); $i++){
				if ($t_liste_competences_valides[$i]){
          $tcomp=explode("/", $t_liste_competences_valides[$i]);
				  while (list($key, $val) = each($tcomp)) {    
					 // echo "$key => $val\n";
					 // if (isset($t_competences_valides[$val])) 
            $t_competences_valides[$val]++;
				  }
				}
			}
		}
		
		$i=0;
		while (list($key, $val) = each($t_competences_valides)) {    
			// echo "$key => $val\n";
			if ((!is_numeric($key) && ($key!=""))  && ($val!="") && ($val>0)){
				$liste_competences_valides.=$key."/";
			}
			$jauge_competences.=$key.":".trim($val)."/";
		}
	}
	
	// DEBUG
	// echo "<br />DEBUG :: Ligne 4123 :: $jauge_competences\n";

	return $jauge_competences; 
}


/**
 * This function returns record certificat from table referentiel_certificat
 *
 * @param userid reference user id of certificat
 * @param ref_referentiel reference referentiel id of certificat 
 * @return object
 * @todo Finish documenting this function
 **/
function referentiel_get_certificat_user($userid, $ref_referentiel){
global $DB;
	if (isset($userid) && ($userid>0) && isset($ref_referentiel) && ($ref_referentiel>0)){
		return $DB->get_record_sql("SELECT * FROM {referentiel_certificat} WHERE ref_referentiel=$ref_referentiel AND userid=$userid ");
	}
	else {
		return false; 
	}
}


/**
 * This function  create / update with valid competencies a certificat for the userid
 *
 * @param userid reference user id
 * @param $ref_referentiel reference a referentiel id (not an instance of it !)
 * @return bolean
 * @todo Finish documenting this function
 **/
function referentiel_genere_certificat($userid, $ref_referentiel){
global $DB;
	$certificat_id=0; // id du certificat cree / modifie
	if (isset($userid) && ($userid>0) && isset($ref_referentiel) && ($ref_referentiel>0)){
		// MODIF JF 28/11/2009
		$competences_activite=referentiel_genere_certificat_liste_competences_declarees($userid, $ref_referentiel);
		$competences_certificat=referentiel_genere_certificat_liste_competences($userid, $ref_referentiel);
		// DEBUG
		// echo "<br />DEBUG :: lib.php :: LIGNE 4194 :: $competences_activite\n";
		// echo "<br />DEBUG :: lib.php :: LIGNE 4195 :: $competences_certificat\n";
		
		if (
			($competences_certificat!="")
			||
			($competences_activite!="")
			){
			// si existe update
			if ($certificat=referentiel_get_certificat_user($userid, $ref_referentiel)){
				$certificat_id=$certificat->id;
				
				// update ?
				
				if ($certificat->verrou==0){
					$certificat->commentaire_certificat=($certificat->commentaire_certificat);
	                $certificat->synthese_certificat=($certificat->synthese_certificat);
					$certificat->decision_jury=($certificat->decision_jury);
					$certificat->evaluation=($certificat->evaluation);
					$certificat->competences_certificat=$competences_certificat;
					$certificat->competences_activite=$competences_activite;
					$certificat->evaluation=referentiel_evaluation($competences_certificat, $ref_referentiel);
					$certificat->valide=1;					
					if(!$DB->update_record("referentiel_certificat", $certificat)){
						// DEBUG 
						// echo "<br /> ERREUR UPDATE CERTIFICAT\n";
					}
				}
			}
			else {
				// sinon creer
				$certificat = new object();
				$certificat->competences_certificat=$competences_certificat;
				$certificat->competences_activite=$competences_activite;
				$certificat->commentaire_certificat="";
                $certificat->synthese_certificat="";
				$certificat->decision_jury="";
				$certificat->date_decision=0;
				$certificat->ref_referentiel=$ref_referentiel;
				$certificat->userid=$userid;	
				$certificat->teacherid=0;
				$certificat->verrou=0;
				$certificat->valide=1;
				$certificat->evaluation=referentiel_evaluation($competences_certificat, $ref_referentiel);
    			// DEBUG
				// print_object($certificat);
    			// echo "<br />";
				$certificat_id= $DB->insert_record("referentiel_certificat", $certificat);
			}
		}
	}
	return $certificat_id;
}

/**
 * This function modify referentiel_certificat list of competencies 
 *
 * @param liste_competences 'A.1.1/A.1.3/A.2.3/'
 * @param userid reference user id
 * @param referentiel_id reference referentiel
 * @return string certificat_jauge
 * A.1.1:0/A.1.2:1/A.1.3:2/A.1.4:0/A.1.5:0/A.2.1:1/A.2.2:1/A.2.3:1/A.3.1:0/A.3.2:0/A.3.3:0/A.3.4:0/B.1.1:0/B.1.2:0/B.1.3:0/B.2.1:0/B.2.2:0/B.2.3:1/B.2.4:0/B.3.1:0/B.3.2:0/B.3.3:0/B.3.4:0/B.3.5:0/B.4.1:1/B.4.2:1/B.4.3:0/
 * @todo Finish documenting this function
 **/
function referentiel_mise_a_jour_competences_certificat_user($liste_competences_moins, $liste_competences_plus, $userid, $referentiel_id, $approved, $modif_declaration=true, $modif_validation=false ){
global $DB;
// 	la liste sous forme de string
//  IN#1  : 'A.1.1/A.1.3/A.2.3/' 
//  IN#2  : '      A.1.3/A.2.3/A.3.1'

// 	la jauge sous forme CODE_COMP_0:n0/CODE_COMP_1:n1/...
//  avec 0 si competence valide 0 fois, n>0 sinon
//  GET  : 'A.1.1:1/A.1.2:1/A.1.3:2/A.1.4:0/A.1.5:0/A.2.1:1/A.2.2:1/A.2.3:1/A.3.1:0/A.3.2:0/A.3.3:0/A.3.4:0/B.1.1:0/B.1.2:0/B.1.3:0/B.2.1:0/B.2.2:0/B.2.3:1/B.2.4:0/B.3.1:0/B.3.2:0/B.3.3:0/B.3.4:0/B.3.5:0/B.4.1:1/B.4.2:1/B.4.3:0/'
//  la jauge sous forme CODE_COMP_0:n0/CODE_COMP_1:n1/...
//  PUT  : 'A.1.1:0/A.1.2:1/A.1.3:2/A.1.4:0/A.1.5:0/A.2.1:1/A.2.2:1/A.2.3:1/A.3.1:1/A.3.2:0/A.3.3:0/A.3.4:0/B.1.1:0/B.1.2:0/B.1.3:0/B.2.1:0/B.2.2:0/B.2.3:1/B.2.4:0/B.3.1:0/B.3.2:0/B.3.3:0/B.3.4:0/B.3.5:0/B.4.1:1/B.4.2:1/B.4.3:0/'	
//                -               =                                       =       +
	$debug=false;
	$certificat_id=0;
	
	// Competences validees
	$liste_competences_valides='';
	$jauge_competences='';	

	$t_competences_jauge=array();
	$t_competences_supprimees=array(); // les competences Ã  supprimer de la liste
	$t_competences_valides=array(); // les competences du certificat validees
	
	// Competences declarees
	$liste_jauge_declarees=''; // competences declarees dans les activites
	$t_competences_jauge_declarees=array();
	$t_competences_declarees=array(); // les competences du certificat declarees
	$jauge_competences_declarees='';
	
	// outils
	$t_jauge= array();
	$tcomp= array();
	
	// preparation
	// competences a supprimer 
	if ($liste_competences_moins!=''){
		// DEBUG
		if ($debug) echo "<br />COMPETENCES MOINS<br />\n";
		$liste_competences_moins=referentiel_purge_dernier_separateur($liste_competences_moins, "/");
	}
	// competences a ajouter 
	if ($liste_competences_plus!=''){
		if ($debug) echo "<br />COMPETENCES PLUS<br />\n";			
		$liste_competences_plus=referentiel_purge_dernier_separateur($liste_competences_plus, "/");
	}
	
	// DEBUG
	if ($debug) echo "<br />DEBUG :: lib.php :: Ligne 4346 :: USERID : $userid :: REFERENTIEL : $referentiel_id<br />LISTE MOINS : $liste_competences_moins <br />LISTE PLUS : $liste_competences_plus<br />\n";
	
	if (!referentiel_certificat_user_exists($userid, $referentiel_id)){
		// CREER ce certificat
		referentiel_genere_certificat($userid, $referentiel_id);
	}
	
	$certificat=referentiel_get_certificat_user($userid, $referentiel_id);
	
	if ($certificat){
		$certificat_id=$certificat->id;
		// DEBUG
		if ($debug) {
			echo "<br />DEBUG : lib.php :: Ligne 4315 :: CERTIFICAT<br /> ";
			print_object($certificat);
    		echo "<br />";
		}
		
		// Competences declarees
		if (!$modif_declaration){ // une validation ou une devalidation d'activite sans ajout ni suppression des competences
			$jauge_competences_declarees=$certificat->competences_activite; // Pas de changement
		}
		else{
			// mise Ã  jour des competences declarees
			$liste_competences_declarees=$certificat->competences_activite;
			if ($liste_competences_declarees!=''){
				$liste_competences_declarees=referentiel_purge_dernier_separateur($liste_competences_declarees, "/");
				// DEBUG
				//echo "<br />DEBUG :: lib.pho :: 4326 :: JAUGE GET : $liste_competences_declarees<br />\n";
				$t_competences_jauge_declarees=explode("/", $liste_competences_declarees); // [A.1.1:0]  [A.1.2:1] [A.1.3:2] [A.1.4:0] ...
				//echo "<br />TABLEAU JAUGE GET :<br />\n";
				//print_r($t_competences_jauge_activite);
				//echo "<br />\n";
				
				// creer et initialise un tableau dont les indices sont les codes de competence
				// echo "<br />JAUGE GET<br />\n";
				while (list($key, $val) = each($t_competences_jauge_declarees)) {
					//echo "$key => $val\n";
					$t_jauge=explode(':',$val);
					$t_competences_declarees[$t_jauge[0]]=$t_jauge[1]; // // [A.1.1]=0  [A.1.2]=1 [A.1.3]=2 [A.1.4]=0 
				}
				if ($debug) {
					echo "<br />TABLEAU COMPETENCES DECLAREEES AVANT SUPPRESSION :<br />\n";
					print_r($t_competences_declarees);
					echo "<br />\n";
				}
				
				// supprimer des competences 
				if ($liste_competences_moins!=''){
					$tcomp0=explode("/", $liste_competences_moins);
					while (list($key0, $val0) = each($tcomp0)) {    
						// echo "<br />$key0 => $val0\n";
						if ($t_competences_declarees[$val0]>0){
							$t_competences_declarees[$val0]--;
						}
					}
				}
				if ($debug) {
					echo "<br />TABLEAU COMPETENCES DECLAREEES APRES SUPPRESSION :<br />\n";
					print_r($t_competences_declarees);
				}
				// Ajouter des competences 
				if ($liste_competences_plus!=''){
					$tcomp1=explode("/", $liste_competences_plus);
					while (list($key1, $val1) = each($tcomp1)) {    
						//echo "<br />$key1 => $val1\n";
						$t_competences_declarees[$val1]++;
					}
				}
				if ($debug) {
					echo "<br />TABLEAU COMPETENCES DECLAREEES APRES AJOUT :<br />\n";
					print_r($t_competences_declarees);
				}
				// reconstitution de la jauge des competences declarees
				
				while (list($key2, $val2) = each($t_competences_declarees)) {
					// echo "<br>$key2 => $val2\n";
					if ((!is_numeric($key2) && ($key2!=""))  && ($val2!="") && ($val2>0)){
						$liste_jauge_declarees.=$key2."/";
					}
					$jauge_competences_declarees.=$key2.":".trim($val2)."/";
				}
			}
		}
		
		if ($debug) {
			echo "<br /><br />COMPETENCES DECLAREEES :<br />$jauge_competences_declarees<br />\n";
		}
		
		// Competences validees
		if (($certificat->verrou!=0) || (!$modif_validation)) { // une mise a jour des competences sans validation ou devalidation
			$jauge_competences=$certificat->competences_certificat; // Pas de changement
		}
		else{
			// sinon modification de la liste des competences validees
			$liste_jauge_competences=$certificat->competences_certificat;
			$liste_jauge_competences=referentiel_purge_dernier_separateur($liste_jauge_competences, "/");
			//
			$t_competences_jauge=explode("/", $liste_jauge_competences); // [A.1.1:0]  [A.1.2:1] [A.1.3:2] [A.1.4:0] ...
			if ($debug) {
				echo "<br />JAUGE CERTIFICAT : $liste_jauge_competences<br />\n";
				echo "<br />TABLEAU COMPETENCES CERTIFICAT :<br />\n";
				print_r($t_competences_jauge);
				echo "<br />\n";
			}
			// creer et initialise un tableau dont les indices sont les codes de competence
			// echo "<br />JAUGE GET<br />\n";
			while (list($key, $val) = each($t_competences_jauge)) {
				// echo "$key => $val\n";
				$t_jauge=explode(':',$val);
				$t_competences_valides[$t_jauge[0]]=$t_jauge[1]; // // [A.1.1]=0  [A.1.2]=1 [A.1.3]=2 [A.1.4]=0 
			}
			if ($debug) {
				echo "<br />TABLEAU COMPETENCES VALIDES AVANT SUPPRESSION :<br />\n";
				print_r($t_competences_valides);
				// echo "<br />lib.php :: EXIT ligne 4457\n";
				// exit;
			}
			
			// competences a supprimer 
			if ($liste_competences_moins!=''){
				$tcomp=explode("/", $liste_competences_moins);
				while (list($key1, $val1) = each($tcomp)) {    
					// echo "<br />$key1 => $val1\n";
					if ($t_competences_valides[$val1]>0){
						$t_competences_valides[$val1]--;
					}
				}
			}
			
			if ($debug) {
				echo "<br />TABLEAU COMPETENCES VALIDES APRES SUPPRESSION :<br />\n";
				print_r($t_competences_valides);
			}
			
			// competences a ajouter 
			if ($approved){ // on ajoute si l'activite est validee
				if ($liste_competences_plus!=''){
					$tcomp=explode("/", $liste_competences_plus);
					while (list($key1, $val1) = each($tcomp)) {    
						//echo "<br />$key1 => $val1\n";
						$t_competences_valides[$val1]++;
					}
				}
				
				if ($debug) {
					echo "<br />TABLEAU COMPETENCES VALIDES APRES AJOUT :<br />\n";
					print_r($t_competences_valides);
				}
			}
			
			// reconstitution de la jauge
			while (list($key2, $val2) = each($t_competences_valides)) {
				// echo "<br>$key2 => $val2\n";
				if ((!is_numeric($key2) && ($key2!=""))  && ($val2!="") && ($val2>0)){
					$liste_competences_valides.=$key2."/";
				}
				$jauge_competences.=$key2.":".trim($val2)."/";
			}
		}	
		
		// DEBUG
		if ($debug) {
			echo "<br />DEBUG :: lib.php :: Ligne 4499 :: USERID : $userid :: REFERENTIEL : $referentiel_id<br />LISTE COMPETENCES : $liste_competences_valides<br />JAUGE : $jauge_competences\n";
		}

		// mise a jour
		$certificat->commentaire_certificat=($certificat->commentaire_certificat);
        $certificat->synthese_certificat=($certificat->synthese_certificat);
		$certificat->decision_jury=($certificat->decision_jury);
		$certificat->evaluation=($certificat->evaluation);
		$certificat->competences_certificat=$jauge_competences;
		$certificat->competences_activite=$jauge_competences_declarees;
		$certificat->evaluation=referentiel_evaluation($certificat->competences_certificat, $referentiel_id);
		$certificat->valide=1;
		// DEBUG
		if ($debug) {
			echo "<br />DEBUG : lib.php :: Ligne 4519 <br /> ";
			print_object($certificat);
    		// echo "<br />lib.php :: EXIT LIGNE 4524";
			// exit;
		}
		
		if (!$DB->update_record("referentiel_certificat", $certificat)){
			// DEBUG 
			// echo "<br />DEBUG : lib_certificat :: Ligne 162  :: ERREUR UPDATE CERTIFICAT\n";
		}
	}
	return $certificat_id;
}


/**
 * This function returns records list of users from table referentiel_certificat
 *
 * @param userid reference user id
 * @param referentiel_id reference referentiel
 * @return object
 * @todo Finish documenting this function
 **/
function referentiel_certificat_user_exists($userid, $referentiel_id){
global $DB;
	if (isset($userid) && ($userid>0) && isset($referentiel_id) && ($referentiel_id>0)){
		$r=$DB->get_record_sql("SELECT * FROM {referentiel_certificat} WHERE ref_referentiel=$referentiel_id AND userid=$userid ");
		if ($r){
			// echo "<br />\n";
			// print_r($r);
			// MODIF JF 2009/11/28
			// controler la completude du certificat post version 4.1.1
			if (($r->competences_certificat!='') || ($r->competences_activite=='')){
				return 0;
			}
			else{
				return ($r->id);
			}
		}
	}
	return 0; 
}

/**
 * This function returns record certificate from table referentiel_certificat
 *
 * @param userid reference user id
 * @param referentiel_id reference referentiel
 * @return object
 * @todo Finish documenting this function
 **/
function referentiel_certificat_user($userid, $referentiel_id){
// Si certificat n'existe pas, cree le certificat et le retourne

	if (isset($userid) && ($userid>0) && isset($referentiel_id) && ($referentiel_id>0)){
		if (!referentiel_certificat_user_exists($userid, $referentiel_id)){
			if (referentiel_genere_certificat($userid, $referentiel_id)){
				return referentiel_get_certificat_user($userid, $referentiel_id);
			}
			else{
				return false;
			}
		}
		else{
			return referentiel_get_certificat_user($userid, $referentiel_id);
		}
	}
	else {
		return false; 
	}
}




/**
 * This function returns records list of users from table referentiel_certificat
 *
 * @param id reference certificat
 * @param select clause : ' AND champ=valeur,  ... '
 * @param order clause : ' champ ASC|DESC, ... ' 
 * @return objects
 * @todo Finish documenting this function
 **/
function referentiel_get_users_certificats($referentiel_id, $select="", $order=""){
global $DB;
	if (isset($referentiel_id) && ($referentiel_id>0)){
		if (empty($order)){
			$order= 'userid ASC ';
		}
		return $DB->get_records_sql("SELECT DISTINCT userid FROM {referentiel_certificat} WHERE ref_referentiel=$referentiel_id  $select ORDER BY $order ");
	}
	else 
		return 0; 
}


/**
 * This function returns records list of users from table referentiel_activite
 *
 * @param id reference certificat
 * @param select clause : ' AND champ=valeur,  ... '
 * @param order clause : ' champ ASC|DESC, ... ' 
 * @return objects
 * @todo Finish documenting this function
 **/
function referentiel_get_users_referentiel_cours($referentiel_id, $course_id, $select="", $order=""){
global $DB;
	if (isset($referentiel_id) && ($referentiel_id>0)){
		if (empty($order)){
			$order= 'userid ASC ';
		}
		return $DB->get_records_sql("SELECT DISTINCT userid FROM {referentiel_activite} WHERE ref_referentiel=$referentiel_id AND ref_course=$course_id $select ORDER BY $order ");
	}
	else 
		return 0; 
}

/**
 * This function returns records list of teachers from table referentiel_certificat
 *
 * @param id reference certificat
 * @return objects
 * @todo Finish documenting this function
 **/
function referentiel_get_teachers_certificats($referentiel_id){
global $DB;
	if (isset($referentiel_id) && ($referentiel_id>0)){
		return $DB->get_records_sql("SELECT DISTINCT teacherid FROM {referentiel_certificat} WHERE ref_referentiel=$referentiel_id ORDER BY teacherid ASC ");
	}
	else 
		return 0; 
}


/**
 * This function get a competencies list and return a float
 *
 * @param userid reference user id
 * @param $ref_referentiel reference a referentiel id (not an instance of it !)
 * @return bolean
 * @todo Finish documenting this function
 **/
function referentiel_evaluation($listecompetences, $referentiel_id){
//A.1.1:0/A.1.2:0/A.1.3:0/A.1.4:0/A.1.5:0/A.2.1:0/A.2.2:0/A.2.3:0/A.3.1:0/A.3.2:0/A.3.3:0/A.3.4:0/B.1.1:0/B.1.2:0/B.1.3:0/B.2.1:0/B.2.2:0/B.2.3:0/B.2.4:0/B.3.1:0/B.3.2:0/B.3.3:0/B.3.4:0/B.3.5:0/B.4.1:1/B.4.2:1/B.4.3:0/
	// DEBUG
	// echo "<br />LISTE ".$listecompetences."\n";
	$evaluation=0.0;
	$tcode=array();
	$tcode=explode("/",$listecompetences);
	for ($i=0; $i<count($tcode); $i++){
		$tvaleur=explode(":",$tcode[$i]);
		
		$code="";
		$svaleur="";
		
		if (isset($tvaleur[0])){ // le code
			$code=trim($tvaleur[0]);
		}
		if (isset($tvaleur[1])){ // la valeur
			$svaleur=trim($tvaleur[1]);
		} 
		// DEBUG
		// echo "<br />DEBUG :: lib.php : 2260 :: CODE : ".$code." VALEUR : ".$svaleur."\n";
		if (($code!="") && ($svaleur!="")){ 
			$poids=referentiel_get_poids_item($code, $referentiel_id);
			$empreinte=referentiel_get_empreinte_item($code, $referentiel_id);
			// echo "<br />POIDS : ".$poids."\n";
			if ($empreinte)
				$evaluation+= ( $poids * $svaleur / $empreinte);
			else
				$evaluation+= ( $poids * $svaleur);
		}
	}
	// echo "<br />EVALUATION : ".$evaluation."\n";
	return $evaluation;
}


/**
 * This function set all certificates
 *
 * @param $referentiel_instance reference an instance of referentiel !)
 * @return bolean
 * @todo Finish documenting this function
 **/
function referentiel_regenere_certificats($referentiel_instance){
	if ($referentiel_instance){
		$records_users=referentiel_get_course_users($referentiel_instance);
		// echo "<br /> lib.php :: referentiel_get_course_users() :: 2018<br />\n";		
		if ($records_users){
			foreach ($records_users as $record_u){
				// echo "<br />DEBUG :: lib.php :: LIGNE 2948 \n";
				// print_r($record_u);
				referentiel_regenere_certificat_user($record_u->id, $referentiel_instance->ref_referentiel);	
			}
		}
	}
}

/**
 * This function set all certificates
 *
 * @param $referentiel_instance reference an instance of referentiel !)
 * @return bolean
 * @todo Finish documenting this function
 **/
function referentiel_regenere_certificat_user($userid, $ref_referentiel){
	if ($ref_referentiel && $userid){
		if (!referentiel_certificat_user_exists($userid, $ref_referentiel)){
			// CREER ce certificat
			referentiel_genere_certificat($userid, $ref_referentiel);
		}
		if (!referentiel_certificat_user_valide($userid, $ref_referentiel)){ 
		// drapeau positionne par l'ancienne version <= 3 quand une activite est validee ou devalidee
		// n'est plus utilise car desormais on modifie directement la jauge du certificat dans la partie activite
			// METTRE A JOUR ce certificat
			referentiel_genere_certificat($userid, $ref_referentiel);
		}
	}
}


/**
 * Given an object containing all the necessary referentiel, 
 * (defined by the form in mod.html) this function 
 * will create a new instance and return the id number 
 * of the new instance.
 *
 * @param object $instance An object from the form in mod.html
 * @return int The id of the newly inserted referentiel record
 **/
function referentiel_add_certificat($form) {
// creation certificat
global $USER;
global $DB;
    // DEBUG
    //echo "DEBUG : ADD CERTIFICAT CALLED";
	//print_object($form);
    //echo "<br />";
	// referentiel
	$certificat = new object();
	$certificat->competences_certificat=$form->competences_certificat;
	$certificat->commentaire_certificat=$form->commentaire_certificat;
	$certificat->synthese_certificat=$form->synthese_certificat;
	if (!empty($form->decision_jury)){
        $certificat->date_decision=time();
    }
    else{
        $certificat->date_decision='';
    }
    $certificat->decision_jury=($form->decision_jury);
	$certificat->date_decision='';
	$certificat->ref_referentiel=$form->ref_referentiel;
	$certificat->userid=$USER->id;	
	$certificat->teacherid=$USER->id;
	$certificat->verrou=0;
	$certificat->valide=$form->valide;
	$certificat->evaluation=referentiel_evaluation($form->competences_certificat, $form->ref_referentiel);	


	$certificat->mailed=1; // MODIF JF 2010/10/05
	if (isset($form->mailnow)){
        $certificat->mailnow=$form->mailnow;
        if ($form->mailnow=='1'){ // renvoyer
            $certificat->mailed=0;   // annuler envoi precedent
        }
    }
    else{ 
        $certificat->mailnow=0;
    }	


    // DEBUG
	//print_object($certificat);
    //echo "<br />";
	
	$certificat_id= $DB->insert_record("referentiel_certificat", $certificat);
    // echo "certificat ID / $certificat_id<br />";
    // DEBUG
    return $certificat_id;
}

/**
 * Given an object containing all the necessary referentiel, 
 * (defined by the form in mod.html) this function 
 * will create a new instance and return the id number 
 * of the new instance.
 *
 * @param object $instance An object from the form in mod.html
 * @return int The id of the newly inserted referentiel record
 **/
function referentiel_update_certificat($form) {
global $DB;
global $DB;
// MAJ certificat
$ok=true;
    // DEBUG

    //echo "\nDEBUG : UPDATE CERTIFICAT CALLED:: 8035\n";
	//print_object($form);
    //echo "<br />\n";
    //exit;

    // certificat
	if (isset($form->action) && ($form->action=="modifier_certificat")){
		$certificat = new object();
		$certificat->id=$form->certificat_id;
		$certificat->commentaire_certificat=$form->commentaire_certificat;
        $certificat->synthese_certificat=$form->synthese_certificat;
		$certificat->competences_certificat=$form->competences_certificat;

        if (!empty($form->decision_jury_sel) && empty($form->decision_jury)){
            $form->decision_jury=$form->decision_jury_sel;
        }
		if (isset($form->decision_jury_old) && ($form->decision_jury_old!=$form->decision_jury)){
	       	$certificat->date_decision=time();
        }
        else{
            $certificat->date_decision=$form->date_decision;
        }
        $certificat->decision_jury=$form->decision_jury;

		$certificat->ref_referentiel=$form->ref_referentiel;
		$certificat->userid=$form->userid;
		$certificat->teacherid=$form->teacherid;
		$certificat->verrou=$form->verrou;
		$certificat->valide=$form->valide;
		$certificat->evaluation=referentiel_evaluation($form->competences_certificat, $form->ref_referentiel);	

		// MODIF JF 2010/02/11
		if (isset($form->mailnow)){
            $certificat->mailnow=$form->mailnow;
            if ($form->mailnow=='1'){ // renvoyer
                $certificat->mailed=0;   // annuler envoi precedent
            }
        }
        else{
            $certificat->mailnow=0;
        }
		
	    // DEBUG
		// print_object($certificat);
	    // echo "<br />";
		if(!$DB->update_record("referentiel_certificat", $certificat)){
			//echo "<br /> ERREUR UPDATE CERTIFICAT\n";
			$ok=false;
		}
		else {
			// echo "<br /> UPDATE CERTIFICAT $certificat->id\n";		
			$ok=true;
		}
		// exit;
		return $ok; 
	}
}

function referentiel_user_can_add_certificat($referentiel, $currentgroup, $groupmode) {
    global $USER;

    if (!$cm = get_coursemodule_from_instance('referentiel', $referentiel->id, $referentiel->course)) {
        print_error('Course Module ID was incorrect');
    }
    $context = get_context_instance(CONTEXT_MODULE, $cm->id);

    if (!has_capability('mod/referentiel:writecertificat', $context)) {
        return false;
    }

    if (!$groupmode or has_capability('moodle/site:accessallgroups', $context)) {
        return true;
    }

    if ($currentgroup) {
        return ismember($currentgroup);
    } else {
        //else it might be group 0 in visible mode
        if ($groupmode == VISIBLEGROUPS){
            return true;
        } else {
            return false;
        }
    }
}


function referentiel_certificat_isowner($id){
global $USER;
global $DB;
	if (isset($id) && ($id>0)){
		$record=$DB->get_record("referentiel_certificat", array("id" => "$id"));
		// DEBUG
		// echo "<br >USERID : $USER->id ; OWNER : $record->userid\n";
		if ($record){
            return ($USER->id == $record->userid);
        }
	}
	return false; 
} 


/**
 * Given a userid  and referentiel id
 * this function will set certificat valide to 0
 *
 * @param $userid user id
  * @param $ref_referentiel refrentiel id
 * @return 
 **/

function referentiel_certificat_user_invalider($userid, $ref_referentiel){
global $DB;
	if ($userid && $ref_referentiel){
		$certificat = $DB->get_record("referentiel_certificat", array("userid" => "$userid", "ref_referentiel" => "$ref_referentiel"));
		if ($certificat) {
			// if ($record->verrou==0) // BUG car en cas de deverrouillage les activites crees entretemps ne seraient pas prises en compte
				$certificat->valide=0; // le certificat doit Ãªtre recalcule
				// $certificat->competences_certificat='';
				// DEBUG
				// echo "<br />DEBUG : lip.php : ligne 3237<br />\n";
				// print_r($certificat);
				// echo "<br />\n";
				
				$certificat->commentaire_certificat=($certificat->commentaire_certificat);
                $certificat->synthese_certificat=($certificat->synthese_certificat);
				$certificat->decision_jury=($certificat->decision_jury);
				$certificat->evaluation=($certificat->evaluation);
	            $DB->update_record('referentiel_certificat', $certificat);
			// }
        }
	}
}

/**
 * Given a userid and referentiel id
 * this function will get valide 
 *
 * @param $userid user id
  * @param $ref_referentiel refrentiel id
 * @return 
 **/

function referentiel_certificat_user_valide($userid, $ref_referentiel){
global $DB;
	if ($userid && $ref_referentiel){
		$record = $DB->get_record("referentiel_certificat", array("userid" => "$userid", "ref_referentiel" => "$ref_referentiel"));
		if ($record) {
			return (($record->valide==1) or ($record->verrou==1));
        }
	}
	return false;
}


// -------------------------------
function referentiel_pourcentage($a, $b){
	if ($b!=0) return round(($a * 100.0) / (float)$b,1);
	else return NULL;
}


// -------------------
function referentiel_affiche_certificat_consolide($separateur1, $separateur2, $liste_code, $ref_referentiel, $bgcolor, $params=NULL){
// ce certificat comporte des pourcentages par domaine et competence
  echo referentiel_retourne_certificat_consolide($separateur1, $separateur2, $liste_code, $ref_referentiel, $bgcolor, $params=NULL);
}

// -------------------
function referentiel_retourne_certificat_consolide($separateur1, $separateur2, $liste_code, $ref_referentiel, $bgcolor, $params=NULL){
// ce certificat comporte des pourcentages par domaine et competence

global $OK_REFERENTIEL_DATA;
global $t_domaine;
global $t_domaine_coeff;
		
// COMPETENCES
global $t_competence;
global $t_competence_coeff;
		
// ITEMS
global $t_item_code;
global $t_item_coeff; // coefficient poids determeine par le modele de calcul (soit poids soit poids / empreinte)
global $t_item_domaine; // index du domaine associÃ© Ã  un item 
global $t_item_competence; // index de la competence associÃ©e Ã  un item 
global $t_item_poids; // poids
global $t_item_empreinte;
global $t_nb_item_domaine;
global $t_nb_item_competence;

	$s='';
	
	// nom des domaines, competences, items
	$label_d="";
	$label_c="";
	$label_i="";
	if (isset($params) && !empty($params)){
		if (isset($params->label_domaine)){
					$label_d=$params->label_domaine;
		}
		if (isset($params->label_competence)){
					$label_c=$params->label_competence;
		}
		if (isset($params->label_item)){
					$label_i=$params->label_item;
		}
	}
	$t_certif_item_valeur=array();	// table des nombres d'items valides 
	$t_certif_item_coeff=array(); // somme des poids du domaine
	$t_certif_competence_poids=array(); // somme des poids de la competence
	$t_certif_domaine_poids=array(); // poids certifies
	for ($i=0; $i<count($t_item_code); $i++){
		$t_certif_item_valeur[$i]=0.0;
		$t_certif_item_coeff[$i]=0.0;
	}
	for ($i=0; $i<count($t_competence); $i++){
		$t_certif_competence_poids[$i]=0.0;
	}
	for ($i=0; $i<count($t_domaine); $i++){
		$t_certif_domaine_poids[$i]=0.0;
	}
	// affichage

		
	// donnees globales du referentiel
	if ($ref_referentiel){
		
		if (!isset($OK_REFERENTIEL_DATA) || ($OK_REFERENTIEL_DATA==false) ){
			$OK_REFERENTIEL_DATA=referentiel_initialise_data_referentiel($ref_referentiel);
		}

		if (isset($OK_REFERENTIEL_DATA) && ($OK_REFERENTIEL_DATA==true)){
		// DEBUG 
		// echo "<br />CODE <br />\n";
		// referentiel_affiche_data_referentiel($ref_referentiel, $params);
		
		// recuperer les items valides
		$tc=array();
		$liste_code=referentiel_purge_dernier_separateur($liste_code, $separateur1);
			
		// DEBUG 
		// echo "<br />DEBUG :: print_lib_certificat.php :: 917 :: LISTE : $liste_code<br />\n";

		if (!empty($liste_code) && ($separateur1!="") && ($separateur2!="")){
			$tc = explode ($separateur1, $liste_code);
			
			// DEBUG 

		
			for ($i=0; $i<count($t_item_domaine); $i++){
				$t_certif_domaine_poids[$i]=0.0;
			}
			for ($i=0; $i<count($t_item_competence); $i++){
				$t_certif_competence_poids[$i]=0.0;
			}

			$i=0;
			while ($i<count($tc)){
				// CODE1:N1
				// DEBUG 
				// echo "<br />".$tc[$i]." <br />\n";
				// exit;
				$t_cc=explode($separateur2, $tc[$i]); // tableau des items valides
				
				// print_r($t_cc);
				// echo "<br />\n";
				// exit;
				if (isset($t_cc[1])){
					if (isset($t_item_poids[$i]) && isset($t_item_empreinte[$i])){
						if (($t_item_poids[$i]>0) && ($t_item_empreinte[$i]>0)){
							// echo "<br>".min($t_cc[1],$t_item_empreinte[$i]);
							$t_certif_item_valeur[$i]=min($t_cc[1],$t_item_empreinte[$i]);
							// calculer le taux
							$coeff=(float)$t_certif_item_valeur[$i] * (float)$t_item_coeff[$i];
							// stocker la valeur pour l'item
							$t_certif_item_coeff[$i]=$coeff;
							// stocker le taux pour la competence
							$t_certif_domaine_poids[$t_item_domaine[$i]]+=$coeff;
							// stocker le taux pour le domaine
							$t_certif_competence_poids[$t_item_competence[$i]]+=$coeff;
						}
						else{
							// echo "<br>".min($t_cc[1],$t_item_empreinte[$i]);
							$t_certif_item_valeur[$i]=0.0;
							$t_certif_item_coeff[$i]=0.0;
							// $t_certif_domaine_poids[$t_item_domaine[$i]]+=0.0;
							// $t_certif_competence_poids[$t_item_competence[$i]]+=0.0;
						}
					}
				}
				
				$i++;
			}
			
			// DEBUG 
			
			// DOMAINES
			$s.= '<table width="100%" cellspacing="0" cellpadding="2"><tr valign="top" >'."\n";
			// if (!empty($label_d)){
			//	$s.='<td  width="5%">'.$label_d.'</td>';
			//}
			// else {
			//	$s.='<td $t_certif_item_coeff width="5%">'.get_string('domaine','referentiel').'</td>';
			//}
			for ($i=0; $i<count($t_domaine_coeff); $i++){
				if ($t_domaine_coeff[$i]){
					$s.='<td  align="center" colspan="'.$t_nb_item_domaine[$i].'"><b>'.$t_domaine[$i].'</b> ('.referentiel_pourcentage($t_certif_domaine_poids[$i], $t_domaine_coeff[$i]).'%)</td>';
				}
				else{
					$s.='<td  align="center" colspan="'.$t_nb_item_domaine[$i].'"><b>'.$t_domaine[$i].'</b> (0%)</td>';
				}
			}
			$s.='</tr>'."\n";

			$s.=  '<tr valign="top"  >'."\n";
			for ($i=0; $i<count($t_competence); $i++){
				if ($t_competence_coeff[$i]){
					$s.='<td align="center" colspan="'.$t_nb_item_competence[$i].'"><b>'.$t_competence[$i].'</b> ('.referentiel_pourcentage($t_certif_competence_poids[$i], $t_competence_coeff[$i]).'%)</td>'."\n";
				}
				else{
					$s.='<td align="center" colspan="'.$t_nb_item_competence[$i].'"><b>'.$t_competence[$i].'</b> (0%)</td>'."\n";
				}
			}
			$s.='</tr>'."\n";
			
      // ITEMS
			$s.= '<tr valign="top" >'."\n";
			for ($i=0; $i<count($t_item_code); $i++){
				if ($t_item_empreinte[$i]){
					if ($t_certif_item_valeur[$i]>=$t_item_empreinte[$i])
						$s.='<td'.$bgcolor.'><span  class="valide">'.$t_item_code[$i].'</span></td>'."\n";
					else
						$s.='<td'.$bgcolor.'><span class="invalide">'.$t_item_code[$i].'</span></td>'."\n";
				}
				else{
					$s.='<td class="nondefini"><span class="nondefini"><i>'.$t_item_code[$i].'</i></span></td>'."\n";
				}
			}
			$s.='</tr>'."\n";
      $s.='<tr valign="top" >'."\n";
			// <td  width="5%">'.get_string('coeff','referentiel').'</td>'."\n";
			// for ($i=0; $i<count($t_item_coeff); $i++){
			for ($i=0; $i<count($t_item_code); $i++){
				if ($t_item_empreinte[$i]){
					if ($t_certif_item_valeur[$i]>=$t_item_empreinte[$i]){
						$s.='<td'.$bgcolor.'><span class="valide">100%</span></td>'."\n";
					}
					else{
						$s.='<td'.$bgcolor.'><span class="invalide">'.referentiel_pourcentage($t_certif_item_valeur[$i], $t_item_empreinte[$i]).'%</span></td>'."\n";
					}
				}
				else {
					$s.='<td class="nondefini"><span class="nondefini">&nbsp;</span></td>'."\n";
				}
			}
			$s.='</tr></table>'."\n";			
    }
	}
	}
	return $s;
}




// TACHES

/**
 * This function returns records from table referentiel_task
 *
 * @param id reference activite
 * @param select clause : ' AND champ=valeur,  ... '
 * @param order clause : ' champ ASC|DESC, ... '
 * @return objects
 * @todo Finish documenting this function
 **/
function referentiel_get_tasks_instance($referentiel_instance_id){
global $DB;
	if (isset($referentiel_instance_id) && ($referentiel_instance_id>0)){
    	$params= array("refid" => "$referentiel_instance_id");
        $sql="SELECT * FROM {referentiel_task} WHERE ref_instance=:refid ";
		return $DB->get_records_sql($sql, $params);
	}
	else 
		return NULL; 
}

/**
 * Given an task id, 
 * this function will permanently delete the task instance 
 * and any consigne that depends on it. 
 *
 * @param object $id
 * @return boolean Success/Failure
 **/

 // -----------------------
function referentiel_delete_task_record($id) {
// suppression task + consignes associes
global $DB;
$ok_task=false;	
	if (isset($id) && ($id>0)){
		if ($task = $DB->get_record("referentiel_task", array("id" => "$id"))) {
	   		// Delete any dependent records here 
			$ok_association=true;
			if ($r_a_users_tasks = $DB->get_records("referentiel_a_user_task", "ref_task", $id)) {
				// DEBUG
				// print_object($r_a_users_tasks);
				// echo "<br />";
				// suppression des associations
				foreach ($r_a_users_tasks as $r_a_user_task){
					// suppression
					$ok_association=$ok_association && referentiel_delete_a_user_task_record($r_a_user_task->id);
				}
			}
			
			$ok_consigne=true;
			if ($consignes = $DB->get_records("referentiel_consigne", array("ref_task" => "$id"))) {
				// DEBUG
				// print_object($consignes);
				// echo "<br />";
				// suppression des consignes associes dans la table referentiel_consigne
				foreach ($consignes as $consigne){
					// suppression
					$ok_consigne=$ok_consigne && referentiel_delete_consigne_record($consigne->id);
				}
			}

			// suppression task
			if ($ok_consigne && $ok_association){
                $ok_task = $DB->delete_records("referentiel_task", array("id" => "$id"));
			}
		}
	}
    return $ok_task;
}


/**
 * Given a a_user_task id, 
 * this function will permanently delete the instance 
 *
 * @param object $id
 * @return boolean Success/Failure
 			
 **/

// -----------------------
function referentiel_delete_a_user_task_record($id){
// suppression association user task
global $DB;
$ok_association=false;
	if (isset($id) && ($id>0)){
		if ($association = $DB->get_record("referentiel_a_user_task", array("id" => "$id"))) {
            $ok_association= $DB->delete_records("referentiel_a_user_task", array("id" => "$id"));
		}
	}
	return $ok_association;
}


/**
 * Given a consigne id, 
 * this function will permanently delete the consigne instance 
 *
 * @param object $id
 * @return boolean Success/Failure
 **/
// -----------------------
function referentiel_delete_consigne_record($id) {
// suppression consigne
global $DB;
$ok_consigne=false;
	if (!empty($id)){
		if ($consigne = $DB->get_record("referentiel_consigne", array("id" => "$id"))) {
			//  CODE A AJOUTER SI GESTION DE FICHIERS DEPOSES SUR LE SERVEUR
			$ok_consigne= $DB->delete_records("referentiel_consigne", array("id" => "$id"));
		}
	}
	return $ok_consigne;
}

// CERTIFICATS
/**
 * This function returns record of certificat from table referentiel_certificat
 *
 * @param id reference certificat id
 * @return object
 * @todo Finish documenting this function
 **/
function referentiel_get_certificat($id){
global $DB;
	if (!empty($id)){
    	$params= array("id" => "$id");
        $sql="SELECT * FROM {referentiel_certificat} WHERE id=:id ";
		return $DB->get_record_sql($sql, $params);
	}
	else 
		return 0; 
}

/**
 * This function returns records of certificat from table referentiel_certificat
 *
 * @param id reference referentiel (no instance)
 * @return objects
 * @todo Finish documenting this function
 **/
function referentiel_get_certificats($referentiel_referentiel_id, $select="", $order=""){
global $DB;
	if (!empty($referentiel_referentiel_id)){
		if (empty($order)){
			$order= 'userid ASC ';
		}
		$params= array("refid", "$referentiel_referentiel_id");
        $sql="SELECT * FROM {referentiel_certificat} WHERE ref_referentiel=:refid  $select ORDER BY $order ";
		return $DB->get_records_sql($sql, $params);
	}
	else 
		return 0; 
}

/**
 * This function returns records of certificat from table referentiel_certificat
 *
 * @param id reference referentiel (no instance)
 * @return objects
 * @todo Finish documenting this function
 **/
function referentiel_get_all_users_with_certificate($referentiel_referentiel_id, $select="", $order=""){
global $DB;
    if (!empty($referentiel_referentiel_id)){
		if (empty($order)){
			$order= 'userid ASC ';
		}
        $params= array("refid", "$referentiel_referentiel_id");
		$sql="SELECT userid FROM {referentiel_certificat} WHERE ref_referentiel=:refid $select ORDER BY $order ";
		return $DB->get_records_sql($sql, $params);
	}
	else 
		return null;
}


/**
 * Given an certificat id, 
 * this function will permanently delete the certificat instance  
 *
 * @param object $id
 * @return boolean Success/Failure
 **/

function referentiel_delete_certificat_record($id) {
// suppression certificat
global $DB;
$ok_certificat=false;
	if (!empty($id)){
		if ($certificat = $DB->get_record("referentiel_certificat", array("id" => "$id"))) {
			// suppression
			$ok_certificat = $DB->delete_records("referentiel_certificat", array("id" => "$id"));
		}
	}
    return $ok_certificat;
}


    /**
     * get directory into which export is going 
     * @return string file path
	 * @ input $course_id : id of current course
	 * @ input $sous_repertoire : a relative path	 
     */
function referentiel_get_export_dir($course_id, $sous_repertoire="") {
	global $CFG;
	/*
    // ensure the files area exists for this course	
	// $path_to_data=referentiel_get_export_dir($course->id,"$referentiel->id/$USER->id");
	$path_to_data=referentiel_get_export_dir($course->id);
    make_upload_directory($path_to_data);	
	*/
        $dirname = get_string('exportfilename', 'referentiel');
        $path = $course_id.'/'.$CFG->moddata.'/'.$dirname; 
		if ($sous_repertoire!=""){
			$pos=strpos($sous_repertoire,'/');
			if (($pos===false) || ($pos!=0)){ // separateur pas en tete
				// RAS
			}
			else {
				$sous_repertoire = substr($sous_repertoire,$pos+1);
			}
			$path .= '/'.$sous_repertoire;
		}
        return $path;
    }


	
	
    /**
     * write a file 
     * @return boolean
	 * @ input $path_to_data : a data path
	 * @ input $filename : a filename
     */
    function referentiel_enregistre_fichier($path_to_data, $filename, $expout) {
        global $CFG;
        // create a directory for the exports (if not already existing)
        if (! $export_dir = make_upload_directory($path_to_data)) {
              print_error( get_string('cannotcreatepath', 'referentiel', $export_dir) );
			  return "";
        }
        $path = $CFG->dataroot.'/'.$path_to_data;

        // write file
        $filepath = $path."/".$filename;
		
		// echo "<br />DEBUG : 2580 :: FILENAME : $filename <br />PATH_TO_DATA : $path_to_data <br />PATH : $path <br />FILEPATH : $filepath\n";
		
        if (!$fh=fopen($filepath,"w")) {
            return "";
        }
        if (!fwrite($fh, $expout, strlen($expout) )) {
            return "";
        }
        fclose($fh);
        return $path_to_data.'/'.$filename;
    }

    /**
     * write a file 
     * @return boolean
	 * @ input $path_to_data : a data path
	 * @ input $filename : a filename
     */
    function referentiel_upload_fichier($path_to_data, $filename_source, $filename_dest) {
        global $CFG;
        // create a directory for the exports (if not already existing)
        if (! $export_dir = make_upload_directory($path_to_data)) {
              print_error( get_string('cannotcreatepath', 'referentiel', $export_dir) );
			  return "";
        }
        $path = $CFG->dataroot.'/'.$path_to_data;
		
		if (referentiel_deplace_fichier($path, $filename_source, $filename_dest, '/', true)){
			return $path_to_data.'/'.$filename_dest;
		}
		else {
			return "";
		}
    }
	
// ------------------
function referentiel_deplace_fichier($dest_path, $source, $dest, $sep, $deplace) {
// recopie un fichier sur le serveur
// pour effectuer un deplacement $deplace=true
// @ devant une fonction signifie qu'aucun message d'erreur n'est affiche
// $dest_path est le dossier de destination du fichier
// source est le nom du fichier source (sans chemin)
// dest est le nom du fichier destination (sans chemin)
// $sep est le separateur de chemin
// retourne true si tout s'est bien deroule

	// Securite
	if (strstr($dest, "..") || strstr($dest, $sep)) {
		// interdire de remonter dans l'arborescence
		// la source est detruite
		if ($deplace) @unlink($source);
		return false;
	}
	
	// repertoire de stockage des fichiers
	$loc = $dest_path.$sep.$dest;
// 	$ok = @copy($source, $loc);
	$ok =  @copy($source, $loc);
	if ($ok){ 
		// le fichier temporaire est supprime
		if ($deplace)  @unlink($source);
	}
	else{ 
		// $ok = @move_uploaded_file($source, $loc);
		$ok =  @move_uploaded_file($source, $loc);
	}
	return $ok;
}

	// ------------------	
	function referentiel_get_logo($referentiel){
	// A TERMINER
		return "pix/logo_men.jpg";
	}
	
	// ------------------
	function referentiel_get_file($filename, $course_id, $path="" ) {
	// retourne un path/nom_de_fichier dans le dossier moodledata
 		global $CFG; global $DB;
 		if ($path==""){
			$currdir = $CFG->dataroot."/$course_id/$CFG->moddata/referentiel/";
  		}
		else {
			$currdir = $CFG->dataroot."/$course_id/$CFG->moddata/referentiel/".$path;
		}
		  
	    if (!file_exists($currdir.'/'.$filename)) {
      		return "";
      	}
		else{
			return $currdir.'/'.$filename;
		}
 	}  




// ACCOMPAGNEMENT
// voir lib_accompagnement.php

// REFERENTIEL_REFERENTIEL IMPORT XML

// -----------------------
function referentiel_set_competence_nb_item($competence_id, $nbitems){
global $DB;
    if ($competence_id && ($nbitems>0)){
        $DB->set_field ('referentiel_competence','nb_item_competences',"$nbitems", array("id" => "$competence_id"));
    }
}

// -----------------------
function referentiel_set_domaine_nb_competence($domaine_id, $nbcompetences){
global $DB;
    if ($domaine_id && ($nbcompetences>0)){
        $DB->set_field ('referentiel_domaine','nb_competences',$nbcompetences, array("id" => "$domaine_id"));
    }
}

// -----------------------
function referentiel_set_referentiel_nb_domaine($referentiel_referentiel_id, $nbdomaines){
global $DB;
    if ($referentiel_referentiel_id && ($nbdomaines>0)){
        $DB->set_field ('referentiel_referentiel','nb_domaines',"$nbdomaines", array("id" => "$referentiel_referentiel_id"));
    }
}


?>
