/*
Tested on jQuery v2.1.4
	by aduh95

API to display a message to the user

Version 1.1 (07/2015)

msg	.alert	(since version 1.1)
	.wait	(since version 1.1)
*/
var msg = function (jQuery, window)
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
			return jQuery("<li class='alert alert-"+className+"'>"+param.msg+"</li>")
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
					debug : false,
					success : false, 
					// doc: document,
					slideUp_speed : "slow",
					slideDown_speed : null
				}), //var
				$msg = new_msg(param, param.success ? "success" : "danger");
			if (!param.debug && param.displayTime)
				setTimeout(slideUp_msg_box($msg, param.slideUp_speed), param.displayTime);
		},

		wait : function (options) {
			var param = toObject(options, {
					msg : "Veuillez patienter",
					// doc: document,
					slideUp_speed : "slow",
					slideDown_speed : null
				}), //var
				$msg = new_msg(param, "info");
			return slideUp_msg_box($msg, param.slideUp_speed);
		},

		confirm : function (options) {
			var param = toObject(options, {
					msg : "Êtes-vous sûr ?",
					// doc : document,
					url : "#",
					withPassword : false,
					token : false,
					title : "Demande de confirmation",
					buttons : [
							{title:"OK", type:"success", name:"OK", default:true},
							{title:"Annuler", "type":"danger", name:"cancel"}
						],
					data : new Object,
					callback : jQuery.noop
				}), //var
				fenetre = window.fenetre || window.top.fenetre || {};
			;;;console.log(param)

			if (fenetre.create)
			{
				var $fieldset = jQuery("<fieldset>"), //var
					$form = jQuery("<form>")
							.attr("action", param.url)
							.submit(param.callback)
							.submit(function (e) {
								e.preventDefault();
								/*setTimeout(*/fenetre.close(/*, 999*/);
							})
							.on("reset", function(){fenetre.close();})
							.append($fieldset), //var
					$buttons = jQuery("<div class='btn-group'>");

				jQuery.each(param.data, function (name) {
					jQuery("<input type='hidden'>").attr({name:name, value:this}).appendTo($fieldset);
				});

				$fieldset.append("<h3>"+param.msg+"</h3>", "<input type='hidden' name='token' value='"+param.token+"'>");
				if (param.withPassword)
					$fieldset.append("<div class='form-group'><label>Mot de passe pour autentification : <input type='password' name='password'></label></div>");

				jQuery.each(param.buttons, function () {
					jQuery("<input type='"+(this.type==="success" ? "submit" : "reset")+"' class='btn btn-"+this.type+"'>")
						.attr("name", this.name)
						.val(this.title)
						.appendTo($buttons);
				});
				$buttons.appendTo($fieldset);

				fenetre.create(param.title, $form/*, param.withPassword || "sm"*/);
				$form.find("input:first")[0].focus();
			}
			else if (window.confirm(param.msg))
				param.callback();
		}
	};
}($, this);
