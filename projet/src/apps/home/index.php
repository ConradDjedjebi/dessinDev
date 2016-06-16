<?php
define('NO_CONNECTION_REQUIRED', 1);

require_once $_SERVER['DOCUMENT_ROOT'].'/dev/index.php';

$page = new HTML\Doc('Acceuil');

$page->addAPI('UIcore');
$page->addAPI('menu');

// $page->addStyle('home', __DIR__);

$page->body.= '<div id="page-content-wrapper">';

$page->body.= HTML::container('row', 
		HTML::h1 ('Administration des concours "BeAnArtist"').
		HTML::container('alert alert-info', 'Cette page est réservée aux administrateurs du concours. Merci de ne rien modifier sans leur accord express.')
	);

$menu = [
    		'Créer un concours' => '~apps/home',
            'Ajouter un membre au jury' => '~apps/home',
            'Ajouter un competiteur' => '~apps/home',
    		'Accéder au concours' => '~apps/home',
    	];
$liste = array();

foreach ($menu as $label => $link)
	$liste[]= HTML::a($link, $label);

	
$page->body.= HTML::container('row', 
		HTML::p('Vous pouvez accéder aux différentes sections du site :').
		HTML::liste('ul', array(), $liste)
	);

$page->body.= '</div>';
