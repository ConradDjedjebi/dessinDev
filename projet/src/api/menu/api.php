<?php
namespace APIs\menu;
use \HTML;

function main($doc)
{
    $doc->addAPI('UIcore');
	// $doc->addStyle('menu', __DIR__);

	$menu = [
            'BeAnArtist' => '~apps/home',
            'Liste des concours' => '~apps/home/listerConcours.php',
            'Créer un concours' => '~apps/home/creerConcours.php',
            'Inscrire un jury' => '~apps/home/ajouterJury.php',
            'Inscrire un competiteur' => '~apps/home/ajouterCompetiteur.php',
    	];
    $liste = array();

    foreach ($menu as $label => $link)
    	$liste[]= HTML::a($link, $label);

	
    $doc->body.= HTML::div(['id'=>'sidebar-wrapper'], HTML::liste('ul', array('class'=>"sidebar-nav"), $liste));
 
}
