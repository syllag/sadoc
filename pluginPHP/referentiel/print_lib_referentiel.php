<?php  // $Id:  print_lib_referentiel.php,v 1.0 2008/04/29 00:00:00 jfruitet Exp $
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
 * Print Library of functions for module referentiel
 * 
 * @author jfruitet
 * @version $Id: lib.php,v 1.4 2006/08/28 16:41:20 mark-nielsen Exp $
 * @version $Id: lib.php,v 1.0 2008/04/29 00:00:00 jfruitet Exp $
 * @package referentiel
 **/


require_once("lib.php");




// Affiche le r�f�rentiel
function referentiel_select_referentiels($params){
// affichage de la liste des referentiels plus bouton de selection
	$str="";
	$referentiels = referentiel_get_referentiel_referentiels($params);
	if ($referentiels){
		// DEBUG
		// echo "<br/>DEBUG :: print_lib_referentiel.php LIGNE 12 <br />\n";
		// print_r($referentiels);
    
		foreach ($referentiels as $record){
			$referentiel_id=$record->id;
		  
    	$name = stripslashes($record->name);
			$code_referentiel = stripslashes($record->code_referentiel);
			/*
			$description_referentiel = $record->description_referentiel;
			$url_referentiel = $record->url_referentiel;
			$seuil_certificat = $record->seuil_certificat;
			$timemodified = $record->timemodified;
			$nb_domaines = $record->nb_domaines;
			*/
			$liste_codes_competence=$record->liste_codes_competence;
			// cours ?
			if (isset($record->course))
				$referentiel_course=$record->course;
			else
				$referentiel_course=0;
			// local ou global ?
			if (isset($record->local))
				$referentiel_local=$record->local;
			else
				$referentiel_local=0;
			
			if (isset($record->pass_referentiel) && ($record->pass_referentiel!='')) {
    			$referentiel_pass = $record->pass_referentiel;
			}
			else{
				$referentiel_pass = '';
			}

			$str.='<input type="radio" name="referentiel_id" value="'.$referentiel_id.'" />';
			$str.=$name." (".$code_referentiel.") "; 	
			if (isset($referentiel_local) && ($referentiel_local!=0)){
				$str.=get_string("local", "referentiel")."\n";	
			}
			else {
				$str.=get_string("global", "referentiel")."\n";
			}
			// echo("<br />".$liste_codes_competence);
			if ($referentiel_pass!=''){
				$str.= ' <i>'.get_string("check_pass_referentiel", "referentiel").'</i> : <input type="password" name="referentiel_pass_'.$referentiel_id.'" id="referentiel_pass_'.$referentiel_id.'" value="" />';
				$str.= ' <input type="hidden" name="givepass_'.$referentiel_id.'" id="givepass_'.$referentiel_id.'" value="1" />';
			}
			else{
				$str.= ' <input type="hidden" name="givepass_'.$referentiel_id.'" id="givepass_'.$referentiel_id.'" value="0" />';
			}
			$str.="\n<br />\n";
		}
		$str.="\n";
	}
	return $str;
}


