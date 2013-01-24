<?php  // $Id:  print_lib_etudiant.php,v 1.0 2008/04/29 00:00:00 jfruitet Exp $
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
 * Print Library of functions for etudiant of module referentiel
 * 
 * @author jfruitet
 * @version $Id: lib.php,v 1.4 2006/08/28 16:41:20 mark-nielsen Exp $
 * @version $Id: lib.php,v 1.0 2008/04/29 00:00:00 jfruitet Exp $
 * @package referentiel
 **/


require_once("lib.php");


// DEBUT DE Etudiant


// Affiche une etudiant 
// *****************************************************************
// input @param a $record_e   of etudiant                          *
// output string                                                   *
// *****************************************************************

// --------------------------------------------
function referentiel_info_etudiant_user($userid, $appli){
	$s='';
	// echo $userid."<br />\n";
	if ($userid){
		$record = referentiel_get_etudiant_user($userid);
		// print_r($record);
		if ($record){
			$s.= '<tr bgcolor="white">';
			$s.= '<td>'.referentiel_get_user_login($userid);
            $s.= '</td><td>'.referentiel_get_user_info($record->userid);
		    $s.= '</td><td>'.stripslashes($record->num_etudiant);
		    $s.= '</td><td>'.stripslashes($record->ddn_etudiant);
	    	$s.= '</td><td>'.stripslashes($record->lieu_naissance);
		    $s.= '</td><td>'.stripslashes($record->departement_naissance);
		    $s.= '</td><td>'.stripslashes($record->adresse_etudiant);
	    	$s.= '</td><td>';
			$s.=referentiel_select_etablissement($userid, $record->ref_etablissement, $appli);
			$s.='</td></tr>';
			$s.= "\n";
			return $s;
		}
	}
	return "";
}

// --------------------------------------------
function referentiel_print_etudiant($userid, $appli){
	$s='';
	// echo $userid."<br />\n";
	if ($userid){
		$s = referentiel_info_etudiant_user($userid, $appli);
		// print_r($record);
		if ($s==''){
			// creer 
			$id = referentiel_add_etudiant_user($userid);
			// recuperer
			return referentiel_info_etudiant_user($userid, $appli);
		}
		else{
			return $s;
		}
	}
	return "";
}

// ----------------------------------------------------
function referentiel_print_etudiant_2($userid, $referentiel_id, $context, $appli){
//	fusion de referentiel_print_etudiant($record) et de referentiel_menu_etudiant($context, $record->id, $referentiel_instance->id, $record->approved);
global $CFG;
global $USER;
global $OUTPUT;
	$s="";
	if ($userid){
		$record = referentiel_get_etudiant_user($userid);
		if ($record){
			$s.= '<tr bgcolor="white">';
		    $s.= '<td>'.referentiel_get_user_login($userid);
		    $s.= '</td><td>'.referentiel_get_user_info($record->userid);
		    $s.= '</td><td>'.stripslashes($record->num_etudiant);
		    $s.= '</td><td>'.stripslashes($record->ddn_etudiant);
    		$s.= '</td><td>'.stripslashes($record->lieu_naissance);
		    $s.= '</td><td>'.stripslashes($record->departement_naissance);
		    $s.= '</td><td>'.stripslashes($record->adresse_etudiant);
    		$s.= '</td><td>';
			$s.=referentiel_select_etablissement($record->userid, $record->ref_etablissement, $appli);
			$s.='</td><td>';
			// menu
			if (has_capability('mod/referentiel:managecertif', $context) 
				or ($USER->id==$record->userid)) {
	        	$s.='&nbsp; <a href="'.$CFG->wwwroot.'/mod/referentiel/etudiant.php?d='.$referentiel_id.'&userid='.$record->userid.'&mode=updateetudiant&sesskey='.sesskey().'"><img src="'.$OUTPUT->pix_url('/t/edit').'" alt="'.get_string('edit').'" title="'.get_string('edit').'" /></a>'."\n";
			}
			if (has_capability('mod/referentiel:managecertif', $context)){
	    		$s.='&nbsp; <a href="'.$CFG->wwwroot.'/mod/referentiel/etudiant.php?d='.$referentiel_id.'&userid='.$record->userid.'&mode=deleteetudiant&sesskey='.sesskey().'"><img src="'.$OUTPUT->pix_url('/t/delete').'" alt="'.get_string('delete').'" title="'.get_string('delete').'" /></a>'."\n";
			}
			$s.='</td></tr>'."\n";
		}
	}
	return $s;
}




