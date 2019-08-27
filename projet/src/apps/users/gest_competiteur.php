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
if (exist_plein('nom','adresse', 'email', 'date_naissance'))
{
	try {
		Prep::$PDO->beginTransaction();

		Prep::insert('Competiteur', ['nom','adresse', 'email', 'date_naissance'], $_POST);

		Prep::$PDO->commit();
		$doc->redirect('~apps/home/');
		$doc->exitSuccess('Enregistré');
	} catch (prep\Exception $e) {
		$doc->exitError('Impossible d\'ajouter le competiteur');
	}
}
else
{
    $doc->exitError('Formulaire incomplet');
}
