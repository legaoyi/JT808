<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/header.jsp"%>
<link rel="stylesheet" href="<c:url value='/css/nav.css'/>"/>
<title>新增设备</title>
<style type="text/css">
	.layui-layer-setwin .layui-layer-close2 {
		right: -15px !important;
		top: -15px !important;
	}
</style>
</head>
<body>
<div id="device-info-dlg">
		<form id="device-info-dlg-frm">
		    <div style="height:15px;"></div>
			<div class="message_con">
				<label>设备名称：</label> <input type="text" required placeholder="设备名称" class="form-control"  maxlength="16" id="name" name="name"><span class="must">*</span>
			</div>
			<div class="message_con">
				<label>SIM卡号：</label> <input type="text" required placeholder="SIM卡号" class="form-control"  maxlength="12" id="simCode" name="simCode"><span class="must">*</span>
			</div>
			<div class="message_con">
				<label>鉴权码：</label> <input type="text" required placeholder="鉴权码" class="form-control"  maxlength="7" id="authCode" name="authCode" value="123456"><span class="must">*</span>
			</div>
		</form>
		<div class="message_footer">
			<button type="button" class="bule" onclick="saveDeviceInfo();">保存</button>
			<button type="button" class="orgen" onclick="closeDialog();">取消</button>
		</div>
</div>
<%@ include file="/common/common.jsp"%>
</body>
</html>
<script type="text/javascript">
function saveDeviceInfo(){
	var formId = "device-info-dlg-frm";
	if (!validForm(formId)) {
		return;
	}
	var data = $("#"+formId).serializeObject();
	var url = "/common/device.do";
	ajaxAsyncPost(url, data, function(result){
		if (result.code != 0) {
			showErrorMessage(result.message);
		} else {
			showMessage("新增成功！");
			closeDialog();
		}
	});
}
</script>