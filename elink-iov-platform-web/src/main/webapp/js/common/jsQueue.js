/*
 * @brief: 定义队列类
 * @remark:实现队列基本功能
 */
function Queue() {
	// 存储元素数组
	this.aElement = new Array();
	/*
	 * @brief: 元素入队 @param: vElement元素列表 @return: 返回当前队列元素个数 @remark:
	 * 1.EnQueue方法参数可以多个 2.参数为空时返回-1
	 */
	this.enQueue = function(element) {
		if (element == null)
			return -1;
		// 元素入队

		this.aElement.push(element);
		return this.aElement.length;
	}
	/*
	 * @brief: 元素出队 @return: vElement @remark: 当队列元素为空时,返回null
	 */
	this.deQueue = function() {
		if (this.aElement.length == 0)
			return null;
		else
			return this.aElement.shift();

	}
	/*
	 * @brief: 获取队列元素个数 @return: 元素个数
	 */
	this.getSize = function() {
		return this.aElement.length;
	}
	/*
	 * @brief: 返回队头素值 @return: vElement @remark: 若队列为空则返回null
	 */
	this.getHead = function() {
		if (this.aElement.length == 0)
			return null;
		else
			return this.aElement[0];
	}
	/*
	 * @brief: 返回队尾素值 @return: vElement @remark: 若队列为空则返回null
	 */
	this.getEnd = function() {
		if (this.aElement.length == 0)
			return null;
		else
			return this.aElement[this.aElement.length - 1];
	}
	/*
	 * @brief: 将队列置空
	 */
	this.makeEmpty = function() {
		this.aElement.length = 0;
	}
	/*
	 * @brief: 判断队列是否为空 @return: 队列为空返回true,否则返回false
	 */
	this.isEmpty = function() {
		if (this.aElement.length == 0)
			return true;
		else
			return false;
	}
	/*
	 * @brief: 将队列元素转化为字符串 @return: 队列元素字符串
	 */
	this.toString = function() {
		var sResult = (this.aElement.reverse()).toString();
		this.aElement.reverse()
		return sResult;
	}
}