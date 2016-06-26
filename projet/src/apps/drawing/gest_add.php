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
if (exist_plein('ref_Concours','date_remise', 'ref_Competiteur') && exist('commentaire'))
{
	Prep::$PDO->beginTransaction();

	try {
		Prep::insert('Participe', ['ref_Concours', 'ref_Competiteur'], $_POST);
	} catch (prep\Exception $ignored) {
		// S'il est déjà inscrit, une exception est levée
	}

	if(intval(Prep::query('SELECT COUNT(*) FROM Dessin
							LEFT JOIN Competiteur c ON ref_Competiteur=c.numero
							WHERE ref_Competiteur=? AND ref_Concours=?;',
				[$_POST['ref_Competiteur'], $_POST['ref_Concours']])
					->fetchColumn()) > 2)
				$doc->exitError(
					Prep::select('Comepetiteur', 'nom', ['numero'=>$jury])->fetchColumn().' a déjà atteint la limite de dessins pour ce concours.');

	try {
		Prep::insert('dessin', ['ref_Concours', 'ref_Competiteur', 'commentaire', 'le_dessin'=>userfile\upload('dessin'), 'date_remise'], $_POST);

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
