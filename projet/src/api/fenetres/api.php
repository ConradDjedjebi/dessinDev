<?php
namespace APIs\fenetres;
use \HTML;

function main($page)
{
    $page->addAPI('ajaxfriendly');

	$page->addStyle('fenetres', '~api/fenetres');
    $page->addScript('fenetres.min', '~api/fenetres');

    $page->body.= HTML::div(['class'=>'modal fade', 'role'=>'dialog', 'id'=>'modalWindow', 'tabindex'=>-1],
        HTML::container('modal-dialog',
            HTML::container('modal-content',

                HTML::container('modal-header',
                    HTML::button(['class'=>'close', 'data-dismiss'=>'modal'], '&times;').
                    HTML::h4(['class'=>'modalWindowTitle'], 'Chargement en cours&hellip;')
                ) .

                HTML::container('modal-body', null) .

                HTML::container('modal-foot', HTML::button(['class'=>'btn btn-default', 'data-dismiss'=>'modal'], 'Fermer'))
            )
        ));
}