function LocalStorage() {
	if(!window.localStorage){
		alert(alert("浏览器不支持localstorage"));
		return false;
	}
	this.localStorage = window.localStorage;

	this.put = function(key, value) {
		this.localStorage.setItem(key,value);
	};

	this.get = function(key) {
		return this.localStorage.getItem(key);
	};
}