function referentiel_affiche_referentiel_instance($instance_id){
// Affiche l'instance et le referentiel associe
global $OUTPUT;
	if (isset($instance_id) && ($instance_id>0)){
		// saisie de l'instance
		$referentiel_instance = referentiel_get_referentiel($instance_id);
		if ($referentiel_instance){
			$name_i=stripslashes($referentiel_instance->name);
			$description_i=stripslashes($referentiel_instance->description_instance);
			$label_d=stripslashes($referentiel_instance->label_domaine);
			$label_c=stripslashes($referentiel_instance->label_competence);
			$label_i=stripslashes($referentiel_instance->label_item);
			
		    $date_i=$referentiel_instance->date_instance;
			$course_id=$referentiel_instance->course;
	

            $s='<div align="center"><h3>'.get_string('referentiel_instance', 'referentiel').' ';
            if (!empty($referentiel_instance->name)){
                $s.=' '.$referentiel_instance->name;
            }
            $s.=$OUTPUT->help_icon('referentielinstanceh','referentiel').'</h3></div>'."\n";
            echo $s;

?>


<table class="referentiel" cellpadding="5">
<tr valign="top"  class="referentiel"> 
    <th class="referentiel" align="right" width="20%"><b><?php  print_string('name_instance','referentiel') ?>:</b></th>
    <td class="referentiel" align="left" width="80%">
        <?php  p($name_i); ?>
    </td>
</tr>
<tr valign="top"  class="referentiel"> 
    <th class="referentiel" align="right" width="20%"><b><?php  print_string('description_instance','referentiel') ?>:</b></th>
    <td class="referentiel" align="left" width="80%">
		<?php  echo (nl2br($description_i)); ?>
    </td>
</tr>
<tr valign="top"  class="referentiel"> 
    <th class="referentiel" align="right" width="20%"><b><?php  print_string('label_domaine','referentiel') ?>:</b></th>
    <td class="referentiel" align="left" width="80%">
		<?php  p($label_d); ?>
    </td>
</tr>
<tr valign="top"  class="referentiel"> 
    <th class="referentiel" align="right" width="20%"><b><?php  print_string('label_competence','referentiel') ?>:</b></th>
    <td class="referentiel" align="left" width="80%">
		<?php  p($label_c); ?>
    </td>
</tr>
<tr valign="top"  class="referentiel"> 
    <th class="referentiel" align="right" width="20%"><b><?php  print_string('label_item','referentiel') ?>:</b></th>
    <td class="referentiel" align="left" width="80%">
		<?php  p($label_i); ?>
    </td>
</tr>
</table>


<?php			
	// get parameters
	
	    	$params = new stdClass;
    		$params->label_domaine = $label_d;
			$params->label_competence = $label_c;
			$params->label_item = $label_i;
			referentiel_affiche_referentiel($referentiel_instance->ref_referentiel, $params, $instance_id);
		}
	}
}


