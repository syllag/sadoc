<?php //$Id: backuplib.php,v 1.12 2006/09/21 09:35:20 stronk7 Exp $
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

    //This function executes all the backup procedure about this mod
    function referentiel_backup_mods($bf,$preferences) {
        global $DB;
        $status = true;
        //Iterate over referentiel table
        $referentiels = $DB->get_records("referentiel", array("course" => "$preferences->backup_course"));
        if ($referentiels) {
            foreach ($referentiels as $referentiel) {
                if (backup_mod_selected($preferences,'referentiel',$referentiel->id)) {
                    $status = referentiel_backup_one_mod($bf,$preferences,$referentiel);
                    // backup files happens in backup_one_mod now too.
                }
            }
        }
        return $status;  
    }

    function referentiel_backup_one_mod($bf,$preferences,$referentiel) {
        global $DB;
    	
        if (is_numeric($referentiel)) { // inutile ici
            $referentiel = $DB->get_record("referentiel", array("id" => "$referentiel"));
        }
    	
        $status = true;
		/*
	Referentiel instance
	
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
  config_impression
  PRIMARY KEY (id)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='Instance de referentiel de competence';

		*/
        //Start mod
        fwrite ($bf,start_tag("MOD",3,true));
        //Print referentiel data
        fwrite ($bf,full_tag("ID",4,false,$referentiel->id));
        fwrite ($bf,full_tag("MODTYPE",4,false,"referentiel"));
        fwrite ($bf,full_tag("NAME",4,false,$referentiel->name));
        fwrite ($bf,full_tag("DESCRIPTION_INSTANCE",4,false,$referentiel->description_instance));
        fwrite ($bf,full_tag("LABEL_DOMAINE",4,false,$referentiel->label_domaine));
        fwrite ($bf,full_tag("LABEL_COMPETENCE",4,false,$referentiel->label_competence));
		fwrite ($bf,full_tag("LABEL_ITEM",4,false,$referentiel->label_item));
        fwrite ($bf,full_tag("DATE_INSTANCE",4,false,$referentiel->date_instance));
        fwrite ($bf,full_tag("REF_REFERENTIEL",4,false,$referentiel->ref_referentiel));
        fwrite ($bf,full_tag("VISIBLE",4,false,$referentiel->visible));
        fwrite ($bf,full_tag("CONFIG",4,false,$referentiel->config));
        fwrite ($bf,full_tag("CONFIG_IMPRESSION",4,false,$referentiel->config_impression));
        
		// referentiel_referentiel
		$status = backup_referentiel_referentiel ($bf,$preferences,$referentiel->ref_referentiel);
		
        //if we've selected to backup users info, then execute backup_referentiel_submisions and
        //backup_referentiel_files_instance
        if (backup_userdata_selected($preferences,'referentiel',$referentiel->id)) {
            $status = backup_referentiel_taches($bf,$preferences,$referentiel->id);
            $status = backup_referentiel_activites($bf,$preferences,$referentiel->id);
            $status = backup_referentiel_certificats($bf,$preferences,$referentiel->ref_referentiel);
            $status = backup_referentiel_etablissement_etudiants ($bf,$preferences);
			$status = backup_referentiel_files_instance($bf,$preferences,$referentiel->id);
        }
        //End mod
        $status =fwrite ($bf,end_tag("MOD",3,true));

        return $status;
    }

    //Backup referentiel_referentiel contents (executed from referentiel_backup_mods)
    function backup_referentiel_referentiel ($bf,$preferences,$referentiel) {
        global $DB;

        $status = true;
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
        $referentiel_referentiel = $DB->get_record("referentiel_referentiel", array("id" => "$referentiel"));
        //If there is submissions
        if ($referentiel_referentiel) {
            //Write start tag
            $status =fwrite ($bf,start_tag("REFERENTIEL",4,true));
            
            //Print activity contents
            fwrite ($bf,full_tag("ID",5,false,$referentiel_referentiel->id));       
            fwrite ($bf,full_tag("NAME",5,false,$referentiel_referentiel->name));       
            fwrite ($bf,full_tag("CODE_REFERENTIEL",5,false,$referentiel_referentiel->code_referentiel));       
            fwrite ($bf,full_tag("MAIL_AUTEUR_REFERENTIEL",5,false,$referentiel_referentiel->mail_auteur_referentiel));
			      fwrite ($bf,full_tag("CLE_REFERENTIEL",5,false,$referentiel_referentiel->cle_referentiel));
			      fwrite ($bf,full_tag("PASS_REFERENTIEL",5,false,$referentiel_referentiel->pass_referentiel));
			      fwrite ($bf,full_tag("DESCRIPTION_REFERENTIEL",5,false,$referentiel_referentiel->description_referentiel));
			      fwrite ($bf,full_tag("URL_REFERENTIEL",5,false,$referentiel_referentiel->url_referentiel));
			      fwrite ($bf,full_tag("SEUIL_CERTIFICAT",5,false,$referentiel_referentiel->seuil_certificat));
			      fwrite ($bf,full_tag("TIMEMODIFIED",5,false,$referentiel_referentiel->timemodified));
			      fwrite ($bf,full_tag("NB_DOMAINES",5,false,$referentiel_referentiel->nb_domaines));
			      fwrite ($bf,full_tag("LISTE_CODES_COMPETENCE",5,false,$referentiel_referentiel->liste_codes_competence));
			      fwrite ($bf,full_tag("LISTE_EMPREINTES_COMPETENCE",5,false,$referentiel_referentiel->liste_empreintes_competence));
			      fwrite ($bf,full_tag("LOCAL",5,false,$referentiel_referentiel->local));
			      fwrite ($bf,full_tag("LOGO_REFERENTIEL",5,false,$referentiel_referentiel->logo_referentiel));
				
			// domaines
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
			     $referentiel_domaines = $DB->get_records("referentiel_domaine", array("ref_referentiel" => "$referentiel_referentiel->id"));
			     if ($referentiel_domaines){
				    //Iterate over each domaine
				    $status =fwrite ($bf,start_tag("DOMAINES",5,true));
                    foreach ($referentiel_domaines as $domaine) {
					   // stard domaine
					   $status =fwrite ($bf,start_tag("DOMAINE",6,true));
                	//Print domaine contents
                	fwrite ($bf,full_tag("ID",7,false,$domaine->id));   
                	fwrite ($bf,full_tag("CODE_DOMAINE",7,false,$domaine->code_domaine));   
                	fwrite ($bf,full_tag("DESCRIPTION_DOMAINE",7,false,$domaine->description_domaine));   
                	fwrite ($bf,full_tag("REF_REFERENTIEL",7,false,$domaine->ref_referentiel));   
                	fwrite ($bf,full_tag("NUM_DOMAINE",7,false,$domaine->num_domaine));
                	fwrite ($bf,full_tag("NB_COMPETENCES",7,false,$domaine->nb_competences));   
						
					// competences
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
					       $referentiel_competences = $DB->get_records("referentiel_competence", array("ref_domaine" => "$domaine->id"));
					       if ($referentiel_competences){
						        // Iterate
						        $status =fwrite ($bf,start_tag("COMPETENCES",7,true));
						        foreach ($referentiel_competences as $competence) {
							         // stard competence
							         $status =fwrite ($bf,start_tag("COMPETENCE",8,true));
							//Print competence contents
							fwrite ($bf,full_tag("ID",9,false,$competence->id));
							fwrite ($bf,full_tag("CODE_COMPETENCE",9,false,$competence->code_competence));
							fwrite ($bf,full_tag("DESCRIPTION_COMPETENCE",9,false,$competence->description_competence));
							fwrite ($bf,full_tag("REF_DOMAINE",9,false,$competence->ref_domaine));
							fwrite ($bf,full_tag("NUM_COMPETENCE",9,false,$competence->num_competence));
							fwrite ($bf,full_tag("NB_ITEM_COMPETENCES",9,false,$competence->nb_item_competences));
							
							// items
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
							$referentiel_items = $DB->get_records("referentiel_item_competence", array("ref_competence" => "$competence->id"));
							if ($referentiel_items){
								// Iterate
								$status =fwrite ($bf,start_tag("ITEMS",9,true));
								foreach ($referentiel_items as $item ) {
									// stard item
									$status =fwrite ($bf,start_tag("ITEM",10,true));
									//Print item contents
									fwrite ($bf,full_tag("ID",11,false,$item->id));
									fwrite ($bf,full_tag("CODE_ITEM",11,false,$item->code_item));
									fwrite ($bf,full_tag("DESCRIPTION_ITEM",11,false,$item->description_item));
									fwrite ($bf,full_tag("REF_REFERENTIEL",11,false,$item->ref_referentiel));
									fwrite ($bf,full_tag("REF_COMPETENCE",11,false,$item->ref_competence));
									fwrite ($bf,full_tag("TYPE_ITEM",11,false,$item->type_item));
									fwrite ($bf,full_tag("POIDS_ITEM",11,false,$item->poids_item));
									fwrite ($bf,full_tag("EMPREINTE_ITEM",11,false,$item->empreinte_item));
									fwrite ($bf,full_tag("NUM_ITEM",11,false,$item->num_item));
									$status =fwrite ($bf,end_tag("ITEM",10,true));
								}
								$status =fwrite ($bf,end_tag("ITEMS",9,true));
							}
							$status =fwrite ($bf,end_tag("COMPETENCE",8,true));
						}
						$status =fwrite ($bf,end_tag("COMPETENCES",7,true));
					}
					$status =fwrite ($bf,end_tag("DOMAINE",6,true));
				}
				$status =fwrite ($bf,end_tag("DOMAINES",5,true));
			}
			$status =fwrite ($bf,end_tag("REFERENTIEL",4,true));
		}
		//End referentiel
        return $status;
    }

	
