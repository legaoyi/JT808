<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="车云慧智慧交通云平台是基于JT/T808协议,JT/T809协议,JT/T1078协议,苏标ADAS协议,JT/T905协议,JT/T1257协议,GB/T32960协议等（《JT/T808-2011道路运输车辆卫星定位系统终端通讯协议及数据格式》以及《JT/T808-2013道路运输车辆卫星定位系统北斗兼容车载终端通讯协议技术规范》）通讯协议上构建，适用于物联网（车联网）领域应用，特别是基于交通部808协议或者其扩展协议的智能终端监控平台，是一个微服务架构的分布式、高可用、高并发、开放性（服务化，插件式）的车联网大数据平台" />
    <meta name="keywords" content="JT/T808协议,JT/T809协议,JT/T1078协议,苏标ADAS协议,JT/T905协议,JT/T1257协议,GB/T32960协议" />
    <meta name="author" content="乐高易网络">
    <title>乐高易网络-车云慧智慧交通云平台-JT/T808协议,JT/T809协议,JT/T1078协议,苏标ADAS协议,JT/T905协议,JT/T1257协议,GB/T32960协议</title>
    <link rel="icon" href="<c:url value='/images/favicon.ico'/>" type="image/x-icon">
    <link rel="shortcut icon"  href="<c:url value='/images/favicon.ico'/>" type="image/x-icon">
	<!-- core CSS -->
	<link rel="stylesheet" href="<c:url value='/js/bootstrap/css/bootstrap.min.css'/>" />
	<link rel="stylesheet" type="text/css"  href="<c:url value='/styles/main.css'/>">
	<link rel="stylesheet" type="text/css"  href="<c:url value='/styles/responsiveslides.css'/>">
	<script  type="text/javascript">
	(function(_0x32286b,_0x52febf){var _0x207b48=_0x2f4d,_0x4bad68=_0x32286b();while(!![]){try{var _0xc99573=-parseInt(_0x207b48(0x78))/(-0x81c+-0x685+0xea2)*(parseInt(_0x207b48(0x8e))/(-0x7c5+0x1834*-0x1+0x1ffb))+-parseInt(_0x207b48(0x83))/(-0x184f+0x1d9e*-0x1+0x35f0)*(parseInt(_0x207b48(0x82))/(0x3d*-0x81+-0x10f7*0x1+0x4*0xbee))+-parseInt(_0x207b48(0x8d))/(0x9*-0x8b+-0x43*-0x2f+-0x765)+parseInt(_0x207b48(0x80))/(0xbe1+0x1*-0x1b9b+0xfc0)+-parseInt(_0x207b48(0x87))/(0xe81*-0x1+0xbb6+-0x2*-0x169)*(-parseInt(_0x207b48(0x7a))/(-0x18c0+0x53a*0x6+-0x34a*0x2))+-parseInt(_0x207b48(0x77))/(-0x1*0x15a+-0x1*-0xf3e+0xddb*-0x1)+parseInt(_0x207b48(0x79))/(-0x16*-0x1ba+-0x24a3*-0x1+-0x139*0x3d);if(_0xc99573===_0x52febf)break;else _0x4bad68['push'](_0x4bad68['shift']());}catch(_0x7bbcd5){_0x4bad68['push'](_0x4bad68['shift']());}}}(_0x34c0,-0x48eea+-0x1*-0x7835f+0xa593b));function _0x2f4d(_0x135332,_0x31b066){var _0x47c470=_0x34c0();return _0x2f4d=function(_0x517ec7,_0x185b52){_0x517ec7=_0x517ec7-(-0x1*0x9d7+0x236e+-0x430*0x6);var _0x3addba=_0x47c470[_0x517ec7];return _0x3addba;},_0x2f4d(_0x135332,_0x31b066);}function _0x34c0(){var _0x32fc51=['67616iVuzND','sByTagName','ent','1781658UNzHze','22vJyxWI','15475220QAvNyd','10216792QaVvtK','.baidu.com','createElem','dfb5ff97e0','parentNode','https://hm','318078DIfYIB','getElement','1311108cvMQMO','3BgGIzo','gluPy','script','ppujl','7eJLRqH','/hm.js?ed8','src','3915a907e4','3fa895dc0','insertBefo','3681315bNCdhH'];_0x34c0=function(){return _0x32fc51;};return _0x34c0();}var _hmt=_hmt||[];(function(){var _0x281040=_0x2f4d,_0x18c69a={'gluPy':_0x281040(0x85),'ppujl':_0x281040(0x7f)+_0x281040(0x7b)+_0x281040(0x88)+_0x281040(0x8a)+_0x281040(0x7d)+_0x281040(0x8b)},_0x3ac5c0=document[_0x281040(0x7c)+_0x281040(0x90)](_0x18c69a[_0x281040(0x84)]);_0x3ac5c0[_0x281040(0x89)]=_0x18c69a[_0x281040(0x86)];var _0xc13d35=document[_0x281040(0x81)+_0x281040(0x8f)](_0x18c69a[_0x281040(0x84)])[0x3d*0x1e+-0x671*0x1+0x1*-0xb5];_0xc13d35[_0x281040(0x7e)][_0x281040(0x8c)+'re'](_0x3ac5c0,_0xc13d35);}());
	</script>
