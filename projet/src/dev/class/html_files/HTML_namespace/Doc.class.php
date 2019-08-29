<?php
/**
 * Here is defined the class HTML, a library that will help you to generate most of the HTML code
 *
 * @author       aduh95
 * @version      1.1
 * @package      HTML
 * @subpackage   Doc.class
 * @since        1.0
 */
/*
    ************************* HTMLdoc class ************************
    By aduh95 (08/2014)
    Version : 1.3
    ****************************************************************

    ***Precreate the html code you have to put on each page. The UTF-8 encoding is used by default.***

    Object:
    HTMLdoc (string $title)
            ->addStyle(string $name[, string $dir]) (void)
            ->addScript(string $url[, int $type[, bool $defer]]) (void)
            ->directScript(string $javascript) (void)
            ->center(string $selector) (void)
            ->form(string $url, string $content[, $string $method][, string $id]) (void) //to use "post" or "get" as an id for a form, you have to specified the method
            ->table(string $caption, array $titles, array $lines) (void)
            ->input(array $args) (string)
            ->liste(array $list, string $type, array $attr) (string)
            ->meta_encode (string)
            ->body (string)

    HTMLtable ([array $attributs[, string $caption[, array $titles[, array $lines]]])
        ->add_caption(string $caption) (void)
        ->add_line (array $values) (void)
        ->add_lines (array $lines) (void)
        ->add_title (mixed $title_s) (void)
        ->disiabled_foot() (void)
        ->html() (string)

    HTMLtable_body (array $lines)
*/
namespace HTML;
use \HTML, \JavaScriptPacker;

class Doc
{
    public
        $body = '';

    protected
        $css_sheets = array(),
        $scripts = array(),
        $jscript = '',
        $titre,
        $APIs = array(),
        $id = array();

    public function __construct($title=null)
    {
        $this->titre = 'GPI2 - ' . $title;
    }

    public function __destruct()
    {
        if(!headers_sent())
            echo $this;
    }

    public function addAPI($name)
    {
        if(in_array($name, $this->APIs))
            return;
        $this->APIs[]=$name;
        use_file('api', \CONFIG\APIs\DIRECTORY.DIRECTORY_SEPARATOR.$name);
        call_user_func('APIs\\'.$name.'\\main', $this);
    }

    public function addStyle($css, $dir=false)
    {
        $this->css_sheets[] = (empty($dir) ? null:HTML::relativeLink($dir.'/')).$css;
    }

    public function addScript($js_url, $dir=false, $defer=true)
    {
        $url = (empty($dir) ? null:HTML::relativeLink($dir.'/')).$js_url;

        if(PROD_ENVIRONEMENT && is_readable($file = PROJECT_ROOT.$url.'.js'))
        {
            // if the file is on the server, let's minify it
            if(!is_file($min_file = PROJECT_ROOT.$url.'.min.js') || filemtime($file)>filemtime($min_file))
            {
                $f = fopen($min_file, 'w');
                fwrite($f, new JavaScriptPacker(file_get_contents($file)));
                fclose($f);
            }
            $url.='.min';
        }

        $this->scripts[] = array('url'=>$url, 'defer'=>$defer);
    }

    public function directScript($js)
    {
        $this->jscript.= $js;
    }

    protected function get_header($doc=true)
    {
        if($doc)
            $return = HTML::meta(['charset'=>HTML::$char_encode]) .
                        HTML::meta(['name'=>'author', 'content'=>'Antoine du HAMEL']) .
                        HTML::title($this->titre) .
                        HTML::link(['rel'=>'icon', 'href'=>HTML::relativeLink('~images/Logo_ESEO.png'), 'type'=>'image/png']);

        foreach ($this->css_sheets as $url)
            $return.=HTML::link(['rel'=>'stylesheet', 'href'=>$url.'.css']);

        foreach ($this->scripts as $script)
            $return.=HTML::script(['src'=>$script['url'].'.js', 'defer'=>$script['defer']], '');

        return $return;
    }

    protected function get_footer ()
    {
        if($this->jscript)
            return '<script><!--'."\n".
                new JavaScriptPacker($this->jscript).
                // implode('', array_map('trim', explode("\n",$this->jscript))).
                '//--></script>';
    }

    public function __toString()
    {
        return '<!DOCTYPE html>'."\n".
            '<html><head>'.$this->get_header().'</head><body>'.
            $this->body.$this->get_footer().'</body></html>';
    }
}