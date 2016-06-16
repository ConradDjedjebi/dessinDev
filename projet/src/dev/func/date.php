<?php
/**
* Here is defined the namespace date, a library that will help you to generate date and DateInterval
* You have to include this file to use its function, with for exemple the function `use_file('date', 'func');`
*
* @author       aduh95
* @version      1.2
* @category 	mixed
* @package      intraJE
* @subpackage   date.namespace
* @since        1.0
*
* @function DateTime 		create(string $date[,string $format])
* @function DateTime|string format(DateTime $date[,string $format])
* @function string 			convert(string $date, string $input_format, string $output_format)
* @function DateTime|string now([string $format])
* @function DateTime|string thatday([int $relativeDay[, int $hour[, int $minute]],][string $format])
* @function DateTime|string in([string $format])
* @function DateTime|string ago([string $format])
* @function boolean 		isPast(DateTime|string $date)
* @function boolean 		isFuture(DateTime|string $date)
*/

namespace date;
use \DateTime, \DateInterval;

// Defining date formats
const US = 'Y-m-d';
const TIME = 'H:i:s';
const MySQL = 'Y-m-d H:i:s';
const FRENCH = 'd/m/Y';
const FRENCH_HM = FRENCH.' H:i';
const FRENCH_HMS = FRENCH.' '.TIME;
const ISO = 'Y-m-d\TH:i';

/**
 * If $format is not given, equivalent to date_create($date)
 * @return DateTime
 */
function create($date='now', $format=false)
{
	return $format ? DateTime::createFromFormat($format, $date) : new DateTime($date);
}

/**
 * If $format is given, equivalent to the build-in function date_format
 * @param string|int|DateTime
 * @param string|false $format If ommited, a DateTime object is returned
 * 
 * @return string|DateTime
 */
function format($datetime, $format)
{
	return $format ? $datetime->format($format) : $datetime;
}

/**
 * Converts a date from one format to another one
 * @param string $date
 * @param string $input_format
 * @param string $output_format
 * 
 * @return string
 */
function convert($date, $input, $output)
{
	return format(create($date, $input), $output);
}

/**
 * Returns the current date
 * If $format is not given, equivalent to date_create()
 * @param string $format If ommited, a DateTime object is returned
 *
 * @return DateTime|string
 */
function now($format=false)
{
	return format(new DateTime, $format);
}

/**
 * Returns a DateTime object dated of the number of day relative to now, at the time defined in parameters
 * @param int $relativeDay
 * @param int $hour Default is midnight
 * @param int $minute Default is 0
 * @param string $format If ommited, a DateTime object is returned
 * 
 * @return DateTime|string
 */
function thatday($relativeDay=0, $hour=0, $min=0)
{
	$format = func_get_arg(func_num_args()-1);

	$relativeDay = intval($relativeDay);
	$hour = intval($hour);
	$min = intval($min);
	
	$date = new DateTime;
	$date->setTime($hour,$min);

	if($relativeDay)
		call_user_func([$date, $relativeDay>0 ? 'add':'sub'], new DateInterval('P'.abs($relativeDay).'D'));


	return format($date, is_string($format) ? $format : false);
}

/**
 * Returns a date in the past, relative to now with the interval defined in parameter
 * @param string $interval @see DateInterval::__construct
 * @param string $format If ommited, a DateTime object is returned
 * 
 * @return DateTime|string
 */
function ago($value, $format=false)
{
	$date = new DateTime;
	$date->sub(new DateInterval($value));
	return format($date, $format);
}

/**
 * Returns a date in the future, relative to now with the interval defined in parameter
 * @param string $interval @see DateInterval::__construct
 * @param string $format If ommited, a DateTime object is returned
 * 
 * @return DateTime|string
 */
function in($value, $format=false)
{
	$date = new DateTime;
	$date->add(new DateInterval($value));
	return format($date, $format);
}

/**
 * Checks if the date passed in parameter is already passed
 * @param string|DateTime $date
 * @return boolean
 */
function isPast($date)
{
	if (is_string($date))
		$date=new DateTime($date);
	return $date<new DateTime;
}

/**
 * Checks if the date passed in parameter is in the future
 * @param string|DateTime $date
 * @return boolean
 */
function isFuture($date)
{
	if (is_string($date))
		$date=new DateTime($date);
	return $date>new DateTime;
}