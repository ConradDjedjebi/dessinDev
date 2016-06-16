<?php
namespace APIs\tables;

function main($doc)
{
	$doc->addAPI('UIcore');

	$doc->addScript('datatables.min', 'https://cdn.datatables.net/u/dt/dt-1.10.12,b-1.2.1,r-2.1.0');
	$doc->addStyle('datatables.min', 'https://cdn.datatables.net/u/dt/dt-1.10.12,b-1.2.1,r-2.1.0');
}
