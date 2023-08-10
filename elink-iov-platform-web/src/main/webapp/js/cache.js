var car_plate_number_cache = new Cache("car_plate_number_cache");

$(function() {
	car_plate_number_cache.clear();
});

function getDeviceCache(deviceId) {
		var url = management_api_server_servlet_path + "/common/device/" + deviceId + ".json";
		var result = ajaxSyncGet(url, {});
		if (result.code != 0) {
			showErrorMessage(result.message);
			return null;
		}
		return result.data;
}

function getCarCache(carId) {
	var url = management_api_server_servlet_path + "/common/car/" + carId + ".json";
	var result = ajaxSyncGet(url, {});
	if (result.code != 0) {
		showErrorMessage(result.message);
		return null;
	}
	return result.data;
}

function getPlateNumberByDeviceId(deviceId){
	var plateNumber = car_plate_number_cache.get(deviceId);
	if(plateNumber){
		return plateNumber;
	}
	var car = getDeviceCarCache(deviceId);
	if (car) {
		car_plate_number_cache.put(deviceId,car.plateNumber);
		return car.plateNumber;
	}
	return "";
}

function getDeviceCarCache(deviceId) {
	var url = management_api_server_servlet_path + "/device/"+deviceId+"/car.json";
	var result = ajaxSyncGet(url,{});
	if (result.code != 0) {
		showErrorMessage(result.message);
	}else{
		return result.data;
	}
}

function getEnterprise(enterpriseId){
	var url = management_api_server_servlet_path + "/common/enterprise/" + enterpriseId + ".json";
	var result = ajaxSyncGet(url, {});
	if (result.code != 0) {
		showErrorMessage(result.message);
	}
	return enterprise = result.data;
}