<?php
/**
 * @author Antoine du HAMEL
 */
define('NO_CONNECTION_REQUIRED', 1);
require_once $_SERVER['DOCUMENT_ROOT'].'/dev/index.php';

use_file('userfile', PHPEXTENSION);

if(!empty($_GET['drawing']) || exist_plein('drawing'))
{
	$drawing = exist_plein('drawing') ? $_POST['drawing'] : $_GET['drawing'];
	$drawing = userfile\relativeLink(Prep::query('SELECT le_dessin FROM Dessin WHERE numero=?', $drawing)->fetchColumn());

	if(exist_plein('format'))
	{
		$doc = new HTML\JSON;

		$doc['MIME'] = userfile\getMIME($drawing);
		if($_POST['format']==='base64')
			$doc['img'] = base64_encode(file_get_contents($drawing));

		$doc->exitSuccess('Image chargée');
	}
	userfile\download($drawing);
}
