<?php
define('NO_CONNECTION_REQUIRED', 1);

require_once $_SERVER['DOCUMENT_ROOT'].'/dev/index.php';

$page = new HTML\Doc('Création d\'un concours');
$page->addAPI('menu');

$page->body.= '<div id="page-content-wrapper">';

$page->body.= HTML::container('row', 
		HTML::h1 ('Créer un nouveau concours').
		HTML::container('alert alert-warning', 'Un seul concours par saison est autorisé.')
	);

$form = new HTML\Form(__DIR__.DIRECTORY_SEPARATOR.'gest_creerConcours.php');

$form->addFieldset('Nouveau concours');
	$form->input(['label'=>'Année', 'type'=>'year', 'name'=>'annee']);
	$form->input(['label'=>'Session', 'type'=>'select', 'name'=>'saison', 'other'=>['options'=>[
			'été', 'primtemps', 'hiver'
		], 'help'=>'Indiquer la saison']]);
	$form->input(['label'=>'Thème', 'name'=>'theme']);
	$form->input(['label'=>'Date de début', 'name'=>'date_debut', 'type'=>'date']);
	$form->input(['label'=>'Date de fin', 'name'=>'date_fin', 'type'=>'date']);

$form->submit('Créer le concours');

$page->body.= HTML::container('row', $form);

$page->body.= '</div>';

