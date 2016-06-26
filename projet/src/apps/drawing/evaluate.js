window.jQuery(function ($) {
	// Affichage de l'image
	var apercu = new Image;
	apercu.className = "col-xs-6";
	apercu.style.maxHeight = "15em";

	$("<div class='row'></div>").append(
		$("select[name=ref_Dessin]").change(function () {
			ajaxpost("download.php", "format=base64&drawing="+parseInt(this.value), function (data) {
				apercu.src = data.result ? "data:"+data.data.MIME+";base64,"+data.data.img : "data:null";
			}, "json", false, "Aucun dessin à afficher");
		}).parent().addClass(apercu.className),
		apercu).insertBefore(document.getElementById("juries").parentNode);

	// Ajout du nom des Jury de manière dynamique
	var smalls = new Array;

	$("fieldset:not(:first-child):not(:last-child)>legend").each(function () {
		this.appendChild(document.createElement("small"));
		smalls.push(this.lastChild);
	});

	$("#juries").change(function () {
		var i=-1;
		var options = this.options;

		for (var j = 0; j < 2; ++j) {
			while(options[++i] && !options[i].selected);

			if(i >= options.length)
				return; // Si il a moins de deux juries séléctionnés

			smalls[j].innerText = options[i].textContent;
		}

		// Limitation à deux juries
		while (i < options.length)
			options[++i].selected = false;
	});
});
