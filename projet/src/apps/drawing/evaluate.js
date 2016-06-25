window.jQuery(function ($) {
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