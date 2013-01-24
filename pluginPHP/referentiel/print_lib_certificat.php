<?php  // $Id:  print_lib_certificat.php,v 1.0 2008/04/29 00:00:00 jfruitet Exp $
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
 * Print Library of functions for certificat of module referentiel
 * 
 * @author jfruitet
 * @version $Id: lib.php,v 1.4 2006/08/28 16:41:20 mark-nielsen Exp $
 * @version $Id: lib.php,v 1.0 2008/04/29 00:00:00 jfruitet Exp $
 * @package referentiel
 **/


require_once("lib.php");
require_once("overlib_item.php");

// Retourne le seuil de certigfication
// *****************************************************************
// *
// output int                                                    *
// *****************************************************************
function referentiel_get_seuil_certification($refrefid){
global $DB;
    $referentiel_referentiel=$DB->get_record("referentiel_referentiel", array("id" => $refrefid));
    if ($referentiel_referentiel){
        return $referentiel_referentiel->seuil_certificat;
    }
    return 0;
}


// Affiche une entete activite
// *****************************************************************
// *
// output string                                                    *
// *****************************************************************
function referentiel_entete_filtre($appli, $data, $oklistesimple=false){
// Affiche une entete  complete
$s="";
$appli=$appli.'&mode_select=selectetab';

	if ($oklistesimple){
		$width="10%";
	}
	else{
		$width="15%";
	}
	$s.='<table class="activite" width="100%"><tr valign="top">'."\n";
	$s.='<th width="2%">'.get_string('id','referentiel').'</th>';
	$s.='<th width="'.$width.'">'.get_string('filtre_auteur','referentiel');
	$s.="\n".'<form action="'.$appli.'" method="get" id="selectetab_filtre_auteur" class="popupform">'."\n";
	$s.=' <select id="selectetab_filtre_auteur" name="filtre_auteur" size="1"
onchange="self.location=document.getElementById(\'selectetab_filtre_auteur\').filtre_auteur.options[document.getElementById(\'selectetab_filtre_auteur\').filtre_auteur.selectedIndex].value;">'."\n";
	if (isset($data) && !empty($data)){
		if ($data->filtre_auteur=='1'){
			$s.='	<option value="'.$appli.'&filtre_auteur=0&filtre_referent=0&filtre_verrou='.$data->filtre_verrou.'&filtre_date_modif='.$data->filtre_date_decision.'">'.get_string('choisir','referentiel').'</option>'."\n";
			$s.='	<option value="'.$appli.'&filtre_auteur=1&filtre_referent=0&filtre_verrou='.$data->filtre_verrou.'&filtre_date_decision='.$data->filtre_date_decision.'" selected="selected">'.get_string('croissant','referentiel').'</option>'."\n";
			$s.='	<option value="'.$appli.'&filtre_auteur=-1&filtre_referent=0&filtre_verrou='.$data->filtre_verrou.'&filtre_date_decision='.$data->filtre_date_decision.'">'.get_string('decroissant','referentiel').'</option>'."\n";
		}
		else if ($data->filtre_auteur=='-1'){
			$s.='	<option value="'.$appli.'&filtre_auteur=0&filtre_referent=0&filtre_verrou='.$data->filtre_verrou.'&filtre_date_decision='.$data->filtre_date_decision.'">'.get_string('choisir','referentiel').'</option>'."\n";
			$s.='	<option value="'.$appli.'&filtre_auteur=1&filtre_referent=0&filtre_verrou='.$data->filtre_verrou.'&filtre_date_decision='.$data->filtre_date_decision.'">'.get_string('croissant','referentiel').'</option>'."\n";
			$s.='	<option value="'.$appli.'&filtre_auteur=-1&filtre_referent=0&filtre_verrou='.$data->filtre_verrou.'&filtre_date_decision='.$data->filtre_date_decision.'" selected="selected">'.get_string('decroissant','referentiel').'</option>'."\n";
		}
		else{
			$s.='	<option value="'.$appli.'&filtre_auteur=0&filtre_referent=0&filtre_verrou='.$data->filtre_verrou.'&filtre_date_decision='.$data->filtre_date_decision.'" selected="selected">'.get_string('choisir','referentiel').'</option>'."\n";
			$s.='	<option value="'.$appli.'&filtre_auteur=1&filtre_referent=0&filtre_verrou='.$data->filtre_verrou.'&filtre_date_decision='.$data->filtre_date_decision.'">'.get_string('croissant','referentiel').'</option>'."\n";
			$s.='	<option value="'.$appli.'&filtre_auteur=-1&filtre_referent=0&filtre_verrou='.$data->filtre_verrou.'&filtre_date_decision='.$data->filtre_date_decision.'">'.get_string('decroissant','referentiel').'</option>'."\n";
		}
	}
	else{
		$s.='	<option value="'.$appli.'&filtre_auteur=0&filtre_referent=0&filtre_verrou=0&filtre_date_decision=0&filtre_date_decision=0" selected="selected">'.get_string('choisir','referentiel').'</option>'."\n";
		$s.='	<option value="'.$appli.'&filtre_auteur=1&filtre_referent=0&filtre_verrou=0&filtre_date_decision=0&filtre_date_decision=0">'.get_string('croissant','referentiel').'</option>'."\n";
		$s.='	<option value="'.$appli.'&filtre_auteur=-1&filtre_referent=0&filtre_verrou=0&filtre_date_decision=0&filtre_date_decision=0">'.get_string('decroissant','referentiel').'</option>'."\n";
	}
	$s.='</select>'."\n";
	$s.='
<script type="text/javascript">
//<![CDATA[
document.getElementById("noscriptnavmenupopup").style.display = "none";
//]]>
</script>'."\n".'</form>'."\n";
	$s.='</th>';

	$s.='<th width="'.$width.'">'.get_string('filtre_verrou','referentiel');
	$s.="\n".'<form action="'.$appli.'" method="get" id="selectetab_filtre_verrou" class="popupform">'."\n";
	$s.=' <select id="selectetab_filtre_verrou" name="filtre_verrou" size="1"
onchange="self.location=document.getElementById(\'selectetab_filtre_verrou\').filtre_verrou.options[document.getElementById(\'selectetab_filtre_verrou\').filtre_verrou.selectedIndex].value;">'."\n";
	if (isset($data) && !empty($data)){
		if ($data->filtre_verrou=='1'){
			$s.='	<option value="'.$appli.'&filtre_verrou=0&filtre_auteur='.$data->filtre_auteur.'&filtre_referent='.$data->filtre_referent.'&filtre_date_decision='.$data->filtre_date_decision.'">'.get_string('choisir','referentiel').'</option>'."\n";
			$s.='	<option value="'.$appli.'&filtre_verrou=1&filtre_auteur='.$data->filtre_auteur.'&filtre_referent='.$data->filtre_referent.'&filtre_date_decision='.$data->filtre_date_decision.'" selected="selected">'.get_string('verrou','referentiel').'</option>'."\n";
			$s.='	<option value="'.$appli.'&filtre_verrou=-1&filtre_auteur='.$data->filtre_auteur.'&filtre_referent='.$data->filtre_referent.'&filtre_date_decision='.$data->filtre_date_decision.'">'.get_string('not_verrou','referentiel').'</option>'."\n";
		}
		else if ($data->filtre_verrou=='-1'){
			$s.='	<option value="'.$appli.'&filtre_verrou=0&filtre_auteur='.$data->filtre_auteur.'&filtre_verrou=0&filtre_referent='.$data->filtre_referent.'&filtre_date_decision='.$data->filtre_date_decision.'">'.get_string('choisir','referentiel').'</option>'."\n";
			$s.='	<option value="'.$appli.'&filtre_verrou=1&filtre_auteur='.$data->filtre_auteur.'&filtre_referent='.$data->filtre_referent.'&filtre_date_decision='.$data->filtre_date_decision.'">'.get_string('verrou','referentiel').'</option>'."\n";
			$s.='	<option value="'.$appli.'&filtre_verrou=-1&filtre_auteur='.$data->filtre_auteur.'&filtre_referent='.$data->filtre_referent.'&filtre_date_decision='.$data->filtre_date_decision.'" selected="selected">'.get_string('not_verrou','referentiel').'</option>'."\n";
		}
		else{
			$s.='	<option value="'.$appli.'&filtre_verrou=0&filtre_auteur='.$data->filtre_auteur.'&filtre_referent='.$data->filtre_referent.'&filtre_date_decision='.$data->filtre_date_decision.'" selected="selected">'.get_string('choisir','referentiel').'</option>'."\n";
			$s.='	<option value="'.$appli.'&filtre_verrou=1&filtre_auteur='.$data->filtre_auteur.'&filtre_referent='.$data->filtre_referent.'&filtre_date_decision='.$data->filtre_date_decision.'">'.get_string('verrou','referentiel').'</option>'."\n";
			$s.='	<option value="'.$appli.'&filtre_verrou=-1&filtre_auteur='.$data->filtre_auteur.'&filtre_referent='.$data->filtre_referent.'&filtre_date_decision='.$data->filtre_date_decision.'">'.get_string('not_verrou','referentiel').'</option>'."\n";
		}
	}
	else{
		$s.='	<option value="'.$appli.'&filtre_verrou=0&filtre_auteur=0&filtre_referent=0&filtre_date_decision=0&filtre_date_decision=0" selected="selected">'.get_string('choisir','referentiel').'</option>'."\n";
		$s.='	<option value="'.$appli.'&filtre_verrou=1&filtre_auteur=0&filtre_referent=0&filtre_date_decision=0&filtre_date_decision=0">'.get_string('verrou','referentiel').'</option>'."\n";
		$s.='	<option value="'.$appli.'&filtre_verrou=-1&filtre_auteur=0&filtre_referent=O&filtre_date_decision=0&filtre_date_decision=0">'.get_string('not_verrou','referentiel').'</option>'."\n";
	}

	$s.='</select>'."\n";
	$s.='
<script type="text/javascript">
//<![CDATA[
document.getElementById("noscriptnavmenupopup").style.display = "none";
//]]>
</script>'."\n".'</form>'."\n";
	$s.='</th>';
// teacher


	$s.='<th width="'.$width.'">'.get_string('suivi','referentiel');
	$s.="\n".'<form action="'.$appli.'" method="get" id="selectetab_filtre_referent" class="popupform">'."\n";
	$s.=' <select id="selectetab_filtre_referent" name="filtre_referent" size="1"
onchange="self.location=document.getElementById(\'selectetab_filtre_referent\').filtre_referent.options[document.getElementById(\'selectetab_filtre_referent\').filtre_referent.selectedIndex].value;">'."\n";
	if (isset($data) && !empty($data)){
		if ($data->filtre_referent=='1'){
			$s.='	<option value="'.$appli.'&filtre_referent=0&filtre_auteur='.$data->filtre_auteur.'&filtre_verrou='.$data->filtre_verrou.'&filtre_date_decision='.$data->filtre_date_decision.'">'.get_string('choisir','referentiel').'</option>'."\n";
			$s.='	<option value="'.$appli.'&filtre_referent=1&filtre_auteur='.$data->filtre_auteur.'&filtre_verrou='.$data->filtre_verrou.'&filtre_date_decision='.$data->filtre_date_decision.'" selected="selected">'.get_string('examine','referentiel').'</option>'."\n";
			$s.='	<option value="'.$appli.'&filtre_referent=-1&filtre_auteur='.$data->filtre_auteur.'&filtre_verrou='.$data->filtre_verrou.'&filtre_date_decision='.$data->filtre_date_decision.'">'.get_string('non_examine','referentiel').'</option>'."\n";
		}
		else if ($data->filtre_referent=='-1'){
			$s.='	<option value="'.$appli.'&filtre_referent=0&filtre_auteur='.$data->filtre_auteur.'&filtre_verrou='.$data->filtre_verrou.'&filtre_date_decision='.$data->filtre_date_decision.'">'.get_string('choisir','referentiel').'</option>'."\n";
			$s.='	<option value="'.$appli.'&filtre_referent=1&filtre_auteur='.$data->filtre_auteur.'&filtre_verrou='.$data->filtre_verrou.'&filtre_date_decision='.$data->filtre_date_decision.'">'.get_string('examine','referentiel').'</option>'."\n";
			$s.='	<option value="'.$appli.'&filtre_referent=-1&filtre_auteur='.$data->filtre_auteur.'&filtre_verrou='.$data->filtre_verrou.'&filtre_date_decision='.$data->filtre_date_decision.'" selected="selected">'.get_string('non_examine','referentiel').'</option>'."\n";
		}
		else{
			$s.='	<option value="'.$appli.'&filtre_referent=0&filtre_auteur='.$data->filtre_auteur.'&filtre_verrou='.$data->filtre_verrou.'&filtre_date_decision='.$data->filtre_date_decision.'" selected="selected">'.get_string('choisir','referentiel').'</option>'."\n";
			$s.='	<option value="'.$appli.'&filtre_referent=1&filtre_auteur='.$data->filtre_auteur.'&filtre_verrou='.$data->filtre_verrou.'&filtre_date_decision='.$data->filtre_date_decision.'">'.get_string('examine','referentiel').'</option>'."\n";
			$s.='	<option value="'.$appli.'&filtre_referent=-1&filtre_auteur='.$data->filtre_auteur.'&filtre_verrou='.$data->filtre_verrou.'&filtre_date_decision='.$data->filtre_date_decision.'">'.get_string('non_examine','referentiel').'</option>'."\n";
		}
	}
	else{
		$s.='	<option value="'.$appli.'&filtre_referent=0&filtre_auteur=0&filtre_verrou=0&filtre_date_decision=0&filtre_date_decision=0" selected="selected">'.get_string('choisir','referentiel').'</option>'."\n";
		$s.='	<option value="'.$appli.'&filtre_referent=1&filtre_auteur=0&filtre_verrou=0&filtre_date_decision=0&filtre_date_decision=0">'.get_string('examine','referentiel').'</option>'."\n";
		$s.='	<option value="'.$appli.'&filtre_referent=-1&filtre_auteur=0&filtre_auteur=0&filtre_verrou=0&filtre_date_decisiont=0&filtre_date_decision=0">'.get_string('non_examine','referentiel').'</option>'."\n";
	}
	$s.='</select>'."\n";
	$s.='
<script type="text/javascript">
//<![CDATA[
document.getElementById("noscriptnavmenupopup").style.display = "none";
//]]>
</script>'."\n".'</form>'."\n";
	$s.='</th>';


	$s.='<th width="'.$width.'">'.get_string('filtre_date_decision','referentiel');
	$s.="\n".'<form action="'.$appli.'" method="get" id="selectetab_filtre_date_decision" class="popupform">'."\n";
	$s.=' <select id="selectetab_filtre_date_decision" name="filtre_date_decision" size="1"
onchange="self.location=document.getElementById(\'selectetab_filtre_date_decision\').filtre_date_decision.options[document.getElementById(\'selectetab_filtre_date_decision\').filtre_date_decision.selectedIndex].value;">'."\n";
	if (isset($data) && !empty($data)){
		if ($data->filtre_date_decision=='1'){
			$s.='	<option value="'.$appli.'&filtre_auteur='.$data->filtre_auteur.'&filtre_date_decision=0&filtre_referent='.$data->filtre_referent.'&filtre_verrou='.$data->filtre_verrou.'">'.get_string('choisir','referentiel').'</option>'."\n";
			$s.='	<option value="'.$appli.'&filtre_auteur='.$data->filtre_auteur.'&filtre_date_decision=1&filtre_referent='.$data->filtre_referent.'&filtre_verrou='.$data->filtre_verrou.'" selected="selected">'.get_string('croissant','referentiel').'</option>'."\n";
			$s.='	<option value="'.$appli.'&filtre_auteur='.$data->filtre_auteur.'&filtre_date_decision=-1&filtre_referent='.$data->filtre_referent.'&filtre_verrou='.$data->filtre_verrou.'">'.get_string('decroissant','referentiel').'</option>'."\n";
		}
		else if ($data->filtre_date_decision=='-1'){
			$s.='	<option value="'.$appli.'&filtre_auteur='.$data->filtre_auteur.'&filtre_date_decision=0&filtre_referent='.$data->filtre_referent.'&filtre_verrou='.$data->filtre_verrou.'">'.get_string('choisir','referentiel').'</option>'."\n";
			$s.='	<option value="'.$appli.'&filtre_auteur='.$data->filtre_auteur.'&filtre_date_decision=1&filtre_referent='.$data->filtre_referent.'&filtre_verrou='.$data->filtre_verrou.'">'.get_string('croissant','referentiel').'</option>'."\n";
			$s.='	<option value="'.$appli.'&filtre_auteur='.$data->filtre_auteur.'&filtre_date_decision=-1&filtre_referent='.$data->filtre_referent.'&filtre_verrou='.$data->filtre_verrou.'" selected="selected">'.get_string('decroissant','referentiel').'</option>'."\n";
		}
		else{
			$s.='	<option value="'.$appli.'&filtre_auteur='.$data->filtre_auteur.'&filtre_date_decision=0&filtre_referent='.$data->filtre_referent.'&filtre_verrou='.$data->filtre_verrou.'" selected="selected">'.get_string('choisir','referentiel').'</option>'."\n";
			$s.='	<option value="'.$appli.'&filtre_auteur='.$data->filtre_auteur.'&filtre_date_decision=1&filtre_referent='.$data->filtre_referent.'&filtre_verrou='.$data->filtre_verrou.'">'.get_string('croissant','referentiel').'</option>'."\n";
			$s.='	<option value="'.$appli.'&filtre_auteur='.$data->filtre_auteur.'&filtre_date_decision=-1&filtre_referent='.$data->filtre_referent.'&filtre_verrou='.$data->filtre_verrou.'">'.get_string('decroissant','referentiel').'</option>'."\n";
		}
	}
	else{
		$s.='	<option value="'.$appli.'&filtre_auteur=0&filtre_date_decision=0&filtre_referent=0&filtre_auteur=0&filtre_verrou=0" selected="selected">'.get_string('choisir','referentiel').'</option>'."\n";
		$s.='	<option value="'.$appli.'&filtre_auteur=0&filtre_date_decision=1&filtre_referent=0&filtre_auteur=0&filtre_verrou=0">'.get_string('croissant','referentiel').'</option>'."\n";
		$s.='	<option value="'.$appli.'1&filtre_auteur=0&filtre_date_decision=-1&filtre_referent=0&filtre_auteur=0&filtre_verrou=0">'.get_string('decroissant','referentiel').'</option>'."\n";
	}
	$s.='</select>'."\n";
	$s.='
<script type="text/javascript">
//<![CDATA[
document.getElementById("noscriptnavmenupopup").style.display = "none";
//]]>
</script>'."\n".'</form>'."\n";
	$s.='</th>';
	
	if ($oklistesimple){
		$s.='<th width="25%">'.get_string('liste_codes_competence','referentiel').'</th>';
	}


	$s.='</tr>'."\n".'</table>';

	return $s;
}