// *****************************************************************
// input @param id_referentiel   of etudiant                       *
// output null                                                     *
// *****************************************************************
// Affiche les etudiants de ce referentiel
function referentiel_liste_tous_etudiants($referentiel_instance){
//
	$records = referentiel_get_course_users($referentiel_instance);
	if (!$records){
		error(get_string('noetudiant','referentiel'), "etudiant.php?d=".$referentiel_instance->id."&mode=add");
	}
	else {
    	// afficher
		// DEBUG
		// echo "<br/>DEBUG ::<br />\n";
		// print_r($records);
		foreach ($records as $record){
			referentiel_print_etudiant($record->id, "etudiant.php?d=".$referentiel_instance->id."&mode=selectetab");
		}
	}
}

// *****************************************************************
// input @param context                                            *
// input @param id_referentiel                                     *
// input @param id_user                                            *
// output string                                                   *
// *****************************************************************
// Affiche le menu EDITER et SUPPRIMER etudiants de ce referentiel
function referentiel_menu_etudiant($context, $referentiel_id, $userid){
	global $CFG;
	global $USER;
	global $OUTPUT;
	$s="";
	
	if (has_capability('mod/referentiel:managecertif', $context) 
		or ($USER->id==$userid)) {
        $s.='&nbsp; <a href="'.$CFG->wwwroot.'/mod/referentiel/etudiant.php?d='.$referentiel_id.'&userid='.$userid.'&mode=updateetudiant&sesskey='.sesskey().'"><img src="'.$OUTPUT->pix_url('/t/edit').'" alt="'.get_string('edit').'" title="'.get_string('edit').'" /></a>'."\n";
	}
	if (has_capability('mod/referentiel:managecertif', $context)){
	    $s.='&nbsp; <a href="'.$CFG->wwwroot.'/mod/referentiel/etudiant.php?d='.$referentiel_id.'&userid='.$userid.'&mode=deleteetudiant&sesskey='.sesskey().'"><img src="'.$OUTPUT->pix_url('/t/delete').'" alt="'.get_string('delete').'" title="'.get_string('delete').'" /></a>'."\n";
	}
	return $s;
}


/************************************************************************
 * takes a list of records, the current referentiel, a search string,   *
 * and mode to display                                                  *
 * input @param string  $mode                                           *
 *       @param object $referentiel_instance                            *
 *       @param int $userid_filtre                                      *
 *       @param array $gusers                                           *
 *       @param int $page                                               *
 * output null                                                         *
 ************************************************************************/
