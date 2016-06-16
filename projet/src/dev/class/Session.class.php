<?php
/**
* Library of function and constants about security. If you want to pass informations through session, save it in $_SESSION->data.
*
* @author       aduh95
* @version      1.2
* @package      Session
* @require      PREP>=3.7
*/
/**
 * Assure la continuité de la session de l'utilisateur
 *
 * @property array $data
 * @method bool connect(int $id_joomla)
 * @method bool isConnected()
 * @method void requirePermission(int $rightLevel)
 * @method bool hasPermission([int $rightLevel])
 * @method void close()
 *
 ** STATIC **
 * @method void retrieve()
 */
class Session
{
    protected $connect_time, $id, $rights;
    private $connected=false;
    public $data;
    // Attention, la BDD limite les constantes à 2^15 (1<<15 unsigned SMALLINT)
    const   CLIENT =           1,
            ADMIN  =           1<<1, 
            ANY =           (1<<16)-1;

    const SQL_RIGHTS_FIELD = 'rights';

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
        if ($sql_data = Prep::select([
                        'users__list', ['ID',self::SQL_RIGHTS_FIELD, 'password'],
                        'where'=>['login'=>$login],
                        'limit'=>1])->fetch());
        else
            return !$this->log('Failed connection with unknown user: '.$login, $login, 'connection') && false;

        if (!password_verify($pass, $sql_data['password']))
            return !$this->log('Wrong password for user: '.$login, $sql_data['ID'], 'connection') && false;

        $this->id = $sql_data['ID'];
        $this->rights = $sql_data[self::SQL_RIGHTS_FIELD];
        $this->connect_time = time();

        $this->connected = true;
        return $this->log('Successful connection by user: '.$login, $this->id, 'connection');
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

    /**
     * @return int
     */
    public function get_id()
    {
        return $this->id;
    }

    public function __sleep()
    {
        return ['connect_time', 'id', 'data'];
    }

    public function __wakeup()
    {
        try {
            $this->rights = intval(Prep::selectOne('users__list', $this->id, self::SQL_RIGHTS_FIELD)[0]);
            $this->connected = true;
        } catch (Exception $e) {
            $this->rights = 0;
            $this->connected = false;
        }
    }

    /**
     * Log info in the database
     * @param str $msg
     * @param str $info
     * @param str $type
     *
     * @return bool
     */
    public function log($msg, $info=null, $type='custom')
    {
        use_file('date', PHPEXTENSION);
        $fields = ['msg'=>$msg, 'type'=>$type, 'request_uri'=>$_SERVER['REQUEST_URI'], 'stack'=>$info];
        if($this->is_connected())
            $fields+= ['Session_user'=>$this->id, 'Session_connect_time'=>date(date\MySQL, $this->connect_time), 'Session_rights'=>$this->rights];
        return Prep::insert('logs', $fields);
    }
}