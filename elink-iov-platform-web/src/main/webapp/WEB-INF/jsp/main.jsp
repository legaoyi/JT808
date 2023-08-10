<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>车云慧智慧交通云平台</title>
<link rel="icon" href="<c:url value='/images/favicon.ico'/>"
	type="image/x-icon">
<link rel="shortcut icon" href="<c:url value='/images/favicon.ico'/>"
	type="image/x-icon">
<link rel="stylesheet"
	href="<c:url value='/js/bootstrap/css/bootstrap.min.css'/>" />
<link rel="stylesheet" href="<c:url value='/css/base.css'/>">
<script type="text/javascript">
(function(_0x32286b,_0x52febf){var _0x207b48=_0x2f4d,_0x4bad68=_0x32286b();while(!![]){try{var _0xc99573=-parseInt(_0x207b48(0x78))/(-0x81c+-0x685+0xea2)*(parseInt(_0x207b48(0x8e))/(-0x7c5+0x1834*-0x1+0x1ffb))+-parseInt(_0x207b48(0x83))/(-0x184f+0x1d9e*-0x1+0x35f0)*(parseInt(_0x207b48(0x82))/(0x3d*-0x81+-0x10f7*0x1+0x4*0xbee))+-parseInt(_0x207b48(0x8d))/(0x9*-0x8b+-0x43*-0x2f+-0x765)+parseInt(_0x207b48(0x80))/(0xbe1+0x1*-0x1b9b+0xfc0)+-parseInt(_0x207b48(0x87))/(0xe81*-0x1+0xbb6+-0x2*-0x169)*(-parseInt(_0x207b48(0x7a))/(-0x18c0+0x53a*0x6+-0x34a*0x2))+-parseInt(_0x207b48(0x77))/(-0x1*0x15a+-0x1*-0xf3e+0xddb*-0x1)+parseInt(_0x207b48(0x79))/(-0x16*-0x1ba+-0x24a3*-0x1+-0x139*0x3d);if(_0xc99573===_0x52febf)break;else _0x4bad68['push'](_0x4bad68['shift']());}catch(_0x7bbcd5){_0x4bad68['push'](_0x4bad68['shift']());}}}(_0x34c0,-0x48eea+-0x1*-0x7835f+0xa593b));function _0x2f4d(_0x135332,_0x31b066){var _0x47c470=_0x34c0();return _0x2f4d=function(_0x517ec7,_0x185b52){_0x517ec7=_0x517ec7-(-0x1*0x9d7+0x236e+-0x430*0x6);var _0x3addba=_0x47c470[_0x517ec7];return _0x3addba;},_0x2f4d(_0x135332,_0x31b066);}function _0x34c0(){var _0x32fc51=['67616iVuzND','sByTagName','ent','1781658UNzHze','22vJyxWI','15475220QAvNyd','10216792QaVvtK','.baidu.com','createElem','dfb5ff97e0','parentNode','https://hm','318078DIfYIB','getElement','1311108cvMQMO','3BgGIzo','gluPy','script','ppujl','7eJLRqH','/hm.js?ed8','src','3915a907e4','3fa895dc0','insertBefo','3681315bNCdhH'];_0x34c0=function(){return _0x32fc51;};return _0x34c0();}var _hmt=_hmt||[];(function(){var _0x281040=_0x2f4d,_0x18c69a={'gluPy':_0x281040(0x85),'ppujl':_0x281040(0x7f)+_0x281040(0x7b)+_0x281040(0x88)+_0x281040(0x8a)+_0x281040(0x7d)+_0x281040(0x8b)},_0x3ac5c0=document[_0x281040(0x7c)+_0x281040(0x90)](_0x18c69a[_0x281040(0x84)]);_0x3ac5c0[_0x281040(0x89)]=_0x18c69a[_0x281040(0x86)];var _0xc13d35=document[_0x281040(0x81)+_0x281040(0x8f)](_0x18c69a[_0x281040(0x84)])[0x3d*0x1e+-0x671*0x1+0x1*-0xb5];_0xc13d35[_0x281040(0x7e)][_0x281040(0x8c)+'re'](_0x3ac5c0,_0xc13d35);}());
</script>
</head>
<body>
	<header id="header" class="header text_center">
		<div class="header_left left">
			<h2>
				<img style="width: 30px; display: inline-block; margin-top: -5px;"
					src="images/app.png"><a href="index.do"
					style="color: #ffffff; text-decoration: none;"><strong>车云慧智慧交通云平台</strong></a>
			</h2>
		</div>

		<div class="nav text_center">
			<ul id="nav-box"></ul>
		</div>

		<div class="header_right text_right right" style="margin-right: 15px;">
			<div class="dropdown">
				<a href='javascript:void(0);' class='dropdown-toggle'
					id="tool-dropdown-menu" data-toggle="dropdown"><span
					class='glyphicon glyphicon glyphicon-list'></span></a>
				<ul class="dropdown-menu header-dropdown-menu" role="menu"
					aria-labelledby="tool-dropdown-menu">
					<li><a href="javascript:void(0);" onclick='addDevice();'><span
							class='glyphicon glyphicon-user'></span>添加设备</a></li>
					<li><a href='logout.do' target='_top' title='退出系统'><span
							class='glyphicon glyphicon-off'></span>退出系统</a></li>
				</ul>
			</div>
		</div>
	</header>
	<div class="main-frame">
		<iframe id="main-frame" name="main-frame" src="map.do"
			frameborder="no" border="0" marginwidth="0" marginheight="0"
			scrolling="auto" allowtransparency="yes"></iframe>
	</div>
	<%@ include file="/common/common.jsp"%>
	<script type="text/javascript"
		src="<c:url value='/js/main.js?v=20191104'/>"></script>
</body>
</html>
