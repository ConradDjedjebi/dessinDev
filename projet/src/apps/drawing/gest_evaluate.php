<?php
/**
 * @author Antoine du HAMEL
 * @category backend
 * @package apps/connect
 * @subpackage Formulaire_gestion
 */
define('NO_CONNECTION_REQUIRED', 1);
const EXPECTED_EVALUATION_NUMBER_PER_DRAWING = 2;

require_once $_SERVER['DOCUMENT_ROOT'].'/dev/index.php';
use_file('userfile', PHPEXTENSION);

$doc = new HTML\JSON;

// $doc->exitError(print_r($_POST, true));
// $doc->exitError(exist_plein('saison') ? 'true' : 'false');
if (exist_plein('juries', 'ref_Dessin', 'commentaire', 'note') && count($_POST['juries'])===EXPECTED_EVALUATION_NUMBER_PER_DRAWING && count($_POST['commentaire'])===EXPECTED_EVALUATION_NUMBER_PER_DRAWING && count($_POST['note'])===EXPECTED_EVALUATION_NUMBER_PER_DRAWING)
{
	try {
		Prep::$PDO->beginTransaction();	

		Prep::updateOne(['Dessin', $_POST['ref_Dessin'], ['etat'=>'évalué'], 'ID_field'=>'numero']);

		Prep::insert('evaluation', ['ref_Dessin'=>$_POST['ref_Dessin'], 'note'=>$_POST['note'][0], 'commentaire'=>$_POST['commentaire'][0], 'ref_Evaluateur'=>$_POST['juries'][0]]);
		Prep::insert('evaluation', ['ref_Dessin'=>$_POST['ref_Dessin'], 'note'=>$_POST['note'][1], 'commentaire'=>$_POST['commentaire'][1], 'ref_Evaluateur'=>$_POST['juries'][1]]);

		Prep::$PDO->commit();
		$doc->redirect('~apps/concours?concours='.$_POST['ref_Concours']);
		$doc->exitSuccess('Enregistré');
	} catch (prep\Exception $e) {
		$doc->exitError('Impossible d\'enregistrer la note');
	}
}
else
{
    $doc->exitError('Formulaire incomplet');
}
