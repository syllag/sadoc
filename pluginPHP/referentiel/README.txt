﻿Moodle module referentiel 
Module Moodle - Référentiel / Pository - Documentation
jean.fruitet@univ-nantes.fr
2007/2012

Type: Activity Module
Requires: Moodle 1.8 / 1.9 or later
Status: Contributed
Maintainer(s): jean.fruitet@univ-nantes.fr

ENGLISH
-------
"referentiel" is a Moodle module for skill certification.
You can:
- specify a repository or import it
- declare activities linked with competencies
- follow students declarations
- propose tasks (a mission, list of competencies, linked documents...)
- export an print certificates

- If your site enables Outcomes (also known as Competencies, Goals, Standards or Criteria),
you can now export a list of Outcomes from referentiel module then grade things using
that scale (forum, database, assigments, etc.) throughout the site.
These grades will be integrated in Referentiel module.

FRANCAIS
--------
"referentiel" est un module Moodle destiné à implanter une activité de type certification
de compétences.
Ce module permet :
- de spécifier un référentiel de compétences (ou de le télécharger) ;
- de déclarer des activités et d'associer celles-ci aux compétences du référentiel ;
- de gérer l'accompagnement ;
- de définir des tâches (mission, consignes, liste de compétences mobilisées pour accomplir la tâche, documents attachés) ;
- d'émettre des certificats basés sur le dit référentiel ;

- Si le site active les Objectifs, vous pouvez exporter le référentiel sous forme d'une
liste d'objectifs qui serviront alors à évaluer toute forme d'activité
(forum, BD, devoir, etc.)
Ces notations sont récupérées dans le module référentiel sous forme de compétences
validées dans des déclaration d'activité.

INSTALLATION
----------------------------
Ce module doit être intégré dans le répertoire ./mod/ d'un serveur Moodle

La procédure suivante s'applique à toute installation Moodle
VOTRE_DOSSIER_MOODLE = le nom du dossier où est placé votre moodle, en général "moodle"
URL_SERVEUR_MOODLE = le nom de votre serveur moodle, en général "http://machine.domaine.fr/moodle/"

1. Décomprimer l'archive "referentiel_xxx.zip" dans le dossier "VOTRE_DOSSIER_MOODLE/mod/"
Les fichiers de langue peuvent être laissés dans le dossier
"VOTRE_DOSSIER_MOODLE/mod/referentiel/lang/"

2. se loger avec le role admin sur "URL_SERVEUR_MOODLE"

3. Installer le module referentiel comme un nouveau module en passant par la rubrique
Administration / Notification
S'il y a des messages d'erreur m'avertir aussitôt par mail en m'envoyant une copie d'écran du message d'erreur.

4. paramétrer le module au niveau du site en passant par la rubrique
Administration / Plugins / Activités / Référentiel

ERREUR FREQUENTE LORS DES MISE A JOUR
------------------------------------------------
L'erreur à éviter est de créer une copie de sauvegarde (sous un autre nom) dans le dossier
VOTRE_DOSSIER_MOODLE/mod/

-------------------------------------------------
Marche à suivre pour désinstaller ce module
-------------------------------------------------
1. Logez-vous avec le role admin

2. Supprimez le module
Administration / Plugins / Activités / Gestion des activités
Sélectionnner  Référentiel :: Supprimer

3. Supprimez le dossier VOTRE_DOSSIER_MOODLE/mod/referentiel

4. Supprimer les dossiers
./moodledata/#course_id/moddata/referentiel
de tous les cours où une instance a été déclarée



ENGLISH
---------------------------------------------------------

The following steps should get you up and running with this module code.
---------------------------------------------------------
1. Unzip the archive in moodle/mod/ directory
Languages files can be left in the moodle/mod/referentiel/lang/ directory.
2. log on with admin role

3. install new module as usual (admin Notification)

4. Set module parameters
Administration / Plugins / Activity / Repository

