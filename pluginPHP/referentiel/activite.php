<?php  // $Id: activite.php,v 1.0 2008/05/03 00:00:00 jfruitet Exp $
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

require_once("../../config.php");
require_once("lib.php");
include('lib_etab.php');
include('print_lib_activite.php');	// AFFICHAGES
include('lib_task.php');
include('print_lib_task.php');	// AFFICHAGES TACHES
// include('print_lib_accompagnement.php');	// AFFICHAGES TACHES  require_once("lib_accompagnement.php");

  $id = optional_param('id', 0, PARAM_INT);    // course module id
  $d  = optional_param('d', 0, PARAM_INT); // Referentiel ID

  $mode  = optional_param('mode', '', PARAM_ALPHANUMEXT);    // Force the browse mode  ('single')

  $group = optional_param('group', -1, PARAM_INT);   // choose the current group
  $activite_id   = optional_param('activite_id', 0, PARAM_INT);    //record activite id
    // $import   = optional_param('import', 0, PARAM_INT);    // show import form

  $mailnow      = optional_param('mailnow', 0, PARAM_INT); // pour afficher les destinataires

  $action  	= optional_param('action','', PARAM_ALPHANUMEXT); // pour distinguer differentes formes de traitements
  $old_mode   = optional_param('old_mode','', PARAM_ALPHA); // mode anterieur
  $add        = optional_param('add','', PARAM_ALPHA);
  $update     = optional_param('update', 0, PARAM_INT);
  $delete     = optional_param('delete', 0, PARAM_INT);
  $approve    = optional_param('approve', 0, PARAM_INT);	
  $comment    = optional_param('comment', 0, PARAM_INT);		
  $course     = optional_param('course', 0, PARAM_INT);
  $groupmode  = optional_param('groupmode', -1, PARAM_INT);
  $cancel     = optional_param('cancel', 0, PARAM_BOOL);
  $userid     = optional_param('userid', 0, PARAM_INT);
	
  $mode_select       = optional_param('mode_select','', PARAM_ALPHANUMEXT);
  $filtre_validation = optional_param('filtre_validation', 0, PARAM_INT);
  $filtre_referent = optional_param('filtre_referent', 0, PARAM_INT);
  $filtre_date_modif = optional_param('filtre_date_modif', 0, PARAM_INT);
  $filtre_date_modif_student = optional_param('filtre_date_modif_student', 0, PARAM_INT);
  $filtre_auteur = optional_param('filtre_auteur', 0, PARAM_INT);
  $sql_filtre_where=optional_param('sql_filtre_where','', PARAM_ALPHA);
  $sql_filtre_order=optional_param('sql_filtre_order','', PARAM_ALPHA);
  $select_acc = optional_param('select_acc', 0, PARAM_INT);      // accompagnement

	// DEBUG
	//echo "<br>DEBUG :: 65 :: ACTIVITE.PHP :: MODE : $mode<br>USERID : $userid\n";


	$data_filtre= new Object(); // paramettres de filtrage
	if (isset($filtre_validation)){
			$data_filtre->filtre_validation=$filtre_validation;
	}
	else {
		$data_filtre->filtre_validation=0;
	}
	if (isset($filtre_referent)){
		$data_filtre->filtre_referent=$filtre_referent;
	}
	else{
		$data_filtre->filtre_referent=0;
	}
	if (isset($filtre_date_modif_student)){
		$data_filtre->filtre_date_modif_student=$filtre_date_modif_student;
	}
	else{
		$data_filtre->filtre_date_modif_student=0;
	}
	if (isset($filtre_date_modif)){
		$data_filtre->filtre_date_modif=$filtre_date_modif;
	}
	else{
		$data_filtre->filtre_date_modif=0;
	}
	if (isset($filtre_auteur)){
		$data_filtre->filtre_auteur=$filtre_auteur;
	}
	else{
		$data_filtre->filtre_auteur=0;
	}


    // nouveaute Moodle 1.9 et 2
    $url = new moodle_url('/mod/referentiel/activite.php');

	if ($d) {     // referentiel_referentiel_id
        if (! $referentiel = $DB->get_record("referentiel", array("id" => "$d"))) {
            print_error('Referentiel instance is incorrect');
        }
        if (! $referentiel_referentiel = $DB->get_record("referentiel_referentiel", array("id" => "$referentiel->ref_referentiel"))) {
            print_error('Réferentiel id is incorrect');
        }
        
		if (! $course = $DB->get_record("course", array("id" => "$referentiel->course"))) {
	            print_error('Course is misconfigured');
    	}
        	
		if (! $cm = get_coursemodule_from_instance('referentiel', $referentiel->id, $course->id)) {
    	        print_error('Course Module ID is incorrect');
		}
		$url->param('d', $d);
	} 
	elseif ($id) {
        if (! $cm = get_coursemodule_from_id('referentiel', $id)) {
        	print_error('Course Module ID was incorrect');
        }
        if (! $course = $DB->get_record("course", array("id" => "$cm->course"))) {
            print_error('Course is misconfigured');
        }
        if (! $referentiel = $DB->get_record("referentiel", array("id" => "$cm->instance"))) {
            print_error('Referentiel instance is incorrect');
        }
        if (! $referentiel_referentiel = $DB->get_record("referentiel_referentiel", array("id" => "$referentiel->ref_referentiel"))) {
            print_error('Referentiel is incorrect');
        }
        $url->param('id', $id);
    } 
	else{
    // print_error('You cannot call this script in that way');	
		print_error(get_string('erreurscript','referentiel','Erreur01 : activite.php'), 'referentiel');
	}
	

	// DEBUG
	//echo "<br>DEBUG :: 143 :: ACTIVITE.PHP :: MODE : $mode<br>USERID : $userid\n";


    $context = get_context_instance(CONTEXT_MODULE, $cm->id);


	if ($activite_id) { // id activite
        if (! $record = $DB->get_record("referentiel_activite", array("id" => "$activite_id"))) {
            print_error('LIGNE 151 :: Activite ID is incorrect');
        }
	}
	
    require_login($course->id, false, $cm);   // pas d'autologin guest

    if (!isloggedin() or isguestuser()) {
        redirect($CFG->wwwroot.'/mod/referentiel/view.php?id='.$cm->id.'&non_redirection=1');
    }

	/// If it's hidden then it's don't show anything.  :)
	/// Some capability checks.
    if (empty($cm->visible)
    && (
        !has_capability('moodle/course:viewhiddenactivities', $context)
            &&
        !has_capability('mod/referentiel:managecomments', $context)
        )

    ) {
        print_error(get_string("activityiscurrentlyhidden"),'error',"$CFG->wwwroot/course/view.php?id=$course->id");
    }


	
  if ($activite_id) {    // So do you have access?
      if (!(has_capability('mod/referentiel:write', $context) 
//			or referentiel_activite_isowner($activite_id)) or !confirm_sesskey() ) {
			or referentiel_activite_isowner($activite_id)) ) {            
          print_error(get_string('noaccess','referentiel'));
        }
    }
	
	
	
	// RECUPERER LES FORMULAIRES
  if (isset($SESSION->modform)) {   // Variables are stored in the session
        $form = $SESSION->modform;
        unset($SESSION->modform);
  } else {
      $form = (object)$_POST;
  }

        // DEBUG
        // echo "<br />DEBUG :: activite.php :: Ligne 195\n";
        // print_r($form);
        // exit;

    // selecteur
    $userid_filtre=0;
	if (isset($userid) && ($userid>0)){
		$userid_filtre=$userid;
	} 
	else if (isset($form->userid) && ($form->userid>0)){
		$userid_filtre=$form->userid;
	} 
		// DEBUG
		// echo "<br>$userid_filtre\n";
		// exit;

	/// selection filtre
    if (isset($mode_select) && ($mode_select=='selectetab') && confirm_sesskey() ){
		// gestion des filtres;
		$sql_filtre_where='';
		$sql_filtre_order='';
		
		if (isset($filtre_validation) && ($filtre_validation=='1')){
			if ($sql_filtre_where!='')
				$sql_filtre_where.=' AND approved=\'1\' ';
			else
				$sql_filtre_where.=' AND approved=\'1\' ';
		}
		else if (isset($filtre_validation) && ($filtre_validation=='-1')){
			if ($sql_filtre_where!='')
				$sql_filtre_where.=' AND approved=\'0\' ';
			else
				$sql_filtre_where.=' AND approved=\'0\' ';
		}
		if (isset($filtre_referent) && ($filtre_referent=='1')){
			if ($sql_filtre_where!='')
				$sql_filtre_where.=' AND teacherid<>0  ';
			else
				$sql_filtre_where.=' AND teacherid<>0  ';
		}
		else if (isset($filtre_referent) && ($filtre_referent=='-1')){
			if ($sql_filtre_where!='')
				$sql_filtre_where.=' AND teacherid=0  ';
			else
				$sql_filtre_where.=' AND teacherid=0  ';
		}
		
		if (isset($filtre_date_modif) && ($filtre_date_modif=='1')){
			if ($sql_filtre_order!='')
				$sql_filtre_order.=', date_modif ASC ';
			else
				$sql_filtre_order.=' date_modif ASC ';
		}
		else if (isset($filtre_date_modif) && ($filtre_date_modif=='-1')){
			if ($sql_filtre_order!='')
				$sql_filtre_order.=', date_modif DESC ';
			else
				$sql_filtre_order.=' date_modif DESC ';
		}
		
		if (isset($filtre_date_modif_student) && ($filtre_date_modif_student=='1')){
			if ($sql_filtre_order!='')
				$sql_filtre_order.=', date_modif_student ASC ';
			else
				$sql_filtre_order.=' date_modif_student ASC ';
		}
		else if (isset($filtre_date_modif_student) && ($filtre_date_modif_student=='-1')){
			if ($sql_filtre_order!='')
				$sql_filtre_order.=', date_modif_student DESC ';
			else
				$sql_filtre_order.=' date_modif_student DESC ';
		}		
		
		if (isset($filtre_auteur) && ($filtre_auteur=='1')){
			if ($sql_filtre_order!='')
				$sql_filtre_order.=', userid ASC ';
			else
				$sql_filtre_order.=' userid ASC ';
		}
		else if (isset($filtre_auteur) && ($filtre_auteur=='-1')){
			if ($sql_filtre_order!='')
				$sql_filtre_order.=', userid DESC ';
			else
				$sql_filtre_order.=' userid DESC ';
		}


		// echo "<br />DEBUG :: activite.php :: Ligne 162 :: FILTRES : $sql_filtre_where $sql_filtre_order\n";
		
  }

	/// selection d'utilisateurs
	
  // accompagnement
	if (!isset($select_acc)){
        if (isset($form->select_acc)){
            $select_acc=$form->select_acc;
        }
        else{
            $select_acc=(referentiel_has_pupils($referentiel->id, $course->id, $USER->id)>0);
        }
        // DEBUG
        // echo "<br />DEBUG :: activite.php :: 280 :: ACCOMPAGNEMENT : $select_acc<br />\n";
    }
	if ($cancel) {
	      // DEBUG
        // echo "<br />DEBUG :: activite.php :: Ligne 285 CANCEL : $cancel SELECT_ACC : $select_acc\n";
        // print_r($form);
        // exit;
        if (isset($form->select_acc)){
          $select_acc=$form->select_acc;
        }
	      
		$mode ='listactivityall';
		if (has_capability('mod/referentiel:managecertif', $context)){
	           $SESSION->returnpage = "$CFG->wwwroot/mod/referentiel/activite.php?d=$referentiel->id&select_acc=$select_acc&userid=0&mode=$mode&filtre_validation=$data_filtre->filtre_validation&filtre_referent=$data_filtre->filtre_referent&filtre_date_modif=$data_filtre->filtre_date_modif&filtre_date_modif_student=$data_filtre->filtre_date_modif_student";
		}
		else{
	           $SESSION->returnpage = "$CFG->wwwroot/mod/referentiel/activite.php?d=$referentiel->id&select_acc=$select_acc&userid=$userid&mode=$mode";				
		}
    	if (!empty($SESSION->returnpage)) {
            $return = $SESSION->returnpage;
	        unset($SESSION->returnpage);
    	    redirect($return);
        } 
		else {
	       redirect("$CFG->wwwroot/mod/referentiel/activite.php?d=$referentiel->id&select_acc=$select_acc&userid=$userid&mode=$mode");
    	}
		
       exit;
    }
  
	// selection utilisateurs accompagnés  
    if (isset($action) && ($action=='select_acc')){
		  if (isset($form->select_acc) && confirm_sesskey() ){
		  	$select_acc=$form->select_acc;
		  }
		  if (isset($form->mode) && ($form->mode!='')){
			 $mode=$form->mode;
		  }
		  // echo "<br />ACTION : $action  SEARCH : $userid_filtre\n";
		  unset($form);
		  unset($action);
		  // exit;
    }
    
    // utilisateur
    if (isset($action) && ($action=='selectuser')){
		  if (isset($form->userid) && ($form->userid>0)
			&& confirm_sesskey() ){
		  	$userid_filtre=$form->userid;
			 // DEBUG
		  }
		  if (isset($form->select_acc)){
		  	$select_acc=$form->select_acc;
		  }

		  if (isset($form->mode) && ($form->mode!='')){
			 $mode=$form->mode;
		  }
		  // echo "<br />ACTION : $action  SEARCH : $userid_filtre\n";
		  unset($form);
		  unset($action);
		  // exit;
    }

    if (isset($action) && ($action=='selectaccompagnement')
      && isset($form->mode) && ($form->mode=='accompagnement')
			&& confirm_sesskey() )
    {

        // accompagnement
        if (isset($form->select_acc)){
		  	$select_acc=$form->select_acc;
        }
			
        if (!empty($form->teachers_list)){
            $teachersids=explode(',',$form->teachers_list);
        }
        if (!empty($form->users_list)){
            $usersids=explode(',',$form->users_list);
        }
        if (isset($form->mode) && ($form->mode!='')){
            $mode=$form->mode;
        }
        foreach($teachersids as $tid){
            // RAZ
            foreach ($usersids as $uid){
                $ok=false;
                $i=0;
                if (!empty($form->t_teachers[$tid])){
                    while (!$ok && ($i<count($form->t_teachers[$tid]))){
                        if ($form->t_teachers[$tid][$i]==$uid){
                            $ok=true;
                            referentiel_set_association_user_teacher($referentiel->id, $course->id, $uid, $tid, $form->type);
                        }
                        $i++;
                    }
                    if (!$ok){
                        referentiel_delete_association_user_teacher($referentiel->id, $course->id, $uid, $tid);
                    }
                }
                else{
                    referentiel_delete_association_user_teacher($referentiel->id, $course->id, $uid, $tid);
                }
            }
        }
          
        unset($form);
        unset($action);
        // exit;
    }

 	
	/// Delete any requested records
    if (isset($delete) && ($delete>0 )
		&& confirm_sesskey() 
		&& (has_capability('mod/referentiel:write', $context) or referentiel_activite_isowner($delete))) {
		
        // echo "<br>DEBUG :: activite.php :: 414\n";
        // print_r($form);

        if ($confirm = optional_param('confirm',0,PARAM_INT)) {
            // suppression
			if (referentiel_delete_activity_record($delete)){
				add_to_log($course->id, 'referentiel', 'record delete', "activite.php?d=$referentiel->id", $delete, $cm->id);
                // notify(get_string('recorddeleted','referentiel'), 'notifysuccess');
            }
        }
        redirect("$CFG->wwwroot/mod/referentiel/activite.php?d=$referentiel->id&select_acc=$select_acc&mode=$mode");
        exit;
    }
	
	/// Approve any requested records
    if (isset($approve) && ($approve>0) && confirm_sesskey() 
		&& has_capability('mod/referentiel:approve', $context)) {
        if ($approverecord = $DB->get_record("referentiel_activite", array("id" => "$approve"))) {
	        $confirm = optional_param('confirm',0,PARAM_INT);
			if ($confirm) {
                $approverecord->approved = 1;
			}
			else{
				$approverecord->approved = 0;
			}
			$approverecord->teacherid=$USER->id;
			$approverecord->date_modif=time();
			$approverecord->type_activite=addslashes($approverecord->type_activite);			
			$approverecord->description_activite=addslashes($approverecord->description_activite);
			$approverecord->commentaire_activite=addslashes($approverecord->commentaire_activite);
			
			// DEBUG
			// print_r($approverecord);
			// echo "<br />\n";
			
            if ($DB->update_record("referentiel_activite", $approverecord)) {
				if (($approverecord->userid>0) && ($approverecord->competences_activite!='')){
					// mise a jour du certificat 
					if ($approverecord->approved){
                        // schema referentiel_mise_a_jour_competences_certificat_user($liste_competences_moins, $liste_competences_plus, $userid, $referentiel_id, $approved, $modif_declaration=true, $modif_validation=false);
						referentiel_mise_a_jour_competences_certificat_user('', $approverecord->competences_activite, $approverecord->userid, $approverecord->ref_referentiel,$approverecord->approved, false, true);
					}
					else{
						referentiel_mise_a_jour_competences_certificat_user($approverecord->competences_activite, '', $approverecord->userid, $approverecord->ref_referentiel,$approverecord->approved, false, true);
					}
				}
            }
			if (isset($userid) && ($userid>0)){
				$userid_filtre=$userid;
			} 

             // MODIF 19/11/2010
			if (isset($old_mode) && ($old_mode!='')){
                $mode=$old_mode;
            }
        }
    }
	
	/// Comment any requested records
    if (isset($comment) && ($comment>0) && confirm_sesskey()
		&& has_capability('mod/referentiel:comment', $context)) 
    {
        if (isset($form) && isset($form->activite_id) && ($form->activite_id>0)){
            // accompagnement
            if (isset($form->select_acc)){
                $select_acc=$form->select_acc;
            }

			if ($approverecord = $DB->get_record("referentiel_activite", array("id" => "$comment"))) {
				$approverecord->teacherid=$USER->id;
				$approverecord->date_modif=time();
				$approverecord->type_activite=addslashes($approverecord->type_activite);
				$approverecord->description_activite=addslashes($approverecord->description_activite);
				$approverecord->commentaire_activite=addslashes($form->commentaire_activite);
				if (isset($form->approved)) {
					$approverecord->approved=$form->approved;
				}
				if (isset($form->userid) && ($form->userid>0)){
					$userid_filtre=$form->userid;
				} 
                // MODIF JF 2010/02/11
                if (isset($form->mailnow)){
                    $approverecord->mailnow=$form->mailnow;
                    if ($form->mailnow=='1'){ // renvoyer
                        $approverecord->mailed=0;   // annuler envoi precedent
                    }
                }
                else{
                    $approverecord->mailnow=0;
                }
				
				// DEBUG
				// print_r($approverecord);
				// echo "<br />\n";
				
                if ($DB->update_record('referentiel_activite', $approverecord)) {
					if (($approverecord->userid>0) && ($approverecord->competences_activite!='')){
						// mise a jour du certificat 
						if ($approverecord->approved){
							referentiel_mise_a_jour_competences_certificat_user('', $approverecord->competences_activite, $approverecord->userid, $approverecord->ref_referentiel,$approverecord->approved, false, true);
						}
						else{
							referentiel_mise_a_jour_competences_certificat_user($approverecord->competences_activite, '', $approverecord->userid, $approverecord->ref_referentiel,$approverecord->approved, false, true);
						}
					}
				}
			}
			unset($form);
			
			// MODIF 19/11/2010
			if (isset($old_mode) && ($old_mode!='')){
                $mode=$old_mode;
            }
    }
  }
	
	
  // if (!empty($course) and confirm_sesskey()) {    // add, delete or update form submitted
	
    if (!empty($referentiel) && !empty($course) && isset($form)) {
    	/// modification globale
  		  		  
        if (isset($_POST['action']) && ($_POST['action']=='modifier_activite_global')){
		    // echo "<br />DEBUG :: activite.php :: 274 :: ACTION : $action \n";
		    $form=$_POST;
		  
            // accompagnement
            if (isset($form['select_acc'])){
		    	$select_acc=$form['select_acc'];
		    }

		    if (isset($form['tactivite_id']) && ($form['tactivite_id'])){
                //
                foreach ($form['tactivite_id'] as $id_activite){
                    // echo "<br />ID :: ".$id_activite."\n";
                    //
                    $form2= new Object();
                    $form2->action='modifier_activite';
                    $form2->activite_id=$form['activite_id_'.$id_activite];
        		    $form2->type_activite=$form['type_activite_'.$id_activite];
		            $form2->old_liste_competences=$form['old_liste_competences_'.$id_activite];
             
                    if (isset($form['code_item_'.$id_activite]) && is_array($form['code_item_'.$id_activite]) ){
                        $form2->competences_activite=reference_conversion_code_2_liste_competence('/', $form['code_item_'.$id_activite]);
                    }
                    else if (isset($form['competences_activite_'.$id_activite])){
                        $form2->competences_activite=$form['competences_activite_'.$id_activite];
                    }
                    else{
                        $form2->competences_activite='';
                    }
		      
                    $form2->description_activite=($form['description_activite_'.$id_activite]);
                    $form2->commentaire_activite=($form['commentaire_activite_'.$id_activite]);
                    $form2->instance=$form['ref_instance_'.$id_activite];
                    $form2->ref_referentiel=$form['ref_referentiel_'.$id_activite];
                    $form2->course=$form['ref_course_'.$id_activite];
                    $form2->date_creation=$form['date_creation_'.$id_activite];
                    $form2->date_modif_student=$form['date_modif_student_'.$id_activite];
                    $form2->date_modif=$form['date_modif_'.$id_activite];
		        
                    if (!empty($form['approved_'.$id_activite]))  {
                        $form2->approved=$form['approved_'.$id_activite];
                    }
                    else {
                        $form2->approved=0;
                    }
                    $form2->userid=$form['userid_'.$id_activite];
                    $form2->teacherid=$form['teacherid_'.$id_activite];
                    $form2->mailnow=$form['mailnow_'.$id_activite];
            
                    // print_object($form2);
                    // echo "<br />\n";
                    $return = referentiel_update_activity($form2);
                    if (!$return) {
                        print_error("Could not update activity $form->activite_id of the referentiel", "activite.php?d=$referentiel->id");
                    }
                    if (is_string($return)) {
                        print_error($return, "activite.php?d=$referentiel->id");
                    }
                    add_to_log($course->id, "referentiel", "update", "mise a jour activite $form2->activite_id", "$form2->instance", "");
                }
            }
            unset($form);
            redirect("$CFG->wwwroot/mod/referentiel/activite.php?d=$referentiel->id&select_acc=$select_acc&mode=$mode");
            exit;
        }
   
        elseif (!empty($form->mode)){
            // add, delete or update form submitted

            // Afficher la liste des destinataires ?
            // MODIF JF 2011/11/29
            if (!empty($form->mailnow)){
                $mailnow=1;
            }

            // accompagnement
            if (isset($form->select_acc)){
		    	$select_acc=$form->select_acc;
            }

            $addfunction    = "referentiel_add_activity";
            $updatefunction = "referentiel_update_activity";
            $deletefunction = "referentiel_delete_activity";

            switch ($form->mode) {
    		  
                case "updateactivity":
                    if (isset($form->name)) {
                        if (trim($form->name) == '') {
       		        	  unset($form->name);
                        }
                    }

                    // echo "<br>DEBUG :: activite.php :: 617\n";
                    // print_r($form);

                    
                    if (isset($form->delete) && ($form->delete==get_string('delete'))){
                        // echo "<br>DEBUG :: activite.php :: 621\n";
                        // print_r($form);

                        // suppression
                        // echo "<br />SUPPRESSION\n";
                        $return = $deletefunction($form);
                        if (!$return) {
							/*
            	        	if (file_exists($moderr)) {
                	        	$form = $form;
	                   		    include_once($moderr);
    	                   		die;
	    	               	}
							*/
    	         	      	print_error("Could not update activity $form->activite_id of the referentiel", "activite.php?d=$referentiel->id");
                        }
                        if (is_string($return)) {
                            print_error($return, 'error', "activite.php?d=$referentiel->id&userid=$form->userid");
                        }
                        add_to_log($course->id, "referentiel", "delete",
            	          "mise a jour activite $form->activite_id",
                          "$form->instance", "");
                    }
                    else {

                        $return = $updatefunction($form);
                        if (!$return) {
						  /*
            		    if (file_exists($moderr)) {
                			$form = $form;
                    		include_once($moderr);
                        	die;
	                    }
						  */
                            print_error("Could not update activity $form->id of the referentiel", 'error', "activite.php?d=$referentiel->id");
                        }
                        if (is_string($return)) {
    	        		    print_error($return, "activite.php?d=$referentiel->id");
                        }
                        add_to_log($course->id, "referentiel", "update",
            	           "mise a jour activite $form->activite_id",
                           "$form->instance", "");

					   // depot de document ?
                        if (isset($form->depot_document) && ($form->depot_document==get_string('yes'))){
						    // APPELER le script upload moodle2.php
                            if (!empty($form->ref_activite) || !empty($form->activite_id) ){
                                if (empty($form->ref_activite)){
                                    $form->ref_activite=$form->activite_id;
                                }
                                if (!empty($form->document_id)){
								    redirect($CFG->wwwroot.'/mod/referentiel/upload_moodle2.php?d='.$referentiel->id
                                    .'&userid='.$form->userid
                                    .'&activite_id='.$form->ref_activite
                                    .'&mailnow='.$mailnow
                                    .'&select_acc='.$select_acc
                                    .'&document_id='.$form->document_id
                                    .'&mode=updatedocument&old_mode=listactivityall&sesskey='.sesskey());
                                    exit;
                                }
                                else{
                                    redirect($CFG->wwwroot.'/mod/referentiel/upload_moodle2.php?d='.$referentiel->id
                                    .'&userid='.$form->userid
                                    .'&activite_id='.$form->ref_activite
                                    .'&mailnow='.$mailnow
                                    .'&select_acc='.$select_acc
                                    .'&document_id=0&mode=adddocument&old_mode=listactivityall&sesskey='.sesskey());
							 	    exit;
                                }
                            }
                        }
                    }
/*
                if (isset($form->redirecturl)) {
    	    		$SESSION->returnpage = $form->redirecturl;
                }
                else {
*/
                    $mode ='listactivityall';
                    if (has_capability('mod/referentiel:managecertif', $context)){
                        $SESSION->returnpage = "$CFG->wwwroot/mod/referentiel/activite.php?d=$referentiel->id&select_acc=$select_acc&userid=$form->userid&mode=$mode&filtre_validation=$data_filtre->filtre_validation&filtre_referent=$data_filtre->filtre_referent&filtre_date_modif=$data_filtre->filtre_date_modif&filtre_date_modif_student=$data_filtre->filtre_date_modif_student";
        			}
                    else{
                        if ($mailnow){  // MODIF JF 2011/11/29
	            		     $SESSION->returnpage = "$CFG->wwwroot/mod/referentiel/activite.php?d=$referentiel->id&select_acc=$select_acc&userid=$form->userid&activite_id=$activite_id&mailnow=$mailnow&mode=$mode";
                        }
                        else{
	            		     $SESSION->returnpage = "$CFG->wwwroot/mod/referentiel/activite.php?d=$referentiel->id&select_acc=$select_acc&userid=$form->userid&mode=$mode";
                        }
                    }
/*
                }
*/
                break;
			
                case "addactivity":
				     // echo "<br />activite.php : Ligne 337 : Formulaire\n";
				     // print_r($form);				
				
                    if (!isset($form->name) || trim($form->name) == '') {
        			    $form->name = get_string("modulename", "referentiel");
                    }
                    $return = $addfunction($form);
		      		if (!$return) {
					     print_error("Could not add a new activity to the referentiel", 'error', "activite.php?d=$referentiel->id");
			     	}
                    if (is_string($return)) {
                        print_error($return, 'error', "activite.php?d=$referentiel->id&select_acc=$select_acc&userid=$form->userid");
				    }

				    // depot de document ?
				    if (isset($form->depot_document) && ($form->depot_document==get_string('yes'))){
				        // APPELER le script
                        if ($return){
                            redirect($CFG->wwwroot.'/mod/referentiel/upload_moodle2.php?d='.$referentiel->id
                            .'&userid='.$form->userid
                            .'&activite_id='.$return
                            .'&select_acc='.$select_acc
                            .'&mailnow='.$mailnow
                            .'&document_id=0&mode=adddocument&old_mode=listactivityall&sesskey='.sesskey());
                            // redirect("$CFG->wwwroot/mod/referentiel/upload_moodle2.php?d=$referentiel->id&select_acc=$select_acc&userid=$form->userid&activite_id=$return&document_id=0&mode=adddocument&sesskey=".sesskey());
                            exit;
                        }
                    }
                    add_to_log($course->id, "referentiel", "add",
                           "creation activite $form->activite_id ",
                           "$form->instance", "");

/*
				    if (isset($form->redirecturl)) {
    	    		     $SESSION->returnpage = $form->redirecturl;
				    }
				    else {
*/
					     $mode ='listactivityall';
					     if (has_capability('mod/referentiel:managecertif', $context)){
    	    	        	$SESSION->returnpage = "$CFG->wwwroot/mod/referentiel/activite.php?d=$referentiel->id&select_acc=$select_acc&userid=$form->userid&mode=$mode&filtre_validation=$data_filtre->filtre_validation&filtre_referent=$data_filtre->filtre_referent&filtre_date_modif=$data_filtre->filtre_date_modif&filtre_date_modif_student=$data_filtre->filtre_date_modif_student";
					     }
					     else{
                            if ($mailnow){
	            		         $SESSION->returnpage = "$CFG->wwwroot/mod/referentiel/activite.php?d=$referentiel->id&select_acc=$select_acc&userid=$form->userid&activite_id=$return&mailnow=$mailnow&mode=$mode";
                            }
                            else{
	            		         $SESSION->returnpage = "$CFG->wwwroot/mod/referentiel/activite.php?d=$referentiel->id&select_acc=$select_acc&userid=$form->userid&mode=$mode";
                            }
				         }
/*
                    }
*/
                break;
			
                case "deleteactivity":
                    if (! $deletefunction($form)) {
	            	    print_error("Could not delete activity of the referentiel module");
                    }
	                else{
                        unset($SESSION->returnpage);
                        add_to_log($course->id, "referentiel", "add",
                           "suppression activite $form->activite_id ",
                           "$form->instance", "");
				    }
      		        $mode ='listactivityall';
			   	    if (has_capability('mod/referentiel:managecertif', $context)){
                        $SESSION->returnpage = "$CFG->wwwroot/mod/referentiel/activite.php?d=$referentiel->id&select_acc=$select_acc&userid=$form->userid&mode=$mode&filtre_validation=$data_filtre->filtre_validation&filtre_referent=$data_filtre->filtre_referent&filtre_date_modif=$data_filtre->filtre_date_modif&filtre_date_modif_student=$data_filtre->filtre_date_modif_student";
				    }
				    else{
                        $SESSION->returnpage = "$CFG->wwwroot/mod/referentiel/activite.php?d=$referentiel->id&select_acc=$select_acc&userid=$form->userid&mode=$mode";
				    }
                break;
            
			    default:
            	   // print_error("No mode defined");
            }
		    
		  
            if (!empty($SESSION->returnpage)) {
                $return = $SESSION->returnpage;
                unset($SESSION->returnpage);
                redirect($return);
            }
		    else {
	    	  redirect("$CFG->wwwroot/mod/referentiel/activite.php?d=$referentiel->id&select_acc=$select_acc&userid=$userid&mode=$mode");
            }
		
            exit;
    
        }
    }

    // afficher les formulaires
    unset($SESSION->modform); // Clear any old ones that may be hanging around.
    $modform = "activite.html";

	/// Check to see if groups are being used here
	/// find out current groups mode
	$groupmode = groups_get_activity_groupmode($cm);
    $currentgroup = groups_get_activity_group($cm, true);

   	/// Get all users that are allowed to submit activite
	$gusers=NULL;
    if ($gusers = get_users_by_capability($context, 'mod/referentiel:write', 'u.id', 'u.lastname', '', '', $currentgroup, '', false)) {
    	$gusers = array_keys($gusers);
    }
	// if groupmembersonly used, remove users who are not in any group
    if ($gusers and !empty($CFG->enablegroupings) and $cm->groupmembersonly) {
    	if ($groupingusers = groups_get_grouping_members($cm->groupingid, 'u.id', 'u.id')) {
       		$gusers = array_intersect($gusers, array_keys($groupingusers));
       	}
    }

	/// Print the tabs

	// DEBUG
	// echo "<br>DEBUG :: 795 :: ACTIVITE.PHP :: MODE : $mode<br>USERID_FILTRE : $userid_filtre\n";

	if (!empty($activite_id) && empty($mode)){
		$mode='listactivitysingle';
	}

	if (empty($mode) || ($mode=="list")){
		$mode='listactivityall';
	}

	/*
	if (isset($mode) && (($mode=="adddocument")|| ($mode=="updatedocument")) && !(has_capability('mod/referentiel:managecertif', $context))){
		$currenttab ='listactivityall';
		$mode ='listactivityall';
	} 
	else 
	*/
	if (!empty($mode) && (($mode=="deleteactivity")
		|| ($mode=="desapproveactivity") || ($mode=="approveactivity") || ($mode=="commentactivity") )){
		$currenttab ='updateactivity';		
	}
	else if (!empty($mode) && ($mode=='listactivitysingle')){
		$currenttab ='listactivityall';
	}
	else if (!empty($mode) && ($mode=='listactivityall')){
		$currenttab ='listactivityall';
	}
	else if (!empty($mode)){
		$currenttab = $mode;
	}
	else{
        $mode='listactivity';
        $currenttab = $mode;
    }

    if ($activite_id) {
    	$editentry = true;  //used in tabs
    }
    

    /// Mark as viewed  ??????????? A COMMENTER
    $completion=new completion_info($course);
    $completion->set_module_viewed($cm);

