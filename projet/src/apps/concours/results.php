<?php
define('NO_CONNECTION_REQUIRED', 1);

require_once $_SERVER['DOCUMENT_ROOT'].'/dev/index.php';
use_file('date', PHPEXTENSION);
use_file('userfile', PHPEXTENSION);

if(!isset($_GET['concours']))
    redirect(ROOT_DIR);

$page = new HTML\Doc('Affichage des résultats du concours');

$page->addAPI('UIcore');
$page->addAPI('menu');
$page->addAPI('tables');

// $page->addStyle('home', __DIR__);

$page->body.= '<div id="page-content-wrapper">';

$page->body.= HTML::container('row', 
		HTML::h1 ('Résultats du concours')
	);

use_file('menu_concours', __DIR__);
$page->body.= menu_concours();


try {
    // Affichage des tous les membres
    $tbody = array();
    $list = Prep::query('

SELECT Dessin.numero, date_remise, etat, eval1.note AS noteeva1, eval1.commentaire AS commenteva1, eval2.note AS noteeva2, eval2.commentaire as commenteva2, evalu1.nom as nameeva1,  evalu2.nom as nameeva2 
FROM Dessin
    LEFT JOIN Evaluation AS eval1
        ON eval1.ref_Dessin = Dessin.numero
    LEFT JOIN Evaluation AS eval2
        ON eval2.ref_Dessin = Dessin.numero
    LEFT JOIN Evaluateur AS evalu1
        ON evalu1.numero = eval1.ref_Evaluateur
    LEFT JOIN Evaluateur AS evalu2
        ON evalu2.numero = eval2.ref_Evaluateur
    WHERE evalu1.numero < evalu2.numero');
    foreach ($list as $drawing)
    	$tbody[] = [
    		HTML::a('~apps/drawing/download.php?drawing='.$drawing['numero'], HTML::icon('glyphicon glyphicon-save'), ['title'=>'Télécharger le fichier']),
            HTML::noXSS($drawing['numero']),
            (new DateTime($drawing['date_remise']))->format(date\FRENCH),
            HTML::noXSS($drawing['etat']),
            HTML::noXSS($drawing['nameeva1']).(isset($drawing['noteeva1']) ? ' ('.$drawing['noteeva1'].')'.HTML::br().HTML::pre(HTML::noXSS($drawing['commenteva1'])) : HTML::em(' (pas encore évalué)')),
            HTML::noXSS($drawing['nameeva2']).(isset($drawing['noteeva2']) ? '('.$drawing['noteeva2'].')'.HTML::br().HTML::pre(HTML::noXSS($drawing['commenteva2'])) : HTML::em(' (pas encore évalué)')),
    	];

    $page->body.= HTML::container('row', 
    new HTML\Table([
        'thead'=>[null, 'Numéro', 'Date de remise', 'État', '1e évaluation', '2e évaluation'],
        'tbody'=>$tbody,
        'options'=>HTML\Table::TITLES_NO_XSS | HTML\Table::TABLELINK,
        ]));
} catch (Exception $e) {
    $page->body.= HTML::container('row', HTML::container('alert alert-warning', HTML::noXSS('Le chargement de la liste des concours a echoué.')));
}

$page->body.= '</div>';
