<?php
define('NO_CONNECTION_REQUIRED', 1);

require_once $_SERVER['DOCUMENT_ROOT'].'/dev/index.php';

if(!isset($_GET['concours']))
	redirect(ROOT_DIR);
use_file('menu_concours', PROJECT_ROOT.DIRECTORY_SEPARATOR.'apps/concours');

$page = new HTML\Doc('Soumettre un dessin');
$page->addAPI('menu');
$page->addAPI('form_handler');

$page->body.= '<div id="page-content-wrapper">';

$page->body.= HTML::container('row', HTML::h1('Ajouter un nouveau dessin'));
$page->body.= menu_concours();


try {
	$form = new HTML\Form(__DIR__.DIRECTORY_SEPARATOR.'gest_add.php');

	$form->addFieldset();
		$form->hidden('ref_Concours', intval($_GET['concours']));
		$form->input(['label'=>'Déposer le dessin', 'type'=>'file', 'name'=>'dessin','accept'=>'application/xml']);
		$form->hidden('MAX_FILE_SIZE', 1<<10<<10);
		$form->input(['label'=>'Date du dessin', 'name'=>'date_remise', 'type'=>'date', 'placeholder'=>'AAAA-MM-JJ']);

		$form->input(['name'=>'ref_Competiteur', 'type'=>'select', 'other'=>['label'=>'Auteur du dessin', 'options'=>
			Prep::selectAll(['competiteur', 'WHERE'=>['ref_Concours'=>$_GET['concours']], 'JOIN'=>Prep::SQL('INNER JOIN participe ON ref_Competiteur=numero'), 'style'=>PDO::FETCH_COLUMN|PDO::FETCH_UNIQUE, 'argument'=>1])]]);

		$form->input(['name'=>'commentaire', 'type'=>'textarea', 'label'=>'commentaire']);

		// $form->input(['name'=>'juries[]', 'type'=>'select', 'multiple'=>true, 'other'=>[
		// 		'options'=>Prep::selectAll(['evaluateur', 'WHERE'=>['ref_Concours'=>$_GET['concours']], 'JOIN'=>Prep::SQL('INNER JOIN jury ON ref_Evaluateur=numero'), 'style'=>PDO::FETCH_COLUMN|PDO::FETCH_UNIQUE, 'argument'=>1]),
		// 		'help'=>'Vous devez choisir exactement deux jurys',
		// 		'label'=>'Choix des jurys',
		// 	]]);

	$form->submit('Soumettre');
} catch (prep\Exception $e) {
	$form = HTML::container('alert alert-danger', 'Aucun jury n\'est inscrit, il n\'est pas possible d\'ajouter un dessin');
}

$page->body.= HTML::container('row', $form);

$page->body.= '</div>';

