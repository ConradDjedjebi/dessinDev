<?php
/**
 * Autoload functions for the classes of the project
 * 
 * @author      Antoine du HAMEL
 * @project 	intraJE
 */


foreach (new DirectoryIterator(__DIR__) as $content) {
	if(!$content->isDot() && $content->isDir())
	{
		if(is_readable($file = $content->getPathname().DIRECTORY_SEPARATOR.'autoload.php'))
			include $file;
	}
}

spl_autoload_register(function ($classname) {
	if(is_readable($file = __DIR__.DIRECTORY_SEPARATOR.$classname.'.class.php'))
		include $file;
});
