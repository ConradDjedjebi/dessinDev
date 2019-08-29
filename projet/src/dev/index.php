<?php
/**
 * Execute les tâches 
 * 
 * @author Antoine du HAMEL
 */

// Vérification de l'âge du fichier de configuration
if(filemtime(__DIR__.DIRECTORY_SEPARATOR.'config.php')<filemtime(__DIR__.DIRECTORY_SEPARATOR.'config.php.save'))
	throw new RuntimeException('The config file is too old');

require 'config.php';

require __DIR__.DIRECTORY_SEPARATOR.'class/autoload.php';
require __DIR__.DIRECTORY_SEPARATOR.'func/index.php';

// Vérification des patchs SQL
// include 'patch/execute.php';