function referentiel_affiche_referentiel($id, $params=NULL, $instance_id){
// Affiche le r�f�rentiel
global $OUTPUT;
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
	$not_light_display=referentiel_site_light_display($instance_id)>0;
	
	if (isset($id) && ($id>0)){
		$record_a = referentiel_get_referentiel_referentiel($id);
        $referentiel_id=$record_a->id;
		$name = $record_a->name;
		$code_referentiel = stripslashes($record_a->code_referentiel);
		$description_referentiel = stripslashes($record_a->description_referentiel);
		$url_referentiel = referentiel_affiche_url($record_a->url_referentiel,"","_blank");
		$seuil_certificat = $record_a->seuil_certificat;
		$timemodified = $record_a->timemodified;
		$nb_domaines = $record_a->nb_domaines;
		$liste_codes_competence=$record_a->liste_codes_competence;
		$liste_empreintes_competence=$record_a->liste_empreintes_competence;
		$liste_poids_competence=referentiel_get_liste_poids_competence($id);
		// local ou global ?
		if (isset($record_a->local))
			$referentiel_local=$record_a->local;
		else
			$referentiel_local=0;
		
		$logo=$record_a->logo_referentiel;
		
        $s='<div align="center"><h3>'.get_string('referentiel', 'referentiel').' ';
        if (!empty($code_referentiel)){
                $s.=' '.$code_referentiel;
        }
        $s.=$OUTPUT->help_icon('referentiel2h','referentiel').'</h3></div>'."\n";
        echo $s;

?>

<table class="referentiel" cellpadding="5">
<tr valign="top"  class="referentiel"> 
    <th class="referentiel" align="right" width="20%"><b><?php  print_string('name','referentiel') ?>:</b></th>
    <td class="referentiel" align="left" width="80%">
        <?php  p($name) ?>
    </td>
</tr>
<tr valign="top"  class="referentiel"> 
    <th class="referentiel" align="right" width="20%"><b><?php  print_string('code','referentiel') ?>:</b></th>
    <td class="referentiel" align="left" width="80%">
        <?php  p($code_referentiel) ?>
    </td>
</tr>
<tr valign="top"  class="referentiel"> 
    <th class="referentiel" align="right" width="20%"><b><?php  print_string('description','referentiel') ?>:</b></th>
    <td class="referentiel" align="left" width="80%">
		<?php  echo (nl2br($description_referentiel)); ?>
    </td>
</tr>
<tr valign="top"  class="referentiel"> 
    <th class="referentiel" align="right" width="20%"><b><?php  print_string('url','referentiel') ?>:</b></th>
    <td class="referentiel" align="left" width="80%">
        <?php  echo $url_referentiel; ?>
    </td>
</tr>
<?php
if ($not_light_display){
?>
<tr valign="top"  class="referentiel"> 
    <th class="referentiel" align="right" width="20%"><b><?php  print_string('seuil_certificat','referentiel') ?>:</b></th>
    <td class="referentiel" align="left" width="80%">
		<?php  p($seuil_certificat) ?>
    </td>
</tr>
<tr valign="top"  class="referentiel"> 
    <th class="referentiel" align="right" width="20%"><b><?php  print_string('referentiel_global','referentiel') ?>:</b></th>
    <td class="referentiel" align="left" width="80%">
<?php	
if (isset($referentiel_local) && ($referentiel_local!=0)){
	print_string("no")."\n";
}
else{
	print_string("yes")."\n";
}
?>

    </td>
</tr>
<tr valign="top"  class="referentiel"> 
    <th class="referentiel" align="right" width="20%"><b><?php  print_string('liste_codes_empreintes_competence','referentiel') ?>:</b></th>
    <td class="referentiel" align="left" width="80%">
		<?php  echo referentiel_affiche_liste_codes_empreintes_competence('/', $liste_codes_competence, $liste_empreintes_competence, $liste_poids_competence); ?>
    </td>
</tr>
<?php
	if (isset($logo)){
		echo '
<tr valign="top"  class="referentiel"> 
    <th class="referentiel" align="right" width="20%"><b>
	';
	print_string('logo','referentiel');
	 	echo ':</b>';
		echo '</th>
    <td class="referentiel" align="left" width="80%">
';
		echo referentiel_affiche_image($logo);
		echo referentiel_menu_logo($instance_id, ($logo!=""));
		echo '    </td>
</tr>
';
	}
}
?>
</table>
<br />
<?php
        $s='<div align="center"><h3>'.get_string('domaine', 'referentiel').' ';
        $s.=$OUTPUT->help_icon('domaineh','referentiel').' ';
        $s.=get_string('competence', 'referentiel').' ';
        $s.=$OUTPUT->help_icon('competenceh','referentiel').' ';
        $s.=get_string('item', 'referentiel').' ';
        $s.=$OUTPUT->help_icon('itemh','referentiel').'</h3></div>'."\n";
        echo $s;

?>
<br />
<table class="referentiel" cellpadding="5">
<?php

		// charger les domaines associes au referentiel courant
		if (isset($id) && ($id>0)){
			$ref_referentiel=$id; // plus pratique
			// AFFICHER LA LISTE DES DOMAINES
			$compteur_domaine=0;
			$records_domaine = referentiel_get_domaines($ref_referentiel);
	    	if ($records_domaine){
    			// afficher
				// DEBUG
				// echo "<br/>DEBUG ::<br />\n";
				// print_r($records_domaine);
				foreach ($records_domaine as $record){
					$compteur_domaine++;
        			$domaine_id=$record->id;
					$nb_competences = $record->nb_competences;
					$code_domaine = stripslashes($record->code_domaine);
					$description_domaine = stripslashes($record->description_domaine);
					$num_domaine = $record->num_domaine;
?>
<!-- DOMAINE -->
<tr valign="top" bgcolor="#ffffcc">
    <td class="domaine" align="left"><b>
<?php  
if (!empty($label_d)){
	p($label_d);
}
else {
	print_string('domaine','referentiel') ;
}
?>
<?php  p($num_domaine) ?>
</b>
    </td>
    <td class="domaine" align="left">
        <?php  p($code_domaine) ?>
    </td>
    <td class="domaine" align="left" colspan="4">
		<?php  echo (nl2br($record->description_domaine)); ?>
    </td>
</tr>

<?php
					// LISTE DES COMPETENCES DE CE DOMAINE
					$compteur_competence=0;
					$records_competences = referentiel_get_competences($domaine_id);
			    	if ($records_competences){
						// DEBUG
						// echo "<br/>DEBUG :: COMPETENCES <br />\n";
						// print_r($records_competences);
						foreach ($records_competences as $record_c){
							$compteur_competence++;
        					$competence_id=$record_c->id;
							$nb_item_competences = $record_c->nb_item_competences;
							$code_competence = stripslashes($record_c->code_competence);
							$description_competence = stripslashes($record_c->description_competence);
							$num_competence = $record_c->num_competence;
							$ref_domaine = $record_c->ref_domaine;
?>
<!-- COMPETENCE -->
<tr valign="top">
    <td class="competence" align="left">
<b>
<?php  
if (!empty($label_c)){
	p($label_c);
}
else {
	print_string('competence','referentiel') ;
}
?>

<!-- i>
<?php  p($num_competence) ?>
</i -->
</b>
    </td>
    <td class="competence" align="left">
<?php  p($code_competence) ?>
    </td>
    <td class="competence" align="left" colspan="4">
<?php  echo (nl2br($description_competence)); ?>
    </td>
</tr>
<?php
							// ITEM
							$compteur_item=0;
							$records_items = referentiel_get_item_competences($competence_id);
							
						    if ($records_items){
								// DEBUG
								// echo "<br/>DEBUG :: ITEMS <br />\n";
								// print_r($records_items);
?>
<tr valign="top" bgcolor="#5555000">
    <th class="item" align="right">
<i>	
<?php  print_string('numero', 'referentiel'); ?>
</i>
    </th>
    <th class="item" align="left">
		<?php  print_string('code', 'referentiel');?>
    </th>
    <th class="item" align="left">
<?php  print_string('description', 'referentiel'); ?>
    </th>
    <?php
    if ($not_light_display){
    ?>
    <th class="item" align="left">
<?php   print_string('t_item', 'referentiel'); ?>
    </th>
    <th class="item" align="left">
<?php   print_string('p_item', 'referentiel'); ?>
    </th>
    <th class="item" align="left">
<?php   print_string('e_item', 'referentiel'); ?>
    </th>
    <?php
    }
    else{
        // echo '<th class="item" colspan="3">&nbsp;</th>'."\n";
    }
    ?>
</tr>
<?php
								
								foreach ($records_items as $record_i){
									$compteur_item++;
	    		    				$item_id=$record_i->id;
									$code_item = stripslashes($record_i->code_item);
									$description_item = stripslashes($record_i->description_item);
									$num_item = $record_i->num_item;
									$type_item = stripslashes($record_i->type_item);
									$poids_item = $record_i->poids_item;	
									$empreinte_item = $record_i->empreinte_item;	
									$ref_competence=$record_i->ref_competence;
?>
<tr valign="top" bgcolor="#ffeefe">
    <td class="item" align="right" bgcolor="#ffffff">
<i>	
<?php  p($num_item) ?>
</i>
    </td>
    <td class="item" align="left">
		<?php  p($code_item) ?>
    </td>
    <td class="item" align="left">
<?php  echo (nl2br($description_item)); ?>
    </td>
    <?php
    if ($not_light_display){
    ?>
    <td class="item" align="left">
<?php  p($type_item) ?>
    </td>
    <td class="poids" align="left">
<?php  p($poids_item) ?>
    </td>
    <td class="empreinte" align="left">
<?php  p($empreinte_item) ?>
    </td>
    <?php
    }
    else{
       // echo '<td colspan="3">&nbsp;</td>'."\n";
    }
    ?>

</tr>
<?php
								}
							}
						}
					}
				}
			}
		}
?>					
</table>

<?php
	}
}



