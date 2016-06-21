<?php
/**
 * Security functions
 * 
 * @author Antoine du HAMEL
 * @category mixed
 * @package domotique
 * @subpackage global_functions
 */

// You should check if this constant is defined in all the PHP files you include
define('INCLUDED', 1);

function char_alea($size=1)
{
    return substr(strtr(base64_encode(mcrypt_create_iv($size)), '+/', '-_'), 0, $size);
}

function no_cache()
{
    header('Cache-Control: no-cache, no-store, must-revalidate'); // HTTP 1.1.
    header('Pragma: no-cache'); // HTTP 1.0.
    header('Expires: 0'); // Proxies
}

function redirect($extra=ROOT_DIR)
{
    if(headers_sent()) return false;
    header('Location: http'.(empty($_SERVER['HTTPS']) ? null : 's').'://'.$_SERVER['HTTP_HOST'].HTML::relativeLink($extra));
    exit;
}
