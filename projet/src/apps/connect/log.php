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

require_once '/home/gpi2/public_html/dev/index.php';

if (exist_plein('user','password'))
{
    $doc = new HTML\JSON;
	if(!$_SESSION->connect($_POST['user'], $_POST['password']))
        $doc->exitError('Login ou mot de passe non valide');

    // On renvoie vers la page demandant la connexion ou sinon vers la page principale
    $doc->redirect(isset($_SESSION['redirect']) ? 'http://'.$_SERVER['HTTP_HOST'] . $_SESSION['redirect'] : '~');
    $doc->exitSuccess('Connexion établie');
}
else
{
    $_SESSION->close();
    redirect();
}
