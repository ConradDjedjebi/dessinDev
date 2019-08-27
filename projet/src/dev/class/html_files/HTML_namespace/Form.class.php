<?php
/**
 * Here is defined the class HTML\Form which will help you generate HTML forms.
 *
 * @author       aduh95
 * @version      1.1
 * @package      HTML
 * @subpackage   Form.class
 * @since        1.0
 *
 *
 * PUBLIC METHODS
 *
 * @method __construct([string $action[, array $attributes]]);
 *
 * @method int addFieldset([string $legend]);
 *
 * @method int addFieldsetCustom([string $tag[, array $attr]]);
 *
 * @method void addElement(string $html);
 *
 * @method void input(array $attributes);
 *
 * @method void submit();
 *
 * @method __toString();
 */
namespace HTML;
use \HTML;

class Form
{
    private
        $action,
        $method,
        $defaultValues=array(),
        $valuesFromPost=false,
        $fieldsets=[array('custom'=>'true', 'tag'=>'fieldset', 'attr'=>[], 'auto'=>true)],
        $lastFieldset=0;
    private static $id=0;

    public function __construct($action='', $attributs=array())
    {
        $this->attributs = array_merge(['action'=>HTML::relativeLink($action), 'method'=>'post'], $attributs);
    }

    public function setDefaultValues($var)
    {
        if(is_bool($var))
            $this->valuesFromPost = $var;
        else
        {
            $this->valuesFromPost = true;
            $this->defaultValues = $var;
        }
    }
    
    public function valuesFromPost()
    {
        return $this->setDefaultValues($_POST);
    }

    /**
     * @param string $legend
     * @return int PHP's ID for the <fieldset>
     */
    public function addFieldset($legend=null)
    {
        return $this->addFieldsetGlobal(['legend'=>$legend, 'custom'=>false]);
    }
    
    /**
     * @param string $tag
     * @param array $attr
     * @return int PHP's ID for the "fieldset"
     */
    public function addFieldsetCustom($tag='fieldset', $attr=array())
    {
        return $this->addFieldsetGlobal(['custom'=>true, 'tag'=>$tag, 'attr'=>$attr]);
    }

    /**
     * @param array $param
     * @return int PHP's ID for the "fieldset"
     */
    protected function addFieldsetGlobal($param)
    {
        // Si aucun fieldset n'a été créé, on supprime le prmier de base
        if (isset($this->fieldsets[0]['auto']) && empty($this->fieldsets[0]['content']))
            $this->fieldsets = array();

        // On rajoute celui qu'il faut créé
        $this->fieldsets[] = $param;

        // On retourne son identifiant
        return $this->lastFieldset=count($this->fieldsets)-1;
    }

    /**
     * Add a string to the fieldset chosen, useful if the Form::input is not enough
     * @param string $content
     * @param int $fieldset
     *
     * @return string $content
     */
    public function addElement($content='', $fieldset=false)
    {
        return $this->fieldsets[$fieldset===false ? $this->lastFieldset : $fieldset]['content'][] = $content;
    }

    /**
     * @param array $attr
     * @param int|false $fieldset
     *
     * @return str The HTML code generated
     */
    public function input($attr, $fieldset=false)
    {
        return $this->addElement(static::inputHTML($attr, $this->valuesFromPost, $this->defaultValues), $fieldset);
    }

    /**
     * @param array $attr
     * @param int|false $fieldset
     *
     * @return str The HTML code generated
     */
    public static function inputHTML($attr, $valuesFromPost=false, array $defaultValues=null)
    {
        $class='form-group';
        if (isset($attr['type']) && ($attr['type']==='radio' || $attr['type']==='checkbox'))
            $class.= ' '.$attr['type'].(empty($attr['disabled']) ? null : ' disabled');

        return HTML::container($class, static::inputGenerate($attr, $valuesFromPost, empty($defaultValues) ? $_POST : $defaultValues));
    }

    /**
     * Generate a HTML submit button
     *
     * There are two ways to call this method
     * @param string $value
     * @param int $fieldset
     * or
     * @param @see Form::input
     *
     * @return string The HTML code generated
     */
    public function submit($attr, $fieldset=false)
    {
        if(is_string($attr))
            $attr=['value'=>$attr];

        if(empty($attr['class']))
            $attr['class'] = 'btn btn-lg btn-success btn-block';

        $attr['type'] = 'submit';
        
        return $this->addElement(static::inputGenerate($attr), $fieldset);
    }

