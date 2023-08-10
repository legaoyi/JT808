function asyncPostRequest(url, method, data, callback, showLoading) {
	$.ajax({
		type : method,
		url : getContextPath() + url,
		data : $.toJSON(data),
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : callback,
		beforeSend : function() {
			if (showLoading) {
				startLoading();
			}
		},
		complete : function(data) {
			if (showLoading) {
				endLoading();
			}
		},
		error : function(data) {
			showErrorMessage(data.responseText);
		}
	});
}

function syncPostRequest(url, method, data, showLoading) {
	var result;
	$.ajax({
		type : method,
		url : getContextPath() + url,
		data :  $.toJSON(data),
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		async : false,
		success : function(data) {
			result = data;
		},
		beforeSend : function() {
			if (showLoading) {
				startLoading();
			}
		},
		complete : function(data) {
			if (showLoading) {
				endLoading();
			}
		},
		error : function(data) {
			showErrorMessage(data.responseText);
		}
	});
	return result;
}

function ajaxAsyncPost(url, data, callback, showLoading) {
	asyncPostRequest(url, "post", data, callback, showLoading);
}

function ajaxAsyncPut(url, data, callback, showLoading) {
	asyncPostRequest(url, "put", data, callback, showLoading);
}

function ajaxAsyncPatch(url, data, callback, showLoading) {
	asyncPostRequest(url, "patch", data, callback, showLoading);
}

function ajaxAsyncGet(url, data, callback, showLoading) {
	asyncPostRequest(url, "get", data, callback, showLoading);
}

function ajaxAsyncDel(url, data, callback, showLoading) {
	asyncPostRequest(url, "delete", data, callback, showLoading);
}

function ajaxSyncPost(url, data, showLoading) {
	return syncPostRequest(url, "post", data, showLoading);
}

function ajaxSyncPut(url, data, showLoading) {
	return syncPostRequest(url, "put", data, showLoading);
}

function ajaxSyncPatch(url, data, showLoading) {
	return syncPostRequest(url, "patch", data, showLoading);
}

function ajaxSyncGet(url, data, showLoading) {
	return syncPostRequest(url, "get", data, showLoading);
}

function ajaxSyncDel(url, data, showLoading) {
	return syncPostRequest(url, "delete", data, showLoading);
}
