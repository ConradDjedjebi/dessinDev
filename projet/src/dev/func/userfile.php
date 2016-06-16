<?php
/**
 * Userfile namespace to handle file upload and download easily 
 * 
 * @author Antoine du HAMEL
 * @category mixed
 * @package domotique
 * @subpackage global_functions
 */
namespace userfile;

use const CONFIG\UPLOAD\DIRECTORY;

/**
 * Generates a valid path not used yet
 * @param string $name
 * @return string -- You only need to store the 50 last chars
 */
function freePath($name)
{
    // Generating a unique dir name
    $dir=DIRECTORY.DIRECTORY_SEPARATOR.md5(uniqid(rand(), true));
    if(!is_dir(DIRECTORY))
        mkdir(DIRECTORY, 0770);
    if(!is_dir($dir))
        mkdir($dir, 0770);

    // Récupération de l'extension d'origine
    $extension = substr(strrchr($name, '.'), 1, 14);
    // $extension = explode('.', $name);
    // $extension = substr(array_pop($extension), 0, 14);
    // Choix d'un séparateur pour l'extension
    while(strpos($extension, $SEPARATOR=char_alea())!==false);

    // Sélection d'un nom de fichier libre
    while(is_file($file = $dir.'/'.$SEPARATOR.$extension.$SEPARATOR.char_alea(15-strlen($extension))));
    return $file;
}

/**
 * Retrieves a name for the file ~> reverse of freePath
 * @param string $path
 * 
 * @return string A random string whith the good extension
 */
function name_retrieve($path)
{
    $fileName = strrchr($path, '/');
    $separator = $fileName[1]; // first char is a /, second is the separator
    if($separator_position = strpos($fileName, $separator, 2))
        return char_alea(8).'.'.substr($fileName, 2, $separator_position-2);
    else
        return $fileName;
}

/**
 * Returns the MIME of the file
 * @param string $file Absolute path of the file
 * @param string $check A REGEX to compare with the actual MIME type. If provied, a bool is returned
 * 
 * @return string|bool
 */
function getMIME($file, $check=false)
{
    if(is_file($file))
    {
        $finfo = finfo_open(FILEINFO_MIME_TYPE);
        $mime = finfo_file($finfo, $file);
        finfo_close($finfo);

        if($check)
            return preg_match($check, $mime);
        else return $mime;
    }
}

/**
 * Get an absolute path if a relative one is provied
 * @param string $path
 * @return string
 */
function relativeLink($path)
{
    return substr_compare($path, realpath('/'), 0) ? DIRECTORY.DIRECTORY_SEPARATOR.$path : $path;
}

/**
 * Move an uploaded file to the upl0/ directory. DOES NOT SUPPORT array of files yet!
 * @param string|ZipArchive $file The $_FILES[somefile] or the ZipArchive containing the uploaded files
 * @param string|NULL $mime REGEX that the MIME type of the file should match
 * @param int $maxsize The maximum size of the file (default is 8 Mio)
 * 
 * @return string(50)|FALSE The new relative path of the file within the upl0/ directory
 */
function upload($file, $mime=null, $maxsize=1<<23)
{
    if (isset($_FILES[$file]))
    {
        if($_FILES[$file]['error'] === UPLOAD_ERR_OK)
    
        {
            $file = $_FILES[$file];
            if($mime)
            {
                $finfo = finfo_open(FILEINFO_MIME_TYPE);
                if(!preg_match($mime, finfo_file($finfo, $file['tmp_name'])))
                    return false;
                finfo_close($finfo);
            }


            move_uploaded_file($file['tmp_name'], $path= freePath($file['name']));
        }
        else throw new UploadException($_FILES[$file]['error']);
    }
    else return false;

    return substr($path, -50);
}

/**
 * Read a file to send it to the user navigator
 * @param string $file If relative path is given, assumes from userfile\DIRECTORY directory
 * @param string $output_name (if not provied, the original name will be used)
 * @return void
 */
