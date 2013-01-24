<?php  // $Id: import_instance.php,v 1.0 2008/04/29/ 00:00:00 jfruitet Exp $
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
* Importation d'un referentiel
* D'apres competency/import.php 
*
* @package referentiel
*/
    require_once('../../config.php');
    require_once('lib.php');
    require_once('import_export_lib.php');	// IMPORT / EXPORT
    require_once('class/import_form.php'); // formulaires de choix de fichiers
    // require_once($CFG->libdir . '/uploadlib.php'); // Moodle 1.9
    require_once("$CFG->dirroot/repository/lib.php");
    

    $id    = optional_param('id', 0, PARAM_INT);    // course module id
    $d     = optional_param('d', 0, PARAM_INT);    // referentiel base id
	$pass  = optional_param('pass', 0, PARAM_INT);    // mot de passe ok
    $checkpass = optional_param('checkpass','', PARAM_ALPHANUM); // mot de passe fourni

    $mode = optional_param('mode','', PARAM_ALPHANUMEXT);

    $format = optional_param('format','', PARAM_FILE );
    $courseid = optional_param('courseid', 0, PARAM_INT);
	$select_acc = optional_param('select_acc', 0, PARAM_INT);      // accompagnement
    
    

    // nouveaute Moodle 1.9 et 2
    $url = new moodle_url('/mod/referentiel/import.php');

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
		print_error(get_string('erreurscript','referentiel','Erreur01 : import.php'), 'referentiel');
	}