// AFFICHAGE DE LA PAGE Moodle 2
    $stractivite = get_string('activite','referentiel');
    $strreferentiel = get_string('modulename', 'referentiel');
    $strreferentiels = get_string('modulenameplural', 'referentiel');
    $strlastmodified = get_string('lastmodified');
    $pagetitle = strip_tags($course->shortname.': '.$strreferentiel.': '.format_string($referentiel->name,true));
    $icon = $OUTPUT->pix_url('icon','referentiel');


	// DEBUG
	// echo "<br>DEBUG :: 846 :: ACTIVITE.PHP :: MODE : $mode<br>USERID_FILTRE : $userid_filtre\n";

	// Moodle 2
    $url->param('mode', $mode);

    $PAGE->set_url($url);
    $PAGE->set_context($context);
    $PAGE->requires->css('/mod/referentiel/activite.css');
    // nouveau moodle 2.2
    $PAGE->requires->js(new moodle_url('/mod/referentiel/overlib/overlib.js'));
    $PAGE->requires->js('/mod/referentiel/functions.js');
    $PAGE->navbar->add($stractivite);
    $PAGE->set_title($pagetitle);
    $PAGE->set_heading($course->fullname);

    echo $OUTPUT->header();
    
	groups_print_activity_menu($cm,  $CFG->wwwroot . '/mod/referentiel/activite.php?d='.$referentiel->id.'&mode='.$mode.'&select_acc='.$select_acc);

    if (!empty($referentiel->name)){
        echo '<div align="center"><h1>'.$referentiel->name.'</h1></div>'."\n";
    }


    // ONGLETS
    include('tabs.php');
