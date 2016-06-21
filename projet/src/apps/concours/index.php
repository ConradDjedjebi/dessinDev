<?php
define('NO_CONNECTION_REQUIRED', 1);

require_once $_SERVER['DOCUMENT_ROOT'].'/dev/index.php';

if(!isset($_GET['concours']))
	redirect(ROOT_DIR);

$concours = Prep::selectOne(['concours', $_GET['concours'], 'field_ID'=>'numero']);

$page = new HTML\Doc('Gérer le concours');

$page->addAPI('UIcore');
$page->addAPI('menu');

// $page->addStyle('home', __DIR__);

$page->body.= '<div id="page-content-wrapper">';

$page->body.= HTML::container('row', 
		HTML::h1 ('Administration du concours '.HTML::noXSS($concours['theme'].' ('.$concours['saison'].' '.$concours['annee'].')'))
	);

$menu = [
    		'Ajouter un dessin' => '~apps/drawing/add.php',
            'Ajouter une note' => '~apps/drawing/evaluate.php',
            'Afficher les résultats' => __DIR__.DIRECTORY_SEPARATOR.'results.php',
    	];
$liste = array();

foreach ($menu as $label => $link)
	$liste[]= HTML::a($link.'?concours='.$_GET['concours'], $label);

	
$page->body.= HTML::container('row', 
		HTML::p('Vous pouvez accéder aux différentes sections pour ce concours :').
		HTML::liste('ul', array(), $liste)
	);

$page->body.= '</div>';
