<?php
define('NO_CONNECTION_REQUIRED', 1);

require_once $_SERVER['DOCUMENT_ROOT'].'/dev/index.php';

$page = new HTML\Doc('Ajouter un membre du jury');
$page->addAPI('menu');

$page->body.= '<div id="page-content-wrapper">';

$form = new HTML\Form(__DIR__.DIRECTORY_SEPARATOR.'gest_ajouterCompetiteur.php');

$form->addFieldset('Ajouter un competiteur');
	$form->input(['label'=>'Nom', 'name'=>'nom', 'autofocus'=>true]);
	
	$form->input(['label'=>'Adresse', 'name'=>'adresse', 'type'=>'textarea']);
	$form->input(['label'=>'Courriel', 'name'=>'email', 'type'=>'email']);
	$form->input(['label'=>'Numéro de téléphone', 'name'=>'telephone', 'type'=>'text']);

$form->submit('Ajouter à la base');

$page->body.= HTML::container('row', $form);

$page->body.= '</div>';

