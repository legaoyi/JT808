
function addDevice(){
	var url = getContextPath() + "/addDevice.do";
	$("#main-frame")[0].contentWindow.showWindows(url,'600px','250px');
}