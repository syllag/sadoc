<?php  // $Id:  lib_certificat.php,v 1.0 2009/10/16 00:00:00 jfruitet Exp $
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
 * @version $Id: lib_certificat.php,v 1.0 2009/10/16 00:00:00 jfruitet Exp $
 * @package referentiel v 4.0 2009/04/29 00:00:00 
 **/

// CERTIFICATS

// ----------------------------------------------------
function referentiel_affiche_competences_certificat($separateur1, $separateur2, $liste, $liste_empreintes, $invalide=true){
// Affiche les codes competences en tenant compte de l'empreinte
// si detail = true les compétences non validees sont aussi affichees
	$t_empreinte=explode($separateur1, $liste_empreintes);

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

					if (isset($tcc[1]) && ($tcc[1]>=$t_empreinte[$i])){
						$s.=' <span class="valide"><span class="small"><b>'.$tcc[0].'</b></span></span>';
					}
					elseif ($invalide==true){
						$s.=' <span class="invalide"><span class="small"><i>'.$tcc[0].'</i></span></span>';
					}
				}
				$i++;
			}
		}
	return $s;
}


/**
 * This function returns number of certificat from table referentiel_certificat
 * which have been locked
 * @param objects
 * @param int verrou
 * @return int  number of locked
 * @todo Finish documenting this function
 **/
function referentiel_verrouiller_certificats($certificats, $verrou){
// modification du champ verrou
// retourne le nombre de champs traités
global $DB;
    $n=0;
    if ($certificats) {
        foreach ($certificats as $certificat) {
            $n++;
            $DB->set_field("referentiel_certificat", "verrou", $verrou, array("id" => "$certificat->id"));
        }
    }
    return $n;
}

/**
 * This function returns records of certificat from table referentiel_certificat
 *
 * @param id reference referentiel (no instance)
 * @param list_userids : a list of ids
 * @return array of objects
 * @todo Finish documenting this function
 **/
function referentiel_get_certificats_from_userslist($list_userids, $refrefid, $ordonner=true){
global $DB;
global $debug_special;
    $records=array();
    $trecords=array(array());
    $tuserids=array();
    $select='';
    $maxselect=50;    // pour éviter une requete enorme
    $nb=0;
    $nbtotal=0;
	if (!empty($refrefid) && !empty($list_userids)){
        $tuserids=explode(',',$list_userids);
/*
        if (isset($debug_special) && $debug_special){
            echo "<br>DEBUG :: lib_certificat.php :: 77 <br>\n";
            echo count($tuserids)."<br />\n";
            print_r($tuserids);
            echo "<br>\n";
            // exit;
        }
*/
        $params = array("refid" => "$refrefid");
        foreach ($tuserids as $userid) {   // creer la requete
            if ($userid){
                if (!empty($select)){
                    $select.= ' OR userid='.$userid.' ';
                }
                else{
                    $select.= ' (userid='.$userid.' ';
                }
                $nb++;
            }
            if ($nb>=$maxselect){
                $nbtotal+=$nb;
                if (!empty($select)){
                    $select.= ' )';
                    $sql="SELECT c.*, u.fistname, u.lastname
                    FROM {referentiel_certificat} as c, {user} as u
                    WHERE ref_referentiel=:refid
                    AND c.userid=u.id
                    AND $select
                    ORDER BY u.lastname, u.firstname ";
                    // DEBUG
                    // echo "<br>lib_certificat.php :: 67 :: <br>SQL&gt;$sql<br>\n";
                    // exit;
                    $trecords[]=$DB->get_records_sql($sql, $params);

                }
                $nb=0;
                $select='';
            }
        }
        // derniers enregistrements
        $nbtotal+=$nb;
        if (!empty($select)){
                    $select.= ' )';
                    $sql="SELECT c.*, u.fistname, u.lastname
                    FROM {referentiel_certificat} as c,
                    FROM {referentiel_certificat} as c, {user} as u
                    WHERE ref_referentiel=:refid
                    AND c.userid=u.id
                    AND $select
                    ORDER BY u.lastname, u.firstname ";
                    // DEBUG
                    // echo "<br>lib_certificat.php :: 126 :: <br>SQL&gt;$sql<br>\n";
                    // exit;
                    $trecords[]=$DB->get_records_sql($sql, $params);
        }
/*
        // DEBUG
        if (isset($debug_special) && $debug_special){
            echo "<br>lib_certificat.php :: 133 :: <br>Nombre de certificats sélectionnés : $nbtotal<br>\n";
        }
*/
        if ($trecords){
            foreach($trecords as $trecord){
                foreach($trecord as $record){
                    $records[]=$record;
                }
            }
/*            if (isset($debug_special) && $debug_special){
                echo "<br>Nombre de certificats obtenus : ".count($records)."\n";
            }
*/
            // trier les certificats dans l'ordre alphabetique
            if ($ordonner){
                //
                uasort($records, 'referentiel_sort_user');
/*
                if (isset($debug_special) && $debug_special){
                    echo "<br>APRES TRI : Nombre de certificats obtenus : ".count($records)."<br> OBJETS <br>\n";
                    print_object($records);
                    //exit;
                }
*/
            }

            return ($records);
        }
    }
	return NULL;
}


