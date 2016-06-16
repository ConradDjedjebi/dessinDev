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
	    $_SESSION->log(substr($e, 0, 1<<10), $e->getTraceAsString(), 'exception');
	    
	    exit('Le chargement de la page a &eacute;chou&eacute;, nous vous invitons &agrave; r&eacuteessayer ult&eacute;rieurement. Veuillez nous excuser pour le d&eacute;sagr&eacute;ment.');
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

/**
 * Include a class file
 * 
 * @param string $className Name of the class. The file is supposed to be in the ~/class/ directory and named <className>.class.php
 */
function use_class($name)
{
	// use_file(strtolower($name).'.class', PROJECT_ROOT.'/class');
}

use_class('prep');
use_class('html');
use_class('session');

array_map(function($f) {
    use_file($f, PHPEXTENSION);
} , ['security', 'isset', 'user', 'format']);

// Connexion à MySQL
Prep::$PDO = Prep::connect(CONFIG\MYSQL\DB, CONFIG\MYSQL\USER, CONFIG\MYSQL\PWD, CONFIG\MYSQL\SERVER);
Session::retrieve();
