<?php
/**
 * Vérifie les identifiants d'un utilisateur
 *
 * @author Antoine du HAMEL
 * @category backend
 * @package apps/connect
 * @subpackage Formulaire_gestion
 */
define('NO_CONNECTION_REQUIRED', 1);

require_once $_SERVER['DOCUMENT_ROOT'].'/dev/index.php';

if (exist_plein('login','mdp'))
{
    $doc = new HTML\JSON('Connexion établie');
    try {
		if(!$_SESSION->connect($_POST['login'], $_POST['mdp']))
	        $doc->exitError('Nom d\'utilisateur ou mot de passe non valide');
	// } catch (prep\MySQLErrorException $e) {
	// 	$doc->exitError('Erreur '.$e->getMySQLError());
	}
	catch (Exception $e) {
		$doc->exitError('Connexion impossible, une erreur s\'est produite.'.$e);
	}

	
    // On renvoie vers la page demandant la connexion ou sinon vers la page principale
    $doc->redirect(isset($_SESSION->data['redirect']) ? 'http://'.$_SERVER['HTTP_HOST'] . $_SESSION->data['redirect'] : '~');

    $doc->exitSuccess('Connexion établie');
}
else
{
    // Fermeture de la session en cours
    $_SESSION->close();
    redirect();
}
