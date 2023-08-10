
function validForm(formId) {
	return $("#" + formId).valid();
}

function initTimepicker() {
	$(".form_time").datetimepicker({
		 language : "zh-CN",
		 weekStart: 1,
	     todayBtn:  0,
		 autoclose: 1,
		 todayHighlight: 1,
		 startView: 1,
		 minView: 0,
		 maxView: 1,
		 forceParse: 0,
		 showMeridian : 1,
		 zIndex : 457372800
	});
}

function initDatetimepicker() {
	$(".form_datetime").datetimepicker({
		language : "zh-CN",
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		forceParse : 0,
		showMeridian : 1,
		zIndex : 457372800
	});
}

function initDatepicker() {
	$(".form_date").datetimepicker({
		language : "zh-CN",
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		forceParse : 0,
		minView : 2,
		zIndex : 457372800
	});
}

function initMonthpicker() {
	$(".form_month").datetimepicker({
		language : "zh-CN",
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		startView : 3,
		forceParse : 0,
		minView : 3,
		zIndex : 457372800
	});
}

function showCommondDialog(dialogId) {
	var title = $("#" + dialogId).attr("title");
	showDialog(title, dialogId);
}

function initRangeDatetimepicker(startDatetimepicker, endDatetimepicker, limit) {// 日期范围
	$('#' + startDatetimepicker).datetimepicker({
		language : "zh-CN",
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		forceParse : 0,
		startView : 2,
		minView : 1,
		showMeridian : 1,
		zIndex : 457372800
	}).on('changeDate', function(e) {
		if(limit && limit > 0){
			var time = e.date;
			var endTime = new Date($("#" + endDatetimepicker).val().replace(/-/g, "/")).getTime();
			if( endTime < time ||  (endTime-time) > limit * 1000 * 60 * 60 * 24){
				var time = new Date(time.format("yyyy/MM/dd") + " 00:00:00");
				time.setTime(time.getTime() + limit * 1000 * 60 * 60 * 24);
				$("#" + endDatetimepicker).datetimepicker("setEndDate", time);
				$("#" + endDatetimepicker).val(time.format("yyyy-MM-dd hh:00:00"));
			}
		}
	});

	// 结束时间
	$('#' + endDatetimepicker).datetimepicker({
		language : "zh-CN",
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		minView : 1,
		forceParse : 0,
		showMeridian : 1,
		zIndex : 457372800
	}).on('changeDate', function(e) {
		if(!limit){
			limit=1
		}
		var time = e.date;
		var startTime = new Date($("#" + startDatetimepicker).val().replace(/-/g, "/")).getTime();
		if(time < startTime || (time-startTime) > limit * 1000 * 60 * 60 * 24){
			var time = new Date(time.format("yyyy/MM/dd") + " 00:00:00");
			time.setTime(time.getTime() - limit * 1000 * 60 * 60 * 24);
			$("#" + startDatetimepicker).val(time.format("yyyy-MM-dd hh:00:00"));
		}
	});
}

function initLimitDatetimepicker(startDatetimepicker, endDatetimepicker,
		startTime, endTime, endStartLimit, endEndLimit) {// 日期范围
	$('#' + startDatetimepicker).datetimepicker({
		language : "zh-CN",
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		forceParse : 0,
		startView : 2,
		minView : 1,
		showMeridian : 1,
		zIndex : 457372800
	}).on('changeDate', function(e) {
		var time = e.date;
		if (endStartLimit) {
			var start = new Date(time.format("yyyy/MM/dd hh") + ":00:00");
			start.setTime(start.getTime() + 1000 * 60 * 60 * endStartLimit);
			$("#" + endDatetimepicker).datetimepicker("setStartDate", start);
		} else {
			$("#" + endDatetimepicker).datetimepicker("setStartDate", time);
		}
		if (endEndLimit) {
			var end = new Date(time.format("yyyy/MM/dd") + " 00:00:00");
			end.setTime(end.getTime() + 1000 * 60 * 60 * endEndLimit);
			$("#" + endDatetimepicker).datetimepicker("setEndDate", end);
		}
		
		var end = $('#'+endDatetimepicker).val();
		var end = new Date(end.replace(/-/g, "/"));
		if(end.getTime() <= time){
			var n = endStartLimit;
			if (!n) {
				n=1;
			}
			time.setTime(time.getTime() + 1000 * 60 * 60 * n);
			$('#'+endDatetimepicker).val(new Date(time).format("yyyy-MM-dd hh:mm:ss"));
		}
	});

	// 结束时间
	$('#' + endDatetimepicker).datetimepicker({
		language : "zh-CN",
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		minView : 1,
		forceParse : 0,
		showMeridian : 1,
		zIndex : 457372800
	}).on('changeDate', function(e) {
		var time = e.date;
		var start = $('#'+startDatetimepicker).val();
		var start = new Date(start.replace(/-/g, "/"));
		
		if(start.getTime() >= time){
			var n = endStartLimit;
			if (!n) {
				n=1;
			}
			time.setTime(time.getTime() + 1000 * 60 * 60 * n);
			$('#'+endDatetimepicker).val(new Date(time).format("yyyy-MM-dd hh:mm:ss"));
		}
	});

	if (startTime) {
		$("#" + startDatetimepicker).datetimepicker("setStartDate", startTime);
		if (endStartLimit) {
			$("#" + endDatetimepicker).datetimepicker("setStartDate", startTime);
		}else{
			$("#" + endDatetimepicker).datetimepicker("setStartDate", startTime.getTime() + 1000 * 60 * 60 * endStartLimit);
		}
	}
	if (endTime) {
		$("#" + startDatetimepicker).datetimepicker("setEndDate", endTime);
		$("#" + endDatetimepicker).datetimepicker("setEndDate", endTime);
	}
}

function fullscreen() {
	elem = document.body;
	if (elem.webkitRequestFullScreen) {
		elem.webkitRequestFullScreen();
	} else if (elem.mozRequestFullScreen) {
		elem.mozRequestFullScreen();
	} else if (elem.requestFullScreen) {
		elem.requestFullscreen();
	} else {
		// 浏览器不支持全屏API或已被禁用
	}
}
function exitFullscreen() {
	var elem = document;
	if (elem.webkitCancelFullScreen) {
		elem.webkitCancelFullScreen();
	} else if (elem.mozCancelFullScreen) {
		elem.mozCancelFullScreen();
	} else if (elem.cancelFullScreen) {
		elem.cancelFullScreen();
	} else if (elem.exitFullscreen) {
		elem.exitFullscreen();
	} else {
		// 浏览器不支持全屏API或已被禁用
	}
}

function getFormatNumber(value,fixed){
	if (parseFloat(value).toString()=="NaN") {
      return value;
	}
	if(value > 10000 * 100000){
		return (value/(10000 * 100000)).toFixed(2) + "十亿"
	}else if(value > 10000 * 10000){
		return (value/(10000 * 10000)).toFixed(2) + "亿"
	}else if(value > 10000 * 1000){
		return (value/(10000 * 1000)).toFixed(2) + "千万"
	}else if(value > 10000 * 100){
		return (value/(10000 * 100)).toFixed(2) + "百万"
	}else if(value > 10000 * 10){
		return (value/(10000 * 10)).toFixed(2) + "十万"
	}else if(value > 10000){
		return (value/10000).toFixed(2) + "万"
	}else if(value > 1000){
		return (value/1000).toFixed(2) + "千"
	}
	if(fixed){
		try{
			return value.toFixed(fixed);
		}catch(e){}
	}
	return value;
}