// Affiche les certificats de ce referentiel
function referentiel_liste_certificats($id_referentiel){
	if (isset($id_referentiel) && ($id_referentiel>0)){
		$records_certificat = referentiel_get_certificats($id_referentiel);
		if (!$records_certificat){
			error(get_string('nocertificat','referentiel'), "certificat.php?d=$id_referentiel&mode=add");
		}
	    else {
			
?>
<h3><?php  print_string('certificat','referentiel') ?></h3>
<table class="certificat" cellpadding="5">
<?php
		
    		// afficher
			// DEBUG
			// echo "<br/>DEBUG ::<br />\n";
			// print_r($records_referentiel);
			foreach ($records_certificat as $record_a){
        		$certificat_id=$record_a->id;
				$commentaire_certificat = stripslashes($record_a->commentaire_certificat);
				$competences_certificat = $record_a->competences_certificat;
				$decision_jury = stripslashes($record_a->decision_jury);
				$ref_referentiel = $record_a->ref_referentiel;
				$userid = $record_a->userid;
				$teacherid = $record_a->teacherid;
				$date_decision = $record_a->date_decision;
?>
<tr valign="top"  class="certificat" > 
    <td class="certificat"  align="right" width="20%">
	<b><?php  print_string('id','referentiel'); ?> : </b>
    </td>
    <td class="certificat"  align="left">
	<?php  p($certificat_id) ?>
    </td>
    <td class="certificat"  align="right" width="20%">
     <b><?php print_string('etudiant','referentiel')?> : </b>
    </td>
    <td class="certificat"  align="left">
		<?php p($userid) ?>
    </td>
</tr>

<tr valign="top"  class="certificat" > 
    <td class="certificat"  align="right" width="20%">
	<b><?php  print_string('decision_jury','referentiel') ?>:</b>
	</td>
    <td class="certificat"  align="left">
        <?php  echo (nl2br($decision_jury)); ?>
    </td>
	<td class="certificat"  align="right" width="20%">
	<b><?php  print_string('date_decision','referentiel') ?> : </b>
	</td>	
    <td class="certificat"  align="left">
		<?php  p($date_decision) ?>
    </td>		
</tr>
<tr valign="top"  class="certificat" > 
    <td class="certificat"  align="right" width="20%">
	<b><?php  print_string('commentaire','referentiel') ?>:</b>
	</td>
    <td class="certificat"  align="left" colspan="3">
        <?php  echo (nl2br($commentaire_certificat)); ?>
    </td>
</tr>
<tr valign="top"  class="certificat" > 
    <td class="certificat"  align="right" width="20%">
	<b><?php  print_string('liste_codes_competence','referentiel') ?> : </b>
	</td>
    <td class="certificat"  align="left" colspan="3">
		<?php  p(referentiel_affiche_liste_codes_competence('/',$competences_certificat)); ?>
    </td>
</tr>
<tr valign="top"  class="certificat" > 
    <td class="certificat"  align="right" width="20%">
     <b><?php   print_string('referent','referentiel') ?> : </b>
    </td>
	<td class="certificat"  align="left" colspan="3">
	<?php p($teacherid); ?>
    </td>
</tr>	
<?php
			}
		}
?>					
</table>				
<?php
	}
}


