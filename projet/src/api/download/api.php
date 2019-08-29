<?php
namespace APIs\download;

function main($page)
{
	$page->addAPI('form_handler');
	$page->addScript('download', __DIR__);
	$page->addScript('autoDL', __DIR__);
}