---------------------------------------------------------
Documentation et mises à jours sous forme d'archive ZIP 
---------------------------------------------------------
sur les sites du MoodleMoot2008 (Nantes) et du MoodleMoot2009 (Lyon)

    * MoodleMoot2008 : http://moodlemoot2008.vet-nantes.fr/moodle/course/view.php?id=36
    * MoodleMoot2009 : http://moodlemoot2009.insa-lyon.fr/course/view.php?id=24
    * MoodleMoot2010 : http://moodlemoot2010.utt.fr/course/view.php?id=33

Pour être tenu informé des mise à jour inscrivez-vous dans les forums de ces cours.

---------------------------------------------------
CVS / Subversion
---------------------------------------------------
Le serveur public des sources CVS / Subversion est  :
Subversion server at: 
https://subversion.cru.fr/referentiel/


----------------------------------------------------
Forums et Tests du module
----------------------------------------------------
A french thread is at 
http://moodle.org/mod/forum/discuss.php?d=127647

Un fil de discussion sur Moodle en Français est consacré au module : 
http://moodle.org/mod/forum/discuss.php?d=127647

A test server here : http://ateliers.moodlelab

Un espace de test est disponible sur le serveur Moodle des Ateliers du  MoodleLab 
http://ateliers.moodlelab
dans la catégorie Référentiels

---------------------------------------------------------
Liste des documents disponibles sur ces différents sites
Useful documentation
---------------------------------------------------------

    * Documentation utilisateurs / Users doc
    * Documentation développeurs / Developers doc
    * Communications au MoodleMoot2008 et MoodleMoot2009 / French MoodleMoots 
    * Captures d'écran et présentations animées / Print screens
    * Vidéos 

--------------------------------------------------------
Liste de référentiels disponibles pour importation
--------------------------------------------------------
After you get runing the referentiel module, go to "./mod/referentiel/sauvegarde_referentiel" directory 
to import some ready made repositories. (In french)
Après installation du module sur un serveur Moodle, le dossier "./mod/referentiel/sauvegarde_referentiel" 
contient les exports/imports suivants :

Référentiel		Format d'import CSV			Format d'import XML
B2i Ecole		referentiel-b2i_ecole.csv	referentiel-b2i_ecole.xml
B2i Collège		referentiel-b2i_college.csv	referentiel-b2i_college.xml
B2i Lycée		referentiel-b2i_lycee.csv	referentiel-b2i_lycee.xml

C2IN1
Version 2008	referentiel-c2n1.csv		referentiel-c2in1.xml
Version 2012    referentiel-c2in1-2012_generique.csv  referentiel-c2in1-2012_generique.xml
ATTENTION :
Les versions des C2iN1 2008 et 2012 ne devraient pas être installées sur le même serveur
pour éviter des confusions lors de la certification.

C2i2
C2i2 Enseignant	version 2005     referentiel-c2i2e.csv		referentiel-c2i2e.xml
C2i2 Enseignant	version 2011/2012    referentiel-c2i2e.xml
ATTENTION :
Les versions des C2i2e 2005 et 2011/2012 ne devraient pas être installées sur le même serveur
pour éviter des confusions lors de la certification.


Moodle Referentiel v5.3.5 de juin 2010 et suivantes
---------------------------------------------------

Un nouveau format XML est proposé en importation : XML simplifié
Il est généré par l'éditeur de référentiel wysiwyg intégré à Moodle depuis la version 5.3.5
On le reconnaît à la présence dans le nom de fichier du suffixe _sxml.xml
Vous pouvez trouver des modèles pour ce format dans le dossier
./mod/referentiel/editor/data de votre serveur.

Les fichiers .txt, .dat de ce dossier sont des modèles pour le module référentiel.
Les fichier _sxml.xml sont au format XML simplifié.
Ils peuvent être importés dans le module lors de la création d'une nouvelle instance de référentiel
en cliquant sur le bouton "Import XML simplifié".