// ###################################### ACTIVITES ########################################
	
	//Backup referentiel_activites contents (executed from referentiel_backup_mods)
    function backup_referentiel_activites ($bf,$preferences,$referentiel) {
        global $DB;

        $status = true;
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
        $referentiel_activites = $DB->get_records("referentiel_activite", array("ref_instance" => "$referentiel");
        //If there is submissions
        if ($referentiel_activites) {
            //Write start tag
            $status =fwrite ($bf,start_tag("ACTIVITES",4,true));
            //Iterate over each activite
            foreach ($referentiel_activites as $activite) {
                //Start activite
                $status =fwrite ($bf,start_tag("ACTIVITE",5,true));
                //Print activity contents
                fwrite ($bf,full_tag("ID",6,false,$activite->id));       
                fwrite ($bf,full_tag("TYPE_ACTIVITE",6,false,$activite->type_activite));       
                fwrite ($bf,full_tag("DESCRIPTION_ACTIVITE",6,false,$activite->description_activite));       
                fwrite ($bf,full_tag("COMPETENCES_ACTIVITE",6,false,$activite->competences_activite));       
                fwrite ($bf,full_tag("COMMENTAIRE_ACTIVITE",6,false,$activite->commentaire_activite));       
                fwrite ($bf,full_tag("REF_INSTANCE",6,false,$activite->ref_instance));       
                fwrite ($bf,full_tag("REF_REFERENTIEL",6,false,$activite->ref_referentiel));       
                fwrite ($bf,full_tag("REF_COURSE",6,false,$activite->ref_course));       // en principe inutile 
				fwrite ($bf,full_tag("USERID",6,false,$activite->userid));       
                fwrite ($bf,full_tag("TEACHERID",6,false,$activite->teacherid));       
                fwrite ($bf,full_tag("DATE_CREATION",6,false,$activite->date_creation));       
                fwrite ($bf,full_tag("DATE_MODIF",6,false,$activite->date_modif));
                fwrite ($bf,full_tag("APPROVED",6,false,$activite->approved));
				fwrite ($bf,full_tag("REF_TASK",6,false,$activite->ref_task));
                fwrite ($bf,full_tag("DATE_MODIF_STUDENT",6,false,$activite->date_modif_student));				
				
                //End activite
				
				 
				// associations tache ?
				if ($activite->ref_task!=0){ // activite associee a une tache
					$status =  backup_referentiel_activite_task($bf, $preferences, $activite->id, $activite->ref_task);
				}

				// Documents associes
				/*
				
CREATE TABLE mdl_referentiel_document (
  id bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  type_document varchar(20) NOT NULL DEFAULT '',
  description_document text NOT NULL,
  url_document varchar(255) NOT NULL DEFAULT '',
  ref_activite bigint(10) unsigned NOT NULL,
  PRIMARY KEY (id)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='Document';

				*/
				$referentiel_documents = $DB->get_records("referentiel_document", array("ref_activite" => "$activite->id");
				if ($referentiel_documents){
					//Iterate over each activite
					$status =fwrite ($bf,start_tag("DOCUMENTS",6,true));
          foreach ($referentiel_documents as $document) {
						//Start document
                		$status =fwrite ($bf,start_tag("DOCUMENT",7,true));
                		//Print activity contents
                		fwrite ($bf,full_tag("ID",8,false,$document->id));       
                		fwrite ($bf,full_tag("TYPE_DOCUMENT",8,false,$document->type_document));
						        fwrite ($bf,full_tag("DESCRIPTION_DOCUMENT",8,false,$document->description_document));
						        fwrite ($bf,full_tag("URL_DOCUMENT",8,false,$document->url_document));
						        fwrite ($bf,full_tag("REF_ACTIVITE",8,false,$document->ref_activite));
						        $status =fwrite ($bf,end_tag("DOCUMENT",7,true));
					}
					$status =fwrite ($bf,end_tag("DOCUMENTS",6,true));
				}
        $status =fwrite ($bf,end_tag("ACTIVITE",5,true));
      }
      //Write end tag
      $status =fwrite ($bf,end_tag("ACTIVITES",4,true));
    }
        return $status;
}

	
// ###################################### TACHES ########################################

    //Backup referentiel_tasks contents (executed from referentiel_backup_mods)
    function backup_referentiel_taches ($bf,$preferences,$referentiel) {

        global $DB;

        $status = true;
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
        $referentiel_taches = $DB->get_records("referentiel_task", array("ref_instance" => "$referentiel"));
        //If there is submissions
        if ($referentiel_taches) {
            //Write start tag
            $status =fwrite ($bf,start_tag("TASKS",4,true));
            //Iterate over each activite
            foreach ($referentiel_taches as $tache) {
                //Start task
                $status =fwrite ($bf,start_tag("TASK",5,true));
                //Print task contents
                fwrite ($bf,full_tag("ID",6,false,$tache->id));       
                fwrite ($bf,full_tag("TYPE_TASK",6,false,$tache->type_task));       
                fwrite ($bf,full_tag("DESCRIPTION_TASK",6,false,$tache->description_task));       
                fwrite ($bf,full_tag("COMPETENCES_TASK",6,false,$tache->competences_task));       
                fwrite ($bf,full_tag("CRITERES_EVALUATION",6,false,$tache->criteres_evaluation));       
                fwrite ($bf,full_tag("REF_INSTANCE",6,false,$tache->ref_instance));       
                fwrite ($bf,full_tag("REF_REFERENTIEL",6,false,$tache->ref_referentiel));       
                fwrite ($bf,full_tag("REF_COURSE",6,false,$tache->ref_course));       // en principe inutile 
				        fwrite ($bf,full_tag("AUTEURID",6,false,$tache->auteurid));       
                fwrite ($bf,full_tag("DATE_CREATION",6,false,$tache->date_creation));       
                fwrite ($bf,full_tag("DATE_MODIF",6,false,$tache->date_modif));
				        fwrite ($bf,full_tag("DATE_DEBUT",6,false,$tache->date_debut));
				        fwrite ($bf,full_tag("DATE_FIN",6,false,$tache->date_fin));
				        fwrite ($bf,full_tag("CLE_SOUSCRIPTION",6,false,$tache->cle_souscription));
				        fwrite ($bf,full_tag("SOUSCRIPTION_LIBRE",6,false,$tache->souscription_libre));
				        
                //End task
				
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
				$referentiel_consignes = $DB->get_records("referentiel_consigne", array("ref_task" => "$tache->id"));
				if ($referentiel_consignes){
					      //Iterate over each activite
					      $status =fwrite ($bf,start_tag("CONSIGNES",6,true));
            		foreach ($referentiel_consignes as $consigne) {
						        //Start document
                		$status =fwrite ($bf,start_tag("CONSIGNE",7,true));
                		//Print activity contents
                		fwrite ($bf,full_tag("ID",8,false,$consigne->id));       
                		fwrite ($bf,full_tag("TYPE_CONSIGNE",8,false,$consigne->type_consigne));
						        fwrite ($bf,full_tag("DESCRIPTION_CONSIGNE",8,false,$consigne->description_consigne));
						        fwrite ($bf,full_tag("URL_CONSIGNE",8,false,$consigne->url_consigne));
						        fwrite ($bf,full_tag("REF_TASK",8,false,$consigne->ref_task));
						        $status =fwrite ($bf,end_tag("CONSIGNE",7,true));
					      }
					      $status =fwrite ($bf,end_tag("CONSIGNES",6,true));
				}
                
				$status =fwrite ($bf,end_tag("TASK",5,true));
      }
      //Write end tag
      $status =fwrite ($bf,end_tag("TASKS",4,true));
    }
        return $status;
}


    //Backup referentiel_a_user_task contents (executed from referentiel_backup_mods)
    function backup_referentiel_activite_task($bf,$preferences, $activite_id, $task_id) {
	// recupere l'association entre une activite et une tache
	// appele depuis backup_referentiel_activites_mod(
        global $DB;
        $status = true;
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
		if (!empty($activite_id) && !empty($task_id)){
			// verifier si association existe
			$params= array("ref_task" => "$task_id", "ref_activite" => "$activite_id");
			$sql="SELECT * FROM {referentiel_a_user_task}  WHERE ref_task=:ref_task AND ref_activite=:activiteid ";
			// echo '<br />DEBUG :: backuplib.php :: 500 :: SQL: '.$sql."\n";
			$referentiel_a_users_tasks = $DB->get_records_sql($sql, $params);
			if ($referentiel_a_users_tasks){
	            //Write start tag
    	        $status =fwrite ($bf,start_tag("USERS_TASKS",6,true));
        	    //Iterate over each record
            	foreach ($referentiel_a_users_tasks as $referentiel_a_user_task) {
			        //If there is a_user_task
    			    if ($referentiel_a_user_task) {
                		//Start task
	                	$status =fwrite ($bf,start_tag("USER_TASK",7,true));
	    	            //Print contents
    	    	        fwrite ($bf,full_tag("ID",8,false,$referentiel_a_user_task->id));       
        	    	    fwrite ($bf,full_tag("REF_USER",8,false,$referentiel_a_user_task->ref_user));       
            	    	fwrite ($bf,full_tag("REF_TASK",8,false,$referentiel_a_user_task->ref_task));       
	            	    fwrite ($bf,full_tag("DATE_SELECTION",8,false,$referentiel_a_user_task->date_selection));       
    	            	fwrite ($bf,full_tag("REF_ACTIVITE",8,false,$referentiel_a_user_task->ref_activite));       
	        	        //End task
    	        	    $status =fwrite ($bf,end_tag("USER_TASK",7,true));
        			}
				}
				//Write end tag
           		$status =fwrite ($bf,end_tag("USERS_TASKS",6,true));
			}
    	}
	    return $status;
    }

    //Backup referentiel_a_user_task contents (executed from referentiel_backup_mods)
    function backup_referentiel_a_user_task ($bf,$preferences,$task_id) {
	// recupere toutes les association ayant in ref_task donné
	// n'est plus utilise dans cette appli
        global $DB;

        $status = true;
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
        $referentiel_a_user_task = $DB->get_records("referentiel_a_user_task", array("ref_task" => "$task_id"));
        //If there is submissions
        if ($referentiel_a_user_task) {
            //Write start tag
            $status =fwrite ($bf,start_tag("USERS_TASKS",6,true));
            //Iterate over each record
            foreach ($referentiel_a_user_task as $tache) {
                //Start task
                $status =fwrite ($bf,start_tag("USER_TASK",7,true));
                //Print contents
                fwrite ($bf,full_tag("ID",8,false,$tache->id));       
                fwrite ($bf,full_tag("REF_USER",8,false,$tache->ref_user));       
                fwrite ($bf,full_tag("REF_TASK",8,false,$tache->ref_task));       
                fwrite ($bf,full_tag("DATE_SELECTION",8,false,$tache->date_selection));       
                fwrite ($bf,full_tag("REF_ACTIVITE",8,false,$tache->ref_activite));       
                //End task
                $status =fwrite ($bf,end_tag("USER_TASK",7,true));
            }
            //Write end tag
            $status =fwrite ($bf,end_tag("USERS_TASKS",6,true));
        }
        return $status;
    }

// ################################### ETABLISSEMENT ##################################
	//Backup referentiel_etablissements contents (executed from referentiel_backup_mods)
    function backup_referentiel_etablissement_etudiants ($bf,$preferences) {

        global $DB;

        $status = true;
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
        $referentiel_etablissements = $DB->get_records("referentiel_etablissement", NULL);
        //If there is submissions
        if ($referentiel_etablissements) {
            //Write start tag
            $status =fwrite ($bf,start_tag("ETABLISSEMENTS",4,true));
            //Iterate over each activite
            foreach ($referentiel_etablissements as $etablissement) {
                //Start etablissement
                $status =fwrite ($bf,start_tag("ETABLISSEMENT",5,true));
                //Print activity contents
                fwrite ($bf,full_tag("ID",6,false,$etablissement->id));       
                fwrite ($bf,full_tag("NUM_ETABLISSEMENT",6,false,$etablissement->num_etablissement));       
				fwrite ($bf,full_tag("NOM_ETABLISSEMENT",6,false,$etablissement->nom_etablissement));
                fwrite ($bf,full_tag("ADRESSE_ETABLISSEMENT",6,false,$etablissement->adresse_etablissement));
				fwrite ($bf,full_tag("LOGO_ETABLISSEMENT",6,false,$etablissement->logo_etablissement));
				//End etablissement
				
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
				$referentiel_etudiants = $DB->get_records("referentiel_etudiant", array("ref_etablissement" => "$etablissement->id"));
				if ($referentiel_etudiants){
					//Iterate over each etudiant
					$status =fwrite ($bf,start_tag("ETUDIANTS",6,true));
            		foreach ($referentiel_etudiants as $etudiant) {
						//Start document
                		$status =fwrite ($bf,start_tag("ETUDIANT",7,true));
                		//Print activity contents
                		fwrite ($bf,full_tag("ID",8,false,$etudiant->id));       
						fwrite ($bf,full_tag("NUM_ETUDIANT",8,false,$etudiant->num_etudiant));
						fwrite ($bf,full_tag("DDN_ETUDIANT",8,false,$etudiant->ddn_etudiant));
						fwrite ($bf,full_tag("LIEU_NAISSANCE",8,false,$etudiant->lieu_naissance));
						fwrite ($bf,full_tag("DEPARTEMENT_NAISSANCE",8,false,$etudiant->departement_naissance));
						fwrite ($bf,full_tag("ADRESSE_ETUDIANT",8,false,$etudiant->adresse_etudiant));
						fwrite ($bf,full_tag("USERID",8,false,$etudiant->userid));
						fwrite ($bf,full_tag("REF_ETABLISSEMENT",8,false,$etudiant->ref_etablissement));
						
						$status =fwrite ($bf,end_tag("ETUDIANT",7,true));
					}
					$status =fwrite ($bf,end_tag("ETUDIANTS",6,true));
				}
                $status =fwrite ($bf,end_tag("ETABLISSEMENT",5,true));
            }
            //Write end tag
            $status =fwrite ($bf,end_tag("ETABLISSEMENTS",4,true));
        }
        return $status;
    }

    //Backup referentiel_referentiel contents (executed from referentiel_backup_mods)
    function backup_referentiel_certificats($bf,$preferences,$referentiel) {

        global $DB;

        $status = true;
/*

DROP TABLE IF EXISTS `mdl_referentiel_certificat`;
CREATE TABLE IF NOT EXISTS `mdl_referentiel_certificat` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `commentaire_certificat` text NOT NULL,
  `competences_certificat` text NOT NULL,
  `competences_activite` text NOT NULL,
  `decision_jury` varchar(80) NOT NULL DEFAULT '',
  `date_decision` bigint(10) unsigned NOT NULL DEFAULT '0',
  `ref_referentiel` bigint(10) unsigned NOT NULL DEFAULT '0',
  `userid` bigint(10) unsigned NOT NULL,
  `teacherid` bigint(10) unsigned NOT NULL,
  `verrou` tinyint(1) unsigned NOT NULL,
  `valide` tinyint(1) unsigned NOT NULL,
  `evaluation` bigint(10) unsigned NOT NULL,
  `mailed` tinyint(2) unsigned NOT NULL DEFAULT '0',
  `mailnow` bigint(10) unsigned NOT NULL DEFAULT '0',
  `synthese_certificat` text,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='Certificat' AUTO_INCREMENT=1 ;

*/
        $referentiel_certificats = $DB->get_records("referentiel_certificat", array("ref_referentiel" => "$referentiel"));
        //If there is certificats
        if ($referentiel_certificats) {
            //Write start tag
            $status =fwrite ($bf,start_tag("CERTIFICATS",4,true));
			//Iterate over each certificat
            foreach ($referentiel_certificats as $certificat) {
				// stard domaine
				$status =fwrite ($bf,start_tag("CERTIFICAT",5,true));
                //Print contents
                fwrite ($bf,full_tag("ID",6,false,$certificat->id));
				fwrite ($bf,full_tag("COMMENTAIRE_CERTIFICAT",6,false,$certificat->commentaire_certificat));
				fwrite ($bf,full_tag("COMPETENCES_CERTIFICAT",6,false,$certificat->competences_certificat));
                fwrite ($bf,full_tag("DECISION_JURY",6,false,$certificat->decision_jury));
				fwrite ($bf,full_tag("DATE_DECISION",6,false,$certificat->date_decision));
				fwrite ($bf,full_tag("REF_REFERENTIEL",6,false,$certificat->ref_referentiel));
				fwrite ($bf,full_tag("USERID",6,false,$certificat->userid));
				fwrite ($bf,full_tag("TEACHERID",6,false,$certificat->teacherid));
				fwrite ($bf,full_tag("VERROU",6,false,$certificat->verrou));
				fwrite ($bf,full_tag("VALIDE",6,false,$certificat->valide));
				fwrite ($bf,full_tag("EVALUATION",6,false,$certificat->evaluation));
				fwrite ($bf,full_tag("SYNTHESE_CERTIFICAT",6,false,$certificat->synthese_certificat));
				$status =fwrite ($bf,end_tag("CERTIFICAT",5,true));
			}
			$status =fwrite ($bf,end_tag("CERTIFICATS",4,true));
		}
		//End certificats
        return $status;
    }

    //Backup referentiel files because we've selected to backup user info
    //and files are user info's level
    function backup_referentiel_files($bf,$preferences) {

        global $DB;
       
        $status = true;

        //First we check to moddata exists and create it as necessary
        //in temp/backup/$backup_code  dir
        $status = check_and_create_moddata_dir($preferences->backup_unique_code);
        //Now copy the referentiel dir
        if ($status) {
            //Only if it exists !! Thanks to Daniel Miksik.
            if (is_dir($CFG->dataroot."/".$preferences->backup_course."/".$CFG->moddata."/referentiel")) {
                $status = backup_copy_file($CFG->dataroot."/".$preferences->backup_course."/".$CFG->moddata."/referentiel",
                                           $CFG->dataroot."/temp/backup/".$preferences->backup_unique_code."/moddata/referentiel");
            }
        }

        return $status;

    } 

    function backup_referentiel_files_instance($bf,$preferences,$instanceid) {

        global $DB;
       
        $status = true;

        //First we check to moddata exists and create it as necessary
        //in temp/backup/$backup_code  dir
        $status = check_and_create_moddata_dir($preferences->backup_unique_code);
        $status = check_dir_exists($CFG->dataroot."/temp/backup/".$preferences->backup_unique_code."/moddata/referentiel/",true);
        //Now copy the referentiel dir
        if ($status) {
            //Only if it exists !! Thanks to Daniel Miksik.
            if (is_dir($CFG->dataroot."/".$preferences->backup_course."/".$CFG->moddata."/referentiel/".$instanceid)) {
                $status = backup_copy_file($CFG->dataroot."/".$preferences->backup_course."/".$CFG->moddata."/referentiel/".$instanceid,
                                           $CFG->dataroot."/temp/backup/".$preferences->backup_unique_code."/moddata/referentiel/".$instanceid);
            }
        }

        return $status;

    } 

    //Return an array of info (name,value)
    function referentiel_check_backup_mods($course,$user_data=false,$backup_unique_code,$instances=null) {
        if (!empty($instances) && is_array($instances) && count($instances)) {
            $info = array();
            foreach ($instances as $id => $instance) {
                $info += referentiel_check_backup_mods_instances($instance,$backup_unique_code);
            }
            return $info;
        }
        //First the course data
        $info[0][0] = get_string("modulenameplural","referentiel");
        if ($ids = referentiel_ids ($course)) {
            $info[0][1] = count($ids);
        } else {
            $info[0][1] = 0;
        }

        //Now, if requested, the user_data
        if ($user_data) {
            $info[1][0] = get_string("task","referentiel");
            if ($ids = referentiel_task_ids_by_course ($course)) { 
                $info[1][1] = count($ids);
            } else {
                $info[1][1] = 0;
            }
            $info[2][0] = get_string("activite","referentiel");
            if ($ids = referentiel_activite_ids_by_course ($course)) { 
                $info[2][1] = count($ids);
            } else {
                $info[2][1] = 0;
            }
        }
		
		// DEBUG
		// echo "<br /> DEBUG :: backuplib.php :: LIGNE 367 :: INFO <br />\n";
		// print_r($info);
		
        return $info;
    }

    //Return an array of info (name,value)
    function referentiel_check_backup_mods_instances($instance,$backup_unique_code) {
        $info[$instance->id.'0'][0] = '<b>'.$instance->name.'</b>';
        $info[$instance->id.'0'][1] = '';
        if (!empty($instance->userdata)) {
            $info[$instance->id.'1'][0] = get_string("task","referentiel");
            if ($ids = referentiel_task_ids_by_instance ($instance->id)) {
                $info[$instance->id.'1'][1] = count($ids);
            } else {
                $info[$instance->id.'1'][1] = 0;
            }
            $info[$instance->id.'2'][0] = get_string("activite","referentiel");
            if ($ids = referentiel_activite_ids_by_instance ($instance->id)) {
                $info[$instance->id.'2'][1] = count($ids);
            } else {
                $info[$instance->id.'2'][1] = 0;
            }
        }

		// DEBUG
		// echo "<br /> DEBUG :: backuplib.php :: LIGNE 393 :: INFO <btr />\n";
		// print_r($info);
        return $info;
	}

    //Return a content encoded to support interactivities linking. Every module
    //should have its own. They are called automatically from the backup procedure.
    function referentiel_encode_content_links ($content,$preferences) {

        global $DB;

        $base = preg_quote($CFG->wwwroot,"/");

        //Link to the list of referentiels
        $buscar="/(".$base."\/mod\/referentiel\/index.php\?id\=)([0-9]+)/";
        $result= preg_replace($buscar,'$@REFERENTIELINDEX*$2@$',$content);

        //Link to referentiel view by moduleid
        $buscar="/(".$base."\/mod\/referentiel\/view.php\?id\=)([0-9]+)/";
        $result= preg_replace($buscar,'$@REFERENTIELVIEWBYID*$2@$',$result);

        return $result;
    }

    // INTERNAL FUNCTIONS. BASED IN THE MOD STRUCTURE

    //Returns an array of referentiels id 
    function referentiel_ids ($course) {

        global $DB;

        return $DB->get_records_sql ("SELECT a.id, a.course
                                 FROM {referentiel} a
                                 WHERE a.course = :course", array("course" => "$course"));
    }
    
    //Returns an array of referentiel_activite id
    function referentiel_activite_ids_by_course ($course) {
        global $DB;

        return $DB->get_records_sql ("SELECT s.id , s.ref_instance
                                 FROM {referentiel_activite} s,
                                      {referentiel} a
                                 WHERE a.course = :course AND
                                       s.ref_instance = a.id", array("course" => "$course"));
    }

    //Returns an array of referentiel_submissions id
    function referentiel_activite_ids_by_instance ($instanceid) {
        global $DB;

        return $DB->get_records_sql ("SELECT s.id , s.ref_instance
                                 FROM {referentiel_activite} s
                                 WHERE s.ref_instance = :refid", array("refid" => "$instanceid") );
    }

	    //Returns an array of referentiel_activite id
    function referentiel_task_ids_by_course ($course) {
        global $DB;

        return $DB->get_records_sql ("SELECT s.id , s.ref_instance
                                 FROM {referentiel_task} s,
                                      {referentiel} a
                                 WHERE a.course = :course AND
                                       s.ref_instance = a.id", array("course" => "$course"));
    }

    //Returns an array of referentiel_submissions id
    function referentiel_task_ids_by_instance ($instanceid) {

        global $DB;

        return $DB->get_records_sql ("SELECT s.id , s.ref_instance
                                 FROM {referentiel_task} s
                                 WHERE s.ref_instance = :refid", array("refid" => "$instanceid"));
    }

?>
