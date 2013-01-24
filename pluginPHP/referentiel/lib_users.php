<?php
 // $Id:  lib_users.php,v 1.0 2011/03/30 00:00:00 jfruitet Exp $
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
 * @version $Id: lib_users.php,v 1.0 2011/03/30 00:00:00 jfruitet Exp $
 * @package referentiel v 6.0.00 2011/04/01 00:00:00
 **/

// COURSE USERS / ADMINS etc.

function referentiel_admins_liste($id_course) {
//liste des admins d'un cours
// version  MOODLE 1.7 et suivants !!!
    $context = get_context_instance(CONTEXT_COURSE, $id_course);
    $admins=get_users_by_capability($context,"moodle/legacy:admin", "u.id,firstname,lastname,email","lastname");
    $liste="";
    foreach ($admins as $p){
       $liste .=$p->id. ' '.$p->firstname. ' '.$p->lastname.'<'.$p->email.'>';
	}
   return $liste;
}

function referentiel_is_admin($userid, $id_course){
    $context = get_context_instance(CONTEXT_COURSE, $id_course);
    $admins=get_users_by_capability($context,"moodle/legacy:admin", "u.id","lastname");
    foreach ($admins as $p){
       if($userid==$p->id){
            return true;
       }
	}
   return false;
}


function referentiel_editingteachers($id_course) {
//liste des profs d'un cours
// version  MOODLE 1.7 !!!
    $context = get_context_instance(CONTEXT_COURSE, $id_course);
    $profs=get_users_by_capability($context,"moodle/legacy:editingteacher", "firstname,lastname,email","lastname");
    $liste="";
    foreach ($profs as $p){
       $liste .=$p->firstname. ' '.$p->lastname.'<'.$p->email.'>';
	}
   return $liste;
}

/**
 * Returns user object
 *
 * @param user id
 * @return user info.
 */
function referentiel_get_user($user_id){
    global $DB;
	if (!empty($user_id)){
        $params=array("userid" => "$user_id");
        $sql="SELECT id, username, firstname, lastname, maildisplay, mailformat, maildigest, emailstop, imagealt,
 email, city, country, lastaccess, lastlogin, picture, timezone, theme, lang, trackforums, mnethostid
 FROM {user} WHERE id=:userid ORDER BY email ASC";
        return $DB->get_record_sql($sql, $params);
  }
  return false;
}

// ------------------------------------------
function referentiel_get_user_info($user_id) {
// retourne le NOM prenom à  partir de l'id
global $DB;
	$user_info="";
	if (!empty($user_id)){
        $params=array("userid" => "$user_id");
		$sql = "SELECT firstname, lastname FROM {user}  WHERE id = :userid ";
		$user = $DB->get_record_sql($sql, $params);
		if ($user){
			$user_info=$user->firstname.' '.$user->lastname;
		}
	}
	return $user_info;
}

// ------------------------------------------
function referentiel_get_user_prenom($user_id) {
// retourne le NOM prenom a partir de l'id
global $DB;
	$user_info="";
	if (!empty($user_id)){
        $params=array("userid" => "$user_id");
		$sql = "SELECT firstname FROM {user}  WHERE id = :userid ";
		$user = $DB->get_record_sql($sql, $params);

		if ($user){
			$user_info=$user->firstname;
		}
	}
	return $user_info;
}

// ------------------------------------------
function referentiel_get_user_nom($user_id) {
// retourne le NOM prenom Ã  partir de l'id
global $DB;
	$user_info="";
	if (!empty($user_id)){
        $params=array("userid" => "$user_id");
		$sql = "SELECT lastname FROM {user}  WHERE id = :userid ";
		$user = $DB->get_record_sql($sql, $params);
		if ($user){
			$user_info=$user->lastname;
		}
	}
	return $user_info;
}



function referentiel_get_user_login($id) {
// retourne le login a partir de l'id
global $DB;
	if (!empty($id)){
        $params=array("id" => "$id");
		$sql = "SELECT username FROM {user} WHERE id = :id ";
		$user = $DB->get_record_sql($sql, $params);
		if ($user){
			return $user->username;
		}
	}
	return '';
}

function referentiel_get_userid_by_login($login){
// retourne l'id a partir du login
global $DB;
	if (!empty($login)){
        $params=array("login" => "$login");
		$sql = "SELECT id FROM {user} as a WHERE a.username = :login ";
		$user = $DB->get_record_sql($sql, $params);
		if ($user){
			return $user->id;
		}
	}
	return 0;
}


