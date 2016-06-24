<?php
/**
 * @author Antoine du HAMEL
 */
define('NO_CONNECTION_REQUIRED', 1);
require_once $_SERVER['DOCUMENT_ROOT'].'/dev/index.php';

use_file('userfile', PHPEXTENSION);

if(!empty($_GET['drawing']))
{
	userfile\download(Prep::query('SELECT le_dessin FROM Dessin WHERE numero=?', $_GET['drawing'])->fetchColumn());
}
