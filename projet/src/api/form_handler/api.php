<?php
namespace APIs\form_handler;

function main($doc)
{
	$doc->addAPI('UIcore');

    $doc->addScript('send_form', __DIR__);
	$doc->addScript('msg', __DIR__);
	$doc->addStyle('msg', __DIR__);
	
	$doc->addStyle('form', __DIR__);
}
