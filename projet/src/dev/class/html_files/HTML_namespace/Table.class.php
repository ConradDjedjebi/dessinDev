<?php
/**
 * Here is defined JSON class
 *
 * @author       aduh95
 * @version      1.1
 * @package      HTML
 * @subpackage   Table.class
 * @since        1.1
 */
namespace HTML;
use \HTML;

class Table
{
    private $caption,
            $thead,
    		$attributes,
    		$tbody,
    		$tbody_xss,
    		$tfoot;
    const   NO_TFOOT=           1,
            TFOOT_EQUALS_THEAD= 1<<1,
            AUTO =              1<<2,
            TBODY_NO_XSS =      1<<3,
            TITLES_NO_XSS =     1<<4,
            DATATABLE  =        1<<5,
            TABLELINK  =        1<<6,
            NO_DEFAULT_CLASSES= 1<<7,
            ROWED =             1<<8;
    use exceptions;
    
    public function __construct($attr=array(), $caption=false, $head=false, $lines=false)
    {
        $args = $this->get_args([
            ['attributes', 'caption', 'thead', 'tbody', 'tfoot', 'options', 'tbody_noXSS'],
            [],
            [array(), null, array(), array(), 'options'=>self::AUTO, 'tbody_noXSS'=>array()]
            ], func_get_args());

        $this->options = $args['options'];
        $this->attributes = $args['attributes'];
        
        // Add default HTML classes
        if(!($this->options & self::NO_DEFAULT_CLASSES))
            $this->attributes['class']= 'table table-striped table-bordered table-hover'.(array_key_exists('class', $this->attributes) ? ' '.$this->attributes['class'] : null);

        if($this->options & self::ROWED)
            $this->attributes['class'].=' table-rowed';
        if($this->options & self::DATATABLE)
            $this->attributes['class'].=' dataTable';
        if($this->options & self::TABLELINK)
            $this->attributes['class'].=' table_link';

        $this->caption = $args['caption'];
        $this->thead = $args['thead'];
        $this->tfoot = empty($args['tfoot']) ? false : $args['tfoot'];
        $this->tbody = $args['tbody'];
        
        $this->tbody_xss = (empty($args['tbody_noXSS']) AND !empty($this->tbody[0])) ? 
            array_fill(0, count($this->tbody[0]), !!($this->options&self::TBODY_NO_XSS)) :
            $args['tbody_noXSS'];
    }

    public function add_line($values)
    {
        $this->tbody[] = $values;
    }
    
    public function add_lines($lines)
    {
        array_map('self::add_line', $lines);
    }

    
    public function __toString()
    {
        return HTML::container('dataTable_wrapper', HTML::table($this->attributes, 
            ($this->caption ? HTML::caption($this->caption, true) : null) .
            $this->get_head().
            $this->get_foot().
            $this->get_body()));
    }

    protected function get_titles_row($titles)
    {
        return HTML::tr(is_array($titles) ? implode(null, array_map('HTML::th', $titles, array_fill(0, count($titles), !!($this->options&self::TITLES_NO_XSS)))) : $titles);
    }
    protected function get_head()
    {
        if ($this->thead)
            return HTML::thead($this->get_titles_row($this->thead));
    }
    protected function get_foot()
    {
        if ($this->options&self::NO_TFOOT);
        else if ($this->tfoot)
        	$return  = $this->tfoot;
        else if ($this->thead && ($this->options&self::TFOOT_EQUALS_THEAD || ($this->options&self::AUTO && count($this->tbody)>10)))
            $return = $this->thead;
        
        if (isset($return))
        	return HTML::tfoot($this->get_titles_row($return));
    }

    protected function get_body()
    {
        $return = '';
        foreach ($this->tbody as $line)
        {
            $return.= '<tr>';
            foreach ((array)$line as $index => $cell) {
                if (!is_array($cell))
                    $cell = [$cell];
                $cell[] = $this->tbody_xss ? $this->tbody_xss[$index] : $this->options&self::TBODY_NO_XSS;
                $return.= HTML::td(...$cell);
            }
            $return.= '</tr>';
        }
        return HTML::tbody($return);
    }

    public static function link($dataSent, $html)
    {
        return HTML::span(['role'=>'link', 'data-sent'=>json_encode($dataSent)], $html);
    }
}