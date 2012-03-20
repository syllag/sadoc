<?php //$Id: restorelib.php,v 0.1 2009/08/07 16:32:01 jfruitet Exp $
    //This php script contains all the stuff to backup/restore
    //referentiel mods

    // This is the "graphical" structure of the referentiel module:
    //
    //                 (refrentiel INSTANCE)                                          (referentiel OCCURRENCE)
    //                     Table referentiel                                         Table referentiel_referentiel         Table referentiel_etablissement     Table referentiel_pedagogie
    //                    (CL,pk->id, fk->id_referentiel_referentiel)                         (pk->id)                         (pk->id, files)                      (pk->id)
    //                        |                        |                                        #                                   |                                 |
    //                        |                        |                                        |                      Table referentiel_etudiant         Table referentiel_a_user_pedagogie
    //                        |                        |-----------------n : 1 -----------------|                      (UL, pk->id, fk->id_etablissement,       ( pk->id, fk->id_pedagogie,
    //                      1 : n                                                               |                       fk->id_user) --------------------------- fk->id_user, fk->id_referentiel_referentiel)
    //                        |                                                                 |                                                                                       |
    //                        |---------------------------------------|                         |-----|---------------------------------------------------------------------------------|
    //                        |                                       |                               |
    //           Table referentiel_activites            Table referentiel_task            Table referentiel_certificat
    //           (UL,pk->id, fk->referentiel,files,	  (UL,pk->id, fk->referentiel,files)   (pk->id, fk->id_referentiel_referentiel
    //                     fk->id_user)          |      |             |                          fk->id_user)
    //                        |                  |      |             |                               |
    //                      1 : n                |      |           1 : n                             |
    //                        |                  |      |             |                               |
    //                 referentiel_document      |      |      referentiel_consigne          referentiel_domaine
    //           (pk->id, fk->id_activite, files)|      |     (pk->id, fk->id_task, files)    (pk->id, fk->id_referentiel_referentiel)
	//                                           |      |                                             |
	//                                           |      |                                    referentiel_competence
	//                                           |------|                                     (pk->id, fk->id_domaine)
	//										         |                                                |
	//                                               |                                       referentiel_item_competence
	//                                    referentiel_a_user_task                            (pk->id, fk->id_competence, fk->id_referentiel_referentiel)
	//                           (UL, pk->id, fk->id_activite, fk->id_task)
	//
    // Meaning: pk->primary key field of the table
    //          fk->foreign key to link with parent
    //          nt->nested field (recursive data)
    //          CL->course level info
    //          UL->user level info
    //          files->table may have files)
    //
    //-----------------------------------------------------------

    //This function executes all the restore procedure about this mod
    function referentiel_restore_mods($mod,$restore) {

        global $CFG;
        global $DB;
		
		// structure pour enregistrer les coorespondances entre old et new ids
		global $referentiel_ids;
		
		$referentiel_ids = new object();
		$referentiel_ids->referentiel_activite= array();
		$referentiel_ids->referentiel_task= array();
		// $referentiel_ids->referentiel_etablissement= array();
		$referentiel_ids->referentiel_userid= array();
		$referentiel_ids->referentiel_teacherid= array();

        $status = true;

        //Get record from backup_ids
        $data = backup_getid($restore->backup_unique_code,$mod->modtype,$mod->id);

        if ($data) {
            //Now get completed xmlized object
            $info = $data->info;
            //if necessary, write to restorelog and adjust date/time fields
            if ($restore->course_startdateoffset) {
                restore_log_date_changes('referentiel', $restore, $info['MOD']['#'], array('DATE_INSTANCE', 'CONFIG'));
            }
            // DEBUG
			// traverse_xmlize($info);                                                                     //Debug
            // print_object ($GLOBALS['traverse_array']);                                                  //Debug
            // $GLOBALS['traverse_array']="";                                                              //Debug

            //Now, build the referentiel record structure
			/*
			CREATE TABLE mdl_referentiel (
  id bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(80) NOT NULL DEFAULT '',
  description_instance text NOT NULL,
  label_domaine varchar(80) NOT NULL DEFAULT '',
  label_competence varchar(80) NOT NULL DEFAULT '',
  label_item varchar(80) NOT NULL DEFAULT '',
  date_instance bigint(10) unsigned NOT NULL DEFAULT '0',
  course bigint(10) unsigned NOT NULL DEFAULT '0',
  ref_referentiel bigint(10) unsigned NOT NULL DEFAULT '0',
  visible tinyint(1) unsigned NOT NULL DEFAULT '1',
  config varchar(255) NOT NULL DEFAULT 'scol:0;creref:0;selref:0;impcert:0;',
  PRIMARY KEY (id)
			*/
            $referentiel->course = $restore->course_id;
            $referentiel->name = backup_todb($info['MOD']['#']['NAME']['0']['#']);
            $referentiel->description_instance = backup_todb($info['MOD']['#']['DESCRIPTION_INSTANCE']['0']['#']);
            $referentiel->label_domaine = backup_todb($info['MOD']['#']['LABEL_DOMAINE']['0']['#']);
            $referentiel->label_competence = backup_todb($info['MOD']['#']['LABEL_COMPETENCE']['0']['#']);
            $referentiel->label_item = backup_todb($info['MOD']['#']['LABEL_ITEM']['0']['#']);
            $referentiel->date_instance = backup_todb($info['MOD']['#']['DATE_INSTANCE']['0']['#']);
            $referentiel->ref_referentiel = backup_todb($info['MOD']['#']['REF_REFERENTIEL']['0']['#']);
            $referentiel->visible = backup_todb($info['MOD']['#']['VISIBLE']['0']['#']);
            $referentiel->config = backup_todb($info['MOD']['#']['CONFIG']['0']['#']);
			$referentiel->config_impression = backup_todb($info['MOD']['#']['CONFIG_IMPRESSION']['0']['#']);
			      
			// recuperer l'id du referentiel_referentiel
			// restore referentiel_referentiel
			$new_referentiel_referentiel_id = referentiel_referentiel_restore_mods($info, $restore);
			
			if ($new_referentiel_referentiel_id) { 
				// mettre a jour
	            $referentiel->ref_referentiel = $new_referentiel_referentiel_id;
				
        	    //The structure is equal to the refrentiel instance, so insert the referentiel
            	$newid = $DB->insert_record ("referentiel",$referentiel);
				
	            //Do some output     
    	        if (!defined('RESTORE_SILENTLY')) {
        	        echo "<li>".get_string("modulename","referentiel")." \"".format_string(stripslashes($referentiel->name),true)."\"</li>";
            	}
	            backup_flush(300);
				
            	if ($newid) {
                	//We have the newid, update backup_ids
	                backup_putid($restore->backup_unique_code,$mod->modtype,$mod->id, $newid);
				    //Now check if want to restore user data and do it.
                	if (restore_userdata_selected($restore,'referentiel',$mod->id)) { 
						//Restore taches
	                    $status = referentiel_tasks_restore_mods($mod->id, $newid, $new_referentiel_referentiel_id, $info, $restore) && $status;
						//Restore activites
        	            $status = referentiel_activites_restore_mods($mod->id, $newid, $new_referentiel_referentiel_id, $info, $restore) && $status;
						
						//Restore certificats
                    	$status = referentiel_certificats_restore_mods($mod->id, $newid, $new_referentiel_referentiel_id, $info, $restore) && $status;
						
						//Restore etablissements
    	                $status = referentiel_etablissements_restore_mods($mod->id, $newid, $info, $restore) && $status;
        	        }
            	} 
				else {
                	$status = false;
            	}
        	} 
			else {
            	$status = false;
        	}
		}
		else {
            	$status = false;
        }
        return $status;
    }

