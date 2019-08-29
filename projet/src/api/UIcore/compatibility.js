// Check the capacity of the user's browser to use the JavaScript APIs that project needs
(window.FormData && window.JSON && window.JSON.parse && window.XMLHttpRequest) || window.alert("Attention ! Votre navigateur n'est pas compatible avec ce site, votre naviguation se trouve fortement compromise. Il est conseillé de réessayer avec un navigateur compatible HTML5.");
if(window===window.top)
	window.onload = function () {
		var divtest = this.document.createElement("div");
		divtest.id = "compatibility_warning";
		this.document.body.appendChild(divtest);
		divtest.innerHTML = "<span data-test='test'></span>";
		if(divtest.firstChild.dataset && divtest.firstChild.dataset.test && divtest.firstChild.dataset.test==="test")
			divtest.parentNode.removeChild(divtest);
		else
			divtest.innerHTML = "Ce site internet utilise la technologie HTML5, or votre navigateur semble pas compatible avec cette norme. Si vous rencontrez des problèmes lors de votre visite, merci de réessayer avec un autre navigateur. Désolé pour le désagrément !";
		divtest.onclick = function () {
			this.parentNode.removeChild(this);
		};
	};