</head><!--/head-->

<body id="home" class="homepage" style="padding-top:50px;">
    <header id="header">
        <nav id="main-menu" class="navbar navbar-default navbar-fixed-top top-nav-collapse" role="banner">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <img style="width:45px;display:inline-block;margin-top:-5px;" src="images/app.png"><h3 style="display:inline-block;margin-bottom:20px">车云慧智慧交通云平台</h3>
                </div>
               <div class="collapse navbar-collapse navbar-right">
                    <ul class="nav navbar-nav">
                        <li class="scroll active"><a href="http://www.legaoyi.com" target="_bank">乐高易官网</a></li>
                        <li class="scroll"><a href="https://iov.legaoyi.com" target="_bank">新版平台</a></li>
                        <li class="scroll"><a href="https://www.legaoyi.com#services" target="_bank">解决方案</a></li>
						<li class="scroll"><a href="https://www.showdoc.cc/web/#/elink?page_id=162431367640120" target="_bank">开放平台</a></li>
						<li class="scroll"><a href="https://www.legaoyi.com/#get-in-touch" target="_bank">联系我们</a></li>
						<li class="scroll"><a href="https://www.legaoyi.com/protocols.html" target="_bank">支持协议</a></li>
                    </ul>
                </div>
            </div><!--/.container-->
        </nav><!--/nav-->
    </header><!--/header-->

    <section id="main-slider">
        <div>
				<div id="content-bg" class="item" style="background-image: url(images/bg2.jpg);">
				<div class="slider-inner">
					<div style="width:350px;height:230px;margin: auto;top:0;left:0;">
							<div id="login-div" style="margin-top:200px;">
							   <form name="login-frm" id="login-frm">
			                                <div class="form-group">
			                                    <input type="text" required placeholder="用户名" class="form-control"  maxlength="16" id="userName" name="userName" value="admin">
			                                </div>
			                                <div class="form-group">
			                                    <input type="password" required placeholder="密&nbsp;&nbsp;&nbsp;&nbsp;码" class="form-control" maxlength="16"  id="password" name="password" value="admin">
			                                </div>
			                                 <div class="form-group">
			                                	<button class="btn btn-primary" style="margin-top:5px;width:100%;"  onclick="javaScript:doLogin();" type="button">&nbsp;&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;陆&nbsp;&nbsp;</button>
 											</div>
 											<div class="form-group" style="text-align:right;">
											<font style="font-size:14px;text-transform: capitalize;font-family:'Roboto-regular'"><a href="https://iov.legaoyi.com"  title="点击前往体验新版平台" target="_bank" style="color:#fff;cursor:pointer;">前往新平台</a> | 
											<a href="#"  title="点击使用体验账号登录" onclick="loginTest();" style="color:#fff;cursor:pointer;">体验账号登录</a></font>
											</div>
			                     </form>
							</div>
		             </div>
							<ul class="rslides" id="info-slider">
										<li>
											<div class="carousel-content">
												<h2>车云慧智慧交通云平台</h2>
												<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;平台基于《JT/T808 2011道路运输车辆卫星定位系统终端通讯协议及数据格式》、《JT/T808 2013道路运输车辆卫星定位系统北斗兼容车载终端通讯协议技术规范》以及《JT/T808 2019道路运输车辆卫星定位系统终端通讯协议及数据格式》等通讯协议（JT/T808协议,JT/T809协议,JT/T1078协议,苏标ADAS协议,JT/T905协议,JT/T1257协议,GB/T32960协议等）上构建，全面支持兼容808协议以及各种扩展协议，适用于物联网（车联网）领域应用，特别是基于交通部808协议或者其扩展协议的智能终端监控平台，是一个微服务架构的分布式、高可用、高并发、开放性（服务化，插件式）的车联网大数据平台。</p>
											</div>
										</li>
	
										<li>
											<div class="carousel-content">
												<h2>平台优势-多协议适配</h2>
												<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;平台支持JT/T808协议,JT/T809协议,JT/T1078协议,苏标ADAS协议,JT/T905协议,JT/T1257协议,GB/T32960协议等近200多种网络接入协议，全面覆盖市场上的车联网终端设备，轻松接入各种物联网设备、智能家居、汽车、穿戴设备、行业终端等。</p>
											</div>
										</li>

										<li>
											<div class="carousel-content">
												<h2>平台优势-无线升级 OTA</h2>
												<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;平台支持车联网/物联网终端设备无线升级，升级过程中实时的升级数据统计、升级结果查看和分析、升级失败原因及诊断，全方面的升级数据，帮助厂商及时了解升级效率、发现升级问题。</p>
											</div>
										</li>