// ###############################################  REFERENTIEL_REFRERENTIEL ####################################################	

	function referentiel_referentiel_restore_mods($info, $restore){
        global $CFG;
        global $DB;
		
        $new_referentiel_referentiel_id = 0;

        //Get the REFERENTIEL DATA - it might not be present
        if (isset($info['MOD']['#']['REFERENTIEL'])) {
            $sub_info = $info['MOD']['#']['REFERENTIEL'];
        } else {
            $sub_info = array();
        }
/*
CREATE TABLE mdl_referentiel_referentiel (
  id bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(80) NOT NULL DEFAULT '',
  code_referentiel varchar(20) NOT NULL DEFAULT '',
  mail_auteur_referentiel varchar(255) NOT NULL DEFAULT '',
  cle_referentiel varchar(255) NOT NULL DEFAULT '',
  pass_referentiel varchar(255) NOT NULL DEFAULT '',
  description_referentiel text NOT NULL,
  url_referentiel varchar(255) NOT NULL DEFAULT '',
  seuil_certificat smallint(3) NOT NULL DEFAULT '0',
  timemodified bigint(10) unsigned NOT NULL DEFAULT '0',
  nb_domaines tinyint(2) unsigned NOT NULL DEFAULT '0',
  liste_codes_competence text NOT NULL,
  liste_empreintes_competence text NOT NULL,
  `local` bigint(10) unsigned NOT NULL DEFAULT '0',
  logo_referentiel varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (id)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='Referentiel de competence';
*/
               
		// DEBUG
		// traverse_xmlize($sub_info);                                                                 //Debug
        // print_object ($GLOBALS['traverse_array']);                                                  //Debug
        // $GLOBALS['traverse_array']="";                                                              //Debug

        if ($sub_info){

            $old_referentiel_referentiel_id = backup_todb($sub_info[0]['#']['ID']['0']['#']);
        
		    //Now, build the referentiel_referentiel record structure
		    $referentiel->name = backup_todb($sub_info[0]['#']['NAME']['0']['#']);
		    $referentiel->code_referentiel = backup_todb($sub_info[0]['#']['CODE_REFERENTIEL']['0']['#']);
		    $referentiel->mail_auteur_referentiel = backup_todb($sub_info[0]['#']['MAIL_AUTEUR_REFERENTIEL']['0']['#']);
		    $referentiel->cle_referentiel = backup_todb($sub_info[0]['#']['CLE_REFERENTIEL']['0']['#']);
		    $referentiel->pass_referentiel = backup_todb($sub_info[0]['#']['PASS_REFERENTIEL']['0']['#']);
		    $referentiel->description_referentiel = backup_todb($sub_info[0]['#']['DESCRIPTION_REFERENTIEL']['0']['#']);
		    $referentiel->url_referentiel = backup_todb($sub_info[0]['#']['URL_REFERENTIEL']['0']['#']);
		    $referentiel->seuil_certificat = backup_todb($sub_info[0]['#']['SEUIL_CERTIFICAT']['0']['#']);
		    $referentiel->timemodified = backup_todb($sub_info[0]['#']['TIMEMODIFIED']['0']['#']);
		    $referentiel->nb_domaines = backup_todb($sub_info[0]['#']['NB_DOMAINES']['0']['#']);
		    $referentiel->liste_codes_competence = backup_todb($sub_info[0]['#']['LISTE_CODES_COMPETENCE']['0']['#']);
		    $referentiel->liste_empreintes_competence = backup_todb($sub_info[0]['#']['LISTE_EMPREINTES_COMPETENCE']['0']['#']);
		    $referentiel->local = backup_todb($sub_info[0]['#']['LOCAL']['0']['#']);
		    $referentiel->logo_referentiel = backup_todb($sub_info[0]['#']['LOGO_REFERENTIEL']['0']['#']);

		    // We have to see if that referentiel_referentiel exists in DB
		    $referentiel_referentiel_exists=NULL;
		    
		    if (!empty($referentiel->cle_referentiel)){
                $sql="SELECT * FROM {referentiel_referentiel}  WHERE cle_referentiel=:cle_referentiel ";

                $param=array("cle_referentiel" => "$referentiel->cle_referentiel");
                // DEBUG
                // echo '<br />DEBUG :: restorelib.php :: 195 :: SQL: '.$sql."\n";
		        $referentiel_referentiel_exists = $DB->get_record_sql($sql, $param);
		    }
		    else{     // comparer le code et l'auteur
                $param=array("code_referentiel" => "$referentiel->code_referentiel", "mail_auteur_referentiel" => "$referentiel->mail_auteur_referentiel");
                $sql="SELECT * FROM {referentiel_referentiel}  WHERE code_referentiel=:code_referentiel AND mail_auteur_referentiel=:mail_auteur_referentiel ";
                // DEBUG
                // echo '<br />DEBUG :: restorelib.php :: 195 :: SQL: '.$sql."\n";
		        $referentiel_referentiel_exists = $DB->get_record_sql($sql, $params);
            }

            if (!empty($referentiel_referentiel_exists)){
                return  $referentiel_referentiel_exists->id; // that's all
		    }
		    else{
                //The structure is equal to the db, so insert the referentiel_referentiel
                $new_referentiel_referentiel_id = $DB->insert_record ("referentiel_referentiel",$referentiel);
			
                if ($new_referentiel_referentiel_id) {
				    // domaines, competences, items
				    $status = referentiel_domaines_restore_mods($new_referentiel_referentiel_id, $info, $restore);
                    return $new_referentiel_referentiel_id;
                }
		    }
        }

        return 0;

    }
	
	
	
    //This function restores the domaines / competences / item
    function referentiel_domaines_restore_mods($new_referentiel_referentiel_id, $info, $restore){

        global $CFG;
        global $DB;
        $status = true;

        //Get the domains array - it might not be present
        if (isset($info['MOD']['#']['REFERENTIEL']['0']['#']['DOMAINES']['0']['#']['DOMAINE'])) {
            $domaines = $info['MOD']['#']['REFERENTIEL']['0']['#']['DOMAINES']['0']['#']['DOMAINE'];
        } else {
            $domaines = array();
        }
			/*
CREATE TABLE mdl_referentiel_domaine (
  id bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  code_domaine varchar(20) NOT NULL DEFAULT '',
  description_domaine text NOT NULL,
  ref_referentiel bigint(10) unsigned NOT NULL DEFAULT '0',
  num_domaine tinyint(2) unsigned NOT NULL DEFAULT '0',
  nb_competences tinyint(2) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (id)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='Domaine de competence';
			*/
        //Iterate over domaines
        for($i = 0; $i < sizeof($domaines); $i++) {
            $sub_info = $domaines[$i];
            
			// DEBUG
			// traverse_xmlize($sub_info);                                                                 //Debug
            // print_object ($GLOBALS['traverse_array']);                                                  //Debug
            // $GLOBALS['traverse_array']="";                                                              //Debug

            //We'll need this later!!
            $old_domaine_id = backup_todb($sub_info['#']['ID']['0']['#']);
			
            //Now, build the referentiel_domaine record structure
			$domaine->code_domaine = backup_todb($sub_info['#']['CODE_DOMAINE']['0']['#']);
			$domaine->description_domaine = backup_todb($sub_info['#']['DESCRIPTION_DOMAINE']['0']['#']);
			
			$domaine->ref_referentiel = backup_todb($sub_info['#']['REF_REFERENTIEL']['0']['#']);

			$domaine->num_domaine = backup_todb($sub_info['#']['NUM_DOMAINE']['0']['#']);
			$domaine->nb_competences = backup_todb($sub_info['#']['NB_COMPETENCES']['0']['#']);
			
            //We have to recode the ref_referentiel field 
			$domaine->ref_referentiel = $new_referentiel_referentiel_id;
			
            //The structure is equal to the db, so insert the referentiel_submission
            $new_domaine_id = $DB->insert_record ("referentiel_domaine",$domaine);
			
			if ($new_domaine_id) {
				// competences
				$status = referentiel_competences_restore_mods($new_domaine_id, $new_referentiel_referentiel_id, $sub_info, $restore);
                //We have the newid, update backup_ids
                backup_putid($restore->backup_unique_code,"referentiel_domaine", $old_domaine_id, $new_domaine_id);
            } else {
                $status = false;
            }
        }
        return $status;
    }

    //This function restores the competences 
    function referentiel_competences_restore_mods($new_domaine_id, $new_referentiel_referentiel_id, $info, $restore){

        global $CFG;
        global $DB;
        $status = true;

        //Get the domains array - it might not be present
        if (isset($info['#']['COMPETENCES']['0']['#']['COMPETENCE'])) {
            $competences = $info['#']['COMPETENCES']['0']['#']['COMPETENCE'];
        } else {
            $competences = array();
        }
/*
CREATE TABLE mdl_referentiel_competence (
  id bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  code_competence varchar(20) NOT NULL DEFAULT '',
  description_competence text NOT NULL,
  ref_domaine bigint(10) unsigned NOT NULL DEFAULT '0',
  num_competence tinyint(2) unsigned NOT NULL DEFAULT '0',
  nb_item_competences tinyint(2) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (id)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='Competence';

*/
        //Iterate over competences
        for($i = 0; $i < sizeof($competences); $i++) {
            $sub_info = $competences[$i];
            
			// DEBUG
			// traverse_xmlize($sub_info);                                                                 //Debug
            // print_object ($GLOBALS['traverse_array']);                                                  //Debug
            // $GLOBALS['traverse_array']="";                                                              //Debug

            //We'll need this later!!
            $old_competence_id = backup_todb($sub_info['#']['ID']['0']['#']);
			
            //Now, build the referentiel_domaine record structure
			$competence->code_competence = backup_todb($sub_info['#']['CODE_COMPETENCE']['0']['#']);
			$competence->description_competence = backup_todb($sub_info['#']['DESCRIPTION_COMPETENCE']['0']['#']);
			
			$competence->ref_domaine = backup_todb($sub_info['#']['REF_DOMAINE']['0']['#']);

			$competence->num_competence = backup_todb($sub_info['#']['NUM_COMPETENCE']['0']['#']);
			$competence->nb_item_competences = backup_todb($sub_info['#']['NB_ITEM_COMPETENCES']['0']['#']);
			
            //We have to recode the ref_referentiel field 
			$competence->ref_domaine = $new_domaine_id;
			
            //The structure is equal to the db, so insert the referentiel_competence
            $new_competence_id = $DB->insert_record ("referentiel_competence",$competence);
			
			if ($new_competence_id) {
				// competences
				$status = referentiel_items_restore_mods($new_competence_id, $new_referentiel_referentiel_id, $sub_info, $restore);
                //We have the newid, update backup_ids
                backup_putid($restore->backup_unique_code,"referentiel_competence", $old_competence_id, $new_competence_id);
            } else {
                $status = false;
            }
        }
        return $status;
    }

    //This function restores the items
    function referentiel_items_restore_mods($new_competence_id,$new_referentiel_referentiel_id, $info, $restore){

        global $CFG;
        global $DB;
        $status = true;
        //Get the competence array - it might not be present
        if (isset($info['#']['ITEMS']['0']['#']['ITEM'])) {
            $items = $info['#']['ITEMS']['0']['#']['ITEM'];
        } else {
            $items = array();
        }


/*

CREATE TABLE `mdl_referentiel_item_competence` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `code_item` varchar(20) NOT NULL DEFAULT '',
  `description_item` text NOT NULL,
  `ref_referentiel` bigint(10) unsigned NOT NULL DEFAULT '0',
  `ref_competence` bigint(10) unsigned NOT NULL DEFAULT '0',
  `type_item` varchar(20) NOT NULL DEFAULT '',
  `poids_item` smallint(3) NOT NULL DEFAULT '0',
  `empreinte_item` smallint(3) NOT NULL DEFAULT '1',
  `num_item` tinyint(2) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='Item de competence';

*/							
        //Iterate over competences
        for($i = 0; $i < sizeof($items); $i++) {
            $sub_info = $items[$i];
            
			// DEBUG
			// traverse_xmlize($sub_info);                                                                 //Debug
            // print_object ($GLOBALS['traverse_array']);                                                  //Debug
            // $GLOBALS['traverse_array']="";                                                              //Debug

            //We'll need this later!!
            $old_item_id = backup_todb($sub_info['#']['ID']['0']['#']);
			
            //Now, build the referentiel_domaine record structure
			$item->code_item = backup_todb($sub_info['#']['CODE_ITEM']['0']['#']);
			$item->description_item = backup_todb($sub_info['#']['DESCRIPTION_ITEM']['0']['#']);
			$item->ref_referentiel = backup_todb($sub_info['#']['REF_REFERENTIEL']['0']['#']);
			$item->ref_competence = backup_todb($sub_info['#']['REF_COMPETENCE']['0']['#']);
			$item->type_item = backup_todb($sub_info['#']['TYPE_ITEM']['0']['#']);
			$item->poids_item = backup_todb($sub_info['#']['POIDS_ITEM']['0']['#']);
			$item->empreinte_item = backup_todb($sub_info['#']['EMPREINTE_ITEM']['0']['#']);
			$item->num_item = backup_todb($sub_info['#']['NUM_ITEM']['0']['#']);
						
            //We have to recode the ref_referentiel and ref_competence fields
			$item->ref_referentiel = $new_referentiel_referentiel_id; 
			$item->ref_competence = $new_competence_id;
			
            //The structure is equal to the db, so insert the referentiel_competence
            $new_item_id = $DB->insert_record ("referentiel_item_competence",$item);
			
			if ($new_item_id) {
                //We have the newid, update backup_ids
                backup_putid($restore->backup_unique_code,"referentiel_item_competence", $old_item_id, $new_item_id);
            } else {
                $status = false;
            }
        }
        return $status;
    }

