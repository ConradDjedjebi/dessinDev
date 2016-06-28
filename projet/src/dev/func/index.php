<?php
/**
 * Core of intraJE. You should require this file a the begining of most of the files of the project
 * 
 * @author aduh95
 * @category mixed
 * @package domotique
 * @subpackage global_functions
 */

define('PHPEXTENSION', __DIR__);


if (!PROD_ENVIRONEMENT)
{
	/**
	 * Debug a variable and stop the execution of the script
	 * @param mixed $var
	 */
	function debug($var)
	{
	    var_dump($var);
	    exit;
	}
}

if(PROD_ENVIRONEMENT)
	set_exception_handler(function($e){
	    echo '<!--/* Uncaught exception on line '.$e->getLine().': '.$e->getMessage().'*/-->';
	    exit('Le chargement de la page a &eacute;chou&eacute;, nous vous invitons &agrave; r&eacuteessayer ult&eacute;rieurement. Veuillez nous excuser pour le d&eacute;sagr&eacute;ment.
'.$e);
	});

/**
 * Include a PHP file
 * 
 * @param string $name Do not precise the extension ".php"
 * @param string $dir  Directory from the project root
 * 
 * @return void
 */
function use_file($name, $dir)
{
	require_once $dir.DIRECTORY_SEPARATOR.$name.'.php';
}


array_map(function($f) {
    use_file($f, PHPEXTENSION);
} , ['security', 'isset', 'format']);

// Connexion à MySQL
Prep::$PDO = Prep::connect(CONFIG\MYSQL\DB, CONFIG\MYSQL\USER, CONFIG\MYSQL\PWD, CONFIG\MYSQL\SERVER);
Session::retrieve();