function referentiel_enqueue_certificat(){
// Affiche une enqueue activite
	$s='';
	$s.='</table>'."\n";
	return $s;
}


/**************************************************************************
 * takes a list of records, the current referentiel, an optionnal user id *
 * and mode to display                                                    *
 * input @param string  $mode                                             *
 *       @param object $referentiel_instance                              *
 *       @param int $userid_filtre                                        *
 *       @param array of objects $gusers of users get from current group  *
 *       @param string $sql_filtre_where, $sql_filtre_order               *
 * output null                                                            *
 **************************************************************************/
function referentiel_evalue_global_liste_certificats($mode, $referentiel_instance,
$userid_filtre=0, $gusers=NULL, $sql_filtre_where='', $sql_filtre_order='',
$data_filtre, $select_acc=0) {
// idem  que referentiel_print_evalue_liste_activite()
// mais  specialise modification
// form globale

    global $CFG;
    global $USER;
    static $istutor=false;
    static $isteacher=false;
    static $isauthor=false;
    static $iseditor=false;
    static $referentiel_id = NULL;

    global $DB;
    
  if (!empty($referentiel_instance)){
    $cm = get_coursemodule_from_instance('referentiel', $referentiel_instance->id);
    $course = $DB->get_record("course", array("id" => "$cm->course"));
    
	if (empty($cm) or empty($course)){
        print_error('REFERENTIEL_ERROR 5 :: print_lib_activite.php :: You cannot call this script in that way');
	}

    $context = get_context_instance(CONTEXT_MODULE, $cm->id);

	$records = array();
	$referentiel_id = $referentiel_instance->ref_referentiel;
	$iseditor = has_capability('mod/referentiel:writereferentiel', $context);
	$isteacher = has_capability('mod/referentiel:approve', $context)&& !$iseditor;
	$istutor = has_capability('mod/referentiel:comment', $context) && !$iseditor  && !$isteacher;
	$isauthor = has_capability('mod/referentiel:write', $context) && !$iseditor  && !$isteacher  && !$istutor;
	/*
	// DEBUG
	if ($isteacher) echo "Teacher ";
	if ($iseditor) echo "Editor ";
	if ($istutor) echo "Tutor ";
	if ($isauthor) echo "Author ";
	*/


	if (isset($referentiel_id) && ($referentiel_id>0)){
		$referentiel_referentiel=referentiel_get_referentiel_referentiel($referentiel_id);
		if (!$referentiel_referentiel){
			if ($iseditor){
				error(get_string('creer_referentiel','referentiel'), "$CFG->wwwroot/mod/referentiel/edit.php?d=$referentiel_instance->id&mode=editreferentiel&sesskey=".sesskey());
			}
			else {
				error(get_string('creer_referentiel','referentiel'), "$CFG->wwwroot/course/view.php?id=$course->id&sesskey=".sesskey());
			}
		}
	 	// preparer les variables globales pour Overlib
		// referentiel_initialise_data_referentiel($referentiel_referentiel->id);
		// empreintes
		$liste_empreintes=referentiel_purge_dernier_separateur(referentiel_get_liste_empreintes_competence($referentiel_instance->id), '/');
		referentiel_initialise_descriptions_items_referentiel($referentiel_referentiel->id);

		// boite pour selectionner les utilisateurs ?
		if ($isteacher || $iseditor || $istutor){
			if (!empty($select_acc)){
                // eleves accompagnes
                $record_id_users  = referentiel_get_accompagnements_teacher($referentiel_instance->id, $course->id,
                    $USER->id);
            }
			else{
                // tous les users possibles (pour la boite de selection)
				// Get your userids the normal way
                $record_id_users  = referentiel_get_students_course($course->id,0,0);  //seulement les stagiaires
			}
            if ($gusers && $record_id_users){ // liste des utilisateurs du groupe courant
				// echo "<br />DEBUG :: print_lib_activite.php :: 740 :: GUSERS<br />\n";
				// print_object($gusers);
				// echo "<br />\n";
				// exit;
				$record_users  = array_intersect($gusers, array_keys($record_id_users));
				// echo "<br />DEBUG :: print_lib_activite.php :: 745 :: RECORD_USERS<br />\n";
				// print_r($record_users  );
				// echo "<br />\n";
				// recopier
				$record_id_users=array();
				foreach ($record_users  as $record_id){
					$record_id_users[]->userid=$record_id;
				}
			}
			// Ajouter l'utilisateur courant pour qu'il voit son certificat
			$record_id_users[]->userid=$USER->id;
			echo referentiel_select_users_accompagnes(
$CFG->wwwroot.'/mod/referentiel/certificat.php?d='.$referentiel_id.'&mode='.$mode.'&sesskey='.sesskey(),
                $mode, $userid_filtre, $select_acc);
			echo referentiel_select_users_certificat($record_id_users,
$CFG->wwwroot.'/mod/referentiel/certificat.php?d='.$referentiel_id.'&mode='.$mode.'&sesskey='.sesskey(),
                $mode, $userid_filtre, $select_acc);
		}
		else{
			$userid_filtre=$USER->id; // les étudiants ne peuvent voir que leur fiche
		}
		// recuperer les utilisateurs filtres

        if (!empty($select_acc) && ($userid_filtre == 0)){
            // eleves accompagnes
            $record_id_users  = referentiel_get_accompagnements_teacher($referentiel_instance->id, $course->id, $USER->id);
        }
        else{
            // retourne les etudiants du cours ou userid_filtre si != 0
            $record_id_users = referentiel_get_students_course($course->id, $userid_filtre, 0);
        }

		// afficher le groupe courant
		if ($record_id_users && $gusers){ // liste des utilisateurs du groupe courant
			$record_users  = array_intersect($gusers, array_keys($record_id_users));
			// recopier
			$record_id_users=array();
			foreach ($record_users  as $record_id){
				$record_id_users[]->userid=$record_id;
			}
		}

		if ((($userid_filtre==$USER->id) || ($userid_filtre==0))
            && ($isteacher || $iseditor|| $istutor)){
			// Ajouter l'utilisateur courant pour qu'il puisse voir ses propres activites
			$record_id_users[]->userid=$USER->id;
		}

		// echo "<br />DEBUG :: print_lib_activite.php :: 1870 :: RECORD_USERS<br />\n";
		// print_r($record_users  );
		// echo "<br />\n";
		// afficher les activites
		if ($record_id_users){
			// Afficher
/*
			if (isset($mode) && (($mode=='updateactivity') || ($mode=='listactivityall') || ($mode=='listactivitysingle'))){
				echo referentiel_modifie_entete_activite_complete_filtre("activite.php?id=".$cm->id."&course=".$course->id."&userid=".$userid_filtre."&select_acc=".$select_acc."&mode=".$mode."&sesskey=".sesskey(), $data_filtre, false);
			}
			else{
				//echo referentiel_print_entete_activite();
				echo referentiel_modifie_entete_activite_complete_filtre("activite.php?id=".$cm->id."&course=".$course->id."&userid=".$userid_filtre."&select_acc=".$select_acc."&mode=".$mode."&sesskey=".sesskey(), $data_filtre, true);
			}
*/
			// ordre d'affichage utilisateurs
			if (isset($data_filtre) && isset($data_filtre->filtre_auteur) && ($data_filtre->filtre_auteur=='-1')){
				$deb=(-count($record_id_users))+1;
				$fin=1;
			}
			else{
				$deb=0;
				$fin=count($record_id_users);
			}


			// Parcours des utilisateurs
			for ($j=$deb; $j<$fin; $j++){
				$i=abs($j);
				// recupere les enregistrements
				// MODIF JF 23/10/2009
				if (isset($userid_filtre) && ($userid_filtre==$USER->id)){
					$actif=true;
				}
/* *********
				else if (isset($mode) && ($mode=='listactivityall')){
					$actif=false;
				}
********* */
				else{
					$actif=false;
					// 	$records=referentiel_get_all_activites_user_course($referentiel_instance->ref_referentiel, $record_id->userid, $course->id);
				}
				// recupération des certificats
                // ATTENTION
                // il faut introduire les filtres SQL
                //	$records=referentiel_get_all_activites_user($referentiel_instance->ref_referentiel, $record_id_users[$i]->userid, $sql_filtre_where, $sql_filtre_order);
                $records[]=referentiel_certificat_user_select($record_id_users[$i]->userid, $referentiel_instance->ref_referentiel, $sql_filtre_where, $sql_filtre_order);
            }

        
            if ($records){
                echo '<table class="activite" width="100%"><tr valign="top">'."\n";
                echo  '<td colspan="4">'."\n";
                echo referentiel_entete_filtre($CFG->wwwroot.'/mod/referentiel/certificat.php?d='.$referentiel_id.'&mode='.$mode.'&sesskey='.sesskey(), $data_filtre, false);
                echo  '</td></tr>'."\n";
                // formulaire global
                echo "\n\n".'<form name="form" id="form" action="certificat.php?id='.$cm->id.'&course='.$course->id.'&filtre_auteur='.$data_filtre->filtre_auteur.'&filtre_verrou='.$data_filtre->filtre_verrou.'&filtre_referent='.$data_filtre->filtre_referent.'&filtre_date_decision='.$data_filtre->filtre_date_decision.'&select_acc='.$select_acc.'&sesskey='.sesskey().'" method="post">'."\n";
                echo  '<tr valign="top">
<td class="ardoise" colspan="4">
 <img class="selectallarrow" src="./pix/arrow_ltr_bas.png" width="38" height="22" alt="Pour la sélection :" />
 <i>'.get_string('cocher_enregistrer', 'referentiel').'</i>
<input type="submit" value="'.get_string("savechanges").'" />
<input type="reset" value="'.get_string("corriger", "referentiel").'" />
<input type="submit" name="cancel" value="'.get_string("quit", "referentiel").'" />
</td></tr>'."\n";

                foreach ($records as $record) {   // afficher le certificat
                    // Afficher
                    if (isset($mode) && ($mode=='editcertif')){
                        echo referentiel_modifie_global_certificat($data_filtre,$mode, $cm, $course, $referentiel_instance, $record, $context, $actif, $liste_empreintes, $select_acc);
                    }
                }

                echo  '<tr valign="top">
<td class="ardoise" colspan="4">
 <img class="selectallarrow" src="./pix/arrow_ltr.png"
    width="38" height="22" alt="Pour la sélection :" />
<i>'.get_string('cocher_enregistrer', 'referentiel').'</i>
<input type="hidden" name="action" value="modifier_certificat_global" />
<!-- accompagnement -->
<input type="hidden" name="select_acc" value="'.$select_acc.'" />
<!-- These hidden variables are always the same -->
<input type="hidden" name="sesskey"     value="'.sesskey().'" />
<input type="hidden" name="modulename"    value="referentiel" />
<input type="hidden" name="mode"          value="'.$mode.'" />
<input type="submit" value="'.get_string("savechanges").'" />
<input type="reset" value="'.get_string("corriger", "referentiel").'" />
<input type="submit" name="cancel" value="'.get_string("quit", "referentiel").'" />
</td></tr>
</form>'."\n";

                // liste des utilisateur achevee
                if (isset($mode) && ($mode=='editcertif')){
                    // echo referentiel_modifie_activite_2_complete($record, $context, $actif);
                    echo referentiel_enqueue_certificat();
                }
                else{
                	echo referentiel_print_enqueue_certificat();
                }
                echo '<br /><br />'."\n";
            }
        }
    }
    return true;
}
}


