<?php
// Style pour la datepicker
namespace APIs\datepicker;

function main($page)
{
	$page->addScript('bootstrap-datepicker', __DIR__);
	$page->addScript('lang.fr', __DIR__);
	$page->addScript('auto-dp', __DIR__);
	$page->addStyle('bootstrap-datepicker3', __DIR__);
}