/**
 * This function returns record certificate from table referentiel_certificat
 *
 * @param userid reference user id
 * @param referentiel_id reference referentiel
 * @return object
 * @todo Finish documenting this function
 **/
function referentiel_certificat_user_select($userid, $referentiel_id, $sql_filtre_where='', $sql_filtre_order=''){
// Si certificat n'existe pas, cree le certificat et le retourne
// si les conditions sont remplies
global $DB;
if (!empty($userid) && !empty($referentiel_id)){
    $params = array("refid" => "$referentiel_id", "userid" => "$userid");
    if (empty($sql_filtre_where)){
        $sql_filtre_where=" WHERE ref_referentiel=:refid AND userid=:userid ";
    }
    else{
        $sql_filtre_where=" WHERE ref_referentiel=:refid AND userid:userid $sql_filtre_where";
    }
    if (!empty($sql_filtre_order)){
        $sql_filtre_order=" ORDER BY $sql_filtre_order ";
    }

    // DEBUG
    // echo "DEBUG :: lib_certificat.php :: Ligne 44<br>WHERE : $sql_filtre_where<br>ORDER : $sql_filtre_order\n";
    $sql="SELECT * FROM {referentiel_certificat} $sql_filtre_where  $sql_filtre_order";

    if (!referentiel_certificat_user_exists($userid, $referentiel_id)){
        if (referentiel_genere_certificat($userid, $referentiel_id)){
            return($DB->get_record_sql($sql, $params));
        }
 		else{
            return false;
		}
	}
	else{
        return($DB->get_record_sql($sql, $params));
	}
}
else {
    return false;
}
}


/**
 * This function returns records of certificat from table referentiel_certificat
 *
 * If select_all == 1 all certificates are displayed
 * Else only course's user certificates are displayed
 * @param object referentiel_instance instance of referentiel
 * @param user_filtee int
 * @param guser Object
 * @param mode string
 * @param appli : calling script
 * @param select_all : int
 * @param select_acc : int
 * @param force_generation: boolean
 * @return objects
 * @todo Finish documenting this function
 **/
