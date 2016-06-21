<?php
define('NO_CONNECTION_REQUIRED', 1);

require_once $_SERVER['DOCUMENT_ROOT'].'/dev/index.php';

$page = new HTML\Doc('Ajouter un membre du jury');
$page->addAPI('menu');
$page->addAPI('form_handler');

$page->body.= '<div id="page-content-wrapper">';

$form = new HTML\Form(__DIR__.DIRECTORY_SEPARATOR.'gest_ajouterJury.php');

$form->addFieldset('Ajouter un jury');
	$form->input(['required'=>true, 'label'=>'Nom', 'name'=>'nom', 'autofocus'=>true]);
	
	$form->input(['required'=>true, 'label'=>'Adresse', 'name'=>'adresse', 'type'=>'textarea']);
	$form->input(['required'=>true, 'label'=>'Courriel', 'name'=>'email', 'type'=>'email']);
	$form->input(['required'=>true, 'label'=>'Numéro de téléphone', 'name'=>'telephone', 'type'=>'number']);

$form->submit('Ajouter à la base');

$page->body.= HTML::container('row', $form);

$page->body.= '</div>';

