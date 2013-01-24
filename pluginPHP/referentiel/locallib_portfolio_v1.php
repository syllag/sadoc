<?php

// This file is part of Moodle - http://moodle.org/
//
// Moodle is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// Moodle is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with Moodle.  If not, see <http://www.gnu.org/licenses/>.

/**
 * Library of functions for forum outside of the core api
 */

require_once($CFG->dirroot . '/mod/referentiel/lib.php');
require_once($CFG->dirroot . '/mod/referentiel/locallib.php');
require_once($CFG->libdir . '/portfolio/caller.php');
require_once($CFG->libdir.'/filelib.php');

/**
 * @package   mod-referentiel
 * @copyright 1999 onwards Martin Dougiamas  {@link http://moodle.com}
 * @copyright 2011 Jean Fruitet  {@link http://univ-nantes.fr}
 * @license   http://www.gnu.org/copyleft/gpl.html GNU GPL v3 or later
 */
class referentiel_portfolio_caller extends portfolio_module_caller_base {

    protected $instanceid;
    protected $userid;
    protected $fullpath;

    private $referentiel;
    /**
     * @return array
     */
    public static function expected_callbackargs() {
        return array(
            'instanceid' => false,
            'userid'   => false,
            'fullpath'   => false,
        );
    }
    /**
     * @param array $callbackargs
     */
    function __construct($callbackargs) {
        parent::__construct($callbackargs);
        if (!$this->instanceid) {
            throw new portfolio_caller_exception('mustprovideinstance', 'referentiel');
        }
        if (!$this->userid) {
            throw new portfolio_caller_exception('mustprovideuser', 'referentiel');
        }
        if (!$this->fullpath) {
            throw new portfolio_caller_exception('mustprovidefullpath', 'referentiel');
        }

    }
    /**
     * @global object
     */
    public function load_data() {
        global $DB;
        global $CFG;
        if ($this->instanceid) {
            if (!$this->referentiel = $DB->get_record('referentiel', array('id' => $this->instanceid))) {
                throw new portfolio_caller_exception('invalidinstanceid', 'referentiel');
            }
        }

        // initialisation par defaut
        $contextid=0;
        $component='mod_referentiel';
        $filearea='referentiel';
        $itemid=0;
        $path='/';
        $filename=$fullpath;

        // Traitement de $fullpath
        if ($fullpath && preg_match('/\//', $fullpath)){
            $t_fullpath=explode('/',$fullpath,6);
            if (!empty($t_fullpath) && empty($t_fullpath[0])){
                $garbage=array_shift($t_fullpath);
            }
            if (!empty($t_fullpath)){
                list($contextid, $component, $filearea, $itemid, $path )  = $t_fullpath;
                if ($path){
                    if (preg_match('/\//', $path)){
                        $filename=substr($path, strrpos($path, '/')+1);
                        $path='/'.substr($path, 0, strrpos($path, '/')+1);
                    }
                    else{
                        $filename=$path;
                        $path='/';
                    }
                }
            }
        }

        // echo "<br>DEBUG :: lib.php :: Ligne 5918 ::<br> $contextid, $component, $filearea, $itemid, $path, $filename\n";
        // devrait afficher cas 0  :: 0, mod_referentiel, referentiel, 0, /, jf44.png
        // devrait afficher cas 1  :: 30, mod_referentiel, referentiel, 0, /rep1/rep2/, jf44.png
        // devrait afficher cas 2  :: 51, mod_referentiel, referentiel, 12, /, jf44.png


        $fs = get_file_storage();

        // Get file
        $file = $fs->get_file($contextid, $component, $filearea, $itemid, $path, $filename);

        // Delete it if it exists
        if (!$file) {
            throw new portfolio_caller_exception('filedonotexist', 'referentiel');
        }
        $this->multifiles = array($file);
        // depending on whether there are files or not, we might have to change richhtml/plainhtml
        $this->add_format(PORTFOLIO_FORMAT_PLAINHTML);

    }

    /**
     * @global object
     * @return string
     */
    function get_return_url() {
        global $CFG;
        return $CFG->wwwroot . '/mod/referentiel/certificat.php?d='.$this->referentiel->id.'&select_acc=0&mode=archive';
    }
    /**
     * @global object
     * @return array
     */
    function get_navigation() {
        global $CFG;

        $navlinks = array();
        $navlinks[] = array(
            'name' => format_string($this->referentiel->name),
            'link' => $CFG->wwwroot . '/mod/referentiel/certificat.php?d='.$this->referentiel->id.'&select_acc=0&mode=archive',
            'type' => 'title'
        );
        return array($navlinks, $this->cm);
    }
    /**
     * either a whole discussion
     * a single post, with or without attachment
     * or just an attachment with no post
     *
     * @global object
     * @global object
     * @uses PORTFOLIO_FORMAT_RICH
     * @return mixed
     */
 function prepare_package() {
        return $this->get('exporter')->copy_existing_file($this->singlefile);
    }
    /**
     * helper function to add a leap2a entry element
     * that corresponds to a single referentiel post,
     * including any attachments
     *
     * the entry/ies are added directly to the leapwriter, which is passed by ref
     *
     * @param portfolio_format_leap2a_writer $leapwriter writer object to add entries to
     * @param object $post                               the stdclass object representing the database record
     * @param string $posthtml                           the content of the post (prepared by {@link prepare_post}
     *
     * @return int id of new entry
     */
    private function prepare_post_leap2a(portfolio_format_leap2a_writer $leapwriter, $post, $posthtml) {
        $entry = new portfolio_format_leap2a_entry('referentielpost' . $post->id,  $post->subject, 'resource', $posthtml);
        $entry->published = $post->created;
        $entry->updated = $post->modified;
        $entry->author = $post->author;
        if (is_array($this->keyedfiles) && array_key_exists($post->id, $this->keyedfiles) && is_array($this->keyedfiles[$post->id])) {
            $leapwriter->link_files($entry, $this->keyedfiles[$post->id], 'referentielpost' . $post->id . 'attachment');
        }
        $entry->add_category('web', 'resource_type');
        $leapwriter->add_entry($entry);
        return $entry->id;
    }

