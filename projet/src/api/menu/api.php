<?php
namespace APIs\menu;
use \HTML;

function main($doc)
{
    $doc->addAPI('UIcore');
	// $doc->addStyle('menu', __DIR__);

	$menu = [
            'BeAnArtist' => '~apps/home',
    		'Créer un concours' => '~apps/home',
            'Ajouter un membre au jury' => '~apps/home',
            'Ajouter un competiteur' => '~apps/home',
    		'Accéder au concours' => '~apps/home',
    	];
    $liste = array();

    foreach ($menu as $label => $link)
    	$liste[]= HTML::a($link, $label);

	
    $doc->body.= HTML::div(['id'=>'sidebar-wrapper'], HTML::liste('ul', array('class'=>"sidebar-nav"), $liste));
 
}
