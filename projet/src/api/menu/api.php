<?php
namespace APIs\menu;
use \HTML;

function main($doc)
{
    $doc->addAPI('UIcore');
	// $doc->addStyle('menu', __DIR__);

	$menu = [
            'BeAnArtist' => '~apps/home',
    		'Créer un concours' => '~apps/home/creerConcours.php',
            'Ajouter un membre au jury' => '~apps/home/ajouterJury.php',
            'Ajouter un competiteur' => '~apps/home/ajouterCompetiteur.php',
            'Accéder aux concours' => '~apps/home/listerConcours.php',
    	];
    $liste = array();

    foreach ($menu as $label => $link)
    	$liste[]= HTML::a($link, $label);

	
    $doc->body.= HTML::div(['id'=>'sidebar-wrapper'], HTML::liste('ul', array('class'=>"sidebar-nav"), $liste));
 
}
