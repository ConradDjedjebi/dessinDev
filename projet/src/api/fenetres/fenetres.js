/*
Tested on jQuery v2.1.4, Bootstrap 3.3.4

Use jQuery, bootstrap, ajaxfriendly

API to create modal elements in your HTML.

Created By aduh95
Version 1.1 (February 2016)
*/
var fenetre = function (jQuery) {
	var $modal, //var
		frame = function (title, src, fct_onload) { // protected
			var frame = document.createElement("iframe");
			frame.seamless=true;
			frame.srcdoc=src;
			frame.src="//"+location.hostname+"/no_srcdoc";

			fenetre.create(title, frame, "lg").onload = fct_onload;
			// return frame; //#desactiver car inutile
		}, //var
		fenetre = { // Public functions/methods
			modal : undefined,

			create: function (title, content, className)
			{
				/*
				* Create a DOM object (movable-"window") and add it to the body of the top window (window.top.document.body).
				* @param string title : Title of your "window"
				* @param (Anything accepted by jQuery) content : The content added to the object
				*
				* @return (@param content)
				*/
				fenetre.change_title(title);
				$modal
					.children().toggleClass("modal-lg", className==="lg").toggleClass("modal-sm", className==="sm")
					.find('.modal-body:first').empty().append(content).removeClass('modal-frame');
				this.modal('show');

				return content; // make the load event listener easier for frames
			},

			frame_src : function (src, data, fct_onload, msg_wait)
			{
				/*
				* Get by AJAX the source from an url, and create an iframe within a "window". The title is given by the HTML <title> element of the new document.
				* @param string src : URL of the document
				* @param mixed data : Data send by post to src
				* @param callable fct_onload : Function called when the iframe is loaded
				* @param string msg_wait : Message prompted during the loading (if ommited, default msg.alert is raised)
				*
				* @return void
				*/
				ajaxpost(src, data, function (data) {
					if(!data)
						return msg.alert("Le chargement a echoué");
					frame("Chargement en cours…", data, function(){
						var that = this;
						fenetre.change_title(jQuery(this.contentDocument.head).children("title").text());
						jQuery(this.parentNode).addClass('modal-frame');
						this.contentWindow.onresize = function () {
							var $modalDialog = $modal.children(), //var
								marginTop = $modalDialog.css("margin-top"),
								$emptyModal = $modalDialog.clone();

							$emptyModal.children().children(".modal-frame").empty();
							$emptyModal.appendTo(this.top.document.body);

							that.style.height = Math.min(jQuery(this.document.body).outerHeight(), this.top.innerHeight - Number(marginTop.substr(0, marginTop.length-2))*2 - $emptyModal.outerHeight())+"px";
							$emptyModal.remove();
						};
						this.contentWindow.onresize();
						if(fct_onload) fct_onload(this);
					});
				}, "html", msg_wait);
			},

			frame_doc : function (title, content, fct_onload)
			{
				/*
				* Create an iframe within a "window" containing the content -- usefull to clone elemnts avoiding ID doublons.
				* TIP: If you want to pass only the HTML code (and not event listeners, etc.), use proprety outerHTML on the content.
				* @param title : same as this.create
				* @param content : same as this.create
				* @param fct_onload : same as this.frame_src
				*
				* @return void
				*/
				frame(title, "<body></body>", function(){
					jQuery(this.contentDocument.body).append(content);
					if(fct_onload) fct_onload(this);
					jQuery(this.parentNode).addClass('modal-frame');
				});
			},

			change_title : function (title)
			{
				/*
				* Change the title displayed of the "window"
				* @param (Anything accepted by jQuery) window_elem : Any element into a "window" or the "window" itself. If ommited, uses the keyword this instead
				* @return void
				*/
				$modal.find('.modalWindowTitle').text(title);
			},

			close : function ()
			{
				this.modal("hide");
			}
		};

	jQuery(document).ready(function($){
		$modal = $("#modalWindow");
		fenetre.modal = $.proxy($modal.modal, $modal);
	});

	return (document!==window.top.document && window.top.fenetre) ? window.top.fenetre : fenetre;
}($);