// ----------------------------------------------------
function referentiel_modifie_global_certificat($data_filtre,$mode, $cm, $course, $referentiel_instance, $record, $context, $actif, $liste_empreintes, $select_acc){
//	Saisie et validation globale
// idem que referentiel_modifie_globale_activite_complete() sauf que le formulaire est globale
// $actif = true : le menu est active, sinon il ne l'est pas
// $data_filtre : parametres de filtrage
// $mode : mode d'affichage
// $cm : course_module
// $course : enregistrement cours
// referentiel_instance : enregistrement instance
// record : enregistrement activite
// $context : contexte roles et capacites
// $actif : affichage menu
global $USER;
global $CFG;
global $OUTPUT;
global $COURSE;
	$s='';
	$s_menu='';
	$s_document='';
	$s_out='';

	// Charger les activites
	// filtres
	$isteacher = has_capability('mod/referentiel:approve', $context);
	$isauthor = has_capability('mod/referentiel:write', $context) && !$isteacher;
	$iseditor = has_capability('mod/referentiel:writereferentiel', $context);
    if ($referentiel_instance){
         $referentiel_instance_id= $referentiel_instance->id;
    }
	if ($record){
        // debug
        // echo "<br />RECORD : print_lib_certificat :: 503\n";
        // print_object($record);
	
		$certificat_id=$record->id;
		$commentaire_certificat = stripslashes($record->commentaire_certificat);
        $synthese_certificat = stripslashes($record->synthese_certificat);
        $competences_certificat = $record->competences_certificat;
		$competences_activite = $record->competences_activite;
		$decision_jury = stripslashes($record->decision_jury);
		$decision_jury_old = stripslashes($record->decision_jury);
		$date_decision = $record->date_decision;
		$ref_referentiel = $record->ref_referentiel;
		$userid = $record->userid;
		$teacherid = $record->teacherid;
		$verrou = $record->verrou;
		$valide = $record->valide;
		$evaluation = $record->evaluation;

		if ($teacherid==0){
			if ($isteacher || $iseditor){
				$teacherid=$USER->id;
			}
		}

		$user_info=referentiel_get_user_info($userid);
		$teacher_info=referentiel_get_user_info($teacherid);

		// dates
		if ($date_decision){
            $date_decision_info=userdate($date_decision);
        }
        else{
            $date_decision_info='';
        }
		if (isset($verrou)) {
			if ($verrou){
				$bgcolor='verrouille';
			}
			else{
				$bgcolor='deverrouille';;
			}
		}
		else{
			$bgcolor='deverrouille';
		}
		// afficher le menu si l'activité


		// $s_menu.='&nbsp; <a href="'.$CFG->wwwroot.'/mod/referentiel/activite.php?d='.$ref_instance.'&activite_id='.$activite_id.'&userid='.$userid.'&mode=listactivityall&sesskey='.sesskey().'#activite"><img src="pix/search').'" alt="'.get_string('plus', 'referentiel').'" title="'.get_string('plus', 'referentiel').'" /></a>'."\n";
        $is_owner=referentiel_certificat_isowner($certificat_id);

		if (has_capability('mod/referentiel:approve', $context) || $is_owner){
            $s_menu.='<a href="'.$CFG->wwwroot.'/mod/referentiel/certificat.php?d='.$referentiel_instance_id.'&select_acc='.$select_acc.'&certificat_id='.$certificat_id.'&userid='.$userid.'&mode=listcertifsingle&sesskey='.sesskey().'#certificat_'.$certificat_id.'"><img src="'.$OUTPUT->pix_url('search','referentiel').'" alt="'.get_string('plus', 'referentiel').'" title="'.get_string('plus', 'referentiel').'" /></a>'."\n";   // loupe
//            $s_menu.='&nbsp; <a href="'.$CFG->wwwroot.'/mod/referentiel/certificat.php?d='.$referentiel_instance_id.'&select_acc='.$select_acc.'&certificat_id='.$certificat_id.'&userid='.$userid.'&mode=updatecertif&sesskey='.sesskey().'"><img src="'.$OUTPUT->pix_url('/t/edit').'" alt="'.get_string('edit').'" title="'.get_string('edit').'" /></a>'."\n";                                                 // edition
        }


        if (has_capability('mod/referentiel:comment', $context)) {
//            $s_menu.='<br /><a href="'.$CFG->wwwroot.'/mod/referentiel/certificat.php?d='.$referentiel_instance_id.'&select_acc='.$select_acc.'&certificat_id='.$certificat_id.'&userid='.$userid.'&mode=commentcertif&sesskey='.sesskey().'"><img src="'.$OUTPUT->pix_url('assigner','referentielfeedback').'" alt="'.get_string('comment', 'referentiel').'" title="'.get_string('comment', 'referentiel').'" /></a>'."\n";
	    }
        if (has_capability('mod/referentiel:managecertif', $context)) {
            $s_menu.='&nbsp; <a href="'.$CFG->wwwroot.'/mod/referentiel/certificat.php?d='.$referentiel_instance_id.'&select_acc='.$select_acc.'&certificat_id='.$certificat_id.'&userid='.$userid.'&mode=deletecertif&sesskey='.sesskey().'"><img src="'.$OUTPUT->pix_url('/t/delete').'" alt="'.get_string('certificat_initialiser', 'referentiel').'" title="'.get_string('certificat_initialiser', 'referentiel').'" /></a>'."\n";   // reinitialisation
           	if ($verrou!=0){
//                $s_menu.='<br /> <a href="'.$CFG->wwwroot.'/mod/referentiel/certificat.php?d='.$referentiel_instance_id.'&select_acc='.$select_acc.'&certificat_id='.$certificat_id.'&userid='.$userid.'&mode=deverrouiller&sesskey='.sesskey().'"><img src="'.$OUTPUT->pix_url('/t/go').'" alt="'.get_string('deverrouiller', 'referentiel').'" title="'.get_string('deverrouiller', 'referentiel').'" /></a>'."\n";    // deverrouiller
            }
            else{
//                $s_menu.='<br /><a href="'.$CFG->wwwroot.'/mod/referentiel/certificat.php?d='.$referentiel_instance_id.'&select_acc='.$select_acc.'&certificat_id='.$certificat_id.'&userid='.$userid.'&mode=verrouiller&sesskey='.sesskey().'"><img src="'.$OUTPUT->pix_url('/t/stop').'" alt="'.get_string('verrouiller', 'referentiel').'" title="'.get_string('verrouiller', 'referentiel').'" /></a>'."\n";            // verrouiller
            }
            if (referentiel_site_can_print_referentiel($referentiel_instance_id)) {
                $s_menu.='&nbsp; <a href="'.$CFG->wwwroot.'/mod/referentiel/print_certificat.php?d='.$referentiel_instance_id.'&select_acc='.$select_acc.'&certificat_id='.$certificat_id.'&userid='.$userid.'&mode=printcertif&sesskey='.sesskey().'"><img src="'.$OUTPUT->pix_url('printer','referentiel').'" alt="'.get_string('print', 'referentiel').'" title="'.get_string('print', 'referentiel').'" /></a>'."\n"; // impression
            }
        }

		// AFFICHAGE

        echo '<tr valign="top">';
        echo '<td rowspan="3" width="3%">'."\n";
        echo  '<input type="checkbox" name="tcertificat_id[]" id="tcertificat_id_'.$certificat_id.'" value="'.$certificat_id.'" />'.$certificat_id;
        echo '<br /><br />'.$s_menu;        // menu
		echo '</td>'."\n".'<td align="center">';
		echo $user_info;
		echo '</td>'."\n".'<td align="center">';
		echo $teacher_info;
		echo '</td>'."\n".'<td align="center">';
        echo '<b>'.get_string('verrou','referentiel').'</b> : ';
		if (has_capability('mod/referentiel:approve', $context)){

			if ($verrou==1){
				echo '<input type="radio" name="verrou_'.$certificat_id.'" id="verrou" value="1" checked="checked" onchange="return validerCheckBox(\'tcertificat_id_'.$certificat_id.'\')" />'.get_string('yes');
                echo ' &nbsp; <input type="radio" name="verrou_'.$certificat_id.'" id="verrou" value="0"  onchange="return validerCheckBox(\'tcertificat_id_'.$certificat_id.'\')" />'.get_string('no')."\n";
			}
			else{
				echo '<input type="radio" name="verrou_'.$certificat_id.'" id="verrou"  value="1" onchange="return validerCheckBox(\'tcertificat_id_'.$certificat_id.'\')" />'.get_string('yes');
                echo '&nbsp; <input type="radio" name="verrou_'.$certificat_id.'" id="verrou"  value="0" checked="checked"  onchange="return validerCheckBox(\'tcertificat_id_'.$certificat_id.'\')" />'.get_string('no')."\n";
			}

		}
		else{
			echo '<input type="hidden" name="verrou_'.$certificat_id.'"  id="verrou"  value="'.$verrou.'" />'."\n";
        }
		echo '</td>';
        /*
        // menu
		echo '<td align="center" rowspan="3" width="10%">'."\n";
		echo $s_menu;
		echo '</td>';
        */
        echo '</tr>'."\n";

		echo '<tr valign="top">';
		if ($verrou==0){
			echo '<td  colspan="3" class="valide" width="80%">';
		}
		else{
			echo '<td colspan="3" class="invalide">';
		}
		echo '<br />';
        // echo referentiel_affiche_overlib_item('/',$competences_certificat);
		// NOUVEAU
		referentiel_affiche_certificat_consolide('/',':',$competences_certificat, $ref_referentiel, ' class="'.$bgcolor.'"');
		// echo referentiel_affiche_competences_certificat('/',':',$competences_certificat, $liste_empreintes);
		echo '<br />';
        echo '</td>';
		echo '</tr>'."\n";
		
		echo '<tr valign="top">';
        echo '<td>';
        echo '<b>'.get_string('synthese_certificat','referentiel').'</b><br />'."\n";
		if (has_capability('mod/referentiel:comment', $context)){
    		echo '<textarea cols="40" rows="3" name="synthese_certificat_'.$certificat_id.'"  onchange="return validerCheckBox(\'tcertificat_id_'.$certificat_id.'\')" >'.$synthese_certificat.'</textarea>'."\n";
		}
		else{
			echo nl2br($synthese_certificat);
            echo '<input type="hidden" name="synthese_certificat_'.$certificat_id.'" value="'.$synthese_certificat.'" />'."\n";
		}
		echo '</td>';
        echo '<td>';
        echo '<b>'.get_string('commentaire_certificat','referentiel').'</b><br />'."\n";
		if (has_capability('mod/referentiel:comment', $context)){
			echo '<textarea cols="40" rows="2" name="commentaire_certificat_'.$certificat_id.'" onchange="return validerCheckBox(\'tcertificat_id_'.$certificat_id.'\') ">'.$commentaire_certificat.'</textarea>'."\n";
		}
		else {
			echo nl2br($commentaire_certificat);
		}
 		echo '<br />'.get_string('notification_certificat','referentiel').'<input type="radio" name="mailnow_'.$certificat_id.'" value="1" onchange="return validerCheckBox(\'tcertificat_id_'.$certificat_id.'\')" />'.get_string('yes').' &nbsp; <input type="radio" name="mailnow_'.$certificat_id.'" value="0" checked="checked" onchange="return validerCheckBox(\'tcertificat_id_'.$certificat_id.'\')" />'.get_string('no').' &nbsp; &nbsp; '."\n";
		echo '</td>';

		echo '<td>';
		echo '<br /><b>'.get_string('decision_jury','referentiel').'</b>'."\n";
		echo $date_decision_info.'<br />'."\n";

if (!empty($decision_jury)
    || (($decision_jury!=get_string('decision_favorable','referentiel'))
        && ($decision_jury!=get_string('decision_defavorable','referentiel'))
        && ($decision_jury!=get_string('decision_differee','referentiel')))){
        // boite de selection
		echo '<input type="radio" name="decision_jury_sel_'.$certificat_id.'" id="decision" value="'.get_string('decision_favorable','referentiel').'" />'.get_string('decision_favorable','referentiel')."\n";
        echo '<input type="radio" name="decision_jury_sel_'.$certificat_id.'" id="decision" value="'.get_string('decision_defavorable','referentiel').'" />'.get_string('decision_defavorable','referentiel')."\n";
        echo '<input type="radio" name="decision_jury_sel_'.$certificat_id.'" id="decision" value="'.get_string('decision_differee','referentiel').'" />'.get_string('decision_differee','referentiel')."\n";
}
else{
    if ($decision_jury==get_string('decision_favorable','referentiel')){
        // boite de selection
		echo '<input type="radio" name="decision_jury_sel_'.$certificat_id.'" id="decision" value="'.get_string('decision_favorable','referentiel').'" checked="checked"  onchange="return validerCheckBox(\'tcertificat_id_'.$certificat_id.'\')"/>'.get_string('decision_favorable','referentiel')."\n";
        echo '<input type="radio" name="decision_jury_sel_'.$certificat_id.'" id="decision" value="'.get_string('decision_defavorable','referentiel').'"  onchange="return validerCheckBox(\'tcertificat_id_'.$certificat_id.'\')"/>'.get_string('decision_defavorable','referentiel')."\n";
        echo '<input type="radio" name="decision_jury_sel_'.$certificat_id.'" id="decision" value="'.get_string('decision_differee','referentiel').'"  onchange="return validerCheckBox(\'tcertificat_id_'.$certificat_id.'\')"/>'.get_string('decision_differee','referentiel')."\n";
	}
    else if ($decision_jury==get_string('decision_defavorable','referentiel')){
        // boite de selection
		echo '<input type="radio" name="decision_jury_sel_'.$certificat_id.'" id="decision" value="'.get_string('decision_favorable','referentiel').'" onchange="return validerCheckBox(\'tcertificat_id_'.$certificat_id.'\')" />'.get_string('decision_favorable','referentiel')."\n";
        echo '<input type="radio" name="decision_jury_sel_'.$certificat_id.'" id="decision" value="'.get_string('decision_defavorable','referentiel').'" checked="checked" onchange="return validerCheckBox(\'tcertificat_id_'.$certificat_id.'\')" />'.get_string('decision_defavorable','referentiel')."\n";
        echo '<input type="radio" name="decision_jury_sel_'.$certificat_id.'" id="decision" value="'.get_string('decision_differee','referentiel').'" onchange="return validerCheckBox(\'tcertificat_id_'.$certificat_id.'\')" />'.get_string('decision_differee','referentiel')."\n";
	}
    else if ($decision_jury==get_string('decision_differee','referentiel')){
        // boite de selection
		echo '<input type="radio" name="decision_jury_sel_'.$certificat_id.'" id="decision" value="'.get_string('decision_favorable','referentiel').'" onchange="return validerCheckBox(\'tcertificat_id_'.$certificat_id.'\')" />'.get_string('decision_favorable','referentiel')."\n";
        echo '<input type="radio" name="decision_jury_sel_'.$certificat_id.'" id="decision" value="'.get_string('decision_defavorable','referentiel').'" checked="checked" onchange="return validerCheckBox(\'tcertificat_id_'.$certificat_id.'\')" />'.get_string('decision_defavorable','referentiel')."\n";
        echo '<input type="radio" name="decision_jury_sel_'.$certificat_id.'" id="decision" value="'.get_string('decision_differee','referentiel').'" checked="checked" onchange="return validerCheckBox(\'tcertificat_id_'.$certificat_id.'\')" />'.get_string('decision_differee','referentiel')."\n";
    }
    else {
        // boite de selection
		echo '<input type="radio" name="decision_jury_sel_'.$certificat_id.'" id="decision" value="'.get_string('decision_favorable','referentiel').'" onchange="return validerCheckBox(\'tcertificat_id_'.$certificat_id.'\')" />'.get_string('decision_favorable','referentiel')."\n";
        echo '<input type="radio" name="decision_jury_sel_'.$certificat_id.'" id="decision" value="'.get_string('decision_defavorable','referentiel').'" onchange="return validerCheckBox(\'tcertificat_id_'.$certificat_id.'\')" />'.get_string('decision_defavorable','referentiel')."\n";
        echo '<input type="radio" name="decision_jury_sel_'.$certificat_id.'" id="decision" value="'.get_string('decision_differee','referentiel').'" onchange="return validerCheckBox(\'tcertificat_id_'.$certificat_id.'\')" />'.get_string('decision_differee','referentiel')."\n";
	}
}
    echo '<br /><i>'.get_string('rediger_decision','referentiel').'</i><br />'."\n";
    echo '<input type="text" name="decision_jury_'.$certificat_id.'" size="80" maxlength="80" value="'.$decision_jury.'" />
    </td>
</tr>
';
        echo '
<input type="hidden" name="decision_jury_old_'.$certificat_id.'" value="'.$decision_jury_old.'" />
<input type="hidden" name="valide_'.$certificat_id.'" value="'.$valide.'" />
<input type="hidden" name="evaluation_'.$certificat_id.'" value="'.$evaluation.'" />
<input type="hidden" name="date_decision_'.$certificat_id.'" value="'.$date_decision.'" />
<input type="hidden" name="userid_'.$certificat_id.'" value="'.$userid.'" />
<input type="hidden" name="teacherid_'.$certificat_id.'" value="'.$teacherid.'" />
<input type="hidden" name="certificat_id_'.$certificat_id.'" value="'.$certificat_id.'" />
<input type="hidden" name="ref_referentiel_'.$certificat_id.'" value="'.$ref_referentiel.'" />
<input type="hidden" name="instance_'.$certificat_id.'" value="'.$referentiel_instance_id.'" />
<input type="hidden" name="competences_activite_'.$certificat_id.'" value="'.$competences_activite.'" />
<input type="hidden" name="competences_certificat_'.$certificat_id.'" value="'.$competences_certificat.'" />'."\n\n";

	}
	return $s;
}