    /**
     * @param array $files
     * @param mixed $justone false of id of single file to copy
     * @return bool|void
     */
    private function copy_files($files, $justone=false) {
        if (empty($files)) {
            return;
        }
        foreach ($files as $f) {
            if ($justone && $f->get_id() != $justone) {
                continue;
            }
            $this->get('exporter')->copy_existing_file($f);
            if ($justone && $f->get_id() == $justone) {
                return true; // all we need to do
            }
        }
    }
    /**
     * this is a very cut down version of what is in referentiel_make_mail_post
     *
     * @global object
     * @param int $post
     * @return string
     */
    private function prepare_post($post, $fileoutputextras=null) {
        global $DB;
        static $users;
        if (empty($users)) {
            $users = array($this->user->id => $this->user);
        }
        if (!array_key_exists($post->userid, $users)) {
            $users[$post->userid] = $DB->get_record('user', array('id' => $post->userid));
        }
        // add the user object on to the post so we can pass it to the leap writer if necessary
        $post->author = $users[$post->userid];
        $viewfullnames = true;
        // format the post body
        $options = portfolio_format_text_options();
        $format = $this->get('exporter')->get('format');
        $formattedtext = format_text($post->message, $post->messageformat, $options, $this->get('course')->id);
        $formattedtext = portfolio_rewrite_pluginfile_urls($formattedtext, $this->modcontext->id, 'mod_referentiel', 'post', $post->id, $format);

        $output = '<table border="0" cellpadding="3" cellspacing="0" class="referentielpost">';

        $output .= '<tr class="header"><td>';// can't print picture.
        $output .= '</td>';

        if ($post->parent) {
            $output .= '<td class="topic">';
        } else {
            $output .= '<td class="topic starter">';
        }
        $output .= '<div class="subject">'.format_string($post->subject).'</div>';

        $fullname = fullname($users[$post->userid], $viewfullnames);
        $by = new stdClass();
        $by->name = $fullname;
        $by->date = userdate($post->modified, '', $this->user->timezone);
        $output .= '<div class="author">'.get_string('bynameondate', 'referentiel', $by).'</div>';

        $output .= '</td></tr>';

        $output .= '<tr><td class="left side" valign="top">';

        $output .= '</td><td class="content">';

        $output .= $formattedtext;

        if (is_array($this->keyedfiles) && array_key_exists($post->id, $this->keyedfiles) && is_array($this->keyedfiles[$post->id]) && count($this->keyedfiles[$post->id]) > 0) {
            $output .= '<div class="attachments">';
            $output .= '<br /><b>' .  get_string('attachments', 'referentiel') . '</b>:<br /><br />';
            foreach ($this->keyedfiles[$post->id] as $file) {
                $output .= $format->file_output($file)  . '<br/ >';
            }
            $output .= "</div>";
        }

        $output .= '</td></tr></table>'."\n\n";

        return $output;
    }
    /**
     * @return string
     */
    function get_sha1() {
        $filesha = '';
        try {
            $filesha = $this->get_sha1_file();
        } catch (portfolio_caller_exception $e) { } // no files

        if ($this->post) {
            return sha1($filesha . ',' . $this->post->subject . ',' . $this->post->message);
        } else {
            $sha1s = array($filesha);
            foreach ($this->posts as $post) {
                $sha1s[] = sha1($post->subject . ',' . $post->message);
            }
            return sha1(implode(',', $sha1s));
        }
    }

    function expected_time() {
        $filetime = $this->expected_time_file();
        if ($this->posts) {
            $posttime = portfolio_expected_time_db(count($this->posts));
            if ($filetime < $posttime) {
                return $posttime;
            }
        }
        return $filetime;
    }
    /**
     * @uses CONTEXT_MODULE
     * @return bool
     */
    function check_permissions() {
        $context = get_context_instance(CONTEXT_MODULE, $this->cm->id);
        if ($this->post) {
            return (has_capability('mod/referentiel:exportpost', $context)
                || ($this->post->userid == $this->user->id
                    && has_capability('mod/referentiel:exportownpost', $context)));
        }
        return has_capability('mod/referentiel:exportdiscussion', $context);
    }
    /**
     * @return string
     */
    public static function display_name() {
        return get_string('modulename', 'referentiel');
    }

    public static function base_supported_formats() {
        return array(PORTFOLIO_FORMAT_FILE, PORTFOLIO_FORMAT_RICHHTML, PORTFOLIO_FORMAT_PLAINHTML, PORTFOLIO_FORMAT_LEAP2A);
    }
}