// ----------------------------------------------------
function referentiel_coupe_liste($separateur,$liste){
// coupe la liste en son milieu
// separateur 
	$nl="";
	$tl=explode($separateur, $liste);
	$ne=count($tl);
	if ($ne>=2){
		for ($i=0; $i<$ne/2;$i++){
			if (trim($tl[$i])!=""){
				$nl.=$tl[$i].$separateur;
			}
		}
		$nl.=' ';
		for ($i=$ne/2; $i<$ne; $i++){
			if (trim($tl[$i])!=""){
				$nl.=$tl[$i].$separateur;
			}
		}
		return $nl;
	}
	return $liste;
}


// ----------------------------------------------------
function referentiel_affiche_liste_codes_competence($separateur, $liste){
// supprime separateur
// separateur 
	return str_replace($separateur, ' ', $liste);
}

// ----------------------------------------------------
function referentiel_selection_liste_codes_item_competence($separateur, $liste){
// input : liste de code de la forme 'CODE''SEPARATEUR' 
// retourne le selecteur
	$nl='';
	$s1='<input type="checkbox" id="code_item_';
	$s2='" name="code_item[]" value="';
	$s3='" />';
	$s4='<label for="code_item_';
	$s5='">';
	$s6='</label> ';	
	$tl=explode($separateur, $liste);
	$ne=count($tl);
	$select='';
	for ($i=0; $i<$ne;$i++){
		if (trim($tl[$i])!=""){
			$nl.=$s1.$i.$s2.$tl[$i].$s3.$s4.$i.$s5.$tl[$i].$s6;
		}
	}
	return $nl;
}

// ----------------------------------------------------
function referentiel_modifer_selection_liste_codes_item_competence($separateur, $liste_complete, $liste_saisie){
// input : liste de code de la forme 'CODE''SEPARATEUR' 
// input : liste2 de code de la forme 'CODE''SEPARATEUR' codes declares
// retourne le selecteur
	// DEBUG
	// echo "$liste_saisie<br />\n";
	$nl='';
	$s1='<input type="checkbox" id="code_item_';
	$s2='" name="code_item[]" value="';
	$s3='"';
	$s4=' /><label for="code_item_';
	$s5='">';
	$s6='</label> ';	
	$checked=' checked';
	$tl=explode($separateur, $liste_complete);
	$liste_saisie=strtr($liste_saisie, $separateur, ' ');
	$liste_saisie=trim(strtr($liste_saisie, '.', '_'));
	// echo "$liste_saisie<br />\n";	
	$ne=count($tl);
	$select='';
	for ($i=0; $i<$ne;$i++){
		$code=trim($tl[$i]);
		
		if ($code!=""){
			$code_search='/'.strtr($code, '.', '_').'/';
			// echo "RECHERCHE '$code_search' dans '$liste_saisie'<br />\n";
			// if (eregi($code_search, $liste_saisie)){
			if (preg_match($code_search, $liste_saisie)){
				$nl.=$s1.$i.$s2.$code.$s3.$checked.$s4.$i.$s5.$code.$s6;
			}
			else {
				$nl.=$s1.$i.$s2.$code.$s3.$s4.$i.$s5.$code.$s6;
			}
		}
	}
	return $nl;
}


