<?php
define('NO_CONNECTION_REQUIRED', 1);

require_once $_SERVER['DOCUMENT_ROOT'].'/dev/index.php';

if(!isset($_GET['concours']))
	redirect(ROOT_DIR);

$page = new HTML\Doc('Soumettre un dessin');
$page->addAPI('menu');
$page->addAPI('form_handler');

$page->body.= '<div id="page-content-wrapper">';

try {
	$form = new HTML\Form(__DIR__.DIRECTORY_SEPARATOR.'gest_ajouterDessin.php');

	$form->addFieldset('Ajouter un dessin');
		$form->hidden('concours', intval($_GET['concours']));
		$form->input(['label'=>'Déposer le dessin', 'type'=>'file', 'name'=>'dessin','accept'=>'applicattion/xml']);
		$form->hidden('MAX_FILE_SIZE', 1<<10<<10);
		$form->input(['label'=>'Date du dessin', 'name'=>'date_remise', 'type'=>'date']);

		$form->input(['name'=>'juries[]', 'type'=>'select', 'multiple'=>true, 'other'=>[
				'options'=>Prep::selectAll(['evaluateur', 'style'=>PDO::FETCH_COLUMN|PDO::FETCH_UNIQUE, 'argument'=>1]),
				'help'=>'Vous devez choisir exactement deux jurys',
				'label'=>'Choix des jurys',
			]]);

	$form->submit('Soumettre');
} catch (prep\Exception $e) {
	$form = HTML::container('alert alert-danger', 'Aucun jury n\'est inscrit, il n\'est pas possible d\'ajouter un dessin');
}

$page->body.= HTML::container('row', $form);

$page->body.= '</div>';

