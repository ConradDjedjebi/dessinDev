<?php
/**
 * @author Antoine du HAMEL
 */

require_once '/home/gpi2/public_html/dev/index.php';

use_file('userfile', PHPEXTENSION);

if(!empty($_GET['drawing']) || exist_plein('drawing'))
{
	$drawing = exist_plein('drawing') ? $_POST['drawing'] : $_GET['drawing'];
	$drawing = Prep::query('SELECT le_dessin FROM Dessin WHERE numero=?', $drawing)->fetchColumn();

	if(exist_plein('format'))
	{
		$doc = new HTML\JSON;

		if($drawing)
			$drawing = userfile\relativeLink($drawing);
		else
			$doc->exitError('Dessin introuvable');

		$doc['MIME'] = userfile\getMIME($drawing);
		if($_POST['format']==='base64')
			$doc['img'] = base64_encode(file_get_contents($drawing));

		$doc->exitSuccess('Image chargée');
	}
	else
		userfile\download($drawing);
}
else
	new HTML\JSON;
