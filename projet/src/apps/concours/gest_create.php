<?php
/**
 * @author Antoine du HAMEL
 * @category backend
 * @package apps/connect
 * @subpackage Formulaire_gestion
 */


require_once '/home/gpi2/public_html/dev/index.php';
use_file('date', PHPEXTENSION);

$doc = new HTML\JSON;

// $doc->exitError(print_r($_POST, true));
// $doc->exitError(exist_plein('saison') ? 'true' : 'false');
if (exist_plein('annee','saison', 'theme', 'date_debut', 'date_fin'))
{
	try {
		Prep::insert('Concours', ['annee', 'saison', 'theme', 'date_debut', 'date_fin'], $_POST);
		$doc->redirect('~apps/concours/?concours='.Prep::$PDO->lastInsertId());
		$doc->exitSuccess('Enregistré');
	} catch (prep\Exception $e) {
		$doc->exitError('Impossible d\'enregistrer le concours');
	}
}
else
{
    $doc->exitError('Formulaire incomplet');
}