function referentiel_print_liste_etudiants($mode, $referentiel_instance, $userid_filtre=0, $gusers=NULL, $page=0) {

global $USER;
static $isteacher=false;
static $isauthor=false;
static $iseditor=false;
static $referentiel_id = NULL;

global $DB;

    if (!empty($referentiel_instance)){
        $cm = get_coursemodule_from_instance('referentiel', $referentiel_instance->id);
        $course = $DB->get_record("course", array("id" => "$cm->course"));

        if (empty($cm) or empty($course)){
            print_error('REFERENTIEL_ERROR 5 :: print_lib_etudiant.php :: You cannot call this script in that way');
        }
		
        $context = get_context_instance(CONTEXT_MODULE, $cm->id);
		
        $records = array();
        $isteacher = has_capability('mod/referentiel:rate', $context);
        $iseditor = has_capability('mod/referentiel:managecertif', $context);

        // Creer les enregistrements pour les etudiants
		$record_id_users  = referentiel_get_students_course($course->id,0,0);  // seulement les stagiaires
		// echo "<br />DEBUG :: print_lib_etudiant.php :: 219 :: RECORD_ID_USERS<br />\n";
		// print_r($record_id_users);
		// echo "<br />\n";
		
		if ($record_id_users){
			foreach ($record_id_users as $un_user_id){
				// l'enregistrement existe-t-il ?
				// echo "<br />".$un_user_id->userid."\n";
				$re = $DB->get_record("referentiel_etudiant", array("userid" => "$un_user_id->userid"));
				if (!$re) {
            		$id_etudiant=referentiel_add_etudiant_user($un_user_id->userid);
        		}
			}
		}
		
		// selection  sur les utilisateurs ?
		if ($isteacher || $iseditor){
			// cr�er les enrgistrements etudiants si 
			if ($gusers && $record_id_users){ // liste des utilisateurs du groupe courant
				// echo "<br />DEBUG :: print_lib_activite.php :: 740 :: GUSERS<br />\n";
				// print_object($gusers);
				// echo "<br />\n";
				$record_users  = array_intersect($gusers, array_keys($record_id_users));
				// $record_users  = array_intersect_assoc($record_id_users, array_keys($gusers));
				// echo "<br />DEBUG :: print_lib_etudiant.php:: 242 :: RECORD_USERS<br />\n";
				// print_r($record_users  );
				// echo "<br />\n";
				// recopier 
				
				$record_id_users=array();
				foreach ($record_users  as $record_id){
					$record_id_users[]->userid=$record_id;
				}
			}
			$boite_selection=referentiel_select_users_etudiant($record_id_users, $userid_filtre);
		}
		else $boite_selection="";
		
		// filtres
		if ((!$isteacher) && (!$iseditor)){
			$userid_filtre=$USER->id; 
		}
		// recuperer les utilisateurs filtres
		
		$record_id_users = referentiel_get_students_course($course->id, $userid_filtre);
		if ($gusers && $record_id_users){
			$record_users  = array_intersect($gusers, array_keys($record_id_users));
			// recopier 
			$record_id_users=array();
			foreach ($record_users  as $record_id){
				$record_id_users[]->userid=$record_id;
			}
		}

		if ($record_id_users){
			echo $boite_selection;
			echo '<table class="certificat">
<tr><th>'.get_string('userid','referentiel').'</th><th>'.get_string('nom_prenom','referentiel').'</th><th>'.get_string('num_etudiant','referentiel').'</th><th>'.get_string('ddn_etudiant','referentiel').'</th><th>'.get_string('lieu_naissance','referentiel').'</th><th>'.get_string('departement_naissance','referentiel').'</th><th>'.get_string('adresse_etudiant','referentiel').'</th><th>'.get_string('ref_etablissement','referentiel').'</th></tr>'."\n";
		    foreach ($record_id_users as $record) {   // afficher la liste d'etudiant
				// Afficher 
				// print_r($record);
				if ($record->userid){
					$isauthor = ($USER->id==$record->userid);
					if ($isauthor || $isteacher || $iseditor) {
						// echo referentiel_print_etudiant($record->userid, $CFG->wwwroot.'/mod/referentiel/etudiant.php?d='.$referentiel_instance->id.'&mode=selectetab&sesskey='.sesskey());
						// echo '<tr><td colspan="7" align="center">'.referentiel_menu_etudiant($context, $referentiel_instance->id, $record->userid).'</td></tr>'."\n";
						echo referentiel_print_etudiant_2($record->userid, $referentiel_instance->id, $context, "etudiant.php?d=".$referentiel_instance->id."&mode=selectetab");
    				}
				}
			}
			echo '</table><br /><br />'."\n";
		}
	}
}

/************************************************************************
 * takes a list of records, a search string,                            *
 * input @param array $records   of users                               *
 *       @param string $search                                          *
 * output null                                                          *
 ************************************************************************/
function referentiel_select_users_etudiant($record_users, $userid=0){
global $cm;
global $mode;
global $course;
$maxcol=6;
$s="";
	if ($record_users){
		$s.='<div align="center">
		
<form name="form" method="post" action="etudiant.php?id='.$cm->id.'&action=selectuser">'."\n"; 
		$s.='<table class="selection">'."\n";
		$s.='<tr>';
		$s.='<td>';
		if (($userid=='') || ($userid==0)){
			$s.='<input type="radio" name="userid" id="userid" value="" checked="checked" />Tous</td>'."\n";;
		}
		else{
			$s.='<input type="radio" name="userid" id="userid" value="" />Tous</td>'."\n";;
		}
		$s.='</tr>';
		$s.='<tr>';
		$col=0;
		$lig=0;
		foreach ($record_users as $record_u) {   // liste d'id users
			$user_info=referentiel_get_user_info($record_u->userid);
			if ($record_u->userid==$userid){
				$s.='<td><input type="radio" name="userid" id="userid" value="'.$record_u->userid.'" checked="checked" />'.$user_info.'</td>'."\n";;
			}
			else{
				$s.='<td><input type="radio" name="userid" id="userid" value="'.$record_u->userid.'" />'.$user_info.'</td>'."\n";;
			}
			if ($col<$maxcol){
				$col++;
			}
			else{
				$s.='</tr><tr>'."\n";
				$col=0;
				$lig++;
			}
		}
		if ($lig>0){
			while ($col<$maxcol){
				$s.='<td>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; </td>'."\n";
				$col++;
			}
		}
		
		$s.='<td>&nbsp; &nbsp; &nbsp; <input type="submit" value="'.get_string('select', 'referentiel').'" /></td>';
		$s.='
<!-- These hidden variables are always the same -->
<input type="hidden" name="course"        value="'.$course->id.'" />
<input type="hidden" name="sesskey"     value="'.sesskey().'" />
<input type="hidden" name="mode"          value="listetudiant" />
</tr></table>
</form>
</div>'."\n";
	}
	return $s;
}

?>