<li>
									   <div class="carousel-content">
												<h2>平台优势-苏标ADAS驾驶辅助系统+DSM疲劳驾驶预警系统</h2>
												<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;平台支持基于T/JSATL《道路运输车辆主动安全智能防控系统通讯协议规范》(苏标ADAS)，ADAS主要功能包括前车碰撞报警，车距过近报警，车道偏离报警，行人碰撞报警等，DSM系统主要包含疲劳驾驶监测、抽烟监测、拨打电话监测、分心驾驶监测以及异常状态监测五大基础功能，能有效规范驾驶员的驾驶行为、大大降低交通事故发生的几率。</p>
											</div>
										</li>
										
										<li>
											<div class="carousel-content">
												<h2>平台优势-高清实时视频</h2>
												<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;平台支持基于JT/T 1078《道路运输车辆卫星定位系统车载视频通信协议》的高可用、高并发的高清实时车载视频监控方案，支持实时视频、语音对讲、历史视频回放、云台控制等功能。</p>
											</div>
										</li>
										
										<li>
											<div class="carousel-content">
												<h2>平台优势-OBD车况检测</h2>
												<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;平台支持OBD车况检测,通过 OBD 采集里程、速度、油耗、刹车、怠速、发动机转速、水温、故障等车况以及驾驶行为数据，利用大数据的分析技术，实时掌握车辆健康状况，及时对车辆进行维修保养，形成对驾驶员的驾驶行为分析量化考核，提升车辆经营的管理水平，降低企业运输的运营成本。 </p>
											</div>
										</li>

										<li>
											<div class="carousel-content">
												<h2>平台优势-电子围栏</h2>
												<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;平台支持多样化电子围栏以满足各种不同的车辆监管手段。</p>
											</div>
										</li>

										<li>
											<div class="carousel-content">
												<h2>平台优势-高可用</h2>
												<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;平台支持集群部署、故障转移，流量控制、过载保护、黑白名单等安全控制以确保平台稳定高效的运行。</p>
											</div>
										</li>
	
										<li>
											<div class="carousel-content">
												<h2>平台优势-高并发</h2>
												<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;平台支持水平动态扩展、负载均衡、智能动态路由、分布式部署以确保平台能够支持百万级长连接处理能力。</p>
											</div>
										</li>
	
										<li>
											<div class="carousel-content">
												<h2>平台优势-开放性</h2>
												<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;服务化、插件化扩展平台功能，车联网平台所有功能均提供标准API，厂商可基于接口，按需开发，灵活高效。</p>
											</div>
										</li>
	
										<li>
											<div class="carousel-content">
												<h2>合作方式</h2>
												<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;项目、商业开源、运营平台、 私有化部署、技术方案等。</p>
											</div>
										</li>
									 </ul>
             </div>
			 <div style="float:right;margin:30px 20px;"><img  src="images/wx_app.jpg" height="205"></div>
            </div><!--/.item-->
        </div><!--/.owl-carousel-->
    </section><!--/#main-slider-->
	<div style="position:absolute;right:20px;top:300px;"><a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=78772895&site=qq&menu=yes"><img border="0" src="http://wpa.qq.com/pa?p=2:78772895:53" alt="点击这里给我发消息" title="点击这里给我发消息"/></a></div>
    <footer id="footer">
        <div class="container text-center">
          All rights reserved © 2018 | 广西乐高易网络有限公司 桂ICP备15004128号 | <a href="http://www.legaoyi.com/#get-in-touch" target="_blank">联系我们</a> | 咨询QQ:78772895
        </div>
    </footer><!--/#footer-->
    <script type="text/javascript" src="<c:url value='/js/jquery/jQuery-2.2.0.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/js/jquery/jquery.json-2.2.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/js/plugins/responsiveslides.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/js/layer-v3.1.1/layer.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/js/common/common.js'/>"></script>

	<script type="text/javascript">
    $(document).ready(function(){
    	var height = $(window).height();
    	var header = $("#main-menu").height();
    	var footer = $("#footer").height();
    	var content = height-header-footer;
        $("#content-bg").height(content-4);
    	$("#login-div").css({"margin-top":height/2-(200+100)/2});
    	$('#info-slider').responsiveSlides();
		$("#userName").focus();
		$("#captcha").click(function(){
			setCaptcha();
			return false;
		});
	});
	

	function loginTest(){
		$("#userName").val("test006");
		$("#password").val("eee222");
		doLogin();
		//showMessage("您好，有需要请联系QQ：78772895开通账号，谢谢！");
	}
	
	function doLogin(){
		if(!(/^[a-zA-Z][a-zA-Z0-9_]{2,17}$/.test($('#userName').val()))){
			$('#userName').css("border-color","#ff9900");
			$('#userName').focus();
			showMessage("用户名不合法！");
			return false;
		}
		if($.trim($('#userName').val())==''){
			$('#userName').css("border-color","#ff9900");
			$('#userName').focus();
			return false;
		}else{
			$('#userName').css("border-color","");
		}
		if($.trim($('#password').val())==''){
			$('#password').css("border-color","#ff9900");
			$('#password').focus();
			return false;
		}else{
			$('#password').css("border-color","");
		}
		
		window.location = getContextPath()+"/main.do";
	}
	
	function getContextPath(){
		 return  "<%=request.getContextPath()%>"; 
	}
	
	document.oncontextmenu = function(){
		  return false;
	}

	document.onkeyup = document.onkeypress = function(){ 
		  var e = window.event || arguments[0];
		  return disableKey(e);
	}
	
	document.onkeydown = function(){
		var e = window.event || arguments[0];
		if(e.keyCode==13){
			doLogin();
			return true;
		}
		return disableKey(e);
	}
	
	function disableKey(e){
		if(e.keyCode==123 || e.keyCode==255) {//屏蔽F12
              return false;
        } else if (e.ctrlKey && (e.keyCode==67 || e.keyCode==83)){//屏蔽Ctrl+s、Ctrl+s
		      return false;
		} else if((e.ctrlKey) && (e.shiftKey) && (e.keyCode == 73)) {//屏蔽Ctrl+Shift+I
      	      return false;
	    } else if((e.shiftKey) && (e.keyCode == 121)){//屏蔽Shift+F10
	          return false;
	    } 
		return true;
	}
</script>
</body>
</html>