// ----------------------------------------------------
function referentiel_affiche_liste_empreintes_competence($separateur, $liste){
// supprime separateur
// separateur 
	return str_replace($separateur, '&nbsp; &nbsp; &nbsp; &nbsp;', $liste);
}

// ----------------------------------------------------
function referentiel_affiche_liste_codes_empreintes_competence($separateur, $listecodes, $listeempreintes, $listepoids=''){
// affiche des codes, poids et empreintes dans un tableau
// supprime separateur
$s="";
$c="";
$e="";
$p="";

$okpoids=false;
$listecodes=referentiel_purge_dernier_separateur($listecodes,$separateur);

    $maxcol=30;
    // Modif JF   2012/01/30
    // Adapter le nombre de colonnes � la taille des codes � afficher

    $lca=strlen($listecodes);
    // echo "<br>Longueur : $lca\n";
    if  ($lca>600){
        $maxcol=round($lca/60)+1 ;
    }
    else if ($lca>400){
        $maxcol=round($lca/40)+1 ;
    }
    else if ($lca>230){
        $maxcol=round($lca/12)+1 ;
    }
    else if ($lca>150){
        $maxcol=round($lca/8)+1 ;
    }
    else{
        $maxcol=30;
    }
    // echo "<br>NB COLONNES : $maxcol<br> \n";
    // exit;

$listeempreintes=referentiel_purge_dernier_separateur($listeempreintes,$separateur);
if (isset($listepoids) && ($listepoids!='')){
	$tpoids=explode($separateur,$listepoids);
	$okpoids=true;
}
$tcode=explode($separateur,$listecodes);
$tempreinte=explode($separateur,$listeempreintes);
	if (($tcode) && (count($tcode)>0)){
		$s.="<table class='referentiel'><tr>";
		$i=0;
		$col=0;
		while ($i<count($tcode)){
			if ($col<$maxcol){
				$c.="<td>".$tcode[$i]."</td>";
				if ($okpoids){
					$p.="<td class='poids'>".$tpoids[$i]."</td>";
				}
				$e.="<td class='empreinte'><i>".$tempreinte[$i]."</i></td>";
			}
			else{
				$c.="</tr>\n<tr>";
				if ($okpoids){
					$p.="</tr>\n<tr>";
				}
				$e.="</tr>\n<tr>";
				$col=-1;
			}
			$col++;
			$i++;
		}
		if ($i>$maxcol){
			while ($col<$maxcol){
				$c.="<td>&nbsp;</td>";
				if ($okpoids){
					$p.="<td class='poids'>&nbsp;</td>";
				}
				$e.="<td class='empreinte'>&nbsp;</td>";
				$col++;
			}
		}
		if ($okpoids){
			$s.=$c."</tr><tr>".$p."</tr><tr>".$e."</tr>\n";
		}
		else{
			$s.=$c."</tr><tr>".$e."</tr>\n";
		}
		$s.="</table>\n";
	}
	return $s;
}

// ------------- 
function referentiel_affiche_image($logo){
	if ($logo!=""){
		return referentiel_affiche_url($logo);
	}
	return "";
}

// -------------
// menu logo
function referentiel_menu_logo($referentiel_instance_id, $delete=false){
	global $CFG;
	global $cm;
	global $OUTPUT;
	$s="";
	// CONTEXTE
    $context = get_context_instance(CONTEXT_MODULE, $cm->id);
	if (has_capability('mod/referentiel:writereferentiel', $context)) {
		$s=' &nbsp; &nbsp; <a href="'.$CFG->wwwroot.'/mod/referentiel/upload_logo.php?d='.$referentiel_instance_id.'&mode=update&sesskey='.sesskey().'"><img src="'.$OUTPUT->pix_url('/t/edit').'" alt="'.get_string('edit').'" title="'.get_string('edit').'" /></a>';
		if ($delete){
			$s.=' <a href="'.$CFG->wwwroot.'/mod/referentiel/upload_logo.php?d='.$referentiel_instance_id.'&mode=delete&sesskey='.sesskey().'"><img src="'.$OUTPUT->pix_url('/t/delete').'" alt="'.get_string('delete').'" title="'.get_string('delete').'" /></a>';
		}
	}
	return $s;
}


?>