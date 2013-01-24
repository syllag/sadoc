<?php // $Id:  lib_etab.php,v 1.0 2008/04/29 00:00:00 jfruitet Exp $
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
 * @version $Id: lib.php,v 1.4 2006/08/28 16:41:20 mark-nielsen Exp $
 * @version $Id: lib.php,v 1.0 2008/04/29 00:00:00 jfruitet Exp $
 * @package referentiel
 **/

 // ////////////////////////// ETUDIANT /////////////////////


 // ---------------------------------------------
function referentiel_add_etudiant_user($userid){
// retourne l'id cree
global $DB;
	$record=new object();
	$record->ddn_etudiant = (get_string('inconnu', 'referentiel'));
	$record->lieu_naissance = (get_string('inconnu', 'referentiel'));
	$record->departement_naissance = (get_string('inconnu', 'referentiel'));
	$record->adresse_etudiant = (get_string('inconnu', 'referentiel'));
	$record->ref_etablissement = referentiel_get_min_etablissement();
	
    if ($userid>0){
        $record->userid=$userid;
        $user=$DB->get_record("user", array("id" => "$userid"));
        if ($user){
            if (!empty($user->idnumber)){
                $record->num_etudiant = $user->idnumber;
            }
            else{
                $record->num_etudiant = $user->username;
            }
        }
        else{
            $record->userid=0;
            // $record->num_etudiant = (get_string('inconnu', 'referentiel'));
            $record->num_etudiant = '';
        }
    }
    
	// DEBUG
	// echo "<br>DEBUG :: lib_etab.php :: 145\n";
	// print_r($record);
	if ($record->userid){
	   return ($DB->insert_record("referentiel_etudiant", $record));
    }
    else{
        return 0;
    }
}

 // ---------------------------------------------
function referentiel_etudiant_isowner($id){
global $DB;
global $USER;
	if (!empty($id)){
		$record=$DB->get_record("referentiel_etudiant", array("userid" => "$id"));
		// DEBUG
		// echo "<br >USERID : $USER->id ; OWNER : $record->userid\n";
		return ($USER->id == $record->userid);
	}
	else 
		return false; 
} 

function referentiel_get_etudiant_id_by_userid($userid){
global $DB;
	if (!empty($userid)){
		$record=$DB->get_record("referentiel_etudiant", array("userid" => "$userid"));
		if ($record){
			return ($record->id);
		}
	}
	return 0;
}


/**
 * This function returns record from table referentiel_etudiant
 *
 * @param id
 * @return object
 * @todo Finish documenting this function
 **/
function referentiel_get_etudiant_user($userid){
global $DB;
 if (!empty($userid)){
		return $DB->get_record_sql("SELECT * FROM {referentiel_etudiant} WHERE userid=:userid", array("userid" => "$userid"));
	}
	else 
		return 0; 
}

/**
 * This function returns record from table referentiel_etudiant
 *
 * @param id
 * @return object
 * @todo Finish documenting this function
 **/
function referentiel_get_etudiant($id){
global $CFG;
	if (!empty($id)){
		return $DB->get_record_sql("SELECT * FROM {referentiel_etudiant} WHERE id=:id" , array("id" => "$id"));
	}
	else 
		return 0; 
}

/**
 * This function returns record from table referentiel_etudiant
 *
 * @param id
 * @return objects
 * @todo Finish documenting this function
 **/