    /**
     * Generate a HTML hidden input
     *
     * @param string $name
     * @param int $fieldset
     * or
     * @param @see Form::input
     *
     * @return string The HTML code generated
     */
    public function hidden($name, $value)
    {
        $attr = ['type' => 'hidden', 'name'=>$name, 'value'=>$value];
        
        return $this->addElement(static::inputGenerate($attr));
    }

    /**
     * Generate most of the form elements in HTML
     * @param array $attr
     * @param int $fieldset
     *
     * @return string The HTML code generated
     */
    protected static function inputGenerate($attr, $valuesFromPost=false, array $defaultValues=array())
    {
        if (empty($attr['type'])) $attr['type']='text';
        $type=$attr['type'];

        if($valuesFromPost && !array_key_exists('value', $attr) && isset($attr['name']) && isset($defaultValues[$attr['name']]))
            $attr['value'] = $defaultValues[$attr['name']];

        // Gestion des cases à cocher et bouttons radios
        $option_input = in_array($type, ['radio', 'checkbox']);
        if(!$option_input)
            $attr['class'] = isset($attr['class']) ? $attr['class'] : 'form-control';

        // Retrocompatibilité - le <label> est sensé se trouver dans l'attribut 'other'
        if(isset($attr['label']))
        {
            $attr['other']['label'] = $attr['label'];
            unset($attr['label']);
        }

        // Gestion des élements exterieurs à l'input
        $label=null; $help=null;
        if(isset($attr['other']))
        {
            // 'other' n'est pas un attribut HTML
            $opt = $attr['other'];
            unset($attr['other']);

            // Gestion des labels
            $label= empty($opt['label']) ? false :  HTML::noXSS(ucfirst($opt['label']));
            
            // Gestion des options pour les <select> ou les <datalist>
            if(isset($opt['options']))
            {
                $val = empty($attr['value']) ? array() : (is_array($attr['value']) ? $attr['value'] : [$attr['value']]);
                $options=null;
                foreach ($opt['options'] as $key => $value)
                {
                    // Gestion des <optgroup>
                    if(is_array($value))
                    {
                        $optgroup = '';
                        foreach ($value as $subkey => $option) {
                            $optgroup.=HTML::option(['selected'=>in_array($subkey, $val), 'value'=>$subkey], $option, true);
                        }
                        $options.=HTML::optgroup(['label'=>$key], $optgroup);
                    }
                    else
                        $options.=HTML::option(['selected'=>in_array($key, $val), 'value'=>$key], $value, true);
                }
            }

            // Gestion des help-boxes
            if(isset($opt['help']))
                $help = HTML::span(['class'=>'help-block'], $opt['help'], true);
        }
        


        // Génération d'un ID si besoin
        if (($label||array_key_exists('list', $attr)) && !$option_input && !array_key_exists('id', $attr))
            $attr['id'] = self::new_id();

        // Gestion des datalist
        if(isset($attr['list']))
            $datalist = static::inputGenerate(['type'=>'datalist', 'other'=>['options'=>$attr['list']], 'id'=>$attr['list']=self::new_id(), 'class'=>'']);
        else
            $datalist=null;

        // Gestion des inputs qui n'utilisent pas la balise HTML <input>
        if (in_array($type, ['textarea', 'select', 'datalist']))
        {
            $val =  isset($options) ? 
                        $options : 
                (   isset($attr['value']) ?
                        HTML::noXSS($attr['value']):
                        null);
            unset($attr['value'], $attr['type'], $attr['other']);
            $return = HTML::tag($type, $attr, $val);
        }
        else
            $return = HTML::input($attr);

        // Rajout du label - avant ou après l'élement suivant $option_input
        if ($label)
            $return = $option_input ? HTML::label($return.' '.$label) : HTML::label(['for'=>$attr['id']], $label).$return;

        // On retourne le code HTML généré
        return $return.$datalist.$help;
    } 

    /**
     * @return string
     */
    private static function new_id()
    {
        return 'fe'.(self::$id++).substr(md5(rand()), 27);
    }

    /**
     * @return string The HTML code of the whole form
     */
    public function __toString()
    {
        return HTML::tag('form', $this->attributs, array_reduce($this->fieldsets, function($pv, $cv){
            if (empty($cv['content'])) return $pv;
            return $pv.
            ($cv['custom'] ? 
                        HTML::tag($cv['tag'], $cv['attr'], implode('', $cv['content'])) :
                        HTML::tag('fieldset', ['class'=>'panel panel-default'],
                            (isset($cv['legend']) ? HTML::legend(['class'=>'panel-heading'], $cv['legend']) : '').
                            HTML::div(['class'=>'panel-body'], implode('', $cv['content']))));
        }));
    }
}