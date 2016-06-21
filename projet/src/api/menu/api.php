<?php
namespace APIs\menu;
use \HTML;

function main($doc)
{
    $doc->addAPI('UIcore');
	// $doc->addStyle('menu', __DIR__);

	$menu = [
            'BeAnArtist' => '~apps/home',
            'Liste des concours' => '~apps/concours/list.php',
            'Créer un concours' => '~apps/concours/create.php',
            'Inscrire un jury' => '~apps/users/ajouterJury.php',
            'Inscrire un competiteur' => '~apps/users/ajouterCompetiteur.php',
    	];
    $liste = array();

    foreach ($menu as $label => $link)
    	$liste[]= HTML::a($link, $label);

	
    $doc->body.= HTML::div(['id'=>'sidebar-wrapper'], HTML::liste('ul', array('class'=>"sidebar-nav"), $liste));
 
}
