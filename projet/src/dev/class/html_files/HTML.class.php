<?php
/**
* Here is defined the class HTML, a library that will help you to generate most of the HTML code
*
* @author       aduh95
* @version      1.1
* @package      HTML
* @subpackage   HTML.class
* @since        1.0
*/

/**
 * Library of the most useful methods and constants to generate HTML
 * 
 * STATIC METHODS:
 * @method string tag(string $name[, array $attributes[, string $sontent[, int $options]]])
 * @method string attributes(array $attributes)
 * @method string noXSS(string $html)
 * @method string container(string $html_class, string $html_content)
 * @method string icon(string $html_classes)
 */
abstract class HTML
{
    const   ONE_TAG=1,
            NO_XSS=2,
            NO_ATTRIBUTE = array();

    const NON_BREAKING_SPACE = '&nbsp;';
    
    public static $char_encode = 'utf-8';

    use HTML\exceptions;

    /**
     * Build a XML markup such as a HTML tag
     * @param 0:$name
     * @param 1:$attributes
     * @param 2:$content
     * @param 3:$options
     *
     * @return string
     */
    public static function tag()
    {
        $arg = self::get_args([
                    ['name', 'attributes', 'content', 'options'],
                    ['name'],
                    [1=>array(), null, 0]], func_get_args());

        $return = '<'.$arg['name'].self::attributes($arg['attributes']);
        if ($arg['options']&self::ONE_TAG)
            $return.='/';
        else
        {
            $return.='>';
            if ($arg['options']&self::NO_XSS)
                $return.=self::noXSS($arg['content']);
            else
                $return.=$arg['content'];
            $return.='</'.$arg['name'];
        }
        return $return.'>';
    }

    /**
     * @param array $attr Keys are the attribute names and value are plain value
     * @return string
     */
    public static function attributes(array $attr)
    {
        $return ='';
        foreach ($attr as $key => $value)
        {
            if($value!==false)
                $return.= ' '.$key.($value===true ? null : '="'.self::noXSS($value).'"');
        }
        return $return;
    }

    /**
     * Convert all applicable characters to HTML entities
     * @param string $value
     * @return string
     */
    public static function noXSS($string)
    {
        return htmlentities($string, ENT_QUOTES | ENT_HTML401 | ENT_SUBSTITUTE, self::$char_encode, false);
    }

    /**
     * If an unknown method is called, HTML tag is assumed
     * @exemple HTML::p('Hello World!');
     * is equivalent to
     * @exemple HTML::tag(['p', 'content'=>'Hello World!'])
     * 
     * There are two ways to call this method, depending if your are looking to a single markup tag or a double markup tag
     * HTML::tag_name([array $attributes, ]string $content[, bool $noXSS]) => "<tag_name>content</tag_name>""
     * HTML::tag_name(array $attributes) => "<tag_name/>"
     * @param @see HTML::tag()
     */
    public static function __callStatic($name, $args)
    {
        $return = ['name'=>$name];
        if (empty($args));
        elseif (is_array($args[0]))
        {
            $return['attributes'] = array_shift($args);
            if (count($args) && is_string($args[0]))
                $return['content'] = array_shift($args);
        }
        else
            $return['content'] = array_shift($args);
        $return['options'] = (empty($args[0]) ? 0 : self::NO_XSS)|(array_key_exists('content', $return) ? 0 : self::ONE_TAG);

        return call_user_func('self::tag', $return);
    }

    /**
     * Make a DIV tag with a specified HTML class
     * @param string $className (or names)
     * @param string $content : NOT XSS protected
     * @param boolean $inline : specify if you want an inline container (such as <span>) or a block one (such as <div>)
     *
     * @return string
     */
    public static function container($className, $content, $inline=false)
    {
        return self::tag($inline ? 'span':'div', ['class'=>$className], $content);
    }
    
    /**
     * Make a HTML liste (such as <ul>, <ol>)
     * @param string $tag
     * @param array $attr
     * @param array $content
     * @param callable $func
     *
     * @return string
     */
    public static function liste($tag, $attr, $content, $func=null)
    {
        $func = is_callable($func) ? $func : function($item){return HTML::li($item);};
        $return='';
        foreach ($content as $item)
            $return.= $func($item);
        return self::tag($tag, $attr, $return);
    }

    /**
     * Generate a HTML link
     * @param string $href
     * @param string $txt
     * @param array $attributes
     *
     * @return string
     */
    public static function a($href, $txt, $attr=array())
    {
        $attr['href'] = $href ? self::relativeLink($href) : '#';
        return self::tag('a', $attr, $txt);
    }

    /**
     * Generate the HTML code for an icon with Bootstrap
     * @param string $class (fa is already specified)
     * @return string
     */
    public static function icon($class)
    {
        return self::tag('i', ['class'=>'fa '.$class]);
    }

    /**
     * Obtain the relative path of a local file
     * @param string $link The link to reach
     * @return string The relative URL
     */
    public static function relativeLink($link)
    {
        if(empty($link)) return;

        $strlen=strlen(PROJECT_ROOT);
        if(DIRECTORY_SEPARATOR!=='/')
            $link = str_replace(DIRECTORY_SEPARATOR, '/', $link);

        if (strncmp($link, PROJECT_ROOT, $strlen)===0)
            $link = ($link[$strlen]==='/' ? ROOT_DIR:'~').substr($link, $strlen);

        return $link[0]==='~' ? ROOT_DIR.'/'.substr($link,1) : $link;
    }
}


spl_autoload_register(function ($class) {
    if(substr($class, 0, 5)==='HTML\\')
    {
        $exception = false;

        $class = substr($class, 5);
        $dir = __DIR__.DIRECTORY_SEPARATOR.'html_files';
        $exception+= !is_dir($dir);

        $file = $dir.DIRECTORY_SEPARATOR.strtolower($class).'.class.php';
        // is_file($file) ? null : $file = $dir.DIRECTORY_SEPARATOR.strtolower($class).'.trait.php';
        $exception+= !is_file($file);

        if($class==='Exception')
            require_once $file;
        elseif ($exception)
            throw new Exception('Unknown class! Missing file or directory "'.$file.'". Unable to load class HTML\\'.$class.'!');
        else
            include $file;
    }
});
