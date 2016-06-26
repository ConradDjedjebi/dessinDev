<?php


require_once $_SERVER['DOCUMENT_ROOT'].'/dev/index.php';

if(!isset($_GET['concours']))
	redirect(ROOT_DIR);
use_file('menu_concours', PROJECT_ROOT.DIRECTORY_SEPARATOR.'apps/concours');

$page = new HTML\Doc('Soumettre un dessin');
$page->addAPI('menu');
$page->addAPI('datepicker');
$page->addAPI('form_handler');

$page->body.= '<div id="page-content-wrapper">';

$page->body.= HTML::container('row', HTML::h1('Ajouter un nouveau dessin'));
$page->body.= menu_concours();

try {
	$concours = Prep::selectOne(['concours', $_GET['concours'], 'field_ID'=>'numero']);
} catch (prep\Exception $e) {
	$page->body.= HTML::container('alert alert-danger', 'Le concours est introuvable');
	$page->body.= '</div>';
	exit;
}

try {
	$form = new HTML\Form(__DIR__.DIRECTORY_SEPARATOR.'gest_add.php');

	$form->addFieldset();
		$form->hidden('ref_Concours', $concours['numero']);
		$form->input(['disabled'=>'true', 'label'=>'Concours', 'value'=>$concours['theme'].' ('.$concours['saison'].' '.$concours['annee'].')']);
		$form->input(['label'=>'Déposer le dessin', 'type'=>'file', 'name'=>'dessin','accept'=>'image/*']);
		$form->hidden('MAX_FILE_SIZE', 1<<10<<10);
		$form->input(['label'=>'Date du dessin', 'name'=>'date_remise', 'min'=>$concours['date_debut'], 'max'=>$concours['date_fin'], 'type'=>'date', 'placeholder'=>'AAAA-MM-JJ']);

		

		$competiteurs = array();

		try {
			$competiteurAlready = Prep::selectAll(['competiteur', ['numero', 'nom'], 'WHERE'=>['ref_Concours'=>$_GET['concours']], 'JOIN'=>Prep::SQL('INNER JOIN participe ON ref_Competiteur=numero'), 'style'=>PDO::FETCH_COLUMN|PDO::FETCH_UNIQUE, 'argument'=>1]);
			$competiteurs['Participant au concours'] = $competiteurAlready;
		} catch (prep\QueryFailedException $e) {
			$competiteurAlready = [];
		}

		try {
			$where = count($competiteurAlready) ? [[Prep::MAIN_TABLE, 'numero', 'value'=>array_keys($competiteurAlready), 'operator'=>false]] : null;
			$competiteurMaybecome = Prep::selectAll(['competiteur', ['numero', 'nom'], 'WHERE'=>$where, 'style'=>PDO::FETCH_COLUMN|PDO::FETCH_UNIQUE, 'argument'=>1]);
			$competiteurs['Pas encore inscrits au concours'] = $competiteurMaybecome;
		} catch (prep\QueryFailedException $e) {
			$competiteurMaybecome = [];
		}

		if(empty($competiteurs))
			throw new RuntimeException('Aucun participant n\'est inscrit, il n\'est pas possible d\'ajouter un dessin.');
			
		$form->input([
				'name'=>'ref_Competiteur',
				'type'=>'select',
				'other'=>[
					'label'=>'Auteur du dessin',
					'options'=> $competiteurs,
				]
			]);

		$form->input(['name'=>'commentaire', 'type'=>'textarea', 'label'=>'commentaire']);

	$form->submit('Soumettre');
} catch (RuntimeException $e) {
	$form = HTML::container('alert alert-danger', $e->getMessage());
}

$page->body.= HTML::container('row', $form);

$page->body.= '</div>';

