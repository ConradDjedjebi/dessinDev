<?php
/**
 * Génère le formulaire de connexion
 * 
 * @author Antoine du HAMEL
 * @category frontend
 * @package apps/connect
 * @subpackage Formulaire_generation
 */
define('NO_CONNECTION_REQUIRED', 1);

require_once '/home/gpi2/public_html/dev/index.php';

$page = new HTML\Doc('Connexion requise');

$page->addAPI('UIcore');
$page->addAPI('form_handler');
$page->addStyle('connect', __DIR__);
// $page->addScript('connect', '~apps/connect');

$form = new HTML\Form(__DIR__.DIRECTORY_SEPARATOR.'log.php');
    $form->input(['placeholder'=>'Identifiant', 'name'=>'user', 'autofocus'=>true, 'required'=>true]);
    $form->input(['placeholder'=>'Mot de passe', 'name'=>'password', 'type'=>'password', 'required'=>true]);
    $form->submit('Se connecter');

$page->body.= HTML::container('container', 
    HTML::container('row', 
        HTML::container('col-sm-6 col-md-4 col-sm-offset-3 col-md-offset-4',
            HTML::container('login-panel panel panel-default', 
                HTML::container('panel-heading', 
                    HTML::a('//'.$_SERVER['HTTP_HOST'], 'Concours BeAnArtist')) .
                HTML::container('panel-body',
                    HTML::video(['width'=>320, 'height'=>180, 'autoplay'=>true],
                        HTML::source(['src'=>HTML::relativeLink('~images/vid.mp4'), 'type'=>'video/mp4']).
                        'Votre navigateur ne peut pas afficher cette animation.').
                    $form))))
    );
