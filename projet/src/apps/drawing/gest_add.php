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
if (exist_plein('ref_Concours','date_remise', 'ref_Competiteur', 'juries'))
{
	if(!is_array($_POST['juries']) || count($_POST['juries'])!==2)
		$doc->exitError('Vous devez séléctionner exactement deux évaluateurs');

	Prep::$PDO->beginTransaction();
	try {

		Prep::insert('dessin', ['ref_Concours', 'ref_Competiteur', 'le_dessin'=>userfile\upload('dessin'), 'date_remise'], $_POST);

		$jury = Prep::$PDO->prepare('INSERT INTO evaluation (ref_Dessin, ref_Evaluateur) VALUES ('.Prep::$PDO->lastInsertId().', ?);');
		$jury->execute([$_POST['juries'][0]]);
		$jury->execute([$_POST['juries'][1]]);

		Prep::$PDO->commit();
		$doc->redirect('~apps/concours?concours='.$_POST['ref_Concours']);
		$doc->exitSuccess('Enregistré');
	} catch (prep\Exception $e) {
		Prep::$PDO->rollBack();
		$doc->exitError('Impossible d\'ajouter le dessin');
	}
}
else
{
    $doc->exitError('Formulaire incomplet');
}
