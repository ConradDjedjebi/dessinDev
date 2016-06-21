<?php
/**
 * @author Antoine du HAMEL
 * @category backend
 * @package apps/connect
 * @subpackage Formulaire_gestion
 */
define('NO_CONNECTION_REQUIRED', 1);

require_once $_SERVER['DOCUMENT_ROOT'].'/dev/index.php';

$doc = new HTML\JSON;

// $doc->exitError(print_r($_POST, true));
// $doc->exitError(exist_plein('saison') ? 'true' : 'false');
if (exist_plein('nom','adresse', 'email', 'telephone'))
{
	try {
		Prep::$PDO->beginTransaction();

		Prep::insert('evaluateur', ['nom','adresse', 'email', 'telephone'], $_POST);

		Prep::$PDO->commit();
		$doc->redirect('~apps/home/');
		$doc->exitSuccess('Enregistré');
	} catch (prep\Exception $e) {
		$doc->exitError('Impossible d\'ajouter le jury');
	}
}
else
{
    $doc->exitError('Formulaire incomplet');
}