function referentiel_get_etudiants($params, $search=""){
global $DB;
	return $DB->get_records_sql("SELECT * FROM {referentiel_etudiant} WHERE $search ", $params);
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
function referentiel_add_etudiant($form) {
// creation certificat
global $DB;
global $USER;
$id=0;
	$record=new object();
	$record->num_etudiant = $form->num_etudiant;
	$record->ddn_etudiant = $form->ddn_etudiant ;
	$record->lieu_naissance = ($form->lieu_naissance);
	$record->departement_naissance = ($form->departement_naissance);
	$record->adresse_etudiant = ($form->adresse_etudiant);
	if ($form->ref_etablissement){
		$record->ref_etablissement = $form->ref_etablissement;
	}
	else{
		$record->ref_etablissement = referentiel_get_min_etablissement();
	}
	$record->userid = $form->userid;
	
	// controle
	if (
    ($record->userid>0) 
     && 
    (($record->num_etudiant=='') || ($record->num_etudiant==get_string('inconnu', 'referentiel')))
    )
    {
      $user=$DB->get_record('user', array("id", "$record->userid"));
      if ($user){
        if (!empty($user->idnumber)){
          $record->num_etudiant = $user->idnumber;
        }
        else{
          $record->num_etudiant = $user->username;      
        }
    }
  }
  
	$id=$DB->insert_record("referentiel_etudiant", $record);
  return $id;
}


/**
 * Given an object containing all the necessary referentiel, 
 * (defined by the form in mod.html) this function 
 * will create a new instance and return the id number 
 * of the new instance.
 *
 * @param object $instance An object from the form in eturdiant.html
 * @return int The id of the newly inserted referentiel record
 **/
function referentiel_update_etudiant($form) {
// MAJ etudiant
global $DB;
$ok=true;
    // DEBUG
    // echo "DEBUG : UPDATE ETUDIANT CALLED";
	// print_object($form);
    // echo "<br />";
	// certificat
if (isset($form->action) && ($form->action=="modifier_etudiant")){
		$record=new object();
		$record->id = $form->etudiant_id;
		$record->num_etudiant = $form->num_etudiant;
		$record->ddn_etudiant = ($form->ddn_etudiant) ;
		$record->lieu_naissance = ($form->lieu_naissance);
		$record->departement_naissance = ($form->departement_naissance);
		$record->adresse_etudiant = ($form->adresse_etudiant);
		$record->ref_etablissement = $form->ref_etablissement;
		$record->userid = $form->userid;
	// controle	
	if (
    ($record->userid>0) 
     && 
    (($record->num_etudiant=='') || ($record->num_etudiant==get_string('inconnu', 'referentiel')))
    )
    {
      $user=$DB->get_record('user', array("id" => "$record->userid"));
      if ($user){
        if (!empty($user->idnumber)){
          $record->num_etudiant = $user->idnumber;
        }
        else{
          $record->num_etudiant = $user->username;      
        }
      }
    }
		
	if(!$DB->update_record("referentiel_etudiant", $record)){
		// echo "<br /> ERREUR UPDATE ETUDIANT\n";
		$ok=false;
	}
	else {
		// echo "<br /> UPDATE ETUDIANT $record->id\n";
		$ok=true;
	}
	return $ok;
}
}


function referentiel_etudiant_set_etablissement($userid, $etablissement_id){
// mise a jour de l'etablisssement
global $DB;
	if ($userid && $etablissement_id){
		$record=referentiel_get_etudiant_user($userid);
		$record->lieu_naissance = ($record->lieu_naissance);
		$record->departement_naissance = ($record->departement_naissance);
		$record->adresse_etudiant = ($record->adresse_etudiant);
		$record->ref_etablissement = $etablissement_id;
		if ($DB->update_record("referentiel_etudiant", $record)){
			return true;
		} 
	}
	return false;	
}


/**
 * Given an etudiant id,
 * this function will permanently delete the etudiant
 *
 * @param object $id
 * @return boolean Success/Failure
 **/

function referentiel_delete_etudiant($id) {
// suppression certificat
global $DB;
$ok_delete=false;
	if (!empty($id)){
		if ($etudiant = $DB->get_record("referentiel_etudiant", array("id" => "$id"))) {
			// suppression 
			$ok_delete = $DB->delete_records("referentiel_etudiant", array("id" => "$id"));
		}
	}
    return $ok_delete;
}

function referentiel_delete_etudiant_user($userid) {
// suppression etudiant
global $DB;
$ok_delete=false;	
	if (!empty($userid)){
		if ($etudiant = $DB->get_record("referentiel_etudiant", array("userid" => "$userid"))) {
			// suppression 
			$ok_delete = $DB->delete_records("referentiel_etudiant", array("id" => "$etudiant->id"));
		}
	}
    return $ok_delete;
}

// ///////////// ETABLISSEMENT ///////////////////
/**
 * This function returns records from table referentiel_etablissement
 *
 * @param ref
 * @return record
 * @todo Finish documenting this function
 **/
function referentiel_get_etablissements(){
global $DB;
	return $DB->get_records("referentiel_etablissement", NULL);
}


function referentiel_add_etablissement($form){
global $DB;
// creer un etablissement
$id=0;
	if (isset($form->action) && ($form->action=="creer_etablissement")){
		$record=new object();
		$record->num_etablissement = ($form->num_etablissement);
		$record->nom_etablissement = ($form->nom_etablissement);
		$record->adresse_etablissement = ($form->adresse_etablissement);
	    $record->logo_etablissement = ' ';
		$id=$DB->insert_record("referentiel_etablissement", $record);
	}
	return $id;
}

function referentiel_update_etablissement($form){
global $DB;

	$ok=false;	
	// DEBUG
	//print_object($form);
	//echo "<br /> 344 ";

// MAJ etablissement
$ok=true;
	if (isset($form->action) && ($form->action=="modifier_etablissement")){
		$record=new object();
		$record->id = $form->etablissement_id;
		$record->num_etablissement = ($form->num_etablissement);
		$record->nom_etablissement = ($form->nom_etablissement);
		$record->adresse_etablissement = ($form->adresse_etablissement);
		if (isset($form->logo_etablissement)){
            $record->logo_etablissement = ($form->logo_etablissement);
        }
        else{

        }
        
		if(!$DB->update_record("referentiel_etablissement", $record)){
			// echo "<br /> ERREUR UPDATE etablissement\n";
			$ok=false;
		}
		else {
			$ok=true;
		}
		return $ok; 
	}
}

function referentiel_select_etablissement($userid, $etablissement_id, $appli){
$s='';
	$records=referentiel_get_etablissements();
	if ($records){
		$s.="\n".'<form action="'.$appli.'" method="get" id="selectetab'.$userid.'" class="popupform">'."\n";
		$s.='<div><select id="selectetab'.$userid.'_jump" name="jump" size="1" 
onchange="self.location=document.getElementById(\'selectetab'.$userid.'\').jump.options[document.getElementById(\'selectetab'.$userid.'\').jump.selectedIndex].value;">'."\n";
		foreach ($records as $record){
			if ($etablissement_id==$record->id){
				$s.='	<option value="'.$appli.'&userid='.$userid.'&etablissement_id='.$record->id.'&sesskey='.sesskey().'" selected="selected" >'.$record->nom_etablissement.'</option>'."\n";
			}
			else{
				$s.='	<option value="'.$appli.'&userid='.$userid.'&etablissement_id='.$record->id.'&sesskey='.sesskey().'">'.$record->nom_etablissement.'</option>'."\n";
			}
		}
		$s.='</select></div>'."\n";
		$s.='
<script type="text/javascript">
//<![CDATA[
document.getElementById("noscriptnavmenupopup").style.display = "none";
//]]>
</script>'."\n";
		$s.='</form>'."\n";
	}
	return $s;
}



function referentiel_get_nom_etablissement($id){
global $DB;
	if (!empty($id)){
		$record = $DB->get_record("referentiel_etablissement",  array("id", "$id"));
		if ($record ){
			return $record->nom_etablissement;
		}
	}
	return "";
}

function referentiel_delete_etablissement($id){
global $DB;
// suppression etablissement
	if (!empty($id)){
		// supprimer les enregistrements dependants
		$etudiants=referentiel_get_etudiants(array("ref_etablissement" => "$id"),"ref_etablissement=:ref_etablissement ");
		if ($etudiants) {
			foreach ($etudiants as $etudiant){
				referentiel_etudiant_set_etablissement($etudiant->userid, 0);
			}
		}
		return $DB->delete_records("referentiel_etablissement", array("id", "$id"));
	}
    return false;
}

function referentiel_get_etablissement($id){
global $DB;
	if (!empty($id)){
		return $DB->get_record("referentiel_etablissement", array("id" => "$id"));
	}
	else 
		return 0; 
}


/**
 * This function returns an referentiel_etablissement id
 *
 * @param NULL
 * @return record
 * @todo Finish documenting this function
 **/
function referentiel_genere_etablissement(){
global $DB;
	$record=new object();
	$record->num_etablissement = get_string('inconnu', 'referentiel');
	$record->nom_etablissement = get_string('inconnu', 'referentiel');
	$record->adresse_etablissement = get_string('inconnu', 'referentiel');
	$record->logo_etablissement = '';
	return $DB->insert_record("referentiel_etablissement", $record);
}

/**
 * This function returns an referentiel_etablissement id
 *
 * @param NULL
 * @return id
 * @todo Finish documenting this function
 **/
function referentiel_get_min_etablissement(){
global $DB;
    $sql="SELECT MIN(id) as minid FROM {referentiel_etablissement}";
    $r=$DB->get_record_sql($sql, NULL);
    // DEBUG
    // echo "<br>DEBUG :: lib_etab.php :: 460\n";
    // print_object($r);
    // exit;
	if (empty($r) || empty($r->minid)){
        $id_etab=referentiel_genere_etablissement();
	}
	else{
		$id_etab=$r->minid;
	}

	return $id_etab;
}

function referentiel_get_id_etablissement($num_etablissement){
global $DB;
	if ($num_etablissement){
		$record = $DB->get_record("referentiel_etablissement", array("num_etablissement" => $num_etablissement));
		if ($record ){
			return $record->id;
		}
	}
	return 0;
}

?>