/*

    // get parameters
    $params = new stdClass;
    $params->choosefile = optional_param('choosefile','',PARAM_PATH);
    $params->stoponerror = optional_param('stoponerror', 0, PARAM_BOOL);
    $params->override = optional_param('override', 0, PARAM_BOOL);	
    $params->newinstance = optional_param('newinstance', 0, PARAM_BOOL);		

    // get display strings
    $txt = new stdClass();
    $txt->referentiel = get_string('referentiel','referentiel');
    $txt->fileformat = get_string('fileformat','referentiel');
	$txt->choosefile = get_string('choosefile','referentiel');
	
	$txt->formatincompatible= get_string('formatincompatible','referentiel');
    $txt->file = get_string('file');
    $txt->fileformat = get_string('fileformat','referentiel');
    $txt->fromfile = get_string('fromfile','referentiel');
    $txt->importerror = get_string('importerror','referentiel');
    $txt->importfilearea = get_string('importfilearea','referentiel');
    $txt->importfileupload = get_string('importfileupload','referentiel');
    $txt->importfromthisfile = get_string('importfromthisfile','referentiel');
    $txt->modulename = get_string('modulename','referentiel');
    $txt->modulenameplural = get_string('modulenameplural','referentiel');
    $txt->onlyteachersimport = get_string('onlyteachersimport','referentiel');
    $txt->stoponerror = get_string('stoponerror', 'referentiel');
	$txt->upload = get_string('upload');
    $txt->uploadproblem = get_string('uploadproblem');
    $txt->uploadthisfile = get_string('uploadthisfile');
	$txt->importreferentiel	= get_string('importreferentiel','referentiel');
	$txt->newinstance	= get_string('newinstance','referentiel');	
	$txt->choix_newinstance	= get_string('choix_newinstance','referentiel');
	$txt->choix_notnewinstance	= get_string('choix_notnewinstance','referentiel');
	$txt->override = get_string('override', 'referentiel');
	$txt->choix_override	= get_string('choix_override','referentiel');
	$txt->choix_notoverride	= get_string('choix_notoverride','referentiel');
*/
	

    require_login($course->id, false, $cm);   // pas d'autologin guest

    if (!isloggedin() or isguestuser()) {
        redirect($CFG->wwwroot.'/index.php?id='.$course->id);
    }

    // check role capability
    $context = get_context_instance(CONTEXT_MODULE, $cm->id);
    require_capability('mod/referentiel:import', $context);

    // ensure the files area exists for this course
    // Moodle 1.9
    // make_upload_directory( "$course->id/$CFG->moddata/referentiel" );

	if (empty($mode)) {
        $mode='import'; // un seul mode possible
    }
    $url->param('mode', $mode);
    $action='importreferentiel'; // une seule action possible
    $url->param('action', $action);

	$currenttab = 'import';
    if ($referentiel->id) {
    	$editentry = true;  //used in tabs
    }
    
    $defaultformat = FORMAT_MOODLE;

    // AFFICHAGE DE LA PAGE Moodle 2
	$strreferentiels = get_string('modulenameplural','referentiel');
	$strreferentiel = get_string('referentiel','referentiel');
	$strpagename=get_string('import','referentiel');
	$strmessage = get_string('importreferentiel','referentiel');
    $strlastmodified = get_string('lastmodified');
    $pagetitle = strip_tags($course->shortname.': '.$strreferentiel.': '.format_string($referentiel->name,true));
    $icon = $OUTPUT->pix_url('icon','referentiel');

    $PAGE->set_url($url);
	/// RSS and CSS and JS meta

    // $PAGE->requires->css('/mod/referentiel/activite.css');
    // $PAGE->requires->css('/mod/referentiel/jauge.css');
    // $PAGE->requires->css('/mod/referentiel/certificat.css');
    // $PAGE->requires->js('/lib/overlib/overlib.js');
    // $PAGE->requires->js('/mod/referentiel/functions.js');

    $PAGE->set_title($pagetitle);
    $PAGE->navbar->add($strpagename);
    $PAGE->set_heading($course->fullname);

    // formulaire de saisie d'un fichier
    $fileformatnames = referentiel_get_import_export_formats( 'import', 'rformat' );
    $options = array('subdirs'=>0, 'maxbytes'=>get_max_upload_file_size($CFG->maxbytes, $course->maxbytes, 0), 'maxfiles'=>1, 'accepted_types'=>'*', 'return_types'=>FILE_INTERNAL);
    $mform = new referentiel_import_form(null, array('d'=>$referentiel->id, 'contextid'=>$context->id, 'filearea'=>'referentiel', 'fileformats' => $fileformatnames, 'override' => 0, 'stoponerror' => 1, 'newinstance' => 1, 'action' => 'importreferentiel', 'msg' =>  get_string('import', 'referentiel'), 'options'=>$options));

    /*
    if (!empty($referentiel->name)){
        echo '<div align="center"><h1>'.$referentiel->name.'</h1></div>'."\n";
    }
    */
    // mot de passe ?
	if ($referentiel_referentiel){
    	// Le referentiel est-il protege par mot de passe ?
        // RECUPERER LES FORMULAIRES
        if (isset($SESSION->modform)) {   // Variables are stored in the session
            $form = $SESSION->modform;
            unset($SESSION->modform);
        }
        else {
            $form = (object)$_POST;
        }


		if (!$pass && ($checkpass=='checkpass') && !empty($form->pass_referentiel)){
			$pass=referentiel_check_pass($referentiel_referentiel, $form->pass_referentiel);
			if (!$pass){
				// Abandonner
 				print_continue($CFG->wwwroot.'/mod/referentiel/view.php?id='.$cm->id.'&non_redirection=1');
      			exit;
			}
		}
		else{
			// saisie du mot de  passe
			if (isset($referentiel_referentiel->mail_auteur_referentiel) && ($referentiel_referentiel->mail_auteur_referentiel!='')
				&& (referentiel_get_user_mail($USER->id)!=$referentiel_referentiel->mail_auteur_referentiel)) {
				//
				echo $OUTPUT->header();
                echo $OUTPUT->box_start('generalbox  boxaligncenter');
    	    	// formulaires
				$appli_appelante="import.php";
				include_once("pass.html");
                echo $OUTPUT->box_end();
                echo $OUTPUT->footer();
                die();
			}
		}
	}

    // recuperer le fichier charge
    if ($mform->is_cancelled()) {
        redirect(new moodle_url('/course/view.php', array('id'=>$course->id)));
    }
    else if ($mform->get_data()) {

        $returnlink = new moodle_url('/mod/referentiel/view.php', array('id'=>$cm->id));

        if ($formdata = $mform->get_data()) {
            // DEBUG
            // echo "<br />DEBUG :: import_instance.php :: 193 :: FORMDATA\n";
            // print_object($formdata);

            // documents activites et consignes des tâches
            $fileareas = array('referentiel', 'document', 'consigne', 'activite', 'task', 'certificat', 'scolarite', 'pedagogie');
            if (empty($formdata->filearea) || !in_array($formdata->filearea, $fileareas)) {
                return false;
            }


            $fs = get_file_storage();
            // suppression du fichier existant ?   NON
            // $fs->delete_area_files($formdata->contextid, 'mod_referentiel', $formdata->filearea, 0);

            if ($newfilename= $mform->get_new_filename('referentiel_file')) {

				echo $OUTPUT->header();

                $contents = $mform->get_file_content('referentiel_file');
                if (!empty($contents)){
                    /*
                    $fullpath = "/$formdata->contextid/mod_referentiel/$formdata->filearea/0/$newfilename";
                    $link = new moodle_url($CFG->wwwroot.'/pluginfile.php'.$fullpath);
                    // DEBUG
                    echo "<br>DEBUG :: 219 :: $link<br />\n";
                    */

                    $format=$formdata->format;

                    // echo "<br>DEBUG :: 235 :: $format<br />\n";

                    if (! is_readable("format/$format/format.php")) {
                        print_error( get_string('formatnotfound','referentiel', $format) );
                    }

                    require_once("format.php");  // Parent class
                    require_once("format/$format/format.php");
                    $classname = "rformat_$format";
                    // echo "<br>DEBUG :: 232 :: $classname<br />\n";

                    $rformat = new $classname();
                    // load data into class
                    $rformat->setIReferentiel( $referentiel ); // instance
                    // $rformat->setRReferentiel( $referentiel_referentiel ); // not yet
                    $rformat->setCourse( $course );
                    $rformat->setContext( $context );
                    $rformat->setCoursemodule( $cm);
                    $rformat->setContents( $contents );
                    $rformat->setStoponerror( $formdata->stoponerror );
                    $rformat->setOverride( $formdata->override );
                    $rformat->setNewinstance( $formdata->newinstance );
                    $rformat->setAction( $formdata->action );


                    // Do anything before that we need to
                    if (! $rformat->importpreprocess()) {
                        print_error( get_string('importerror','referentiel') , $returnlink);
                    }

                    // Process the uploaded file

                    if (! $rformat->importprocess() ) {
                        print_error( get_string('importerror','referentiel') , $returnlink);
                    }

                    // In case anything needs to be done after
                    if (! $rformat->importpostprocess()) {
                        print_error( get_string('importerror','referentiel') , $returnlink);
                    }

                    // Verifier si  referentiel charge
                    if (! $rformat->new_referentiel_id) {
                        print_error( get_string('importerror_referentiel_id','referentiel') , $returnlink);
                    }

                    // mettre a jour l'instance
                    if (empty($formdata->newinstance)){
                        $DB->set_field ('referentiel','ref_referentiel',$rformat->new_referentiel_id, array("id" => "$referentiel->id"));
                    }
                    else{
                        // il faudrait créer une nouvelle instance mais ce n'est pas l'endroit
                        // on se contente de créer le référentiel_referentiel
                        // sans l'associer à l'instance courante
                    }

                    echo "<hr />";
                    if (isset($rformat->returnpage) && ($rformat->returnpage!="")){
                        print_continue($rformat->returnpage);
                    }
                    else{
                        print_continue($CFG->wwwroot.'/mod/referentiel/view.php?id='.$cm->id.'&non_redirection=1');
                    }

                }
                else{
                    print_error( get_string('cannotread','referentiel') );
                }
                echo $OUTPUT->footer();
                die();
            }
        }
        redirect($returnlink);
    }
    
    // afficher la page
    echo $OUTPUT->header();

    // ONGLETS
    include('tabs.php');

    echo '<div align="center"><h2><img src="'.$icon.'" border="0" title="" alt=""> '.$strmessage.' '.$OUTPUT->help_icon('importreferentielh','referentiel').'</h2></div>'."\n";


    echo $OUTPUT->box_start('generalbox');
    $mform->display();
    echo $OUTPUT->box_end();
    echo $OUTPUT->footer();
    die();

?>
