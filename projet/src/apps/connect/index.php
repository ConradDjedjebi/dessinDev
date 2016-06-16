<?php
/**
 * Génère le formulaire de connexion
 *
 * @author Antoine du HAMEL
 * @category frontend
 * @package apps/connect
 * @subpackage Formulaire_generation
 */
define('NO_CONNECTION_REQUIRED', 1);

require_once $_SERVER['DOCUMENT_ROOT'].'/dev/index.php';
$page = new HTML\Doc('Login');

// $page->addAPI('UIcore');
$page->addAPI('form_handler');
$page->addStyle('connect', __DIR__);
// $page->addScript('connect', '~apps/connect');

$page->body.= <<<'HTML'
    <header>
        <h1>RESEAU <br> DOMOTIQUE</h1>
    </header>
HTML;
$page->body.= <<<'HTML'
    <div class="login">
        <form action="log.php" method="post">
            <br><br>
            <label>Identifiant</label>
            <input name="login" name="login" type="text" required autofocus >
            <label>Mot de passe</label>
            <input name="mdp" type="password" name="password" required> 
            
            <input value="Accéder" type="submit">
            
        </form>
    </div>
HTML;
