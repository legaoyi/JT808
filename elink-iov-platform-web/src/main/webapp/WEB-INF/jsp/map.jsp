<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/header.jsp"%>
<title>实时监控</title>
<style type="text/css">
	.layui-layer-setwin .layui-layer-close2 {
		right: -15px !important;
		top: -15px !important;
	}
</style>
</head>
<body>
	<!--内容部分-->
	<div class="con1" id="content-div">
		<div class="map_box" id="map_box"></div>
	</div>
	<%@ include file="/common/common.jsp"%>
	<%@ include file="mapScript.jsp"%>
	<script type="text/javascript" src="<c:url value='/js/mapControl.js?v=20200118'/>"></script>
</body>
</html>
