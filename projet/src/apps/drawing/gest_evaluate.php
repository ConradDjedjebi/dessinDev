<?php
/**
 * @author Antoine du HAMEL
 * @category backend
 * @package apps/connect
 * @subpackage Formulaire_gestion
 */
define('NO_CONNECTION_REQUIRED', 1);

require_once $_SERVER['DOCUMENT_ROOT'].'/dev/index.php';
use_file('userfile', PHPEXTENSION);

$doc = new HTML\JSON;

// $doc->exitError(print_r($_POST, true));
// $doc->exitError(exist_plein('saison') ? 'true' : 'false');
if (exist_plein('ref_Evaluateur', 'ref_Dessin', 'commentaire') && exist('note'))
{
	if(!is_array($_POST['juries']) || count($_POST['juries'])!==2)
		$doc->exitError('Vous devez séléctionner exactement deux évaluateurs');

	try {
		Prep::$PDO->beginTransaction();

		Prep::update('evaluation', ['note'=>$_POST['note'], 'commentaire'=>$_POST['commentaire']], ['ref_Dessin'=>$_POST['ref_Dessin'], 'ref_Evaluateur'=>$_POST['ref_Evaluateur']]);

		Prep::$PDO->commit();
		$doc->redirect(__DIR__);
		$doc->exitSuccess('Enregistré');
	} catch (prep\Exception $e) {
		$doc->exitError('Impossible d\'enregistrer la note');
	}
}
else
{
    $doc->exitError('Formulaire incomplet');
}
