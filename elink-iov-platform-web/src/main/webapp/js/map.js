var mapUtil;
var pointSimplifierIns, markerClusterer;

$(function() {
	var key = "高德地图key";
	mapUtil = new MapUtil();
	mapUtil.loadJScript(key);
});

// 加载地图
function initMap() {

	var type = 1;
	if (typeof (MAP_TYPE) == "undefined") {

	} else {
		type = MAP_TYPE;
	}
	var div;
	if (typeof (MAP_DIV) == "undefined") {
		div = "map_box";
	} else {
		div = MAP_DIV;
	}

	mapUtil.init(div, type);
	mapUtil.setMapStyle("blue");
	var script = document.createElement("script");
	script.type = "text/javascript";
	
	var url = "webapi.amap.com/ui/1.0/main.js?v=1.0.11";
	if(window.isSecureContext===false){
		url = "http://" + url;
	}else{
		url = "https://" + url;
	}
	
	script.src = url;
	
	document.body.appendChild(script);

	if (typeof (onLoadMapSuccess) == "function") {
		// 按行政区查车
		setTimeout("onLoadMapSuccess()", 2 * 1000);
	}
}

function setDevicePointSimplifier(data) {
	if (pointSimplifierIns) {
		pointSimplifierIns.setData(data);
		return;
	}
	AMapUI.load([ 'ui/misc/PointSimplifier', 'lib/$' ], function(
			PointSimplifier, $) {
		if (!PointSimplifier.supportCanvas) {
			showAlertMessage('当前浏览器不支持 Canvas，请使用其他浏览器查看！');
			return;
		}

		initPointSimplifier(data, PointSimplifier, $);
	});
}

function initPointSimplifier(data, PointSimplifier, $) {
	var groupStyleMap;
	pointSimplifierIns = new PointSimplifier({
		map : mapUtil.getMap(), // 所属的地图实例
		getPosition : function(item) {
			if (!item) {
				return null;
			}
			// 返回经纬度
			var lacation = item.lacation;
			return [ lacation.getLng(), lacation.getLat() ];
		},
		getHoverTitle : function(item, idx) {
			return item.deviceName;
		},
		// 鼠标hover时的title信息
		hoverTitleStyle : {
			position : 'top'
		},
		// 使用GroupStyleRender
		renderConstructor : PointSimplifier.Render.Canvas.GroupStyleRender,
		renderOptions : {
			getGroupId : function(item, idx) {
				return item.state;
			},
			groupStyleOptions : function(state) {
				return groupStyleMap[state];
			}
		}
	});

	pointSimplifierIns.setData(data);

	// 监听事件
	pointSimplifierIns.on('pointMouseover', function(e, item) {
		var data = item.data;
		if (e.type == 'pointMouseover'
				&& typeof (showDeviceInfoWindow) == "function") {
			showDeviceInfoWindow(data.deviceId, data.lacation);
		}
	});

	groupStyleMap = {
		'2' : {
			pointStyle : {
				// 绘制点占据的矩形区域
				content : PointSimplifier.Render.Canvas.getImageContent(
						'/img/car-grey.png', onPointSimplifierIconLoad,
						onPointSimplifierIconError),
				// 宽度
				width : 25,
				// 高度
				height : 25,
				fillStyle : null,
			// strokeStyle: null
			}
		},
		'3' : {
			pointStyle : {
				// 绘制点占据的矩形区域
				content : PointSimplifier.Render.Canvas.getImageContent(
						'/img/car-green.png', onPointSimplifierIconLoad,
						onPointSimplifierIconError),
				// 宽度
				width : 25,
				// 高度
				height : 25,
				fillStyle : null,
			// strokeStyle: null
			}
		}
	};

	function onPointSimplifierIconLoad() {
		pointSimplifierIns.renderLater();
	}

	function onPointSimplifierIconError(e) {
		showMessage('图片加载失败！');
	}
}

function setDeviceMarkerClusterer(markers) {
	if(markers && markers.length>0){
		if (markerClusterer) {
			markerClusterer.setMarkers(markers);
		}else{
			markerClusterer = new AMap.MarkerClusterer(mapUtil.getMap(), markers, {
				gridSize : 80,
				minClusterSize : 50,
				maxZoom : 15
			});
		}
		var lngLat = markers[0].getPosition();
		mapUtil.setCenter(lngLat.getLng(), lngLat.getLat());
	}
}

function addDeviceMarker(marker) {
	if (markerClusterer) {
		markerClusterer.addMarker(marker);
	} else {
		var markers = [];
		markers.push(marker);
		setDeviceMarkerClusterer(markers);
	}
}

function clearDeviceMarkers(){
	if(markerClusterer){
		markerClusterer.clearMarkers();
	}
}
