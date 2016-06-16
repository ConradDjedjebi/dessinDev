<?php
namespace APIs\download;

function main($page)
{
	$page->addAPI('ajaxfriendly');
	$page->addScript('download.min', '~api/download');
}