function referentiel_get_user_mail($user_id) {
// retourne le NOM prenom a partir de l'id
global $DB;
	$user_info="";
	if (!empty($user_id)){
        $params=array("userid" => "$user_id");
		$sql = "SELECT email FROM {user} WHERE id = :userid ";
		$user = $DB->get_record_sql($sql, $params);
		if ($user){
			$user_info=$user->email;
		}
	}
	return $user_info;
}


/**
 * This function returns records list of students from course
 *
 * @return objects
 * @todo Finish documenting this function
 **/
function referentiel_get_all_users_course_by_role($courseid, $userid=0, $roleid=0){
global $DB;

	if (! $course = $DB->get_record("course", array("id" => "$courseid"))) {
		print_error("Course ID is incorrect");
	}
	if (! $context = get_context_instance(CONTEXT_COURSE, $course->id)) {
		print_error("Context ID is incorrect");
	}
    // we are looking for all users with this role assigned in this context or higher
    if ($usercontexts = get_parent_contexts($context)) {
        // $listofcontexts = '('.implode(',', $usercontexts).')';
        $listofcontexts = $usercontexts;
    } else {
        $listofcontexts = array($sitecontext->id); // must be site
    }

    $params=array(CONTEXT_USER, "$courseid", "$context->id", "$courseid", "guest", "$roleid", "$userid");

    list($usql, $params_in) = $DB->get_in_or_equal($listeofcontexts);

    // DEBUG
    echo "<br>DEBUG :: lib.php :: 6683 :: SQL&gt;$usql<br>\n";
    print_r($params_in);
    foreach ($params_in as $in){
        $params[]=$in;
    }
    echo "<br>DEBUG :: lib.php :: 6688 :: PARAMS<br>\n";
    print_r($params);


	$rq="SELECT distinct u.id as userid
    FROM {user} u
	LEFT OUTER JOIN {context} ctx
    	ON (u.id=ctx.instanceid AND ctx.contextlevel = ?)
    JOIN {role_assignments} r
    	ON u.id=r.userid
    LEFT OUTER JOIN {user_lastaccess} ul
    	ON (r.userid=ul.userid and ul.courseid = ?)
WHERE (r.contextid = ? OR r.contextid $usql)
	AND u.deleted = 0
    AND (ul.courseid = ? OR ul.courseid IS NULL)
    AND u.username != ?";
// AND r.hidden = 0  ";

	if ($roleid){
		$rq.=" AND r.roleid = ? ";
	}
	if ($userid){
		$rq.=" AND u.id = ? ";
	}
	$rq.= " ORDER BY u.lastname ".$order_order;
	return $DB->get_records_sql($rq, $params);
}

/**
 * This function returns records list of roles where archetype is
 * editingteachers or teachers
 *
 * @return objects
 * @todo Finish documenting this function
 **/
function referentiel_get_teacher_roles(){
// This function returns records list of teacher's roles
// Remonte aussi les rôles derivés de editingteacher et de teacher...
// rechercher les rôles dont l'archetype est editingteacher ou teacher

global $DB;
    $teacherroles= array();

    $params=array('editingteacher', 'teacher');
    $sql= "SELECT distinct id FROM {role} WHERE (archetype = ? OR archetype= ?) ";
    // DEBUG
    // echo "<br>DEBUG : lib.php :: 265 :: SQL &gt; $sql\n";

    $teacherroles= $DB->get_records_sql($sql, $params);
    if ($teacherroles){
        // DEBUG
        // echo "<br>DEBUG : lib.php :: 270 :: ROLES<br>\n";
        // print_object($teacherroles);
    }

    return $teacherroles;
}


/**
 * This function returns records list of teachers from course
 *
 * @return objects
 * @todo Finish documenting this function
 **/
function referentiel_get_teachers_course($courseid){
// This function returns records list of teachers from course
// Remonte aussi les rôles derivés de editingteacher et de teacher...
global $CFG;
global $DB;
    $teachersids=array();
	if (! $course = $DB->get_record("course", array("id" => "$courseid"))) {
		print_error("Course ID is incorrect");
	}
	if (! $context = get_context_instance(CONTEXT_COURSE, $course->id)) {
		print_error("Context ID is incorrect");
	}

    $roles = referentiel_get_teacher_roles();
    if ($roles){
        // DEBUG
        // echo "<br>DEBUG : lib.php :: 300 :: ROLES<br>\n";
        // print_object($roles);

        foreach ($roles as $role){
            $users= get_role_users($role->id, $context);
            if ($users){
                foreach($users as $user){
                    if (empty($teachersids[$user->id])){
                        $teachersids[$user->id]->userid=$user->id;
                    }
                }
            }
        }
    }
    // DEBUG
    /*
    echo "<br>DEBUG : lib.php :: 313 :: TEACHERS ID<br>\n";
    print_object($teachersids);
    echo "<br>EXIT\n";
    exit;
    */
    return $teachersids;
  }

