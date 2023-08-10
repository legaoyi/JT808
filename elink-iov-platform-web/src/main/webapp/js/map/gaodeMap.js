function GaodeMap() {

	var map;

	var drawingManager = null;

	var mapDrawListener = null;

	var districtSearch = null;
	
	var graspRoad = null;

	var shapeOptions = {
		strokeColor : "red", // 线颜色
		strokeOpacity : 1, // 线透明度
		strokeWeight : 3, // 线粗细度
		fillColor : "#ee2200", // 填充颜色
		fillOpacity : 0
	// 填充透明度
	};

	this.loadJScript = function(key) {
		var script = document.createElement("script");
		script.type = "text/javascript";
		
		var url="webapi.amap.com/maps?v=1.4.15&key=" + key + "&callback=initMap";
		if(window.isSecureContext===false){
			url = "http://" + url;
		}else{
			url = "https://" + url;
		}
		
		script.src = url;
		document.body.appendChild(script);
	};

	this.init = function(container, type) {
		// 高德地图API功能,初始化加载地图时，若center及level属性缺省，地图默认显示用户当前城市范围
		map = new AMap.Map(container);// 创建Map实例
		map.setZoom(11);// 设置地图级别
		if (type == 1) {
			AMap.plugin([ 'AMap.Autocomplete', 'AMap.GraspRoad','AMap.MarkerClusterer','AMap.Geocoder'],// 'AMap.ToolBar',
			function() {
				// map.addControl(new AMap.ToolBar());// 集成了缩放、平移、定位等功能按钮在内的组合控件
			});

			map.on('click', function(e) {
				try {
					closeDraw();
				} catch (e) {
				}
				try {
					mapOnClickEvent(e);
				} catch (e) {
				}
			});

			// 加载行政区划插件
			AMap.service("AMap.DistrictSearch", function() {
				var opts = {
					subdistrict : 1, // 返回下一级行政区
					showbiz : false
				// 最后一级返回街道信息
				};
				// 实例化DistrictSearch
				districtSearch = new AMap.DistrictSearch(opts);
			});
		} else if (type == 0) {
		}
	};

	initDrawingManager = function() {

	}

	this.getMap = function() {
		return map;
	};

	this.getDistrictSearch = function() {
		return districtSearch;
	};

	this.setCenter = function(point) {
		map.panTo(point);
	};

	this.getCenter = function() {
		return map.getCenter();
	};

	this.panTo = function(point) {
		map.panTo(point);
	};

	this.setMapStyle = function(styleName) {
		styleName = "amap://styles/" + styleName;
		map.setMapStyle(styleName);
	}

	this.addMarker = function(markerId, point, text, iconUrl, rotation) {
		var currentMarker = this.getMarker(markerId, point, text, iconUrl, rotation);
		currentMarker.setMap(map);
	    currentMarker.setTitle(text);
		return currentMarker;
	};
	
	this.getMarker = function(markerId, point, text, iconUrl, rotation) {
		if (!rotation) {
			rotation = 0;
		}
		return new AMap.Marker({
			position : point,
			angle : rotation,
			icon : iconUrl,
			title : text,
			extData : markerId
		}); 
	}
	
	this.addText = function(position, text) {
		var mapText = new AMap.Text({
	        text:text,
	        position: position,
	        offset: new AMap.Pixel(-45, -15)
	    });
		mapText.setMap(map);
		return mapText;
	}
	
	this.setTextPosition = function(text, position) {
		text.setPosition(position);
	};
	
	this.addMarkerLabel = function(marker, text) {
		// 设置label标签
		// currentMarker.setLabel({//label默认蓝框白底左上角显示，样式className为：amap-marker-label
		// offset: new AMap.Pixel(25,-8),//修改label相对于maker的位置
		// content:text
		// });

		this.addLabel(marker.getPosition(), text);
	}

	this.addLabel = function(point, text) {
		// map.clearInfoWindow();
		// 构建信息窗体中显示的内容
		var info = document.createElement("div");
		info.className = "info";
		// 定义内容
		var middle = document.createElement("div");
		middle.className = "info-middle";
		middle.style.backgroundColor = 'white';
		middle.innerHTML = text;
		info.appendChild(middle);

		var infoWindow = new AMap.InfoWindow({
			content : info,
		    offset : new AMap.Pixel(5,-25)
		});

		infoWindow.open(map, point);
		return infoWindow;
	}
	
	this.resetLabel = function(infoWindow,content,position){
		if(!infoWindow){
			return ;
		}
		
		if(content){
			infoWindow.setContent(content);
		}
		
		if(position){
			infoWindow.setPosition(position);
		}
	}
	
	this.setLabelPosition = function(label, point) {
		label.setPosition(point);
	};

	this.setMarkerPosition = function(marker, point) {
		marker.setPosition(point);
	};

	this.setMarkerRotation = function(marker, rotation) {
		marker.setAngle(rotation);
	};

	this.setMarkerIcon = function(marker, url) {
		marker.setIcon(url);
	};

	this.getPoint = function(lng, lat) {
		return new AMap.LngLat(lng, lat);
	};

	this.getDistance = function(point1, point2) {
		return point1.distance(point2);
	};

	this.addCircleMarker = function(center){
		 var circleMarker = new AMap.CircleMarker({
	          center:center,
	          radius:5,
	          strokeColor:'red',
	          strokeWeight:2,
	          strokeOpacity:0.5
	       });
	       circleMarker.setMap(mapUtil.getMap());
	       return circleMarker;
	};

	this.drawPolyline = function(points, color) {
		if (!color) {
			color = "red";// "#F33",
		}

		var polyline = new AMap.Polyline({
			path : points, // 设置线覆盖物路径
			strokeColor : color, // 线颜色
			strokeOpacity : 1, // 线透明度
			strokeWeight : 3, // 线宽
			strokeStyle : "solid", // 线样式
			strokeDasharray : [ 10, 5 ]
		// 补充线样式
		});
		polyline.setMap(map);
		return polyline;
	};

	this.clearOverlays = function() {
		map.clearMap();
		// map.clearInfoWindow();
	};

	this.removeOverlay = function(overlay) {
		map.remove(overlay);
	};

	this.openDraw = function() {

	}

	this.closeDraw = function() {
		if (drawingManager) {
			drawingManager.close(false);
		}
		if (mapDrawListener) {
			AMap.event.removeListener(mapDrawListener);
		}
	}

	this.drawCircle = function(drawCompletedHandler) {
		map.plugin([ "AMap.MouseTool" ], function() {
			drawingManager = new AMap.MouseTool(map);
			drawingManager.circle(shapeOptions);
			mapDrawListener = AMap.event.addListener(drawingManager, "draw",
					drawCompletedHandler);
		});
	}

	this.addCircle = function(point, radius, color) {
		if (!color) {
			color = "red";// "#F33",
		}
		var circle = new AMap.Circle({
			center : point,// 圆心位置
			radius : radius
		// 半径
		});
		circle.setOptions(shapeOptions);
		circle.setMap(map);
		return circle;
	}

	this.drawRectangle = function(drawCompletedHandler) {
		map.plugin([ "AMap.MouseTool" ], function() {
			drawingManager = new AMap.MouseTool(map);
			drawingManager.rectangle(shapeOptions);
			mapDrawListener = AMap.event.addListener(drawingManager, "draw",
					drawCompletedHandler);
		});
	}

	this.drawPolygon = function(drawCompletedHandler) {
		map.plugin([ "AMap.MouseTool" ], function() {
			drawingManager = new AMap.MouseTool(map);
			drawingManager.polygon(shapeOptions);
			mapDrawListener = AMap.event.addListener(drawingManager, "draw",
					drawCompletedHandler);
		});
	}
	
	this.drawLine = function(drawCompletedHandler) {
		map.plugin([ "AMap.MouseTool" ], function() {
			drawingManager = new AMap.MouseTool(map);
			drawingManager.polyline(shapeOptions);
			mapDrawListener = AMap.event.addListener(drawingManager, "draw",drawCompletedHandler);
		});
	}

	this.addPolygon = function(path, color) {
		if (!color) {
			color = "red";// "#F33",
		}
		var polygon = new AMap.Polygon({
			path : path
		});
		polygon.setOptions(shapeOptions);
		polygon.setMap(map);
		return polygon;
	}

	this.regeocoder = function(point, resultHandler) { // 逆地理编码
		map.plugin([ "AMap.Geocoder" ], function() {
			var geocoder = new AMap.Geocoder();
			geocoder.getAddress(point, function(status, result) {
				var address = "";
				if (status === 'complete' && result.info === 'OK') {
					address = result.regeocode.formattedAddress;
				}
				resultHandler(address);
			});
		});
	}
	
	this.wgs842gcj02 = function(path,resultHandler){
		AMap.convertFrom(path, 'gps', function (status, result) {
	        if (result.info === 'ok') {
	            resultHandler(result.locations);
	        }else{
	        	console.error(result.info);
	        }
	    });
	}
	
	this.graspDriving = function(path,resultHandler){
		 if(!graspRoad) {
	        graspRoad = new AMap.GraspRoad()
	     }
         graspRoad.driving(path,function(error,result){
		      if(!error){
		        resultHandler(result.data.points);
		      }else{
		    	  console.error(error);
		      }
         });
	}
}
