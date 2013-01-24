<?php // $Id: version.php,v 1.3 2006/08/28 16:41:20 mark-nielsen Exp $
/**
 * Code fragment to define the version of referentiel
 * This fragment is called by moodle_needs_upgrading() and /admin/index.php
 *
 * @author 
 * @version $Id: version.php,v 1.3 2011/06/01 16:41:20 jf Exp $
 * @package referentiel Moodle 2
 **/



$module->requires = 2010080300;  // Requires this Moodle version
$module->version  = 2011112615;  // The current module version (Date: YYYYMMDDXX)
$module->release  =  '2.2.04 - 2012/01/31';    // User-friendly date of release
$module->cron     =  60; //  Period for cron to check this module (secs)

?>