/**
 * This function returns records list of teachers from course
 *
 * @return objects
 * @todo Finish documenting this function
 **/
function referentiel_get_students_course($courseid, $userid=0, $roleid=0, $quiet=false){
// This function returns records list of students from course
global $DB;
	if (! $course = $DB->get_record("course", array("id" => "$courseid"))) {
		if (!$quiet) print_error("Course ID is incorrect");
		else return false;
	}
	if (! $context = get_context_instance(CONTEXT_COURSE, $course->id)) {
		if (!$quiet) print_error("Context ID is incorrect");
		else return false;
	}

    // we are looking for all users with this role assigned in this context or higher
   /*
//Moodle 1.9
    if ($usercontexts = get_parent_contexts($context)) {
        $listofcontexts = '('.implode(',', $usercontexts).')';
    } else {
        $listofcontexts = '('.$sitecontext->id.')'; // must be site
    }

	$rq="SELECT distinct u.id as userid FROM {$CFG->prefix}user u ";

	$rq.= " LEFT OUTER JOIN {$CFG->prefix}context ctx
    	ON (u.id=ctx.instanceid AND ctx.contextlevel = ".CONTEXT_USER.")
    JOIN {$CFG->prefix}role_assignments r
    	ON u.id=r.userid
    LEFT OUTER JOIN {$CFG->prefix}user_lastaccess ul
    	ON (r.userid=ul.userid and ul.courseid = $course->id)
WHERE ((r.contextid = $context->id)
	OR (r.contextid in $listofcontexts))
	AND
	u.deleted = 0
    AND (ul.courseid = $course->id OR ul.courseid IS NULL)
    AND u.username != 'guest'
	AND r.roleid NOT IN (1,2,3,4)
    AND r.hidden = 0  ";

	if ($roleid){
		$rq.=" AND r.roleid = ".$roleid." ";
	}
	if ($userid){
		$rq.=" AND u.id = ".$userid." ";
	}

	*/

    // MODIF JF 2012/01/31
    $listofroleteachers=array(1,2);
    $teacherroles=referentiel_get_teacher_roles();
    if ($teacherroles){
        foreach($teacherroles as $trole){
            $listofroleteachers[]=$trole->id;
        }
    }

// Moodle 2
    $listofcontexts = '';
    if ($usercontexts = get_parent_contexts($context)) {
        $listofcontexts = $usercontexts;
    } else {
        $listofcontexts = $sitecontext->id; // must be site
    }

	/*
	  1 Administrator admin Administrators can usually do anything on the site... 0
      2 Course creator coursecreator Course creators can create new courses and teach i... 1
      3 Teacher editingteacher Teachers can do anything within a course, includin... 2
      4 Non-editing teacher teacher Non-editing teachers can teach in courses and grad... 3
      5 Student student Students generally have fewer privileges within a ... 4
      6 Guest guest Guests have minimal privileges and usually can not... 5
      7 Authenticated user user All logged in users.
	*/

    // Liste des parametres dans l'ordre d'utilisation
    $params=array(CONTEXT_USER, "$courseid", "$context->id");

    list($csql, $params_in) = $DB->get_in_or_equal($listofcontexts);
    // DEBUG
    /*
    echo "<br>DEBUG :: lib_users :: 528 <br />LIST OF CONTEXT<br />\n";
    echo "<br />CSQL= $csql<br />\n";
    print_r($params_in);
    */

    // NOT IN (1,2,3,4)
    list($rsql, $params_rin) = $DB->get_in_or_equal($listofroleteachers, SQL_PARAMS_QM, 'param_r_0000', false);
    // DEBUG
    /*
    echo "<br>DEBUG :: lib_users :: 422 <br /> SQL&gt;$rsql<br>\n";
    print_r($params_rin);
    echo "<br>EXIT\n";
    exit;
    */
    
    foreach ($params_in as $in){
        $params[]=$in;
    }

    $params[]="guest";

    foreach ($params_rin as $rin){
        $params[]=$rin;
    }
    if ($roleid){
        $params[]=$roleid;
    }
    if ($userid){
        $params[]=$userid;
    }
    /*
    echo "<br>DEBUG :: lib_users.php :: 557 :: PARAMS<br>\n";
    print_r($params);
    */

	$rq="SELECT distinct u.id as userid FROM {user} u ";

	$rq.= " LEFT OUTER JOIN {context} ctx
    	ON (u.id=ctx.instanceid AND ctx.contextlevel = ?)
    JOIN {role_assignments} r
    	ON u.id=r.userid
    LEFT OUTER JOIN {user_lastaccess} ul
    	ON (r.userid=ul.userid and ul.courseid = ?)
WHERE ((r.contextid = ?)
	OR (r.contextid $csql))
	AND
	u.deleted = 0
    AND (ul.courseid = $course->id OR ul.courseid IS NULL)
    AND u.username != ?
	AND r.roleid $rsql";

	if ($roleid){
		$rq.=" AND r.roleid = ? ";
	}
	if ($userid){
		$rq.=" AND u.id = ? ";
	}

  	// DEBUG
	/*
	echo "<br>EXIT DEBUG :: lib_users.php :: 586<br />PARAMS\n";
    print_r($params);
    echo "<br />SQL&gt;".$rq."<br />\n";
    */

	return $DB->get_records_sql($rq, $params);
}


