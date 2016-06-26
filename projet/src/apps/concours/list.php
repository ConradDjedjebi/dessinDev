<?php


require_once $_SERVER['DOCUMENT_ROOT'].'/dev/index.php';
use_file('date', PHPEXTENSION);

$page = new HTML\Doc('Liste des concours');

$page->addAPI('UIcore');
$page->addAPI('menu');
$page->addAPI('tables');

// $page->addStyle('home', __DIR__);

$page->body.= '<div id="page-content-wrapper">';

$page->body.= HTML::container('row', 
		HTML::h1 ('Liste des concours existants')
	);
	
try {
    // Affichage des tous les membres
    $tbody = array();
    $list = Prep::selectAll('concours');
    foreach ($list as $concours)
    	$tbody[] = [
    		HTML\Table::link(['concours'=>$concours['numero']], HTML::noXSS($concours['saison'].' '.$concours['annee'])),
    		HTML::noXSS($concours['theme']),
    		(new DateTime($concours['date_debut']))->format(date\FRENCH),
    		(new DateTime($concours['date_fin']))->format(date\FRENCH),
    	];

    $page->body.= HTML::container('row', 
    new HTML\Table([
        ['data-href'=>HTML::relativeLink(__DIR__), 'data-fenetre'=>true],
        'thead'=>['Concours', 'Thème', 'Date de début', 'Date de fin'],
        'tbody'=>$tbody,
        'options'=>HTML\Table::TITLES_NO_XSS | HTML\Table::TABLELINK,
        ]));
} catch (Exception $e) {
    $page->body.= HTML::container('row', HTML::container('alert alert-warning', HTML::noXSS('Le chargement de la liste des concours a echoué.')));
}

$page->body.= '</div>';
