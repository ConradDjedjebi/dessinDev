<?php
/**
 * @author Antoine du HAMEL
 * @category backend
 * @package apps/connect
 * @subpackage Formulaire_gestion
 */

const EXPECTED_EVALUATION_NUMBER_PER_DRAWING = 2;

require_once '/home/gpi2/public_html/dev/index.php';
use_file('userfile', PHPEXTENSION);

$doc = new HTML\JSON;

// $doc->exitError(print_r($_POST, true));
// $doc->exitError(exist_plein('saison') ? 'true' : 'false');
if (exist_plein('ref_Concours', 'juries', 'ref_Dessin', 'commentaire', 'note'))
{
	if (count($_POST['juries'])!==EXPECTED_EVALUATION_NUMBER_PER_DRAWING || count($_POST['commentaire'])!==EXPECTED_EVALUATION_NUMBER_PER_DRAWING || count($_POST['note'])!==EXPECTED_EVALUATION_NUMBER_PER_DRAWING)
		$doc->exitError('Vous devez sélectionner deux jurys');

	try {
		// Récupération des infos sur le Dessin
		$dessin = Prep::select('Dessin', ['ref_Concours', 'ref_Competiteur'], ['numero'=>$_POST['ref_Dessin'], 'etat'=>'déposé'])->fetch() or $doc->exitError('Impossible de trouver le dessin');
	} catch (prep\Exception $e) {
		$doc->exitError('Une erreur s\'est produite');
	}

	try {
		foreach ($_POST['juries'] as $jury) {
			// Vérification des contraintes
			$counts = Prep::query('SELECT COUNT(*) FROM Evaluation
				LEFT JOIN Dessin ON ref_Dessin=numero
				WHERE ref_Competiteur=:competiteur AND ref_Evaluateur=:evaluateur AND ref_Concours=:concours

				UNION

				SELECT COUNT(*) FROM Evaluation
				LEFT JOIN Dessin ON ref_Dessin=numero
				WHERE ref_Evaluateur=:evaluateur AND ref_Concours=:concours;',
				[
					'competiteur'=>$dessin['ref_Competiteur'],
					'evaluateur'=>$jury,
					'concours'=>$dessin['ref_Concours']
				]);

			if(intval($counts->fetchColumn()) > 1)
				$doc->exitError(Prep::select('Evaluateur', 'nom', ['numero'=>$jury])->fetchColumn().' a déjà atteint la limite de dessins notés pour ce participant');
			
			if(intval($counts->fetchColumn()) > 7)
				$doc->exitError(Prep::select('Evaluateur', 'nom', ['numero'=>$jury])->fetchColumn().' a déjà atteint la limite de dessins notés pour ce concours');

			try {
				Prep::insert('Jury', ['ref_Evaluateur'=>$jury, 'ref_Concours'=>$dessin['ref_Concours']]);
			} catch (prep\Exception $ignored) {
				// Si le Jury est déjà inscrit pour ce concours, on ignore l'exception levée à cause la duplication des clefs primaires
			}

		}

		Prep::$PDO->beginTransaction();	


		Prep::updateOne(['Dessin', $_POST['ref_Dessin'], ['etat'=>'évalué'], 'field_ID'=>'numero']);

		Prep::insert('Evaluation', ['ref_Dessin'=>$_POST['ref_Dessin'], 'note'=>$_POST['note'][0], 'commentaire'=>$_POST['commentaire'][0], 'ref_Evaluateur'=>$_POST['juries'][0]]);
		Prep::insert('Evaluation', ['ref_Dessin'=>$_POST['ref_Dessin'], 'note'=>$_POST['note'][1], 'commentaire'=>$_POST['commentaire'][1], 'ref_Evaluateur'=>$_POST['juries'][1]]);

		Prep::$PDO->commit();
		$doc->redirect('~apps/concours?concours='.$_POST['ref_Concours']);
		$doc->exitSuccess('Enregistré');
	} catch (prep\Exception $e) {
		$doc->exitError('Impossible d\'enregistrer la note'.$e);
	}
}
else
{
    $doc->exitError('Formulaire incomplet');
}