function referentiel_jauge_activite($valide, $empreinte){
// ecrit un tableau dont le nombre de cases est proportionnel à la valeur de l'empreinte
// remplit ce tableau avec des cases colorees en indiquant le nombre de validation obtenues / a obtenir
	$s='<table class="certificat" width="100%">'."\n";
	$s.='<tr valign="top">'."\n";
	if ($valide==0)	{
		$s.='<td class="verrouille">0</td>';
	}
	else if ($valide<$empreinte){
		$reste=$empreinte-$valide;
		$s.='<td class="deverrouille" colspan='.$valide.'>'.$valide.'</td>';
		$s.='<td class="verrouille" colspan='.$reste.'>'.$reste.'</td>';
	}
	else if ($valide>=$empreinte){
		$s.='<td class="deverrouille">'.$valide.'</td>';
	}
	$s.='</tr></table>'."\n";		
	return $s;
}

// ----------------------------------------------------
function referentiel_affiche_detail_competences($separateur1, $separateur2, $liste, $liste_empreintes, $liste_poids){

	$t_empreinte=explode($separateur1, $liste_empreintes);
	$t_poids=explode('|', $liste_poids);	
	// DEBUG
	// echo "<br>DEBUG : print_lib_certificat.php :: 105<br>LISTE EMPREINTES : $liste_empreintes<br>\n";
	// print_r($t_empreinte);
	// DEBUG
	// echo "<br>DEBUG : print_lib_certificat.php :: 108<br>LISTE POIDS : $liste_poids<br>\n";
	// print_r($t_poids);
	// exit;
	$s='';
	$tc=array();
	$liste=referentiel_purge_dernier_separateur($liste, $separateur1);
		if (!empty($liste) && ($separateur1!="") && ($separateur2!="")){
			$tc = explode ($separateur1, $liste);
			// DEBUG 
			// echo "<br />CODE <br />\n";
			// print_r($tc);
			$i=0;
			while ($i<count($tc)){
				// CODE1:N1
				// DEBUG 
				// echo "<br />".$tc[$i]." <br />\n";
				// exit;
				if ($tc[$i]!=''){
					$tcc=explode($separateur2, $tc[$i]);
					// echo "<br />".$tc[$i]." <br />\n";
					// print_r($tcc);
					// exit;
					$s.='<tr valign="top">'."\n";
					
					if (isset($tcc[1]) && ($tcc[1]>=$t_empreinte[$i])){
						$s.='<td> <span class="valide"><b>'.$tcc[0].'</b></span></td>'."\n";
						$s.='<td>'.referentiel_jauge_activite($tcc[1], $t_empreinte[$i]).'</td>'."\n";
						$s.='<td colspan="3">'.str_replace('#','</td><td><b>',$t_poids[$i]).'</b></td>'."\n";
					}
					else{
						$s.='<td> <span class="invalide"><i>'.$tcc[0].'</i></span></td>'."\n";
						$s.='<td>'.referentiel_jauge_activite($tcc[1], $t_empreinte[$i]).'</td>'."\n";
						$s.='<td colspan="3">'.str_replace('#','</td><td><i>',$t_poids[$i]).'</i></td>'."\n";
					}
					$s.='<td>'.$t_empreinte[$i].'</td>'."\n";
					$s.='</tr>'."\n";
				}
				$i++;
			} 
		}
	return $s;
}


