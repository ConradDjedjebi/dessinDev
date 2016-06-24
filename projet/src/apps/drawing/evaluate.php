<?php
define('NO_CONNECTION_REQUIRED', 1);

require_once $_SERVER['DOCUMENT_ROOT'].'/dev/index.php';

if(!isset($_GET['concours']))
	redirect(ROOT_DIR);

use_file('menu_concours', PROJECT_ROOT.DIRECTORY_SEPARATOR.'apps/concours');
$page = new HTML\Doc('Noter un dessin');
$page->addAPI('menu');
$page->addAPI('form_handler');

$page->body.= '<div id="page-content-wrapper">';

$page->body.= HTML::container('row', HTML::h1('Évaluer un dessin'));
$page->body.= menu_concours();

try {
	$form = new HTML\Form(__DIR__.DIRECTORY_SEPARATOR.'gest_evaluate.php');

	$form->addFieldset('Noter un dessin');
		$form->hidden('ref_Concours', intval($_GET['concours']));

		$form->input(['name'=>'ref_Dessin', 'type'=>'select', 'other'=>[
				'options'=>Prep::selectAll(['dessin', 'where'=>['ref_Concours'=>$_GET['concours']/*, 'etat'=>''*/], 'style'=>PDO::FETCH_COLUMN|PDO::FETCH_UNIQUE, 'argument'=>1]),
				'label'=>'Choix du dessin',
			]]);
	$form->input(['name'=>'ref_Evaluateur', 'type'=>'select', 'other'=>[
				'options'=>Prep::selectAll(['evaluateur', 'style'=>PDO::FETCH_COLUMN|PDO::FETCH_UNIQUE, 'argument'=>1]),
				'label'=>'Choix du jury',
			]]);
		
	$form->input(['label'=>'Note', 'name'=>'note', 'type'=>'number']);
	$form->input(['label'=>'Commentaire', 'name'=>'commentaire', 'type'=>'textarea']);

	$form->submit('Soumettre');
} catch (prep\Exception $e) {
	$page->body.= HTML::h3('Noter un dessin');
	$form = HTML::container('alert alert-danger', 'Aucun dessin ou jury n\'est inscrit, il n\'est pas possible de noter un dessin');
}

$page->body.= HTML::container('row', $form);

$page->body.= '</div>';