/*
// ANCIENNE PRESENTATION - 2011/12/01
    if  ($mode=='accompagnement'){
        // print_heading_with_help($stractivite, 'activite', 'referentiel', $icon);
        echo '<div align="center"><h2><img src="'.$icon.'" border="0" title="" alt=""> '.get_string('accompagnement','referentiel').' '.$OUTPUT->help_icon('accompagnementh','referentiel').'</h2></div>'."\n";
	}
	else{	
*/
        echo '<div align="center"><h2><img src="'.$icon.'" border="0" title="" alt=""> '.$stractivite.' '.$OUTPUT->help_icon('activiteh','referentiel').'</h2></div>'."\n";
/*
    }
*/
    // JF 2011/11/29 - Affiche destinataires en cas de notification
    if ($mailnow && $activite_id) { // id 	activite
        $destinataires=referentiel_get_referents_notification($DB->get_record('referentiel_activite', array('id' => $activite_id)));
        if ($destinataires->nbdestinataires){
            echo '<div align="center"> <a class="overlib" href="javascript:void(0);" onmouseover="return overlib(\''.$destinataires->liste_destinataires.'\', WIDTH, 500, STICKY, MOUSEOFF, VAUTO, FGCOLOR, \'#DDEEFF\', CAPTION, \''.get_string('referents', 'referentiel').'\');" onmouseout="return nd();">'.get_string('destinataires_notification', 'referentiel').'</a></div>'."\n";
        }
    }

	// DEBUG
	// echo "<br>DEBUG :: 871 :: ACTIVITE.PHP :: MODE : $mode<br>USERID_FILTRE : $userid_filtre\n";
	
	if ((!$activite_id) && ($mode=='updateactivity') && (has_capability('mod/referentiel:managecertif', $context))) {
       // MODIFIER globalement
       referentiel_print_evalue_global_liste_activites($mode, $referentiel, $userid_filtre, $gusers, $sql_filtre_where, $sql_filtre_order, $data_filtre, $select_acc);
       // referentiel_print_evalue_liste_activites($mode, $referentiel, $userid_filtre, $gusers, $sql_filtre_where, $sql_filtre_order, $data_filtre, $select_acc);
	}
	
	else if  (($mode=='listactivitysingle') && ($activite_id) && empty($userid_filtre)){
        // Afficher une activite
        referentiel_print_activite_id($activite_id, $referentiel);
	}
	else if  (($mode=='list') || ($mode=='listactivity') || ($mode=='listactivityall') || ($mode=='listactivitysingle')){
        // Affichage detaillé
		referentiel_print_evalue_liste_activites($mode, $referentiel, $userid_filtre, $gusers, $sql_filtre_where, $sql_filtre_order, $data_filtre, $select_acc);
	}
