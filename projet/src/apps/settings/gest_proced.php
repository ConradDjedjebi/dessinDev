<?php
require_once $_SERVER['DOCUMENT_ROOT'].'/dev/index.php';

if (exist_plein('currentPass', 'pass1', 'pass2'))
{
    use_file('mail', PHPEXTENSION);
    use_file('date', PHPEXTENSION);

    $reponse = new HTML\JSON('Le mot de passe a été modifié');
    if (strlen($_POST['pass1'])<6)
        $reponse->exitError('Le nouveau mot de passe doit faire plus de 6 caractères');
    if ($_POST['pass1']!=$_POST['pass2'])
        $reponse->exitError('Les deux mots de passe ne corespondent pas');
    if ($_POST['pass1']==$_POST['currentPass'])
        $reponse->exitError('Le nouveau mot de passe doit être différent de l\'ancien');

    try {
        $user = Prep::selectOne('users__list', $_SESSION->get_id(), ['email', 'nom', 'prenom', 'password']);

        if (!password_verify($_POST['currentPass'], $user['password']))
            $reponse->exitError('Le mot de passe est incorrect');
    } catch (Exception $e) {
        $reponse->exitError();
    }

    try {
        Prep::updateOne('users__list', $_SESSION->get_id(), ['password'=>password_hash($_POST['pass1'], PASSWORD_BCRYPT)]);

        email($user['email'], 'Votre mot de passe a été modifié', 'mail_modif_mdp.md', ['host'=>$_SERVER['HTTP_HOST'], 'mail'=>$user['email'], 'name'=>getName($user), 'date'=>date\now(date\FRENCH_HMS)]);
    } catch (Exception $e) {
        $reponse->exitError();
    }
}