// ###############################################  TACHES ####################################################


    //This function restores the referentiel_activite
    function referentiel_tasks_restore_mods($old_referentiel_id, $new_referentiel_id, $new_referentiel_referentiel_id, $info, $restore) {

        global $CFG;
        global $DB;
		global $referentiel_ids;

        $status = true;

        //Get the activites array - it might not be present
        if (isset($info['MOD']['#']['TASKS']['0']['#']['TASK'])) {
            $tasks = $info['MOD']['#']['TASKS']['0']['#']['TASK'];
        } else {
            $tasks = array();
        }
/*
CREATE TABLE mdl_referentiel_task (
  id bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  type_task varchar(80) NOT NULL DEFAULT '',
  description_task text NOT NULL,
  competences_task text NOT NULL,
  criteres_evaluation text NOT NULL,
  ref_instance bigint(10) unsigned NOT NULL DEFAULT '0',
  ref_referentiel bigint(10) unsigned NOT NULL DEFAULT '0',
  ref_course bigint(10) unsigned NOT NULL DEFAULT '0',
  auteurid bigint(10) unsigned NOT NULL,
  date_creation bigint(10) unsigned NOT NULL DEFAULT '0',
  date_modif bigint(10) unsigned NOT NULL DEFAULT '0',
  date_debut bigint(10) unsigned NOT NULL DEFAULT '0',
  date_fin bigint(10) unsigned NOT NULL DEFAULT '0',
`cle_souscription` varchar(255) NOT NULL DEFAULT '',
  `souscription_libre` int(4) NOT NULL DEFAULT '1',  
  PRIMARY KEY (id)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='task';

*/
        //Iterate over activites
        for($i = 0; $i < sizeof($tasks); $i++) {
            $sub_info = $tasks[$i];
            
			// DEBUG
			// traverse_xmlize($sub_info);                                                                 //Debug
            // print_object ($GLOBALS['traverse_array']);                                                  //Debug
            // $GLOBALS['traverse_array']="";                                                              //Debug

            //We'll need this later!!
            $old_task_id = backup_todb($sub_info['#']['ID']['0']['#']);
            $oldauteurid = backup_todb($sub_info['#']['AUTEURID']['0']['#']);
			
            //Now, build the referentiel_task record structure
            $task->ref_instance = $new_referentiel_id;
            $task->ref_course = $restore->course_id;
			$task->type_task = backup_todb($sub_info['#']['TYPE_TASK']['0']['#']);
			$task->description_task = backup_todb($sub_info['#']['DESCRIPTION_TASK']['0']['#']);
			$task->competences_task = backup_todb($sub_info['#']['COMPETENCES_TASK']['0']['#']);
		    $task->criteres_evaluation = backup_todb($sub_info['#']['CRITERES_EVALUATION']['0']['#']);
			$task->ref_referentiel = backup_todb($sub_info['#']['REF_REFERENTIEL']['0']['#']);
            $task->auteurid = backup_todb($sub_info['#']['AUTEURID']['0']['#']);
            $task->date_creation = backup_todb($sub_info['#']['DATE_CREATION']['0']['#']);
		    $task->date_modif = backup_todb($sub_info['#']['DATE_MODIF']['0']['#']);
			$task->date_debut = backup_todb($sub_info['#']['DATE_DEBUT']['0']['#']);
			$task->date_fin = backup_todb($sub_info['#']['DATE_FIN']['0']['#']);
			$task->cle_souscription = backup_todb($sub_info['#']['CLE_SOUSCRIPTION']['0']['#']);
			$task->souscription_libre = backup_todb($sub_info['#']['SOUSCRIPTION_LIBRE']['0']['#']);
			
			//We have to recode the ref_referentiel field
			$task->ref_referentiel = $new_referentiel_referentiel_id;

            //We have to recode the auteurid field
            $user = backup_getid($restore->backup_unique_code,"user",$task->auteurid);
            if ($user) {
                $task->auteurid = $user->new_id;
				// stocker
            	$referentiel_ids->referentiel_userid[$oldauteurid]= $user->new_id;
			}

            //We have to recode the referentiel_referentiel field
            $user = backup_getid($restore->backup_unique_code,"user",$task->auteurid);
            if ($user) {
                $task->auteurid = $user->new_id;
				        // stocker
            	 $referentiel_ids->referentiel_userid[$oldauteurid]= $user->new_id;
			}

            //The structure is equal to the db, so insert the referentiel_submission
            $new_task_id = $DB->insert_record ("referentiel_task",$task);
			// stocker
			$referentiel_ids->referentiel_task[$old_task_id]=$new_task_id;
			
			
            //Do some output
            if (($i+1) % 50 == 0) {
                if (!defined('RESTORE_SILENTLY')) {
                    echo ".";
                    if (($i+1) % 1000 == 0) {
                        echo "<br />";
                    }
                }
                backup_flush(300);
            }

			if ($new_task_id) {
				// documents
                $status_document = referentiel_consignes_restore_mods($new_task_id, $info, $restore);
			}
			
            if ($new_task_id) {
                //We have the newid, update backup_ids
                backup_putid($restore->backup_unique_code,"referentiel_task", $old_task_id, $new_task_id);

                //Now copy moddata associated files
                $status = referentiel_restore_files ($old_task_id, $new_task_id, $oldauteurid, $task->auteurid, $restore);
            } else {
                $status = false;
            }
        }

        return $status;
    }

    //This function restores the referentiel_consigne
    function referentiel_consignes_restore_mods($new_task_id, $info, $restore) {

        global $CFG;
        global $DB;

        $status = true;

        //Get the consignes array - it might not be present
        if (isset($info['MOD']['#']['TASKS']['0']['#']['TASK']['0']['#']['CONSIGNES']['0']['#']['CONSIGNE'])) {
            $consignes = $info['MOD']['#']['TASKS']['0']['#']['TASK']['0']['#']['CONSIGNES']['0']['#']['CONSIGNE'];
        } else {
            $consignes = array();
        }
/*
				// Documents consignes associes
				/*
CREATE TABLE mdl_referentiel_consigne (
  id bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  type_consigne varchar(20) NOT NULL DEFAULT '',
  description_consigne text NOT NULL,
  url_consigne varchar(255) NOT NULL DEFAULT '',
  ref_task bigint(10) unsigned NOT NULL,
  PRIMARY KEY (id)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='consigne';
				*/
		//Iterate over documents
        for($i = 0; $i < sizeof($consignes); $i++) {
            $sub_info = $consignes[$i];
            
			// DEBUG
			// traverse_xmlize($sub_info);                                                                 //Debug
            // print_object ($GLOBALS['traverse_array']);                                                  //Debug
            // $GLOBALS['traverse_array']="";                                                              //Debug

            //We'll need this later!!
            $old_consigne_id = backup_todb($sub_info['#']['ID']['0']['#']);

            //Now, build the referentiel_activite record structure
			
			$consigne->type_consigne = backup_todb($sub_info['#']['TYPE_CONSIGNE']['0']['#']);
			$consigne->description_consigne = backup_todb($sub_info['#']['DESCRIPTION_CONSIGNE']['0']['#']);
			$consigne->url_consigne = backup_todb($sub_info['#']['URL_CONSIGNE']['0']['#']);
			$consigne->ref_task = backup_todb($sub_info['#']['REF_TASK']['0']['#']);
			// Mise a jour
			$consigne->ref_task = $new_task_id;
			
            //The structure is equal to the db, so insert the referentiel_submission
            $new_consigne_id = $DB->insert_record ("referentiel_consigne", $consigne);

            //Do some output
            if (($i+1) % 50 == 0) {
                if (!defined('RESTORE_SILENTLY')) {
                    echo ".";
                    if (($i+1) % 1000 == 0) {
                        echo "<br />";
                    }
                }
                backup_flush(300);
            }

            if ($new_consigne_id) {
                //We have the newid, update backup_ids
                backup_putid($restore->backup_unique_code,"referentiel_consigne",$old_consigne_id, $new_consigne_id);
				// les fichers sont associes a l'utilisateur et pas au document
            } else {
                $status = false;
            }
        }

        return $status;
    }

    //This function restores the referentiel_submissions
    function referentiel_a_user_task_restore_mods($new_task_id, $info, $restore) {
	// n'est pas utilise dans cette appli
        global $CFG;
        global $DB;

        $status = true;

        //Get the consignes array - it might not be present
        if (isset($info['MOD']['#']['TASKS']['0']['#']['TASK']['0']['#']['USERS_TASKS']['0']['#']['USER_TASK'])) {
            $a_users_tasks = $info['MOD']['#']['TASKS']['0']['#']['TASK']['0']['#']['USERS_TASKS']['0']['#']['USER_TASK'];
        } else {
            $a_users_tasks = array();
        }
/*

CREATE TABLE mdl_referentiel_a_user_task (
  id bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  ref_user bigint(10) unsigned NOT NULL,
  ref_task bigint(10) unsigned NOT NULL,
  date_selection bigint(10) unsigned NOT NULL DEFAULT '0',
  ref_activite bigint(10) unsigned NOT NULL,
  PRIMARY KEY (id)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='user_select_task';

*/
				        //Iterate over documents
        for($i = 0; $i < sizeof($a_users_tasks); $i++) {
            $sub_info = $a_users_tasks[$i];
            
			// DEBUG
			// traverse_xmlize($sub_info);                                                                 //Debug
            // print_object ($GLOBALS['traverse_array']);                                                  //Debug
            // $GLOBALS['traverse_array']="";                                                              //Debug

            //We'll need this later!!
            $old_a_user_task_id = backup_todb($sub_info['#']['ID']['0']['#']);
			$olduserid = backup_todb($sub_info['#']['REF_USER']['0']['#']);
			
            //Now, build the referentiel_activite record structure
			
			$a_user_task->ref_user = backup_todb($sub_info['#']['REF_USER']['0']['#']);
			$a_user_task->ref_task = backup_todb($sub_info['#']['REF_TASK']['0']['#']);
			$a_user_task->date_selection = backup_todb($sub_info['#']['DATE_SELECTION']['0']['#']);
			$a_user_task->ref_activite = backup_todb($sub_info['#']['REF_ACTIVITE']['0']['#']);
			
			// mise a jour
			$a_user_task->ref_task = $new_task_id;
            //We have to recode the userid field
            $user = backup_getid($restore->backup_unique_code,"user",$a_user_task->ref_user);
            if ($user) {
                $a_user_task->ref_user = $user->new_id;
				// stocker
            	$referentiel_ids->referentiel_userid[$olduserid]= $user->new_id;
			}
			// mise a jour de l'id d'activite reporté après lecture des activites
			
            //The structure is equal to the db, so insert the referentiel_submission
            $new_a_user_task_id = $DB->insert_record ("referentiel_a_user_task", $a_user_task);

            //Do some output
            if (($i+1) % 50 == 0) {
                if (!defined('RESTORE_SILENTLY')) {
                    echo ".";
                    if (($i+1) % 1000 == 0) {
                        echo "<br />";
                    }
                }
                backup_flush(300);
            }

            if ($new_a_user_task_id) {
                //We have the newid, update backup_ids
                backup_putid($restore->backup_unique_code,"referentiel_a_user_task",$old_a_user_task_id, $new_a_user_task_id);
				// les fichers sont associes a l'utilisateur et pas au document
            } else {
                $status = false;
            }
        }

        return $status;
    }
	
