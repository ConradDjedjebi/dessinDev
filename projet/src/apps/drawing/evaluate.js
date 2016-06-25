window.jQuery(function ($) {
	// Affichage de l'image
	var apercu = new Image;
	apercu.className = "col-xs-6";
	apercu.style.maxHeight = "15em";

	$("select[name=ref_Dessin]").change(function () {
		ajaxpost("download.php", "format=base64&drawing="+parseInt(this.value), function (data) {
			apercu.src = "data:"+data.data.MIME+";base64,"+data.data.img;
		}, "json");
	}).parent().addClass(apercu.className).after(apercu);

	// Ajout du nom des Jury de manière dynamique
	var smalls = new Array;

	$("fieldset:not(:first-child):not(:last-child)>legend").each(function () {
		this.appendChild(document.createElement("small"));
		smalls.push(this.lastChild);
	});

	$("#juries").change(function () {
		var i=-1;
		for (var j = 0; j < 2; ++j) {
			while(!this.children[++i].selected)
			{
				if(i >= this.childElementCount)
					return; // Si il a moins de deux juries séléctionnés
			}
			smalls[j].innerText = this.children[i].textContent;
		}

		// Limitation à deux juries
		while (i < this.childElementCount)
			this.children[++i].selected = false;
	});
});
