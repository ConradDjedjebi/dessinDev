<?php
define('NO_CONNECTION_REQUIRED', 1);

require_once $_SERVER['DOCUMENT_ROOT'].'/dev/index.php';

if(!isset($_GET['concours']))
	redirect(ROOT_DIR);

use_file('menu_concours', PROJECT_ROOT.DIRECTORY_SEPARATOR.'apps/concours');
$page = new HTML\Doc('Noter un dessin');
$page->addAPI('menu');
$page->addAPI('form_handler');
$page->addScript('evaluate', __DIR__);
$page->addStyle('evaluate', __DIR__);

$page->body.= '<div id="page-content-wrapper">';

$page->body.= HTML::container('row', HTML::h1('Évaluer un dessin'));
$page->body.= menu_concours();

try {
	$concours = Prep::selectOne(['concours', $_GET['concours'], 'field_ID'=>'numero']);
} catch (prep\Exception $e) {
	$page->body.= HTML::container('alert alert-danger', 'Le concours est introuvable');
	$page->body.= '</div>';
	exit;
}

try {
	$form = new HTML\Form(__DIR__.DIRECTORY_SEPARATOR.'gest_evaluate.php');

	$form->addFieldset('Noter un dessin');
		$form->hidden('ref_Concours', $concours['numero']);
		$form->input(['disabled'=>'true', 'label'=>'Concours', 'value'=>$concours['theme'].' ('.$concours['saison'].' '.$concours['annee'].')']);

		try {
			$drawings = Prep::selectAll(['dessin', ['numero', 'numero'], 'where'=>['ref_Concours'=>$_GET['concours'], 'etat'=>'déposé'], 'style'=>PDO::FETCH_COLUMN|PDO::FETCH_UNIQUE, 'argument'=>1]);
		} catch (prep\Exception $e) {
			throw new RuntimeException('Aucun dessin n\'est inscrit, il n\'est pas possible de noter un dessin', 1, $e);
		}
			
		$form->input(['name'=>'ref_Dessin', 'type'=>'select', 'other'=>[
				'options'=>['Choisissez un dessin à noter']+$drawings,
				'label'=>'Choix du dessin',
			]]);

		$juries = array();
		try {
			$juriesAlready = Prep::selectAll(['evaluateur', ['numero', 'nom'], 'WHERE'=>['ref_Concours'=>$_GET['concours']], 'JOIN'=>Prep::SQL('INNER JOIN jury ON ref_Evaluateur=numero'), 'style'=>PDO::FETCH_COLUMN|PDO::FETCH_UNIQUE, 'argument'=>1]);
			$juries['Jury ayant noté au moins un dessin dans ce concours'] = $juriesAlready;
		} catch (prep\QueryFailedException $e) {
			$juriesAlready = [];
		}

		try {
			$where = count($competiteurAlready) ? [[Prep::MAIN_TABLE, 'numero', 'value'=>array_keys($juriesAlready), 'operator'=>false]] : null;
			$juriesMaybecome = Prep::selectAll(['evaluateur', ['numero', 'nom'], 'WHERE'=>$where, 'style'=>PDO::FETCH_COLUMN|PDO::FETCH_UNIQUE, 'argument'=>1]);
			$competiteurs['Jury n\'ayant noté aucun dessin dans ce concours'] = $competiteurMaybecome;
		} catch (prep\QueryFailedException $e) {
			$juriesMaybecome = [];
		}
		
		if(empty($juries))
			throw new RuntimeException('Aucun jury n\'est inscrit, il n\'est pas possible de noter un dessin');
			
		$form->input(['name'=>'juries[]', 'id'=>'juries', 'type'=>'select', 'multiple'=>true, 'other'=>[
				'options'=>array_reverse($juries),
				'help'=>'Vous devez choisir exactement deux jurys',
				'label'=>'Choix des jurys',
			]]);
		
	$form->addFieldset('Premier jury');
		$form->input(['label'=>'Note', 'name'=>'note[]', 'type'=>'number']);
		$form->input(['label'=>'Commentaire', 'name'=>'commentaire[]', 'type'=>'textarea']);
		
	$form->addFieldset('Deuxième jury');
		$form->input(['label'=>'Note', 'name'=>'note[]', 'type'=>'number']);
		$form->input(['label'=>'Commentaire', 'name'=>'commentaire[]', 'type'=>'textarea']);

	$form->addFieldset();
	$form->submit('Soumettre');
} catch (RuntimeException $e) {
	$page->body.= HTML::h3('Noter un dessin');
	$form = HTML::container('alert alert-danger', $e->getMessage());
}

$page->body.= HTML::container('row', $form);

$page->body.= '</div>';

