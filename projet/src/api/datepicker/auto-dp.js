$(function () {
	$("input[type=date]").each(function(){
		var obj = {
			format:"yyyy-mm-dd",
			language:"fr",
			defaultViewDate:this.value
		};
		if(this.dataset.datetimeoptions)
			$.extend(obj, JSON.parse(this.dataset.datetimeoptions));
		if(this.min)
			obj.startDate	= this.min;
		if(this.max)
			obj.endDate		= this.max;
		$(this).datepicker(obj);
	})
});