// ###############################################  ACTIVITES ####################################################	

    //This function restores the referentiel_activite
    function referentiel_activites_restore_mods($old_referentiel_id, $new_referentiel_id, $new_referentiel_referentiel_id, $info, $restore) {

        global $CFG;
        global $DB;
		global $referentiel_ids;

        $status = true;

        //Get the activites array - it might not be present
        if (isset($info['MOD']['#']['ACTIVITES']['0']['#']['ACTIVITE'])) {
            $activites = $info['MOD']['#']['ACTIVITES']['0']['#']['ACTIVITE'];
        } else {
            $activites = array();
        }
/*
CREATE TABLE mdl_referentiel_activite (
  id bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  type_activite varchar(80) NOT NULL DEFAULT '',
  description_activite text NOT NULL,
  competences_activite text NOT NULL,
  commentaire_activite text NOT NULL,
  ref_instance bigint(10) unsigned NOT NULL DEFAULT '0',
  ref_referentiel bigint(10) unsigned NOT NULL DEFAULT '0',
  ref_course bigint(10) unsigned NOT NULL DEFAULT '0',
  userid bigint(10) unsigned NOT NULL,
  teacherid bigint(10) unsigned NOT NULL,
  date_creation bigint(10) unsigned NOT NULL DEFAULT '0',
  date_modif_student bigint(10) unsigned NOT NULL DEFAULT '0',
  date_modif bigint(10) unsigned NOT NULL DEFAULT '0',
  approved smallint(4) unsigned NOT NULL DEFAULT '0',
  ref_task bigint(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (id)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='Activite';
*/
        //Iterate over activites
        for($i = 0; $i < sizeof($activites); $i++) {
            $sub_info = $activites[$i];
            
			// DEBUG
			// traverse_xmlize($sub_info);                                                                 //Debug
            // print_object ($GLOBALS['traverse_array']);                                                  //Debug
            // $GLOBALS['traverse_array']="";                                                              //Debug

            //We'll need this later!!
            $old_activite_id = backup_todb($sub_info['#']['ID']['0']['#']);
            $olduserid = backup_todb($sub_info['#']['USERID']['0']['#']);
            $oldteacherid = backup_todb($sub_info['#']['TEACHERID']['0']['#']);
			
            //Now, build the referentiel_activite record structure
            $activite->ref_instance = $new_referentiel_id;
			$activite->ref_course = $restore->course_id;
			$activite->type_activite = backup_todb($sub_info['#']['TYPE_ACTIVITE']['0']['#']);
			$activite->description_activite = backup_todb($sub_info['#']['DESCRIPTION_ACTIVITE']['0']['#']);
			$activite->competences_activite = backup_todb($sub_info['#']['COMPETENCES_ACTIVITE']['0']['#']);
			$activite->commentaire_activite = backup_todb($sub_info['#']['COMMENTAIRE_ACTIVITE']['0']['#']);
			
			$activite->ref_referentiel = backup_todb($sub_info['#']['REF_REFERENTIEL']['0']['#']);
			
			$activite->userid = backup_todb($sub_info['#']['USERID']['0']['#']);
			$activite->teacherid = backup_todb($sub_info['#']['TEACHERID']['0']['#']);
			$activite->date_creation = backup_todb($sub_info['#']['DATE_CREATION']['0']['#']);
			$activite->date_modif = backup_todb($sub_info['#']['DATE_MODIF']['0']['#']);
			$activite->approved = backup_todb($sub_info['#']['APPROVED']['0']['#']);
			$activite->ref_task = backup_todb($sub_info['#']['REF_TASK']['0']['#']);
			$activite->date_modif_student = backup_todb($sub_info['#']['DATE_MODIF_STUDENT']['0']['#']);
			//We have to recode the ref_referentiel field 
			$activite->ref_referentiel = $new_referentiel_referentiel_id;
			
            //We have to recode the userid field
            $user = backup_getid($restore->backup_unique_code,"user",$activite->userid);
            if ($user) {
                $activite->userid = $user->new_id;
				// stocker
            	$referentiel_ids->referentiel_userid[$olduserid]= $user->new_id;
			}

            //We have to recode the teacher field
            $teacher = backup_getid($restore->backup_unique_code,"user",$activite->teacherid);
            if ($teacher) {
                $activite->teacherid = $teacher->new_id;
				// stocker
            	$referentiel_ids->referentiel_teacherid[$oldteacherid]= $teacher->new_id;
			} 

			// Have we to recode task ref ?
			if (($activite->ref_task != 0) && isset($referentiel_ids->referentiel_task[$activite->ref_task]) && ($referentiel_ids->referentiel_task[$activite->ref_task]>0)){ 
				$activite->ref_task = $referentiel_ids->referentiel_task[$activite->ref_task];
				// Penser à mettre a jour la table referentiel_a_user_task 
			}
			
            //The structure is equal to the db, so insert the referentiel_submission
            $new_activite_id = $DB->insert_record ("referentiel_activite",$activite);

			if ($new_activite_id){ 
				// Taches ?
				if ($activite->ref_task>0){
					// mettre a jour la table referentiel_a_user_task plus bas
					$status_a_task=referentiel_a_activite_task_restore_mods($new_activite_id, $activite->ref_task, $activite->userid, $info, $restore);
				}
				
				// documents
				$status_document = referentiel_documents_restore_mods($new_activite_id, $info, $restore);
			}
			
            if ($new_activite_id) {
                //We have the newid, update backup_ids
                backup_putid($restore->backup_unique_code,"referentiel_activite", $old_activite_id, $new_activite_id);

                //Now copy moddata associated files
                $status = referentiel_restore_files ($old_activite_id, $new_activite_id, $olduserid, $activite->userid, $restore);
            } else {
                $status = false;
            }

            //Do some output
            if (($i+1) % 50 == 0) {
                if (!defined('RESTORE_SILENTLY')) {
                    echo ".";
                    if (($i+1) % 1000 == 0) {
                        echo "<br />";
                    }
                }
                backup_flush(300);
            }
        }

        return $status;
    }
	
	
	    //This function restores the referentiel_submissions
    function referentiel_a_activite_task_restore_mods($new_activite_id, $new_task_id, $new_user_id, $info, $restore) {
	// retablit l'association entre l'activite et la tache
        global $CFG;
        global $DB;

        $status = true;

        //Get the user_task array - it might not be present
        if (isset($info['MOD']['#']['ACTIVITES']['0']['#']['ACTIVITE']['0']['#']['USERS_TASKS']['0']['#']['USER_TASK'])) {
            $a_users_tasks = $info['MOD']['#']['ACTIVITES']['0']['#']['ACTIVITE']['0']['#']['USERS_TASKS']['0']['#']['USER_TASK'];
        } else {
            $a_users_tasks = array();
        }
/*

CREATE TABLE mdl_referentiel_a_user_task (
  id bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  ref_user bigint(10) unsigned NOT NULL,
  ref_task bigint(10) unsigned NOT NULL,
  date_selection bigint(10) unsigned NOT NULL DEFAULT '0',
  ref_activite bigint(10) unsigned NOT NULL,
  PRIMARY KEY (id)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='user_select_task';

*/
        //Iterate over documents
        for($i = 0; $i < sizeof($a_users_tasks); $i++) {
            $sub_info = $a_users_tasks[$i];
			
	        // DEBUG
			traverse_xmlize($sub_info);                                                                 //Debug
        	print_object ($GLOBALS['traverse_array']);                                                  //Debug
	        $GLOBALS['traverse_array']="";                                                              //Debug
	
			//We'll need this later!!
        	$old_a_user_task_id = backup_todb($sub_info['#']['ID']['0']['#']);
			$olduserid = backup_todb($sub_info['#']['REF_USER']['0']['#']);
			$oldtaskid = backup_todb($sub_info['#']['REF_TASK']['0']['#']);
			$oldactiviteid = backup_todb($sub_info['#']['REF_ACTIVITE']['0']['#']);
			//Now, build the referentiel_activite record structure
			$a_user_task->ref_user = backup_todb($sub_info['#']['REF_USER']['0']['#']);
			$a_user_task->ref_task = backup_todb($sub_info['#']['REF_TASK']['0']['#']);
			$a_user_task->date_selection = backup_todb($sub_info['#']['DATE_SELECTION']['0']['#']);
			$a_user_task->ref_activite = backup_todb($sub_info['#']['REF_ACTIVITE']['0']['#']);
			// mise a jour
			$a_user_task->ref_task = $new_task_id;
			$a_user_task->ref_activite = $new_activite_id;
			$a_user_task->ref_user = $new_user_id;
		/*
        //We have to recode the userid field
        $user = backup_getid($restore->backup_unique_code,"user",$a_user_task->ref_user);
        if ($user) {
        	$a_user_task->ref_user = $user->new_id;
			// stocker
            $referentiel_ids->referentiel_userid[$olduserid]= $user->new_id;
		}
		*/
			//The structure is equal to the db, so insert the referentiel_submission
    	    $new_a_user_task_id = $DB->insert_record ("referentiel_a_user_task", $a_user_task);
			
	        if ($new_a_user_task_id) {
    	    	//We have the newid, update backup_ids
        	    backup_putid($restore->backup_unique_code,"referentiel_a_user_task",$old_a_user_task_id, $new_a_user_task_id);
			} 
			else {
        		$status = false;
			}
		}
        return $status;
    }
	


    //This function restores the referentiel_submissions
    function referentiel_documents_restore_mods($new_activite_id, $info, $restore) {

        global $CFG;
        global $DB;

        $status = true;

        //Get the activites array - it might not be present
        if (isset($info['MOD']['#']['ACTIVITES']['0']['#']['ACTIVITE']['0']['#']['DOCUMENTS']['0']['#']['DOCUMENT'])) {
            $documents = $info['MOD']['#']['ACTIVITES']['0']['#']['ACTIVITE']['0']['#']['DOCUMENTS']['0']['#']['DOCUMENT'];
        } else {
            $documents = array();
        }
/*
				// Documents associes
				
CREATE TABLE mdl_referentiel_document (
  id bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  type_document varchar(20) NOT NULL DEFAULT '',
  description_document text NOT NULL,
  url_document varchar(255) NOT NULL DEFAULT '',
  ref_activite bigint(10) unsigned NOT NULL,
  PRIMARY KEY (id)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='Document';
                		fwrite ($bf,full_tag("ID",8,false,$document->id));       
                		fwrite ($bf,full_tag("TYPE_DOCUMENT",8,false,$document->type_document));
						fwrite ($bf,full_tag("DESCRIPTION_DOCUMENT",8,false,$document->description_document));
						fwrite ($bf,full_tag("URL_DOCUMENT",8,false,$document->url_document));
						fwrite ($bf,full_tag("REF_ACTIVITE",8,false,$document->ref_activite));

				
*/
        //Iterate over documents
        for($i = 0; $i < sizeof($documents); $i++) {
            $sub_info = $documents[$i];
            
			// DEBUG
			// traverse_xmlize($sub_info);                                                                 //Debug
            // print_object ($GLOBALS['traverse_array']);                                                  //Debug
            // $GLOBALS['traverse_array']="";                                                              //Debug

            //We'll need this later!!
            $old_document_id = backup_todb($sub_info['#']['ID']['0']['#']);

            //Now, build the referentiel_activite record structure
			
			$document->type_activite = backup_todb($sub_info['#']['TYPE_DOCUMENT']['0']['#']);
			$document->description_document = backup_todb($sub_info['#']['DESCRIPTION_DOCUMENT']['0']['#']);
			$document->url_document = backup_todb($sub_info['#']['URL_DOCUMENT']['0']['#']);
			$document->ref_activite = backup_todb($sub_info['#']['REF_ACTIVITE']['0']['#']);
			
			// mise a jour
			$document->ref_activite = $new_activite_id;
            //The structure is equal to the db, so insert the referentiel_submission
            $new_document_id = $DB->insert_record ("referentiel_document", $document);

            //Do some output
            if (($i+1) % 50 == 0) {
                if (!defined('RESTORE_SILENTLY')) {
                    echo ".";
                    if (($i+1) % 1000 == 0) {
                        echo "<br />";
                    }
                }
                backup_flush(300);
            }

            if ($new_document_id) {
                //We have the newid, update backup_ids
                backup_putid($restore->backup_unique_code,"referentiel_document",$old_document_id, $new_document_id);
				// les fichers sont associes a l'utilisateur et pas au document
            } else {
                $status = false;
            }
        }

        return $status;
    }
	
	

