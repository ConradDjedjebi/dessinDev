var ajaxpost = function (windowOriginal, $, undefined)
{
    var feedback = function (event, data) {
            msg.alert({success:data.result, msg:data.msg});
            return data.result;
        }, //var
        FormData = windowOriginal.FormData, //var
        defaultReturnedType = "json", //var
        ajaxpost = function (url, data, fct, type, wait_msg, error_msg)
        {
            return (data instanceof FormData ?
             $.ajax({
                url: url,
                data: data,
                processData: false,
                contentType: false,
                method: 'POST',
                success: fct,
                dataType: type
             }) :
             $.post(url, data, fct, type))
                .always(msg.wait(wait_msg || "Chargement en cours"))
                .fail(function (jqXHR){
                    var msg_error;
                    switch(jqXHR.status)
                    {
                        case 200:
                        ;;;console.log((function () {msg.alert(jqXHR.responseText);return jqXHR.responseText;})())
                        // ;;;console.log({msg:"Invalid response (MIME type unmatched)",args:[url, data, fct, type],textAnswer:type!=="text"&&ajaxpost(url, data, msg.alert,"text", "Débugage")});
                        msg_error = "Réponse invalide du serveur";
                        break;

                        case 401:
                        msg_error = "Niveau de privilège insuffisant";
                        break;

                        case 403:
                        msg_error = "Opération illégale";
                        break;

                        case 404:
                        msg_error = "Serveur injoignable";
                        break;
                        
                        case 410:
                        msg_error = "Vous devez vous connecter de nouveau";
                        break;

                        case 500:
                        msg_error = "Erreur du serveur";
                        break;

                        default:
                        msg_error = "Erreur de communication";
                    }
                    msg.alert(error_msg || msg_error);});
        };
    $(document).ready(function(){
        // Utilisation de AJAX pour l'envoie des formulaires
        var eventName = "ajax:response", //var
            classname="ajax-active", //var
            ajaxfriendly = function (e, data) {
                var DATADATA = data.data, //var
                    window = this.ownerDocument.defaultView, //var
                    fen = window.fenetre || window.top.fenetre;


                if(DATADATA.reload)
                    window.top.location.reload();

                if(DATADATA.redirect)
                    (DATADATA.topRedirect ? windowOriginal.top.document : document).location.href = DATADATA.redirect;

                if(DATADATA.modal && fen)
                    fen.frame_src(DATADATA.modal.url, DATADATA.modal.data);

                if(DATADATA.confirm && msg && msg.confirm)
                {
                    if(!DATADATA.confirm.callback)
                        DATADATA.confirm.callback = function (event) {
                                var $form = $(this), //var
                                    window = this.ownerDocument.defaultView; // contourne un bug qui rendait window assigné à un objet de type != Window

                                if(document.activeElement && document.activeElement.name==="cancel")
                                    return;
                                window.ajaxpost(/*event ?*/ this.action/* || DATADATA.url*/, /*event ?*/ $form.serialize()/* || DATADATA.data*/, function(data){
                                    ;;;window.console.log(data)

                                    $form.on(eventName, ajaxfriendly).on(eventName, feedback).trigger(eventName, data);
                                }, "json", "wait", "error");
                            };
                    msg.confirm(DATADATA.confirm); 
                }

                if(DATADATA.warnings)
                    $.each(DATADATA.warnings, function () {
                        msg.alert(this);
                    });
                
                if (data.result)
                    this.reset();
            }, //var
            $form = $("form").submit(function(e){
                    var $form = $(this).fadeTo(1,.3);
                    var form = this;
                    var CKEDITOR = windowOriginal.CKEDITOR;
                    e.preventDefault();
                    if($form.data("submitting"))
                        return;
                    else
                        $form.data("submitting", true);

                    if(CKEDITOR)
                        $.each(CKEDITOR.instances, function () {
                            if(this.element.$.form===form)
                                this.updateElement();
                        });

                    ajaxpost(this.action, new FormData(this), function (data) {
                            ;;;console.log(data);
                            
                            ;;;if(!windowOriginal.top.debugAJAX)
                                $form.trigger(eventName, data);
                        }, defaultReturnedType)
                    .always(function(){
                            setTimeout(function(){$form.fadeTo(1,1).data("submitting", false);}, 500);});
                }).on(eventName, ajaxfriendly).on(eventName, feedback);


        $form.filter(".autoSend").find(":input").change(function(){
            var fa_icons = "glyphicon-refresh glyphicon-remove glyphicon-check", //var
                parent = this.parentNode, //var
                $parent = parent.tagName==="DIV" ? 
                                          $(parent).removeClass("has-error has-success")
                                        : $("<div class='has-feedback'></div>").appendTo(parent).append(this, '<span class="glyphicon form-control-feedback"></span>'), //var
                failFunc = function(data){
                    ;;;console.log(data);
                    var success = data && !data.status && feedback(null, data);
                    $parent.addClass("has-"+(success ? "success" : "error")).children(":last").removeClass(fa_icons).addClass("glyphicon-"+(success ? "ok" : "remove"));
                };
            $parent.addClass("has-warning").children(":last").removeClass(fa_icons).addClass("glyphicon-refresh");
            if(this.checkValidity && !this.checkValidity())
                msg.alert(this.validationMessage) && failFunc();
            else
                ajaxpost(this.form.action, $(this).serialize(), failFunc, defaultReturnedType).fail(failFunc).always(function () {
                    $parent.removeClass("has-warning");
                });
        });

        $form.filter(".betterCheckboxes").each(function(){
            var submitGroup;
            $(this)
                .on(eventName, function(e, data){
                        if(data.result)
                            $(submitGroup).children().toggleClass("active").each(function(){
                                this.type = this.type==="submit" ? "button" : "submit";
                            });
                        submitGroup = null;
                    })
                .find(":checkbox").not(".classicCheckbox").each(function (){
                    var $btnGroup = $("<div class='btn-group col-sm-2'><button value='on' class='btn-success'>Oui</button><button value='no' class='btn-danger'>Non</button></div>");
                    $btnGroup
                        .mousedown(function(){
                            submitGroup = this;
                        })
                        .children()
                            .addClass("btn").attr("name", this.name).attr("type", "submit")
                            .eq(!this.checked).addClass("active").attr("type", "button");

                    if(this.disabled)
                        $btnGroup.children(":submit").attr({type: "button", disabled: true});

                    $(this.parentNode).addClass("col-sm-10").after($btnGroup).parent().addClass("betterCheckbox").removeClass("checkbox");
                    $(this).remove();
                });
        });


        $(":input[required]").parent().addClass("form-group-required");
        $(document.head).append("<style type='text/css'>\
            .betterCheckbox:hover>label::before{\
                content:'\\b7';\
                position:absolute;\
                left:.6em;\
            }\
            \
            }</style>");
    });
    return ajaxpost;
}(this, this.jQuery);