<?php
// Style pour la datepicker
namespace APIs\datepicker;

function main($page)
{
	$page->addScript('bootstrap-datepicker', '~api/datepicker');
	$page->addScript('bootstrap-datepicker.fr.min', '~api/datepicker');
	$page->addStyle('bootstrap-datepicker3', '~api/datepicker');

}