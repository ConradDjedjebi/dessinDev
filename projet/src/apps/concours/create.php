<?php


require_once '/home/gpi2/public_html/dev/index.php';

$page = new HTML\Doc('Création d\'un concours');
$page->addAPI('menu');
$page->addAPI('form_handler');
$page->addAPI('datepicker');

$page->body.= '<div id="page-content-wrapper">';

$page->body.= HTML::container('row', 
		HTML::h1 ('Créer un nouveau concours').
		HTML::container('alert alert-warning', 'Un seul concours par saison est autorisé.')
	);

$form = new HTML\Form(__DIR__.DIRECTORY_SEPARATOR.'gest_create.php');

$saisons = ['printemps', 'été', 'automne', 'hiver',];
$form->addFieldset('Nouveau concours');
	$form->input(['label'=>'Année', 'type'=>'number', 'name'=>'annee', 'autofocus'=>true, 'value'=>date('Y')]);
	$form->input(['label'=>'Session', 'type'=>'select', 'name'=>'saison', 'other'=>['options'=>array_combine($saisons, $saisons)]]);
	$form->input(['label'=>'Thème', 'name'=>'theme']);
	$form->input(['label'=>'Date de début', 'name'=>'date_debut', 'type'=>'date', 'other'=>['help'=>'Le format attendu est de type AAAA-MM-JJ']]);
	$form->input(['label'=>'Date de fin', 'name'=>'date_fin', 'type'=>'date', 'other'=>['help'=>'Le format attendu est de type AAAA-MM-JJ']]);

$form->submit('Créer le concours');

$page->body.= HTML::container('row', $form);

$page->body.= '</div>';

