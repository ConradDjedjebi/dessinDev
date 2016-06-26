<?php
/**
* Takes care of security through user sessions.
*
* @author       Antoine du HAMEL
* @version      GPI2
* @package      Session
*/
class Session implements ArrayAccess
{
    protected $connect_time, $id, $rights;
    private $connected=false;
    public $data;
    // Attention, la BDD limite les constantes à 2^15 (1<<15 unsigned SMALLINT)
    const   CLIENT =           1,
            ADMIN  =           1<<1, 
            ANY =           (1<<16)-1;

    /**
     * Transforme `$_SESSION` en array à la fin du chargement de la page
     */
    public function __destruct()
    {
        $_SESSION = ['sess_obj'=>serialize($this)];
    }

    /**
     * Destroys the actual Session informations
     * @return void
     */
    public function close()
    {
        $_SESSION = array();
        if (ini_get('session.use_cookies')) {
            $params = session_get_cookie_params();
            setcookie(session_name(), '', time() - 42000,
                $params['path'], $params['domain'],
                $params['secure'], $params['httponly']
            );
        }
        session_destroy();
        session_start();
    }

    /**
     * Wakes up the existing Session, or creates a new one
     * @return void
     */
    public static function retrieve()
    {
        if (session_status()===PHP_SESSION_ACTIVE)
            return;
        if (!session_start())
            throw new Exception('Impossible d\'instancier la session.');
        $_SESSION = empty($_SESSION['sess_obj']) ? new Session : unserialize($_SESSION['sess_obj']);

        // Redirection if connection required
        if(!$_SESSION->is_connected() && !defined('NO_CONNECTION_REQUIRED'))
        {
            $_SESSION->data['redirect'] = $_SERVER['REQUEST_URI'];
            redirect(ROOT_DIR.'/apps/connect/');
        }
    }

    /**
     * Check the log informations
     * @param string $mail
     * @param string $pass
     *
     * @return bool
     */
    public function connect($login, $pass)
    {
        if ($login !== CONFIG\LOGIN\USERNAME)
            return false;
        
        if (!password_verify($pass, CONFIG\LOGIN\PASSWORD_HASH))
            return false;

        $this->id = 0;
        $this->rights = 1;
        $this->connect_time = time();

        $this->connected = true;
        return true;
        // return $this->log('Successful connection by user: '.$login, $this->id, 'connection');
    }

    /**
     * Stop the script execution if the rights' level is ot reached
     * @param int $rightLevel
     * @return void
     */
    public function requirePermission($rightLevel)
    {
        if(!$this->hasPermission($rightLevel))
            exit(header('HTTP/1.0 401 Unauthorized'));
    }

    /**
     * Checks if the user's rights reach the permission level expected
     * @param int $rightLevel
     *
     * @return bool
     */
    public function hasPermission($rightLevel=self::ANY)
    {
        if (is_string($rightLevel))
            $rightLevel = constant('self::'.$rightLevel);
        return $this->connected && ($this->rights&$rightLevel || (isset($_GET['override']) && $this->rights&self::ADMIN));
    }

    /**
     * @return bool
     */
    public function is_connected()
    {
        return $this->connected;
    }

    public function __sleep()
    {
        return ['connect_time', 'id', 'data', 'rights', 'connected'];
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
    
    /**
     * Prints the array of the objet when a var_dump is made
     * @return array
     */
    public function __debugInfo()
    {
        return $this->data;
    }
}