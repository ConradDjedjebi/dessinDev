$(document).ready(function () {
	var $table = $("table");
	
	// $.fn.DataTable.defaults.language={url:"//cdn.datatables.net/plug-ins/1.10.10/i18n/French.json"};
	
	// $table.filter(".dataTable").DataTable();
	$table.filter(".table_link").each(function () {
		var data = this.dataset;
		$(this).children("tbody").children("tr").click(function (e) {
			var fen = window.fenetre || window.top.fenetre;
			var post = JSON.parse(e.target.dataset.sent || this.firstChild.firstChild.dataset.sent);
			if(undefined!==data.fenetre && fen)
				fen.frame_src(data.href, post);
			else
			{
				var form = document.createElement("form");
				var fieldset = document.createElement("fieldset");
				form.action = data.href;
				if (data.method)
					form.method = data.method;
				$.each(post, function (name) {
					var input = document.createElement("input");
					input.type = "hidden";
					input.name = name;
					input.defaultValue = this;
					fieldset.appendChild(input);
				});
				form.appendChild(fieldset);
				document.body.appendChild(form);
				form.submit();
			}

		});
	});
	$table.filter(".table-rowed").find("tbody>tr>:first-child").each(function(){
		$(this).replaceWith("<th>"+this.innerHTML+"</th>");
	});
});
