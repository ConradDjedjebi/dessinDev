<?php
namespace HTML;
/**
    ************************ HTML namespace ***************************
    * @author       aduh95
    * @package      HTML
    * @version      1.1
    * @release      Nov 2015
    * @require      PHP >=5.4, MySQL >=5.0
    *******************************************************************
*/

array_map(function ($name) { require __DIR__.DIRECTORY_SEPARATOR.'HTML_namespace'.DIRECTORY_SEPARATOR.$name.'.trait.php'; },
    ['exceptions']);

spl_autoload_register(function ($class) {
    if($class==='HTML')
        include __DIR__.DIRECTORY_SEPARATOR.'HTML.class.php';
    else if (strncmp($class, 'HTML\\', 5)===0 && is_readable($file=__DIR__.DIRECTORY_SEPARATOR.'HTML_namespace'.DIRECTORY_SEPARATOR.substr($class,5).'.class.php'))
        include $file;
});
