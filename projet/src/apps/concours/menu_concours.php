<?php

function menu_concours() {
	$menu = [
	    		'Liste des concours' => __DIR__.DIRECTORY_SEPARATOR.'index.php',
	    		'Ajouter un dessin' => '~apps/drawing/add.php',
	            'Ajouter une note' => '~apps/drawing/evaluate.php',
	            'Afficher les résultats' => __DIR__.DIRECTORY_SEPARATOR.'results.php',
	    	];
	$liste = array();

	foreach ($menu as $label => $link)
		$liste[]= HTML::a($link.'?concours='.$_GET['concours'], $label, ['class'=>'btn btn-default'.($_SERVER['SCRIPT_NAME']===HTML::relativeLink($link) ? ' active disabled' : null)]);

	return HTML::container('row', 
			HTML::container('btn-group', implode('', $liste)).HTML::hr()
		);
	
}
