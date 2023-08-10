function Cache(cacheName) {
	this.cacheName = cacheName;
	this.cache;
	if (window.localStorage) {
		this.cache = new LocalStorage();
	} else {
		this.cache = JsMap();
	}

	this.clear = function(){
		if (window.localStorage) {
			window.localStorage.clear();
		} else {
			this.cache = JsMap();
		}
	}

	this.put = function(key, value) {
		try{
			this.cache.put(this.cacheName + "_" + key, value);
		}catch(e){
			this.clear();
			this.cache.put(this.cacheName + "_" + key, value);
		}
	};

	this.get = function(key) {
		return this.cache.get(this.cacheName + "_" + key);
	};
	
	this.remove = function(key){
		if (window.localStorage) {
			window.localStorage.removeItem(this.cacheName + "_" + key);
		} else {
			this.cache.remove(this.cacheName + "_" + key);
		}
	};
}