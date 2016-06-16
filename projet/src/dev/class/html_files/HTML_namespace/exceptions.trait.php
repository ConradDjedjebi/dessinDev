<?php
/**
* Declare the trait exceptions
*
* @author       aduh95
* @version      1.1
* @package      HTML
* @subpackage   exceptions.trait
* @since        1.0
*/
namespace HTML;

trait exceptions
{
    protected static function get_args($keys, $args, $method=null)
    {
        /**
        * Filter the arguments provied
        * @param array $keys
            * @key 0=> list of all the acceptable arguments
            * @key 1=> list of required arguments (default value will be ignored for each required argument)
            * @key 2=> list of the default value for each argument
        * @param array $args: The argument passed to the function. May be a list of argument, or an array into the array
        * @param ~string $method : You should pass the magic constant __METHOD__, will be usefull if an error occures and an Exception is raised
        *
        * @return array
        * @throws Exception If a key passed in $keys[1] is not in $args
        */
        $return = array();
        $keys = array_replace(array_fill(0, 3, array()), $keys);
        $args = (count($args)===1 && is_array($args[0])) ? $args[0] : $args;

        foreach ($keys[0] as $index=>$key) {
            if(array_key_exists($tmp=$index, $args) or array_key_exists($tmp=$key, $args))
                $return[$key]= $args[$tmp];
            else if (in_array($key, $keys[1]))
                self::throwException($method, 'Missing non optional argument #'.$index.' "'.$key.'"');
            else if (array_key_exists($tmp=$index, $keys[2]) || array_key_exists($tmp=$key, $keys[2]))
                $return[$key] = $keys[2][$tmp]; // Default value
        }
        return $return;
    }

    final protected static function throwException($method, $message='Unknown method')
    {
        /**
        * Protected alias for throwPrepException
        * @see throwPrepException
        */
        throw new Exception($method, $message);
    }
}

class Exception extends \Exception
{
    public function __construct($method, $message='Unknown method')
    {
        parent::__construct('HTML error: '.$message."! \n".'Exception raised when calling '.$method);
    }
}