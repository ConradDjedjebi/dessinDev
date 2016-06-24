<?php
define('NO_CONNECTION_REQUIRED', 1);

require_once $_SERVER['DOCUMENT_ROOT'].'/dev/index.php';

if(!isset($_GET['concours']))
	redirect(ROOT_DIR);

use_file('menu_concours', __DIR__);
$concours = Prep::selectOne(['concours', $_GET['concours'], 'field_ID'=>'numero']);

$page = new HTML\Doc('Gérer le concours');

$page->addAPI('UIcore');
$page->addAPI('menu');

// $page->addStyle('home', __DIR__);

$page->body.= '<div id="page-content-wrapper">';

$page->body.= HTML::container('row', 
		HTML::h1 ('Administration du concours '.HTML::noXSS($concours['theme'].' ('.$concours['saison'].' '.$concours['annee'].')')).
		HTML::p('Vous pouvez accéder aux différentes sections pour ce concours :')
	);


$page->body.= menu_concours();

$page->body.= '</div>';
