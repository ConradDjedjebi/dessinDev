/*
Tested on jQuery v2.1.4
	by aduh95

API to display a message to the user

Version 1.1 (07/2015)

msg	.alert	(since version 1.1)
	.wait	(since version 1.1)
*/
var msg = function (jQuery)
{
	var isNotDocument = function (doc) {
			return !doc || !doc.body || doc.body.ownerDocument!==doc;
		}, //var
		get_msg_box = function (doc) {
			// Create the HTMLElement if not existing yet
			if (isNotDocument(doc))
				doc = document;
			return jQuery(doc.getElementById("msg_box") || "<ul id='msg_box'></ul>").appendTo(doc.body);
		}, //var
		slideUp_msg_box = function ($msg, speed) {
			return function () {
				$msg.slideUp(speed, function(){jQuery(this).remove();});
			};
		}, //var
		new_msg = function (param, className) {
			return jQuery("<li class='msg_"+className+"'>"+param.msg+"</li>")
					.appendTo(get_msg_box(param.doc))
					.slideDown(param.slideDown_speed)
					.click(function(){
						jQuery(this).remove();
					});
		}, //var
		toObject = function (options, defaultOptions) {
			// Fonction assurant la retrocompatibilité et la présence de tous les parametres utiles
			return options ?
				jQuery.extend(
						defaultOptions,
						jQuery.isPlainObject(options) ? options : {msg:options, good_news: options.substr(0,1)==="­"}
					) :
				defaultOptions;
		} ;
	return {
		alert : function (options) {
			var param = toObject(options, {
					msg : "Une erreur s'est produite !",
					displayTime : 10000,
					good_news : false,
					// doc: document,
					slideUp_speed : "slow",
					slideDown_speed : null
				}), //var
				$msg = new_msg(param, param.good_news ? "info" : "warning");
			if (param.displayTime)
				setTimeout(slideUp_msg_box($msg, param.slideUp_speed), param.displayTime);
		},

		wait : function (options) {
			var param = toObject(options, {
					msg : "Veuillez patienter",
					// doc: document,
					slideUp_speed : "slow",
					slideDown_speed : null
				}), //var
				$msg = new_msg(param, "wait");
			return slideUp_msg_box($msg, param.slideUp_speed);
		}
	};
}($);