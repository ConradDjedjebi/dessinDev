<?php
namespace APIs\UIcore;

function main($doc)
{
    $doc->addScript('compatibility', '~api/UIcore', false);

    $doc->addScript('jquery.min', 'https://ajax.googleapis.com/ajax/libs/jquery/2.2.4', false);
    $doc->directScript('window.jQuery||document.write("<scr"+"ipt src=\''.\HTML::relativeLink('~api/UIcore/jquery.min.js').'\'></scr"+"ipt>");//--></script><script><!--
');

    $doc->addScript('bootstrap.min', '//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js', false);
    // $doc->directScript('$.fn.modal||document.write("<script src=\''.\HTML::relativeLink('~api/UIcore/bootstrap.min.js').'\'><\/script>");');
    $doc->addStyle('bootstrap.min', '//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css');
    // $doc->directScript('if($(document.body).css("color")!=="rgb(51, 51, 51)")$(document.head).prepend(\'<link rel="stylesheet" href="'.\HTML::relativeLink('~api/UIcore/bootstrap.min.css').'"/>\');');

    // $doc->addStyle('font-awesome.min', 'https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/');
    $doc->addStyle('simple-sidebar', '~api/UIcore');

    // $doc->addScript('metisMenu.min', 'api/UIcore');
    // $doc->addStyle('metisMenu.min', 'api/UIcore');

    $doc->addStyle('font-awesome.min', 'https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css');

    // $doc->addStyle('jquery-ui.min', 'http://127.0.0.1/jquery-ui-1.11.4');
    // $doc->addScript('jquery-ui.min', 'http://127.0.0.1/jquery-ui-1.11.4');
}