<?php
// Faster isset functions
function exist()
{
    foreach(func_get_args() as $value)
    {
        if (!isset($_POST[$value]))
            return false;
    }
    return true;
}

function exist_plein ()
{
    foreach(func_get_args() as $value)
    {
        if (empty($_POST[$value]))
            return false;
    }
    return true;
}