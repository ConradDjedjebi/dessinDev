$(document).ready(function () {
	$("input[type=datetime-local]").each(function(){
		var obj = {
			format:"YYYY-MM-DDTHH:mm",
			locale:"fr",
			defaultDate:this.value
		};
		if(this.dataset.datetimeoptions)
			$.extend(obj, JSON.parse(this.dataset.datetimeoptions));
		if(this.min)
			obj.minDate	= this.min;
		if(this.max)
			obj.maxDate	= this.max;
		$(this).datetimepicker(obj);
	})
});