/**
 * This function returns records list of teachers from course
 *
 * @return objects
 * @todo Finish documenting this function
 **/
function referentiel_get_teachers_course_v2($courseid){
// This function returns records list of teachers from course
// Probleme, elle ne remonte pas les rôles derivés...
global $CFG;
global $DB;
    $teachersids=array();
	if (! $course = $DB->get_record("course", array("id" => "$courseid"))) {
		print_error("Course ID is incorrect");
	}
	if (! $context = get_context_instance(CONTEXT_COURSE, $course->id)) {
		print_error("Context ID is incorrect");
	}
    // enseignants avec droits d'édition
    $role = $DB->get_record("role", array("shortname" => "editingteacher"));
    $users= get_role_users($role->id, $context);
    if ($users){
        foreach($users as $user){
            $teachersids[]->userid=$user->id;
        }
    }
    // enseignant tuteurs
    $role = $DB->get_record("role", array("shortname" => "teacher"));
    $users= get_role_users($role->id, $context);
    if ($users){
        foreach($users as $user){
            $teachersids[]->userid=$user->id;
        }
    }
    return $teachersids;
  }



/**
 * This function returns records list of teachers from course
 *
 * @return objects
 * @todo Finish documenting this function
 **/
function referentiel_get_students_course_v1($courseid, $userid=0, $roleid=0, $quiet=false){
// This function returns records list of students from course
// if $userid != 0 ne retourne que celui-ci
// BUG : IL EN MANQUE !
global $DB;
    $studentsids=array();
	if (! $course = $DB->get_record("course", array("id" => "$courseid"))) {
		if (!$quiet) print_error("Course ID is incorrect");
		else return false;
	}
	if (! $context = get_context_instance(CONTEXT_COURSE, $course->id)) {
		if (!$quiet) print_error("Context ID is incorrect");
		else return false;
	}
    // etudiant

    if ($roleid){
        $users= get_role_users($roleid, $context);
    }
    else{
        $role = $DB->get_record("role", array("shortname" => "student"));
        $users= get_role_users($role->id, $context);
    }
    if ($users){
        foreach($users as $user){
            if ($userid){
                if ($userid==$user->id){
                    $studentsids[]->userid=$user->id;
                }
            }
            else{
                $studentsids[]->userid=$user->id;
            }
        }
    }
    return $studentsids;
  }



/**
 * This function get all user role in current course
 *
 * @param courseid reference course id
 * @return objects
 * @todo Finish documenting this function
 **/
function referentiel_get_course_users($referentiel_instance){
global $DB;
    if ($cm = get_coursemodule_from_instance('referentiel', $referentiel_instance->id, $referentiel_instance->course)) {
		// SQL
		$params = array("$referentiel_instance->course", "$referentiel_instance->course", "guest");
		
	    $rq = "SELECT DISTINCT u.id FROM {user} u
LEFT OUTER JOIN
    {user_lastaccess} ul on (ul.courseid = ?)
	WHERE u.deleted = 0
        AND (ul.courseid = ? OR ul.courseid IS NULL)
        AND u.username != ?";
		// DEBUG
		// echo "<br /> DEBUG <br />\n";
		// echo "<br /> lib_users.php :: referentiel_get_course_users() :: 171<br />SQL&gt;$rq\n";

        $ru=$DB->get_records_sql($rq, $params);
		// print_r($ru);
		// exit;
		return $ru;
	}
	return NULL;
}





?>