// ----------------------------------------------------
function referentiel_print_entete_certificat(){
// Affiche une entete certificat
$s="";
	$s.='<table class="certificat">'."\n";
	$s.='<tr valign="top">';
	// $s.='<th><b>'.get_string('id','referentiel').'</b></th>';
	$s.='<th>'.get_string('etudiant','referentiel').'</th>';
	$s.='<th>'.get_string('referent','referentiel').'</th>';

	// $s.='<th>'.get_string('certificat_etat','referentiel').'</th>';
	$s.='<th><i>'.get_string('seuil','referentiel').'</i></th>';
    $s.='<th><span class="small"><b>'.get_string('date_proposition','referentiel').'</b></span>';
	$s.=' : '.get_string('decision_jury','referentiel').'</th>';
    $s.='</tr>'."\n";

    // <$s.='<th>'.get_string('verrou','referentiel').'</th>';
	// $s.='<th>'.get_string('valide','referentiel').'</th>';
	// $s.='<th colspan="2">'.get_string('evaluation','referentiel').'</th>';

    $s.='<tr valign="top">';
    $s.='<th>'.get_string('synthese_certificat','referentiel').'</th>';
    $s.='<th colspan="2">'.get_string('competences_certificat','referentiel').'</th>';
	$s.='<th>'.get_string('commentaire','referentiel').'</th>';
    $s.='</tr>'."\n";

	return $s;
}



// ----------------------------------------------------
function referentiel_print_entete_certificat_tableau(){
// Affiche une entete certificat
$s="";
	$s.='<table class="certificat">'."\n";
	$s.='<tr valign="top">';
	// $s.='<th>'.get_string('id','referentiel').'</th>';
	$s.='<th>'.get_string('etudiant','referentiel').'</th>';
	$s.='<th>'.get_string('referent','referentiel').'</th>';
	$s.='<th colspan="4" rowspan="2"><b>'.get_string('competences_certificat','referentiel').'</th>';
	/*
	print_string('commentaire_certificat','referentiel');
	print_string('synthese_certificat','referentiel');
	*/
	// $s.='<th>'.get_string('certificat_etat','referentiel').'</th>';

	$s.='<th colspan="2"><i>'.get_string('seuil','referentiel').'</i><br /><span class="small"><b>'.get_string('date_proposition','referentiel').'</b></span></th>';
    $s.='</tr><tr valign="top">';
	$s.='<th colspan="2"><b>'.get_string('decision_jury','referentiel').'</th>';

	// <$s.='<th>'.get_string('verrou','referentiel').'</th>';
	// $s.='<th>'.get_string('valide','referentiel').'</th>';
	// $s.='<th colspan="2"><b>'.get_string('evaluation','referentiel').'</th>';

    $s.='<th colspan="2"><b>'.get_string('synthese_certificat','referentiel').'</th>';
	$s.='<th colspan="2"><b>'.get_string('commentaire','referentiel').'</th>';
	$s.='</tr>'."\n";
	return $s;
}

// ----------------------------------------------------
function referentiel_print_enqueue_certificat(){
// Affiche enqueue certificat
	$s='</table>'."\n";
	return $s;
}

// Affiche une certificat en mode compact
// *****************************************************************
// input @param a $record_a   of certificat                        *
// output null                                                     *
// *****************************************************************

function new_referentiel_print_certificat($record_a){
$s="";
	if ($record_a){
		$certificat_id=$record_a->id;
		$commentaire_certificat = stripslashes($record_a->commentaire_certificat);
        $synthese_certificat = stripslashes($record_a->synthese_certificat);
        $competences_certificat = $record_a->competences_certificat;
		$competences_activite = $record_a->competences_activite;
		$decision_jury = stripslashes($record_a->decision_jury);
		$date_decision = $record_a->date_decision;
		$ref_referentiel = $record_a->ref_referentiel;
		$userid = $record_a->userid;
		$teacherid = $record_a->teacherid;
		$verrou = $record_a->verrou;
		$valide = $record_a->valide;
		$evaluation = $record_a->evaluation;
		
		$user_info=referentiel_get_user_info($userid);
		$teacher_info=referentiel_get_user_info($teacherid);
		
		// dates
		$date_decision_info=userdate($date_decision);
		
		// empreintes
		$liste_empreintes=referentiel_purge_dernier_separateur(referentiel_get_liste_empreintes_competence($ref_referentiel), '/');

		echo '<tr valign="top"><td>';
		echo $certificat_id;
		echo '</td><td>';
		echo $user_info;
		if (isset($verrou) && ($verrou!="")) {
			if ($verrou!=0){
				$bgcolor='verrouille';
			}
			else{
				$bgcolor='deverrouille';;
			}
		}
		else{
			$bgcolor='deverrouille';
		}
		echo '</td><td>';
		// NOUVEAU 
		referentiel_affiche_certificat_consolide('/',':',$competences_certificat, $ref_referentiel, ' class="'.$bgcolor.'"');
		// $s.=referentiel_affiche_competences_certificat('/',':',$competences_certificat, $liste_empreintes);
/* MODIF JF */
		$s.='</td><td>';
		$s.=nl2br($synthese_certificat);
		$s.='</td><td>';
		$s.=nl2br($commentaire_certificat);

/* */
/*
		$s.='</td>';
		if (!isset($verrou) or ($verrou=="") or ($verrou==0)){
			$s.='</td><td class="deverrouille">';
			$s.=get_string('deverrouille', 'referentiel');
		}
		else {
			$s.='</td><td class="verrouille">';		
			$s.=get_string('verrouille', 'referentiel');
		}
 */
		echo '</td><td>';
		echo $teacher_info;
		echo '</td><td>';
		if (isset($decision_jury) && ($decision_jury!="")){
			echo $decision_jury;
		}
		else{
			echo $decision_jury;	
		}
		echo '</td><td>';
		if (($date_decision!="") && ($date_decision>0)){
			echo '<span class="small">'.$date_decision_info.'</span>';
		}
		else{
			echo '&nbsp;';
		}
		echo '</td><td>';
/*		
		if (isset($verrou) && ($verrou!="")) {
			if ($verrou!=0){
				$s.='</td><td class="verrouille">'.get_string('verrouille','referentiel');
			}
			else{
				$s.='</td><td class="deverrouille">'.get_string('deverrouille','referentiel');
			}
		}
		else{
			$s.='</td><td class="deverrouille">'.get_string('deverrouille','referentiel');
		}
		if (isset($valide) && ($valide!="")) {
			if ($valide!=0){
				$s.='</td><td>'.get_string('valide','referentiel');
			}
			else{
				$s.='</td><td>'.get_string('invalide','referentiel');
			}
		}
		else{
			$s.='</td><td>'.get_string('invalide','referentiel');
		}
*/
		if (isset($evaluation)) {
			echo $evaluation;
		}
		else{
			echo '&nbsp;';
		}
		echo '</td></tr>'."\n";
	  return true;
  }
	return false;
}

/**
 * Impression d'un certificat sous forme de tableau
 *
 */
 
// --------------------------------------------
function referentiel_print_certificat($record_a, $seuil_certification=0){
$s="";
	if ($record_a){
		$certificat_id=$record_a->id;
		$commentaire_certificat = stripslashes($record_a->commentaire_certificat);
        $synthese_certificat = stripslashes($record_a->synthese_certificat);
		$competences_certificat = $record_a->competences_certificat;
		$competences_activite = $record_a->competences_activite;
		$decision_jury = stripslashes($record_a->decision_jury);
		$date_decision = $record_a->date_decision;
		$ref_referentiel = $record_a->ref_referentiel;
		$userid = $record_a->userid;
		$teacherid = $record_a->teacherid;
		$verrou = $record_a->verrou;
		$valide = $record_a->valide;
		$evaluation = $record_a->evaluation;
		
		$user_info=referentiel_get_user_info($userid);
		$teacher_info=referentiel_get_user_info($teacherid);
		
		// dates
		$date_decision_info=userdate($date_decision);
		
		// empreintes
		$liste_empreintes=referentiel_purge_dernier_separateur(referentiel_get_liste_empreintes_competence($ref_referentiel), '/');
		if (isset($verrou) && ($verrou!="")) {
			if ($verrou!=0){
				$bgcolor='verrouille';
			}
			else{
				$bgcolor='deverrouille';;
			}
		}
		else{
			$bgcolor='deverrouille';
		}

		$s.='<tr class="bordertop" valign="top">';
        /*
        $s.= <td>';
		$s.= $certificat_id;
		$s.='</td>';
        */
        if ($bgcolor=='deverrouille'){
            $s.='<td class="deverrouille">';
        }
        else{
            $s.='<td class="verrouille">';
        }
		$s.=$user_info;
		$s.='</td><td>';
		if ($teacher_info){
            $s.=$teacher_info;
        }
        else{
            $s.='&nbsp;';
        }
        $s.='</td>';
  		if (isset($evaluation) && $seuil_certification) {
            if ($evaluation > $seuil_certification){
                $s.='<td class="prioritaire">';
            }
            else{
                $s.='<td>';
            }
            $s.="<b>$evaluation</b> / <i>$seuil_certification</i>\n";
		}
		else{
            $s.='<td>&nbsp;';
        }
		$s.='</td><td>';
		if (isset($decision_jury) && ($decision_jury!="")){
            if (($date_decision!="") && ($date_decision>0)){
                $s.='<span class="small">'.$date_decision_info.'</span> : ';
            }
			$s.='<b>'.$decision_jury.'</b>';
		}
		else{
			$s.='&nbsp;';
		}
        $s.='</td>';
        $s.='</tr>'."\n";
        $s.='<tr valign="top"><td colspan="2">&nbsp;';
        $s.=nl2br($synthese_certificat);
		$s.='</td><td colspan="2">&nbsp;';
		$s.=nl2br($commentaire_certificat);

/* */
/*
		$s.='</td>';
		if (!isset($verrou) or ($verrou=="") or ($verrou==0)){
			$s.='</td><td class="deverrouille">';
			$s.=get_string('deverrouille', 'referentiel');
		}
		else {
			$s.='</td><td class="verrouille">';
			$s.=get_string('verrouille', 'referentiel');
		}
*/


/*
		if (isset($verrou) && ($verrou!="")) {
			if ($verrou!=0){
				$s.='</td><td class="verrouille">'.get_string('verroulle','referentiel');
			}
			else{
				$s.='</td><td class="deverrouille">'.get_string('deverroulle','referentiel');
			}
		}
		else{
			$s.='</td><td class="deverrouille">'.get_string('deverroulle','referentiel');
		}
		if (isset($valide) && ($valide!="")) {
			if ($valide!=0){
				$s.='</td><td>'.get_string('valide','referentiel');
			}
			else{
				$s.='</td><td>'.get_string('invalide','referentiel');
			}
		}
		else{
			$s.='</td><td>'.get_string('invalide','referentiel');
		}
*/
		$s.='</td></tr>'."\n";

        $s.='<tr valign="top">';
		$s.='<td colspan="4">';
		echo $s;

        // NOUVEAU
		referentiel_affiche_certificat_consolide('/',':',$competences_certificat, $ref_referentiel, ' class="'.$bgcolor.'"');

		// $s.=referentiel_affiche_competences_certificat('/',':',$competences_certificat, $liste_empreintes);
		$s='';
		$s.='</td></tr>'."\n";

		echo $s;
		return true;
	}
	return false;
}

// Affiche une certificat 
// *****************************************************************
// input @param a $record_a   of certificat                        *
// output null                                                     *
// *****************************************************************

