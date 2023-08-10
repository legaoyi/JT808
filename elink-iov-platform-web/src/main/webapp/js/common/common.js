function showAlertMessage(message) {
	layer.alert(message, {
		offset : '100px',
		skin: 'show_alert_box'
	});
}

function showMessage(message) {
	layer.msg(message, {
		offset : '100px',
		time: 3000,
		skin: 'show_message_box'
	});
}

function showErrorMessage(message) {
	message = (message == null || message == "") ? "系统异常" : message;
	showMessage(message);
}

function showTips(id, content) {
	layer.tips(content, id, {
		tipsMore : true,
		skin: 'show_taps_box'
	});
}

function showImg(title, url) {
	var json = {
		"title" : "", // 相册标题
		"id" : 1, // 相册id
		"start" : 0, // 初始显示的图片序号，默认0
		"data" : [ // 相册包含的图片，数组格式
		{
			"alt" : title,
			"pid" : 1, // 图片id
			"src" : url, // 原图地址
			"thumb" : "" // 缩略图地址
		} ]
	};
	layer.photos({
		photos : json,
		anim : 5
	});
}

/**
 * 
 * @param title
 *            标题
 * @param objId
 *            DOM对象id
 * @returns
 */
function showDialog(title, objId,onClose) {
	var width = $("#" + objId).outerWidth(true);
	var height = $("#" + objId).outerHeight(true) + 35;
	layer.open({
		type : 1,
		skin : 'layui-layer-lan',
		title : title,
		fix : true,
		resize:false,
		offset : '60px',
		zIndex : '99',
		area : [ width + 'px', height + 'px' ], // 宽高
		// shadeClose : true,
		content : $("#" + objId),
		end:function(){
			if(onClose){
				onClose.apply(this);
			}
		}
	});
}

function showWindows(url,width,height) {
	
	layer.open({
		type: 2,
		title: false,
		closeBtn: 2,
		shadeClose: true,
		shade: 0.8,
		area: [width?width:'100%', height?height:'100%'],
		content: url
	});
}

function closeDialog() {
	layer.closeAll('page');
}

function showIframe(title,url,width,height){
	layer.open({
		  type: 2,
		  title: title,
		  shadeClose: true,
		  shade: 0.8,
		  area : [ width + 'px', height + 'px' ],
		  content: url
		});
}

function closeIframe(){
	layer.closeAll('iframe'); 
}

function showConfirm(content, yesHandler) {
	layer.msg(content, {
		time : 0,
		btn : [ '确定', '取消' ],
		yes : function(index){
			layer.close(index);
			yesHandler.apply(this);
		},
		skin: 'show_confirm_box'
	});
}

function startLoading() {
	layer.load();
}

function endLoading() {
	layer.closeAll('loading');
}