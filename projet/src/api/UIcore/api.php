<?php
namespace APIs\UIcore;

function main($doc)
{
    $doc->addScript('compatibility', __DIR__, false);
    $doc->addStyle('global', __DIR__);

    $doc->addScript('jquery.min', __DIR__, false);
    $doc->addScript('bootstrap.min', __DIR__, false);
    $doc->addStyle('bootstrap.min', __DIR__, false);

    // $doc->addStyle('font-awesome.min', 'https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/');
    $doc->addStyle('simple-sidebar', __DIR__);

    // $doc->addStyle('font-awesome.min', 'https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css');
}