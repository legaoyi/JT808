jQuery.validator.addMethod("userAccount", function (value, element) {
    return this.optional(element) || (/^[a-zA-Z][a-zA-Z0-9_]{3,15}$/.test(value));
}, "用户名不合法（字母开头，允许4-16个字符串，允许字母数字下划线）");

jQuery.validator.addMethod("integer", function (value, element) {
    return this.optional(element) || (/^[+]?[1-9]+\d*$/i.test(value));
}, "请输入整数");

jQuery.validator.addMethod("chinese", function (value, element) {
    return this.optional(element) || (/^[A-Za-z]+$/i.test(value));
}, "请输入中文");

jQuery.validator.addMethod("ip", function (value, element) {
    return this.optional(element) || (/d+.d+.d+.d+/i.test(value));
}, "IP地址格式不正确");

jQuery.validator.addMethod("infoName", function (value, element) {
    return this.optional(element) || (/^[a-zA-Z0-9_\u4e00-\u9fa5]+$/i.test(value));
}, "请输入汉字、数字、字母或者下划线");