/*
	else if (($mode=='listactivitysingle') && ($userid_filtre)){
		referentiel_print_liste_activites_user($referentiel, $userid_filtre); 
	}
*/
/*
// ANCIENNE PRESENTATION - 2011/12/01
	else
	if  ($mode=='accompagnement'){
        if (has_capability('mod/referentiel:managecertif', $context)) {
            referentiel_select_accompagnement($mode, $referentiel, $USER->id, $userid_filtre, $gusers, $select_acc);
        }
        else
        if (has_capability('mod/referentiel:write', $context)){
            referentiel_print_liste_accompagnements($referentiel, $userid_filtre, $gusers, $select_acc);
        }
	}
*/
	else {
		// print_simple_box_start('center', '', '', 5, 'generalbox', $referentiel->name);
		echo $OUTPUT->box_start('generalbox  boxaligncenter');
		// recuperer l'id de l'activite
		if ($activite_id) { // id 	activite
            // page modification d'une activite
            if (! $record =  $DB->get_record("referentiel_activite", array("id" => "$activite_id"))) {
		    	print_error('Activite ID is incorrect');
			}
			$modform = "activite_edit.html";
		}
		else {
            // saisie d'une nouvelle activite
			$modform = "activite.html";
		}

    	// formulaires
	    if (file_exists($modform)) {
    	    if ($usehtmleditor = can_use_html_editor()) {
        	    $defaultformat = FORMAT_HTML;
            	$editorfields = '';
	        } 
			else {
        	    $defaultformat = FORMAT_MOODLE;
	        }
		}
		else {
    	    notice("ERREUR : No file found at : $modform)", "activite.php?d=$referentiel->id");
    	}
		
		include_once($modform);
        echo $OUTPUT->box_end();
	} 
	
    echo $OUTPUT->footer();
    die();
?>
