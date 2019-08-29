<?php
/**
 * @author Antoine du HAMEL
 * @category backend
 * @package apps/connect
 * @subpackage Formulaire_gestion
 */
require_once '/home/gpi2/public_html/dev/index.php';

$doc = new HTML\JSON;

// $doc->exitError(print_r($_POST, true));
// $doc->exitError(exist_plein('saison') ? 'true' : 'false');
if (exist_plein('nom','adresse', 'email', 'telephone'))
{
	if(!$_POST['telephone']=verif('phone', $_POST['telephone']))
		$doc->exitError('Numéro de téléphone invalide');
	
	if(!$_POST['email']=verif('mail', $_POST['email']))
		$doc->exitError('Adresse e-mail invalide');

	try {
		Prep::$PDO->beginTransaction();

		Prep::insert('Evaluateur', ['nom','adresse', 'email', 'telephone'], $_POST);

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
