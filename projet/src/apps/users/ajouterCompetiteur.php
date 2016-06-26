<?php


require_once $_SERVER['DOCUMENT_ROOT'].'/dev/index.php';

$page = new HTML\Doc('Ajouter un competiteur');
$page->addAPI('menu');
$page->addAPI('form_handler');
$page->addAPI('datepicker');

$page->body.= '<div id="page-content-wrapper">';

$form = new HTML\Form(__DIR__.DIRECTORY_SEPARATOR.'gest_competiteur.php');

$form->addFieldset('Ajouter un competiteur');
	$form->input(['label'=>'Nom', 'name'=>'nom', 'autofocus'=>true]);
	
	$form->input(['label'=>'Adresse', 'name'=>'adresse', 'type'=>'textarea']);
	$form->input(['label'=>'Courriel', 'name'=>'email', 'type'=>'email']);
	$form->input(['label'=>'Date de naissance', 'name'=>'date_naissance', 'type'=>'date', 'placeholder'=>'AAAA-MM-JJ']);

$form->submit('Ajouter à la base');

$page->body.= HTML::container('row', $form);

$page->body.= '</div>';