function referentiel_get_liste_certificats($referentiel_instance, $course, $context,
        $list_userids='', $userid_filtre=0, $gusers, $select_acc=0, $select_all=0,
        $force_generation=0) {
global $debug_special;
global $USER;
static $isteacher=false;
static $isauthor=false;
static $iseditor=false;
static $referentiel_id = NULL;

    $records=array();

    if ($referentiel_instance){
        $isadmin = has_capability('mod/referentiel:exportcertif', $context);
        $iseditor = has_capability('mod/referentiel:managecertif', $context);
        $isteacher = has_capability('mod/referentiel:approve', $context)&& !$iseditor;
        $istutor = has_capability('mod/referentiel:comment', $context) && !$iseditor  && !$isteacher;
        $isauthor = has_capability('mod/referentiel:write', $context) && !$iseditor  && !$isteacher  && !$istutor;


        // recuperer les certificats
        if (!empty($list_userids)){  // liste d'utilisateurs obtenus par les pedagogies
            if (!empty($force_generation)){
                // echo "<br>DEBUG :: lib_certificat.php :: 226 <br />$list_userids\n";
                referentiel_force_certificats_from_userslist($list_userids, $referentiel_instance->ref_referentiel);
            }
            // resultat trié dans l'ordre alphabetique des utilisateurs
            $records=referentiel_get_certificats_from_userslist($list_userids, $referentiel_instance->ref_referentiel);
        }
        else if ($isadmin && $select_all){  //
            //tous les certificats
            // $record_id_users  = referentiel_get_all_users_with_certificate($referentiel_instance->ref_referentiel);
            $records = $records = referentiel_get_certificats_users($referentiel_instance->ref_referentiel);
        }
        else {  // recuperer les utilisateurs filtres
            if (!empty($userid_filtre)){      // un seul certificat
                $record_id_users = referentiel_get_students_course($course->id, $userid_filtre, 0);
            }
            else { // teachers    :: les certificats du cours seulement
                if (!empty($select_acc)){
                    // eleves accompagnes
                    $record_id_users  = referentiel_get_accompagnements_teacher($referentiel_instance->id, $course->id, $USER->id);
                }
                else{
                    $record_id_users = referentiel_get_students_course($course->id, $userid_filtre, 0);
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
            }
            if ($record_id_users){
                foreach ($record_id_users  as $record_id) {   // afficher la liste d'users
                    $records[]=referentiel_certificat_user($record_id->userid, $referentiel_instance->ref_referentiel);
                }
            }
        }

    } //  if referentiel
    return $records;

}


// ----------------------
function referentiel_select_all_certificates($referentiel_instance, $appli, $mode, $userid=0, $select_acc=0, $select_all=0, $existe_pedagos=0){
// selection tous certificats ?
global $cm;
global $course;
    //echo "<br>DEBUG :: lib_certificat.php :: 174 :: APPLI : $appli :: MODE : $mode :: SELECT_ACC : $select_acc :: SELECT_ALL : $select_all\n";

$s="";
	$s.='<div align="center">'."\n";
	$s.='<table align="center" class="selection">'."\n";
	$s.='<tr><td>';
	// accompagnement
	$s.="\n".'<form name="form" method="post" action="'.$appli.'&action=select_all_certificates">'."\n";
    $s.=get_string('exportallcertificates', 'referentiel')."<br />\n";
    if (empty($select_all)){
		$s.='<input type="radio" name="select_all" value="0" checked="checked" />'.get_string('no')."\n";
        if ($existe_pedagos){
            $s.='<input type="radio" name="select_all" value="1" />'.get_string('yes')."\n";
            $s.='<input type="radio" name="select_all" value="2" />'.get_string('exportpedagos', 'referentiel')."\n";
        }
        else{
            $s.='<input type="radio" name="select_all" value="1" checked="checked" />'.get_string('yes')."\n";
        }
	}
	else{
		$s.='<input type="radio" name="select_all" value="0" />'.get_string('no')."\n";
        if ($existe_pedagos){
            if ($select_all==2){
                $s.='<input type="radio" name="select_all" value="1" />'.get_string('yes')."\n";
                $s.='<input type="radio" name="select_all" value="2" checked="checked" />'.get_string('exportpedagos', 'referentiel')."\n";
            }
            else{
                $s.='<input type="radio" name="select_all" value="1" checked="checked" />'.get_string('yes')."\n";
                $s.='<input type="radio" name="select_all" value="2" />'.get_string('exportpedagos', 'referentiel')."\n";
            }
        }
        else{
            $s.='<input type="radio" name="select_all" value="1" checked="checked" />'.get_string('yes')."\n";
        }
    }
    $s.='</td><td><input type="submit" value="'.get_string('go').'" />'."\n";;
	$s.='

<!-- accompagnement -->
<input type="hidden" name="select_acc" value="'.$select_acc.'" />
<!-- These hidden variables are always the same -->
<input type="hidden" name="course"        value="'.$course->id.'" />
<input type="hidden" name="sesskey"     value="'.sesskey().'" />
<input type="hidden" name="mode"          value="'.$mode.'" />
</form>'."\n";
	$s.='</td>';
	$s.='</tr></table>'."\n";

	$s.='</div>'."\n";

	return $s;
}

// ----------------------
function referentiel_select_users_accompagnes($appli='certificat.php', $mode='listcertif', $userid=0, $select_acc=0, $select_all=0){

global $cm;
global $course;
$s="";

	$s.='<div align="center">'."\n"; 
	$s.='<table class="selection">'."\n";
	$s.='<tr><td>';		
	// accompagnement
	// $s.="\n".'<form name="form" method="post" action="activite.php?id='.$cm->id.'&action=select_acc">'."\n";
	$s.="\n".'<form name="form" method="post" action="'.$appli.'?id='.$cm->id.'&action=select_acc">'."\n"; 		

	$s.=get_string('select_acc', 'referentiel');
  if (empty($select_acc)){
      $s.=' <input type="radio" name="select_acc" value="1" />'.get_string('yes')."\n";
		  $s.='<input type="radio" name="select_acc" value="0" checked="checked" />'.get_string('no')."\n";
	}
	else{
      $s.=' <input type="radio" name="select_acc" value="1" checked="checked" />'.get_string('yes')."\n";
		  $s.='<input type="radio" name="select_acc" value="0" />'.get_string('no')."\n";
  }
  $s.='</td><td><input type="submit" value="'.get_string('go').'" />'."\n";;
	$s.='
<!-- tous les certificats -->
<input type="hidden" name="select_all" value="'.$select_all.'" />	
<!-- These hidden variables are always the same -->
<input type="hidden" name="course"        value="'.$course->id.'" />
<input type="hidden" name="sesskey"     value="'.sesskey().'" />
<input type="hidden" name="mode"          value="'.$mode.'" />
</form>'."\n";
	$s.='</td>';
	$s.='</tr></table>'."\n";
	$s.='</div>'."\n";
			
	return $s;
}


// ----------------------
function referentiel_select_users_certificat($record_users, $appli='certificat.php', $mode='listcertif', $userid=0, $select_acc=0, $select_all=0){

global $cm;
global $course;
$maxcol=MAXBOITESSELECTION;
$s="";
$t_users=array();

	if ($record_users){
    $s.='<div align="center">'."\n"; 		
		$s.='<table class="selection">'."\n";
		$s.='<tr>';
		
		// $s.='<option value="0" selected="selected">'.get_string('choisir', 'referentiel').'</option>'."\n";		
	  foreach ($record_users as $record_u) {   // liste d'id users
			// 
			$t_users[]= array('id' => $record_u->userid, 'lastname' => referentiel_get_user_nom($record_u->userid), 'firstname' => referentiel_get_user_prenom($record_u->userid));
			$t_users_id[]= $record_u->userid;
			$t_users_lastname[] = referentiel_get_user_nom($record_u->userid);
			$t_users_firstname[] = referentiel_get_user_prenom($record_u->userid);
		}
		array_multisort($t_users_lastname, SORT_ASC, $t_users_firstname, SORT_ASC, $t_users);
		// 
		// echo "<br />Debug :: print_lib_activite.php :: 1419 ::<br />\n";
		// print_r($t_users);
		
		// exit;
		$n=count($t_users);
    if ($n>=18){
			$l=$maxcol;
			$c=(int) ($n / $l);
		}
    elseif ($n>=6){
			$l=$maxcol-2;
			$c=(int) ($n / $l);    
    }
		else{
			$l=1;
			$c=(int) ($n);		
		}
		
		if ($c*$l==$n){
      $reste=false;
    }
    else{
      $reste=true;
    }
		$i=0;
		
		for ($j=0; $j<$l; $j++){
			$s.='<td>';
			$s.="\n".'<form name="form" method="post" action="'.$appli.'?id='.$cm->id.'&select_acc='.$select_acc.'&action=selectuser">'."\n";
			$s.='<select name="userid" id="userid" size="4">'."\n";
			
      if ($j<$l-1){
        if (($userid=='') || ($userid==0)){
	   			$s.='<option value="0" selected="selected">'.get_string('choisir', 'referentiel').'</option>'."\n";
		  	}
			  else{
				  $s.='<option value="0">'.get_string('choisir', 'referentiel').'</option>'."\n";
			  }
			}
			else{
			   if ($reste){
            if (($userid=='') || ($userid==0)){
	   	     		$s.='<option value="0" selected="selected">'.get_string('choisir', 'referentiel').'</option>'."\n";
		  	    }
			      else{
				      $s.='<option value="0">'.get_string('choisir', 'referentiel').'</option>'."\n";
			      }
         }
         else{
    	   		if (($userid=='') || ($userid==0)){
		  	     	$s.='<option value="0" selected="selected">'.get_string('tous', 'referentiel').'</option>'."\n";
			      }
			      else{
				      $s.='<option value="0">'.get_string('tous', 'referentiel').'</option>'."\n";
			      }         
         }
			}
			
			for ($k=0; $k<$c; $k++){
				if ($userid==$t_users[$i]['id']){
					$s.='<option value="'.$t_users[$i]['id'].'" selected="selected">'.$t_users[$i]['lastname'].' '.$t_users[$i]['firstname'].'</option>'."\n";
				}
				else{
					$s.='<option value="'.$t_users[$i]['id'].'">'.$t_users[$i]['lastname'].' '.$t_users[$i]['firstname'].'</option>'."\n";
				}
				$i++; 
			}
			$s.='</select>'."\n";
			$s.='<br /><input type="submit" value="'.get_string('select', 'referentiel').'" />'."\n";;
			$s.='
<!-- tous les certificats -->
<input type="hidden" name="select_all" value="'.$select_all.'" />				
<!-- accompagnement -->
<input type="hidden" name="select_acc" value="'.$select_acc.'" />
<!-- These hidden variables are always the same -->
<input type="hidden" name="course"        value="'.$course->id.'" />
<input type="hidden" name="sesskey"     value="'.sesskey().'" />
<input type="hidden" name="mode"          value="'.$mode.'" />
</form>'."\n";
			$s.='</td>';
		}
		
		if ($i<$n){
			$s.='<td>';
			$s.='<form name="form" method="post" action="'.$appli.'?id='.$cm->id.'&action=selectuser">'."\n";	
			$s.='<select name="userid" id="userid" size="4">'."\n";
			if (($userid=='') || ($userid==0)){
				$s.='<option value="0" selected="selected">'.get_string('tous', 'referentiel').'</option>'."\n";
			}
			else{
				$s.='<option value="0">'.get_string('tous', 'referentiel').'</option>'."\n";
			}

			while ($i <$n){
				
				if ($userid==$t_users[$i]['id']){
					$s.='<option value="'.$t_users[$i]['id'].'" selected="selected">'.$t_users[$i]['lastname'].' '.$t_users[$i]['firstname'].'</option>'."\n";
				}
				else{
					$s.='<option value="'.$t_users[$i]['id'].'">'.$t_users[$i]['lastname'].' '.$t_users[$i]['firstname'].'</option>'."\n";
				}
				$i++;
			}
			$s.='</select>'."\n";
			$s.='<br /><input type="submit" value="'.get_string('select', 'referentiel').'" />'."\n";;
			$s.='
<!-- tous les certificats -->
<input type="hidden" name="select_all" value="'.$select_all.'" />			
<!-- accompagnement -->
<input type="hidden" name="select_acc" value="'.$select_acc.'" />
<!-- These hidden variables are always the same -->
<input type="hidden" name="select_acc" value="'.$select_acc.'" />
<input type="hidden" name="course"        value="'.$course->id.'" />
<input type="hidden" name="sesskey"     value="'.sesskey().'" />
<input type="hidden" name="mode"          value="'.$mode.'" />
</form>'."\n";
			$s.='</td>';
		}
		$s.='</tr></table>'."\n";
		$s.='</div>'."\n";
	}
	
	return $s;
}

/**
 * This function select users
 *
 * If select_all == 1 all pedagogies certificates are displayed
 * Else only course's user certificates are displayed
 * @param object referentiel_instance instance of referentiel
 * @param list_userids ;: a list of user from pedagogie
 * @param user_filtre int
 * @param guser Object
 * @param mode string
 * @param appli : calling script
 * @param select_all : int
 * @param select_acc : int
 * @param affiche : boolean
 * @return objects
 * @todo Finish documenting this function
 **/

function referentiel_select_liste_certificats($referentiel_instance, $list_pedagoids='',
    $userid_filtre=0, $gusers, $select_acc=0, $mode='listcertif', $appli='certificat.php',
    $select_all=0, $sql_filtre_where='', $data_param=NULL) {
global $USER;
global $existe_pedagos;   /// renseigne au niveau de export_certificat

static $isteacher=false;
static $isauthor=false;
static $iseditor=false;
static $referentiel_id = NULL;
global $DB;
    // DEBUG
    // echo "<br>DEBUG :: lib_certificat.php :: 502 :: APPLI : $appli :: MODE : $mode :: LISTE_PEDAGO : $list_pedagoids :: SELECT_ACC : $select_acc :: SELECT_ALL : $select_all\n";

    $records=array();

    // contexte
    $cm = get_coursemodule_from_instance('referentiel', $referentiel_instance->id);
    $course = $DB->get_record('course', array("id" => "$cm->course"));
    if (empty($cm) or empty($course)){
        print_error('REFERENTIEL_ERROR :: lib_certificat.php :: 438 :: You cannot call this script in that way');
    }

    $context = get_context_instance(CONTEXT_MODULE, $cm->id);

    $referentiel_id = $referentiel_instance->ref_referentiel;

    $isadmin = has_capability('mod/referentiel:exportcertif', $context);
    $iseditor = has_capability('mod/referentiel:managecertif', $context);
    $isteacher = has_capability('mod/referentiel:approve', $context)&& !$iseditor;
    $istutor = has_capability('mod/referentiel:comment', $context) && !$iseditor  && !$isteacher;
    $isauthor = has_capability('mod/referentiel:write', $context) && !$iseditor  && !$isteacher  && !$istutor;

    if (isset($referentiel_id) && ($referentiel_id>0)){
        $referentiel_referentiel=referentiel_get_referentiel_referentiel($referentiel_id);
        if (!$referentiel_referentiel){
            if ($iseditor){
                print_error(get_string('creer_referentiel','referentiel'), "edit.php?d=$referentiel_instance->id&mode=editreferentiel&sesskey=".sesskey());
            }
            else {
                print_error(get_string('creer_referentiel','referentiel'), "../../course/view.php?id=$course->id&sesskey=".sesskey());
            }
        }
        // Selectionner les utilisateurs pour les boîtes de selection

        if ($isadmin  || $isteacher || $iseditor || $istutor){
            if (!empty($select_acc)){
                // eleves accompagnes
                $record_id_users  = referentiel_get_accompagnements_teacher($referentiel_instance->id, $course->id, $USER->id);
            }
            else{
                // tous les users possibles (pour la boite de selection)
                // Get your userids the normal way
                $record_id_users  = referentiel_get_students_course($course->id,0,0);  //seulement les stagiaires
            }

            // tenir compte des groupes pour les boites de selection
            if ( ($isadmin && !$select_all) || $isteacher || $iseditor || $istutor){
                // groupe ?
                if ($gusers && $record_id_users){ // liste des utilisateurs du groupe courant
                    $record_users  = array_intersect($gusers, array_keys($record_id_users));
                    // recopier
                    $record_id_users=array();
                    foreach ($record_users  as $record_id){
                        $record_id_users[]->userid=$record_id;
                    }
                }
            }

            if ($isadmin){ // admin peut tout voir
                // PEDAGOGIES
                // DEBUG
                // variable globale
                // $existe_pedagos=referentiel_pedagogies_exists($referentiel_instance->ref_referentiel);

                // DEBUG
                //if ($existe_pedagos) echo "<br>DEBUG :: lib_certificat.php :: 493 :: EXISTE PEDAGOS :: LISTE_PEDAGO : $list_pedagoids :: SELECT_ACC : $select_acc :: SELECT_ALL : $select_all\n";
                //else  echo "<br>DEBUG :: lib_certificat.php :: 493 :: EXISTE PAS PEDAGOS :: LISTE_PEDAGO : $list_pedagoids :: SELECT_ACC : $select_acc :: SELECT_ALL : $select_all\n";

                echo referentiel_select_all_certificates($referentiel_instance, $appli, $mode, $userid_filtre, $select_acc, $select_all, $existe_pedagos);

                if (($select_all!=2) || (!$existe_pedagos)){
                    echo referentiel_select_users_accompagnes($appli, $mode, $userid_filtre, $select_acc, $select_all);
                    echo referentiel_select_users_certificat($record_id_users, $appli, $mode,  $userid_filtre, $select_acc, $select_all);
                }
                else if ($select_all==2) {
                    echo referentiel_select_pedagogies($referentiel_instance, $appli, $mode, $list_pedagoids, $select_acc, $select_all, $sql_filtre_where, $data_param);
                }
            }
            else{
                echo referentiel_select_users_accompagnes($appli, $mode, $userid_filtre, $select_acc, $select_all);
                echo referentiel_select_users_certificat($record_id_users, $appli, $mode,  $userid_filtre, $select_acc, $select_all);
            }
        }
        else{
            $userid_filtre=$USER->id; // les étudiants ne peuvent voir que leur fiche
        } // affiche
    } //  if referentiel

}

/**
 * This function returns records of certificat from table referentiel_certificat
 *
 * @param id reference referentiel (no instance)
 * @param list_userids : a list of ids
 * @return array of objects
 * @todo Finish documenting this function
 **/
function referentiel_force_certificats_from_userslist($list_userids, $refrefid){

global $debug_special;
    $tuserids=array();
    $nb=0;
    $nbforce=0;
	if (!empty($refrefid) && !empty($list_userids)){
        $tuserids=explode(',',$list_userids);
        if (isset($debug_special) && $debug_special){
            echo "<br>DEBUG :: lib_certificat.php :: referentiel_force_certificats_from_userslist()<br>Nombre d'utilisateurs traités :\n";
            $nu=count($tuserids)-1;
            echo $nu."\n";
            // exit;
        }
        foreach ($tuserids as $userid) {   // forcer la generation
            if ($userid){
                if (!referentiel_user_certificat_exists($userid, $refrefid)){
                    referentiel_genere_certificat($userid, $refrefid);
                    $nbforce++;
                }
                $nb++;
            }
        }

        // DEBUG
        if (isset($debug_special) && $debug_special){
            echo "<br>Nombre de certificats traités : $nb<br>Nombre de certificats créés : $nbforce\n";
        }
    }
}


/**
 * This function returns true if a record referentiel_certificat exists for userid
 *
 * @param userid reference user id
 * @param referentiel_id reference referentiel_referentiel id
 * @return boolean
 * @todo Finish documenting this function
 **/
function referentiel_user_certificat_exists($userid, $referentiel_id){
global $DB;
	if (!empty($userid) && !empty($referentiel_id)){
		$r=$DB->get_record('referentiel_certificat', array("ref_referentiel" => $referentiel_id, "userid" => $userid));
		// print_object($r);
		if ($r){
			return ($r->id);
		}
	}
	return 0;
}


/**
 * This function returns records of users ids
 *
 * If select_all == 1 all certificates ussers are displayed
 * Else only course's users are displayed
 * @param object referentiel_instance instance of referentiel
 * @param user_filtre int
 * @param guser Object
 * @param mode string
 * @param select_all : int
 * @param select_acc : int
 * @param affiche : boolean
 * @return objects
 * @todo Finish documenting this function
 **/
function referentiel_get_liste_users($referentiel_instance, $course, $context,
        $list_userids='', $userid_filtre=0, $gusers, $select_acc=0, $select_all=0) {
global $debug_special;

global $USER;
static $isteacher=false;
static $isauthor=false;
static $iseditor=false;
static $referentiel_id = NULL;

    $records=array();

    if ($referentiel_instance){
        $isadmin = has_capability('mod/referentiel:exportcertif', $context);

        // recuperer les certificats
        if (!empty($list_userids)){  // liste d'utilisateurs obtenus par les pedagogies
            $tuserids=explode(',',$list_userids);
            foreach ($tuserids as $userid) {   // forcer la generation
                if ($userid){
                    $records[]=$userid;
                }
            }
        }
        else if ($isadmin && $select_all){
            // tous les utilisateurs ayant un certificat
            $records = referentiel_get_certificats_id_users($referentiel_instance->ref_referentiel);
        }
        else {  // recuperer les utilisateurs filtres
            if (!empty($userid_filtre)){      // un seul certificat
                $records[]->userid=$userid_filtre;
            }
            else { // teachers    :: les certificats du cours seulement
                if (!empty($select_acc)){
                    // eleves accompagnes
                    $record_id_users  = referentiel_get_accompagnements_teacher($referentiel_instance->id, $course->id, $USER->id);
                }
                else{
                    $record_id_users = referentiel_get_students_course($course->id, $userid_filtre, 0);
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
                    foreach ($record_id_users  as $record_id) {   // afficher la liste d'users
                        $records[]=$record_id->userid;
                    }
                }
            }
        }
    } //  if referentiel
    return $records;

}

/**
 * This function returns records of certificat from table referentiel_certificat
 *  order by lastname, firstname of userid
 * @param id reference referentiel (no instance)
 * @return objects
 * @todo Finish documenting this function
 **/
function referentiel_get_certificats_id_users($refrefid){
global $DB;
	if (!empty($refrefid)){
        $sql = "SELECT c.userid
    FROM {referentiel_certificat} as c, {user} user as u
    WHERE c.ref_referentiel=:refid
    AND c.userid = u.id ORDER BY u.lastname, u.firstname ";
        return $DB->get_records_sql($sql, array("refid" => "$refrefid"));
    }
    return NULL;
}


/**
 * This function compare two object records a, b
 * @param object a->certificat : record, a->firstname : string, a->lastname : string
 * @param object b idem
 * @return int
**/
function referentiel_sort_user($a, $b){
    if ($a->lastname<$b->lastname){
        return -1;
    }
    elseif ($a->lastname>$b->lastname){
        return 1;
    }
    else{
        if ($a->firstname<$b->firstname){
            return -1;
        }
        elseif ($a->firstname>$b->firstname){
            return 1;
        }
        else{
            return 0;
        }
    }
}

?>