function referentiel_print_certificat_detail($record_a){
	if ($record_a){
		$certificat_id=$record_a->id;
		$commentaire_certificat = stripslashes($record_a->commentaire_certificat);
        $synthese_certificat = stripslashes($record_a->synthese_certificat);
		$competences_certificat = $record_a->competences_certificat;
		$decision_jury = stripslashes($record_a->decision_jury);
		$date_decision = $record_a->date_decision;
		$ref_referentiel = $record_a->ref_referentiel;
		$userid = $record_a->userid;
		$teacherid = $record_a->teacherid;
		$verrou = $record_a->verrou;
		$valide = $record_a->valide;
		$evaluation = $record_a->evaluation;

		$user_info=referentiel_get_user_info($userid);
		$teacher_info=referentiel_get_user_info($teacherid);
		
		// dates
		$date_decision_info=userdate($date_decision);
		
		// empreintes
		$liste_empreintes=referentiel_purge_dernier_separateur(referentiel_get_liste_empreintes_competence($ref_referentiel), '/');
?>

<a name="<?php  echo "certificat_$certificat_id"; ?>"></a>
<hr />
<table cellpadding="5">
<tr valign="top">
    <td align="right" width="20%">
	<b><?php  print_string('id','referentiel'); ?> : </b>
    </td>
    <td align="left">
	<?php  p($certificat_id) ?>
    </td>
    <td align="right" width="20%">
     <b><?php print_string('etudiant','referentiel')?> : </b>
    </td>
    <td align="left">
		<?php p($user_info) ?>
    </td>
	<td align="right" width="20%">
	<b><?php  print_string('date_decision','referentiel') ?> : </b>
	</td>	
    <td align="left">
		<?php  echo '<span class="small">'.$date_decision_info.'</span>'; ?>
    </td>		
</tr>
<tr valign="top">
    <td align="right" width="20%">
	<b><?php  print_string('competences_certificat','referentiel') ?> : </b>
	</td>
    <td align="left" colspan="5">	
<?php  
		echo referentiel_affiche_competences_certificat('/',':',$competences_certificat, $liste_empreintes);
?>
    </td>
</tr>
<tr valign="top">
    <td align="left" colspan="3">
        <?php  echo (nl2br($synthese_certificat)); ?>
    </td>
    <td align="right" width="20%">
	<b><?php  print_string('synthese_certificat','referentiel') ?>:</b>
	</td>
</tr>
<tr valign="top">
    <td align="right" width="20%">
	<b><?php  print_string('commentaire','referentiel') ?>:</b>
	</td>
    <td align="left" colspan="3">
        <?php  echo (nl2br($commentaire_certificat)); ?>
    </td>
</tr>
<tr valign="top">
    <td align="right" width="20%">
	<b><?php  print_string('certificat_etat','referentiel') ?>:</b>
	</td>
    <td align="left">
<?php
		if (!isset($verrou) or ($verrou=="") or ($verrou==0)){
			echo get_string('deverrouille', 'referentiel');
		}
		else {
			echo get_string('verrouille', 'referentiel');
		}
?>
    <td align="right" width="20%">
     <b><?php   print_string('referent','referentiel') ?> : </b>
    </td>
	<td align="left">
	<?php p($teacher_info); ?>
    </td>
    <td align="right" width="20%">
     <b><?php   print_string('validation','referentiel') ?> : </b>
    </td>
	<td align="left">
<?php
		if (isset($decision_jury) && ($decision_jury!="")){
			p($decision_jury);
		}
		else{
			echo '&nbsp;'."\n";	
		}
		echo '</td>
<td>';
		if (isset($valide) && ($valide!="")) {
			if ($valide!=0){
				echo get_string('valide','referentiel');
			}
			else{
				echo get_string('invalide','referentiel');
			}
		}
		else{
			echo get_string('invalide','referentiel');
		}
		echo '</td>
<td>';
		if (isset($evaluation)) {
			echo $evaluation;
		}
		else{
			echo '&nbsp;';
		}
?>	
</td>
</tr>
</table>
<?php
	}
}


// Affiche une certificat en ligne avec le detail des competences
// *****************************************************************
// input @param a $record_a   of certificat                        *
// output null                                                     *
// *****************************************************************

function referentiel_print_certificat_detail_une_page($record_a){
	if ($record_a){
		$certificat_id=$record_a->id;
		$commentaire_certificat = stripslashes($record_a->commentaire_certificat);
		$synthese_certificat = stripslashes($record_a->synthese_certificat);
		$competences_certificat = $record_a->competences_certificat;
		$decision_jury = stripslashes($record_a->decision_jury);
		$date_decision = $record_a->date_decision;
		$ref_referentiel = $record_a->ref_referentiel;
		$userid = $record_a->userid;
		$teacherid = $record_a->teacherid;
		$verrou = $record_a->verrou;
		$valide = $record_a->valide;
		$evaluation = $record_a->evaluation;

		$user_info=referentiel_get_user_info($userid);
		$teacher_info=referentiel_get_user_info($teacherid);
		
		// dates
		$date_decision_info=userdate($date_decision);
		// empreintes
		$liste_empreintes=referentiel_purge_dernier_separateur(referentiel_get_liste_empreintes_competence($ref_referentiel), '/');
		$liste_poids=referentiel_purge_dernier_separateur(referentiel_get_liste_poids($ref_referentiel), '|');
		
		// DEBUG
		// echo "<br />DEBUG :: 595 print_lib_certificat.php :: <br />EMPREINTES :  $liste_empreintes<br />POIDS : $liste_poids\n";
?>

<a name="<?php  echo "certificat_$certificat_id"; ?>"></a>
<hr />
<table class="certificat">
<tr valign="top">
    <td width="5%">
	<b><?php  print_string('id','referentiel'); ?> : </b>
	<?php  p($certificat_id) ?>
    </td>
    <td>
     <b><?php print_string('etudiant','referentiel')?> : </b>
		<?php p($user_info) ?>
    </td>
	<td>
	<b><?php  print_string('date_decision','referentiel') ?> : </b>
		<?php  echo '<span class="small">'.$date_decision_info.'</span>'; ?>
    </td>		
    <td>
	<b><?php  print_string('certificat_etat','referentiel') ?>:</b>
<?php
		if (!isset($verrou) or ($verrou=="") or ($verrou==0)){
			echo get_string('deverrouille', 'referentiel');
		}
		else {
			echo get_string('verrouille', 'referentiel');
		}
?>
	</td>
    <td>
     <b><?php   print_string('referent','referentiel') ?> : </b>
	<?php p($teacher_info); ?>
    </td>
    <td>
     <b><?php   print_string('validation','referentiel') ?> : </b>
<?php
		if (isset($decision_jury) && ($decision_jury!="")){
			p($decision_jury);
		}
		else{
			echo '&nbsp;'."\n";	
		}
/*		
		echo '</td><td>';
		if (isset($valide) && ($valide!="")) {
			if ($valide!=0){
				echo get_string('valide','referentiel');
			}
			else{
				echo get_string('invalide','referentiel');
			}
		}
		else{
			echo get_string('invalide','referentiel');
		}
*/		
		echo '   </td>
<td><b>'.get_string('evaluation','referentiel').' :</b>'."\n";
		if (isset($evaluation)) {
			echo $evaluation;
		}
		else{
			echo '&nbsp;';
		}
?>	
    </td>
</tr>
<tr valign="top">
	<td colspan="7">
	<b><?php print_string('competences_certificat','referentiel'); ?></b>
	</td>
</tr>
<tr valign="top">
    <td>
	<b><?php  print_string('code','referentiel') ?> </b>
    </td>
    <td>
	<b><?php  print_string('verrou','referentiel') ?> </b>
    </td>
    <td colspan="3">
	<b><?php  print_string('description_item','referentiel') ?> </b>
    </td>
    <td>
	<b><?php  print_string('p_item','referentiel') ?> </b>
    </td>
    <td>
	<b><?php  print_string('e_item','referentiel') ?> </b>
    </td>
</tr>
<?php echo referentiel_affiche_detail_competences('/',':',$competences_certificat, $liste_empreintes, $liste_poids); ?>
<tr valign="top">
    <td colspan="7">
	<b><?php  print_string('synthese_certificat','referentiel') ?> :</b>
        <?php  echo (nl2br($synthese_certificat)); ?>
    </td>
</tr>
<tr valign="top">
    <td colspan="7">
	<b><?php  print_string('commentaire','referentiel') ?> :</b>
        <?php  echo (nl2br($commentaire_certificat)); ?>
    </td>
</tr>
</table>
<?php
	}
}



// *****************************************************************
// input @param id_referentiel   of certificat                     *
// output null                                                     *
// *****************************************************************
// Affiche les certificats de ce referentiel
function referentiel_liste_tous_certificats($id_referentiel){
	if (isset($id_referentiel) && ($id_referentiel>0)){
		// DEBUG
		// echo "<br/>DEBUG :: $id_referentiel<br />\n";
		$seuil_certification=referentiel_get_seuil_certification($id_referentiel);

		$records = referentiel_get_certificats($id_referentiel);
		if (!$records){
			error(get_string('nocertificat','referentiel'), "certificat.php?d=$id_referentiel&mode=add");
		}
	    else {
    		// afficher
			// DEBUG
			// echo "<br/>DEBUG ::<br />\n";
			// print_r($records);
			foreach ($records as $record){
				referentiel_print_certificat($record, $seuil_certification);
			}
		}
	}
}

// Affiche les certificats de ce referentiel
function referentiel_menu_certificat_detail($context, $certificat_id, $referentiel_instance_id, $verrou, $userid, $select_acc=0){
	global $CFG;
	global $OUTPUT;
	echo '<div align="center">';
	echo '&nbsp; <a href="'.$CFG->wwwroot.'/mod/referentiel/certificat.php?d='.$referentiel_instance_id.'&select_acc='.$select_acc.'&certificat_id='.$certificat_id.'&userid='.$userid.'&mode=listcertif&sesskey='.sesskey().'#certificat_'.$certificat_id.'"><img src="'.$OUTPUT->pix_url('nosearch','referentiel').'" alt="'.get_string('moins', 'referentiel').'" title="'.get_string('moins', 'referentiel').'" /></a>';
	if (has_capability('mod/referentiel:comment', $context)) {
//		or referentiel_certificat_isowner($certificat_id)) {
        echo '&nbsp; <a href="'.$CFG->wwwroot.'/mod/referentiel/certificat.php?d='.$referentiel_instance_id.'&select_acc='.$select_acc.'&certificat_id='.$certificat_id.'&userid='.$userid.'&mode=commentcertif&sesskey='.sesskey().'"><img src="'.$OUTPUT->pix_url('feedback','referentiel').'" alt="'.get_string('comment', 'referentiel').'" title="'.get_string('comment', 'referentiel').'" /></a>'."\n";
	}
	if (has_capability('mod/referentiel:managecertif', $context)) {
//		or referentiel_certificat_isowner($certificat_id)) {
        echo '&nbsp; <a href="'.$CFG->wwwroot.'/mod/referentiel/certificat.php?d='.$referentiel_instance_id.'&select_acc='.$select_acc.'&certificat_id='.$certificat_id.'&userid='.$userid.'&mode=updatecertif&sesskey='.sesskey().'"><img src="'.$OUTPUT->pix_url('/t/edit').'" alt="'.get_string('edit').'" title="'.get_string('edit').'" /></a>'."\n";
        echo '&nbsp; <a href="'.$CFG->wwwroot.'/mod/referentiel/certificat.php?d='.$referentiel_instance_id.'&select_acc='.$select_acc.'&certificat_id='.$certificat_id.'&userid='.$userid.'&mode=deletecertif&sesskey='.sesskey().'"><img src="'.$OUTPUT->pix_url('/t/delete').'" alt="'.get_string('certificat_initialiser', 'referentiel').'" title="'.get_string('certificat_initialiser', 'referentiel').'" /></a>'."\n";
		if ($verrou){
			echo '&nbsp; <a href="'.$CFG->wwwroot.'/mod/referentiel/certificat.php?d='.$referentiel_instance_id.'&select_acc='.$select_acc.'&certificat_id='.$certificat_id.'&userid='.$userid.'&mode=deverrouiller&sesskey='.sesskey().'"><img src="'.$OUTPUT->pix_url('/t/stop').'" alt="'.get_string('deverrouiller', 'referentiel').'" /></a>'."\n";
        }
		else{
			echo '&nbsp; <a href="'.$CFG->wwwroot.'/mod/referentiel/certificat.php?d='.$referentiel_instance_id.'&select_acc='.$select_acc.'&certificat_id='.$certificat_id.'&userid='.$userid.'&mode=verrouiller&sesskey='.sesskey().'"><img src="'.$OUTPUT->pix_url('/t/go').'" alt="'.get_string('verrouiller', 'referentiel').'" /></a>'."\n";
		}
        if (referentiel_site_can_print_referentiel($referentiel_instance_id)) {
			echo '&nbsp; <a href="'.$CFG->wwwroot.'/mod/referentiel/print_certificat.php?d='.$referentiel_instance_id.'&select_acc='.$select_acc.'&certificat_id='.$certificat_id.'&userid='.$userid.'&mode=printcertif&sesskey='.sesskey().'"><img src="'.$OUTPUT->pix_url('printer','referentiel').'" alt="'.get_string('print', 'referentiel').'" title="'.get_string('print', 'referentiel').'" /></a>'."\n";
		}
	}
	echo '</div><br />';
}



