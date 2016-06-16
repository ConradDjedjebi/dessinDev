<?php
require_once $_SERVER['DOCUMENT_ROOT'].'/dev/index.php';
$page = new HTML\Doc('Paramètres du compte');

$page->addAPI('UIcore');

$page->addAPI('form_handler');
$page->addAPI('menu');


$form = new HTML\Form(__DIR__.DIRECTORY_SEPARATOR.'gest_proced.php');
$form->addFieldsetCustom();
$form->addElement(HTML::legend('Modifier mon mot de passe'));
$form->input(['placeholder'=>'Mot de passe actuel', 'type'=>'password', 'name'=>'currentPass', 'autofocus'=>true, 'required'=>true]);
$form->input(['placeholder'=>'Nouveau mot de passe', 'type'=>'password', 'name'=>'pass1', 'autofocus'=>false, 'required'=>true]);
$form->input(['placeholder'=>'Confirmez le mot de passe', 'type'=>'password', 'name'=>'pass2', 'autofocus'=>false, 'required'=>true]);
$form->submit('Modifier le mot de passe');

$page->body.= '<div id="page-wrapper">';

$page->body.= HTML::container('row', '<h1>Paramètres du compte</h1>');
$page->body.= $form;

$page->body.= '</div>';