// ###############################################  CERTIFICATS ####################################################


    //This function restores the referentiel_activite
    function referentiel_certificats_restore_mods($old_referentiel_id, $new_referentiel_id, $new_referentiel_referentiel_id, $info, $restore) {

        global $CFG;
        global $DB;
		global $referentiel_ids;

        $status = true;

        //Get the activites array - it might not be present
        if (isset($info['MOD']['#']['CERTIFICATS']['0']['#']['CERTIFICAT'])) {
            $certificats = $info['MOD']['#']['CERTIFICATS']['0']['#']['CERTIFICAT'];
        } else {
            $certificats = array();
        }
/*
CREATE TABLE mdl_referentiel_certificat (
  id bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  commentaire_certificat text NOT NULL,
  competences_certificat text NOT NULL,
  decision_jury varchar(80) NOT NULL DEFAULT '',
  date_decision bigint(10) unsigned NOT NULL DEFAULT '0',
  ref_referentiel bigint(10) unsigned NOT NULL DEFAULT '0',
  userid bigint(10) unsigned NOT NULL,
  teacherid bigint(10) unsigned NOT NULL,
  verrou tinyint(1) unsigned NOT NULL,
  valide tinyint(1) unsigned NOT NULL,
  evaluation bigint(10) unsigned NOT NULL,
  PRIMARY KEY (id)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='Certificat';
*/
        //Iterate over certificats
        for($i = 0; $i < sizeof($certificats); $i++) {
            $sub_info = $certificats[$i];
            
			// DEBUG
			// traverse_xmlize($sub_info);                                                                 //Debug
            // print_object ($GLOBALS['traverse_array']);                                                  //Debug
            // $GLOBALS['traverse_array']="";                                                              //Debug

            //We'll need this later!!
            $old_certificat_id = backup_todb($sub_info['#']['ID']['0']['#']);
            $olduserid = backup_todb($sub_info['#']['USERID']['0']['#']);
            $oldteacherid = backup_todb($sub_info['#']['TEACHERID']['0']['#']);
						
            //Now, build the referentiel_task record structure
			$certificat->commentaire_certificat = backup_todb($sub_info['#']['COMMENTAIRE_CERTIFICAT']['0']['#']);
			$certificat->competences_certificat = backup_todb($sub_info['#']['COMPETENCES_CERTIFICAT']['0']['#']);
			$certificat->decision_jury = backup_todb($sub_info['#']['DECISION_JURY']['0']['#']);
			$certificat->date_decision = backup_todb($sub_info['#']['DATE_DECISION']['0']['#']);
			$certificat->ref_referentiel = backup_todb($sub_info['#']['REF_REFERENTIEL']['0']['#']);
            $certificat->userid = backup_todb($sub_info['#']['USERID']['0']['#']);
            $certificat->teacherid = backup_todb($sub_info['#']['TEACHERID']['0']['#']);
			$certificat->verrou = backup_todb($sub_info['#']['VERROU']['0']['#']);
            $certificat->valide = backup_todb($sub_info['#']['VALIDE']['0']['#']);
            $certificat->evaluation = backup_todb($sub_info['#']['EVALUATION']['0']['#']);
            $certificat->competences_certificat = backup_todb($sub_info['#']['SYNTHESE_CERTIFICAT']['0']['#']);
			$certificat->synthese_certificat = backup_todb($sub_info['#']['SYNTHESE_CERTIFICAT']['0']['#']);

			//We have to recode the ref_referentiel field 
			$certificat->ref_referentiel = $new_referentiel_referentiel_id;

            //We have to recode the userid field
            $user = backup_getid($restore->backup_unique_code,"user",$certificat->userid);
            if ($user) {
                $certificat->userid = $user->new_id;
				// stocker
            	$referentiel_ids->referentiel_userid[$olduserid]= $user->new_id;
			}
            //We have to recode the teacherid field
            $teacher = backup_getid($restore->backup_unique_code,"user",$certificat->teacherid);
            if ($teacher) {
                $certificat->teacherid = $teacher->new_id;
				// stocker
            	$referentiel_ids->referentiel_teacherid[$oldteacherid]= $teacher->new_id;
			}

			// Verifier si le certificat existe pour cet utilisateur dans la base de donnees
			
			
			$sql="SELECT * FROM {referentiel_certificat}  WHERE ref_referentiel=:ref_referentiel AND userid=:userid ";
			// DEBUG
			// echo '<br />DEBUG :: restorelib.php :: 1084 :: SQL: '.$sql."\n";
			$certificat_exists = $DB->get_record_sql($sql, array("ref_referentiel"=>"$certificat->ref_referentiel", "userid"=>"userid");
			
			if (!$certificat_exists){
            	//The structure is equal to the db, so insert the referentiel_submission
            	$new_certificat_id = $DB->insert_record ("referentiel_certificat", $certificat);
				
	            if ($new_certificat_id) {
        	        //We have the newid, update backup_ids
            	    backup_putid($restore->backup_unique_code,"referentiel_certificat", $old_certificat_id, $new_certificat_id);
	            } 
				else {
                	$status = false;
            	}
        	}
		}

        return $status;
    }

	
	

// ###############################################  ETABLISSEMENTS ####################################################


    //This function restores the referentiel_etablissement
    function referentiel_etablissements_restore_mods($mod, $newid, $info, $restore) {

        global $CFG;
        global $DB;
		global $referentiel_ids;

        $status = true;

        //Get the etablissement array - it might not be present
        if (isset($info['MOD']['#']['ETABLISSEMENTS']['0']['#']['ETABLISSEMENT'])) {
            $etablissements = $info['MOD']['#']['ETABLISSEMENTS']['0']['#']['ETABLISSEMENT'];
        } else {
            $etablissements = array();
        }
/*
CREATE TABLE mdl_referentiel_etablissement (
  id bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  num_etablissement varchar(20) NOT NULL DEFAULT 'INCONNU',
  nom_etablissement varchar(80) NOT NULL DEFAULT 'A COMPLETER',
  adresse_etablissement varchar(255) NOT NULL DEFAULT 'A COMPLETER',
  logo_etablissement text NOT NULL,
  PRIMARY KEY (id)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='Etablissement';
*/
        //Iterate over etablissements
        for ($i = 0; $i < sizeof($etablissements); $i++) {
            $sub_info = $etablissements[$i];
            
			// DEBUG
			// traverse_xmlize($sub_info);                                                                 //Debug
            // print_object ($GLOBALS['traverse_array']);                                                  //Debug
            // $GLOBALS['traverse_array']="";                                                              //Debug

            //We'll need this later!!
            $old_etablissement_id = backup_todb($sub_info['#']['ID']['0']['#']);
						
            //Now, build the referentiel_task record structure
			$etablissement->num_etablissement = backup_todb($sub_info['#']['NUM_ETABLISSEMENT']['0']['#']);
			$etablissement->nom_etablissement = backup_todb($sub_info['#']['NOM_ETABLISSEMENT']['0']['#']);
			$etablissement->adresse_etablissement = backup_todb($sub_info['#']['ADRESSE_ETABLISSEMENT']['0']['#']);
			$etablissement->logo_etablissement = backup_todb($sub_info['#']['LOGO_ETABLISSEMENT']['0']['#']);

			// Verifier si l'etablissement existe pour dans la base de donnees
			$sql="SELECT * FROM {referentiel_etablissement}  WHERE num_etablissement=:num_etablissement ";
			// DEBUG
			// echo '<br />DEBUG :: restorelib.php :: 1084 :: SQL: '.$sql."\n";
			$etablissement_exists = $DB->get_record_sql($sql, array("num_etablissement"=>"$etablissement->num_etablissement"));
			
			if (!$etablissement_exists){
            	//The structure is equal to the db, so insert the referentiel_submission
            	$new_etablissement_id = $DB->insert_record ("referentiel_etablissement", $etablissement);
				
	            if ($new_etablissement_id) {
        	        //We have the newid, update backup_ids
            	    backup_putid($restore->backup_unique_code,"referentiel_etablissement", $old_etablissement_id, $new_etablissement_id);
					
					// etudiants
					$status = referentiel_etudiants_restore_mods($new_etablissement_id, $info, $restore);
	            } 
				else {
                	$status = false;
            	}
        	}
			else{
				// etudiants
				$status = referentiel_etudiants_restore_mods($etablissement_exists->id, $info, $restore);
			}
		}

        return $status;
    }

    //This function restores the referentiel_etudiant
    function referentiel_etudiants_restore_mods($etablissement_id, $info, $restore) {

        global $CFG;
        global $DB;
		global $referentiel_ids;

        $status = true;

        //Get the etudiant array - it might not be present
        if (isset($info['MOD']['#']['ETABLISSEMENTS']['0']['#']['ETABLISSEMENT']['0']['#']['ETUDIANTS']['0']['#']['ETUDIANT'])) {
            $etudiants = $info['MOD']['#']['ETABLISSEMENTS']['0']['#']['ETABLISSEMENT']['0']['#']['ETUDIANTS']['0']['#']['ETUDIANT'];
        } else {
            $etudiants = array();
        }
/*
CREATE TABLE mdl_referentiel_etudiant (
  id bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  num_etudiant varchar(20) NOT NULL DEFAULT '',
  ddn_etudiant varchar(14) NOT NULL DEFAULT '',
  lieu_naissance varchar(255) NOT NULL DEFAULT '',
  departement_naissance varchar(255) NOT NULL DEFAULT '',
  adresse_etudiant varchar(255) NOT NULL DEFAULT '',
  userid bigint(10) unsigned NOT NULL,
  ref_etablissement bigint(10) unsigned NOT NULL,
  PRIMARY KEY (id)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='Fiche Etudiant';
*/				
        //Iterate over etudiants
        for ($i = 0; $i < sizeof($etudiants); $i++) {
            $sub_info = $etudiants[$i];
            
			// DEBUG
			// traverse_xmlize($sub_info);                                                                 //Debug
            // print_object ($GLOBALS['traverse_array']);                                                  //Debug
            // $GLOBALS['traverse_array']="";                                                              //Debug

            //We'll need this later!!
            $old_etudiant_id = backup_todb($sub_info['#']['ID']['0']['#']);
			$olduserid = backup_todb($sub_info['#']['USERID']['0']['#']);
						
            //Now, build the referentiel_task record structure
			$etudiant->num_etudiant = backup_todb($sub_info['#']['NUM_ETUDIANT']['0']['#']);
			$etudiant->ddn_etudiant = backup_todb($sub_info['#']['DDN_ETUDIANT']['0']['#']);
			$etudiant->lieu_naissance = backup_todb($sub_info['#']['LIEU_NAISSANCE']['0']['#']);
			$etudiant->departement_naissance = backup_todb($sub_info['#']['DEPARTEMENT_NAISSANCE']['0']['#']);
			$etudiant->adresse_etudiant = backup_todb($sub_info['#']['ADRESSE_ETUDIANT']['0']['#']);
			$etudiant->userid = backup_todb($sub_info['#']['USERID']['0']['#']);
			$etudiant->ref_etablissement = backup_todb($sub_info['#']['REF_ETABLISSEMENT']['0']['#']);
			
			
			// mise a jour
			$etudiant->ref_etablissement = $etablissement_id;
			
			            //We have to recode the userid field
            $user = backup_getid($restore->backup_unique_code,"user",$etudiant->userid);
            if ($user) {
                $etudiant->userid = $user->new_id;
				// stocker
            	$referentiel_ids->referentiel_userid[$olduserid]= $user->new_id;
			}

			// Verifier si l'etudiant existe dans la base de donnees
			$sql="SELECT * FROM {referentiel_etudiant}  WHERE num_etudiant=:num_etudiant ";
			// DEBUG
			// echo '<br />DEBUG :: restorelib.php :: 1084 :: SQL: '.$sql."\n";
			$etudiant_exists = $DB->get_record_sql($sql, array("num_etudiant"=>"$etudiant->num_etudiant" ));
			
			if (!$etudiant_exists){
            	//The structure is equal to the db, so insert the referentiel_submission
            	$new_etudiant_id = $DB->insert_record ("referentiel_etudiant", $etudiant);
				
	            if ($new_etudiant_id) {
        	        //We have the newid, update backup_ids
            	    backup_putid($restore->backup_unique_code,"referentiel_etudiant", $old_etudiant_id, $new_etudiant_id);
	            } 
				else {
                	$status = false;
            	}
        	}
		}

        return $status;
    }

	
// ########################################  FICHIERS ########################################

    //This function copies the referentiel related info from backup temp dir to course moddata folder,
    //creating it if needed and recoding everything (referentiel id and user id) 
    function referentiel_restore_files ($oldassid, $newassid, $olduserid, $newuserid, $restore) {

        global $CFG;

        $status = true;
        $todo = false;
        $moddata_path = "";
        $referentiel_path = "";
        $temp_path = "";

        //First, we check to "course_id" exists and create is as necessary
        //in CFG->dataroot
        $dest_dir = $CFG->dataroot."/".$restore->course_id;
        $status = check_dir_exists($dest_dir,true);

        //Now, locate course's moddata directory
        $moddata_path = $CFG->dataroot."/".$restore->course_id."/".$CFG->moddata;
   
        //Check it exists and create it
        $status = check_dir_exists($moddata_path,true);

        //Now, locate referentiel directory
        if ($status) {
            $referentiel_path = $moddata_path."/referentiel";
            //Check it exists and create it
            $status = check_dir_exists($referentiel_path,true);
        }

        //Now locate the temp dir we are gong to restore
        if ($status) {
            $temp_path = $CFG->dataroot."/temp/backup/".$restore->backup_unique_code.
                         "/moddata/referentiel/".$oldassid."/".$olduserid;
            //Check it exists
            if (is_dir($temp_path)) {
                $todo = true;
            }
        }

        //If todo, we create the neccesary dirs in course moddata/referentiel
        if ($status and $todo) {
            //First this referentiel id
            $this_referentiel_path = $referentiel_path."/".$newassid;
            $status = check_dir_exists($this_referentiel_path,true);
            //Now this user id
            $user_referentiel_path = $this_referentiel_path."/".$newuserid;
            //And now, copy temp_path to user_referentiel_path
            $status = backup_copy_file($temp_path, $user_referentiel_path); 
        }
       
        return $status;
    }

    //Return a content decoded to support interactivities linking. Every module
    //should have its own. They are called automatically from
    //referentiel_decode_content_links_caller() function in each module
    //in the restore process
    function referentiel_decode_content_links ($content,$restore) {
            
        global $CFG; global $DB;
            
        $result = $content;
                
        //Link to the list of referentiels
                
        $searchstring='/\$@(REFERENTIELINDEX)\*([0-9]+)@\$/';
        //We look for it
        preg_match_all($searchstring,$content,$foundset);
        //If found, then we are going to look for its new id (in backup tables)
        if ($foundset[0]) {
            //print_object($foundset);                                     //Debug
            //Iterate over foundset[2]. They are the old_ids
            foreach($foundset[2] as $old_id) {
                //We get the needed variables here (course id)
                $rec = backup_getid($restore->backup_unique_code,"course",$old_id);
                //Personalize the searchstring
                $searchstring='/\$@(REFERENTIELINDEX)\*('.$old_id.')@\$/';
                //If it is a link to this course, update the link to its new location
                if($rec->new_id) {
                    //Now replace it
                    $result= preg_replace($searchstring,$CFG->wwwroot.'/mod/referentiel/index.php?id='.$rec->new_id,$result);
                } else { 
                    //It's a foreign link so leave it as original
                    $result= preg_replace($searchstring,$restore->original_wwwroot.'/mod/referentiel/index.php?id='.$old_id,$result);
                }
            }
        }

        //Link to referentiel view by moduleid

        $searchstring='/\$@(referentielVIEWBYID)\*([0-9]+)@\$/';
        //We look for it
        preg_match_all($searchstring,$result,$foundset);
        //If found, then we are going to look for its new id (in backup tables)
        if ($foundset[0]) {
            //print_object($foundset);                                     //Debug
            //Iterate over foundset[2]. They are the old_ids
            foreach($foundset[2] as $old_id) {
                //We get the needed variables here (course_modules id)
                $rec = backup_getid($restore->backup_unique_code,"course_modules",$old_id);
                //Personalize the searchstring
                $searchstring='/\$@(referentielVIEWBYID)\*('.$old_id.')@\$/';
                //If it is a link to this course, update the link to its new location
                if($rec->new_id) {
                    //Now replace it
                    $result= preg_replace($searchstring,$CFG->wwwroot.'/mod/referentiel/view.php?id='.$rec->new_id,$result);
                } else {
                    //It's a foreign link so leave it as original
                    $result= preg_replace($searchstring,$restore->original_wwwroot.'/mod/referentiel/view.php?id='.$old_id,$result);
                }
            }
        }

        return $result;
    }

    //This function makes all the necessary calls to xxxx_decode_content_links()
    //function in each module, passing them the desired contents to be decoded
    //from backup format to destination site/course in order to mantain inter-activities
    //working in the backup/restore process. It's called from restore_decode_content_links()
    //function in restore process
    function referentiel_decode_content_links_caller($restore) {
        global $CFG; global $DB;
        $status = true;
        $params=array("courseid" => "$restore->course_id");
        $sql="SELECT a.id, a.description_instance FROM {referentiel} a WHERE a.course = :courseid ";
        if ($referentiels = $DB->get_records_sql ($sql, $params)) {
            //Iterate over each referentiel->description
            $i = 0;   //Counter to send some output to the browser to avoid timeouts
            foreach ($referentiels as $referentiel) {
                //Increment counter
                $i++;
                $content = $referentiel->description_instance;
                $result = restore_decode_content_links_worker($content,$restore);
                if ($result != $content) {
                    //Update record
                    $referentiel->description = addslashes($result);
                    $status = $DB->update_record("referentiel",$referentiel);
                    if (debugging()) {
                        if (!defined('RESTORE_SILENTLY')) {
                            echo '<br /><hr />'.s($content).'<br />changed to<br />'.s($result).'<hr /><br />';
                        }
                    }
                }
                //Do some output
                if (($i+1) % 5 == 0) {
                    if (!defined('RESTORE_SILENTLY')) {
                        echo ".";
                        if (($i+1) % 100 == 0) {
                            echo "<br />";
                        }
                    }
                    backup_flush(300);
                }
            }
        }
        return $status;
    }


    //This function returns a log record with all the necessay transformations
    //done. It's used by restore_log_module() to restore modules log.
    function referentiel_restore_logs($restore,$log) {
    // a adapter au referentiel
        $status = false;
                    
        //Depending of the action, we recode different things
        switch ($log->action) {
        case "add":
            if ($log->cmid) {
                //Get the new_id of the module (to recode the info field)
                $mod = backup_getid($restore->backup_unique_code,$log->module,$log->info);
                if ($mod) {
                    $log->url = "view.php?id=".$log->cmid;
                    $log->info = $mod->new_id;
                    $status = true;
                }
            }
            break;
        case "update":
            if ($log->cmid) {
                //Get the new_id of the module (to recode the info field)
                $mod = backup_getid($restore->backup_unique_code,$log->module,$log->info);
                if ($mod) {
                    $log->url = "view.php?id=".$log->cmid;
                    $log->info = $mod->new_id;
                    $status = true;
                }
            }
            break;
        case "view":
            if ($log->cmid) {
                //Get the new_id of the module (to recode the info field)
                $mod = backup_getid($restore->backup_unique_code,$log->module,$log->info);
                if ($mod) {
                    $log->url = "view.php?id=".$log->cmid;
                    $log->info = $mod->new_id;
                    $status = true;
                }
            }
            break;
        case "view all":
            $log->url = "index.php?id=".$log->course;
            $status = true;
            break;
        case "upload":
            if ($log->cmid) {
                //Get the new_id of the module (to recode the info field)
                $mod = backup_getid($restore->backup_unique_code,$log->module,$log->info);
                if ($mod) {
                    $log->url = "view.php?a=".$mod->new_id;
                    $log->info = $mod->new_id;
                    $status = true;
                }
            }
            break;
        case "view submission":
            if ($log->cmid) {
                //Get the new_id of the module (to recode the info field)
                $mod = backup_getid($restore->backup_unique_code,$log->module,$log->info);
                if ($mod) {
                    $log->url = "submissions.php?id=".$mod->new_id;
                    $log->info = $mod->new_id;
                    $status = true;
                }
            }
            break;
        case "update grades":
            if ($log->cmid) {
                //Extract the referentiel id from the url field                             
                $assid = substr(strrchr($log->url,"="),1);
                //Get the new_id of the module (to recode the info field)
                $mod = backup_getid($restore->backup_unique_code,$log->module,$assid);
                if ($mod) {
                    $log->url = "submissions.php?id=".$mod->new_id;
                    $status = true;
                }
            }
            break;
        default:
            if (!defined('RESTORE_SILENTLY')) {
                echo "action (".$log->module."-".$log->action.") unknown. Not restored<br />";                 //Debug
            }
            break;
        }

        if ($status) {
            $status = $log;
        }
        return $status;
    }
?>