// Affiche les certificats de ce referentiel
function referentiel_menu_certificat($context, $certificat_id, $referentiel_instance_id, $verrou, $userid=0, $select_acc=0){
	global $CFG;
	global $OUTPUT;
	$s="";

	$s.='<tr class="borderbottom" valign="top"><td align="center" colspan="6">'."\n";

    $s.='&nbsp; <a href="'.$CFG->wwwroot.'/mod/referentiel/certificat.php?d='.$referentiel_instance_id.'&select_acc='.$select_acc.'&certificat_id='.$certificat_id.'&userid='.$userid.'&mode=listcertifsingle&sesskey='.sesskey().'#certificat_'.$certificat_id.'"><img src="'.$OUTPUT->pix_url('search','referentiel').'" alt="'.get_string('plus', 'referentiel').'" title="'.get_string('plus', 'referentiel').'" /></a>'."\n";
    $s.='&nbsp; <a href="'.$CFG->wwwroot.'/mod/referentiel/certificat.php?d='.$referentiel_instance_id.'&select_acc='.$select_acc.'&certificat_id='.$certificat_id.'&userid='.$userid.'&mode=updatecertif&sesskey='.sesskey().'"><img src="'.$OUTPUT->pix_url('/t/edit').'" alt="'.get_string('edit').'" title="'.get_string('edit').'" /></a>'."\n";

	if (has_capability('mod/referentiel:comment', $context)) {
//		or referentiel_certificat_isowner($certificat_id)) {
		$s.='&nbsp; <a href="'.$CFG->wwwroot.'/mod/referentiel/certificat.php?d='.$referentiel_instance_id.'&select_acc='.$select_acc.'&certificat_id='.$certificat_id.'&userid='.$userid.'&mode=commentcertif&sesskey='.sesskey().'"><img src="'.$OUTPUT->pix_url('feedback','referentiel').'" alt="'.get_string('comment', 'referentiel').'" title="'.get_string('comment', 'referentiel').'" /></a>'."\n";
	}
	if (has_capability('mod/referentiel:managecertif', $context)) {
//		or referentiel_certificat_isowner($certificat_id)) {
//     $s.='&nbsp; <a href="'.$CFG->wwwroot.'/mod/referentiel/certificat.php?d='.$referentiel_instance_id.'&select_acc='.$select_acc.'&certificat_id='.$certificat_id.'&userid='.$userid.'&mode=updatecertif&sesskey='.sesskey().'"><img src="'.$OUTPUT->pix_url('/t/edit').'" alt="'.get_string('edit').'" title="'.get_string('edit').'" /></a>'."\n";
	  $s.='&nbsp; <a href="'.$CFG->wwwroot.'/mod/referentiel/certificat.php?d='.$referentiel_instance_id.'&select_acc='.$select_acc.'&certificat_id='.$certificat_id.'&userid='.$userid.'&mode=deletecertif&sesskey='.sesskey().'"><img src="'.$OUTPUT->pix_url('/t/delete').'" alt="'.get_string('certificat_initialiser', 'referentiel').'" title="'.get_string('certificat_initialiser', 'referentiel').'" /></a>'."\n";
		if ($verrou){
			$s.='&nbsp; <a href="'.$CFG->wwwroot.'/mod/referentiel/certificat.php?d='.$referentiel_instance_id.'&select_acc='.$select_acc.'&certificat_id='.$certificat_id.'&userid='.$userid.'&mode=deverrouiller&sesskey='.sesskey().'"><img src="'.$OUTPUT->pix_url('/t/go').'" alt="'.get_string('deverrouiller', 'referentiel').'" title="'.get_string('deverrouiller', 'referentiel').'" /></a>'."\n";
        }
		else{
			$s.='&nbsp; <a href="'.$CFG->wwwroot.'/mod/referentiel/certificat.php?d='.$referentiel_instance_id.'&select_acc='.$select_acc.'&certificat_id='.$certificat_id.'&userid='.$userid.'&mode=verrouiller&sesskey='.sesskey().'"><img src="'.$OUTPUT->pix_url('/t/stop').'" alt="'.get_string('verrouiller', 'referentiel').'" title="'.get_string('verrouiller', 'referentiel').'" /></a>'."\n";
		}
		if (referentiel_site_can_print_referentiel($referentiel_instance_id)) { 
    	$s.='&nbsp; <a href="'.$CFG->wwwroot.'/mod/referentiel/print_certificat.php?d='.$referentiel_instance_id.'&select_acc='.$select_acc.'&certificat_id='.$certificat_id.'&userid='.$userid.'&mode=printcertif&sesskey='.sesskey().'"><img src="'.$OUTPUT->pix_url('printer','referentiel').'" alt="'.get_string('print', 'referentiel').'" title="'.get_string('print', 'referentiel').'" /></a>'."\n";
		}
	}
	
	// Portofolio
	if (!empty($CFG->enableportfolios)){
        require_once($CFG->libdir.'/portfoliolib.php');
        $button = new portfolio_add_button();
        $button->set_callback_options('referentiel_portfolio_caller',
            array('instanceid' => $referentiel_instance_id, 'certificatid' => $certificat_id, 'report' => 0, 'export_format' => ''), '/mod/referentiel/locallib_portfolio.php');
        $button->set_formats(array(PORTFOLIO_FORMAT_PLAINHTML, PORTFOLIO_FORMAT_LEAP2A));
        $s.=$button->to_html(PORTFOLIO_ADD_ICON_LINK);
    }
	$s.='</td></tr>'."\n";
	return $s;
}

/************************************************************************
 * takes a list of records, the current referentiel, a search string,   *
 * and mode to display                                                  *
 * input @param array $records   of certificat                            *
 *       @param object $referentiel                                     *
 *       @param string $search                                          *
 *       @param int $select_acc                                            *
 * output null                                                          *
 ************************************************************************/
function referentiel_print_liste_certificats($mode, $referentiel_instance, $userid_filtre=0, $gusers, $select_acc=0) {
global $CFG;
global $USER;
static $isteacher=false;
static $isauthor=false;
static $iseditor=false;
static $referentiel_id = NULL;
	// contexte
global $DB;

    if (!empty($referentiel_instance)){
        $cm = get_coursemodule_from_instance('referentiel', $referentiel_instance->id);
        $course = $DB->get_record("course", array("id" => "$cm->course"));
        if (empty($cm) or empty($course)){
            print_error('REFERENTIEL_ERROR 5 :: print_lib_certificat.php :: You cannot call this script in that way');
	   }
	
    $context = get_context_instance(CONTEXT_MODULE, $cm->id);

	$referentiel_id = $referentiel_instance->ref_referentiel;
	
	$iseditor = has_capability('mod/referentiel:managecertif', $context);
	$isteacher = has_capability('mod/referentiel:approve', $context)&& !$iseditor;
	$istutor = has_capability('mod/referentiel:comment', $context) && !$iseditor  && !$isteacher;	
	$isauthor = has_capability('mod/referentiel:write', $context) && !$iseditor  && !$isteacher  && !$istutor;

	
	// DEBUG
	/*
	if ($isteacher) echo "Teacher ";
	if ($iseditor) echo "Editor ";
	if ($istutor) echo "Tutor ";
	if ($isauthor) echo "Author ";
	echo "<br>UseridFiltre= $userid_filtre\n";
	*/
	
	if (isset($referentiel_id) && ($referentiel_id>0)){
		$referentiel_referentiel=referentiel_get_referentiel_referentiel($referentiel_id);
		if (!$referentiel_referentiel){
			if ($iseditor){
				error(get_string('creer_referentiel','referentiel'), "$CFG->wwwroot/mod/referentiel/edit.php?d=$referentiel_instance->id&mode=editreferentiel&sesskey=".sesskey());
			}
			else {
				error(get_string('creer_referentiel','referentiel'), "$CFG->wwwroot/course/view.php?id=$course->id&sesskey=".sesskey());
			}
		}

        $seuil_certification=$referentiel_referentiel->seuil_certificat;
		
		// REGENERER LES CERTIFICATS
		// MODIF JF 2009/10/23
		// referentiel_regenere_certificats($referentiel_instance); // INUTILE DESORMAIS
		// boite pour selectionner les utilisateurs ?
		if ($isteacher || $iseditor || $istutor){
			if (!empty($select_acc)){
			  // eleves accompagnes
                $record_id_users  = referentiel_get_accompagnements_teacher($referentiel_instance->id, $course->id, $USER->id);
            }
			else{
			  // tous les users possibles (pour la boite de selection)
				// Get your userids the normal way
                $record_id_users  = referentiel_get_students_course($course->id,0,0);  //seulement les stagiaires
			}

			if ($gusers && $record_id_users){ // liste des utilisateurs du groupe courant
				// echo "<br />DEBUG :: print_lib_activite.php :: 740 :: GUSERS<br />\n";
				// print_object($gusers);
				// echo "<br />\n";
				$record_users  = array_intersect($gusers, array_keys($record_id_users));
				// $record_users  = array_intersect_assoc($record_id_users, array_keys($gusers));
				// echo "<br />DEBUG :: print_lib_activite.php :: 745 :: RECORD_USERS<br />\n";
				// print_r($record_users  );
				// echo "<br />\n";
				// recopier 
				$record_id_users=array();
				foreach ($record_users  as $record_id){
					$record_id_users[]->userid=$record_id;
				}
			}
			echo referentiel_select_users_accompagnes("certificat.php", $mode, $userid_filtre, $select_acc);
			echo referentiel_select_users_certificat($record_id_users, "certificat.php", $mode,  $userid_filtre, $select_acc);
		}
		else{
			$userid_filtre=$USER->id; // les étudiants ne peuvent voir que leur fiche
		}

		// recuperer les utilisateurs filtres
		// si $userid_filtre ==0 on retourne tous les utilisateurs du cours et du groupe
        if (!empty($userid_filtre)){
            $record_id_users = referentiel_get_students_course($course->id, $userid_filtre, 0);
        }
		else{
            if (!empty($select_acc)){
                // eleves accompagnes
                $record_id_users  = referentiel_get_accompagnements_teacher($referentiel_instance->id, $course->id, $USER->id);
            }
            else{
                $record_id_users = referentiel_get_students_course($course->id, $userid_filtre, 0);
		  }
    }
			
    // groupes ?
		if ($gusers && $record_id_users){
				$record_users  = array_intersect($gusers, array_keys($record_id_users));
				// recopier 
				$record_id_users=array();
				foreach ($record_users  as $record_id){
					$record_id_users[]->userid=$record_id;
				}
		}

		if ($record_id_users){
			// Afficher 		
			if (isset($mode) && ($mode=='listcertifsingle')){
				;
			}
			else{
				echo referentiel_print_entete_certificat();
			}
			
		  foreach ($record_id_users  as $record_id) {   // afficher la liste d'users
				// recupere les enregistrements de certificats ou les cree si necessaire
				$record=referentiel_certificat_user($record_id->userid, $referentiel_instance->ref_referentiel);
				// if (($record) && ($record->evaluation>0)){
				if ($record){ // MODIF JF 2010/10/07
					$isauthor = referentiel_certificat_isowner($record->id);
					if ($isauthor  || $istutor || $isteacher || $iseditor) {
						if (isset($mode) && ($mode=='listcertifsingle')){
							referentiel_print_certificat_detail($record);
							referentiel_menu_certificat_detail($context, $record->id, $referentiel_instance->id, $record->verrou, $record_id->userid, $select_acc);
						}
						else{
							referentiel_print_certificat($record, $seuil_certification);
							echo referentiel_menu_certificat($context, $record->id, $referentiel_instance->id, $record->verrou, $record_id->userid, $select_acc);
						}
					}
                }
			}
		}
		// Afficher 		
		if (isset($mode) && ($mode=='listcertifsingle')){
			// prints ratings options
      // referentiel_print_ratings($referentiel, $record);
			// prints ratings options
			// referentiel_print_comments($referentiel, $record);
		}
		else{
			echo referentiel_print_enqueue_certificat();
		}
		echo '<br /><br />'."\n";
	}
	}
}


/************************************************************************
 * takes a list of records, the current referentiel, a search string,   *
 * and mode to display                                                  *
 * input @param array $records   of certificat                            *
 *       @param object $referentiel                                     *
 *       @param string $search                                          *
 *       @param string $page                                            *
 * output null                                                          *
 ************************************************************************/
function referentiel_print_un_certificat_detail($certificat_id, $referentiel_instance, $userid=0, $select_acc=0) {
global $CFG;
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
            print_error('REFERENTIEL_ERROR 5 :: print_lib_certificat.php :: You cannot call this script in that way');
        }

        $context = get_context_instance(CONTEXT_MODULE, $cm->id);
    
        $referentiel_id = $referentiel_instance->ref_referentiel;
	
        $isteacher = has_capability('mod/referentiel:rate', $context);
        $iseditor = has_capability('mod/referentiel:managecertif', $context);
	
        if (isset($referentiel_id) && ($referentiel_id>0)){
            $referentiel_referentiel=referentiel_get_referentiel_referentiel($referentiel_id);
            if (!$referentiel_referentiel){
                if ($iseditor){
                    print_error(get_string('creer_referentiel','referentiel'), "$CFG->wwwroot/mod/referentiel/edit.php?d=$referentiel_instance->id&mode=editreferentiel&sesskey=".sesskey());
                }
    			else {
	       			print_error(get_string('creer_referentiel','referentiel'), "$CFG->wwwroot/course/view.php?id=$course->id&sesskey=".sesskey());
		      	}
		    }

    		$record = referentiel_get_certificat($certificat_id);
	       	if (!$record){
		      	print_error(get_string('nocertificat','referentiel'), "activite.php?d=".$referentiel_instance->id."&mode=addactivity&sesskey=".sesskey());
            }
    		// Afficher
    		$isauthor = referentiel_certificat_isowner($record->id);
    		if ($isauthor || $isteacher || $iseditor) {
    			referentiel_print_certificat_detail_une_page($record);
    			referentiel_menu_certificat_detail($context, $record->id, $referentiel_instance->id, $record->verrou, $userid, $select_acc);
            }
    	}
	}
}


