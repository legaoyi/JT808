/**
 * js实现list
 * 
 */
function List() {
	this.value = [];

	/* 添加 */
	this.add = function(obj) {
		return this.value.push(obj);
	};

	/* 大小 */
	this.size = function() {
		return this.value.length;
	};

	/* 返回指定索引的值 */
	this.get = function(index) {
		return this.value[index];
	};

	/* 删除指定索引的值 */
	this.remove = function(index) {
		this.value.splice(index, 1);
		return this.value;
	};

	/* 删除全部值 */
	this.removeAll = function() {
		return this.value = [];
	};

	/* 是否包含某个对象 */
	this.constains = function(obj) {
		for ( var i in this.value) {
			if (obj == this.value[i]) {
				return true;
			} else {
				continue;
			}
		}
		return false;
	};

	/* 是否包含某个对象 */
	this.getAll = function() {
		var allInfos = '';
		for ( var i in this.value) {
			if (i != (value.length - 1)) {
				allInfos += this.value[i] + ",";
			} else {
				allInfos += this.value[i];
			}
		}
		return allInfos += this.value[i] + ",";
		;
	};
}