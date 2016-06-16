<?php
/**
 * Fonction de formatage est de vérification du bon formatage
 * 
 * @author aduh95
 * @category mixed
 * @package domotique
 * @subpackage global_functions
 */

/**
 * Formate un numéro de téléphone pour l'affichage
 * 
 * @param int $number
 * 
 * @return string|NULL
 */
function phone($number)
{
	if (empty($number)) 
		return null;

	return chunk_split('0'.$number, 2, ' ');
}

/**
 * Formate un IBAN pour l'affichage
 * 
 * @param string $iban L'IBAN de l'utilisateur à formater
 * 
 * @return string L'IBAN formater
 */
function IBAN_format($iban)
{
	return chunk_split($iban, 4, ' ');
}

/**
 * Vérifie le formatage d'un numéro de tel, d'une adresse mail ou d'une date
 * @param string $type Accepte mail, phone, mobile
 * @param string $chaine_a_tester
 * 
 * @return string|FALSE
 */
function verif ($type, $string)
{
	if(empty($string))
		return false;
	switch ($type)
	{
		case 'mail':
		return preg_match("#^[a-zA-Z0-9._-]+@[a-z0-9._-]{2,}\.[a-z]{2,4}$#", $string) ? $string : false;

		case 'phone':
		if ($string[0]=='0' && preg_match('#^0[1-9]\d{8}$#', $string)) // \d <=> [0-9]
			return intval($string);
		else return false;
		// else if (preg_match('#^.[1-9]{0,3} \d+$#', $string)) // num étrangers
		// 	return intval(str_replace(' ','00',substr($string,1)));

		case 'mobile':
		if ($string[0]=='0' && preg_match('#^0[67]\d{8}$#', $string)) // \d <=> [0-9]
			return intval($string);
		else return false;

		default: return false;
	}
}