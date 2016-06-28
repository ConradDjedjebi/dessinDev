<?php


require_once $_SERVER['DOCUMENT_ROOT'].'/dev/index.php';

if(!isset($_GET['concours']))
	redirect(ROOT_DIR);

use_file('menu_concours', __DIR__);
try {
	$concours = Prep::selectOne(['concours', $_GET['concours'], 'field_ID'=>'numero']);
} catch (prep\QueryFailedException $e) {
	redirect('~apps/home');
}

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

if(Prep::selectCount('Participe', ['ref_Concours'=>$_GET['concours']]) < 5)
	$page->body.= HTML::container('alert alert-danger', 'Le nombre de participants est insuffisant pour ce concours');


$page->body.= '</div>';
