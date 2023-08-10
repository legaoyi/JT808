function MapUtil() {
	var map;
	this.loadJScript = function(key,mapType) {
		var type = mapType || "gaode";
		if (type=="baidu") {
			map = new BaiduMap(key);
		} else if (type=="gaode"){
			map = new GaodeMap(key);
		}
		map.loadJScript(key);
	};

	this.init = function(container, type) {
		map.init(container, type);
	};

	this.addMarker = function(markerId, point, text, iconUrl, rotation) {
		return map.addMarker(markerId, point, text, iconUrl, rotation);
	};
	
	this.getMarker = function(markerId, point, text, iconUrl, rotation) {
		return map.getMarker(markerId, point, text, iconUrl, rotation);
	};
	
	this.addText = function(position, text) {
		return map.addText(position, text);
	}

	this.setTextPosition = function(text, position) {
		map.setTextPosition(text, position);
	};

	this.addMarkerLabel = function(currentMarker, text) {
		return map.addMarkerLabel(currentMarker, text);
	};

	this.addLabel = function(point, text) {
		return map.addLabel(point, text);
	};

	this.resetLabel = function(infoWindow,content,position){
		return map.resetLabel(infoWindow,content,position);
	};

	this.clearOverlays = function() {
		map.clearOverlays();
	};

	this.removeOverlay = function(overlay) {
		map.removeOverlay(overlay);
	};

	this.getPoint = function(lng, lat) {
		return map.getPoint(lng, lat);
	};

	this.getDistance = function(point1, point2) {
		return map.getDistance(point1, point2);
	};

	this.addCircleMarker = function(center){
		return map.addCircleMarker(center);
	};
	
	
	this.drawPolyline = function(points, color) {
		return map.drawPolyline(points, color);
	};

	this.setCenter = function(lng, lat) {
		map.setCenter(this.getPoint(lng, lat));
	};

	this.getCenter = function() {
		return map.getCenter();
	};

	this.getMap = function() {
		return map.getMap();
	};

	this.getDistrictSearch = function() {
		return map.getDistrictSearch();
	};

	this.setLabelPosition = function(label, point) {
		map.setPosition(label, point);
	};

	this.setMarkerPosition = function(marker, point) {
		map.setMarkerPosition(marker, point);
	};

	this.setMarkerRotation = function(marker, rotation) {
		map.setMarkerRotation(marker, rotation)
	};

	this.setMarkerIcon = function(marker, url) {
		map.setMarkerIcon(marker, url);
	};

	this.panTo = function(point) {
		map.panTo(point);
	};

	this.setMapStyle = function(styleName) {
		map.setMapStyle(styleName);
	}

	this.openDraw = function() {
		map.openDraw();
	};

	this.closeDraw = function() {
		map.closeDraw();
	};

	this.drawCircle = function(drawCompletedHandler) {
		map.drawCircle(drawCompletedHandler);
	};

	this.addCircle = function(point, radius, color) {
		return map.addCircle(point, radius, color);
	};

	this.drawRectangle = function(drawCompletedHandler) {
		map.drawRectangle(drawCompletedHandler);
	};

	this.drawPolygon = function(drawCompletedHandler) {
		map.drawPolygon(drawCompletedHandler);
	};
	
	this.drawLine = function(drawCompletedHandler) {
		map.drawLine(drawCompletedHandler);
	};

	this.addPolygon = function(path, color) {
		return map.addPolygon(path, color);
	};

	this.regeocoder = function(point, resultHandler) {
		map.regeocoder(point, resultHandler);
	}
	
	this.wgs842gcj02 = function(path,resultHandler){
		map.wgs842gcj02(path,resultHandler);
	}
	
	this.graspDriving = function(path,resultHandler){
		map.graspDriving(path,resultHandler);
	}
}

function TracePlayer(mapUtil, marker, points) {
	var mapUtil = mapUtil;
	var points = points;
	var marker = marker;
	var timer;
	var centerPoint = mapUtil.getCenter();
	var index = 0;
	var self = this;

	this.play = function() {
		var point = points[index];
		mapUtil.setMarkerPosition(marker, point);
		if (index > 0) {
			var lastPoint = points[index - 1];
			mapUtil.drawPolyline([ lastPoint, point ]);
			mapUtil.setMarkerRotation(marker, getAngle(lastPoint, point));
		}

		index++;

		if (index < points.length) {
			timer = window.setTimeout(function() {
				self.play();
			}, 200);
		} else {
			mapUtil.panTo(point);
		}
	}

	this.pause = function() {
		if (timer) {
			window.clearTimeout(timer);
		}
	}

	this.reset = function() {
		if (timer) {
			window.clearTimeout(timer);
		}
		index = 0;
		mapUtil.setMarkerPosition(marker, points[0]);
		map.panTo(centerPoint);
	}
}

function getAngle(firstPoint, nextPoint) {
	var dRotateAngle = Math.atan2(Math.abs(firstPoint.lng - nextPoint.lng),
			Math.abs(firstPoint.lat - nextPoint.lat));
	if (nextPoint.lng >= firstPoint.lng) {
		if (nextPoint.lat >= firstPoint.lat) {
		} else {
			dRotateAngle = Math.PI - dRotateAngle;
		}
	} else {
		if (nextPoint.lat >= firstPoint.lat) {
			dRotateAngle = 2 * Math.PI - dRotateAngle;
		} else {
			dRotateAngle = Math.PI + dRotateAngle;
		}
	}
	dRotateAngle = dRotateAngle * 180 / Math.PI;
	return dRotateAngle;
}