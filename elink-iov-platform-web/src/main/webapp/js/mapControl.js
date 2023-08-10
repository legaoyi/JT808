var MAP_TYPE = 1, MAP_DIV = "map_box";
var speedDashboardChart,speedDashboardChartOption;
var current_dialog_id;
var downMessageLimitMap = new JsMap();
var deviceListMap = new JsMap();

$(function() {
	initDatetimepicker();
	initDatepicker();
	initTimepicker();
});

function onLoadMapSuccess() {
	var url = "/common/gps/last/all.do";
	ajaxAsyncGet(url, "time="+new Date().getTime(),function(result){
		if (result.code != 0) {
		} else {
			var list = result.data;
			for(var i=0;i<list.length;i++){
				var item = list[i];
				var gpsInfo = $.parseJSON(item.gpsInfo);
				var lng = gpsInfo.lng
				var lat = gpsInfo.lat
				var simCode = item.simCode;
				
				var gcj02 = LngLatConverter.wgs84togcj02(lng, lat);
				var point = mapUtil.getPoint(gcj02[0], gcj02[1]);
				
				var marker = getMarker(simCode,point,gpsInfo.direction);
				addDeviceMarker(marker);
				
				var map = mapUtil.getMap();
				map.panTo(point);
			}
		}
	});
}

function getMarker(simCode,point,direction){
	var marker = mapUtil.getMarker(simCode, point, simCode,null, direction);
	marker.on('click', function(e) {
		var simCode = e.target.getExtData();
		var lacation = e.target.getPosition();
		showDeviceInfoWindow(simCode, lacation,null);

	});
	var text = mapUtil.addText(point, simCode);
	//selectedMarkerTextMap.put(simCode,text);
	return marker;
}

var show_device_infowindow;
var current_selected_device_simCode;
function showDeviceInfoWindow(simCode, lacation, gpsInfo) {
	if(lacation){
		current_selected_device_simCode = simCode;
		mapUtil.regeocoder(lacation, function(address) {
			var text = "<br/>SIM卡号：" + simCode;
			if (address) {
				text += "<br/>地址：" + address;
			}
			text += "<br/><span class='amap-info-window-menu'><a href='javascript:void(0);' onclick=\"showCmdDialog('gps-history-query-dlg');\" >轨迹</a></span>";
			text ="<div class='amap-info-window-content'>"+text+"</div>";
			// 设置label标签
			if(show_device_infowindow){
				mapUtil.resetLabel(show_device_infowindow,text,lacation);
			}else{
				show_device_infowindow = mapUtil.addLabel(lacation, text);
			}
			show_device_infowindow.on('close', function(e) {
				show_device_infowindow = null;
			});
		});
	}
}

function showCmdDialog(dialogId) {
	if (current_selected_device_simCode) {
		current_dialog_id = dialogId;
		$("#" + dialogId + "-frm-name").val(current_selected_device_simCode);
		$("#" + dialogId + "-frm-deviceId").val(current_selected_device_simCode);
		var title = $("#" + dialogId).attr("title");
		showDialog(title, dialogId);
	} else {
		showMessage("请选择要操作的车辆！");
	}
}
