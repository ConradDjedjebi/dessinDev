window.jQuery(function ($) {
	$("a[download]").not(".classicDownload").click(function (e) {
		e.preventDefault();

		download(this.href);
	});
});
