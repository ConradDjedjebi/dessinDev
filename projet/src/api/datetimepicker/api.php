<?php
// Style pour la datepicker
namespace APIs\datetimepicker;

function main($page)
{
	$page->addScript('moment.min', '~api/datetimepicker');
	$page->addScript('fr', '~api/datetimepicker/locale');
	$page->addScript('datetimepicker.min', '~api/datetimepicker');
	$page->addScript('datetimepicker', '~api/datetimepicker');
	$page->addStyle('datetimepicker', '~api/datetimepicker');
}