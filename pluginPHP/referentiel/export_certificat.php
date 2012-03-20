<?php  // $Id: export_certificat.php,v 1.0 2008/04/29/ 00:00:00 jfruitet Exp $
///////////////////////////////////////////////////////////////////////////
//                                                                       //
// GPL
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
* D'apres quiz/export.php 
* Export instance referentiel + certificat
*
* @version $Id: export_certificat.php,v 1.0 2008/04/29/ 00:00:00 jfruitet Exp $
* @author Martin Dougiamas, Howard Miller, and many others.
*         {@link http://moodle.org}
* @license http://www.gnu.org/copyleft/gpl.html GNU Public License
* @package referentiel
*/

    require_once("../../config.php");
    require_once('lib.php');
    require_once('lib_etab.php');
    include('lib_certificat.php');
    include('lib_pedagogie.php');
    include('print_lib_certificat.php');	// AFFICHAGES
    include('import_export_lib.php');	// IMPORT / EXPORT

    $exportfilename = optional_param('exportfilename','',PARAM_FILE );
    $format = optional_param('format','', PARAM_FILE );
	
    $id    = optional_param('id', 0, PARAM_INT);    // course module id
    $d     = optional_param('d', 0, PARAM_INT);    // referentielbase id
	
    $certificat_id   = optional_param('certificat_id', 0, PARAM_INT);    //record certificat id
    $action        = optional_param('action','', PARAM_ALPHA);
    $mode          = optional_param('mode','', PARAM_ALPHANUMEXT);
    $add           = optional_param('add','', PARAM_ALPHA);
    $update        = optional_param('update', 0, PARAM_INT);
    $delete        = optional_param('delete', 0, PARAM_INT);
    $approve        = optional_param('approve', 0, PARAM_INT);
    $comment        = optional_param('comment', 0, PARAM_INT);
    $course        = optional_param('course', 0, PARAM_INT);
    $groupmode     = optional_param('groupmode', -1, PARAM_INT);
    $cancel        = optional_param('cancel', 0, PARAM_BOOL);
    $userid = optional_param('userid', 0, PARAM_INT);
    $select_acc = optional_param('select_acc', 0, PARAM_INT);      // accompagnement
    $select_all = optional_param('select_all', 0, PARAM_INT);      // tous les certificats
    $format_condense = optional_param('format_condense', 0, PARAM_INT);  // format compact

    // Formations / Pedagogies
    $mode_select       = optional_param('mode_select','', PARAM_ALPHA);
    $list_pedagoids  = optional_param('list_pedagoids', '',PARAM_TEXT);
    $list_userids  = optional_param('list_userids', '',PARAM_TEXT);
    $export_pedagos = optional_param('export_pedagos', 0, PARAM_INT);  // exporattion des donnees de formation / pedagogie

    $f_promotion = optional_param('f_promotion', '', PARAM_ALPHANUM);
    $f_formation = optional_param('f_formation', '', PARAM_ALPHANUM);
    $f_pedagogie = optional_param('f_pedagogie', '', PARAM_ALPHANUM);
    $f_composante = optional_param('f_composante', '', PARAM_ALPHANUM);
    $f_num_groupe = optional_param('f_num_groupe', '', PARAM_ALPHANUM);

/*
  $sql_f_where=optional_param('sql_f_where','', PARAM_ALPHANUM);
  $sql_f_order=optional_param('sql_f_order','', PARAM_ALPHANUM);
*/

	$sql_filtre_where='';
    $data_filtre= new Object(); // parametres de filtrage

	if (isset($f_promotion)){
		$data_filtre->f_promotion=$f_promotion;
	}
	else{
		$data_filtre->f_promotion='';
	}
	if (isset($f_formation)){
		$data_filtre->f_formation=$f_formation;
	}
	else{
		$data_filtre->f_formation='';
	}
	if (isset($f_pedagogie)){
		$data_filtre->f_pedagogie=$f_pedagogie;
	}
	else{
		$data_filtre->f_pedagogie='';
	}
    if (isset($f_composante)){
		$data_filtre->f_composante=$f_composante;
	}
	else{
		$data_filtre->f_composante='';
	}
    if (isset($f_num_groupe)){
		$data_filtre->f_num_groupe=$f_num_groupe;
	}
	else{
		$data_filtre->f_num_groupe='';
	}

    // nouveaute Moodle 1.9 et 2
    $url = new moodle_url('/mod/referentiel/export_certificat.php');

	if ($d) {     // referentiel_referentiel_id
        if (! $referentiel = $DB->get_record("referentiel", array("id" => "$d"))) {
            print_error('Referentiel instance is incorrect');
        }
        if (! $referentiel_referentiel = $DB->get_record("referentiel_referentiel", array("id" => "$referentiel->ref_referentiel"))) {
            print_error('R�ferentiel id is incorrect');
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
		print_error(get_string('erreurscript','referentiel','Erreur01 : export_certificat.php'), 'referentiel');
	}



	if ($certificat_id) { // id certificat
        if (! $record = $DB->get_record("referentiel_certificat", array("id" => "$certificat_id"))) {
            print_error('certificat ID is incorrect');
        }
	}

    // MODIF JF mai 2011
    if ($referentiel->ref_referentiel){
        $existe_pedagos=referentiel_pedagogies_exists($referentiel->ref_referentiel);
    }
    else{
        $existe_pedagos=0;
    }

    // get display strings
    $txt = new object;
    $txt->choisir = get_string('choisir','referentiel');
    $txt->condense = get_string('format_condense','referentiel');
    $txt->reduit1 = get_string('format_reduit1','referentiel');
    $txt->reduit2 = get_string('format_reduit2','referentiel');
    $txt->referentiel = get_string('referentiel','referentiel');
    $txt->download = get_string('download','referentiel');
    $txt->downloadextra = get_string('downloadextra','referentiel');
    $txt->exporterror = get_string('exporterror','referentiel');
    $txt->exportname = get_string('exportname','referentiel');
    $txt->exportcertificat = get_string('exportcertificat', 'referentiel');
    $txt->fileformat = get_string('fileformat','referentiel');
    $txt->modulename = get_string('modulename','referentiel');
    $txt->modulenameplural = get_string('modulenameplural','referentiel');
    // $txt->tofile = get_string('tofile','referentiel');
	
	// PAS DE RSS
    // require_once("$CFG->libdir/rsslib.php");

	
    require_login($course->id, false, $cm);

    if (!isloggedin() or isguestuser()) {
        redirect($CFG->wwwroot.'/mod/referentiel/view.php?id='.$cm->id.'&non_redirection=1');
    }

    // check role capability
    $context = get_context_instance(CONTEXT_MODULE, $cm->id);
    require_capability('mod/referentiel:export', $context);

    // ensure the files area exists for this course
    // Moodle 1.9
    // make_upload_directory( "$course->id/$CFG->moddata/referentiel" );

    if ($certificat_id) {    // So do you have access?
      if (!(has_capability('mod/referentiel:writereferentiel', $context) 
			or referentiel_certificat_isowner($certificat_id)) or !confirm_sesskey() ) {
            print_error(get_string('noaccess','referentiel'));
        }
    }
	
 
	// selecteur
	$userid_filtre=0;
    if (!empty($userid)) {
        $userid_filtre=$userid;
    }
	
	// RECUPERER LES FORMULAIRES
    if (isset($SESSION->modform)) {   // Variables are stored in the session
        $form = $SESSION->modform;
        unset($SESSION->modform);
    } else {
        $form = (object)$_POST;
    }



/// data selection filtre
  if (isset($mode_select) && ($mode_select=='selectetab') && confirm_sesskey() ){
		// gestion des filtres;


// WHERE
		if (!empty($f_promotion)){
				$sql_filtre_where.=' AND promotion=\''.$f_promotion.'\' ';
		}
		if (!empty($f_formation)){
				$sql_filtre_where.=' AND formation=\''.$f_formation.'\' ';
		}
		if (!empty($f_pedagogie)){
				$sql_filtre_where.=' AND pedagogie=\''.$f_pedagogie.'\' ';
		}
		if (!empty($f_composante)){
				$sql_filtre_where.=' AND composante=\''.$f_composante.'\' ';
		}
		if (!empty($f_num_groupe)){
				$sql_filtre_where.=' AND num_groupe=\''.$f_num_groupe.'\' ';
		}

		// echo "<br />DEBUG :: export_certificat.php :: Ligne 265 :: FILTRES : WHERE : $sql_filtre_where \n";
		// exit;

  }


	if ($cancel) {
        if (!empty($SESSION->returnpage)) {
            $return = $SESSION->returnpage;
            unset($SESSION->returnpage);
            redirect($return);
        } else {
            redirect('certificat.php?d='.$referentiel->id);
        }
    }


    if (!empty($form->deletefile) && confirm_sesskey()){
        // Suppression des fichiers
        foreach ($form->deletefile as $fullpathfile) {
            if ($fullpathfile){
                // echo "<br>DEBUG :: archive.php :: 252<br>\n";
                // echo "<br>$fullpathfile\n";
                referentiel_delete_a_file($fullpathfile);
            }
        }
        unset($form);
    }

    // tous les certificats ?
    /// selection utilisateurs accompagn�s
    if (isset($action) && ($action=='select_acc') && confirm_sesskey()){
		  if (isset($form->select_acc)  ){
		  	$select_acc=$form->select_acc;
		  }
		  if (isset($form->select_all)){
		  	$select_all=$form->select_all;
		  }
		  if (isset($form->mode) && ($form->mode!='')){
			 $mode=$form->mode;
		  }
		  // echo "<br />ACTION : $action  SEARCH : $userid_filtre\n";
		  unset($form);
		  unset($action);
		  // exit;
    }
    
    // SELECTION PEDAGOGIES
    $affiche=true;    // affichage les boites de selection

    if (isset($action) && ($action=='selectpedagogie') && confirm_sesskey()){
        // charger les pedagogies
        // DEBUG
        //echo "<br>DEBUG :: export_certificat.php :: 201<br>\n";
        //print_r($form);
        if (isset($form->select_acc)){
		  	$select_acc=$form->select_acc;
        }
		if (isset($form->select_all)){
		  	$select_all=$form->select_all;
		}

        if (isset($form->mode) && ($form->mode!='')){
            $mode=$form->mode;
        }


        $r_userids=array();
        $list_pedagoids='';
        $list_userids='';
        if (!empty($form->t_pedago)){
            // echo "<br>DEBUG :: export_certificat.php :: 209<br>\n";
            // print_r($form->t_pedago);
            foreach($form->t_pedago as $pedagoid){
                $list_pedagoids.=$pedagoid.',';
                $r_userids[]=referentiel_get_userids_pedagogie($pedagoid, $referentiel_referentiel->id);
            }
        }
        if (!empty($r_userids)){
            // recuperer la liste des utilisateurs
            foreach ($r_userids  as $record_u) {   // afficher la liste d'users
                //echo "<br>DEBUG :: lib_certificat.php :: 193<br>\n";
                //print_r($record_u);
                foreach ($record_u as $ru){
                    //echo "<br>DEBUG :: lib_certificat.php :: 196<br>\n";
                    //print_r($ru);
                    //echo "<br>".$ru->userid;
                    $list_userids.=$ru->userid.',';
                }
            }
            $select_all=2;
        }
        //echo "<br>DEBUG :: export_certificat.php :: 212<br>\n";
        //print_r($r_userids);
        //echo "<br>DEBUG :: export_certificat.php :: 209 :: LISTE_PEDAGO : $list_pedagoids :: SELECT_ACC : $select_acc :: SELECT_ALL : $select_all\n";

		// echo "<br />ACTION : $action \n";
		unset($form);
		unset($action);
		//exit;
    }

    /// selection utilisateurs
    if (isset($action) && ($action=='select_all_certificates')){
		  if (isset($form->select_all) && confirm_sesskey() ){
		  	$select_all=$form->select_all;
		  }
		  if (isset($form->mode) && ($form->mode!='')){
			 $mode=$form->mode;
		  }
		  // echo "<br />ACTION : $action  SEARCH : $userid_filtre\n";
		  unset($form);
		  unset($action);
		  // exit;
    }
    
	if (empty($mode)){
		$mode='managecertif'; // un seul mode possible
	}
	$currenttab = 'managecertif';
	// Moodle 2
    $url->param('mode', $mode);

    $defaultformat = FORMAT_MOODLE;

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


    /// Mark as viewed  ??????????? A COMMENTER
    $completion=new completion_info($course);
    $completion->set_module_viewed($cm);

// AFFICHAGE DE LA PAGE Moodle 2
    $strreferentiel = get_string('modulename', 'referentiel');
    $strreferentiels = get_string('modulenameplural', 'referentiel');
    $strmessage = get_string('exportcertificat','referentiel');
    $strpagename=get_string('exportcertificat','referentiel');
    $strlastmodified = get_string('lastmodified');
    $pagetitle = strip_tags($course->shortname.': '.$strreferentiel.': '.format_string($referentiel->name,true));
    $icon = $OUTPUT->pix_url('icon','referentiel');

    $PAGE->set_url($url);
    $PAGE->requires->css('/mod/referentiel/activite.css');
    $PAGE->requires->css('/mod/referentiel/jauge.css');
    $PAGE->requires->css('/mod/referentiel/certificat.css');
    // $PAGE->requires->js('/lib/overlib/overlib.js');
    // $PAGE->requires->js('/mod/referentiel/functions.js');

    $PAGE->set_title($pagetitle);
    $PAGE->navbar->add($strpagename);
    $PAGE->set_heading($course->fullname);

    echo $OUTPUT->header();

	groups_print_activity_menu($cm,  $CFG->wwwroot . '/mod/referentiel/export_certificat.php?d='.$referentiel->id.'&mode='.$mode.'&select_acc='.$select_acc);

    if (!empty($referentiel->name)){
        echo '<div align="center"><h1>'.$referentiel->name.'</h1></div>'."\n";
    }

    // ONGLETS
    include('tabs.php');

    // print_heading_with_help($strmessage, 'exportcertificat', 'referentiel', $icon);

    echo '<div align="center"><h2><img src="'.$icon.'" border="0" title="" alt=""> '.$strmessage.' '.$OUTPUT->help_icon('exportcertificath','referentiel').'</h2></div>'."\n";

    
    if (!empty($format)) {  // La selection du format d'exportation est faite

        //echo "<br>DEBUG :: export_certificat.php :: 426 :: MODE : $mode :: LISTE_USERS : $list_userids :: SELECT_ACC : $select_acc :: SELECT_ALL : $select_all<br>\n";
        $records_certificats=referentiel_get_liste_certificats($referentiel, $course, $context, $list_userids, $userid_filtre, $gusers, $select_acc, $select_all);
        //echo "<br>DEBUG :: Print :: 427<br>CERTIFICATS <br>\n";
        //print_r($records_certificats);
		//exit;

		/// Filename et format d'exportation
// DEBUG 
//echo "<br /> OK 1\n";
        if (!confirm_sesskey()) {
            print_error( 'sesskey' );
        }
// DEBUG 
//echo "<br /> OK 2\n";
        if (! is_readable("format/$format/format.php")) {
            print_error( "Format not known ($format)" );  
		}
// DEBUG 
//echo "<br /> OK 3\n";
    // load parent class for import/export
        require("format.php");
// DEBUG 
//echo "<br /> OK 4\n";
    // and then the class for the selected format
        require("format/$format/format.php");
// DEBUG 
//echo "<br /> OK 5\n";
        $classname = "cformat_$format";
        $cformat = new $classname();
// DEBUG 
// echo "<br /> OK 6\n";
        // $cformat->setCategory( $category );
        $cformat->setCoursemodule( $cm );
        $cformat->setContext( $context );
        $cformat->setCourse( $course );
        $cformat->setFilename( $exportfilename );
        $cformat->setIReferentiel( $referentiel);
        $cformat->setRReferentiel( $referentiel_referentiel);
        $cformat->setRefReferentiel( $referentiel->ref_referentiel);
        $cformat->setRCertificats($records_certificats);
        // DEBUG
        if (isset($format_condense)){
            $cformat->setRCFormat($format_condense);
		}
        if (isset($export_pedagos)){
            $cformat->setExportPedago($export_pedagos);
		}
		
        // DEBUG
        // echo "<br /> OK 7\n";

        if (! $cformat->exportpreprocess()) {   // Do anything before that we need to
              print_error( $txt->exporterror, $CFG->wwwroot.'/mod/referentiel/export_certificat.php?id='.$cm->id);
        }
        // echo "<br /> OK 8\n";
        if (! $cformat->exportprocess()) {         // Process the export data
            print_error( $txt->exporterror, $CFG->wwwroot.'/mod/referentiel/export_certificat.php?id='.$cm->id);
        }
        // echo "<br /> OK 9\n";
        if (! $cformat->exportpostprocess()) {                    // In case anything needs to be done after
            print_error( $txt->exporterror, $CFG->wwwroot.'/mod/referentiel/export_certificat.php?d='.$cm->id);
        }
        echo "<hr />";
        // echo "<br /> OK 10\n";

        // link to download the finished file
        $file_ext = $cformat->export_file_extension();
        // Moodle 1.9
        /*
        if ($CFG->slasharguments) {
          $efile = "{$CFG->wwwroot}/file.php/".$cformat->get_export_dir()."/$exportfilename".$file_ext."?forcedownload=1";
        }
        else {
          $efile = "{$CFG->wwwroot}/file.php?file=/".$cformat->get_export_dir()."/$exportfilename".$file_ext."&forcedownload=1";
        }
        */
        
        // Moodle 2.0
        $fullpath = '/'.$context->id.'/mod_referentiel/certificat/0'.$cformat->get_export_dir().$exportfilename.$file_ext;
        $efile = new moodle_url($CFG->wwwroot.'/pluginfile.php'.$fullpath);

        echo "<p><div class=\"boxaligncenter\"><a href=\"$efile\">$txt->download</a></div></p>";
        echo "<p><div class=\"boxaligncenter\"><font size=\"-1\">$txt->downloadextra</font></div></p>";

        print_continue($CFG->wwwroot.'/mod/referentiel/certificat.php?id='.$cm->id);
        echo $OUTPUT->footer();
        die();

    }
    else{ // BOITES DE SELECTION

            echo '<div align="center"><h3><img src="'.$icon.'" border="0" title="" alt=""> '.get_string('selectcertificat','referentiel').' '.$OUTPUT->help_icon('selectcertificath','referentiel').'</h3></div>'."\n";
            referentiel_select_liste_certificats($referentiel, $list_pedagoids, $userid_filtre, $gusers, $select_acc, $mode, $CFG->wwwroot . '/mod/referentiel/export_certificat.php?d='.$referentiel->id, $select_all, $sql_filtre_where, $data_filtre);

             /// Display upload form

            // get valid formats to generate dropdown list
            $fileformatnames = referentiel_get_import_export_formats( 'export', 'cformat' );

            // get filename
            if (empty($exportfilename)) {
                $exportfilename = referentiel_default_export_filename($course, $referentiel, 'certificat');
            }
            echo "\n<br />\n";

            //print_box_start('generalbox boxwidthnormal boxaligncenter');
            echo $OUTPUT->box_start('generalbox  boxaligncenter');
            echo "\n<div align=\"center\">\n";
?>

            <form enctype="multipart/form-data" method="post" action="export_certificat.php?id=<?php echo $cm->id; ?>">
            <fieldset class="invisiblefieldset" style="display: block;">
            <input type="hidden" name="sesskey" value="<?php echo sesskey(); ?>" />

            <table cellpadding="5">
                <tr>
                    <td><?php echo $txt->fileformat; ?>:</td>
                    <td>
                        <?php
                        // choose_from_menu($fileformatnames, 'format', 'csv', '');
                        echo html_writer::select($fileformatnames, 'format', 'csv', false);
                        // helpbutton('format_export_certificat', $txt->referentiel, 'referentiel');
                        echo $OUTPUT->help_icon('formath', 'referentiel');
                        ?>
                    </td>
                    <td>
                        <?php echo $txt->condense.' ?<br>' ; ?>
                        <input type="radio" name="format_condense" value="0"/><?php print_string('no'); ?>
                        <input type="radio" name="format_condense" value="1"/><?php echo $txt->reduit1; ?>
                        <input type="radio" name="format_condense" value="2" checked="checked"/><?php echo $txt->reduit2; ?>
                    </td>
                </tr>
<?php
if ($existe_pedagos){
    echo '<tr><td colspan="2"><i>'.get_string('export_pedagos','referentiel').'</i>: </td>
<td>
    <input type="radio" name="export_pedagos" value="0"/>'.get_string('no').'
    <input type="radio" name="export_pedagos" value="1" checked="checked"/>'.get_string('yes').'
</td></tr>'."\n";
}
?>

                <tr>
                    <td><?php echo $txt->exportname; ?>:</td>
                    <td align="center" colspan="2">
                        <input type="text" size="60" name="exportfilename" value="<?php echo $exportfilename; ?>" />
                    </td>
                </tr>
                <tr>
                    <td align="center" colspan="3">
<?php
if (!empty($list_pedagoids)){
    echo '<i>'.get_string('forcer_regeneration', 'referentiel').'</i>: <input type="radio" name="debug_special" value="0" checked="checked"/>'.get_string('no').'
    <input type="radio" name="debug_special" value="1" />'.get_string('yes')."\n";
}
?>

                        <input type="submit" name="save" value="<?php echo $txt->exportcertificat; ?>" />
                    </td>
                </tr>
            </table>
            <!-- tous les certificats -->
            <input type="hidden" name="select_all" value="<?php echo $select_all; ?>" />
            <!-- accompagnement -->
            <input type="hidden" name="select_acc" value="<?php echo $select_acc; ?>" />
            <!-- user -->
            <input type="hidden" name="userid" value="<?php echo $userid; ?>" />
            <input type="hidden" name="afficher" value="0" />
            <input type="hidden" name="list_userids" value="<?php echo $list_userids; ?>">
            </form>
<?php
            echo "\n</div>\n";
            echo $OUTPUT->box_end();


            // print_heading_with_help(get_string('selectcertificat','referentiel'), 'selectcertificat', 'referentiel', $icon);

           // Gestion des fichiers d'archives
            referentiel_get_manage_files($context->id, 'certificat', 0, get_string('exportedcertificates', 'referentiel'), "export_certificat.php?id=$cm->id", !empty($CFG->enableportfolios));


        echo $OUTPUT->footer();
        die();
    }
?>