/************************************************************************
 * takes a list of records, the current referentiel, a search string,   *
 * and mode to display                                                  *
 * input @param array $records   of certificat                            *
 *       @param object $referentiel                                     *
 *       @param string $search                                          *
 *       @param int $select_acc                                            *
 * output null                                                          *
 ************************************************************************/
function referentiel_print_graph_certificats($referentiel_instance, $referentiel_referentiel, $gusers, $currentgroup=0) {
global $CFG;
global $USER;
static $isteacher=false;
static $istutor=false;
static $isauthor=false;
static $iseditor=false;

global $DB;
    if (!empty($referentiel_instance)){
        $cm = get_coursemodule_from_instance('referentiel', $referentiel_instance->id);
        $course = $DB->get_record("course", array("id" => "$cm->course"));
        if (empty($cm) or empty($course)){
            print_error('REFERENTIEL_ERROR 5 :: print_lib_certificat.php :: You cannot call this script in that way');
        }
	}

    $context = get_context_instance(CONTEXT_MODULE, $cm->id);

	$referentiel_id = $referentiel_instance->ref_referentiel;

	$iseditor = has_capability('mod/referentiel:managecertif', $context);
	$isteacher = has_capability('mod/referentiel:approve', $context)&& !$iseditor;
	$istutor = has_capability('mod/referentiel:comment', $context) && !$iseditor  && !$isteacher;
	$isauthor = has_capability('mod/referentiel:write', $context) && !$iseditor  && !$isteacher  && !$istutor;

    // codes item
	$liste_codes=referentiel_purge_dernier_separateur($referentiel_referentiel->liste_codes_competence, '/');
	// empreintes
	$liste_empreintes=referentiel_purge_dernier_separateur($referentiel_referentiel->liste_empreintes_competence, '/');
    // poids
    $liste_poids=referentiel_purge_dernier_separateur($referentiel_referentiel->liste_poids_competence, '/');

    // cours ?
    if (!empty($course)){
                    $course_name=$course->shortname;
            }
    else{
                    $course_name="";
            }

    // groupes ?
    if (!empty($currentgroup)){
                    $group_name=groups_get_group_name($currentgroup);
    }
    else{
                    $group_name="";
    }

    $titre= get_string('certification_etat', 'referentiel');
    if (!empty($course_name)){
                    $titre.=' ('.$course_name;
                    if (!empty($group_name)){
                        $titre.= ' - '.$group_name;
                    }
                    $titre.= ')';
    }
    $titre=str_replace(' ','_',$titre);
    
    // utilisateurs
    $record_id_users  = referentiel_get_students_course($course->id,0,0);  //seulement les stagiaires

    // groupes ?
	if ($gusers && $record_id_users){
        $record_users  = array_intersect($gusers, array_keys($record_id_users));
        // recopier
        $record_id_users=array();
        foreach ($record_users  as $record_id){
            $record_id_users[]->userid=$record_id;
        }
	}


    if ($record_id_users){
        // Afficher
        foreach ($record_id_users  as $record_id) {   // afficher la liste d'users
				// recupere les enregistrements de certificats ou les cree si necessaire
				$records_c[]=referentiel_certificat_user($record_id->userid, $referentiel_id);
        }
        if ($records_c){
            // Let's get the datas
            $separateur1='/';
            $separateur2=':';
            $separateur3='|';
            
            $t_user     = array(); // tableau des userid
            $t_code     = explode($separateur1, $liste_codes);
            $t_empreinte= explode($separateur1, $liste_empreintes);
            $t_poids    = explode($separateur1, $liste_poids);

            $moyennes   = array();
            $t_valides   = array(array());
            // $t_declarees = array(array());
            $user_name_souligne="";

            foreach($records_c as $record_c) { // pour tous les certificats
                if ($record_c){
                    $certificat_id=$record_c->id;
                	$commentaire_certificat = stripslashes($record_c->commentaire_certificat);
                    $synthese_certificat = stripslashes($record_c->synthese_certificat);
                    $competences_certificat = $record_c->competences_certificat;
                    $competences_activites = $record_c->competences_activite;
                    $decision_jury = stripslashes($record_c->decision_jury);
                    $date_decision = $record_c->date_decision;
                    $ref_referentiel = $record_c->ref_referentiel;
                    $user_id = $record_c->userid;
                    $teacherid = $record_c->teacherid;
                    $verrou = $record_c->verrou;
                    $valide = $record_c->valide;
                    $evaluation = $record_c->evaluation;

                    $user_name=referentiel_get_user_nom($user_id).' '.referentiel_get_user_prenom($user_id);
                    $user_info=referentiel_get_user_info($user_id);
                    $teacher_info=referentiel_get_user_info($teacherid);

                    // dates
                    $date_decision_info=userdate($date_decision);

                    //$t_user[]=$user_name;
                    $t_user[]=$user_id;
                    
                    // competences validees dans le certificat
	                $tc=array();
	                $lcc=referentiel_purge_dernier_separateur($competences_certificat, $separateur1);
                    if (!empty($lcc)){
                        $tc = explode ($separateur1, $lcc);
                        // A.1.1:1 A.1.2:1 A.1.3:1 A.1.4:0/A.1.5:0/A.2.1:1/A.2.2:0/A.2.3:0/A.3.1:0/A.3.2:0/A.3.3:0/A.3.4:0/B.1.1:0/B.1.2:0/B.1.3:0/B.2.1:1/B.2.2:1/B.2.3:0/B.2.4:0/B.3.1:0/B.3.2:0/B.3.3:0/B.3.4:0/B.3.5:0/B.4.1:0/B.4.2:0/B.4.3:0/
		                $i=0;
                        while ($i<count($tc)){
                                // CODE1:N1
                                // DEBUG
                                // echo "<br />".$tc[$i]." <br />\n";
                                if ($tc[$i]!=''){
                                    $tcc=explode($separateur2, $tc[$i]);
                                    // A.1.1 1
                                    if (isset($tcc[1])){
                                        // $t_valides[$userid][]=$tcc[1];
                                        //$t_valides[$user_name][]=min($tcc[1], $t_empreinte[$i]) ;
                                        $t_valides[$user_id][]=min($tcc[1], $t_empreinte[$i]) ;
                                    }
                                }
                                $i++;
                        }
                    }
                    /*
                        // competences declarees dans les activites
                        $tc=array();
                        $lcd=referentiel_purge_dernier_separateur($competences_activites, $separateur1);
                        if (!empty($lcd)){
                            $tc = explode ($separateur1, $lcd);
                            // A.1.1:1 A.1.2:1 A.1.3:1 A.1.4:0/A.1.5:0/A.2.1:1/A.2.2:0/A.2.3:0/A.3.1:0/A.3.2:0/A.3.3:0/A.3.4:0/B.1.1:0/B.1.2:0/B.1.3:0/B.2.1:1/B.2.2:1/B.2.3:0/B.2.4:0/B.3.1:0/B.3.2:0/B.3.3:0/B.3.4:0/B.3.5:0/B.4.1:0/B.4.2:0/B.4.3:0/
                            $i=0;
                            while ($i<count($tc)){
                                // CODE1:N1
                                // DEBUG
                                // echo "<br />".$tc[$i]." <br />\n";
                                // exit;
                                if ($tc[$i]!=''){
                                    $tcc=explode($separateur2, $tc[$i]);
                                    // A.1.1 1
                                    if (isset($tcc[1])){
					                   $t_declarees[$user_name][]=$tcc[1];
                    				}
			                    }
                                $i++;
                            }
                        }
                    */
                }
            }

            // calculer la moyenne
            $n=0;
            for ($i=0; $i<count($t_user); $i++){
                //$valeurs=$t_valides[$t_user[$i]];
                // moyenne
                $n++;
                $moyennes=referentiel_somme_valeur($moyennes, $t_valides[$t_user[$i]]);
            }
            $moyennes=referentiel_quotient_valeur($moyennes, $n);
            $lmoyennes=implode("/", $moyennes);

            // Distribuer les data sur plusieurs images
            // pagination
            $page0=0;
            $page1=0;
            $nbuser=count($t_user);
            $page=0;
            $pagemax=min($nbuser,MAXLIGNEGRAPH);
            $npages= (int) ($nbuser / $pagemax);
            $reste = (int) ($nbuser % $pagemax);
            if ($reste) $npages++;

    /*
            // DEBUG
            echo "<br>DEBUG :: 1858 :: CODE<br>\n";
            print_r($t_code);
            echo "<br> EMPREINTES<br>\n";
            print_r($t_empreinte);
            echo "<br> POIDS<br>\n";
            print_r($t_poids);

            echo "<br>DEBUG :: 1858 :: USERS<br>\n";
            print_r($t_user);
            echo "<br>COMPETENCES VALIDES<br>\n";
            for ($i=0; $i<$nbuser; $i++){
                echo "<br>User:".$t_user[$i]." <br>\n";
                print_r($t_valides[$t_user[$i]]);
            }
    */
            // echo "<br>NBUSER: $nbuser  NBPAGES: $npages  PAGEMAX: $pagemax\n";

            for ($page=0; $page<$npages; $page++){
                // preparer les donnees
                $t_data=array(array());
                $j=0;
                for ($j=0; $j<$pagemax; $j++){
                    if (isset($t_user[$page*$pagemax+$j])){
                        $user_name=referentiel_get_user_nom($t_user[$page*$pagemax+$j]).' '.referentiel_get_user_prenom($t_user[$page*$pagemax+$j]);
                        $t_data[$user_name]=$t_valides[$t_user[$page*$pagemax+$j]];
                    }
                }
                // DEBUG
                //echo "<br>DEBUG :: 1876 :: DATA<br>\n";
                //print_r($t_data);
                //echo "<br>DEBUG :: 1885 :: DATA<br>\n";
                $ltdata="";
                foreach($t_data as $key=>$data){
                    if ($data){
                        //echo "<br>$key<br>\n";
                        //print_r($data);
                        $ldata=implode("/", $data);
                        $ltdata.="$key:$ldata|";
                    }
                }


                // Afficher
                //echo '<br />DATA -&gt;'.$ltdata."\n";

                $num_page=$page+1;
                if ($isteacher || $istutor || $iseditor){
                    $affichage_complet=1;
                }
                else{
                    $affichage_complet=0;
                }
                //echo "<br>AFFCOMPLET : $affichage_complet\n";
                //redirect ($CFG->wwwroot.'/mod/referentiel/graph_certificats.php?d='.$referentiel_instance->id.'&ltdata='.$ltdata.'&lmoyennes='.$lmoyennes.'&lcode='.$liste_codes.'&lempreinte='.$liste_empreintes.'&lpoids='.$liste_poids.'&affcomplet='.$affichage_complet.'&titre='.$titre.'&page='.$num_page.'&npages='.$npages);

                echo '<center><img src="'.$CFG->wwwroot.'/mod/referentiel/graph_certificats.php?d='.$referentiel_instance->id.'&ltdata='.$ltdata.'&lmoyennes='.$lmoyennes.'&lcode='.$liste_codes.'&lempreinte='.$liste_empreintes.'&lpoids='.$liste_poids.'&affcomplet='.$affichage_complet.'&titre='.$titre.'&page='.$num_page.'&npages='.$npages.'" border="0" title="'.get_string('statcertif', 'referentiel').'"></center>'."\n";
		        echo '<br />'."\n";
            }
        }
    }
}

//-------------
function referentiel_somme_valeur($t_sommes, $t_valeurs){
// ajoute le contenu du tableau t_valeurs à t_sommes
    for ($i=0; $i<count($t_valeurs); $i++){
        if (isset($t_sommes[$i])){
            $t_sommes[$i]=$t_sommes[$i]+$t_valeurs[$i];
        }
        else{
            $t_sommes[$i]=$t_valeurs[$i];
        }
    }
    return $t_sommes;
}

//-------------
function referentiel_quotient_valeur($t_sommes, $quotient){
// divise le contenu du tableau t_sommes par quotient
    if ($quotient){
        for ($i=0; $i<count($t_sommes); $i++){
            $t_sommes[$i]=$t_sommes[$i]=(float) $t_sommes[$i] / (float) $quotient;
        }
    }
    return $t_sommes;
}




?>