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
        ON evalu2.numero = eval2.ref_Evaluateur');
    foreach ($list as $concours)
    	$tbody[] = [
    		HTML\Table::link(['concours'=>$concours['numero']], HTML::icon('fa-download')),
            HTML::noXSS($concours['numero']),
            (new DateTime($concours['date_remise']))->format(date\FRENCH),
            HTML::noXSS($concours['etat']),
            HTML::noXSS(getName($concours['IDeva1']).'('.$concours['noteeva1'].')').HTML::br().HTML::pre(HTML::noXSS($concours['commenteva1'])),
            HTML::noXSS(getName($concours['IDeva2']).'('.$concours['noteeva2'].')').HTML::br().HTML::pre(HTML::noXSS($concours['commenteva2'])),
    	];

    $page->body.= HTML::container('row', 
    new HTML\Table([
        ['data-href'=>HTML::relativeLink('~apps/home/gestionConcours.php'), 'data-fenetre'=>true],
        'thead'=>[null, 'Numéro', 'Date de remise', 'État', '1e évaluation', '2e évaluation'],
        'tbody'=>$tbody,
        'options'=>HTML\Table::TITLES_NO_XSS | HTML\Table::TABLELINK,
        ]));
} catch (Exception $e) {
    $page->body.= HTML::container('row', HTML::container('alert alert-warning', HTML::noXSS('Le chargement de la liste des concours a echoué.')));
}

$page->body.= '</div>';
