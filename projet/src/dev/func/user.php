<?php

function getName($user)
{
    try {
        $donnees = is_array($user) ? $user : Prep::selectOne('users__list', $user, ['nom', 'prenom']);
        return HTML::noXSS($donnees['prenom']. ' ' . $donnees['nom']);
    }
    catch (Exception $e) {
        return 'ERREUR';
    }
}

function getNomFamille($id)
{
    try {
        $donnees = Prep::selectOne('users__list', $id, 'nom');
        return HTML::noXSS($donnees['nom']);
    }
    catch (Exception $e) {
        return 'ERREUR';
    }
}

function getPersoInfo($id)
{
    $keys = ['nom', 'prenom', 'email', 'telephone', 'inscription', 'promotion', 'IBAN'];
    try {
        return Prep::selectOne(['users__list', $id, $keys, 'style'=>PDO::FETCH_ASSOC]);
    } catch (Exception $e) {
        return array_fill_keys($keys, '');
    }
}

function getPromotion($id)
{
    try {
        return Prep::selectOne([
            'users__promotions',
            $id,
            Prep::SQL('CONCAT(nom, " ", annee, " (", niveau, ")")')])[0];
    } catch (Exception $e) {
        return '';
    }
}

function getPromotions($where=false)
{
    try {
        return Prep::selectAll([
            'users__promotions',
            Prep::SQL('ID, CONCAT(nom, " ", annee, " (", niveau, ")")'),
            $where,
            'order_by'=>'annee',
            'style'=>PDO::FETCH_COLUMN|PDO::FETCH_UNIQUE, 'argument'=>1]);

    } catch (Exception $e) {
        return [];
    }
}

function getTeam()
{
	try {
		return array_reduce(Prep::selectAll([
            'users__list',
            array('ID', 'nom', 'prenom'), 
            'join'=>['users__members'=>'ID'],
            'where'=>[['column'=>'droits', 'operator'=>'&', 'value'=>Session::TEAM, 'table'=>Prep::MAIN_TABLE]],
            'order_by'=>'promotion',
            ]),
        function($return, $cv) {
            $return[$cv['ID']] = getName($cv);
            return $return;
        }
        , array());
	} catch (Exception $e) {
		return [];
	}
}

