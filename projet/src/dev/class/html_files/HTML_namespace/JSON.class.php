<?php
/**
 * Here is defined JSON class
 *
 * @author       aduh95
 * @version      1.2
 * @package      HTML
 * @subpackage   JSON.class
 * @since        1.1
 */
namespace HTML;
class JSON implements \ArrayAccess
{
    const PASSWORD_NEEDED = true;
    const TOP_REDIRECT = true;

    public $data = array();
    protected
        $result=false,
        $msg='Interruption inattendue',
        $info=0;

    public function __construct()
    {
        no_cache();
        header('Content-Type: application/json');
    }
    public function __destruct()
    {
        echo json_encode(['result'=>$this->result, 'msg'=>$this->msg, 'info'=>$this->info, 'data'=>$this->data]);
    }

    public function set_msg($msg)
    {
        $this->msg=$msg;
    }

    public function redirect($link='~', $topRedirect=false)
    {
        $this->data['redirect'] = \HTML::relativeLink($link);
        $this->data['topRedirect'] = $topRedirect;
    }

    public function reload()
    {
        $this->data['reload'] = true;
    }

    public function modal($url, array $data=array())
    {
        $this->data['modal'] = ['url'=>\HTML::relativeLink($url), 'data'=>$data];
    }
    
    public function confirm($message, $password_needed = false, $data = array(), $timeout=60000)
    {
        if(isset($_SESSION['json-confirm'], $_SESSION['json-confirm']['token']) && exist('token'))
        {
            if(time()>$_SESSION['json-confirm']['timeout'])
                $this->addWarning('Délai dépassé');

            else if($_SESSION['json-confirm']['token']!==$_POST['token'])
                $this->addWarning('Tokens dismatch');

            else if($_SESSION['json-confirm']['password_needed'] && !(exist('password') && $_SESSION->verifyPassword($_POST['password'])))
                $this->addWarning('Mot de passe incorrect');

            else
            {
                $_POST = $_SESSION['json-confirm']['_POST'];
                unset($_SESSION['json-confirm']);
                return true;
            }
        }
        
        $token = char_alea(16);

        $_SESSION['json-confirm'] = ['password_needed'=>$password_needed, 'token' => $token, 'timeout'=>time()+$timeout, '_POST'=>$_POST];
        $this->data['confirm'] = [
                'url'=>$_SERVER['REQUEST_URI'],
                'data'=>$data+array_map(function($cv) {return 'confirm';}, $_POST),
                'token'=>$token,
                'timeout'=>$timeout,
                'msg'=>$message,
                'withPassword'=>$password_needed,
            ];
        $this->exitSuccess('Confirmation requise');
    }

    public function addWarning($msg='Une erreur inconnue est survenue')
    {
        if(!isset($this->data['warnings']))
            $this->data['warnings']=array();
        $this->data['warnings'][] = $msg;
    }

    public function exitError($msgError='Opération refusée')
    {
        $this->result = false;
        $this->msg = $msgError;
        exit;
    }

    public function exitSuccess($msgSuccess='Opération réussie')
    {
        $this->result = true;
        $this->msg = $msgSuccess;
        exit;
    }



    /**
     * Checks if an offset is set in this object
     * 
     * @param mixed $key The key to test
     * @return boolean The result of the test
     */
    public function offsetExists($key)
    {
        return isset($this->data[$key]);
    }

    /**
     * Returns the value the variable set in this object
     * 
     * @param mixed $key The key to get
     * @return mixed The stored result in this object
     */
    public function offsetGet($key)
    {
        return $this->offsetExists($key) ? $this->data[$key] : trigger_error('Unfinded index "'.$key.'"');
    }

    /**
     * Sets the value the variable in this object
     * 
     * @param mixed $key    The key to set
     * @param mixed $value  The value to set
     * @return void
     */
    public function offsetSet($key, $value)
    {
        $this->data[$key] = $value;
    }

    /**
     * Unsets the value the variable in this object
     * 
     * @param mixed $key The key to unset
     * @return void
     */
    public function offsetUnset($key)
    {
        unset($this->data[$key]);
    }
}
