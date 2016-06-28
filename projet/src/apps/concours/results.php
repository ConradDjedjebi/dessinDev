<?php


require_once $_SERVER['DOCUMENT_ROOT'].'/dev/index.php';
use_file('date', PHPEXTENSION);

if(!isset($_GET['concours']))
    redirect(ROOT_DIR);

$page = new HTML\Doc('Affichage des résultats du concours');

$page->addAPI('UIcore');
$page->addAPI('menu');
$page->addAPI('download');
$page->addAPI('tables');

// $page->addStyle('home', __DIR__);

$page->body.= '<div id="page-content-wrapper">';

$page->body.= HTML::container('row', 
		HTML::h1 ('Résultats du concours')
	);

use_file('menu_concours', __DIR__);
$page->body.= menu_concours();

/**
 * Calcule la moyenne des nombres passés en argument
 * 
 * @return int
 */
function avg(...$numbers)
{
    return array_reduce($numbers, function ($pv, $cv) {
            return intval($cv)+$pv;
        }, 0) / count($numbers);
}


try {
    // Affichage des tous les membres
    $tbody = array();
    $list = Prep::query('

SELECT Dessin.numero, comp.nom, date_remise, etat, eval1.note AS noteeva1, eval1.commentaire AS commenteva1, eval2.note AS noteeva2, eval2.commentaire as commenteva2, evalu1.nom as nameeva1,  evalu2.nom as nameeva2 
FROM Dessin
    LEFT JOIN Competiteur AS comp
        ON ref_Competiteur = comp.numero
    LEFT JOIN Evaluation AS eval1
        ON eval1.ref_Dessin = Dessin.numero
    LEFT JOIN Evaluateur AS evalu1
        ON evalu1.numero = eval1.ref_Evaluateur
    LEFT JOIN Evaluation AS eval2
        ON eval2.ref_Dessin = Dessin.numero
    LEFT JOIN Evaluateur AS evalu2
        ON evalu2.numero = eval2.ref_Evaluateur
    WHERE ref_Concours=? AND ((etat="evalué" AND evalu1.numero < evalu2.numero) OR etat="déposé")', $_GET['concours']);
    foreach ($list as $drawing)
    	$tbody[] = [
    		HTML::a('~apps/drawing/download.php?drawing='.$drawing['numero'], HTML::icon('glyphicon glyphicon-floppy-save'), ['title'=>'Télécharger le fichier', 'download'=>true]),
            HTML::noXSS($drawing['numero']),
            HTML::noXSS($drawing['nom']),
            date_create($drawing['date_remise'])->format(date\FRENCH),
            HTML::noXSS($drawing['etat']),
            (isset($drawing['noteeva1'], $drawing['noteeva2']) ? avg($drawing['noteeva1'], $drawing['noteeva2']) : HTML::em('--')),
            HTML::noXSS($drawing['nameeva1']).(isset($drawing['noteeva1']) ? ' ('.$drawing['noteeva1'].')'.(empty($drawing['commenteva1']) ? null : HTML::br().HTML::pre($drawing['commenteva1'], HTML::NO_XSS)) : HTML::em(' (pas encore évalué)')),
            HTML::noXSS($drawing['nameeva2']).' ('.(isset($drawing['noteeva2']) ? $drawing['noteeva2'].')'.(empty($drawing['commenteva2']) ? null : HTML::br().HTML::pre($drawing['commenteva2'], HTML::NO_XSS)) : HTML::em('pas encore évalué)')),
    	];

    $page->body.= HTML::container('row', 
    new HTML\Table([
        'thead'=>[null, 'Numéro', 'Competiteur', 'Date de remise', 'État', 'Moyenne', '1e évaluation', '2e évaluation'],
        'tbody'=>$tbody,
        'options'=>HTML\Table::TITLES_NO_XSS,
        ]));
} catch (Exception $e) {
    $page->body.= HTML::container('row', HTML::container('alert alert-warning', HTML::noXSS('Le chargement de la liste des concours a echoué.')));
}

$page->body.= '</div>';