N'essayez pas d'importer un référentiel XML simplifié (dont le nom est de la forme xxx_sxml.xml)
depuis la rubrique "Référentiel/Importer" de l'activité Référentiel


--------------------------------------------------------
Sauvegarde des données sur le serveur Moodle
et importation /  exportation et restauration du module
-------------------------------------------------------
Exports and user data are in ./moodledata/#course_id/moddata/referentiel

Ce module enregistre les exportations dans le dossier ./moodledata/#course_id/moddata/referentiel
Attention : En cas de suppression du module Référentiel ces dossier doivent être purgés à la main 
par l'administrateur système !



Outcomes used in moodle activities are integrated in Pository activity.
=========================================================================
If your site enables Outcomes (also known as Competencies, Goals, Standards or Criteria),
you can now export a list of Outcomes from referentiel module then grade things using that scale (forum, database, assigments, etc.)
throughout the site. These grades will be automatically integrated in Referentiel module.


Evaluer des activités Moodle (forum, devoirs, etc.) au regard d'un barème de référentiel.
==========================================================================================
Si les objectifs sont activés sur votre serveur Moodle (voir avec l'administrateur comment les activer)
vous pouvez sauvegarder le référentiel sous forme d'un barême d'objectifs
puis utiliser ce barême pour évaluer toute forme d'activité Moodle (forums, devoirs, bases de données, wiki, etc.)
Le module Référentiel récupèrera ces évaluations et génèrera des déclarations qui seront dès lors accessibles
dans la liste des activités du module référentiel.

Protocole

   1. Avec le rôle d'administrateur activer les Objectifs au niveau du serveur
   2. Depuis le module Référentiel Exporter les objectifs (Onglet "Référentiel / Exporter")
      Enregistrez le fichier "outcomes_referentiel_xxx.csv" sur votre disque dur.
   3. Au niveau du cours passer par Administration / Notes et sélectionner Modifier Objectifs
   4. Choisir alors Importer comme objectifs de ce cours ou Importer comme objectifs standards
puis dans la rubrique Importer des objectifs (Taille maximale : xxMo) sélectionnez le fichier
"outcomes_referentiel_xxx.csv" ci-dessus enregistré.

Désormais vous pouvez utiliser ce barême pour évaluer toute activité du cours.
Les étudiants notés selon ce barême verront leurs productions intégrées directement
dans le module référentiel sous forme de déclarations d'activité accessibles et modifiables selon les modalités usuelles.


ATTENTION : Moodle 1.9.5 to 1.9.7 does not permit outcomes to be imported by teachers.
http://tracker.moodle.org/browse/MDL-18506
Certaines versions de Moodle ne supportent pas correctement l'importation
des fichiers d'Objectifs.
This is corrected with this patch :
Il faut installer un patch :
http://moodle.org/file.php/5/moddata/forum/397/634415/grade_edit_outcome.zip
Commentaire à cette adresse :
Commentary about this bug :
http://moodle.org/mod/forum/discuss.php?d=145112

---------------------------------------------------------
Historique des versions 
---------------------------------------------------------

Référentiel pour Moodle 2.2
---------------------------
AVERTISSEMENT
Version en cours de développement.
Activer le mode Debug complet sur le serveur Moodle.
Merci de faire remonter les erreurs en fournissant une capture d"écran.

Version 2.2.04 (2011112615) Mise à jour du 2012/01/31
Affichage corrigé du référentiel et des jauges d'activité pour éviter que la jauge ne sorte de la page
Modification de la gestion des rôles pour récupérer dans le module référentiel les rôles hérités des rôles standards "editingteacher"et "teacher".


Version 2.2.03 (2011112615) Mise à jour du 2012/01/16
Nouvel onglet "Objectifs" dans la rubrique "Certificats"
Correction d'un bug pour la notification des tâches.


Version 2.2.01 (2011112615) Mise à jour du 05/01/2012
Changement du numéro de version pour distinguer les versions pour Moodle 2.1 et celles pour Moodle 2.2