function download($file, $output_name=false)
{
    $file = relativeLink($file);

    if(is_file($file))
    {
        if(!$output_name)
            $output_name  = name_retrieve($file);

        header('Content-Description: File Transfer');
        header('Content-Type: '.getMIME($file));
        header('Content-Disposition: attachment; filename="'.$output_name.'"');
        header('Set-Cookie: fileDownload=true; path=/');
        header('Cache-Control: private, max-age=0, must-revalidate');
        header('Pragma: public');
        ob_clean();flush();
        readfile($file);
        exit;
    }
    else exit(header('HTTP/1.0 404 Not Found').'Unreacheable file, download aborted!');
}

/**
 * Zips one or more file for a faster download
 * @param string|array $file If relative path(s) is given, assumes from userfile\DIRECTORY directory
 * @param string $output_name (if not provied, the original name will be used)
 * 
 * @return void
 */
function ZIP_download($file, $output_name=false)
{
    if (!is_array($file))
        $file = [$file];

    $zip = new Zip;

    foreach ($file as $name=>$path)
        $zip->addFile($path, is_numeric($name) ? name_retrieve($path) : $name);

    download($zip->close(), $output_name);
    $zip->remove();
}

/**
 * Class to zip files to take fewer place on the server
 */
class Zip extends \ZipArchive
{
    private $path;
    /**
     * Creates a new zip archive, or overwrite on an existing on if a valid file is provied in parameter
     * @param string $path
     */
    public function __construct($file=false)
    {
        // parent::__construct();
        $return = is_file($file) ? $this->open($file, self::OVERWRITE) : $this->open($this->path=freePath('1.zip'), self::CREATE);
        if($return!==true)
            throw new \Exception('userfile\Zip error, please check the ZipArchive manual.', $return);
        $this->path=$this->filename;
    }

    /**
     * If the zip archive file is empty, the file won't be created. If you want to create an empty file, try with the touch function
     * @return string|null The relative string to the zip file, or NULL if the archive were empty
     */
    public function close()
    {
        $empty = empty($this->numFiles);
        call_user_func('parent::'.($empty ? 'unchangeAll':'close'));
        return $empty ? null : substr($this->path, -50);
    }

    /**
     * Add an uploaded file to the archive, making all the verifications first
     * @param string $name The name of the HTML input
     * @param string|false $mime A REGEX on the MIME type of the file (default, everything is accepted)
     * @param int $max_size The maximum size accepted for the file in bytes
     * @param string $prefix A prefix for the file. By default, the $name attribute is used
     * 
     * @return boolean
     */
    public function addUploadedFile($name, $mime=false, $max_size=1<<23, $prefix=null)
    {
        if($_FILES[$name]['error'] === UPLOAD_ERR_OK && $_FILES[$name]['size']<$max_size && (empty($mime) || getMIME($_FILES[$name]['tmp_name'], $mime)))
        {
            // On le zip
            move_uploaded_file($_FILES[$name]['tmp_name'], $tmp=tempnam(PROJECT_ROOT.'/tmp', 'zip'));
            return $this->addFile($tmp, (func_num_args()<4 ? $name : $prefix).'.'.$_FILES[$name]['name']);
        }
        else return false;
    }

    /**
     * Remove the archive from the server
     */
    public function remove()
    {
        if(!empty($this->filename))
            $this->close();

        if(is_file($this->path))
            unlink($this->path);
    }
}

class UploadException extends \Exception 
{ 
    public function __construct($code) { 
        parent::__construct($this->codeToMessage($code), $code); 
    } 

    private function codeToMessage($code) 
    { 
        switch ($code) { 
            case UPLOAD_ERR_INI_SIZE: 
                return 'The uploaded file exceeds the upload_max_filesize directive in php.ini';
                break; 
            case UPLOAD_ERR_FORM_SIZE: 
                return 'The uploaded file exceeds the MAX_FILE_SIZE directive that was specified in the HTML form';
                break; 
            case UPLOAD_ERR_PARTIAL: 
                return 'The uploaded file was only partially uploaded';
                break; 
            case UPLOAD_ERR_NO_FILE: 
                return 'No file was uploaded';
                break; 
            case UPLOAD_ERR_NO_TMP_DIR: 
                return 'Missing a temporary folder';
                break; 
            case UPLOAD_ERR_CANT_WRITE: 
                return 'Failed to write file to disk';
                break; 
            case UPLOAD_ERR_EXTENSION: 
                return 'File upload stopped by extension';
                break; 

            default: 
                return 'Unknown upload error';
                break; 
        } 
    } 
} 
